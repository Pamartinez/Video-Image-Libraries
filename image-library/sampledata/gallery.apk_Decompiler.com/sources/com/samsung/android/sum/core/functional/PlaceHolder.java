package com.samsung.android.sum.core.functional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface PlaceHolder<T> {
    @Deprecated
    Object[] getParameters() {
        throw new UnsupportedOperationException();
    }

    boolean isEmpty();

    boolean isNotEmpty() {
        return !isEmpty();
    }

    void put(T t);

    T reset();

    @Deprecated
    PlaceHolder<T> setParameters(Object... objArr) {
        throw new UnsupportedOperationException();
    }
}
