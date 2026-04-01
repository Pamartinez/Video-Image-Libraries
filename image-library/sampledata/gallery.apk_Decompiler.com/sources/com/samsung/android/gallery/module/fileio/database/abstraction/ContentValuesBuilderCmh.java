package com.samsung.android.gallery.module.fileio.database.abstraction;

import com.samsung.android.gallery.module.fileio.database.abstraction.ContentValuesBuilder;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentValuesBuilderCmh extends ContentValuesBuilder {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CmhColumnNameHolder {
        static final HashMap<ContentValuesBuilder.ColumnName, String> map = new HashMap<ContentValuesBuilder.ColumnName, String>() {
            {
                put(ContentValuesBuilder.ColumnName.DATA, "_data");
                put(ContentValuesBuilder.ColumnName.TITLE, "title");
                put(ContentValuesBuilder.ColumnName.DISPLAY_NAME, "_display_name");
                put(ContentValuesBuilder.ColumnName.BUCKET_ID, "bucket_id");
                put(ContentValuesBuilder.ColumnName.BUCKET_DISPLAY_NAME, "bucket_display_name");
                put(ContentValuesBuilder.ColumnName.MEDIA_TYPE, ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
                put(ContentValuesBuilder.ColumnName.MIME_TYPE, "mime_type");
                put(ContentValuesBuilder.ColumnName.WIDTH, "width");
                put(ContentValuesBuilder.ColumnName.HEIGHT, "height");
                put(ContentValuesBuilder.ColumnName.ORIENTATION, "orientation");
                put(ContentValuesBuilder.ColumnName.SIZE, IParameterKey.SIZE);
                put(ContentValuesBuilder.ColumnName.LATITUDE, "latitude");
                put(ContentValuesBuilder.ColumnName.LONGITUDE, "longitude");
                put(ContentValuesBuilder.ColumnName.DATE_TAKEN, IParameterKey.DATE_TAKEN);
                put(ContentValuesBuilder.ColumnName.DATE_ADDED, "date_added");
                put(ContentValuesBuilder.ColumnName.DATE_MODIFIED, IParameterKey.DATE_MODIFIED);
                put(ContentValuesBuilder.ColumnName.DURATION, "duration");
                put(ContentValuesBuilder.ColumnName.RESOLUTION, EngramQueryOptionBundleWrapper.BUNDLE_KEY_RESOLUTION);
                put(ContentValuesBuilder.ColumnName.VIDEO_360, "is_360_video");
                put(ContentValuesBuilder.ColumnName.RECORDING_MODE, "recording_mode");
                put(ContentValuesBuilder.ColumnName.RECORDING_TYPE, "recordingtype");
                put(ContentValuesBuilder.ColumnName.CAPTURE_URL, "captured_url");
                put(ContentValuesBuilder.ColumnName.CAPTURE_APP, "captured_app");
                put(ContentValuesBuilder.ColumnName.GROUP_ID, BundleKey.GROUP_ID);
                put(ContentValuesBuilder.ColumnName.GROUP_TYPE, "not-supported");
                put(ContentValuesBuilder.ColumnName.GROUP_BEST, "best_image");
                put(ContentValuesBuilder.ColumnName.SEF_TYPE, "sef_file_type");
                put(ContentValuesBuilder.ColumnName.SEF_SUB_TYPE, "sef_file_sub_type");
                put(ContentValuesBuilder.ColumnName.SEF_TYPES, "sef_file_types");
                put(ContentValuesBuilder.ColumnName.HIDE, "is_hide");
                put(ContentValuesBuilder.ColumnName.DRM, "is_drm");
                put(ContentValuesBuilder.ColumnName.FAVORITE, "is_favorite");
                put(ContentValuesBuilder.ColumnName.RELATIVE_PATH, "not-supported");
            }
        };
    }

    public String getColumnName(ContentValuesBuilder.ColumnName columnName) {
        return CmhColumnNameHolder.map.getOrDefault(columnName, "not-supported");
    }
}
