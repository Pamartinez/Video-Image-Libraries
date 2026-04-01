package com.samsung.android.gallery.app.ui.dialog;

import A.a;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import q4.C;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TurnOnTrashDialog extends BaseDialog {
    private void dismissDialog() {
        try {
            dismissAllowingStateLoss();
        } catch (Exception e) {
            a.s(e, new StringBuilder("dismiss dialog failed e="), this.TAG);
        }
    }

    private String getTrashOnMessage(Context context) {
        return context.getString(R.string.deleted_items_will_stay_in_the_trash_30_days);
    }

    /* access modifiers changed from: private */
    public void onCancelClicked(DialogInterface dialogInterface, int i2) {
        publishInternal(2);
        dismissDialog();
    }

    /* access modifiers changed from: private */
    public void onTurnOnTrashClicked(DialogInterface dialogInterface, int i2) {
        publishInternal(1);
        dismissDialog();
    }

    private void publishInternal(Integer num) {
        getBlackboard().post("data://user/dialog/TurnOnTrash", num);
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context == null) {
            return super.createDialog(bundle);
        }
        return new AlertDialog.Builder(context).setTitle((int) R.string.turn_on_the_trash_q).setMessage((CharSequence) getTrashOnMessage(context)).setCancelable(false).setPositiveButton((int) R.string.turn_on_trash, (DialogInterface.OnClickListener) new C(this, 0)).setNeutralButton((int) R.string.not_now, (DialogInterface.OnClickListener) new C(this, 1)).create();
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/TurnOnTrash", (Object) null);
    }
}
