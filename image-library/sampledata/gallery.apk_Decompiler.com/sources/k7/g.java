package k7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DecorViewDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DecorViewDelegate e;

    public /* synthetic */ g(DecorViewDelegate decorViewDelegate, int i2) {
        this.d = i2;
        this.e = decorViewDelegate;
    }

    public final void run() {
        int i2 = this.d;
        DecorViewDelegate decorViewDelegate = this.e;
        switch (i2) {
            case 0:
                decorViewDelegate.startSlideUp(new Object[0]);
                return;
            case 1:
                decorViewDelegate.lambda$setActionInvokeListener$3();
                return;
            case 2:
                decorViewDelegate.initToolbar();
                return;
            default:
                decorViewDelegate.replaceToolbar();
                return;
        }
    }
}
