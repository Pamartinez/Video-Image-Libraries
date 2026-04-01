package n7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel.GroupItemPanelDelegate;

/* renamed from: n7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0494a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GroupItemPanelDelegate e;

    public /* synthetic */ C0494a(GroupItemPanelDelegate groupItemPanelDelegate, int i2) {
        this.d = i2;
        this.e = groupItemPanelDelegate;
    }

    public final void run() {
        int i2 = this.d;
        GroupItemPanelDelegate groupItemPanelDelegate = this.e;
        switch (i2) {
            case 0:
                groupItemPanelDelegate.lambda$onConfigurationChanged$2();
                return;
            case 1:
                groupItemPanelDelegate.lambda$onTableModeChanged$3();
                return;
            case 2:
                groupItemPanelDelegate.lambda$showGroupPanelWithAnim$5();
                return;
            case 3:
                groupItemPanelDelegate.updateLayout();
                return;
            default:
                groupItemPanelDelegate.lambda$onViewCreated$1();
                return;
        }
    }
}
