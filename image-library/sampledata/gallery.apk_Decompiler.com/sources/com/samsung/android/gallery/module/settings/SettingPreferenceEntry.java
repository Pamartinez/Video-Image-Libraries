package com.samsung.android.gallery.module.settings;

import android.content.Context;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SettingPreferenceEntry {
    private static final boolean IS_CHINESE_DEVICE = Features.isEnabled(Features.IS_CHINESE_DEVICE);
    private static final boolean SUPPORT_AUTO_CREATE_STORY = Features.isEnabled(Features.SUPPORT_AUTO_CREATE_STORY);
    private static final boolean SUPPORT_HDR10PLUS_CONVERSION = Features.isEnabled(Features.SUPPORT_HDR10PLUS_CONVERSION);
    private static final boolean SUPPORT_HEIF_CONVERSION = Features.isEnabled(Features.SUPPORT_HEIF_CONVERSION);
    private static final boolean SUPPORT_MEITU = Features.isEnabled(Features.SUPPORT_MEITU);
    private static final boolean SUPPORT_MX_ALBUMS = PreferenceFeatures.OneUi5x.MX_ALBUMS;
    private static final boolean SUPPORT_ONE_TRASH = Features.isEnabled(Features.SUPPORT_ONE_TRASH);
    private static final boolean SUPPORT_STORY_NOTIFICATION = Features.isEnabled(Features.SUPPORT_STORY_NOTIFICATION);
    private Boolean ALBUM_NAME_MERGE;
    private Boolean AUTO_CREATE_EVENT_FROM_CMH;
    private Boolean AUTO_PLAY_MOTION_PHOTO;
    private Boolean BAIDU_CLOUD;
    private Boolean ESSENTIAL_ALBUMS;
    private Boolean HDR10PLUS_AUTO_CONVERT;
    private Boolean HEIF_AUTO_CONVERT;
    private Boolean KEY_MEMBER_UPDATES;
    private Boolean KEY_NEW_ALBUMS;
    private Boolean KEY_NEW_POSTS;
    private Boolean MEITU;
    private Boolean PHOTO_HDR;
    private Boolean SHARED_ALBUM_NOTIFICATION;
    private Boolean STORY_NOTIFICATION_STATE;
    private Boolean TENCENT_CLOUD;
    private Boolean USE_TRASH;
    private Boolean mRestoredValue;

    private Boolean getPreference(JSONObject jSONObject, PreferenceName preferenceName) {
        if (hasPreferenceBooleanValue(jSONObject, preferenceName)) {
            return this.mRestoredValue;
        }
        return null;
    }

    private boolean hasPreferenceBooleanValue(JSONObject jSONObject, PreferenceName preferenceName) {
        try {
            this.mRestoredValue = Boolean.valueOf(jSONObject.getBoolean(preferenceName.key()));
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("SettingPreferenceEntry", "No value for " + preferenceName.key());
            this.mRestoredValue = null;
            return false;
        }
    }

    private Boolean isEnabled(SettingPrefApi settingPrefApi) {
        return Boolean.valueOf(settingPrefApi.isEnabled());
    }

    private void putPreference(JSONObject jSONObject, PreferenceName preferenceName, Boolean bool) {
        if (bool != null) {
            jSONObject.put(preferenceName.key(), bool);
            Log.d("SettingPreferenceEntry", "putPreference " + preferenceName.key() + "#" + bool);
        }
    }

    private void savePreference(PreferenceFeatures preferenceFeatures, PreferenceName preferenceName, Boolean bool) {
        if (bool != null && preferenceFeatures.isEnabled() != bool.booleanValue()) {
            GalleryPreference.getInstance().saveState(preferenceName, bool.booleanValue());
            PreferenceFeatures.recycle(preferenceFeatures);
            Log.d("SettingPreferenceEntry", "savePreference " + preferenceName.key() + "#" + bool);
        }
    }

    public JSONObject buildJson() {
        JSONObject jSONObject = new JSONObject();
        if (IS_CHINESE_DEVICE) {
            putPreference(jSONObject, PreferenceName.BAIDU_CLOUD_ENABLED, this.BAIDU_CLOUD);
            putPreference(jSONObject, PreferenceName.TENCENT_CLOUD_ENABLED, this.TENCENT_CLOUD);
        }
        putPreference(jSONObject, PreferenceName.AUTO_PLAY_MOTION_PHOTO, this.AUTO_PLAY_MOTION_PHOTO);
        if (SUPPORT_MEITU) {
            putPreference(jSONObject, PreferenceName.MEITU, this.MEITU);
        }
        if (SUPPORT_AUTO_CREATE_STORY) {
            putPreference(jSONObject, PreferenceName.AUTO_CREATE_EVENT_FROM_CMH, this.AUTO_CREATE_EVENT_FROM_CMH);
        }
        putPreference(jSONObject, PreferenceName.SHARED_ALBUM_NOTIFICATION, this.SHARED_ALBUM_NOTIFICATION);
        putPreference(jSONObject, PreferenceName.KEY_NEW_ALBUMS, this.KEY_NEW_ALBUMS);
        putPreference(jSONObject, PreferenceName.KEY_NEW_POSTS, this.KEY_NEW_POSTS);
        putPreference(jSONObject, PreferenceName.KEY_MEMBER_UPDATES, this.KEY_MEMBER_UPDATES);
        putPreference(jSONObject, PreferenceName.USE_TRASH, this.USE_TRASH);
        if (SUPPORT_HEIF_CONVERSION) {
            putPreference(jSONObject, PreferenceName.HEIF_AUTO_CONVERT, this.HEIF_AUTO_CONVERT);
        }
        if (SUPPORT_HDR10PLUS_CONVERSION) {
            putPreference(jSONObject, PreferenceName.HDR10PLUS_AUTO_CONVERT, this.HDR10PLUS_AUTO_CONVERT);
        }
        if (SUPPORT_MX_ALBUMS) {
            putPreference(jSONObject, PreferenceName.ESSENTIAL_ALBUMS, this.ESSENTIAL_ALBUMS);
            putPreference(jSONObject, PreferenceName.ALBUM_NAME_MERGE, this.ALBUM_NAME_MERGE);
        }
        if (SUPPORT_STORY_NOTIFICATION) {
            putPreference(jSONObject, PreferenceName.STORY_NOTIFICATION_STATE, this.STORY_NOTIFICATION_STATE);
        }
        if (SuperHdrConfig.SUPPORT) {
            putPreference(jSONObject, PreferenceName.PHOTO_HDR, this.PHOTO_HDR);
        }
        return jSONObject;
    }

    public SettingPreferenceEntry loadJson(JSONObject jSONObject) {
        if (IS_CHINESE_DEVICE) {
            this.BAIDU_CLOUD = getPreference(jSONObject, PreferenceName.BAIDU_CLOUD_ENABLED);
            this.TENCENT_CLOUD = getPreference(jSONObject, PreferenceName.TENCENT_CLOUD_ENABLED);
        }
        this.AUTO_PLAY_MOTION_PHOTO = getPreference(jSONObject, PreferenceName.AUTO_PLAY_MOTION_PHOTO);
        if (SUPPORT_MEITU) {
            this.MEITU = getPreference(jSONObject, PreferenceName.MEITU);
        }
        if (SUPPORT_AUTO_CREATE_STORY) {
            this.AUTO_CREATE_EVENT_FROM_CMH = getPreference(jSONObject, PreferenceName.AUTO_CREATE_EVENT_FROM_CMH);
        }
        this.SHARED_ALBUM_NOTIFICATION = getPreference(jSONObject, PreferenceName.SHARED_ALBUM_NOTIFICATION);
        this.KEY_NEW_ALBUMS = getPreference(jSONObject, PreferenceName.KEY_NEW_ALBUMS);
        this.KEY_NEW_POSTS = getPreference(jSONObject, PreferenceName.KEY_NEW_POSTS);
        this.KEY_MEMBER_UPDATES = getPreference(jSONObject, PreferenceName.KEY_MEMBER_UPDATES);
        if (!SUPPORT_ONE_TRASH) {
            this.USE_TRASH = getPreference(jSONObject, PreferenceName.USE_TRASH);
        }
        if (SUPPORT_HEIF_CONVERSION) {
            this.HEIF_AUTO_CONVERT = getPreference(jSONObject, PreferenceName.HEIF_AUTO_CONVERT);
        }
        if (SUPPORT_HDR10PLUS_CONVERSION) {
            this.HDR10PLUS_AUTO_CONVERT = getPreference(jSONObject, PreferenceName.HDR10PLUS_AUTO_CONVERT);
        }
        if (SUPPORT_MX_ALBUMS) {
            this.ESSENTIAL_ALBUMS = getPreference(jSONObject, PreferenceName.ESSENTIAL_ALBUMS);
            this.ALBUM_NAME_MERGE = getPreference(jSONObject, PreferenceName.ALBUM_NAME_MERGE);
        }
        if (SUPPORT_STORY_NOTIFICATION) {
            this.STORY_NOTIFICATION_STATE = getPreference(jSONObject, PreferenceName.STORY_NOTIFICATION_STATE);
        }
        if (SuperHdrConfig.SUPPORT) {
            this.PHOTO_HDR = getPreference(jSONObject, PreferenceName.PHOTO_HDR);
        }
        return this;
    }

    public SettingPreferenceEntry loadPreference() {
        Log.d("SettingPreferenceEntry", "loadPreference");
        if (IS_CHINESE_DEVICE) {
            this.BAIDU_CLOUD = isEnabled(SettingPreference.BaiduCloud);
            this.TENCENT_CLOUD = isEnabled(SettingPreference.TencentCloud);
        }
        this.AUTO_PLAY_MOTION_PHOTO = isEnabled(SettingPreference.MotionPhotoAutoPlay);
        if (SUPPORT_MEITU) {
            this.MEITU = isEnabled(SettingPreference.Meitu);
        }
        if (SUPPORT_AUTO_CREATE_STORY) {
            this.AUTO_CREATE_EVENT_FROM_CMH = isEnabled(SettingPreference.StoryAutoCreation);
        }
        this.SHARED_ALBUM_NOTIFICATION = isEnabled(SettingPreference.SharedNotification);
        this.KEY_NEW_ALBUMS = isEnabled(SettingPreference.SharedNotificationAlbum);
        this.KEY_NEW_POSTS = isEnabled(SettingPreference.SharedNotificationPosting);
        this.KEY_MEMBER_UPDATES = isEnabled(SettingPreference.SharedNotificationMember);
        this.USE_TRASH = isEnabled(SettingPreference.Trash);
        if (SUPPORT_HEIF_CONVERSION) {
            this.HEIF_AUTO_CONVERT = isEnabled(SettingPreference.HeifAutoConv);
        }
        if (SUPPORT_HDR10PLUS_CONVERSION) {
            this.HDR10PLUS_AUTO_CONVERT = isEnabled(SettingPreference.Hdr10PlusAutoConv);
        }
        if (SUPPORT_MX_ALBUMS) {
            this.ESSENTIAL_ALBUMS = isEnabled(SettingPreference.EssentialAlbum);
            this.ALBUM_NAME_MERGE = isEnabled(SettingPreference.MergedAlbums);
        }
        if (SUPPORT_STORY_NOTIFICATION) {
            this.STORY_NOTIFICATION_STATE = isEnabled(SettingPreference.StoryNotification);
        }
        if (SuperHdrConfig.SUPPORT) {
            this.PHOTO_HDR = isEnabled(SettingPreference.PhotoHdr);
        }
        return this;
    }

    public void restoreStoryOptions() {
        if (SUPPORT_AUTO_CREATE_STORY) {
            SettingPreference.StoryAutoCreation.restoreChanged();
        }
        if (SUPPORT_STORY_NOTIFICATION) {
            SettingPreference.StoryNotification.restoreChanged();
        }
    }

    public void save(Context context) {
        Boolean bool;
        Log.d("SettingPreferenceEntry", "save");
        if (IS_CHINESE_DEVICE) {
            savePreference(PreferenceFeatures.BaiduCloudEnabled, PreferenceName.BAIDU_CLOUD_ENABLED, this.BAIDU_CLOUD);
            savePreference(PreferenceFeatures.TencentCloudEnabled, PreferenceName.TENCENT_CLOUD_ENABLED, this.TENCENT_CLOUD);
        }
        savePreference(PreferenceFeatures.AutoPlayMotionPhoto, PreferenceName.AUTO_PLAY_MOTION_PHOTO, this.AUTO_PLAY_MOTION_PHOTO);
        if (SUPPORT_MEITU) {
            savePreference(PreferenceFeatures.Meitu, PreferenceName.MEITU, this.MEITU);
        }
        if (SUPPORT_AUTO_CREATE_STORY && (bool = this.AUTO_CREATE_EVENT_FROM_CMH) != null) {
            SettingPreference.StoryAutoCreation.setAndNotifyIfChanged(context, bool.booleanValue());
        }
        savePreference(PreferenceFeatures.SharingNotification, PreferenceName.SHARED_ALBUM_NOTIFICATION, this.SHARED_ALBUM_NOTIFICATION);
        savePreference(PreferenceFeatures.SharingAlbumNewAlbumNotification, PreferenceName.KEY_NEW_ALBUMS, this.KEY_NEW_ALBUMS);
        savePreference(PreferenceFeatures.SharingAlbumNewPostNotification, PreferenceName.KEY_NEW_POSTS, this.KEY_NEW_POSTS);
        savePreference(PreferenceFeatures.SharingAlbumMemberUpdatesNotification, PreferenceName.KEY_MEMBER_UPDATES, this.KEY_MEMBER_UPDATES);
        if (!SUPPORT_ONE_TRASH) {
            savePreference(PreferenceFeatures.UseTrash, PreferenceName.USE_TRASH, this.USE_TRASH);
        }
        if (SUPPORT_HEIF_CONVERSION) {
            savePreference(PreferenceFeatures.HeifAutoConvert, PreferenceName.HEIF_AUTO_CONVERT, this.HEIF_AUTO_CONVERT);
        }
        if (SUPPORT_HDR10PLUS_CONVERSION) {
            savePreference(PreferenceFeatures.HDR10PlusAutoConvert, PreferenceName.HDR10PLUS_AUTO_CONVERT, this.HDR10PLUS_AUTO_CONVERT);
        }
        if (SUPPORT_MX_ALBUMS) {
            savePreference(PreferenceFeatures.EssentialAlbums, PreferenceName.ESSENTIAL_ALBUMS, this.ESSENTIAL_ALBUMS);
            savePreference(PreferenceFeatures.MxAlbumsMergeNames, PreferenceName.ALBUM_NAME_MERGE, this.ALBUM_NAME_MERGE);
        }
        if (SUPPORT_STORY_NOTIFICATION) {
            SettingPreference.StoryNotification.setAndNotifyIfChanged(context, this.STORY_NOTIFICATION_STATE.booleanValue());
        }
        if (SuperHdrConfig.SUPPORT) {
            savePreference(PreferenceFeatures.ShowHdrImages, PreferenceName.PHOTO_HDR, this.PHOTO_HDR);
        }
    }
}
