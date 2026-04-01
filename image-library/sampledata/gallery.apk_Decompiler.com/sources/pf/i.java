package pf;

import B1.b;
import D1.f;
import Dd.C0730a;
import Gd.a;
import i.C0212a;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import lf.C;
import lf.C1148a;
import lf.C1157j;
import lf.C1159l;
import lf.C1171y;
import lf.G;
import lf.Q;
import lf.Z;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import nf.C1205b;
import nf.C1209f;
import of.c;
import of.k;
import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1266p;
import rf.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public static final C1258h f5029a;

    static {
        C1258h hVar = new C1258h();
        hVar.a(k.f5008a);
        hVar.a(k.b);
        hVar.a(k.f5009c);
        hVar.a(k.d);
        hVar.a(k.e);
        hVar.a(k.f);
        hVar.a(k.g);
        hVar.a(k.f5010h);
        hVar.a(k.f5011i);
        hVar.a(k.f5012j);
        hVar.a(k.k);
        hVar.a(k.l);
        hVar.a(k.m);
        hVar.a(k.n);
        f5029a = hVar;
    }

    public static e a(C1159l lVar, C1209f fVar, b bVar) {
        String str;
        String str2;
        j.e(lVar, "proto");
        j.e(fVar, "nameResolver");
        j.e(bVar, "typeTable");
        C1266p pVar = k.f5008a;
        j.d(pVar, "constructorSignature");
        c cVar = (c) f.q(lVar, pVar);
        if (cVar == null || (cVar.e & 1) != 1) {
            str = "<init>";
        } else {
            str = fVar.getString(cVar.f);
        }
        if (cVar == null || (cVar.e & 2) != 2) {
            List list = lVar.f4859h;
            j.d(list, "getValueParameterList(...)");
            Iterable<Z> iterable = list;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (Z z : iterable) {
                j.b(z);
                String e = e(a.j0(z, bVar), fVar);
                if (e == null) {
                    return null;
                }
                arrayList.add(e);
            }
            str2 = C1194l.R0(arrayList, "", "(", ")V", (Ae.b) null, 56);
        } else {
            str2 = fVar.getString(cVar.g);
        }
        return new e(str, str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        if (r4 == null) goto L_0x0054;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static pf.d b(lf.G r4, nf.C1209f r5, B1.b r6, boolean r7) {
        /*
            java.lang.String r0 = "proto"
            kotlin.jvm.internal.j.e(r4, r0)
            java.lang.String r0 = "nameResolver"
            kotlin.jvm.internal.j.e(r5, r0)
            java.lang.String r0 = "typeTable"
            kotlin.jvm.internal.j.e(r6, r0)
            rf.p r0 = of.k.d
            java.lang.String r1 = "propertySignature"
            kotlin.jvm.internal.j.d(r0, r1)
            java.lang.Object r0 = D1.f.q(r4, r0)
            of.e r0 = (of.e) r0
            r1 = 0
            if (r0 != 0) goto L_0x0020
            goto L_0x0054
        L_0x0020:
            int r2 = r0.e
            r3 = 1
            r2 = r2 & r3
            if (r2 != r3) goto L_0x0029
            of.b r0 = r0.f
            goto L_0x002a
        L_0x0029:
            r0 = r1
        L_0x002a:
            if (r0 != 0) goto L_0x002f
            if (r7 == 0) goto L_0x002f
            goto L_0x0054
        L_0x002f:
            if (r0 == 0) goto L_0x0039
            int r7 = r0.e
            r7 = r7 & r3
            if (r7 != r3) goto L_0x0039
            int r7 = r0.f
            goto L_0x003b
        L_0x0039:
            int r7 = r4.f4749i
        L_0x003b:
            if (r0 == 0) goto L_0x004a
            int r2 = r0.e
            r3 = 2
            r2 = r2 & r3
            if (r2 != r3) goto L_0x004a
            int r4 = r0.g
            java.lang.String r4 = r5.getString(r4)
            goto L_0x0055
        L_0x004a:
            lf.Q r4 = Gd.a.f0(r4, r6)
            java.lang.String r4 = e(r4, r5)
            if (r4 != 0) goto L_0x0055
        L_0x0054:
            return r1
        L_0x0055:
            pf.d r6 = new pf.d
            java.lang.String r5 = r5.getString(r7)
            r6.<init>(r5, r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: pf.i.b(lf.G, nf.f, B1.b, boolean):pf.d");
    }

    public static e c(C1171y yVar, C1209f fVar, b bVar) {
        int i2;
        String str;
        j.e(yVar, "proto");
        j.e(fVar, "nameResolver");
        j.e(bVar, "typeTable");
        C1266p pVar = k.b;
        j.d(pVar, "methodSignature");
        c cVar = (c) f.q(yVar, pVar);
        if (cVar == null || (cVar.e & 1) != 1) {
            i2 = yVar.f4888i;
        } else {
            i2 = cVar.f;
        }
        if (cVar == null || (cVar.e & 2) != 2) {
            Collection r0 = C1195m.r0(a.c0(yVar, bVar));
            List list = yVar.r;
            j.d(list, "getValueParameterList(...)");
            Iterable<Z> iterable = list;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (Z z : iterable) {
                j.b(z);
                arrayList.add(a.j0(z, bVar));
            }
            ArrayList X02 = C1194l.X0(arrayList, r0);
            ArrayList arrayList2 = new ArrayList(C1196n.w0(X02, 10));
            Iterator it = X02.iterator();
            while (it.hasNext()) {
                String e = e((Q) it.next(), fVar);
                if (e == null) {
                    return null;
                }
                arrayList2.add(e);
            }
            String e7 = e(a.e0(yVar, bVar), fVar);
            if (e7 == null) {
                return null;
            }
            str = C0212a.p(new StringBuilder(), C1194l.R0(arrayList2, "", "(", ")", (Ae.b) null, 56), e7);
        } else {
            str = fVar.getString(cVar.g);
        }
        return new e(fVar.getString(i2), str);
    }

    public static final boolean d(G g) {
        j.e(g, "proto");
        C1205b bVar = c.f5026a;
        Object j2 = g.j(k.e);
        j.d(j2, "getExtension(...)");
        return bVar.c(((Number) j2).intValue()).booleanValue();
    }

    public static String e(Q q, C1209f fVar) {
        if ((q.f & 16) == 16) {
            return b.b(fVar.X(q.l));
        }
        return null;
    }

    public static final me.i f(String[] strArr, String[] strArr2) {
        j.e(strArr2, "strings");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a.a(strArr));
        g g = g(byteArrayInputStream, strArr2);
        C1148a aVar = C1157j.f4841N;
        aVar.getClass();
        C1256f fVar = new C1256f(byteArrayInputStream);
        C1252b bVar = (C1252b) aVar.a(fVar, f5029a);
        try {
            fVar.a(0);
            if (bVar.isInitialized()) {
                return new me.i(g, (C1157j) bVar);
            }
            u uVar = new u(new C0730a().getMessage());
            uVar.d = bVar;
            throw uVar;
        } catch (u e) {
            e.d = bVar;
            throw e;
        }
    }

    public static g g(ByteArrayInputStream byteArrayInputStream, String[] strArr) {
        of.j jVar = (of.j) of.j.k.b(byteArrayInputStream, f5029a);
        j.d(jVar, "parseDelimitedFrom(...)");
        return new g(jVar, strArr);
    }

    public static final me.i h(String[] strArr, String[] strArr2) {
        j.e(strArr, "data");
        j.e(strArr2, "strings");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a.a(strArr));
        g g = g(byteArrayInputStream, strArr2);
        C1148a aVar = C.f4732o;
        aVar.getClass();
        C1256f fVar = new C1256f(byteArrayInputStream);
        C1252b bVar = (C1252b) aVar.a(fVar, f5029a);
        try {
            fVar.a(0);
            if (bVar.isInitialized()) {
                return new me.i(g, (C) bVar);
            }
            u uVar = new u(new C0730a().getMessage());
            uVar.d = bVar;
            throw uVar;
        } catch (u e) {
            e.d = bVar;
            throw e;
        }
    }
}
