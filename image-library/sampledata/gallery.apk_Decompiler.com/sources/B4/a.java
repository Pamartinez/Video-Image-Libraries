package B4;

import com.samsung.android.gallery.app.ui.list.albums.AlbumsFooterView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumsFooterView e;

    public /* synthetic */ a(AlbumsFooterView albumsFooterView, int i2) {
        this.d = i2;
        this.e = albumsFooterView;
    }

    public final void run() {
        int i2 = this.d;
        AlbumsFooterView albumsFooterView = this.e;
        switch (i2) {
            case 0:
                albumsFooterView.lambda$new$1();
                return;
            case 1:
                albumsFooterView.lambda$onClickCreateGroup$3();
                return;
            default:
                albumsFooterView.lambda$onClickHideAlbum$2();
                return;
        }
    }
}
