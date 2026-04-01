package w1;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import u1.l;
import x1.C0333a;

/* renamed from: w1.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0316c extends C0333a {
    public static final Parcelable.Creator<C0316c> CREATOR = new l(8);
    public final h d;
    public final boolean e;
    public final boolean f;
    public final int[] g;

    /* renamed from: h  reason: collision with root package name */
    public final int f1996h;

    /* renamed from: i  reason: collision with root package name */
    public final int[] f1997i;

    public C0316c(h hVar, boolean z, boolean z3, int[] iArr, int i2, int[] iArr2) {
        this.d = hVar;
        this.e = z;
        this.f = z3;
        this.g = iArr;
        this.f1996h = i2;
        this.f1997i = iArr2;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.S(parcel, 1, this.d, i2);
        c.Y(2, 4, parcel);
        parcel.writeInt(this.e ? 1 : 0);
        c.Y(3, 4, parcel);
        parcel.writeInt(this.f ? 1 : 0);
        int[] iArr = this.g;
        if (iArr != null) {
            int W10 = c.W(parcel, 4);
            parcel.writeIntArray(iArr);
            c.X(parcel, W10);
        }
        c.Y(5, 4, parcel);
        parcel.writeInt(this.f1996h);
        int[] iArr2 = this.f1997i;
        if (iArr2 != null) {
            int W11 = c.W(parcel, 6);
            parcel.writeIntArray(iArr2);
            c.X(parcel, W11);
        }
        c.X(parcel, W6);
    }
}
