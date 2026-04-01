package n7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel.GroupItemPanelDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: n7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0495b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2674a;
    public final /* synthetic */ GroupItemPanelDelegate b;

    public /* synthetic */ C0495b(GroupItemPanelDelegate groupItemPanelDelegate, int i2) {
        this.f2674a = i2;
        this.b = groupItemPanelDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2674a;
        GroupItemPanelDelegate groupItemPanelDelegate = this.b;
        switch (i2) {
            case 0:
                groupItemPanelDelegate.updateAlphaByDecorView(objArr);
                return;
            case 1:
                groupItemPanelDelegate.updateVisibility(objArr);
                return;
            case 2:
                groupItemPanelDelegate.lambda$setActionInvokeListener$0(objArr);
                return;
            default:
                groupItemPanelDelegate.lambda$new$6(objArr);
                return;
        }
    }
}
