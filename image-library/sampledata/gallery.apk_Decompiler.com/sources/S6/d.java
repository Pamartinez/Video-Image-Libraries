package S6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.timeline.quicksearch.QuickSearchDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ QuickSearchDelegate e;

    public /* synthetic */ d(QuickSearchDelegate quickSearchDelegate, int i2) {
        this.d = i2;
        this.e = quickSearchDelegate;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        QuickSearchDelegate quickSearchDelegate = this.e;
        switch (i2) {
            case 0:
                quickSearchDelegate.onImageFilterButtonClicked(view);
                return;
            case 1:
                quickSearchDelegate.onVideoFilterButtonClicked(view);
                return;
            case 2:
                quickSearchDelegate.onDateFilterButtonClicked(view);
                return;
            case 3:
                quickSearchDelegate.onLocationFilterButtonClicked(view);
                return;
            case 4:
                quickSearchDelegate.onPeopleFilterButtonClicked(view);
                return;
            case 5:
                quickSearchDelegate.onExpandButtonClicked(view);
                return;
            default:
                quickSearchDelegate.onDateFilterCardClicked(view);
                return;
        }
    }
}
