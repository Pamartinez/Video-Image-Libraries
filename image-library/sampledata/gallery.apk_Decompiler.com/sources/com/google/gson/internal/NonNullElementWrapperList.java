package com.google.gson.internal;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NonNullElementWrapperList<E> extends AbstractList<E> implements RandomAccess {
    private final ArrayList<E> delegate;

    public NonNullElementWrapperList(ArrayList<E> arrayList) {
        Objects.requireNonNull(arrayList);
        this.delegate = arrayList;
    }

    private E nonNull(E e) {
        if (e != null) {
            return e;
        }
        throw new NullPointerException("Element must be non-null");
    }

    public void add(int i2, E e) {
        this.delegate.add(i2, nonNull(e));
    }

    public void clear() {
        this.delegate.clear();
    }

    public boolean contains(Object obj) {
        return this.delegate.contains(obj);
    }

    public boolean equals(Object obj) {
        return this.delegate.equals(obj);
    }

    public E get(int i2) {
        return this.delegate.get(i2);
    }

    public int hashCode() {
        return this.delegate.hashCode();
    }

    public int indexOf(Object obj) {
        return this.delegate.indexOf(obj);
    }

    public int lastIndexOf(Object obj) {
        return this.delegate.lastIndexOf(obj);
    }

    public E remove(int i2) {
        return this.delegate.remove(i2);
    }

    public boolean removeAll(Collection<?> collection) {
        return this.delegate.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return this.delegate.retainAll(collection);
    }

    public E set(int i2, E e) {
        return this.delegate.set(i2, nonNull(e));
    }

    public int size() {
        return this.delegate.size();
    }

    public Object[] toArray() {
        return this.delegate.toArray();
    }

    public boolean remove(Object obj) {
        return this.delegate.remove(obj);
    }

    public <T> T[] toArray(T[] tArr) {
        return this.delegate.toArray(tArr);
    }
}
