package p4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabController;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DrawerTabController e;

    public /* synthetic */ f(DrawerTabController drawerTabController, int i2) {
        this.d = i2;
        this.e = drawerTabController;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        DrawerTabController drawerTabController = this.e;
        switch (i2) {
            case 0:
                drawerTabController.onAlbumDataSwapped(obj, bundle);
                return;
            case 1:
                drawerTabController.onEnterMoveMode(obj, bundle);
                return;
            case 2:
                drawerTabController.onExitMoveMode(obj, bundle);
                return;
            case 3:
                drawerTabController.openDrawer(obj, bundle);
                return;
            case 4:
                drawerTabController.closeDrawer(obj, bundle);
                return;
            case 5:
                drawerTabController.onEnterSelectionMode(obj, bundle);
                return;
            case 6:
                drawerTabController.onExitSelectionMode(obj, bundle);
                return;
            default:
                drawerTabController.onThumbnailLoadDone(obj, bundle);
                return;
        }
    }
}
