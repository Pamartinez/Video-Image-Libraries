package q4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.RequestGroupSharingEnableDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ RequestGroupSharingEnableDialog e;

    public /* synthetic */ p(RequestGroupSharingEnableDialog requestGroupSharingEnableDialog, int i2) {
        this.d = i2;
        this.e = requestGroupSharingEnableDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        RequestGroupSharingEnableDialog requestGroupSharingEnableDialog = this.e;
        switch (i7) {
            case 0:
                requestGroupSharingEnableDialog.startGroupSharingSetting(dialogInterface, i2);
                return;
            default:
                requestGroupSharingEnableDialog.dismissDialog(dialogInterface, i2);
                return;
        }
    }
}
