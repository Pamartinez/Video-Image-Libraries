package com.samsung.android.gallery.module.fileio.database.abstraction;

import android.content.ContentValues;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.support.utils.FileUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ContentValuesBuilder {
    private boolean mDrm;
    private boolean mFavorite;
    long mFileSize;
    String mTargetPath;
    final ContentValues mValues = new ContentValues();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ColumnName {
        DATA,
        TITLE,
        DISPLAY_NAME,
        BUCKET_ID,
        BUCKET_DISPLAY_NAME,
        MEDIA_TYPE,
        MIME_TYPE,
        WIDTH,
        HEIGHT,
        ORIENTATION,
        SIZE,
        LATITUDE,
        LONGITUDE,
        DATE_TAKEN,
        DATE_ADDED,
        DATE_MODIFIED,
        DURATION,
        RESOLUTION,
        VIDEO_360,
        RECORDING_MODE,
        RECORDING_TYPE,
        CAPTURE_URL,
        CAPTURE_APP,
        GROUP_ID,
        GROUP_TYPE,
        GROUP_BEST,
        SEF_TYPE,
        SEF_SUB_TYPE,
        SEF_TYPES,
        HIDE,
        DRM,
        FAVORITE,
        RELATIVE_PATH,
        CLOUD_DATA,
        CLOUD_SERVER_PATH,
        CLOUD_THUMB_PATH,
        CLOUD_ORIGINAL_SIZE,
        CLOUD_HASH,
        CLOUD_ORIGINAL_BINARY_HASH,
        CLOUD_ORIGINAL_BINARY_SIZE,
        CLOUD_STORAGE_ID,
        CLOUD_DIRTY
    }

    public ContentValues build() {
        buildData();
        buildExtraData();
        if (this.mFavorite) {
            this.mValues.put("is_favorite", Boolean.TRUE);
        }
        return this.mValues;
    }

    public void buildData() {
        String directoryFromPath = FileUtils.getDirectoryFromPath(this.mTargetPath, false);
        String nameFromPath = FileUtils.getNameFromPath(this.mTargetPath);
        this.mValues.put(getColumnName(ColumnName.DATA), this.mTargetPath);
        this.mValues.put(getColumnName(ColumnName.DISPLAY_NAME), nameFromPath);
        this.mValues.put(getColumnName(ColumnName.TITLE), FileUtils.getBaseName(nameFromPath));
        this.mValues.put(getColumnName(ColumnName.BUCKET_ID), Integer.valueOf(FileUtils.getBucketId(directoryFromPath)));
        this.mValues.put(getColumnName(ColumnName.BUCKET_DISPLAY_NAME), FileUtils.getNameFromPath(directoryFromPath));
        if (this.mFileSize > 0) {
            this.mValues.put(getColumnName(ColumnName.SIZE), Long.valueOf(this.mFileSize));
        }
    }

    public void buildExtraData() {
        if (this.mDrm) {
            this.mValues.put("is_drm", Boolean.TRUE);
        }
    }

    public ContentValuesBuilder clearGroupInfo() {
        this.mValues.remove(getColumnName(ColumnName.GROUP_ID));
        this.mValues.remove(getColumnName(ColumnName.GROUP_TYPE));
        this.mValues.remove(getColumnName(ColumnName.GROUP_BEST));
        return this;
    }

    public abstract String getColumnName(ColumnName columnName);

    public ContentValuesBuilder setCaptureInfo(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.mValues.put(getColumnName(ColumnName.CAPTURE_URL), str);
        }
        if (!TextUtils.isEmpty(str2)) {
            this.mValues.put(getColumnName(ColumnName.CAPTURE_APP), str2);
        }
        return this;
    }

    public ContentValuesBuilder setDateInfo(long j2, long j3, long j8) {
        this.mValues.put(getColumnName(ColumnName.DATE_TAKEN), Long.valueOf(j2));
        this.mValues.put(getColumnName(ColumnName.DATE_ADDED), Long.valueOf(j3));
        this.mValues.put(getColumnName(ColumnName.DATE_MODIFIED), Long.valueOf(j8));
        return this;
    }

    public ContentValuesBuilder setDrm(boolean z) {
        this.mDrm = z;
        return this;
    }

    public ContentValuesBuilder setFavorite(boolean z) {
        this.mFavorite = z;
        return this;
    }

    public ContentValuesBuilder setFileSize(long j2) {
        this.mFileSize = j2;
        return this;
    }

    public ContentValuesBuilder setGroupInfo(long j2, int i2, int i7) {
        this.mValues.put(getColumnName(ColumnName.GROUP_ID), Long.valueOf(j2));
        if (i7 > 0) {
            this.mValues.put(getColumnName(ColumnName.GROUP_BEST), Integer.valueOf(i7));
        }
        return this;
    }

    public ContentValuesBuilder setHide(int i2) {
        this.mValues.put(getColumnName(ColumnName.HIDE), Integer.valueOf(i2));
        return this;
    }

    public ContentValuesBuilder setLocationInfo(double d, double d2) {
        this.mValues.put(getColumnName(ColumnName.LATITUDE), Double.valueOf(d));
        this.mValues.put(getColumnName(ColumnName.LONGITUDE), Double.valueOf(d2));
        return this;
    }

    public ContentValuesBuilder setMediaInfo(String str, int i2, int i7, int i8) {
        this.mValues.put(getColumnName(ColumnName.MIME_TYPE), str);
        this.mValues.put(getColumnName(ColumnName.WIDTH), Integer.valueOf(i2));
        this.mValues.put(getColumnName(ColumnName.HEIGHT), Integer.valueOf(i7));
        this.mValues.put(getColumnName(ColumnName.ORIENTATION), Integer.valueOf(i8));
        return this;
    }

    public ContentValuesBuilder setPath(String str) {
        this.mTargetPath = str;
        return this;
    }

    public ContentValuesBuilder setSefInfo(int i2, int i7, String str) {
        if (i2 >= 0) {
            this.mValues.put(getColumnName(ColumnName.SEF_TYPE), Integer.valueOf(i2));
        }
        if (i7 >= 0) {
            this.mValues.put(getColumnName(ColumnName.SEF_SUB_TYPE), Integer.valueOf(i7));
        }
        if (!TextUtils.isEmpty(str)) {
            this.mValues.put(getColumnName(ColumnName.SEF_TYPES), str);
        }
        return this;
    }

    public ContentValuesBuilder setVideoInfo(int i2, String str, boolean z) {
        this.mValues.put(getColumnName(ColumnName.DURATION), Integer.valueOf(i2));
        this.mValues.put(getColumnName(ColumnName.RESOLUTION), str);
        this.mValues.put(getColumnName(ColumnName.VIDEO_360), Boolean.valueOf(z));
        return this;
    }

    public ContentValuesBuilder setVideoRecordingInfo(int i2, int i7) {
        this.mValues.put(getColumnName(ColumnName.RECORDING_MODE), Integer.valueOf(i2));
        this.mValues.put(getColumnName(ColumnName.RECORDING_TYPE), Integer.valueOf(i7));
        return this;
    }

    public ContentValuesBuilder setCloudData(String str) {
        return this;
    }

    public ContentValuesBuilder setCloudMediaType(MediaType mediaType) {
        return this;
    }

    public ContentValuesBuilder setCloudOriginalSize(long j2) {
        return this;
    }

    public ContentValuesBuilder setCloudThumbPath(String str) {
        return this;
    }

    public ContentValuesBuilder setRelativePath(String str) {
        return this;
    }

    public ContentValuesBuilder setCloudInfo(String str, long j2) {
        return this;
    }
}
