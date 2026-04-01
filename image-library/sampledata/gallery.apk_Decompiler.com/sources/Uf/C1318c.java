package uf;

import Hf.P;
import Hf.d0;
import If.i;
import Qe.C0819i;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1202t;
import o1.C0246a;

/* renamed from: uf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1318c implements C1317b {

    /* renamed from: a  reason: collision with root package name */
    public final P f5147a;
    public i b;

    public C1318c(P p6) {
        j.e(p6, "projection");
        this.f5147a = p6;
        p6.a();
        d0 d0Var = d0.INVARIANT;
    }

    public final P a() {
        return this.f5147a;
    }

    public final Ne.i f() {
        Ne.i f = this.f5147a.b().s0().f();
        j.d(f, "getBuiltIns(...)");
        return f;
    }

    public final /* bridge */ /* synthetic */ C0819i g() {
        return null;
    }

    public final List getParameters() {
        return C1202t.d;
    }

    public final Collection h() {
        Object obj;
        P p6 = this.f5147a;
        if (p6.a() == d0.OUT_VARIANCE) {
            obj = p6.b();
        } else {
            obj = f().o();
        }
        j.b(obj);
        return C0246a.e0(obj);
    }

    public final boolean i() {
        return false;
    }

    public final String toString() {
        return "CapturedTypeConstructor(" + this.f5147a + ')';
    }
}
