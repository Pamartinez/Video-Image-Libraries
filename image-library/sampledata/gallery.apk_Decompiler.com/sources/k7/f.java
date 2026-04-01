package k7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DecorViewDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2659a;
    public final /* synthetic */ DecorViewDelegate b;

    public /* synthetic */ f(DecorViewDelegate decorViewDelegate, int i2) {
        this.f2659a = i2;
        this.b = decorViewDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2659a;
        DecorViewDelegate decorViewDelegate = this.b;
        switch (i2) {
            case 0:
                decorViewDelegate.lambda$setActionInvokeListener$4(objArr);
                return;
            case 1:
                decorViewDelegate.onDetailsSlide(objArr);
                return;
            case 2:
                decorViewDelegate.onOverlayViewStateChanged(objArr);
                return;
            case 3:
                decorViewDelegate.onBottomSheetStateChanged(objArr);
                return;
            case 4:
                decorViewDelegate.onToggleDecorView(objArr);
                return;
            case 5:
                decorViewDelegate.setToolbarTitle(objArr);
                return;
            case 6:
                decorViewDelegate.lambda$setActionInvokeListener$0(objArr);
                return;
            case 7:
                decorViewDelegate.lambda$setActionInvokeListener$2(objArr);
                return;
            default:
                decorViewDelegate.captureDecorView(objArr);
                return;
        }
    }
}
