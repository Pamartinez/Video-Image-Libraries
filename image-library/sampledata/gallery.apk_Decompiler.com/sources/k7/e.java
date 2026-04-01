package k7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.BottomSheetDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2658a;
    public final /* synthetic */ BottomSheetDelegate b;

    public /* synthetic */ e(BottomSheetDelegate bottomSheetDelegate, int i2) {
        this.f2658a = i2;
        this.b = bottomSheetDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2658a;
        BottomSheetDelegate bottomSheetDelegate = this.b;
        switch (i2) {
            case 0:
                bottomSheetDelegate.onBottomSheetStateChanged(objArr);
                return;
            case 1:
                bottomSheetDelegate.onDetailsSlide(objArr);
                return;
            case 2:
                bottomSheetDelegate.onOverlayViewStateChanged(objArr);
                return;
            default:
                bottomSheetDelegate.onSingleTakenSelectModeChanged(objArr);
                return;
        }
    }
}
