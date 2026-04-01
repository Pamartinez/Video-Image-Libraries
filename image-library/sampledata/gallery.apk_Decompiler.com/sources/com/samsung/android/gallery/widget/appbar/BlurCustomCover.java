package com.samsung.android.gallery.widget.appbar;

import Bb.g;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blur.BlurImageInfo;
import com.samsung.android.gallery.widget.PinchBlurImageView;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.function.Supplier;
import k6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BlurCustomCover extends CustomCover {
    private final View.OnLayoutChangeListener mImageViewLayoutChangeListener = new g(11, this);

    public BlurCustomCover(View view) {
        super(view);
    }

    private void addLayoutChangeListener() {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(this.mImageViewLayoutChangeListener);
        }
    }

    private boolean isCoverVisible() {
        Supplier<Boolean> supplier = this.mCoverVisibilitySupplier;
        if (supplier == null || !supplier.get().booleanValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (i10 - i7 != i14 - i12 || i8 - i2 != i13 - i11) {
            updateImageMatrix(this.mImageView, this.mMediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateImageView$1() {
        ImageView imageView;
        if (this.mView != null && (imageView = this.mImageView) != null && imageView.getWidth() == 0 && this.mImageView.getHeight() == 0) {
            this.mImageView.measure(View.MeasureSpec.makeMeasureSpec(this.mView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.mView.getHeight(), 1073741824));
            this.mImageView.layout(0, 0, this.mView.getWidth(), this.mView.getHeight());
        }
    }

    private void removeLayoutChangeListener() {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.removeOnLayoutChangeListener(this.mImageViewLayoutChangeListener);
        }
    }

    private void setTitleVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mTitleView, z);
        ViewUtils.setVisibleOrGone(this.mSubTitleView, z);
    }

    public void bindImage(MediaItem mediaItem, Bitmap bitmap) {
        if (bitmap == null) {
            bitmap = ThumbnailLoader.getInstance().getReplacedThumbnail(R$color.album_no_pic_background_color);
        }
        super.lambda$bindImage$1(mediaItem, bitmap);
        addLayoutChangeListener();
        updateViewLayout(this.mImageView.getResources().getConfiguration().orientation);
    }

    public void bindViews(View view) {
        this.mImageView = (ImageView) view.findViewById(R$id.cover_image);
        this.mTitleView = (TextView) view.findViewById(R$id.toolbar_title);
        this.mSubTitleView = (TextView) view.findViewById(R$id.toolbar_sub_title);
    }

    public void destroy() {
        super.destroy();
        removeLayoutChangeListener();
    }

    public ThumbKind getThumbKind() {
        return ThumbKind.XLARGE_NC_KIND;
    }

    public void handleOrientationChange(int i2) {
        updateViewLayout(i2);
        if (this.mMediaItem != null && this.mBitmap != null && isCoverVisible()) {
            bindImage(this.mMediaItem, this.mBitmap);
        }
    }

    public void setAlphaOnCoverView(float f) {
        this.mView.setY(0.0f);
        ViewUtils.setAlpha(this.mTitleView, f);
        ViewUtils.setAlpha(this.mSubTitleView, f);
    }

    public void setEnabled(boolean z) {
        int i2;
        View view = this.mView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        ViewUtils.setVisibility(view, i2);
    }

    public void updateBlur() {
        ImageView imageView = this.mImageView;
        if (imageView instanceof PinchBlurImageView) {
            PinchBlurImageView pinchBlurImageView = (PinchBlurImageView) imageView;
            pinchBlurImageView.setBlurInfo(new BlurImageInfo.Builder().setCustomCover(true).setEmptyAlbum(MediaItemUtil.isEmptyAlbum(this.mMediaItem)).build());
            pinchBlurImageView.enableBlur(true);
        }
    }

    public void updateImageView(boolean z) {
        View view = this.mView;
        if (view != null) {
            view.post(new b(4, this));
        }
        super.updateImageView(z);
    }

    public void updateTextView(TextView textView, CharSequence charSequence) {
        int i2;
        if (textView != null) {
            textView.setText(charSequence);
            if (charSequence == null || !isCoverVisible()) {
                i2 = 4;
            } else {
                i2 = 0;
            }
            textView.setVisibility(i2);
        }
    }

    public void updateViewLayout(int i2) {
        boolean z;
        boolean isCoverVisible = isCoverVisible();
        if (this.mBitmap == null || !isCoverVisible) {
            z = false;
        } else {
            z = true;
        }
        setImageVisibility(z);
        setTitleVisibility(isCoverVisible);
    }

    public BlurCustomCover(View view, int i2) {
        super(view, i2);
    }

    public void updateShape(ImageView imageView) {
    }
}
