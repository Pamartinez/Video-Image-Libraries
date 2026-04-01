package androidx.media3.exoplayer.source;

import androidx.media3.common.Timeline;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ForwardingTimeline extends Timeline {
    protected final Timeline timeline;

    public ForwardingTimeline(Timeline timeline2) {
        this.timeline = timeline2;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int getFirstWindowIndex(boolean z) {
        return this.timeline.getFirstWindowIndex(z);
    }

    public int getIndexOfPeriod(Object obj) {
        return this.timeline.getIndexOfPeriod(obj);
    }

    public int getLastWindowIndex(boolean z) {
        return this.timeline.getLastWindowIndex(z);
    }

    public int getNextWindowIndex(int i2, int i7, boolean z) {
        return this.timeline.getNextWindowIndex(i2, i7, z);
    }

    public Timeline.Period getPeriod(int i2, Timeline.Period period, boolean z) {
        return this.timeline.getPeriod(i2, period, z);
    }

    public final Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
        return super.getPeriodByUid(obj, period);
    }

    public int getPeriodCount() {
        return this.timeline.getPeriodCount();
    }

    public int getPreviousWindowIndex(int i2, int i7, boolean z) {
        return this.timeline.getPreviousWindowIndex(i2, i7, z);
    }

    public Object getUidOfPeriod(int i2) {
        return this.timeline.getUidOfPeriod(i2);
    }

    public Timeline.Window getWindow(int i2, Timeline.Window window, long j2) {
        return this.timeline.getWindow(i2, window, j2);
    }

    public int getWindowCount() {
        return this.timeline.getWindowCount();
    }

    public final int hashCode() {
        return super.hashCode();
    }
}
