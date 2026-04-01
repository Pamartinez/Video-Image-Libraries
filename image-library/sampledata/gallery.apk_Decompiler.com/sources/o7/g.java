package o7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.input.ViewerTouchPadDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2691a;
    public final /* synthetic */ ViewerTouchPadDelegate b;

    public /* synthetic */ g(ViewerTouchPadDelegate viewerTouchPadDelegate, int i2) {
        this.f2691a = i2;
        this.b = viewerTouchPadDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2691a;
        ViewerTouchPadDelegate viewerTouchPadDelegate = this.b;
        switch (i2) {
            case 0:
                viewerTouchPadDelegate.lambda$setActionInvokeListener$0(objArr);
                return;
            default:
                viewerTouchPadDelegate.lambda$setActionInvokeListener$1(objArr);
                return;
        }
    }
}
