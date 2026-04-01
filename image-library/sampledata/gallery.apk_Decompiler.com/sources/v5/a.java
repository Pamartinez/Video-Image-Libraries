package v5;

import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResult2Adapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResult2Fragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerAdapter;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Object get() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return Boolean.valueOf(((SearchPicturesFragment) obj).isSearchProcessing());
            case 1:
                return ((SearchClusterResult2Adapter) obj).lambda$setSelectionModeByLongPress$0();
            case 2:
                return ((SearchClusterResult2Fragment) obj).lambda$enterSelectionMode$0();
            case 3:
                return ((SearchClusterResultPresenter) obj).lambda$new$1();
            default:
                return Integer.valueOf(((StoryHighlightViewPagerAdapter) obj).getCurrentItem());
        }
    }
}
