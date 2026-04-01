package q4;

import android.content.DialogInterface;
import com.samsung.android.gallery.app.ui.dialog.RangeDatePickerDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ RangeDatePickerDialog e;

    public /* synthetic */ n(RangeDatePickerDialog rangeDatePickerDialog, int i2) {
        this.d = i2;
        this.e = rangeDatePickerDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        RangeDatePickerDialog rangeDatePickerDialog = this.e;
        switch (i7) {
            case 0:
                rangeDatePickerDialog.onDone(dialogInterface, i2);
                return;
            default:
                rangeDatePickerDialog.onCancel(dialogInterface, i2);
                return;
        }
    }
}
