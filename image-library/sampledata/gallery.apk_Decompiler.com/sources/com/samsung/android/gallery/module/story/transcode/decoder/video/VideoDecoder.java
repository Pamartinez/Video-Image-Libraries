package com.samsung.android.gallery.module.story.transcode.decoder.video;

import N2.j;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.net.Uri;
import android.widget.ImageView;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.effectfilter.FilterManager;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.story.transcode.config.FrameProperty;
import com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame;
import com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecoderFrameManager;
import com.samsung.android.gallery.module.story.transcode.decoder.video.metadata.VideoMetaData;
import com.samsung.android.gallery.module.story.transcode.decoder.video.processor.Processor;
import com.samsung.android.gallery.module.story.transcode.decoder.video.processor.ProcessorFactory;
import com.samsung.android.gallery.module.story.transcode.renderer.render.RectCorners;
import com.samsung.android.gallery.module.story.transcode.util.CodecsHelper;
import com.samsung.android.gallery.module.story.transcode.util.OnErrorListener;
import com.samsung.android.gallery.module.story.transcode.util.ThumbProvider;
import com.samsung.android.gallery.module.story.transcode.util.UnsupportedCodecException;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoDecoder extends Decoder {
    private final DecoderFrameManager mDecodeFrameManager;
    protected Processor mDecodeProcessor;
    private final int mEncodeFrameRate;
    private final OnErrorListener mErrorListener;
    private MediaExtractor mExtractor;
    private Processor mFilterProcessor;
    protected int mFrameIndex;
    protected final AtomicBoolean mInterrupted;
    private boolean mKeepFrame;
    private boolean mMediaResourceReleased;
    private VideoMetaData mMetaData;
    protected VideoRender mVideoRender;

    /* renamed from: com.samsung.android.gallery.module.story.transcode.decoder.video.VideoDecoder$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                android.widget.ImageView$ScaleType[] r0 = android.widget.ImageView.ScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$android$widget$ImageView$ScaleType = r0
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x001d }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.story.transcode.decoder.video.VideoDecoder.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        Bitmap backgroundBitmap;
        RectF cornerRadius;
        DecoderFrameManager decoderFrameManager;
        RectF displayPositionRect;
        int duration;
        OnErrorListener errorListener;
        String filePath;
        Filter filter;
        Bitmap foregroundBitmap;
        int id;
        long length;
        long offset;
        int outputHeight;
        int outputWidth;
        ImageView.ScaleType scaleType;
        int startMs;
        ThumbProvider thumbProvider;
        Uri uri;
        boolean withSmartCrop;

        /* access modifiers changed from: private */
        public boolean isUriItem() {
            if (this.uri != null) {
                return true;
            }
            return false;
        }

        private boolean supportedScaleType(ImageView.ScaleType scaleType2) {
            int i2 = AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[scaleType2.ordinal()];
            if (i2 == 1 || i2 == 2) {
                return true;
            }
            return false;
        }

        public Decoder build() {
            return new VideoDecoder(this, 0);
        }

        public Builder setBackgroundBitmap(Bitmap bitmap) {
            this.backgroundBitmap = bitmap;
            return this;
        }

        public Builder setCornerRadius(RectF rectF) {
            this.cornerRadius = rectF;
            return this;
        }

        public Builder setData(String str) {
            this.filePath = str;
            return this;
        }

        public Builder setDisplayPositionRect(RectF rectF) {
            this.displayPositionRect = rectF;
            return this;
        }

        public Builder setDuration(int i2) {
            this.duration = i2;
            return this;
        }

        public Builder setForeGroundBitmap(Bitmap bitmap) {
            this.foregroundBitmap = bitmap;
            return this;
        }

        public Builder setId(int i2) {
            this.id = i2;
            return this;
        }

        public Builder setScaleType(ImageView.ScaleType scaleType2) {
            this.scaleType = scaleType2;
            if (scaleType2 == null || supportedScaleType(scaleType2)) {
                return this;
            }
            throw new UnsupportedOperationException("unsupported scaleType = " + scaleType2);
        }

        public Builder setSize(int i2, int i7) {
            this.outputWidth = i2;
            this.outputHeight = i7;
            return this;
        }

        public Builder setStartMs(int i2) {
            this.startMs = i2;
            return this;
        }

        public Builder setThumbnailProvider(ThumbProvider thumbProvider2) {
            this.thumbProvider = thumbProvider2;
            return this;
        }

        public Builder withDecoderFrameManager(DecoderFrameManager decoderFrameManager2) {
            this.decoderFrameManager = decoderFrameManager2;
            return this;
        }

        public Builder withErrorListener(OnErrorListener onErrorListener) {
            this.errorListener = onErrorListener;
            return this;
        }

        public Builder withFilter(Filter filter2) {
            this.filter = filter2;
            return this;
        }

        public Builder withMotionPhoto(FileItemInterface fileItemInterface, boolean z) {
            if (z && this.filePath != null) {
                long currentTimeMillis = System.currentTimeMillis();
                MediaHelper.VideoInfo load = MetadataManager.getInstance().lambda$preload$0(fileItemInterface);
                long j2 = load.offset;
                this.offset = j2;
                this.length = load.length;
                Long valueOf = Long.valueOf(j2);
                Long valueOf2 = Long.valueOf(this.length);
                Integer valueOf3 = Integer.valueOf(load.duration);
                Log.d("VideoDecoder.Builder", "motionPhoto", valueOf, valueOf2, valueOf3, " +" + (System.currentTimeMillis() - currentTimeMillis));
            }
            return this;
        }

        public Builder setData(Uri uri2) {
            this.uri = uri2;
            return this;
        }
    }

    public /* synthetic */ VideoDecoder(Builder builder, int i2) {
        this(builder);
    }

    private boolean checkKeepFrameAndDraw(FrameProperty frameProperty) {
        if (!this.mKeepFrame && !this.mMediaResourceReleased && ((frameProperty.getAlpha() >= 1.0f || this.mFrameIndex <= 0) && (this.mMetaData.isLooping() || !this.mFilterProcessor.isProcessDone()))) {
            return false;
        }
        super.draw(frameProperty);
        this.mVideoRender.draw(frameProperty);
        return true;
    }

    private boolean checkSkipFrame(DecodedFrame decodedFrame) {
        if (this.mMetaData.getFrameRate() > 30) {
            if (decodedFrame.presentationTimeUS < (this.mMetaData.getEncodeIntervalUs() * ((long) this.mFrameIndex)) - (this.mMetaData.getSourceIntervalUs() / 2)) {
                return true;
            }
        }
        return false;
    }

    private int getFilterIntensity(Filter filter) {
        if (filter != null) {
            return filter.getIntensity();
        }
        return -1;
    }

    private String getFilterPath(Filter filter) {
        if (filter == null || filter.noneFilter()) {
            return null;
        }
        return FilterManager.getInstance().getFilterRawPath(filter);
    }

    private MediaFormat getMediaFormat(Builder builder) {
        try {
            MediaExtractor mediaExtractor = this.mExtractor;
            return mediaExtractor.getTrackFormat(CodecsHelper.getAndSelectVideoTrackIndex(mediaExtractor));
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "fail to read track", Integer.valueOf(builder.duration), Long.valueOf(builder.offset), Long.valueOf(builder.length), e.getMessage());
            MediaFormat mediaFormat = new MediaFormat();
            mediaFormat.setLong("durationUs", (long) builder.duration);
            mediaFormat.setInteger("width", 1440);
            mediaFormat.setInteger("height", 1980);
            mediaFormat.setString(MediaDefs.Image.HEIF.HEIF_MIME_BOX, Encode.CodecsMime.VIDEO_CODEC_H264);
            return mediaFormat;
        }
    }

    private void increaseFrameIndex() {
        if (!isProcessDone()) {
            this.mFrameIndex++;
        }
    }

    private void initBgImage(Builder builder) {
        Bitmap bitmap = builder.backgroundBitmap;
        if (bitmap != null) {
            initBgImage(bitmap, builder.outputWidth, builder.outputHeight);
        } else {
            initBgImage(builder.thumbProvider.getBlurImage(builder.id), builder.outputWidth, builder.outputHeight);
        }
    }

    private void initExtractor(Builder builder) {
        if (builder.isUriItem()) {
            this.mExtractor = CodecsHelper.createExtractor(builder.uri);
            return;
        }
        long j2 = builder.offset;
        if (j2 > 0) {
            long j3 = builder.length;
            if (j3 > 0) {
                this.mExtractor = CodecsHelper.createExtractor(builder.filePath, j2, j3);
                return;
            }
        }
        this.mExtractor = CodecsHelper.createExtractor(builder.filePath);
    }

    private void initFgImage(Builder builder) {
        Bitmap bitmap = builder.foregroundBitmap;
        if (bitmap != null) {
            initFgImage(bitmap, builder.outputWidth, builder.outputHeight);
        }
    }

    private void initMetaData(Builder builder) {
        VideoMetaData videoMetaData = new VideoMetaData(builder.id, 30, builder.startMs, (long) builder.duration, looping(builder));
        this.mMetaData = videoMetaData;
        videoMetaData.setFilter(builder.filter);
        if (builder.isUriItem()) {
            this.mMetaData.setData(builder.uri);
        } else {
            this.mMetaData.setData(builder.filePath);
            this.mMetaData.setSubData(builder.offset, builder.length);
        }
        this.mMetaData.init(getMediaFormat(builder));
    }

    private void initProcessor(Filter filter) {
        this.mDecodeProcessor = ProcessorFactory.createDecodeProcessor(this.mExtractor, this.mMetaData);
        Processor createFilterProcessor = ProcessorFactory.createFilterProcessor(getFilterPath(filter), getFilterIntensity(filter));
        this.mFilterProcessor = createFilterProcessor;
        createFilterProcessor.configure(this.mId, this.mMetaData.getMediaFormat(), this.mVideoRender.getSurface());
        this.mFilterProcessor.setInputBufferQueue(this.mDecodeProcessor.getOutputBufferQueue());
        this.mFilterProcessor.setDecodeFrameManager(this.mDecodeFrameManager);
        this.mDecodeProcessor.configure(this.mId, this.mMetaData.getMediaFormat(), this.mFilterProcessor.createInputSurface());
        this.mDecodeProcessor.setErrorListener(this.mErrorListener);
    }

    private void initVideoRender(Builder builder) {
        this.mVideoRender = new VideoRender(builder.outputWidth, builder.outputHeight, this.mMetaData.getMediaFormat());
        if (RectUtils.isValidRect(builder.displayPositionRect)) {
            this.mVideoRender.setDisplayRect(builder.displayPositionRect);
        } else if (builder.withSmartCrop) {
            this.mVideoRender.setSmartCropRect(builder.thumbProvider);
        } else {
            this.mVideoRender.setScaleType(builder.scaleType);
        }
        if (RectUtils.hasValue(builder.cornerRadius)) {
            RectF rectF = builder.cornerRadius;
            this.mVideoRender.setRoundness(RectCorners.fromPixel((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
        }
        this.mVideoRender.initRender(this.mId, this.mMetaData.getInputOrientation());
    }

    private boolean isDecodedFramesPrepared() {
        if (isProcessDone() || this.mDecodeFrameManager.isDecodedFramesPrepared(this.mId)) {
            return true;
        }
        return false;
    }

    private boolean isNewVideoFrame(DecodedFrame decodedFrame) {
        if (isProcessDone() || decodedFrame == null) {
            return false;
        }
        long j2 = decodedFrame.presentationTimeUS;
        long startTimeUs = this.mMetaData.getStartTimeUs();
        long encodeIntervalUs = this.mMetaData.getEncodeIntervalUs();
        int i2 = this.mFrameIndex;
        if (j2 <= (encodeIntervalUs * ((long) i2)) + startTimeUs || i2 == 0) {
            return true;
        }
        return false;
    }

    private boolean looping(Builder builder) {
        if (builder.length <= 0 || builder.offset <= 0) {
            return true;
        }
        return false;
    }

    private synchronized void releaseMediaResource() {
        if (!this.mMediaResourceReleased) {
            this.mMediaResourceReleased = true;
            try {
                this.mExtractor.release();
            } catch (Exception e) {
                String str = this.TAG;
                Log.e(str, "release fail=" + e.getMessage());
                e.printStackTrace();
            }
            this.mFilterProcessor.release();
            this.mDecodeProcessor.release();
            Log.d(this.TAG, "releaseMediaResource");
        }
    }

    private boolean setKeepFrame(long j2) {
        boolean z;
        if (System.currentTimeMillis() - j2 > 2500) {
            z = true;
        } else {
            z = false;
        }
        this.mKeepFrame = z;
        if (z) {
            Log.e((CharSequence) this.TAG, "detected corrupt frame : ", Long.valueOf(this.mFilterProcessor.getPresentationTimeUs()));
        }
        return this.mKeepFrame;
    }

    private void waitFrameRendering(boolean z) {
        if (z && !this.mVideoRender.waitRenderFrame(1000)) {
            String str = this.TAG;
            Log.e(str, "video decoder: checkForNewImage return false!! videoID[" + this.mId + "]");
        }
    }

    public void draw(FrameProperty frameProperty) {
        if (!checkKeepFrameAndDraw(frameProperty)) {
            startDecoding();
            long currentTimeMillis = System.currentTimeMillis();
            while (!this.mInterrupted.get()) {
                if (setKeepFrame(currentTimeMillis) && checkKeepFrameAndDraw(frameProperty)) {
                    return;
                }
                if (isDecodedFramesPrepared()) {
                    DecodedFrame peakHead = this.mDecodeFrameManager.peakHead(this.mId);
                    boolean isNewVideoFrame = isNewVideoFrame(peakHead);
                    if ((!isProcessDone() && isNewVideoFrame) || this.mDecodeFrameManager.isEndOfStream(this.mId)) {
                        try {
                            this.mFilterProcessor.releaseAndRender(this.mDecodeFrameManager.dequeueFrame(this.mId));
                            if (!checkKeepFrameAndDraw(frameProperty)) {
                                waitFrameRendering(isNewVideoFrame);
                            } else {
                                return;
                            }
                        } catch (IllegalArgumentException e) {
                            Log.e((CharSequence) this.TAG, "releaseAndRender failed", e.getMessage());
                            this.mErrorListener.onError(e);
                        }
                    }
                    try {
                        if (isValidPresentationTime() && !checkSkipFrame(peakHead)) {
                            render(frameProperty);
                            increaseFrameIndex();
                            return;
                        }
                    } catch (RuntimeException e7) {
                        j.u(e7, new StringBuilder("exporting  error "), this.TAG);
                    }
                }
            }
        }
    }

    public void interrupt() {
        if (!this.mInterrupted.getAndSet(true)) {
            this.mFilterProcessor.interrupt();
            Log.d(this.TAG, "interrupt");
        }
    }

    public boolean isProcessDone() {
        return this.mFilterProcessor.isProcessDone();
    }

    public boolean isValidPresentationTime() {
        long presentationTimeUs = this.mFilterProcessor.getPresentationTimeUs();
        if (presentationTimeUs < this.mMetaData.getPlayStartTimeUs() || presentationTimeUs > this.mMetaData.getPlayEndTimeUs()) {
            return false;
        }
        return true;
    }

    public void preRelease() {
        releaseMediaResource();
    }

    public void release() {
        super.release();
        interrupt();
        releaseMediaResource();
        this.mVideoRender.release();
    }

    public void renderInternal(FrameProperty frameProperty) {
        this.mVideoRender.draw(frameProperty);
    }

    public void startDecoding() {
        if (!this.mDecodeProcessor.isRunning()) {
            try {
                this.mDecodeProcessor.start();
            } catch (MediaCodec.CodecException e) {
                throw new UnsupportedCodecException(e);
            }
        }
    }

    private VideoDecoder(Builder builder) {
        super(builder.id);
        this.mEncodeFrameRate = 30;
        this.mInterrupted = new AtomicBoolean();
        this.mErrorListener = builder.errorListener;
        this.mDecodeFrameManager = builder.decoderFrameManager;
        initExtractor(builder);
        initMetaData(builder);
        initVideoRender(builder);
        initBgImage(builder);
        initFgImage(builder);
        initProcessor(builder.filter);
    }
}
