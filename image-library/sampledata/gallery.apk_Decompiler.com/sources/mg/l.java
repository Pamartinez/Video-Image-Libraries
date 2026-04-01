package mg;

import G0.c;
import ig.f;
import ig.k;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import jg.a;
import kg.C1140v;
import kg.Q;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import lg.C;
import lg.C1174b;
import lg.i;
import lg.m;
import lg.y;
import ne.C1182C;
import ne.z;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class l extends a {

    /* renamed from: h  reason: collision with root package name */
    public final y f4932h;

    /* renamed from: i  reason: collision with root package name */
    public final String f4933i;

    /* renamed from: j  reason: collision with root package name */
    public final f f4934j;
    public int k;
    public boolean l;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public l(C1174b bVar, y yVar, String str, f fVar) {
        super(bVar);
        j.e(bVar, "json");
        this.f4932h = yVar;
        this.f4933i = str;
        this.f4934j = fVar;
    }

    public final boolean D() {
        if (this.l || !super.D()) {
            return false;
        }
        return true;
    }

    public String Q(f fVar, int i2) {
        Object obj;
        j.e(fVar, "descriptor");
        C1174b bVar = this.f;
        h.m(fVar, bVar);
        String f = fVar.f(i2);
        if (this.g.g && !T().d.keySet().contains(f)) {
            j.e(bVar, "<this>");
            c cVar = bVar.f4895c;
            C1140v vVar = new C1140v(1, fVar, bVar);
            cVar.getClass();
            i iVar = h.f4927a;
            Object m = cVar.m(fVar, iVar);
            if (m == null) {
                m = vVar.invoke();
                ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) cVar.e;
                Object obj2 = concurrentHashMap.get(fVar);
                if (obj2 == null) {
                    obj2 = new ConcurrentHashMap(2);
                    concurrentHashMap.put(fVar, obj2);
                }
                ((Map) obj2).put(iVar, m);
            }
            Map map = (Map) m;
            Iterator it = T().d.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                Integer num = (Integer) map.get((String) obj);
                if (num != null && num.intValue() == i2) {
                    break;
                }
            }
            String str = (String) obj;
            if (str != null) {
                return str;
            }
        }
        return f;
    }

    /* renamed from: W */
    public y T() {
        return this.f4932h;
    }

    public final a a(f fVar) {
        j.e(fVar, "descriptor");
        f fVar2 = this.f4934j;
        if (fVar != fVar2) {
            return super.a(fVar);
        }
        lg.l C5 = C();
        if (C5 instanceof y) {
            String str = this.f4933i;
            return new l(this.f, (y) C5, str, fVar2);
        }
        StringBuilder sb2 = new StringBuilder("Expected ");
        w wVar = v.f4727a;
        sb2.append(wVar.b(y.class));
        sb2.append(" as the serialized body of ");
        sb2.append(fVar2.i());
        sb2.append(", but had ");
        sb2.append(wVar.b(C5.getClass()));
        throw h.c(-1, sb2.toString());
    }

    public void b(f fVar) {
        Set set;
        Iterable iterable;
        j.e(fVar, "descriptor");
        i iVar = this.g;
        if (!iVar.b && !(fVar.b() instanceof ig.c)) {
            C1174b bVar = this.f;
            h.m(fVar, bVar);
            if (!iVar.g) {
                set = Q.b(fVar);
            } else {
                Set b = Q.b(fVar);
                j.e(bVar, "<this>");
                Map map = (Map) bVar.f4895c.m(fVar, h.f4927a);
                if (map != null) {
                    iterable = map.keySet();
                } else {
                    iterable = null;
                }
                if (iterable == null) {
                    iterable = ne.v.d;
                }
                set = C1182C.b0(b, iterable);
            }
            for (String str : T().d.keySet()) {
                if (!set.contains(str) && !j.a(str, this.f4933i)) {
                    String yVar = T().toString();
                    j.e(str, "key");
                    j.e(yVar, "input");
                    StringBuilder k10 = N2.j.k("Encountered an unknown key '", str, "'.\nUse 'ignoreUnknownKeys = true' in 'Json {}' builder to ignore unknown keys.\nCurrent input: ");
                    k10.append(h.l(yVar, -1));
                    throw h.c(-1, k10.toString());
                }
            }
        }
    }

    public int d(f fVar) {
        C c5;
        j.e(fVar, "descriptor");
        while (this.k < fVar.e()) {
            int i2 = this.k;
            this.k = i2 + 1;
            String S = S(fVar, i2);
            boolean z = true;
            int i7 = this.k - 1;
            this.l = false;
            boolean containsKey = T().containsKey(S);
            C1174b bVar = this.f;
            if (!containsKey) {
                if (bVar.f4894a.f4902c || fVar.j(i7) || !fVar.h(i7).c()) {
                    z = false;
                }
                this.l = z;
                if (!z) {
                    continue;
                }
            }
            if (this.g.e) {
                f h5 = fVar.h(i7);
                if (h5.c() || !(i(S) instanceof lg.v)) {
                    if (j.a(h5.b(), k.d) && (!h5.c() || !(i(S) instanceof lg.v))) {
                        lg.l i8 = i(S);
                        String str = null;
                        if (i8 instanceof C) {
                            c5 = (C) i8;
                        } else {
                            c5 = null;
                        }
                        if (c5 != null) {
                            int i10 = m.f4903a;
                            if (!(c5 instanceof lg.v)) {
                                str = c5.i();
                            }
                        }
                        if (str != null && h.i(h5, bVar, str) == -3) {
                        }
                    }
                }
            }
            return i7;
        }
        return -1;
    }

    public lg.l i(String str) {
        j.e(str, "tag");
        return (lg.l) z.Y(str, T());
    }
}
