package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular;

import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import n0.C0235b;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IrregularCollageViewHolder extends CollageViewHolder {
    private Rect mCropRect;
    private int mDrawableHeight;
    private int mDrawableWidth;
    private final RectF mInset;

    public IrregularCollageViewHolder(View view, int i2, RectF rectF) {
        super(view, i2);
        this.mInset = rectF;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setViewMatrix$0() {
        setViewMatrix(this.mCropRect);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setViewMatrix$1(CropCalculator cropCalculator, RectF rectF) {
        this.mCropRect = cropCalculator.calculateCropRect(rectF, this.mInset, this.mBitmap);
        ThreadUtil.postOnUiThread(new t(22, this));
    }

    public Rect getCropRect() {
        return this.mCropRect;
    }

    public void setDrawableSize(int i2, int i7) {
        this.mDrawableWidth = i2;
        this.mDrawableHeight = i7;
    }

    public void setViewMatrix() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            if (mediaItem.isVideo() || this.mBitmap == null) {
                super.setViewMatrix();
            }
            CropCalculator cropCalculator = new CropCalculator(this.mMediaItem, this.mDrawableWidth, this.mDrawableHeight);
            ThreadUtil.postOnBgThread(new C0235b(this, cropCalculator, cropCalculator.calculateImageCropRatio(), 19));
        }
    }

    private void setViewMatrix(Rect rect) {
        Drawable drawable = getImage().getDrawable();
        int orientation = (this.mMediaItem.isVideo() || this.mMediaItem.isBroken()) ? 0 : this.mMediaItem.getOrientation();
        if (drawable != null) {
            getImage().setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(getImage(), true).withCropRect(RectUtils.getRotatedRect(rect, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), orientation)).withOrientation(orientation).withOrientationTag(this.mMediaItem.getOrientationTag())));
        }
    }
}
