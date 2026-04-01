package Q0;

import i.C0212a;
import java.io.Serializable;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements Serializable {
    public final transient int[] d;
    public final transient char[] e;
    public final transient byte[] f;
    public final String g;

    /* renamed from: h  reason: collision with root package name */
    public final char f608h;

    /* renamed from: i  reason: collision with root package name */
    public final int f609i;

    /* renamed from: j  reason: collision with root package name */
    public final boolean f610j;
    public final a k;

    public b(String str, String str2, boolean z, char c5, int i2) {
        int[] iArr = new int[128];
        this.d = iArr;
        char[] cArr = new char[64];
        this.e = cArr;
        this.f = new byte[64];
        this.g = str;
        this.f610j = z;
        this.f608h = c5;
        this.f609i = i2;
        int length = str2.length();
        if (length == 64) {
            str2.getChars(0, length, cArr, 0);
            Arrays.fill(iArr, -1);
            for (int i7 = 0; i7 < length; i7++) {
                char c6 = this.e[i7];
                this.f[i7] = (byte) c6;
                this.d[c6] = i7;
            }
            if (z) {
                this.d[c5] = -2;
            }
            this.k = z ? a.PADDING_REQUIRED : a.PADDING_FORBIDDEN;
            return;
        }
        throw new IllegalArgumentException(C0212a.j(length, "Base64Alphabet length must be exactly 64 (was ", ")"));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != b.class) {
            return false;
        }
        b bVar = (b) obj;
        if (bVar.f608h == this.f608h && bVar.f609i == this.f609i && bVar.f610j == this.f610j && bVar.k == this.k && this.g.equals(bVar.g)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.g.hashCode();
    }

    public final String toString() {
        return this.g;
    }

    public b(b bVar) {
        a aVar = bVar.k;
        int[] iArr = new int[128];
        this.d = iArr;
        char[] cArr = new char[64];
        this.e = cArr;
        byte[] bArr = new byte[64];
        this.f = bArr;
        this.g = "MIME-NO-LINEFEEDS";
        byte[] bArr2 = bVar.f;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        char[] cArr2 = bVar.e;
        System.arraycopy(cArr2, 0, cArr, 0, cArr2.length);
        int[] iArr2 = bVar.d;
        System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
        this.f610j = true;
        this.f608h = '=';
        this.f609i = Integer.MAX_VALUE;
        this.k = aVar;
    }
}
