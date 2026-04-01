package com.samsung.android.sum.core.descriptor;

import android.os.Parcelable;
import com.samsung.android.sum.core.filter.MediaFilter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MFDescriptor extends Parcelable {
    static MFDescriptorBuilder newBuilder() {
        return new MFDescriptorBuilder();
    }

    <T> T getExtra(String str);

    <T> T getExtra(String str, T t);

    String getFilterId();

    MediaFilter.Option getFilterOption();

    Class<?> getFilterType() {
        return MediaFilter.class;
    }

    Class<?> getFilterTypeFromId();

    <T> void setExtra(String str, T t);

    void setFilterOption(MediaFilter.Option option);
}
