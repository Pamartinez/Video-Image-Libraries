package com.samsung.android.sum.core.buffer;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.format.Duplicable;
import com.samsung.android.sum.core.format.MediaFormat;
import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Align implements Serializable, Parcelable, Duplicable<Align>, Comparable<Align> {
    public static final Parcelable.Creator<Align> CREATOR = new Parcelable.Creator<Align>() {
        public Align createFromParcel(Parcel parcel) {
            return new Align(parcel);
        }

        public Align[] newArray(int i2) {
            return new Align[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) Align.class);
    private static final long serialVersionUID = 1;
    private int alignOfHeight;
    private int alignOfWidth;
    private int scanline;
    private int stride;

    public Align() {
        this.stride = 0;
        this.scanline = 0;
        this.alignOfWidth = 0;
        this.alignOfHeight = 0;
    }

    public static Align of(int i2) {
        return new Align(0, 0, i2, 1);
    }

    public static Align scanlineOf(int i2) {
        return new Align(0, i2);
    }

    public static Align setByFormat(MediaFormat mediaFormat) {
        if (mediaFormat.getShape() == null || mediaFormat.getCols() == 0 || mediaFormat.getRows() == 0) {
            return new Align();
        }
        if (mediaFormat.getChannels() <= 0) {
            return shapeOf(mediaFormat.getCols(), mediaFormat.getRows());
        }
        return shapeOf(mediaFormat.getChannels() * mediaFormat.getCols(), mediaFormat.getRows());
    }

    public static Align shapeOf(int i2, int i7) {
        return new Align(i2, i7);
    }

    public static Align strideOf(int i2) {
        return new Align(i2, 0);
    }

    public void adjustAlign() {
        int i2;
        int i7;
        int i8 = this.stride;
        if (i8 > 0 && (i7 = this.alignOfWidth) > 0 && i8 % i7 != 0) {
            this.stride = ((i8 + i7) - 1) & (-i7);
        }
        int i10 = this.scanline;
        if (i10 > 0 && (i2 = this.alignOfHeight) > 0 && i10 % i2 != 0) {
            this.scanline = ((i10 + i2) - 1) & (-i2);
        }
    }

    public void adjustAlignByFormat(MediaFormat mediaFormat) {
        if (getDimension() == 0) {
            Align byFormat = setByFormat(mediaFormat);
            this.stride = byFormat.stride;
            this.scanline = byFormat.scanline;
            adjustAlign();
        }
    }

    public String contentToString() {
        return contentToString(this);
    }

    public int describeContents() {
        return 0;
    }

    public Pair getAlign() {
        return new Pair(Integer.valueOf(this.alignOfWidth), Integer.valueOf(this.alignOfHeight));
    }

    public int getAlignOfHeight() {
        return this.alignOfHeight;
    }

    public int getAlignOfWidth() {
        return this.alignOfWidth;
    }

    public int getDimension() {
        int i2;
        int i7 = this.stride;
        if (i7 <= 0 || (i2 = this.scanline) <= 0) {
            return 0;
        }
        return i7 * i2;
    }

    public int getScanline() {
        return this.scanline;
    }

    public int getStride() {
        return this.stride;
    }

    public Align set(int i2, int i7) {
        this.alignOfWidth = i2;
        this.alignOfHeight = i7;
        adjustAlign();
        return this;
    }

    public Align setAlignOfHeight(int i2) {
        this.alignOfHeight = i2;
        adjustAlign();
        return this;
    }

    public Align setAlignOfWidth(int i2) {
        this.alignOfWidth = i2;
        adjustAlign();
        return this;
    }

    public Align setScanline(int i2) {
        this.scanline = i2;
        return this;
    }

    public Align setShape(int i2, int i7) {
        this.stride = i2;
        this.scanline = i7;
        return this;
    }

    public Align setStride(int i2) {
        this.stride = i2;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.stride);
        parcel.writeInt(this.scanline);
    }

    public static Align of(int i2, int i7) {
        return new Align(0, 0, i2, i7);
    }

    public int compareTo(Align align) {
        return getDimension() - align.getDimension();
    }

    public String contentToString(Object obj) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Def.taglnOf(obj));
        sb2.append(Def.contentToString("stride=" + this.stride, "scanline=" + this.scanline, "width-align=" + this.alignOfWidth, "height-align=" + this.alignOfHeight));
        return sb2.toString();
    }

    public Align dup() {
        try {
            return (Align) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new IllegalStateException("fail to clone");
        }
    }

    public Align dupAll() {
        return new Align(this.stride, this.scanline, this.alignOfWidth, this.alignOfHeight);
    }

    public static Align of(int i2, int i7, int i8, int i10) {
        return new Align(i2, i7, i8, i10);
    }

    public Align(int i2, int i7) {
        this.stride = i2;
        this.scanline = i7;
        this.alignOfWidth = 0;
        this.alignOfHeight = 0;
    }

    public Align(int i2, int i7, int i8, int i10) {
        this.stride = i2;
        this.scanline = i7;
        this.alignOfWidth = i8;
        this.alignOfHeight = i10;
    }

    public Align(Parcel parcel) {
        this.stride = parcel.readInt();
        this.scanline = parcel.readInt();
        this.alignOfWidth = parcel.readInt();
        this.alignOfHeight = parcel.readInt();
    }
}
