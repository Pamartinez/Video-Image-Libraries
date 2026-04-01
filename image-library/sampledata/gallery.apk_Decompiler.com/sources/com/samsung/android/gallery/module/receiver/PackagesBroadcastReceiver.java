package com.samsung.android.gallery.module.receiver;

import A.a;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import i.C0212a;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PackagesBroadcastReceiver extends AbsBroadcastReceiver {
    private String addWildcard(String str) {
        return C0212a.m(".*", str, ".*");
    }

    private void clearFeatureOnDestroy() {
        Log.d(this.TAG, "clearFeatureOnDestroy");
        PackageMonitorCompat.getInstance().recycle();
        Features.recycle(Features.SUPPORT_VISION_INTELLIGENCE);
        Features.recycle(Features.SUPPORT_CONTACT_US);
        Features.recycle(Features.IS_RUBIN_ENABLED);
        Features.recycle(Features.SUPPORT_MEMORY_SAVER);
        Features.recycle(Features.SUPPORT_GO_TO_URL);
    }

    private boolean disableGroupSharing(String str, Intent intent) {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo;
        if (!CommonUtils.MOBILE_SERVICE_PACKAGE_NAME.equals(str) || !"android.intent.action.PACKAGE_CHANGED".equals(intent.getAction()) || (packageInfo = PackageMonitorCompat.getInstance().getPackageInfo(CommonUtils.MOBILE_SERVICE_PACKAGE_NAME, 0)) == null || (applicationInfo = packageInfo.applicationInfo) == null || applicationInfo.enabled) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$resetFeature$0() {
        KnoxUtil.getInstance().reload();
        Blackboard.postEventGlobal(EventMessage.obtain(1056));
    }

    /* JADX WARNING: type inference failed for: r1v27, types: [java.lang.Object, java.lang.Runnable] */
    private void resetFeature(String str) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        PackageMonitorCompat.getInstance().clear(lowerCase);
        if (lowerCase.contains("knox")) {
            SimpleThreadPool.getInstance().execute(new Object());
        } else if (lowerCase.contains("voc")) {
            Features.recycle(Features.SUPPORT_CONTACT_US);
        } else if (lowerCase.contains("rubin")) {
            Features.recycle(Features.IS_RUBIN_ENABLED);
        } else if (lowerCase.equals("com.samsung.android.visionintelligence")) {
            Features.recycle(Features.SUPPORT_VISION_INTELLIGENCE);
        } else if (lowerCase.equals("com.samsung.memorysaver")) {
            Features.recycle(Features.SUPPORT_MEMORY_SAVER);
        } else if (lowerCase.equals("com.samsung.android.app.smartcapture")) {
            Features.recycle(Features.SUPPORT_GO_TO_URL);
        } else if (lowerCase.equals("com.vcast.mediamanager") && Features.isEnabled(Features.IS_VERIZON_DEVICE)) {
            Features.recycle(Features.SUPPORT_VERIZON_CLOUD);
            Blackboard.postEventGlobal(EventMessage.obtain(1087));
        } else if (lowerCase.equals("com.microsoft.skydrive")) {
            Features.recycle(Features.SUPPORT_BACK_UP_SD_CARD_TO_ONE_DRIVE);
        } else if (lowerCase.equals(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME)) {
            Features.recycle(Features.SUPPORT_FAMILY_SHARED_EDIT_PHOTO);
            Features.recycle(Features.SUPPORT_FAMILY_SHARED_EDIT_GIF);
        } else if (lowerCase.equals("com.sec.android.app.vepreload")) {
            Features.recycle(Features.SUPPORT_FAMILY_SHARED_EDIT_VIDEO);
        } else if (lowerCase.equals("com.samsung.app.newtrim")) {
            Features.recycle(Features.SUPPORT_FAMILY_SHARED_EDIT_LITE_VIDEO);
        } else if (lowerCase.equals("com.samsung.android.gallery.assistant.app")) {
            Features.recycle(Features.SUPPORT_GALLERY_ASSISTANT);
        }
    }

    public void onReceive(Context context, Intent intent) {
        try {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            String str = this.TAG;
            Log.d(str, "onReceive {" + intent.getAction() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + schemeSpecificPart + "}");
            resetFeature(schemeSpecificPart);
            if (Features.isEnabled(Features.SUPPORT_SHARED_PERMISSION_POPUP) && disableGroupSharing(schemeSpecificPart, intent)) {
                Blackboard.postGlobal("command://FinishSharingAlbum", (Object) null);
            }
            if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction()) && "com.samsung.android.gallery.plugins".equals(schemeSpecificPart)) {
                Blackboard.postGlobal("command://reloadMap", (Object) null);
            }
            if ("com.samsung.android.gallery.assistant.app".equals(schemeSpecificPart)) {
                Blackboard.postGlobal("global://setting/assistant/PackageChanged", intent);
            }
        } catch (Exception unused) {
            String str2 = this.TAG;
            Log.e(str2, "onReceive failed " + intent);
        }
    }

    public void registerReceiver(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addDataScheme("package");
            intentFilter.addDataSchemeSpecificPart(addWildcard("knox"), 2);
            intentFilter.addDataSchemeSpecificPart(addWildcard("voc"), 2);
            intentFilter.addDataSchemeSpecificPart(addWildcard("rubin"), 2);
            intentFilter.addDataSchemeSpecificPart("com.samsung.android.visionintelligence", 0);
            intentFilter.addDataSchemeSpecificPart("com.samsung.memorysaver", 0);
            intentFilter.addDataSchemeSpecificPart("com.samsung.android.app.smartcapture", 0);
            if (Features.isEnabled(Features.SUPPORT_SHARED_PERMISSION_POPUP)) {
                intentFilter.addDataSchemeSpecificPart(CommonUtils.MOBILE_SERVICE_PACKAGE_NAME, 0);
            }
            if (Features.isEnabled(Features.IS_VERIZON_DEVICE)) {
                intentFilter.addDataSchemeSpecificPart("com.vcast.mediamanager", 0);
            }
            intentFilter.addDataSchemeSpecificPart("com.microsoft.skydrive", 0);
            if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
                intentFilter.addDataSchemeSpecificPart(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, 0);
                intentFilter.addDataSchemeSpecificPart("com.sec.android.app.vepreload", 0);
                intentFilter.addDataSchemeSpecificPart("com.samsung.app.newtrim", 0);
            }
            intentFilter.addDataSchemeSpecificPart("com.samsung.android.gallery.assistant.app", 0);
            AndroidCompat.registerSystemReceiver(context, this, intentFilter);
        } catch (Exception e) {
            a.s(e, new StringBuilder("registerReceiver failed e="), this.TAG);
        }
    }

    public void unregisterReceiver(Context context) {
        super.unregisterReceiver(context);
        clearFeatureOnDestroy();
    }
}
