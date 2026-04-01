package U4;

import android.view.MenuItem;
import androidx.appcompat.widget.PopupMenu;
import com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements PopupMenu.OnMenuItemClickListener, PopupMenu.OnDismissListener {
    public final /* synthetic */ ListPopupMenuDelegate d;

    public /* synthetic */ b(ListPopupMenuDelegate listPopupMenuDelegate) {
        this.d = listPopupMenuDelegate;
    }

    public void onDismiss(PopupMenu popupMenu) {
        this.d.lambda$showPopupMenu$5(popupMenu);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.d.lambda$showPopupMenu$4(menuItem);
    }
}
