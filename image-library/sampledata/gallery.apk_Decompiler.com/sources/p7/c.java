package p7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2696a;
    public final /* synthetic */ ViewerMenuDelegate b;

    public /* synthetic */ c(ViewerMenuDelegate viewerMenuDelegate, int i2) {
        this.f2696a = i2;
        this.b = viewerMenuDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2696a;
        ViewerMenuDelegate viewerMenuDelegate = this.b;
        switch (i2) {
            case 0:
                viewerMenuDelegate.lambda$setActionInvokeListener$5(objArr);
                return;
            case 1:
                viewerMenuDelegate.lambda$setActionInvokeListener$6(objArr);
                return;
            case 2:
                viewerMenuDelegate.updateGroupPanelMenu(objArr);
                return;
            case 3:
                viewerMenuDelegate.updateMenuDim(objArr);
                return;
            case 4:
                viewerMenuDelegate.onSecondaryClicked(objArr);
                return;
            case 5:
                viewerMenuDelegate.lambda$setActionInvokeListener$2(objArr);
                return;
            case 6:
                viewerMenuDelegate.lambda$setActionInvokeListener$3(objArr);
                return;
            default:
                viewerMenuDelegate.lambda$setActionInvokeListener$4(objArr);
                return;
        }
    }
}
