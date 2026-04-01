package M4;

import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumsPanePresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumsPanePresenter e;
    public final /* synthetic */ int f;

    public /* synthetic */ r(AlbumsPanePresenter albumsPanePresenter, int i2, int i7) {
        this.d = i7;
        this.e = albumsPanePresenter;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$moveToTargetAlbum$2(this.f);
                return;
            case 1:
                this.e.lambda$moveToTargetAlbum$0(this.f);
                return;
            default:
                this.e.lambda$findAndMoveToNewAlbum$5(this.f);
                return;
        }
    }
}
