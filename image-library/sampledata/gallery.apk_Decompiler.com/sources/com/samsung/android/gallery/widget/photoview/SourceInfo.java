package com.samsung.android.gallery.widget.photoview;

import A.a;
import M4.n;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.CodecCompat;
import com.samsung.android.gallery.module.graphics.ImageRegionDecoder;
import com.samsung.android.gallery.module.graphics.ImageRegionDecoderFactory;
import com.samsung.android.gallery.support.cache.BytesBuffer;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.ocr.MOCRLang;
import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SourceInfo {
    private static final List<Integer> VALID_ORIENTATIONS = Arrays.asList(new Integer[]{0, 90, Integer.valueOf(MOCRLang.KHMER), 270});
    BitmapOptions mBitmapOptions;
    private RectF mCropRect;
    private BytesBuffer mData;
    private long mDateModified;
    private long mFileSize;
    MediaItem mMediaItem;
    private String mMimeType;
    private int mOrientation = 0;
    private int mOrientationTag = 0;
    private String mPath;
    boolean mPpp;
    private int mSHeight;
    private int mSWidth;
    private boolean mSupportRegionDecoding;
    private BooleanSupplier mSupportRegionDecodingDynamic;

    private void setCropRect(RectF rectF) {
        if (RectUtils.isValidRect(rectF)) {
            this.mCropRect = new RectF(rectF);
        }
    }

    private void setData(BytesBuffer bytesBuffer) {
        this.mBitmapOptions = null;
        this.mData = bytesBuffer;
        this.mPath = null;
    }

    private void setMimeType(String str) {
        this.mMimeType = str;
    }

    private void setOrientation(int i2) {
        if (!VALID_ORIENTATIONS.contains(Integer.valueOf(i2))) {
            a.B(i2, "setOrientation invalid value ", "SourceInfo");
            i2 = 0;
        }
        this.mOrientation = i2;
    }

    private void setOrientationTag(int i2) {
        this.mOrientationTag = i2;
    }

    private void setPath(String str) {
        this.mPath = str;
        this.mData = null;
    }

    public int calculateInSampleSize(float f) {
        int i2;
        int i7 = 1;
        int width = getWidth(true);
        int height = getHeight(true);
        float f5 = (float) width;
        int i8 = (int) (f5 * f);
        float f8 = (float) height;
        int i10 = (int) (f * f8);
        if (i8 == 0 || i10 == 0) {
            return 32;
        }
        if (width > i8 || height > i10) {
            i2 = Math.min(Math.round(f8 / ((float) i10)), Math.round(f5 / ((float) i8)));
        } else {
            i2 = 1;
        }
        while (true) {
            int i11 = i7 * 2;
            if (i11 > i2) {
                return i7;
            }
            i7 = i11;
        }
    }

    public BitmapOptions computeBitmapOptions() {
        if (this.mBitmapOptions == null) {
            this.mBitmapOptions = createBitmapOptions();
        }
        return this.mBitmapOptions;
    }

    public BitmapOptions createBitmapOptions() {
        BytesBuffer bytesBuffer = this.mData;
        if (bytesBuffer != null) {
            return BitmapOptionsFactory.parse(bytesBuffer.data, bytesBuffer.offset, bytesBuffer.length);
        }
        return BitmapOptionsFactory.parse(this.mPath);
    }

    public ImageRegionDecoder createRegionDecoder() {
        boolean z;
        BufferedInputStream bufferedInputStream;
        int i2;
        String str = this.mMimeType;
        BytesBuffer bytesBuffer = this.mData;
        if (!CodecCompat.PATCH_HEIF_FILE_TRANSCODING || !CodecCompat.contains(this.mMediaItem)) {
            z = false;
        } else {
            z = true;
        }
        if (CodecCompat.PATCH_JPEG_PROGRESSIVE && !z && MimeType.isJpeg(str)) {
            z = !CodecCompat.ensureJpegSyntaxCompatible(this.mMediaItem);
        }
        if (bytesBuffer != null && (i2 = bytesBuffer.length) > 0) {
            return ImageRegionDecoderFactory.create(bytesBuffer.data, bytesBuffer.offset, i2, z, str);
        }
        String str2 = this.mPath;
        if (str2 == null) {
            return null;
        }
        if (!this.mMediaItem.isUriItem()) {
            return ImageRegionDecoderFactory.create(str2, z, str);
        }
        try {
            bufferedInputStream = new BufferedInputStream(AppResources.getAppContext().getContentResolver().openInputStream(Uri.parse(str2)));
            ImageRegionDecoder create = ImageRegionDecoderFactory.create(bufferedInputStream);
            bufferedInputStream.close();
            return create;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("createRegionDecoder failed. e="), "SourceInfo");
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public PointF getCenter(boolean z) {
        if (z) {
            RectF cropRect = getCropRect();
            if (RectUtils.isValidRect(cropRect)) {
                RectF rotatedRectFRatio = RectUtils.getRotatedRectFRatio(cropRect, getOrientation());
                float width = (rotatedRectFRatio.width() / 2.0f) + rotatedRectFRatio.left;
                float f = rotatedRectFRatio.top;
                return new PointF(((float) getWidth(true)) * width, ((float) getHeight(true)) * ((rotatedRectFRatio.height() / 2.0f) + f));
            }
        }
        return new PointF(((float) getWidth(true)) / 2.0f, ((float) getHeight(true)) / 2.0f);
    }

    public RectF getCropRect() {
        return this.mCropRect;
    }

    public long getFileSize() {
        return this.mFileSize;
    }

    public int getHash() {
        return (this.mPath + this.mData + this.mMimeType + this.mOrientation + this.mSWidth + this.mSHeight + this.mFileSize + this.mDateModified).hashCode();
    }

    public int getHeight(boolean z) {
        int i2;
        if (!z || ((i2 = this.mOrientation) != 90 && i2 != 270)) {
            return this.mSHeight;
        }
        return this.mSWidth;
    }

    public PointF getInitialPosition(boolean z) {
        return getCenter(z);
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getOrientationTag() {
        return this.mOrientationTag;
    }

    public int getWidth(boolean z) {
        int i2;
        if (!z || ((i2 = this.mOrientation) != 90 && i2 != 270)) {
            return this.mSWidth;
        }
        return this.mSHeight;
    }

    public boolean isAvailable() {
        BytesBuffer bytesBuffer = this.mData;
        if (bytesBuffer == null) {
            return !TextUtils.isEmpty(this.mPath);
        }
        if (bytesBuffer.length > 0) {
            return true;
        }
        return false;
    }

    public boolean isChanged(String str, BytesBuffer bytesBuffer) {
        if ((str == null || str.equals(this.mPath)) && bytesBuffer == this.mData) {
            return false;
        }
        return true;
    }

    public boolean isSourceReady() {
        if (this.mSWidth <= 0 || this.mSHeight <= 0) {
            return false;
        }
        return true;
    }

    public boolean isSupportRegionDecoding() {
        BooleanSupplier booleanSupplier = this.mSupportRegionDecodingDynamic;
        if (booleanSupplier == null) {
            return this.mSupportRegionDecoding;
        }
        return booleanSupplier.getAsBoolean();
    }

    public void setSize(int i2, int i7) {
        this.mSWidth = i2;
        this.mSHeight = i7;
    }

    public void setSourceInfo(MediaItem mediaItem, Bitmap bitmap, BytesBuffer bytesBuffer) {
        int i2;
        String str;
        this.mBitmapOptions = null;
        this.mMediaItem = mediaItem;
        int width = mediaItem.getWidth();
        int height = mediaItem.getHeight();
        int width2 = bitmap.getWidth();
        int height2 = bitmap.getHeight();
        if (mediaItem.isMtp() || mediaItem.isBroken() || width <= 0 || height <= 0 || mediaItem.isVideo()) {
            setSize(width2, height2);
            Integer valueOf = Integer.valueOf(width2);
            Integer valueOf2 = Integer.valueOf(height2);
            Integer valueOf3 = Integer.valueOf(width);
            Integer valueOf4 = Integer.valueOf(height);
            if (mediaItem.isPostProcessing()) {
                str = "PPP";
            } else {
                str = "";
            }
            Log.w((CharSequence) "SourceInfo", "setSourceInfo by bitmap", valueOf, valueOf2, valueOf3, valueOf4, str);
        } else {
            setSize(width, height);
        }
        this.mFileSize = mediaItem.getFileSize();
        this.mDateModified = mediaItem.getDateModified();
        if (mediaItem.isVideo()) {
            i2 = 0;
        } else {
            i2 = mediaItem.getOrientation();
        }
        setOrientation(i2);
        setOrientationTag(mediaItem.getOrientationTag());
        if (bytesBuffer != null) {
            setData(bytesBuffer);
            this.mFileSize = (long) bytesBuffer.length;
        } else {
            setPath(mediaItem.getPath());
        }
        setMimeType(mediaItem.getMimeType());
        if (mediaItem.isWebp()) {
            this.mSupportRegionDecodingDynamic = new n(mediaItem, 1);
        } else {
            this.mSupportRegionDecoding = mediaItem.isSupportRegionDecoding();
        }
        this.mPpp = mediaItem.isCommonPostProcessing();
        setCropRect(mediaItem.getCropRect());
    }
}
