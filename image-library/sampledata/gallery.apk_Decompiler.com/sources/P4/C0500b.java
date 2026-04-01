package p4;

import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerFakeViewManager;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabController;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabLayoutManager;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import java.util.function.Consumer;

/* renamed from: p4.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0500b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ DrawerTabController e;

    public /* synthetic */ C0500b(DrawerTabController drawerTabController, int i2) {
        this.d = i2;
        this.e = drawerTabController;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        DrawerTabController drawerTabController = this.e;
        switch (i2) {
            case 0:
                drawerTabController.lambda$updateDrawerWidth$1((DrawerTabLayoutManager) obj);
                return;
            case 1:
                drawerTabController.lambda$updateExpandedAlbumsTab$8((DrawerTabViewAdapter) obj);
                return;
            case 2:
                drawerTabController.lambda$completeSlideAnimation$16((DrawerFakeViewManager) obj);
                return;
            case 3:
                drawerTabController.lambda$completeSlideAnimation$17((DrawerTabViewAdapter) obj);
                return;
            case 4:
                drawerTabController.lambda$completeSlideAnimation$18((DrawerTabLayoutManager) obj);
                return;
            case 5:
                drawerTabController.lambda$updateCollapsedAlbumsTabFocus$9((DrawerTabViewAdapter) obj);
                return;
            case 6:
                drawerTabController.lambda$prepareSlideAnimation$12((DrawerTabViewAdapter) obj);
                return;
            default:
                drawerTabController.lambda$prepareSlideAnimation$13((DrawerFakeViewManager) obj);
                return;
        }
    }
}
