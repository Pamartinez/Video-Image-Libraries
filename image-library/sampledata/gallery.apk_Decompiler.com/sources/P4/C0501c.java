package p4;

import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabController;

/* renamed from: p4.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0501c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DrawerTabController e;

    public /* synthetic */ C0501c(DrawerTabController drawerTabController, int i2) {
        this.d = i2;
        this.e = drawerTabController;
    }

    public final void run() {
        int i2 = this.d;
        DrawerTabController drawerTabController = this.e;
        switch (i2) {
            case 0:
                drawerTabController.lambda$changeFocus$2();
                return;
            case 1:
                drawerTabController.lambda$completeSlideAnimation$14();
                return;
            default:
                drawerTabController.setAdapterIfNeeded();
                return;
        }
    }
}
