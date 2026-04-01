package com.samsung.android.gallery.widget.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.crop.CropViewImpl;
import com.samsung.android.gallery.widget.crop.api.CropImageView;
import com.samsung.android.gallery.widget.crop.api.OnCropImageMatrixChangedListener;
import o6.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CropPhotoView extends PhotoView implements CropImageView {
    private OnCropImageMatrixChangedListener mOnCropImageMatrixChangedListener;

    public CropPhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private boolean needToSwap(int i2, int i7, int i8, int i10) {
        if (i7 > i2 && i8 > i10) {
            return true;
        }
        if (i2 <= i7 || i10 <= i8) {
            return false;
        }
        return true;
    }

    private void updateFullMatrix() {
        float[] fArr;
        this.mConfig.mMatrix.reset();
        Bitmap bitmap = this.mImageProcessor.getBitmap();
        if (bitmap != null) {
            float[] fArr2 = new float[8];
            float[] fArr3 = new float[8];
            PhotoViewDelegate.setMatrixArray(fArr2, 0.0f, 0.0f, (float) bitmap.getWidth(), 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight(), 0.0f, (float) bitmap.getHeight());
            RectF displayRect = getDisplayRect();
            if (displayRect != null) {
                if (this.mImageProcessor.is0Degree()) {
                    float f = displayRect.left;
                    float f5 = displayRect.top;
                    float f8 = displayRect.right;
                    float f10 = displayRect.bottom;
                    fArr = fArr3;
                    PhotoViewDelegate.setMatrixArray(fArr, f, f5, f8, f5, f8, f10, f, f10);
                } else {
                    fArr = fArr3;
                    if (this.mImageProcessor.is90Degree()) {
                        float f11 = displayRect.right;
                        float f12 = displayRect.top;
                        float f13 = displayRect.bottom;
                        float f14 = displayRect.left;
                        PhotoViewDelegate.setMatrixArray(fArr, f11, f12, f11, f13, f14, f13, f14, f12);
                    } else if (this.mImageProcessor.is180Degree()) {
                        float f15 = displayRect.right;
                        float f16 = displayRect.bottom;
                        float f17 = displayRect.left;
                        float f18 = displayRect.top;
                        PhotoViewDelegate.setMatrixArray(fArr, f15, f16, f17, f16, f17, f18, f15, f18);
                    } else if (this.mImageProcessor.is270Degree()) {
                        float f19 = displayRect.left;
                        float f20 = displayRect.bottom;
                        float f21 = displayRect.top;
                        float f22 = displayRect.right;
                        PhotoViewDelegate.setMatrixArray(fArr, f19, f20, f19, f21, f22, f21, f22, f20);
                    }
                }
                float[] fArr4 = fArr;
                this.mConfig.mMatrix.setPolyToPoly(fArr2, 0, fArr4, 0, 4);
            }
        }
    }

    public boolean isContentChanged(MediaItem mediaItem, Bitmap bitmap) {
        if (!mediaItem.isVideo()) {
            return super.isContentChanged(mediaItem, bitmap);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = mediaItem.getWidth();
        int height2 = mediaItem.getHeight();
        if (needToSwap(width, height, width2, height2)) {
            return this.mImageProcessor.hasDifferentSize(false, height2, width2);
        }
        return this.mImageProcessor.hasDifferentSize(false, width2, height2);
    }

    public boolean setFrame(int i2, int i7, int i8, int i10) {
        boolean frame = super.setFrame(i2, i7, i8, i10);
        if (frame) {
            resetScaleAndCenter();
        }
        return frame;
    }

    public void setOnCropImageMatrixChangedListener(OnCropImageMatrixChangedListener onCropImageMatrixChangedListener) {
        this.mOnCropImageMatrixChangedListener = onCropImageMatrixChangedListener;
    }

    public void updateCropImageMatrix() {
        if (this.mOnCropImageMatrixChangedListener != null) {
            if (this.mImageProcessor.getTileMap() != null && this.mImageProcessor.isBaseLayerReady()) {
                updateFullMatrix();
            }
            OnCropImageMatrixChangedListener onCropImageMatrixChangedListener = this.mOnCropImageMatrixChangedListener;
            ((CropViewImpl) ((p) onCropImageMatrixChangedListener).e).onImageMatrixChanged(getDisplayMatrix());
        }
    }
}
