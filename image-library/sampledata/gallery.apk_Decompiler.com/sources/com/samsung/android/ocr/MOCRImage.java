package com.samsung.android.ocr;

import android.graphics.Bitmap;
import com.samsung.android.ocr.MOCRConstants;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MOCRImage {
    private static final int MAX_AREA = 16777216;
    private static final int MAX_HEIGHT = 16384;
    private static final int MAX_WIDTH = 16384;
    private static final String TAG = "MOCRImage";
    private byte[] mByteArr;
    private int mHeight;
    private int mImageFormat;
    private float mScale = 1.0f;
    private int mWidth;

    private MOCRImage(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            MOCRLog.e(TAG, "Error reading bitmap.");
            throw new Exception("Error reading bitmap. Null or recycled already.");
        }
        this.mHeight = bitmap.getHeight();
        this.mWidth = bitmap.getWidth();
        this.mImageFormat = MOCRConstants.MOCRPxlFmt.RGBA8888.getValue();
        Bitmap resizeBmp = resizeBmp(bitmap);
        try {
            ByteBuffer allocate = ByteBuffer.allocate(resizeBmp.getRowBytes() * this.mHeight);
            if (!bitmap.isRecycled()) {
                resizeBmp.copyPixelsToBuffer(allocate);
            }
            this.mByteArr = allocate.array();
        } catch (Error | Exception e) {
            MOCRLog.e(TAG, "Error reading bitmap. Could be recycled already.");
            throw new Exception("Error reading bitmap. Could be recycled already.", e);
        }
    }

    public static MOCRImage fromBitmap(Bitmap bitmap) {
        if (bitmap.isRecycled()) {
            MOCRLog.e(TAG, "Skipping recycled bitmap");
            return null;
        } else if (bitmap.getConfig() == Bitmap.Config.RGBA_F16 || bitmap.getConfig() == Bitmap.Config.RGB_565) {
            try {
                return new MOCRImage(bitmap.copy(Bitmap.Config.ARGB_8888, false));
            } catch (Exception unused) {
                return null;
            }
        } else if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
            String str = TAG;
            MOCRLog.e(str, "Bitmap format not supported " + bitmap.getConfig().toString());
            return null;
        } else {
            try {
                return new MOCRImage(bitmap);
            } catch (Exception unused2) {
                return null;
            }
        }
    }

    public static MOCRImage fromByteArray(byte[] bArr, int i2, int i7, int i8) {
        return new MOCRImage(bArr, i2, i7, i8);
    }

    private Bitmap resizeBmp(Bitmap bitmap) {
        boolean z;
        int i2 = this.mHeight;
        int i7 = this.mWidth;
        if (i2 * i7 > MAX_AREA) {
            this.mScale = (float) Math.sqrt((double) (((float) (i7 * i2)) / 1.6777216E7f));
            String str = TAG;
            MOCRLog.i(str, "Area Scale : " + this.mScale);
            float f = this.mScale;
            this.mHeight = (int) (((float) this.mHeight) / f);
            this.mWidth = (int) (((float) this.mWidth) / f);
            MOCRLog.i(str, "Updated ht / wd max area : " + this.mHeight + "  " + this.mWidth);
            z = true;
        } else {
            z = false;
        }
        int i8 = this.mWidth;
        if (i8 > 16384) {
            float f5 = ((float) i8) / 16384.0f;
            this.mHeight = (int) (((float) this.mHeight) / f5);
            this.mWidth = 16384;
            this.mScale *= f5;
            MOCRLog.i(TAG, "Updated ht / wd max wd : " + this.mHeight + "  " + this.mWidth);
            z = true;
        }
        int i10 = this.mHeight;
        if (i10 > 16384) {
            float f8 = ((float) i10) / 16384.0f;
            this.mWidth = (int) (((float) this.mWidth) / f8);
            this.mHeight = 16384;
            this.mScale *= f8;
            MOCRLog.i(TAG, "Updated ht / wd max ht : " + this.mHeight + "  " + this.mWidth);
            z = true;
        }
        if (z) {
            return Bitmap.createScaledBitmap(bitmap, this.mWidth, this.mHeight, true);
        }
        return bitmap;
    }

    public byte[] getByte() {
        return this.mByteArr;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getImageFormat() {
        return this.mImageFormat;
    }

    public float getScale() {
        return this.mScale;
    }

    public int getWidth() {
        return this.mWidth;
    }

    private MOCRImage(byte[] bArr, int i2, int i7, int i8) {
        this.mByteArr = bArr;
        this.mHeight = i2;
        this.mWidth = i7;
        this.mImageFormat = i8;
    }
}
