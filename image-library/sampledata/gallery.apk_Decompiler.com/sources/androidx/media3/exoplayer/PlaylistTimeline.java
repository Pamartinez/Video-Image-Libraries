package androidx.media3.exoplayer;

import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.ForwardingTimeline;
import androidx.media3.exoplayer.source.ShuffleOrder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PlaylistTimeline extends AbstractConcatenatedTimeline {
    private final HashMap<Object, Integer> childIndexByUid;
    private final int[] firstPeriodInChildIndices;
    private final int[] firstWindowInChildIndices;
    private final int periodCount;
    private final Timeline[] timelines;
    private final Object[] uids;
    private final int windowCount;

    public PlaylistTimeline(Collection<? extends MediaSourceInfoHolder> collection, ShuffleOrder shuffleOrder) {
        this(getTimelines(collection), getUids(collection), shuffleOrder);
    }

    private static Timeline[] getTimelines(Collection<? extends MediaSourceInfoHolder> collection) {
        Timeline[] timelineArr = new Timeline[collection.size()];
        int i2 = 0;
        for (MediaSourceInfoHolder timeline : collection) {
            timelineArr[i2] = timeline.getTimeline();
            i2++;
        }
        return timelineArr;
    }

    private static Object[] getUids(Collection<? extends MediaSourceInfoHolder> collection) {
        Object[] objArr = new Object[collection.size()];
        int i2 = 0;
        for (MediaSourceInfoHolder uid : collection) {
            objArr[i2] = uid.getUid();
            i2++;
        }
        return objArr;
    }

    public PlaylistTimeline copyWithPlaceholderTimeline(ShuffleOrder shuffleOrder) {
        Timeline[] timelineArr = new Timeline[this.timelines.length];
        int i2 = 0;
        while (true) {
            Timeline[] timelineArr2 = this.timelines;
            if (i2 >= timelineArr2.length) {
                return new PlaylistTimeline(timelineArr, this.uids, shuffleOrder);
            }
            timelineArr[i2] = new ForwardingTimeline(timelineArr2[i2]) {
                private final Timeline.Window window = new Timeline.Window();

                public Timeline.Period getPeriod(int i2, Timeline.Period period, boolean z) {
                    Timeline.Period period2 = super.getPeriod(i2, period, z);
                    if (getWindow(period2.windowIndex, this.window).isLive()) {
                        period2.set(period.id, period.uid, period.windowIndex, period.durationUs, period.positionInWindowUs, AdPlaybackState.NONE, true);
                        return period2;
                    }
                    period2.isPlaceholder = true;
                    return period2;
                }
            };
            i2++;
        }
    }

    public int getChildIndexByChildUid(Object obj) {
        Integer num = this.childIndexByUid.get(obj);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public int getChildIndexByPeriodIndex(int i2) {
        return Util.binarySearchFloor(this.firstPeriodInChildIndices, i2 + 1, false, false);
    }

    public int getChildIndexByWindowIndex(int i2) {
        return Util.binarySearchFloor(this.firstWindowInChildIndices, i2 + 1, false, false);
    }

    public List<Timeline> getChildTimelines() {
        return Arrays.asList(this.timelines);
    }

    public Object getChildUidByChildIndex(int i2) {
        return this.uids[i2];
    }

    public int getFirstPeriodIndexByChildIndex(int i2) {
        return this.firstPeriodInChildIndices[i2];
    }

    public int getFirstWindowIndexByChildIndex(int i2) {
        return this.firstWindowInChildIndices[i2];
    }

    public int getPeriodCount() {
        return this.periodCount;
    }

    public Timeline getTimelineByChildIndex(int i2) {
        return this.timelines[i2];
    }

    public int getWindowCount() {
        return this.windowCount;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private PlaylistTimeline(Timeline[] timelineArr, Object[] objArr, ShuffleOrder shuffleOrder) {
        super(false, shuffleOrder);
        int i2 = 0;
        int length = timelineArr.length;
        this.timelines = timelineArr;
        this.firstPeriodInChildIndices = new int[length];
        this.firstWindowInChildIndices = new int[length];
        this.uids = objArr;
        this.childIndexByUid = new HashMap<>();
        int length2 = timelineArr.length;
        int i7 = 0;
        int i8 = 0;
        int i10 = 0;
        while (i2 < length2) {
            Timeline timeline = timelineArr[i2];
            this.timelines[i10] = timeline;
            this.firstWindowInChildIndices[i10] = i7;
            this.firstPeriodInChildIndices[i10] = i8;
            i7 += timeline.getWindowCount();
            i8 += this.timelines[i10].getPeriodCount();
            this.childIndexByUid.put(objArr[i10], Integer.valueOf(i10));
            i2++;
            i10++;
        }
        this.windowCount = i7;
        this.periodCount = i8;
    }
}
