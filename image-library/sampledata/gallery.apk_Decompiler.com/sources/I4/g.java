package I4;

import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsViewAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.LiveMotionPageTransformer;
import com.samsung.android.gallery.widget.livemotion.ViewPagerTransformBuilder;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransform;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListViewHolder e;

    public /* synthetic */ g(ListViewHolder listViewHolder, int i2) {
        this.d = i2;
        this.e = listViewHolder;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ListViewHolder listViewHolder = this.e;
        switch (i2) {
            case 0:
                MxAlbumsViewAdapter.lambda$enableAlbumExpiryIcon$1(listViewHolder, (MediaItem) obj);
                return;
            case 1:
                LiveMotionPageTransformer.lambda$transformSwipeInternal$1(listViewHolder, (PageTransform) obj);
                return;
            case 2:
                ViewPagerTransformBuilder.lambda$prepare$0(listViewHolder, (PageTransform) obj);
                return;
            default:
                listViewHolder.restoreThumbnailBorder();
                return;
        }
    }
}
