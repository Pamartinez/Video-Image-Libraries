package Ke;

import He.o;
import He.r;
import He.s;
import L1.d;
import Qe.O;
import kotlin.jvm.internal.j;
import me.h;

/* renamed from: Ke.d0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0784d0 extends n0 implements s {
    public final Object q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0784d0(F f, String str, String str2, Object obj) {
        super(f, str, str2, obj);
        j.e(str, "name");
        j.e(str2, "signature");
        h hVar = h.PUBLICATION;
        this.q = d.p(hVar, new C0780b0(this, 0));
        d.p(hVar, new C0780b0(this, 1));
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final Object get(Object obj) {
        return ((C0782c0) this.q.getValue()).call(obj);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final o getGetter() {
        return (C0782c0) this.q.getValue();
    }

    public final Object invoke(Object obj) {
        return get(obj);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final k0 t() {
        return (C0782c0) this.q.getValue();
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    /* renamed from: getGetter  reason: collision with other method in class */
    public final r m49getGetter() {
        return (C0782c0) this.q.getValue();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0784d0(F f, O o2) {
        super(f, o2);
        j.e(o2, "descriptor");
        h hVar = h.PUBLICATION;
        this.q = d.p(hVar, new C0780b0(this, 0));
        d.p(hVar, new C0780b0(this, 1));
    }
}
