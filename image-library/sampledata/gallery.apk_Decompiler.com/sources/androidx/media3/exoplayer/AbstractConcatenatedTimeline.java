package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.source.ShuffleOrder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbstractConcatenatedTimeline extends Timeline {
    private final int childCount;
    private final boolean isAtomic;
    private final ShuffleOrder shuffleOrder;

    public AbstractConcatenatedTimeline(boolean z, ShuffleOrder shuffleOrder2) {
        this.isAtomic = z;
        this.shuffleOrder = shuffleOrder2;
        this.childCount = shuffleOrder2.getLength();
    }

    public static Object getChildPeriodUidFromConcatenatedUid(Object obj) {
        return ((Pair) obj).second;
    }

    public static Object getChildTimelineUidFromConcatenatedUid(Object obj) {
        return ((Pair) obj).first;
    }

    public static Object getConcatenatedUid(Object obj, Object obj2) {
        return Pair.create(obj, obj2);
    }

    private int getNextChildIndex(int i2, boolean z) {
        if (z) {
            return this.shuffleOrder.getNextIndex(i2);
        }
        if (i2 < this.childCount - 1) {
            return i2 + 1;
        }
        return -1;
    }

    private int getPreviousChildIndex(int i2, boolean z) {
        if (z) {
            return this.shuffleOrder.getPreviousIndex(i2);
        }
        if (i2 > 0) {
            return i2 - 1;
        }
        return -1;
    }

    public abstract int getChildIndexByChildUid(Object obj);

    public abstract int getChildIndexByPeriodIndex(int i2);

    public abstract int getChildIndexByWindowIndex(int i2);

    public abstract Object getChildUidByChildIndex(int i2);

    public abstract int getFirstPeriodIndexByChildIndex(int i2);

    public int getFirstWindowIndex(boolean z) {
        if (this.childCount == 0) {
            return -1;
        }
        int i2 = 0;
        if (this.isAtomic) {
            z = false;
        }
        if (z) {
            i2 = this.shuffleOrder.getFirstIndex();
        }
        while (getTimelineByChildIndex(i2).isEmpty()) {
            i2 = getNextChildIndex(i2, z);
            if (i2 == -1) {
                return -1;
            }
        }
        return getTimelineByChildIndex(i2).getFirstWindowIndex(z) + getFirstWindowIndexByChildIndex(i2);
    }

    public abstract int getFirstWindowIndexByChildIndex(int i2);

    public final int getIndexOfPeriod(Object obj) {
        int indexOfPeriod;
        if (!(obj instanceof Pair)) {
            return -1;
        }
        Object childTimelineUidFromConcatenatedUid = getChildTimelineUidFromConcatenatedUid(obj);
        Object childPeriodUidFromConcatenatedUid = getChildPeriodUidFromConcatenatedUid(obj);
        int childIndexByChildUid = getChildIndexByChildUid(childTimelineUidFromConcatenatedUid);
        if (childIndexByChildUid == -1 || (indexOfPeriod = getTimelineByChildIndex(childIndexByChildUid).getIndexOfPeriod(childPeriodUidFromConcatenatedUid)) == -1) {
            return -1;
        }
        return getFirstPeriodIndexByChildIndex(childIndexByChildUid) + indexOfPeriod;
    }

    public int getLastWindowIndex(boolean z) {
        int i2;
        int i7 = this.childCount;
        if (i7 == 0) {
            return -1;
        }
        if (this.isAtomic) {
            z = false;
        }
        if (z) {
            i2 = this.shuffleOrder.getLastIndex();
        } else {
            i2 = i7 - 1;
        }
        while (getTimelineByChildIndex(i2).isEmpty()) {
            i2 = getPreviousChildIndex(i2, z);
            if (i2 == -1) {
                return -1;
            }
        }
        return getTimelineByChildIndex(i2).getLastWindowIndex(z) + getFirstWindowIndexByChildIndex(i2);
    }

    public int getNextWindowIndex(int i2, int i7, boolean z) {
        int i8 = 0;
        if (this.isAtomic) {
            if (i7 == 1) {
                i7 = 2;
            }
            z = false;
        }
        int childIndexByWindowIndex = getChildIndexByWindowIndex(i2);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByWindowIndex);
        Timeline timelineByChildIndex = getTimelineByChildIndex(childIndexByWindowIndex);
        int i10 = i2 - firstWindowIndexByChildIndex;
        if (i7 != 2) {
            i8 = i7;
        }
        int nextWindowIndex = timelineByChildIndex.getNextWindowIndex(i10, i8, z);
        if (nextWindowIndex != -1) {
            return firstWindowIndexByChildIndex + nextWindowIndex;
        }
        int nextChildIndex = getNextChildIndex(childIndexByWindowIndex, z);
        while (nextChildIndex != -1 && getTimelineByChildIndex(nextChildIndex).isEmpty()) {
            nextChildIndex = getNextChildIndex(nextChildIndex, z);
        }
        if (nextChildIndex != -1) {
            return getTimelineByChildIndex(nextChildIndex).getFirstWindowIndex(z) + getFirstWindowIndexByChildIndex(nextChildIndex);
        } else if (i7 == 2) {
            return getFirstWindowIndex(z);
        } else {
            return -1;
        }
    }

    public final Timeline.Period getPeriod(int i2, Timeline.Period period, boolean z) {
        int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i2);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByPeriodIndex);
        getTimelineByChildIndex(childIndexByPeriodIndex).getPeriod(i2 - getFirstPeriodIndexByChildIndex(childIndexByPeriodIndex), period, z);
        period.windowIndex += firstWindowIndexByChildIndex;
        if (z) {
            period.uid = getConcatenatedUid(getChildUidByChildIndex(childIndexByPeriodIndex), Assertions.checkNotNull(period.uid));
        }
        return period;
    }

    public final Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
        Object childTimelineUidFromConcatenatedUid = getChildTimelineUidFromConcatenatedUid(obj);
        Object childPeriodUidFromConcatenatedUid = getChildPeriodUidFromConcatenatedUid(obj);
        int childIndexByChildUid = getChildIndexByChildUid(childTimelineUidFromConcatenatedUid);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByChildUid);
        getTimelineByChildIndex(childIndexByChildUid).getPeriodByUid(childPeriodUidFromConcatenatedUid, period);
        period.windowIndex += firstWindowIndexByChildIndex;
        period.uid = obj;
        return period;
    }

    public int getPreviousWindowIndex(int i2, int i7, boolean z) {
        int i8 = 0;
        if (this.isAtomic) {
            if (i7 == 1) {
                i7 = 2;
            }
            z = false;
        }
        int childIndexByWindowIndex = getChildIndexByWindowIndex(i2);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByWindowIndex);
        Timeline timelineByChildIndex = getTimelineByChildIndex(childIndexByWindowIndex);
        int i10 = i2 - firstWindowIndexByChildIndex;
        if (i7 != 2) {
            i8 = i7;
        }
        int previousWindowIndex = timelineByChildIndex.getPreviousWindowIndex(i10, i8, z);
        if (previousWindowIndex != -1) {
            return firstWindowIndexByChildIndex + previousWindowIndex;
        }
        int previousChildIndex = getPreviousChildIndex(childIndexByWindowIndex, z);
        while (previousChildIndex != -1 && getTimelineByChildIndex(previousChildIndex).isEmpty()) {
            previousChildIndex = getPreviousChildIndex(previousChildIndex, z);
        }
        if (previousChildIndex != -1) {
            return getTimelineByChildIndex(previousChildIndex).getLastWindowIndex(z) + getFirstWindowIndexByChildIndex(previousChildIndex);
        } else if (i7 == 2) {
            return getLastWindowIndex(z);
        } else {
            return -1;
        }
    }

    public abstract Timeline getTimelineByChildIndex(int i2);

    public final Object getUidOfPeriod(int i2) {
        int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i2);
        return getConcatenatedUid(getChildUidByChildIndex(childIndexByPeriodIndex), getTimelineByChildIndex(childIndexByPeriodIndex).getUidOfPeriod(i2 - getFirstPeriodIndexByChildIndex(childIndexByPeriodIndex)));
    }

    public final Timeline.Window getWindow(int i2, Timeline.Window window, long j2) {
        int childIndexByWindowIndex = getChildIndexByWindowIndex(i2);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByWindowIndex);
        int firstPeriodIndexByChildIndex = getFirstPeriodIndexByChildIndex(childIndexByWindowIndex);
        getTimelineByChildIndex(childIndexByWindowIndex).getWindow(i2 - firstWindowIndexByChildIndex, window, j2);
        Object childUidByChildIndex = getChildUidByChildIndex(childIndexByWindowIndex);
        if (!Timeline.Window.SINGLE_WINDOW_UID.equals(window.uid)) {
            childUidByChildIndex = getConcatenatedUid(childUidByChildIndex, window.uid);
        }
        window.uid = childUidByChildIndex;
        window.firstPeriodIndex += firstPeriodIndexByChildIndex;
        window.lastPeriodIndex += firstPeriodIndexByChildIndex;
        return window;
    }
}
