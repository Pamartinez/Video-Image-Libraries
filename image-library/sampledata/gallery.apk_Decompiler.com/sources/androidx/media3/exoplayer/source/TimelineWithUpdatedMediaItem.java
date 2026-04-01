package androidx.media3.exoplayer.source;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TimelineWithUpdatedMediaItem extends ForwardingTimeline {
    private final MediaItem updatedMediaItem;

    public TimelineWithUpdatedMediaItem(Timeline timeline, MediaItem mediaItem) {
        super(timeline);
        this.updatedMediaItem = mediaItem;
    }

    public Timeline.Window getWindow(int i2, Timeline.Window window, long j2) {
        Object obj;
        super.getWindow(i2, window, j2);
        MediaItem mediaItem = this.updatedMediaItem;
        window.mediaItem = mediaItem;
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        if (localConfiguration != null) {
            obj = localConfiguration.tag;
        } else {
            obj = null;
        }
        window.tag = obj;
        return window;
    }
}
