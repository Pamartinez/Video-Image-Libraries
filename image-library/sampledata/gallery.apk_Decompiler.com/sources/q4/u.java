package q4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.SimpleConfirmDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SimpleConfirmDialog e;

    public /* synthetic */ u(SimpleConfirmDialog simpleConfirmDialog, int i2) {
        this.d = i2;
        this.e = simpleConfirmDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        SimpleConfirmDialog simpleConfirmDialog = this.e;
        switch (i7) {
            case 0:
                simpleConfirmDialog.onOption2Clicked(dialogInterface, i2);
                return;
            case 1:
                simpleConfirmDialog.onCancelClicked(dialogInterface, i2);
                return;
            default:
                simpleConfirmDialog.onOption1Clicked(dialogInterface, i2);
                return;
        }
    }
}
