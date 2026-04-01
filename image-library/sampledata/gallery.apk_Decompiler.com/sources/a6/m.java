package a6;

import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.ProcessingViewManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ProcessingViewManager e;

    public /* synthetic */ m(ProcessingViewManager processingViewManager, int i2) {
        this.d = i2;
        this.e = processingViewManager;
    }

    public final void run() {
        int i2 = this.d;
        ProcessingViewManager processingViewManager = this.e;
        switch (i2) {
            case 0:
                processingViewManager.lambda$hideGlowView$2();
                return;
            default:
                processingViewManager.hide();
                return;
        }
    }
}
