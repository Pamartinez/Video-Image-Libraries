package com.samsung.android.gallery.app.controller.viewer;

import A.a;
import Ba.h;
import G7.e;
import O3.u;
import U5.c;
import U9.b;
import W3.C0412b;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.c2pa.C2paWrapper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.fileio.redact.FileRedactorBuilder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefFileCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeoutWorker;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveCaptureCmd extends BaseCommand {
    static final Object FILE_WRITE_LOCK = new Object();
    private static int sGlobalId;
    private Consumer<EventMessage> mCallback;
    private Uri mCapturedUri;
    private int mErrorCase = 0;
    private boolean mIsDownloadedSharedVideo;
    private boolean mIsFavoriteItem;
    protected MediaItem mMediaItem;
    private long mOriginalMediaId;
    protected String mOriginalPath;
    private final InstantSubscriberListener mQuickCropDataChanged = new e(4, this);
    protected int mSessionId;
    private QuickCropShareUri mShareUri;

    public SaveCaptureCmd() {
        int i2 = sGlobalId;
        sGlobalId = i2 + 1;
        this.mSessionId = i2;
        this.TAG = "CaptureCmd/" + this.mSessionId;
    }

    private Bitmap captureScreen(ImageView imageView, Rect rect) {
        Bitmap bitmap;
        Log.d(this.TAG, "2. captureScreen from view");
        Bitmap[] bitmapArr = new Bitmap[1];
        if (!new TimeoutWorker(3000).postOnUi(new c(13, imageView, bitmapArr)) || (bitmap = bitmapArr[0]) == null) {
            return null;
        }
        return BitmapUtils.crop(bitmap, rect);
    }

    /* access modifiers changed from: private */
    /* renamed from: captureToFitOriginal */
    public void lambda$executeCapture$0(MediaItem mediaItem, ImageView imageView, Rect rect, Rect rect2) {
        Log.d(this.TAG, "1. captureToFitOriginal");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Bitmap captureScreen = captureScreen(imageView, rect);
            if (captureScreen == null) {
                this.mErrorCase = 0;
                sendCompleteEventIfFailed();
                return;
            }
            publishPreviewBitmap(mediaItem, captureScreen);
            sendProcessingEvent();
            RectUtils.rotate(rect2, mediaItem.getWidth(), mediaItem.getHeight(), mediaItem.getOrientation());
            saveCapturedFile(mediaItem, new SecureFile(this.mOriginalPath), rect2);
            String str = this.TAG;
            Log.d(str, "captureToFitOriginal completed" + Logger.vt(currentTimeMillis));
        } catch (Exception e) {
            String str2 = this.TAG;
            Log.e(str2, "captureToFitOriginal failed e=" + e.getMessage());
            isNullThenSendEvent((Object) null);
        }
    }

    private Object[] composeMessage(MediaItem mediaItem) {
        return new Object[]{Long.valueOf(this.mOriginalMediaId), mediaItem, buildUri()};
    }

    private void copyAndScan(MediaItem mediaItem, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean copy = FileUtils.copy(str, str2);
        if (copy) {
            FileUtils.setDateModified(str2, FileUtils.getDateModified(this.mOriginalPath) + 1000);
            if (mediaItem.isVideo()) {
                scanFile(str2);
            } else {
                runC2PA(mediaItem, str2, new b(5, this), new h(19, this));
            }
            FileUtils.delete(str);
        } else {
            sendCompleteEventIfFailed();
        }
        String str3 = this.TAG;
        a.A(new Object[]{Boolean.valueOf(copy), Long.valueOf(currentTimeMillis)}, new StringBuilder("4. copyAndScan"), str3);
    }

    private boolean isDownloadedSharedVideo(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY) || !mediaItem.isSharing() || !MediaItemMde.isDownloadVideoVerified(mediaItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$captureScreen$1(ImageView imageView, Bitmap[] bitmapArr) {
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        if (width > 0 && height > 0) {
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            imageView.layout(0, 0, width, height);
            imageView.draw(canvas);
            bitmapArr[0] = createBitmap;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$copyAndScan$10(String str, Uri uri) {
        onScanComplete(uri);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$copyAndScan$9(Boolean bool) {
        Log.d(this.TAG, "c2pa applyed", bool);
        getBlackboard().post("command://event/DataDirty", (Object) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onScanComplete$3() {
        new TagEditApi().setFavorite(this.mCapturedUri, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onUpdateQuickCropMediaItem$6() {
        MediaItem mediaItem;
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mCapturedUri);
        ArrayList arrayList2 = new ArrayList();
        if (UriItemLoader.loadMediaItemFromUris(arrayList, arrayList2)) {
            if (arrayList2.size() > 0) {
                mediaItem = (MediaItem) arrayList2.get(0);
            } else {
                mediaItem = null;
            }
            if (mediaItem != null) {
                updateThumbnail(mediaItem);
            }
            this.mCapturedUri = null;
            return;
        }
        Log.e((CharSequence) this.TAG, "onUpdateQuickCropMediaItem invalid uri", this.mCapturedUri);
        sendCompleteEventIfFailed();
        if (this.mCapturedUri != null) {
            sendCompleteEvent((Object[]) null);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveCapturedFileWithBitmap$8() {
        getBlackboard().post("command://update/MediaItem/QuickCrop", (Object) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$scanFile$2(String str, Uri uri) {
        onScanComplete(uri);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendCompleteEvent$5(Object[] objArr) {
        Consumer<EventMessage> consumer = this.mCallback;
        if (consumer != null) {
            consumer.accept(EventMessage.obtain(3025, 0, 0, objArr));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendProcessingEvent$4() {
        Consumer<EventMessage> consumer = this.mCallback;
        if (consumer != null) {
            consumer.accept(EventMessage.obtain(3024, 0, 0, Long.valueOf(this.mOriginalMediaId)));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateThumbnail$7(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        sendCompleteEvent(composeMessage(mediaItem));
    }

    /* access modifiers changed from: private */
    public void notifyComplete() {
        subscribeEvent();
        sendCompleteEventIfFailed();
        BlackboardUtils.forceRefreshPicturesDataGlobal();
    }

    private void onScanComplete(Uri uri) {
        Log.d(this.TAG, "onScanComplete", uri);
        this.mCapturedUri = uri;
        if (uri == null || !this.mIsFavoriteItem) {
            notifyComplete();
            return;
        }
        ThreadUtil.runOnBgThread(new C0412b(this, 0));
        ThreadUtil.postOnBgThreadDelayed(new C0412b(this, 1), 100);
    }

    /* access modifiers changed from: private */
    public void onUpdateQuickCropMediaItem(Object obj, Bundle bundle) {
        boolean z;
        String str = this.TAG;
        if (this.mCapturedUri != null) {
            z = true;
        } else {
            z = false;
        }
        Log.d(str, "5. onUpdateQuickCropMediaItem", Boolean.valueOf(z));
        if (this.mCapturedUri != null) {
            ThreadUtil.postOnBgThreadDelayed(new C0412b(this, 4), 100);
        }
    }

    private void runC2PA(MediaItem mediaItem, String str, Consumer<Boolean> consumer, BiConsumer<String, Uri> biConsumer) {
        MediaItem mediaItem2 = new MediaItem();
        mediaItem2.setPath(str);
        FileRedactorBuilder.create((FileItemInterface) mediaItem2, (FileItemInterface) mediaItem).setUseFileDescriptor(false).setCallback(consumer).setManifest(C2paWrapper.Manifest.actionEdit).withMediaScan(biConsumer).build().run();
    }

    private void saveBuffer(String str, ByteBuffer byteBuffer) {
        FileOutputStream fileOutputStream;
        synchronized (FILE_WRITE_LOCK) {
            try {
                fileOutputStream = new FileOutputStream(new SecureFile(str));
                FileChannel channel = fileOutputStream.getChannel();
                channel.write(byteBuffer);
                channel.close();
                fileOutputStream.close();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "saveBuffer failed", (Throwable) e);
                FileUtils.delete(str);
                throw e;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return;
        throw th;
    }

    private void saveSefHdr(String str, MediaItem mediaItem) {
        SefFileCompat sefFileCompat = SeApiCompat.getSefFileCompat();
        File file = new File(str);
        try {
            SefInfo sefInfo = SefInfo.PHOTO_HDR_INFO;
            sefFileCompat.addData(file, sefInfo.keyName, new byte[]{0}, sefInfo.keyCode);
        } catch (IOException unused) {
            Log.e(this.TAG, "saveSef failed");
        }
    }

    private void subscribeEvent() {
        if (this.mCapturedUri != null && this.mCallback != null) {
            getBlackboard().subscribe("command://update/MediaItem/QuickCrop", this.mQuickCropDataChanged);
        }
    }

    public SaveCaptureCmd andGo(boolean z, String str, String str2) {
        this.mShareUri = new QuickCropShareUri(z, str, str2);
        return this;
    }

    public String buildUri() {
        QuickCropShareUri quickCropShareUri = this.mShareUri;
        if (quickCropShareUri != null) {
            return quickCropShareUri.getUri();
        }
        return null;
    }

    public void executeCapture(MediaItem mediaItem, Object... objArr) {
        boolean z = true;
        ImageView imageView = objArr[1];
        Rect rect = objArr[2];
        Rect rect2 = objArr[3];
        if (!FileUtils.exists(this.mOriginalPath)) {
            Log.e((CharSequence) this.TAG, "executeCapture failed. invalid path", Logger.getEncodedString(this.mOriginalPath));
            sendCompleteEventIfFailed();
        }
        if (imageView == null || rect == null || rect2 == null || rect2.width() <= 0 || rect2.height() <= 0) {
            String str = this.TAG;
            if (imageView == null) {
                z = false;
            }
            Log.e((CharSequence) str, "executeCapture failed. invalid params", Boolean.valueOf(z), rect, rect2);
            sendCompleteEventIfFailed();
            return;
        }
        SimpleThreadPool.getInstance().execute(new B5.c(this, mediaItem, imageView, rect, rect2, 14));
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_QUICK_CROP.toString();
    }

    public boolean isNullThenSendEvent(Object obj) {
        if (obj != null) {
            return false;
        }
        this.mErrorCase = 1;
        sendCompleteEventIfFailed();
        return true;
    }

    public String makeCapturedPath(boolean z) {
        String str;
        if (z) {
            str = "jpg";
        } else {
            str = FileUtils.getExtension(this.mOriginalPath, false);
        }
        return new FileNameBuilder(this.mOriginalPath).setExtension(str).buildUnique();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str;
        if (StorageUtil.checkLowStorage(true)) {
            Log.e(this.TAG, "operation failed. low storage");
            sendCompleteEventIfFailed();
            return;
        }
        MediaItem mediaItem = objArr[0];
        this.mMediaItem = mediaItem;
        if (mediaItem == null) {
            sendCompleteEventIfFailed();
            return;
        }
        boolean isDownloadedSharedVideo = isDownloadedSharedVideo(mediaItem);
        this.mIsDownloadedSharedVideo = isDownloadedSharedVideo;
        if (isDownloadedSharedVideo) {
            str = MediaItemMde.getDownloadVideoPath(this.mMediaItem);
        } else {
            str = this.mMediaItem.getPath();
        }
        this.mOriginalPath = str;
        this.mOriginalMediaId = this.mMediaItem.getMediaId();
        this.mIsFavoriteItem = this.mMediaItem.isFavourite();
        executeCapture(this.mMediaItem, objArr);
    }

    public void publishPreviewBitmap(MediaItem mediaItem, Bitmap bitmap) {
        Blackboard blackboard = getBlackboard();
        String str = "viewer_quick_crop_pre_bitmap/" + mediaItem.getMediaId();
        if (mediaItem.getOrientation() != 0) {
            bitmap = BitmapUtils.rotateBitmap(bitmap, 360 - mediaItem.getOrientation());
        }
        blackboard.publish(str, bitmap);
    }

    public void saveBitmap(String str, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        synchronized (FILE_WRITE_LOCK) {
            try {
                fileOutputStream = new FileOutputStream(new File(str));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 96, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                String str2 = this.TAG;
                Log.e((CharSequence) str2, "saveBitmap failed " + Logger.toString(bitmap), (Throwable) e);
                FileUtils.delete(str);
                throw e;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return;
        throw th;
    }

    public void saveCapturedFile(MediaItem mediaItem, File file, Rect rect) {
        saveCapturedFileWithBuffer(mediaItem, SeApiCompat.getQuickCropStream(file, rect));
    }

    public void saveCapturedFileWithBitmap(MediaItem mediaItem, Bitmap bitmap) {
        boolean z;
        String str;
        if (isNullThenSendEvent(bitmap)) {
            Log.e(this.TAG, "3. saveCapturedFileWithBitmap failed. null capture");
            return;
        }
        String makeCapturedPath = makeCapturedPath(true);
        if (!FileUtils.makeDirectoryIfAbsent(new SecureFile(makeCapturedPath).getParent())) {
            Log.e(this.TAG, "3. saveCapturedFileWithBitmap failed to mkdirs");
            return;
        }
        if (Build.VERSION.SDK_INT < 34 || !bitmap.hasGainmap()) {
            z = false;
        } else {
            z = true;
        }
        String str2 = this.TAG;
        if (z) {
            str = ",HDR";
        } else {
            str = "";
        }
        Log.d(str2, "3. saveCapturedFileWithBitmap ".concat(str));
        String str3 = FileUtils.getExternalFilesDir("tmp") + File.separator + mediaItem.getFileId() + FileUtils.getExtension(makeCapturedPath, true);
        saveBitmap(str3, bitmap);
        saveExif(str3, bitmap.getWidth(), bitmap.getHeight());
        saveSef(str3, mediaItem);
        if (z) {
            saveSefHdr(str3, mediaItem);
        }
        copyAndScan(mediaItem, str3, makeCapturedPath);
        if (Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY) && this.mIsDownloadedSharedVideo) {
            ThreadUtil.postOnBgThreadDelayed(new C0412b(this, 3), 300);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00b4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveCapturedFileWithBuffer(com.samsung.android.gallery.module.data.MediaItem r9, java.nio.ByteBuffer r10) {
        /*
            r8 = this;
            boolean r0 = r8.isNullThenSendEvent(r10)
            if (r0 == 0) goto L_0x000e
            java.lang.String r8 = r8.TAG
            java.lang.String r9 = "3. saveCapturedFileWithBuffer failed. null capture"
            com.samsung.android.gallery.support.utils.Log.e(r8, r9)
            return
        L_0x000e:
            r0 = 0
            java.lang.String r1 = r8.makeCapturedPath(r0)
            com.samsung.android.gallery.support.utils.SecureFile r2 = new com.samsung.android.gallery.support.utils.SecureFile
            r2.<init>(r1)
            java.lang.String r2 = r2.getParent()
            boolean r2 = com.samsung.android.gallery.support.utils.FileUtils.makeDirectoryIfAbsent((java.lang.String) r2)
            if (r2 != 0) goto L_0x002a
            java.lang.String r8 = r8.TAG
            java.lang.String r9 = "3. saveCapturedFileWithBuffer failed to mkdirs"
            com.samsung.android.gallery.support.utils.Log.e(r8, r9)
            return
        L_0x002a:
            java.lang.String r2 = r8.TAG
            java.lang.String r3 = "3. saveCapturedFileWithBuffer "
            com.samsung.android.gallery.support.utils.Log.d(r2, r3)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "tmp"
            java.lang.String r3 = com.samsung.android.gallery.support.utils.FileUtils.getExternalFilesDir(r3)
            r2.append(r3)
            java.lang.String r3 = java.io.File.separator
            r2.append(r3)
            long r3 = r9.getFileId()
            r2.append(r3)
            java.lang.String r3 = ".jpg"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.samsung.android.gallery.support.utils.Features r3 = com.samsung.android.gallery.support.utils.Features.SUPPORT_PHOTO_REMASTER_UPSCALE_ULTRA
            boolean r3 = com.samsung.android.gallery.support.utils.Features.isEnabled(r3)
            boolean r4 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi7x.isAutoUpscaleEnabled()
            java.lang.String r5 = r8.TAG
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "3-1. upscale config : f="
            r6.<init>(r7)
            r6.append(r3)
            java.lang.String r7 = ",s="
            r6.append(r7)
            r6.append(r4)
            java.lang.String r6 = r6.toString()
            com.samsung.android.gallery.support.utils.Log.d(r5, r6)
            if (r4 == 0) goto L_0x00c6
            if (r3 == 0) goto L_0x00c6
            com.samsung.android.gallery.module.graphics.BitmapOptions r3 = new com.samsung.android.gallery.module.graphics.BitmapOptions
            r3.<init>()
            r4 = 1
            r3.inSampleSize = r4
            byte[] r5 = r10.array()
            int r6 = r10.capacity()
            android.graphics.Bitmap r0 = com.samsung.android.gallery.module.graphics.ImageDecoder.decodeByteArray(r5, r0, r6, r3)
            if (r0 == 0) goto L_0x00a7
            int r3 = r0.getWidth()
            int r5 = r0.getHeight()
            int r5 = r5 * r3
            r3 = 4000000(0x3d0900, float:5.605194E-39)
            if (r5 > r3) goto L_0x00a7
            r3 = 4
            android.graphics.Bitmap r3 = com.samsung.android.gallery.module.graphics.ai.BitmapEnhancer.upscale(r0, r3, r9)
            goto L_0x00a8
        L_0x00a7:
            r3 = 0
        L_0x00a8:
            if (r3 == 0) goto L_0x00b4
            java.lang.String r1 = r8.makeCapturedPath(r4)
            r10 = 100
            com.samsung.android.gallery.module.graphics.BitmapUtils.saveAsJpeg(r3, r2, r10)
            goto L_0x00ba
        L_0x00b4:
            r10.rewind()
            r8.saveBuffer(r2, r10)
        L_0x00ba:
            java.lang.String r10 = r8.TAG
            java.lang.String r4 = "3-2. upscale "
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r3}
            com.samsung.android.gallery.support.utils.Log.d(r10, r4, r0)
            goto L_0x00c9
        L_0x00c6:
            r8.saveBuffer(r2, r10)
        L_0x00c9:
            com.samsung.android.gallery.module.graphics.BitmapOptions r10 = com.samsung.android.gallery.module.graphics.BitmapOptionsFactory.parse(r2)
            int r0 = r10.outWidth
            int r10 = r10.outHeight
            r8.saveExif(r2, r0, r10)
            r8.saveSef(r2, r9)
            r8.copyAndScan(r9, r2, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.viewer.SaveCaptureCmd.saveCapturedFileWithBuffer(com.samsung.android.gallery.module.data.MediaItem, java.nio.ByteBuffer):void");
    }

    public void saveExif(String str, int i2, int i7) {
        ExifUtils.copy(this.mOriginalPath, str, i2, i7);
    }

    public void saveSef(String str, MediaItem mediaItem) {
        SefFileCompat sefFileCompat = SeApiCompat.getSefFileCompat();
        File file = new File(this.mOriginalPath);
        File file2 = new File(str);
        try {
            SefInfo sefInfo = SefInfo.IMAGE_UTC_DATA;
            sefFileCompat.addData(file2, sefInfo.keyName, sefFileCompat.getData(file, sefInfo.keyName), sefInfo.keyCode, mediaItem.getMimeType());
        } catch (IOException unused) {
            Log.e(this.TAG, "saveSef failed");
        }
    }

    public void scanFile(String str) {
        MediaScannerConnection.scanFile(getContext(), new String[]{str}, (String[]) null, new u(2, this));
        getBlackboard().post("command://event/DataDirty", (Object) null);
    }

    public void sendCompleteEvent(Object[] objArr) {
        if (this.mCallback != null) {
            ThreadUtil.runOnUiThread(new c(12, this, objArr));
        }
    }

    public void sendCompleteEventIfFailed() {
        int i2;
        if (this.mCapturedUri == null) {
            Log.d(this.TAG, "sendCompleteEventIfFailed", Integer.valueOf(this.mErrorCase));
            if (this.mErrorCase == 1) {
                i2 = R.string.couldnt_start_quick_crop_try_again;
            } else {
                i2 = R.string.unable_to_save;
            }
            showToast(i2);
            sendCompleteEvent((Object[]) null);
        }
    }

    public void sendProcessingEvent() {
        if (this.mCallback != null) {
            ThreadUtil.runOnUiThread(new C0412b(this, 2));
        }
    }

    public SaveCaptureCmd setCallback(Consumer<EventMessage> consumer) {
        this.mCallback = consumer;
        return this;
    }

    public void unsubscribeEvent() {
        Blackboard blackboard = getBlackboard();
        if (blackboard != null) {
            blackboard.unsubscribe("command://update/MediaItem/QuickCrop", this.mQuickCropDataChanged);
        } else {
            Log.w(this.TAG, "unsubscribeEvent fail");
        }
    }

    public void updateThumbnail(MediaItem mediaItem) {
        ThumbnailLoader.getInstance().removeCache(mediaItem);
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        Objects.requireNonNull(mediaItem);
        instance.loadThumbnail(mediaItem, thumbKind, new B8.e(mediaItem, 1), new O3.b(14, this, mediaItem));
    }
}
