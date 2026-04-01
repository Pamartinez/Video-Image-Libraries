package c7;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.map.staticmarker.StaticMarkerSearchMapFragment;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;

/* renamed from: c7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0446b implements MapContainer.OnSnapshotReadyListener, MapContainer.OnClickListener {
    public final /* synthetic */ StaticMarkerSearchMapFragment d;

    public /* synthetic */ C0446b(StaticMarkerSearchMapFragment staticMarkerSearchMapFragment) {
        this.d = staticMarkerSearchMapFragment;
    }

    public void onClick() {
        this.d.onClick();
    }

    public void onSnapshotReady(Bitmap bitmap) {
        this.d.snapshotReady(bitmap);
    }
}
