package C4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumsBasePresenter e;

    public /* synthetic */ h(AlbumsBasePresenter albumsBasePresenter, int i2) {
        this.d = i2;
        this.e = albumsBasePresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        AlbumsBasePresenter albumsBasePresenter = this.e;
        switch (i2) {
            case 0:
                albumsBasePresenter.onCoverChanged(obj, bundle);
                return;
            case 1:
                albumsBasePresenter.onLocalDatabaseUpdated(obj, bundle);
                return;
            case 2:
                albumsBasePresenter.onAlbumSyncStatusUpdated(obj, bundle);
                return;
            case 3:
                albumsBasePresenter.enterMoveMode(obj, bundle);
                return;
            case 4:
                albumsBasePresenter.exitMoveMode(obj, bundle);
                return;
            default:
                albumsBasePresenter.onNewItemCreated(obj, bundle);
                return;
        }
    }
}
