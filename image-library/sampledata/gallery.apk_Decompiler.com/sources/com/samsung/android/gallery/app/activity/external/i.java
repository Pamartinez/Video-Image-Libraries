package com.samsung.android.gallery.app.activity.external;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ QuickViewNavigatorController d;
    public final /* synthetic */ boolean e;

    public /* synthetic */ i(QuickViewNavigatorController quickViewNavigatorController, boolean z) {
        this.d = quickViewNavigatorController;
        this.e = z;
    }

    public final void run() {
        this.d.lambda$finishActivityOnUiThread$5(this.e);
    }
}
