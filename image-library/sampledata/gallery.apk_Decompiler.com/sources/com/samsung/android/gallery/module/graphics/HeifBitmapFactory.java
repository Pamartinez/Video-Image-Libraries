package com.samsung.android.gallery.module.graphics;

import A.a;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.media.SemHEIFCodec;
import com.samsung.android.media.SemHEIFRegionDecoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class HeifBitmapFactory {
    static final boolean SYSTEM_HEIF_CODEC;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CodecHolder {
        static final HeifCodec sInstance = create();

        private static HeifCodec create() {
            if (HeifBitmapFactory.SYSTEM_HEIF_CODEC) {
                try {
                    return new SemHeifCodec();
                } catch (Error | Exception e) {
                    a.z(e, new StringBuilder("create failed e="), "HeifBitmapFactory");
                }
            }
            Log.w("HeifBitmapFactory", "SemHeifCodecDisabled");
            return new HeifCodec();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HeifCodec {
        final String TAG = getClass().getSimpleName();

        public Bitmap decodeFile(String str, BitmapFactory.Options options) {
            Log.w(this.TAG, "HeifCodec decodeFile by BitmapFactory");
            return BitmapFactory.decodeFile(str, options);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SemHeifCodec extends HeifCodec {
        static final boolean SUPPORT_STREAM_DECODE = SdkConfig.atLeast(SdkConfig.SEM.R);

        public Bitmap decodeFile(String str, BitmapFactory.Options options) {
            Bitmap bitmap;
            try {
                bitmap = SemHEIFCodec.decodeFile(str, options);
            } catch (Error | Exception e) {
                a.z(e, new StringBuilder("decodeFile  failed e="), this.TAG);
                bitmap = null;
            }
            if (bitmap != null) {
                return bitmap;
            }
            Log.w(this.TAG, "decodeFile failed. use aosp");
            return super.decodeFile(str, options);
        }
    }

    static {
        boolean z;
        if (!SdkConfig.atLeast(SdkConfig.SEM.Q_MR5) || PocFeatures.USE_ANDROID_CODEC) {
            z = false;
        } else {
            z = true;
        }
        SYSTEM_HEIF_CODEC = z;
    }

    public static Bitmap decodeFile(String str, BitmapFactory.Options options) {
        return CodecHolder.sInstance.decodeFile(str, options);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RegionDecoder implements ImageRegionDecoder {
        final SemHEIFRegionDecoder mRegionDecoder;

        public RegionDecoder(SemHEIFRegionDecoder semHEIFRegionDecoder) {
            this.mRegionDecoder = semHEIFRegionDecoder;
        }

        public static ImageRegionDecoder newInstance(String str, boolean z) {
            if (HeifBitmapFactory.SYSTEM_HEIF_CODEC) {
                try {
                    SemHEIFRegionDecoder newInstance = SemHEIFRegionDecoder.newInstance(str);
                    if (newInstance != null) {
                        return new RegionDecoder(newInstance);
                    }
                } catch (Error | Exception e) {
                    a.z(e, new StringBuilder("createRegionDecoder failed e="), "HeifBitmapFactory");
                }
            }
            return AndroidRegionDecoder.newInstance(str, z);
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

        public static ImageRegionDecoder newInstance(byte[] bArr, int i2, int i7, boolean z) {
            if (HeifBitmapFactory.SYSTEM_HEIF_CODEC) {
                try {
                    SemHEIFRegionDecoder newInstance = SemHEIFRegionDecoder.newInstance(bArr, i2, i7, z);
                    if (newInstance != null) {
                        return new RegionDecoder(newInstance);
                    }
                } catch (Error | Exception e) {
                    a.z(e, new StringBuilder("createRegionDecoder(byte) failed e="), "HeifBitmapFactory");
                }
            }
            return AndroidRegionDecoder.newInstance(bArr, i2, i7, z);
        }
    }
}
