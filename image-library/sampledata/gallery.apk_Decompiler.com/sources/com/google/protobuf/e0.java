package com.google.protobuf;

import c0.C0086a;
import java.util.Arrays;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e0 extends C0131c implements RandomAccess {
    public static final e0 g = new e0(new Object[0], 0, false);
    public Object[] e;
    public int f;

    public e0(Object[] objArr, int i2, boolean z) {
        super(z);
        this.e = objArr;
        this.f = i2;
    }

    public final Internal$ProtobufList a(int i2) {
        if (i2 >= this.f) {
            return new e0(Arrays.copyOf(this.e, i2), this.f, true);
        }
        throw new IllegalArgumentException();
    }

    public final boolean add(Object obj) {
        i();
        int i2 = this.f;
        Object[] objArr = this.e;
        if (i2 == objArr.length) {
            this.e = Arrays.copyOf(objArr, ((i2 * 3) / 2) + 1);
        }
        Object[] objArr2 = this.e;
        int i7 = this.f;
        this.f = i7 + 1;
        objArr2[i7] = obj;
        this.modCount++;
        return true;
    }

    public final Object get(int i2) {
        p(i2);
        return this.e[i2];
    }

    public final void p(int i2) {
        if (i2 < 0 || i2 >= this.f) {
            StringBuilder o2 = C0086a.o(i2, "Index:", ", Size:");
            o2.append(this.f);
            throw new IndexOutOfBoundsException(o2.toString());
        }
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

    public final void add(int i2, Object obj) {
        int i7;
        i();
        if (i2 < 0 || i2 > (i7 = this.f)) {
            StringBuilder o2 = C0086a.o(i2, "Index:", ", Size:");
            o2.append(this.f);
            throw new IndexOutOfBoundsException(o2.toString());
        }
        Object[] objArr = this.e;
        if (i7 < objArr.length) {
            System.arraycopy(objArr, i2, objArr, i2 + 1, i7 - i2);
        } else {
            Object[] objArr2 = new Object[(((i7 * 3) / 2) + 1)];
            System.arraycopy(objArr, 0, objArr2, 0, i2);
            System.arraycopy(this.e, i2, objArr2, i2 + 1, this.f - i2);
            this.e = objArr2;
        }
        this.e[i2] = obj;
        this.f++;
        this.modCount++;
    }
}
