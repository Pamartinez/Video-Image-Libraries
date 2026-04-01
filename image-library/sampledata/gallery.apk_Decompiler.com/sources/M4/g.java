package M4;

import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumPicturesFragment e;
    public final /* synthetic */ String f;

    public /* synthetic */ g(AlbumPicturesFragment albumPicturesFragment, String str, int i2) {
        this.d = i2;
        this.e = albumPicturesFragment;
        this.f = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onChangeAlbum$30(this.f);
                return;
            default:
                this.e.lambda$changeAlbum$35(this.f);
                return;
        }
    }
}
