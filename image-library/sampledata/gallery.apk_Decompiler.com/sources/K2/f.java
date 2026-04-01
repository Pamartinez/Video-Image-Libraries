package k2;

import android.content.Context;
import androidx.appcompat.view.menu.MenuItemImpl;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends d {

    /* renamed from: W  reason: collision with root package name */
    public final /* synthetic */ MenuItemImpl f1810W;
    public final /* synthetic */ int a0;
    public final /* synthetic */ h b0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(h hVar, Context context, int i2, MenuItemImpl menuItemImpl, int i7) {
        super(context, i2);
        this.b0 = hVar;
        this.f1810W = menuItemImpl;
        this.a0 = i7;
    }

    public final int getItemLayoutResId() {
        if (this.f1810W.getSeslNaviMenuItemType() == 1) {
            return R.layout.sesl_bottom_navigation_item_checkbox;
        }
        int i2 = this.a0;
        if (i2 == 2) {
            return R.layout.sesl_bottom_navigation_item_icon_only;
        }
        if (i2 != 3) {
            return R.layout.sesl_bottom_navigation_item;
        }
        return R.layout.sesl_bottom_navigation_item_text;
    }

    public final void initialize(MenuItemImpl menuItemImpl, int i2) {
        super.initialize(menuItemImpl, i2);
        menuItemImpl.setExclusiveCheckable(this.b0.U);
    }
}
