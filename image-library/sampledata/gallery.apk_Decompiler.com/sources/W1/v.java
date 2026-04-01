package w1;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.a;
import t1.C0276a;
import u1.g;
import u1.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v extends o {
    public final IBinder g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ a f2019h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public v(a aVar, int i2, IBinder iBinder, Bundle bundle) {
        super(aVar, i2, bundle);
        this.f2019h = aVar;
        this.g = iBinder;
    }

    public final void a(C0276a aVar) {
        g gVar = this.f2019h.f1343o;
        if (gVar != null) {
            ((h) gVar.f2007a).a(aVar);
        }
        System.currentTimeMillis();
    }

    public final boolean b() {
        IBinder iBinder = this.g;
        try {
            r.b(iBinder);
            String interfaceDescriptor = iBinder.getInterfaceDescriptor();
            a aVar = this.f2019h;
            if (!aVar.q().equals(interfaceDescriptor)) {
                String q = aVar.q();
                Log.w("GmsClient", "service descriptor mismatch: " + q + " vs. " + interfaceDescriptor);
                return false;
            }
            IInterface m = aVar.m(iBinder);
            if (m == null || (!a.u(aVar, 2, 4, m) && !a.u(aVar, 3, 4, m))) {
                return false;
            }
            aVar.s = null;
            g gVar = aVar.n;
            if (gVar == null) {
                return true;
            }
            ((g) gVar.f2007a).onConnected();
            return true;
        } catch (RemoteException unused) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }
}
