package v1;

import Kd.a;
import P1.b;
import P1.c;
import P1.h;
import Y1.f;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import ge.W0;
import java.util.Map;
import java.util.concurrent.Executor;
import t1.C0278c;
import t1.i;
import u1.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s extends n {
    public final f b;

    /* renamed from: c  reason: collision with root package name */
    public final c f1979c;
    public final i d;

    public s(int i2, f fVar, c cVar, i iVar) {
        super(i2);
        this.f1979c = cVar;
        this.b = fVar;
        this.d = iVar;
        if (i2 == 2 && fVar.b) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    public final boolean a(k kVar) {
        return this.b.b;
    }

    public final C0278c[] b(k kVar) {
        return (C0278c[]) this.b.d;
    }

    public final void c(Status status) {
        d dVar;
        this.d.getClass();
        if (status.f != null) {
            dVar = new d(status);
        } else {
            dVar = new d(status);
        }
        this.f1979c.b(dVar);
    }

    public final void d(Exception exc) {
        this.f1979c.b(exc);
    }

    public final void e(k kVar) {
        c cVar = this.f1979c;
        try {
            f fVar = this.b;
            ((h) ((f) fVar.e).d).accept(kVar.b, cVar);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e7) {
            c(n.g(e7));
        } catch (RuntimeException e8) {
            cVar.b(e8);
        }
    }

    public final void f(W0 w02, boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        c cVar = this.f1979c;
        ((Map) w02.f).put(cVar, valueOf);
        h hVar = cVar.f571a;
        W0 w03 = new W0(10, (Object) w02, (Object) cVar);
        hVar.getClass();
        ((a) hVar.d).h(new P1.f((Executor) P1.d.f572a, (b) w03));
        hVar.o();
    }
}
