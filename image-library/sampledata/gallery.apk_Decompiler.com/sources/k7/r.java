package k7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.SystemBarDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2661a;
    public final /* synthetic */ SystemBarDelegate b;

    public /* synthetic */ r(SystemBarDelegate systemBarDelegate, int i2) {
        this.f2661a = i2;
        this.b = systemBarDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2661a;
        SystemBarDelegate systemBarDelegate = this.b;
        switch (i2) {
            case 0:
                systemBarDelegate.onOverlayViewStateChanged(objArr);
                return;
            case 1:
                systemBarDelegate.onBottomSheetStateChanged(objArr);
                return;
            default:
                systemBarDelegate.onRequestSetScreenModeOnPageSelected(objArr);
                return;
        }
    }
}
