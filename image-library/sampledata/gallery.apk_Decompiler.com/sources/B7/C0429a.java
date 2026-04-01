package b7;

import android.graphics.Bitmap;
import androidx.activity.result.ActivityResultCallback;
import com.samsung.android.gallery.app.ui.map.search.SearchMapFragment;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import java.util.Map;

/* renamed from: b7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0429a implements MapContainer.OnCameraIdleListener, ActivityResultCallback, MapContainer.OnMarkerClickListener, MapContainer.OnClickListener, MapContainer.OnSnapshotReadyListener {
    public final /* synthetic */ SearchMapFragment d;

    public /* synthetic */ C0429a(SearchMapFragment searchMapFragment) {
        this.d = searchMapFragment;
    }

    public void onActivityResult(Object obj) {
        this.d.lambda$registerPermissionLauncher$0((Map) obj);
    }

    public void onClick() {
        this.d.onMapClicked();
    }

    public void onListen() {
        this.d.cameraIdle();
    }

    public void onSnapshotReady(Bitmap bitmap) {
        this.d.lambda$takeSnapShot$3(bitmap);
    }

    public void onClick(Object obj) {
        this.d.onMarkerClicked(obj);
    }
}
