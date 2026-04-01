package W4;

import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapPresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ClusteringMapPresenter e;

    public /* synthetic */ e(ClusteringMapPresenter clusteringMapPresenter, int i2) {
        this.d = i2;
        this.e = clusteringMapPresenter;
    }

    public final void run() {
        int i2 = this.d;
        ClusteringMapPresenter clusteringMapPresenter = this.e;
        switch (i2) {
            case 0:
                clusteringMapPresenter.lambda$onMapMediaDataChanged$1();
                return;
            default:
                clusteringMapPresenter.lambda$onMapMediaDataChanged$0();
                return;
        }
    }
}
