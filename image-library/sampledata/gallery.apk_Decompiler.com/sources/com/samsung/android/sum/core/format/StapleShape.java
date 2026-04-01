package com.samsung.android.sum.core.format;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StapleShape implements Shape {
    public static final Parcelable.Creator<StapleShape> CREATOR = new Parcelable.Creator<StapleShape>() {
        public StapleShape createFromParcel(Parcel parcel) {
            return new StapleShape(parcel);
        }

        public StapleShape[] newArray(int i2) {
            return new StapleShape[i2];
        }
    };
    public static final int NONE = -1;
    private final MutableShape impl;

    public StapleShape(MutableShape mutableShape) {
        this.impl = mutableShape;
    }

    public MutableShape asMutable() {
        return this.impl;
    }

    public int describeContents() {
        return 0;
    }

    public int getBatch() {
        return this.impl.getBatch();
    }

    public int getChannels() {
        return this.impl.getChannels();
    }

    public int getCols() {
        return this.impl.getCols();
    }

    public int getDimension() {
        return this.impl.getDimension();
    }

    public int getRows() {
        return this.impl.getRows();
    }

    public int getTotal() {
        return this.impl.getTotal();
    }

    public int[] toArray(int i2) {
        return this.impl.toArray(i2);
    }

    public <V extends MutableShape> V toMutableShape() {
        return (MutableShape) this.impl.dupAll();
    }

    public String toString() {
        return this.impl.toString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.impl, i2);
    }

    public int compareTo(Shape shape) {
        return this.impl.compareTo(shape);
    }

    public Shape dup() {
        return new StapleShape((MutableShape) this.impl.dup());
    }

    public Shape dupAll() {
        return new StapleShape((MutableShape) this.impl.dupAll());
    }

    public StapleShape(Parcel parcel) {
        this.impl = (MutableShape) parcel.readParcelable(StapleMutableShape.class.getClassLoader());
    }
}
