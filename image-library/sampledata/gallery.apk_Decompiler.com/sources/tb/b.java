package Tb;

import android.view.View;
import com.samsung.android.gallery.widget.search.filter.AllFiltersView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AllFiltersView e;

    public /* synthetic */ b(AllFiltersView allFiltersView, int i2) {
        this.d = i2;
        this.e = allFiltersView;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        AllFiltersView allFiltersView = this.e;
        switch (i2) {
            case 0:
                allFiltersView.lambda$bindView$1(view);
                return;
            default:
                allFiltersView.lambda$bindView$0(view);
                return;
        }
    }
}
