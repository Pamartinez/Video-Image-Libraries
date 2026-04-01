package B4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsPresenter;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements InstantSubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumsPresenter e;

    public /* synthetic */ f(AlbumsPresenter albumsPresenter, int i2) {
        this.d = i2;
        this.e = albumsPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        AlbumsPresenter albumsPresenter = this.e;
        switch (i2) {
            case 0:
                albumsPresenter.lambda$onLoadFullMenu$2(obj, bundle);
                return;
            default:
                albumsPresenter.lambda$onLoadFullMenu$4(obj, bundle);
                return;
        }
    }
}
