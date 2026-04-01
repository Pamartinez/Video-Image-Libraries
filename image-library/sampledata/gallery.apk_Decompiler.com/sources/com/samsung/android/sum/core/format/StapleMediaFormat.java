package com.samsung.android.sum.core.format;

import android.graphics.Rect;
import com.samsung.android.sum.core.types.CodecType;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ColorSpace;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.FlipType;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.SplitType;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StapleMediaFormat implements MediaFormat {
    MutableMediaFormat impl;

    public StapleMediaFormat(MutableMediaFormat mutableMediaFormat) {
        this.impl = mutableMediaFormat;
    }

    public float bytePerPixel() {
        return this.impl.bytePerPixel();
    }

    public float bytePerSample() {
        return this.impl.bytePerSample();
    }

    public boolean checkTypeOf(String str, Class<?> cls) {
        return this.impl.checkTypeOf(str, cls);
    }

    public boolean contains(String str) {
        return this.impl.contains(str);
    }

    public boolean containsAllOf(String... strArr) {
        return this.impl.containsAllOf(strArr);
    }

    public boolean containsAnyOf(String... strArr) {
        return this.impl.containsAnyOf(strArr);
    }

    public String contentToString() {
        return this.impl.contentToString();
    }

    public <T> T get(String str) {
        return this.impl.get(str);
    }

    public List<String> getAttributeKeys() {
        return this.impl.getAttributeKeys();
    }

    public int getBatch() {
        return this.impl.getBatch();
    }

    public int getChannels() {
        return this.impl.getChannels();
    }

    public CodecType getCodecType() {
        return this.impl.getCodecType();
    }

    public ColorFormat getColorFormat() {
        return this.impl.getColorFormat();
    }

    public ColorSpace getColorSpace() {
        return this.impl.getColorSpace();
    }

    public int getCols() {
        return this.impl.getCols();
    }

    public Rect getCropRect() {
        return this.impl.getCropRect();
    }

    public DataType getDataType() {
        return this.impl.getDataType();
    }

    public FlipType getFlipType() {
        return this.impl.getFlipType();
    }

    public MediaType getMediaType() {
        return this.impl.getMediaType();
    }

    public List<? extends MediaFormat> getPlanesFormat() {
        return this.impl.getPlanesFormat();
    }

    public int getRotation() {
        return this.impl.getRotation();
    }

    public int getRows() {
        return this.impl.getRows();
    }

    public Shape getShape() {
        return this.impl.getShape();
    }

    public SplitType getSplitType() {
        return this.impl.getSplitType();
    }

    public long getUsage() {
        return this.impl.getUsage();
    }

    public boolean isValid() {
        return this.impl.isValid();
    }

    public <T> T remove(String str) {
        return this.impl.remove(str);
    }

    public long size() {
        return this.impl.size();
    }

    public MutableMediaFormat toMutableFormat() {
        return (MutableMediaFormat) this.impl.dupAll();
    }

    public String toString() {
        return contentToString(this);
    }

    public String contentToString(Object obj) {
        return this.impl.contentToString(obj);
    }

    public <T> T get(String str, T t) {
        return this.impl.get(str, t);
    }
}
