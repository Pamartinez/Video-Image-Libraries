package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import android.view.View;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements WidthAnimator.WidthAnimationCallback, PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ int d;

    public /* synthetic */ c(int i2) {
        this.d = i2;
    }

    public boolean isWidthAnimationNeeded(View view, int i2) {
        switch (this.d) {
            case 0:
                return DrawerSlideAnimationManager.lambda$addItemWidthAnimator$6(view, i2);
            case 2:
                return DrawerSlideAnimationManager.lambda$addItemWidthAnimator$8(view, i2);
            case 4:
                return DrawerSlideAnimationManager.lambda$addWidthHeightAnimator$1(view, i2);
            case 5:
                return DrawerSlideAnimationManager.lambda$addMatchedDividerAnimator$13(view, i2);
            default:
                return DrawerSlideAnimationManager.lambda$addMatchedDividerAnimator$15(view, i2);
        }
    }

    public void onAnimationEnd(View view) {
        switch (this.d) {
            case 1:
                ViewUtils.setWidth(view, -1);
                return;
            case 3:
                ViewUtils.setWidth(view, -1);
                return;
            case 6:
                ViewUtils.setWidth(view, -1);
                return;
            default:
                ViewUtils.setWidth(view, -1);
                return;
        }
    }
}
