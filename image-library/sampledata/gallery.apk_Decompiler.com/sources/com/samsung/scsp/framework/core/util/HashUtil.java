package com.samsung.scsp.framework.core.util;

import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.scsp.framework.core.ScspException;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HashUtil {
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final String SHA256 = "SHA-256";

    private static byte[] decodeHex(char[] cArr) {
        int length = cArr.length;
        if ((length & 1) == 0) {
            byte[] bArr = new byte[(length >> 1)];
            int i2 = 0;
            int i7 = 0;
            while (i2 < length) {
                int i8 = i2 + 1;
                i2 += 2;
                bArr[i7] = (byte) (((toDigit(cArr[i2], i2) << 4) | toDigit(cArr[i8], i8)) & ScoverState.TYPE_NFC_SMART_COVER);
                i7++;
            }
            return bArr;
        }
        throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "Odd number of characters.");
    }

    private static char[] encodeHex(byte[] bArr) {
        return encodeHex(bArr, true);
    }

    private static String encodeHexString(byte[] bArr) {
        return new String(encodeHex(bArr));
    }

    public static String generateValidationKey(String str, String str2) {
        if (!StringUtil.isEmpty(str2)) {
            return encodeHexString(xor(decodeHex(str.toCharArray()), sha256(str2.split(" ")[1])));
        }
        throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "Access token is null or empty. please check access token.");
    }

    public static String getByteArraySHA256(byte[] bArr) {
        return encodeHexString(sha256(bArr));
    }

    private static MessageDigest getDigest(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String getFileSHA256(InputStream inputStream) {
        MessageDigest digest = getDigest(SHA256);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, 1024);
            if (read <= 0) {
                return encodeHexString(digest.digest());
            }
            digest.update(bArr, 0, read);
        }
    }

    public static String getStringSHA256(String str) {
        return encodeHexString(sha256(str));
    }

    private static byte[] sha256(byte[] bArr) {
        return getDigest(SHA256).digest(bArr);
    }

    private static int toDigit(char c5, int i2) {
        int digit = Character.digit(c5, 16);
        if (digit != -1) {
            return digit;
        }
        throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "Illegal hexadecimal character " + c5 + " at index " + i2);
    }

    private static byte[] xor(byte[] bArr, byte[] bArr2) {
        if (bArr.length == bArr2.length) {
            byte[] bArr3 = new byte[bArr.length];
            for (int i2 = 0; i2 < bArr.length; i2++) {
                bArr3[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
            }
            return bArr3;
        }
        throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "Lengths are different.");
    }

    private static char[] encodeHex(byte[] bArr, boolean z) {
        return encodeHex(bArr, z ? DIGITS_LOWER : DIGITS_UPPER);
    }

    private static byte[] sha256(String str) {
        return sha256(str.getBytes(StandardCharsets.UTF_8));
    }

    private static char[] encodeHex(byte[] bArr, char[] cArr) {
        char[] cArr2 = new char[(r0 << 1)];
        int i2 = 0;
        for (byte b : bArr) {
            int i7 = i2 + 1;
            cArr2[i2] = cArr[(b & 240) >>> 4];
            i2 += 2;
            cArr2[i7] = cArr[b & 15];
        }
        return cArr2;
    }

    public static String getFileSHA256(String str) {
        return getFileSHA256(new File(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFileSHA256(java.io.File r2) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0011 }
            r1.<init>(r2)     // Catch:{ all -> 0x0011 }
            java.lang.String r2 = getFileSHA256((java.io.InputStream) r1)     // Catch:{ all -> 0x000e }
            r1.close()
            return r2
        L_0x000e:
            r2 = move-exception
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r2 = move-exception
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.scsp.framework.core.util.HashUtil.getFileSHA256(java.io.File):java.lang.String");
    }
}
