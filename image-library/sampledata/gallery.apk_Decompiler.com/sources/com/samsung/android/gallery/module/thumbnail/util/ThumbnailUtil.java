package com.samsung.android.gallery.module.thumbnail.util;

import A.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.Utils;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ThumbnailUtil {
    public static Bitmap createDummyBitmap(ThumbnailInterface thumbnailInterface) {
        int orientation;
        int width = thumbnailInterface.getWidth();
        int height = thumbnailInterface.getHeight();
        if (thumbnailInterface.isVideo() && (((orientation = thumbnailInterface.getOrientation()) == 90 || orientation == 270) && width > height)) {
            width = thumbnailInterface.getHeight();
            height = thumbnailInterface.getWidth();
        }
        if (width <= 0 || height <= 0) {
            width = ThumbKind.MEDIUM_KIND_SIZE;
            height = 320;
        }
        float max = ((float) Math.max(width, height)) / 320.0f;
        return BitmapUtils.createBitmap((int) (((float) width) / max), (int) (((float) height) / max), Bitmap.Config.ARGB_8888, AppResources.getColor(R$color.thumbnail_dummy_color));
    }

    public static Bitmap getMiniCropFromBitmap(Bitmap bitmap, ThumbnailInterface thumbnailInterface) {
        BitmapOperator crop = new BitmapOperator(bitmap).resize(32).crop(RectUtils.getRotatedRect(RectUtils.createCenterCropRect(bitmap.getWidth(), bitmap.getHeight(), thumbnailInterface.getWidth(), thumbnailInterface.getHeight()), bitmap.getWidth(), bitmap.getHeight(), thumbnailInterface.getOrientation()));
        int i2 = 0;
        BitmapOperator stretchable = crop.stretchable(false);
        if (!thumbnailInterface.isVideo()) {
            i2 = thumbnailInterface.getOrientation();
        }
        return stretchable.rotate(i2, thumbnailInterface.getOrientationTag()).apply();
    }

    public static Bitmap getSmallCropFromBitmap(Bitmap bitmap, ThumbnailInterface thumbnailInterface, int i2) {
        Rect rect;
        RectF rawCropRectRatio = thumbnailInterface.getRawCropRectRatio();
        if (!RectUtils.isValidRect(rawCropRectRatio) || thumbnailInterface.isCustomCover()) {
            rect = null;
        } else {
            rect = RectUtils.getSmartCropRectWithLimit(rawCropRectRatio, bitmap.getWidth(), bitmap.getHeight());
        }
        return new BitmapOperator(bitmap).resize(i2).crop(rect).stretchable(true).apply();
    }

    public static Bitmap getTinyCropFromBitmap(Bitmap bitmap, ThumbnailInterface thumbnailInterface, int i2) {
        Rect rect;
        int i7 = 0;
        if (bitmap.getWidth() != bitmap.getHeight() || thumbnailInterface.getWidth() == thumbnailInterface.getHeight()) {
            RectF rawCropRectRatio = thumbnailInterface.getRawCropRectRatio();
            if (RectUtils.isValidRect(rawCropRectRatio)) {
                rect = RectUtils.getSmartCropRectWithLimit(rawCropRectRatio, bitmap.getWidth(), bitmap.getHeight());
            } else {
                rect = null;
            }
            BitmapOperator crop = new BitmapOperator(bitmap).resize(i2).crop(rect);
            if (!thumbnailInterface.isVideo()) {
                i7 = thumbnailInterface.getOrientation();
            }
            return crop.rotate(i7, thumbnailInterface.getOrientationTag()).stretchable(true).apply();
        }
        BitmapOperator resize = new BitmapOperator(bitmap).resize(i2);
        if (!thumbnailInterface.isVideo()) {
            i7 = thumbnailInterface.getOrientation();
        }
        return resize.rotate(i7, thumbnailInterface.getOrientationTag()).stretchable(true).apply();
    }

    public static byte[] readMagic(Uri uri) {
        BufferedInputStream bufferedInputStream;
        byte[] bArr = new byte[32];
        try {
            bufferedInputStream = new BufferedInputStream(AppResources.getAppContext().getContentResolver().openInputStream(uri));
            bufferedInputStream.read(bArr);
            bufferedInputStream.close();
            return bArr;
        } catch (Exception | OutOfMemoryError | StackOverflowError e) {
            a.z(e, new StringBuilder("getImageThumbnail failed e="), "ThumbnailUtil");
            return bArr;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static void updateImageInfo(ThumbnailInterface thumbnailInterface, Uri uri) {
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(AppResources.getAppContext().getContentResolver().openInputStream(uri));
            updateImageInfo(thumbnailInterface, (InputStream) bufferedInputStream);
            bufferedInputStream.close();
            return;
        } catch (Exception | OutOfMemoryError | StackOverflowError e) {
            Log.e((CharSequence) "ThumbnailUtil", "updateImageInfo failed", e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static void updateImageInfoWithBitmapOptions(ThumbnailInterface thumbnailInterface) {
        BitmapOptions parse = BitmapOptionsFactory.parse(thumbnailInterface.getPath());
        thumbnailInterface.setSize(parse.outWidth, parse.outHeight);
        thumbnailInterface.setOrientation(0);
    }

    public static void updateVideoInfo(ThumbnailInterface thumbnailInterface, Context context) {
        if (thumbnailInterface.isUriItem()) {
            MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(context, Uri.parse(thumbnailInterface.getPath()));
            thumbnailInterface.setSize(videoInfo.width, videoInfo.height);
            thumbnailInterface.setOrientation(videoInfo.orientation);
        }
    }

    public static void updateImageInfo(ThumbnailInterface thumbnailInterface) {
        try {
            ExifInterface exif = ExifUtils.getExif(thumbnailInterface.getPath());
            if (exif != null) {
                int width = ExifUtils.getWidth(exif);
                int height = ExifUtils.getHeight(exif);
                if (width > 0) {
                    if (height > 0) {
                        thumbnailInterface.setSize(width, height);
                        thumbnailInterface.setOrientation(ExifUtils.getOrientation(exif));
                        thumbnailInterface.setOrientationTag(ExifUtils.getOrientationTag(exif));
                        return;
                    }
                }
                Log.d("ThumbnailUtil", "updateImageInfo exif size invalid {" + width + ArcCommonLog.TAG_COMMA + height + "}");
                updateImageInfoWithBitmapOptions(thumbnailInterface);
                return;
            }
            Log.d("ThumbnailUtil", "exif is null. updateImageInfoWithBitmapOptions.");
            updateImageInfoWithBitmapOptions(thumbnailInterface);
        } catch (Error | Exception e) {
            Log.e((CharSequence) "ThumbnailUtil", "updateImageInfo failed", e);
        }
    }

    public static void updateImageInfo(ThumbnailInterface thumbnailInterface, InputStream inputStream) {
        System.currentTimeMillis();
        if (inputStream.markSupported()) {
            inputStream.mark(Integer.MAX_VALUE);
        }
        ExifInterface exif = ExifUtils.getExif(inputStream);
        boolean z = false;
        if (exif != null) {
            int orientation = thumbnailInterface.getOrientation();
            thumbnailInterface.update(exif);
            if (thumbnailInterface.isRawImage() && orientation != thumbnailInterface.getOrientation()) {
                z = true;
            }
        }
        if (thumbnailInterface.getWidth() <= 0 || thumbnailInterface.getHeight() <= 0 || z) {
            if (inputStream.markSupported()) {
                Utils.resetSilently(inputStream);
            }
            BitmapOptions bitmapOptions = new BitmapOptions();
            bitmapOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, (Rect) null, bitmapOptions);
            thumbnailInterface.setSize(bitmapOptions.outWidth, bitmapOptions.outHeight);
        }
        if (thumbnailInterface.getFileSize() <= 0) {
            try {
                thumbnailInterface.setFileSize((long) inputStream.available());
            } catch (IOException unused) {
            }
            Log.v("ThumbnailUtil", "updateImageInfo size 0 to " + thumbnailInterface.getFileSize());
        }
        if (inputStream.markSupported()) {
            Utils.resetSilently(inputStream);
        }
    }
}
