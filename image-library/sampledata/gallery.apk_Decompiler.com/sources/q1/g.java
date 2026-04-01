package q1;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends ViewGroup.MarginLayoutParams implements b {
    public static final Parcelable.Creator<g> CREATOR = new a(24);
    public int d;
    public float e;
    public float f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public float f1863h;

    /* renamed from: i  reason: collision with root package name */
    public int f1864i;

    /* renamed from: j  reason: collision with root package name */
    public int f1865j;
    public int k;
    public int l;
    public boolean m;

    public final int a() {
        return this.g;
    }

    public final float c() {
        return this.f;
    }

    public final int describeContents() {
        return 0;
    }

    public final int f() {
        return this.f1864i;
    }

    public final int g() {
        return this.topMargin;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getOrder() {
        return this.d;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void h(int i2) {
        this.f1865j = i2;
    }

    public final float j() {
        return this.e;
    }

    public final float l() {
        return this.f1863h;
    }

    public final boolean m() {
        return this.m;
    }

    public final int n() {
        return this.k;
    }

    public final void p(int i2) {
        this.f1864i = i2;
    }

    public final int q() {
        return this.bottomMargin;
    }

    public final int r() {
        return this.leftMargin;
    }

    public final int t() {
        return this.rightMargin;
    }

    public final int v() {
        return this.f1865j;
    }

    public final int w() {
        return this.l;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.d);
        parcel.writeFloat(this.e);
        parcel.writeFloat(this.f);
        parcel.writeInt(this.g);
        parcel.writeFloat(this.f1863h);
        parcel.writeInt(this.f1864i);
        parcel.writeInt(this.f1865j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeByte(this.m ? (byte) 1 : 0);
        parcel.writeInt(this.bottomMargin);
        parcel.writeInt(this.leftMargin);
        parcel.writeInt(this.rightMargin);
        parcel.writeInt(this.topMargin);
        parcel.writeInt(this.height);
        parcel.writeInt(this.width);
    }
}
