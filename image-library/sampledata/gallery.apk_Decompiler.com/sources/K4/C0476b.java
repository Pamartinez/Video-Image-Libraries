package k4;

import android.view.View;
import com.samsung.android.gallery.app.ui.container.menu.BottomMenuItem;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabMenu;
import java.util.function.Consumer;

/* renamed from: k4.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0476b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomTabMenu e;

    public /* synthetic */ C0476b(BottomTabMenu bottomTabMenu, int i2) {
        this.d = i2;
        this.e = bottomTabMenu;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        BottomTabMenu bottomTabMenu = this.e;
        switch (i2) {
            case 0:
                bottomTabMenu.lambda$onItemClicked$1((BottomMenuItem) obj);
                return;
            default:
                bottomTabMenu.lambda$createVideoStudioView$0((View) obj);
                return;
        }
    }
}
