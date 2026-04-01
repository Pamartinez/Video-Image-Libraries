package q4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.GroupShotCheckBoxDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ GroupShotCheckBoxDialog e;

    public /* synthetic */ l(GroupShotCheckBoxDialog groupShotCheckBoxDialog, int i2) {
        this.d = i2;
        this.e = groupShotCheckBoxDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        GroupShotCheckBoxDialog groupShotCheckBoxDialog = this.e;
        switch (i7) {
            case 0:
                groupShotCheckBoxDialog.lambda$createDialog$1(dialogInterface, i2);
                return;
            default:
                groupShotCheckBoxDialog.lambda$createDialog$2(dialogInterface, i2);
                return;
        }
    }
}
