package com.samsung.scsp.media.api.constant;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaApiContract {
    public static final long DAY_IN_MILLI = 86400000;
    public static final long ONEDRIVE_UPLOAD_URL_EXPIRATION_DATE_PERIOD = 432000000;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Parameter {
        public static final String ANALYSIS = "analysis";
        public static final String CALLER_PACKAGE = "caller_package";
        public static final String CHANGE_POINT = "changePoint";
        public static final String CLEAR = "clear";
        public static final String CLIENT_TIMESTAMP = "clientTimestamp";
        public static final String CONTENT_RANGE = "content_range";
        public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
        public static final String COUNT = "count";
        public static final String DOWNLOAD = "download";
        public static final String DOWNLOAD_URL = "downloadUrl";
        public static final String ENABLED = "enabled";
        public static final String EXCLUDE_DELETED = "excludeDeleted";
        public static final String EXPIRATION_DATE_TIME = "expiration_date_time";
        public static final String FAVORITE = "favorite";
        public static final String FILE_DESCRIPTOR = "fileDescriptor";
        public static final String FILE_PATH = "filePath";
        public static final String FREE = "free";
        public static final String HASH = "hash";
        public static final String INCLUDE_DETAIL = "includeDetail";
        public static final String KEY = "key";
        public static final String LATITUDE = "latitude";
        public static final String LIST = "list";
        public static final String LOCATION = "location";
        public static final String LONGITUDE = "longitude";
        public static final String MEDIA_TYPE = "mediaType";
        public static final String MODIFIED_AFTER = "modifiedAfter";
        public static final String NEXT_EXPECTED_RANGE = "next_expected_ranges";
        public static final String ONE_DRIVE = "od";
        public static final String ORIENTATION = "orientation";
        public static final String ORIGINAL_BINARY_HASH = "originalBinaryHash";
        public static final String ORIGINAL_BINARY_SIZE = "originalBinarySize";
        public static final String ORIGINAL_PATH_TO_UPLOAD = "original_path_to_upload";
        public static final String ORIGINAL_UPLOAD_RANGE_START = "original_upload_range_start";
        public static final String ORIGINAL_URL = "originalUrl";
        public static final String PAGE_TOKEN = "pageToken";
        public static final String PHOTO_ID = "photoId";
        public static final String RCODE = "rcode";
        public static final String REDIRECT = "redirect";
        public static final String RESIZED = "resized";
        public static final String REVISION = "revision";
        public static final String SIZE = "size";
        public static final String STREAMING_URL = "streamingUrl";
        public static final String TIMESTAMP = "timestamp";
        public static final String URL = "url";
        public static final String VALIDATION_KEY = "validationKey";
        public static final String VALUE = "value";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Rcode {
        public static final int DEDUPLICATED = 204;
        public static final int FILE_DOES_NOT_EXIST = 108305;
        public static final int GDPR_DATA_ACCESS_IS_RESTRICTED = 101902;
        public static final int GDPR_DATA_IS_BEING_PROCESSED = 101901;
        public static final int GDPR_DATA_IS_DELETED = 101903;
        public static final int INSUFFICIENT_STORAGE = 507;
        public static final int INVALID_STATE = 364102;
        public static final int IO_EXCEPTION = 60000000;
        public static final int NETWORK_IO_ERROR = 60000004;
        public static final int NETWORK_IS_CLOSED = 60000004;
        public static final int ONE_DRIVE_MIGRATING = 101503;
        public static final int QUOTA_OVER_FLOW = 364106;
        public static final int REQUESTS_CONFLICT = 364104;
        public static final int RESOURCE_DOES_NOT_EXIST = 364201;
    }
}
