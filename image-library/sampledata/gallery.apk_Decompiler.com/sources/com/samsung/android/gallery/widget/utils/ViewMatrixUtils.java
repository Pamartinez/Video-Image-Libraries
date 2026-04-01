package com.samsung.android.gallery.widget.utils;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.crop.SmartCropUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.RectUtils;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewMatrixUtils {
    public static Matrix createImageMatrix(ImageView imageView, FileItemInterface fileItemInterface, boolean z) {
        boolean z3 = true;
        if (fileItemInterface == null) {
            return ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, true));
        }
        int orientation = (fileItemInterface.isVideo() || fileItemInterface.isBroken()) ? 0 : fileItemInterface.getOrientation();
        if (fileItemInterface.isCreature() || fileItemInterface.isPanoramic() || fileItemInterface.isCustomCover()) {
            z3 = false;
        }
        RectF cropRectRatio = fileItemInterface.getCropRectRatio();
        return ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, z3).withCropRect(RectUtils.isValidRect(cropRectRatio) ? RectUtils.getSmartCropRect(imageView.getDrawable(), cropRectRatio, orientation, z) : null).withOrientation(orientation).withOrientationTag(fileItemInterface.getOrientationTag()));
    }

    private static Rect getCropRect(ImageView imageView, Drawable drawable, MediaItem mediaItem, RectF rectF, int i2) {
        Rect rect;
        if (!SmartCropUtils.isValidRect(rectF)) {
            rectF = getFaceCropRect(imageView, mediaItem);
        }
        if (SmartCropUtils.isValidRect(rectF)) {
            rect = SmartCropUtils.calcSmartCropRectForStory(new SmartCropUtils.CropInfo.Builder(rectF).setDrawableSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()).setViewSize((float) imageView.getWidth(), (float) imageView.getHeight()).setRotation(i2).build());
        } else {
            rect = null;
        }
        return RectUtils.getRotatedRect(rect, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), i2);
    }

    private static RectF getFaceCropRect(ImageView imageView, MediaItem mediaItem) {
        ArrayList cropRectRatioList;
        int i2;
        if (mediaItem == null || (cropRectRatioList = mediaItem.getCropRectRatioList()) == null || cropRectRatioList.size() != 6) {
            return null;
        }
        if (((float) imageView.getHeight()) / ((float) imageView.getWidth()) >= 0.75f) {
            i2 = 3;
        } else {
            i2 = 5;
        }
        return (RectF) cropRectRatioList.get(i2);
    }

    private static boolean isSmallCropThumb(int i2, int i7, boolean z) {
        if (z || i2 != i7) {
            return false;
        }
        return true;
    }

    public static void setViewMatrix(ImageView imageView, MediaItem mediaItem, boolean z) {
        if (mediaItem != null && imageView.getScaleType() == ImageView.ScaleType.MATRIX) {
            boolean z3 = false;
            int orientation = (mediaItem.isVideo() || mediaItem.isBroken()) ? 0 : mediaItem.getOrientation();
            int intrinsicWidth = imageView.getDrawable().getIntrinsicWidth();
            int intrinsicHeight = imageView.getDrawable().getIntrinsicHeight();
            boolean isCustomCover = mediaItem.isCustomCover();
            RectF cropRectRatio = mediaItem.getCropRectRatio();
            Rect smartCropRect = (!RectUtils.isValidRect(cropRectRatio) || isSmallCropThumb(intrinsicWidth, intrinsicHeight, isCustomCover)) ? null : RectUtils.getSmartCropRect(imageView.getDrawable(), cropRectRatio, orientation, z);
            if (!mediaItem.isCreature() && !mediaItem.isPanoramic() && !isCustomCover) {
                z3 = true;
            }
            imageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, z3).withCropRect(smartCropRect).withOrientation(orientation).withOrientationTag(mediaItem.getOrientationTag())));
        }
    }

    public static Matrix createImageMatrix(ImageView imageView, FileItemInterface fileItemInterface) {
        return createImageMatrix(imageView, fileItemInterface, true);
    }

    public static void setViewMatrix(ImageView imageView, MediaItem mediaItem, RectF rectF, int i2, boolean z) {
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            imageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, z).withCropRect(getCropRect(imageView, drawable, mediaItem, rectF, i2)).withOrientation(i2).withOrientationTag(mediaItem.getOrientationTag())));
        }
    }

    public static void setViewMatrix(ImageView imageView, MediaItem mediaItem, RectF rectF, int i2, int i7, boolean z) {
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            imageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, z).withCropRect(getCropRect(imageView, drawable, mediaItem, rectF, i2)).withOrientation(i2).withOrientationTag(i7)));
        }
    }
}
