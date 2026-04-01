package com.samsung.android.gallery.app.ui.map.google;

import K1.e;
import android.content.Context;
import com.samsung.android.gallery.module.map.abstraction.IMap;
import com.samsung.android.gallery.module.map.manager.IMarkerIconManager;
import com.samsung.android.gallery.module.map.manager.SimpleMarkerIconManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GoogleSimpleMarkerManager<MAP extends IMap<e>> extends GoogleMarkerManager<MAP> {
    public GoogleSimpleMarkerManager(Context context, MAP map) {
        super(context, map);
    }

    public IMarkerIconManager createMarkerIconManager(Context context) {
        return new SimpleMarkerIconManager();
    }

    public String tag() {
        return "GoogleSimpleMarkerManager";
    }
}
