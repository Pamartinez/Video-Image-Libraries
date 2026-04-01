package I3;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.controller.creature.EditCreatureDialogDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements DialogInterface.OnDismissListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditCreatureDialogDelegate e;

    public /* synthetic */ b(EditCreatureDialogDelegate editCreatureDialogDelegate, int i2) {
        this.d = i2;
        this.e = editCreatureDialogDelegate;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        int i2 = this.d;
        EditCreatureDialogDelegate editCreatureDialogDelegate = this.e;
        switch (i2) {
            case 0:
                editCreatureDialogDelegate.lambda$showUnlinkContactDialog$7(dialogInterface);
                return;
            case 1:
                editCreatureDialogDelegate.lambda$showTagAnotherCreatureNameDialog$1(dialogInterface);
                return;
            default:
                editCreatureDialogDelegate.lambda$showMergeWithLinkedContactDialog$3(dialogInterface);
                return;
        }
    }
}
