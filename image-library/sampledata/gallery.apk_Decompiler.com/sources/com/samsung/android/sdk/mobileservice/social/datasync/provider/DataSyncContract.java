package com.samsung.android.sdk.mobileservice.social.datasync.provider;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataSyncContract {
    public static final String AUTHORITY = "com.samsung.android.mobileservice.datasync";
    /* access modifiers changed from: private */
    public static final Uri AUTHORITY_URI = Uri.parse("content://com.samsung.android.mobileservice.datasync");

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Data {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DataSyncContract.AUTHORITY_URI, "data");
        public static final String TIMESTAMP = "timestamp";

        private Data() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GallerySettingData extends Data {
        public static final String SORT_TYPE = "sort_type";

        private GallerySettingData() {
            super();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ProfileCardData extends Data {
        public static final String BACKGROUND_FILE_URI = "bg_file_uri";
        public static final String BACKGROUND_TYPE = "bg_type";
        public static final String BG_SOURCE = "bg_source";
        public static final String CUSTOM_BACKGROUND_HASH = "custom_bg_profile";
        public static final String CUSTOM_TIMESTAMP = "custom_timestamp";
        public static final String FILE_EXTENSION = "file_extension";
        public static final String FONT_FACE = "font_face";
        public static final String FONT_WEIGHT = "font_weight";
        public static final String GRADIENT_ANGLE = "gradient_angle";
        public static final String NAME_ALIGN = "name_align";
        public static final String NAME_COLOR = "name_color";
        public static final String SQUARE_CROP_RECT = "square_crop_rect";

        private ProfileCardData() {
            super();
        }
    }

    private DataSyncContract() {
    }
}
