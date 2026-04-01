package V6;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.map.MapSnapshotPublisher;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.support.utils.SystemEnvironment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements SystemEnvironment.EnvironmentChangeListener, MapContainer.OnSnapshotReadyListener {
    public final /* synthetic */ MapSnapshotPublisher d;

    public /* synthetic */ b(MapSnapshotPublisher mapSnapshotPublisher) {
        this.d = mapSnapshotPublisher;
    }

    public void onEnvironmentChange(Context context) {
        this.d.onLocaleChanged(context);
    }

    public void onSnapshotReady(Bitmap bitmap) {
        this.d.onSnapshotReady(bitmap);
    }
}
