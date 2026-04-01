package com.samsung.android.sum.core.format;

import android.graphics.Rect;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.types.CodecType;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ColorSpace;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.FlipType;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.SplitType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MutableMediaFormat extends MediaFormat, Duplicable<MutableMediaFormat> {
    MutableMediaFormat set(MediaFilter.Option option);

    MutableMediaFormat set(String str, Object obj);

    MutableMediaFormat setChannels(int i2);

    MutableMediaFormat setCodecType(CodecType codecType);

    MutableMediaFormat setColorFormat(ColorFormat colorFormat) {
        throw new UnsupportedOperationException("not support for " + getMediaType());
    }

    MutableMediaFormat setColorSpace(ColorSpace colorSpace) {
        throw new UnsupportedOperationException("not support for " + getMediaType());
    }

    MutableMediaFormat setCols(int i2);

    MutableMediaFormat setCropRect(Rect rect);

    MutableMediaFormat setDataType(DataType dataType);

    MutableMediaFormat setDimension(int i2, int i7) {
        setCols(i2);
        setRows(i7);
        return this;
    }

    MutableMediaFormat setFlipType(FlipType flipType);

    MutableMediaFormat setMediaType(MediaType mediaType);

    MutableMediaFormat setRotation(int i2);

    MutableMediaFormat setRows(int i2);

    MutableMediaFormat setShape(Shape shape);

    MutableMediaFormat setSplitType(SplitType splitType);

    MutableMediaFormat setUsage(long j2);

    MediaFormat toMediaFormat() {
        throw new UnsupportedOperationException();
    }
}
