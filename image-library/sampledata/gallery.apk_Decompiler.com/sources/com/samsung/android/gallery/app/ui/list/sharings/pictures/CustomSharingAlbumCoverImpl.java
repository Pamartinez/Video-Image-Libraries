package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.crop.SmartCropUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeMediaItemHelper;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.appbar.CustomCover;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CustomSharingAlbumCoverImpl extends CustomCover {
    private ViewGroup mCoverLayout;
    private ImageView mOwnerIcon;

    public CustomSharingAlbumCoverImpl(View view, int i2) {
        super(view, i2);
    }

    private RectF getFaceCropRect(ImageView imageView, MediaItem mediaItem) {
        int i2;
        ArrayList cropRectRatioList = mediaItem.getCropRectRatioList();
        if (cropRectRatioList == null || cropRectRatioList.size() != 6) {
            return null;
        }
        if (((float) imageView.getHeight()) / ((float) imageView.getWidth()) >= 0.75f) {
            i2 = 3;
        } else {
            i2 = 5;
        }
        return (RectF) cropRectRatioList.get(i2);
    }

    private void setOwnerVisibility(boolean z) {
        int i2;
        ImageView imageView = this.mOwnerIcon;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(imageView, i2);
    }

    public void bindImage(MediaItem mediaItem, Bitmap bitmap) {
        super.lambda$bindImage$1(mediaItem, bitmap);
        setOwnerVisibility(MediaItemMde.isOwnedByMe(mediaItem));
        if (ResourceCompat.isLandscape((View) this.mCoverLayout)) {
            updateViewLayout(2);
        }
    }

    public void bindViews(View view) {
        this.mCoverLayout = (ViewGroup) view.findViewById(R.id.cover_container);
        this.mImageView = (ImageView) view.findViewById(R.id.cover_image);
        this.mTitleView = (TextView) view.findViewById(R.id.toolbar_title);
        this.mSubTitleView = (TextView) view.findViewById(R.id.toolbar_sub_title);
        this.mOwnerIcon = (ImageView) view.findViewById(R.id.owner_icon);
    }

    public Matrix createImageMatrix(ImageView imageView, MediaItem mediaItem) {
        int i2;
        RectF rectF;
        if (mediaItem.isVideo() || mediaItem.isBroken()) {
            i2 = 0;
        } else {
            i2 = mediaItem.getOrientation();
        }
        Rect rect = null;
        if (TextUtils.isEmpty(mediaItem.getPath()) || !MediaItemMde.isUserCoverItem(mediaItem)) {
            rectF = null;
        } else {
            rectF = RectUtils.stringToRectF(MediaItemMde.getSpaceCoverRectRatio(mediaItem));
        }
        if (!SmartCropUtils.isValidRect(rectF)) {
            rectF = getFaceCropRect(imageView, mediaItem);
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            if (SmartCropUtils.isValidRect(rectF)) {
                rect = SmartCropUtils.calcSmartCropRectForStory(new SmartCropUtils.CropInfo.Builder(rectF).setDrawableSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()).setViewSize((float) imageView.getWidth(), (float) imageView.getHeight()).setRotation(i2).build());
            }
            rect = RectUtils.getRotatedRect(rect, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), i2);
        }
        return ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, false).withCropRect(rect).withOrientation(i2).withOrientationTag(mediaItem.getOrientationTag()));
    }

    public void destroy() {
        super.destroy();
        this.mCoverLayout = null;
        this.mOwnerIcon = null;
    }

    public boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!super.equalsItem(mediaItem, mediaItem2) || MediaItemMde.isUserCoverItem(mediaItem) != MediaItemMde.isUserCoverItem(mediaItem2) || !TextUtils.equals(MediaItemMde.getSpaceCoverRectRatio(mediaItem), MediaItemMde.getSpaceCoverRectRatio(mediaItem2))) {
            return false;
        }
        if (!PreferenceFeatures.OneUi41.SHARING_LEADER_AUTHORITY_DELEGATION || MdeMediaItemHelper.isSameOwner(mediaItem, mediaItem2)) {
            return true;
        }
        return false;
    }

    public void handleOrientationChange(int i2) {
        updateViewLayout(i2);
        if (this.mMediaItem != null && this.mBitmap != null && !ResourceCompat.isLandscape((View) this.mCoverLayout)) {
            bindImage(this.mMediaItem, this.mBitmap);
        }
    }

    public void setImageVisibility(boolean z) {
        int i2;
        ViewGroup viewGroup = this.mCoverLayout;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(viewGroup, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0005, code lost:
        if (r2 == 1) goto L_0x0009;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateViewLayout(int r2) {
        /*
            r1 = this;
            android.graphics.Bitmap r0 = r1.mBitmap
            if (r0 == 0) goto L_0x0008
            r0 = 1
            if (r2 != r0) goto L_0x0008
            goto L_0x0009
        L_0x0008:
            r0 = 0
        L_0x0009:
            r1.setImageVisibility(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.sharings.pictures.CustomSharingAlbumCoverImpl.updateViewLayout(int):void");
    }

    public void updateViews() {
        super.updateViews();
        setOwnerVisibility(MediaItemMde.isOwnedByMe(this.mMediaItem));
    }
}
