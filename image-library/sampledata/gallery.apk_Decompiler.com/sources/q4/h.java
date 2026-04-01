package q4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.DateTimePickerDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DateTimePickerDialog e;

    public /* synthetic */ h(DateTimePickerDialog dateTimePickerDialog, int i2) {
        this.d = i2;
        this.e = dateTimePickerDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        DateTimePickerDialog dateTimePickerDialog = this.e;
        switch (i7) {
            case 0:
                dateTimePickerDialog.onDoneClicked(dialogInterface, i2);
                return;
            default:
                dateTimePickerDialog.onCancelClicked(dialogInterface, i2);
                return;
        }
    }
}
