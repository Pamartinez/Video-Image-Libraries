package R6;

import com.samsung.android.gallery.app.ui.list.timeline.TimelineFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ TimelineFragment e;

    public /* synthetic */ b(TimelineFragment timelineFragment, int i2) {
        this.d = i2;
        this.e = timelineFragment;
    }

    public final void run() {
        int i2 = this.d;
        TimelineFragment timelineFragment = this.e;
        switch (i2) {
            case 0:
                timelineFragment.lambda$dismissSyncView$9();
                return;
            case 1:
                timelineFragment.syncIfYearDataChanged();
                return;
            case 2:
                timelineFragment.createForTimelineViewPoolAsync();
                return;
            default:
                timelineFragment.lambda$syncIfYearDataChanged$7();
                return;
        }
    }
}
