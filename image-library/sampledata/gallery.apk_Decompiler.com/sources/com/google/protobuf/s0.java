package com.google.protobuf;

import Gd.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class s0 {

    /* renamed from: a  reason: collision with root package name */
    public static final a f1629a;

    static {
        q0 q0Var;
        if (!p0.e || !p0.d || C0132d.a()) {
            q0Var = new q0(0);
        } else {
            q0Var = new q0(1);
        }
        f1629a = q0Var;
    }

    public static int a(int i2, int i7, byte[] bArr) {
        byte b = bArr[i2 - 1];
        int i8 = i7 - i2;
        if (i8 != 0) {
            if (i8 == 1) {
                return c(b, bArr[i2]);
            }
            if (i8 == 2) {
                return d(b, bArr[i2], bArr[i2 + 1]);
            }
            throw new AssertionError();
        } else if (b > -12) {
            return -1;
        } else {
            return b;
        }
    }

    public static int b(String str) {
        int length = str.length();
        int i2 = 0;
        int i7 = 0;
        while (i7 < length && str.charAt(i7) < 128) {
            i7++;
        }
        int i8 = length;
        while (true) {
            if (i7 >= length) {
                break;
            }
            char charAt = str.charAt(i7);
            if (charAt < 2048) {
                i8 += (127 - charAt) >>> 31;
                i7++;
            } else {
                int length2 = str.length();
                while (i7 < length2) {
                    char charAt2 = str.charAt(i7);
                    if (charAt2 < 2048) {
                        i2 += (127 - charAt2) >>> 31;
                    } else {
                        i2 += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(str, i7) >= 65536) {
                                i7++;
                            } else {
                                throw new r0(i7, length2);
                            }
                        }
                    }
                    i7++;
                }
                i8 += i2;
            }
        }
        if (i8 >= length) {
            return i8;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i8) + 4294967296L));
    }

    public static int c(int i2, int i7) {
        if (i2 > -12 || i7 > -65) {
            return -1;
        }
        return i2 ^ (i7 << 8);
    }

    public static int d(int i2, int i7, int i8) {
        if (i2 > -12 || i7 > -65 || i8 > -65) {
            return -1;
        }
        return (i2 ^ (i7 << 8)) ^ (i8 << 16);
    }
}
