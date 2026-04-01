package pf;

import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {
    static {
        String str;
        try {
            str = System.getProperty("kotlin.jvm.serialization.use8to7");
        } catch (SecurityException unused) {
            str = null;
        }
        SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(str);
    }

    public static byte[] a(String[] strArr) {
        if (strArr != null) {
            if (strArr.length > 0 && !strArr[0].isEmpty()) {
                char charAt = strArr[0].charAt(0);
                if (charAt == 0) {
                    String[] strArr2 = (String[]) strArr.clone();
                    strArr2[0] = strArr2[0].substring(1);
                    int i2 = 0;
                    for (String length : strArr2) {
                        i2 += length.length();
                    }
                    byte[] bArr = new byte[i2];
                    int i7 = 0;
                    for (String str : strArr2) {
                        int length2 = str.length();
                        int i8 = 0;
                        while (i8 < length2) {
                            bArr[i7] = (byte) str.charAt(i8);
                            i8++;
                            i7++;
                        }
                    }
                    return bArr;
                } else if (charAt == 65535) {
                    strArr = (String[]) strArr.clone();
                    strArr[0] = strArr[0].substring(1);
                }
            }
            int i10 = 0;
            for (String length3 : strArr) {
                i10 += length3.length();
            }
            byte[] bArr2 = new byte[i10];
            int i11 = 0;
            for (String str2 : strArr) {
                int length4 = str2.length();
                int i12 = 0;
                while (i12 < length4) {
                    bArr2[i11] = (byte) str2.charAt(i12);
                    i12++;
                    i11++;
                }
            }
            for (int i13 = 0; i13 < i10; i13++) {
                bArr2[i13] = (byte) ((bArr2[i13] + Byte.MAX_VALUE) & 127);
            }
            int i14 = (i10 * 7) / 8;
            byte[] bArr3 = new byte[i14];
            int i15 = 0;
            byte b = 0;
            for (int i16 = 0; i16 < i14; i16++) {
                int i17 = i15 + 1;
                int i18 = b + 1;
                bArr3[i16] = (byte) (((bArr2[i15] & 255) >>> b) + ((bArr2[i17] & ((1 << i18) - 1)) << (7 - b)));
                if (b == 6) {
                    i15 += 2;
                    b = 0;
                } else {
                    i15 = i17;
                    b = i18;
                }
            }
            return bArr3;
        }
        Object[] objArr = new Object[3];
        objArr[0] = "data";
        objArr[1] = "kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/BitEncoding";
        switch (7) {
            case 1:
            case 3:
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
                break;
            case 2:
                objArr[2] = "encode8to7";
                break;
            case 4:
                objArr[2] = "addModuloByte";
                break;
            case 5:
                objArr[2] = "splitBytesToStringArray";
                break;
            case 7:
                objArr[2] = "decodeBytes";
                break;
            case 9:
                objArr[2] = "dropMarker";
                break;
            case 11:
                objArr[2] = "combineStringArrayIntoBytes";
                break;
            case 13:
                objArr[2] = "decode7to8";
                break;
            default:
                objArr[2] = "encodeBytes";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }
}
