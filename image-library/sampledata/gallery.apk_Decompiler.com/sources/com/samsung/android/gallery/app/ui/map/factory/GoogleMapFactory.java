package com.samsung.android.gallery.app.ui.map.factory;

import android.content.Context;
import com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer;
import com.samsung.android.gallery.app.ui.map.google.GoogleMarkerManager;
import com.samsung.android.gallery.app.ui.map.google.GoogleSimpleMarkerManager;
import com.samsung.android.gallery.app.ui.map.picker.google.GoogleMapPickerContainer;
import com.samsung.android.gallery.module.map.abstraction.IMap;
import com.samsung.android.gallery.module.map.abstraction.IMapFactory;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.map.abstraction.MapPickerContainer;
import com.samsung.android.gallery.module.map.abstraction.MarkerManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class GoogleMapFactory implements IMapFactory {
    public MapContainer createMap(Context context) {
        return new GoogleMapContainer(context);
    }

    public MarkerManager createMarkerManager(Context context, IMap<?> iMap) {
        return new GoogleMarkerManager(context, iMap);
    }

    public MapPickerContainer createPickerMap(Context context) {
        return new GoogleMapPickerContainer(context);
    }

    public MarkerManager createSimpleMarkerManager(Context context, IMap<?> iMap) {
        return new GoogleSimpleMarkerManager(context, iMap);
    }

    public /* bridge */ /* synthetic */ int getVersionCode() {
        return super.getVersionCode();
    }
}
