package Hf;

import Qe.C0819i;
import Qe.V;
import kotlin.jvm.internal.j;

/* renamed from: Hf.t  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0770t extends T {
    public final V[] b;

    /* renamed from: c  reason: collision with root package name */
    public final P[] f3451c;
    public final boolean d;

    public C0770t(V[] vArr, P[] pArr, boolean z) {
        j.e(vArr, "parameters");
        j.e(pArr, "arguments");
        this.b = vArr;
        this.f3451c = pArr;
        this.d = z;
    }

    public final boolean b() {
        return this.d;
    }

    public final P d(C0774x xVar) {
        V v;
        C0819i g = xVar.s0().g();
        if (g instanceof V) {
            v = (V) g;
        } else {
            v = null;
        }
        if (v != null) {
            int index = v.getIndex();
            V[] vArr = this.b;
            if (index < vArr.length && j.a(vArr[index].q(), v.q())) {
                return this.f3451c[index];
            }
        }
        return null;
    }

    public final boolean e() {
        if (this.f3451c.length == 0) {
            return true;
        }
        return false;
    }
}
