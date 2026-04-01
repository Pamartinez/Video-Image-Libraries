package B4;

import com.samsung.android.gallery.app.ui.list.albums.AlbumsPresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumsPresenter e;

    public /* synthetic */ e(AlbumsPresenter albumsPresenter, int i2) {
        this.d = i2;
        this.e = albumsPresenter;
    }

    public final void run() {
        int i2 = this.d;
        AlbumsPresenter albumsPresenter = this.e;
        switch (i2) {
            case 0:
                albumsPresenter.lambda$onLoadFullMenu$1();
                return;
            case 1:
                albumsPresenter.lambda$handleEvent$6();
                return;
            case 2:
                albumsPresenter.forceReloadData();
                return;
            default:
                albumsPresenter.lambda$onLoadFullMenu$3();
                return;
        }
    }
}
