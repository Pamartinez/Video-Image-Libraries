package Ga;

import android.content.DialogInterface;
import androidx.preference.DropDownPreference;
import com.samsung.android.gallery.settings.ui.delegate.DialogDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements DialogInterface.OnDismissListener {
    public final /* synthetic */ DialogDelegate.OnDialogListener d;
    public final /* synthetic */ DropDownPreference e;
    public final /* synthetic */ int f;

    public /* synthetic */ c(DropDownPreference dropDownPreference, int i2, DialogDelegate.OnDialogListener onDialogListener) {
        this.d = onDialogListener;
        this.e = dropDownPreference;
        this.f = i2;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        this.d.onDismiss(this.e, this.f);
    }
}
