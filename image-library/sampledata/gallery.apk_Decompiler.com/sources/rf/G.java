package rf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class G {

    /* renamed from: a  reason: collision with root package name */
    public static final E f5055a = new Object();
    public static final F b = new Object();

    public static int a(int i2, int i7) {
        if (i2 > -12 || i7 > -65) {
            return -1;
        }
        return i2 ^ (i7 << 8);
    }

    public static int b(int i2, int i7, byte[] bArr) {
        byte b5 = bArr[i2 - 1];
        int i8 = i7 - i2;
        if (i8 != 0) {
            if (i8 == 1) {
                return a(b5, bArr[i2]);
            }
            if (i8 == 2) {
                byte b8 = bArr[i2];
                byte b10 = bArr[i2 + 1];
                if (b5 > -12 || b8 > -65 || b10 > -65) {
                    return -1;
                }
                return (b10 << 16) ^ ((b8 << 8) ^ b5);
            }
            throw new AssertionError();
        } else if (b5 > -12) {
            return -1;
        } else {
            return b5;
        }
    }

    public static int c(int i2, int i7, byte[] bArr) {
        while (r7 < i7 && bArr[r7] >= 0) {
            i2 = r7 + 1;
        }
        if (r7 >= i7) {
            return 0;
        }
        while (r7 < i7) {
            int i8 = r7 + 1;
            byte b5 = bArr[r7];
            if (b5 >= 0) {
                r7 = i8;
            } else if (b5 < -32) {
                if (i8 >= i7) {
                    return b5;
                }
                if (b5 < -62) {
                    return -1;
                }
                r7 += 2;
                if (bArr[i8] > -65) {
                    return -1;
                }
            } else if (b5 < -16) {
                if (i8 >= i7 - 1) {
                    return b(i8, i7, bArr);
                }
                int i10 = r7 + 2;
                byte b8 = bArr[i8];
                if (b8 > -65) {
                    return -1;
                }
                if (b5 == -32 && b8 < -96) {
                    return -1;
                }
                if (b5 == -19 && b8 >= -96) {
                    return -1;
                }
                r7 += 3;
                if (bArr[i10] > -65) {
                    return -1;
                }
            } else if (i8 >= i7 - 2) {
                return b(i8, i7, bArr);
            } else {
                int i11 = r7 + 2;
                byte b10 = bArr[i8];
                if (b10 > -65) {
                    return -1;
                }
                if ((((b10 + 112) + (b5 << 28)) >> 30) != 0) {
                    return -1;
                }
                int i12 = r7 + 3;
                if (bArr[i11] > -65) {
                    return -1;
                }
                r7 += 4;
                if (bArr[i12] > -65) {
                    return -1;
                }
            }
        }
        return 0;
    }
}
