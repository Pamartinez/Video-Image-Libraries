package com.google.android.appfunctions.schema.internal.dependencies;

import a.C0068a;
import java.util.Arrays;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class K extends a0 implements RandomAccess {
    public static final Object[] g;

    /* renamed from: h  reason: collision with root package name */
    public static final K f1208h;
    public Object[] e;
    public int f;

    static {
        Object[] objArr = new Object[0];
        g = objArr;
        f1208h = new K(objArr, 0, false);
    }

    public K(Object[] objArr, int i2, boolean z) {
        this.d = z;
        this.e = objArr;
        this.f = i2;
    }

    public final void add(int i2, Object obj) {
        int i7;
        i();
        if (i2 < 0 || i2 > (i7 = this.f)) {
            throw new IndexOutOfBoundsException(C0068a.g0(this.f, i2, (byte) 13, "Index:", ", Size:"));
        }
        int i8 = i2 + 1;
        Object[] objArr = this.e;
        int length = objArr.length;
        if (i7 < length) {
            System.arraycopy(objArr, i2, objArr, i8, i7 - i2);
        } else {
            Object[] objArr2 = new Object[Math.max(((length * 3) / 2) + 1, 10)];
            System.arraycopy(this.e, 0, objArr2, 0, i2);
            System.arraycopy(this.e, i2, objArr2, i8, this.f - i2);
            this.e = objArr2;
        }
        this.e[i2] = obj;
        this.f++;
        this.modCount++;
    }

    public final Object get(int i2) {
        p(i2);
        return this.e[i2];
    }

    public final void p(int i2) {
        if (i2 < 0 || i2 >= this.f) {
            throw new IndexOutOfBoundsException(C0068a.g0(this.f, i2, (byte) 13, "Index:", ", Size:"));
        }
    }

    public final /* bridge */ /* synthetic */ K q(int i2) {
        Object[] objArr;
        if (i2 >= this.f) {
            if (i2 == 0) {
                objArr = g;
            } else {
                objArr = Arrays.copyOf(this.e, i2);
            }
            return new K(objArr, this.f, true);
        }
        throw new IllegalArgumentException();
    }

    public final Object remove(int i2) {
        i();
        p(i2);
        Object[] objArr = this.e;
        Object obj = objArr[i2];
        int i7 = this.f;
        if (i2 < i7 - 1) {
            System.arraycopy(objArr, i2 + 1, objArr, i2, (i7 - i2) - 1);
        }
        this.f--;
        this.modCount++;
        return obj;
    }

    public final Object set(int i2, Object obj) {
        i();
        p(i2);
        Object[] objArr = this.e;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        this.modCount++;
        return obj2;
    }

    public final int size() {
        return this.f;
    }

    public final boolean add(Object obj) {
        i();
        int i2 = this.f;
        int length = this.e.length;
        if (i2 == length) {
            this.e = Arrays.copyOf(this.e, Math.max(((length * 3) / 2) + 1, 10));
        }
        Object[] objArr = this.e;
        int i7 = this.f;
        this.f = i7 + 1;
        objArr[i7] = obj;
        this.modCount++;
        return true;
    }
}
