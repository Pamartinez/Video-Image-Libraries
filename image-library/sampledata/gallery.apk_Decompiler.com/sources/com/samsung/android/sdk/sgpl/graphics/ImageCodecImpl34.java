package com.samsung.android.sdk.sgpl.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.samsung.android.media.SemBitmapFactory;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ImageCodecImpl34 extends ImageCodecImpl31 {
    public Bitmap decodeByteArray(byte[] bArr, int i2, int i7, BitmapFactory.Options options) {
        return SemBitmapFactory.decodeByteArray(bArr, i2, i7, options);
    }

    public Bitmap decodeFile(String str, BitmapFactory.Options options) {
        return SemBitmapFactory.decodeFile(str, options);
    }

    public Bitmap decodeHeifThumbnail(String str, int i2) {
        try {
            return SemBitmapFactory.decodeThumbnailFile(str, (BitmapFactory.Options) null);
        } catch (Error | Exception e) {
            String str2 = this.TAG;
            Log.w(str2, "decodeHeifThumbnail failed. e=" + e.getMessage());
            return null;
        }
    }

    public Bitmap decodeStream(InputStream inputStream, BitmapFactory.Options options) {
        return SemBitmapFactory.decodeStream(inputStream, options);
    }

    public Bitmap decodeHeifThumbnail(byte[] bArr, int i2, int i7, int i8) {
        try {
            return SemBitmapFactory.decodeThumbnailByteArray(bArr, i2, i7, (BitmapFactory.Options) null);
        } catch (Error | Exception e) {
            String str = this.TAG;
            Log.w(str, "decodeHeifThumbnail failed. e=" + e.getMessage());
            return null;
        }
    }
}
