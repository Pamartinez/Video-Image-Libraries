package androidx.media3.common;

import F2.U;
import androidx.media3.common.Timeline;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BasePlayer implements Player {
    protected final Timeline.Window window = new Timeline.Window();

    private int getRepeatModeForNavigation() {
        int repeatMode = getRepeatMode();
        if (repeatMode == 1) {
            return 0;
        }
        return repeatMode;
    }

    public final long getContentDuration() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return -9223372036854775807L;
        }
        return currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).getDurationMs();
    }

    public final int getNextMediaItemIndex() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return -1;
        }
        return currentTimeline.getNextWindowIndex(getCurrentMediaItemIndex(), getRepeatModeForNavigation(), getShuffleModeEnabled());
    }

    public final int getPreviousMediaItemIndex() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return -1;
        }
        return currentTimeline.getPreviousWindowIndex(getCurrentMediaItemIndex(), getRepeatModeForNavigation(), getShuffleModeEnabled());
    }

    public final boolean hasNextMediaItem() {
        if (getNextMediaItemIndex() != -1) {
            return true;
        }
        return false;
    }

    public final boolean hasPreviousMediaItem() {
        if (getPreviousMediaItemIndex() != -1) {
            return true;
        }
        return false;
    }

    public final boolean isCurrentMediaItemDynamic() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty() || !currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isDynamic) {
            return false;
        }
        return true;
    }

    public final boolean isCurrentMediaItemLive() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty() || !currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isLive()) {
            return false;
        }
        return true;
    }

    public final boolean isCurrentMediaItemSeekable() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty() || !currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isSeekable) {
            return false;
        }
        return true;
    }

    public final void play() {
        setPlayWhenReady(true);
    }

    public final void setMediaItem(MediaItem mediaItem) {
        setMediaItems(U.B(mediaItem));
    }

    public final void setMediaItems(List<MediaItem> list) {
        setMediaItems(list, true);
    }
}
