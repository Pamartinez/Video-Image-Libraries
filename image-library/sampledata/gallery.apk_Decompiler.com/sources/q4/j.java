package q4;

import androidx.picker.widget.SeslDatePicker;
import androidx.picker.widget.SeslTimePicker;
import com.samsung.android.gallery.app.ui.dialog.DateTimePickerDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements SeslTimePicker.OnTimeChangedListener, SeslDatePicker.OnDateChangedListener {
    public final /* synthetic */ DateTimePickerDialog d;

    public /* synthetic */ j(DateTimePickerDialog dateTimePickerDialog) {
        this.d = dateTimePickerDialog;
    }

    public void onDateChanged(SeslDatePicker seslDatePicker, int i2, int i7, int i8) {
        this.d.onDateChanged(seslDatePicker, i2, i7, i8);
    }
}
