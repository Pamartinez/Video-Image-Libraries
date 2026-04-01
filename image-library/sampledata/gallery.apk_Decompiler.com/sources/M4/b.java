package M4;

import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumPicturesFragment e;

    public /* synthetic */ b(AlbumPicturesFragment albumPicturesFragment, int i2) {
        this.d = i2;
        this.e = albumPicturesFragment;
    }

    public final void run() {
        int i2 = this.d;
        AlbumPicturesFragment albumPicturesFragment = this.e;
        switch (i2) {
            case 0:
                albumPicturesFragment.initAlbumListPosition();
                return;
            case 1:
                albumPicturesFragment.createViewPoolExtras();
                return;
            case 2:
                albumPicturesFragment.lambda$onConfigurationChanged$24();
                return;
            default:
                albumPicturesFragment.lambda$showSplitAnimation$17();
                return;
        }
    }
}
