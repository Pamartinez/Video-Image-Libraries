package xb;

import L7.u;
import android.view.MenuItem;
import com.samsung.android.gallery.widget.editdetails.EditDetailsView;
import k2.q;
import p2.C0262c;

/* renamed from: xb.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0716a implements C0262c, q {
    public final /* synthetic */ EditDetailsView.onMenuItemClickListener d;

    public /* synthetic */ C0716a(EditDetailsView.onMenuItemClickListener onmenuitemclicklistener) {
        this.d = onmenuitemclicklistener;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return ((u) this.d).d.onNavigationItemSelected(menuItem);
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return ((u) this.d).d.onNavigationItemSelected(menuItem);
    }
}
