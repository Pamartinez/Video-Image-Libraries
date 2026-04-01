package com.samsung.android.gallery.support.analytics;

import F6.f;
import com.samsung.android.gallery.support.utils.PreferenceAnalytics;
import com.samsung.android.gallery.support.utils.PreferenceName;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum AnalyticsStatusId {
    STATUS_TIME_VIEW_TYPE("0101", PreferenceAnalytics.TimelineColumnCount),
    STATUS_ALBUM_VIEW_TYPE("0102", PreferenceAnalytics.AlbumsColumnCount),
    STATUS_ALBUM_PICTURES_VIEW_TYPE("0104", PreferenceAnalytics.AlbumPicturesColumnCount),
    STATUS_MAIN_TAB("0105", (int) "location://variable/currentv1", "location://timeline"),
    STATUS_STORY_ALBUM_COUNT("0106", (int) "SaStoryAlbumCount", "default"),
    STATUS_SPLIT_VIEW_STATE("0116", (int) PreferenceName.SPLIT_MODE, (String) new f(5)),
    STATUS_360_PHOTO_VIEWER_IMAGE_TYPE("4641", (int) "", "default"),
    STATUS_DEV_IMAGE_COUNT("9000", (int) null, "default"),
    STATUS_DEV_VIDEO_COUNT("9001", (int) null, "default"),
    STATUS_DEV_CLUSTER_COUNT("9002", PreferenceAnalytics.ClusterCount),
    STATUS_DEV_SDCARD_SIZE("9003", (int) null, "default"),
    STATUS_DEV_BIG_ALBUM_FILE_COUNT("9004", PreferenceAnalytics.BigAlbumCount),
    STATUS_DEV_SHARED_COUNT("9005", PreferenceAnalytics.SharedCount),
    STATUS_DEV_CLOUD_COUNT("9006", (int) null, "default"),
    STATUS_DEV_GROUP_MAX_DEPTH("9007", (int) null, "default"),
    STATUS_SIMILAR_MODE("9008", (int) PreferenceName.TIMELINE_SIMILAR_PHOTO_MODE, (String) new f(6)),
    STATUS_ALBUM_COUNT("0107", (int) "album_count", "n/a"),
    STATUS_GROUP_COUNT("0108", (int) "group_count", "0"),
    STATUS_CREATED_BY_USER_ALBUM_COUNT("0901", (int) "created_by_user_album_count", "0"),
    STATUS_MERGED_ALBUM_COUNT("0902", (int) "merged_album_count", "0"),
    STATUS_AUTO_UPDATED_ALBUM_COUNT("0903", (int) "auto_updated_album_count", "0"),
    STATUS_SYSTEM_ALBUM_COUNT("0904", (int) "system_album_count", "0"),
    STATUS_GALAXY_ALBUM_COUNT("0905", (int) "galaxy_album_count", "0"),
    STATUS_ESSENTIAL_ALBUM_COUNT("0300", (int) "essential_album_count", "0"),
    STATUS_CAMERA_ALBUM_COUNT_RATIO("0109", (int) "camera_album_count_ratio", "0%"),
    STATUS_CAMERA_SD_ALBUM_COUNT_RATIO("0110", (int) "camera_sd_album_count_ratio", "0%"),
    STATUS_FAVORITES_IMAGE_COUNT("0907", (int) "SaFavoriteImageCount", "0"),
    STATUS_FAVORITES_VIDEO_COUNT("0908", (int) "SaFavoriteVideoCount", "0"),
    STATUS_FAVORITES_NO_IMAGE_AND_VIDEO_COUNT("0909", (int) "SaNoFavorites", "0"),
    STATUS_EOF("", (int) null, "default");
    
    private static final String DEFAULT = "default";
    private String defaultValue;
    private final String preferenceKey;
    private final BooleanSupplier supplier;
    private final String value;

    private AnalyticsStatusId(String str, String str2, String str3) {
        this.value = str;
        this.preferenceKey = str2;
        this.supplier = null;
        this.defaultValue = str3;
    }

    public String getPreferenceKey() {
        return this.preferenceKey;
    }

    public String getPreferenceValue() {
        BooleanSupplier booleanSupplier = this.supplier;
        if (booleanSupplier == null) {
            return this.defaultValue;
        }
        if (booleanSupplier.getAsBoolean()) {
            return "1";
        }
        return "0";
    }

    public String id() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }

    private AnalyticsStatusId(String str, PreferenceAnalytics preferenceAnalytics) {
        this.value = str;
        this.preferenceKey = preferenceAnalytics.key;
        this.supplier = null;
    }

    private AnalyticsStatusId(String str, PreferenceName preferenceName, BooleanSupplier booleanSupplier) {
        this.value = str;
        this.preferenceKey = preferenceName.key();
        this.supplier = booleanSupplier;
    }
}
