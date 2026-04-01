package N8;

import android.view.MenuItem;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements MenuItem.OnMenuItemClickListener {
    public final /* synthetic */ ObjectCaptureHelper d;

    public /* synthetic */ f(ObjectCaptureHelper objectCaptureHelper) {
        this.d = objectCaptureHelper;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        return this.d.onMenuClick(menuItem);
    }
}
