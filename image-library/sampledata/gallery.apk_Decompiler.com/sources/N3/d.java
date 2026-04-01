package N3;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.controller.externals.StartRemoteServerCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ StartRemoteServerCmd e;

    public /* synthetic */ d(StartRemoteServerCmd startRemoteServerCmd, int i2) {
        this.d = i2;
        this.e = startRemoteServerCmd;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        StartRemoteServerCmd startRemoteServerCmd = this.e;
        switch (i7) {
            case 0:
                startRemoteServerCmd.lambda$onExecute$2(dialogInterface, i2);
                return;
            default:
                startRemoteServerCmd.lambda$onExecute$4(dialogInterface, i2);
                return;
        }
    }
}
