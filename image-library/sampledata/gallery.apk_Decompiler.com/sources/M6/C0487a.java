package m6;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.menu.AbsMenuDelegator;
import com.samsung.android.gallery.widget.bottom.BottomBar;
import com.samsung.android.gallery.widget.bottom.BottomBarData;

/* renamed from: m6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0487a implements Toolbar.OnMenuItemClickListener, BottomBarData.InitListener {
    public final /* synthetic */ AbsMenuDelegator d;

    public /* synthetic */ C0487a(AbsMenuDelegator absMenuDelegator) {
        this.d = absMenuDelegator;
    }

    public void onInit(String str, BottomBar bottomBar) {
        this.d.lambda$getBottomBarInitListener$1(str, bottomBar);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.d.lambda$setToolbar$0(menuItem);
    }
}
