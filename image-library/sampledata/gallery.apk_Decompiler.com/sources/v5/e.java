package v5;

import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.ForegroundViewController;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchPicturesPresenter e;

    public /* synthetic */ e(SearchPicturesPresenter searchPicturesPresenter, int i2) {
        this.d = i2;
        this.e = searchPicturesPresenter;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SearchPicturesPresenter searchPicturesPresenter = this.e;
        switch (i2) {
            case 0:
                searchPicturesPresenter.lambda$onViewDestroy$6((ForegroundViewController) obj);
                return;
            case 1:
                searchPicturesPresenter.lambda$createGlobalSubscriberList$3((SearchHeaderView) obj);
                return;
            case 2:
                searchPicturesPresenter.lambda$moveToViewer$11((MediaItem) obj);
                return;
            default:
                searchPicturesPresenter.lambda$onViewCreated$1((ForegroundViewController) obj);
                return;
        }
    }
}
