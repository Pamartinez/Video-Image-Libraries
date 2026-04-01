package P2;

import D0.e;
import P0.b;
import S2.c;

/* renamed from: P2.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0057g extends A {

    /* renamed from: h  reason: collision with root package name */
    public final c f599h;

    /* renamed from: i  reason: collision with root package name */
    public byte[] f600i = null;

    public C0057g(c cVar) {
        super(1, -1);
        this.f599h = cVar;
    }

    public final void a(C0056f fVar) {
        e.s(fVar, this.f599h);
    }

    public final q b() {
        return q.TYPE_ENCODED_ARRAY_ITEM;
    }

    public final int e(A a7) {
        return this.f599h.compareTo(((C0057g) a7).f599h);
    }

    public final int hashCode() {
        return this.f599h.hashCode();
    }

    public final void i(z zVar, int i2) {
        b bVar = new b();
        new e(zVar.b, bVar).f0(this.f599h, false);
        int i7 = bVar.b;
        byte[] bArr = new byte[i7];
        System.arraycopy((byte[]) bVar.e, 0, bArr, 0, i7);
        this.f600i = bArr;
        j(i7);
    }

    public final void k(C0056f fVar, b bVar) {
        if (bVar.d()) {
            bVar.b(0, g() + " encoded array");
            new e(fVar, bVar).f0(this.f599h, true);
            return;
        }
        bVar.j(this.f600i);
    }
}
