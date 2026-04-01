package com.samsung.android.gallery.module.map.abstraction;

import android.content.Context;
import com.samsung.android.gallery.module.map.abstraction.IMap;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.model.MapLatLngBounds;
import com.samsung.android.gallery.module.map.model.MapPositionHelper;
import com.samsung.android.gallery.module.map.model.MapVisibleRegion;
import com.samsung.android.gallery.module.map.transition.BaseMarkerManager;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;
import com.samsung.android.gallery.support.utils.Log;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MarkerManager<MAP extends IMap> extends BaseMarkerManager {
    private MapLatLngBounds mLargeVisibleRegion;
    protected MAP mMap;
    private final HashMap<MapVisibleRegion, MapLatLngBounds> mVisibleRegionCache = new HashMap<>();

    public MarkerManager(Context context, MAP map) {
        super(context);
        this.mMap = map;
    }

    public MarkerWithPosition createMarkerWithPosition(IMapMarker iMapMarker, ICluster<MapItem> iCluster) {
        return new MarkerWithPosition(iMapMarker, iCluster);
    }

    public void destroy() {
        super.destroy();
        if (this.mMap != null) {
            this.mMap = null;
        }
    }

    public double getMapZoom() {
        try {
            return this.mMap.getMapZoom();
        } catch (Exception unused) {
            return -1.0d;
        }
    }

    public boolean isVisible(double[] dArr) {
        MapLatLngBounds mapLatLngBounds = this.mLargeVisibleRegion;
        if (mapLatLngBounds == null || !mapLatLngBounds.contains(MapPositionHelper.toLatLng(dArr))) {
            return false;
        }
        return true;
    }

    public String tag() {
        return "MarkerManager";
    }

    public void updateVisibleRegion() {
        MAP map = this.mMap;
        if (map == null) {
            Log.e(this.TAG, "Map is null; fail to update visible region");
            return;
        }
        MapVisibleRegion visibleRegion = map.getVisibleRegion();
        if (visibleRegion != null && this.mVisibleRegionCache.get(visibleRegion) == null) {
            MapLatLngBounds largeVisibleRegion = MapPositionHelper.getLargeVisibleRegion(this.mMap.getVisibleRegion());
            this.mLargeVisibleRegion = largeVisibleRegion;
            this.mVisibleRegionCache.put(visibleRegion, largeVisibleRegion);
        }
    }
}
