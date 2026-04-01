package com.samsung.android.sum.core.format;

import android.graphics.Rect;
import com.samsung.android.sum.core.types.CodecType;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ColorSpace;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.FlipType;
import com.samsung.android.sum.core.types.MediaType;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaFormatBuilder {
    Map<String, Object> attributes = new HashMap();
    CodecType codecType = CodecType.NONE;
    ColorFormat colorFormat = ColorFormat.NONE;
    ColorSpace colorSpace = ColorSpace.NONE;
    Rect cropRect = null;
    DataType dataType = DataType.NONE;
    FlipType flipType = FlipType.NONE;
    MediaType mediaType = MediaType.NONE;
    MutableShape shape;
    long usage = 0;

    public MediaFormatBuilder asCompressed() {
        this.mediaType = MediaType.of(this.mediaType.rank(), 1);
        return this;
    }

    public MediaFormat build() {
        return new StapleMediaFormat(buildMutable());
    }

    public MutableMediaFormat buildMutable() {
        StapleMutableMediaFormat stapleMutableMediaFormat = new StapleMutableMediaFormat(this);
        if (!this.attributes.isEmpty()) {
            this.attributes.forEach(new a(stapleMutableMediaFormat));
        }
        return stapleMutableMediaFormat;
    }

    public MediaFormatBuilder setAttribute(String str, Object obj) {
        this.attributes.put(str, obj);
        return this;
    }

    public MediaFormatBuilder setCodecType(CodecType codecType2) {
        this.codecType = codecType2;
        return this;
    }

    public MediaFormatBuilder setColorFormat(ColorFormat colorFormat2) {
        this.colorFormat = colorFormat2;
        return this;
    }

    public MediaFormatBuilder setColorSpace(ColorSpace colorSpace2) {
        this.colorSpace = colorSpace2;
        return this;
    }

    public MediaFormatBuilder setCols(int i2) {
        MutableShape mutableShape = this.shape;
        if (mutableShape == null) {
            setShape(Shape.of(0, i2));
            return this;
        }
        mutableShape.setCols(i2);
        return this;
    }

    public MediaFormatBuilder setCropRect(Rect rect) {
        this.cropRect = rect;
        return this;
    }

    public MediaFormatBuilder setDataType(DataType dataType2) {
        this.dataType = dataType2;
        return this;
    }

    public MediaFormatBuilder setFlipType(FlipType flipType2) {
        this.flipType = flipType2;
        return this;
    }

    public MediaFormatBuilder setMediaType(MediaType mediaType2) {
        this.mediaType = mediaType2;
        return this;
    }

    public MediaFormatBuilder setRotation(int i2) {
        this.attributes.put("rotation", Integer.valueOf(i2));
        return this;
    }

    public MediaFormatBuilder setRows(int i2) {
        MutableShape mutableShape = this.shape;
        if (mutableShape == null) {
            setShape(Shape.of(i2, 0));
            return this;
        }
        mutableShape.setRows(i2);
        return this;
    }

    public MediaFormatBuilder setShape(MutableShape mutableShape) {
        this.shape = mutableShape;
        return this;
    }

    public MediaFormatBuilder setUsage(long j2) {
        this.usage = j2;
        return this;
    }

    public MediaFormatBuilder setDataType(DataType dataType2, int i2) {
        this.dataType = DataType.of(dataType2, i2);
        return this;
    }

    public MediaFormatBuilder setShape(Shape shape2) {
        this.shape = shape2.toMutableShape();
        return this;
    }

    public MediaFormatBuilder setShape(int i2) {
        return setShape(Shape.of(i2));
    }

    public MediaFormatBuilder setShape(int i2, int i7) {
        return setShape(Shape.of(i7, i2));
    }

    public MediaFormatBuilder setShape(Rect rect) {
        return setShape(Shape.of(rect.width(), rect.height()));
    }

    public MediaFormatBuilder setShape(int i2, int i7, int i8, int i10) {
        return setShape(Shape.of(i2, i8, i7, i10));
    }
}
