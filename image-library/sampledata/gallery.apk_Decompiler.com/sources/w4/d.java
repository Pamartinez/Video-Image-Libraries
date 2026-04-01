package W4;

import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapFragmentV2;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements MapContainer.OnGalleryMapReadyListener, MapContainer.OnCameraIdleListener, MapContainer.OnMarkerClickListener, MapContainer.OnClickListener {
    public final /* synthetic */ ClusteringMapFragmentV2 d;

    public /* synthetic */ d(ClusteringMapFragmentV2 clusteringMapFragmentV2) {
        this.d = clusteringMapFragmentV2;
    }

    public void onClick() {
        this.d.onMapClicked();
    }

    public void onListen() {
        this.d.cameraIdle();
    }

    public void onMapReady(Object obj) {
        this.d.onMapReady(obj);
    }

    public void onClick(Object obj) {
        this.d.onMarkerClicked(obj);
    }
}
