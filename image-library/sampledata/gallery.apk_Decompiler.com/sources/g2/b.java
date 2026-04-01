package G2;

import L1.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class b {
    public static final char[] d = "0123456789abcdef".toCharArray();

    public final boolean equals(Object obj) {
        boolean z;
        boolean z3;
        if (obj instanceof b) {
            byte[] bArr = ((a) this).e;
            int length = bArr.length * 8;
            byte[] bArr2 = ((a) ((b) obj)).e;
            if (length == bArr2.length * 8) {
                if (bArr.length != bArr2.length) {
                    z = false;
                } else {
                    z = true;
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        if (bArr[i2] == bArr2[i2]) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z &= z3;
                    }
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        boolean z;
        byte[] bArr = ((a) this).e;
        if (bArr.length * 8 >= 32) {
            if (bArr.length >= 4) {
                z = true;
            } else {
                z = false;
            }
            int length = bArr.length;
            if (z) {
                return ((bArr[3] & 255) << 24) | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
            }
            throw new IllegalStateException(d.r("HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", Integer.valueOf(length)));
        }
        byte b = bArr[0] & 255;
        for (int i2 = 1; i2 < bArr.length; i2++) {
            b |= (bArr[i2] & 255) << (i2 * 8);
        }
        return b;
    }

    public final String toString() {
        byte[] bArr = ((a) this).e;
        StringBuilder sb2 = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            char[] cArr = d;
            sb2.append(cArr[(b >> 4) & 15]);
            sb2.append(cArr[b & 15]);
        }
        return sb2.toString();
    }
}
