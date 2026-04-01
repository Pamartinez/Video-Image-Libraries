package com.google.protobuf;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k0 extends AbstractList implements I, RandomAccess {
    public final H d;

    public k0(H h5) {
        this.d = h5;
    }

    public final List b() {
        return Collections.unmodifiableList(this.d.e);
    }

    public final void f(ByteString byteString) {
        throw new UnsupportedOperationException();
    }

    public final Object get(int i2) {
        return (String) this.d.get(i2);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.protobuf.j0, java.util.Iterator, java.lang.Object] */
    public final Iterator iterator() {
        ? obj = new Object();
        obj.d = this.d.iterator();
        return obj;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.protobuf.i0, java.util.ListIterator, java.lang.Object] */
    public final ListIterator listIterator(int i2) {
        ? obj = new Object();
        obj.d = this.d.listIterator(i2);
        return obj;
    }

    public final Object n(int i2) {
        return this.d.e.get(i2);
    }

    public final int size() {
        return this.d.e.size();
    }

    public final I c() {
        return this;
    }
}
