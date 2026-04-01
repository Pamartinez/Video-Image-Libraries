package com.samsung.android.gallery.app.ui.list.albums.viewholder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.blur.BlurImageInfo;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.PinchBlurImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFolderImageViewHolder extends ImageViewHolder {
    public AlbumFolderImageViewHolder(View view, int i2, boolean z) {
        super(view, i2, z);
    }

    public void bindImage(Bitmap bitmap) {
        if (this.mMediaItem == null) {
            return;
        }
        if (bitmap != null && equalBitmap(bitmap)) {
            return;
        }
        if (!PocFeatures.SUPPORT_LOCKED_ALBUM || !MediaItemUtil.containsLockedAlbum(this.mMediaItem)) {
            if (bitmap == null && this.mMediaItem.isEmptyAlbum()) {
                bitmap = ThumbnailLoader.getInstance().getDefaultAlbumImage(true);
            }
            super.bindImage(bitmap);
            return;
        }
        this.mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.mImageView.setImageBitmap(ThumbnailLoader.getInstance().getLockedAlbumImage());
        this.mImageView.setBackground((Drawable) null);
    }

    public void enableBlur(boolean z) {
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            ImageView imageView = this.mImageView;
            if (imageView instanceof PinchBlurImageView) {
                ((PinchBlurImageView) imageView).enableBlur(z);
            }
        }
    }

    public void enableSubAlbumBlur(boolean z, boolean z3, boolean z7, View view) {
        ImageView imageView = this.mImageView;
        if (imageView instanceof PinchBlurImageView) {
            PinchBlurImageView pinchBlurImageView = (PinchBlurImageView) imageView;
            if (z) {
                BlurImageInfo.Builder maxDepth = new BlurImageInfo.Builder().setFolder(true).setGridType(z3).setMaxDepth(z7);
                if (!z3) {
                    view = this.mImageView;
                }
                pinchBlurImageView.setBlurInfo(maxDepth.setTargetView(view).build());
            }
            pinchBlurImageView.enableBlur(z);
        }
    }
}
