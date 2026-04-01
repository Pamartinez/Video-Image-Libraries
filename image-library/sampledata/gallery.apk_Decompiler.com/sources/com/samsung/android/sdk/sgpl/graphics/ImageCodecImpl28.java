package com.samsung.android.sdk.sgpl.graphics;

import N2.j;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.media.SemHEIFCodec;
import com.sec.samsung.gallery.decoder.QuramCodecInterface;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ImageCodecImpl28 extends ImageCodecImpl {
    static final int DATA_JPEG = 6;
    static final int DATA_JPEG_XL = 52546;
    static final int DATA_LOSSLESS = 7;
    static final int DATA_UNCOMPRESSED = 1;
    static final String SAMSUNG_DNG = "samsung";
    static final String SAMSUNG_DNG_FS = "samsung:fullSizeJpeg";
    static final String SAMSUNG_DNG_V2 = "samsung:v2";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class QuramCodecHolder {
        static volatile boolean sQuramEnabled = QuramCodecInterface.loadLibrary();
    }

    public Bitmap decodeByteArray(byte[] bArr, int i2, int i7, BitmapFactory.Options options) {
        if (!QuramCodecHolder.sQuramEnabled) {
            return null;
        }
        try {
            return QuramCodecInterface.nativeDecodeByteArray(bArr, i2, i7, options);
        } catch (UnsatisfiedLinkError e) {
            String str = this.TAG;
            Log.e(str, "decodeByteArray failed. e=" + e.getMessage());
            QuramCodecHolder.sQuramEnabled = false;
            return null;
        }
    }

    public Bitmap decodeDngThumbnail(String str, int i2) {
        if (!QuramCodecHolder.sQuramEnabled) {
            return null;
        }
        try {
            return QuramCodecInterface.nativeDecodePreview(str);
        } catch (UnsatisfiedLinkError e) {
            QuramCodecHolder.sQuramEnabled = false;
            String str2 = this.TAG;
            Log.e(str2, "decodeDngThumbnail failed. e=" + e.getMessage());
            return null;
        }
    }

    public Bitmap decodeFile(String str, BitmapFactory.Options options) {
        if (!QuramCodecHolder.sQuramEnabled) {
            return null;
        }
        try {
            return QuramCodecInterface.nativeDecodeFile(str, options);
        } catch (UnsatisfiedLinkError e) {
            String str2 = this.TAG;
            Log.e(str2, "decodeFile failed. e=" + e.getMessage());
            QuramCodecHolder.sQuramEnabled = false;
            return null;
        }
    }

    public Bitmap decodeHeifThumbnail(String str, int i2) {
        try {
            return SemHEIFCodec.getThumbnail(str, (BitmapFactory.Options) null);
        } catch (Exception e) {
            String str2 = this.TAG;
            Log.w(str2, "decodeHeifThumbnail failed. e=" + e.getMessage());
            return null;
        }
    }

    public String parseDngVersion(String str) {
        try {
            ExifInterface exifInterface = new ExifInterface(str);
            if (SAMSUNG_DNG.equalsIgnoreCase(exifInterface.getAttribute("Make"))) {
                String attribute = exifInterface.getAttribute("Compression");
                if (TextUtils.isEmpty(attribute)) {
                    return "";
                }
                return parseDngVersion(Integer.parseInt(attribute));
            }
        } catch (Error | Exception e) {
            Log.e(this.TAG, j.i(e, new StringBuilder("parseDngVersion failed. e=")));
        }
        return "";
    }

    public Bitmap decodeHeifThumbnail(byte[] bArr, int i2, int i7, int i8) {
        try {
            return SemHEIFCodec.getThumbnail(bArr, i2, i7, (BitmapFactory.Options) null);
        } catch (Exception e) {
            String str = this.TAG;
            Log.w(str, "decodeHeifThumbnail(byte) failed. e=" + e.getMessage());
            return null;
        }
    }

    public String parseDngVersion(byte[] bArr, int i2, int i7) {
        ByteArrayInputStream byteArrayInputStream;
        String str;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr, i2, i7);
            ExifInterface exifInterface = new ExifInterface((InputStream) byteArrayInputStream);
            if (SAMSUNG_DNG.equalsIgnoreCase(exifInterface.getAttribute("Make"))) {
                String attribute = exifInterface.getAttribute("Compression");
                if (TextUtils.isEmpty(attribute)) {
                    str = "";
                } else {
                    str = parseDngVersion(Integer.parseInt(attribute));
                }
                byteArrayInputStream.close();
                return str;
            }
            byteArrayInputStream.close();
            return "";
        } catch (Error | Exception e) {
            Log.e(this.TAG, j.i(e, new StringBuilder("parseDngVersion failed. e=")));
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String parseDngVersion(int i2) {
        return i2 == 1 ? SAMSUNG_DNG : "";
    }
}
