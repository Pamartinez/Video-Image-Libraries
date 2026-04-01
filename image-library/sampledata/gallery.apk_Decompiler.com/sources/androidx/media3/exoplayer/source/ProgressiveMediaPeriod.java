package androidx.media3.exoplayer.source;

import android.net.Uri;
import android.os.Handler;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.source.IcyDataSource;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ForwardingSeekMap;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ProgressiveMediaPeriod implements MediaPeriod, ExtractorOutput, Loader.Callback<ExtractingLoadable>, Loader.ReleaseCallback, SampleQueue.UpstreamFormatChangedListener {
    /* access modifiers changed from: private */
    public static final Format ICY_FORMAT = new Format.Builder().setId("icy").setSampleMimeType("application/x-icy").build();
    /* access modifiers changed from: private */
    public static final Map<String, String> ICY_METADATA_HEADERS = createIcyMetadataHeaders();
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    /* access modifiers changed from: private */
    public final long continueLoadingCheckIntervalBytes;
    /* access modifiers changed from: private */
    public final String customCacheKey;
    private final DataSource dataSource;
    private int dataType;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    /* access modifiers changed from: private */
    public long durationUs;
    private int enabledTrackCount;
    private int extractedSamplesCountAtStartOfLoad;
    /* access modifiers changed from: private */
    public final Handler handler;
    private boolean haveAudioVideoTracks;
    /* access modifiers changed from: private */
    public IcyHeaders icyHeaders;
    private boolean isLengthKnown;
    private boolean isLive;
    private boolean isSingleSample;
    private long lastSeekPositionUs;
    private final Listener listener;
    private final ConditionVariable loadCondition;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Loader loader;
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private boolean notifyDiscontinuity;
    /* access modifiers changed from: private */
    public final Runnable onContinueLoadingRequestedRunnable;
    private boolean pendingDeferredRetry;
    private boolean pendingInitialDiscontinuity;
    private long pendingResetPositionUs;
    private boolean prepared;
    private final ProgressiveMediaExtractor progressiveMediaExtractor;
    private boolean released;
    private TrackId[] sampleQueueTrackIds;
    private SampleQueue[] sampleQueues;
    private boolean sampleQueuesBuilt;
    private SeekMap seekMap;
    private boolean seenFirstTrackSelection;
    private final long singleSampleDurationUs;
    private final Format singleTrackFormat;
    private final int singleTrackId;
    private TrackState trackState;
    private final Uri uri;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ExtractingLoadable implements Loader.Loadable, IcyDataSource.Listener {
        /* access modifiers changed from: private */
        public final StatsDataSource dataSource;
        /* access modifiers changed from: private */
        public DataSpec dataSpec = buildDataSpec(0);
        private final ExtractorOutput extractorOutput;
        private TrackOutput icyTrackOutput;
        private volatile boolean loadCanceled;
        private final ConditionVariable loadCondition;
        /* access modifiers changed from: private */
        public final long loadTaskId = LoadEventInfo.getNewId();
        private boolean pendingExtractorSeek = true;
        private final PositionHolder positionHolder = new PositionHolder();
        private final ProgressiveMediaExtractor progressiveMediaExtractor;
        /* access modifiers changed from: private */
        public long seekTimeUs;
        private boolean seenIcyMetadata;
        private final Uri uri;

        public ExtractingLoadable(Uri uri2, DataSource dataSource2, ProgressiveMediaExtractor progressiveMediaExtractor2, ExtractorOutput extractorOutput2, ConditionVariable conditionVariable) {
            this.uri = uri2;
            this.dataSource = new StatsDataSource(dataSource2);
            this.progressiveMediaExtractor = progressiveMediaExtractor2;
            this.extractorOutput = extractorOutput2;
            this.loadCondition = conditionVariable;
        }

        private DataSpec buildDataSpec(long j2) {
            return new DataSpec.Builder().setUri(this.uri).setPosition(j2).setKey(ProgressiveMediaPeriod.this.customCacheKey).setFlags(6).setHttpRequestHeaders(ProgressiveMediaPeriod.ICY_METADATA_HEADERS).build();
        }

        /* access modifiers changed from: private */
        public void setLoadPosition(long j2, long j3) {
            this.positionHolder.position = j2;
            this.seekTimeUs = j3;
            this.pendingExtractorSeek = true;
            this.seenIcyMetadata = false;
        }

        public void cancelLoad() {
            this.loadCanceled = true;
        }

        public void load() {
            int i2 = 0;
            while (i2 == 0 && !this.loadCanceled) {
                try {
                    long j2 = this.positionHolder.position;
                    DataSpec buildDataSpec = buildDataSpec(j2);
                    this.dataSpec = buildDataSpec;
                    long open = this.dataSource.open(buildDataSpec);
                    if (this.loadCanceled) {
                        if (!(i2 == 1 || this.progressiveMediaExtractor.getCurrentInputPosition() == -1)) {
                            this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
                        }
                        DataSourceUtil.closeQuietly(this.dataSource);
                        return;
                    }
                    if (open != -1) {
                        open += j2;
                        ProgressiveMediaPeriod.this.onLengthKnown();
                    }
                    long j3 = open;
                    IcyHeaders unused = ProgressiveMediaPeriod.this.icyHeaders = IcyHeaders.parse(this.dataSource.getResponseHeaders());
                    DataReader dataReader = this.dataSource;
                    if (!(ProgressiveMediaPeriod.this.icyHeaders == null || ProgressiveMediaPeriod.this.icyHeaders.metadataInterval == -1)) {
                        dataReader = new IcyDataSource(this.dataSource, ProgressiveMediaPeriod.this.icyHeaders.metadataInterval, this);
                        TrackOutput icyTrack = ProgressiveMediaPeriod.this.icyTrack();
                        this.icyTrackOutput = icyTrack;
                        icyTrack.format(ProgressiveMediaPeriod.ICY_FORMAT);
                    }
                    this.progressiveMediaExtractor.init(dataReader, this.uri, this.dataSource.getResponseHeaders(), j2, j3, this.extractorOutput);
                    if (ProgressiveMediaPeriod.this.icyHeaders != null) {
                        this.progressiveMediaExtractor.disableSeekingOnMp3Streams();
                    }
                    if (this.pendingExtractorSeek) {
                        this.progressiveMediaExtractor.seek(j2, this.seekTimeUs);
                        this.pendingExtractorSeek = false;
                    }
                    while (i2 == 0 && !this.loadCanceled) {
                        this.loadCondition.block();
                        i2 = this.progressiveMediaExtractor.read(this.positionHolder);
                        long currentInputPosition = this.progressiveMediaExtractor.getCurrentInputPosition();
                        if (currentInputPosition > ProgressiveMediaPeriod.this.continueLoadingCheckIntervalBytes + j2) {
                            this.loadCondition.close();
                            ProgressiveMediaPeriod.this.handler.post(ProgressiveMediaPeriod.this.onContinueLoadingRequestedRunnable);
                            j2 = currentInputPosition;
                        }
                    }
                    if (i2 == 1) {
                        i2 = 0;
                    } else if (this.progressiveMediaExtractor.getCurrentInputPosition() != -1) {
                        this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
                    }
                    DataSourceUtil.closeQuietly(this.dataSource);
                } catch (InterruptedException unused2) {
                    throw new InterruptedIOException();
                } catch (Throwable th) {
                    if (!(i2 == 1 || this.progressiveMediaExtractor.getCurrentInputPosition() == -1)) {
                        this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
                    }
                    DataSourceUtil.closeQuietly(this.dataSource);
                    throw th;
                }
            }
        }

        public void onIcyMetadata(ParsableByteArray parsableByteArray) {
            long max;
            if (!this.seenIcyMetadata) {
                max = this.seekTimeUs;
            } else {
                max = Math.max(ProgressiveMediaPeriod.this.getLargestQueuedTimestampUs(true), this.seekTimeUs);
            }
            long j2 = max;
            int bytesLeft = parsableByteArray.bytesLeft();
            TrackOutput trackOutput = (TrackOutput) Assertions.checkNotNull(this.icyTrackOutput);
            trackOutput.sampleData(parsableByteArray, bytesLeft);
            trackOutput.sampleMetadata(j2, 1, bytesLeft, 0, (TrackOutput.CryptoData) null);
            this.seenIcyMetadata = true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onSourceInfoRefreshed(long j2, SeekMap seekMap, boolean z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class SampleStreamImpl implements SampleStream {
        /* access modifiers changed from: private */
        public final int track;

        public SampleStreamImpl(int i2) {
            this.track = i2;
        }

        public void maybeThrowError() {
            ProgressiveMediaPeriod.this.maybeThrowError(this.track);
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i2) {
            return ProgressiveMediaPeriod.this.readData(this.track, formatHolder, decoderInputBuffer, i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TrackId {
        public final int id;
        public final boolean isIcyTrack;

        public TrackId(int i2, boolean z) {
            this.id = i2;
            this.isIcyTrack = z;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && TrackId.class == obj.getClass()) {
                TrackId trackId = (TrackId) obj;
                if (this.id == trackId.id && this.isIcyTrack == trackId.isIcyTrack) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return (this.id * 31) + (this.isIcyTrack ? 1 : 0);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TrackState {
        public final boolean[] trackEnabledStates;
        public final boolean[] trackIsAudioVideoFlags;
        public final boolean[] trackNotifiedDownstreamFormats;
        public final TrackGroupArray tracks;

        public TrackState(TrackGroupArray trackGroupArray, boolean[] zArr) {
            this.tracks = trackGroupArray;
            this.trackIsAudioVideoFlags = zArr;
            int i2 = trackGroupArray.length;
            this.trackEnabledStates = new boolean[i2];
            this.trackNotifiedDownstreamFormats = new boolean[i2];
        }
    }

    public ProgressiveMediaPeriod(Uri uri2, DataSource dataSource2, ProgressiveMediaExtractor progressiveMediaExtractor2, DrmSessionManager drmSessionManager2, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, MediaSourceEventListener.EventDispatcher eventDispatcher2, Listener listener2, Allocator allocator2, String str, int i2, int i7, Format format, long j2, ReleasableExecutor releasableExecutor) {
        Loader loader2;
        ReleasableExecutor releasableExecutor2 = releasableExecutor;
        this.uri = uri2;
        this.dataSource = dataSource2;
        this.drmSessionManager = drmSessionManager2;
        this.drmEventDispatcher = eventDispatcher;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.mediaSourceEventDispatcher = eventDispatcher2;
        this.listener = listener2;
        this.allocator = allocator2;
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = (long) i2;
        this.singleTrackId = i7;
        this.singleTrackFormat = format;
        if (releasableExecutor2 != null) {
            loader2 = new Loader(releasableExecutor2);
        } else {
            loader2 = new Loader("ProgressiveMediaPeriod");
        }
        this.loader = loader2;
        this.progressiveMediaExtractor = progressiveMediaExtractor2;
        this.singleSampleDurationUs = j2;
        this.loadCondition = new ConditionVariable();
        this.maybeFinishPrepareRunnable = new d(this, 1);
        this.onContinueLoadingRequestedRunnable = new d(this, 2);
        this.handler = Util.createHandlerForCurrentLooper();
        this.sampleQueueTrackIds = new TrackId[0];
        this.sampleQueues = new SampleQueue[0];
        this.pendingResetPositionUs = -9223372036854775807L;
        this.dataType = 1;
    }

    private void assertPrepared() {
        Assertions.checkState(this.prepared);
        Assertions.checkNotNull(this.trackState);
        Assertions.checkNotNull(this.seekMap);
    }

    private boolean configureRetry(ExtractingLoadable extractingLoadable, int i2) {
        SeekMap seekMap2;
        if (this.isLengthKnown || !((seekMap2 = this.seekMap) == null || seekMap2.getDurationUs() == -9223372036854775807L)) {
            this.extractedSamplesCountAtStartOfLoad = i2;
            return true;
        }
        if (!this.prepared || suppressRead()) {
            this.notifyDiscontinuity = this.prepared;
            this.lastSeekPositionUs = 0;
            this.extractedSamplesCountAtStartOfLoad = 0;
            for (SampleQueue reset : this.sampleQueues) {
                reset.reset();
            }
            extractingLoadable.setLoadPosition(0, 0);
            return true;
        }
        this.pendingDeferredRetry = true;
        return false;
    }

    private static Map<String, String> createIcyMetadataHeaders() {
        HashMap hashMap = new HashMap();
        hashMap.put("Icy-MetaData", "1");
        return Collections.unmodifiableMap(hashMap);
    }

    private int getExtractedSamplesCount() {
        int i2 = 0;
        for (SampleQueue writeIndex : this.sampleQueues) {
            i2 += writeIndex.getWriteIndex();
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public long getLargestQueuedTimestampUs(boolean z) {
        long j2 = Long.MIN_VALUE;
        for (int i2 = 0; i2 < this.sampleQueues.length; i2++) {
            if (z || ((TrackState) Assertions.checkNotNull(this.trackState)).trackEnabledStates[i2]) {
                j2 = Math.max(j2, this.sampleQueues[i2].getLargestQueuedTimestampUs());
            }
        }
        return j2;
    }

    private boolean isPendingReset() {
        if (this.pendingResetPositionUs != -9223372036854775807L) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (!this.released) {
            ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLengthKnown$2() {
        this.isLengthKnown = true;
    }

    /* access modifiers changed from: private */
    public void maybeFinishPrepare() {
        boolean z;
        boolean z3;
        Metadata metadata;
        if (!this.released && !this.prepared && this.sampleQueuesBuilt && this.seekMap != null) {
            SampleQueue[] sampleQueueArr = this.sampleQueues;
            int length = sampleQueueArr.length;
            int i2 = 0;
            while (i2 < length) {
                if (sampleQueueArr[i2].getUpstreamFormat() != null) {
                    i2++;
                } else {
                    return;
                }
            }
            this.loadCondition.close();
            int length2 = this.sampleQueues.length;
            TrackGroup[] trackGroupArr = new TrackGroup[length2];
            boolean[] zArr = new boolean[length2];
            for (int i7 = 0; i7 < length2; i7++) {
                Format format = (Format) Assertions.checkNotNull(this.sampleQueues[i7].getUpstreamFormat());
                String str = format.sampleMimeType;
                boolean isAudio = MimeTypes.isAudio(str);
                if (isAudio || MimeTypes.isVideo(str)) {
                    z = true;
                } else {
                    z = false;
                }
                zArr[i7] = z;
                this.haveAudioVideoTracks = z | this.haveAudioVideoTracks;
                boolean isImage = MimeTypes.isImage(str);
                if (this.singleSampleDurationUs == -9223372036854775807L || length2 != 1 || !isImage) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                this.isSingleSample = z3;
                IcyHeaders icyHeaders2 = this.icyHeaders;
                if (icyHeaders2 != null) {
                    if (isAudio || this.sampleQueueTrackIds[i7].isIcyTrack) {
                        Metadata metadata2 = format.metadata;
                        if (metadata2 == null) {
                            metadata = new Metadata(icyHeaders2);
                        } else {
                            metadata = metadata2.copyWithAppendedEntries(icyHeaders2);
                        }
                        format = format.buildUpon().setMetadata(metadata).build();
                    }
                    if (isAudio && format.averageBitrate == -1 && format.peakBitrate == -1 && icyHeaders2.bitrate != -1) {
                        format = format.buildUpon().setAverageBitrate(icyHeaders2.bitrate).build();
                    }
                }
                Format copyWithCryptoType = format.copyWithCryptoType(this.drmSessionManager.getCryptoType(format));
                trackGroupArr[i7] = new TrackGroup(Integer.toString(i7), copyWithCryptoType);
                this.pendingInitialDiscontinuity = copyWithCryptoType.hasPrerollSamples | this.pendingInitialDiscontinuity;
            }
            this.trackState = new TrackState(new TrackGroupArray(trackGroupArr), zArr);
            if (this.isSingleSample && this.durationUs == -9223372036854775807L) {
                this.durationUs = this.singleSampleDurationUs;
                this.seekMap = new ForwardingSeekMap(this.seekMap) {
                    public long getDurationUs() {
                        return ProgressiveMediaPeriod.this.durationUs;
                    }
                };
            }
            this.listener.onSourceInfoRefreshed(this.durationUs, this.seekMap, this.isLive);
            this.prepared = true;
            ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
        }
    }

    private void maybeNotifyDownstreamFormat(int i2) {
        assertPrepared();
        TrackState trackState2 = this.trackState;
        boolean[] zArr = trackState2.trackNotifiedDownstreamFormats;
        if (!zArr[i2]) {
            Format format = trackState2.tracks.get(i2).getFormat(0);
            this.mediaSourceEventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(format.sampleMimeType), format, 0, (Object) null, this.lastSeekPositionUs);
            zArr[i2] = true;
        }
    }

    private void maybeStartDeferredRetry(int i2) {
        assertPrepared();
        if (!this.pendingDeferredRetry) {
            return;
        }
        if (!this.haveAudioVideoTracks || this.trackState.trackIsAudioVideoFlags[i2]) {
            if (!this.sampleQueues[i2].isReady(false)) {
                this.pendingResetPositionUs = 0;
                this.pendingDeferredRetry = false;
                this.notifyDiscontinuity = true;
                this.lastSeekPositionUs = 0;
                this.extractedSamplesCountAtStartOfLoad = 0;
                for (SampleQueue reset : this.sampleQueues) {
                    reset.reset();
                }
                ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onLengthKnown() {
        this.handler.post(new d(this, 0));
    }

    private TrackOutput prepareTrackOutput(TrackId trackId) {
        int length = this.sampleQueues.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (trackId.equals(this.sampleQueueTrackIds[i2])) {
                return this.sampleQueues[i2];
            }
        }
        if (this.sampleQueuesBuilt) {
            Log.w("ProgressiveMediaPeriod", "Extractor added new track (id=" + trackId.id + ") after finishing tracks.");
            return new DiscardingTrackOutput();
        }
        SampleQueue createWithDrm = SampleQueue.createWithDrm(this.allocator, this.drmSessionManager, this.drmEventDispatcher);
        createWithDrm.setUpstreamFormatChangeListener(this);
        int i7 = length + 1;
        TrackId[] trackIdArr = (TrackId[]) Arrays.copyOf(this.sampleQueueTrackIds, i7);
        trackIdArr[length] = trackId;
        this.sampleQueueTrackIds = (TrackId[]) Util.castNonNullTypeArray(trackIdArr);
        SampleQueue[] sampleQueueArr = (SampleQueue[]) Arrays.copyOf(this.sampleQueues, i7);
        sampleQueueArr[length] = createWithDrm;
        this.sampleQueues = (SampleQueue[]) Util.castNonNullTypeArray(sampleQueueArr);
        return createWithDrm;
    }

    private boolean seekInsideBufferUs(boolean[] zArr, long j2, boolean z) {
        boolean z3;
        int length = this.sampleQueues.length;
        for (int i2 = 0; i2 < length; i2++) {
            SampleQueue sampleQueue = this.sampleQueues[i2];
            if (sampleQueue.getReadIndex() != 0 || !z) {
                if (this.isSingleSample) {
                    z3 = sampleQueue.seekTo(sampleQueue.getFirstIndex());
                } else {
                    z3 = sampleQueue.seekTo(j2, this.loadingFinished);
                }
                if (!z3 && (zArr[i2] || !this.haveAudioVideoTracks)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: setSeekMap */
    public void lambda$seekMap$1(SeekMap seekMap2) {
        SeekMap seekMap3;
        boolean z;
        if (this.icyHeaders == null) {
            seekMap3 = seekMap2;
        } else {
            seekMap3 = new SeekMap.Unseekable(-9223372036854775807L);
        }
        this.seekMap = seekMap3;
        this.durationUs = seekMap2.getDurationUs();
        int i2 = 1;
        if (this.isLengthKnown || seekMap2.getDurationUs() != -9223372036854775807L) {
            z = false;
        } else {
            z = true;
        }
        this.isLive = z;
        if (z) {
            i2 = 7;
        }
        this.dataType = i2;
        if (this.prepared) {
            this.listener.onSourceInfoRefreshed(this.durationUs, seekMap2, z);
        } else {
            maybeFinishPrepare();
        }
    }

    private void startLoading() {
        ExtractingLoadable extractingLoadable = new ExtractingLoadable(this.uri, this.dataSource, this.progressiveMediaExtractor, this, this.loadCondition);
        if (this.prepared) {
            Assertions.checkState(isPendingReset());
            long j2 = this.durationUs;
            if (j2 == -9223372036854775807L || this.pendingResetPositionUs <= j2) {
                extractingLoadable.setLoadPosition(((SeekMap) Assertions.checkNotNull(this.seekMap)).getSeekPoints(this.pendingResetPositionUs).first.position, this.pendingResetPositionUs);
                for (SampleQueue startTimeUs : this.sampleQueues) {
                    startTimeUs.setStartTimeUs(this.pendingResetPositionUs);
                }
                this.pendingResetPositionUs = -9223372036854775807L;
            } else {
                this.loadingFinished = true;
                this.pendingResetPositionUs = -9223372036854775807L;
                return;
            }
        }
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        this.loader.startLoading(extractingLoadable, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.dataType));
    }

    private boolean suppressRead() {
        if (this.notifyDiscontinuity || isPendingReset()) {
            return true;
        }
        return false;
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        if (this.loadingFinished || this.loader.hasFatalError() || this.pendingDeferredRetry) {
            return false;
        }
        if ((this.prepared || this.singleTrackFormat != null) && this.enabledTrackCount == 0) {
            return false;
        }
        boolean open = this.loadCondition.open();
        if (this.loader.isLoading()) {
            return open;
        }
        startLoading();
        return true;
    }

    public void discardBuffer(long j2, boolean z) {
        if (!this.isSingleSample) {
            assertPrepared();
            if (!isPendingReset()) {
                boolean[] zArr = this.trackState.trackEnabledStates;
                int length = this.sampleQueues.length;
                for (int i2 = 0; i2 < length; i2++) {
                    this.sampleQueues[i2].discardTo(j2, z, zArr[i2]);
                }
            }
        }
    }

    public void endTracks() {
        this.sampleQueuesBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        assertPrepared();
        if (!this.seekMap.isSeekable()) {
            return 0;
        }
        SeekMap.SeekPoints seekPoints = this.seekMap.getSeekPoints(j2);
        return seekParameters.resolveSeekPositionUs(j2, seekPoints.first.timeUs, seekPoints.second.timeUs);
    }

    public long getBufferedPositionUs() {
        long j2;
        assertPrepared();
        if (this.loadingFinished || this.enabledTrackCount == 0) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.haveAudioVideoTracks) {
            int length = this.sampleQueues.length;
            j2 = Long.MAX_VALUE;
            for (int i2 = 0; i2 < length; i2++) {
                TrackState trackState2 = this.trackState;
                if (trackState2.trackIsAudioVideoFlags[i2] && trackState2.trackEnabledStates[i2] && !this.sampleQueues[i2].isLastSampleQueued()) {
                    j2 = Math.min(j2, this.sampleQueues[i2].getLargestQueuedTimestampUs());
                }
            }
        } else {
            j2 = Long.MAX_VALUE;
        }
        if (j2 == Long.MAX_VALUE) {
            j2 = getLargestQueuedTimestampUs(false);
        }
        if (j2 == Long.MIN_VALUE) {
            return this.lastSeekPositionUs;
        }
        return j2;
    }

    public long getNextLoadPositionUs() {
        return getBufferedPositionUs();
    }

    public TrackGroupArray getTrackGroups() {
        assertPrepared();
        return this.trackState.tracks;
    }

    public TrackOutput icyTrack() {
        return prepareTrackOutput(new TrackId(0, true));
    }

    public boolean isLoading() {
        if (!this.loader.isLoading() || !this.loadCondition.isOpen()) {
            return false;
        }
        return true;
    }

    public void maybeThrowError(int i2) {
        this.sampleQueues[i2].maybeThrowError();
        maybeThrowError();
    }

    public void maybeThrowPrepareError() {
        maybeThrowError();
        if (this.loadingFinished && !this.prepared) {
            throw ParserException.createForMalformedContainer("Loading finished before preparation is complete.", (Throwable) null);
        }
    }

    public void onLoaderReleased() {
        for (SampleQueue release : this.sampleQueues) {
            release.release();
        }
        this.progressiveMediaExtractor.release();
    }

    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void prepare(MediaPeriod.Callback callback2, long j2) {
        this.callback = callback2;
        if (this.singleTrackFormat != null) {
            track(this.singleTrackId, 3).format(this.singleTrackFormat);
            lambda$seekMap$1(new IndexSeekMap(new long[]{0}, new long[]{0}, -9223372036854775807L));
            endTracks();
            this.pendingResetPositionUs = j2;
            return;
        }
        this.loadCondition.open();
        startLoading();
    }

    public int readData(int i2, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i7) {
        if (suppressRead()) {
            return -3;
        }
        maybeNotifyDownstreamFormat(i2);
        int read = this.sampleQueues[i2].read(formatHolder, decoderInputBuffer, i7, this.loadingFinished);
        if (read == -3) {
            maybeStartDeferredRetry(i2);
        }
        return read;
    }

    public long readDiscontinuity() {
        if (this.pendingInitialDiscontinuity) {
            this.pendingInitialDiscontinuity = false;
            return this.lastSeekPositionUs;
        } else if (!this.notifyDiscontinuity) {
            return -9223372036854775807L;
        } else {
            if (!this.loadingFinished && getExtractedSamplesCount() <= this.extractedSamplesCountAtStartOfLoad) {
                return -9223372036854775807L;
            }
            this.notifyDiscontinuity = false;
            return this.lastSeekPositionUs;
        }
    }

    public void release() {
        if (this.prepared) {
            for (SampleQueue preRelease : this.sampleQueues) {
                preRelease.preRelease();
            }
        }
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages((Object) null);
        this.callback = null;
        this.released = true;
    }

    public void seekMap(SeekMap seekMap2) {
        this.handler.post(new e(this, seekMap2));
    }

    public long seekToUs(long j2) {
        boolean z;
        assertPrepared();
        boolean[] zArr = this.trackState.trackIsAudioVideoFlags;
        if (!this.seekMap.isSeekable()) {
            j2 = 0;
        }
        int i2 = 0;
        this.notifyDiscontinuity = false;
        if (this.lastSeekPositionUs == j2) {
            z = true;
        } else {
            z = false;
        }
        this.lastSeekPositionUs = j2;
        if (isPendingReset()) {
            this.pendingResetPositionUs = j2;
            return j2;
        }
        if (this.dataType == 7 || ((!this.loadingFinished && !this.loader.isLoading()) || !seekInsideBufferUs(zArr, j2, z))) {
            this.pendingDeferredRetry = false;
            this.pendingResetPositionUs = j2;
            this.loadingFinished = false;
            this.pendingInitialDiscontinuity = false;
            if (this.loader.isLoading()) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                int length = sampleQueueArr.length;
                while (i2 < length) {
                    sampleQueueArr[i2].discardToEnd();
                    i2++;
                }
                this.loader.cancelLoading();
                return j2;
            }
            this.loader.clearFatalError();
            SampleQueue[] sampleQueueArr2 = this.sampleQueues;
            int length2 = sampleQueueArr2.length;
            while (i2 < length2) {
                sampleQueueArr2[i2].reset();
                i2++;
            }
        }
        return j2;
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        boolean z;
        ExoTrackSelection exoTrackSelection;
        boolean z3;
        boolean z7;
        assertPrepared();
        TrackState trackState2 = this.trackState;
        TrackGroupArray trackGroupArray = trackState2.tracks;
        boolean[] zArr3 = trackState2.trackEnabledStates;
        int i2 = this.enabledTrackCount;
        int i7 = 0;
        for (int i8 = 0; i8 < exoTrackSelectionArr.length; i8++) {
            SampleStreamImpl sampleStreamImpl = sampleStreamArr[i8];
            if (sampleStreamImpl != null && (exoTrackSelectionArr[i8] == null || !zArr[i8])) {
                int access$000 = sampleStreamImpl.track;
                Assertions.checkState(zArr3[access$000]);
                this.enabledTrackCount--;
                zArr3[access$000] = false;
                sampleStreamArr[i8] = null;
            }
        }
        if (!this.seenFirstTrackSelection ? j2 == 0 || this.isSingleSample : i2 != 0) {
            z = false;
        } else {
            z = true;
        }
        for (int i10 = 0; i10 < exoTrackSelectionArr.length; i10++) {
            if (sampleStreamArr[i10] == null && (exoTrackSelection = exoTrackSelectionArr[i10]) != null) {
                if (exoTrackSelection.length() == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                Assertions.checkState(z3);
                if (exoTrackSelection.getIndexInTrackGroup(0) == 0) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                Assertions.checkState(z7);
                int indexOf = trackGroupArray.indexOf(exoTrackSelection.getTrackGroup());
                Assertions.checkState(!zArr3[indexOf]);
                this.enabledTrackCount++;
                zArr3[indexOf] = true;
                this.pendingInitialDiscontinuity = exoTrackSelection.getSelectedFormat().hasPrerollSamples | this.pendingInitialDiscontinuity;
                sampleStreamArr[i10] = new SampleStreamImpl(indexOf);
                zArr2[i10] = true;
                if (!z) {
                    SampleQueue sampleQueue = this.sampleQueues[indexOf];
                    if (sampleQueue.getReadIndex() == 0 || sampleQueue.seekTo(j2, true)) {
                        z = false;
                    } else {
                        z = true;
                    }
                }
            }
        }
        if (this.enabledTrackCount == 0) {
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = false;
            this.pendingInitialDiscontinuity = false;
            if (this.loader.isLoading()) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                int length = sampleQueueArr.length;
                while (i7 < length) {
                    sampleQueueArr[i7].discardToEnd();
                    i7++;
                }
                this.loader.cancelLoading();
            } else {
                this.loadingFinished = false;
                SampleQueue[] sampleQueueArr2 = this.sampleQueues;
                int length2 = sampleQueueArr2.length;
                while (i7 < length2) {
                    sampleQueueArr2[i7].reset();
                    i7++;
                }
            }
        } else if (z) {
            j2 = seekToUs(j2);
            while (i7 < sampleStreamArr.length) {
                if (sampleStreamArr[i7] != null) {
                    zArr2[i7] = true;
                }
                i7++;
            }
        }
        this.seenFirstTrackSelection = true;
        return j2;
    }

    public TrackOutput track(int i2, int i7) {
        return prepareTrackOutput(new TrackId(i2, false));
    }

    public void onLoadCanceled(ExtractingLoadable extractingLoadable, long j2, long j3, boolean z) {
        StatsDataSource access$100 = extractingLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, access$100.getBytesRead());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(extractingLoadable.loadTaskId);
        this.mediaSourceEventDispatcher.loadCanceled(loadEventInfo, 1, -1, (Format) null, 0, (Object) null, extractingLoadable.seekTimeUs, this.durationUs);
        if (!z) {
            for (SampleQueue reset : this.sampleQueues) {
                reset.reset();
            }
            if (this.enabledTrackCount > 0) {
                ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
            }
        }
    }

    public void onLoadCompleted(ExtractingLoadable extractingLoadable, long j2, long j3) {
        if (this.durationUs == -9223372036854775807L && this.seekMap != null) {
            long largestQueuedTimestampUs = getLargestQueuedTimestampUs(true);
            long j8 = largestQueuedTimestampUs == Long.MIN_VALUE ? 0 : largestQueuedTimestampUs + 10000;
            this.durationUs = j8;
            this.listener.onSourceInfoRefreshed(j8, this.seekMap, this.isLive);
        }
        StatsDataSource access$100 = extractingLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, access$100.getBytesRead());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(extractingLoadable.loadTaskId);
        this.mediaSourceEventDispatcher.loadCompleted(loadEventInfo, 1, -1, (Format) null, 0, (Object) null, extractingLoadable.seekTimeUs, this.durationUs);
        this.loadingFinished = true;
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }

    public Loader.LoadErrorAction onLoadError(ExtractingLoadable extractingLoadable, long j2, long j3, IOException iOException, int i2) {
        ExtractingLoadable extractingLoadable2;
        Loader.LoadErrorAction loadErrorAction;
        StatsDataSource access$100 = extractingLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, access$100.getBytesRead());
        IOException iOException2 = iOException;
        long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(loadEventInfo, new MediaLoadData(1, -1, (Format) null, 0, (Object) null, Util.usToMs(extractingLoadable.seekTimeUs), Util.usToMs(this.durationUs)), iOException2, i2));
        if (retryDelayMsFor == -9223372036854775807L) {
            loadErrorAction = Loader.DONT_RETRY_FATAL;
            extractingLoadable2 = extractingLoadable;
        } else {
            int extractedSamplesCount = getExtractedSamplesCount();
            boolean z = extractedSamplesCount > this.extractedSamplesCountAtStartOfLoad;
            extractingLoadable2 = extractingLoadable;
            if (configureRetry(extractingLoadable2, extractedSamplesCount)) {
                loadErrorAction = Loader.createRetryAction(z, retryDelayMsFor);
            } else {
                loadErrorAction = Loader.DONT_RETRY;
            }
        }
        boolean isRetry = loadErrorAction.isRetry();
        this.mediaSourceEventDispatcher.loadError(loadEventInfo, 1, -1, (Format) null, 0, (Object) null, extractingLoadable2.seekTimeUs, this.durationUs, iOException2, !isRetry);
        if (!isRetry) {
            this.loadErrorHandlingPolicy.onLoadTaskConcluded(extractingLoadable2.loadTaskId);
        }
        return loadErrorAction;
    }

    public void onLoadStarted(ExtractingLoadable extractingLoadable, long j2, long j3, int i2) {
        LoadEventInfo loadEventInfo;
        StatsDataSource access$100 = extractingLoadable.dataSource;
        if (i2 == 0) {
            loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, j2);
        } else {
            loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, access$100.getBytesRead());
        }
        this.mediaSourceEventDispatcher.loadStarted(loadEventInfo, 1, -1, (Format) null, 0, (Object) null, extractingLoadable.seekTimeUs, this.durationUs, i2);
    }

    public void maybeThrowError() {
        this.loader.maybeThrowError(this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.dataType));
    }

    public void reevaluateBuffer(long j2) {
    }
}
