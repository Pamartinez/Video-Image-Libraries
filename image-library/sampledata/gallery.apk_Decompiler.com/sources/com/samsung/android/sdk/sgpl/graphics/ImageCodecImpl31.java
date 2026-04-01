package com.samsung.android.sdk.sgpl.graphics;

import N2.j;
import android.graphics.Bitmap;
import com.samsung.android.sdk.sgpl.graphics.ImageCodecImpl28;
import com.sec.samsung.gallery.decoder.QuramCodecInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ImageCodecImpl31 extends ImageCodecImpl30 {
    public Bitmap decodeDngThumbnail(String str, int i2) {
        if (ImageCodecImpl28.QuramCodecHolder.sQuramEnabled) {
            try {
                byte[] nativeGetDNGPreviewImageFromFile = QuramCodecInterface.nativeGetDNGPreviewImageFromFile(str);
                if (nativeGetDNGPreviewImageFromFile != null && nativeGetDNGPreviewImageFromFile.length > 0) {
                    Bitmap decodeThumbnail = decodeThumbnail(nativeGetDNGPreviewImageFromFile, 0, nativeGetDNGPreviewImageFromFile.length, i2);
                    if (decodeThumbnail != null) {
                        return decodeThumbnail;
                    }
                    return decodeEmbeddedThumbnail(nativeGetDNGPreviewImageFromFile, i2);
                }
            } catch (Error | Exception e) {
                Log.e(this.TAG, j.i(e, new StringBuilder("decodeDngThumbnail failed. e=")));
            }
        }
        return super.decodeDngThumbnail(str, i2);
    }

    public String parseDngVersion(int i2) {
        if (i2 == 7 || i2 == 52546) {
            return "samsung:v2";
        }
        return super.parseDngVersion(i2);
    }

    public Bitmap decodeDngThumbnail(byte[] bArr, int i2, int i7, int i8) {
        if (ImageCodecImpl28.QuramCodecHolder.sQuramEnabled) {
            try {
                byte[] nativeGetDNGPreviewImageFromByteArray = QuramCodecInterface.nativeGetDNGPreviewImageFromByteArray(bArr, i2, i7);
                if (nativeGetDNGPreviewImageFromByteArray != null && nativeGetDNGPreviewImageFromByteArray.length > 0) {
                    return decodeEmbeddedThumbnail(bArr, i8);
                }
            } catch (Error | Exception e) {
                Log.e(this.TAG, j.i(e, new StringBuilder("decodeDngThumbnail failed. e=")));
            }
        }
        return super.decodeDngThumbnail(bArr, i2, i7, i8);
    }
}
