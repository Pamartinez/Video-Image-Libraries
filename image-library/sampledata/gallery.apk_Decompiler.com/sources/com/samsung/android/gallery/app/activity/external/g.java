package com.samsung.android.gallery.app.activity.external;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ QuickViewNavigatorController e;

    public /* synthetic */ g(QuickViewNavigatorController quickViewNavigatorController, int i2) {
        this.d = i2;
        this.e = quickViewNavigatorController;
    }

    public final void run() {
        int i2 = this.d;
        QuickViewNavigatorController quickViewNavigatorController = this.e;
        switch (i2) {
            case 0:
                quickViewNavigatorController.finishActivityOnUiThread();
                return;
            case 1:
                quickViewNavigatorController.lambda$launchUriItemView$1();
                return;
            case 2:
                quickViewNavigatorController.lambda$launchUriItemView$2();
                return;
            default:
                quickViewNavigatorController.lambda$onNavigatorCreated$0();
                return;
        }
    }
}
