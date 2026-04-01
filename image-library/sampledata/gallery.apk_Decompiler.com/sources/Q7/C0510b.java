package q7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ViewPositionDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: q7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0510b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2699a;
    public final /* synthetic */ ViewPositionDelegate b;

    public /* synthetic */ C0510b(ViewPositionDelegate viewPositionDelegate, int i2) {
        this.f2699a = i2;
        this.b = viewPositionDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2699a;
        ViewPositionDelegate viewPositionDelegate = this.b;
        switch (i2) {
            case 0:
                viewPositionDelegate.scrollTo(objArr);
                return;
            case 1:
                viewPositionDelegate.deleteScroll(objArr);
                return;
            case 2:
                viewPositionDelegate.scrollByDirection(objArr);
                return;
            case 3:
                viewPositionDelegate.setViewPagerPos(objArr);
                return;
            case 4:
                viewPositionDelegate.onRequestFlingMove(objArr);
                return;
            default:
                viewPositionDelegate.lambda$setActionInvokeListener$0(objArr);
                return;
        }
    }
}
