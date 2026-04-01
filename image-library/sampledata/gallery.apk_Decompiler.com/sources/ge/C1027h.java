package ge;

import He.F;
import ee.C0970c;
import ee.C0971d;
import ee.C0985s;
import ee.C0986t;
import ee.C0990x;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* renamed from: ge.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1027h extends C0971d {

    /* renamed from: c  reason: collision with root package name */
    public final C1036k f4514c;
    public final Q0 d;

    public C1027h(C1036k kVar, Q0 q0) {
        this.f4514c = kVar;
        F.n(q0, "time");
        this.d = q0;
    }

    public static Level l(C0970c cVar) {
        int i2 = C1024g.f4511a[cVar.ordinal()];
        if (i2 == 1 || i2 == 2) {
            return Level.FINE;
        }
        if (i2 != 3) {
            return Level.FINEST;
        }
        return Level.FINER;
    }

    public final void b(C0970c cVar, String str) {
        C0985s sVar;
        C1036k kVar = this.f4514c;
        C0990x xVar = kVar.b;
        Level l = l(cVar);
        if (C1036k.f4527c.isLoggable(l)) {
            C1036k.a(xVar, l, str);
        }
        if (k(cVar) && cVar != C0970c.DEBUG) {
            int i2 = C1024g.f4511a[cVar.ordinal()];
            if (i2 == 1) {
                sVar = C0985s.CT_ERROR;
            } else if (i2 != 2) {
                sVar = C0985s.CT_INFO;
            } else {
                sVar = C0985s.CT_WARNING;
            }
            C0985s sVar2 = sVar;
            this.d.getClass();
            long nanos = TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
            F.n(str, "description");
            F.n(sVar2, "severity");
            new C0986t(str, sVar2, nanos, (C1031i0) null);
            synchronized (kVar.f4528a) {
                try {
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public final void c(C0970c cVar, String str, Object... objArr) {
        String str2;
        Level l = l(cVar);
        if (k(cVar) || C1036k.f4527c.isLoggable(l)) {
            str2 = MessageFormat.format(str, objArr);
        } else {
            str2 = null;
        }
        b(cVar, str2);
    }

    public final boolean k(C0970c cVar) {
        if (cVar == C0970c.DEBUG) {
            return false;
        }
        synchronized (this.f4514c.f4528a) {
        }
        return false;
    }
}
