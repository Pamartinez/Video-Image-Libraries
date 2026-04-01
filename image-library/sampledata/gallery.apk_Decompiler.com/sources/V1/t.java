package v1;

import Kd.a;
import P1.c;
import P1.h;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import ge.W0;
import t1.C0278c;
import u1.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t extends n {
    public final c b;

    public t(c cVar) {
        super(4);
        this.b = cVar;
    }

    public final boolean a(k kVar) {
        if (kVar.f.get((Object) null) == null) {
            return false;
        }
        throw new ClassCastException();
    }

    public final C0278c[] b(k kVar) {
        if (kVar.f.get((Object) null) == null) {
            return null;
        }
        throw new ClassCastException();
    }

    public final void c(Status status) {
        this.b.b(new d(status));
    }

    public final void d(Exception exc) {
        this.b.b(exc);
    }

    public final void e(k kVar) {
        try {
            h(kVar);
        } catch (DeadObjectException e) {
            c(n.g(e));
            throw e;
        } catch (RemoteException e7) {
            c(n.g(e7));
        } catch (RuntimeException e8) {
            this.b.b(e8);
        }
    }

    public final void h(k kVar) {
        if (kVar.f.remove((Object) null) == null) {
            c cVar = this.b;
            Boolean bool = Boolean.FALSE;
            h hVar = cVar.f571a;
            synchronized (hVar.b) {
                try {
                    if (!hVar.f575a) {
                        hVar.f575a = true;
                        hVar.f576c = bool;
                        ((a) hVar.d).i(hVar);
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        } else {
            throw new ClassCastException();
        }
    }

    public final /* bridge */ /* synthetic */ void f(W0 w02, boolean z) {
    }
}
