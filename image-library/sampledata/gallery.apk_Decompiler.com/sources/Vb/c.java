package Vb;

import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchToolbar e;
    public final /* synthetic */ FilterResultsEntity f;

    public /* synthetic */ c(SearchToolbar searchToolbar, FilterResultsEntity filterResultsEntity, int i2) {
        this.d = i2;
        this.e = searchToolbar;
        this.f = filterResultsEntity;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$replaceLastFilterToFuzzyKeyword$5(this.f, (SearchSelectedFiltersView) obj);
                return;
            default:
                this.e.lambda$replaceLastFilter$4(this.f, (SearchSelectedFiltersView) obj);
                return;
        }
    }
}
