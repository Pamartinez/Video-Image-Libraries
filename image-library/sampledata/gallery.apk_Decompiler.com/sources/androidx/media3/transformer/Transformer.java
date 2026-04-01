package androidx.media3.transformer;

import A4.Q;
import F2.C0040v;
import F2.G;
import F2.U;
import F2.y0;
import I.b;
import P1.e;
import W.a;
import a6.g;
import android.content.Context;
import android.media.metrics.LogSessionId;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.FlagSet;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Util;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.effect.DefaultVideoFrameProcessor;
import androidx.media3.muxer.Muxer;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.AudioMixer;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.DefaultAudioMixer;
import androidx.media3.transformer.DefaultDecoderFactory;
import androidx.media3.transformer.DefaultEncoderFactory;
import androidx.media3.transformer.DefaultMuxer;
import androidx.media3.transformer.EditingMetricsCollector;
import androidx.media3.transformer.ExportResult;
import androidx.media3.transformer.MuxerWrapper;
import androidx.media3.transformer.TransformationRequest;
import androidx.media3.transformer.TransformerInternal;
import androidx.media3.transformer.TransmuxTranscodeHelper;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.s;
import com.samsung.android.ocr.MOCRLang;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Transformer {
    public static final long DEFAULT_MAX_DELAY_BETWEEN_MUXER_SAMPLES_MS;
    private final U allowedEncodingRotationDegrees;
    private final HandlerWrapper applicationHandler;
    private final AssetLoader.Factory assetLoaderFactory;
    private final AudioMixer.Factory audioMixerFactory;
    private final U audioProcessors;
    private final Clock clock;
    /* access modifiers changed from: private */
    public final ComponentListener componentListener;
    /* access modifiers changed from: private */
    public Composition composition;
    private final Context context;
    private ListenableFuture copyOutputFuture;
    private final DebugViewProvider debugViewProvider;
    private EditingMetricsCollector editingMetricsCollector;
    /* access modifiers changed from: private */
    public final Codec.EncoderFactory encoderFactory;
    /* access modifiers changed from: private */
    public final ExportResult.Builder exportResultBuilder;
    /* access modifiers changed from: private */
    public WatchdogTimer exportWatchdogTimer;
    private final boolean fileStartsOnVideoFrameEnabled;
    private ListenableFuture getResumeMetadataFuture;
    private final ListenerSet<Listener> listeners;
    private final Looper looper;
    /* access modifiers changed from: private */
    public final long maxDelayBetweenMuxerSamplesMs;
    private final int maxFramesInEncoder;
    /* access modifiers changed from: private */
    public Mp4Info mediaItemInfo;
    private final EditingMetricsCollector.MetricsReporter.Factory metricsReporterFactory;
    private final boolean mp4EditListTrimEnabled;
    /* access modifiers changed from: private */
    public final Muxer.Factory muxerFactory;
    private String oldFilePath;
    /* access modifiers changed from: private */
    public String outputFilePath;
    private final boolean removeAudio;
    private final boolean removeVideo;
    /* access modifiers changed from: private */
    public MuxerWrapper remuxingMuxerWrapper;
    /* access modifiers changed from: private */
    public final TransformationRequest transformationRequest;
    /* access modifiers changed from: private */
    public TransformerInternal transformerInternal;
    /* access modifiers changed from: private */
    public int transformerState;
    private final boolean trimOptimizationEnabled;
    private final boolean usePlatformDiagnostics;
    private final U videoEffects;
    private final VideoFrameProcessor.Factory videoFrameProcessorFactory;

    /* renamed from: androidx.media3.transformer.Transformer$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    abstract class AnonymousClass1 implements s {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ComponentListener implements TransformerInternal.Listener, MuxerWrapper.Listener {
        private ComponentListener() {
        }

        public void onCompleted(U u, String str, String str2) {
            Transformer.this.exportResultBuilder.addProcessedInputs(u);
            if (str != null) {
                Transformer.this.exportResultBuilder.setAudioEncoderName(str);
            }
            if (str2 != null) {
                Transformer.this.exportResultBuilder.setVideoEncoderName(str2);
            }
            TransformerInternal unused = Transformer.this.transformerInternal = null;
            if (Transformer.this.transformerState == 1) {
                Transformer.this.processRemainingVideo();
            } else if (Transformer.this.transformerState == 2) {
                MuxerWrapper unused2 = Transformer.this.remuxingMuxerWrapper = null;
                Transformer.this.processAudio();
            } else if (Transformer.this.transformerState == 3) {
                Transformer.this.copyOutput();
            } else if (Transformer.this.transformerState == 5) {
                Transformer.this.remuxRemainingMedia();
            } else if (Transformer.this.transformerState == 6) {
                Mp4Info unused3 = Transformer.this.mediaItemInfo = null;
                Transformer.this.exportResultBuilder.setOptimizationResult(1);
                Transformer.this.onExportCompletedWithSuccess();
            } else {
                Transformer.this.onExportCompletedWithSuccess();
            }
        }

        public void onEnded(long j2, long j3) {
            Transformer.this.exportResultBuilder.setDurationMs(j2).setFileSizeBytes(j3);
            ((TransformerInternal) Assertions.checkNotNull(Transformer.this.transformerInternal)).endWithCompletion();
        }

        public void onError(U u, String str, String str2, ExportException exportException) {
            if (exportException.errorCode != 7003 || (!Transformer.this.isExportTrimOptimization() && !Transformer.this.isExportResumed())) {
                Transformer.this.exportResultBuilder.addProcessedInputs(u);
                if (str != null) {
                    Transformer.this.exportResultBuilder.setAudioEncoderName(str);
                }
                if (str2 != null) {
                    Transformer.this.exportResultBuilder.setVideoEncoderName(str2);
                }
                Transformer.this.exportResultBuilder.setExportException(exportException);
                Transformer.this.onExportCompletedWithError(exportException);
                TransformerInternal unused = Transformer.this.transformerInternal = null;
                return;
            }
            MuxerWrapper unused2 = Transformer.this.remuxingMuxerWrapper = null;
            TransformerInternal unused3 = Transformer.this.transformerInternal = null;
            Transformer.this.exportResultBuilder.reset();
            Transformer.this.exportResultBuilder.setOptimizationResult(6);
            Transformer.this.processFullInput();
        }

        public void onSampleWrittenOrDropped() {
            boolean z;
            if (Transformer.this.exportWatchdogTimer != null) {
                Transformer.this.exportWatchdogTimer.reset();
                return;
            }
            if (Transformer.this.maxDelayBetweenMuxerSamplesMs == -9223372036854775807L) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
        }

        public void onTrackEnded(int i2, Format format, int i7, int i8) {
            if (i2 == 1) {
                Transformer.this.exportResultBuilder.setAudioMimeType(format.sampleMimeType).setAverageAudioBitrate(i7);
                if (format.channelCount != -1) {
                    Transformer.this.exportResultBuilder.setChannelCount(format.channelCount);
                }
                if (format.sampleRate != -1) {
                    Transformer.this.exportResultBuilder.setSampleRate(format.sampleRate);
                }
            } else if (i2 == 2) {
                Transformer.this.exportResultBuilder.setVideoMimeType(format.sampleMimeType).setAverageVideoBitrate(i7).setColorInfo(format.colorInfo).setVideoFrameCount(i8);
                if (format.height != -1) {
                    Transformer.this.exportResultBuilder.setHeight(format.height);
                }
                if (format.width != -1) {
                    Transformer.this.exportResultBuilder.setWidth(format.width);
                }
            }
        }

        public /* synthetic */ ComponentListener(Transformer transformer, AnonymousClass1 r22) {
            this();
        }
    }

    static {
        long j2;
        MediaLibraryInfo.registerModule("media3.transformer");
        if (Util.isRunningOnEmulator()) {
            j2 = 25000;
        } else {
            j2 = 10000;
        }
        DEFAULT_MAX_DELAY_BETWEEN_MUXER_SAMPLES_MS = j2;
    }

    public /* synthetic */ Transformer(Context context2, TransformationRequest transformationRequest2, U u, U u3, boolean z, boolean z3, boolean z7, boolean z9, U u6, boolean z10, boolean z11, long j2, int i2, ListenerSet listenerSet, AssetLoader.Factory factory, AudioMixer.Factory factory2, VideoFrameProcessor.Factory factory3, Codec.EncoderFactory encoderFactory2, Muxer.Factory factory4, Looper looper2, DebugViewProvider debugViewProvider2, Clock clock2, EditingMetricsCollector.MetricsReporter.Factory factory5, AnonymousClass1 r25) {
        this(context2, transformationRequest2, u, u3, z, z3, z7, z9, u6, z10, z11, j2, i2, listenerSet, factory, factory2, factory3, encoderFactory2, factory4, looper2, debugViewProvider2, clock2, factory5);
    }

    private boolean canCollectEditingMetrics() {
        if (Build.VERSION.SDK_INT < 35 || !this.usePlatformDiagnostics) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void copyOutput() {
        this.transformerState = 4;
        ListenableFuture copyFileAsync = TransmuxTranscodeHelper.copyFileAsync(new File((String) Assertions.checkNotNull(this.oldFilePath)), new File((String) Assertions.checkNotNull(this.outputFilePath)));
        this.copyOutputFuture = copyFileAsync;
        AnonymousClass2 r1 = new s() {
            public void onFailure(Throwable th) {
                Transformer.this.onExportCompletedWithError(ExportException.createForUnexpected(new IOException("Copy output task failed for the resumed export", th)));
            }

            public void onSuccess(Void voidR) {
                Transformer.this.onExportCompletedWithSuccess();
            }
        };
        HandlerWrapper handlerWrapper = this.applicationHandler;
        Objects.requireNonNull(handlerWrapper);
        copyFileAsync.addListener(new e(copyFileAsync, r1, false, 7), new b(2, handlerWrapper));
    }

    private int getTrimOptimizationProgress(ProgressHolder progressHolder) {
        int progress;
        if (this.mediaItemInfo == null) {
            return 1;
        }
        long j2 = ((EditedMediaItem) ((EditedMediaItemSequence) ((Composition) Assertions.checkNotNull(this.composition)).sequences.get(0)).editedMediaItems.get(0)).mediaItem.clippingConfiguration.startPositionUs;
        Mp4Info mp4Info = this.mediaItemInfo;
        float f = ((float) (mp4Info.firstSyncSampleTimestampUsAfterTimeUs - j2)) / ((float) mp4Info.durationUs);
        if (this.transformerState == 5) {
            TransformerInternal transformerInternal2 = this.transformerInternal;
            if (transformerInternal2 == null || (progress = transformerInternal2.getProgress(progressHolder)) == 0 || progress == 1) {
                return 1;
            }
            if (progress == 2) {
                progressHolder.progress = Math.round(((float) progressHolder.progress) * f);
                return 2;
            } else if (progress == 3) {
                return 3;
            } else {
                throw new IllegalStateException();
            }
        } else {
            float f5 = 100.0f * f;
            TransformerInternal transformerInternal3 = this.transformerInternal;
            if (transformerInternal3 == null) {
                progressHolder.progress = Math.round(f5);
                return 2;
            }
            int progress2 = transformerInternal3.getProgress(progressHolder);
            if (progress2 == 0 || progress2 == 1) {
                progressHolder.progress = Math.round(f5);
                return 2;
            } else if (progress2 == 2) {
                progressHolder.progress = Math.round(((1.0f - f) * ((float) progressHolder.progress)) + f5);
                return 2;
            } else if (progress2 == 3) {
                return 3;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private void initialize(Composition composition2, String str) {
        maybeInitializeExportWatchdogTimer();
        this.composition = composition2;
        this.outputFilePath = str;
        this.exportResultBuilder.reset();
    }

    /* access modifiers changed from: private */
    public boolean isExportResumed() {
        int i2 = this.transformerState;
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isExportTrimOptimization() {
        int i2 = this.transformerState;
        if (i2 == 5 || i2 == 6) {
            return true;
        }
        return false;
    }

    private boolean isMultiAsset() {
        if (((Composition) Assertions.checkNotNull(this.composition)).sequences.size() > 1 || ((EditedMediaItemSequence) this.composition.sequences.get(0)).editedMediaItems.size() > 1) {
            return true;
        }
        return false;
    }

    private boolean isSingleAssetTrimming() {
        if (isMultiAsset()) {
            return false;
        }
        return !((EditedMediaItem) ((EditedMediaItemSequence) ((Composition) Assertions.checkNotNull(this.composition)).sequences.get(0)).editedMediaItems.get(0)).mediaItem.clippingConfiguration.equals(MediaItem.ClippingConfiguration.UNSET);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$maybeInitializeExportWatchdogTimer$0() {
        ((TransformerInternal) Assertions.checkNotNull(this.transformerInternal)).endWithException(ExportException.createForMuxer(new IllegalStateException(Util.formatInvariant("Abort: no output sample written in the last %d milliseconds. DebugTrace: %s", Long.valueOf(this.maxDelayBetweenMuxerSamplesMs), DebugTraceUtil.generateTraceSummary())), 7002));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExportCompletedWithError$2(ExportResult exportResult, ExportException exportException, Listener listener) {
        listener.onError((Composition) Assertions.checkNotNull(this.composition), exportResult, exportException);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExportCompletedWithSuccess$1(ExportResult exportResult, Listener listener) {
        listener.onCompleted((Composition) Assertions.checkNotNull(this.composition), exportResult);
    }

    private void maybeInitializeExportWatchdogTimer() {
        long j2 = this.maxDelayBetweenMuxerSamplesMs;
        if (j2 != -9223372036854775807L) {
            WatchdogTimer watchdogTimer = new WatchdogTimer(j2, new g(12, this));
            this.exportWatchdogTimer = watchdogTimer;
            watchdogTimer.start();
        }
    }

    private void maybeStopExportWatchdogTimer() {
        WatchdogTimer watchdogTimer = this.exportWatchdogTimer;
        if (watchdogTimer != null) {
            watchdogTimer.stop();
            this.exportWatchdogTimer = null;
        }
    }

    /* access modifiers changed from: private */
    public void onExportCompletedWithError(ExportException exportException) {
        maybeStopExportWatchdogTimer();
        ExportResult build = this.exportResultBuilder.build();
        int i2 = -1;
        this.listeners.queueEvent(-1, new Q((Object) this, (Object) build, (Object) exportException, 14));
        this.listeners.flushEvents();
        if (canCollectEditingMetrics()) {
            ProgressHolder progressHolder = new ProgressHolder();
            if (getProgress(progressHolder) == 2) {
                i2 = progressHolder.progress;
            }
            ((EditingMetricsCollector) Assertions.checkNotNull(this.editingMetricsCollector)).onExportError(i2, exportException, build);
        }
        this.transformerState = 0;
    }

    /* access modifiers changed from: private */
    public void onExportCompletedWithSuccess() {
        maybeStopExportWatchdogTimer();
        ExportResult build = this.exportResultBuilder.build();
        this.listeners.queueEvent(-1, new O3.b(19, this, build));
        this.listeners.flushEvents();
        if (canCollectEditingMetrics()) {
            ((EditingMetricsCollector) Assertions.checkNotNull(this.editingMetricsCollector)).onExportSuccess(build);
        }
        this.transformerState = 0;
    }

    /* access modifiers changed from: private */
    public void processAudio() {
        this.transformerState = 3;
        MuxerWrapper muxerWrapper = new MuxerWrapper((String) Assertions.checkNotNull(this.oldFilePath), this.muxerFactory, this.componentListener, 0, false, (Format) null, shouldApplyMp4EditListTrim());
        startInternal(TransmuxTranscodeHelper.createAudioTranscodeAndVideoTransmuxComposition((Composition) Assertions.checkNotNull(this.composition), (String) Assertions.checkNotNull(this.outputFilePath)), muxerWrapper, this.componentListener, 0, false);
    }

    /* access modifiers changed from: private */
    public void processFullInput() {
        this.transformerState = 0;
        startInternal((Composition) Assertions.checkNotNull(this.composition), new MuxerWrapper((String) Assertions.checkNotNull(this.outputFilePath), this.muxerFactory, this.componentListener, 0, false, (Format) null, false), this.componentListener, 0, false);
    }

    private void processMediaBeforeFirstSyncSampleAfterTrimStartTime() {
        this.transformerState = 5;
        final EditedMediaItem editedMediaItem = (EditedMediaItem) ((EditedMediaItemSequence) ((Composition) Assertions.checkNotNull(this.composition)).sequences.get(0)).editedMediaItems.get(0);
        MediaItem mediaItem = editedMediaItem.mediaItem;
        MediaItem.ClippingConfiguration clippingConfiguration = mediaItem.clippingConfiguration;
        final long j2 = clippingConfiguration.startPositionUs;
        final long j3 = clippingConfiguration.endPositionUs;
        ListenableFuture mp4Info = TransmuxTranscodeHelper.getMp4Info(this.context, ((MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration)).uri.toString(), j2);
        AnonymousClass3 r1 = new s() {
            public void onFailure(Throwable th) {
                Transformer.this.exportResultBuilder.setOptimizationResult(5);
                Transformer.this.processFullInput();
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
                r2 = r2.sampleRate;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSuccess(androidx.media3.transformer.Mp4Info r20) {
                /*
                    r19 = this;
                    r0 = r19
                    r1 = r20
                    long r2 = r1.firstSyncSampleTimestampUsAfterTimeUs
                    r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
                    int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                    if (r4 != 0) goto L_0x001f
                    androidx.media3.transformer.Transformer r1 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.ExportResult$Builder r1 = r1.exportResultBuilder
                    r2 = 4
                    r1.setOptimizationResult(r2)
                    androidx.media3.transformer.Transformer r0 = androidx.media3.transformer.Transformer.this
                    r0.processFullInput()
                    return
                L_0x001f:
                    r4 = -9223372036854775808
                    int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                    r7 = 2
                    if (r6 == 0) goto L_0x0175
                    long r8 = r3
                    int r4 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
                    if (r4 == 0) goto L_0x0032
                    int r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
                    if (r2 >= 0) goto L_0x0032
                    goto L_0x0175
                L_0x0032:
                    androidx.media3.common.Format r2 = r1.audioFormat
                    if (r2 == 0) goto L_0x0042
                    int r2 = r2.sampleRate
                    r3 = -1
                    if (r2 == r3) goto L_0x0042
                    r3 = 1024(0x400, double:5.06E-321)
                    long r2 = androidx.media3.common.util.Util.sampleCountToDurationUs(r3, r2)
                    goto L_0x0044
                L_0x0042:
                    r2 = 0
                L_0x0044:
                    long r4 = r1.firstSyncSampleTimestampUsAfterTimeUs
                    long r8 = r1.firstVideoSampleTimestampUs
                    int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                    if (r6 != 0) goto L_0x0071
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.Composition r8 = r2.composition
                    long r9 = r5
                    long r11 = r3
                    long r13 = r1.durationUs
                    r15 = 1
                    r16 = 0
                    androidx.media3.transformer.Composition r1 = androidx.media3.transformer.TransmuxTranscodeHelper.buildUponCompositionForTrimOptimization(r8, r9, r11, r13, r15, r16)
                    androidx.media3.transformer.Composition unused = r2.composition = r1
                    androidx.media3.transformer.Transformer r1 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.ExportResult$Builder r1 = r1.exportResultBuilder
                    r1.setOptimizationResult(r7)
                    androidx.media3.transformer.Transformer r0 = androidx.media3.transformer.Transformer.this
                    r0.processFullInput()
                    return
                L_0x0071:
                    long r8 = r5
                    long r4 = r4 - r8
                    int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
                    if (r2 <= 0) goto L_0x0150
                    boolean r2 = r1.isFirstVideoSampleAfterTimeUsSyncSample
                    if (r2 == 0) goto L_0x007e
                    goto L_0x0150
                L_0x007e:
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.MuxerWrapper r3 = new androidx.media3.transformer.MuxerWrapper
                    androidx.media3.transformer.Transformer r4 = androidx.media3.transformer.Transformer.this
                    java.lang.String r4 = r4.outputFilePath
                    java.lang.Object r4 = androidx.media3.common.util.Assertions.checkNotNull(r4)
                    java.lang.String r4 = (java.lang.String) r4
                    androidx.media3.transformer.Transformer r5 = androidx.media3.transformer.Transformer.this
                    androidx.media3.muxer.Muxer$Factory r5 = r5.muxerFactory
                    androidx.media3.transformer.Transformer r6 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.Transformer$ComponentListener r6 = r6.componentListener
                    androidx.media3.common.Format r9 = r1.videoFormat
                    r10 = 0
                    r7 = 1
                    r8 = 0
                    r3.<init>(r4, r5, r6, r7, r8, r9, r10)
                    androidx.media3.transformer.MuxerWrapper unused = r2.remuxingMuxerWrapper = r3
                    androidx.media3.common.Format r2 = r1.videoFormat
                    java.lang.Object r2 = androidx.media3.common.util.Assertions.checkNotNull(r2)
                    r3 = r2
                    androidx.media3.common.Format r3 = (androidx.media3.common.Format) r3
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.Composition r4 = r2.composition
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.TransformationRequest r6 = r2.transformationRequest
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.Codec$EncoderFactory r7 = r2.encoderFactory
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.MuxerWrapper r8 = r2.remuxingMuxerWrapper
                    r5 = 0
                    boolean r2 = androidx.media3.transformer.TransformerUtil.shouldTranscodeVideo(r3, r4, r5, r6, r7, r8)
                    if (r2 != 0) goto L_0x013a
                    androidx.media3.common.Format r3 = r1.audioFormat
                    if (r3 == 0) goto L_0x00f1
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.Composition r4 = r2.composition
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.TransformationRequest r6 = r2.transformationRequest
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.Codec$EncoderFactory r7 = r2.encoderFactory
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.MuxerWrapper r8 = r2.remuxingMuxerWrapper
                    r5 = 0
                    boolean r2 = androidx.media3.transformer.TransformerUtil.shouldTranscodeAudio(r3, r4, r5, r6, r7, r8)
                    if (r2 == 0) goto L_0x00f1
                    goto L_0x013a
                L_0x00f1:
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.Mp4Info unused = r2.mediaItemInfo = r1
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.MuxerWrapper r2 = r2.remuxingMuxerWrapper
                    androidx.media3.transformer.EditedMediaItem r3 = r7
                    androidx.media3.transformer.Effects r3 = r3.effects
                    F2.U r3 = r3.videoEffects
                    androidx.media3.common.Format r4 = r1.videoFormat
                    java.lang.Object r4 = androidx.media3.common.util.Assertions.checkNotNull(r4)
                    androidx.media3.common.Format r4 = (androidx.media3.common.Format) r4
                    androidx.media3.transformer.TransformerUtil.maybeSetMuxerWrapperAdditionalRotationDegrees(r2, r3, r4)
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.Composition r3 = r2.composition
                    long r4 = r5
                    long r6 = r1.firstSyncSampleTimestampUsAfterTimeUs
                    long r8 = r1.durationUs
                    r10 = 0
                    r11 = 1
                    androidx.media3.transformer.Composition r13 = androidx.media3.transformer.TransmuxTranscodeHelper.buildUponCompositionForTrimOptimization(r3, r4, r6, r8, r10, r11)
                    androidx.media3.transformer.Transformer r12 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.MuxerWrapper r1 = r12.remuxingMuxerWrapper
                    java.lang.Object r1 = androidx.media3.common.util.Assertions.checkNotNull(r1)
                    r14 = r1
                    androidx.media3.transformer.MuxerWrapper r14 = (androidx.media3.transformer.MuxerWrapper) r14
                    androidx.media3.transformer.Transformer r0 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.Transformer$ComponentListener r15 = r0.componentListener
                    r16 = 0
                    r18 = 0
                    r12.startInternal(r13, r14, r15, r16, r18)
                    return
                L_0x013a:
                    androidx.media3.transformer.Transformer r1 = androidx.media3.transformer.Transformer.this
                    r2 = 0
                    androidx.media3.transformer.MuxerWrapper unused = r1.remuxingMuxerWrapper = r2
                    androidx.media3.transformer.Transformer r1 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.ExportResult$Builder r1 = r1.exportResultBuilder
                    r2 = 3
                    r1.setOptimizationResult(r2)
                    androidx.media3.transformer.Transformer r0 = androidx.media3.transformer.Transformer.this
                    r0.processFullInput()
                    return
                L_0x0150:
                    androidx.media3.transformer.Transformer r2 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.Composition r8 = r2.composition
                    long r9 = r1.firstSyncSampleTimestampUsAfterTimeUs
                    long r11 = r3
                    long r13 = r1.durationUs
                    r15 = 1
                    r16 = 0
                    androidx.media3.transformer.Composition r1 = androidx.media3.transformer.TransmuxTranscodeHelper.buildUponCompositionForTrimOptimization(r8, r9, r11, r13, r15, r16)
                    androidx.media3.transformer.Composition unused = r2.composition = r1
                    androidx.media3.transformer.Transformer r1 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.ExportResult$Builder r1 = r1.exportResultBuilder
                    r1.setOptimizationResult(r7)
                    androidx.media3.transformer.Transformer r0 = androidx.media3.transformer.Transformer.this
                    r0.processFullInput()
                    return
                L_0x0175:
                    androidx.media3.transformer.Transformer r1 = androidx.media3.transformer.Transformer.this
                    androidx.media3.transformer.ExportResult$Builder r1 = r1.exportResultBuilder
                    r1.setOptimizationResult(r7)
                    androidx.media3.transformer.Transformer r0 = androidx.media3.transformer.Transformer.this
                    r0.processFullInput()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.Transformer.AnonymousClass3.onSuccess(androidx.media3.transformer.Mp4Info):void");
            }
        };
        HandlerWrapper handlerWrapper = this.applicationHandler;
        Objects.requireNonNull(handlerWrapper);
        mp4Info.addListener(new e(mp4Info, r1, false, 7), new b(2, handlerWrapper));
    }

    /* access modifiers changed from: private */
    public void processRemainingVideo() {
        this.transformerState = 2;
        TransmuxTranscodeHelper.buildUponComposition((Composition) Assertions.checkNotNull(this.composition), true, false, (TransmuxTranscodeHelper.ResumeMetadata) null);
        Assertions.checkNotNull(this.remuxingMuxerWrapper);
        this.remuxingMuxerWrapper.changeToAppendMode();
        Assertions.checkNotNull(null).getClass();
        throw new ClassCastException();
    }

    /* access modifiers changed from: private */
    public void remuxRemainingMedia() {
        this.transformerState = 6;
        Mp4Info mp4Info = (Mp4Info) Assertions.checkNotNull(this.mediaItemInfo);
        MediaItem.ClippingConfiguration clippingConfiguration = ((EditedMediaItem) ((EditedMediaItemSequence) ((Composition) Assertions.checkNotNull(this.composition)).sequences.get(0)).editedMediaItems.get(0)).mediaItem.clippingConfiguration;
        long j2 = clippingConfiguration.startPositionUs;
        Composition buildUponCompositionForTrimOptimization = TransmuxTranscodeHelper.buildUponCompositionForTrimOptimization(this.composition, mp4Info.firstSyncSampleTimestampUsAfterTimeUs, clippingConfiguration.endPositionUs, mp4Info.durationUs, true, true);
        Assertions.checkNotNull(this.remuxingMuxerWrapper);
        this.remuxingMuxerWrapper.changeToAppendMode();
        MuxerWrapper muxerWrapper = this.remuxingMuxerWrapper;
        startInternal(buildUponCompositionForTrimOptimization, muxerWrapper, this.componentListener, mp4Info.firstSyncSampleTimestampUsAfterTimeUs - j2, false);
    }

    private boolean shouldApplyMp4EditListTrim() {
        if (!this.mp4EditListTrimEnabled || !isSingleAssetTrimming()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void startInternal(Composition composition2, MuxerWrapper muxerWrapper, ComponentListener componentListener2, long j2, boolean z) {
        boolean z3;
        String str;
        Composition composition3 = composition2;
        if (this.transformerInternal == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        Assertions.checkState(z3, "There is already an export in progress.");
        TransformationRequest transformationRequest2 = this.transformationRequest;
        if (composition3.hdrMode != 0) {
            transformationRequest2 = transformationRequest2.buildUpon().setHdrMode(composition3.hdrMode).build();
        }
        TransformationRequest transformationRequest3 = transformationRequest2;
        LogSessionId logSessionId = null;
        if (canCollectEditingMetrics()) {
            if (this.muxerFactory instanceof DefaultMuxer.Factory) {
                str = DefaultMuxer.MUXER_NAME;
            } else {
                str = null;
            }
            EditingMetricsCollector.MetricsReporter create = ((EditingMetricsCollector.MetricsReporter.Factory) Assertions.checkNotNull(this.metricsReporterFactory)).create();
            if (create instanceof EditingMetricsCollector.DefaultMetricsReporter) {
                logSessionId = ((EditingMetricsCollector.DefaultMetricsReporter) create).getLogSessionId();
            }
            this.editingMetricsCollector = new EditingMetricsCollector(create, "androidx.media3:media3-transformer:1.8.0", str);
        }
        FallbackListener fallbackListener = new FallbackListener(composition3, this.listeners, this.applicationHandler, transformationRequest3);
        AssetLoader.Factory factory = this.assetLoaderFactory;
        if (z || factory == null) {
            Context context2 = this.context;
            factory = new DefaultAssetLoaderFactory(context2, new DefaultDecoderFactory.Builder(context2).build(), this.clock, logSessionId);
        }
        AssetLoader.Factory factory2 = factory;
        DebugTraceUtil.reset();
        LogSessionId logSessionId2 = logSessionId;
        MuxerWrapper muxerWrapper2 = muxerWrapper;
        TransformerInternal transformerInternal2 = new TransformerInternal(this.context, composition3, transformationRequest3, factory2, this.audioMixerFactory, this.videoFrameProcessorFactory, this.encoderFactory, this.allowedEncodingRotationDegrees, this.maxFramesInEncoder, muxerWrapper2, componentListener2, fallbackListener, this.applicationHandler, this.debugViewProvider, this.clock, j2, logSessionId2, shouldApplyMp4EditListTrim());
        this.transformerInternal = transformerInternal2;
        transformerInternal2.start();
    }

    private void verifyApplicationThread() {
        if (Looper.myLooper() != this.looper) {
            throw new IllegalStateException("Transformer is accessed on the wrong thread.");
        }
    }

    public void cancel() {
        verifyApplicationThread();
        TransformerInternal transformerInternal2 = this.transformerInternal;
        if (transformerInternal2 == null) {
            maybeStopExportWatchdogTimer();
            return;
        }
        int i2 = -1;
        try {
            transformerInternal2.cancel();
            ListenableFuture listenableFuture = this.getResumeMetadataFuture;
            if (listenableFuture != null && !listenableFuture.isDone()) {
                this.getResumeMetadataFuture.cancel(false);
            }
            ListenableFuture listenableFuture2 = this.copyOutputFuture;
            if (listenableFuture2 != null && !listenableFuture2.isDone()) {
                this.copyOutputFuture.cancel(false);
            }
            maybeStopExportWatchdogTimer();
        } finally {
            ProgressHolder progressHolder = new ProgressHolder();
            int progress = getProgress(progressHolder);
            this.transformerInternal = null;
            if (canCollectEditingMetrics()) {
                if (progress == 2) {
                    i2 = progressHolder.progress;
                }
                ((EditingMetricsCollector) Assertions.checkNotNull(this.editingMetricsCollector)).onExportCancelled(i2);
            }
        }
    }

    public int getProgress(ProgressHolder progressHolder) {
        verifyApplicationThread();
        if (isExportResumed()) {
            return 3;
        }
        if (isExportTrimOptimization()) {
            return getTrimOptimizationProgress(progressHolder);
        }
        TransformerInternal transformerInternal2 = this.transformerInternal;
        if (transformerInternal2 == null) {
            return 0;
        }
        return transformerInternal2.getProgress(progressHolder);
    }

    public void start(Composition composition2, String str) {
        verifyApplicationThread();
        initialize(composition2, str);
        if (!this.trimOptimizationEnabled || !isSingleAssetTrimming()) {
            MuxerWrapper muxerWrapper = new MuxerWrapper(str, this.muxerFactory, this.componentListener, 0, this.fileStartsOnVideoFrameEnabled, (Format) null, shouldApplyMp4EditListTrim());
            MuxerWrapper muxerWrapper2 = muxerWrapper;
            startInternal(composition2, muxerWrapper2, this.componentListener, 0, false);
            return;
        }
        processMediaBeforeFirstSyncSampleAfterTrimStartTime();
    }

    private Transformer(Context context2, TransformationRequest transformationRequest2, U u, U u3, boolean z, boolean z3, boolean z7, boolean z9, U u6, boolean z10, boolean z11, long j2, int i2, ListenerSet<Listener> listenerSet, AssetLoader.Factory factory, AudioMixer.Factory factory2, VideoFrameProcessor.Factory factory3, Codec.EncoderFactory encoderFactory2, Muxer.Factory factory4, Looper looper2, DebugViewProvider debugViewProvider2, Clock clock2, EditingMetricsCollector.MetricsReporter.Factory factory5) {
        Looper looper3 = looper2;
        Clock clock3 = clock2;
        Assertions.checkState(!z || !z3, "Audio and video cannot both be removed.");
        this.context = context2;
        this.transformationRequest = transformationRequest2;
        this.audioProcessors = u;
        this.videoEffects = u3;
        this.removeAudio = z;
        this.removeVideo = z3;
        this.trimOptimizationEnabled = z7;
        this.mp4EditListTrimEnabled = z9;
        this.allowedEncodingRotationDegrees = u6;
        this.fileStartsOnVideoFrameEnabled = z10;
        this.usePlatformDiagnostics = z11;
        this.maxDelayBetweenMuxerSamplesMs = j2;
        this.maxFramesInEncoder = i2;
        this.listeners = listenerSet;
        this.assetLoaderFactory = factory;
        this.audioMixerFactory = factory2;
        this.videoFrameProcessorFactory = factory3;
        this.encoderFactory = encoderFactory2;
        this.muxerFactory = factory4;
        this.looper = looper3;
        this.debugViewProvider = debugViewProvider2;
        this.clock = clock3;
        this.metricsReporterFactory = factory5;
        this.transformerState = 0;
        this.applicationHandler = clock3.createHandler(looper3, (Handler.Callback) null);
        this.componentListener = new ComponentListener(this, (AnonymousClass1) null);
        this.exportResultBuilder = new ExportResult.Builder();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private static final U ALL_ROTATION_DEGREES;
        private U allowedEncodingRotationDegrees;
        private AssetLoader.Factory assetLoaderFactory;
        private String audioMimeType;
        private AudioMixer.Factory audioMixerFactory;
        private final U audioProcessors;
        private Clock clock;
        private final Context context;
        private DebugViewProvider debugViewProvider;
        private Codec.EncoderFactory encoderFactory;
        private boolean fileStartsOnVideoFrameEnabled;
        private ListenerSet<Listener> listeners;
        private Looper looper;
        private long maxDelayBetweenMuxerSamplesMs = Transformer.DEFAULT_MAX_DELAY_BETWEEN_MUXER_SAMPLES_MS;
        private int maxFramesInEncoder = -1;
        private EditingMetricsCollector.MetricsReporter.Factory metricsReporterFactory;
        private boolean mp4EditListTrimEnabled;
        private Muxer.Factory muxerFactory;
        private boolean removeAudio;
        private boolean removeVideo;
        private TransformationRequest transformationRequest;
        private boolean trimOptimizationEnabled;
        private boolean usePlatformDiagnostics;
        private final U videoEffects;
        private VideoFrameProcessor.Factory videoFrameProcessorFactory;
        private String videoMimeType;

        static {
            Integer valueOf = Integer.valueOf(MOCRLang.KHMER);
            G g = U.e;
            Object[] objArr = {0, 90, valueOf, 270};
            C0040v.a(4, objArr);
            ALL_ROTATION_DEGREES = U.w(4, objArr);
        }

        public Builder(Context context2) {
            Context applicationContext = context2.getApplicationContext();
            this.context = applicationContext;
            G g = U.e;
            y0 y0Var = y0.f278h;
            this.audioProcessors = y0Var;
            this.videoEffects = y0Var;
            this.audioMixerFactory = new DefaultAudioMixer.Factory();
            this.videoFrameProcessorFactory = new DefaultVideoFrameProcessor.Factory.Builder().build();
            this.encoderFactory = new DefaultEncoderFactory.Builder(applicationContext).build();
            this.muxerFactory = new DefaultMuxer.Factory();
            Looper currentOrMainLooper = Util.getCurrentOrMainLooper();
            this.looper = currentOrMainLooper;
            this.debugViewProvider = DebugViewProvider.NONE;
            Clock clock2 = Clock.DEFAULT;
            this.clock = clock2;
            this.listeners = new ListenerSet<>(currentOrMainLooper, clock2, new a(7));
            if (Build.VERSION.SDK_INT >= 35) {
                this.usePlatformDiagnostics = true;
                this.metricsReporterFactory = new EditingMetricsCollector.DefaultMetricsReporter.Factory(context2);
            }
            this.allowedEncodingRotationDegrees = ALL_ROTATION_DEGREES;
        }

        private void checkSampleMimeType(String str) {
            boolean contains = this.muxerFactory.getSupportedSampleMimeTypes(MimeTypes.getTrackType(str)).contains(str);
            Assertions.checkState(contains, "Unsupported sample MIME type " + str);
        }

        public Builder addListener(Listener listener) {
            this.listeners.add(listener);
            return this;
        }

        public Transformer build() {
            TransformationRequest.Builder builder;
            boolean z;
            TransformationRequest transformationRequest2 = this.transformationRequest;
            if (transformationRequest2 == null) {
                builder = new TransformationRequest.Builder();
            } else {
                builder = transformationRequest2.buildUpon();
            }
            String str = this.audioMimeType;
            if (str != null) {
                builder.setAudioMimeType(str);
            }
            String str2 = this.videoMimeType;
            if (str2 != null) {
                builder.setVideoMimeType(str2);
            }
            TransformationRequest build = builder.build();
            this.transformationRequest = build;
            String str3 = build.audioMimeType;
            if (str3 != null) {
                checkSampleMimeType(str3);
            }
            String str4 = this.transformationRequest.videoMimeType;
            if (str4 != null) {
                checkSampleMimeType(str4);
            }
            if (!this.mp4EditListTrimEnabled || this.muxerFactory.supportsWritingNegativeTimestampsInEditList()) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z, String.format("Muxer.Factory %s does not support writing negative timestamps to an edit list.", new Object[]{this.muxerFactory}));
            Context context2 = this.context;
            TransformationRequest transformationRequest3 = this.transformationRequest;
            U u = this.audioProcessors;
            U u3 = this.videoEffects;
            boolean z3 = this.removeAudio;
            boolean z7 = this.removeVideo;
            boolean z9 = this.trimOptimizationEnabled;
            boolean z10 = this.mp4EditListTrimEnabled;
            U u6 = this.allowedEncodingRotationDegrees;
            boolean z11 = this.fileStartsOnVideoFrameEnabled;
            boolean z12 = this.usePlatformDiagnostics;
            long j2 = this.maxDelayBetweenMuxerSamplesMs;
            int i2 = this.maxFramesInEncoder;
            long j3 = j2;
            ListenerSet<Listener> listenerSet = this.listeners;
            AssetLoader.Factory factory = this.assetLoaderFactory;
            return new Transformer(context2, transformationRequest3, u, u3, z3, z7, z9, z10, u6, z11, z12, j3, i2, listenerSet, factory, this.audioMixerFactory, this.videoFrameProcessorFactory, this.encoderFactory, this.muxerFactory, this.looper, this.debugViewProvider, this.clock, this.metricsReporterFactory, (AnonymousClass1) null);
        }

        public Builder setAudioMimeType(String str) {
            String normalizeMimeType = MimeTypes.normalizeMimeType(str);
            boolean isAudio = MimeTypes.isAudio(normalizeMimeType);
            Assertions.checkArgument(isAudio, "Not an audio MIME type: " + normalizeMimeType);
            this.audioMimeType = normalizeMimeType;
            return this;
        }

        public Builder setEncoderFactory(Codec.EncoderFactory encoderFactory2) {
            this.encoderFactory = encoderFactory2;
            return this;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$new$0(Listener listener, FlagSet flagSet) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onCompleted(Composition composition, ExportResult exportResult);

        void onError(Composition composition, ExportResult exportResult, ExportException exportException);

        void onFallbackApplied(Composition composition, TransformationRequest transformationRequest, TransformationRequest transformationRequest2) {
        }
    }
}
