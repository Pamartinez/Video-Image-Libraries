package A4;

import android.view.MenuItem;
import android.widget.PopupMenu;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel.GroupPanelSelectionMenuDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* renamed from: A4.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0373h implements PopupMenu.OnMenuItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2247a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2248c;

    public /* synthetic */ C0373h(int i2, Object obj, Object obj2) {
        this.f2247a = i2;
        this.b = obj;
        this.f2248c = obj2;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        switch (this.f2247a) {
            case 0:
                return ((BaseListDelegate) this.b).lambda$createPopupMenu$3((Blackboard) this.f2248c, menuItem);
            default:
                return ((GroupPanelSelectionMenuDelegate) this.b).lambda$createPopupMenu$0((MediaItem) this.f2248c, menuItem);
        }
    }
}
