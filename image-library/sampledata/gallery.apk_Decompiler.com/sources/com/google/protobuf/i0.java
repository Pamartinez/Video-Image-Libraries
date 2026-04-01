package com.google.protobuf;

import java.util.ListIterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i0 implements ListIterator {
    public ListIterator d;

    public final void add(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.d.hasNext();
    }

    public final boolean hasPrevious() {
        return this.d.hasPrevious();
    }

    public final Object next() {
        return (String) this.d.next();
    }

    public final int nextIndex() {
        return this.d.nextIndex();
    }

    public final Object previous() {
        return (String) this.d.previous();
    }

    public final int previousIndex() {
        return this.d.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final void set(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }
}
