package R2;

import Nf.f;
import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends f {
    public final k[] e;
    public int f;

    static {
        new m(0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public m(int r3) {
        /*
            r2 = this;
            r0 = 0
            if (r3 == 0) goto L_0x0005
            r1 = 1
            goto L_0x0006
        L_0x0005:
            r1 = r0
        L_0x0006:
            r2.<init>(r1)
            R2.k[] r3 = new R2.k[r3]
            r2.e = r3
            r2.f = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: R2.m.<init>(int):void");
    }

    public final k d(int i2) {
        try {
            return this.e[i2];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("bogus reg");
        }
    }

    public final int e() {
        int i2 = this.f;
        if (i2 >= 0) {
            return i2;
        }
        int i7 = 0;
        for (k kVar : this.e) {
            if (kVar != null) {
                i7++;
            }
        }
        this.f = i7;
        return i7;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        k[] kVarArr = mVar.e;
        k[] kVarArr2 = this.e;
        int length = kVarArr2.length;
        if (length != kVarArr.length || e() != mVar.e()) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            k kVar = kVarArr2[i2];
            k kVar2 = kVarArr[i2];
            if (kVar != kVar2 && (kVar == null || !kVar.equals(kVar2))) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i2;
        int i7 = 0;
        for (k kVar : this.e) {
            if (kVar == null) {
                i2 = 0;
            } else {
                i2 = kVar.hashCode();
            }
            i7 = (i7 * 31) + i2;
        }
        return i7;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer(r0 * 25);
        stringBuffer.append('{');
        boolean z = false;
        for (k kVar : this.e) {
            if (kVar != null) {
                if (z) {
                    stringBuffer.append(ArcCommonLog.TAG_COMMA);
                } else {
                    z = true;
                }
                stringBuffer.append(kVar);
            }
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
