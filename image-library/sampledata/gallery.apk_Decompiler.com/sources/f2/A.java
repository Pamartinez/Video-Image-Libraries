package F2;

import D1.f;
import He.F;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A extends AbstractMap implements Serializable {
    public static final Object m = new Object();
    public transient Object d;
    public transient int[] e;
    public transient Object[] f;
    public transient Object[] g;

    /* renamed from: h  reason: collision with root package name */
    public transient int f228h;

    /* renamed from: i  reason: collision with root package name */
    public transient int f229i;

    /* renamed from: j  reason: collision with root package name */
    public transient C0042x f230j;
    public transient C0042x k;
    public transient C0044z l;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.AbstractMap, F2.A] */
    public static A a() {
        ? abstractMap = new AbstractMap();
        abstractMap.f228h = Math.min(Math.max(3, 1), 1073741823);
        return abstractMap;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.AbstractMap, F2.A] */
    public static A b(int i2) {
        boolean z;
        ? abstractMap = new AbstractMap();
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        F.i("Expected size must be >= 0", z);
        abstractMap.f228h = Math.min(Math.max(i2, 1), 1073741823);
        return abstractMap;
    }

    public final Map c() {
        Object obj = this.d;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    public final void clear() {
        if (!g()) {
            this.f228h += 32;
            Map c5 = c();
            if (c5 != null) {
                this.f228h = Math.min(Math.max(size(), 3), 1073741823);
                c5.clear();
                this.d = null;
                this.f229i = 0;
                return;
            }
            Arrays.fill(k(), 0, this.f229i, (Object) null);
            Arrays.fill(l(), 0, this.f229i, (Object) null);
            Object obj = this.d;
            Objects.requireNonNull(obj);
            if (obj instanceof byte[]) {
                Arrays.fill((byte[]) obj, (byte) 0);
            } else if (obj instanceof short[]) {
                Arrays.fill((short[]) obj, 0);
            } else {
                Arrays.fill((int[]) obj, 0);
            }
            Arrays.fill(j(), 0, this.f229i, 0);
            this.f229i = 0;
        }
    }

    public final boolean containsKey(Object obj) {
        Map c5 = c();
        if (c5 != null) {
            return c5.containsKey(obj);
        }
        if (e(obj) != -1) {
            return true;
        }
        return false;
    }

    public final boolean containsValue(Object obj) {
        Map c5 = c();
        if (c5 != null) {
            return c5.containsValue(obj);
        }
        for (int i2 = 0; i2 < this.f229i; i2++) {
            if (f.p(obj, l()[i2])) {
                return true;
            }
        }
        return false;
    }

    public final int d() {
        return (1 << (this.f228h & 31)) - 1;
    }

    public final int e(Object obj) {
        if (g()) {
            return -1;
        }
        int q = C0040v.q(obj);
        int d2 = d();
        Object obj2 = this.d;
        Objects.requireNonNull(obj2);
        int r = C0040v.r(q & d2, obj2);
        if (r == 0) {
            return -1;
        }
        int i2 = ~d2;
        int i7 = q & i2;
        do {
            int i8 = r - 1;
            int i10 = j()[i8];
            if ((i10 & i2) == i7 && f.p(obj, k()[i8])) {
                return i8;
            }
            r = i10 & d2;
        } while (r != 0);
        return -1;
    }

    public final Set entrySet() {
        C0042x xVar = this.k;
        if (xVar != null) {
            return xVar;
        }
        C0042x xVar2 = new C0042x(this, 0);
        this.k = xVar2;
        return xVar2;
    }

    public final void f(int i2, int i7) {
        Object obj = this.d;
        Objects.requireNonNull(obj);
        int[] j2 = j();
        Object[] k10 = k();
        Object[] l8 = l();
        int size = size();
        int i8 = size - 1;
        if (i2 < i8) {
            Object obj2 = k10[i8];
            k10[i2] = obj2;
            l8[i2] = l8[i8];
            k10[i8] = null;
            l8[i8] = null;
            j2[i2] = j2[i8];
            j2[i8] = 0;
            int q = C0040v.q(obj2) & i7;
            int r = C0040v.r(q, obj);
            if (r == size) {
                C0040v.s(q, i2 + 1, obj);
                return;
            }
            while (true) {
                int i10 = r - 1;
                int i11 = j2[i10];
                int i12 = i11 & i7;
                if (i12 == size) {
                    j2[i10] = C0040v.l(i11, i2 + 1, i7);
                    return;
                }
                r = i12;
            }
        } else {
            k10[i2] = null;
            l8[i2] = null;
            j2[i2] = 0;
        }
    }

    public final boolean g() {
        if (this.d == null) {
            return true;
        }
        return false;
    }

    public final Object get(Object obj) {
        Map c5 = c();
        if (c5 != null) {
            return c5.get(obj);
        }
        int e7 = e(obj);
        if (e7 == -1) {
            return null;
        }
        return l()[e7];
    }

    public final Object h(Object obj) {
        if (!g()) {
            int d2 = d();
            Object obj2 = this.d;
            Objects.requireNonNull(obj2);
            int n = C0040v.n(obj, (Object) null, d2, obj2, j(), k(), (Object[]) null);
            if (n != -1) {
                Object obj3 = l()[n];
                f(n, d2);
                this.f229i--;
                this.f228h += 32;
                return obj3;
            }
        }
        return m;
    }

    public final boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public final int[] j() {
        int[] iArr = this.e;
        Objects.requireNonNull(iArr);
        return iArr;
    }

    public final Object[] k() {
        Object[] objArr = this.f;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    public final Set keySet() {
        C0042x xVar = this.f230j;
        if (xVar != null) {
            return xVar;
        }
        C0042x xVar2 = new C0042x(this, 1);
        this.f230j = xVar2;
        return xVar2;
    }

    public final Object[] l() {
        Object[] objArr = this.g;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    public final int m(int i2, int i7, int i8, int i10) {
        Object d2 = C0040v.d(i7);
        int i11 = i7 - 1;
        if (i10 != 0) {
            C0040v.s(i8 & i11, i10 + 1, d2);
        }
        Object obj = this.d;
        Objects.requireNonNull(obj);
        int[] j2 = j();
        for (int i12 = 0; i12 <= i2; i12++) {
            int r = C0040v.r(i12, obj);
            while (r != 0) {
                int i13 = r - 1;
                int i14 = j2[i13];
                int i15 = ((~i2) & i14) | i12;
                int i16 = i15 & i11;
                int r5 = C0040v.r(i16, d2);
                C0040v.s(i16, r, d2);
                j2[i13] = C0040v.l(i15, r5, i11);
                r = i14 & i2;
            }
        }
        this.d = d2;
        this.f228h = C0040v.l(this.f228h, 32 - Integer.numberOfLeadingZeros(i11), 31);
        return i11;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0103 A[SYNTHETIC] */
    public final java.lang.Object put(java.lang.Object r23, java.lang.Object r24) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            boolean r3 = r0.g()
            r4 = 2
            r5 = 4
            r6 = 32
            r7 = 1
            if (r3 == 0) goto L_0x005b
            boolean r3 = r0.g()
            java.lang.String r8 = "Arrays already allocated"
            He.F.t(r3, r8)
            int r3 = r0.f228h
            int r8 = r3 + 1
            int r8 = java.lang.Math.max(r8, r4)
            int r9 = java.lang.Integer.highestOneBit(r8)
            double r10 = (double) r9
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r12 = r12 * r10
            int r10 = (int) r12
            if (r8 <= r10) goto L_0x0034
            int r9 = r9 << 1
            if (r9 <= 0) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            r9 = 1073741824(0x40000000, float:2.0)
        L_0x0034:
            int r8 = java.lang.Math.max(r5, r9)
            java.lang.Object r9 = F2.C0040v.d(r8)
            r0.d = r9
            int r8 = r8 - r7
            int r8 = java.lang.Integer.numberOfLeadingZeros(r8)
            int r8 = 32 - r8
            int r9 = r0.f228h
            r10 = 31
            int r8 = F2.C0040v.l(r9, r8, r10)
            r0.f228h = r8
            int[] r8 = new int[r3]
            r0.e = r8
            java.lang.Object[] r8 = new java.lang.Object[r3]
            r0.f = r8
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r0.g = r3
        L_0x005b:
            java.util.Map r3 = r0.c()
            if (r3 == 0) goto L_0x0066
            java.lang.Object r0 = r3.put(r1, r2)
            return r0
        L_0x0066:
            int[] r3 = r0.j()
            java.lang.Object[] r8 = r0.k()
            java.lang.Object[] r9 = r0.l()
            int r10 = r0.f229i
            int r11 = r10 + 1
            int r12 = F2.C0040v.q(r1)
            int r13 = r0.d()
            r14 = r12 & r13
            java.lang.Object r15 = r0.d
            java.util.Objects.requireNonNull(r15)
            int r15 = F2.C0040v.r(r14, r15)
            if (r15 != 0) goto L_0x00a9
            if (r11 <= r13) goto L_0x00a0
            if (r13 >= r6) goto L_0x0092
            r16 = 4
            goto L_0x0094
        L_0x0092:
            r16 = 2
        L_0x0094:
            int r3 = r13 + 1
            int r3 = r3 * r16
            int r13 = r0.m(r13, r3, r12, r10)
        L_0x009c:
            r19 = r7
            goto L_0x012e
        L_0x00a0:
            java.lang.Object r3 = r0.d
            java.util.Objects.requireNonNull(r3)
            F2.C0040v.s(r14, r11, r3)
            goto L_0x009c
        L_0x00a9:
            int r14 = ~r13
            r5 = r12 & r14
            r18 = 0
        L_0x00ae:
            int r15 = r15 - r7
            r19 = r7
            r7 = r3[r15]
            r20 = r6
            r6 = r7 & r14
            if (r6 != r5) goto L_0x00c6
            r6 = r8[r15]
            boolean r6 = D1.f.p(r1, r6)
            if (r6 == 0) goto L_0x00c6
            r0 = r9[r15]
            r9[r15] = r2
            return r0
        L_0x00c6:
            r6 = r7 & r13
            int r4 = r18 + 1
            if (r6 != 0) goto L_0x018a
            r5 = 9
            if (r4 < r5) goto L_0x0117
            int r3 = r0.d()
            int r3 = r3 + 1
            java.util.LinkedHashMap r4 = new java.util.LinkedHashMap
            r5 = 1065353216(0x3f800000, float:1.0)
            r4.<init>(r3, r5)
            boolean r3 = r0.isEmpty()
            r5 = -1
            if (r3 == 0) goto L_0x00e7
        L_0x00e4:
            r17 = r5
            goto L_0x00e9
        L_0x00e7:
            r17 = 0
        L_0x00e9:
            if (r17 < 0) goto L_0x0103
            java.lang.Object[] r3 = r0.k()
            r3 = r3[r17]
            java.lang.Object[] r6 = r0.l()
            r6 = r6[r17]
            r4.put(r3, r6)
            int r3 = r17 + 1
            int r6 = r0.f229i
            if (r3 >= r6) goto L_0x00e4
            r17 = r3
            goto L_0x00e9
        L_0x0103:
            r0.d = r4
            r3 = 0
            r0.e = r3
            r0.f = r3
            r0.g = r3
            int r3 = r0.f228h
            int r3 = r3 + 32
            r0.f228h = r3
            java.lang.Object r0 = r4.put(r1, r2)
            return r0
        L_0x0117:
            if (r11 <= r13) goto L_0x0128
            r4 = r20
            if (r13 >= r4) goto L_0x011f
            r4 = 4
            goto L_0x0120
        L_0x011f:
            r4 = 2
        L_0x0120:
            int r3 = r13 + 1
            int r3 = r3 * r4
            int r13 = r0.m(r13, r3, r12, r10)
            goto L_0x012e
        L_0x0128:
            int r4 = F2.C0040v.l(r7, r11, r13)
            r3[r15] = r4
        L_0x012e:
            int[] r3 = r0.j()
            int r3 = r3.length
            if (r11 <= r3) goto L_0x0166
            int r4 = r3 >>> 1
            r7 = r19
            int r4 = java.lang.Math.max(r7, r4)
            int r4 = r4 + r3
            r4 = r4 | r7
            r5 = 1073741823(0x3fffffff, float:1.9999999)
            int r4 = java.lang.Math.min(r5, r4)
            if (r4 == r3) goto L_0x0166
            int[] r3 = r0.j()
            int[] r3 = java.util.Arrays.copyOf(r3, r4)
            r0.e = r3
            java.lang.Object[] r3 = r0.k()
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r4)
            r0.f = r3
            java.lang.Object[] r3 = r0.l()
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r4)
            r0.g = r3
        L_0x0166:
            r15 = 0
            int r3 = F2.C0040v.l(r12, r15, r13)
            int[] r4 = r0.j()
            r4[r10] = r3
            java.lang.Object[] r3 = r0.k()
            r3[r10] = r1
            java.lang.Object[] r1 = r0.l()
            r1[r10] = r2
            r0.f229i = r11
            int r1 = r0.f228h
            r20 = 32
            int r1 = r1 + 32
            r0.f228h = r1
            r21 = 0
            return r21
        L_0x018a:
            r21 = 0
            r18 = r4
            r15 = r6
            r7 = r19
            r6 = r20
            goto L_0x00ae
        */
        throw new UnsupportedOperationException("Method not decompiled: F2.A.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final Object remove(Object obj) {
        Map c5 = c();
        if (c5 != null) {
            return c5.remove(obj);
        }
        Object h5 = h(obj);
        if (h5 == m) {
            return null;
        }
        return h5;
    }

    public final int size() {
        Map c5 = c();
        if (c5 != null) {
            return c5.size();
        }
        return this.f229i;
    }

    public final Collection values() {
        C0044z zVar = this.l;
        if (zVar != null) {
            return zVar;
        }
        C0044z zVar2 = new C0044z(this);
        this.l = zVar2;
        return zVar2;
    }
}
