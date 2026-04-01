package com.samsung.o3dp.lib3dphotography.utils;

import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileExtensionParser {
    private static final int HEADER_BYTES_TO_READ = 64;
    private static final String[] HEIF_BRANDS = {IFormat.FORMAT_HEIC, "heif", "heix", "mif1"};
    private static final byte JPEG_SOI_FIRST = -1;
    private static final byte JPEG_SOI_SECOND = -40;
    private static final String[] MP4_BRANDS = {"mp41", "mp42", "isom", "iso2", "iso5", "iso6", "mpg4"};

    private static SupportedExtension detectExtensionByBrand(byte[] bArr) {
        if (bArr.length >= 16) {
            String normalizeBrand = normalizeBrand(bArr, 8);
            if (isHeifBrand(normalizeBrand)) {
                return SupportedExtension.HEIF;
            }
            if (isMp4Brand(normalizeBrand)) {
                return SupportedExtension.MP4;
            }
            throw new IllegalArgumentException("Failed to get extension even file is IsoBmff format");
        }
        throw new IllegalArgumentException("Not enough header length to detect brand");
    }

    public static SupportedExtension getSupportedExtension(String str) {
        byte[] readFileHeader = readFileHeader(str);
        if (readFileHeader.length < 2) {
            throw new IllegalArgumentException("Not enough header length to get supported extension");
        } else if (isJpeg(readFileHeader)) {
            return SupportedExtension.JPEG;
        } else {
            if (readFileHeader.length < 12) {
                throw new IllegalArgumentException("Not enough header length to get supported extension");
            } else if (isIsoBmff(readFileHeader)) {
                return detectExtensionByBrand(readFileHeader);
            } else {
                throw new IllegalArgumentException("Not supported file format (or wrong extension)");
            }
        }
    }

    private static boolean isHeifBrand(String str) {
        return matchesAny(str, HEIF_BRANDS);
    }

    private static boolean isIsoBmff(byte[] bArr) {
        if (bArr.length >= 12 && bArr[4] == 102 && bArr[5] == 116 && bArr[6] == 121 && bArr[7] == 112) {
            return true;
        }
        return false;
    }

    private static boolean isJpeg(byte[] bArr) {
        if (bArr.length >= 2 && (bArr[0] & JPEG_SOI_FIRST) == 255 && (bArr[1] & JPEG_SOI_FIRST) == 216) {
            return true;
        }
        return false;
    }

    private static boolean isMp4Brand(String str) {
        return matchesAny(str, MP4_BRANDS);
    }

    private static boolean matchesAny(String str, String[] strArr) {
        if (str.isEmpty()) {
            return false;
        }
        for (String equals : strArr) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private static String normalizeBrand(byte[] bArr, int i2) {
        String str = new String(bArr, i2, 4, StandardCharsets.US_ASCII);
        int indexOf = str.indexOf(0);
        if (indexOf >= 0) {
            str = str.substring(0, indexOf);
        }
        return str.toLowerCase(Locale.US);
    }

    private static byte[] readFileHeader(String str) {
        byte[] bArr = new byte[64];
        FileInputStream fileInputStream = new FileInputStream(str);
        int i2 = 0;
        while (i2 < 64) {
            try {
                int read = fileInputStream.read(bArr, i2, 64 - i2);
                if (read <= 0) {
                    break;
                }
                i2 += read;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        fileInputStream.close();
        byte[] bArr2 = new byte[i2];
        if (i2 > 0) {
            System.arraycopy(bArr, 0, bArr2, 0, i2);
        }
        return bArr2;
        throw th;
    }
}
