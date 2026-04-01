package com.samsung.android.gallery.app.activity.external;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingsViewNavigatorController e;

    public /* synthetic */ k(SharingsViewNavigatorController sharingsViewNavigatorController, int i2) {
        this.d = i2;
        this.e = sharingsViewNavigatorController;
    }

    public final void run() {
        int i2 = this.d;
        SharingsViewNavigatorController sharingsViewNavigatorController = this.e;
        switch (i2) {
            case 0:
                sharingsViewNavigatorController.lambda$onNavigatorCreated$0();
                return;
            default:
                sharingsViewNavigatorController.lambda$launchFamilyAlbumView$1();
                return;
        }
    }
}
