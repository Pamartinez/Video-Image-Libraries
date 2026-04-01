package og;

import c0.C0086a;
import i.C0212a;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class c implements Serializable, Comparable {
    public static final char[] g = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: h  reason: collision with root package name */
    public static final c f5013h = new c((byte[]) new byte[0].clone());
    public final byte[] d;
    public transient int e;
    public transient String f;

    public c(byte[] bArr) {
        this.d = bArr;
    }

    public static c a(String str) {
        c cVar = new c(str.getBytes(n.f5020a));
        cVar.f = str;
        return cVar;
    }

    public byte b(int i2) {
        return this.d[i2];
    }

    public String c() {
        byte[] bArr = this.d;
        char[] cArr = new char[(bArr.length * 2)];
        int i2 = 0;
        for (byte b : bArr) {
            int i7 = i2 + 1;
            char[] cArr2 = g;
            cArr[i2] = cArr2[(b >> 4) & 15];
            i2 += 2;
            cArr[i7] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public final int compareTo(Object obj) {
        c cVar = (c) obj;
        int g3 = g();
        int g10 = cVar.g();
        int min = Math.min(g3, g10);
        int i2 = 0;
        while (i2 < min) {
            byte b = b(i2) & 255;
            byte b5 = cVar.b(i2) & 255;
            if (b == b5) {
                i2++;
            } else if (b < b5) {
                return -1;
            } else {
                return 1;
            }
        }
        if (g3 == g10) {
            return 0;
        }
        if (g3 < g10) {
            return -1;
        }
        return 1;
    }

    public byte[] d() {
        return this.d;
    }

    public boolean e(int i2, int i7, int i8, byte[] bArr) {
        if (i2 >= 0) {
            byte[] bArr2 = this.d;
            if (i2 <= bArr2.length - i8 && i7 >= 0 && i7 <= bArr.length - i8) {
                Charset charset = n.f5020a;
                int i10 = 0;
                while (i10 < i8) {
                    if (bArr2[i10 + i2] == bArr[i10 + i7]) {
                        i10++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            int g3 = cVar.g();
            byte[] bArr = this.d;
            if (g3 != bArr.length || !cVar.e(0, 0, bArr.length, bArr)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean f(c cVar, int i2) {
        return cVar.e(0, 0, i2, this.d);
    }

    public int g() {
        return this.d.length;
    }

    public c h() {
        byte[] bArr = this.d;
        if (64 > bArr.length) {
            throw new IllegalArgumentException(C0086a.l(new StringBuilder("endIndex > length("), bArr.length, ")"));
        } else if (64 == bArr.length) {
            return this;
        } else {
            byte[] bArr2 = new byte[64];
            System.arraycopy(bArr, 0, bArr2, 0, 64);
            return new c(bArr2);
        }
    }

    public int hashCode() {
        int i2 = this.e;
        if (i2 != 0) {
            return i2;
        }
        int hashCode = Arrays.hashCode(this.d);
        this.e = hashCode;
        return hashCode;
    }

    public String j() {
        String str = this.f;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.d, n.f5020a);
        this.f = str2;
        return str2;
    }

    public String toString() {
        byte[] bArr = this.d;
        if (bArr.length == 0) {
            return "[size=0]";
        }
        String j2 = j();
        int length = j2.length();
        int i2 = 0;
        int i7 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = j2.length();
                break;
            } else if (i7 == 64) {
                break;
            } else {
                int codePointAt = j2.codePointAt(i2);
                if ((!Character.isISOControl(codePointAt) || codePointAt == 10 || codePointAt == 13) && codePointAt != 65533) {
                    i7++;
                    i2 += Character.charCount(codePointAt);
                }
            }
        }
        i2 = -1;
        if (i2 != -1) {
            String replace = j2.substring(0, i2).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            if (i2 >= j2.length()) {
                return C0212a.m("[text=", replace, "]");
            }
            return "[size=" + bArr.length + " text=" + replace + "…]";
        } else if (bArr.length <= 64) {
            return "[hex=" + c() + "]";
        } else {
            return "[size=" + bArr.length + " hex=" + h().c() + "…]";
        }
    }
}
