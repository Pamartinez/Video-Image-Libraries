package Ab;

import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatTransitoryItemOnDemandViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d = 1;
    public final /* synthetic */ View e;
    public final /* synthetic */ ViewGroup f;

    public /* synthetic */ e(View view, ViewGroup viewGroup) {
        this.e = view;
        this.f = viewGroup;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ViewUtils.removeView(this.f, this.e);
                return;
            default:
                StoriesCatTransitoryItemOnDemandViewHolder.lambda$bindSearchToolbarAsync$0(this.e, this.f);
                return;
        }
    }

    public /* synthetic */ e(ViewGroup viewGroup, View view) {
        this.f = viewGroup;
        this.e = view;
    }
}
