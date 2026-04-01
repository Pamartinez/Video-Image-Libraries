package com.samsung.android.gallery.module.map.transition.abstraction;

import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.clustering.ICluster;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MarkerWithPosition {
    private ICluster<MapItem> mCluster;
    private final IMapMarker mMarker;

    public MarkerWithPosition(IMapMarker iMapMarker, ICluster<MapItem> iCluster) {
        this.mMarker = iMapMarker;
        this.mCluster = iCluster;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MarkerWithPosition)) {
            return false;
        }
        MarkerWithPosition markerWithPosition = (MarkerWithPosition) obj;
        if (this.mMarker.equals(markerWithPosition.mMarker) || getCluster().equals(markerWithPosition.getCluster())) {
            return true;
        }
        return false;
    }

    public ICluster<MapItem> getCluster() {
        return this.mCluster;
    }

    public String getId() {
        return this.mMarker.getId();
    }

    public IMapMarker getMarker() {
        return this.mMarker;
    }

    public double[] getPosition() {
        return this.mCluster.getPosition();
    }
}
