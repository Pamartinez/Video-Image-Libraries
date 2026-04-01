package J5;

import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchSelectedFiltersDelegate;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchSelectedFiltersDelegate e;
    public final /* synthetic */ ArrayList f;

    public /* synthetic */ d(SearchSelectedFiltersDelegate searchSelectedFiltersDelegate, ArrayList arrayList, int i2) {
        this.d = i2;
        this.e = searchSelectedFiltersDelegate;
        this.f = arrayList;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$initSubKeywordEntities$4(this.f);
                return;
            default:
                this.e.lambda$initSubKeywordEntities$3(this.f);
                return;
        }
    }
}
