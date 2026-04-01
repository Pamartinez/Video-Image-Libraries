package w1;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import u1.l;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends C0333a {
    public static final Parcelable.Creator<f> CREATOR = new l(3);
    public final int d;
    public final int e;
    public final int f;
    public final long g;

    /* renamed from: h  reason: collision with root package name */
    public final long f2003h;

    /* renamed from: i  reason: collision with root package name */
    public final String f2004i;

    /* renamed from: j  reason: collision with root package name */
    public final String f2005j;
    public final int k;
    public final int l;

    public f(int i2, int i7, int i8, long j2, long j3, String str, String str2, int i10, int i11) {
        this.d = i2;
        this.e = i7;
        this.f = i8;
        this.g = j2;
        this.f2003h = j3;
        this.f2004i = str;
        this.f2005j = str2;
        this.k = i10;
        this.l = i11;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.Y(1, 4, parcel);
        parcel.writeInt(this.d);
        c.Y(2, 4, parcel);
        parcel.writeInt(this.e);
        c.Y(3, 4, parcel);
        parcel.writeInt(this.f);
        c.Y(4, 8, parcel);
        parcel.writeLong(this.g);
        c.Y(5, 8, parcel);
        parcel.writeLong(this.f2003h);
        c.T(parcel, 6, this.f2004i);
        c.T(parcel, 7, this.f2005j);
        c.Y(8, 4, parcel);
        parcel.writeInt(this.k);
        c.Y(9, 4, parcel);
        parcel.writeInt(this.l);
        c.X(parcel, W6);
    }
}
