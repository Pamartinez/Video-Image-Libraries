package H2;

import He.F;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends d {
    public final char[] d = new char[512];

    public b(a aVar) {
        super(aVar, (Character) null);
        boolean z;
        char[] cArr = aVar.b;
        if (cArr.length == 16) {
            z = true;
        } else {
            z = false;
        }
        F.j(z);
        for (int i2 = 0; i2 < 256; i2++) {
            char[] cArr2 = this.d;
            cArr2[i2] = cArr[i2 >>> 4];
            cArr2[i2 | 256] = cArr[i2 & 15];
        }
    }
}
