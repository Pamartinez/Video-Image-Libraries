package q1;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends RecyclerView.LayoutParams implements b {
    public static final Parcelable.Creator<i> CREATOR = new a(25);
    public float d;
    public float e;
    public int f;
    public float g;

    /* renamed from: h  reason: collision with root package name */
    public int f1869h;

    /* renamed from: i  reason: collision with root package name */
    public int f1870i;

    /* renamed from: j  reason: collision with root package name */
    public int f1871j;
    public int k;
    public boolean l;

    public final int a() {
        return this.f;
    }

    public final float c() {
        return this.e;
    }

    public final int describeContents() {
        return 0;
    }

    public final int f() {
        return this.f1869h;
    }

    public final int g() {
        return this.topMargin;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getOrder() {
        return 1;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void h(int i2) {
        this.f1870i = i2;
    }

    public final float j() {
        return this.d;
    }

    public final float l() {
        return this.g;
    }

    public final boolean m() {
        return this.l;
    }

    public final int n() {
        return this.f1871j;
    }

    public final void p(int i2) {
        this.f1869h = i2;
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
        return this.f1870i;
    }

    public final int w() {
        return this.k;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.e);
        parcel.writeInt(this.f);
        parcel.writeFloat(this.g);
        parcel.writeInt(this.f1869h);
        parcel.writeInt(this.f1870i);
        parcel.writeInt(this.f1871j);
        parcel.writeInt(this.k);
        parcel.writeByte(this.l ? (byte) 1 : 0);
        parcel.writeInt(this.bottomMargin);
        parcel.writeInt(this.leftMargin);
        parcel.writeInt(this.rightMargin);
        parcel.writeInt(this.topMargin);
        parcel.writeInt(this.height);
        parcel.writeInt(this.width);
    }
}
