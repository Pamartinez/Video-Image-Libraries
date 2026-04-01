package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import android.view.View;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DrawerSlideAnimationManager e;

    public /* synthetic */ a(int i2, DrawerSlideAnimationManager drawerSlideAnimationManager) {
        this.d = i2;
        this.e = drawerSlideAnimationManager;
    }

    public final void onAnimationEnd(View view) {
        int i2 = this.d;
        DrawerSlideAnimationManager drawerSlideAnimationManager = this.e;
        switch (i2) {
            case 0:
                drawerSlideAnimationManager.resetTranslate(view);
                return;
            case 1:
                drawerSlideAnimationManager.lambda$addItemWidthAnimator$10(view);
                return;
            case 2:
                drawerSlideAnimationManager.lambda$addWidthHeightAnimator$2(view);
                return;
            case 3:
                drawerSlideAnimationManager.resetTranslate(view);
                return;
            case 4:
                drawerSlideAnimationManager.resetTranslate(view);
                return;
            case 5:
                drawerSlideAnimationManager.resetTranslate(view);
                return;
            default:
                drawerSlideAnimationManager.resetTranslate(view);
                return;
        }
    }
}
