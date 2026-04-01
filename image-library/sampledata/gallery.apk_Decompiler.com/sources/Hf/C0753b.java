package Hf;

import Gf.p;
import Ne.i;
import Qe.C0816f;
import qf.C1240g;
import xf.C1353d;

/* renamed from: Hf.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0753b extends C0758g {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0753b(p pVar) {
        super(pVar);
        if (pVar != null) {
        } else {
            l(0);
            throw null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void l(int r9) {
        /*
            r0 = 4
            r1 = 3
            r2 = 1
            if (r9 == r2) goto L_0x000c
            if (r9 == r1) goto L_0x000c
            if (r9 == r0) goto L_0x000c
            java.lang.String r3 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            goto L_0x000e
        L_0x000c:
            java.lang.String r3 = "@NotNull method %s.%s must not return null"
        L_0x000e:
            r4 = 2
            if (r9 == r2) goto L_0x0017
            if (r9 == r1) goto L_0x0017
            if (r9 == r0) goto L_0x0017
            r5 = r1
            goto L_0x0018
        L_0x0017:
            r5 = r4
        L_0x0018:
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/types/AbstractClassTypeConstructor"
            r7 = 0
            if (r9 == r2) goto L_0x002f
            if (r9 == r4) goto L_0x002a
            if (r9 == r1) goto L_0x002f
            if (r9 == r0) goto L_0x002f
            java.lang.String r8 = "storageManager"
            r5[r7] = r8
            goto L_0x0031
        L_0x002a:
            java.lang.String r8 = "classifier"
            r5[r7] = r8
            goto L_0x0031
        L_0x002f:
            r5[r7] = r6
        L_0x0031:
            if (r9 == r2) goto L_0x003f
            if (r9 == r1) goto L_0x003a
            if (r9 == r0) goto L_0x003a
            r5[r2] = r6
            goto L_0x0043
        L_0x003a:
            java.lang.String r6 = "getAdditionalNeighboursInSupertypeGraph"
            r5[r2] = r6
            goto L_0x0043
        L_0x003f:
            java.lang.String r6 = "getBuiltIns"
            r5[r2] = r6
        L_0x0043:
            if (r9 == r2) goto L_0x0054
            if (r9 == r4) goto L_0x0050
            if (r9 == r1) goto L_0x0054
            if (r9 == r0) goto L_0x0054
            java.lang.String r6 = "<init>"
            r5[r4] = r6
            goto L_0x0054
        L_0x0050:
            java.lang.String r6 = "isSameClassifier"
            r5[r4] = r6
        L_0x0054:
            java.lang.String r3 = java.lang.String.format(r3, r5)
            if (r9 == r2) goto L_0x0064
            if (r9 == r1) goto L_0x0064
            if (r9 == r0) goto L_0x0064
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r3)
            goto L_0x0069
        L_0x0064:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r3)
        L_0x0069:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: Hf.C0753b.l(int):void");
    }

    public final C0774x c() {
        C0816f m = g();
        if (m != null) {
            C1240g gVar = i.e;
            if (i.b(m, Ne.p.f3565a) || i.b(m, Ne.p.b)) {
                return null;
            }
            return f().e();
        }
        i.a(108);
        throw null;
    }

    public final i f() {
        i e = C1353d.e(g());
        if (e != null) {
            return e;
        }
        l(1);
        throw null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004f, code lost:
        if (kotlin.jvm.internal.j.a(((Te.B) ((Qe.H) r4)).f3743i, ((Te.B) ((Qe.H) r5)).f3743i) != false) goto L_0x0051;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0072 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean j(Qe.C0819i r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof Qe.C0816f
            r1 = 0
            if (r0 == 0) goto L_0x0073
            Qe.f r4 = r4.g()
            java.lang.String r0 = "first"
            kotlin.jvm.internal.j.e(r4, r0)
            qf.g r0 = r4.getName()
            qf.g r2 = r5.getName()
            boolean r0 = kotlin.jvm.internal.j.a(r0, r2)
            r2 = 1
            if (r0 != 0) goto L_0x001f
        L_0x001d:
            r4 = r1
            goto L_0x0070
        L_0x001f:
            Qe.l r4 = r4.g()
            Qe.l r5 = r5.g()
        L_0x0027:
            if (r4 == 0) goto L_0x0051
            if (r5 == 0) goto L_0x0051
            boolean r0 = r4 instanceof Qe.C
            if (r0 == 0) goto L_0x0032
            boolean r4 = r5 instanceof Qe.C
            goto L_0x0070
        L_0x0032:
            boolean r0 = r5 instanceof Qe.C
            if (r0 == 0) goto L_0x0037
            goto L_0x001d
        L_0x0037:
            boolean r0 = r4 instanceof Qe.H
            if (r0 == 0) goto L_0x0053
            boolean r0 = r5 instanceof Qe.H
            if (r0 == 0) goto L_0x001d
            Qe.H r4 = (Qe.H) r4
            Te.B r4 = (Te.B) r4
            qf.c r4 = r4.f3743i
            Qe.H r5 = (Qe.H) r5
            Te.B r5 = (Te.B) r5
            qf.c r5 = r5.f3743i
            boolean r4 = kotlin.jvm.internal.j.a(r4, r5)
            if (r4 == 0) goto L_0x001d
        L_0x0051:
            r4 = r2
            goto L_0x0070
        L_0x0053:
            boolean r0 = r5 instanceof Qe.H
            if (r0 == 0) goto L_0x0058
            goto L_0x001d
        L_0x0058:
            qf.g r0 = r4.getName()
            qf.g r3 = r5.getName()
            boolean r0 = kotlin.jvm.internal.j.a(r0, r3)
            if (r0 != 0) goto L_0x0067
            goto L_0x001d
        L_0x0067:
            Qe.l r4 = r4.g()
            Qe.l r5 = r5.g()
            goto L_0x0027
        L_0x0070:
            if (r4 == 0) goto L_0x0073
            return r2
        L_0x0073:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: Hf.C0753b.j(Qe.i):boolean");
    }

    /* renamed from: m */
    public abstract C0816f g();
}
