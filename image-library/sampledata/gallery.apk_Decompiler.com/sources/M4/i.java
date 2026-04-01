package M4;

import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumPicturesPresenter e;

    public /* synthetic */ i(AlbumPicturesPresenter albumPicturesPresenter, int i2) {
        this.d = i2;
        this.e = albumPicturesPresenter;
    }

    public final void run() {
        int i2 = this.d;
        AlbumPicturesPresenter albumPicturesPresenter = this.e;
        switch (i2) {
            case 0:
                albumPicturesPresenter.lambda$updateCoverOnAppbar$16();
                return;
            case 1:
                albumPicturesPresenter.lambda$onAlbumItemChanged$15();
                return;
            case 2:
                albumPicturesPresenter.moveToNextAlbum();
                return;
            case 3:
                albumPicturesPresenter.finishFragment();
                return;
            case 4:
                albumPicturesPresenter.lambda$onTipCardCloseClicked$11();
                return;
            default:
                albumPicturesPresenter.lambda$handleEvent$23();
                return;
        }
    }
}
