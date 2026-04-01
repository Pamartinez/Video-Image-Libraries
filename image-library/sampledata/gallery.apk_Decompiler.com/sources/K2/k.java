package k2;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.SubMenuBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k implements MenuPresenter.Callback {
    public final /* synthetic */ m d;

    public k(m mVar) {
        this.d = mVar;
    }

    public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder instanceof SubMenuBuilder) {
            menuBuilder.getRootMenu().close(false);
        }
        MenuPresenter.Callback callback = this.d.getCallback();
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z);
        }
    }

    public final boolean onOpenSubMenu(MenuBuilder menuBuilder) {
        MenuPresenter.Callback callback;
        if (menuBuilder == null || (callback = this.d.getCallback()) == null || !callback.onOpenSubMenu(menuBuilder)) {
            return false;
        }
        return true;
    }
}
