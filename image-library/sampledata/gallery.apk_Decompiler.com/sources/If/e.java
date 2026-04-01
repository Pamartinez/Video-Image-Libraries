package If;

import Ef.b;
import Hf.B;
import Hf.C0754c;
import Hf.C0768q;
import Hf.C0774x;
import Hf.c0;
import Kf.d;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public static final e f3460a = new Object();

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: Hf.c0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: Hf.w} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: Hf.c0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: Hf.w} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: Hf.w} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: Hf.c0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: Hf.w} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: Hf.c0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: Hf.c0} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Hf.B b(Hf.B r12) {
        /*
            Hf.M r0 = r12.s0()
            boolean r1 = r0 instanceof uf.C1318c
            r2 = 10
            r3 = 0
            if (r1 == 0) goto L_0x007a
            uf.c r0 = (uf.C1318c) r0
            Hf.P r1 = r0.f5147a
            Hf.d0 r4 = r1.a()
            Hf.d0 r5 = Hf.d0.IN_VARIANCE
            if (r4 != r5) goto L_0x0019
            r4 = r1
            goto L_0x001a
        L_0x0019:
            r4 = r3
        L_0x001a:
            if (r4 == 0) goto L_0x0028
            Hf.x r4 = r4.b()
            if (r4 == 0) goto L_0x0028
            Hf.c0 r4 = r4.x0()
            r8 = r4
            goto L_0x0029
        L_0x0028:
            r8 = r3
        L_0x0029:
            If.i r4 = r0.b
            if (r4 != 0) goto L_0x0063
            java.util.Collection r4 = r0.h()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r5 = new java.util.ArrayList
            int r2 = ne.C1196n.w0(r4, r2)
            r5.<init>(r2)
            java.util.Iterator r2 = r4.iterator()
        L_0x0040:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0054
            java.lang.Object r4 = r2.next()
            Hf.x r4 = (Hf.C0774x) r4
            Hf.c0 r4 = r4.x0()
            r5.add(r4)
            goto L_0x0040
        L_0x0054:
            If.i r2 = new If.i
            Ff.e r4 = new Ff.e
            r6 = 1
            r4.<init>(r5, r6)
            r5 = 8
            r2.<init>((Hf.P) r1, (Ff.e) r4, (Qe.V) r3, (int) r5)
            r0.b = r2
        L_0x0063:
            If.h r5 = new If.h
            Kf.b r6 = Kf.b.FOR_SUBTYPING
            If.i r7 = r0.b
            kotlin.jvm.internal.j.b(r7)
            Hf.I r9 = r12.p0()
            boolean r10 = r12.u0()
            r11 = 32
            r5.<init>((Kf.b) r6, (If.i) r7, (Hf.c0) r8, (Hf.I) r9, (boolean) r10, (int) r11)
            return r5
        L_0x007a:
            boolean r1 = r0 instanceof Hf.C0773w
            if (r1 == 0) goto L_0x00d1
            boolean r1 = r12.u0()
            if (r1 == 0) goto L_0x00d1
            Hf.w r0 = (Hf.C0773w) r0
            java.util.LinkedHashSet r12 = r0.b
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = ne.C1196n.w0(r12, r2)
            r1.<init>(r2)
            java.util.Iterator r12 = r12.iterator()
            r2 = 0
        L_0x0096:
            boolean r4 = r12.hasNext()
            if (r4 == 0) goto L_0x00ab
            java.lang.Object r2 = r12.next()
            Hf.x r2 = (Hf.C0774x) r2
            Hf.c0 r2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.J(r2)
            r1.add(r2)
            r2 = 1
            goto L_0x0096
        L_0x00ab:
            if (r2 != 0) goto L_0x00ae
            goto L_0x00c9
        L_0x00ae:
            Hf.x r12 = r0.f3452a
            if (r12 == 0) goto L_0x00b6
            Hf.c0 r3 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.J(r12)
        L_0x00b6:
            r1.isEmpty()
            java.util.LinkedHashSet r12 = new java.util.LinkedHashSet
            r12.<init>(r1)
            r12.hashCode()
            Hf.w r1 = new Hf.w
            r1.<init>(r12)
            r1.f3452a = r3
            r3 = r1
        L_0x00c9:
            if (r3 != 0) goto L_0x00cc
            goto L_0x00cd
        L_0x00cc:
            r0 = r3
        L_0x00cd:
            Hf.B r12 = r0.b()
        L_0x00d1:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: If.e.b(Hf.B):Hf.B");
    }

    public final c0 a(d dVar) {
        c0 c0Var;
        C0774x xVar;
        j.e(dVar, "type");
        if (dVar instanceof C0774x) {
            c0 x02 = ((C0774x) dVar).x0();
            if (x02 instanceof B) {
                c0Var = b((B) x02);
            } else if (x02 instanceof C0768q) {
                C0768q qVar = (C0768q) x02;
                B b = qVar.f;
                B b5 = qVar.e;
                B b8 = b(b5);
                B b10 = b(b);
                if (b8 == b5 && b10 == b) {
                    c0Var = x02;
                } else {
                    c0Var = C0754c.f(b8, b10);
                }
            } else {
                throw new RuntimeException();
            }
            b bVar = new b(1, this, 4);
            C0774x g = C0754c.g(x02);
            if (g != null) {
                xVar = (C0774x) bVar.invoke(g);
            } else {
                xVar = null;
            }
            return C0754c.G(c0Var, xVar);
        }
        throw new IllegalArgumentException("Failed requirement.");
    }
}
