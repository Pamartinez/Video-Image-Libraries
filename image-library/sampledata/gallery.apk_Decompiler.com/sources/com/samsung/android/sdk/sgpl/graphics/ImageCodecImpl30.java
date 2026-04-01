package com.samsung.android.sdk.sgpl.graphics;

import N2.j;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.samsung.android.media.SemHEIFCodec;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ImageCodecImpl30 extends ImageCodecImpl28 {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HeifCodec {
        static final SemHeifCodec instance = new SemHeifCodec();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SemHeifCodec extends ImageCodecImpl {
        public Bitmap decodeByteArray(byte[] bArr, int i2, int i7, BitmapFactory.Options options) {
            try {
                return SemHEIFCodec.decodeByteArray(bArr, i2, i7, options);
            } catch (Error | Exception e) {
                Log.e(this.TAG, j.i(e, new StringBuilder("decodeByteArray failed. e=")));
                return null;
            }
        }

        public Bitmap decodeFile(String str, BitmapFactory.Options options) {
            try {
                return SemHEIFCodec.decodeFile(str, options);
            } catch (Error | Exception e) {
                Log.e(this.TAG, j.i(e, new StringBuilder("decodeFile failed. e=")));
                return null;
            }
        }

        public Bitmap decodeStream(InputStream inputStream, BitmapFactory.Options options) {
            try {
                return SemHEIFCodec.decodeStream(inputStream, options);
            } catch (Error | Exception e) {
                Log.e(this.TAG, j.i(e, new StringBuilder("decodeStream failed. e=")));
                return null;
            }
        }
    }

    public Bitmap decodeByteArray(byte[] bArr, int i2, int i7, BitmapFactory.Options options) {
        if (isHeif(options)) {
            return HeifCodec.instance.decodeByteArray(bArr, i2, i7, options);
        }
        return super.decodeByteArray(bArr, i2, i7, options);
    }

    public Bitmap decodeFile(String str, BitmapFactory.Options options) {
        if (isHeif(options)) {
            return HeifCodec.instance.decodeFile(str, options);
        }
        return super.decodeFile(str, options);
    }

    public Bitmap decodeStream(InputStream inputStream, BitmapFactory.Options options) {
        if (isHeif(options)) {
            return HeifCodec.instance.decodeStream(inputStream, options);
        }
        return super.decodeStream(inputStream, options);
    }
}
