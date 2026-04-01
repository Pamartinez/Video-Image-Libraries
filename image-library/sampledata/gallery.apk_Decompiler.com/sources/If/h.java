package If;

import Ae.a;
import Af.p;
import Df.E;
import Hf.B;
import Hf.I;
import Hf.M;
import Hf.P;
import Hf.c0;
import Jf.l;
import Kf.b;
import Kf.c;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends B implements c {
    public final b e;
    public final i f;
    public final c0 g;

    /* renamed from: h  reason: collision with root package name */
    public final I f3463h;

    /* renamed from: i  reason: collision with root package name */
    public final boolean f3464i;

    /* renamed from: j  reason: collision with root package name */
    public final boolean f3465j;

    public h(b bVar, i iVar, c0 c0Var, I i2, boolean z, boolean z3) {
        j.e(bVar, "captureStatus");
        j.e(iVar, "constructor");
        j.e(i2, "attributes");
        this.e = bVar;
        this.f = iVar;
        this.g = c0Var;
        this.f3463h = i2;
        this.f3464i = z;
        this.f3465j = z3;
    }

    public final p A() {
        return l.a(Jf.h.CAPTURED_TYPE_SCOPE, true, new String[0]);
    }

    public final B B0(boolean z) {
        return new h(this.e, this.f, this.g, this.f3463h, z, 32);
    }

    public final B C0(I i2) {
        j.e(i2, "newAttributes");
        return new h(this.e, this.f, this.g, i2, this.f3464i, this.f3465j);
    }

    /* renamed from: D0 */
    public final h z0(f fVar) {
        E e7;
        c0 c0Var;
        j.e(fVar, "kotlinTypeRefiner");
        i iVar = this.f;
        iVar.getClass();
        P d = iVar.f3466a.d(fVar);
        if (iVar.b != null) {
            e7 = new E(3, (Object) iVar, (Object) fVar);
        } else {
            e7 = null;
        }
        i iVar2 = iVar.f3467c;
        if (iVar2 == null) {
            iVar2 = iVar;
        }
        i iVar3 = new i(d, (a) e7, iVar2, iVar.d);
        c0 c0Var2 = this.g;
        if (c0Var2 != null) {
            c0Var = c0Var2;
        } else {
            c0Var = null;
        }
        return new h(this.e, iVar3, c0Var, this.f3463h, this.f3464i, 32);
    }

    public final List e0() {
        return C1202t.d;
    }

    public final I p0() {
        return this.f3463h;
    }

    public final M s0() {
        return this.f;
    }

    public final boolean u0() {
        return this.f3464i;
    }

    public final c0 y0(boolean z) {
        return new h(this.e, this.f, this.g, this.f3463h, z, 32);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public h(Kf.b r8, If.i r9, Hf.c0 r10, Hf.I r11, boolean r12, int r13) {
        /*
            r7 = this;
            r0 = r13 & 8
            if (r0 == 0) goto L_0x000b
            D0.e r11 = Hf.I.e
            r11.getClass()
            Hf.I r11 = Hf.I.f
        L_0x000b:
            r4 = r11
            r11 = r13 & 16
            if (r11 == 0) goto L_0x0011
            r12 = 0
        L_0x0011:
            r5 = r12
            r6 = 0
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r0.<init>((Kf.b) r1, (If.i) r2, (Hf.c0) r3, (Hf.I) r4, (boolean) r5, (boolean) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: If.h.<init>(Kf.b, If.i, Hf.c0, Hf.I, boolean, int):void");
    }
}
