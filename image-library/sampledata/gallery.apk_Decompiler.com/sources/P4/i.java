package p4;

import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DrawerTabViewAdapter e;

    public /* synthetic */ i(int i2, DrawerTabViewAdapter drawerTabViewAdapter) {
        this.d = i2;
        this.e = drawerTabViewAdapter;
    }

    public final void run() {
        int i2 = this.d;
        DrawerTabViewAdapter drawerTabViewAdapter = this.e;
        switch (i2) {
            case 0:
                drawerTabViewAdapter.updateItemsEnableStatus();
                return;
            default:
                drawerTabViewAdapter.lambda$new$0();
                return;
        }
    }
}
