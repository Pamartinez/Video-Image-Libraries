package androidx.media3.transformer;

import F2.N;
import F2.Q;
import F2.U;
import N2.j;
import android.content.Context;
import android.media.metrics.LogSessionId;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.VideoCompositorSettings;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Util;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.muxer.MuxerException;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.AudioMixer;
import androidx.media3.transformer.Codec;
import bc.C0584a;
import c0.C0086a;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TransformerInternal {
    /* access modifiers changed from: private */
    public final U allowedEncodingRotationDegrees;
    private final HandlerWrapper applicationHandler;
    /* access modifiers changed from: private */
    public final boolean applyMp4EditListTrim;
    /* access modifiers changed from: private */
    public final AssetLoaderInputTracker assetLoaderInputTracker;
    /* access modifiers changed from: private */
    public final Object assetLoaderLock;
    private RuntimeException cancelException;
    private final ConditionVariable canceledConditionVariable;
    private final Clock clock;
    private final Composition composition;
    /* access modifiers changed from: private */
    public final boolean compositionHasLoopingSequence;
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public long currentMaxSequenceDurationUs;
    /* access modifiers changed from: private */
    public final CapturingEncoderFactory encoderFactory;
    /* access modifiers changed from: private */
    public final HandlerWrapper internalHandler;
    private final HandlerThread internalHandlerThread;
    private final ProgressHolder internalProgressHolder;
    private boolean isDrainingExporters;
    private final Listener listener;
    /* access modifiers changed from: private */
    public final int maxFramesInEncoder;
    /* access modifiers changed from: private */
    public final MuxerWrapper muxerWrapper;
    /* access modifiers changed from: private */
    public int nonLoopingSequencesWithNonFinalDuration;
    private final Object progressLock;
    private int progressState;
    private int progressValue;
    private final Object releaseLock;
    private boolean released;
    private final List<SampleExporter> sampleExporters;
    /* access modifiers changed from: private */
    public final List<SequenceAssetLoader> sequenceAssetLoaders;
    /* access modifiers changed from: private */
    public final Object setMaxSequenceDurationUsLock;
    /* access modifiers changed from: private */
    public final long videoSampleTimestampOffsetUs;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AssetLoaderInputTracker {
        private final List<SequenceMetadata> sequencesMetadata = new ArrayList();
        private final SparseArray<Integer> trackTypeToNumberOfRegisteredGraphInput;
        private final SparseArray<SampleExporter> trackTypeToSampleExporter;
        private final SparseArray<Boolean> trackTypeToShouldTranscode;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class SequenceMetadata {
            public int requiredTrackCount = -1;
            public final SparseArray<Format> trackTypeToFirstAssetLoaderInputFormat = new SparseArray<>();
        }

        public AssetLoaderInputTracker(Composition composition) {
            for (int i2 = 0; i2 < composition.sequences.size(); i2++) {
                this.sequencesMetadata.add(new SequenceMetadata());
            }
            this.trackTypeToSampleExporter = new SparseArray<>();
            this.trackTypeToShouldTranscode = new SparseArray<>();
            this.trackTypeToNumberOfRegisteredGraphInput = new SparseArray<>();
        }

        public Format getAssetLoaderInputFormat(int i2, int i7) {
            SparseArray<Format> sparseArray = this.sequencesMetadata.get(i2).trackTypeToFirstAssetLoaderInputFormat;
            Assertions.checkState(Util.contains(sparseArray, i7));
            return sparseArray.get(i7);
        }

        public int getIndexForPrimarySequence(int i2) {
            Assertions.checkState(hasRegisteredAllTracks(), "Primary track can only be queried after all tracks are added.");
            for (int i7 = 0; i7 < this.sequencesMetadata.size(); i7++) {
                if (Util.contains(this.sequencesMetadata.get(i7).trackTypeToFirstAssetLoaderInputFormat, i2)) {
                    return i7;
                }
            }
            return -1;
        }

        public int getOutputTrackCount() {
            int i2 = 0;
            int i7 = 0;
            for (int i8 = 0; i8 < this.sequencesMetadata.size(); i8++) {
                SparseArray<Format> sparseArray = this.sequencesMetadata.get(i8).trackTypeToFirstAssetLoaderInputFormat;
                if (Util.contains(sparseArray, 1)) {
                    i2 = 1;
                }
                if (Util.contains(sparseArray, 2)) {
                    i7 = 1;
                }
            }
            return i2 + i7;
        }

        public SampleExporter getSampleExporter(int i2) {
            return this.trackTypeToSampleExporter.get(i2);
        }

        public boolean hasAllTrackCounts() {
            for (int i2 = 0; i2 < this.sequencesMetadata.size(); i2++) {
                if (this.sequencesMetadata.get(i2).requiredTrackCount == -1) {
                    return false;
                }
            }
            return true;
        }

        public boolean hasAssociatedAllTracksWithGraphInput(int i2) {
            int i7 = 0;
            for (int i8 = 0; i8 < this.sequencesMetadata.size(); i8++) {
                if (Util.contains(this.sequencesMetadata.get(i8).trackTypeToFirstAssetLoaderInputFormat, i2)) {
                    i7++;
                }
            }
            if (this.trackTypeToNumberOfRegisteredGraphInput.get(i2).intValue() == i7) {
                return true;
            }
            return false;
        }

        public boolean hasMultipleConcurrentVideoTracks() {
            if (this.sequencesMetadata.size() < 2) {
                return false;
            }
            int i2 = 0;
            for (int i7 = 0; i7 < this.sequencesMetadata.size(); i7++) {
                if (Util.contains(this.sequencesMetadata.get(i7).trackTypeToFirstAssetLoaderInputFormat, 2)) {
                    i2++;
                }
            }
            if (i2 > 1) {
                return true;
            }
            return false;
        }

        public boolean hasRegisteredAllTracks() {
            if (!hasAllTrackCounts()) {
                return false;
            }
            for (int i2 = 0; i2 < this.sequencesMetadata.size(); i2++) {
                SequenceMetadata sequenceMetadata = this.sequencesMetadata.get(i2);
                if (sequenceMetadata.requiredTrackCount != sequenceMetadata.trackTypeToFirstAssetLoaderInputFormat.size()) {
                    return false;
                }
            }
            return true;
        }

        public void registerGraphInput(int i2) {
            int i7 = 1;
            if (Util.contains(this.trackTypeToNumberOfRegisteredGraphInput, i2)) {
                i7 = 1 + this.trackTypeToNumberOfRegisteredGraphInput.get(i2).intValue();
            }
            this.trackTypeToNumberOfRegisteredGraphInput.put(i2, Integer.valueOf(i7));
        }

        public void registerSampleExporter(int i2, SampleExporter sampleExporter) {
            Assertions.checkState(!Util.contains(this.trackTypeToSampleExporter, i2), "Exactly one SampleExporter can be added for each track type.");
            this.trackTypeToSampleExporter.put(i2, sampleExporter);
        }

        public void registerTrack(int i2, Format format) {
            int processedTrackType = TransformerUtil.getProcessedTrackType(format.sampleMimeType);
            SparseArray<Format> sparseArray = this.sequencesMetadata.get(i2).trackTypeToFirstAssetLoaderInputFormat;
            Assertions.checkState(!Util.contains(sparseArray, processedTrackType));
            sparseArray.put(processedTrackType, format);
        }

        public boolean sequenceHasMultipleTracks(int i2) {
            if (this.sequencesMetadata.get(i2).trackTypeToFirstAssetLoaderInputFormat.size() > 1) {
                return true;
            }
            return false;
        }

        public void setShouldTranscode(int i2, boolean z) {
            boolean z3;
            if (Util.contains(this.trackTypeToShouldTranscode, i2)) {
                if (z == this.trackTypeToShouldTranscode.get(i2).booleanValue()) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                Assertions.checkState(z3);
                return;
            }
            this.trackTypeToShouldTranscode.put(i2, Boolean.valueOf(z));
        }

        public void setTrackCount(int i2, int i7) {
            this.sequencesMetadata.get(i2).requiredTrackCount = i7;
        }

        public boolean shouldTranscode(int i2) {
            Assertions.checkState(Util.contains(this.trackTypeToShouldTranscode, i2));
            return this.trackTypeToShouldTranscode.get(i2).booleanValue();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onCompleted(U u, String str, String str2);

        void onError(U u, String str, String str2, ExportException exportException);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.media3.transformer.TransformerInternal] */
    public TransformerInternal(Context context2, Composition composition2, TransformationRequest transformationRequest, AssetLoader.Factory factory, AudioMixer.Factory factory2, VideoFrameProcessor.Factory factory3, Codec.EncoderFactory encoderFactory2, U u, int i2, MuxerWrapper muxerWrapper2, Listener listener2, FallbackListener fallbackListener, HandlerWrapper handlerWrapper, DebugViewProvider debugViewProvider, Clock clock2, long j2, LogSessionId logSessionId, boolean z) {
        Composition composition3 = composition2;
        Clock clock3 = clock2;
        ? obj = new Object();
        obj.context = context2;
        obj.composition = composition3;
        obj.encoderFactory = new CapturingEncoderFactory(encoderFactory2);
        obj.allowedEncodingRotationDegrees = u;
        obj.maxFramesInEncoder = i2;
        obj.listener = listener2;
        obj.applicationHandler = handlerWrapper;
        obj.clock = clock3;
        obj.videoSampleTimestampOffsetUs = j2;
        obj.muxerWrapper = muxerWrapper2;
        obj.applyMp4EditListTrim = z;
        StringBuilder k = j.k("Init ", Integer.toHexString(System.identityHashCode(obj)), " [AndroidXMedia3/1.8.0] [");
        k.append(Util.DEVICE_DEBUG_INFO);
        k.append("]");
        Log.i("TransformerInternal", k.toString());
        HandlerThread handlerThread = new HandlerThread("Transformer:Internal");
        obj.internalHandlerThread = handlerThread;
        handlerThread.start();
        obj.sequenceAssetLoaders = new ArrayList();
        Looper looper = handlerThread.getLooper();
        obj.assetLoaderLock = new Object();
        obj.assetLoaderInputTracker = new AssetLoaderInputTracker(composition3);
        boolean z3 = false;
        int i7 = 0;
        TransformerInternal transformerInternal = obj;
        while (i7 < composition3.sequences.size()) {
            SequenceAssetLoaderListener sequenceAssetLoaderListener = new SequenceAssetLoaderListener(i7, composition3, transformationRequest, factory2, factory3, fallbackListener, debugViewProvider, logSessionId);
            TransformerInternal transformerInternal2 = transformerInternal;
            int i8 = i7;
            Composition composition4 = composition3;
            EditedMediaItemSequence editedMediaItemSequence = (EditedMediaItemSequence) composition4.sequences.get(i8);
            Clock clock4 = clock3;
            Looper looper2 = looper;
            transformerInternal2.sequenceAssetLoaders.add(new SequenceAssetLoader(editedMediaItemSequence, factory, new AssetLoader.CompositionSettings(transformationRequest.hdrMode, composition4.retainHdrFromUltraHdrImage), sequenceAssetLoaderListener, clock4, looper2));
            if (!editedMediaItemSequence.isLooping) {
                transformerInternal2.nonLoopingSequencesWithNonFinalDuration++;
            }
            i7 = i8 + 1;
            clock3 = clock4;
            looper = looper2;
            transformerInternal = transformerInternal2;
            composition3 = composition4;
        }
        TransformerInternal transformerInternal3 = transformerInternal;
        Clock clock5 = clock3;
        Looper looper3 = looper;
        transformerInternal3.compositionHasLoopingSequence = transformerInternal3.nonLoopingSequencesWithNonFinalDuration != composition3.sequences.size() ? true : z3;
        transformerInternal3.setMaxSequenceDurationUsLock = new Object();
        transformerInternal3.canceledConditionVariable = new ConditionVariable();
        transformerInternal3.progressLock = new Object();
        transformerInternal3.internalProgressHolder = new ProgressHolder();
        transformerInternal3.releaseLock = new Object();
        transformerInternal3.sampleExporters = new ArrayList();
        transformerInternal3.internalHandler = clock5.createHandler(looper3, new g(transformerInternal3));
    }

    public static /* synthetic */ int access$1310(TransformerInternal transformerInternal) {
        int i2 = transformerInternal.nonLoopingSequencesWithNonFinalDuration;
        transformerInternal.nonLoopingSequencesWithNonFinalDuration = i2 - 1;
        return i2;
    }

    /* access modifiers changed from: private */
    public boolean clippingRequiresTranscode(MediaItem mediaItem) {
        if (this.applyMp4EditListTrim) {
            return false;
        }
        MediaItem.ClippingConfiguration clippingConfiguration = mediaItem.clippingConfiguration;
        if (clippingConfiguration.startPositionMs <= 0 || clippingConfiguration.startsAtKeyFrame) {
            return false;
        }
        return true;
    }

    private void drainExportersInternal() {
        for (int i2 = 0; i2 < this.sampleExporters.size(); i2++) {
            do {
            } while (this.sampleExporters.get(i2).processData());
        }
        updateProgressInternal();
        if (!this.muxerWrapper.isEnded()) {
            this.internalHandler.sendEmptyMessageDelayed(3, 10);
        }
    }

    private void endInternal(int i2, ExportException exportException) {
        boolean z;
        N n = new N(4);
        for (int i7 = 0; i7 < this.sequenceAssetLoaders.size(); i7++) {
            n.c(this.sequenceAssetLoaders.get(i7).getProcessedInputs());
        }
        if (i2 == 1) {
            z = true;
        } else {
            z = false;
        }
        boolean z3 = this.released;
        ExportException exportException2 = null;
        if (!z3) {
            synchronized (this.releaseLock) {
                this.released = true;
            }
            Log.i("TransformerInternal", "Release " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.8.0] [" + Util.DEVICE_DEBUG_INFO + "] [" + MediaLibraryInfo.registeredModules() + "]");
            for (int i8 = 0; i8 < this.sampleExporters.size(); i8++) {
                try {
                    this.sampleExporters.get(i8).release();
                } catch (RuntimeException e) {
                    if (exportException2 == null) {
                        exportException2 = ExportException.createForUnexpected(e);
                        this.cancelException = e;
                    }
                }
            }
            for (int i10 = 0; i10 < this.sequenceAssetLoaders.size(); i10++) {
                try {
                    this.sequenceAssetLoaders.get(i10).release();
                } catch (RuntimeException e7) {
                    if (exportException2 == null) {
                        exportException2 = ExportException.createForUnexpected(e7);
                        this.cancelException = e7;
                    }
                }
            }
            try {
                this.muxerWrapper.finishWritingAndMaybeRelease(getMuxerReleaseReason(i2));
            } catch (MuxerException e8) {
                if (exportException2 == null) {
                    exportException2 = ExportException.createForMuxer(e8, 7001);
                }
            } catch (RuntimeException e9) {
                if (exportException2 == null) {
                    ExportException createForUnexpected = ExportException.createForUnexpected(e9);
                    this.cancelException = e9;
                    exportException2 = createForUnexpected;
                }
            }
            HandlerWrapper handlerWrapper = this.internalHandler;
            HandlerThread handlerThread = this.internalHandlerThread;
            Objects.requireNonNull(handlerThread);
            handlerWrapper.post(new C0584a(4, handlerThread));
        }
        if (z) {
            this.canceledConditionVariable.open();
            return;
        }
        if (exportException == null) {
            exportException = exportException2;
        }
        if (exportException != null) {
            if (z3) {
                Log.w("TransformerInternal", "Export error after export ended", exportException);
            } else {
                Assertions.checkState(this.applicationHandler.post(new e(this, n, exportException, 1)));
            }
        } else if (!z3) {
            Assertions.checkState(this.applicationHandler.post(new d(2, this, n)));
        }
    }

    private int getMuxerReleaseReason(int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (i2 == 1) {
            return 1;
        }
        if (i2 == 2) {
            return 2;
        }
        throw new IllegalStateException(C0086a.i(i2, "Unexpected end reason "));
    }

    /* access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        if (this.released && message.what != 4) {
            return true;
        }
        try {
            int i2 = message.what;
            if (i2 == 1) {
                startInternal();
            } else if (i2 == 2) {
                registerSampleExporterInternal((SampleExporter) message.obj);
            } else if (i2 == 3) {
                drainExportersInternal();
            } else if (i2 != 4) {
                return false;
            } else {
                endInternal(message.arg1, (ExportException) message.obj);
            }
        } catch (ExportException e) {
            endInternal(2, e);
        } catch (RuntimeException e7) {
            endInternal(2, ExportException.createForUnexpected(e7));
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$endInternal$0(Q q, ExportException exportException) {
        this.listener.onError(q.f(), this.encoderFactory.getAudioEncoderName(), this.encoderFactory.getVideoEncoderName(), exportException);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$endInternal$1(Q q) {
        this.listener.onCompleted(q.f(), this.encoderFactory.getAudioEncoderName(), this.encoderFactory.getVideoEncoderName());
    }

    private void registerSampleExporterInternal(SampleExporter sampleExporter) {
        this.sampleExporters.add(sampleExporter);
        if (!this.isDrainingExporters) {
            this.internalHandler.sendEmptyMessage(3);
            this.isDrainingExporters = true;
        }
    }

    private void startInternal() {
        for (int i2 = 0; i2 < this.sequenceAssetLoaders.size(); i2++) {
            this.sequenceAssetLoaders.get(i2).start();
        }
    }

    private void updateProgressInternal() {
        if (!this.released) {
            int i2 = 0;
            int i7 = 0;
            for (int i8 = 0; i8 < this.sequenceAssetLoaders.size(); i8++) {
                if (!((EditedMediaItemSequence) this.composition.sequences.get(i8)).isLooping) {
                    this.internalProgressHolder.progress = 0;
                    int progress = this.sequenceAssetLoaders.get(i8).getProgress(this.internalProgressHolder);
                    if (progress != 2) {
                        synchronized (this.progressLock) {
                            this.progressState = progress;
                            this.progressValue = 0;
                        }
                        return;
                    }
                    i2 += this.internalProgressHolder.progress;
                    i7++;
                }
            }
            synchronized (this.progressLock) {
                this.progressState = 2;
                this.progressValue = i2 / i7;
            }
        }
    }

    /* access modifiers changed from: private */
    public void verifyInternalThreadAlive() {
        Assertions.checkState(this.internalHandlerThread.isAlive(), "Internal thread is dead.");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        r6.clock.onThreadBlocked();
        r6.canceledConditionVariable.blockUninterruptible();
        r6.canceledConditionVariable.close();
        r6 = r6.cancelException;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        if (r6 != null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.releaseLock
            monitor-enter(r0)
            boolean r1 = r6.released     // Catch:{ all -> 0x0009 }
            if (r1 == 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            return
        L_0x0009:
            r6 = move-exception
            goto L_0x0031
        L_0x000b:
            r6.verifyInternalThreadAlive()     // Catch:{ all -> 0x0009 }
            androidx.media3.common.util.HandlerWrapper r1 = r6.internalHandler     // Catch:{ all -> 0x0009 }
            r2 = 0
            r3 = 0
            r4 = 4
            r5 = 1
            androidx.media3.common.util.HandlerWrapper$Message r1 = r1.obtainMessage(r4, r5, r2, r3)     // Catch:{ all -> 0x0009 }
            r1.sendToTarget()     // Catch:{ all -> 0x0009 }
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            androidx.media3.common.util.Clock r0 = r6.clock
            r0.onThreadBlocked()
            androidx.media3.common.util.ConditionVariable r0 = r6.canceledConditionVariable
            r0.blockUninterruptible()
            androidx.media3.common.util.ConditionVariable r0 = r6.canceledConditionVariable
            r0.close()
            java.lang.RuntimeException r6 = r6.cancelException
            if (r6 != 0) goto L_0x0030
            return
        L_0x0030:
            throw r6
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.TransformerInternal.cancel():void");
    }

    public void endWithCompletion() {
        verifyInternalThreadAlive();
        this.internalHandler.obtainMessage(4, 0, 0, (Object) null).sendToTarget();
    }

    public void endWithException(ExportException exportException) {
        synchronized (this.releaseLock) {
            try {
                if (this.released) {
                    Log.w("TransformerInternal", "Export error after export ended", exportException);
                    return;
                }
                verifyInternalThreadAlive();
                this.internalHandler.obtainMessage(4, 2, 0, exportException).sendToTarget();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int getProgress(ProgressHolder progressHolder) {
        int i2;
        synchronized (this.progressLock) {
            try {
                i2 = this.progressState;
                if (i2 == 2) {
                    progressHolder.progress = this.progressValue;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2;
    }

    public void start() {
        verifyInternalThreadAlive();
        this.internalHandler.sendEmptyMessage(1);
        synchronized (this.progressLock) {
            try {
                this.progressState = 1;
                this.progressValue = 0;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        DebugTraceUtil.logEvent("TransformerInternal", "Start", -9223372036854775807L, "%s", Util.DEVICE_DEBUG_INFO);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class SequenceAssetLoaderListener implements AssetLoader.Listener {
        private final AudioMixer.Factory audioMixerFactory;
        private final Composition composition;
        private long currentSequenceDurationUs;
        private final DebugViewProvider debugViewProvider;
        private final FallbackListener fallbackListener;
        private final EditedMediaItem firstEditedMediaItem;
        private final LogSessionId logSessionId;
        private final int sequenceIndex;
        private final TransformationRequest transformationRequest;
        private final VideoFrameProcessor.Factory videoFrameProcessorFactory;

        public SequenceAssetLoaderListener(int i2, Composition composition2, TransformationRequest transformationRequest2, AudioMixer.Factory factory, VideoFrameProcessor.Factory factory2, FallbackListener fallbackListener2, DebugViewProvider debugViewProvider2, LogSessionId logSessionId2) {
            this.sequenceIndex = i2;
            this.firstEditedMediaItem = (EditedMediaItem) ((EditedMediaItemSequence) composition2.sequences.get(i2)).editedMediaItems.get(0);
            this.composition = composition2;
            this.transformationRequest = transformationRequest2;
            this.audioMixerFactory = factory;
            this.videoFrameProcessorFactory = factory2;
            this.fallbackListener = fallbackListener2;
            this.debugViewProvider = debugViewProvider2;
            this.logSessionId = logSessionId2;
        }

        private void createDecodedSampleExporter(Format format) {
            boolean z;
            Format build;
            Format format2 = format;
            int processedTrackType = TransformerUtil.getProcessedTrackType(format2.sampleMimeType);
            boolean z3 = false;
            if (TransformerInternal.this.assetLoaderInputTracker.getSampleExporter(processedTrackType) == null) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
            Format assetLoaderInputFormat = TransformerInternal.this.assetLoaderInputTracker.getAssetLoaderInputFormat(this.sequenceIndex, processedTrackType);
            if (MimeTypes.isAudio(format2.sampleMimeType)) {
                TransformerInternal.this.assetLoaderInputTracker.registerSampleExporter(1, new AudioSampleExporter(assetLoaderInputFormat, format2, this.transformationRequest, this.firstEditedMediaItem, this.composition.effects.audioProcessors, this.audioMixerFactory, TransformerInternal.this.encoderFactory, TransformerInternal.this.muxerWrapper, this.fallbackListener, this.logSessionId));
                return;
            }
            if (MimeTypes.isVideo(format2.sampleMimeType)) {
                if (this.transformationRequest.hdrMode == 1) {
                    z3 = true;
                }
                build = assetLoaderInputFormat.buildUpon().setColorInfo(TransformerUtil.getDecoderOutputColor(TransformerUtil.getValidColor(assetLoaderInputFormat.colorInfo), z3)).build();
            } else if (MimeTypes.isImage(format2.sampleMimeType)) {
                build = format2.buildUpon().setColorInfo(TransformerUtil.getValidColor(format2.colorInfo)).build();
            } else {
                throw ExportException.createForUnexpected(new IllegalArgumentException("assetLoaderOutputFormat has to have a audio, video or image mimetype."));
            }
            Format format3 = build;
            AssetLoaderInputTracker access$100 = TransformerInternal.this.assetLoaderInputTracker;
            Context access$700 = TransformerInternal.this.context;
            TransformationRequest transformationRequest2 = this.transformationRequest;
            Composition composition2 = this.composition;
            VideoCompositorSettings videoCompositorSettings = composition2.videoCompositorSettings;
            access$100.registerSampleExporter(2, new VideoSampleExporter(access$700, format3, transformationRequest2, videoCompositorSettings, composition2.effects.videoEffects, this.videoFrameProcessorFactory, TransformerInternal.this.encoderFactory, TransformerInternal.this.muxerWrapper, new i(this), this.fallbackListener, this.debugViewProvider, TransformerInternal.this.videoSampleTimestampOffsetUs, TransformerInternal.this.assetLoaderInputTracker.hasMultipleConcurrentVideoTracks(), TransformerInternal.this.allowedEncodingRotationDegrees, TransformerInternal.this.maxFramesInEncoder, this.logSessionId));
        }

        private void createEncodedSampleExporter(int i2) {
            boolean z;
            if (TransformerInternal.this.assetLoaderInputTracker.getSampleExporter(i2) == null) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
            Assertions.checkArgument(!((EditedMediaItemSequence) this.composition.sequences.get(this.sequenceIndex)).hasGaps(), "Gaps can not be transmuxed.");
            TransformerInternal.this.assetLoaderInputTracker.registerSampleExporter(i2, new EncodedSampleExporter(TransformerInternal.this.assetLoaderInputTracker.getAssetLoaderInputFormat(this.sequenceIndex, i2), this.transformationRequest, TransformerInternal.this.muxerWrapper, this.fallbackListener, TransformerInternal.this.videoSampleTimestampOffsetUs));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onOutputFormat$0(int i2, GraphInput graphInput, EditedMediaItem editedMediaItem, long j2, Format format, boolean z) {
            onMediaItemChanged(i2, j2, z);
            graphInput.onMediaItemChanged(editedMediaItem, j2, format, z);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
            if (((androidx.media3.transformer.EditedMediaItemSequence) r4.composition.sequences.get(r4.sequenceIndex)).isLooping == false) goto L_0x0038;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0038, code lost:
            r0 = 0;
            r1 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
            if (r6 == -9223372036854775807L) goto L_0x0045;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0043, code lost:
            r5 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
            r5 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
            androidx.media3.common.util.Assertions.checkState(r5, "MediaItem duration required for sequence looping could not be extracted.");
            r4.currentSequenceDurationUs += r6;
            r5 = androidx.media3.transformer.TransformerInternal.access$1200(r4.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
            monitor-enter(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0057, code lost:
            if (r8 == false) goto L_0x0061;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            androidx.media3.transformer.TransformerInternal.access$1310(r4.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x005f, code lost:
            r4 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0067, code lost:
            if (androidx.media3.transformer.TransformerInternal.access$1300(r4.this$0) != 0) goto L_0x006a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x006a, code lost:
            r1 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0075, code lost:
            if (r4.currentSequenceDurationUs > androidx.media3.transformer.TransformerInternal.access$1400(r4.this$0)) goto L_0x0079;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0077, code lost:
            if (r1 == false) goto L_0x00ac;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0079, code lost:
            r6 = r4.this$0;
            androidx.media3.transformer.TransformerInternal.access$1402(r6, java.lang.Math.max(r4.currentSequenceDurationUs, androidx.media3.transformer.TransformerInternal.access$1400(r6)));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0092, code lost:
            if (r0 >= androidx.media3.transformer.TransformerInternal.access$300(r4.this$0).size()) goto L_0x00ac;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0094, code lost:
            ((androidx.media3.transformer.SequenceAssetLoader) androidx.media3.transformer.TransformerInternal.access$300(r4.this$0).get(r0)).setMaxSequenceDurationUs(androidx.media3.transformer.TransformerInternal.access$1400(r4.this$0), r1);
            r0 = r0 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ac, code lost:
            monitor-exit(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ad, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00af, code lost:
            throw r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void onMediaItemChanged(int r5, long r6, boolean r8) {
            /*
                r4 = this;
                androidx.media3.transformer.TransformerInternal r0 = androidx.media3.transformer.TransformerInternal.this
                boolean r0 = r0.compositionHasLoopingSequence
                if (r0 != 0) goto L_0x0009
                goto L_0x0037
            L_0x0009:
                androidx.media3.transformer.TransformerInternal r0 = androidx.media3.transformer.TransformerInternal.this
                java.lang.Object r0 = r0.assetLoaderLock
                monitor-enter(r0)
                androidx.media3.transformer.TransformerInternal r1 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0023 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r1 = r1.assetLoaderInputTracker     // Catch:{ all -> 0x0023 }
                int r2 = r4.sequenceIndex     // Catch:{ all -> 0x0023 }
                boolean r1 = r1.sequenceHasMultipleTracks(r2)     // Catch:{ all -> 0x0023 }
                if (r1 == 0) goto L_0x0026
                r1 = 2
                if (r5 != r1) goto L_0x0026
                monitor-exit(r0)     // Catch:{ all -> 0x0023 }
                return
            L_0x0023:
                r4 = move-exception
                goto L_0x00b0
            L_0x0026:
                monitor-exit(r0)     // Catch:{ all -> 0x0023 }
                androidx.media3.transformer.Composition r5 = r4.composition
                F2.U r5 = r5.sequences
                int r0 = r4.sequenceIndex
                java.lang.Object r5 = r5.get(r0)
                androidx.media3.transformer.EditedMediaItemSequence r5 = (androidx.media3.transformer.EditedMediaItemSequence) r5
                boolean r5 = r5.isLooping
                if (r5 == 0) goto L_0x0038
            L_0x0037:
                return
            L_0x0038:
                r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
                int r5 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
                r0 = 0
                r1 = 1
                if (r5 == 0) goto L_0x0045
                r5 = r1
                goto L_0x0046
            L_0x0045:
                r5 = r0
            L_0x0046:
                java.lang.String r2 = "MediaItem duration required for sequence looping could not be extracted."
                androidx.media3.common.util.Assertions.checkState(r5, r2)
                long r2 = r4.currentSequenceDurationUs
                long r2 = r2 + r6
                r4.currentSequenceDurationUs = r2
                androidx.media3.transformer.TransformerInternal r5 = androidx.media3.transformer.TransformerInternal.this
                java.lang.Object r5 = r5.setMaxSequenceDurationUsLock
                monitor-enter(r5)
                if (r8 == 0) goto L_0x0061
                androidx.media3.transformer.TransformerInternal r6 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x005f }
                androidx.media3.transformer.TransformerInternal.access$1310(r6)     // Catch:{ all -> 0x005f }
                goto L_0x0061
            L_0x005f:
                r4 = move-exception
                goto L_0x00ae
            L_0x0061:
                androidx.media3.transformer.TransformerInternal r6 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x005f }
                int r6 = r6.nonLoopingSequencesWithNonFinalDuration     // Catch:{ all -> 0x005f }
                if (r6 != 0) goto L_0x006a
                goto L_0x006b
            L_0x006a:
                r1 = r0
            L_0x006b:
                long r6 = r4.currentSequenceDurationUs     // Catch:{ all -> 0x005f }
                androidx.media3.transformer.TransformerInternal r8 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x005f }
                long r2 = r8.currentMaxSequenceDurationUs     // Catch:{ all -> 0x005f }
                int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
                if (r6 > 0) goto L_0x0079
                if (r1 == 0) goto L_0x00ac
            L_0x0079:
                androidx.media3.transformer.TransformerInternal r6 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x005f }
                long r7 = r4.currentSequenceDurationUs     // Catch:{ all -> 0x005f }
                long r2 = r6.currentMaxSequenceDurationUs     // Catch:{ all -> 0x005f }
                long r7 = java.lang.Math.max(r7, r2)     // Catch:{ all -> 0x005f }
                long unused = r6.currentMaxSequenceDurationUs = r7     // Catch:{ all -> 0x005f }
            L_0x0088:
                androidx.media3.transformer.TransformerInternal r6 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x005f }
                java.util.List r6 = r6.sequenceAssetLoaders     // Catch:{ all -> 0x005f }
                int r6 = r6.size()     // Catch:{ all -> 0x005f }
                if (r0 >= r6) goto L_0x00ac
                androidx.media3.transformer.TransformerInternal r6 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x005f }
                java.util.List r6 = r6.sequenceAssetLoaders     // Catch:{ all -> 0x005f }
                java.lang.Object r6 = r6.get(r0)     // Catch:{ all -> 0x005f }
                androidx.media3.transformer.SequenceAssetLoader r6 = (androidx.media3.transformer.SequenceAssetLoader) r6     // Catch:{ all -> 0x005f }
                androidx.media3.transformer.TransformerInternal r7 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x005f }
                long r7 = r7.currentMaxSequenceDurationUs     // Catch:{ all -> 0x005f }
                r6.setMaxSequenceDurationUs(r7, r1)     // Catch:{ all -> 0x005f }
                int r0 = r0 + 1
                goto L_0x0088
            L_0x00ac:
                monitor-exit(r5)     // Catch:{ all -> 0x005f }
                return
            L_0x00ae:
                monitor-exit(r5)     // Catch:{ all -> 0x005f }
                throw r4
            L_0x00b0:
                monitor-exit(r0)     // Catch:{ all -> 0x0023 }
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.TransformerInternal.SequenceAssetLoaderListener.onMediaItemChanged(int, long, boolean):void");
        }

        private boolean shouldTranscode(Format format, int i2) {
            boolean z;
            boolean z3;
            boolean z7;
            boolean z9;
            boolean z10;
            boolean z11;
            boolean z12 = false;
            if ((i2 & 2) != 0) {
                z = true;
            } else {
                z = false;
            }
            if ((i2 & 1) != 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z || z3) {
                z7 = true;
            } else {
                z7 = false;
            }
            Assertions.checkArgument(z7);
            int processedTrackType = TransformerUtil.getProcessedTrackType(format.sampleMimeType);
            if (!z3) {
                z9 = true;
            } else if (processedTrackType == 1) {
                z9 = TransformerUtil.shouldTranscodeAudio(format, this.composition, this.sequenceIndex, this.transformationRequest, TransformerInternal.this.encoderFactory, TransformerInternal.this.muxerWrapper);
            } else {
                Format format2 = format;
                if (processedTrackType == 2) {
                    Format format3 = format2;
                    if (TransformerUtil.shouldTranscodeVideo(format3, this.composition, this.sequenceIndex, this.transformationRequest, TransformerInternal.this.encoderFactory, TransformerInternal.this.muxerWrapper) || TransformerInternal.this.clippingRequiresTranscode(this.firstEditedMediaItem.mediaItem)) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    if (!TransformerInternal.this.applyMp4EditListTrim || !z10) {
                        z11 = true;
                    } else {
                        z11 = false;
                    }
                    Assertions.checkState(z11, "Transcoding is required for track " + format3 + " but MP4 edit list trimming is enabled. Disable mp4EditListTrimEnabled or ensure this track does not require transcoding.");
                    z9 = z10;
                } else {
                    z9 = false;
                }
            }
            if (!z9 || z) {
                z12 = true;
            }
            Assertions.checkState(z12);
            return z9;
        }

        public void onError(ExportException exportException) {
            TransformerInternal.this.endWithException(exportException);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0095, code lost:
            return r7;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.media3.transformer.SampleConsumer onOutputFormat(androidx.media3.common.Format r7) {
            /*
                r6 = this;
                androidx.media3.transformer.TransformerInternal r0 = androidx.media3.transformer.TransformerInternal.this
                java.lang.Object r0 = r0.assetLoaderLock
                monitor-enter(r0)
                androidx.media3.transformer.TransformerInternal r1 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r1 = r1.assetLoaderInputTracker     // Catch:{ all -> 0x0016 }
                boolean r1 = r1.hasRegisteredAllTracks()     // Catch:{ all -> 0x0016 }
                r2 = 0
                if (r1 != 0) goto L_0x0019
                monitor-exit(r0)     // Catch:{ all -> 0x0016 }
                return r2
            L_0x0016:
                r6 = move-exception
                goto L_0x0096
            L_0x0019:
                java.lang.String r1 = r7.sampleMimeType     // Catch:{ all -> 0x0016 }
                int r1 = androidx.media3.transformer.TransformerUtil.getProcessedTrackType(r1)     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.TransformerInternal r3 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r3 = r3.assetLoaderInputTracker     // Catch:{ all -> 0x0016 }
                boolean r3 = r3.shouldTranscode(r1)     // Catch:{ all -> 0x0016 }
                if (r3 == 0) goto L_0x003d
                androidx.media3.transformer.TransformerInternal r3 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r3 = r3.assetLoaderInputTracker     // Catch:{ all -> 0x0016 }
                int r3 = r3.getIndexForPrimarySequence(r1)     // Catch:{ all -> 0x0016 }
                int r4 = r6.sequenceIndex     // Catch:{ all -> 0x0016 }
                if (r3 != r4) goto L_0x0040
                r6.createDecodedSampleExporter(r7)     // Catch:{ all -> 0x0016 }
                goto L_0x0040
            L_0x003d:
                r6.createEncodedSampleExporter(r1)     // Catch:{ all -> 0x0016 }
            L_0x0040:
                androidx.media3.transformer.TransformerInternal r3 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r3 = r3.assetLoaderInputTracker     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.SampleExporter r3 = r3.getSampleExporter(r1)     // Catch:{ all -> 0x0016 }
                if (r3 != 0) goto L_0x004e
                monitor-exit(r0)     // Catch:{ all -> 0x0016 }
                return r2
            L_0x004e:
                androidx.media3.transformer.EditedMediaItem r2 = r6.firstEditedMediaItem     // Catch:{ all -> 0x0016 }
                int r4 = r6.sequenceIndex     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.GraphInput r7 = r3.getInput(r2, r7, r4)     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.h r2 = new androidx.media3.transformer.h     // Catch:{ all -> 0x0016 }
                r2.<init>(r6, r1, r7)     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.TransformerInternal r4 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0016 }
                java.util.List r4 = r4.sequenceAssetLoaders     // Catch:{ all -> 0x0016 }
                int r5 = r6.sequenceIndex     // Catch:{ all -> 0x0016 }
                java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.SequenceAssetLoader r4 = (androidx.media3.transformer.SequenceAssetLoader) r4     // Catch:{ all -> 0x0016 }
                r4.addOnMediaItemChangedListener(r2, r1)     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.TransformerInternal r2 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r2 = r2.assetLoaderInputTracker     // Catch:{ all -> 0x0016 }
                r2.registerGraphInput(r1)     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.TransformerInternal r2 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.TransformerInternal$AssetLoaderInputTracker r2 = r2.assetLoaderInputTracker     // Catch:{ all -> 0x0016 }
                boolean r1 = r2.hasAssociatedAllTracksWithGraphInput(r1)     // Catch:{ all -> 0x0016 }
                if (r1 == 0) goto L_0x0094
                androidx.media3.transformer.TransformerInternal r1 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0016 }
                r1.verifyInternalThreadAlive()     // Catch:{ all -> 0x0016 }
                androidx.media3.transformer.TransformerInternal r6 = androidx.media3.transformer.TransformerInternal.this     // Catch:{ all -> 0x0016 }
                androidx.media3.common.util.HandlerWrapper r6 = r6.internalHandler     // Catch:{ all -> 0x0016 }
                r1 = 2
                androidx.media3.common.util.HandlerWrapper$Message r6 = r6.obtainMessage(r1, r3)     // Catch:{ all -> 0x0016 }
                r6.sendToTarget()     // Catch:{ all -> 0x0016 }
            L_0x0094:
                monitor-exit(r0)     // Catch:{ all -> 0x0016 }
                return r7
            L_0x0096:
                monitor-exit(r0)     // Catch:{ all -> 0x0016 }
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.TransformerInternal.SequenceAssetLoaderListener.onOutputFormat(androidx.media3.common.Format):androidx.media3.transformer.SampleConsumer");
        }

        public boolean onTrackAdded(Format format, int i2) {
            boolean shouldTranscode;
            int processedTrackType = TransformerUtil.getProcessedTrackType(format.sampleMimeType);
            synchronized (TransformerInternal.this.assetLoaderLock) {
                try {
                    TransformerInternal.this.assetLoaderInputTracker.registerTrack(this.sequenceIndex, format);
                    if (TransformerInternal.this.assetLoaderInputTracker.hasRegisteredAllTracks()) {
                        int outputTrackCount = TransformerInternal.this.assetLoaderInputTracker.getOutputTrackCount();
                        TransformerInternal.this.muxerWrapper.setTrackCount(outputTrackCount);
                        this.fallbackListener.setTrackCount(outputTrackCount);
                    }
                    shouldTranscode = shouldTranscode(format, i2);
                    if (!shouldTranscode && TransformerUtil.getProcessedTrackType(format.sampleMimeType) == 2) {
                        TransformerUtil.maybeSetMuxerWrapperAdditionalRotationDegrees(TransformerInternal.this.muxerWrapper, this.firstEditedMediaItem.effects.videoEffects, format);
                    }
                    TransformerInternal.this.assetLoaderInputTracker.setShouldTranscode(processedTrackType, shouldTranscode);
                } catch (Throwable th) {
                    throw th;
                }
            }
            return shouldTranscode;
        }

        public void onTrackCount(int i2) {
            if (i2 <= 0) {
                onError(ExportException.createForAssetLoader(new IllegalStateException("AssetLoader instances must provide at least 1 track."), 1001));
                return;
            }
            synchronized (TransformerInternal.this.assetLoaderLock) {
                TransformerInternal.this.assetLoaderInputTracker.setTrackCount(this.sequenceIndex, i2);
            }
        }

        public void onDurationUs(long j2) {
        }
    }
}
