package M4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumPicturesPresenter e;

    public /* synthetic */ h(AlbumPicturesPresenter albumPicturesPresenter, int i2) {
        this.d = i2;
        this.e = albumPicturesPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        AlbumPicturesPresenter albumPicturesPresenter = this.e;
        switch (i2) {
            case 0:
                albumPicturesPresenter.onAlbumItemChanged(obj, bundle);
                return;
            case 1:
                albumPicturesPresenter.onViewChanged(obj, bundle);
                return;
            case 2:
                albumPicturesPresenter.onCoverChanged(obj, bundle);
                return;
            case 3:
                albumPicturesPresenter.lambda$createSubscriberList$14(obj, bundle);
                return;
            case 4:
                albumPicturesPresenter.onCloudServiceChanged(obj, bundle);
                return;
            case 5:
                albumPicturesPresenter.onCloudSyncOnOffChanged(obj, bundle);
                return;
            default:
                albumPicturesPresenter.onMergeAlbumsChanged(obj, bundle);
                return;
        }
    }
}
