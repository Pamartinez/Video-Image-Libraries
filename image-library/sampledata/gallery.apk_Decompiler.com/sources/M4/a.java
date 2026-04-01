package M4;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumsPaneFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.header.AlbumPicturesHeaderView;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.appbar.CustomCover;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.noitem.GalleryListNoItemView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumPicturesFragment e;

    public /* synthetic */ a(AlbumPicturesFragment albumPicturesFragment, int i2) {
        this.d = i2;
        this.e = albumPicturesFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        AlbumPicturesFragment albumPicturesFragment = this.e;
        switch (i2) {
            case 0:
                albumPicturesFragment.lambda$showNoItemViewAnimation$18((GalleryListNoItemView) obj);
                return;
            case 1:
                albumPicturesFragment.lambda$handleOrientationChange$23((AlbumPicturesHeaderView) obj);
                return;
            case 2:
                albumPicturesFragment.lambda$resetSlideAnimation$14((GalleryListView) obj);
                return;
            case 3:
                albumPicturesFragment.lambda$resetSlideAnimation$15((GalleryListNoItemView) obj);
                return;
            case 4:
                albumPicturesFragment.lambda$resetSlideAnimation$16((GalleryListView) obj);
                return;
            case 5:
                albumPicturesFragment.lambda$refreshEmptyView$4((NoItemView) obj);
                return;
            case 6:
                albumPicturesFragment.lambda$initializeEmptyView$2((GalleryListView) obj);
                return;
            case 7:
                albumPicturesFragment.lambda$initAlbumListPosition$26((View) obj);
                return;
            case 8:
                albumPicturesFragment.lambda$updateFloatingToolbarLayout$45((GalleryToolbar) obj);
                return;
            case 9:
                albumPicturesFragment.lambda$onResume$19((CustomCover) obj);
                return;
            default:
                albumPicturesFragment.lambda$setSplitModeToAlbumPane$41((AlbumsPaneFragment) obj);
                return;
        }
    }
}
