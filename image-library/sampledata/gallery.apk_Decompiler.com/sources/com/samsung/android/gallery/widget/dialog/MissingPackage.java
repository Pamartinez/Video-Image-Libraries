package com.samsung.android.gallery.widget.dialog;

import A.a;
import C3.g;
import C3.h;
import N2.j;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import java.util.function.Predicate;
import qa.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MissingPackage {
    /* access modifiers changed from: private */
    public static final String TAG = "MissingPackage";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DownloadBuilder {
        private static final boolean IS_AVAILABLE_GALAXY_APPS = Features.isEnabled(Features.SUPPORT_GALAXY_APPS);
        String mAppName = "";
        String mPackageName = "";
        boolean mUpdateRequired;

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

        private AlertDialog createDownloadAppGuideDialog(Context context) {
            return new AlertDialog.Builder(context).setTitle(SeApiCompat.naturalizeText(context.getString(R$string.download_app_dialog_title, new Object[]{this.mAppName}))).setMessage(context.getString(R$string.download_app_dialog_msg, new Object[]{this.mAppName})).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R$string.download, new b(this, context, 1)).create();
        }

        private AlertDialog createUnsupportedGalaxyAppsGuideDialog(Context context) {
            return new AlertDialog.Builder(context).setTitle(context.getString(R$string.can_not_download_right_now, new Object[]{this.mAppName})).setMessage(context.getString(R$string.can_not_download_right_now_msg, new Object[]{this.mAppName})).setNegativeButton(R$string.ok, (DialogInterface.OnClickListener) null).create();
        }

        private AlertDialog createUpdateAppGuideDialog(Context context) {
            return new AlertDialog.Builder(context).setTitle(context.getString(R$string.update_app_dialog_title, new Object[]{this.mAppName})).setMessage(context.getString(R$string.update_app_dialog_description, new Object[]{this.mAppName})).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R$string.update_app_button_text, new b(this, context, 0)).create();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$createDownloadAppGuideDialog$0(Context context, DialogInterface dialogInterface, int i2) {
            onPositiveButtonClicked(context);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$createUpdateAppGuideDialog$1(Context context, DialogInterface dialogInterface, int i2) {
            onPositiveButtonClicked(context);
        }

        private void onPositiveButtonClicked(Context context) {
            if (!TextUtils.isEmpty(this.mPackageName)) {
                startSamsungAppStore(context, this.mPackageName);
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
                j.p(e, new StringBuilder("startSamsungAppStore failed e="), MissingPackage.TAG);
                Toast.makeText(context, R$string.activity_not_found, 0).show();
            }
        }

        public AlertDialog build(Context context) {
            if (!IS_AVAILABLE_GALAXY_APPS) {
                return createUnsupportedGalaxyAppsGuideDialog(context);
            }
            if (this.mUpdateRequired) {
                return createUpdateAppGuideDialog(context);
            }
            return createDownloadAppGuideDialog(context);
        }

        public DownloadBuilder setPackage(String str, String str2) {
            this.mPackageName = str;
            this.mAppName = str2;
            return this;
        }

        public DownloadBuilder setUpdateRequired(boolean z) {
            this.mUpdateRequired = z;
            return this;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$showGotoSettingIfDisabled$1(String str, Context context, DialogInterface dialogInterface, int i2) {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + str));
            context.startActivity(intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("goto settings failed. e="), TAG);
        }
    }

    public static boolean showGotoSettingIfDisabled(Context context, String str) {
        return showGotoSettingIfDisabled(context, str, new e(8));
    }

    public static boolean showGotoStoreIfDeleted(Context context, String str, String str2, boolean z) {
        ThreadUtil.runOnUiThread(new F8.a(str, str2, z, context));
        return true;
    }

    public static boolean supportPackageDisabling(ApplicationInfo applicationInfo) {
        if (applicationInfo == null || !"com.sec.android.app.vepreload".equals(applicationInfo.packageName)) {
            return true;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle == null || !bundle.getBoolean("com.sec.android.app.unblockdisabling", false)) {
            return false;
        }
        return true;
    }

    public static boolean showGotoSettingIfDisabled(Context context, String str, Predicate<ApplicationInfo> predicate) {
        try {
            PackageManager packageManager = PackageMonitorCompat.getInstance().getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            if (applicationInfo.enabled) {
                return false;
            }
            if (predicate == null || predicate.test(applicationInfo)) {
                Log.d(TAG, "showGotoSettingIfDisabled", str);
                ThreadUtil.runOnUiThread(new h(new AlertDialog.Builder(context).setMessage(SeApiCompat.naturalizeText(String.format(context.getString(R$string.request_group_sharing_dialog_message), new Object[]{packageManager.getApplicationLabel(applicationInfo)}))).setNegativeButton(R$string.cancel, new Ba.a(6)).setPositiveButton(R$string.settings, new g(context, str)), 1));
                return true;
            }
            Log.w((CharSequence) TAG, "showGotoSettingIfDisabled skip. not supported", str);
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("showGotoSettingIfDisabled failed. e="), TAG);
            return false;
        }
    }
}
