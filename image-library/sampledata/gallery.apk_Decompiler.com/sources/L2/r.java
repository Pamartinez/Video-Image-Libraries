package L2;

import S2.u;
import T2.c;
import c0.C0086a;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r {
    public static final r d;
    public static final r e;
    public static final r f;
    public static final r g;

    /* renamed from: h  reason: collision with root package name */
    public static final r f409h;

    /* renamed from: i  reason: collision with root package name */
    public static final r f410i;

    /* renamed from: j  reason: collision with root package name */
    public static final r f411j;
    public static final r k;
    public static final r l;
    public static final r m = new r(c.v);
    public static final HashMap n;

    /* renamed from: a  reason: collision with root package name */
    public final String f412a;
    public final c b;

    /* renamed from: c  reason: collision with root package name */
    public final u f413c;

    static {
        r rVar = new r(c.f829j);
        d = rVar;
        r rVar2 = new r(c.k);
        e = rVar2;
        r rVar3 = new r(c.l);
        f = rVar3;
        r rVar4 = new r(c.m);
        g = rVar4;
        r rVar5 = new r(c.n);
        f409h = rVar5;
        r rVar6 = new r(c.f830o);
        f410i = rVar6;
        r rVar7 = new r(c.f831p);
        f411j = rVar7;
        r rVar8 = new r(c.q);
        k = rVar8;
        r rVar9 = new r(c.r);
        l = rVar9;
        new r(c.w);
        HashMap hashMap = new HashMap();
        n = hashMap;
        hashMap.put(Boolean.TYPE, rVar);
        hashMap.put(Byte.TYPE, rVar2);
        hashMap.put(Character.TYPE, rVar3);
        hashMap.put(Double.TYPE, rVar4);
        hashMap.put(Float.TYPE, rVar5);
        hashMap.put(Integer.TYPE, rVar6);
        hashMap.put(Long.TYPE, rVar7);
        hashMap.put(Short.TYPE, rVar8);
        hashMap.put(Void.TYPE, rVar9);
    }

    public r(String str, c cVar) {
        if (str == null || cVar == null) {
            throw null;
        }
        this.f412a = str;
        this.b = cVar;
        this.f413c = u.f(cVar);
    }

    public static r a(Class cls) {
        c cVar;
        if (cls.isPrimitive()) {
            return (r) n.get(cls);
        }
        String replace = cls.getName().replace('.', '/');
        if (!cls.isArray()) {
            replace = C0086a.h(';', "L", replace);
        }
        try {
            if (replace.equals("V")) {
                cVar = c.r;
            } else {
                cVar = c.g(replace);
            }
            return new r(replace, cVar);
        } catch (NullPointerException unused) {
            throw new NullPointerException("descriptor == null");
        }
    }

    public final q b(r rVar, String str, r... rVarArr) {
        return new q(this, rVar, str, new s(rVarArr));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r) || !((r) obj).f412a.equals(this.f412a)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.f412a.hashCode();
    }

    public final String toString() {
        return this.f412a;
    }

    public r(c cVar) {
        this(cVar.d, cVar);
    }
}
