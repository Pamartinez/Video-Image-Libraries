package R0;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f636a;
    public static final byte[] b;

    /* renamed from: c  reason: collision with root package name */
    public static final int[] f637c;
    public static final int[] d;

    static {
        int i2;
        char[] charArray = "0123456789ABCDEF".toCharArray();
        f636a = charArray;
        int length = charArray.length;
        b = new byte[length];
        for (int i7 = 0; i7 < length; i7++) {
            b[i7] = (byte) f636a[i7];
        }
        int[] iArr = new int[256];
        for (int i8 = 0; i8 < 32; i8++) {
            iArr[i8] = -1;
        }
        iArr[34] = 1;
        iArr[92] = 1;
        int[] iArr2 = new int[256];
        System.arraycopy(iArr, 0, iArr2, 0, 256);
        for (int i10 = 128; i10 < 256; i10++) {
            if ((i10 & 224) == 192) {
                i2 = 2;
            } else if ((i10 & 240) == 224) {
                i2 = 3;
            } else if ((i10 & 248) == 240) {
                i2 = 4;
            } else {
                i2 = -1;
            }
            iArr2[i10] = i2;
        }
        f637c = iArr2;
        int[] iArr3 = new int[256];
        Arrays.fill(iArr3, -1);
        for (int i11 = 33; i11 < 256; i11++) {
            if (Character.isJavaIdentifierPart((char) i11)) {
                iArr3[i11] = 0;
            }
        }
        iArr3[64] = 0;
        iArr3[35] = 0;
        iArr3[42] = 0;
        iArr3[45] = 0;
        iArr3[43] = 0;
        int[] iArr4 = new int[256];
        System.arraycopy(iArr3, 0, iArr4, 0, 256);
        Arrays.fill(iArr4, 128, 128, 0);
        int[] iArr5 = new int[256];
        int[] iArr6 = f637c;
        System.arraycopy(iArr6, 128, iArr5, 128, 128);
        Arrays.fill(iArr5, 0, 32, -1);
        iArr5[9] = 0;
        iArr5[10] = 10;
        iArr5[13] = 13;
        iArr5[42] = 42;
        int[] iArr7 = new int[256];
        System.arraycopy(iArr6, 128, iArr7, 128, 128);
        Arrays.fill(iArr7, 0, 32, -1);
        iArr7[32] = 1;
        iArr7[9] = 1;
        iArr7[10] = 10;
        iArr7[13] = 13;
        iArr7[47] = 47;
        iArr7[35] = 35;
        int[] iArr8 = new int[128];
        for (int i12 = 0; i12 < 32; i12++) {
            iArr8[i12] = -1;
        }
        iArr8[34] = 34;
        iArr8[92] = 92;
        iArr8[8] = 98;
        iArr8[9] = 116;
        iArr8[12] = 102;
        iArr8[10] = 110;
        iArr8[13] = 114;
        int[] iArr9 = new int[256];
        d = iArr9;
        Arrays.fill(iArr9, -1);
        for (int i13 = 0; i13 < 10; i13++) {
            d[i13 + 48] = i13;
        }
        for (int i14 = 0; i14 < 6; i14++) {
            int[] iArr10 = d;
            int i15 = i14 + 10;
            iArr10[i14 + 97] = i15;
            iArr10[i14 + 65] = i15;
        }
    }
}
