package J2;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f367a;

    static {
        byte[] bArr = new byte[128];
        Arrays.fill(bArr, (byte) -1);
        for (int i2 = 0; i2 < 10; i2++) {
            bArr[i2 + 48] = (byte) i2;
        }
        for (int i7 = 0; i7 < 26; i7++) {
            byte b = (byte) (i7 + 10);
            bArr[i7 + 65] = b;
            bArr[i7 + 97] = b;
        }
        f367a = bArr;
    }
}
