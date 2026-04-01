package com.samsung.android.gallery.app.ui.map.factory;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.map.abstraction.IMap;
import com.samsung.android.gallery.module.map.abstraction.IMapFactory;
import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.map.abstraction.MapPickerContainer;
import com.samsung.android.gallery.module.map.abstraction.MarkerManager;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class EmptyMapFactory implements IMapFactory {
    static final IMapMarker I_MAP_MARKER = new DummyMapMarker();

    public MapContainer createMap(Context context) {
        return new DummyMapContainer();
    }

    public MarkerManager createMarkerManager(Context context, IMap<?> iMap) {
        return new MarkerManager(context, iMap) {
            public IMapMarker addMarker(double[] dArr, Bitmap bitmap) {
                return EmptyMapFactory.I_MAP_MARKER;
            }

            public void animateMarker(MarkerWithPosition markerWithPosition, float f, double[] dArr, double[] dArr2, float f5) {
            }
        };
    }

    public MapPickerContainer createPickerMap(Context context) {
        return new DummyMapPickerContainer(context);
    }

    public MarkerManager createSimpleMarkerManager(Context context, IMap<?> iMap) {
        return new MarkerManager(context, iMap) {
            public IMapMarker addMarker(double[] dArr, Bitmap bitmap) {
                return EmptyMapFactory.I_MAP_MARKER;
            }

            public void animateMarker(MarkerWithPosition markerWithPosition, float f, double[] dArr, double[] dArr2, float f5) {
            }
        };
    }

    public /* bridge */ /* synthetic */ int getVersionCode() {
        return super.getVersionCode();
    }
}
