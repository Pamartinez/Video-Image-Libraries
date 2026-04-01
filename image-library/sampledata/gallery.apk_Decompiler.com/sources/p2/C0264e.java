package p2;

import android.content.Context;
import android.os.Parcelable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.SubMenuBuilder;
import com.google.android.material.oneui.dividerbuttonlayout.DividerButtonLayout;
import kotlin.jvm.internal.j;

/* renamed from: p2.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0264e implements MenuPresenter {
    public DividerButtonLayout d;
    public boolean e;

    public final boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        j.e(menuBuilder, "menu");
        j.e(menuItemImpl, "item");
        return false;
    }

    public final boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        j.e(menuBuilder, "menu");
        j.e(menuItemImpl, "item");
        return false;
    }

    public final boolean flagActionItems() {
        return false;
    }

    public final int getId() {
        return 0;
    }

    public final void initForMenu(Context context, MenuBuilder menuBuilder) {
        j.e(context, "context");
        j.e(menuBuilder, "menu");
    }

    public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        j.e(menuBuilder, "menu");
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        j.e(parcelable, "state");
    }

    public final Parcelable onSaveInstanceState() {
        return null;
    }

    public final boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        j.e(subMenuBuilder, "subMenu");
        return false;
    }

    public final void updateMenuView(boolean z) {
        if (!this.e) {
            if (z) {
                DividerButtonLayout dividerButtonLayout = this.d;
                if (dividerButtonLayout != null) {
                    dividerButtonLayout.b();
                } else {
                    j.k("menuView");
                    throw null;
                }
            } else {
                DividerButtonLayout dividerButtonLayout2 = this.d;
                if (dividerButtonLayout2 != null) {
                    dividerButtonLayout2.d();
                } else {
                    j.k("menuView");
                    throw null;
                }
            }
        }
    }
}
