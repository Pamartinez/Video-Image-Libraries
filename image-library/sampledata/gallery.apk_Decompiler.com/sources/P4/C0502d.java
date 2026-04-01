package p4;

import android.view.View;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabController;

/* renamed from: p4.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0502d implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DrawerTabController e;

    public /* synthetic */ C0502d(DrawerTabController drawerTabController, int i2) {
        this.d = i2;
        this.e = drawerTabController;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        DrawerTabController drawerTabController = this.e;
        switch (i2) {
            case 0:
                drawerTabController.onNavigationIconClicked(view);
                return;
            default:
                drawerTabController.onStartSettings(view);
                return;
        }
    }
}
