package com.samsung.android.gallery.app.ui.list.mapclustering;

import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IClusteringMapViewV2 extends IPicturesView {
    String getListLocationKey();

    double getMapZoom();

    boolean hasMap();

    boolean isDataShowing();

    boolean isRecentEntryItem();

    void onClusterZoomChanged();

    void onClustersChanged(Set<ICluster<MapItem>> set);

    void reopenMapData(boolean z);

    boolean supportViewAll();

    void updateVisibleMarkers();
}
