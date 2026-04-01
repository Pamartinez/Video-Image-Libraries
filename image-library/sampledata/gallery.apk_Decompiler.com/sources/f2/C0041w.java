package F2;

import He.F;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: F2.w  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0041w implements Iterator {
    public int d;
    public int e;
    public int f;
    public final /* synthetic */ A g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f273h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ A f274i;

    public C0041w(A a7, int i2) {
        int i7;
        this.f273h = i2;
        this.f274i = a7;
        this.g = a7;
        this.d = a7.f228h;
        if (a7.isEmpty()) {
            i7 = -1;
        } else {
            i7 = 0;
        }
        this.e = i7;
        this.f = -1;
    }

    public final boolean hasNext() {
        if (this.e >= 0) {
            return true;
        }
        return false;
    }

    public final Object next() {
        C0043y yVar;
        A a7 = this.g;
        if (a7.f228h != this.d) {
            throw new ConcurrentModificationException();
        } else if (hasNext()) {
            int i2 = this.e;
            this.f = i2;
            switch (this.f273h) {
                case 0:
                    yVar = this.f274i.k()[i2];
                    break;
                case 1:
                    yVar = new C0043y(this.f274i, i2);
                    break;
                default:
                    yVar = this.f274i.l()[i2];
                    break;
            }
            int i7 = this.e + 1;
            if (i7 >= a7.f229i) {
                i7 = -1;
            }
            this.e = i7;
            return yVar;
        } else {
            throw new NoSuchElementException();
        }
    }

    public final void remove() {
        boolean z;
        A a7 = this.g;
        if (a7.f228h == this.d) {
            if (this.f >= 0) {
                z = true;
            } else {
                z = false;
            }
            F.t(z, "no calls to next() since the last call to remove()");
            this.d += 32;
            a7.remove(a7.k()[this.f]);
            this.e--;
            this.f = -1;
            return;
        }
        throw new ConcurrentModificationException();
    }
}
