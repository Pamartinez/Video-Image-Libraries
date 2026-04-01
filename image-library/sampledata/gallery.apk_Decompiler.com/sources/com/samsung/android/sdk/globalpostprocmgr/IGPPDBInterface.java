package com.samsung.android.sdk.globalpostprocmgr;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IGPPDBInterface {
    public static final String AUTHORITY = "com.samsung.provider.gppm";
    public static final Uri AUTHORITY_URI;
    public static final Uri FEATURE_SUPPORT_TABLE_URI;
    public static final Uri PIPELINE_TABLE_URI;
    public static final Uri PLUGIN_TABLE_URI;
    public static final String PP_FEATURE_SUPPORT_TABLE_NAME = "pp_feature_support";
    public static final String PP_PIPELINE_TABLE_NAME = "pp_pipeline";
    public static final String PP_PLUGIN_TABLE_NAME = "pp_plugin";
    public static final String PP_REQUEST_QUEUE_TABLE_NAME = "pp_request_queue";
    public static final Uri REQUEST_QUEUE_TABLE_URI;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface IFeatureSupportColumns {
        public static final String FIELD_FEATURE_NAME = "feature_name";
        public static final String FIELD_FEATURE_PARAM_LIST = "feature_param_list";
        public static final String FIELD_FEATURE_SOLUTION_LIB_LIST = "feature_solution_lib_list";
        public static final String FIELD_FEATURE_SUPPORTED = "feature_supported";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface IPipelineColumns {
        public static final String FIELD_PIPELINE_ID = "_id";
        public static final String FIELD_PIPELINE_LIST = "plugin_list";
        public static final String FIELD_PIPELINE_NAME = "name";
        public static final String FIELD_PIPELINE_PRIORITY = "priority";
        public static final String FIELD_PIPELINE_VERSION = "version";
        public static final String FIELD_UPDATE_TIME = "update_time";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface IPluginColumns {
        public static final String FIELD_PLUGIN_DESCRIPTION = "description";
        public static final String FIELD_PLUGIN_ID = "_id";
        public static final String FIELD_PLUGIN_NAME = "name";
        public static final String FIELD_PLUGIN_TYPE = "type";
        public static final String FIELD_PLUGIN_VERSION = "version";
        public static final String FIELD_UPDATE_TIME = "update_time";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface IRequestQueue {
        public static final String FIELD_REQUEST_DATETIME = "datetime";
        public static final String FIELD_REQUEST_FILE_PATH = "file_path";
        public static final String FIELD_REQUEST_ID = "_id";
        public static final String FIELD_REQUEST_MEDIA_ID = "media_id";
        public static final String FIELD_REQUEST_PIPELINE_NAME = "pipeline_name";
        public static final String FIELD_REQUEST_PRIORITY = "priority";
        public static final String FIELD_REQUEST_SEC_MEDIA_ID = "sec_media_id";
        public static final String FIELD_REQUEST_STATUS = "status";
        public static final String FIELD_UPDATE_TIME = "update_time";
    }

    static {
        Uri parse = Uri.parse("content://com.samsung.provider.gppm");
        AUTHORITY_URI = parse;
        PLUGIN_TABLE_URI = Uri.withAppendedPath(parse, PP_PLUGIN_TABLE_NAME);
        PIPELINE_TABLE_URI = Uri.withAppendedPath(parse, PP_PIPELINE_TABLE_NAME);
        REQUEST_QUEUE_TABLE_URI = Uri.withAppendedPath(parse, PP_REQUEST_QUEUE_TABLE_NAME);
        FEATURE_SUPPORT_TABLE_URI = Uri.withAppendedPath(parse, PP_FEATURE_SUPPORT_TABLE_NAME);
    }
}
