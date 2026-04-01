package H2;

import He.F;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends d {
    public c(String str, String str2) {
        this(new a(str, str2.toCharArray()), (Character) '=');
    }

    public final void a(Appendable appendable, byte[] bArr, int i2) {
        a aVar;
        boolean z;
        int i7 = 0;
        F.p(0, i2, bArr.length);
        int i8 = i2;
        int i10 = 0;
        while (true) {
            aVar = this.f339a;
            if (i8 < 3) {
                break;
            }
            int i11 = i10 + 2;
            int i12 = (bArr[i10 + 1] & 255) << 8;
            i10 += 3;
            byte b = i12 | ((bArr[i10] & 255) << 16) | (bArr[i11] & 255);
            char[] cArr = aVar.b;
            char[] cArr2 = aVar.b;
            char c5 = cArr[b >>> 18];
            StringBuilder sb2 = (StringBuilder) appendable;
            sb2.append(c5);
            sb2.append(cArr2[(b >>> 12) & 63]);
            sb2.append(cArr2[(b >>> 6) & 63]);
            sb2.append(cArr2[b & 63]);
            i8 -= 3;
        }
        if (i10 < i2) {
            int i13 = i2 - i10;
            StringBuilder sb3 = (StringBuilder) appendable;
            F.p(i10, i10 + i13, bArr.length);
            int i14 = aVar.f;
            int i15 = aVar.d;
            if (i13 <= i14) {
                z = true;
            } else {
                z = false;
            }
            F.j(z);
            long j2 = 0;
            for (int i16 = 0; i16 < i13; i16++) {
                j2 = (j2 | ((long) (bArr[i10 + i16] & 255))) << 8;
            }
            int i17 = ((i13 + 1) * 8) - i15;
            while (i7 < i13 * 8) {
                sb3.append(aVar.b[((int) (j2 >>> (i17 - i7))) & aVar.f337c]);
                i7 += i15;
            }
            Character ch = this.b;
            if (ch != null) {
                while (i7 < aVar.f * 8) {
                    sb3.append(ch.charValue());
                    i7 += i15;
                }
            }
        }
    }

    public c(a aVar, Character ch) {
        super(aVar, ch);
        F.j(aVar.b.length == 64);
    }
}
