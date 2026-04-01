package com.samsung.android.gallery.module.map.transition.abstraction;

import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MarkerCollection {
    private final Set<ICluster<MapItem>> mClusters = Collections.newSetFromMap(new ConcurrentHashMap());
    private final Map<String, ICluster<MapItem>> mMarkerToCluster = new ConcurrentHashMap();
    private final Set<MarkerWithPosition> mMarkers = Collections.newSetFromMap(new ConcurrentHashMap());

    public void addMarker(MarkerWithPosition markerWithPosition, ICluster<MapItem> iCluster) {
        this.mClusters.add(iCluster);
        this.mMarkers.add(markerWithPosition);
        this.mMarkerToCluster.put(markerWithPosition.getId(), iCluster);
    }

    public void clear() {
        this.mClusters.clear();
        this.mMarkers.clear();
        this.mMarkerToCluster.clear();
    }

    public boolean containsPosition(double[] dArr) {
        for (ICluster<MapItem> position : this.mClusters) {
            if (position.getPosition() == dArr) {
                return true;
            }
        }
        return false;
    }

    public ICluster<MapItem> findClusterByMarker(IMapMarker iMapMarker) {
        return this.mMarkerToCluster.get(iMapMarker.getId());
    }

    public Set<ICluster<MapItem>> getAllClusters() {
        return this.mClusters;
    }

    public Set<MarkerWithPosition> getAllMarkers() {
        return this.mMarkers;
    }

    public void removeMarker(ICluster<MapItem> iCluster, MarkerWithPosition markerWithPosition) {
        this.mClusters.remove(iCluster);
        this.mMarkers.remove(markerWithPosition);
        this.mMarkerToCluster.remove(markerWithPosition.getId());
    }
}
