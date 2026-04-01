package com.samsung.android.gallery.module.map.manager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.R$layout;
import com.samsung.android.gallery.module.map.manager.MarkerIconManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SmallMapMarkerIconManager extends MarkerIconManager {
    public SmallMapMarkerIconManager(Context context) {
        super(context);
    }

    private View getSmallMapViewPopupLayout(Context context) {
        return View.inflate(context, R$layout.map_view_small_popup_layout, (ViewGroup) null);
    }

    public MarkerIconManager.ViewHolder createViewHolder(Context context, boolean z) {
        if (z) {
            return super.createViewHolder(context, true);
        }
        return new MarkerIconManager.ViewHolder(getSmallMapViewPopupLayout(context), false, true);
    }
}
