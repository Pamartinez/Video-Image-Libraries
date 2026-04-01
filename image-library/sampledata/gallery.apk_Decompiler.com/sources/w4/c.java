package W4;

import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapFragmentV2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ClusteringMapFragmentV2 e;

    public /* synthetic */ c(ClusteringMapFragmentV2 clusteringMapFragmentV2, int i2) {
        this.d = i2;
        this.e = clusteringMapFragmentV2;
    }

    public final void run() {
        int i2 = this.d;
        ClusteringMapFragmentV2 clusteringMapFragmentV2 = this.e;
        switch (i2) {
            case 0:
                clusteringMapFragmentV2.lambda$updateBehaviorExpandedOffset$2();
                return;
            default:
                clusteringMapFragmentV2.onMarkerTransitionEnd();
                return;
        }
    }
}
