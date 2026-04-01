package o4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.container.tablet.bottomtab.BottomTabController;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomTabController e;

    public /* synthetic */ b(BottomTabController bottomTabController, int i2) {
        this.d = i2;
        this.e = bottomTabController;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        BottomTabController bottomTabController = this.e;
        switch (i2) {
            case 0:
                bottomTabController.onEnterSelectionMode(obj, bundle);
                return;
            case 1:
                bottomTabController.onExitSelectionMode(obj, bundle);
                return;
            case 2:
                bottomTabController.blockFocus(obj, bundle);
                return;
            case 3:
                bottomTabController.onEnterMoveMode(obj, bundle);
                return;
            default:
                bottomTabController.onExitMoveMode(obj, bundle);
                return;
        }
    }
}
