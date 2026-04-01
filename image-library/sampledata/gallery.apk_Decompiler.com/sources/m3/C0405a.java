package M3;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* renamed from: M3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0405a implements DatePickerDialog.OnDateSetListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Blackboard f2393a;

    public /* synthetic */ C0405a(Blackboard blackboard) {
        this.f2393a = blackboard;
    }

    public final void onDateSet(DatePicker datePicker, int i2, int i7, int i8) {
        this.f2393a.publish("data://user/Date", new int[]{i2, i7 + 1, i8});
    }
}
