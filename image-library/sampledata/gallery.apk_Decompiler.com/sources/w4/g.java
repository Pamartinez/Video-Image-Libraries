package W4;

import com.samsung.android.gallery.app.ui.list.mapclustering.FilterableClusteringMapPresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FilterableClusteringMapPresenter e;

    public /* synthetic */ g(FilterableClusteringMapPresenter filterableClusteringMapPresenter, int i2) {
        this.d = i2;
        this.e = filterableClusteringMapPresenter;
    }

    public final void run() {
        int i2 = this.d;
        FilterableClusteringMapPresenter filterableClusteringMapPresenter = this.e;
        switch (i2) {
            case 0:
                filterableClusteringMapPresenter.lambda$onFilteredMediaDataChanged$1();
                return;
            default:
                filterableClusteringMapPresenter.lambda$onMapReady$0();
                return;
        }
    }
}
