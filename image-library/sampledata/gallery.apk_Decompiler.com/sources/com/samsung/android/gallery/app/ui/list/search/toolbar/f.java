package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.animation.ValueAnimator;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ GalleryListView d;

    public /* synthetic */ f(GalleryListView galleryListView) {
        this.d = galleryListView;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        ViewMarginUtils.setBottomPadding(this.d, ((Integer) valueAnimator.getAnimatedValue()).intValue());
    }
}
