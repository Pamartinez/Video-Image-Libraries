package com.samsung.android.gallery.app.remote.dlna;

import Fa.C0558l;
import a4.C0416a;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import androidx.appcompat.app.AlertDialog;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DlnaDisconnectDialog {
    private final WeakReference<Context> mContextRef;
    private Dialog mDialog;

    public DlnaDisconnectDialog(Context context) {
        this.mContextRef = new WeakReference<>(context);
    }

    /* access modifiers changed from: private */
    public boolean onKeyClicked(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 4) {
            return false;
        }
        dialogInterface.dismiss();
        return true;
    }

    /* access modifiers changed from: private */
    public void onPositiveClicked(DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
    }

    public boolean createDialog() {
        Context context = this.mContextRef.get();
        if (this.mDialog != null || context == null) {
            return false;
        }
        AlertDialog create = new AlertDialog.Builder(context).setTitle((int) R.string.error).setMessage((int) R.string.allshare_player_is_not_available).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) new C0558l(8, this)).setOnKeyListener(new C0416a(0, this)).create();
        this.mDialog = create;
        create.setCanceledOnTouchOutside(false);
        return true;
    }

    public boolean showDialog() {
        createDialog();
        if (this.mDialog.isShowing()) {
            return false;
        }
        this.mDialog.show();
        return true;
    }
}
