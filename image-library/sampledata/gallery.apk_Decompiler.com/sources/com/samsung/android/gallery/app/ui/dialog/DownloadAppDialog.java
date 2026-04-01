package com.samsung.android.gallery.app.ui.dialog;

import Fa.C0558l;
import N2.j;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadAppDialog extends BaseDialog {
    private static final boolean IS_AVAILABLE_GALAXY_APPS = Features.isEnabled(Features.SUPPORT_GALAXY_APPS);
    private String mPackageName;

    private Intent createAppPackageIntent(String str) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("samsungapps://ProductDetail/" + str));
        intent.addFlags(268435456);
        return intent;
    }

    private Intent createCloudPackageIntent(String str) {
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.app.samsungapps", "com.sec.android.app.samsungapps.Main");
        intent.putExtra("directcall", true);
        intent.putExtra("CallerType", 1);
        intent.putExtra(BuddyContract.Info.GUID, "com.samsung.android.scloud");
        intent.putExtra("type", "cover");
        intent.addFlags(335544352);
        return intent;
    }

    private AlertDialog createDownloadAppGuideDialog(String str) {
        return new AlertDialog.Builder(getActivity()).setTitle((CharSequence) SeApiCompat.naturalizeText(getString(R.string.download_app_dialog_title, str))).setMessage((CharSequence) getString(R.string.download_app_dialog_msg, str)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton((int) R.string.download, (DialogInterface.OnClickListener) new C0558l(12, this)).create();
    }

    private AlertDialog createUnsupportedGalaxyAppsGuideDialog(String str) {
        return new AlertDialog.Builder(getActivity()).setTitle((CharSequence) getString(R.string.can_not_download_right_now, str)).setMessage((CharSequence) getString(R.string.can_not_download_right_now_msg, str)).setNegativeButton((int) R.string.ok, (DialogInterface.OnClickListener) null).create();
    }

    private AlertDialog createUpdateAppGuideDialog(String str) {
        return new AlertDialog.Builder(getActivity()).setTitle((CharSequence) getString(R.string.update_app_dialog_title, str)).setMessage((CharSequence) getString(R.string.update_app_dialog_description, str)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton((int) R.string.update_app_button_text, (DialogInterface.OnClickListener) new C0558l(12, this)).create();
    }

    /* access modifiers changed from: private */
    public void onPositiveButtonClicked(DialogInterface dialogInterface, int i2) {
        if (!TextUtils.isEmpty(this.mPackageName)) {
            startSamsungAppStore(getContext(), this.mPackageName);
        }
    }

    private void startSamsungAppStore(Context context, String str) {
        Intent intent;
        try {
            if ("com.samsung.android.scloud".equalsIgnoreCase(str)) {
                intent = createCloudPackageIntent(str);
            } else {
                intent = createAppPackageIntent(str);
            }
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            j.p(e, new StringBuilder("startSamsungAppStore failed e="), this.TAG);
            Toast.makeText(context, R.string.activity_not_found, 0).show();
        }
    }

    public Dialog createDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mPackageName = arguments.getString(MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME, "");
            String string = arguments.getString("appName", "");
            boolean z = BundleWrapper.getBoolean(arguments, "appUpdate");
            if (!IS_AVAILABLE_GALAXY_APPS) {
                return createUnsupportedGalaxyAppsGuideDialog(string);
            }
            if (z) {
                return createUpdateAppGuideDialog(string);
            }
            return createDownloadAppGuideDialog(string);
        }
        throw new IllegalArgumentException("bundle is null");
    }
}
