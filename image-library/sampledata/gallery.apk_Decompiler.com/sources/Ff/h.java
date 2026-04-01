package Ff;

import Ae.b;
import Df.l;
import Df.n;
import Gf.i;
import Hf.C0774x;
import If.f;
import Qe.C0816f;
import Qe.C0819i;
import Ye.a;
import Ye.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1200r;
import qf.C1235b;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends s {
    public final f g;

    /* renamed from: h  reason: collision with root package name */
    public final i f3382h;

    /* renamed from: i  reason: collision with root package name */
    public final i f3383i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ k f3384j;

    /* JADX WARNING: type inference failed for: r0v6, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r9v3, types: [Gf.h, Gf.i] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public h(Ff.k r8, If.f r9) {
        /*
            r7 = this;
            java.lang.String r0 = "kotlinTypeRefiner"
            kotlin.jvm.internal.j.e(r9, r0)
            r7.f3384j = r8
            Df.n r2 = r8.f3389o
            lf.j r0 = r8.f3386h
            java.util.List r3 = r0.t
            java.lang.String r1 = "getFunctionList(...)"
            kotlin.jvm.internal.j.d(r3, r1)
            java.util.List r4 = r0.u
            java.lang.String r1 = "getPropertyList(...)"
            kotlin.jvm.internal.j.d(r4, r1)
            java.util.List r5 = r0.v
            java.lang.String r1 = "getTypeAliasList(...)"
            kotlin.jvm.internal.j.d(r5, r1)
            java.util.List r0 = r0.n
            java.lang.String r1 = "getNestedClassNameList(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            Df.n r8 = r8.f3389o
            java.lang.Object r8 = r8.b
            nf.f r8 = (nf.C1209f) r8
            java.util.ArrayList r1 = new java.util.ArrayList
            r6 = 10
            int r6 = ne.C1196n.w0(r0, r6)
            r1.<init>(r6)
            java.util.Iterator r0 = r0.iterator()
        L_0x003e:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L_0x0056
            java.lang.Object r6 = r0.next()
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
            qf.g r6 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(r8, r6)
            r1.add(r6)
            goto L_0x003e
        L_0x0056:
            Ff.e r6 = new Ff.e
            r8 = 0
            r6.<init>(r1, r8)
            r1 = r7
            r1.<init>(r2, r3, r4, r5, r6)
            java.lang.Object r7 = r2.f3358a
            Df.l r7 = (Df.l) r7
            r1.g = r9
            Gf.m r8 = r7.f3349a
            Ff.f r9 = new Ff.f
            r0 = 0
            r9.<init>(r1, r0)
            r8.getClass()
            Gf.i r0 = new Gf.i
            r0.<init>(r8, r9)
            r1.f3382h = r0
            Gf.m r7 = r7.f3349a
            Ff.f r8 = new Ff.f
            r9 = 1
            r8.<init>(r1, r9)
            r7.getClass()
            Gf.i r9 = new Gf.i
            r9.<init>(r7, r8)
            r1.f3383i = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Ff.h.<init>(Ff.k, If.f):void");
    }

    public final Collection a(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        s(gVar, aVar);
        return super.a(gVar, aVar);
    }

    public final C0819i c(C1240g gVar, a aVar) {
        C0816f fVar;
        j.e(gVar, "name");
        j.e(aVar, "location");
        s(gVar, aVar);
        D0.f fVar2 = this.f3384j.s;
        if (fVar2 == null || (fVar = (C0816f) ((Gf.j) fVar2.f).invoke(gVar)) == null) {
            return super.c(gVar, aVar);
        }
        return fVar;
    }

    public final Collection d(Af.f fVar, b bVar) {
        j.e(fVar, "kindFilter");
        return (Collection) this.f3382h.invoke();
    }

    public final Collection f(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        s(gVar, aVar);
        return super.f(gVar, aVar);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: ne.t} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void h(java.util.ArrayList r4, Ae.b r5) {
        /*
            r3 = this;
            Ff.k r3 = r3.f3384j
            D0.f r3 = r3.s
            if (r3 == 0) goto L_0x0038
            java.lang.Object r5 = r3.e
            java.util.LinkedHashMap r5 = (java.util.LinkedHashMap) r5
            java.util.Set r5 = r5.keySet()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r5 = r5.iterator()
        L_0x0017:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0039
            java.lang.Object r1 = r5.next()
            qf.g r1 = (qf.C1240g) r1
            java.lang.String r2 = "name"
            kotlin.jvm.internal.j.e(r1, r2)
            java.lang.Object r2 = r3.f
            Gf.j r2 = (Gf.j) r2
            java.lang.Object r1 = r2.invoke(r1)
            Qe.f r1 = (Qe.C0816f) r1
            if (r1 == 0) goto L_0x0017
            r0.add(r1)
            goto L_0x0017
        L_0x0038:
            r0 = 0
        L_0x0039:
            if (r0 != 0) goto L_0x003d
            ne.t r0 = ne.C1202t.d
        L_0x003d:
            r4.addAll(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Ff.h.h(java.util.ArrayList, Ae.b):void");
    }

    public final void j(ArrayList arrayList, C1240g gVar) {
        j.e(gVar, "name");
        ArrayList arrayList2 = new ArrayList();
        for (C0774x A10 : (Collection) this.f3383i.invoke()) {
            arrayList2.addAll(A10.A().a(gVar, c.FOR_ALREADY_TRACKED));
        }
        n nVar = this.b;
        arrayList.addAll(((l) nVar.f3358a).n.e(gVar, this.f3384j));
        ArrayList arrayList3 = new ArrayList(arrayList);
        C1240g gVar2 = gVar;
        ((If.l) ((l) nVar.f3358a).q).d.h(gVar2, arrayList2, arrayList3, this.f3384j, new g(arrayList, 0));
    }

    public final void k(ArrayList arrayList, C1240g gVar) {
        j.e(gVar, "name");
        ArrayList arrayList2 = new ArrayList();
        for (C0774x A10 : (Collection) this.f3383i.invoke()) {
            arrayList2.addAll(A10.A().f(gVar, c.FOR_ALREADY_TRACKED));
        }
        ArrayList arrayList3 = new ArrayList(arrayList);
        C1240g gVar2 = gVar;
        ((If.l) ((l) this.b.f3358a).q).d.h(gVar2, arrayList2, arrayList3, this.f3384j, new g(arrayList, 0));
    }

    public final C1235b l(C1240g gVar) {
        j.e(gVar, "name");
        return this.f3384j.k.d(gVar);
    }

    public final Set n() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (C0774x A10 : this.f3384j.q.h()) {
            Set e = A10.A().e();
            if (e == null) {
                return null;
            }
            C1200r.A0(e, linkedHashSet);
        }
        return linkedHashSet;
    }

    public final Set o() {
        k kVar = this.f3384j;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (C0774x A10 : kVar.q.h()) {
            C1200r.A0(A10.A().b(), linkedHashSet);
        }
        linkedHashSet.addAll(((l) this.b.f3358a).n.d(kVar));
        return linkedHashSet;
    }

    public final Set p() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (C0774x A10 : this.f3384j.q.h()) {
            C1200r.A0(A10.A().g(), linkedHashSet);
        }
        return linkedHashSet;
    }

    public final boolean r(v vVar) {
        return ((l) this.b.f3358a).f3354o.b(this.f3384j, vVar);
    }

    public final void s(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        j.e(((l) this.b.f3358a).f3352i, "<this>");
        j.e(this.f3384j, "scopeOwner");
    }
}
