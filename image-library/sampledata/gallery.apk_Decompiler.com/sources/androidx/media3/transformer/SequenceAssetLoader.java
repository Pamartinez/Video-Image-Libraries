package androidx.media3.transformer;

import F2.D0;
import F2.N;
import F2.Q;
import F2.U;
import F2.Y;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConstantRateTimestampIterator;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.ExportResult;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SequenceAssetLoader implements AssetLoader, AssetLoader.Listener {
    /* access modifiers changed from: private */
    public static final Format BLANK_IMAGE_BITMAP_FORMAT = new Format.Builder().setWidth(1).setHeight(1).setSampleMimeType("image/raw").setColorInfo(ColorInfo.SRGB_BT709_FULL).build();
    private static final Format FORCE_AUDIO_TRACK_FORMAT = new Format.Builder().setSampleMimeType(Encode.CodecsMime.AUDIO_CODEC_AAC).setSampleRate(44100).setChannelCount(2).build();
    /* access modifiers changed from: private */
    public final AssetLoader.Factory assetLoaderFactory;
    /* access modifiers changed from: private */
    public final AssetLoader.CompositionSettings compositionSettings;
    /* access modifiers changed from: private */
    public volatile long currentAssetDurationAfterEffectsAppliedUs;
    private volatile long currentAssetDurationUs;
    /* access modifiers changed from: private */
    public AssetLoader currentAssetLoader;
    private Format currentAudioInputFormat;
    /* access modifiers changed from: private */
    public int currentMediaItemIndex;
    private Format currentVideoInputFormat;
    /* access modifiers changed from: private */
    public boolean decodeAudio;
    private boolean decodeVideo;
    /* access modifiers changed from: private */
    public final List<EditedMediaItem> editedMediaItems;
    /* access modifiers changed from: private */
    public final boolean forceAudioTrack;
    /* access modifiers changed from: private */
    public final boolean forceVideoTrack;
    /* access modifiers changed from: private */
    public final HandlerWrapper handler;
    /* access modifiers changed from: private */
    public boolean isCurrentAssetFirstAsset = true;
    /* access modifiers changed from: private */
    public final boolean isLooping;
    /* access modifiers changed from: private */
    public volatile boolean isMaxSequenceDurationUsFinal;
    private boolean isTrackCountReported;
    /* access modifiers changed from: private */
    public volatile long maxSequenceDurationUs;
    private final Map<Integer, OnMediaItemChangedListener> mediaItemChangedListenersByTrackType = new HashMap();
    /* access modifiers changed from: private */
    public final AtomicInteger nonEndedTrackCount = new AtomicInteger();
    private final Q processedInputsBuilder = new N(4);
    private int processedInputsSize;
    /* access modifiers changed from: private */
    public volatile boolean released;
    private final AtomicInteger reportedTrackCount = new AtomicInteger();
    private final Map<Integer, SampleConsumerWrapper> sampleConsumersByTrackType = new HashMap();
    private final AssetLoader.Listener sequenceAssetLoaderListener;
    /* access modifiers changed from: private */
    public volatile boolean sequenceHasAudio;
    /* access modifiers changed from: private */
    public volatile boolean sequenceHasVideo;
    private int sequenceLoopCount;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ClippingIterator implements TimestampIterator {
        private final long clippingValue;
        private boolean hasReachedClippingValue;
        private final TimestampIterator iterator;

        public ClippingIterator(TimestampIterator timestampIterator, long j2) {
            this.iterator = timestampIterator;
            this.clippingValue = j2;
        }

        public TimestampIterator copyOf() {
            return new ClippingIterator(this.iterator.copyOf(), this.clippingValue);
        }

        public boolean hasNext() {
            if (this.hasReachedClippingValue || !this.iterator.hasNext()) {
                return false;
            }
            return true;
        }

        public long next() {
            Assertions.checkState(hasNext());
            long next = this.iterator.next();
            if (this.clippingValue <= next) {
                this.hasReachedClippingValue = true;
            }
            return next;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class GapInterceptingAssetLoaderFactory implements AssetLoader.Factory {
        private final AssetLoader.Factory factory;

        public GapInterceptingAssetLoaderFactory(AssetLoader.Factory factory2) {
            this.factory = factory2;
        }

        public AssetLoader createAssetLoader(EditedMediaItem editedMediaItem, Looper looper, AssetLoader.Listener listener, AssetLoader.CompositionSettings compositionSettings) {
            if (editedMediaItem.isGap()) {
                return new GapSignalingAssetLoader(editedMediaItem.durationUs);
            }
            return this.factory.createAssetLoader(editedMediaItem, looper, listener, compositionSettings);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class GapSignalingAssetLoader implements AssetLoader {
        private final Format audioTrackDecodedFormat;
        private final Format audioTrackFormat;
        private final long durationUs;
        private boolean producedAudio;
        private boolean producedVideo;
        private final boolean shouldProduceAudio;
        private final boolean shouldProduceVideo;

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x0058 A[Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }] */
        /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void outputFormatToSequenceAssetLoader() {
            /*
                r5 = this;
                boolean r0 = r5.shouldProduceAudio
                r1 = 0
                r2 = 1
                if (r0 == 0) goto L_0x000c
                boolean r0 = r5.producedAudio
                if (r0 != 0) goto L_0x000c
                r0 = r2
                goto L_0x000d
            L_0x000c:
                r0 = r1
            L_0x000d:
                boolean r3 = r5.shouldProduceVideo
                if (r3 == 0) goto L_0x0017
                boolean r3 = r5.producedVideo
                if (r3 != 0) goto L_0x0017
                r3 = r2
                goto L_0x0018
            L_0x0017:
                r3 = r1
            L_0x0018:
                if (r0 != 0) goto L_0x001f
                if (r3 == 0) goto L_0x001d
                goto L_0x001f
            L_0x001d:
                r4 = r1
                goto L_0x0020
            L_0x001f:
                r4 = r2
            L_0x0020:
                androidx.media3.common.util.Assertions.checkState(r4)
                if (r0 == 0) goto L_0x003b
                androidx.media3.transformer.SequenceAssetLoader r0 = androidx.media3.transformer.SequenceAssetLoader.this     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                androidx.media3.common.Format r4 = r5.audioTrackDecodedFormat     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper r0 = r0.onOutputFormat((androidx.media3.common.Format) r4)     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                if (r0 != 0) goto L_0x0031
                r1 = r2
                goto L_0x003b
            L_0x0031:
                r0.onAudioGapSignalled()     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                r5.producedAudio = r2     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                goto L_0x003b
            L_0x0037:
                r0 = move-exception
                goto L_0x006a
            L_0x0039:
                r0 = move-exception
                goto L_0x0076
            L_0x003b:
                if (r3 == 0) goto L_0x0055
                androidx.media3.transformer.SequenceAssetLoader r0 = androidx.media3.transformer.SequenceAssetLoader.this     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                androidx.media3.common.Format r3 = androidx.media3.transformer.SequenceAssetLoader.BLANK_IMAGE_BITMAP_FORMAT     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper r0 = r0.onOutputFormat((androidx.media3.common.Format) r3)     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                if (r0 != 0) goto L_0x004a
                goto L_0x0056
            L_0x004a:
                androidx.media3.transformer.SequenceAssetLoader r0 = androidx.media3.transformer.SequenceAssetLoader.this     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                android.graphics.Bitmap r3 = androidx.media3.transformer.SequenceAssetLoader.getBlankImageBitmap()     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                r0.lambda$insertBlankFrames$1(r3)     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                r5.producedVideo = r2     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
            L_0x0055:
                r2 = r1
            L_0x0056:
                if (r2 == 0) goto L_0x007b
                androidx.media3.transformer.SequenceAssetLoader r0 = androidx.media3.transformer.SequenceAssetLoader.this     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                androidx.media3.common.util.HandlerWrapper r0 = r0.handler     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                androidx.media3.transformer.f r1 = new androidx.media3.transformer.f     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                r2 = 1
                r1.<init>(r2, r5)     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                r2 = 10
                r0.postDelayed(r1, r2)     // Catch:{ ExportException -> 0x0039, RuntimeException -> 0x0037 }
                return
            L_0x006a:
                androidx.media3.transformer.SequenceAssetLoader r5 = androidx.media3.transformer.SequenceAssetLoader.this
                r1 = 1000(0x3e8, float:1.401E-42)
                androidx.media3.transformer.ExportException r0 = androidx.media3.transformer.ExportException.createForAssetLoader(r0, r1)
                r5.onError(r0)
                goto L_0x007b
            L_0x0076:
                androidx.media3.transformer.SequenceAssetLoader r5 = androidx.media3.transformer.SequenceAssetLoader.this
                r5.onError(r0)
            L_0x007b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.SequenceAssetLoader.GapSignalingAssetLoader.outputFormatToSequenceAssetLoader():void");
        }

        public Y getDecoderNames() {
            return D0.k;
        }

        public int getProgress(ProgressHolder progressHolder) {
            boolean z;
            boolean z3 = true;
            if (!this.shouldProduceAudio || this.producedAudio) {
                z = false;
            } else {
                z = true;
            }
            if (!this.shouldProduceVideo || this.producedVideo) {
                z3 = false;
            }
            if (z && z3) {
                progressHolder.progress = 0;
                return 2;
            } else if (z || z3) {
                progressHolder.progress = 50;
                return 2;
            } else {
                progressHolder.progress = 99;
                return 2;
            }
        }

        public void start() {
            int i2;
            SequenceAssetLoader.this.onDurationUs(this.durationUs);
            if (!this.shouldProduceAudio || !this.shouldProduceVideo) {
                i2 = 1;
            } else {
                i2 = 2;
            }
            SequenceAssetLoader.this.onTrackCount(i2);
            if (this.shouldProduceAudio) {
                SequenceAssetLoader.this.onTrackAdded(this.audioTrackFormat, 2);
            }
            if (this.shouldProduceVideo) {
                SequenceAssetLoader.this.onTrackAdded(SequenceAssetLoader.BLANK_IMAGE_BITMAP_FORMAT, 2);
            }
            outputFormatToSequenceAssetLoader();
        }

        private GapSignalingAssetLoader(long j2) {
            this.durationUs = j2;
            boolean z = true;
            boolean z3 = SequenceAssetLoader.this.sequenceHasAudio || SequenceAssetLoader.this.forceAudioTrack;
            this.shouldProduceAudio = z3;
            boolean z7 = SequenceAssetLoader.this.sequenceHasVideo || SequenceAssetLoader.this.forceVideoTrack;
            this.shouldProduceVideo = z7;
            if (!z3 && !z7) {
                z = false;
            }
            Assertions.checkState(z);
            this.audioTrackFormat = new Format.Builder().setSampleMimeType("audio/raw").build();
            this.audioTrackDecodedFormat = new Format.Builder().setSampleMimeType("audio/raw").setSampleRate(44100).setChannelCount(2).setPcmEncoding(2).build();
        }

        public void release() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class SampleConsumerWrapper implements SampleConsumer {
        private boolean audioLoopingEnded;
        private final SampleConsumer sampleConsumer;
        private long totalDurationUs;
        private final int trackType;
        private boolean videoLoopingEnded;

        public SampleConsumerWrapper(SampleConsumer sampleConsumer2, int i2) {
            this.sampleConsumer = sampleConsumer2;
            this.trackType = i2;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$switchAssetLoader$0() {
            try {
                if (!SequenceAssetLoader.this.released) {
                    SequenceAssetLoader.this.addCurrentProcessedInput();
                    this.totalDurationUs += SequenceAssetLoader.this.currentAssetDurationAfterEffectsAppliedUs;
                    SequenceAssetLoader.this.currentAssetLoader.release();
                    boolean unused = SequenceAssetLoader.this.isCurrentAssetFirstAsset = false;
                    SequenceAssetLoader.access$1208(SequenceAssetLoader.this);
                    if (SequenceAssetLoader.this.currentMediaItemIndex == SequenceAssetLoader.this.editedMediaItems.size()) {
                        int unused2 = SequenceAssetLoader.this.currentMediaItemIndex = 0;
                        SequenceAssetLoader.access$1408(SequenceAssetLoader.this);
                    }
                    SequenceAssetLoader sequenceAssetLoader = SequenceAssetLoader.this;
                    AssetLoader.Factory access$1600 = sequenceAssetLoader.assetLoaderFactory;
                    SequenceAssetLoader sequenceAssetLoader2 = SequenceAssetLoader.this;
                    AssetLoader unused3 = sequenceAssetLoader.currentAssetLoader = access$1600.createAssetLoader((EditedMediaItem) SequenceAssetLoader.this.editedMediaItems.get(SequenceAssetLoader.this.currentMediaItemIndex), (Looper) Assertions.checkNotNull(Looper.myLooper()), sequenceAssetLoader2, sequenceAssetLoader2.compositionSettings);
                    SequenceAssetLoader.this.currentAssetLoader.start();
                }
            } catch (RuntimeException e) {
                SequenceAssetLoader.this.onError(ExportException.createForAssetLoader(e, 1000));
            }
        }

        /* access modifiers changed from: private */
        public void onAudioGapSignalled() {
            if (SequenceAssetLoader.this.nonEndedTrackCount.decrementAndGet() == 0 && !SequenceAssetLoader.this.isLastMediaItemInSequence()) {
                switchAssetLoader();
            }
        }

        private void switchAssetLoader() {
            SequenceAssetLoader.this.handler.post(new f(2, this));
        }

        public DecoderInputBuffer getInputBuffer() {
            return this.sampleConsumer.getInputBuffer();
        }

        public Surface getInputSurface() {
            return this.sampleConsumer.getInputSurface();
        }

        public int getPendingVideoFrameCount() {
            return this.sampleConsumer.getPendingVideoFrameCount();
        }

        public int queueInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator) {
            if (SequenceAssetLoader.this.isLooping) {
                long j2 = -9223372036854775807L;
                while (true) {
                    if (!timestampIterator.hasNext()) {
                        break;
                    }
                    long next = timestampIterator.next();
                    if (this.totalDurationUs + next <= SequenceAssetLoader.this.maxSequenceDurationUs) {
                        j2 = next;
                    } else if (!SequenceAssetLoader.this.isMaxSequenceDurationUsFinal) {
                        return 2;
                    } else {
                        if (j2 != -9223372036854775807L) {
                            ClippingIterator clippingIterator = new ClippingIterator(timestampIterator.copyOf(), j2);
                            this.videoLoopingEnded = true;
                            timestampIterator = clippingIterator;
                        } else if (this.videoLoopingEnded) {
                            return 2;
                        } else {
                            this.videoLoopingEnded = true;
                            signalEndOfVideoInput();
                            return 3;
                        }
                    }
                }
            }
            return this.sampleConsumer.queueInputBitmap(bitmap, timestampIterator.copyOf());
        }

        public boolean queueInputBuffer() {
            DecoderInputBuffer decoderInputBuffer = (DecoderInputBuffer) Assertions.checkStateNotNull(this.sampleConsumer.getInputBuffer());
            long j2 = this.totalDurationUs + decoderInputBuffer.timeUs;
            if (!SequenceAssetLoader.this.isLooping || (j2 < SequenceAssetLoader.this.maxSequenceDurationUs && !this.audioLoopingEnded)) {
                if (decoderInputBuffer.isEndOfStream()) {
                    SequenceAssetLoader.this.nonEndedTrackCount.decrementAndGet();
                    if (!SequenceAssetLoader.this.isLastMediaItemInSequence() || SequenceAssetLoader.this.isLooping) {
                        if (this.trackType != 1 || SequenceAssetLoader.this.isLooping || !SequenceAssetLoader.this.decodeAudio) {
                            decoderInputBuffer.clear();
                            decoderInputBuffer.timeUs = 0;
                        } else {
                            Assertions.checkState(this.sampleConsumer.queueInputBuffer());
                        }
                        if (SequenceAssetLoader.this.nonEndedTrackCount.get() == 0) {
                            switchAssetLoader();
                        }
                        return true;
                    }
                }
                Assertions.checkState(this.sampleConsumer.queueInputBuffer());
                return true;
            }
            if (SequenceAssetLoader.this.isMaxSequenceDurationUsFinal && !this.audioLoopingEnded) {
                ((ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.data)).limit(0);
                decoderInputBuffer.setFlags(4);
                Assertions.checkState(this.sampleConsumer.queueInputBuffer());
                this.audioLoopingEnded = true;
                SequenceAssetLoader.this.nonEndedTrackCount.decrementAndGet();
            }
            return false;
        }

        public boolean registerVideoFrame(long j2) {
            long j3 = this.totalDurationUs + j2;
            if (!SequenceAssetLoader.this.isLooping || j3 < SequenceAssetLoader.this.maxSequenceDurationUs) {
                return this.sampleConsumer.registerVideoFrame(j2);
            }
            if (!SequenceAssetLoader.this.isMaxSequenceDurationUsFinal || this.videoLoopingEnded) {
                return false;
            }
            this.videoLoopingEnded = true;
            signalEndOfVideoInput();
            return false;
        }

        public void signalEndOfVideoInput() {
            boolean z;
            SequenceAssetLoader.this.nonEndedTrackCount.decrementAndGet();
            if (SequenceAssetLoader.this.isLooping) {
                z = this.videoLoopingEnded;
            } else {
                z = SequenceAssetLoader.this.isLastMediaItemInSequence();
            }
            if (z) {
                this.sampleConsumer.signalEndOfVideoInput();
            } else if (SequenceAssetLoader.this.nonEndedTrackCount.get() == 0) {
                switchAssetLoader();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v5, types: [F2.N, F2.Q] */
    public SequenceAssetLoader(EditedMediaItemSequence editedMediaItemSequence, AssetLoader.Factory factory, AssetLoader.CompositionSettings compositionSettings2, AssetLoader.Listener listener, Clock clock, Looper looper) {
        U u = editedMediaItemSequence.editedMediaItems;
        this.editedMediaItems = u;
        this.isLooping = editedMediaItemSequence.isLooping;
        this.forceAudioTrack = editedMediaItemSequence.forceAudioTrack;
        this.forceVideoTrack = editedMediaItemSequence.forceVideoTrack;
        GapInterceptingAssetLoaderFactory gapInterceptingAssetLoaderFactory = new GapInterceptingAssetLoaderFactory(factory);
        this.assetLoaderFactory = gapInterceptingAssetLoaderFactory;
        this.compositionSettings = compositionSettings2;
        this.sequenceAssetLoaderListener = listener;
        this.handler = clock.createHandler(looper, (Handler.Callback) null);
        this.currentAssetLoader = gapInterceptingAssetLoaderFactory.createAssetLoader((EditedMediaItem) u.get(0), looper, this, compositionSettings2);
    }

    public static /* synthetic */ int access$1208(SequenceAssetLoader sequenceAssetLoader) {
        int i2 = sequenceAssetLoader.currentMediaItemIndex;
        sequenceAssetLoader.currentMediaItemIndex = i2 + 1;
        return i2;
    }

    public static /* synthetic */ int access$1408(SequenceAssetLoader sequenceAssetLoader) {
        int i2 = sequenceAssetLoader.sequenceLoopCount;
        sequenceAssetLoader.sequenceLoopCount = i2 + 1;
        return i2;
    }

    /* access modifiers changed from: private */
    public void addCurrentProcessedInput() {
        int size = this.editedMediaItems.size() * this.sequenceLoopCount;
        int i2 = this.currentMediaItemIndex;
        if (size + i2 >= this.processedInputsSize) {
            MediaItem mediaItem = this.editedMediaItems.get(i2).mediaItem;
            Y decoderNames = getDecoderNames();
            this.processedInputsBuilder.a(new ExportResult.ProcessedInput(mediaItem, this.currentAssetDurationUs, this.currentAudioInputFormat, this.currentVideoInputFormat, (String) decoderNames.get(1), (String) decoderNames.get(2)));
            this.processedInputsSize++;
        }
    }

    /* access modifiers changed from: private */
    public static Bitmap getBlankImageBitmap() {
        return Bitmap.createBitmap(new int[]{-16777216}, 1, 1, Bitmap.Config.ARGB_8888);
    }

    /* access modifiers changed from: private */
    /* renamed from: insertBlankFrames */
    public void lambda$insertBlankFrames$1(Bitmap bitmap) {
        SampleConsumerWrapper sampleConsumerWrapper = (SampleConsumerWrapper) Assertions.checkNotNull(this.sampleConsumersByTrackType.get(2));
        if (sampleConsumerWrapper.queueInputBitmap(bitmap, new ConstantRateTimestampIterator(this.currentAssetDurationUs, 30.0f)) != 1) {
            this.handler.postDelayed(new d(1, this, bitmap), 10);
        } else {
            sampleConsumerWrapper.signalEndOfVideoInput();
        }
    }

    /* access modifiers changed from: private */
    public boolean isLastMediaItemInSequence() {
        if (this.currentMediaItemIndex == this.editedMediaItems.size() - 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onOutputFormat$0() {
        lambda$insertBlankFrames$1(getBlankImageBitmap());
    }

    private void onMediaItemChanged(int i2, Format format) {
        long j2;
        OnMediaItemChangedListener onMediaItemChangedListener = this.mediaItemChangedListenersByTrackType.get(Integer.valueOf(i2));
        if (onMediaItemChangedListener != null) {
            EditedMediaItem editedMediaItem = this.editedMediaItems.get(this.currentMediaItemIndex);
            if (i2 != 1 || !this.isLooping || !this.decodeAudio) {
                j2 = this.currentAssetDurationUs;
            } else {
                j2 = -9223372036854775807L;
            }
            if (editedMediaItem.isGap() && i2 == 1) {
                format = null;
            }
            onMediaItemChangedListener.onMediaItemChanged(editedMediaItem, j2, format, isLastMediaItemInSequence());
        }
    }

    public void addOnMediaItemChangedListener(OnMediaItemChangedListener onMediaItemChangedListener, int i2) {
        boolean z;
        boolean z3 = false;
        if (i2 == 1 || i2 == 2) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        if (this.mediaItemChangedListenersByTrackType.get(Integer.valueOf(i2)) == null) {
            z3 = true;
        }
        Assertions.checkArgument(z3);
        this.mediaItemChangedListenersByTrackType.put(Integer.valueOf(i2), onMediaItemChangedListener);
    }

    public Y getDecoderNames() {
        return this.currentAssetLoader.getDecoderNames();
    }

    public U getProcessedInputs() {
        addCurrentProcessedInput();
        return this.processedInputsBuilder.f();
    }

    public int getProgress(ProgressHolder progressHolder) {
        if (this.isLooping) {
            return 3;
        }
        int progress = this.currentAssetLoader.getProgress(progressHolder);
        int size = this.editedMediaItems.size();
        if (size == 1 || progress == 0) {
            return progress;
        }
        int percentInt = Util.percentInt((long) this.currentMediaItemIndex, (long) size);
        if (progress == 2) {
            percentInt += progressHolder.progress / size;
        }
        progressHolder.progress = percentInt;
        return 2;
    }

    public void onDurationUs(long j2) {
        boolean z;
        if (j2 != -9223372036854775807L || isLastMediaItemInSequence()) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "Could not retrieve required duration for EditedMediaItem " + this.currentMediaItemIndex);
        this.currentAssetDurationAfterEffectsAppliedUs = this.editedMediaItems.get(this.currentMediaItemIndex).getDurationAfterEffectsApplied(j2);
        this.currentAssetDurationUs = j2;
        if (this.editedMediaItems.size() == 1 && !this.isLooping) {
            this.sequenceAssetLoaderListener.onDurationUs(this.currentAssetDurationAfterEffectsAppliedUs);
        }
    }

    public void onError(ExportException exportException) {
        this.sequenceAssetLoaderListener.onError(exportException);
    }

    public boolean onTrackAdded(Format format, int i2) {
        boolean z;
        String str;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10 = false;
        if (TransformerUtil.getProcessedTrackType(format.sampleMimeType) == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            str = "audio";
        } else {
            str = "video";
        }
        DebugTraceUtil.logEvent("AssetLoader", "InputFormat", -9223372036854775807L, "%s:%s", str, format);
        if (z) {
            this.currentAudioInputFormat = format;
        } else {
            this.currentVideoInputFormat = format;
        }
        if (!this.isCurrentAssetFirstAsset) {
            if (z) {
                z9 = this.decodeAudio;
            } else {
                z9 = this.decodeVideo;
            }
            if (z9) {
                if ((i2 & 2) != 0) {
                    z10 = true;
                }
                Assertions.checkArgument(z10);
                return z9;
            }
            if ((i2 & 1) != 0) {
                z10 = true;
            }
            Assertions.checkArgument(z10);
            return z9;
        }
        if (this.reportedTrackCount.get() == 1) {
            if (!this.forceAudioTrack || z) {
                z7 = false;
            } else {
                z7 = true;
            }
            if (!this.forceVideoTrack || !z) {
                z3 = false;
            } else {
                z3 = true;
            }
        } else {
            z7 = false;
            z3 = false;
        }
        if (!this.isTrackCountReported) {
            int i7 = this.reportedTrackCount.get();
            if (z7 || z3) {
                z10 = true;
            }
            this.sequenceAssetLoaderListener.onTrackCount(i7 + (z10 ? 1 : 0));
            this.isTrackCountReported = true;
        }
        boolean onTrackAdded = this.sequenceAssetLoaderListener.onTrackAdded(format, i2);
        if (z) {
            this.decodeAudio = onTrackAdded;
        } else {
            this.decodeVideo = onTrackAdded;
        }
        if (z7) {
            this.sequenceAssetLoaderListener.onTrackAdded(FORCE_AUDIO_TRACK_FORMAT, 2);
            this.decodeAudio = true;
        }
        if (z3) {
            this.sequenceAssetLoaderListener.onTrackAdded(BLANK_IMAGE_BITMAP_FORMAT, 2);
            this.decodeVideo = true;
        }
        return onTrackAdded;
    }

    public void onTrackCount(int i2) {
        this.reportedTrackCount.set(i2);
        this.nonEndedTrackCount.set(i2);
    }

    public void release() {
        this.currentAssetLoader.release();
        this.released = true;
    }

    public void setMaxSequenceDurationUs(long j2, boolean z) {
        this.maxSequenceDurationUs = j2;
        this.isMaxSequenceDurationUsFinal = z;
    }

    public void start() {
        this.currentAssetLoader.start();
        if (this.editedMediaItems.size() > 1 || this.isLooping) {
            this.sequenceAssetLoaderListener.onDurationUs(-9223372036854775807L);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media3.transformer.SequenceAssetLoader.SampleConsumerWrapper onOutputFormat(androidx.media3.common.Format r10) {
        /*
            r9 = this;
            java.lang.String r0 = r10.sampleMimeType
            int r0 = androidx.media3.transformer.TransformerUtil.getProcessedTrackType(r0)
            java.lang.String r1 = androidx.media3.common.util.Util.getTrackTypeString(r0)
            java.lang.Object[] r7 = new java.lang.Object[]{r1, r10}
            java.lang.String r2 = "AssetLoader"
            java.lang.String r3 = "OutputFormat"
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            java.lang.String r6 = "%s:%s"
            androidx.media3.effect.DebugTraceUtil.logEvent(r2, r3, r4, r6, r7)
            boolean r1 = r9.isCurrentAssetFirstAsset
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x00a1
            if (r0 != r3) goto L_0x0028
            r9.sequenceHasVideo = r4
            goto L_0x002a
        L_0x0028:
            r9.sequenceHasAudio = r4
        L_0x002a:
            androidx.media3.transformer.AssetLoader$Listener r1 = r9.sequenceAssetLoaderListener
            androidx.media3.transformer.SampleConsumer r1 = r1.onOutputFormat(r10)
            if (r1 != 0) goto L_0x0033
            return r2
        L_0x0033:
            androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper r5 = new androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper
            r5.<init>(r1, r0)
            java.util.Map<java.lang.Integer, androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper> r1 = r9.sampleConsumersByTrackType
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            r1.put(r6, r5)
            java.util.concurrent.atomic.AtomicInteger r1 = r9.reportedTrackCount
            int r1 = r1.get()
            if (r1 != r4) goto L_0x00bb
            boolean r1 = r9.forceAudioTrack
            if (r1 == 0) goto L_0x007e
            if (r0 != r3) goto L_0x007e
            androidx.media3.transformer.AssetLoader$Listener r1 = r9.sequenceAssetLoaderListener
            androidx.media3.common.Format r6 = FORCE_AUDIO_TRACK_FORMAT
            androidx.media3.common.Format$Builder r6 = r6.buildUpon()
            java.lang.String r7 = "audio/raw"
            androidx.media3.common.Format$Builder r6 = r6.setSampleMimeType(r7)
            androidx.media3.common.Format$Builder r6 = r6.setPcmEncoding(r3)
            androidx.media3.common.Format r6 = r6.build()
            androidx.media3.transformer.SampleConsumer r1 = r1.onOutputFormat(r6)
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkStateNotNull(r1)
            androidx.media3.transformer.SampleConsumer r1 = (androidx.media3.transformer.SampleConsumer) r1
            java.util.Map<java.lang.Integer, androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper> r6 = r9.sampleConsumersByTrackType
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper r8 = new androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper
            r8.<init>(r1, r4)
            r6.put(r7, r8)
            goto L_0x00bb
        L_0x007e:
            boolean r1 = r9.forceVideoTrack
            if (r1 == 0) goto L_0x00bb
            if (r0 != r4) goto L_0x00bb
            androidx.media3.transformer.AssetLoader$Listener r1 = r9.sequenceAssetLoaderListener
            androidx.media3.common.Format r6 = BLANK_IMAGE_BITMAP_FORMAT
            androidx.media3.transformer.SampleConsumer r1 = r1.onOutputFormat(r6)
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkStateNotNull(r1)
            androidx.media3.transformer.SampleConsumer r1 = (androidx.media3.transformer.SampleConsumer) r1
            java.util.Map<java.lang.Integer, androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper> r6 = r9.sampleConsumersByTrackType
            java.lang.Integer r7 = java.lang.Integer.valueOf(r3)
            androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper r8 = new androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper
            r8.<init>(r1, r3)
            r6.put(r7, r8)
            goto L_0x00bb
        L_0x00a1:
            if (r0 != r4) goto L_0x00a6
            java.lang.String r1 = "The preceding MediaItem does not contain any audio track. If the sequence starts with an item without audio track (like images), followed by items with audio tracks, then EditedMediaItemSequence.Builder.experimentalSetForceAudioTrack() needs to be set to true."
            goto L_0x00a8
        L_0x00a6:
            java.lang.String r1 = "The preceding MediaItem does not contain any video track. If the sequence starts with an item without video track (audio only), followed by items with video tracks, then EditedMediaItemSequence.Builder.experimentalSetForceVideoTrack() needs to be set to true."
        L_0x00a8:
            java.util.Map<java.lang.Integer, androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper> r5 = r9.sampleConsumersByTrackType
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            java.lang.Object r5 = r5.get(r6)
            androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper r5 = (androidx.media3.transformer.SequenceAssetLoader.SampleConsumerWrapper) r5
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkStateNotNull(r5, r1)
            r5 = r1
            androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper r5 = (androidx.media3.transformer.SequenceAssetLoader.SampleConsumerWrapper) r5
        L_0x00bb:
            r9.onMediaItemChanged(r0, r10)
            java.util.concurrent.atomic.AtomicInteger r10 = r9.reportedTrackCount
            int r10 = r10.get()
            if (r10 != r4) goto L_0x00e9
            java.util.Map<java.lang.Integer, androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper> r10 = r9.sampleConsumersByTrackType
            int r10 = r10.size()
            if (r10 != r3) goto L_0x00e9
            if (r0 != r4) goto L_0x00e6
            androidx.media3.common.Format r10 = BLANK_IMAGE_BITMAP_FORMAT
            r9.onMediaItemChanged(r3, r10)
            java.util.concurrent.atomic.AtomicInteger r10 = r9.nonEndedTrackCount
            r10.incrementAndGet()
            androidx.media3.common.util.HandlerWrapper r10 = r9.handler
            androidx.media3.transformer.f r0 = new androidx.media3.transformer.f
            r1 = 0
            r0.<init>(r1, r9)
            r10.post(r0)
            return r5
        L_0x00e6:
            r9.onMediaItemChanged(r4, r2)
        L_0x00e9:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.SequenceAssetLoader.onOutputFormat(androidx.media3.common.Format):androidx.media3.transformer.SequenceAssetLoader$SampleConsumerWrapper");
    }
}
