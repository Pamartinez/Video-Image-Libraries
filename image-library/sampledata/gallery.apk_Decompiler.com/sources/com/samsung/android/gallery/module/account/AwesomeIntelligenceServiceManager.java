package com.samsung.android.gallery.module.account;

import A.a;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AwesomeIntelligenceServiceManager {
    private static final AwesomeIntelligenceServiceManager sInstance = new AwesomeIntelligenceServiceManager();

    private AwesomeIntelligenceServiceManager() {
    }

    public static AwesomeIntelligenceServiceManager getInstance() {
        return sInstance;
    }

    public boolean isIntelligenceServiceShown(Context context) {
        if (context == null || SeApiCompat.getSettingsSystemInt(context, "intelligent_features_info_confirmed", 0) == 1) {
            return true;
        }
        return false;
    }

    public boolean needToShowAiIntroduction(Context context) {
        if (context == null) {
            return false;
        }
        boolean hasAccount = SamsungAccountManager.getInstance().reload().hasAccount();
        boolean isIntelligenceServiceShown = isIntelligenceServiceShown(context);
        if (!hasAccount || !isIntelligenceServiceShown) {
            return true;
        }
        return false;
    }

    public void setIntelligenceServiceShown(Context context) {
        if (context != null) {
            Settings.System.putInt(context.getContentResolver(), "intelligent_features_info_confirmed", 1);
        }
    }

    public void showRestrictedAccountToast(Context context) {
        int childGraduateAge = SamsungAccountManager.getChildGraduateAge(context);
        if (childGraduateAge != -1) {
            Utils.showToast(context, AppResources.getQuantityString(R$plurals.at_least_pd_years_old_to_use_intelligent_features, childGraduateAge, Integer.valueOf(childGraduateAge)));
        } else {
            Log.e("AwesomeIntelligenceServiceUtils", "get restricted age failed");
        }
    }

    public void startActivity(Activity activity) {
        if (activity != null) {
            try {
                Intent intent = new Intent("com.samsung.android.settings.action.INTELLIGENT_FEATURES_SETTINGS");
                intent.setPackage("com.android.settings");
                intent.putExtra("key_calling_package", activity.getPackageName());
                activity.startActivityForResult(intent, 798);
            } catch (Exception e) {
                a.s(e, new StringBuilder("launch intelligence setting failed. e="), "AwesomeIntelligenceServiceUtils");
            }
        }
    }

    public void release() {
    }
}
