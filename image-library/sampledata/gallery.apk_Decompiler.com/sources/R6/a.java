package R6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.timeline.TimelineFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ TimelineFragment e;

    public /* synthetic */ a(TimelineFragment timelineFragment, int i2) {
        this.d = i2;
        this.e = timelineFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        TimelineFragment timelineFragment = this.e;
        View view = (View) obj;
        switch (i2) {
            case 0:
                timelineFragment.onUpdateSyncView(view);
                return;
            default:
                timelineFragment.onUpdateSmartSwitch(view);
                return;
        }
    }
}
