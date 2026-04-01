package A4;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerPopupMenu;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.PopupMenuData;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListDelegate;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* renamed from: A4.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0374i implements PopupMenu.OnDismissListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2249a;
    public final /* synthetic */ View b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2250c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0374i(Object obj, Object obj2, Object obj3, View view, int i2) {
        this.f2249a = i2;
        this.f2250c = obj;
        this.d = obj2;
        this.e = obj3;
        this.b = view;
    }

    public final void onDismiss(PopupMenu popupMenu) {
        switch (this.f2249a) {
            case 0:
                ((BaseListDelegate) this.f2250c).lambda$createPopupMenu$4((Blackboard) this.d, (ViewGroup) this.e, this.b, popupMenu);
                return;
            default:
                ((DrawerPopupMenu) this.f2250c).lambda$create$1((EventContext) this.d, (PopupMenuData) this.e, this.b, popupMenu);
                return;
        }
    }
}
