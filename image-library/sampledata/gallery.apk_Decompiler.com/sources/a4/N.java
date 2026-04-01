package A4;

import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailResizer;
import com.samsung.android.gallery.app.ui.list.search.keyword.stories.SearchResultStoriesViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResultViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.StoriesViewAdapter;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterrupter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class N implements ThumbnailInterrupter {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListViewHolder e;
    public final /* synthetic */ ThumbnailRequestHolder f;

    public /* synthetic */ N(ListViewHolder listViewHolder, ThumbnailRequestHolder thumbnailRequestHolder, int i2) {
        this.d = i2;
        this.e = listViewHolder;
        this.f = thumbnailRequestHolder;
    }

    public final boolean isInterrupted() {
        switch (this.d) {
            case 0:
                return BaseListViewAdapter.lambda$requestThumbnail$1(this.e, this.f);
            case 1:
                return ThumbnailResizer.lambda$changeThumbnailSize$0(this.e, this.f);
            case 2:
                return StoriesViewAdapter.lambda$requestThumbnail$1(this.e, this.f);
            case 3:
                return SearchResultStoriesViewAdapter.lambda$requestThumbnail$0(this.e, this.f);
            case 4:
                return ClusterResultViewAdapter.lambda$requestThumbnail$2(this.e, this.f);
            default:
                return ClusterResultViewAdapter.lambda$replaceIfHighQualityRequired$6(this.e, this.f);
        }
    }
}
