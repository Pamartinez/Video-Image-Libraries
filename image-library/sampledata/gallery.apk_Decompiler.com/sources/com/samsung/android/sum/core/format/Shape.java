package com.samsung.android.sum.core.format;

import android.graphics.Rect;
import android.os.Parcelable;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Shape extends Serializable, Parcelable, Duplicable<Shape>, Comparable<Shape> {
    public static final int TYPE_NHWC = 2;
    public static final int TYPE_NONE = 0;
    public static final int TYPE_NWHC = 1;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface Type {
    }

    static MutableShape mutableOf() {
        return mutableOf(-1, 0, 0, -1);
    }

    static Shape of(int i2) {
        return of(-1, 1, i2, -1);
    }

    static Rect rectOf(int i2, int i7) {
        return new Rect(0, 0, i2, i7);
    }

    int getBatch();

    int getChannels();

    int getCols();

    int getDimension();

    int getRows();

    int getTotal();

    boolean isEmpty() {
        if (getTotal() == 0) {
            return true;
        }
        return false;
    }

    int[] toArray(int i2);

    <V extends MutableShape> V toMutableShape();

    static MutableShape mutableOf(int i2, int i7, int i8, int i10) {
        return new StapleMutableShape(i2, i7, i8, i10);
    }

    static Shape of(int i2, int i7) {
        return of(-1, i2, i7, -1);
    }

    static Rect rectOf(int i2, int i7, int i8, int i10) {
        return new Rect(i2, i7, i8, i10);
    }

    static Shape of(int i2, int i7, int i8, int i10) {
        return new StapleShape(mutableOf(i2, i7, i8, i10));
    }
}
