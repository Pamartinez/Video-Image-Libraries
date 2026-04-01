package A7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.dynamicview.DynamicViewPlayerHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DynamicViewPlayerHandler e;

    public /* synthetic */ a(DynamicViewPlayerHandler dynamicViewPlayerHandler, int i2) {
        this.d = i2;
        this.e = dynamicViewPlayerHandler;
    }

    public final void run() {
        int i2 = this.d;
        DynamicViewPlayerHandler dynamicViewPlayerHandler = this.e;
        switch (i2) {
            case 0:
                dynamicViewPlayerHandler.lambda$playVideo$2();
                return;
            case 1:
                dynamicViewPlayerHandler.lambda$onVideoPrepared$3();
                return;
            default:
                dynamicViewPlayerHandler.lambda$onBgmLoaded$1();
                return;
        }
    }
}
