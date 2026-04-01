package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ DrawerSlideAnimationManager d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Runnable f;

    public /* synthetic */ b(DrawerSlideAnimationManager drawerSlideAnimationManager, boolean z, Runnable runnable) {
        this.d = drawerSlideAnimationManager;
        this.e = z;
        this.f = runnable;
    }

    public final void run() {
        this.d.lambda$completeAnimation$12(this.e, this.f);
    }
}
