package P2;

import P0.b;
import Q2.a;
import S2.t;
import a.C0068a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class E extends A {

    /* renamed from: h  reason: collision with root package name */
    public final t f579h;

    public E(t tVar) {
        super(1, C0068a.c0(tVar.d.length()) + tVar.e.f634a + 1);
        this.f579h = tVar;
    }

    public final q b() {
        return q.TYPE_STRING_DATA_ITEM;
    }

    public final int e(A a7) {
        return this.f579h.compareTo(((E) a7).f579h);
    }

    public final void k(C0056f fVar, b bVar) {
        t tVar = this.f579h;
        a aVar = tVar.e;
        int length = tVar.d.length();
        if (bVar.d()) {
            bVar.b(C0068a.c0(length), "utf16_size: ".concat(L2.a.E(length)));
            bVar.b(aVar.f634a + 1, tVar.f());
        }
        bVar.o(length);
        int i2 = aVar.f634a;
        int i7 = bVar.b;
        int i8 = i2 + i7;
        if (bVar.f569a) {
            bVar.g(i8);
        } else if (i8 > ((byte[]) bVar.e).length) {
            b.i();
            throw null;
        }
        byte[] bArr = (byte[]) bVar.e;
        int length2 = bArr.length - i7;
        int i10 = aVar.f634a;
        if (length2 >= i10) {
            System.arraycopy((byte[]) aVar.b, 0, bArr, i7, i10);
            bVar.b = i8;
            bVar.k(0);
            return;
        }
        throw new IndexOutOfBoundsException("(out.length - offset) < size()");
    }

    public final void a(C0056f fVar) {
    }
}
