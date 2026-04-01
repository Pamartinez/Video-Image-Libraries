package androidx.media3.exoplayer.source;

import E2.r;
import K4.a;
import android.net.Uri;
import android.os.Looper;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManagerProvider;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.ProgressiveMediaExtractor;
import androidx.media3.exoplayer.source.ProgressiveMediaPeriod;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.SeekMap;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ProgressiveMediaSource extends BaseMediaSource implements ProgressiveMediaPeriod.Listener {
    private final int continueLoadingCheckIntervalBytes;
    private final DataSource.Factory dataSourceFactory;
    private final r downloadExecutorSupplier;
    private final DrmSessionManager drmSessionManager;
    private final LoadErrorHandlingPolicy loadableLoadErrorHandlingPolicy;
    private MediaItem mediaItem;
    private final ProgressiveMediaExtractor.Factory progressiveMediaExtractorFactory;
    private final Format singleTrackFormat;
    private final int singleTrackId;
    private long timelineDurationUs;
    private boolean timelineIsLive;
    private boolean timelineIsPlaceholder;
    private boolean timelineIsSeekable;
    private TransferListener transferListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory implements MediaSourceFactory {
        private int continueLoadingCheckIntervalBytes;
        private final DataSource.Factory dataSourceFactory;
        private r downloadExecutorSupplier;
        private DrmSessionManagerProvider drmSessionManagerProvider;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private ProgressiveMediaExtractor.Factory progressiveMediaExtractorFactory;
        private Format singleTrackFormat;
        private int singleTrackId;

        public Factory(DataSource.Factory factory, ExtractorsFactory extractorsFactory) {
            this(factory, (ProgressiveMediaExtractor.Factory) new a(8, extractorsFactory));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ ProgressiveMediaExtractor lambda$new$0(ExtractorsFactory extractorsFactory, PlayerId playerId) {
            return new BundledExtractorsAdapter(extractorsFactory);
        }

        public Factory enableLazyLoadingWithSingleTrack(int i2, Format format) {
            this.singleTrackId = i2;
            this.singleTrackFormat = (Format) Assertions.checkNotNull(format);
            return this;
        }

        public Factory(DataSource.Factory factory, ProgressiveMediaExtractor.Factory factory2) {
            this(factory, factory2, new DefaultDrmSessionManagerProvider(), new DefaultLoadErrorHandlingPolicy(), MediaDefs.Meta.SEF.SEF_MIN_SIZE);
        }

        public ProgressiveMediaSource createMediaSource(MediaItem mediaItem) {
            Assertions.checkNotNull(mediaItem.localConfiguration);
            return new ProgressiveMediaSource(mediaItem, this.dataSourceFactory, this.progressiveMediaExtractorFactory, this.drmSessionManagerProvider.get(mediaItem), this.loadErrorHandlingPolicy, this.continueLoadingCheckIntervalBytes, this.singleTrackId, this.singleTrackFormat, this.downloadExecutorSupplier);
        }

        public Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider2) {
            this.drmSessionManagerProvider = (DrmSessionManagerProvider) Assertions.checkNotNull(drmSessionManagerProvider2, "MediaSource.Factory#setDrmSessionManagerProvider no longer handles null by instantiating a new DefaultDrmSessionManagerProvider. Explicitly construct and pass an instance in order to retain the old behavior.");
            return this;
        }

        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy2) {
            this.loadErrorHandlingPolicy = (LoadErrorHandlingPolicy) Assertions.checkNotNull(loadErrorHandlingPolicy2, "MediaSource.Factory#setLoadErrorHandlingPolicy no longer handles null by instantiating a new DefaultLoadErrorHandlingPolicy. Explicitly construct and pass an instance in order to retain the old behavior.");
            return this;
        }

        public Factory(DataSource.Factory factory, ProgressiveMediaExtractor.Factory factory2, DrmSessionManagerProvider drmSessionManagerProvider2, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, int i2) {
            this.dataSourceFactory = factory;
            this.progressiveMediaExtractorFactory = factory2;
            this.drmSessionManagerProvider = drmSessionManagerProvider2;
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
            this.continueLoadingCheckIntervalBytes = i2;
        }
    }

    private MediaItem.LocalConfiguration getLocalConfiguration() {
        return (MediaItem.LocalConfiguration) Assertions.checkNotNull(getMediaItem().localConfiguration);
    }

    private void notifySourceInfoRefreshed() {
        Timeline singlePeriodTimeline = new SinglePeriodTimeline(this.timelineDurationUs, this.timelineIsSeekable, false, this.timelineIsLive, (Object) null, getMediaItem());
        if (this.timelineIsPlaceholder) {
            singlePeriodTimeline = new ForwardingTimeline(singlePeriodTimeline) {
                public Timeline.Period getPeriod(int i2, Timeline.Period period, boolean z) {
                    super.getPeriod(i2, period, z);
                    period.isPlaceholder = true;
                    return period;
                }

                public Timeline.Window getWindow(int i2, Timeline.Window window, long j2) {
                    super.getWindow(i2, window, j2);
                    window.isPlaceholder = true;
                    return window;
                }
            };
        }
        refreshSourceInfo(singlePeriodTimeline);
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        ReleasableExecutor releasableExecutor;
        ProgressiveMediaPeriod progressiveMediaPeriod;
        DataSource createDataSource = this.dataSourceFactory.createDataSource();
        TransferListener transferListener2 = this.transferListener;
        if (transferListener2 != null) {
            createDataSource.addTransferListener(transferListener2);
        }
        MediaItem.LocalConfiguration localConfiguration = getLocalConfiguration();
        Uri uri = localConfiguration.uri;
        ProgressiveMediaExtractor.Factory factory = this.progressiveMediaExtractorFactory;
        ProgressiveMediaExtractor a7 = Factory.lambda$new$0((ExtractorsFactory) ((a) factory).e, getPlayerId());
        ProgressiveMediaExtractor progressiveMediaExtractor = a7;
        DrmSessionManager drmSessionManager2 = this.drmSessionManager;
        DrmSessionEventListener.EventDispatcher createDrmEventDispatcher = createDrmEventDispatcher(mediaPeriodId);
        LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.loadableLoadErrorHandlingPolicy;
        MediaSourceEventListener.EventDispatcher createEventDispatcher = createEventDispatcher(mediaPeriodId);
        String str = localConfiguration.customCacheKey;
        int i2 = this.continueLoadingCheckIntervalBytes;
        int i7 = this.singleTrackId;
        Format format = this.singleTrackFormat;
        long msToUs = Util.msToUs(localConfiguration.imageDurationMs);
        r rVar = this.downloadExecutorSupplier;
        if (rVar != null) {
            releasableExecutor = (ReleasableExecutor) rVar.get();
        } else {
            releasableExecutor = null;
        }
        ReleasableExecutor releasableExecutor2 = releasableExecutor;
        progressiveMediaPeriod = new ProgressiveMediaPeriod(uri, createDataSource, progressiveMediaExtractor, drmSessionManager2, createDrmEventDispatcher, loadErrorHandlingPolicy, createEventDispatcher, this, allocator, str, i2, i7, format, msToUs, releasableExecutor2);
        return progressiveMediaPeriod;
    }

    public synchronized MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public void onSourceInfoRefreshed(long j2, SeekMap seekMap, boolean z) {
        if (j2 == -9223372036854775807L) {
            j2 = this.timelineDurationUs;
        }
        boolean isSeekable = seekMap.isSeekable();
        if (this.timelineIsPlaceholder || this.timelineDurationUs != j2 || this.timelineIsSeekable != isSeekable || this.timelineIsLive != z) {
            this.timelineDurationUs = j2;
            this.timelineIsSeekable = isSeekable;
            this.timelineIsLive = z;
            this.timelineIsPlaceholder = false;
            notifySourceInfoRefreshed();
        }
    }

    public void prepareSourceInternal(TransferListener transferListener2) {
        this.transferListener = transferListener2;
        this.drmSessionManager.setPlayer((Looper) Assertions.checkNotNull(Looper.myLooper()), getPlayerId());
        this.drmSessionManager.prepare();
        notifySourceInfoRefreshed();
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((ProgressiveMediaPeriod) mediaPeriod).release();
    }

    public void releaseSourceInternal() {
        this.drmSessionManager.release();
    }

    public synchronized void updateMediaItem(MediaItem mediaItem2) {
        this.mediaItem = mediaItem2;
    }

    private ProgressiveMediaSource(MediaItem mediaItem2, DataSource.Factory factory, ProgressiveMediaExtractor.Factory factory2, DrmSessionManager drmSessionManager2, LoadErrorHandlingPolicy loadErrorHandlingPolicy, int i2, int i7, Format format, r rVar) {
        this.mediaItem = mediaItem2;
        this.dataSourceFactory = factory;
        this.progressiveMediaExtractorFactory = factory2;
        this.drmSessionManager = drmSessionManager2;
        this.loadableLoadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.continueLoadingCheckIntervalBytes = i2;
        this.singleTrackFormat = format;
        this.singleTrackId = i7;
        this.timelineIsPlaceholder = true;
        this.timelineDurationUs = -9223372036854775807L;
        this.downloadExecutorSupplier = rVar;
    }

    public void maybeThrowSourceInfoRefreshError() {
    }
}
