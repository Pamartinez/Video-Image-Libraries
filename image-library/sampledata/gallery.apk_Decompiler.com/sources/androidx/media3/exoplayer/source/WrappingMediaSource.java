package androidx.media3.exoplayer.source;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.MediaSource;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WrappingMediaSource extends CompositeMediaSource<Void> {
    private static final Void CHILD_SOURCE_ID = null;
    protected final MediaSource mediaSource;

    public WrappingMediaSource(MediaSource mediaSource2) {
        this.mediaSource = mediaSource2;
    }

    public Timeline getInitialTimeline() {
        return this.mediaSource.getInitialTimeline();
    }

    public MediaItem getMediaItem() {
        return this.mediaSource.getMediaItem();
    }

    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        return mediaPeriodId;
    }

    public long getMediaTimeForChildMediaTime(long j2, MediaSource.MediaPeriodId mediaPeriodId) {
        return j2;
    }

    public int getWindowIndexForChildWindowIndex(int i2) {
        return i2;
    }

    public boolean isSingleWindow() {
        return this.mediaSource.isSingleWindow();
    }

    public abstract void onChildSourceInfoRefreshed(Timeline timeline);

    public final void prepareChildSource() {
        prepareChildSource(CHILD_SOURCE_ID, this.mediaSource);
    }

    public final void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        prepareSourceInternal();
    }

    public void updateMediaItem(MediaItem mediaItem) {
        this.mediaSource.updateMediaItem(mediaItem);
    }

    public final void onChildSourceInfoRefreshed(Void voidR, MediaSource mediaSource2, Timeline timeline) {
        onChildSourceInfoRefreshed(timeline);
    }

    public final MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Void voidR, MediaSource.MediaPeriodId mediaPeriodId) {
        return getMediaPeriodIdForChildMediaPeriodId(mediaPeriodId);
    }

    public final long getMediaTimeForChildMediaTime(Void voidR, long j2, MediaSource.MediaPeriodId mediaPeriodId) {
        return getMediaTimeForChildMediaTime(j2, mediaPeriodId);
    }

    public final int getWindowIndexForChildWindowIndex(Void voidR, int i2) {
        return getWindowIndexForChildWindowIndex(i2);
    }

    public void prepareSourceInternal() {
        prepareChildSource();
    }
}
