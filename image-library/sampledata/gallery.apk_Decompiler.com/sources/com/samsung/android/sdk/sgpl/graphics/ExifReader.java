package com.samsung.android.sdk.sgpl.graphics;

import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.ocr.MOCRLang;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExifReader {
    final ExifInterface exif;
    int orientationTag;

    public ExifReader(String str) {
        ExifInterface exifInterface;
        try {
            exifInterface = new ExifInterface(str);
        } catch (Exception unused) {
            exifInterface = null;
        }
        this.exif = exifInterface;
    }

    public static int toOrientation(int i2) {
        switch (i2) {
            case 3:
            case 4:
                return MOCRLang.KHMER;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public String getAttribute(String str, String str2) {
        ExifInterface exifInterface = this.exif;
        if (exifInterface == null) {
            return str2;
        }
        return exifInterface.getAttribute(str);
    }

    public int getAttributeInt(String str, int i2) {
        ExifInterface exifInterface = this.exif;
        if (exifInterface == null) {
            return i2;
        }
        return exifInterface.getAttributeInt(str, i2);
    }

    public int getOrientationTag() {
        if (this.orientationTag == 0) {
            this.orientationTag = getAttributeInt("Orientation", 1);
        }
        return this.orientationTag;
    }

    public ExifReader(byte[] bArr, int i2, int i7) {
        ExifInterface exifInterface;
        ByteArrayInputStream byteArrayInputStream;
        ExifInterface exifInterface2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr, i2, i7);
            exifInterface = new ExifInterface((InputStream) byteArrayInputStream);
            try {
                byteArrayInputStream.close();
            } catch (Exception unused) {
                exifInterface2 = exifInterface;
            }
        } catch (Exception unused2) {
            exifInterface = exifInterface2;
            this.exif = exifInterface;
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        this.exif = exifInterface;
        return;
        throw th;
    }
}
