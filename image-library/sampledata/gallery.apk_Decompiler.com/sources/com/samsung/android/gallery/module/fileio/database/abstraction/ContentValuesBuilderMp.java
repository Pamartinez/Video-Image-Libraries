package com.samsung.android.gallery.module.fileio.database.abstraction;

import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.cloud.SCloudHelper;
import com.samsung.android.gallery.module.fileio.database.abstraction.ContentValuesBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentValuesBuilderMp extends ContentValuesBuilder {
    private String mCloudData2;
    private boolean mCloudOnly;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MpColumnNameHolder {
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
                put(ContentValuesBuilder.ColumnName.GROUP_TYPE, com.samsung.android.sdk.mobileservice.social.group.BundleKey.GROUP_TYPE);
                put(ContentValuesBuilder.ColumnName.GROUP_BEST, "best_image");
                put(ContentValuesBuilder.ColumnName.SEF_TYPE, "sef_file_type");
                put(ContentValuesBuilder.ColumnName.SEF_SUB_TYPE, "sef_file_sub_type");
                put(ContentValuesBuilder.ColumnName.SEF_TYPES, "sef_file_types");
                put(ContentValuesBuilder.ColumnName.HIDE, "is_hide");
                put(ContentValuesBuilder.ColumnName.DRM, "is_drm");
                put(ContentValuesBuilder.ColumnName.FAVORITE, "is_favorite");
                put(ContentValuesBuilder.ColumnName.RELATIVE_PATH, "relative_path");
                put(ContentValuesBuilder.ColumnName.CLOUD_DATA, "_data2");
                put(ContentValuesBuilder.ColumnName.CLOUD_SERVER_PATH, "cloud_server_path");
                put(ContentValuesBuilder.ColumnName.CLOUD_THUMB_PATH, "cloud_thumb_path");
                put(ContentValuesBuilder.ColumnName.CLOUD_ORIGINAL_SIZE, "cloud_original_size");
                put(ContentValuesBuilder.ColumnName.CLOUD_ORIGINAL_BINARY_HASH, "cloud_original_binary_hash");
                put(ContentValuesBuilder.ColumnName.CLOUD_ORIGINAL_BINARY_SIZE, "cloud_original_binary_size");
                put(ContentValuesBuilder.ColumnName.CLOUD_HASH, "hash");
                put(ContentValuesBuilder.ColumnName.CLOUD_STORAGE_ID, "storage_id");
                put(ContentValuesBuilder.ColumnName.CLOUD_DIRTY, "dirty");
            }
        };
    }

    public void buildBucketId(String str) {
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.BUCKET_ID), Integer.valueOf(FileUtils.getBucketId(str)));
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.BUCKET_DISPLAY_NAME), FileUtils.getNameFromPath(str));
    }

    public void buildData() {
        String nameFromPath = FileUtils.getNameFromPath(this.mTargetPath);
        String directoryFromPath = FileUtils.getDirectoryFromPath(this.mTargetPath, false);
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.TITLE), FileUtils.getBaseName(nameFromPath));
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.DISPLAY_NAME), nameFromPath);
        buildBucketId(directoryFromPath);
        if (this.mCloudOnly) {
            String cloudRemotePath = SCloudHelper.getCloudRemotePath(this.mCloudData2, directoryFromPath, nameFromPath);
            if (cloudRemotePath != null) {
                this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.CLOUD_DATA), cloudRemotePath);
            }
            this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.CLOUD_SERVER_PATH), this.mTargetPath);
            return;
        }
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.DATA), this.mTargetPath);
        if (this.mFileSize > 0) {
            this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.SIZE), Long.valueOf(this.mFileSize));
        }
    }

    public String getColumnName(ContentValuesBuilder.ColumnName columnName) {
        return MpColumnNameHolder.map.getOrDefault(columnName, "not-supported");
    }

    public ContentValuesBuilder setCloudData(String str) {
        this.mCloudOnly = true;
        this.mCloudData2 = str;
        return this;
    }

    public ContentValuesBuilder setCloudInfo(String str, long j2) {
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.CLOUD_HASH), str);
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.CLOUD_ORIGINAL_SIZE), Long.valueOf(j2));
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.CLOUD_STORAGE_ID), 65537);
        return this;
    }

    public ContentValuesBuilder setCloudMediaType(MediaType mediaType) {
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.MEDIA_TYPE), Integer.valueOf(mediaType.toInt()));
        return this;
    }

    public ContentValuesBuilder setCloudThumbPath(String str) {
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.CLOUD_THUMB_PATH), str);
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.CLOUD_DIRTY), 1);
        return this;
    }
}
