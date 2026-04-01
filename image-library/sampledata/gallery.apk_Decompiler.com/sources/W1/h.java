package w1;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import u1.l;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends C0333a {
    public static final Parcelable.Creator<h> CREATOR = new l(6);
    public final int d;
    public final boolean e;
    public final boolean f;
    public final int g;

    /* renamed from: h  reason: collision with root package name */
    public final int f2008h;

    public h(int i2, boolean z, boolean z3, int i7, int i8) {
        this.d = i2;
        this.e = z;
        this.f = z3;
        this.g = i7;
        this.f2008h = i8;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.Y(1, 4, parcel);
        parcel.writeInt(this.d);
        c.Y(2, 4, parcel);
        parcel.writeInt(this.e ? 1 : 0);
        c.Y(3, 4, parcel);
        parcel.writeInt(this.f ? 1 : 0);
        c.Y(4, 4, parcel);
        parcel.writeInt(this.g);
        c.Y(5, 4, parcel);
        parcel.writeInt(this.f2008h);
        c.X(parcel, W6);
    }
}
