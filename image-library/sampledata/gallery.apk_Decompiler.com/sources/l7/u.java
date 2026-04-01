package L7;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.viewer2.details.EditDetailsHandler;
import com.samsung.android.gallery.widget.editdetails.EditDetailsView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements EditDetailsView.onMenuItemClickListener, Toolbar.OnMenuItemClickListener {
    public final /* synthetic */ EditDetailsHandler d;

    public /* synthetic */ u(EditDetailsHandler editDetailsHandler) {
        this.d = editDetailsHandler;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.d.lambda$initEditDetailsView$2(menuItem);
    }
}
