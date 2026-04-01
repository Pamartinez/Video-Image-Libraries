package com.samsung.android.gallery.support.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BufferedArray<T> implements Iterable<T>, Cloneable {
    private final Object LOCK = new Object();
    private int capacity;
    Object[] list;
    private int size;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class IndexArray extends BufferedArray<Integer> {
        public IndexArray(int i2) {
            super(i2);
            for (int i7 = 0; i7 < i2; i7++) {
                this.list[i7] = Integer.valueOf(i7);
            }
        }
    }

    public BufferedArray(int i2) {
        this.capacity = i2;
        this.size = i2;
        this.list = new Object[i2];
    }

    public Object clone() {
        try {
            BufferedArray bufferedArray = (BufferedArray) super.clone();
            bufferedArray.list = Arrays.copyOf(this.list, this.size);
            bufferedArray.capacity = this.capacity;
            bufferedArray.size = this.size;
            return bufferedArray;
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    public T computeIfAbsent(int i2, Function<Integer, T> function) {
        T t = get(i2);
        if (t != null) {
            return t;
        }
        T apply = function.apply(Integer.valueOf(i2));
        set(i2, apply);
        return apply;
    }

    public T get(int i2) {
        T t;
        synchronized (this.LOCK) {
            t = this.list[i2];
        }
        return t;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;

            public boolean hasNext() {
                if (this.index < BufferedArray.this.size()) {
                    return true;
                }
                return false;
            }

            public T next() {
                BufferedArray bufferedArray = BufferedArray.this;
                int i2 = this.index;
                this.index = i2 + 1;
                return bufferedArray.get(i2);
            }
        };
    }

    public T removeAt(int i2) {
        T t = get(i2);
        synchronized (this.LOCK) {
            try {
                int i7 = (this.size - 1) - i2;
                if (i7 > 0) {
                    Object[] objArr = this.list;
                    System.arraycopy(objArr, i2 + 1, objArr, i2, i7);
                }
                Object[] objArr2 = this.list;
                int i8 = this.size - 1;
                this.size = i8;
                objArr2[i8] = null;
            } catch (Throwable th) {
                throw th;
            }
        }
        return t;
    }

    public void removeIf(Predicate<? super T> predicate) {
        int i2 = 0;
        while (i2 < this.size) {
            if (predicate.test(get(i2))) {
                removeAt(i2);
            } else {
                i2++;
            }
        }
    }

    public void set(int i2, T t) {
        synchronized (this.LOCK) {
            this.list[i2] = t;
        }
    }

    public int size() {
        return this.size;
    }
}
