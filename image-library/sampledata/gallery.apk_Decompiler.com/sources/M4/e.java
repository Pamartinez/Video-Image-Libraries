package M4;

import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesViewAdapter;
import com.samsung.android.gallery.widget.listview.GallerySwipeView;
import com.samsung.android.gallery.widget.listview.noitem.GalleryListNoItemView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumPicturesFragment e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ e(AlbumPicturesFragment albumPicturesFragment, boolean z, int i2) {
        this.d = i2;
        this.e = albumPicturesFragment;
        this.f = z;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$setEmptyViewListener$8(this.f, (GalleryListNoItemView) obj);
                return;
            case 1:
                this.e.lambda$setListeners$6(this.f, (GallerySwipeView) obj);
                return;
            default:
                this.e.lambda$updateAdapter$32(this.f, (AlbumPicturesViewAdapter) obj);
                return;
        }
    }
}
