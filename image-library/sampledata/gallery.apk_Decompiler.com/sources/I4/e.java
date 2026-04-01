package I4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsPresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MxAlbumsPresenter e;

    public /* synthetic */ e(MxAlbumsPresenter mxAlbumsPresenter, int i2) {
        this.d = i2;
        this.e = mxAlbumsPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        MxAlbumsPresenter mxAlbumsPresenter = this.e;
        switch (i2) {
            case 0:
                mxAlbumsPresenter.onEssentialAlbumsChanged(obj, bundle);
                return;
            default:
                mxAlbumsPresenter.onSharedAlbumsBadgeUpdated(obj, bundle);
                return;
        }
    }
}
