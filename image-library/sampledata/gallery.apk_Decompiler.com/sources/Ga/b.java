package Ga;

import android.content.DialogInterface;
import com.samsung.android.gallery.settings.ui.delegate.DialogDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements DialogInterface.OnClickListener {
    public final /* synthetic */ DialogDelegate.OnDialogListener d;
    public final /* synthetic */ int e;

    public /* synthetic */ b(DialogDelegate.OnDialogListener onDialogListener, int i2) {
        this.d = onDialogListener;
        this.e = i2;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        this.d.onConfirmed(this.e);
    }
}
