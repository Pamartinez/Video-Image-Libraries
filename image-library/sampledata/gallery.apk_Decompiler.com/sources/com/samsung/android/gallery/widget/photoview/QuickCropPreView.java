package com.samsung.android.gallery.widget.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.abstraction.SharedViewElement;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuickCropPreView extends AppCompatImageView implements SharedViewElement, View.OnLayoutChangeListener {
    private Bitmap mBitmap;
    private MediaItem mMediaItem;
    private int mOrientation;

    public QuickCropPreView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private float getCornerRadius() {
        return getResources().getDimension(R$dimen.quick_crop_preview_radius);
    }

    private boolean isLandscapeBitmap(int i2, int i7) {
        int i8 = this.mOrientation;
        if (i8 == 90 || i8 == 270) {
            if (i7 > i2) {
                return true;
            }
            return false;
        } else if (i2 > i7) {
            return true;
        } else {
            return false;
        }
    }

    private void setViewMatrix() {
        int i2;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            i2 = mediaItem.getOrientationTag();
        } else {
            i2 = 0;
        }
        setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(this, true).withOrientation(this.mOrientation).withOrientationTag(i2)));
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (this.mBitmap != null) {
            setViewMatrix();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.mBitmap = bitmap;
    }

    public void setMediaItem(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
    }

    public void setOrientation(int i2) {
        this.mOrientation = i2;
    }

    public void updateLayoutSize() {
        int width;
        int height;
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null && (width = bitmap.getWidth()) != (height = this.mBitmap.getHeight())) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.quick_crop_preview_size);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.quick_crop_preview_max_size);
            if (isLandscapeBitmap(width, height)) {
                marginLayoutParams.width = dimensionPixelSize2;
                marginLayoutParams.height = dimensionPixelSize;
            } else {
                marginLayoutParams.width = dimensionPixelSize;
                marginLayoutParams.height = dimensionPixelSize2;
            }
            setLayoutParams(marginLayoutParams);
            Log.d("QuickCropPreView", "updateLayoutSize [w,h][" + marginLayoutParams.width + GlobalPostProcInternalPPInterface.SPLIT_REGEX + marginLayoutParams.height + "]");
        }
    }

    public QuickCropPreView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOrientation = 0;
        addOnLayoutChangeListener(this);
        ViewUtils.setViewShape(this, 1, getCornerRadius());
    }

    public ImageView getImage() {
        return this;
    }
}
