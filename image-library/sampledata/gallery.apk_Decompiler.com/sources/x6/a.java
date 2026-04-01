package X6;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.map.clusteringmaker.MultiMarkerMapFragment;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements MapContainer.OnCameraIdleListener, MapContainer.OnClickListener, MapContainer.OnMarkerClickListener, MapContainer.OnSnapshotReadyListener {
    public final /* synthetic */ MultiMarkerMapFragment d;

    public /* synthetic */ a(MultiMarkerMapFragment multiMarkerMapFragment) {
        this.d = multiMarkerMapFragment;
    }

    public void onClick() {
        this.d.onMapClicked();
    }

    public void onListen() {
        this.d.cameraIdle();
    }

    public void onSnapshotReady(Bitmap bitmap) {
        this.d.snapshotReady(bitmap);
    }

    public void onClick(Object obj) {
        this.d.onMarkerClicked(obj);
    }
}
