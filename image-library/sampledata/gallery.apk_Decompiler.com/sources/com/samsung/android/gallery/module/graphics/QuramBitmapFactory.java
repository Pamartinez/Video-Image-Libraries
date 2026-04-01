package com.samsung.android.gallery.module.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.text.TextUtils;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.ExifCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.sec.samsung.gallery.decoder.QuramCodecInterface;
import com.sec.samsung.gallery.decoder.regiondecoder.QuramCodecRegionDecoder;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class QuramBitmapFactory {
    static final boolean OS_VER_S = SdkConfig.atLeast(SdkConfig.GED.S);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class QuramCodecHolder {
        static volatile boolean sQuramEnabled = QuramCodecInterface.loadLibrary();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RegionDecoder implements ImageRegionDecoder {
        private final QuramCodecRegionDecoder mRegionDecoder;

        public RegionDecoder(QuramCodecRegionDecoder quramCodecRegionDecoder) {
            this.mRegionDecoder = quramCodecRegionDecoder;
        }

        public static ImageRegionDecoder newInstance(String str, boolean z) {
            QuramCodecRegionDecoder newInstance;
            if (!QuramCodecHolder.sQuramEnabled || (newInstance = QuramCodecRegionDecoder.newInstance(str)) == null) {
                return null;
            }
            return new RegionDecoder(newInstance);
        }

        public Bitmap decodeRegion(Rect rect, BitmapOptions bitmapOptions) {
            return this.mRegionDecoder.decodeRegion(rect, bitmapOptions);
        }

        public int getHeight() {
            return this.mRegionDecoder.getHeight();
        }

        public int getWidth() {
            return this.mRegionDecoder.getWidth();
        }

        public boolean isRecycled() {
            return this.mRegionDecoder.isRecycled();
        }

        public void recycle() {
            this.mRegionDecoder.recycle();
        }
    }

    public static Bitmap decodeByteArray(byte[] bArr, int i2, int i7, BitmapFactory.Options options) {
        if (!QuramCodecHolder.sQuramEnabled || i7 <= 0 || i2 >= i7 || !support(options)) {
            return null;
        }
        try {
            if (options.inSampleSize <= 0) {
                options.inSampleSize = 1;
            }
            return QuramCodecInterface.nativeDecodeByteArray(bArr, i2, i7, options);
        } catch (UnsatisfiedLinkError unused) {
            QuramCodecHolder.sQuramEnabled = false;
            Log.e("QuramBitmapFactory", "decodeByteArray failed");
            return null;
        }
    }

    public static Bitmap decodeDngThumbnail(String str) {
        if (!QuramCodecHolder.sQuramEnabled) {
            return null;
        }
        try {
            Trace.beginSection("QuramBitmapFactory#decodeDNGPreview");
            return QuramCodecInterface.nativeDecodePreview(str);
        } catch (UnsatisfiedLinkError unused) {
            QuramCodecHolder.sQuramEnabled = false;
            Log.e("QuramBitmapFactory", "decodeDNGPreview failed");
            return null;
        } finally {
            Trace.endSection();
        }
    }

    public static Bitmap decodeFile(String str, BitmapFactory.Options options) {
        if (!QuramCodecHolder.sQuramEnabled || TextUtils.isEmpty(str) || !support(options)) {
            return null;
        }
        try {
            if (options.inSampleSize <= 0) {
                options.inSampleSize = 1;
            }
            return QuramCodecInterface.nativeDecodeFile(str, options);
        } catch (UnsatisfiedLinkError unused) {
            QuramCodecHolder.sQuramEnabled = false;
            Log.e("QuramBitmapFactory", "decodeFile failed");
            return null;
        }
    }

    public static byte[] getDngPreviewData(String str) {
        if (!QuramCodecHolder.sQuramEnabled) {
            return null;
        }
        try {
            return QuramCodecInterface.nativeGetDNGPreviewImageFromFile(str);
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getDngPreviewData(f) failed e="), "QuramBitmapFactory");
            return null;
        }
    }

    public static String getDngVer(String str) {
        try {
            ExifCompat exifCompat = new ExifCompat(str);
            if ("samsung".equalsIgnoreCase(exifCompat.getAttribute("Make"))) {
                int i2 = UnsafeCast.toInt(exifCompat.getAttribute("Compression"), 0);
                if (i2 == 1) {
                    return "samsung";
                }
                if (!OS_VER_S || (i2 != 7 && i2 != 52546)) {
                    return "";
                }
                return "samsung:v2";
            }
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getDngVer failed. e="), "QuramBitmapFactory");
        }
        return "";
    }

    public static int getLibraryVersion() {
        if (QuramCodecHolder.sQuramEnabled) {
            return QuramCodecInterface.getVersion();
        }
        return 0;
    }

    public static boolean isEnabled() {
        return QuramCodecHolder.sQuramEnabled;
    }

    public static boolean isSecDng2(String str) {
        if (!OS_VER_S || !"samsung:v2".equals(str)) {
            return false;
        }
        return true;
    }

    private static boolean support(BitmapFactory.Options options) {
        if (options.inPreferredConfig != Bitmap.Config.HARDWARE) {
            return true;
        }
        return false;
    }
}
