package h1;

import V0.c;
import c0.C0086a;
import i1.a;
import java.io.Serializable;
import java.util.Iterator;

/* renamed from: h1.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0204d implements Serializable {
    public static final C0204d f = new C0204d(new String[0], new c[0]);
    public final c[] d;
    public final int e;

    public C0204d(String[] strArr, c[] cVarArr) {
        this.d = cVarArr;
        if (strArr.length == cVarArr.length) {
            int length = cVarArr.length;
            int i2 = 1;
            for (int i7 = 0; i7 < length; i7++) {
                i2 += this.d[i7].f866h;
            }
            this.e = i2;
            return;
        }
        StringBuilder sb2 = new StringBuilder("Mismatching names (");
        sb2.append(strArr.length);
        sb2.append("), types (");
        throw new IllegalArgumentException(C0086a.l(sb2, cVarArr.length, ")"));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        Iterator it = a.f1782a;
        if (obj == null || obj.getClass() != C0204d.class) {
            return false;
        }
        c[] cVarArr = this.d;
        int length = cVarArr.length;
        c[] cVarArr2 = ((C0204d) obj).d;
        if (length == cVarArr2.length) {
            int i2 = 0;
            while (i2 < length) {
                if (cVarArr2[i2].equals(cVarArr[i2])) {
                    i2++;
                }
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.e;
    }

    public final String toString() {
        c[] cVarArr = this.d;
        if (cVarArr.length == 0) {
            return "<>";
        }
        StringBuilder sb2 = new StringBuilder("<");
        int length = cVarArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 > 0) {
                sb2.append(',');
            }
            c cVar = cVarArr[i2];
            StringBuilder sb3 = new StringBuilder(40);
            cVar.Y(sb3);
            sb2.append(sb3.toString());
        }
        sb2.append('>');
        return sb2.toString();
    }
}
