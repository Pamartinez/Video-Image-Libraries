package com.samsung.android.gallery.module.store;

import A.a;
import N2.j;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppRatingHelper {
    private static boolean checkAppRatingAvailable(Blackboard blackboard) {
        if (!isInstalledPackage()) {
            Log.e("AppRatingHelper", "checkAppRatingAvailable not installed package");
            PreferenceCache.AppRatingNeverShow.setBoolean(true);
            return false;
        } else if (!isStoreAvailable() || isKnox() || !PickerUtil.isNormalLaunchMode(blackboard)) {
            return false;
        } else {
            return true;
        }
    }

    public static void decline() {
        PreferenceCache.AppRatingNoThanks.setBoolean(true);
        PreferenceCache.AppRatingNeverShow.setBoolean(true);
        PreferenceCache.AppRatingShow.setBoolean(false);
    }

    public static void increaseCount(Context context, Blackboard blackboard) {
        if (!PreferenceCache.AppRatingNeverShow.getBoolean() && checkAppRatingAvailable(blackboard) && isApplicationSentToBackground(context)) {
            int incrementAndGet = PreferenceCache.AppRatingEnteringCount.incrementAndGet();
            boolean isAppRatingPossible = isAppRatingPossible(blackboard);
            PreferenceCache.AppRatingShow.setBoolean(isAppRatingPossible);
            Log.d("AppRatingHelper", "increaseCount {" + incrementAndGet + ",30," + isAppRatingPossible + "}");
        }
    }

    private static boolean isAppRatingEligible(Blackboard blackboard) {
        if (!PreferenceCache.AppRatingShow.getBoolean() || !checkAppRatingAvailable(blackboard)) {
            return false;
        }
        return true;
    }

    private static boolean isAppRatingPossible(Blackboard blackboard) {
        if (PreferenceCache.AppRatingNeverShow.getBoolean() || PreferenceCache.AppRatingEnteringCount.getInt() < 30 || !checkAppRatingAvailable(blackboard)) {
            return false;
        }
        return true;
    }

    private static boolean isApplicationSentToBackground(Context context) {
        ComponentName componentName;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return false;
            }
            List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
            if (!runningTasks.isEmpty() && (componentName = runningTasks.get(0).topActivity) != null && !componentName.getPackageName().equals(context.getPackageName())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("isApplicationSentToBackground failed e="), "AppRatingHelper");
        }
    }

    private static boolean isInstalledPackage() {
        PackageMonitorCompat instance = PackageMonitorCompat.getInstance();
        if ((instance.isInstallerPackageOfGallery("com.android.vending") || instance.isInstallerPackageOfGallery("com.sec.android.app.samsungapps")) && !isSourceDirInSystem()) {
            return true;
        }
        return false;
    }

    private static boolean isKnox() {
        if (Features.isEnabled(Features.IS_KNOX_MODE) || Features.isEnabled(Features.IS_AFW_MODE)) {
            return true;
        }
        return false;
    }

    public static boolean isSourceDirInSystem() {
        try {
            ApplicationInfo applicationInfo = PackageMonitorCompat.getInstance().getPackageManager().getApplicationInfo("com.sec.android.gallery3d", 0);
            if (applicationInfo != null && !TextUtils.isEmpty(applicationInfo.sourceDir)) {
                boolean startsWith = applicationInfo.sourceDir.startsWith("/system");
                Log.d("AppRatingHelper", "isSourceDirInSystem=" + startsWith);
                return startsWith;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isStoreAvailable() {
        if (Features.isEnabled(Features.SUPPORT_GALAXY_APPS) || Features.isEnabled(Features.SUPPORT_GOOGLE_PLAY_SERVICE)) {
            return true;
        }
        return false;
    }

    public static void postpone() {
        PreferenceCache.AppRatingEnteringCount.setInt(-60);
        if (PreferenceCache.AppRatingLaterCount.incrementAndGet() >= 3) {
            PreferenceCache.AppRatingNeverShow.setBoolean(true);
        }
        PreferenceCache.AppRatingShow.setBoolean(false);
    }

    public static void reset() {
        boolean z = PreferenceCache.AppRatingNoThanks.getBoolean();
        a.v("reset {declined=", "}", "AppRatingHelper", z);
        if (!z) {
            PreferenceCache.AppRatingShow.setBoolean(false);
            PreferenceCache.AppRatingNeverShow.setBoolean(false);
            PreferenceCache.AppRatingLaterCount.setInt(0);
            PreferenceCache.AppRatingEnteringCount.setInt(1);
        }
    }

    public static boolean showDialog(Blackboard blackboard) {
        if (!isAppRatingEligible(blackboard)) {
            return false;
        }
        PreferenceCache.AppRatingShow.setBoolean(false);
        blackboard.post(CommandKey.DATA_REQUEST(new UriBuilder("data://user/dialog/AppRating").build()), (Object) null);
        return true;
    }

    public static void startGooglePlayStore(Context context) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName())));
        } catch (ActivityNotFoundException unused) {
            Log.e("AppRatingHelper", "Rating Option Exception");
        }
    }

    public static void startStore(Context context) {
        if (context == null) {
            Log.e("AppRatingHelper", "startStore failed null context");
            return;
        }
        try {
            if (PackageMonitorCompat.getInstance().isInstallerPackageOfGallery("com.android.vending")) {
                startGooglePlayStore(context);
            } else {
                MarketHelper.startGalaxyApps(context);
            }
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startStore failed e="), "AppRatingHelper");
        }
        PreferenceCache.AppRatingNeverShow.setBoolean(true);
        PreferenceCache.AppRatingShow.setBoolean(false);
    }
}
