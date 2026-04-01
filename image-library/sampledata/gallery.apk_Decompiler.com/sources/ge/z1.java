package ge;

import e5.C0451a;
import ee.C0968a;
import ee.C0969b;
import ee.C0971d;
import ee.Q;
import ee.S;
import ee.a0;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class z1 extends C0971d {

    /* renamed from: c  reason: collision with root package name */
    public final C0971d f4577c;
    public final /* synthetic */ A1 d;

    public z1(A1 a12, C0971d dVar) {
        this.d = a12;
        this.f4577c = dVar;
    }

    public final void e(a0 a0Var) {
        this.f4577c.e(a0Var);
        this.d.f.execute(new C0451a(20, this));
    }

    public final void i(S s) {
        C0969b bVar = s.b;
        IdentityHashMap identityHashMap = bVar.f4292a;
        C0968a aVar = A1.g;
        if (identityHashMap.get(aVar) == null) {
            List list = Collections.EMPTY_LIST;
            C0969b bVar2 = C0969b.b;
            List list2 = s.f4280a;
            Q q = s.f4281c;
            bVar.getClass();
            y1 y1Var = new y1(this.d);
            IdentityHashMap identityHashMap2 = new IdentityHashMap(1);
            identityHashMap2.put(aVar, y1Var);
            for (Map.Entry entry : bVar.f4292a.entrySet()) {
                if (!identityHashMap2.containsKey(entry.getKey())) {
                    identityHashMap2.put((C0968a) entry.getKey(), entry.getValue());
                }
            }
            this.f4577c.i(new S(list2, new C0969b(identityHashMap2), q));
            return;
        }
        throw new IllegalStateException("RetryingNameResolver can only be used once to wrap a NameResolver");
    }
}
