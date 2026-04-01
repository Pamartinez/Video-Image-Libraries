package Ke;

import He.o;
import He.p;
import He.q;
import L1.d;
import Qe.O;
import kotlin.jvm.internal.j;
import me.h;

/* renamed from: Ke.a0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0778a0 extends n0 implements q {
    public final Object q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0778a0(F f, O o2) {
        super(f, o2);
        j.e(o2, "descriptor");
        h hVar = h.PUBLICATION;
        this.q = d.p(hVar, new Y(this, 0));
        d.p(hVar, new Y(this, 1));
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final o getGetter() {
        return (Z) this.q.getValue();
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [me.f, java.lang.Object] */
    public final Object invoke() {
        return ((Z) this.q.getValue()).call(new Object[0]);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final k0 t() {
        return (Z) this.q.getValue();
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    /* renamed from: getGetter  reason: collision with other method in class */
    public final p m48getGetter() {
        return (Z) this.q.getValue();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0778a0(F f, String str, String str2, Object obj) {
        super(f, str, str2, obj);
        j.e(str, "name");
        j.e(str2, "signature");
        h hVar = h.PUBLICATION;
        this.q = d.p(hVar, new Y(this, 0));
        d.p(hVar, new Y(this, 1));
    }
}
