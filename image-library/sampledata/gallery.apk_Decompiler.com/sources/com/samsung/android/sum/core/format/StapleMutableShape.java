package com.samsung.android.sum.core.format;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.types.ShapeType;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StapleMutableShape implements MutableShape {
    public static final Parcelable.Creator<StapleMutableShape> CREATOR = new Parcelable.Creator<StapleMutableShape>() {
        public StapleMutableShape createFromParcel(Parcel parcel) {
            return new StapleMutableShape(parcel);
        }

        public StapleMutableShape[] newArray(int i2) {
            return new StapleMutableShape[i2];
        }
    };
    private int batch;
    private int channels;
    private int cols;
    private int rows;

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MutableShape)) {
            return false;
        }
        MutableShape mutableShape = (MutableShape) obj;
        return Arrays.equals(new int[]{this.batch, this.rows, this.cols, this.channels}, new int[]{mutableShape.getBatch(), mutableShape.getRows(), mutableShape.getCols(), mutableShape.getChannels()});
    }

    public int getBatch() {
        return this.batch;
    }

    public int getChannels() {
        return this.channels;
    }

    public int getCols() {
        return this.cols;
    }

    public int getDimension() {
        return this.cols * this.rows;
    }

    public int getRows() {
        return this.rows;
    }

    public int getTotal() {
        return this.batch * this.cols * this.rows * this.channels;
    }

    public MutableShape scale(Float f) {
        this.rows = (int) (f.floatValue() * ((float) this.rows));
        this.cols = (int) (f.floatValue() * ((float) this.cols));
        return this;
    }

    public MutableShape setBatch(int i2) {
        this.batch = i2;
        return this;
    }

    public MutableShape setChannels(int i2) {
        this.channels = i2;
        return this;
    }

    public MutableShape setCols(int i2) {
        this.cols = i2;
        return this;
    }

    public MutableShape setRows(int i2) {
        this.rows = i2;
        return this;
    }

    public int[] toArray(int i2) {
        if (i2 == 2) {
            return new int[]{this.batch, this.cols, this.rows, this.channels};
        }
        return new int[]{this.batch, this.rows, this.cols, this.channels};
    }

    public Shape toShape() {
        return new StapleShape((MutableShape) this);
    }

    public String toString() {
        return Def.tagOf((Object) this) + Def.fmtstr("batch/rows/cols/channels=[%d %d %d %d]", Integer.valueOf(this.batch), Integer.valueOf(this.rows), Integer.valueOf(this.cols), Integer.valueOf(this.channels));
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.batch);
        parcel.writeInt(this.rows);
        parcel.writeInt(this.cols);
        parcel.writeInt(this.channels);
    }

    public StapleMutableShape() {
        this.batch = -1;
        this.rows = 0;
        this.cols = 0;
        this.channels = -1;
    }

    public int compareTo(Shape shape) {
        return getTotal() - shape.getTotal();
    }

    public Shape dup() {
        try {
            return (Shape) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new IllegalStateException("fail to clone");
        }
    }

    public MutableShape dupAll() {
        return new StapleMutableShape(this.batch, this.rows, this.cols, this.channels);
    }

    public MutableShape scale(Pair<Float, Float> pair) {
        this.rows = (int) (((Float) pair.first).floatValue() * ((float) this.rows));
        this.cols = (int) (((Float) pair.second).floatValue() * ((float) this.cols));
        return this;
    }

    public StapleMutableShape(int i2, int i7, int i8, int i10) {
        this.batch = i2;
        this.rows = i7;
        this.cols = i8;
        this.channels = i10;
    }

    public StapleMutableShape(ShapeType shapeType, int[] iArr) {
        this.batch = iArr[0];
        if (shapeType == ShapeType.NHWC) {
            this.rows = iArr[1];
            this.cols = iArr[2];
        } else {
            this.cols = iArr[1];
            this.rows = iArr[2];
        }
        this.channels = iArr[3];
    }

    private StapleMutableShape(Parcel parcel) {
        this.batch = parcel.readInt();
        this.rows = parcel.readInt();
        this.cols = parcel.readInt();
        this.channels = parcel.readInt();
    }

    public <V extends MutableShape> V toMutableShape() {
        return this;
    }
}
