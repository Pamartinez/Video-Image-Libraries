package q4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.UntagPeopleDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class D implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ UntagPeopleDialog e;

    public /* synthetic */ D(UntagPeopleDialog untagPeopleDialog, int i2) {
        this.d = i2;
        this.e = untagPeopleDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        UntagPeopleDialog untagPeopleDialog = this.e;
        switch (i7) {
            case 0:
                untagPeopleDialog.onClickNegative(dialogInterface, i2);
                return;
            default:
                untagPeopleDialog.onClickPositive(dialogInterface, i2);
                return;
        }
    }
}
