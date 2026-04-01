package q4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.ShowLowStorageDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ShowLowStorageDialog e;

    public /* synthetic */ t(ShowLowStorageDialog showLowStorageDialog, int i2) {
        this.d = i2;
        this.e = showLowStorageDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        ShowLowStorageDialog showLowStorageDialog = this.e;
        switch (i7) {
            case 0:
                showLowStorageDialog.dismissDialog(dialogInterface, i2);
                return;
            case 1:
                showLowStorageDialog.startMyFilesStorageAnalysis(dialogInterface, i2);
                return;
            default:
                showLowStorageDialog.startMyFiles(dialogInterface, i2);
                return;
        }
    }
}
