package androidx.media3.transformer;

import F2.U;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaCodec;
import android.media.metrics.LogSessionId;
import android.util.Pair;
import android.view.Surface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.common.VideoCompositorSettings;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoGraph;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.TransformationRequest;
import com.google.common.util.concurrent.r;
import com.samsung.android.ocr.MOCRLang;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class VideoSampleExporter extends SampleExporter {
    private final DecoderInputBuffer encoderOutputBuffer;
    /* access modifiers changed from: private */
    public final EncoderWrapper encoderWrapper;
    /* access modifiers changed from: private */
    public volatile long finalFramePresentationTimeUs;
    private boolean hasMuxedTimestampZero;
    private long lastMuxerInputBufferTimestampUs;
    private final VideoGraphWrapper videoGraph;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EncoderWrapper {
        private final U allowedEncodingRotationDegrees;
        private volatile Codec encoder;
        private final Codec.EncoderFactory encoderFactory;
        private SurfaceInfo encoderSurfaceInfo;
        private final FallbackListener fallbackListener;
        private final int hdrModeAfterFallback;
        private final Format inputFormat;
        private final LogSessionId logSessionId;
        private final List<String> muxerSupportedMimeTypes;
        private volatile int outputRotationDegrees;
        private volatile boolean releaseEncoder;
        private final String requestedOutputMimeType;
        private final TransformationRequest transformationRequest;

        public EncoderWrapper(Codec.EncoderFactory encoderFactory2, Format format, U u, List<String> list, TransformationRequest transformationRequest2, FallbackListener fallbackListener2, LogSessionId logSessionId2) {
            boolean z;
            if (format.colorInfo != null) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.encoderFactory = encoderFactory2;
            this.inputFormat = format;
            this.allowedEncodingRotationDegrees = u;
            this.muxerSupportedMimeTypes = list;
            this.transformationRequest = transformationRequest2;
            this.fallbackListener = fallbackListener2;
            this.logSessionId = logSessionId2;
            Pair<String, Integer> requestedOutputMimeTypeAndHdrModeAfterFallback = getRequestedOutputMimeTypeAndHdrModeAfterFallback(format, transformationRequest2);
            this.requestedOutputMimeType = (String) requestedOutputMimeTypeAndHdrModeAfterFallback.first;
            this.hdrModeAfterFallback = ((Integer) requestedOutputMimeTypeAndHdrModeAfterFallback.second).intValue();
        }

        private static TransformationRequest createSupportedTransformationRequest(TransformationRequest transformationRequest2, boolean z, Format format, Format format2, int i2) {
            TransformationRequest.Builder buildUpon = transformationRequest2.buildUpon();
            if (transformationRequest2.hdrMode != i2) {
                buildUpon.setHdrMode(i2);
            }
            if (!Objects.equals(format.sampleMimeType, format2.sampleMimeType)) {
                buildUpon.setVideoMimeType(format2.sampleMimeType);
            }
            if (z) {
                int i7 = format.width;
                int i8 = format2.width;
                if (i7 != i8) {
                    buildUpon.setResolution(i8);
                }
            } else {
                int i10 = format.height;
                int i11 = format2.height;
                if (i10 != i11) {
                    buildUpon.setResolution(i11);
                }
            }
            return buildUpon.build();
        }

        private static Pair<String, Integer> getRequestedOutputMimeTypeAndHdrModeAfterFallback(Format format, TransformationRequest transformationRequest2) {
            String str = (String) Assertions.checkNotNull(format.sampleMimeType);
            String str2 = transformationRequest2.videoMimeType;
            if (str2 != null) {
                str = str2;
            } else if (MimeTypes.isImage(str)) {
                str = "video/hevc";
            }
            return TransformerUtil.getOutputMimeTypeAndHdrModeAfterFallback(transformationRequest2.hdrMode, str, format.colorInfo);
        }

        private ColorInfo getSupportedInputColor() {
            if (ColorInfo.isTransferHdr(this.inputFormat.colorInfo) && this.hdrModeAfterFallback != 0) {
                return ColorInfo.SDR_BT709_LIMITED;
            }
            if (ColorInfo.SRGB_BT709_FULL.equals(this.inputFormat.colorInfo)) {
                return ColorInfo.SDR_BT709_LIMITED;
            }
            return (ColorInfo) Assertions.checkNotNull(this.inputFormat.colorInfo);
        }

        public int getHdrModeAfterFallback() {
            return this.hdrModeAfterFallback;
        }

        public ByteBuffer getOutputBuffer() {
            if (this.encoder != null) {
                return this.encoder.getOutputBuffer();
            }
            return null;
        }

        public MediaCodec.BufferInfo getOutputBufferInfo() {
            if (this.encoder != null) {
                return this.encoder.getOutputBufferInfo();
            }
            return null;
        }

        public Format getOutputFormat() {
            if (this.encoder == null) {
                return null;
            }
            Format outputFormat = this.encoder.getOutputFormat();
            if (outputFormat == null || this.outputRotationDegrees == 0) {
                return outputFormat;
            }
            return outputFormat.buildUpon().setRotationDegrees(this.outputRotationDegrees).build();
        }

        public SurfaceInfo getSurfaceInfo(int i2, int i7) {
            if (this.releaseEncoder) {
                return null;
            }
            SurfaceInfo surfaceInfo = this.encoderSurfaceInfo;
            if (surfaceInfo != null) {
                return surfaceInfo;
            }
            if (i2 < i7) {
                this.outputRotationDegrees = 90;
                int i8 = i7;
                i7 = i2;
                i2 = i8;
            }
            if (this.inputFormat.rotationDegrees % MOCRLang.KHMER == this.outputRotationDegrees % MOCRLang.KHMER) {
                this.outputRotationDegrees = this.inputFormat.rotationDegrees;
            }
            boolean z = false;
            if (!this.allowedEncodingRotationDegrees.contains(Integer.valueOf(this.outputRotationDegrees))) {
                int i10 = (this.outputRotationDegrees + MOCRLang.KHMER) % 360;
                if (this.allowedEncodingRotationDegrees.contains(Integer.valueOf(i10))) {
                    this.outputRotationDegrees = i10;
                } else {
                    this.outputRotationDegrees = ((Integer) this.allowedEncodingRotationDegrees.get(0)).intValue();
                    int i11 = i7;
                    i7 = i2;
                    i2 = i11;
                }
            }
            Format build = new Format.Builder().setWidth(i2).setHeight(i7).setRotationDegrees(0).setFrameRate(this.inputFormat.frameRate).setSampleMimeType(this.requestedOutputMimeType).setColorInfo(getSupportedInputColor()).setCodecs(this.inputFormat.codecs).build();
            this.encoder = this.encoderFactory.createForVideoEncoding(build.buildUpon().setSampleMimeType(SampleExporter.findSupportedMimeTypeForEncoderAndMuxer(build, this.muxerSupportedMimeTypes)).build(), this.logSessionId);
            Format configurationFormat = this.encoder.getConfigurationFormat();
            FallbackListener fallbackListener2 = this.fallbackListener;
            TransformationRequest transformationRequest2 = this.transformationRequest;
            if (this.outputRotationDegrees != 0) {
                z = true;
            }
            fallbackListener2.onTransformationRequestFinalized(createSupportedTransformationRequest(transformationRequest2, z, build, configurationFormat, this.hdrModeAfterFallback));
            this.encoderSurfaceInfo = new SurfaceInfo(this.encoder.getInputSurface(), configurationFormat.width, configurationFormat.height, this.outputRotationDegrees, true);
            if (this.releaseEncoder) {
                this.encoder.release();
            }
            return this.encoderSurfaceInfo;
        }

        public boolean isEnded() {
            if (this.encoder == null || !this.encoder.isEnded()) {
                return false;
            }
            return true;
        }

        public void release() {
            if (this.encoder != null) {
                this.encoder.release();
            }
            this.releaseEncoder = true;
        }

        public void releaseOutputBuffer(boolean z) {
            if (this.encoder != null) {
                this.encoder.releaseOutputBuffer(z);
            }
        }

        public void signalEndOfInputStream() {
            if (this.encoder != null) {
                this.encoder.signalEndOfInputStream();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class VideoGraphInput implements GraphInput {
        private final long initialTimestampOffsetUs;
        private final int inputIndex;
        private final AtomicLong mediaItemOffsetUs = new AtomicLong();
        private final VideoGraph videoGraph;

        public VideoGraphInput(VideoGraph videoGraph2, int i2, long j2) {
            this.videoGraph = videoGraph2;
            this.inputIndex = i2;
            this.initialTimestampOffsetUs = j2;
        }

        private static Format applyDecoderRotation(Format format) {
            if (format.rotationDegrees % MOCRLang.KHMER == 0) {
                return format;
            }
            return format.buildUpon().setWidth(format.height).setHeight(format.width).setRotationDegrees(0).build();
        }

        private static int getInputTypeForMimeType(String str) {
            if (MimeTypes.isImage(str)) {
                return 2;
            }
            if (str.equals("video/raw")) {
                return 3;
            }
            if (MimeTypes.isVideo(str)) {
                return 1;
            }
            throw new IllegalArgumentException("MIME type not supported ".concat(str));
        }

        private static boolean isMediaItemForSurfaceAssetLoader(EditedMediaItem editedMediaItem) {
            String scheme;
            MediaItem.LocalConfiguration localConfiguration = editedMediaItem.mediaItem.localConfiguration;
            if (localConfiguration == null || (scheme = localConfiguration.uri.getScheme()) == null) {
                return false;
            }
            return scheme.equals("transformer_surface_asset");
        }

        public Surface getInputSurface() {
            return this.videoGraph.getInputSurface(this.inputIndex);
        }

        public int getPendingVideoFrameCount() {
            return this.videoGraph.getPendingInputFrameCount(this.inputIndex);
        }

        public void onMediaItemChanged(EditedMediaItem editedMediaItem, long j2, Format format, boolean z) {
            int inputTypeForMimeType;
            boolean isMediaItemForSurfaceAssetLoader = isMediaItemForSurfaceAssetLoader(editedMediaItem);
            long durationAfterEffectsApplied = editedMediaItem.getDurationAfterEffectsApplied(j2);
            if (format != null) {
                Format applyDecoderRotation = applyDecoderRotation(format);
                VideoGraph videoGraph2 = this.videoGraph;
                int i2 = this.inputIndex;
                if (isMediaItemForSurfaceAssetLoader) {
                    inputTypeForMimeType = 4;
                } else {
                    inputTypeForMimeType = getInputTypeForMimeType((String) Assertions.checkNotNull(applyDecoderRotation.sampleMimeType));
                }
                int i7 = inputTypeForMimeType;
                videoGraph2.registerInputStream(i2, i7, applyDecoderRotation, editedMediaItem.effects.videoEffects, this.mediaItemOffsetUs.get() + this.initialTimestampOffsetUs);
            }
            this.mediaItemOffsetUs.addAndGet(durationAfterEffectsApplied);
        }

        public int queueInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator) {
            if (this.videoGraph.queueInputBitmap(this.inputIndex, bitmap, timestampIterator)) {
                return 1;
            }
            return 2;
        }

        public boolean registerVideoFrame(long j2) {
            return this.videoGraph.registerInputFrame(this.inputIndex);
        }

        public void signalEndOfVideoInput() {
            this.videoGraph.signalEndOfInput(this.inputIndex);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class VideoGraphWrapper implements VideoGraph.Listener {
        private final Consumer<ExportException> errorConsumer;
        private int framesAvailableToRender;
        private int framesInEncoder;
        private final long initialTimestampOffsetUs;
        private final Object lock = new Object();
        private final int maxFramesInEncoder;
        private final boolean renderFramesAutomatically;
        private final VideoGraph videoGraph;

        public VideoGraphWrapper(Context context, VideoGraph.Factory factory, ColorInfo colorInfo, DebugViewProvider debugViewProvider, VideoCompositorSettings videoCompositorSettings, List<Effect> list, Consumer<ExportException> consumer, long j2, int i2, boolean z) {
            this.errorConsumer = consumer;
            boolean z3 = z;
            this.renderFramesAutomatically = z3;
            long j3 = j2;
            this.initialTimestampOffsetUs = j3;
            this.maxFramesInEncoder = i2;
            Context context2 = context;
            VideoGraph.Factory factory2 = factory;
            ColorInfo colorInfo2 = colorInfo;
            DebugViewProvider debugViewProvider2 = debugViewProvider;
            VideoGraph create = factory2.create(context2, colorInfo2, debugViewProvider2, this, r.INSTANCE, j3, z3);
            this.videoGraph = create;
            create.setCompositionEffects(list);
            create.setCompositorSettings(videoCompositorSettings);
        }

        private void maybeRenderEarliestOutputFrame() {
            boolean z;
            int i2;
            synchronized (this.lock) {
                try {
                    int i7 = this.framesAvailableToRender;
                    if (i7 <= 0 || (i2 = this.framesInEncoder) >= this.maxFramesInEncoder) {
                        z = false;
                    } else {
                        z = true;
                        this.framesInEncoder = i2 + 1;
                        this.framesAvailableToRender = i7 - 1;
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            if (z) {
                this.videoGraph.renderOutputFrame(-3);
            }
        }

        public GraphInput createInput(int i2) {
            this.videoGraph.registerInput(i2);
            return new VideoGraphInput(this.videoGraph, i2, this.initialTimestampOffsetUs);
        }

        public boolean hasEncoderReleasedAllBuffersAfterEndOfStream() {
            boolean z;
            boolean z3 = false;
            if (this.renderFramesAutomatically) {
                return false;
            }
            if (VideoSampleExporter.this.finalFramePresentationTimeUs != -9223372036854775807L) {
                z = true;
            } else {
                z = false;
            }
            synchronized (this.lock) {
                if (this.framesInEncoder == 0 && z) {
                    z3 = true;
                }
            }
            return z3;
        }

        public boolean hasProducedFrameWithTimestampZero() {
            return this.videoGraph.hasProducedFrameWithTimestampZero();
        }

        public void initialize() {
            this.videoGraph.initialize();
        }

        public void onEncoderBufferReleased() {
            boolean z;
            if (!this.renderFramesAutomatically) {
                synchronized (this.lock) {
                    if (this.framesInEncoder > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Assertions.checkState(z);
                    this.framesInEncoder--;
                }
                maybeRenderEarliestOutputFrame();
            }
        }

        public void onEnded(long j2) {
            long unused = VideoSampleExporter.this.finalFramePresentationTimeUs = j2;
            try {
                VideoSampleExporter.this.encoderWrapper.signalEndOfInputStream();
            } catch (ExportException e) {
                this.errorConsumer.accept(e);
            }
        }

        public void onError(VideoFrameProcessingException videoFrameProcessingException) {
            this.errorConsumer.accept(ExportException.createForVideoFrameProcessingException(videoFrameProcessingException));
        }

        public void onOutputFrameAvailableForRendering(long j2, boolean z) {
            if (!this.renderFramesAutomatically) {
                synchronized (this.lock) {
                    this.framesAvailableToRender++;
                }
                maybeRenderEarliestOutputFrame();
            }
        }

        public void onOutputSizeChanged(int i2, int i7) {
            SurfaceInfo surfaceInfo;
            try {
                surfaceInfo = VideoSampleExporter.this.encoderWrapper.getSurfaceInfo(i2, i7);
            } catch (ExportException e) {
                this.errorConsumer.accept(e);
                surfaceInfo = null;
            }
            this.videoGraph.setOutputSurfaceInfo(surfaceInfo);
        }

        public void release() {
            this.videoGraph.release();
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VideoSampleExporter(android.content.Context r22, androidx.media3.common.Format r23, androidx.media3.transformer.TransformationRequest r24, androidx.media3.common.VideoCompositorSettings r25, java.util.List<androidx.media3.common.Effect> r26, androidx.media3.common.VideoFrameProcessor.Factory r27, androidx.media3.transformer.Codec.EncoderFactory r28, androidx.media3.transformer.MuxerWrapper r29, androidx.media3.common.util.Consumer<androidx.media3.transformer.ExportException> r30, androidx.media3.transformer.FallbackListener r31, androidx.media3.common.DebugViewProvider r32, long r33, boolean r35, F2.U r36, int r37, android.media.metrics.LogSessionId r38) {
        /*
            r21 = this;
            r1 = r21
            r0 = r23
            r2 = r27
            r3 = r29
            r1.<init>(r0, r3)
            r4 = 0
            r5 = 1
            r11 = r37
            if (r11 >= r5) goto L_0x0013
            r12 = r5
            goto L_0x0014
        L_0x0013:
            r12 = r4
        L_0x0014:
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r1.finalFramePresentationTimeUs = r6
            r1.lastMuxerInputBufferTimestampUs = r6
            androidx.media3.common.ColorInfo r6 = r0.colorInfo
            java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r6)
            androidx.media3.common.ColorInfo r6 = (androidx.media3.common.ColorInfo) r6
            int r7 = r6.colorTransfer
            r8 = 2
            if (r7 != r8) goto L_0x004f
            java.lang.String r7 = r0.sampleMimeType
            java.lang.String r9 = "image/jpeg_r"
            boolean r7 = java.util.Objects.equals(r7, r9)
            if (r7 == 0) goto L_0x004c
            androidx.media3.common.ColorInfo$Builder r7 = new androidx.media3.common.ColorInfo$Builder
            r7.<init>()
            r9 = 6
            androidx.media3.common.ColorInfo$Builder r7 = r7.setColorSpace(r9)
            r9 = 7
            androidx.media3.common.ColorInfo$Builder r7 = r7.setColorTransfer(r9)
            androidx.media3.common.ColorInfo$Builder r5 = r7.setColorRange(r5)
            androidx.media3.common.ColorInfo r5 = r5.build()
            goto L_0x0050
        L_0x004c:
            androidx.media3.common.ColorInfo r5 = androidx.media3.common.ColorInfo.SDR_BT709_LIMITED
            goto L_0x0050
        L_0x004f:
            r5 = r6
        L_0x0050:
            androidx.media3.transformer.VideoSampleExporter$EncoderWrapper r13 = new androidx.media3.transformer.VideoSampleExporter$EncoderWrapper
            androidx.media3.common.Format$Builder r0 = r0.buildUpon()
            androidx.media3.common.Format$Builder r0 = r0.setColorInfo(r5)
            androidx.media3.common.Format r15 = r0.build()
            F2.U r17 = r3.getSupportedSampleMimeTypes(r8)
            r18 = r24
            r14 = r28
            r19 = r31
            r16 = r36
            r20 = r38
            r13.<init>(r14, r15, r16, r17, r18, r19, r20)
            r1.encoderWrapper = r13
            androidx.media3.decoder.DecoderInputBuffer r0 = new androidx.media3.decoder.DecoderInputBuffer
            r0.<init>(r4)
            r1.encoderOutputBuffer = r0
            int r0 = r13.getHdrModeAfterFallback()
            if (r0 != r8) goto L_0x0086
            boolean r0 = androidx.media3.common.ColorInfo.isTransferHdr(r6)
            if (r0 == 0) goto L_0x0086
            androidx.media3.common.ColorInfo r5 = androidx.media3.common.ColorInfo.SDR_BT709_LIMITED
        L_0x0086:
            r4 = r5
            androidx.media3.transformer.VideoSampleExporter$VideoGraphWrapper r0 = new androidx.media3.transformer.VideoSampleExporter$VideoGraphWrapper     // Catch:{ VideoFrameProcessingException -> 0x00ac }
            if (r35 == 0) goto L_0x009d
            androidx.media3.effect.MultipleInputVideoGraph$Factory r3 = new androidx.media3.effect.MultipleInputVideoGraph$Factory     // Catch:{ VideoFrameProcessingException -> 0x00ac }
            r3.<init>(r2)     // Catch:{ VideoFrameProcessingException -> 0x00ac }
        L_0x0090:
            r2 = r22
            r6 = r25
            r7 = r26
            r8 = r30
            r5 = r32
            r9 = r33
            goto L_0x00a3
        L_0x009d:
            androidx.media3.effect.SingleInputVideoGraph$Factory r3 = new androidx.media3.effect.SingleInputVideoGraph$Factory     // Catch:{ VideoFrameProcessingException -> 0x00ac }
            r3.<init>(r2)     // Catch:{ VideoFrameProcessingException -> 0x00ac }
            goto L_0x0090
        L_0x00a3:
            r0.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r11, r12)     // Catch:{ VideoFrameProcessingException -> 0x00ac }
            r1.videoGraph = r0     // Catch:{ VideoFrameProcessingException -> 0x00ac }
            r0.initialize()     // Catch:{ VideoFrameProcessingException -> 0x00ac }
            return
        L_0x00ac:
            r0 = move-exception
            androidx.media3.transformer.ExportException r0 = androidx.media3.transformer.ExportException.createForVideoFrameProcessingException(r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.VideoSampleExporter.<init>(android.content.Context, androidx.media3.common.Format, androidx.media3.transformer.TransformationRequest, androidx.media3.common.VideoCompositorSettings, java.util.List, androidx.media3.common.VideoFrameProcessor$Factory, androidx.media3.transformer.Codec$EncoderFactory, androidx.media3.transformer.MuxerWrapper, androidx.media3.common.util.Consumer, androidx.media3.transformer.FallbackListener, androidx.media3.common.DebugViewProvider, long, boolean, F2.U, int, android.media.metrics.LogSessionId):void");
    }

    public GraphInput getInput(EditedMediaItem editedMediaItem, Format format, int i2) {
        try {
            return this.videoGraph.createInput(i2);
        } catch (VideoFrameProcessingException e) {
            throw ExportException.createForVideoFrameProcessingException(e);
        }
    }

    public DecoderInputBuffer getMuxerInputBuffer() {
        this.encoderOutputBuffer.data = this.encoderWrapper.getOutputBuffer();
        if (this.encoderOutputBuffer.data == null) {
            return null;
        }
        MediaCodec.BufferInfo bufferInfo = (MediaCodec.BufferInfo) Assertions.checkNotNull(this.encoderWrapper.getOutputBufferInfo());
        if (bufferInfo.presentationTimeUs == 0 && this.videoGraph.hasProducedFrameWithTimestampZero() == this.hasMuxedTimestampZero && this.finalFramePresentationTimeUs != -9223372036854775807L && bufferInfo.size > 0) {
            bufferInfo.presentationTimeUs = this.finalFramePresentationTimeUs;
        }
        DecoderInputBuffer decoderInputBuffer = this.encoderOutputBuffer;
        decoderInputBuffer.timeUs = bufferInfo.presentationTimeUs;
        decoderInputBuffer.setFlags(bufferInfo.flags);
        this.lastMuxerInputBufferTimestampUs = bufferInfo.presentationTimeUs;
        return this.encoderOutputBuffer;
    }

    public Format getMuxerInputFormat() {
        return this.encoderWrapper.getOutputFormat();
    }

    public boolean isMuxerInputEnded() {
        if (this.encoderWrapper.isEnded() || this.videoGraph.hasEncoderReleasedAllBuffersAfterEndOfStream()) {
            return true;
        }
        return false;
    }

    public void release() {
        this.videoGraph.release();
        this.encoderWrapper.release();
    }

    public void releaseMuxerInputBuffer() {
        if (this.lastMuxerInputBufferTimestampUs == 0) {
            this.hasMuxedTimestampZero = true;
        }
        this.encoderWrapper.releaseOutputBuffer(false);
        this.videoGraph.onEncoderBufferReleased();
    }
}
