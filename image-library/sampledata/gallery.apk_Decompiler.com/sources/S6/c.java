package S6;

import com.samsung.android.gallery.app.ui.list.timeline.quicksearch.QuickSearchDelegate;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2425a;
    public final /* synthetic */ QuickSearchDelegate b;

    public /* synthetic */ c(QuickSearchDelegate quickSearchDelegate, int i2) {
        this.f2425a = i2;
        this.b = quickSearchDelegate;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f2425a;
        String str = (String) obj;
        Boolean bool = (Boolean) obj2;
        QuickSearchDelegate quickSearchDelegate = this.b;
        switch (i2) {
            case 0:
                quickSearchDelegate.onLocationFilterListItemClicked(str, bool);
                return;
            default:
                quickSearchDelegate.onPeopleFilterListItemClicked(str, bool);
                return;
        }
    }
}
