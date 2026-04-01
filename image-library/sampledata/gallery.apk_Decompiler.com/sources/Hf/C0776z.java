package Hf;

import Ae.a;
import Df.E;
import Gf.h;
import Gf.i;
import Gf.k;
import Gf.m;
import Gf.p;
import If.f;
import java.util.List;
import kotlin.jvm.internal.j;

/* renamed from: Hf.z  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0776z extends C0774x {
    public final p e;
    public final a f;
    public final i g;

    /* JADX WARNING: type inference failed for: r0v1, types: [Gf.h, Gf.i] */
    public C0776z(p pVar, a aVar) {
        j.e(pVar, "storageManager");
        this.e = pVar;
        this.f = aVar;
        this.g = new h((m) pVar, aVar);
    }

    public final Af.p A() {
        return y0().A();
    }

    public final List e0() {
        return y0().e0();
    }

    public final I p0() {
        return y0().p0();
    }

    public final M s0() {
        return y0().s0();
    }

    public final String toString() {
        i iVar = this.g;
        if (iVar.f == k.NOT_COMPUTED || iVar.f == k.COMPUTING) {
            return "<Not computed yet>";
        }
        return y0().toString();
    }

    public final boolean u0() {
        return y0().u0();
    }

    public final C0774x w0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        return new C0776z(this.e, new E(2, (Object) fVar, (Object) this));
    }

    public final c0 x0() {
        C0774x y0 = y0();
        while (y0 instanceof C0776z) {
            y0 = ((C0776z) y0).y0();
        }
        j.c(y0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.UnwrappedType");
        return (c0) y0;
    }

    public final C0774x y0() {
        return (C0774x) this.g.invoke();
    }
}
