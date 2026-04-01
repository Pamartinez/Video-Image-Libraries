package q4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.DateTimePickerDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DateTimePickerDialog e;

    public /* synthetic */ i(DateTimePickerDialog dateTimePickerDialog, int i2) {
        this.d = i2;
        this.e = dateTimePickerDialog;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        DateTimePickerDialog dateTimePickerDialog = this.e;
        switch (i2) {
            case 0:
                dateTimePickerDialog.lambda$bindTimeViews$2(view);
                return;
            default:
                dateTimePickerDialog.lambda$bindDateViews$1(view);
                return;
        }
    }
}
