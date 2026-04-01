package w1;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import u1.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f2013a;

    public q(IBinder iBinder) {
        this.f2013a = iBinder;
    }

    public final void a(t tVar, d dVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
            obtain.writeStrongBinder(tVar);
            obtain.writeInt(1);
            l.a(dVar, obtain, 0);
            this.f2013a.transact(46, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.f2013a;
    }
}
