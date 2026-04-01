package N7;

import com.samsung.android.gallery.app.ui.viewer2.grouppanel.GroupItemPanelContentViewHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2402a;
    public final /* synthetic */ GroupItemPanelContentViewHandler b;

    public /* synthetic */ b(GroupItemPanelContentViewHandler groupItemPanelContentViewHandler, int i2) {
        this.f2402a = i2;
        this.b = groupItemPanelContentViewHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2402a;
        GroupItemPanelContentViewHandler groupItemPanelContentViewHandler = this.b;
        switch (i2) {
            case 0:
                groupItemPanelContentViewHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                groupItemPanelContentViewHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 2:
                groupItemPanelContentViewHandler.lambda$addActionInvokeListener$2(objArr);
                return;
            case 3:
                groupItemPanelContentViewHandler.lambda$addActionInvokeListener$3(objArr);
                return;
            default:
                groupItemPanelContentViewHandler.onTableModeAnimationUpdated(objArr);
                return;
        }
    }
}
