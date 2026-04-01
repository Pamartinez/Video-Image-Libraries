package com.samsung.android.gallery.app.ui.map.staticmarker;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.window.embedding.c;
import c7.C0445a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MarkerViewHolder {
    private ImageView mBackground;
    private Bitmap mBitmap;
    private ImageView mImageView;
    private View.OnLayoutChangeListener mLayoutChangedListener;
    private MediaItem mMediaItem;
    private View mRootView;

    public MarkerViewHolder(View view) {
        this.mRootView = view;
        this.mImageView = (ImageView) view.findViewById(R.id.map_view_marker);
        this.mBackground = (ImageView) view.findViewById(R.id.map_view_marker_background);
        View findViewById = view.findViewById(R.id.map_view_marker_count);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
    }

    private void addLayoutChangeListener(Bitmap bitmap) {
        C0445a aVar = new C0445a(this, bitmap);
        this.mLayoutChangedListener = aVar;
        this.mImageView.addOnLayoutChangeListener(aVar);
    }

    /* access modifiers changed from: private */
    /* renamed from: bindImageInternal */
    public void lambda$addLayoutChangeListener$0(Bitmap bitmap) {
        boolean z;
        Drawable drawable;
        ImageView.ScaleType scaleType;
        boolean z3 = true;
        if (this.mMediaItem == null || (bitmap != null && bitmap == this.mBitmap)) {
            StringBuilder sb2 = new StringBuilder("bindImage skip {");
            if (this.mMediaItem != null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            if (bitmap != this.mBitmap) {
                z3 = false;
            }
            sb2.append(z3);
            sb2.append("}");
            Log.d("MarkerViewHolder", sb2.toString());
            return;
        }
        recycleBitmap();
        if (bitmap == null) {
            Log.w("MarkerViewHolder", "bindImage broken {" + this.mMediaItem.getFileId() + "}");
            this.mMediaItem.setBroken(true);
            drawable = ResourceUtil.getBrokenDrawable(this.mRootView.getContext(), ThumbnailLoader.getInstance().getReplacedThumbnail(this.mImageView.getContext(), ResourceUtil.getBrokenDrawable(this.mMediaItem), ResourceUtil.getBrokenBgColor(this.mMediaItem)));
        } else {
            drawable = null;
        }
        if (drawable != null) {
            this.mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            this.mImageView.setImageDrawable(drawable);
            return;
        }
        this.mBitmap = bitmap;
        ImageView imageView = this.mImageView;
        if (!isViewValid(imageView) || this.mMediaItem.isScenesOrCreature()) {
            scaleType = ImageView.ScaleType.CENTER_CROP;
        } else {
            scaleType = ImageView.ScaleType.MATRIX;
        }
        imageView.setScaleType(scaleType);
        this.mImageView.setImageBitmap(bitmap);
        setViewMatrix(this.mImageView, this.mMediaItem);
    }

    private void clearLayoutChangeListener() {
        View.OnLayoutChangeListener onLayoutChangeListener = this.mLayoutChangedListener;
        if (onLayoutChangeListener != null) {
            this.mImageView.removeOnLayoutChangeListener(onLayoutChangeListener);
            this.mLayoutChangedListener = null;
        }
    }

    private boolean isViewValid(View view) {
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addLayoutChangeListener$1(Bitmap bitmap, View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        this.mImageView.post(new c(12, this, bitmap));
        clearLayoutChangeListener();
    }

    private void setViewMatrix(ImageView imageView, MediaItem mediaItem) {
        int i2;
        Rect rect;
        if (mediaItem != null && imageView.getScaleType() == ImageView.ScaleType.MATRIX) {
            boolean z = false;
            if (mediaItem.isVideo() || mediaItem.isBroken()) {
                i2 = 0;
            } else {
                i2 = mediaItem.getOrientation();
            }
            int intrinsicWidth = imageView.getDrawable().getIntrinsicWidth();
            int intrinsicHeight = imageView.getDrawable().getIntrinsicHeight();
            RectF cropRectRatio = mediaItem.getCropRectRatio();
            if (!RectUtils.isValidRect(cropRectRatio) || intrinsicWidth == intrinsicHeight) {
                rect = null;
            } else {
                rect = RectUtils.getSmartCropRect(imageView.getDrawable(), cropRectRatio, i2, true);
            }
            if (!mediaItem.isCreature() && !mediaItem.isPanoramic() && !mediaItem.isCustomCover()) {
                z = true;
            }
            imageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, z).withCropRect(rect).withOrientation(i2).withOrientationTag(mediaItem.getOrientationTag())));
        }
    }

    public void bind(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
    }

    public void bindImage(Bitmap bitmap) {
        if (isViewValid(this.mImageView)) {
            lambda$addLayoutChangeListener$0(bitmap);
            return;
        }
        clearLayoutChangeListener();
        addLayoutChangeListener(bitmap);
    }

    public void recycle() {
        this.mRootView.setVisibility(8);
        this.mImageView.setImageDrawable((Drawable) null);
        this.mImageView.setImageMatrix((Matrix) null);
        clearLayoutChangeListener();
        this.mMediaItem = null;
        recycleBitmap();
    }

    public void recycleBitmap() {
        if (this.mBitmap != null) {
            ThumbnailLoader.getInstance().recycle((String) null, this.mBitmap);
            this.mBitmap = null;
        }
    }

    public void setVisibility(int i2) {
        if (i2 == 0) {
            this.mRootView.bringToFront();
        }
        if (this.mRootView.getVisibility() == i2) {
            return;
        }
        if (i2 == 0) {
            SimpleAnimator.setVisibility(this.mRootView, 0, 120);
        } else {
            this.mRootView.setVisibility(i2);
        }
    }
}
