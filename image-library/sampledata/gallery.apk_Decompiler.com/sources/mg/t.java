package mg;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class t {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f4949a;

    static {
        String[] strArr = new String[93];
        for (int i2 = 0; i2 < 32; i2++) {
            strArr[i2] = "\\u" + b(i2 >> 12) + b(i2 >> 8) + b(i2 >> 4) + b(i2);
        }
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        f4949a = strArr;
        byte[] bArr = new byte[93];
        for (int i7 = 0; i7 < 32; i7++) {
            bArr[i7] = 1;
        }
        bArr[34] = 34;
        bArr[92] = 92;
        bArr[9] = 116;
        bArr[8] = 98;
        bArr[10] = 110;
        bArr[13] = 114;
        bArr[12] = 102;
    }

    public static final void a(StringBuilder sb2, String str) {
        j.e(str, "value");
        sb2.append('\"');
        int length = str.length();
        int i2 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            char charAt = str.charAt(i7);
            String[] strArr = f4949a;
            if (charAt < strArr.length && strArr[charAt] != null) {
                sb2.append(str, i2, i7);
                sb2.append(strArr[charAt]);
                i2 = i7 + 1;
            }
        }
        if (i2 != 0) {
            sb2.append(str, i2, str.length());
        } else {
            sb2.append(str);
        }
        sb2.append('\"');
    }

    public static final char b(int i2) {
        int i7;
        int i8 = i2 & 15;
        if (i8 < 10) {
            i7 = i8 + 48;
        } else {
            i7 = i8 + 87;
        }
        return (char) i7;
    }
}
