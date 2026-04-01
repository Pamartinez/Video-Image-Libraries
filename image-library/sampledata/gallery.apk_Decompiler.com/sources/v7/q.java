package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.OverlayViewHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2731a;
    public final /* synthetic */ OverlayViewHandler b;

    public /* synthetic */ q(OverlayViewHandler overlayViewHandler, int i2) {
        this.f2731a = i2;
        this.b = overlayViewHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2731a;
        OverlayViewHandler overlayViewHandler = this.b;
        switch (i2) {
            case 0:
                overlayViewHandler.exitOverlayView(objArr);
                return;
            case 1:
                overlayViewHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            default:
                overlayViewHandler.lambda$addActionInvokeListener$1(objArr);
                return;
        }
    }
}
