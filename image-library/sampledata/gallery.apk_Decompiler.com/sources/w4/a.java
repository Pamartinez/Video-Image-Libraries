package W4;

import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapFragment;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements MapContainer.OnCameraIdleListener, MapContainer.OnClickListener, MapContainer.OnMarkerClickListener {
    public final /* synthetic */ ClusteringMapFragment d;

    public /* synthetic */ a(ClusteringMapFragment clusteringMapFragment) {
        this.d = clusteringMapFragment;
    }

    public void onClick() {
        this.d.onMapClicked();
    }

    public void onListen() {
        this.d.cameraIdle();
    }

    public void onClick(Object obj) {
        this.d.onMarkerClicked(obj);
    }
}
