package S4;

import com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ VirtualAlbumPicturesFragment e;

    public /* synthetic */ a(VirtualAlbumPicturesFragment virtualAlbumPicturesFragment, int i2) {
        this.d = i2;
        this.e = virtualAlbumPicturesFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        VirtualAlbumPicturesFragment virtualAlbumPicturesFragment = this.e;
        switch (i2) {
            case 0:
                virtualAlbumPicturesFragment.lambda$enableHeaderView$0((GalleryListView) obj);
                return;
            default:
                virtualAlbumPicturesFragment.lambda$refreshEmptyView$3((NoItemView) obj);
                return;
        }
    }
}
