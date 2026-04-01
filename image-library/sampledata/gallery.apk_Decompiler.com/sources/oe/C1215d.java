package oe;

import Be.a;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.j;

/* renamed from: oe.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1215d implements Iterator, a {
    public final C1217f d;
    public int e;
    public int f = -1;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f4978h;

    public C1215d(C1217f fVar, int i2) {
        this.f4978h = i2;
        j.e(fVar, "map");
        this.d = fVar;
        this.g = fVar.k;
        b();
    }

    public final void a() {
        if (this.d.k != this.g) {
            throw new ConcurrentModificationException();
        }
    }

    public final void b() {
        while (true) {
            int i2 = this.e;
            C1217f fVar = this.d;
            if (i2 < fVar.f4980i && fVar.f[i2] < 0) {
                this.e = i2 + 1;
            } else {
                return;
            }
        }
    }

    public final boolean hasNext() {
        if (this.e < this.d.f4980i) {
            return true;
        }
        return false;
    }

    public final Object next() {
        switch (this.f4978h) {
            case 0:
                a();
                int i2 = this.e;
                C1217f fVar = this.d;
                if (i2 < fVar.f4980i) {
                    this.e = i2 + 1;
                    this.f = i2;
                    C1216e eVar = new C1216e(fVar, i2);
                    b();
                    return eVar;
                }
                throw new NoSuchElementException();
            case 1:
                a();
                int i7 = this.e;
                C1217f fVar2 = this.d;
                if (i7 < fVar2.f4980i) {
                    this.e = i7 + 1;
                    this.f = i7;
                    Object obj = fVar2.d[i7];
                    b();
                    return obj;
                }
                throw new NoSuchElementException();
            default:
                a();
                int i8 = this.e;
                C1217f fVar3 = this.d;
                if (i8 < fVar3.f4980i) {
                    this.e = i8 + 1;
                    this.f = i8;
                    Object[] objArr = fVar3.e;
                    j.b(objArr);
                    Object obj2 = objArr[this.f];
                    b();
                    return obj2;
                }
                throw new NoSuchElementException();
        }
    }

    public final void remove() {
        a();
        if (this.f != -1) {
            C1217f fVar = this.d;
            fVar.c();
            fVar.o(this.f);
            this.f = -1;
            this.g = fVar.k;
            return;
        }
        throw new IllegalStateException("Call next() before removing element from the iterator.");
    }
}
