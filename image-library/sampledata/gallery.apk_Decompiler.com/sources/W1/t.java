package w1;

import F1.a;
import F1.b;
import G1.f;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t extends a {
    public com.google.android.gms.common.internal.a b;

    /* renamed from: c  reason: collision with root package name */
    public final int f2017c;

    public t(com.google.android.gms.common.internal.a aVar, int i2) {
        super("com.google.android.gms.common.internal.IGmsCallbacks", 0);
        this.b = aVar;
        this.f2017c = i2;
    }

    public final boolean c(int i2, Parcel parcel, Parcel parcel2) {
        h hVar;
        if (i2 == 1) {
            b.b(parcel);
            r.c(this.b, "onPostInitComplete can be called only once per call to getRemoteService");
            com.google.android.gms.common.internal.a aVar = this.b;
            int i7 = this.f2017c;
            aVar.getClass();
            v vVar = new v(aVar, parcel.readInt(), parcel.readStrongBinder(), (Bundle) b.a(parcel, Bundle.CREATOR));
            s sVar = aVar.e;
            sVar.sendMessage(sVar.obtainMessage(1, i7, -1, vVar));
            this.b = null;
        } else if (i2 == 2) {
            parcel.readInt();
            Bundle bundle = (Bundle) b.a(parcel, Bundle.CREATOR);
            b.b(parcel);
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        } else if (i2 != 3) {
            return false;
        } else {
            int readInt = parcel.readInt();
            IBinder readStrongBinder = parcel.readStrongBinder();
            x xVar = (x) b.a(parcel, x.CREATOR);
            b.b(parcel);
            com.google.android.gms.common.internal.a aVar2 = this.b;
            r.c(aVar2, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            r.b(xVar);
            aVar2.u = xVar;
            if (aVar2 instanceof f) {
                C0316c cVar = xVar.g;
                g b5 = g.b();
                if (cVar == null) {
                    hVar = null;
                } else {
                    hVar = cVar.d;
                }
                synchronized (b5) {
                    if (hVar == null) {
                        hVar = g.f2006c;
                    } else {
                        h hVar2 = (h) b5.f2007a;
                        if (hVar2 != null) {
                            if (hVar2.d < hVar.d) {
                            }
                        }
                    }
                    b5.f2007a = hVar;
                }
            }
            Bundle bundle2 = xVar.d;
            r.c(this.b, "onPostInitComplete can be called only once per call to getRemoteService");
            com.google.android.gms.common.internal.a aVar3 = this.b;
            int i8 = this.f2017c;
            aVar3.getClass();
            v vVar2 = new v(aVar3, readInt, readStrongBinder, bundle2);
            s sVar2 = aVar3.e;
            sVar2.sendMessage(sVar2.obtainMessage(1, i8, -1, vVar2));
            this.b = null;
        }
        parcel2.writeNoException();
        return true;
    }
}
