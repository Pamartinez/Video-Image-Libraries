package fe;

import G0.a;
import android.os.Parcel;
import android.os.RemoteException;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n extends o {
    public final void a(int i2, a aVar) {
        Parcel c5 = aVar.c();
        aVar.e = null;
        try {
            if (!this.f4361a.transact(i2, c5, (Parcel) null, 1)) {
                throw new RemoteException(C0212a.j(i2, "BinderProxy#transact(", ", FLAG_ONEWAY) returned false"));
            }
        } finally {
            c5.recycle();
        }
    }
}
