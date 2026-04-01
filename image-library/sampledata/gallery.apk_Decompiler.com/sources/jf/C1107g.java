package jf;

import B1.a;
import Df.l;
import Df.r;
import Ve.b;
import We.C0892d;
import ee.P;
import java.util.Set;
import kf.C1116b;
import kotlin.jvm.internal.j;
import ne.C1192j;
import pf.f;

/* renamed from: jf.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1107g {
    public static final Set b = a.S(C1116b.CLASS);

    /* renamed from: c  reason: collision with root package name */
    public static final Set f4642c = C1192j.z0(new C1116b[]{C1116b.FILE_FACADE, C1116b.MULTIFILE_CLASS_PART});
    public static final f d = new f(false, new int[]{1, 1, 11});
    public static final f e = new f(false, new int[]{1, 1, 13});

    /* renamed from: a  reason: collision with root package name */
    public l f4643a;

    static {
        new f(false, new int[]{1, 1, 2});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0024, code lost:
        if (f4642c.contains((kf.C1116b) r0.f4277c) != false) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Ff.t a(Qe.H r17, Ve.b r18) {
        /*
            r16 = this;
            r1 = r16
            r2 = r18
            java.lang.String r3 = "Could not read data from "
            java.lang.String r0 = "kotlinClass"
            kotlin.jvm.internal.j.e(r2, r0)
            ee.P r0 = r2.b
            java.lang.Object r4 = r0.e
            java.lang.String[] r4 = (java.lang.String[]) r4
            if (r4 != 0) goto L_0x0017
            java.lang.Object r4 = r0.f
            java.lang.String[] r4 = (java.lang.String[]) r4
        L_0x0017:
            r5 = 0
            if (r4 == 0) goto L_0x0027
            java.lang.Object r6 = r0.f4277c
            kf.b r6 = (kf.C1116b) r6
            java.util.Set r7 = f4642c
            boolean r6 = r7.contains(r6)
            if (r6 == 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r4 = r5
        L_0x0028:
            if (r4 != 0) goto L_0x002b
            goto L_0x0074
        L_0x002b:
            java.lang.Object r6 = r0.d
            r11 = r6
            pf.f r11 = (pf.f) r11
            java.lang.Object r0 = r0.g
            java.lang.String[] r0 = (java.lang.String[]) r0
            if (r0 != 0) goto L_0x0037
            goto L_0x0074
        L_0x0037:
            me.i r0 = pf.i.h(r4, r0)     // Catch:{ u -> 0x003e }
            goto L_0x0072
        L_0x003c:
            r0 = move-exception
            goto L_0x0055
        L_0x003e:
            r0 = move-exception
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x003c }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x003c }
            r6.<init>(r3)     // Catch:{ all -> 0x003c }
            java.lang.String r3 = r2.a()     // Catch:{ all -> 0x003c }
            r6.append(r3)     // Catch:{ all -> 0x003c }
            java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x003c }
            r4.<init>(r3, r0)     // Catch:{ all -> 0x003c }
            throw r4     // Catch:{ all -> 0x003c }
        L_0x0055:
            Df.l r3 = r1.c()
            Df.m r3 = r3.f3350c
            r3.getClass()
            Df.l r3 = r1.c()
            Df.m r3 = r3.f3350c
            java.lang.String r4 = "<this>"
            kotlin.jvm.internal.j.e(r3, r4)
            pf.f r3 = pf.f.g
            boolean r3 = r11.b(r3)
            if (r3 != 0) goto L_0x00b2
            r0 = r5
        L_0x0072:
            if (r0 != 0) goto L_0x0075
        L_0x0074:
            return r5
        L_0x0075:
            java.lang.Object r3 = r0.d
            r10 = r3
            pf.g r10 = (pf.g) r10
            java.lang.Object r0 = r0.e
            r9 = r0
            lf.C r9 = (lf.C) r9
            jf.i r12 = new jf.i
            r1.d(r2)
            r1.e(r2)
            Ff.l r0 = r1.b(r2)
            r12.<init>(r2, r9, r10, r0)
            Ff.t r7 = new Ff.t
            Df.l r13 = r1.c()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "scope for "
            r0.<init>(r1)
            r0.append(r12)
            java.lang.String r1 = " in "
            r0.append(r1)
            r8 = r17
            r0.append(r8)
            java.lang.String r14 = r0.toString()
            jf.f r15 = jf.C1106f.d
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15)
            return r7
        L_0x00b2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: jf.C1107g.a(Qe.H, Ve.b):Ff.t");
    }

    public final Ff.l b(b bVar) {
        c().f3350c.getClass();
        int i2 = bVar.b.b;
        if ((i2 & 16) == 0 || (i2 & 32) != 0) {
            return Ff.l.STABLE;
        }
        return Ff.l.UNSTABLE;
    }

    public final l c() {
        l lVar = this.f4643a;
        if (lVar != null) {
            return lVar;
        }
        j.k("components");
        throw null;
    }

    public final r d(b bVar) {
        f fVar;
        f fVar2;
        c().f3350c.getClass();
        j.e(c().f3350c, "<this>");
        f fVar3 = f.g;
        if (((f) bVar.b.d).b(fVar3)) {
            return null;
        }
        f fVar4 = (f) bVar.b.d;
        j.e(c().f3350c, "<this>");
        j.e(c().f3350c, "<this>");
        boolean z = fVar4.f;
        fVar3.getClass();
        if (z) {
            fVar = fVar3;
        } else {
            fVar = f.f5027h;
        }
        int i2 = fVar.b;
        int i7 = fVar3.b;
        if (i2 <= i7 && (i2 < i7 || fVar.f4952c <= fVar3.f4952c)) {
            fVar2 = fVar3;
        } else {
            fVar2 = fVar;
        }
        return new r(fVar4, fVar3, fVar3, fVar2, bVar.a(), C0892d.a(bVar.f3829a));
    }

    public final boolean e(b bVar) {
        c().f3350c.getClass();
        c().f3350c.getClass();
        P p6 = bVar.b;
        if ((p6.b & 2) == 0 || !((f) p6.d).equals(d)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001b, code lost:
        if (b.contains((kf.C1116b) r1.f4277c) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Df.C0741g f(Ve.b r7) {
        /*
            r6 = this;
            java.lang.String r0 = "Could not read data from "
            ee.P r1 = r7.b
            java.lang.Object r2 = r1.e
            java.lang.String[] r2 = (java.lang.String[]) r2
            if (r2 != 0) goto L_0x000e
            java.lang.Object r2 = r1.f
            java.lang.String[] r2 = (java.lang.String[]) r2
        L_0x000e:
            r3 = 0
            if (r2 == 0) goto L_0x001e
            java.lang.Object r4 = r1.f4277c
            kf.b r4 = (kf.C1116b) r4
            java.util.Set r5 = b
            boolean r4 = r5.contains(r4)
            if (r4 == 0) goto L_0x001e
            goto L_0x001f
        L_0x001e:
            r2 = r3
        L_0x001f:
            if (r2 != 0) goto L_0x0022
            goto L_0x006a
        L_0x0022:
            java.lang.Object r4 = r1.d
            pf.f r4 = (pf.f) r4
            java.lang.Object r1 = r1.g
            java.lang.String[] r1 = (java.lang.String[]) r1
            if (r1 != 0) goto L_0x002d
            goto L_0x006a
        L_0x002d:
            me.i r0 = pf.i.f(r2, r1)     // Catch:{ u -> 0x0034 }
            goto L_0x0068
        L_0x0032:
            r0 = move-exception
            goto L_0x004b
        L_0x0034:
            r1 = move-exception
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0032 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0032 }
            r5.<init>(r0)     // Catch:{ all -> 0x0032 }
            java.lang.String r0 = r7.a()     // Catch:{ all -> 0x0032 }
            r5.append(r0)     // Catch:{ all -> 0x0032 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0032 }
            r2.<init>(r0, r1)     // Catch:{ all -> 0x0032 }
            throw r2     // Catch:{ all -> 0x0032 }
        L_0x004b:
            Df.l r1 = r6.c()
            Df.m r1 = r1.f3350c
            r1.getClass()
            Df.l r1 = r6.c()
            Df.m r1 = r1.f3350c
            java.lang.String r2 = "<this>"
            kotlin.jvm.internal.j.e(r1, r2)
            pf.f r1 = pf.f.g
            boolean r1 = r4.b(r1)
            if (r1 != 0) goto L_0x0088
            r0 = r3
        L_0x0068:
            if (r0 != 0) goto L_0x006b
        L_0x006a:
            return r3
        L_0x006b:
            java.lang.Object r1 = r0.d
            pf.g r1 = (pf.g) r1
            java.lang.Object r0 = r0.e
            lf.j r0 = (lf.C1157j) r0
            jf.r r2 = new jf.r
            r6.d(r7)
            r6.e(r7)
            Ff.l r6 = r6.b(r7)
            r2.<init>(r7, r6)
            Df.g r6 = new Df.g
            r6.<init>(r1, r0, r4, r2)
            return r6
        L_0x0088:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: jf.C1107g.f(Ve.b):Df.g");
    }
}
