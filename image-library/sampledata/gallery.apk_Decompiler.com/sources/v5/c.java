package v5;

import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesNoItemDelegate;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchPicturesNoItemDelegate e;

    public /* synthetic */ c(SearchPicturesNoItemDelegate searchPicturesNoItemDelegate, int i2) {
        this.d = i2;
        this.e = searchPicturesNoItemDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SearchPicturesNoItemDelegate searchPicturesNoItemDelegate = this.e;
        switch (i2) {
            case 0:
                searchPicturesNoItemDelegate.lambda$bindSuggestionView$1((SuggesterView) obj);
                return;
            default:
                searchPicturesNoItemDelegate.lambda$bindSuggestionView$0((Boolean) obj);
                return;
        }
    }
}
