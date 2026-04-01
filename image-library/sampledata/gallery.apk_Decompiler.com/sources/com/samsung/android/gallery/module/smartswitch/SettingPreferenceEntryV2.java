package com.samsung.android.gallery.module.smartswitch;

import android.text.TextUtils;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.module.settings.SettingPrefApi;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import i.C0212a;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
enum SettingPreferenceEntryV2 {
    BAIDU_CLOUD {
        public boolean support() {
            return Features.isEnabled(Features.SUPPORT_BAIDU_CLOUD);
        }
    },
    TENCENT_CLOUD {
        public boolean support() {
            return Features.isEnabled(Features.SUPPORT_TENCENT_CLOUD);
        }
    },
    AUTO_PLAY_MOTION_PHOTO(SettingPreference.MotionPhotoAutoPlay, PreferenceName.AUTO_PLAY_MOTION_PHOTO),
    OPEN_IN_VIDEO_PLAYER {
        public void restore(JSONObject jSONObject) {
            restoreAndNotify(jSONObject);
        }

        public boolean support() {
            return PreferenceFeatures.VideoPlayerFeature.SUPPORT_SETTING;
        }
    },
    DETAIL_ENHANCER {
        public void backup(JSONObject jSONObject) {
            backupString(jSONObject, GalleryPreference.getInstance().loadString(PreferenceName.AUTO_UPSCALE_IMAGE));
        }

        public void restore(JSONObject jSONObject) {
            restoreString(jSONObject, GalleryPreference.getInstance().loadString(PreferenceName.AUTO_UPSCALE_IMAGE));
        }

        public boolean support() {
            return Features.isEnabled(Features.SUPPORT_PHOTO_REMASTER_UPSCALE_ULTRA);
        }
    },
    MEITU {
        public boolean support() {
            return Features.isEnabled(Features.SUPPORT_MEITU);
        }
    },
    AUTO_CREATE_EVENT_FROM_CMH {
        public void restore(JSONObject jSONObject) {
            restoreAndNotify(jSONObject);
        }

        public boolean support() {
            if (!Features.isEnabled(Features.SUPPORT_AUTO_CREATE_STORY) || !Features.isEnabled(Features.SUPPORT_STORY) || Features.isEnabled(Features.IS_UPSM)) {
                return false;
            }
            return true;
        }
    },
    SHARED_ALBUM_NOTIFICATION(SettingPreference.SharedNotification, PreferenceName.SHARED_ALBUM_NOTIFICATION),
    KEY_NEW_ALBUMS(SettingPreference.SharedNotificationAlbum, PreferenceName.KEY_NEW_ALBUMS),
    KEY_NEW_POSTS(SettingPreference.SharedNotificationPosting, PreferenceName.KEY_NEW_POSTS),
    KEY_MEMBER_UPDATES(SettingPreference.SharedNotificationMember, PreferenceName.KEY_MEMBER_UPDATES),
    USE_TRASH {
        public void restore(JSONObject jSONObject) {
            if (!Features.isEnabled(Features.SUPPORT_ONE_TRASH)) {
                SettingPreferenceEntryV2.super.restore(jSONObject);
            }
        }
    },
    HEIF_AUTO_CONVERT {
        public boolean support() {
            return Features.isEnabled(Features.SUPPORT_HEIF_CONVERSION);
        }
    },
    HDR10PLUS_AUTO_CONVERT {
        public boolean support() {
            return Features.isEnabled(Features.SUPPORT_HDR10PLUS_CONVERSION);
        }
    },
    ESSENTIAL_ALBUMS {
        public boolean support() {
            return PreferenceFeatures.OneUi5x.MX_ALBUMS;
        }
    },
    ALBUM_NAME_MERGE {
        public boolean support() {
            return PreferenceFeatures.OneUi5x.MX_ALBUMS;
        }
    },
    STORY_NOTIFICATION_STATE {
        public void restore(JSONObject jSONObject) {
            restoreAndNotify(jSONObject);
        }

        public boolean support() {
            return Features.isEnabled(Features.SUPPORT_STORY_NOTIFICATION);
        }
    },
    PHOTO_HDR {
        public boolean support() {
            return SuperHdrConfig.SUPPORT;
        }
    };
    
