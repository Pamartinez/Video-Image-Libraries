package v5;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchPicturesPresenter e;

    public /* synthetic */ h(SearchPicturesPresenter searchPicturesPresenter, int i2) {
        this.d = i2;
        this.e = searchPicturesPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SearchPicturesPresenter searchPicturesPresenter = this.e;
        switch (i2) {
            case 0:
                searchPicturesPresenter.lambda$new$0(obj, bundle);
                return;
            case 1:
                searchPicturesPresenter.clearSearchExpansion(obj, bundle);
                return;
            case 2:
                searchPicturesPresenter.lambda$createGlobalSubscriberList$2(obj, bundle);
                return;
            default:
                searchPicturesPresenter.lambda$createGlobalSubscriberList$4(obj, bundle);
                return;
        }
    }
}
