package com.samsung.android.gallery.module.cloud;

import A.a;
import N2.j;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.TimeUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDriveManager {
    private static final OneDriveManager sInstance = new OneDriveManager();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum LinkingStatus {
        LINKING_AVAILABLE,
        APP_NOT_INSTALLED,
        APP_UPDATE,
        APP_DISABLED
    }

    public static OneDriveManager getInstance() {
        return sInstance;
    }

    private Intent getMainIntent() {
        Intent intent = new Intent();
        intent.setPackage("com.microsoft.skydrive");
        intent.setAction("com.microsoft.skydrive.samsunghandleractivity.action.navigatetoplans");
        return intent;
    }

    private Intent getOneDriveAppInfoIntent() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addFlags(268435456);
        intent.setData(Uri.fromParts("package", "com.microsoft.skydrive", (String) null));
        return intent;
    }

    private Intent getPartnerAppStoreIntent() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.microsoft.skydrive"));
        intent.setPackage("com.android.vending");
        return intent;
    }

    private LinkingStatus isLinkingAvailable(Activity activity) {
        if (!PackageMonitorCompat.getInstance().isPackageInstalled("com.microsoft.skydrive")) {
            return LinkingStatus.APP_NOT_INSTALLED;
        }
        if (!PackageMonitorCompat.getInstance().isPackageEnabled("com.microsoft.skydrive")) {
            return LinkingStatus.APP_DISABLED;
        }
        if (getMainIntent().resolveActivity(activity.getPackageManager()) != null) {
            return LinkingStatus.LINKING_AVAILABLE;
        }
        return LinkingStatus.APP_UPDATE;
    }

    private boolean startActivity(Activity activity, Intent intent, int i2) {
        try {
            activity.startActivityForResult(intent, i2);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.w("OneDriveManager", " ActivityNotFoundException" + e);
            return false;
        }
    }

    public boolean isQuotaFullDisplayPeriodRefreshed() {
        if (PreferenceCache.OneDriveQuotaFullTipCardTime.getLong() < TimeUtil.getDaysAgo(7)) {
            return true;
        }
        return false;
    }

    public boolean isQuotaFullDisplayPeriodRefreshedForSetting() {
        if (PreferenceCache.OneDriveQuotaFullTipCardTimeInSetting.getLong() < TimeUtil.getDaysAgo(7)) {
            return true;
        }
        return false;
    }

    public boolean startBackUpSdCard(Context context) {
        try {
            context.startActivity(new Intent("com.microsoft.skydrive.samsunghandleractivity.action.navigatetosdcardsettings"));
            return true;
        } catch (ActivityNotFoundException | IllegalStateException e) {
            j.u(e, new StringBuilder("startBackUpSdCard failed e="), "OneDriveManager");
            return false;
        }
    }

    public void startBonusSpaceActivity(Activity activity) {
        try {
            Intent intent = new Intent("com.microsoft.skydrive.samsunghandleractivity.action.navigatetoplans");
            intent.putExtra("startFrom", "tipCard");
            activity.startActivity(intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("startBonusSpaceActivity failed e="), "OneDriveManager");
        }
    }

    public void startEosActivity(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("samsungcloud://com.samsung.android.scloud/gallery/entry?from=" + str));
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("startEofActivity failed e="), "OneDriveManager");
        }
    }

    public boolean startMigration(Activity activity) {
        try {
            activity.startActivityForResult(new Intent("com.samsung.android.scloud.app.activity.LAUNCH_MIGRATION_AGREEMENT"), 0);
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("execute failed e ="), "OneDriveManager");
            return false;
        }
    }

    public void startOneDriveQuotaUpgradeActivity(Activity activity, int i2) {
        LinkingStatus isLinkingAvailable = isLinkingAvailable(activity);
        if (isLinkingAvailable == LinkingStatus.APP_NOT_INSTALLED || isLinkingAvailable == LinkingStatus.APP_UPDATE) {
            if (startActivity(activity, getPartnerAppStoreIntent(), i2)) {
                Toast.makeText(activity, R$string.download_one_drive, 0).show();
            }
        } else if (isLinkingAvailable != LinkingStatus.APP_DISABLED) {
            startActivity(activity, getMainIntent(), i2);
        } else if (startActivity(activity, getOneDriveAppInfoIntent(), i2)) {
            String string = activity.getString(R$string.one_drive);
            Toast.makeText(activity, SeApiCompat.naturalizeText(String.format(activity.getString(R$string.app_disabled_go_to_settings_and_enable_app), new Object[]{string, string})), 0).show();
        }
    }
}
