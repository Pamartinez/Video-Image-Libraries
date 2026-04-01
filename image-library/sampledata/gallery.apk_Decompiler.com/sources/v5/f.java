package v5;

import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter;
import com.samsung.android.gallery.widget.listview.GalleryListView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements GalleryListView.onTouchUpListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2718a;
    public final /* synthetic */ SearchPicturesPresenter b;

    public /* synthetic */ f(SearchPicturesPresenter searchPicturesPresenter, int i2) {
        this.f2718a = i2;
        this.b = searchPicturesPresenter;
    }

    public final void touchUpOnSelectionMode() {
        int i2 = this.f2718a;
        SearchPicturesPresenter searchPicturesPresenter = this.b;
        switch (i2) {
            case 0:
                searchPicturesPresenter.setEnableOptionsMenu();
                return;
            default:
                searchPicturesPresenter.hideSearchToolbarOnEnterSelectionMode();
                return;
        }
    }
}
