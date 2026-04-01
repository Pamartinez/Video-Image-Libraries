package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ActionModeHandler;

/* renamed from: v7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0529b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ActionModeHandler e;

    public /* synthetic */ C0529b(ActionModeHandler actionModeHandler, int i2) {
        this.d = i2;
        this.e = actionModeHandler;
    }

    public final void run() {
        int i2 = this.d;
        ActionModeHandler actionModeHandler = this.e;
        switch (i2) {
            case 0:
                actionModeHandler.lambda$onConfigurationChanged$1();
                return;
            default:
                actionModeHandler.onFinishActionMode(new Object[0]);
                return;
        }
    }
}
