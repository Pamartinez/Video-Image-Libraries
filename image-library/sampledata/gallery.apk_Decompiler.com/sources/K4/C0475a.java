package k4;

import android.view.View;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabMenu;

/* renamed from: k4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0475a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomTabMenu e;

    public /* synthetic */ C0475a(BottomTabMenu bottomTabMenu, int i2) {
        this.d = i2;
        this.e = bottomTabMenu;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        BottomTabMenu bottomTabMenu = this.e;
        switch (i2) {
            case 0:
                bottomTabMenu.onItemClicked(view);
                return;
            default:
                bottomTabMenu.onVideoStudioClicked(view);
                return;
        }
    }
}
