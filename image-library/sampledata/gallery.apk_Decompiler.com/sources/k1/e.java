package K1;

import B1.b;
import C1.a;
import H1.d;
import L1.f;
import M1.c;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.HashMap;
import w1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public final f f380a;
    public b b;

    public e(f fVar) {
        new HashMap();
        new HashMap();
        r.b(fVar);
        this.f380a = fVar;
    }

    public final M1.b a(c cVar) {
        try {
            r.c(cVar, "MarkerOptions must not be null.");
            f fVar = this.f380a;
            Parcel c5 = fVar.c();
            d.c(c5, cVar);
            Parcel b5 = fVar.b(c5, 11);
            H1.c d = H1.b.d(b5.readStrongBinder());
            b5.recycle();
            if (d == null) {
                return null;
            }
            if (cVar.t == 1) {
                return new M1.b(d);
            }
            return new M1.b(d);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void b(a aVar) {
        try {
            r.c(aVar, "CameraUpdate must not be null.");
            f fVar = this.f380a;
            a aVar2 = aVar.f379a;
            Parcel c5 = fVar.c();
            d.d(c5, aVar2);
            fVar.d(c5, 5);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void c() {
        try {
            f fVar = this.f380a;
            fVar.d(fVar.c(), 14);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final b d() {
        E1.a aVar;
        try {
            if (this.b == null) {
                f fVar = this.f380a;
                Parcel b5 = fVar.b(fVar.c(), 25);
                IBinder readStrongBinder = b5.readStrongBinder();
                if (readStrongBinder == null) {
                    aVar = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (queryLocalInterface instanceof L1.c) {
                        aVar = (L1.c) queryLocalInterface;
                    } else {
                        aVar = new E1.a(readStrongBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate", 2);
                    }
                }
                b5.recycle();
                this.b = new b(3, aVar);
            }
            return this.b;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void e(U3.a aVar) {
        f fVar = this.f380a;
        try {
            i iVar = new i(aVar, 0);
            Parcel c5 = fVar.c();
            d.d(c5, iVar);
            fVar.d(c5, 99);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void f(c cVar) {
        f fVar = this.f380a;
        if (cVar == null) {
            try {
                Parcel c5 = fVar.c();
                d.d(c5, (IInterface) null);
                fVar.d(c5, 28);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            i iVar = new i(cVar);
            Parcel c6 = fVar.c();
            d.d(c6, iVar);
            fVar.d(c6, 28);
        }
    }

    public final void g(O3.b bVar) {
        f fVar = this.f380a;
        if (bVar == null) {
            try {
                Parcel c5 = fVar.c();
                d.d(c5, (IInterface) null);
                fVar.d(c5, 42);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            i iVar = new i(bVar);
            Parcel c6 = fVar.c();
            d.d(c6, iVar);
            fVar.d(c6, 42);
        }
    }

    public final void h(U3.a aVar) {
        f fVar = this.f380a;
        if (aVar == null) {
            try {
                Parcel c5 = fVar.c();
                d.d(c5, (IInterface) null);
                fVar.d(c5, 30);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            i iVar = new i(aVar);
            Parcel c6 = fVar.c();
            d.d(c6, iVar);
            fVar.d(c6, 30);
        }
    }

    public final void i(int i2, int i7, int i8) {
        try {
            f fVar = this.f380a;
            Parcel c5 = fVar.c();
            c5.writeInt(i2);
            c5.writeInt(i7);
            c5.writeInt(i8);
            c5.writeInt(0);
            fVar.d(c5, 39);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void j(U3.a aVar) {
        try {
            f fVar = this.f380a;
            i iVar = new i(aVar, (byte) 0);
            Parcel c5 = fVar.c();
            d.d(c5, iVar);
            c5.writeStrongBinder((IBinder) null);
            fVar.d(c5, 38);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
