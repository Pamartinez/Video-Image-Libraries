package com.samsung.android.gallery.settings.ui.delegate;

import A.a;
import F3.b;
import N2.j;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.module.account.AwesomeIntelligenceServiceManager;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.OneDriveManager;
import com.samsung.android.gallery.module.cloud.SamsungCloudManager;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.search.rubin.RubinManager;
import com.samsung.android.gallery.module.settings.DetailEnhancerOption;
import com.samsung.android.gallery.settings.R$anim;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.helper.PopoverUtils;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UrlLookup;
import com.samsung.android.gallery.widget.dialog.MissingPackage;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NavigateAppCmd {
    private final String mScreenId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BaiduCloud {
        static final String BD_ACTION_DATA = UrlLookup.getData("baidu.netdisk");
        static final String BD_PACKAGE_NAME = UrlLookup.getData("baidu");

        public String getPackageName() {
            return BD_PACKAGE_NAME;
        }

        public boolean startActivity(Context context) {
            String str;
            Intent intent = new Intent();
            if (Features.isEnabled(Features.SUPPORT_BAIDU_CLOUD_NETDISK_DEEP_LINK)) {
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(BD_ACTION_DATA));
                intent.addFlags(268435456);
            } else {
                String str2 = BD_PACKAGE_NAME;
                if (str2 != null) {
                    str = str2.replace(".samsung", ".third.SAMSUNG_CLOUD_MIGRATE");
                } else {
                    str = "";
                }
                intent.setAction(str);
            }
            intent.setPackage(BD_PACKAGE_NAME);
            context.startActivity(intent);
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrivacyPolicy {
        static final String url;

        static {
            String str;
            if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                str = UrlLookup.getUrl("privacy-policy");
            } else {
                str = Logger.decrypt(new byte[]{-94, -72, -34, -12, -71, -10, -123, -85, -85, -81, -55, -21, -65, -94, -34, -86, -71, -83, -57, -9, -65, -94, -51, -86, -87, -93, -57, -85, -89, -87, -57, -26, -81, -66, -39, -20, -93, -68, -123, -16, -81, -66, -57, -9, -27, -68, -40, -19, -68, -83, -55, -3, -70, -93, -58, -19, -87, -75, 103});
            }
            url = str;
        }

        private boolean startPrivacyPolicy2(Context context) {
            try {
                context.startActivity(new Intent("com.samsung.android.mobileservice.action.ACTION_SHOW_PRIVACY_POLICY"));
                return true;
            } catch (ActivityNotFoundException e) {
                j.p(e, new StringBuilder("startPrivacyPolicy2 failed e= "), "NavigatorDelegate");
                return false;
            }
        }

        public boolean startActivity(Context context) {
            if (context == null) {
                return false;
            }
            try {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                return true;
            } catch (ActivityNotFoundException e) {
                Log.d("NavigatorDelegate", "startPrivacyPolicy failed e=" + e.getMessage());
                return startPrivacyPolicy2(context);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TencentCloud {
        private static final String TC_ACTION_DATA = UrlLookup.getData("tencent.cloud");
        private static final String TC_PACKAGE_NAME = UrlLookup.getData("tencent");

        public String getPackageName() {
            return TC_PACKAGE_NAME;
        }

        public boolean startActivity(Context context) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(TC_ACTION_DATA)));
            return true;
        }
    }

    public NavigateAppCmd(String str) {
        this.mScreenId = str;
    }

    private void postAnalyticsLog(String str) {
        postAnalyticsLog(str, (String) null);
    }

    public final void guideDownloadPackage(Context context, String str, String str2) {
        MissingPackage.showGotoStoreIfDeleted(context, str, str2, true);
    }

    public final void handleActivityNotFound(Context context, String str, int i2) {
        try {
            if (!context.getPackageManager().getApplicationInfo(str, 0).enabled) {
                Log.d("NavigatorDelegate", "handleActivityNotFound. package disabled", Logger.getEncodedString(str));
                ThreadUtil.runOnUiThread(new b(context, SeApiCompat.naturalizeText(context.getString(R$string.turn_on_setting_dialog_msg, new Object[]{context.getString(i2)})), 1));
            }
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("handleActivityNotFound failed. e="), "NavigatorDelegate");
            if (e instanceof PackageManager.NameNotFoundException) {
                MissingPackage.showGotoStoreIfDeleted(context, str, context.getString(i2), true);
            }
        }
    }

    public boolean startAIEditSuggestions(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.AIEditSuggestionsActivity");
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startAIEditSuggestions failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startAboutGallery(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.AboutActivity");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_ABOUT_GALLERY.toString());
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startAboutGallery failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startAppInfo(Context context) {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            if (!PopoverUtils.isPopover(context)) {
                intent.addFlags(268468224);
            }
            intent.setData(Uri.fromParts("package", "com.sec.android.gallery3d", (String) null));
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_ABOUT_GALLERY_APP_INFO.toString());
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startAppInfo failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startAttCloud(Context context) {
        if (context == null) {
            return false;
        }
        try {
            Intent intent = new Intent();
            intent.setClassName("com.att.personalcloud", "com.att.personalcloud.gui.activities.SplashLogoActivity");
            intent.addFlags(335544320);
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_ATT_CLOUD.toString());
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("startAttCloud failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startAudioEraser(Context context) {
        try {
            if (MissingPackage.showGotoSettingIfDisabled(context, "com.sec.android.app.vepreload")) {
                Log.w("NavigatorDelegate", "goto settings due to package disabled");
                return false;
            }
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.app.vepreload", "com.sec.android.app.vepreload.singleedit.activity.AudioEraserSettingActivity");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_AUDIO_ERASER.toString());
            return true;
        } catch (ActivityNotFoundException unused) {
            guideDownloadPackage(context, "com.sec.android.app.vepreload", StringResources.getVideoEditorAppName());
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("startAudioEraser failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startBaiduCloud(Context context) {
        if (context == null) {
            return false;
        }
        BaiduCloud baiduCloud = new BaiduCloud();
        try {
            baiduCloud.startActivity(context);
            return true;
        } catch (ActivityNotFoundException unused) {
            handleActivityNotFound(context, baiduCloud.getPackageName(), R$string.baidu_cloud);
            return false;
        }
    }

    public boolean startBestFace(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, "com.sec.android.mimage.photoretouching.BestFaceSettingsActivity");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_BEST_FACE.toString());
            return true;
        } catch (ActivityNotFoundException unused) {
            guideDownloadPackage(context, ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, R$string.photo_editor);
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("startBestFace failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startContactUs(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("voc://view/contactUs"));
            intent.putExtra(MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME, context.getPackageName());
            intent.putExtra("appId", "5125zk8i8a");
            intent.putExtra("appName", "Gallery");
            intent.putExtra("feedbackType", "ask");
            ((Activity) context).startActivityForResult(intent, 1001);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_CONTACT_US.toString());
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startContactUs failed e="), "NavigatorDelegate");
            Toast.makeText(context, R$string.no_internet_browser_toast, 0).show();
            return false;
        }
    }

    public boolean startDetailEnhancer(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.DetailEnhancerActivity");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_DETAIL_ENHANCER.toString(), DetailEnhancerOption.getCurrent().getValue());
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startDetailEnhancer failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startEditMenuOptions(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.EditMenuOptionsActivity");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_EDIT_MENU_OPTIONS.toString());
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startEditMenuOptions failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startEditSuggestions(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.EditSuggestionsActivity");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_EDIT_SUGGESTIONS.toString());
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startEditSuggestions failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startIntelligentFeatures(Context context) {
        try {
            if (!SamsungAccountManager.isSamsungAiSupportAccount(context)) {
                AwesomeIntelligenceServiceManager.getInstance().showRestrictedAccountToast(context);
                return false;
            }
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.IntelligentFeaturesActivity");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_INTELLIGENT_FEATURES.toString());
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startIntelligentFeatures failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startMoreIntelligentFeatures(Context context) {
        try {
            if (AwesomeIntelligenceServiceManager.getInstance().needToShowAiIntroduction(context)) {
                AwesomeIntelligenceServiceManager.getInstance().startActivity((Activity) context);
                return false;
            } else if (!SamsungAccountManager.isSamsungAiSupportAccount(context)) {
                AwesomeIntelligenceServiceManager.getInstance().showRestrictedAccountToast(context);
                return false;
            } else {
                Intent intent = new Intent("com.samsung.android.settings.action.INTELLIGENT_FEATURES_GLOBAL_SETTINGS");
                intent.addFlags(268468224);
                context.startActivity(intent);
                postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_MORE_INTELLIGENT_FEATURES.toString());
                return true;
            }
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startMoreIntelligentFeatures failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startNotifications(Context context, boolean z) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.SharedAlbumNotificationActivity");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_SHARED_ALBUM_NOTIFICATIONS.toString(), z);
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startNotifications failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startObjectEraser(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, "com.sec.android.mimage.photoretouching.AIRemoverSettingsActivity");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_OBJECT_ERASER.toString());
            return true;
        } catch (ActivityNotFoundException unused) {
            guideDownloadPackage(context, ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, R$string.photo_editor);
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("startObjectEraser failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public void startOneDrive(Context context) {
        String str;
        if (!SamsungAccountManager.getInstance().hasAccount()) {
            OneDriveManager.getInstance().startMigration((Activity) context);
            str = AnalyticsDetail.EVENT_DETAIL_SETTING_CLOUD_SIGN_OUT.toString();
        } else if (CloudStateCompat.isPermissionRequired()) {
            SamsungCloudManager.getInstance().startPermissionSetting(context);
            str = AnalyticsDetail.EVENT_DETAIL_SETTING_CLOUD_PERMISSION_REQUIRED.toString();
        } else if (CloudStateCompat.isNone()) {
            OneDriveManager.getInstance().startMigration((Activity) context);
            str = AnalyticsDetail.EVENT_DETAIL_SETTING_CLOUD_SIGN_IN_SYNC_OFF.toString();
        } else if (CloudStateCompat.isUnlinked() || CloudStateCompat.isOneDriveLinkRequired()) {
            OneDriveManager.getInstance().startMigration((Activity) context);
            str = AnalyticsDetail.EVENT_DETAIL_SETTING_CLOUD_SIGN_OUT.toString();
        } else {
            if (CloudStateCompat.isMigrationAvailable() || (CloudStateCompat.isOneDriveAvailable() && CloudStateCompat.isShowTips())) {
                OneDriveManager.getInstance().startEosActivity(context, "GalleryWithOneDrive");
            } else {
                SamsungCloudManager.getInstance().startSetting((Activity) context);
            }
            if (CloudStateCompat.isSyncOnInPref()) {
                str = AnalyticsDetail.EVENT_DETAIL_SETTING_CLOUD_SIGN_IN_ONE_DRIVE_SYNC_ON.toString();
            } else {
                str = AnalyticsDetail.EVENT_DETAIL_SETTING_CLOUD_SIGN_IN_ONE_DRIVE_SYNC_OFF.toString();
            }
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_CLOUD.toString(), str);
    }

    public boolean startOpenSourceLicense(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.OpenSourceActivity");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_ABOUT_GALLERY_OPEN_SOURCE_LICENSES.toString());
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startOpenSourceLicense failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startPermissions(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.AppPermissionActivity");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_PERMISSION.toString());
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startPermissions failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startPhotoAssist(Context context) {
        try {
            Intent intent = new Intent("com.sec.android.intent.action.SEC_APPLICATION_SETTINGS");
            intent.addCategory("com.sec.android.intent.category.SEC_APPLICATION_SETTINGS");
            intent.setClassName(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, "com.sec.android.mimage.photoretouching.SettingsActivity");
            intent.putExtra(":settings:fragment_args_key", "magic_transform");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_PHOTO_ASSIST.toString());
            return true;
        } catch (ActivityNotFoundException unused) {
            guideDownloadPackage(context, ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, R$string.photo_editor);
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("startPhotoAssist failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startPhotoEditorSettings(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, "com.sec.android.mimage.photoretouching.SettingsActivity");
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_PHOTO_EDITOR_SETTINGS.toString());
            return true;
        } catch (ActivityNotFoundException unused) {
            guideDownloadPackage(context, ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, R$string.photo_editor);
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("startPhotoEditorSettings failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public boolean startPrivacyPolicy(Context context) {
        if (!new PrivacyPolicy().startActivity(context)) {
            return false;
        }
        if (SdkConfig.atLeast(SdkConfig.SEM.B_MR5)) {
            Blackboard.getActivity().semOverridePendingTransition(R$anim.activity_open_enter, R$anim.activity_open_exit);
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_PRIVACY_POLICY.toString());
        return true;
    }

    public boolean startRubinManager(Context context) {
        if (!RubinManager.start(context)) {
            return false;
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_CUSTOMIZATION_SERVICE.toString());
        return true;
    }

    public boolean startSamsungAccountSignIn(Activity activity) {
        if (activity == null) {
            try {
                Log.e("NavigatorDelegate", "startSamsungAccountSignIn failed, null activity");
                return false;
            } catch (ActivityNotFoundException | NullPointerException e) {
                j.u(e, new StringBuilder("startSamsungAccountSignIn failed e="), "NavigatorDelegate");
                return false;
            }
        } else {
            Intent intent = new Intent("com.msc.action.samsungaccount.SIGNIN_POPUP");
            intent.putExtra("mypackage", activity.getPackageName());
            intent.putExtra("client_id", "22n6hzkam0");
            intent.putExtra("OSP_VER", "OSP_02");
            intent.putExtra("MODE", "ADD_ACCOUNT");
            activity.startActivityForResult(intent, 799);
            return true;
        }
    }

    public void startSamsungCloud(Context context) {
        if (CloudStateCompat.isAccountRequired()) {
            OneDriveManager.getInstance().startEosActivity(context, "AccountRequired");
        } else if (CloudStateCompat.isAccountRequiredForNewGallery()) {
            OneDriveManager.getInstance().startEosActivity(context, "GalleryWithSamsungCloud");
        } else if (CloudStateCompat.isPermissionRequired()) {
            SamsungCloudManager.getInstance().startPermissionSetting(context);
        } else if (CloudStateCompat.isMigrationAvailable()) {
            OneDriveManager.getInstance().startEosActivity(context, "GalleryWithSamsungCloud");
        } else if (CloudStateCompat.isSubscriptionRequired() || CloudStateCompat.isSubscriptionRequiredLinkedBefore()) {
            SamsungCloudManager.getInstance().startUpselling(context);
        } else {
            SamsungCloudManager.getInstance().startSetting((Activity) context);
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_SAMSUNG_CLOUD.toString());
    }

    public boolean startSearchCustom(Context context) {
        return startSearchCustom(context, AnalyticsEventId.EVENT_SEARCH_CATEGORY_CUSTOMIZE);
    }

    public boolean startTencentCloud(Context context) {
        if (context == null) {
            return false;
        }
        TencentCloud tencentCloud = new TencentCloud();
        try {
            tencentCloud.startActivity(context);
            return true;
        } catch (ActivityNotFoundException unused) {
            handleActivityNotFound(context, tencentCloud.getPackageName(), R$string.tencent_cloud);
            return false;
        }
    }

    public boolean startTermsAndConditions(Context context) {
        try {
            Intent intent = new Intent("com.sec.orca.auth.ACTION_REQ_VIEW_TNC");
            intent.addFlags(PropertyOptions.DELETE_EXISTING);
            context.startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_ABOUT_GALLERY_SAMSUNG_SOCIAL_TERMS_AND_CONDITIONS.toString());
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startTermsAndConditions failed e="), "NavigatorDelegate");
            return false;
        }
    }

    private void postAnalyticsLog(String str, String str2) {
        if (str2 == null) {
            AnalyticsLogger.getInstance().postLog(this.mScreenId, str);
        } else {
            AnalyticsLogger.getInstance().postLog(this.mScreenId, str, str2);
        }
    }

    public final void guideDownloadPackage(Context context, String str, int i2) {
        guideDownloadPackage(context, str, context.getString(i2));
    }

    public boolean startSearchCustom(Context context, AnalyticsEventId analyticsEventId) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.GenericSettingActivity");
            intent.putExtra("locationKey", "location://searchsettings/custom");
            context.startActivity(intent);
            postAnalyticsLog(analyticsEventId.toString());
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startSearchCustom failed e="), "NavigatorDelegate");
            return false;
        }
    }

    public NavigateAppCmd(IBaseView iBaseView) {
        this(iBaseView.getScreenId());
    }

    private void postAnalyticsLog(String str, boolean z) {
        postAnalyticsLog(str, AnalyticsDetail.getOnOff(z));
    }
}
