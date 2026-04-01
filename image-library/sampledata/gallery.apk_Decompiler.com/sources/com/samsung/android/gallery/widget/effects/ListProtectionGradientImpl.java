package com.samsung.android.gallery.widget.effects;

import android.app.Activity;
import android.view.View;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListProtectionGradientImpl implements ListProtectionGradient {
    private final BooleanSupplier mVisibleCondition;

    public ListProtectionGradientImpl(BooleanSupplier booleanSupplier) {
        this.mVisibleCondition = booleanSupplier;
    }

    public void update(Activity activity, View view, GalleryRecyclerView galleryRecyclerView) {
        View findViewById;
        GalleryRecyclerView.FadingEdgeDirection fadingEdgeDirection;
        if (activity != null && view != null && galleryRecyclerView != null && (findViewById = view.findViewById(R$id.status_bar_protection_gradient)) != null) {
            boolean asBoolean = this.mVisibleCondition.getAsBoolean();
            ViewUtils.setVisibleOrGone(findViewById, asBoolean);
            if (asBoolean) {
                SystemUi.setDarkStatusBar(activity);
            } else {
                SystemUi.setNormalStatusBar(activity);
            }
            if (asBoolean) {
                fadingEdgeDirection = GalleryRecyclerView.FadingEdgeDirection.BOTTOM;
            } else {
                fadingEdgeDirection = GalleryRecyclerView.FadingEdgeDirection.ALL;
            }
            galleryRecyclerView.setFadingEdge(fadingEdgeDirection);
        }
    }
}
