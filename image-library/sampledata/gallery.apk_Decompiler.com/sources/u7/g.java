package U7;

import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterProcessingViewHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RemasterProcessingViewHandler e;

    public /* synthetic */ g(RemasterProcessingViewHandler remasterProcessingViewHandler, int i2) {
        this.d = i2;
        this.e = remasterProcessingViewHandler;
    }

    public final void run() {
        int i2 = this.d;
        RemasterProcessingViewHandler remasterProcessingViewHandler = this.e;
        switch (i2) {
            case 0:
                remasterProcessingViewHandler.lambda$updateEffect$2();
                return;
            default:
                remasterProcessingViewHandler.hideLoadingView();
                return;
        }
    }
}
