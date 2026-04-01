package k7;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.MirroringDelegate;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MirroringDelegate e;

    public /* synthetic */ o(MirroringDelegate mirroringDelegate, int i2) {
        this.d = i2;
        this.e = mirroringDelegate;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        MirroringDelegate mirroringDelegate = this.e;
        switch (i2) {
            case 0:
                mirroringDelegate.onExecuteCurrentShotMode(obj, bundle);
                return;
            case 1:
                mirroringDelegate.onShow(obj, bundle);
                return;
            case 2:
                mirroringDelegate.onHide(obj, bundle);
                return;
            case 3:
                mirroringDelegate.updateDMRButton(obj, bundle);
                return;
            case 4:
                mirroringDelegate.updateSmartViewButton(obj, bundle);
                return;
            case 5:
                mirroringDelegate.onDisplayOccupied(obj, bundle);
                return;
            default:
                mirroringDelegate.onDisplayReleased(obj, bundle);
                return;
        }
    }
}
