package P3;

import android.os.Bundle;
import com.samsung.android.gallery.app.controller.mtp.SingleMtpImportCmd;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: P3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0408a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SingleMtpImportCmd e;

    public /* synthetic */ C0408a(SingleMtpImportCmd singleMtpImportCmd, int i2) {
        this.d = i2;
        this.e = singleMtpImportCmd;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SingleMtpImportCmd singleMtpImportCmd = this.e;
        switch (i2) {
            case 0:
                singleMtpImportCmd.onOptionSelected(obj, bundle);
                return;
            default:
                singleMtpImportCmd.onRenameSelected(obj, bundle);
                return;
        }
    }
}
