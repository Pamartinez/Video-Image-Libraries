package mg;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f4923a = new char[117];
    public static final byte[] b = new byte[126];

    static {
        for (int i2 = 0; i2 < 32; i2++) {
        }
        a(8, 'b');
        a(9, 't');
        a(10, 'n');
        a(12, 'f');
        a(13, 'r');
        a(47, '/');
        a(34, '\"');
        a(92, '\\');
        byte[] bArr = b;
        for (int i7 = 0; i7 < 33; i7++) {
            bArr[i7] = Byte.MAX_VALUE;
        }
        bArr[9] = 3;
        bArr[10] = 3;
        bArr[13] = 3;
        bArr[32] = 3;
        bArr[44] = 4;
        bArr[58] = 5;
        bArr[123] = 6;
        bArr[125] = 7;
        bArr[91] = 8;
        bArr[93] = 9;
        bArr[34] = 1;
        bArr[92] = 2;
    }

    public static void a(int i2, char c5) {
        if (c5 != 'u') {
            f4923a[c5] = (char) i2;
        }
    }
}
