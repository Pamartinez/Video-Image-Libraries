package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.creature.FacePosRatioHelper;
import com.samsung.android.gallery.module.crop.SmartCropUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.smartrect.CoverRect;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CropCalculator {
    private int mBitmapHeight;
    private int mBitmapWidth;
    private final int mDrawableHeight;
    private final int mDrawableWidth;
    private final MediaItem mMediaItem;

    public CropCalculator(MediaItem mediaItem, Drawable drawable) {
        this(mediaItem, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    private SmartCropUtils.CropInfo buildCropInfo(RectF rectF) {
        return new SmartCropUtils.CropInfo.Builder(getCropRectRatio(rectF)).setDrawableSize(this.mBitmapWidth, this.mBitmapHeight).setViewSize((float) this.mDrawableWidth, (float) this.mDrawableHeight).setRotation(getOrientation()).build();
    }

    private Rect calcCropRectWithFace(Rect rect, Rect rect2) {
        Rect rect3 = new Rect(rect);
        if (!rect3.contains(rect2)) {
            updateSize(rect3, rect2);
            keepViewRatio(rect3, ((float) rect.height()) / ((float) rect.width()));
            moveFaceToCenter(rect3, rect2);
            offsetEdges(rect3);
        }
        if (isCropped(rect3)) {
            return rect3;
        }
        return moveFaceToCenter(rect, rect2);
    }

    private RectF getCropRectRatio(RectF rectF) {
        if (rectF != null) {
            return rectF;
        }
        return new RectF(0.0f, 0.0f, 1.0f, 1.0f);
    }

    private RectF getFaceCropRect(MediaItem mediaItem, int i2, int i7) {
        ArrayList cropRectRatioList;
        int i8;
        if (mediaItem == null || (cropRectRatioList = mediaItem.getCropRectRatioList()) == null || cropRectRatioList.size() != 6) {
            return null;
        }
        if (((float) i7) / ((float) i2) >= 0.75f) {
            i8 = 3;
        } else {
            i8 = 5;
        }
        return (RectF) cropRectRatioList.get(i8);
    }

    private int getOrientation() {
        if (this.mMediaItem.isVideo() || this.mMediaItem.isBroken()) {
            return 0;
        }
        return this.mMediaItem.getOrientation();
    }

    private Rect getRect(RectF rectF) {
        int i2 = this.mBitmapWidth;
        int i7 = this.mBitmapHeight;
        return new Rect((int) (((float) i2) * rectF.left), (int) (((float) i7) * rectF.top), (int) (((float) i2) * rectF.right), (int) (((float) i7) * rectF.bottom));
    }

    private RectF getUnitedFaceRectRatio(ArrayList<RectF> arrayList, RectF rectF) {
        Iterator<RectF> it = arrayList.iterator();
        RectF rectF2 = null;
        while (it.hasNext()) {
            RectF next = it.next();
            if (rectF2 == null) {
                rectF2 = new RectF(next);
            } else {
                rectF2.union(next);
            }
        }
        if (rectF2 != null) {
            float f = rectF2.left;
            float f5 = 0.0f;
            if (f < rectF.left) {
                f = 0.0f;
            }
            rectF2.left = f;
            float f8 = rectF2.top;
            if (f8 >= rectF.top) {
                f5 = f8;
            }
            rectF2.top = f5;
            float f10 = rectF2.right;
            float f11 = 1.0f;
            if (rectF.right + f10 > 1.0f) {
                f10 = 1.0f;
            }
            rectF2.right = f10;
            float f12 = rectF2.bottom;
            if (rectF.bottom + f12 <= 1.0f) {
                f11 = f12;
            }
            rectF2.bottom = f11;
        }
        return rectF2;
    }

    private boolean isCropped(Rect rect) {
        if ((((float) rect.width()) * ((float) rect.height())) / ((float) (this.mBitmapWidth * this.mBitmapHeight)) < 0.95f) {
            return true;
        }
        return false;
    }

    private void keepViewRatio(Rect rect, float f) {
        float height = ((float) rect.height()) / ((float) rect.width());
        if (height > f) {
            float height2 = ((float) rect.height()) / f;
            int i2 = this.mBitmapWidth;
            if (height2 >= ((float) i2)) {
                float f5 = ((float) i2) / height2;
                height2 *= f5;
                rect.bottom = rect.top + ((int) (((float) rect.height()) * f5));
            }
            rect.offset((int) ((((float) rect.width()) - height2) / 2.0f), 0);
            rect.right = (int) (((float) rect.left) + height2);
        } else if (height < f) {
            float width = ((float) rect.width()) * f;
            int i7 = this.mBitmapHeight;
            if (width >= ((float) i7)) {
                float f8 = ((float) i7) / width;
                width *= f8;
                rect.right = rect.left + ((int) (((float) rect.width()) * f8));
            }
            rect.offset(0, (int) ((((float) rect.height()) - width) / 2.0f));
            rect.bottom = (int) (((float) rect.top) + width);
        }
    }

    private Rect moveFaceToCenter(Rect rect, Rect rect2) {
        int i2;
        int i7;
        if (rect2.centerX() < rect.centerX()) {
            i2 = Math.max(rect2.centerX() - rect.centerX(), -rect.left);
        } else {
            i2 = Math.min(rect2.centerX() - rect.centerX(), this.mBitmapWidth - rect.right);
        }
        if (rect2.centerY() < rect.centerY()) {
            i7 = Math.max(rect2.centerY() - rect.centerY(), -rect.top);
        } else {
            i7 = Math.min(rect2.centerY() - rect.centerY(), this.mBitmapHeight - rect.bottom);
        }
        rect.offset(i2, i7);
        return rect;
    }

    private void offsetBottom(Rect rect) {
        int i2 = rect.bottom;
        int i7 = this.mBitmapHeight;
        if (i2 > i7) {
            int i8 = i2 - i7;
            int height = rect.height() + i8;
            int i10 = this.mBitmapHeight;
            if (height <= i10) {
                rect.offset(0, -i8);
                return;
            }
            rect.top = 0;
            rect.bottom = i10;
        }
    }

    private void offsetEdges(Rect rect) {
        offsetLeft(rect);
        offsetTop(rect);
        offsetRight(rect);
        offsetBottom(rect);
    }

    private void offsetLeft(Rect rect) {
        int i2 = rect.left;
        if (i2 < 0) {
            int width = rect.width() - i2;
            int i7 = this.mBitmapWidth;
            if (width <= i7) {
                rect.offset(-i2, 0);
                return;
            }
            rect.left = 0;
            rect.right = i7;
        }
    }

    private void offsetRight(Rect rect) {
        int i2 = rect.right;
        int i7 = this.mBitmapWidth;
        if (i2 > i7) {
            int i8 = i2 - i7;
            int width = rect.width() + i8;
            int i10 = this.mBitmapWidth;
            if (width <= i10) {
                rect.offset(-i8, 0);
                return;
            }
            rect.left = 0;
            rect.right = i10;
        }
    }

    private void offsetTop(Rect rect) {
        int i2 = rect.top;
        if (i2 < 0) {
            int height = rect.height() - i2;
            int i7 = this.mBitmapHeight;
            if (height <= i7) {
                rect.offset(0, -i2);
                return;
            }
            rect.top = 0;
            rect.bottom = i7;
        }
    }

    private void updateSize(Rect rect, Rect rect2) {
        if (rect.width() < rect2.width()) {
            rect.right = rect2.width() + rect.left;
        }
        if (rect.height() < rect2.height()) {
            rect.bottom = rect2.height() + rect.top;
        }
    }

    public Rect calculateCropRect(RectF rectF, RectF rectF2, Bitmap bitmap) {
        this.mBitmapWidth = bitmap.getWidth();
        this.mBitmapHeight = bitmap.getHeight();
        ArrayList<RectF> faceRectList = FacePosRatioHelper.getFaceRectList(this.mMediaItem.getFileId());
        Rect calcSmartCropRectForStory = SmartCropUtils.calcSmartCropRectForStory(buildCropInfo(rectF));
        if (faceRectList == null || faceRectList.isEmpty()) {
            return calcSmartCropRectForStory;
        }
        return calcCropRectWithFace(calcSmartCropRectForStory, getRect(getUnitedFaceRectRatio(faceRectList, rectF2)));
    }

    public RectF calculateImageCropRatio() {
        boolean z;
        MediaItem mediaItem = this.mMediaItem;
        if (this.mDrawableWidth >= this.mDrawableHeight) {
            z = true;
        } else {
            z = false;
        }
        RectF smartCropForCover = CoverRect.getSmartCropForCover((FileItemInterface) mediaItem, z);
        if (!SmartCropUtils.isValidRect(smartCropForCover)) {
            smartCropForCover = getFaceCropRect(this.mMediaItem, this.mDrawableWidth, this.mDrawableHeight);
        }
        if (!SmartCropUtils.isValidRect(smartCropForCover)) {
            smartCropForCover = this.mMediaItem.getCropRectRatio();
        }
        if (SmartCropUtils.isValidRect(smartCropForCover)) {
            return smartCropForCover;
        }
        return null;
    }

    public Rect getImageCropRect(Bitmap bitmap, RectF rectF) {
        return calculateCropRect(calculateImageCropRatio(), rectF, bitmap);
    }

    public CropCalculator(MediaItem mediaItem, int i2, int i7) {
        this.mMediaItem = mediaItem;
        this.mDrawableWidth = i2;
        this.mDrawableHeight = i7;
    }
}
