package com.samsung.android.gallery.app.ui.dialog;

import Fa.C0558l;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartAppInfoDialog extends BaseDialog {
    private String mAppName;
    private String mPackageName;

    private void dismissWithLog() {
        Log.d(this.TAG, "App or package name is null");
        dismiss();
    }

    private void initNames() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mAppName = arguments.getString("appName");
            this.mPackageName = arguments.getString(MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$0(DialogInterface dialogInterface, int i2) {
        onPositiveButtonClicked();
    }

    private void onPositiveButtonClicked() {
        getBlackboard().post("data://user/dialog/StartAppInfo", this.mPackageName);
    }

    public Dialog createDialog(Bundle bundle) {
        initNames();
        if (this.mAppName == null || this.mPackageName == null) {
            dismissWithLog();
        }
        return new AlertDialog.Builder(getActivity()).setMessage((CharSequence) SeApiCompat.naturalizeText(getResources().getString(R.string.start_app_info_description, new Object[]{this.mAppName}))).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton((int) R.string.settings, (DialogInterface.OnClickListener) new C0558l(16, this)).create();
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        getBlackboard().post("data://user/dialog/StartAppInfo", (Object) null);
    }
}
