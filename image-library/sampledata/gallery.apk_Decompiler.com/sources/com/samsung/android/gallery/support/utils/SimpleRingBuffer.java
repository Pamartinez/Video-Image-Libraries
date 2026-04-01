package com.samsung.android.gallery.support.utils;

import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleRingBuffer<T> {
    final Object LOCK = new Object();
    final T[] buffer;
    int index;
    final int size;

    public SimpleRingBuffer(int i2) {
        this.buffer = new Object[i2];
        this.size = i2;
    }

    public void add(T t) {
        synchronized (this.LOCK) {
            try {
                T[] tArr = this.buffer;
                int i2 = this.index;
                int i7 = i2 + 1;
                this.index = i7;
                tArr[i2] = t;
                if (i7 >= this.size) {
                    this.index = 0;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public List<T> dump() {
        Object[] objArr;
        List<T> asList;
        synchronized (this.LOCK) {
            try {
                T[] tArr = this.buffer;
                int i2 = this.index;
                if (tArr[i2] != null) {
                    int i7 = this.size;
                    objArr = new Object[i7];
                    System.arraycopy(tArr, i2, objArr, 0, i7 - i2);
                    T[] tArr2 = this.buffer;
                    int i8 = this.size;
                    int i10 = this.index;
                    System.arraycopy(tArr2, 0, objArr, i8 - i10, i10);
                } else if (i2 > 0) {
                    objArr = new Object[i2];
                    System.arraycopy(tArr, 0, objArr, 0, i2);
                } else {
                    objArr = new Object[0];
                }
                asList = Arrays.asList(objArr);
            } catch (Throwable th) {
                throw th;
            }
        }
        return asList;
    }
}
