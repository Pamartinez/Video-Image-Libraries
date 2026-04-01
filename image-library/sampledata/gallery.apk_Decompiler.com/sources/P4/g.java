package p4;

import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ DrawerTabViewAdapter e;

    public /* synthetic */ g(int i2, DrawerTabViewAdapter drawerTabViewAdapter) {
        this.d = i2;
        this.e = drawerTabViewAdapter;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        DrawerTabViewAdapter drawerTabViewAdapter = this.e;
        switch (i2) {
            case 0:
                drawerTabViewAdapter.lambda$setOnItemClickListener$4((MediaItem) obj);
                return;
            case 1:
                drawerTabViewAdapter.lambda$updateUsbTab$9((DrawerTabItem) obj);
                return;
            default:
                drawerTabViewAdapter.lambda$refreshTabItemEnableCondition$11((DrawerTabItem) obj);
                return;
        }
    }
}
