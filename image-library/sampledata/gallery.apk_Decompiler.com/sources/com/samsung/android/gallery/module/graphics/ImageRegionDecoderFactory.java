package com.samsung.android.gallery.module.graphics;

import A.a;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.module.graphics.HeifBitmapFactory;
import com.samsung.android.gallery.module.graphics.QuramBitmapFactory;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ImageRegionDecoderFactory {
    private static final RegionDecoderFactoryImpl sInstance;

    static {
        RegionDecoderFactoryImpl regionDecoderFactoryImpl;
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            regionDecoderFactoryImpl = new RegionDecoderFactorySem150Impl();
        } else {
            regionDecoderFactoryImpl = new RegionDecoderFactoryImpl();
        }
        sInstance = regionDecoderFactoryImpl;
    }

    public static ImageRegionDecoder create(String str, boolean z, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = FileType.getMimeType(str);
        }
        if (PocFeatures.USE_ANDROID_CODEC || z || MimeType.isWebp(str2)) {
            return AndroidRegionDecoder.newInstance(str, false);
        }
        return sInstance.create(str, false, str2);
    }

    public static ImageRegionDecoder create(String str, boolean z) {
        return create(str, z, (String) null);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RegionDecoderFactoryImpl {
        private static final boolean USE_ANDROID_CODEC_FOR_PNG = SdkConfig.lessThan(SdkConfig.SEM.Q_MR5);

        public RegionDecoderFactoryImpl() {
            Log.d("RegionDecoderFactory", "construct", Logger.getSimpleName((Object) this));
        }

        public ImageRegionDecoder create(String str, boolean z, String str2) {
            ImageRegionDecoder newInstance;
            if (HeifBitmapFactory.SYSTEM_HEIF_CODEC && MimeType.isHeif(str2) && (newInstance = HeifBitmapFactory.RegionDecoder.newInstance(str, z)) != null) {
                return newInstance;
            }
            if (USE_ANDROID_CODEC_FOR_PNG && MimeType.isPng(str2)) {
                return AndroidRegionDecoder.newInstance(str, z);
            }
            ImageRegionDecoder newInstance2 = QuramBitmapFactory.RegionDecoder.newInstance(str, z);
            return (newInstance2 != null || MimeType.isBmp(str2)) ? newInstance2 : AndroidRegionDecoder.newInstance(str, z);
        }

        public ImageRegionDecoder create(byte[] bArr, int i2, int i7, boolean z, String str) {
            ImageRegionDecoder newInstance;
            if (!HeifBitmapFactory.SYSTEM_HEIF_CODEC || !MimeType.isHeif(str) || (newInstance = HeifBitmapFactory.RegionDecoder.newInstance(bArr, i2, i7, z)) == null) {
                return AndroidRegionDecoder.newInstance(bArr, i2, i7, z);
            }
            return newInstance;
        }

        public ImageRegionDecoder create(InputStream inputStream, boolean z) {
            return AndroidRegionDecoder.newInstance(inputStream, z);
        }
    }

    public static ImageRegionDecoder create(byte[] bArr, int i2, int i7, boolean z, String str) {
        if (PocFeatures.USE_ANDROID_CODEC || z || MimeType.isWebp(str)) {
            return AndroidRegionDecoder.newInstance(bArr, i2, i7, false);
        }
        if (!CodecCompat.PATCH_HEIF_STREAM_FATAL || CodecCompat.ensureIntegrity(bArr, i2)) {
            return sInstance.create(bArr, i2, i7, false, str);
        }
        return AndroidRegionDecoder.newInstance(bArr, i2, i7, false);
    }

    public static ImageRegionDecoder create(InputStream inputStream) {
        return sInstance.create(inputStream, false);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RegionDecoderFactorySem150Impl extends RegionDecoderFactoryImpl {
        static volatile boolean SUPPORT = true;

        public ImageRegionDecoder create(String str, boolean z, String str2) {
            if (!SUPPORT) {
                return super.create(str, z, str2);
            }
            try {
                ImageRegionDecoder newInstance = Sem150RegionDecoder.newInstance(str);
                if (newInstance != null) {
                    return newInstance;
                }
                Log.e("RegionDecoderFactory(150)", "create(file) failed. " + FileUtils.info(str));
                return AndroidRegionDecoder.newInstance(str, z);
            } catch (Error | Exception e) {
                if (e instanceof NoClassDefFoundError) {
                    SUPPORT = false;
                }
                a.z(e, new StringBuilder("create(file) failed. e="), "RegionDecoderFactory(150)");
            }
        }

        public ImageRegionDecoder create(byte[] bArr, int i2, int i7, boolean z, String str) {
            if (!SUPPORT) {
                return super.create(bArr, i2, i7, z, str);
            }
            try {
                ImageRegionDecoder newInstance = Sem150RegionDecoder.newInstance(bArr, i2, i7);
                if (newInstance != null) {
                    return newInstance;
                }
                Log.e("RegionDecoderFactory(150)", "create(buffer) failed. " + Logger.v(Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i7)) + ArcCommonLog.TAG_COMMA + StringCompat.valueOf(bArr, 16));
                return AndroidRegionDecoder.newInstance(bArr, i2, i7, z);
            } catch (Error | Exception e) {
                Throwable th = e;
                if (th instanceof NoClassDefFoundError) {
                    SUPPORT = false;
                }
                a.z(th, new StringBuilder("create(buffer) failed. e="), "RegionDecoderFactory(150)");
            }
        }
    }
}
