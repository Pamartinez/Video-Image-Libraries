package nb;

import android.view.MenuItem;
import android.widget.PopupMenu;
import com.samsung.android.gallery.widget.bottom.BottomBar;

/* renamed from: nb.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0701b implements PopupMenu.OnMenuItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BottomBar f3279a;

    public /* synthetic */ C0701b(BottomBar bottomBar) {
        this.f3279a = bottomBar;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        return this.f3279a.onNavigationItemSelected(menuItem);
    }
}
