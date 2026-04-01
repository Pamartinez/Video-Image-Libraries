package com.samsung.android.gallery.module.map.abstraction;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMapFactory {
    MapContainer createMap(Context context);

    MarkerManager createMarkerManager(Context context, IMap<?> iMap);

    MapPickerContainer createPickerMap(Context context);

    MarkerManager createSimpleMarkerManager(Context context, IMap<?> iMap);

    int getVersionCode() {
        return 1;
    }
}
