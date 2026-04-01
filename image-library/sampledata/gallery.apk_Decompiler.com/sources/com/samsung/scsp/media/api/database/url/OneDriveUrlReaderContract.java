package com.samsung.scsp.media.api.database.url;

import android.provider.BaseColumns;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDriveUrlReaderContract {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Entry implements BaseColumns {
        public static final String COLUMN_NAME_EXPIRATION_DATE = "expiration_date";
        public static final String COLUMN_NAME_HASH = "hash";
        public static final String COLUMN_NAME_NDE_ORG_EXPIRATION_DATE = "nde_original_expiration_date";
        public static final String COLUMN_NAME_NDE_ORG_HASH = "nde_original_hash";
        public static final String COLUMN_NAME_NDE_ORG_URL = "nde_original_url";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String UPDATE_TABLE_NAME = "update_url";
        public static final String UPLOAD_TABLE_NAME = "upload_url";
    }
}
