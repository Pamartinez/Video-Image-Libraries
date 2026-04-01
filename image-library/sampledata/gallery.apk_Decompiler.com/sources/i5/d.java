package I5;

import com.samsung.android.gallery.app.ui.list.search.suggestionview.HierarchicalViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.IHierarchicalView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ HierarchicalViewAdapter e;

    public /* synthetic */ d(HierarchicalViewAdapter hierarchicalViewAdapter, int i2) {
        this.d = i2;
        this.e = hierarchicalViewAdapter;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        HierarchicalViewAdapter hierarchicalViewAdapter = this.e;
        switch (i2) {
            case 0:
                hierarchicalViewAdapter.lambda$setData$1((IHierarchicalView) obj);
                return;
            default:
                hierarchicalViewAdapter.lambda$parse$2((String) obj);
                return;
        }
    }
}
