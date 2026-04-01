package n7;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements PopupMenu.OnDismissListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewGroup f2676a;
    public final /* synthetic */ View b;

    public /* synthetic */ d(ViewGroup viewGroup, View view) {
        this.f2676a = viewGroup;
        this.b = view;
    }

    public final void onDismiss(PopupMenu popupMenu) {
        ViewUtils.removeView(this.f2676a, this.b);
    }
}
