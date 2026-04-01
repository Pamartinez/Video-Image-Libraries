package k2;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends MenuBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final Class f1809a;

    public e(Context context, Class cls) {
        super(context);
        this.f1809a = cls;
    }

    public final MenuItem addInternal(int i2, int i7, int i8, CharSequence charSequence) {
        stopDispatchingItemsChanged();
        MenuItem addInternal = super.addInternal(i2, i7, i8, charSequence);
        if (addInternal instanceof MenuItemImpl) {
            ((MenuItemImpl) addInternal).setExclusiveCheckable(true);
        }
        startDispatchingItemsChanged();
        return addInternal;
    }

    public final SubMenu addSubMenu(int i2, int i7, int i8, CharSequence charSequence) {
        throw new UnsupportedOperationException(this.f1809a.getSimpleName().concat(" does not support submenus"));
    }
}
