package Vb;

import android.view.View;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchToolbar e;

    public /* synthetic */ d(SearchToolbar searchToolbar, int i2) {
        this.d = i2;
        this.e = searchToolbar;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        SearchToolbar searchToolbar = this.e;
        switch (i2) {
            case 0:
                searchToolbar.lambda$inflateSelectedFiltersView$2(view);
                return;
            case 1:
                searchToolbar.onMoreButtonClickInternal(view);
                return;
            case 2:
                searchToolbar.lambda$initVoiceView$6(view);
                return;
            case 3:
                searchToolbar.onUpButtonClick(view);
                return;
            default:
                searchToolbar.lambda$initCloseView$7(view);
                return;
        }
    }
}
