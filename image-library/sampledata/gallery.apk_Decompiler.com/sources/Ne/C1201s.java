package ne;

import Be.a;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* renamed from: ne.s  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1201s implements ListIterator, a {
    public static final C1201s d = new Object();

    public final /* bridge */ /* synthetic */ void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean hasNext() {
        return false;
    }

    public final boolean hasPrevious() {
        return false;
    }

    public final Object next() {
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return 0;
    }

    public final Object previous() {
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return -1;
    }

    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final /* bridge */ /* synthetic */ void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
