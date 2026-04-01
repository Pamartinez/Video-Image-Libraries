package q4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.FileOperationDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileOperationDialog e;

    public /* synthetic */ k(FileOperationDialog fileOperationDialog, int i2) {
        this.d = i2;
        this.e = fileOperationDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        FileOperationDialog fileOperationDialog = this.e;
        switch (i7) {
            case 0:
                fileOperationDialog.onClickNegative(dialogInterface, i2);
                return;
            default:
                fileOperationDialog.onClickNeutral(dialogInterface, i2);
                return;
        }
    }
}
