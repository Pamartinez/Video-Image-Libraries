package k2;

import android.view.MenuItem;
import androidx.appcompat.view.menu.MenuBuilder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n implements MenuBuilder.Callback {
    public final /* synthetic */ BottomNavigationView d;

    public n(BottomNavigationView bottomNavigationView) {
        this.d = bottomNavigationView;
    }

    public final boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        BottomNavigationView bottomNavigationView = this.d;
        o unused = bottomNavigationView.getClass();
        p unused2 = bottomNavigationView.getClass();
        if (bottomNavigationView.selectedListener == null || bottomNavigationView.selectedListener.onNavigationItemSelected(menuItem)) {
            return false;
        }
        return true;
    }

    public final void onMenuModeChange(MenuBuilder menuBuilder) {
    }
}
