package M1;

import B1.b;
import B3.a;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import com.google.android.gms.maps.model.LatLng;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class c extends C0333a {
    public static final Parcelable.Creator<c> CREATOR = new a(11);
    public LatLng d;
    public String e;
    public String f;
    public b g;

    /* renamed from: h  reason: collision with root package name */
    public float f418h = 0.5f;

    /* renamed from: i  reason: collision with root package name */
    public float f419i = 1.0f;

    /* renamed from: j  reason: collision with root package name */
    public boolean f420j;
    public boolean k = true;
    public boolean l = false;
    public float m = 0.0f;
    public float n = 0.5f;

    /* renamed from: o  reason: collision with root package name */
    public float f421o = 0.0f;

    /* renamed from: p  reason: collision with root package name */
    public float f422p = 1.0f;
    public float q;
    public int r = 0;
    public View s;
    public int t;
    public String u;
    public float v;

    public final void writeToParcel(Parcel parcel, int i2) {
        IBinder iBinder;
        int W6 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.W(parcel, 20293);
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.S(parcel, 2, this.d, i2);
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.T(parcel, 3, this.e);
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.T(parcel, 4, this.f);
        b bVar = this.g;
        if (bVar == null) {
            iBinder = null;
        } else {
            iBinder = ((C1.a) bVar.e).asBinder();
        }
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.R(parcel, 5, iBinder);
        float f5 = this.f418h;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(6, 4, parcel);
        parcel.writeFloat(f5);
        float f8 = this.f419i;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(7, 4, parcel);
        parcel.writeFloat(f8);
        boolean z = this.f420j;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(8, 4, parcel);
        parcel.writeInt(z ? 1 : 0);
        boolean z3 = this.k;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(9, 4, parcel);
        parcel.writeInt(z3 ? 1 : 0);
        boolean z7 = this.l;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(10, 4, parcel);
        parcel.writeInt(z7 ? 1 : 0);
        float f10 = this.m;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(11, 4, parcel);
        parcel.writeFloat(f10);
        float f11 = this.n;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(12, 4, parcel);
        parcel.writeFloat(f11);
        float f12 = this.f421o;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(13, 4, parcel);
        parcel.writeFloat(f12);
        float f13 = this.f422p;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(14, 4, parcel);
        parcel.writeFloat(f13);
        float f14 = this.q;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(15, 4, parcel);
        parcel.writeFloat(f14);
        int i7 = this.r;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(17, 4, parcel);
        parcel.writeInt(i7);
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.R(parcel, 18, new C1.b(this.s));
        int i8 = this.t;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(19, 4, parcel);
        parcel.writeInt(i8);
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.T(parcel, 20, this.u);
        float f15 = this.v;
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(21, 4, parcel);
        parcel.writeFloat(f15);
        com.samsung.context.sdk.samsunganalytics.internal.sender.c.X(parcel, W6);
    }
}