    private static final String TAG = "SettingPreferenceEntryV2";
    Boolean mData;
    final SettingPrefApi mPrefApi;
    final PreferenceName mPrefName;
    String mStringData;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.SettingPreferenceEntryV2$14  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass14 extends SettingPreferenceEntryV2 {
        String defaultValue;

        public /* synthetic */ AnonymousClass14(MiscSettingPreference miscSettingPreference, PreferenceName preferenceName) {
            this("SEARCH_CATEGORY_DISABLED", 18, miscSettingPreference, preferenceName);
        }

        public void backup(JSONObject jSONObject) {
            PreferenceName preferenceName;
            GalleryPreference instance = GalleryPreference.getInstance();
            if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
                preferenceName = PreferenceName.COLLECTION_CAT_DISABLED;
            } else {
                preferenceName = PreferenceName.SEARCH_CAT_DISABLED;
            }
            backupString(jSONObject, instance.loadString(preferenceName.key(), this.defaultValue));
        }

        public void restore(JSONObject jSONObject) {
            restoreString(jSONObject, this.defaultValue);
        }

        public boolean support() {
            return PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
        }

        private AnonymousClass14(String str, int i2, SettingPrefApi settingPrefApi, PreferenceName preferenceName) {
            super(str, i2, settingPrefApi, preferenceName, 0);
            this.defaultValue = "MyTag";
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.SettingPreferenceEntryV2$15  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass15 extends SettingPreferenceEntryV2 {
        public /* synthetic */ AnonymousClass15(MiscSettingPreference miscSettingPreference, PreferenceName preferenceName) {
            this("SEARCH_CATEGORY_ORDER", 19, miscSettingPreference, preferenceName);
        }

        public void backup(JSONObject jSONObject) {
            PreferenceName preferenceName;
            GalleryPreference instance = GalleryPreference.getInstance();
            if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
                preferenceName = PreferenceName.COLLECTION_CAT_ORDER;
            } else {
                preferenceName = PreferenceName.SEARCH_CAT_ORDER;
            }
            backupString(jSONObject, instance.loadString(preferenceName.key(), (String) null));
        }

        public void restore(JSONObject jSONObject) {
            restoreString(jSONObject, (String) null);
        }

        public boolean support() {
            return PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
        }

        private AnonymousClass15(String str, int i2, SettingPrefApi settingPrefApi, PreferenceName preferenceName) {
            super(str, i2, settingPrefApi, preferenceName, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.SettingPreferenceEntryV2$16  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass16 extends SettingPreferenceEntryV2 {
        public /* synthetic */ AnonymousClass16(MiscSettingPreference miscSettingPreference, PreferenceName preferenceName) {
            this("SEARCH_SHOW_RECENT_SEARCHES", 20, miscSettingPreference, preferenceName);
        }

        public void restore(JSONObject jSONObject) {
            restoreAndNotify(jSONObject);
        }

        private AnonymousClass16(String str, int i2, SettingPrefApi settingPrefApi, PreferenceName preferenceName) {
            super(str, i2, settingPrefApi, preferenceName, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.SettingPreferenceEntryV2$17  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass17 extends SettingPreferenceEntryV2 {
        public /* synthetic */ AnonymousClass17(MiscSettingPreference miscSettingPreference, PreferenceName preferenceName) {
            this("SEARCH_SHOW_SUGGESTIONS", 21, miscSettingPreference, preferenceName);
        }

        public void restore(JSONObject jSONObject) {
            restoreAndNotify(jSONObject);
        }

        private AnonymousClass17(String str, int i2, SettingPrefApi settingPrefApi, PreferenceName preferenceName) {
            super(str, i2, settingPrefApi, preferenceName, 0);
        }
    }

    static {
        PreferenceName preferenceName;
        PreferenceName preferenceName2;
        BAIDU_CLOUD = new AnonymousClass1(SettingPreference.BaiduCloud, PreferenceName.BAIDU_CLOUD_ENABLED);
        TENCENT_CLOUD = new AnonymousClass2(SettingPreference.TencentCloud, PreferenceName.TENCENT_CLOUD_ENABLED);
        AUTO_PLAY_MOTION_PHOTO = new SettingPreferenceEntryV2("AUTO_PLAY_MOTION_PHOTO", 2, SettingPreference.MotionPhotoAutoPlay, PreferenceName.AUTO_PLAY_MOTION_PHOTO);
        OPEN_IN_VIDEO_PLAYER = new AnonymousClass3(SettingPreference.OpenInVideoPlayer, PreferenceName.VIDEO_PLAYER_MODE);
        DETAIL_ENHANCER = new AnonymousClass4(SettingPreference.DetailEnhancer, PreferenceName.AUTO_UPSCALE_IMAGE);
        MEITU = new AnonymousClass5(SettingPreference.Meitu, PreferenceName.MEITU);
        AUTO_CREATE_EVENT_FROM_CMH = new AnonymousClass6(SettingPreference.StoryAutoCreation, PreferenceName.AUTO_CREATE_EVENT_FROM_CMH);
        SHARED_ALBUM_NOTIFICATION = new SettingPreferenceEntryV2("SHARED_ALBUM_NOTIFICATION", 7, SettingPreference.SharedNotification, PreferenceName.SHARED_ALBUM_NOTIFICATION);
        KEY_NEW_ALBUMS = new SettingPreferenceEntryV2("KEY_NEW_ALBUMS", 8, SettingPreference.SharedNotificationAlbum, PreferenceName.KEY_NEW_ALBUMS);
        KEY_NEW_POSTS = new SettingPreferenceEntryV2("KEY_NEW_POSTS", 9, SettingPreference.SharedNotificationPosting, PreferenceName.KEY_NEW_POSTS);
        KEY_MEMBER_UPDATES = new SettingPreferenceEntryV2("KEY_MEMBER_UPDATES", 10, SettingPreference.SharedNotificationMember, PreferenceName.KEY_MEMBER_UPDATES);
        USE_TRASH = new AnonymousClass7(SettingPreference.Trash, PreferenceName.USE_TRASH);
        HEIF_AUTO_CONVERT = new AnonymousClass8(SettingPreference.HeifAutoConv, PreferenceName.HEIF_AUTO_CONVERT);
        HDR10PLUS_AUTO_CONVERT = new AnonymousClass9(SettingPreference.Hdr10PlusAutoConv, PreferenceName.HDR10PLUS_AUTO_CONVERT);
        ESSENTIAL_ALBUMS = new AnonymousClass10(SettingPreference.EssentialAlbum, PreferenceName.ESSENTIAL_ALBUMS);
        ALBUM_NAME_MERGE = new AnonymousClass11(SettingPreference.MergedAlbums, PreferenceName.ALBUM_NAME_MERGE);
        STORY_NOTIFICATION_STATE = new AnonymousClass12(SettingPreference.StoryNotification, PreferenceName.STORY_NOTIFICATION_STATE);
        PHOTO_HDR = new AnonymousClass13(SettingPreference.PhotoHdr, PreferenceName.PHOTO_HDR);
        MiscSettingPreference miscSettingPreference = MiscSettingPreference.Customize;
        boolean z = PreferenceFeatures.OneUi8x.COLLECTION_TAB;
        if (z) {
            preferenceName = PreferenceName.COLLECTION_CAT_DISABLED;
        } else {
            preferenceName = PreferenceName.SEARCH_CAT_DISABLED;
        }
        SEARCH_CATEGORY_DISABLED = new AnonymousClass14(miscSettingPreference, preferenceName);
        if (z) {
            preferenceName2 = PreferenceName.COLLECTION_CAT_ORDER;
        } else {
            preferenceName2 = PreferenceName.SEARCH_CAT_ORDER;
        }
        SEARCH_CATEGORY_ORDER = new AnonymousClass15(miscSettingPreference, preferenceName2);
        SEARCH_SHOW_RECENT_SEARCHES = new AnonymousClass16(MiscSettingPreference.SearchSaveRecent, PreferenceName.SAVE_RECENT_SEARCHES);
        SEARCH_SHOW_SUGGESTIONS = new AnonymousClass17(MiscSettingPreference.SearchShowSuggestions, PreferenceName.SHOW_SUGGESTIONS);
        $VALUES = $values();
    }

    private PreferenceFeatures getPreferenceFeature() {
        return (PreferenceFeatures) Arrays.stream(PreferenceFeatures.values()).filter(new d(this, 1)).findFirst().orElse((Object) null);
    }

    public void backup(JSONObject jSONObject) {
        this.mData = Boolean.valueOf(this.mPrefApi.isEnabled());
        try {
            jSONObject.put(this.mPrefName.key(), this.mData);
            Log.v(TAG, "backup " + this.mPrefName.key() + "#" + this.mData);
        } catch (JSONException e) {
            Log.e(TAG, "no key " + e.getMessage());
        }
    }

    public void backupString(JSONObject jSONObject, String str) {
        this.mStringData = str;
        try {
            jSONObject.put(this.mPrefName.key(), this.mStringData);
            Log.v(TAG, "backupString " + this.mPrefName.key() + "#" + this.mStringData);
        } catch (JSONException e) {
            Log.e(TAG, "no key " + e.getMessage());
        }
    }

    public void getFromJson(JSONObject jSONObject) {
        try {
            this.mData = Boolean.valueOf(jSONObject.getBoolean(this.mPrefName.key()));
        } catch (JSONException e) {
            this.mData = Boolean.valueOf(this.mPrefApi.isEnabled());
            Log.e((CharSequence) TAG, "no value " + this.mPrefName, e.getMessage());
        }
    }

    public void restore(JSONObject jSONObject) {
        getFromJson(jSONObject);
        if (this.mData != null) {
            PreferenceFeatures preferenceFeature = getPreferenceFeature();
            if (preferenceFeature == null || preferenceFeature.isEnabled() == this.mData.booleanValue()) {
                Log.v(TAG, "do not need to restore " + this.mPrefName.key() + "#" + this.mData);
                return;
            }
            Log.v(TAG, "restore " + this.mPrefName.key() + "#" + this.mData);
            GalleryPreference.getInstance().saveState(this.mPrefName, this.mData.booleanValue());
            PreferenceFeatures.recycle(preferenceFeature);
        }
    }

    public void restoreAndNotify(JSONObject jSONObject) {
        getFromJson(jSONObject);
        if (this.mData == null) {
            return;
        }
        if (this.mPrefApi.isEnabled() == this.mData.booleanValue()) {
            Log.v(TAG, "do not need to restoreAndNotify " + this.mPrefName.key() + "#" + this.mData);
            return;
        }
        Log.v(TAG, "restoreAndNotify " + this.mPrefName.key() + "#" + this.mData);
        this.mPrefApi.setAndNotifyIfChanged(AppResources.getAppContext(), this.mData.booleanValue());
    }

    public void restoreString(JSONObject jSONObject, String str) {
        String key = this.mPrefName.key();
        try {
            this.mStringData = jSONObject.getString(key);
            if (!TextUtils.equals(GalleryPreference.getInstance().loadString(key, str), this.mStringData)) {
                GalleryPreference.getInstance().saveState(key, this.mStringData);
                Log.v(TAG, "restoreString " + key + "#" + this.mStringData);
                return;
            }
            Log.v(TAG, "do not need to restoreString " + key + "#" + this.mStringData);
        } catch (JSONException e) {
            Log.w((CharSequence) TAG, C0212a.l("No value for ", key), e.getMessage());
        }
    }

    public boolean support() {
        return true;
    }

    private SettingPreferenceEntryV2(SettingPrefApi settingPrefApi, PreferenceName preferenceName) {
        this.mPrefApi = settingPrefApi;
        this.mPrefName = preferenceName;
    }

    public static JSONObject backup() {
        JSONObject jSONObject = new JSONObject();
        Arrays.stream(values()).filter(new a(2)).forEach(new b(jSONObject, 1));
        return jSONObject;
    }

    public static void restore(String str) {
        Arrays.stream(values()).filter(new a(2)).forEach(new b(new JSONObject(str), 2));
    }
}
