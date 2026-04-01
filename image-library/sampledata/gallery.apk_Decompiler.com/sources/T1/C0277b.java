package t1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import w1.r;

/* renamed from: t1.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0277b extends DialogFragment {
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
            Activity activity = getActivity();
            r.b(activity);
            this.f = new AlertDialog.Builder(activity).create();
        }
        return this.f;
    }
}
