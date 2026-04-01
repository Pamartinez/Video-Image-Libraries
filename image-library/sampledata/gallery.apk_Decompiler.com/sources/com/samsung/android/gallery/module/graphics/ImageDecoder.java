package com.samsung.android.gallery.module.graphics;

import A.a;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.JpegParser;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import i.C0212a;
import java.io.File;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ImageDecoder {
    private static final boolean ANDROID_GTE_POS = SdkConfig.atLeast(SdkConfig.GED.P);
    private static int TEST_IN_BITMAP_COUNT = 10;
    public static final boolean USE_CODEC2 = false;
    private static final DebugLogger sDebugLogger = new DebugLogger();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DebugLogger {
        private final LruCache<String, String> mRecentFileEnd = new LruCache<>(5);
        private final LruCache<String, String> mRecentFileStart = new LruCache<>(5);

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$dump$0(PrintWriter printWriter, String str, String str2) {
            if (FileUtils.isProgressiveCandidate(str) && JpegParser.isProgressive(str)) {
                str2 = C0212a.A(str2, " ProgressiveJpeg");
            }
            StringBuilder t = C0212a.t(str2, " ");
            t.append(new File(str).length());
            String sb2 = t.toString();
            Logger.dumpLog(printWriter, Logger.getEncodedString(str) + " : " + sb2);
        }

        public void dump(PrintWriter printWriter) {
            try {
                Logger.dumpLog(printWriter, "=== recent decoded image ==");
                this.mRecentFileStart.snapshot().forEach(new d(printWriter, 0));
                this.mRecentFileEnd.snapshot().forEach(new d(printWriter, 1));
            } catch (Error | Exception unused) {
            }
        }

        public void logEnding(String str, String str2) {
            try {
                this.mRecentFileStart.remove(str);
                this.mRecentFileEnd.put(str, str2);
            } catch (Error | Exception unused) {
            }
        }

        public void logStarting(String str, String str2) {
            try {
                this.mRecentFileStart.put(str, str2);
            } catch (Error | Exception unused) {
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DecoderHolder {
        /* access modifiers changed from: private */
        public static final ImageDecoderImpl sImageDecoder = ImageDecoder.init();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DecoderHolder2 {
        static final ImageDecoderCompat instance = new ImageDecoderCompat();
    }

    private static void assertThread() {
        ThreadUtil.assertBgThread("ImageDecoder should run on background thread");
    }

    public static void clear() {
        DecoderHolder.sImageDecoder.recycle();
        if (USE_CODEC2) {
            DecoderHolder2.instance.recycle();
        }
    }

    public static Bitmap decodeByteArray(byte[] bArr, int i2, int i7, BitmapOptions bitmapOptions) {
        if (bitmapOptions.inJustDecodeBounds) {
            BitmapFactory.decodeByteArray(bArr, i2, i7, bitmapOptions);
            return null;
        } else if (USE_CODEC2) {
            return DecoderHolder2.instance.decodeByteArray(bArr, i2, i7, bitmapOptions);
        } else {
            assertThread();
            if (bitmapOptions.inPreferredAndroidCodec) {
                return BitmapFactory.decodeByteArray(bArr, i2, i7, bitmapOptions);
            }
            try {
                Trace.beginSection("decodeByteArray");
                if (bitmapOptions.outWidth <= 0) {
                    bitmapOptions.parse(bArr, i2, i7);
                }
                if (ANDROID_GTE_POS) {
                    BitmapPool.getInstance().apply(bitmapOptions);
                }
                Bitmap decodeByteArray = DecoderHolder.sImageDecoder.decodeByteArray(bArr, i2, i7, bitmapOptions);
                if (decodeByteArray == null) {
                    Log.e("ImageDecoder", "decodeByteArray failed " + bitmapOptions + ArcCommonLog.TAG_COMMA + StringCompat.valueOf(bArr, 16));
                }
                return BitmapUtils.resizeForMaxBitmapSize(decodeByteArray);
            } finally {
                Trace.endSection();
            }
        }
    }

    public static Bitmap decodeDngThumbnail(String str, String str2) {
        return decodeDngThumbnail(str, str2, 224);
    }

    public static Bitmap decodeFile(String str, BitmapOptions bitmapOptions) {
        String str2;
        if (str == null) {
            return null;
        }
        if (bitmapOptions.inJustDecodeBounds) {
            BitmapFactory.decodeFile(str, bitmapOptions);
            return null;
        } else if (USE_CODEC2) {
            return DecoderHolder2.instance.decodeFile(str, bitmapOptions);
        } else {
            assertThread();
            if (bitmapOptions.inPreferredAndroidCodec) {
                return BitmapFactory.decodeFile(str, bitmapOptions);
            }
            try {
                Trace.beginSection("decodeFile");
                long currentTimeMillis = System.currentTimeMillis();
                if (ANDROID_GTE_POS) {
                    BitmapPool.getInstance().apply(bitmapOptions);
                }
                DebugLogger debugLogger = sDebugLogger;
                debugLogger.logStarting(str, "start[" + Log.getLogIndex() + "]");
                Bitmap decodeFile = DecoderHolder.sImageDecoder.decodeFile(str, bitmapOptions);
                if (decodeFile == null) {
                    Log.e("ImageDecoder", "decodeFile failed. " + bitmapOptions + ArcCommonLog.TAG_COMMA + FileUtils.info(str));
                }
                if (decodeFile != null) {
                    str2 = Logger.vt(decodeFile, Long.valueOf(currentTimeMillis));
                } else {
                    str2 = "fail";
                }
                debugLogger.logEnding(str, str2);
                return BitmapUtils.resizeForMaxBitmapSize(decodeFile);
            } finally {
                Trace.endSection();
            }
        }
    }

    public static Bitmap decodeHeifThumbnail(String str) {
        if (!USE_CODEC2) {
            return SeApiCompat.getThumbnailFromHeif(str);
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return DecoderHolder2.instance.decodeHeifThumbnail(str, 640);
    }

    public static Bitmap decodeJpegThumbnail(String str) {
        if (!USE_CODEC2) {
            try {
                byte[] thumbnailBytes = new ExifInterface(str).getThumbnailBytes();
                if (thumbnailBytes != null && thumbnailBytes.length > 0) {
                    return decodeByteArray(thumbnailBytes, 0, thumbnailBytes.length, new BitmapOptions());
                }
            } catch (Error | Exception unused) {
            }
            return null;
        } else if (TextUtils.isEmpty(str)) {
            return null;
        } else {
            return DecoderHolder2.instance.decodeThumbnail(str, 640);
        }
    }

    public static void dump(PrintWriter printWriter) {
        sDebugLogger.dump(printWriter);
        try {
            Logger.dumpLog(printWriter, "=== ImageDecoder ==");
            Logger.dumpLog(printWriter, "Impl:=" + DecoderHolder.sImageDecoder);
            Logger.dumpLog(printWriter, "Buffer pool:=" + DecoderHolder.sImageDecoder.toDebugString());
        } catch (Error | Exception unused) {
        }
    }

    public static ImageDecoderImpl init() {
        try {
            if (PocFeatures.USE_ANDROID_CODEC) {
                return new ImageDecoderImpl();
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.B)) {
                return new ImageDecoderSem150Impl();
            }
            if (!SdkConfig.atLeast(SdkConfig.SEM.U) || Features.isEnabled(Features.IS_SEP_LITE)) {
                return new ImageDecoderSemImpl();
            }
            return new ImageDecoderSem150Impl();
        } catch (Error | Exception e) {
            Log.e("ImageDecoder", "init failed. e=" + e.getMessage());
            return new ImageDecoderImpl();
        }
    }

    public static Bitmap decodeDngThumbnail(String str, String str2, int i2) {
        byte[] dngPreviewData;
        if (!USE_CODEC2) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    if (!QuramBitmapFactory.isSecDng2(str2) || (dngPreviewData = QuramBitmapFactory.getDngPreviewData(str)) == null) {
                        Bitmap decodeDngThumbnail = QuramBitmapFactory.decodeDngThumbnail(str);
                        return (decodeDngThumbnail == null || Math.max(decodeDngThumbnail.getWidth(), decodeDngThumbnail.getHeight()) <= 1024) ? decodeDngThumbnail : BitmapUtils.resize(decodeDngThumbnail, Math.min(i2 * 2, 1024));
                    }
                    return decodeByteArray(dngPreviewData, 0, dngPreviewData.length, BitmapOptionsFactory.parse(dngPreviewData, 0, dngPreviewData.length).adjustInSampleSizeLargerThan(i2));
                } catch (Error | Exception e) {
                    a.z(e, new StringBuilder("decodeDngThumbnail failed. e="), "ImageDecoder");
                }
            }
            return null;
        } else if (TextUtils.isEmpty(str)) {
            return null;
        } else {
            return DecoderHolder2.instance.decodeDngThumbnail(str, i2);
        }
    }
}
