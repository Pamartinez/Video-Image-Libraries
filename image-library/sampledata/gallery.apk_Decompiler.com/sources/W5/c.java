package w5;

import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchClusterResultFragment e;

    public /* synthetic */ c(SearchClusterResultFragment searchClusterResultFragment, int i2) {
        this.d = i2;
        this.e = searchClusterResultFragment;
    }

    public final void run() {
        int i2 = this.d;
        SearchClusterResultFragment searchClusterResultFragment = this.e;
        switch (i2) {
            case 0:
                searchClusterResultFragment.startShrinkAnimation();
                return;
            default:
                searchClusterResultFragment.lambda$createSimpleAutoScroller$3();
                return;
        }
    }
}
