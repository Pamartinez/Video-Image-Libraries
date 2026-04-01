package Ke;

import He.t;
import L1.d;
import Le.g;
import Qe.O;
import a.C0068a;
import i.C0212a;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import kotlin.jvm.internal.b;
import kotlin.jvm.internal.j;
import me.h;
import nf.C1209f;
import o1.C0246a;
import of.c;
import of.e;
import qf.C1235b;
import sf.C1283j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class n0 extends C0800s implements t {

    /* renamed from: p  reason: collision with root package name */
    public static final Object f3505p = new Object();

    /* renamed from: j  reason: collision with root package name */
    public final F f3506j;
    public final String k;
    public final String l;
    public final Object m;
    public final Object n;

    /* renamed from: o  reason: collision with root package name */
    public final x0 f3507o;

    public n0(F f, String str, String str2, O o2, Object obj) {
        this.f3506j = f;
        this.k = str;
        this.l = str2;
        this.m = obj;
        this.n = d.p(h.PUBLICATION, new h0(this, 0));
        this.f3507o = C0246a.d0(o2, new h0(this, 1));
    }

    public final boolean equals(Object obj) {
        n0 c5 = E0.c(obj);
        if (c5 != null && j.a(this.f3506j, c5.f3506j) && j.a(this.k, c5.k) && j.a(this.l, c5.l) && j.a(this.m, c5.m)) {
            return true;
        }
        return false;
    }

    public final g g() {
        return t().g();
    }

    public final String getName() {
        return this.k;
    }

    public final F h() {
        return this.f3506j;
    }

    public final int hashCode() {
        return this.l.hashCode() + C0212a.d(this.f3506j.hashCode() * 31, 31, this.k);
    }

    public final g i() {
        t().getClass();
        return null;
    }

    public final boolean isSuspend() {
        return false;
    }

    public final boolean q() {
        if (this.m != b.NO_RECEIVER) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r6v1, types: [me.f, java.lang.Object] */
    public final Member r() {
        if (!j().u()) {
            return null;
        }
        C1235b bVar = C0.f3487a;
        C0068a b = C0.b(j());
        if (b instanceof C0796n) {
            C0796n nVar = (C0796n) b;
            C1209f fVar = nVar.g;
            e eVar = nVar.f;
            if ((eVar.e & 16) == 16) {
                c cVar = eVar.f4996j;
                int i2 = cVar.e;
                if ((i2 & 1) != 1 || (i2 & 2) != 2) {
                    return null;
                }
                return this.f3506j.h(fVar.getString(cVar.f), fVar.getString(cVar.g));
            }
        }
        return (Field) this.n.getValue();
    }

    /* renamed from: s */
    public final O j() {
        Object invoke = this.f3507o.invoke();
        j.d(invoke, "invoke(...)");
        return (O) invoke;
    }

    public abstract k0 t();

    public final String toString() {
        C1283j jVar = B0.f3485a;
        return B0.c(j());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public n0(F f, String str, String str2, Object obj) {
        this(f, str, str2, (O) null, obj);
        j.e(str, "name");
        j.e(str2, "signature");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public n0(Ke.F r8, Qe.O r9) {
        /*
            r7 = this;
            java.lang.String r0 = "descriptor"
            kotlin.jvm.internal.j.e(r9, r0)
            qf.g r0 = r9.getName()
            java.lang.String r3 = r0.b()
            java.lang.String r0 = "asString(...)"
            kotlin.jvm.internal.j.d(r3, r0)
            a.a r0 = Ke.C0.b(r9)
            java.lang.String r4 = r0.h()
            java.lang.Object r6 = kotlin.jvm.internal.b.NO_RECEIVER
            r1 = r7
            r2 = r8
            r5 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.n0.<init>(Ke.F, Qe.O):void");
    }
}
