package com.samsung.android.gallery.module.fileio.database.abstraction;

import com.samsung.android.gallery.module.fileio.database.abstraction.ContentValuesBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentValuesBuilderMpR extends ContentValuesBuilderMpQ {
    public ContentValuesBuilder setCloudOriginalSize(long j2) {
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.CLOUD_ORIGINAL_SIZE), Long.valueOf(j2));
        return this;
    }

    public void buildExtraData() {
    }

    public ContentValuesBuilder setCaptureInfo(String str, String str2) {
        return this;
    }

    public ContentValuesBuilder setLocationInfo(double d, double d2) {
        return this;
    }

    public ContentValuesBuilder setVideoRecordingInfo(int i2, int i7) {
        return this;
    }

    public ContentValuesBuilder setDateInfo(long j2, long j3, long j8) {
        return this;
    }

    public ContentValuesBuilder setGroupInfo(long j2, int i2, int i7) {
        return this;
    }

    public ContentValuesBuilder setSefInfo(int i2, int i7, String str) {
        return this;
    }

    public ContentValuesBuilder setVideoInfo(int i2, String str, boolean z) {
        return this;
    }
}
