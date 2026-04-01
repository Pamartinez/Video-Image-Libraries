package q4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.dialog.SimpleProgressDialog;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class v implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SimpleProgressDialog e;

    public /* synthetic */ v(SimpleProgressDialog simpleProgressDialog, int i2) {
        this.d = i2;
        this.e = simpleProgressDialog;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SimpleProgressDialog simpleProgressDialog = this.e;
        switch (i2) {
            case 0:
                simpleProgressDialog.lambda$new$0(obj, bundle);
                return;
            default:
                simpleProgressDialog.lambda$new$1(obj, bundle);
                return;
        }
    }
}
