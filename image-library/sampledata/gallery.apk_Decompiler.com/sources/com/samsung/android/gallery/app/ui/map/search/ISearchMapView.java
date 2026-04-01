package com.samsung.android.gallery.app.ui.map.search;

import androidx.activity.result.ActivityResultLauncher;
import com.samsung.android.gallery.app.ui.map.base.IGalleryMapView;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISearchMapView extends IGalleryMapView {
    void animateCamera(double[] dArr);

    double getMapZoom();

    MediaData getMediaData();

    ActivityResultLauncher<String[]> getPermissionLauncher();

    boolean hasMap();

    void onClustersChanged(Set<ICluster<MapItem>> set);

    void updateVisibleMarkers();
}
