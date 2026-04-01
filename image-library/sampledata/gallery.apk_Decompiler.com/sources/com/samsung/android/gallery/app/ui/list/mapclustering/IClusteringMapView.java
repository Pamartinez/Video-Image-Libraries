package com.samsung.android.gallery.app.ui.list.mapclustering;

import com.samsung.android.gallery.app.ui.map.base.IGalleryMapView;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IClusteringMapView extends IGalleryMapView {
    double getMapZoom();

    void onClustersChanged(Set<ICluster<MapItem>> set);

    void retryMarkerClicked();

    void setMapZoomLevel(float f);

    void updateVisibleMarkers();
}
