package o1;

import d1.C0179a;
import kotlin.jvm.internal.j;
import ne.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends C0179a {
    public final v f;
    public final int g = 512;

    /* renamed from: h  reason: collision with root package name */
    public final j f1851h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g() {
        super(i.f1852a);
        j jVar = j.DISABLED;
        j.e(jVar, "singletonSupport");
        this.f1851h = jVar;
        this.f = v.d;
    }

    /* JADX WARNING: type inference failed for: r0v14, types: [java.lang.Object, Q0.j] */
    /* JADX WARNING: type inference failed for: r3v3, types: [Q0.j] */
    /* JADX WARNING: type inference failed for: r0v16, types: [Q0.j] */
    /* JADX WARNING: type inference failed for: r0v25, types: [e1.a] */
    /* JADX WARNING: type inference failed for: r0v26, types: [X0.a] */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r2v16, types: [X0.a] */
    /* JADX WARNING: type inference failed for: r0v30 */
    /* JADX WARNING: type inference failed for: r0v35 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c(B1.b r11) {
        /*
            r10 = this;
            V0.g r0 = V0.g.USE_ANNOTATIONS
            java.lang.Object r1 = r11.e
            V0.j r1 = (V0.j) r1
            V0.l r2 = r1.f867h
            int r2 = r2.d
            boolean r0 = r0.c(r2)
            if (r0 == 0) goto L_0x0139
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            int r2 = r10.g
            r3 = 1061997773(0x3f4ccccd, float:0.8)
            r4 = 4
            r0.<init>(r2, r3, r4)
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r0.<init>(r2, r3, r4)
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r0.<init>(r2, r3, r4)
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r0.<init>(r2, r3, r4)
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r0.<init>(r2, r3, r4)
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r0.<init>(r2, r3, r4)
            o1.d r0 = new o1.d
            r0.<init>()
            X0.d r2 = r1.k
            X0.b r2 = r2.f893j
            W0.h r3 = r2.f892j
            o1.d[] r4 = r3.f889h
            java.lang.Object[] r0 = Gd.a.J(r4, r0)
            r9 = r0
            o1.d[] r9 = (o1.C0249d[]) r9
            W0.h r4 = new W0.h
            o1.c[] r5 = r3.d
            W0.f[] r6 = r3.e
            X0.c[] r7 = r3.f
            og.k[] r8 = r3.g
            r4.<init>(r5, r6, r7, r8, r9)
            if (r3 != r4) goto L_0x0058
            goto L_0x005f
        L_0x0058:
            java.util.Iterator r0 = i1.a.f1782a
            X0.b r2 = new X0.b
            r2.<init>(r4)
        L_0x005f:
            X0.d r0 = r1.k
            X0.d r0 = r0.b0(r2)
            r1.k = r0
            int[] r0 = o1.C0250e.f1850a
            o1.j r2 = r10.f1851h
            int r2 = r2.ordinal()
            r0 = r0[r2]
            r2 = 2
            if (r0 == r2) goto L_0x0075
            goto L_0x007a
        L_0x0075:
            o1.b r0 = o1.C0247b.d
            r11.c(r0)
        L_0x007a:
            a1.e r0 = new a1.e
            r0.<init>()
            r11.h(r0)
            a1.e r0 = new a1.e
            ne.v r10 = r10.f
            java.lang.String r2 = "ignoredClassesForImplyingJsonCreator"
            kotlin.jvm.internal.j.e(r10, r2)
            r0.<init>()
            V0.a r10 = r1.f869j
            W0.a r2 = r10.e
            Q0.j r3 = r2.f
            if (r3 != 0) goto L_0x0098
            r3 = r0
            goto L_0x009d
        L_0x0098:
            a1.b r3 = new a1.b
            r3.<init>()
        L_0x009d:
            W0.a r2 = r2.a(r3)
            W0.j r10 = r10.b(r2)
            V0.a r10 = (V0.a) r10
            r1.f869j = r10
            V0.l r10 = r1.f867h
            W0.a r2 = r10.e
            Q0.j r3 = r2.f
            if (r3 != 0) goto L_0x00b2
            goto L_0x00b7
        L_0x00b2:
            a1.b r0 = new a1.b
            r0.<init>()
        L_0x00b7:
            W0.a r0 = r2.a(r0)
            W0.j r10 = r10.b(r0)
            V0.l r10 = (V0.l) r10
            r1.f867h = r10
            o1.c r10 = new o1.c
            r10.<init>()
            X0.d r0 = r1.k
            X0.b r0 = r0.f893j
            W0.h r2 = r0.f892j
            o1.c[] r3 = r2.d
            java.lang.Object[] r10 = Gd.a.J(r3, r10)
            r4 = r10
            o1.c[] r4 = (o1.C0248c[]) r4
            W0.h r3 = new W0.h
            W0.f[] r5 = r2.e
            X0.c[] r6 = r2.f
            og.k[] r7 = r2.g
            o1.d[] r8 = r2.f889h
            r3.<init>(r4, r5, r6, r7, r8)
            if (r2 != r3) goto L_0x00e7
            goto L_0x00ee
        L_0x00e7:
            java.util.Iterator r10 = i1.a.f1782a
            X0.b r0 = new X0.b
            r0.<init>(r3)
        L_0x00ee:
            X0.d r10 = r1.k
            X0.d r10 = r10.b0(r0)
            r1.k = r10
            o1.h r10 = new o1.h
            r10.<init>()
            e1.b r0 = r1.f868i
            W0.l r2 = r0.d
            o1.h[] r3 = r2.d
            java.lang.Object[] r10 = Gd.a.J(r3, r10)
            o1.h[] r10 = (o1.h[]) r10
            W0.l r3 = new W0.l
            o1.h[] r4 = r2.e
            e1.c[] r2 = r2.f
            r3.<init>(r10, r4, r2)
            W0.l r10 = r0.d
            if (r10 != r3) goto L_0x0115
            goto L_0x011a
        L_0x0115:
            e1.b r0 = new e1.b
            r0.<init>(r3)
        L_0x011a:
            r1.f868i = r0
            o1.f r10 = new o1.f
            r0 = 0
            r10.<init>(r0, r11)
            java.lang.Class<Ge.e> r11 = Ge.e.class
            java.lang.Class<o1.a> r0 = o1.C0246a.class
            r10.a(r11, r0)
            java.lang.Class<Ge.a> r11 = Ge.a.class
            r10.a(r11, r0)
            java.lang.Class<Ge.f> r11 = Ge.f.class
            r10.a(r11, r0)
            java.lang.Class<Ge.b> r11 = Ge.b.class
            r10.a(r11, r0)
            return
        L_0x0139:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "The Jackson Kotlin module requires USE_ANNOTATIONS to be true or it cannot function"
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: o1.g.c(B1.b):void");
    }
}
