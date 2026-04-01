package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ DrawerSlideAnimationManager d;

    public /* synthetic */ e(DrawerSlideAnimationManager drawerSlideAnimationManager) {
        this.d = drawerSlideAnimationManager;
    }

    public final void run() {
        this.d.resetItemAnimator();
    }
}
