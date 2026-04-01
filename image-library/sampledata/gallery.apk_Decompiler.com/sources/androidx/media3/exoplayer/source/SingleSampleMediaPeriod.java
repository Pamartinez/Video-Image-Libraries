package androidx.media3.exoplayer.source;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SingleSampleMediaPeriod implements MediaPeriod, Loader.Callback<SourceLoadable> {
    private final DataSource.Factory dataSourceFactory;
    private final DataSpec dataSpec;
    private final long durationUs;
    /* access modifiers changed from: private */
    public final MediaSourceEventListener.EventDispatcher eventDispatcher;
    final Format format;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    final Loader loader;
    boolean loadingFinished;
    byte[] sampleData;
    int sampleSize;
    private final ArrayList<SampleStreamImpl> sampleStreams = new ArrayList<>();
    private final TrackGroupArray tracks;
    private final TransferListener transferListener;
    final boolean treatLoadErrorsAsEndOfStream;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class SampleStreamImpl implements SampleStream {
        private boolean notifiedDownstreamFormat;
        private int streamState;

        private SampleStreamImpl() {
        }

        private void maybeNotifyDownstreamFormat() {
            if (!this.notifiedDownstreamFormat) {
                SingleSampleMediaPeriod.this.eventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(SingleSampleMediaPeriod.this.format.sampleMimeType), SingleSampleMediaPeriod.this.format, 0, (Object) null, 0);
                this.notifiedDownstreamFormat = true;
            }
        }

        public void maybeThrowError() {
            SingleSampleMediaPeriod singleSampleMediaPeriod = SingleSampleMediaPeriod.this;
            if (!singleSampleMediaPeriod.treatLoadErrorsAsEndOfStream) {
                singleSampleMediaPeriod.loader.maybeThrowError();
            }
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i2) {
            maybeNotifyDownstreamFormat();
            SingleSampleMediaPeriod singleSampleMediaPeriod = SingleSampleMediaPeriod.this;
            boolean z = singleSampleMediaPeriod.loadingFinished;
            if (z && singleSampleMediaPeriod.sampleData == null) {
                this.streamState = 2;
            }
            int i7 = this.streamState;
            if (i7 == 2) {
                decoderInputBuffer.addFlag(4);
                return -4;
            } else if ((i2 & 2) != 0 || i7 == 0) {
                formatHolder.format = singleSampleMediaPeriod.format;
                this.streamState = 1;
                return -5;
            } else if (!z) {
                return -3;
            } else {
                Assertions.checkNotNull(singleSampleMediaPeriod.sampleData);
                decoderInputBuffer.addFlag(1);
                decoderInputBuffer.timeUs = 0;
                if ((i2 & 4) == 0) {
                    decoderInputBuffer.ensureSpaceForWrite(SingleSampleMediaPeriod.this.sampleSize);
                    ByteBuffer byteBuffer = decoderInputBuffer.data;
                    SingleSampleMediaPeriod singleSampleMediaPeriod2 = SingleSampleMediaPeriod.this;
                    byteBuffer.put(singleSampleMediaPeriod2.sampleData, 0, singleSampleMediaPeriod2.sampleSize);
                }
                if ((i2 & 1) == 0) {
                    this.streamState = 2;
                }
                return -4;
            }
        }

        public void reset() {
            if (this.streamState == 2) {
                this.streamState = 1;
            }
        }
    }

    public SingleSampleMediaPeriod(DataSpec dataSpec2, DataSource.Factory factory, TransferListener transferListener2, Format format2, long j2, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, MediaSourceEventListener.EventDispatcher eventDispatcher2, boolean z, ReleasableExecutor releasableExecutor) {
        Loader loader2;
        this.dataSpec = dataSpec2;
        this.dataSourceFactory = factory;
        this.transferListener = transferListener2;
        this.format = format2;
        this.durationUs = j2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.eventDispatcher = eventDispatcher2;
        this.treatLoadErrorsAsEndOfStream = z;
        this.tracks = new TrackGroupArray(new TrackGroup(format2));
        if (releasableExecutor != null) {
            loader2 = new Loader(releasableExecutor);
        } else {
            loader2 = new Loader("SingleSampleMediaPeriod");
        }
        this.loader = loader2;
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        if (this.loadingFinished || this.loader.isLoading() || this.loader.hasFatalError()) {
            return false;
        }
        DataSource createDataSource = this.dataSourceFactory.createDataSource();
        TransferListener transferListener2 = this.transferListener;
        if (transferListener2 != null) {
            createDataSource.addTransferListener(transferListener2);
        }
        this.loader.startLoading(new SourceLoadable(this.dataSpec, createDataSource), this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(1));
        return true;
    }

    public long getBufferedPositionUs() {
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        return 0;
    }

    public long getNextLoadPositionUs() {
        if (this.loadingFinished || this.loader.isLoading()) {
            return Long.MIN_VALUE;
        }
        return 0;
    }

    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    public boolean isLoading() {
        return this.loader.isLoading();
    }

    public void prepare(MediaPeriod.Callback callback, long j2) {
        callback.onPrepared(this);
    }

    public long readDiscontinuity() {
        return -9223372036854775807L;
    }

    public void release() {
        this.loader.release();
    }

    public long seekToUs(long j2) {
        for (int i2 = 0; i2 < this.sampleStreams.size(); i2++) {
            this.sampleStreams.get(i2).reset();
        }
        return j2;
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        for (int i2 = 0; i2 < exoTrackSelectionArr.length; i2++) {
            SampleStream sampleStream = sampleStreamArr[i2];
            if (sampleStream != null && (exoTrackSelectionArr[i2] == null || !zArr[i2])) {
                this.sampleStreams.remove(sampleStream);
                sampleStreamArr[i2] = null;
            }
            if (sampleStreamArr[i2] == null && exoTrackSelectionArr[i2] != null) {
                SampleStreamImpl sampleStreamImpl = new SampleStreamImpl();
                this.sampleStreams.add(sampleStreamImpl);
                sampleStreamArr[i2] = sampleStreamImpl;
                zArr2[i2] = true;
            }
        }
        return j2;
    }

    public void onLoadCanceled(SourceLoadable sourceLoadable, long j2, long j3, boolean z) {
        StatsDataSource access$100 = sourceLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(sourceLoadable.loadTaskId, sourceLoadable.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, access$100.getBytesRead());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(sourceLoadable.loadTaskId);
        this.eventDispatcher.loadCanceled(loadEventInfo, 1, -1, (Format) null, 0, (Object) null, 0, this.durationUs);
    }

    public void onLoadCompleted(SourceLoadable sourceLoadable, long j2, long j3) {
        this.sampleSize = (int) sourceLoadable.dataSource.getBytesRead();
        this.sampleData = (byte[]) Assertions.checkNotNull(sourceLoadable.sampleData);
        this.loadingFinished = true;
        StatsDataSource access$100 = sourceLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(sourceLoadable.loadTaskId, sourceLoadable.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, (long) this.sampleSize);
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(sourceLoadable.loadTaskId);
        this.eventDispatcher.loadCompleted(loadEventInfo, 1, -1, this.format, 0, (Object) null, 0, this.durationUs);
    }

    public Loader.LoadErrorAction onLoadError(SourceLoadable sourceLoadable, long j2, long j3, IOException iOException, int i2) {
        Loader.LoadErrorAction loadErrorAction;
        SourceLoadable sourceLoadable2 = sourceLoadable;
        IOException iOException2 = iOException;
        int i7 = i2;
        StatsDataSource access$100 = sourceLoadable2.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(sourceLoadable2.loadTaskId, sourceLoadable2.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, access$100.getBytesRead());
        long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(loadEventInfo, new MediaLoadData(1, -1, this.format, 0, (Object) null, 0, Util.usToMs(this.durationUs)), iOException2, i7));
        int i8 = (retryDelayMsFor > -9223372036854775807L ? 1 : (retryDelayMsFor == -9223372036854775807L ? 0 : -1));
        boolean z = i8 == 0 || i7 >= this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(1);
        if (this.treatLoadErrorsAsEndOfStream && z) {
            Log.w("SingleSampleMediaPeriod", "Loading failed, treating as end-of-stream.", iOException2);
            this.loadingFinished = true;
            loadErrorAction = Loader.DONT_RETRY;
        } else if (i8 != 0) {
            loadErrorAction = Loader.createRetryAction(false, retryDelayMsFor);
        } else {
            loadErrorAction = Loader.DONT_RETRY_FATAL;
        }
        Loader.LoadErrorAction loadErrorAction2 = loadErrorAction;
        boolean isRetry = loadErrorAction2.isRetry();
        this.eventDispatcher.loadError(loadEventInfo, 1, -1, this.format, 0, (Object) null, 0, this.durationUs, iOException2, !isRetry);
        if (!isRetry) {
            this.loadErrorHandlingPolicy.onLoadTaskConcluded(sourceLoadable2.loadTaskId);
        }
        return loadErrorAction2;
    }

    public void onLoadStarted(SourceLoadable sourceLoadable, long j2, long j3, int i2) {
        LoadEventInfo loadEventInfo;
        SourceLoadable sourceLoadable2 = sourceLoadable;
        StatsDataSource access$100 = sourceLoadable2.dataSource;
        if (i2 == 0) {
            loadEventInfo = new LoadEventInfo(sourceLoadable2.loadTaskId, sourceLoadable2.dataSpec, j2);
        } else {
            loadEventInfo = new LoadEventInfo(sourceLoadable2.loadTaskId, sourceLoadable2.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, access$100.getBytesRead());
        }
        this.eventDispatcher.loadStarted(loadEventInfo, 1, -1, this.format, 0, (Object) null, 0, this.durationUs, i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SourceLoadable implements Loader.Loadable {
        /* access modifiers changed from: private */
        public final StatsDataSource dataSource;
        public final DataSpec dataSpec;
        public final long loadTaskId = LoadEventInfo.getNewId();
        /* access modifiers changed from: private */
        public byte[] sampleData;

        public SourceLoadable(DataSpec dataSpec2, DataSource dataSource2) {
            this.dataSpec = dataSpec2;
            this.dataSource = new StatsDataSource(dataSource2);
        }

        public void load() {
            this.dataSource.resetBytesRead();
            try {
                this.dataSource.open(this.dataSpec);
                int i2 = 0;
                while (i2 != -1) {
                    int bytesRead = (int) this.dataSource.getBytesRead();
                    byte[] bArr = this.sampleData;
                    if (bArr == null) {
                        this.sampleData = new byte[1024];
                    } else if (bytesRead == bArr.length) {
                        this.sampleData = Arrays.copyOf(bArr, bArr.length * 2);
                    }
                    StatsDataSource statsDataSource = this.dataSource;
                    byte[] bArr2 = this.sampleData;
                    i2 = statsDataSource.read(bArr2, bytesRead, bArr2.length - bytesRead);
                }
                DataSourceUtil.closeQuietly(this.dataSource);
            } catch (Throwable th) {
                DataSourceUtil.closeQuietly(this.dataSource);
                throw th;
            }
        }

        public void cancelLoad() {
        }
    }

    public void maybeThrowPrepareError() {
    }

    public void reevaluateBuffer(long j2) {
    }

    public void discardBuffer(long j2, boolean z) {
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        return j2;
    }
}
