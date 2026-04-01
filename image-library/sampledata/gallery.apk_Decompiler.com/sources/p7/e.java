package p7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewerMenuDelegate e;

    public /* synthetic */ e(ViewerMenuDelegate viewerMenuDelegate, int i2) {
        this.d = i2;
        this.e = viewerMenuDelegate;
    }

    public final void run() {
        int i2 = this.d;
        ViewerMenuDelegate viewerMenuDelegate = this.e;
        switch (i2) {
            case 0:
                viewerMenuDelegate.lambda$resetAllMenus$14();
                return;
            case 1:
                viewerMenuDelegate.lambda$onEnterTransitionStart$8();
                return;
            case 2:
                viewerMenuDelegate.lambda$runLastExecution$10();
                return;
            case 3:
                viewerMenuDelegate.lambda$onConfigurationChanged$11();
                return;
            default:
                viewerMenuDelegate.invalidateOptionsMenu();
                return;
        }
    }
}
