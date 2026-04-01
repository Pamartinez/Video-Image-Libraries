package n7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel.GroupPanelSelectionMenuDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2675a;
    public final /* synthetic */ GroupPanelSelectionMenuDelegate b;

    public /* synthetic */ c(GroupPanelSelectionMenuDelegate groupPanelSelectionMenuDelegate, int i2) {
        this.f2675a = i2;
        this.b = groupPanelSelectionMenuDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2675a;
        GroupPanelSelectionMenuDelegate groupPanelSelectionMenuDelegate = this.b;
        switch (i2) {
            case 0:
                groupPanelSelectionMenuDelegate.onSelectModeChanged(objArr);
                return;
            case 1:
                groupPanelSelectionMenuDelegate.listItemSelected(objArr);
                return;
            case 2:
                groupPanelSelectionMenuDelegate.allItemSelected(objArr);
                return;
            case 3:
                groupPanelSelectionMenuDelegate.onPrepareShareSheet(objArr);
                return;
            default:
                groupPanelSelectionMenuDelegate.onSecondaryClicked(objArr);
                return;
        }
    }
}
