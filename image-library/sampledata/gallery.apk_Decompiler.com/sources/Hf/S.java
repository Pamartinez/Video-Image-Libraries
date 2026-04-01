package Hf;

import Re.h;
import com.samsung.android.sum.core.message.Message;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class S extends T {
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final T f3435c;

    public /* synthetic */ S(T t, int i2) {
        this.b = i2;
        this.f3435c = t;
    }

    public boolean a() {
        switch (this.b) {
            case 1:
                return this.f3435c.a();
            default:
                return super.a();
        }
    }

    public boolean b() {
        switch (this.b) {
            case 1:
                return true;
            default:
                return super.b();
        }
    }

    public final h c(h hVar) {
        switch (this.b) {
            case 0:
                j.e(hVar, "annotations");
                return this.f3435c.c(hVar);
            default:
                j.e(hVar, "annotations");
                return this.f3435c.c(hVar);
        }
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [Qe.i] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Hf.P d(Hf.C0774x r3) {
        /*
            r2 = this;
            int r0 = r2.b
            switch(r0) {
                case 0: goto L_0x0022;
                default: goto L_0x0005;
            }
        L_0x0005:
            Hf.T r2 = r2.f3435c
            Hf.P r2 = r2.d(r3)
            r0 = 0
            if (r2 == 0) goto L_0x0021
            Hf.M r3 = r3.s0()
            Qe.i r3 = r3.g()
            boolean r1 = r3 instanceof Qe.V
            if (r1 == 0) goto L_0x001d
            r0 = r3
            Qe.V r0 = (Qe.V) r0
        L_0x001d:
            Hf.P r0 = L2.a.k(r2, r0)
        L_0x0021:
            return r0
        L_0x0022:
            Hf.T r2 = r2.f3435c
            Hf.P r2 = r2.d(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Hf.S.d(Hf.x):Hf.P");
    }

    public final boolean e() {
        switch (this.b) {
            case 0:
                return this.f3435c.e();
            default:
                return this.f3435c.e();
        }
    }

    public final C0774x f(C0774x xVar, d0 d0Var) {
        switch (this.b) {
            case 0:
                j.e(xVar, "topLevelType");
                j.e(d0Var, Message.KEY_POSITION);
                return this.f3435c.f(xVar, d0Var);
            default:
                j.e(xVar, "topLevelType");
                j.e(d0Var, Message.KEY_POSITION);
                return this.f3435c.f(xVar, d0Var);
        }
    }
}
