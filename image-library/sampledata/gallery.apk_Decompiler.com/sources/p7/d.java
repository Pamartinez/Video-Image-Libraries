package p7;

import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements GalleryToolbar.OnOverflowMenuShownListener, Toolbar.OnMenuItemClickListener, FastOptionItemView.ItemSelectedListener {
    public final /* synthetic */ ViewerMenuDelegate d;

    public /* synthetic */ d(ViewerMenuDelegate viewerMenuDelegate) {
        this.d = viewerMenuDelegate;
    }

    public void onItemSelected(int i2, View view) {
        this.d.onFastOptionItemSelected(i2, view);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.d.lambda$createMenu$12(menuItem);
    }
}
