package U2;

import Nf.f;
import T2.c;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class d extends f implements g {
    public final Object[] e;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public d(int r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x0004
            r0 = 1
            goto L_0x0005
        L_0x0004:
            r0 = 0
        L_0x0005:
            r1.<init>(r0)
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ NegativeArraySizeException -> 0x000d }
            r1.e = r2     // Catch:{ NegativeArraySizeException -> 0x000d }
            return
        L_0x000d:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "size < 0"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: U2.d.<init>(int):void");
    }

    public String a() {
        String name = getClass().getName();
        return f(name.substring(name.lastIndexOf(46) + 1) + '{', "}", true);
    }

    public final Object d(int i2) {
        try {
            Object obj = this.e[i2];
            if (obj != null) {
                return obj;
            }
            throw new NullPointerException("unset: " + i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            if (i2 < 0) {
                throw new IndexOutOfBoundsException("n < 0");
            }
            throw new IndexOutOfBoundsException("n >= size()");
        }
    }

    public final void e(int i2, Object obj) {
        c();
        try {
            this.e[i2] = obj;
        } catch (ArrayIndexOutOfBoundsException unused) {
            if (i2 < 0) {
                throw new IndexOutOfBoundsException("n < 0");
            }
            throw new IndexOutOfBoundsException("n >= size()");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.e, ((d) obj).e);
    }

    public final String f(String str, String str2, boolean z) {
        Object[] objArr = this.e;
        int length = objArr.length;
        StringBuffer stringBuffer = new StringBuffer((length * 10) + 10);
        if (str != null) {
            stringBuffer.append(str);
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != 0) {
                stringBuffer.append(ArcCommonLog.TAG_COMMA);
            }
            if (z) {
                stringBuffer.append(((g) objArr[i2]).a());
            } else {
                stringBuffer.append(objArr[i2]);
            }
        }
        if (str2 != null) {
            stringBuffer.append(str2);
        }
        return stringBuffer.toString();
    }

    public c getType(int i2) {
        return (c) d(i2);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.e);
    }

    public final String toString() {
        String name = getClass().getName();
        return f(name.substring(name.lastIndexOf(46) + 1) + '{', "}", false);
    }
}
