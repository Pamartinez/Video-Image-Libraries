package com.samsung.android.gallery.app.ui.dialog;

import A.a;
import N2.j;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import o6.t;
import q4.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestGroupSharingEnableDialog extends BaseDialog {
    /* access modifiers changed from: private */
    public void dismissDialog(DialogInterface dialogInterface, int i2) {
        try {
            dismissAllowingStateLoss();
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("unable to dismiss dialog e="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public void startGroupSharingSetting(DialogInterface dialogInterface, int i2) {
        if (getContext() == null) {
            Log.e(this.TAG, "unable to start group sharing setting, null context");
        } else {
            ThreadUtil.postOnBgThread(new t(10, this));
        }
    }

    /* access modifiers changed from: private */
    public void startGroupSharingSettingInternal() {
        try {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("package:com.samsung.android.mobileservice"));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            j.p(e, new StringBuilder("execute failed, e="), this.TAG);
        }
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context == null) {
            return super.createDialog(bundle);
        }
        AlertDialog.Builder message = new AlertDialog.Builder(context).setCancelable(false).setMessage((CharSequence) SeApiCompat.naturalizeText(context.getString(R.string.request_group_sharing_dialog_message, new Object[]{context.getString(R.string.group_sharing)})));
        message.setPositiveButton((int) R.string.settings, (DialogInterface.OnClickListener) new p(this, 0));
        message.setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new p(this, 1));
        return message.create();
    }
}
