package com.samsung.android.gallery.app.ui.dialog;

import A.a;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShowLowStorageDialog extends BaseDialog {
    /* access modifiers changed from: private */
    public void dismissDialog(DialogInterface dialogInterface, int i2) {
        try {
            dismissAllowingStateLoss();
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("unable to dismiss dialog e="), this.TAG);
        }
    }

    private boolean isFromMyFiles() {
        LaunchIntent launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isFromMyFiles()) {
            return false;
        }
        return true;
    }

    private boolean isMyFilesEnabled() {
        return PackageMonitorCompat.getInstance().isPackageEnabled("com.sec.android.app.myfiles");
    }

    private boolean isMyFilesInstalled() {
        return PackageMonitorCompat.getInstance().isPackageInstalled("com.sec.android.app.myfiles");
    }

    /* access modifiers changed from: private */
    public void startMyFiles(DialogInterface dialogInterface, int i2) {
        Context context = getContext();
        if (context == null) {
            Log.e(this.TAG, "unable to start my files, null context");
        } else if (!isMyFilesEnabled()) {
            Toast.makeText(context, SeApiCompat.naturalizeText(context.getString(R.string.not_enabled_app_in_mpsm, new Object[]{context.getString(R.string.my_files)})), 1).show();
        } else {
            ThreadUtil.postOnBgThread(new t(11, this));
        }
    }

    /* access modifiers changed from: private */
    public void startMyFilesInternal() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Log.e(this.TAG, "unable to start my files, null activity");
            return;
        }
        try {
            activity.startActivity(activity.getPackageManager().getLaunchIntentForPackage("com.sec.android.app.myfiles"));
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to start my files e="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public void startMyFilesStorageAnalysis(DialogInterface dialogInterface, int i2) {
        Context context = getContext();
        if (context == null) {
            Log.e(this.TAG, "unable to start my files storage analysis, null context");
        } else if (!isMyFilesEnabled()) {
            Toast.makeText(context, SeApiCompat.naturalizeText(context.getString(R.string.not_enabled_app_in_mpsm, new Object[]{context.getString(R.string.my_files)})), 1).show();
        } else {
            startActivity(new Intent("com.sec.android.app.myfiles.RUN_STORAGE_ANALYSIS"));
        }
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context == null) {
            return super.createDialog(bundle);
        }
        AlertDialog.Builder message = new AlertDialog.Builder(context).setTitle((int) R.string.not_enough_storage_space).setCancelable(false).setMessage((CharSequence) context.getString(R.string.not_enough_storage_space_description, new Object[]{context.getString(R.string.my_files)}));
        if (isFromMyFiles() || !isMyFilesInstalled()) {
            message.setNegativeButton((int) R.string.ok, (DialogInterface.OnClickListener) new q4.t(this, 0));
        } else {
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                message.setPositiveButton((int) R.string.manage_storage, (DialogInterface.OnClickListener) new q4.t(this, 1));
            } else {
                message.setPositiveButton((int) R.string.my_files, (DialogInterface.OnClickListener) new q4.t(this, 2));
            }
            message.setNegativeButton((int) R.string.ok, (DialogInterface.OnClickListener) new q4.t(this, 0));
        }
        return message.create();
    }
}
