package w1;

import E1.a;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import t1.C0276a;
import u1.l;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n extends C0333a {
    public static final Parcelable.Creator<n> CREATOR = new l(5);
    public final int d;
    public final IBinder e;
    public final C0276a f;
    public final boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f2010h;

    public n(int i2, IBinder iBinder, C0276a aVar, boolean z, boolean z3) {
        this.d = i2;
        this.e = iBinder;
        this.f = aVar;
        this.g = z;
        this.f2010h = z3;
    }

    public final boolean equals(Object obj) {
        Object obj2;
        if (obj == null) {
            return false;
        }
        if (this != obj) {
            if (!(obj instanceof n)) {
                return false;
            }
            n nVar = (n) obj;
            if (!this.f.equals(nVar.f)) {
                return false;
            }
            Object obj3 = null;
            IBinder iBinder = this.e;
            if (iBinder == null) {
                obj2 = null;
            } else {
                int i2 = C0314a.b;
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                if (queryLocalInterface instanceof e) {
                    obj2 = (e) queryLocalInterface;
                } else {
                    obj2 = new a(iBinder, "com.google.android.gms.common.internal.IAccountAccessor", 1);
                }
            }
            IBinder iBinder2 = nVar.e;
            if (iBinder2 != null) {
                int i7 = C0314a.b;
                IInterface queryLocalInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                if (queryLocalInterface2 instanceof e) {
                    obj3 = (e) queryLocalInterface2;
                } else {
                    obj3 = new a(iBinder2, "com.google.android.gms.common.internal.IAccountAccessor", 1);
                }
            }
            if (r.d(obj2, obj3)) {
                return true;
            }
            return false;
        }
        return true;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.Y(1, 4, parcel);
        parcel.writeInt(this.d);
        c.R(parcel, 2, this.e);
        c.S(parcel, 3, this.f, i2);
        c.Y(4, 4, parcel);
        parcel.writeInt(this.g ? 1 : 0);
        c.Y(5, 4, parcel);
        parcel.writeInt(this.f2010h ? 1 : 0);
        c.X(parcel, W6);
    }
}
