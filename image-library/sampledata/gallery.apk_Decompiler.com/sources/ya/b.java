package ya;

import android.view.MenuItem;
import android.view.MotionEvent;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.plugins.compare.CompareActivity;
import com.samsung.android.gallery.plugins.compare.GridView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Toolbar.OnMenuItemClickListener, GridView.InterceptTouchEventListener {
    public final /* synthetic */ CompareActivity d;

    public /* synthetic */ b(CompareActivity compareActivity) {
        this.d = compareActivity;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.d.onTouchInterceptedEvent(motionEvent);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.d.lambda$bindToolbar$15(menuItem);
    }
}
