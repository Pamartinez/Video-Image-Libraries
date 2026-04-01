package y4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.dialog.switchable.SwitchableDialog;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SwitchableDialog e;

    public /* synthetic */ a(SwitchableDialog switchableDialog, int i2) {
        this.d = i2;
        this.e = switchableDialog;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SwitchableDialog switchableDialog = this.e;
        switch (i2) {
            case 0:
                switchableDialog.lambda$new$0(obj, bundle);
                return;
            case 1:
                switchableDialog.lambda$new$1(obj, bundle);
                return;
            default:
                switchableDialog.lambda$new$2(obj, bundle);
                return;
        }
    }
}
