package com.samsung.android.gallery.module.graphics;

import Wc.a;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.sgpl.graphics.CodecConfig;
import com.samsung.android.sdk.sgpl.graphics.ImageCodec;
import com.samsung.android.sdk.sgpl.graphics.VideoCodec;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageDecoderCompat {
    final CodecConfig codecConfig;
    final ImageCodec imageCodec;
    final VideoCodec videoCodec;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CustomCodecImpl implements CodecConfig.CustomCodec {
        private final String TAG = "ImageCodec";

        public boolean decodeByteArray(byte[] bArr, int i2, int i7, BitmapFactory.Options options) {
            if (!(options instanceof BitmapOptions)) {
                return false;
            }
            BitmapOptions bitmapOptions = (BitmapOptions) options;
            if (CodecCompat.PATCH_JPEG_PROGRESSIVE && !bitmapOptions.inPreferredAndroidCodec && !CodecCompat.ensureJpegSyntaxCompatible(bArr, i2, i7, bitmapOptions)) {
                bitmapOptions.inPreferredAndroidCodec = true;
                Log.w("ImageCodec", "unsupported jpeg-syntax");
            }
            if (!bitmapOptions.inPreferredAndroidCodec) {
                return false;
            }
            try {
                options.inBitmap = BitmapFactory.decodeByteArray(bArr, i2, i7, options);
            } catch (Error | Exception e) {
                options.inBitmap = null;
                Log.w("ImageCodec", "decodeByteArray(AOSP) failed. e=" + e.getMessage());
            }
            return true;
        }

        public boolean decodeFile(String str, BitmapFactory.Options options) {
            if (!(options instanceof BitmapOptions) || !((BitmapOptions) options).inPreferredAndroidCodec) {
                return false;
            }
            try {
                options.inBitmap = BitmapFactory.decodeFile(str, options);
                return true;
            } catch (Error | Exception e) {
                options.inBitmap = null;
                Log.w("ImageCodec", "decodeFile(AOSP) failed. e=" + e.getMessage());
                return true;
            }
        }
    }

    public ImageDecoderCompat() {
        CodecConfig.CodecPolicy codecPolicy;
        CodecConfig.Builder withLogBuilder = new CodecConfig.Builder().withDebug(false).withLogBuilder(new a(5));
        if (PocFeatures.USE_ANDROID_CODEC) {
            codecPolicy = CodecConfig.CodecPolicy.PreferAndroid;
        } else {
            codecPolicy = CodecConfig.CodecPolicy.PreferVendor;
        }
        CodecConfig build = withLogBuilder.withImageCodecPolicy(codecPolicy).withCustomCodec(new CustomCodecImpl()).withHeap(true).withBitmapPool(BitmapPool.getInstance()).build();
        this.codecConfig = build;
        this.imageCodec = build.imageCodec;
        this.videoCodec = build.videoCodec;
    }

    public static String toDebugString(byte[] bArr, int i2, int i7) {
        return C0212a.p(A.a.h(i2, i7, "bytes[", GlobalPostProcInternalPPInterface.SPLIT_REGEX, GlobalPostProcInternalPPInterface.SPLIT_REGEX), StringCompat.valueOf(bArr, i2, Math.min(16, i7), ':'), "]");
    }

    /* JADX INFO: finally extract failed */
    public Bitmap decodeByteArray(byte[] bArr, int i2, int i7, BitmapOptions bitmapOptions) {
        try {
            Trace.beginSection("decodeByteArray");
            Bitmap decodeByteArray = this.imageCodec.decodeByteArray(bArr, i2, i7, bitmapOptions);
            if (decodeByteArray == null) {
                Log.e("ImageDecoderCompat", "decodeByteArray failed " + bitmapOptions + " " + toDebugString(bArr, i2, i7));
                Trace.endSection();
                return null;
            }
            Bitmap resizeForMaxBitmapSize = BitmapUtils.resizeForMaxBitmapSize(decodeByteArray);
            Trace.endSection();
            return resizeForMaxBitmapSize;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public Bitmap decodeDngThumbnail(String str, int i2) {
        return this.imageCodec.decodeDngThumbnail(str, i2);
    }

    /* JADX INFO: finally extract failed */
    public Bitmap decodeFile(String str, BitmapOptions bitmapOptions) {
        try {
            Trace.beginSection("decodeFile");
            Bitmap decodeFile = this.imageCodec.decodeFile(str, bitmapOptions);
            if (decodeFile == null) {
                Log.e("ImageDecoderCompat", "decodeFile failed " + bitmapOptions + " " + FileUtils.info(str));
                Trace.endSection();
                return null;
            }
            Bitmap resizeForMaxBitmapSize = BitmapUtils.resizeForMaxBitmapSize(decodeFile);
            Trace.endSection();
            return resizeForMaxBitmapSize;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public Bitmap decodeHeifThumbnail(String str, int i2) {
        return this.imageCodec.decodeHeifThumbnail(str, i2);
    }

    public Bitmap decodeThumbnail(String str, int i2) {
        return this.imageCodec.decodeThumbnail(str, i2);
    }

    public void recycle() {
        this.imageCodec.recycle();
    }
}
