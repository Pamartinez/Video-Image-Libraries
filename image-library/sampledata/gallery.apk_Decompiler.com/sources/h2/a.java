package H2;

import L1.d;
import java.math.RoundingMode;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final String f336a;
    public final char[] b;

    /* renamed from: c  reason: collision with root package name */
    public final int f337c;
    public final int d;
    public final int e;
    public final int f;
    public final byte[] g;

    public a(String str, char[] cArr) {
        boolean z;
        byte[] bArr = new byte[128];
        Arrays.fill(bArr, (byte) -1);
        int i2 = 0;
        while (true) {
            boolean z3 = true;
            if (i2 < cArr.length) {
                char c5 = cArr[i2];
                if (c5 < 128) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    if (bArr[c5] != -1 ? false : z3) {
                        bArr[c5] = (byte) i2;
                        i2++;
                    } else {
                        throw new IllegalArgumentException(d.r("Duplicate character: %s", Character.valueOf(c5)));
                    }
                } else {
                    throw new IllegalArgumentException(d.r("Non-ASCII character: %s", Character.valueOf(c5)));
                }
            } else {
                this.f336a = str;
                this.b = cArr;
                try {
                    int length = cArr.length;
                    RoundingMode roundingMode = RoundingMode.UNNECESSARY;
                    int s = d.s(length);
                    this.d = s;
                    int numberOfTrailingZeros = Integer.numberOfTrailingZeros(s);
                    int i7 = 1 << (3 - numberOfTrailingZeros);
                    this.e = i7;
                    this.f = s >> numberOfTrailingZeros;
                    this.f337c = cArr.length - 1;
                    this.g = bArr;
                    boolean[] zArr = new boolean[i7];
                    for (int i8 = 0; i8 < this.f; i8++) {
                        int i10 = this.d;
                        RoundingMode roundingMode2 = RoundingMode.CEILING;
                        zArr[d.g(i8 * 8, i10)] = true;
                    }
                    return;
                } catch (ArithmeticException e7) {
                    throw new IllegalArgumentException("Illegal alphabet length " + cArr.length, e7);
                }
            }
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a) || !Arrays.equals(this.b, ((a) obj).b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b) + 1237;
    }

    public final String toString() {
        return this.f336a;
    }
}
