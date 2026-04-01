package com.samsung.android.gallery.support.utils;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum PreferenceName {
    TIMELINE_GRID_SIZE("timeViewColCnt", -3),
    TIMELINE_GRID_SIZE_V30("timeViewColCntV30", -3),
    TIMELINE_GRID_SIZE_V60("timeViewColCntV60", -3),
    TIMELINE_GRID_SIZE_V70("timeViewColCntV70"),
    ALBUMS_GRID_SIZE("albumViewColCnt"),
    ALBUM_PICTURES_GRID_SIZE("albumPicturesViewColCnt", 1),
    ALBUM_PICTURES_GRID_SIZE70("albumPicturesViewColCntV70"),
    SHARING_PICTURES_GRID_SIZE("sharedViewColCnt", 1),
    SHARING_PICTURES_GRID_SIZE70("sharedViewColCntV70"),
    STORY_PICTURES_GRID_SIZE("storyPicturesViewColCnt"),
    STORY_HIGHLIGHT_LIST_GRID_SIZE("storyHighlightListViewColCnt"),
    SEARCH_PICTURES_GRID_SIZE("searchPicturesViewColCnt", 1),
    SEARCH_PICTURES_GRID_SIZE70("searchPicturesViewColCntV70"),
    REMASTER_PICTURES_GRID_SIZE("remasterPicturesViewColCnt"),
    STORIES_GRID_SIZE("storiesViewColCnt"),
    SUGGESTION_CLEAN_OUT_PICTURES_GRID_SIZE("cleanOutPicturesViewColCnt"),
    CLEAN_PICTURES_GRID_SIZE("cleanPicturesViewColCnt"),
    VIRTUAL_ALBUM_PICTURES_GRID_SIZE("virtualAlbumViewColCnt"),
    TRASH_PICTURES_GRID_SIZE("trashColCnt"),
    TRASH_PICTURES_GRID_SIZE70("trashColCntV70"),
    PICTURES_PICKER_GRID_SIZE("PicturesPickerViewColCnt"),
    PICTURES_PICKER_GRID_SIZE70("PicturesPickerViewColCntV70"),
    TIMELINE_SIMILAR_PHOTO_MODE("timelineSimilarPhotoMode", r10, 1),
    TIMELINE_SIMILAR_PHOTO_EXIST("timelineSimilarPhotoExist"),
    PHOTO_HDR("photo_hdr", r9),
    AUTO_PLAY_MOTION_PHOTO("auto_play_motion_photo", r9, 1),
    MEITU("meitu_posts_and_movies", r9, 1),
    VIDEO_PLAYER_MODE("VideoPlayerMode", 0),
    SORT_BY_ALBUM("sort_by_album"),
    SORT_BY_SHARING("sort_by_sharing"),
    SAVE_ALBUM_COVER("save_album_cover"),
    SHOW_ALBUM_INLINE_CUE("show_album_inline_cue"),
    SORT_BY("sort_by"),
    SPLIT_MODE("split_mode", r11, 1),
    SHARED_ALBUM_NOTIFICATION("shared_album_notification", r0, 1),
    SHARE_SERVICE_V2_STATUS("mde_sharing_service_status"),
    SHARE_SERVICE_STATUS("shared_service_status"),
    LOCATION_AUTH("location_auth", r11, 1),
    LOCATION_AUTH_V2("location_auth_v2", r11, 1),
    KEY_NEW_ALBUMS("new_albums", r9, 1),
    KEY_NEW_POSTS("new_posts", r9, 1),
    KEY_MEMBER_UPDATES("member_updates", r9, 1),
    SHOW_NOTIFICATION_MAIN_SWITCH("show_notification_main_switch"),
    SHARED_SORT_BY("shared_sort_by"),
    MDE_QUOTA_TYPE("mde_quota_type"),
    SUGGESTION_LIST_ORDER("suggestion_list_order"),
    SAVE_RECENT_SEARCHES("save_recent_searches"),
    SHOW_SUGGESTIONS("show_suggestions"),
    CUSTOM_RELATIONSHIPS("custom_relationships"),
    SEARCH_CAT_ORDER("search_cat_order"),
    SEARCH_CAT_DISABLED("search_cat_disabled"),
    COLLECTION_CAT_ORDER("collection_cat_order"),
    COLLECTION_CAT_DISABLED("collection_cat_disabled"),
    SEARCH_CAT_SHORTCUT_BUTTON("search_cat_shortcut_button"),
    SEARCH_ME_TAGGED("search_me_tagged"),
    LOCATION_SORT_BY("location_sort_by"),
    AUTO_CREATE_EVENT_FROM_CMH("auto_create_event", 1),
    HIDE_SCENE_SELECTION_GRID_SIZE("hideSceneSelectionViewColCnt"),
    STORY_HIGHLIGHT_CUSTOM_THEME("story_highlight_custom_theme"),
    STORY_NOTIFICATION_STATE("story_notification_state", r9),
    STORY_HIGHLIGHT_AUDIO_MUTED("story_highlight_audio_muted"),
    QUICK_CROP_FORMATS("quick_crop_formats"),
    AUTO_UPSCALE_IMAGE("AutoUpscaleImage", "1"),
    BUILD_ID("build_id"),
    ALLOW_DATA_USAGE_FOR_CHN("data_usage_state_for_chn", -1),
    IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP("is_need_to_show_location_gdpr_popup"),
    IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP_V2("is_need_to_show_location_gdpr_popup_v2"),
    IS_NEED_TO_SHOW_IMAGE_ANALYSIS_GDPR_POPUP("is_need_to_show_image_analysis_gdpr_popup"),
    IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP_FOR_BAIDU("is_need_to_show_location_gdpr_popup_for_baidu"),
    HEIF_AUTO_CONVERT("heif_auto_convert", r9, 1),
    HDR10PLUS_AUTO_CONVERT("hdr10plus_auto_convert", r9, 1),
    USE_TRASH("use_trash", r9, 1),
    USE_TRASH2("use_trash2", r9),
    RETAIL_MODE("retail_mode", r11),
    RETAIL_ALBUM_SET("retail_album_set"),
    ESSENTIAL_ALBUMS("EssentialAlbums", r9, 1),
    ALBUM_NAME_MERGE("MxAlbumsMergeNames", r9, 1),
    MX_ALBUM_SHOW_DEFAULT_NUM("mx_album_show_default_num"),
    SCREENSHOT_FILTER_ORDER("screenshot_filter_order"),
    CMH_PROVIDER_PERMISSION_STATUS("cmh_provider_permission_status", r11),
    CLEAR_SEP11_CACHE("clear_sep11_cache"),
    IS_SCLOUD_SYNC_ON("is_scloud_sync_on"),
    CLOUD_SWITCH_STATUS("cloud_switch_status", r11, 1),
    ONE_DRIVE_MIGRATION_SUPPORTED("one_drive_supported"),
    ONE_DRIVE_LINK_STATE("one_drive_link_state"),
    ONE_DRIVE_SYNC_ON("one_drive_sync_on"),
    BAIDU_CLOUD_ENABLED("baidu_cloud_enabled", r9, 1),
    TENCENT_CLOUD_ENABLED("tencent_cloud_enabled", r9, 1),
    SEARCH_LLM_QP_OPERATION("llm_qp_operation"),
    SEARCH_CREATURE_VIEW_ALL("creature_view_all"),
    DISK_CACHE_VERSION,
    IS_NEED_TO_SHOW_SCREENSHOT_CATEGORY_TIP_CARD("need_screenshot_category_tip_card"),
    LAST_FILE_COUNT("lfc_"),
    LAST_ALBUM_COUNT("lac_"),
    KEY_EXCEPTION_DUMP_PATH("exceptionDumpPath"),
    KEY_CLEANOUT_MOTIONPHOTO_TEST("CleanOutMotionPhotoTest"),
    LAST_DECODE_FILE,
    UNHANDLED_EXIT_COUNTER,
    RESERVE_CLEAR_MAP_CACHE;
    
    private static final String TAG = "PreferenceName";
    private int mAnalyticsLoggingLevel;
    private final Object mDefault;
    private final String mKey;

    public static PreferenceName valueOfKey(String str) {
        if (str != null) {
            return (PreferenceName) Arrays.stream(values()).filter(new L(str, 1)).findFirst().orElse((Object) null);
        }
        return null;
    }

    public boolean getBooleanDefault() {
        Object obj = this.mDefault;
        if (obj == null) {
            return false;
        }
        return ((Boolean) obj).booleanValue();
    }

    public Object getDefault() {
        return this.mDefault;
    }

    public int getIntegerDefault() {
        Object obj = this.mDefault;
        if (obj == null) {
            return 0;
        }
        return ((Integer) obj).intValue();
    }

    public long getLongDefault() {
        Object obj = this.mDefault;
        if (obj == null) {
            return 0;
        }
        return ((Long) obj).longValue();
    }

    public String getStringDefault() {
        Object obj = this.mDefault;
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public String key() {
        return this.mKey;
    }

    public int level() {
        return this.mAnalyticsLoggingLevel;
    }

    public void saveBoolean(boolean z) {
        GalleryPreference.getInstance().saveState(key(), z);
        if (this.mAnalyticsLoggingLevel == 1) {
            GalleryPreference.getInstanceAnalytics().saveState(key(), z);
        }
    }

    public String toString() {
        return this.mKey;
    }

    private PreferenceName(String str) {
        this.mKey = str;
        this.mDefault = null;
    }

    private <T> PreferenceName(String str, T t) {
        this.mKey = str;
        this.mDefault = t;
    }

    private <T> PreferenceName(String str, T t, int i2) {
        this.mKey = str;
        this.mDefault = t;
        this.mAnalyticsLoggingLevel = i2;
    }
}
