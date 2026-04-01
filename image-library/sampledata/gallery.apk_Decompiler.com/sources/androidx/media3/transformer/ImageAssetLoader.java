package androidx.media3.transformer;

import F2.D0;
import F2.Y;
import P1.e;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Looper;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.ConstantRateTimestampIterator;
import androidx.media3.transformer.AssetLoader;
import c0.C0088c;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.s;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ImageAssetLoader implements AssetLoader {
    private final BitmapLoader bitmapLoader;
    private final Context context;
    private final EditedMediaItem editedMediaItem;
    /* access modifiers changed from: private */
    public final AssetLoader.Listener listener;
    /* access modifiers changed from: private */
    public volatile int progress;
    private int progressState;
    /* access modifiers changed from: private */
    public final boolean retainHdrFromUltraHdrImage;
    private SampleConsumer sampleConsumer;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduledExecutorService;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory implements AssetLoader.Factory {
        private final BitmapLoader bitmapLoader;
        private final Context context;

        public Factory(Context context2, BitmapLoader bitmapLoader2) {
            this.context = context2;
            this.bitmapLoader = bitmapLoader2;
        }

        public AssetLoader createAssetLoader(EditedMediaItem editedMediaItem, Looper looper, AssetLoader.Listener listener, AssetLoader.CompositionSettings compositionSettings) {
            return new ImageAssetLoader(this.context, editedMediaItem, listener, this.bitmapLoader, compositionSettings.retainHdrFromUltraHdrImage);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: queueBitmapInternal */
    public void lambda$queueBitmapInternal$1(Bitmap bitmap, Format format) {
        try {
            SampleConsumer sampleConsumer2 = this.sampleConsumer;
            if (sampleConsumer2 == null) {
                this.sampleConsumer = this.listener.onOutputFormat(format);
                this.scheduledExecutorService.schedule(new C0088c(this, bitmap, format, 0), 10, TimeUnit.MILLISECONDS);
                return;
            }
            EditedMediaItem editedMediaItem2 = this.editedMediaItem;
            int queueInputBitmap = sampleConsumer2.queueInputBitmap(bitmap, new ConstantRateTimestampIterator(editedMediaItem2.durationUs, (float) editedMediaItem2.frameRate));
            if (queueInputBitmap == 1) {
                this.progress = 100;
                this.sampleConsumer.signalEndOfVideoInput();
            } else if (queueInputBitmap == 2) {
                this.scheduledExecutorService.schedule(new C0088c(this, bitmap, format, 1), 10, TimeUnit.MILLISECONDS);
            } else if (queueInputBitmap == 3) {
                this.progress = 100;
            } else {
                throw new IllegalStateException();
            }
        } catch (ExportException e) {
            this.listener.onError(e);
        } catch (RuntimeException e7) {
            this.listener.onError(ExportException.createForAssetLoader(e7, 1000));
        }
    }

    public Y getDecoderNames() {
        return D0.k;
    }

    public int getProgress(ProgressHolder progressHolder) {
        if (this.progressState == 2) {
            progressHolder.progress = this.progress;
        }
        return this.progressState;
    }

    public void release() {
        this.progressState = 0;
        this.scheduledExecutorService.shutdownNow();
    }

    /* JADX WARNING: type inference failed for: r1v7, types: [com.google.common.util.concurrent.q, java.lang.Object] */
    public void start() {
        ListenableFuture listenableFuture;
        this.progressState = 2;
        this.listener.onDurationUs(this.editedMediaItem.durationUs);
        this.listener.onTrackCount(1);
        String imageMimeType = TransformerUtil.getImageMimeType(this.context, this.editedMediaItem.mediaItem);
        if (imageMimeType == null || !this.bitmapLoader.supportsMimeType(imageMimeType)) {
            ParserException createForUnsupportedContainerFeature = ParserException.createForUnsupportedContainerFeature("Attempted to load a Bitmap from unsupported MIME type: " + imageMimeType);
            createForUnsupportedContainerFeature.getClass();
            ? obj = new Object();
            obj.setException(createForUnsupportedContainerFeature);
            listenableFuture = obj;
        } else {
            listenableFuture = this.bitmapLoader.loadBitmap(((MediaItem.LocalConfiguration) Assertions.checkNotNull(this.editedMediaItem.mediaItem.localConfiguration)).uri);
        }
        AnonymousClass1 r1 = new s() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onSuccess$0(Bitmap bitmap, Format format) {
                ImageAssetLoader.this.lambda$queueBitmapInternal$1(bitmap, format);
            }

            public void onFailure(Throwable th) {
                ImageAssetLoader.this.listener.onError(ExportException.createForAssetLoader(th, 2000));
            }

            public void onSuccess(Bitmap bitmap) {
                int unused = ImageAssetLoader.this.progress = 50;
                Format build = new Format.Builder().setHeight(bitmap.getHeight()).setWidth(bitmap.getWidth()).setSampleMimeType("image/raw").setColorInfo(ColorInfo.SRGB_BT709_FULL).build();
                Format build2 = (!ImageAssetLoader.this.retainHdrFromUltraHdrImage || Build.VERSION.SDK_INT < 34 || !bitmap.hasGainmap()) ? build : build.buildUpon().setSampleMimeType("image/jpeg_r").build();
                try {
                    ImageAssetLoader.this.listener.onTrackAdded(build, 2);
                    ImageAssetLoader.this.scheduledExecutorService.submit(new e(this, bitmap, build2, 0));
                } catch (RuntimeException e) {
                    ImageAssetLoader.this.listener.onError(ExportException.createForAssetLoader(e, 1000));
                }
            }
        };
        listenableFuture.addListener(new e(listenableFuture, r1, false, 7), this.scheduledExecutorService);
    }

    private ImageAssetLoader(Context context2, EditedMediaItem editedMediaItem2, AssetLoader.Listener listener2, BitmapLoader bitmapLoader2, boolean z) {
        boolean z3 = true;
        Assertions.checkState(editedMediaItem2.durationUs != -9223372036854775807L);
        Assertions.checkState(editedMediaItem2.frameRate == -2147483647 ? false : z3);
        this.context = context2;
        this.editedMediaItem = editedMediaItem2;
        this.listener = listener2;
        this.bitmapLoader = bitmapLoader2;
        this.retainHdrFromUltraHdrImage = z;
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.progressState = 0;
    }
}
