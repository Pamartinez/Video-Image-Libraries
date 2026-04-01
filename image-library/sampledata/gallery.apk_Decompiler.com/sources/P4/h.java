package p4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DrawerTabViewAdapter e;

    public /* synthetic */ h(int i2, DrawerTabViewAdapter drawerTabViewAdapter) {
        this.d = i2;
        this.e = drawerTabViewAdapter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        DrawerTabViewAdapter drawerTabViewAdapter = this.e;
        switch (i2) {
            case 0:
                drawerTabViewAdapter.lambda$new$1(obj, bundle);
                return;
            default:
                drawerTabViewAdapter.onUsbStorageChanged(obj, bundle);
                return;
        }
    }
}
