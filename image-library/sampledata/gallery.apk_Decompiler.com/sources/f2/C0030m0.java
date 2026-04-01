package F2;

import java.util.NoSuchElementException;

/* renamed from: F2.m0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0030m0 extends O0 {
    public final Object d;
    public boolean e;

    public C0030m0(Object obj) {
        this.d = obj;
    }

    public final boolean hasNext() {
        return !this.e;
    }

    public final Object next() {
        if (!this.e) {
            this.e = true;
            return this.d;
        }
        throw new NoSuchElementException();
    }
}
