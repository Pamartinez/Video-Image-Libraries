package com.samsung.android.gallery.module.map.abstraction;

import android.content.Context;
import com.samsung.android.gallery.module.map.model.MapVisibleRegion;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMap<MAP> {
    MAP getMapInstance();

    double getMapZoom();

    MapVisibleRegion getVisibleRegion();

    void handleDensityChanged(Context context);

    boolean hasMap();

    void setZoomLevel(float f);
}
