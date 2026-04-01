package Vf;

import cg.a;
import cg.r;
import me.i;
import qe.C1227c;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0 extends r {

    /* renamed from: h  reason: collision with root package name */
    public final ThreadLocal f3835h;
    private volatile boolean threadLocalIsSet;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0(qe.C1227c r3, qe.C1232h r4) {
        /*
            r2 = this;
            Vf.D0 r0 = Vf.D0.d
            qe.f r1 = r4.get(r0)
            if (r1 != 0) goto L_0x000d
            qe.h r0 = r4.plus(r0)
            goto L_0x000e
        L_0x000d:
            r0 = r4
        L_0x000e:
            r2.<init>(r3, r0)
            java.lang.ThreadLocal r0 = new java.lang.ThreadLocal
            r0.<init>()
            r2.f3835h = r0
            qe.h r3 = r3.getContext()
            qe.d r0 = qe.C1228d.d
            qe.f r3 = r3.get(r0)
            boolean r3 = r3 instanceof Vf.C0886x
            if (r3 != 0) goto L_0x0031
            r3 = 0
            java.lang.Object r3 = cg.a.l(r4, r3)
            cg.a.g(r4, r3)
            r2.Z(r4, r3)
        L_0x0031:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.C0.<init>(qe.c, qe.h):void");
    }

    public final boolean Y() {
        boolean z;
        if (!this.threadLocalIsSet || this.f3835h.get() != null) {
            z = false;
        } else {
            z = true;
        }
        this.f3835h.remove();
        return !z;
    }

    public final void Z(C1232h hVar, Object obj) {
        this.threadLocalIsSet = true;
        this.f3835h.set(new i(hVar, obj));
    }

    public final void l(Object obj) {
        if (this.threadLocalIsSet) {
            i iVar = (i) this.f3835h.get();
            if (iVar != null) {
                a.g((C1232h) iVar.d, iVar.e);
            }
            this.f3835h.remove();
        }
        Object p6 = D.p(obj);
        C1227c cVar = this.g;
        C1232h context = cVar.getContext();
        C0 c02 = null;
        Object l = a.l(context, (Object) null);
        if (l != a.d) {
            c02 = D.v(cVar, context, l);
        }
        try {
            this.g.resumeWith(p6);
            if (c02 == null || c02.Y()) {
                a.g(context, l);
            }
        } catch (Throwable th) {
            if (c02 == null || c02.Y()) {
                a.g(context, l);
            }
            throw th;
        }
    }
}
