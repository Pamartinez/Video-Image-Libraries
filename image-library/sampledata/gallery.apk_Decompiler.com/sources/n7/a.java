package N7;

import com.samsung.android.gallery.app.ui.viewer2.grouppanel.GroupItemPanelContentViewHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GroupItemPanelContentViewHandler e;

    public /* synthetic */ a(GroupItemPanelContentViewHandler groupItemPanelContentViewHandler, int i2) {
        this.d = i2;
        this.e = groupItemPanelContentViewHandler;
    }

    public final void run() {
        int i2 = this.d;
        GroupItemPanelContentViewHandler groupItemPanelContentViewHandler = this.e;
        switch (i2) {
            case 0:
                groupItemPanelContentViewHandler.setPhotoViewCenterCrop();
                return;
            case 1:
                groupItemPanelContentViewHandler.setVideoViewCenterCrop();
                return;
            case 2:
                groupItemPanelContentViewHandler.lambda$updateLayout$4();
                return;
            default:
                groupItemPanelContentViewHandler.updateLayout();
                return;
        }
    }
}
