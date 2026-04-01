package com.samsung.android.gallery.module.map.model;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MapVisibleRegion {
    private final MapLatLng farLeft;
    private final MapLatLng farRight;
    public final MapLatLngBounds latLngBounds;
    private final MapLatLng nearLeft;
    private final MapLatLng nearRight;

    public MapVisibleRegion(MapLatLng mapLatLng, MapLatLng mapLatLng2, MapLatLng mapLatLng3, MapLatLng mapLatLng4, MapLatLngBounds mapLatLngBounds) {
        this.nearLeft = mapLatLng;
        this.nearRight = mapLatLng2;
        this.farLeft = mapLatLng3;
        this.farRight = mapLatLng4;
        this.latLngBounds = mapLatLngBounds;
    }
}
