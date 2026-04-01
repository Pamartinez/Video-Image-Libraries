package androidx.media3.exoplayer.source;

import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExternallyLoadedMediaSource extends BaseMediaSource {
    private MediaItem mediaItem;
    private final long timelineDurationUs;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory implements MediaSource.Factory {
        private final long timelineDurationUs;

        public Factory(long j2, ExternalLoader externalLoader) {
            this.timelineDurationUs = j2;
        }

        public ExternallyLoadedMediaSource createMediaSource(MediaItem mediaItem) {
            return new ExternallyLoadedMediaSource(mediaItem, this.timelineDurationUs, (ExternalLoader) null);
        }

        public MediaSource.Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            return this;
        }

        public MediaSource.Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            return this;
        }
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        MediaItem mediaItem2 = getMediaItem();
        Assertions.checkNotNull(mediaItem2.localConfiguration);
        Assertions.checkNotNull(mediaItem2.localConfiguration.mimeType, "Externally loaded mediaItems require a MIME type.");
        MediaItem.LocalConfiguration localConfiguration = mediaItem2.localConfiguration;
        return new ExternallyLoadedMediaPeriod(localConfiguration.uri, localConfiguration.mimeType, (ExternalLoader) null);
    }

    public synchronized MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public void prepareSourceInternal(TransferListener transferListener) {
        refreshSourceInfo(new SinglePeriodTimeline(this.timelineDurationUs, true, false, false, (Object) null, getMediaItem()));
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((ExternallyLoadedMediaPeriod) mediaPeriod).releasePeriod();
    }

    public synchronized void updateMediaItem(MediaItem mediaItem2) {
        this.mediaItem = mediaItem2;
    }

    private ExternallyLoadedMediaSource(MediaItem mediaItem2, long j2, ExternalLoader externalLoader) {
        this.mediaItem = mediaItem2;
        this.timelineDurationUs = j2;
    }

    public void maybeThrowSourceInfoRefreshError() {
    }

    public void releaseSourceInternal() {
    }
}
