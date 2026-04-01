package oe;

import Be.a;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import ne.C1188f;

/* renamed from: oe.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1212a implements ListIterator, a {
    public final /* synthetic */ int d = 0;
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public final C1188f f4976h;

    public C1212a(C1214c cVar, int i2) {
        this.f4976h = cVar;
        this.e = i2;
        this.f = -1;
        this.g = cVar.modCount;
    }

    public void a() {
        if (((C1213b) this.f4976h).f4977h.modCount != this.g) {
            throw new ConcurrentModificationException();
        }
    }

    public final void add(Object obj) {
        switch (this.d) {
            case 0:
                a();
                C1213b bVar = (C1213b) this.f4976h;
                int i2 = this.e;
                this.e = i2 + 1;
                bVar.add(i2, obj);
                this.f = -1;
                this.g = bVar.modCount;
                return;
            default:
                b();
                C1214c cVar = (C1214c) this.f4976h;
                int i7 = this.e;
                this.e = i7 + 1;
                cVar.add(i7, obj);
                this.f = -1;
                this.g = cVar.modCount;
                return;
        }
    }

    public void b() {
        if (((C1214c) this.f4976h).modCount != this.g) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean hasNext() {
        switch (this.d) {
            case 0:
                if (this.e < ((C1213b) this.f4976h).f) {
                    return true;
                }
                return false;
            default:
                if (this.e < ((C1214c) this.f4976h).e) {
                    return true;
                }
                return false;
        }
    }

    public final boolean hasPrevious() {
        switch (this.d) {
            case 0:
                if (this.e > 0) {
                    return true;
                }
                return false;
            default:
                if (this.e > 0) {
                    return true;
                }
                return false;
        }
    }

    public final Object next() {
        switch (this.d) {
            case 0:
                a();
                int i2 = this.e;
                C1213b bVar = (C1213b) this.f4976h;
                if (i2 < bVar.f) {
                    this.e = i2 + 1;
                    this.f = i2;
                    return bVar.d[bVar.e + i2];
                }
                throw new NoSuchElementException();
            default:
                b();
                int i7 = this.e;
                C1214c cVar = (C1214c) this.f4976h;
                if (i7 < cVar.e) {
                    this.e = i7 + 1;
                    this.f = i7;
                    return cVar.d[i7];
                }
                throw new NoSuchElementException();
        }
    }

    public final int nextIndex() {
        switch (this.d) {
            case 0:
                return this.e;
            default:
                return this.e;
        }
    }

    public final Object previous() {
        switch (this.d) {
            case 0:
                a();
                int i2 = this.e;
                if (i2 > 0) {
                    int i7 = i2 - 1;
                    this.e = i7;
                    this.f = i7;
                    C1213b bVar = (C1213b) this.f4976h;
                    return bVar.d[bVar.e + i7];
                }
                throw new NoSuchElementException();
            default:
                b();
                int i8 = this.e;
                if (i8 > 0) {
                    int i10 = i8 - 1;
                    this.e = i10;
                    this.f = i10;
                    return ((C1214c) this.f4976h).d[i10];
                }
                throw new NoSuchElementException();
        }
    }

    public final int previousIndex() {
        int i2;
        switch (this.d) {
            case 0:
                i2 = this.e;
                break;
            default:
                i2 = this.e;
                break;
        }
        return i2 - 1;
    }

    public final void remove() {
        switch (this.d) {
            case 0:
                C1213b bVar = (C1213b) this.f4976h;
                a();
                int i2 = this.f;
                if (i2 != -1) {
                    bVar.q(i2);
                    this.e = this.f;
                    this.f = -1;
                    this.g = bVar.modCount;
                    return;
                }
                throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
            default:
                C1214c cVar = (C1214c) this.f4976h;
                b();
                int i7 = this.f;
                if (i7 != -1) {
                    cVar.q(i7);
                    this.e = this.f;
                    this.f = -1;
                    this.g = cVar.modCount;
                    return;
                }
                throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
        }
    }

    public final void set(Object obj) {
        switch (this.d) {
            case 0:
                a();
                int i2 = this.f;
                if (i2 != -1) {
                    ((C1213b) this.f4976h).set(i2, obj);
                    return;
                }
                throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
            default:
                b();
                int i7 = this.f;
                if (i7 != -1) {
                    ((C1214c) this.f4976h).set(i7, obj);
                    return;
                }
                throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
        }
    }

    public C1212a(C1213b bVar, int i2) {
        this.f4976h = bVar;
        this.e = i2;
        this.f = -1;
        this.g = bVar.modCount;
    }
}
