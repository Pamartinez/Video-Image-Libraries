package v5;

import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter;
import com.samsung.android.gallery.module.search.root.IntelligentSearch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements IntelligentSearch.DataChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2717a;
    public final /* synthetic */ SearchPicturesPresenter b;

    public /* synthetic */ d(SearchPicturesPresenter searchPicturesPresenter, int i2) {
        this.f2717a = i2;
        this.b = searchPicturesPresenter;
    }

    public final void onChanged() {
        int i2 = this.f2717a;
        SearchPicturesPresenter searchPicturesPresenter = this.b;
        switch (i2) {
            case 0:
                searchPicturesPresenter.lambda$registerScsContentObserver$7();
                return;
            default:
                searchPicturesPresenter.lambda$registerScsContentObserver$8();
                return;
        }
    }
}
