package F2;

import java.util.Arrays;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D0 extends Y {
    public static final D0 k = new D0(new Object[0], 0, (Object) null);

    /* renamed from: h  reason: collision with root package name */
    public final transient Object f236h;

    /* renamed from: i  reason: collision with root package name */
    public final transient Object[] f237i;

    /* renamed from: j  reason: collision with root package name */
    public final transient int f238j;

    public D0(Object[] objArr, int i2, Object obj) {
        this.f236h = obj;
        this.f237i = objArr;
        this.f238j = i2;
    }

    public static Object j(Object[] objArr, int i2, int i7, int i8) {
        W w = null;
        if (i2 == 1) {
            Objects.requireNonNull(objArr[i8]);
            Objects.requireNonNull(objArr[i8 ^ 1]);
            return null;
        }
        int i10 = i7 - 1;
        int i11 = 0;
        if (i7 <= 128) {
            byte[] bArr = new byte[i7];
            Arrays.fill(bArr, (byte) -1);
            int i12 = 0;
            while (i11 < i2) {
                int i13 = (i11 * 2) + i8;
                int i14 = (i12 * 2) + i8;
                Object obj = objArr[i13];
                Objects.requireNonNull(obj);
                Object obj2 = objArr[i13 ^ 1];
                Objects.requireNonNull(obj2);
                int p6 = C0040v.p(obj.hashCode());
                while (true) {
                    int i15 = p6 & i10;
                    byte b = bArr[i15] & 255;
                    if (b == 255) {
                        bArr[i15] = (byte) i14;
                        if (i12 < i11) {
                            objArr[i14] = obj;
                            objArr[i14 ^ 1] = obj2;
                        }
                        i12++;
                    } else if (obj.equals(objArr[b])) {
                        byte b5 = b ^ 1;
                        Object obj3 = objArr[b5];
                        Objects.requireNonNull(obj3);
                        w = new W(obj, obj2, obj3);
                        objArr[b5] = obj2;
                        break;
                    } else {
                        p6 = i15 + 1;
                    }
                }
                i11++;
            }
            if (i12 == i2) {
                return bArr;
            }
            return new Object[]{bArr, Integer.valueOf(i12), w};
        } else if (i7 <= 32768) {
            short[] sArr = new short[i7];
            Arrays.fill(sArr, -1);
            int i16 = 0;
            while (i11 < i2) {
                int i17 = (i11 * 2) + i8;
                int i18 = (i16 * 2) + i8;
                Object obj4 = objArr[i17];
                Objects.requireNonNull(obj4);
                Object obj5 = objArr[i17 ^ 1];
                Objects.requireNonNull(obj5);
                int p8 = C0040v.p(obj4.hashCode());
                while (true) {
                    int i19 = p8 & i10;
                    short s = sArr[i19] & 65535;
                    if (s == 65535) {
                        sArr[i19] = (short) i18;
                        if (i16 < i11) {
                            objArr[i18] = obj4;
                            objArr[i18 ^ 1] = obj5;
                        }
                        i16++;
                    } else if (obj4.equals(objArr[s])) {
                        short s5 = s ^ 1;
                        Object obj6 = objArr[s5];
                        Objects.requireNonNull(obj6);
                        w = new W(obj4, obj5, obj6);
                        objArr[s5] = obj5;
                        break;
                    } else {
                        p8 = i19 + 1;
                    }
                }
                i11++;
            }
            if (i16 == i2) {
                return sArr;
            }
            return new Object[]{sArr, Integer.valueOf(i16), w};
        } else {
            int[] iArr = new int[i7];
            Arrays.fill(iArr, -1);
            int i20 = 0;
            while (i11 < i2) {
                int i21 = (i11 * 2) + i8;
                int i22 = (i20 * 2) + i8;
                Object obj7 = objArr[i21];
                Objects.requireNonNull(obj7);
                Object obj8 = objArr[i21 ^ 1];
                Objects.requireNonNull(obj8);
                int p10 = C0040v.p(obj7.hashCode());
                while (true) {
                    int i23 = p10 & i10;
                    int i24 = iArr[i23];
                    if (i24 == -1) {
                        iArr[i23] = i22;
                        if (i20 < i11) {
                            objArr[i22] = obj7;
                            objArr[i22 ^ 1] = obj8;
                        }
                        i20++;
                    } else if (obj7.equals(objArr[i24])) {
                        int i25 = i24 ^ 1;
                        Object obj9 = objArr[i25];
                        Objects.requireNonNull(obj9);
                        w = new W(obj7, obj8, obj9);
                        objArr[i25] = obj8;
                        break;
                    } else {
                        p10 = i23 + 1;
                    }
                }
                i11++;
            }
            if (i20 == i2) {
                return iArr;
            }
            return new Object[]{iArr, Integer.valueOf(i20), w};
        }
    }

    public static Object k(Object obj, Object[] objArr, int i2, int i7, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (i2 == 1) {
            Object obj3 = objArr[i7];
            Objects.requireNonNull(obj3);
            if (!obj3.equals(obj2)) {
                return null;
            }
            Object obj4 = objArr[i7 ^ 1];
            Objects.requireNonNull(obj4);
            return obj4;
        } else if (obj == null) {
            return null;
        } else {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                int length = bArr.length - 1;
                int p6 = C0040v.p(obj2.hashCode());
                while (true) {
                    int i8 = p6 & length;
                    byte b = bArr[i8] & 255;
                    if (b == 255) {
                        return null;
                    }
                    if (obj2.equals(objArr[b])) {
                        return objArr[b ^ 1];
                    }
                    p6 = i8 + 1;
                }
            } else if (obj instanceof short[]) {
                short[] sArr = (short[]) obj;
                int length2 = sArr.length - 1;
                int p8 = C0040v.p(obj2.hashCode());
                while (true) {
                    int i10 = p8 & length2;
                    short s = sArr[i10] & 65535;
                    if (s == 65535) {
                        return null;
                    }
                    if (obj2.equals(objArr[s])) {
                        return objArr[s ^ 1];
                    }
                    p8 = i10 + 1;
                }
            } else {
                int[] iArr = (int[]) obj;
                int length3 = iArr.length - 1;
                int p10 = C0040v.p(obj2.hashCode());
                while (true) {
                    int i11 = p10 & length3;
                    int i12 = iArr[i11];
                    if (i12 == -1) {
                        return null;
                    }
                    if (obj2.equals(objArr[i12])) {
                        return objArr[i12 ^ 1];
                    }
                    p10 = i11 + 1;
                }
            }
        }
    }

    public final C0010c0 b() {
        return new A0(this, this.f237i, 0, this.f238j);
    }

    public final C0010c0 c() {
        return new B0(this, new C0(this.f237i, 0, this.f238j));
    }

    public final O d() {
        return new C0(this.f237i, 1, this.f238j);
    }

    public final boolean f() {
        return false;
    }

    public final Object get(Object obj) {
        Object k10 = k(this.f236h, this.f237i, this.f238j, 0, obj);
        if (k10 == null) {
            return null;
        }
        return k10;
    }

    public final int size() {
        return this.f238j;
    }
}
