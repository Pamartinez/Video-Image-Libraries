package q4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.RangeDatePickerDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ RangeDatePickerDialog e;

    public /* synthetic */ o(RangeDatePickerDialog rangeDatePickerDialog, int i2) {
        this.d = i2;
        this.e = rangeDatePickerDialog;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        RangeDatePickerDialog rangeDatePickerDialog = this.e;
        switch (i2) {
            case 0:
                rangeDatePickerDialog.onStartTabClick(view);
                return;
            default:
                rangeDatePickerDialog.onEndTabClick(view);
                return;
        }
    }
}
