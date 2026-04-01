package nb;

import android.view.MenuItem;
import com.samsung.android.gallery.widget.bottom.BottomBar;
import k2.q;

/* renamed from: nb.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0700a implements q {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomBar e;

    public /* synthetic */ C0700a(BottomBar bottomBar, int i2) {
        this.d = i2;
        this.e = bottomBar;
    }

    public final boolean onNavigationItemSelected(MenuItem menuItem) {
        int i2 = this.d;
        BottomBar bottomBar = this.e;
        switch (i2) {
            case 0:
                return bottomBar.onNavigationItemSelected(menuItem);
            default:
                return bottomBar.lambda$new$0(menuItem);
        }
    }
}
