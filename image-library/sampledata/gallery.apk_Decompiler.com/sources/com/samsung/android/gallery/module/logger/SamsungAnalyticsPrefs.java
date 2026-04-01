package com.samsung.android.gallery.module.logger;

import android.content.Context;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.support.analytics.AnalyticsStatusId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import n4.C0491c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SamsungAnalyticsPrefs {
    static final List<String> MISC_KEYS = new ArrayList<String>() {
        {
            addAll(List.of(PreferenceName.BUILD_ID.key(), PreferenceFeatures.PhotoStrip41.getKey(), "location://variable/currentv1"));
            if (SdkConfig.range(SdkConfig.SEM.T, SdkConfig.SEM.V)) {
                add(PreferenceName.AUTO_CREATE_EVENT_FROM_CMH.key());
            }
        }
    };
    public static final List<PreferenceName> SETTING_PREF_NAME = new ArrayList<PreferenceName>() {
        {
            PreferenceName preferenceName;
            Context appContext = AppResources.getAppContext();
            if (SettingPreference.CloudSync.support(appContext)) {
                add(PreferenceName.CLOUD_SWITCH_STATUS);
            }
            if (SettingPreference.MotionPhotoAutoPlay.support(appContext)) {
                add(PreferenceName.AUTO_PLAY_MOTION_PHOTO);
            }
            if (SettingPreference.Hdr10PlusAutoConv.support(appContext)) {
                add(PreferenceName.HDR10PLUS_AUTO_CONVERT);
            }
            if (SettingPreference.SharedNotification.support(appContext)) {
                add(PreferenceName.SHARED_ALBUM_NOTIFICATION);
                if (SettingPreference.SharedNotificationAlbum.support(appContext)) {
                    add(PreferenceName.KEY_NEW_ALBUMS);
                }
                if (SettingPreference.SharedNotificationPosting.support(appContext)) {
                    add(PreferenceName.KEY_NEW_POSTS);
                }
                if (SettingPreference.SharedNotificationMember.support(appContext)) {
                    add(PreferenceName.KEY_MEMBER_UPDATES);
                }
            }
            if (SettingPreference.LocationAuth.support(appContext)) {
                if (Features.isEnabled(Features.SUPPORT_SAMSUNG_PLACE)) {
                    preferenceName = PreferenceName.LOCATION_AUTH_V2;
                } else {
                    preferenceName = PreferenceName.LOCATION_AUTH;
                }
                add(preferenceName);
            }
            if (SettingPreference.Trash.support(appContext)) {
                add(PreferenceName.USE_TRASH);
            }
            add(PreferenceName.SPLIT_MODE);
            if (Features.isEnabled(Features.SUPPORT_SIMILAR_PHOTO)) {
                add(PreferenceName.TIMELINE_SIMILAR_PHOTO_MODE);
            }
            if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                if (SettingPreference.BaiduCloud.support(appContext)) {
                    add(PreferenceName.BAIDU_CLOUD_ENABLED);
                }
                if (SettingPreference.TencentCloud.support(appContext)) {
                    add(PreferenceName.TENCENT_CLOUD_ENABLED);
                }
                if (SettingPreference.Meitu.support(appContext)) {
                    add(PreferenceName.MEITU);
                }
                if (SettingPreference.CmhProvider.support(appContext)) {
                    add(PreferenceName.CMH_PROVIDER_PERMISSION_STATUS);
                }
            }
            if (SettingPreference.HeifAutoConv.support(appContext)) {
                add(PreferenceName.HEIF_AUTO_CONVERT);
            }
            if (SettingPreference.PhotoHdr.support(appContext)) {
                add(PreferenceName.PHOTO_HDR);
            }
            if (SettingPreference.OpenInVideoPlayer.support(appContext)) {
                add(PreferenceName.VIDEO_PLAYER_MODE);
            }
            if (SettingPreference.EssentialAlbum.support(appContext)) {
                add(PreferenceName.ESSENTIAL_ALBUMS);
            }
            if (SettingPreference.MergedAlbums.support(appContext)) {
                add(PreferenceName.ALBUM_NAME_MERGE);
            }
            if (SettingPreference.StoryNotification.support(appContext)) {
                add(PreferenceName.STORY_NOTIFICATION_STATE);
            }
            if (SettingPreference.DetailEnhancer.support(appContext)) {
                add(PreferenceName.AUTO_UPSCALE_IMAGE);
            }
            if (SettingPreference.StoryAutoCreation.support(appContext)) {
                add(PreferenceName.AUTO_CREATE_EVENT_FROM_CMH);
            }
        }
    };
    static final List<String> STATUS_KEYS = List.of(new String[]{AnalyticsStatusId.STATUS_ALBUM_COUNT.id(), AnalyticsStatusId.STATUS_GROUP_COUNT.id(), AnalyticsStatusId.STATUS_CREATED_BY_USER_ALBUM_COUNT.id(), AnalyticsStatusId.STATUS_MERGED_ALBUM_COUNT.id(), AnalyticsStatusId.STATUS_AUTO_UPDATED_ALBUM_COUNT.id(), AnalyticsStatusId.STATUS_SYSTEM_ALBUM_COUNT.id(), AnalyticsStatusId.STATUS_GALAXY_ALBUM_COUNT.id(), AnalyticsStatusId.STATUS_ESSENTIAL_ALBUM_COUNT.id(), AnalyticsStatusId.STATUS_CAMERA_ALBUM_COUNT_RATIO.id(), AnalyticsStatusId.STATUS_CAMERA_SD_ALBUM_COUNT_RATIO.id(), AnalyticsStatusId.STATUS_FAVORITES_IMAGE_COUNT.id(), AnalyticsStatusId.STATUS_FAVORITES_VIDEO_COUNT.id(), AnalyticsStatusId.STATUS_FAVORITES_NO_IMAGE_AND_VIDEO_COUNT.id(), AnalyticsStatusId.STATUS_STORY_ALBUM_COUNT.id(), AnalyticsStatusId.STATUS_MAIN_TAB.id(), AnalyticsStatusId.STATUS_ALBUM_VIEW_TYPE.id(), AnalyticsStatusId.STATUS_ALBUM_PICTURES_VIEW_TYPE.id(), AnalyticsStatusId.STATUS_TIME_VIEW_TYPE.id(), AnalyticsStatusId.STATUS_SPLIT_VIEW_STATE.id(), AnalyticsStatusId.STATUS_DEV_IMAGE_COUNT.id(), AnalyticsStatusId.STATUS_DEV_VIDEO_COUNT.id(), AnalyticsStatusId.STATUS_DEV_CLUSTER_COUNT.id(), AnalyticsStatusId.STATUS_DEV_BIG_ALBUM_FILE_COUNT.id(), AnalyticsStatusId.STATUS_DEV_SHARED_COUNT.id(), AnalyticsStatusId.STATUS_DEV_SDCARD_SIZE.id(), AnalyticsStatusId.STATUS_DEV_CLOUD_COUNT.id(), AnalyticsStatusId.STATUS_DEV_GROUP_MAX_DEPTH.id()});
    private static final CharSequence TAG = "SamsungAnalyticsPrefs";

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$writeSettingPrefValue$0(Map map, GalleryPreference galleryPreference, PreferenceName preferenceName) {
        String key = preferenceName.key();
        Object obj = map.get(key);
        if (obj != null) {
            galleryPreference.saveState(key, obj.toString());
        } else {
            galleryPreference.saveState(key, preferenceName.getDefault().toString());
        }
    }

    public static void writeSettingPrefValue() {
        GalleryPreference instanceAnalytics = GalleryPreference.getInstanceAnalytics();
        SETTING_PREF_NAME.forEach(new C0491c(16, GalleryPreference.getInstance().getAll(), instanceAnalytics));
    }
}
