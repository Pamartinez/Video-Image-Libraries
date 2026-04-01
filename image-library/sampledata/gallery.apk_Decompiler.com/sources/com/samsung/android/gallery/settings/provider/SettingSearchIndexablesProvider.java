package com.samsung.android.gallery.settings.provider;

import A.a;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$drawable;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.helper.KnoxRestrictions;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import fd.b;
import fd.c;
import java.util.ArrayList;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SettingSearchIndexablesProvider extends c {
    private Object[] appendSynonym(Context context, Object[] objArr, int... iArr) {
        if (iArr != null && iArr.length > 1) {
            ArrayList arrayList = new ArrayList();
            for (int string : iArr) {
                arrayList.add(context.getString(string));
            }
            objArr[5] = String.join(ArcCommonLog.TAG_COMMA, arrayList);
        }
        return objArr;
    }

    private String getRawTitle(int i2) {
        if (SdkConfig.atLeast(SdkConfig.SEM.S)) {
            return String.valueOf(i2);
        }
        return AppResources.getAppContext().getString(i2);
    }

    private void setAdvanceRawInfo(Context context, MatrixCursor matrixCursor) {
        int i2;
        SettingPreference settingPreference = SettingPreference.Trash;
        if (settingPreference.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.trash), settingPreference.key));
        }
        SettingPreference settingPreference2 = SettingPreference.CmhProvider;
        if (settingPreference2.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.cmh_provider_permission_title), settingPreference2.key));
        }
        SettingPreference settingPreference3 = SettingPreference.LocationAuth;
        if (settingPreference3.support(context)) {
            if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                i2 = R$string.show_location_info;
            } else {
                i2 = R$string.show_place_names;
            }
            matrixCursor.addRow(setRawInfo(getRawTitle(i2), settingPreference3.key));
        }
        SettingPreference settingPreference4 = SettingPreference.HeifAutoConv;
        if (settingPreference4.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.heif_auto_conversion_title), settingPreference4.key));
        }
        SettingPreference settingPreference5 = SettingPreference.Hdr10PlusAutoConv;
        if (settingPreference5.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.hdr10plus_auto_conversion_title), settingPreference5.key));
        }
    }

    private void setAlbumsRawInfo(Context context, MatrixCursor matrixCursor) {
        int i2;
        SettingPreference settingPreference = SettingPreference.EssentialAlbum;
        if (settingPreference.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.select_essential_albums), settingPreference.key));
        }
        SettingPreference settingPreference2 = SettingPreference.MergedAlbums;
        if (settingPreference2.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.merge_albums), settingPreference2.key));
        }
        SettingPreference settingPreference3 = SettingPreference.SharedNotification;
        if (settingPreference3.support(context)) {
            if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                i2 = R$string.shared_album_notification;
            } else {
                i2 = R$string.share_notification;
            }
            if (PreferenceFeatures.CHINA.SHARING_SERVICE_ENABLER) {
                int i7 = R$string.shared_album;
                matrixCursor.addRow(setRawInfo(getRawTitle(i7), SettingPreference.SharingServiceEnabler.key));
                matrixCursor.addRow(setRawInfo(getRawTitle(i2), settingPreference3.key, AppResources.getAppContext().getString(i7)));
            } else {
                matrixCursor.addRow(setRawInfo(getRawTitle(i2), settingPreference3.key));
            }
            String string = AppResources.getAppContext().getString(R$string.shared_album_notification);
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.shared_albums), SettingPreference.SharedNotificationAlbum.key, string, "com.android.gallery.settings.action.VIEW_SHARED_ALBUM_NOTIFICATIONS", "com.samsung.android.gallery.settings.activity.SharedAlbumNotificationActivity"));
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.new_posts), SettingPreference.SharedNotificationPosting.key, string, "com.android.gallery.settings.action.VIEW_SHARED_ALBUM_NOTIFICATIONS", "com.samsung.android.gallery.settings.activity.SharedAlbumNotificationActivity"));
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.member_updates), SettingPreference.SharedNotificationMember.key, string, "com.android.gallery.settings.action.VIEW_SHARED_ALBUM_NOTIFICATIONS", "com.samsung.android.gallery.settings.activity.SharedAlbumNotificationActivity"));
        }
    }

    private void setCloudRawInfo(Context context, MatrixCursor matrixCursor) {
        long currentTimeMillis = System.currentTimeMillis();
        CloudStateCompat.load(true);
        SamsungCloudCompat.checkSamsungCloud2(context);
        a.x(new StringBuilder("setCloudRawInfo#load +"), currentTimeMillis, "SearchIndexables");
        SettingPreference settingPreference = SettingPreference.CloudSync;
        if (settingPreference.support(context)) {
            if (!Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                matrixCursor.addRow(setRawInfo(context.getString(R$string.sync_with_one_drive), settingPreference.key));
            }
            SettingPreference settingPreference2 = SettingPreference.SdCardBackupCloud;
            if (settingPreference2.support(context) && (!CloudStateCompat.isLegacyServiceStatusRequired() ? CloudStateCompat.isMigrationAvailable() || CloudStateCompat.isOneDriveAvailable() || CloudStateCompat.isOneDriveLinkRequired() : CloudStateCompat.isMigrationSupported() || CloudStateCompat.isEnabled())) {
                matrixCursor.addRow(setRawInfo(getRawTitle(R$string.back_up_sd_card_to_one_drive), settingPreference2.key));
            }
            SettingPreference settingPreference3 = SettingPreference.BaiduCloud;
            if (settingPreference3.support(context)) {
                matrixCursor.addRow(setRawInfo(getRawTitle(R$string.baidu_cloud), settingPreference3.key));
            }
            SettingPreference settingPreference4 = SettingPreference.TencentCloud;
            if (settingPreference4.support(context)) {
                matrixCursor.addRow(setRawInfo(getRawTitle(R$string.tencent_cloud), settingPreference4.key));
            }
            SettingPreference settingPreference5 = SettingPreference.AttCloud;
            if (settingPreference5.support(context)) {
                matrixCursor.addRow(setRawInfo(getRawTitle(R$string.att_cloud), settingPreference5.key));
            }
        }
    }

    private void setEditingRawInfo(Context context, MatrixCursor matrixCursor) {
        SettingPreference settingPreference = SettingPreference.PhotoAssist;
        if (settingPreference.support(context)) {
            int i2 = R$string.photo_assist;
            matrixCursor.addRow(appendSynonym(context, setRawInfo(getRawTitle(i2), settingPreference.key), i2, R$string.ai_eraser_and_move, R$string.eraser, R$string.restyle, R$string.generative_edit_synonym));
        }
        SettingPreference settingPreference2 = SettingPreference.AudioEraser;
        if (settingPreference2.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.audio_eraser_settings), settingPreference2.key));
        }
        SettingPreference settingPreference3 = SettingPreference.AIEditSuggestions;
        if (settingPreference3.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.edit_suggestions), settingPreference3.key));
        }
        SettingPreference settingPreference4 = SettingPreference.DetailEnhancer;
        if (settingPreference4.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.detail_enhancer), settingPreference4.key));
        }
        SettingPreference settingPreference5 = SettingPreference.PhotoEditorSettings;
        if (settingPreference5.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.photo_editor_settings), settingPreference5.key));
        }
    }

    private void setEventServiceRawInfo(Context context, MatrixCursor matrixCursor) {
        SettingPreference settingPreference = SettingPreference.StoryAutoCreation;
        if (settingPreference.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.auto_create_event), settingPreference.key));
        }
        SettingPreference settingPreference2 = SettingPreference.Rubin;
        if (settingPreference2.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.customization_service), settingPreference2.key));
        }
        SettingPreference settingPreference3 = SettingPreference.StoryNotification;
        if (settingPreference3.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.story_notification), settingPreference3.key));
        }
    }

    private void setGallerySettingRawInfo(Context context, MatrixCursor matrixCursor) {
        matrixCursor.addRow(setRawInfo(getRawTitle(R$string.gallery_settings), SettingPreference.Settings.key, R$drawable.mainmenu_icon_gallery));
    }

    private void setGeneralRawInfo(Context context, MatrixCursor matrixCursor) {
        matrixCursor.addRow(setRawInfo(getRawTitle(R$string.about_gallery_setting), SettingPreference.About.key));
        SettingPreference settingPreference = SettingPreference.ContactUs;
        if (settingPreference.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.contact_us), settingPreference.key));
        }
    }

    private void setIntelligentFeaturesRawInfo(Context context, MatrixCursor matrixCursor) {
        SettingSearchIndexablesProvider settingSearchIndexablesProvider;
        SettingPreference settingPreference = SettingPreference.IntelligentFeatures;
        if (settingPreference.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.intelligent_features), settingPreference.key));
        }
        String string = AppResources.getAppContext().getString(R$string.intelligent_features);
        SettingPreference settingPreference2 = SettingPreference.EditSuggestions;
        if (settingPreference2.support(context)) {
            settingSearchIndexablesProvider = this;
            matrixCursor.addRow(settingSearchIndexablesProvider.setRawInfo(getRawTitle(R$string.edit_suggestions), settingPreference2.key, string, "com.android.gallery.settings.action.ACTION_VIEW_INTELLIGENT_FEATURES", "com.samsung.android.gallery.settings.activity.IntelligentFeaturesActivity"));
        } else {
            settingSearchIndexablesProvider = this;
        }
        SettingPreference settingPreference3 = SettingPreference.ObjectEraser;
        if (settingPreference3.support(context)) {
            matrixCursor.addRow(settingSearchIndexablesProvider.setRawInfo(settingSearchIndexablesProvider.getRawTitle(R$string.object_eraser), settingPreference3.key, string, "com.android.gallery.settings.action.ACTION_VIEW_INTELLIGENT_FEATURES", "com.samsung.android.gallery.settings.activity.IntelligentFeaturesActivity"));
        }
        SettingPreference settingPreference4 = SettingPreference.BestFace;
        if (settingPreference4.support(context)) {
            matrixCursor.addRow(settingSearchIndexablesProvider.setRawInfo(settingSearchIndexablesProvider.getRawTitle(R$string.opt_best_face), settingPreference4.key, string, "com.android.gallery.settings.action.ACTION_VIEW_INTELLIGENT_FEATURES", "com.samsung.android.gallery.settings.activity.IntelligentFeaturesActivity"));
        }
    }

    private Object[] setNonIndexableKeyInfo(String str) {
        return new Object[]{str};
    }

    private void setPrivacyRawInfo(Context context, MatrixCursor matrixCursor) {
        matrixCursor.addRow(setRawInfo(getRawTitle(R$string.privacy_policy), SettingPreference.PrivacyPolicy.key));
        matrixCursor.addRow(setRawInfo(getRawTitle(R$string.permissions), SettingPreference.AppPermission.key));
    }

    private Object[] setRawInfo(String str, String str2) {
        Object[] objArr = new Object[16];
        objArr[1] = str;
        objArr[12] = str2;
        objArr[6] = AppResources.getAppContext().getString(R$string.gallery_settings);
        objArr[7] = "com.samsung.android.gallery.settings.activity.SettingActivity";
        objArr[9] = "com.sec.android.intent.action.SEC_APPLICATION_SETTINGS";
        objArr[10] = "com.sec.android.gallery3d";
        objArr[11] = "com.samsung.android.gallery.settings.activity.SettingActivity";
        return objArr;
    }

    private void setSamsungCloudRawInfo(Context context, MatrixCursor matrixCursor) {
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        SamsungCloudCompat.checkSamsungCloud2(context);
        a.x(new StringBuilder("setSamsungCloudRawInfo#checkSamsungCloud2 +"), currentTimeMillis, "SearchIndexables");
        if (SettingPreference.SamsungCloudSync.support(context) && !CloudStateCompat.isServiceNotAvailable() && !CloudStateCompat.isOneDriveAvailable() && !CloudStateCompat.isOneDriveLinkRequired() && !CloudStateCompat.isLegacyServiceStatusRequired()) {
            if (CloudStateCompat.isAccountRequired()) {
                i2 = R$string.sync_with_cloud_account;
            } else {
                i2 = R$string.sync_with_samsung_cloud;
            }
            matrixCursor.addRow(setRawInfo(getRawTitle(i2), SettingPreference.CloudSync.key));
        }
    }

    private void setViewingRawInfo(Context context, MatrixCursor matrixCursor) {
        int i2;
        SettingPreference settingPreference = SettingPreference.PhotoHdr;
        if (settingPreference.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.super_hdr), settingPreference.key));
        }
        SettingPreference settingPreference2 = SettingPreference.MotionPhotoAutoPlay;
        if (settingPreference2.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.auto_play_motion_photo), settingPreference2.key));
        }
        SettingPreference settingPreference3 = SettingPreference.OpenInVideoPlayer;
        if (settingPreference3.support(context)) {
            matrixCursor.addRow(setRawInfo(getRawTitle(R$string.open_in_video_player), settingPreference3.key));
        }
        SettingPreference settingPreference4 = SettingPreference.Meitu;
        if (settingPreference4.support(context)) {
            if (Features.isEnabled(Features.SUPPORT_MEITU_IMAGE_TO_IMAGE)) {
                i2 = R$string.add_meitu_options_to_gallery;
            } else {
                i2 = R$string.meitu_posts_and_movies;
            }
            matrixCursor.addRow(setRawInfo(getRawTitle(i2), settingPreference4.key));
        }
    }

    public boolean onCreate() {
        return false;
    }

    public Cursor queryMenuData() {
        return queryRawData((String[]) null);
    }

    public Cursor queryNonIndexableKeys(String[] strArr) {
        Context appContext = AppResources.getAppContext();
        KnoxRestrictions of2 = KnoxRestrictions.of(appContext);
        MatrixCursor matrixCursor = new MatrixCursor(b.b);
        SettingPreference settingPreference = SettingPreference.PhotoAssist;
        if (settingPreference.support(appContext) && of2.contains("key_photo_editor")) {
            matrixCursor.addRow(setNonIndexableKeyInfo(settingPreference.key));
        }
        SettingPreference settingPreference2 = SettingPreference.AudioEraser;
        if (settingPreference2.support(appContext) && of2.contains("key_audio_eraser")) {
            matrixCursor.addRow(setNonIndexableKeyInfo(settingPreference2.key));
        }
        return matrixCursor;
    }

    public Cursor queryRawData(String[] strArr) {
        Log.d("SearchIndexables", "queryRawData");
        Context appContext = AppResources.getAppContext();
        MatrixCursor matrixCursor = new MatrixCursor(b.f1729a);
        setGallerySettingRawInfo(appContext, matrixCursor);
        setCloudRawInfo(appContext, matrixCursor);
        setSamsungCloudRawInfo(appContext, matrixCursor);
        setIntelligentFeaturesRawInfo(appContext, matrixCursor);
        setViewingRawInfo(appContext, matrixCursor);
        setEditingRawInfo(appContext, matrixCursor);
        setEventServiceRawInfo(appContext, matrixCursor);
        setAlbumsRawInfo(appContext, matrixCursor);
        setAdvanceRawInfo(appContext, matrixCursor);
        setPrivacyRawInfo(appContext, matrixCursor);
        setGeneralRawInfo(appContext, matrixCursor);
        return matrixCursor;
    }

    public Cursor queryXmlResources(String[] strArr) {
        return null;
    }

    public String secQueryGetFingerprint() {
        return PackageMonitorCompat.getVersionCode() + Locale.getDefault();
    }

    private Object[] setRawInfo(String str, String str2, int i2) {
        Object[] rawInfo = setRawInfo(str, str2);
        rawInfo[8] = Integer.valueOf(i2);
        return rawInfo;
    }

    private Object[] setRawInfo(String str, String str2, String str3) {
        Object[] rawInfo = setRawInfo(str, str2);
        rawInfo[6] = str3;
        return rawInfo;
    }

    private Object[] setRawInfo(String str, String str2, String str3, String str4, String str5) {
        Object[] rawInfo = setRawInfo(str, str2);
        rawInfo[6] = str3;
        rawInfo[9] = str4;
        rawInfo[11] = str5;
        return rawInfo;
    }
}
