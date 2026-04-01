package com.sec.samsung.gallery.decoder.regiondecoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;
import com.sec.samsung.gallery.decoder.QuramCodecInterface;
import com.sec.samsung.gallery.decoder.QuramCodecMetadata;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuramCodecRegionDecoder {
    private static final String TAG = "QuramCodecRegionDecoder";
    private boolean mDngFile = false;
    private long mHandle = 0;
    private int mHeight;
    private final QuramCodecMetadata mMetadataInfo;
    private final Object mNativeLock;
    private final String mPath;
    private boolean mRecycled;
    private int mWidth;

    private QuramCodecRegionDecoder(String str) {
        boolean z = true;
        this.mRecycled = true;
        this.mNativeLock = new Object();
        this.mPath = str;
        this.mDngFile = (str == null || !str.toLowerCase().endsWith("dng")) ? false : z;
        this.mHandle = nativeNewInstance(str, getBitmapFactoryOptions());
        this.mMetadataInfo = new QuramCodecMetadata();
        this.mRecycled = false;
    }

    public static native Bitmap nativeDecodeRegion(long j2, Rect rect, BitmapFactory.Options options);

    public static native int nativeGetHeight(long j2);

    public static native int nativeGetWidth(long j2);

    public static native long nativeNewInstance(String str, BitmapFactory.Options options);

    public static native int nativeRecycle(long j2);

    public static native int nativeRegionCancel(long j2);

    public static QuramCodecRegionDecoder newInstance(String str) {
        try {
            QuramCodecRegionDecoder quramCodecRegionDecoder = new QuramCodecRegionDecoder(str);
            if (quramCodecRegionDecoder.mHandle != 0) {
                return quramCodecRegionDecoder;
            }
            Log.d(TAG, "Don't Support regionDecoder");
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Exception : " + e.toString());
            return null;
        }
    }

    public Bitmap decodeRegion(Rect rect, BitmapFactory.Options options) {
        int cropOriginX;
        try {
            if (!this.mRecycled) {
                if (this.mHandle != 0) {
                    int i2 = options.inSampleSize;
                    if (i2 == 0) {
                        i2 = 1;
                    }
                    options.inSampleSize = i2;
                    int width = ((rect.width() + i2) - 1) / i2;
                    int height = ((rect.height() + i2) - 1) / i2;
                    Bitmap bitmap = options.inBitmap;
                    if (bitmap != null) {
                        if (bitmap.getWidth() == width) {
                            if (options.inBitmap.getHeight() != height) {
                            }
                        }
                        Log.v(TAG, " RegionDecode Input Bitmap Err");
                        return options.inBitmap;
                    }
                    if (this.mDngFile && (cropOriginX = this.mMetadataInfo.getCropOriginX()) > 0) {
                        rect.top += cropOriginX;
                        rect.left += cropOriginX;
                        rect.bottom += cropOriginX;
                        rect.right += cropOriginX;
                    }
                    return nativeDecodeRegion(this.mHandle, rect, options);
                }
            }
            return options.inBitmap;
        } catch (Exception e) {
            Log.e(TAG, "Exception : " + e.toString());
            return options.inBitmap;
        }
    }

    public void finalize() {
        try {
            recycle();
        } finally {
            super.finalize();
        }
    }

    public BitmapFactory.Options getBitmapFactoryOptions() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return options;
    }

    public int getHeight() {
        int i2;
        int i7 = this.mHeight;
        if (i7 > 0) {
            return i7;
        }
        long j2 = this.mHandle;
        if (j2 != 0) {
            if (this.mDngFile) {
                if (this.mMetadataInfo.getImageHeight() == 0) {
                    QuramCodecInterface.nativeParseMetadata(this.mPath, this.mMetadataInfo);
                }
                if (this.mMetadataInfo.getCropOriginY() > 0) {
                    i2 = this.mMetadataInfo.getCropSizeHeight();
                } else {
                    i2 = this.mMetadataInfo.getImageHeight();
                }
                this.mHeight = i2;
            } else {
                this.mHeight = nativeGetHeight(j2);
            }
        }
        return this.mHeight;
    }

    public int getWidth() {
        int i2;
        int i7 = this.mWidth;
        if (i7 > 0) {
            return i7;
        }
        long j2 = this.mHandle;
        if (j2 != 0) {
            if (this.mDngFile) {
                if (this.mMetadataInfo.getImageWidth() == 0) {
                    QuramCodecInterface.nativeParseMetadata(this.mPath, this.mMetadataInfo);
                }
                if (this.mMetadataInfo.getCropOriginX() > 0) {
                    i2 = this.mMetadataInfo.getCropSizeWidth();
                } else {
                    i2 = this.mMetadataInfo.getImageWidth();
                }
                this.mWidth = i2;
            } else {
                this.mWidth = nativeGetWidth(j2);
            }
        }
        return this.mWidth;
    }

    public final boolean isRecycled() {
        if (this.mHandle == 0) {
            return true;
        }
        return false;
    }

    public void recycle() {
        synchronized (this.mNativeLock) {
            try {
                if (!this.mRecycled) {
                    long j2 = this.mHandle;
                    if (j2 != 0) {
                        nativeRecycle(j2);
                        this.mHandle = 0;
                        this.mRecycled = true;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void requestCancelDecode() {
        synchronized (this.mNativeLock) {
            try {
                long j2 = this.mHandle;
                if (j2 != 0 && !this.mRecycled) {
                    nativeRegionCancel(j2);
                }
                recycle();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
