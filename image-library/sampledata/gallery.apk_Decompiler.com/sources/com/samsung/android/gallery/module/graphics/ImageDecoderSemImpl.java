package com.samsung.android.gallery.module.graphics;

import A.a;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ImageDecoderSemImpl extends ImageDecoderImpl {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class QuramLibHolder {
        static final boolean QURAM_ENABLED = QuramBitmapFactory.isEnabled();
    }

    public Bitmap decodeByteArray(byte[] bArr, int i2, int i7, BitmapOptions bitmapOptions) {
        if (QuramLibHolder.QURAM_ENABLED && bitmapOptions.inPreferredQuramCodec) {
            try {
                if (CodecCompat.PATCH_JPEG_PROGRESSIVE && !CodecCompat.ensureJpegSyntaxCompatible(bArr, i2, i7, bitmapOptions)) {
                    return BitmapFactory.decodeByteArray(bArr, i2, i7, bitmapOptions);
                }
                Bitmap decodeByteArray = QuramBitmapFactory.decodeByteArray(bArr, i2, i7, bitmapOptions);
                if (decodeByteArray != null) {
                    return decodeByteArray;
                }
            } catch (Error | Exception e) {
                a.z(e, new StringBuilder("decodeByteArray failed. e="), this.TAG);
            }
        }
        return super.decodeByteArray(bArr, i2, i7, bitmapOptions);
    }

    public Bitmap decodeFile(String str, BitmapOptions bitmapOptions) {
        Bitmap decodeFile;
        byte[] readByteArray;
        try {
            if (bitmapOptions.inPreferredHeifCodec) {
                return HeifBitmapFactory.decodeFile(str, bitmapOptions);
            }
            File file = new File(str);
            int length = (int) file.length();
            if ((bitmapOptions.inPreferredJavaHeap || length < 5242880) && (readByteArray = readByteArray(file)) != null && readByteArray.length > 0) {
                Bitmap decodeByteArray = decodeByteArray(readByteArray, 0, length, bitmapOptions);
                if (decodeByteArray != null) {
                    return decodeByteArray;
                }
                String str2 = this.TAG;
                Log.e(str2, "decodeFile(heap) failed " + bitmapOptions + ArcCommonLog.TAG_COMMA + StringCompat.valueOf(readByteArray, 16));
            }
            if (QuramLibHolder.QURAM_ENABLED && bitmapOptions.inPreferredQuramCodec && (decodeFile = QuramBitmapFactory.decodeFile(str, bitmapOptions)) != null) {
                return decodeFile;
            }
            return super.decodeFile(str, bitmapOptions);
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("decodeFile failed. e="), this.TAG);
        }
    }
}
