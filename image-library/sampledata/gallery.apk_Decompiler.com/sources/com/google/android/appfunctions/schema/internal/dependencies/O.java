package com.google.android.appfunctions.schema.internal.dependencies;

import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class O {
    public static final O f = new O(0, new int[0], new Object[0], false);

    /* renamed from: a  reason: collision with root package name */
    public int f1213a;
    public int[] b;

    /* renamed from: c  reason: collision with root package name */
    public Object[] f1214c;
    public int d = -1;
    public boolean e;

    public O(int i2, int[] iArr, Object[] objArr, boolean z) {
        this.f1213a = i2;
        this.b = iArr;
        this.f1214c = objArr;
        this.e = z;
    }

    public final void a(C0114y yVar) {
        h0 h0Var = (h0) yVar.f1229a;
        if (this.f1213a != 0) {
            for (int i2 = 0; i2 < this.f1213a; i2++) {
                int i7 = this.b[i2];
                Object obj = this.f1214c[i2];
                int i8 = i7 & 7;
                int i10 = i7 >>> 3;
                if (i8 == 0) {
                    h0Var.c0(i10, ((Long) obj).longValue());
                } else if (i8 == 1) {
                    h0Var.e0(i10, ((Long) obj).longValue());
                } else if (i8 == 2) {
                    h0Var.g0(i10, (f0) obj);
                } else if (i8 == 3) {
                    h0Var.Z(i10, 3);
                    ((O) obj).a(yVar);
                    h0Var.Z(i10, 4);
                } else if (i8 == 5) {
                    h0Var.b0(i10, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(new C0107q());
                }
            }
        }
    }

    public final int b() {
        int l0;
        int m02;
        int l02;
        int i2 = this.d;
        if (i2 != -1) {
            return i2;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < this.f1213a; i8++) {
            int i10 = this.b[i8];
            int i11 = i10 >>> 3;
            int i12 = i10 & 7;
            if (i12 != 0) {
                if (i12 == 1) {
                    ((Long) this.f1214c[i8]).getClass();
                    l02 = h0.l0(i11 << 3) + 8;
                } else if (i12 == 2) {
                    int l03 = h0.l0(i11 << 3);
                    int r = ((f0) this.f1214c[i8]).r();
                    i7 = C0086a.b(r, r, l03, i7);
                } else if (i12 == 3) {
                    int l04 = h0.l0(i11 << 3);
                    l0 = l04 + l04;
                    m02 = ((O) this.f1214c[i8]).b();
                } else if (i12 == 5) {
                    ((Integer) this.f1214c[i8]).getClass();
                    l02 = h0.l0(i11 << 3) + 4;
                } else {
                    throw new IllegalStateException(new C0107q());
                }
                i7 = l02 + i7;
            } else {
                int i13 = i11 << 3;
                long longValue = ((Long) this.f1214c[i8]).longValue();
                l0 = h0.l0(i13);
                m02 = h0.m0(longValue);
            }
            i7 = m02 + l0 + i7;
        }
        this.d = i7;
        return i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof O)) {
            return false;
        }
        O o2 = (O) obj;
        int i2 = this.f1213a;
        if (i2 == o2.f1213a) {
            int[] iArr = this.b;
            int[] iArr2 = o2.b;
            int i7 = 0;
            while (true) {
                if (i7 >= i2) {
                    Object[] objArr = this.f1214c;
                    Object[] objArr2 = o2.f1214c;
                    int i8 = this.f1213a;
                    int i10 = 0;
                    while (i10 < i8) {
                        if (objArr[i10].equals(objArr2[i10])) {
                            i10++;
                        }
                    }
                    return true;
                } else if (iArr[i7] != iArr2[i7]) {
                    break;
                } else {
                    i7++;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i2 = this.f1213a;
        int i7 = i2 + 527;
        int[] iArr = this.b;
        int i8 = 17;
        int i10 = 17;
        for (int i11 = 0; i11 < i2; i11++) {
            i10 = (i10 * 31) + iArr[i11];
        }
        int i12 = ((i7 * 31) + i10) * 31;
        Object[] objArr = this.f1214c;
        int i13 = this.f1213a;
        for (int i14 = 0; i14 < i13; i14++) {
            i8 = (i8 * 31) + objArr[i14].hashCode();
        }
        return i12 + i8;
    }
}
