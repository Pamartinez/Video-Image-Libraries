package Da;

import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.plugins.portrait.LiveEffectActivity;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements FastOptionItemView.ItemSelectedListener, Toolbar.OnMenuItemClickListener {
    public final /* synthetic */ LiveEffectActivity d;

    public /* synthetic */ b(LiveEffectActivity liveEffectActivity) {
        this.d = liveEffectActivity;
    }

    public void onItemSelected(int i2, View view) {
        this.d.lambda$bindFastOptionView$9(i2, view);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.d.lambda$bindToolbar$8(menuItem);
    }
}
