package t1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import w1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class j extends DialogFragment {
    public Dialog d;
    public DialogInterface.OnCancelListener e;
    public AlertDialog f;

    public final void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.e;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    public final Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = this.d;
        if (dialog != null) {
            return dialog;
        }
        setShowsDialog(false);
        if (this.f == null) {
            Context context = getContext();
            r.b(context);
            this.f = new AlertDialog.Builder(context).create();
        }
        return this.f;
    }
}
