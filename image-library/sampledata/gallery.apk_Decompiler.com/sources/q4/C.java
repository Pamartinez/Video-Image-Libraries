package q4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.TurnOnTrashDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ TurnOnTrashDialog e;

    public /* synthetic */ C(TurnOnTrashDialog turnOnTrashDialog, int i2) {
        this.d = i2;
        this.e = turnOnTrashDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        TurnOnTrashDialog turnOnTrashDialog = this.e;
        switch (i7) {
            case 0:
                turnOnTrashDialog.onTurnOnTrashClicked(dialogInterface, i2);
                return;
            default:
                turnOnTrashDialog.onCancelClicked(dialogInterface, i2);
                return;
        }
    }
}
