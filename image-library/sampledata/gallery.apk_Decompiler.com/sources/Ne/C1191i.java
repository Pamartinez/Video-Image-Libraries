package ne;

import Qe.B;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.j;

/* renamed from: ne.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1191i extends C1188f {
    public static final Object[] g = new Object[0];
    public int d;
    public Object[] e = g;
    public int f;

    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    public final boolean addAll(Collection collection) {
        j.e(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        x();
        s(collection.size() + p());
        r(w(p() + this.d), collection);
        return true;
    }

    public final void addFirst(Object obj) {
        x();
        s(this.f + 1);
        int i2 = this.d;
        if (i2 == 0) {
            Object[] objArr = this.e;
            j.e(objArr, "<this>");
            i2 = objArr.length;
        }
        int i7 = i2 - 1;
        this.d = i7;
        this.e[i7] = obj;
        this.f++;
    }

    public final void addLast(Object obj) {
        x();
        s(p() + 1);
        this.e[w(p() + this.d)] = obj;
        this.f = p() + 1;
    }

    public final void clear() {
        if (!isEmpty()) {
            x();
            v(this.d, w(p() + this.d));
        }
        this.d = 0;
        this.f = 0;
    }

    public final boolean contains(Object obj) {
        if (indexOf(obj) != -1) {
            return true;
        }
        return false;
    }

    public final Object get(int i2) {
        C1184b bVar = C1187e.Companion;
        int i7 = this.f;
        bVar.getClass();
        C1184b.a(i2, i7);
        return this.e[w(this.d + i2)];
    }

    public final int indexOf(Object obj) {
        int i2;
        int w = w(p() + this.d);
        int i7 = this.d;
        if (i7 < w) {
            while (i7 < w) {
                if (j.a(obj, this.e[i7])) {
                    i2 = this.d;
                } else {
                    i7++;
                }
            }
            return -1;
        } else if (i7 < w) {
            return -1;
        } else {
            int length = this.e.length;
            while (true) {
                if (i7 >= length) {
                    int i8 = 0;
                    while (i8 < w) {
                        if (j.a(obj, this.e[i8])) {
                            i7 = i8 + this.e.length;
                            i2 = this.d;
                        } else {
                            i8++;
                        }
                    }
                    return -1;
                } else if (j.a(obj, this.e[i7])) {
                    i2 = this.d;
                    break;
                } else {
                    i7++;
                }
            }
        }
        return i7 - i2;
    }

    public final boolean isEmpty() {
        if (p() == 0) {
            return true;
        }
        return false;
    }

    public final int lastIndexOf(Object obj) {
        int i2;
        int i7;
        int w = w(this.f + this.d);
        int i8 = this.d;
        if (i8 < w) {
            i2 = w - 1;
            if (i8 <= i2) {
                while (!j.a(obj, this.e[i2])) {
                    if (i2 != i8) {
                        i2--;
                    }
                }
                i7 = this.d;
            }
            return -1;
        }
        if (i8 > w) {
            int i10 = w - 1;
            while (true) {
                if (-1 >= i10) {
                    Object[] objArr = this.e;
                    j.e(objArr, "<this>");
                    int length = objArr.length - 1;
                    int i11 = this.d;
                    if (i11 <= length) {
                        while (!j.a(obj, this.e[i2])) {
                            if (i2 != i11) {
                                length = i2 - 1;
                            }
                        }
                        i7 = this.d;
                    }
                } else if (j.a(obj, this.e[i10])) {
                    i2 = i10 + this.e.length;
                    i7 = this.d;
                    break;
                } else {
                    i10--;
                }
            }
        }
        return -1;
        return i2 - i7;
    }

    public final int p() {
        return this.f;
    }

    public final Object q(int i2) {
        C1184b bVar = C1187e.Companion;
        int i7 = this.f;
        bVar.getClass();
        C1184b.a(i2, i7);
        if (i2 == C1195m.p0(this)) {
            return removeLast();
        }
        if (i2 == 0) {
            return removeFirst();
        }
        x();
        int w = w(this.d + i2);
        Object[] objArr = this.e;
        Object obj = objArr[w];
        if (i2 < (this.f >> 1)) {
            int i8 = this.d;
            if (w >= i8) {
                C1192j.g0(i8 + 1, i8, w, objArr, objArr);
            } else {
                C1192j.g0(1, 0, w, objArr, objArr);
                Object[] objArr2 = this.e;
                objArr2[0] = objArr2[objArr2.length - 1];
                int i10 = this.d;
                C1192j.g0(i10 + 1, i10, objArr2.length - 1, objArr2, objArr2);
            }
            Object[] objArr3 = this.e;
            int i11 = this.d;
            objArr3[i11] = null;
            this.d = t(i11);
        } else {
            int w6 = w(C1195m.p0(this) + this.d);
            if (w <= w6) {
                Object[] objArr4 = this.e;
                C1192j.g0(w, w + 1, w6 + 1, objArr4, objArr4);
            } else {
                Object[] objArr5 = this.e;
                C1192j.g0(w, w + 1, objArr5.length, objArr5, objArr5);
                Object[] objArr6 = this.e;
                objArr6[objArr6.length - 1] = objArr6[0];
                C1192j.g0(0, 1, w6 + 1, objArr6, objArr6);
            }
            this.e[w6] = null;
        }
        this.f--;
        return obj;
    }

    public final void r(int i2, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.e.length;
        while (i2 < length && it.hasNext()) {
            this.e[i2] = it.next();
            i2++;
        }
        int i7 = this.d;
        for (int i8 = 0; i8 < i7 && it.hasNext(); i8++) {
            this.e[i8] = it.next();
        }
        this.f = collection.size() + this.f;
    }

    public final boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        q(indexOf);
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3, types: [int] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean removeAll(java.util.Collection r12) {
        /*
            r11 = this;
            java.lang.String r0 = "elements"
            kotlin.jvm.internal.j.e(r12, r0)
            boolean r0 = r11.isEmpty()
            r1 = 0
            if (r0 != 0) goto L_0x008f
            java.lang.Object[] r0 = r11.e
            int r0 = r0.length
            if (r0 != 0) goto L_0x0013
            goto L_0x008f
        L_0x0013:
            int r0 = r11.d
            int r2 = r11.f
            int r2 = r2 + r0
            int r0 = r11.w(r2)
            int r2 = r11.d
            r3 = 0
            r4 = 1
            if (r2 >= r0) goto L_0x0041
            r5 = r2
        L_0x0023:
            if (r2 >= r0) goto L_0x003b
            java.lang.Object[] r6 = r11.e
            r6 = r6[r2]
            boolean r7 = r12.contains(r6)
            if (r7 != 0) goto L_0x0037
            java.lang.Object[] r7 = r11.e
            int r8 = r5 + 1
            r7[r5] = r6
            r5 = r8
            goto L_0x0038
        L_0x0037:
            r1 = r4
        L_0x0038:
            int r2 = r2 + 1
            goto L_0x0023
        L_0x003b:
            java.lang.Object[] r12 = r11.e
            ne.C1192j.k0(r12, r3, r5, r0)
            goto L_0x0081
        L_0x0041:
            java.lang.Object[] r5 = r11.e
            int r5 = r5.length
            r7 = r1
            r6 = r2
        L_0x0046:
            if (r2 >= r5) goto L_0x0060
            java.lang.Object[] r8 = r11.e
            r9 = r8[r2]
            r8[r2] = r3
            boolean r8 = r12.contains(r9)
            if (r8 != 0) goto L_0x005c
            java.lang.Object[] r8 = r11.e
            int r10 = r6 + 1
            r8[r6] = r9
            r6 = r10
            goto L_0x005d
        L_0x005c:
            r7 = r4
        L_0x005d:
            int r2 = r2 + 1
            goto L_0x0046
        L_0x0060:
            int r2 = r11.w(r6)
            r5 = r2
        L_0x0065:
            if (r1 >= r0) goto L_0x0080
            java.lang.Object[] r2 = r11.e
            r6 = r2[r1]
            r2[r1] = r3
            boolean r2 = r12.contains(r6)
            if (r2 != 0) goto L_0x007c
            java.lang.Object[] r2 = r11.e
            r2[r5] = r6
            int r5 = r11.t(r5)
            goto L_0x007d
        L_0x007c:
            r7 = r4
        L_0x007d:
            int r1 = r1 + 1
            goto L_0x0065
        L_0x0080:
            r1 = r7
        L_0x0081:
            if (r1 == 0) goto L_0x008f
            r11.x()
            int r12 = r11.d
            int r5 = r5 - r12
            int r12 = r11.u(r5)
            r11.f = r12
        L_0x008f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: ne.C1191i.removeAll(java.util.Collection):boolean");
    }

    public final Object removeFirst() {
        if (!isEmpty()) {
            x();
            Object[] objArr = this.e;
            int i2 = this.d;
            Object obj = objArr[i2];
            objArr[i2] = null;
            this.d = t(i2);
            this.f = p() - 1;
            return obj;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    public final Object removeLast() {
        if (!isEmpty()) {
            x();
            int w = w(C1195m.p0(this) + this.d);
            Object[] objArr = this.e;
            Object obj = objArr[w];
            objArr[w] = null;
            this.f = p() - 1;
            return obj;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    public final void removeRange(int i2, int i7) {
        C1184b bVar = C1187e.Companion;
        int i8 = this.f;
        bVar.getClass();
        C1184b.c(i2, i7, i8);
        int i10 = i7 - i2;
        if (i10 != 0) {
            if (i10 == this.f) {
                clear();
            } else if (i10 == 1) {
                q(i2);
            } else {
                x();
                if (i2 < this.f - i7) {
                    int w = w((i2 - 1) + this.d);
                    int w6 = w((i7 - 1) + this.d);
                    while (i2 > 0) {
                        int i11 = w + 1;
                        int min = Math.min(i2, Math.min(i11, w6 + 1));
                        Object[] objArr = this.e;
                        int i12 = w6 - min;
                        int i13 = w - min;
                        C1192j.g0(i12 + 1, i13 + 1, i11, objArr, objArr);
                        w = u(i13);
                        w6 = u(i12);
                        i2 -= min;
                    }
                    int w9 = w(this.d + i10);
                    v(this.d, w9);
                    this.d = w9;
                } else {
                    int w10 = w(this.d + i7);
                    int w11 = w(this.d + i2);
                    int i14 = this.f;
                    while (true) {
                        i14 -= i7;
                        if (i14 <= 0) {
                            break;
                        }
                        Object[] objArr2 = this.e;
                        i7 = Math.min(i14, Math.min(objArr2.length - w10, objArr2.length - w11));
                        Object[] objArr3 = this.e;
                        int i15 = w10 + i7;
                        C1192j.g0(w11, w10, i15, objArr3, objArr3);
                        w10 = w(i15);
                        w11 = w(w11 + i7);
                    }
                    int w12 = w(this.f + this.d);
                    v(u(w12 - i10), w12);
                }
                this.f -= i10;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3, types: [int] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean retainAll(java.util.Collection r12) {
        /*
            r11 = this;
            java.lang.String r0 = "elements"
            kotlin.jvm.internal.j.e(r12, r0)
            boolean r0 = r11.isEmpty()
            r1 = 0
            if (r0 != 0) goto L_0x008f
            java.lang.Object[] r0 = r11.e
            int r0 = r0.length
            if (r0 != 0) goto L_0x0013
            goto L_0x008f
        L_0x0013:
            int r0 = r11.d
            int r2 = r11.f
            int r2 = r2 + r0
            int r0 = r11.w(r2)
            int r2 = r11.d
            r3 = 0
            r4 = 1
            if (r2 >= r0) goto L_0x0041
            r5 = r2
        L_0x0023:
            if (r2 >= r0) goto L_0x003b
            java.lang.Object[] r6 = r11.e
            r6 = r6[r2]
            boolean r7 = r12.contains(r6)
            if (r7 == 0) goto L_0x0037
            java.lang.Object[] r7 = r11.e
            int r8 = r5 + 1
            r7[r5] = r6
            r5 = r8
            goto L_0x0038
        L_0x0037:
            r1 = r4
        L_0x0038:
            int r2 = r2 + 1
            goto L_0x0023
        L_0x003b:
            java.lang.Object[] r12 = r11.e
            ne.C1192j.k0(r12, r3, r5, r0)
            goto L_0x0081
        L_0x0041:
            java.lang.Object[] r5 = r11.e
            int r5 = r5.length
            r7 = r1
            r6 = r2
        L_0x0046:
            if (r2 >= r5) goto L_0x0060
            java.lang.Object[] r8 = r11.e
            r9 = r8[r2]
            r8[r2] = r3
            boolean r8 = r12.contains(r9)
            if (r8 == 0) goto L_0x005c
            java.lang.Object[] r8 = r11.e
            int r10 = r6 + 1
            r8[r6] = r9
            r6 = r10
            goto L_0x005d
        L_0x005c:
            r7 = r4
        L_0x005d:
            int r2 = r2 + 1
            goto L_0x0046
        L_0x0060:
            int r2 = r11.w(r6)
            r5 = r2
        L_0x0065:
            if (r1 >= r0) goto L_0x0080
            java.lang.Object[] r2 = r11.e
            r6 = r2[r1]
            r2[r1] = r3
            boolean r2 = r12.contains(r6)
            if (r2 == 0) goto L_0x007c
            java.lang.Object[] r2 = r11.e
            r2[r5] = r6
            int r5 = r11.t(r5)
            goto L_0x007d
        L_0x007c:
            r7 = r4
        L_0x007d:
            int r1 = r1 + 1
            goto L_0x0065
        L_0x0080:
            r1 = r7
        L_0x0081:
            if (r1 == 0) goto L_0x008f
            r11.x()
            int r12 = r11.d
            int r5 = r5 - r12
            int r12 = r11.u(r5)
            r11.f = r12
        L_0x008f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: ne.C1191i.retainAll(java.util.Collection):boolean");
    }

    public final void s(int i2) {
        if (i2 >= 0) {
            Object[] objArr = this.e;
            if (i2 > objArr.length) {
                if (objArr == g) {
                    if (i2 < 10) {
                        i2 = 10;
                    }
                    this.e = new Object[i2];
                    return;
                }
                C1184b bVar = C1187e.Companion;
                int length = objArr.length;
                bVar.getClass();
                Object[] objArr2 = new Object[C1184b.d(length, i2)];
                Object[] objArr3 = this.e;
                C1192j.g0(0, this.d, objArr3.length, objArr3, objArr2);
                Object[] objArr4 = this.e;
                int length2 = objArr4.length;
                int i7 = this.d;
                C1192j.g0(length2 - i7, 0, i7, objArr4, objArr2);
                this.d = 0;
                this.e = objArr2;
                return;
            }
            return;
        }
        throw new IllegalStateException("Deque is too big.");
    }

    public final Object set(int i2, Object obj) {
        C1184b bVar = C1187e.Companion;
        int i7 = this.f;
        bVar.getClass();
        C1184b.a(i2, i7);
        int w = w(this.d + i2);
        Object[] objArr = this.e;
        Object obj2 = objArr[w];
        objArr[w] = obj;
        return obj2;
    }

    public final int t(int i2) {
        Object[] objArr = this.e;
        j.e(objArr, "<this>");
        if (i2 == objArr.length - 1) {
            return 0;
        }
        return i2 + 1;
    }

    public final Object[] toArray() {
        return toArray(new Object[p()]);
    }

    public final int u(int i2) {
        if (i2 < 0) {
            return i2 + this.e.length;
        }
        return i2;
    }

    public final void v(int i2, int i7) {
        if (i2 < i7) {
            C1192j.k0(this.e, (B) null, i2, i7);
            return;
        }
        Object[] objArr = this.e;
        Arrays.fill(objArr, i2, objArr.length, (Object) null);
        C1192j.k0(this.e, (B) null, 0, i7);
    }

    public final int w(int i2) {
        Object[] objArr = this.e;
        if (i2 >= objArr.length) {
            return i2 - objArr.length;
        }
        return i2;
    }

    public final void x() {
        this.modCount++;
    }

    public final void add(int i2, Object obj) {
        int i7;
        C1184b bVar = C1187e.Companion;
        int i8 = this.f;
        bVar.getClass();
        C1184b.b(i2, i8);
        if (i2 == this.f) {
            addLast(obj);
        } else if (i2 == 0) {
            addFirst(obj);
        } else {
            x();
            s(this.f + 1);
            int w = w(this.d + i2);
            int i10 = this.f;
            if (i2 < ((i10 + 1) >> 1)) {
                if (w == 0) {
                    Object[] objArr = this.e;
                    j.e(objArr, "<this>");
                    w = objArr.length;
                }
                int i11 = w - 1;
                int i12 = this.d;
                if (i12 == 0) {
                    Object[] objArr2 = this.e;
                    j.e(objArr2, "<this>");
                    i7 = objArr2.length - 1;
                } else {
                    i7 = i12 - 1;
                }
                int i13 = this.d;
                if (i11 >= i13) {
                    Object[] objArr3 = this.e;
                    objArr3[i7] = objArr3[i13];
                    C1192j.g0(i13, i13 + 1, i11 + 1, objArr3, objArr3);
                } else {
                    Object[] objArr4 = this.e;
                    C1192j.g0(i13 - 1, i13, objArr4.length, objArr4, objArr4);
                    Object[] objArr5 = this.e;
                    objArr5[objArr5.length - 1] = objArr5[0];
                    C1192j.g0(0, 1, i11 + 1, objArr5, objArr5);
                }
                this.e[i11] = obj;
                this.d = i7;
            } else {
                int w6 = w(i10 + this.d);
                if (w < w6) {
                    Object[] objArr6 = this.e;
                    C1192j.g0(w + 1, w, w6, objArr6, objArr6);
                } else {
                    Object[] objArr7 = this.e;
                    C1192j.g0(1, 0, w6, objArr7, objArr7);
                    Object[] objArr8 = this.e;
                    objArr8[0] = objArr8[objArr8.length - 1];
                    C1192j.g0(w + 1, w, objArr8.length - 1, objArr8, objArr8);
                }
                this.e[w] = obj;
            }
            this.f++;
        }
    }

    public final Object[] toArray(Object[] objArr) {
        j.e(objArr, "array");
        int length = objArr.length;
        int i2 = this.f;
        if (length < i2) {
            Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), i2);
            j.c(newInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
            objArr = (Object[]) newInstance;
        }
        int w = w(this.f + this.d);
        int i7 = this.d;
        if (i7 < w) {
            C1192j.h0(i7, w, 2, this.e, objArr);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.e;
            C1192j.g0(0, this.d, objArr2.length, objArr2, objArr);
            Object[] objArr3 = this.e;
            C1192j.g0(objArr3.length - this.d, 0, w, objArr3, objArr);
        }
        int i8 = this.f;
        if (i8 < objArr.length) {
            objArr[i8] = null;
        }
        return objArr;
    }

    public final boolean addAll(int i2, Collection collection) {
        j.e(collection, "elements");
        C1184b bVar = C1187e.Companion;
        int i7 = this.f;
        bVar.getClass();
        C1184b.b(i2, i7);
        if (collection.isEmpty()) {
            return false;
        }
        if (i2 == this.f) {
            return addAll(collection);
        }
        x();
        s(collection.size() + this.f);
        int w = w(this.f + this.d);
        int w6 = w(this.d + i2);
        int size = collection.size();
        if (i2 < ((this.f + 1) >> 1)) {
            int i8 = this.d;
            int i10 = i8 - size;
            if (w6 < i8) {
                Object[] objArr = this.e;
                C1192j.g0(i10, i8, objArr.length, objArr, objArr);
                if (size >= w6) {
                    Object[] objArr2 = this.e;
                    C1192j.g0(objArr2.length - size, 0, w6, objArr2, objArr2);
                } else {
                    Object[] objArr3 = this.e;
                    C1192j.g0(objArr3.length - size, 0, size, objArr3, objArr3);
                    Object[] objArr4 = this.e;
                    C1192j.g0(0, size, w6, objArr4, objArr4);
                }
            } else if (i10 >= 0) {
                Object[] objArr5 = this.e;
                C1192j.g0(i10, i8, w6, objArr5, objArr5);
            } else {
                Object[] objArr6 = this.e;
                i10 += objArr6.length;
                int i11 = w6 - i8;
                int length = objArr6.length - i10;
                if (length >= i11) {
                    C1192j.g0(i10, i8, w6, objArr6, objArr6);
                } else {
                    C1192j.g0(i10, i8, i8 + length, objArr6, objArr6);
                    Object[] objArr7 = this.e;
                    C1192j.g0(0, this.d + length, w6, objArr7, objArr7);
                }
            }
            this.d = i10;
            r(u(w6 - size), collection);
            return true;
        }
        int i12 = w6 + size;
        if (w6 < w) {
            int i13 = size + w;
            Object[] objArr8 = this.e;
            if (i13 <= objArr8.length) {
                C1192j.g0(i12, w6, w, objArr8, objArr8);
            } else if (i12 >= objArr8.length) {
                C1192j.g0(i12 - objArr8.length, w6, w, objArr8, objArr8);
            } else {
                int length2 = w - (i13 - objArr8.length);
                C1192j.g0(0, length2, w, objArr8, objArr8);
                Object[] objArr9 = this.e;
                C1192j.g0(i12, w6, length2, objArr9, objArr9);
            }
        } else {
            Object[] objArr10 = this.e;
            C1192j.g0(size, 0, w, objArr10, objArr10);
            Object[] objArr11 = this.e;
            if (i12 >= objArr11.length) {
                C1192j.g0(i12 - objArr11.length, w6, objArr11.length, objArr11, objArr11);
            } else {
                C1192j.g0(0, objArr11.length - size, objArr11.length, objArr11, objArr11);
                Object[] objArr12 = this.e;
                C1192j.g0(i12, w6, objArr12.length - size, objArr12, objArr12);
            }
        }
        r(w6, collection);
        return true;
    }
}
