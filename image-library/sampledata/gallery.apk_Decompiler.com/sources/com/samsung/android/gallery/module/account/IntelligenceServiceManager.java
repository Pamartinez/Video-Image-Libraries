package com.samsung.android.gallery.module.account;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntelligenceServiceManager {
    private static final IntelligenceServiceManager sInstance = new IntelligenceServiceManager();

    private IntelligenceServiceManager() {
    }

    public static IntelligenceServiceManager getInstance() {
        return sInstance;
    }

    public boolean isIntelligenceServiceShown(Context context) {
        if (SeApiCompat.getSettingsSystemInt(context, "ai_info_confirmed", 0) == 1) {
            return true;
        }
        return false;
    }

    public boolean needToShownSignIn(Context context) {
        boolean hasSamsungAccountId = SamsungAccountManager.getInstance().hasSamsungAccountId(context);
        boolean isIntelligenceServiceShown = isIntelligenceServiceShown(context);
        if (!hasSamsungAccountId || !isIntelligenceServiceShown) {
            return true;
        }
        return false;
    }

    public void showRestrictedAccountToast(Context context) {
        int childGraduateAge = SamsungAccountManager.getChildGraduateAge(context);
        if (childGraduateAge != -1) {
            Utils.showToast(context, AppResources.getQuantityString(R$plurals.at_least_pd_years_old_to_use_intelligent_features, childGraduateAge, Integer.valueOf(childGraduateAge)));
        } else {
            Log.e("IntelligenceServiceManager", "get restricted age failed");
        }
    }

    public void startIntelligenceService(Activity activity) {
        try {
            Intent intent = new Intent("com.samsung.android.settings.action.INTELLIGENCE_SERVICE_SETTINGS");
            intent.setPackage("com.android.settings");
            intent.putExtra("key_calling_package", activity.getPackageName());
            activity.startActivityForResult(intent, 800);
        } catch (ActivityNotFoundException unused) {
            Log.e("IntelligenceServiceManager", "ActivityNotFoundException");
        }
    }
}
