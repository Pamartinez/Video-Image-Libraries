package p4;

import android.view.MenuItem;
import android.widget.PopupMenu;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerPopupMenu;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.PopupMenuData;

/* renamed from: p4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0499a implements PopupMenu.OnMenuItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DrawerPopupMenu f2692a;
    public final /* synthetic */ EventContext b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ PopupMenuData f2693c;

    public /* synthetic */ C0499a(DrawerPopupMenu drawerPopupMenu, EventContext eventContext, PopupMenuData popupMenuData) {
        this.f2692a = drawerPopupMenu;
        this.b = eventContext;
        this.f2693c = popupMenuData;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        return this.f2692a.lambda$create$0(this.b, this.f2693c, menuItem);
    }
}
