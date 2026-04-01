package com.samsung.android.gallery.app.ui.viewer2.selection;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.gesture.GestureIdentifier;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SelectionViewHolder extends ImageViewHolder {
    private GestureIdentifier mGestureIdentifier;
    private final int mHeight;
    private int mResizedHeight;
    private int mResizedWidth;
    private final int mWidth;
    /* access modifiers changed from: private */
    public SelectionViewZoomDelegate mZoomDelegate;

    public SelectionViewHolder(View view, int i2, int i7, int i8) {
        super(view, i2);
        int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.burst_shot_select_image_margin) * 2;
        this.mWidth = i7 - dimensionPixelSize;
        this.mHeight = i8 - dimensionPixelSize;
        setCheckboxEnabled(true);
        if (PreferenceFeatures.Poc.SUPPORT_BURST_SHOT_ZOOM) {
            initZoomDelegate();
        }
    }

    public static SelectionViewHolder create(ViewGroup viewGroup, int i2) {
        return new SelectionViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_burstshot_select_layout, viewGroup, false), i2, viewGroup.getWidth(), viewGroup.getHeight());
    }

    private boolean hasDifferentResolution(Bitmap bitmap) {
        boolean z;
        boolean z3;
        if (!(bitmap == null || this.mMediaItem.getWidth() == 0 || this.mMediaItem.getHeight() == 0)) {
            if (this.mMediaItem.getWidth() > this.mMediaItem.getHeight()) {
                z = true;
            } else {
                z = false;
            }
            if (bitmap.getWidth() > bitmap.getHeight()) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z ^ z3) {
                return true;
            }
        }
        return false;
    }

    private void initLayout(Bitmap bitmap, int i2, int i7) {
        float f;
        boolean isRotated = isRotated(bitmap);
        float width = (float) this.mMediaItem.getWidth();
        float height = (float) this.mMediaItem.getHeight();
        if ((width <= 0.0f || height <= 0.0f) && bitmap != null) {
            width = (float) bitmap.getWidth();
            height = (float) bitmap.getHeight();
        }
        if (isRotated) {
            f = height / width;
        } else {
            f = width / height;
        }
        ViewGroup.LayoutParams layoutParams = this.mImageView.getLayoutParams();
        if (ResourceCompat.isPortrait(getContext())) {
            int min = (int) Math.min(((float) i2) / f, (float) i7);
            layoutParams.height = min;
            layoutParams.width = (int) (((float) min) * f);
        } else {
            int min2 = (int) Math.min(((float) i7) * f, (float) i2);
            layoutParams.width = min2;
            layoutParams.height = (int) (((float) min2) / f);
        }
        this.mResizedHeight = layoutParams.height;
        this.mResizedWidth = layoutParams.width;
        this.mImageView.setVisibility(0);
        this.mImageView.setLayoutParams(layoutParams);
        CheckBox checkBox = this.mCheckboxView;
        if (checkBox != null) {
            checkBox.setVisibility(0);
        }
    }

    private void initZoomDelegate() {
        this.mZoomDelegate = new SelectionViewZoomDelegate(getContext(), this.mImageView);
        this.mGestureIdentifier = new GestureIdentifier(getContext(), new GestureIdentifier.SimpleGestureListener() {
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (!SelectionViewHolder.this.isOriginalBitmapLoaded() || scaleFactor <= 1.0f) {
                    return true;
                }
                SelectionViewHolder.this.mZoomDelegate.start(SelectionViewHolder.this.getBindingAdapterPosition());
                return true;
            }
        });
    }

    private boolean isRotated(Bitmap bitmap) {
        int orientation = this.mMediaItem.getOrientation();
        if (orientation == 90 || orientation == 270 || hasDifferentResolution(bitmap)) {
            return true;
        }
        return false;
    }

    public boolean applyImageColorFilter() {
        return false;
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        setSupportDecoItemType(1);
        setThumbKind(ThumbKind.MEDIUM_KIND);
        setImageUid(mediaItem.getPath());
    }

    public void bindImage(Bitmap bitmap) {
        initLayout(bitmap, this.mWidth, this.mHeight);
        super.bindImage(bitmap);
    }

    public boolean isOriginalBitmapLoaded() {
        if (getThumbKind().size() <= ThumbKind.MEDIUM_KIND.size() || !hasBitmap()) {
            return false;
        }
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        GestureIdentifier gestureIdentifier = this.mGestureIdentifier;
        if (gestureIdentifier != null) {
            gestureIdentifier.onTouchEvent(motionEvent);
        }
        return super.onTouch(view, motionEvent);
    }

    public void setCheckboxEnabled(boolean z) {
        super.setCheckboxEnabled(z);
        CheckBox checkBox = this.mCheckboxView;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
    }

    public void setViewMatrix() {
        int i2;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            if (mediaItem.isBroken()) {
                i2 = 0;
            } else {
                i2 = this.mMediaItem.getOrientation();
            }
            ImageView imageView = this.mImageView;
            imageView.setImageMatrix(ImageMatrix.getMatrix(imageView, i2, this.mResizedWidth, this.mResizedHeight));
            ViewUtils.setViewShape(this.mImageView, 1, this.itemView.getResources().getDimension(R.dimen.burst_shot_select_image_radius));
        }
    }

    public String toString() {
        Object obj;
        MediaItem mediaItem = this.mMediaItem;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.TAG);
        sb2.append("{");
        sb2.append(getViewPosition());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getViewType());
        sb2.append(',');
        sb2.append(getThumbKind());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (mediaItem != null) {
            obj = Long.valueOf(mediaItem.getFileId());
        } else {
            obj = "null";
        }
        sb2.append(obj);
        sb2.append("}");
        return sb2.toString();
    }

    public void attach() {
    }

    public void detach() {
    }

    public void recycleBitmap(Bitmap bitmap) {
    }
}
