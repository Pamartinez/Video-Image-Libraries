package W4;

import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.list.mapclustering.IClusteringMapView;
import com.samsung.android.gallery.app.ui.list.mapclustering.IClusteringMapViewV2;
import com.samsung.android.gallery.app.ui.map.clusteringmaker.IMultiMarkerMapView;
import com.samsung.android.gallery.app.ui.map.search.ISearchMapView;
import com.samsung.android.gallery.module.map.clustering.IClusteringListener;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements IClusteringListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2464a;
    public final /* synthetic */ IMvpBaseView b;

    public /* synthetic */ f(IMvpBaseView iMvpBaseView, int i2) {
        this.f2464a = i2;
        this.b = iMvpBaseView;
    }

    public final void onClustersChanged(Set set) {
        int i2 = this.f2464a;
        IMvpBaseView iMvpBaseView = this.b;
        switch (i2) {
            case 0:
                ((IClusteringMapView) iMvpBaseView).onClustersChanged(set);
                return;
            case 1:
                ((IClusteringMapViewV2) iMvpBaseView).onClustersChanged(set);
                return;
            case 2:
                ((IMultiMarkerMapView) iMvpBaseView).onClustersChanged(set);
                return;
            default:
                ((ISearchMapView) iMvpBaseView).onClustersChanged(set);
                return;
        }
    }
}
