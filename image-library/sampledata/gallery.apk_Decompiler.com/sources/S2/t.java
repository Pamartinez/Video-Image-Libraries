package S2;

import Q2.a;
import T2.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t extends v {
    public final String d;
    public final a e;

    static {
        new t("");
    }

    public t(String str) {
        if (str != null) {
            this.d = str.intern();
            int length = str.length();
            byte[] bArr = new byte[(length * 3)];
            int i2 = 0;
            for (int i7 = 0; i7 < length; i7++) {
                char charAt = str.charAt(i7);
                if (charAt != 0 && charAt < 128) {
                    bArr[i2] = (byte) charAt;
                    i2++;
                } else if (charAt < 2048) {
                    bArr[i2] = (byte) (((charAt >> 6) & 31) | 192);
                    bArr[i2 + 1] = (byte) ((charAt & '?') | 128);
                    i2 += 2;
                } else {
                    bArr[i2] = (byte) (((charAt >> 12) & 15) | 224);
                    bArr[i2 + 1] = (byte) (((charAt >> 6) & 63) | 128);
                    bArr[i2 + 2] = (byte) ((charAt & '?') | 128);
                    i2 += 3;
                }
            }
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            this.e = new a(bArr2);
            return;
        }
        throw new NullPointerException("string == null");
    }

    public final String a() {
        char c5;
        boolean z;
        String str = this.d;
        int length = str.length();
        StringBuilder sb2 = new StringBuilder((length * 3) / 2);
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt >= ' ' && charAt < 127) {
                if (charAt == '\'' || charAt == '\"' || charAt == '\\') {
                    sb2.append('\\');
                }
                sb2.append(charAt);
            } else if (charAt > 127) {
                sb2.append("\\u");
                sb2.append(Character.forDigit(charAt >> 12, 16));
                sb2.append(Character.forDigit((charAt >> 8) & 15, 16));
                sb2.append(Character.forDigit((charAt >> 4) & 15, 16));
                sb2.append(Character.forDigit(charAt & 15, 16));
            } else if (charAt == 9) {
                sb2.append("\\t");
            } else if (charAt == 10) {
                sb2.append("\\n");
            } else if (charAt != 13) {
                if (i2 < length - 1) {
                    c5 = str.charAt(i2 + 1);
                } else {
                    c5 = 0;
                }
                if (c5 < '0' || c5 > '7') {
                    z = false;
                } else {
                    z = true;
                }
                sb2.append('\\');
                for (int i7 = 6; i7 >= 0; i7 -= 3) {
                    char c6 = (char) (((charAt >> i7) & 7) + 48);
                    if (c6 != '0' || z) {
                        sb2.append(c6);
                        z = true;
                    }
                }
                if (!z) {
                    sb2.append('0');
                }
            } else {
                sb2.append("\\r");
            }
        }
        return sb2.toString();
    }

    public final int d(a aVar) {
        return this.d.compareTo(((t) aVar).d);
    }

    public final String e() {
        return "utf8";
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof t)) {
            return false;
        }
        return this.d.equals(((t) obj).d);
    }

    public final String f() {
        return "\"" + a() + '\"';
    }

    public final c getType() {
        return c.w;
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    public final String toString() {
        return "string{\"" + a() + "\"}";
    }
}
