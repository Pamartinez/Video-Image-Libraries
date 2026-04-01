package r6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.RelatedLayoutManager;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.RelatedPage;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements ListViewHolder.OnItemClickListener, RelatedLayoutManager.LayoutInfoSupplier {
    public final /* synthetic */ RelatedPage d;

    public /* synthetic */ h(RelatedPage relatedPage) {
        this.d = relatedPage;
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        this.d.onItemClick(listViewHolder, i2, mediaItem, i7);
    }
}
