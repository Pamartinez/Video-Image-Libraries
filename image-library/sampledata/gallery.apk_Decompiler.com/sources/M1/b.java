package M1;

import H1.a;
import H1.c;
import H1.d;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.LatLng;
import w1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final c f417a;

    public b(c cVar) {
        r.b(cVar);
        this.f417a = cVar;
    }

    public final LatLng a() {
        try {
            a aVar = (a) this.f417a;
            Parcel b = aVar.b(aVar.c(), 4);
            LatLng latLng = (LatLng) d.a(b, LatLng.CREATOR);
            b.recycle();
            return latLng;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof b)) {
            return false;
        }
        try {
            c cVar = this.f417a;
            c cVar2 = ((b) obj).f417a;
            a aVar = (a) cVar;
            Parcel c5 = aVar.c();
            d.d(c5, cVar2);
            Parcel b = aVar.b(c5, 16);
            if (b.readInt() != 0) {
                z = true;
            }
            b.recycle();
            return z;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final int hashCode() {
        try {
            a aVar = (a) this.f417a;
            Parcel b = aVar.b(aVar.c(), 17);
            int readInt = b.readInt();
            b.recycle();
            return readInt;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
