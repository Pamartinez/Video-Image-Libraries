package com.adobe.internal.xmp.impl;

import java.io.UnsupportedEncodingException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Latin1Converter {
    private static final int STATE_START = 0;
    private static final int STATE_UTF8CHAR = 11;

    private Latin1Converter() {
    }

    public static ByteBuffer convert(ByteBuffer byteBuffer) {
        if (!"UTF-8".equals(byteBuffer.getEncoding())) {
            return byteBuffer;
        }
        byte[] bArr = new byte[8];
        ByteBuffer byteBuffer2 = new ByteBuffer((byteBuffer.length() * 4) / 3);
        int i2 = 0;
        boolean z = false;
        int i7 = 0;
        int i8 = 0;
        while (i2 < byteBuffer.length()) {
            int charAt = byteBuffer.charAt(i2);
            if (z) {
                if (i7 <= 0 || (charAt & 192) != 128) {
                    byteBuffer2.append(convertToUTF8(bArr[0]));
                    i2 -= i8;
                } else {
                    int i10 = i8 + 1;
                    bArr[i8] = (byte) charAt;
                    i7--;
                    if (i7 == 0) {
                        byteBuffer2.append(bArr, 0, i10);
                    } else {
                        i8 = i10;
                    }
                }
                z = false;
                i8 = 0;
            } else if (charAt < 127) {
                byteBuffer2.append((byte) charAt);
            } else if (charAt >= 192) {
                i7 = -1;
                int i11 = charAt;
                while (i7 < 8 && (i11 & 128) == 128) {
                    i7++;
                    i11 <<= 1;
                }
                bArr[i8] = (byte) charAt;
                i8++;
                z = true;
            } else {
                byteBuffer2.append(convertToUTF8((byte) charAt));
            }
            i2++;
        }
        if (z) {
            for (int i12 = 0; i12 < i8; i12++) {
                byteBuffer2.append(convertToUTF8(bArr[i12]));
            }
        }
        return byteBuffer2;
    }

    private static byte[] convertToUTF8(byte b) {
        byte b5 = b & 255;
        if (b5 >= 128) {
            if (b5 == 129 || b5 == 141 || b5 == 143 || b5 == 144 || b5 == 157) {
                return new byte[]{32};
            }
            try {
                return new String(new byte[]{b}, "cp1252").getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return new byte[]{b};
    }
}
