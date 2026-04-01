package x4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.permission.PermissionRationaleDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ PermissionRationaleDialog e;

    public /* synthetic */ a(PermissionRationaleDialog permissionRationaleDialog, int i2) {
        this.d = i2;
        this.e = permissionRationaleDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        PermissionRationaleDialog permissionRationaleDialog = this.e;
        switch (i7) {
            case 0:
                permissionRationaleDialog.onClickNegative(dialogInterface, i2);
                return;
            default:
                permissionRationaleDialog.onClickPositive(dialogInterface, i2);
                return;
        }
    }
}
