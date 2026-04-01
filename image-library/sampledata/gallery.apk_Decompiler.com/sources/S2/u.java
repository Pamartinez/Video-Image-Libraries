package S2;

import T2.c;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u extends v {
    public static final HashMap f = new HashMap(100);
    public static final u g = f(c.v);
    public final c d;
    public t e;

    static {
        f(c.y);
        f(c.z);
        f(c.f816A);
        f(c.B);
        f(c.f817C);
        f(c.E);
        f(c.D);
        f(c.f818F);
        f(c.f819G);
        f(c.f820H);
        f(c.f821I);
        f(c.f822J);
        f(c.f823K);
        f(c.L);
        f(c.f825N);
        f(c.f824M);
        f(c.f827P);
    }

    public u(c cVar) {
        if (cVar == null) {
            throw new NullPointerException("type == null");
        } else if (cVar != c.s) {
            this.d = cVar;
            this.e = null;
        } else {
            throw new UnsupportedOperationException("KNOWN_NULL is not representable");
        }
    }

    public static u f(c cVar) {
        u uVar;
        HashMap hashMap = f;
        synchronized (hashMap) {
            try {
                uVar = (u) hashMap.get(cVar);
                if (uVar == null) {
                    uVar = new u(cVar);
                    hashMap.put(cVar, uVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return uVar;
    }

    public final String a() {
        return this.d.a();
    }

    public final int d(a aVar) {
        return this.d.d.compareTo(((u) aVar).d.d);
    }

    public final String e() {
        return "type";
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof u) && this.d == ((u) obj).d) {
            return true;
        }
        return false;
    }

    public final c getType() {
        return c.u;
    }

    public final int hashCode() {
        return this.d.d.hashCode();
    }

    public final String toString() {
        return "type{" + this.d.a() + '}';
    }
}
