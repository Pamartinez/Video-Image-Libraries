package com.samsung.android.gallery.module.map.abstraction;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.map.model.MapLatLng;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMapMarker {
    String getId();

    void remove();

    void setAlpha(float f);

    void setIcon(Bitmap bitmap);

    void setPosition(MapLatLng mapLatLng);
}
