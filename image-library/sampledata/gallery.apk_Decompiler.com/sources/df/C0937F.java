package df;

import A0.l;
import B0.a;
import Hf.C0754c;
import Hf.C0774x;
import Hf.Y;
import Hf.a0;
import Jd.b;
import Qf.h;
import Te.C0842c;
import We.C;
import We.q;
import Ze.C0894a;
import a.C0068a;
import cf.C0922a;
import hf.C1081c;
import hf.C1091m;
import hf.C1094p;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import o1.C0246a;

/* renamed from: df.F  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0937F extends C0842c {

    /* renamed from: o  reason: collision with root package name */
    public final a f4238o;

    /* renamed from: p  reason: collision with root package name */
    public final C f4239p;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0937F(B0.a r11, We.C r12, int r13, Qe.C0823m r14) {
        /*
            r10 = this;
            java.lang.String r0 = "javaTypeParameter"
            kotlin.jvm.internal.j.e(r12, r0)
            java.lang.Object r0 = r11.d
            cf.a r0 = (cf.C0922a) r0
            Gf.p r2 = r0.f4006a
            cf.c r4 = new cf.c
            r1 = 0
            r4.<init>(r11, r12, r1)
            java.lang.reflect.TypeVariable r1 = r12.f3878a
            java.lang.String r1 = r1.getName()
            qf.g r5 = qf.C1240g.e(r1)
            Hf.d0 r6 = Hf.d0.INVARIANT
            r7 = 0
            Qe.S r9 = r0.m
            r1 = r10
            r8 = r13
            r3 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            r1.f4238o = r11
            r1.f4239p = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: df.C0937F.<init>(B0.a, We.C, int, Qe.m):void");
    }

    public final List E0(List list) {
        C0774x xVar;
        C1081c cVar;
        C0937F f;
        C0774x xVar2;
        a aVar = this.f4238o;
        C1081c cVar2 = ((C0922a) aVar.d).r;
        cVar2.getClass();
        Iterable<C0774x> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (C0774x xVar3 : iterable) {
            C1091m mVar = C1091m.g;
            j.e(xVar3, "<this>");
            if (a0.c(xVar3, mVar, (h) null)) {
                f = this;
                cVar = cVar2;
                xVar2 = xVar3;
            } else {
                f = this;
                cVar = cVar2;
                xVar2 = xVar3;
                xVar = cVar.a(new b(f, false, aVar, C0894a.TYPE_PARAMETER_BOUNDS, false), xVar2, C1202t.d, (C1094p) null, false);
                if (xVar != null) {
                    arrayList.add(xVar);
                    this = f;
                    cVar2 = cVar;
                }
            }
            xVar = xVar2;
            arrayList.add(xVar);
            this = f;
            cVar2 = cVar;
        }
        return arrayList;
    }

    public final List F0() {
        Type type;
        Type[] bounds = this.f4239p.f3878a.getBounds();
        j.d(bounds, "getBounds(...)");
        ArrayList arrayList = new ArrayList(bounds.length);
        for (Type qVar : bounds) {
            arrayList.add(new q(qVar));
        }
        q qVar2 = (q) C1194l.d1(arrayList);
        if (qVar2 != null) {
            type = qVar2.f3892a;
        } else {
            type = null;
        }
        RandomAccess randomAccess = arrayList;
        if (j.a(type, Object.class)) {
            randomAccess = C1202t.d;
        }
        Collection collection = (Collection) randomAccess;
        boolean isEmpty = collection.isEmpty();
        a aVar = this.f4238o;
        if (isEmpty) {
            return C0246a.e0(C0754c.f(((C0922a) aVar.d).f4011o.f().e(), ((C0922a) aVar.d).f4011o.f().o()));
        }
        Iterable<q> iterable = collection;
        ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable, 10));
        for (q p6 : iterable) {
            arrayList2.add(((l) aVar.f34h).p(p6, C0068a.Y(Y.COMMON, false, this, 3)));
        }
        return arrayList2;
    }
}
