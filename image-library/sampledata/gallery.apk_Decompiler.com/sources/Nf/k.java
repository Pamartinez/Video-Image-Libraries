package Nf;

import L1.d;
import bf.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k implements d {
    public static final k b = new k(0);

    /* renamed from: c  reason: collision with root package name */
    public static final k f3589c = new k(1);

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3590a;

    public /* synthetic */ k(int i2) {
        this.f3590a = i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(bf.g r5) {
        /*
            r4 = this;
            int r4 = r4.f3590a
            switch(r4) {
                case 0: goto L_0x0040;
                default: goto L_0x0005;
            }
        L_0x0005:
            java.util.List r4 = r5.B()
            java.lang.String r5 = "getValueParameters(...)"
            kotlin.jvm.internal.j.d(r4, r5)
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            boolean r5 = r4 instanceof java.util.Collection
            if (r5 == 0) goto L_0x001e
            r5 = r4
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x001e
            goto L_0x003e
        L_0x001e:
            java.util.Iterator r4 = r4.iterator()
        L_0x0022:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x003e
            java.lang.Object r5 = r4.next()
            Te.Q r5 = (Te.Q) r5
            kotlin.jvm.internal.j.b(r5)
            boolean r0 = xf.C1353d.a(r5)
            if (r0 != 0) goto L_0x003c
            Hf.x r5 = r5.n
            if (r5 != 0) goto L_0x003c
            goto L_0x0022
        L_0x003c:
            r4 = 0
            goto L_0x003f
        L_0x003e:
            r4 = 1
        L_0x003f:
            return r4
        L_0x0040:
            java.util.List r4 = r5.B()
            r5 = 1
            java.lang.Object r4 = r4.get(r5)
            Te.Q r4 = (Te.Q) r4
            Ne.n r5 = Ne.o.d
            kotlin.jvm.internal.j.b(r4)
            Qe.C r0 = xf.C1353d.j(r4)
            r5.getClass()
            qf.b r5 = Ne.p.R
            Qe.f r5 = Qe.C0833x.d(r0, r5)
            if (r5 != 0) goto L_0x0061
            r5 = 0
            goto L_0x008d
        L_0x0061:
            D0.e r0 = Hf.I.e
            r0.getClass()
            Hf.I r0 = Hf.I.f
            Hf.G r1 = new Hf.G
            Hf.M r2 = r5.q()
            java.util.List r2 = r2.getParameters()
            java.lang.String r3 = "getParameters(...)"
            kotlin.jvm.internal.j.d(r2, r3)
            java.lang.Object r2 = ne.C1194l.b1(r2)
            java.lang.String r3 = "single(...)"
            kotlin.jvm.internal.j.d(r2, r3)
            Qe.V r2 = (Qe.V) r2
            r1.<init>((Qe.V) r2)
            java.util.List r1 = o1.C0246a.e0(r1)
            Hf.B r5 = Hf.C0754c.t(r0, r5, r1)
        L_0x008d:
            r0 = 0
            if (r5 == 0) goto L_0x00a5
            Te.S r4 = (Te.S) r4
            Hf.x r4 = r4.getType()
            java.lang.String r1 = "getType(...)"
            kotlin.jvm.internal.j.d(r4, r1)
            Hf.c0 r4 = Hf.a0.g(r4, r0)
            If.l r0 = If.d.f3459a
            boolean r0 = r0.b(r5, r4)
        L_0x00a5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Nf.k.a(bf.g):boolean");
    }

    public final String b(g gVar) {
        switch (this.f3590a) {
            case 0:
                return d.n(this, gVar);
            default:
                return d.n(this, gVar);
        }
    }

    public final String getDescription() {
        switch (this.f3590a) {
            case 0:
                return "second parameter must be of type KProperty<*> or its supertype";
            default:
                return "should not have varargs or parameters with default values";
        }
    }
}
