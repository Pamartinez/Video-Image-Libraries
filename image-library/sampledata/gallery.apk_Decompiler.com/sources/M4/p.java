package M4;

import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesViewAdapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumPicturesViewAdapter e;

    public /* synthetic */ p(AlbumPicturesViewAdapter albumPicturesViewAdapter, int i2) {
        this.d = i2;
        this.e = albumPicturesViewAdapter;
    }

    public final void run() {
        int i2 = this.d;
        AlbumPicturesViewAdapter albumPicturesViewAdapter = this.e;
        switch (i2) {
            case 0:
                albumPicturesViewAdapter.lambda$unSelectAll$3();
                return;
            default:
                albumPicturesViewAdapter.notifySelectedItemChanged();
                return;
        }
    }
}
