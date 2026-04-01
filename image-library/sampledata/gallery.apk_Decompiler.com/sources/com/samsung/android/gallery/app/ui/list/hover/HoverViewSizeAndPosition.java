package com.samsung.android.gallery.app.ui.list.hover;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class HoverViewSizeAndPosition {
    private int mAdjustX;
    private int mAdjustY;
    private int mBottomInset;
    private int mHeight;
    private int mMinWidth;
    private Rect mParentRect;
    private int mRotation;
    private Point mViewPos;
    private int mWidth;

    private int calcLeft(ListViewHolder listViewHolder) {
        int i2;
        int width = this.mViewPos.x - ((this.mWidth / 2) - (listViewHolder.getImage().getWidth() / 2));
        int i7 = this.mWidth;
        int i8 = width + i7;
        int i10 = this.mParentRect.right;
        if (i8 < i10) {
            i2 = this.mViewPos.x - ((i7 / 2) - (listViewHolder.getImage().getWidth() / 2));
        } else {
            i2 = i10 - i7;
        }
        if (i2 < 0) {
            return this.mParentRect.left + this.mAdjustX;
        }
        int i11 = this.mWidth + i2;
        int i12 = this.mParentRect.right;
        int i13 = this.mAdjustX;
        if (i11 > i12 - i13) {
            return i2 - i13;
        }
        return i2;
    }

    private void calcSize(ViewGroup viewGroup, ListViewHolder listViewHolder) {
        this.mWidth = 0;
        this.mHeight = 0;
        this.mAdjustX = WindowUtils.getSystemInsetsStart(viewGroup.getRootWindowInsets()) + getAdjustX(viewGroup.getContext());
        this.mAdjustY = getAdjustY(viewGroup.getContext());
        this.mBottomInset = WindowUtils.getSystemInsetsBottom(viewGroup.getRootWindowInsets());
        this.mMinWidth = getMinWidth(viewGroup.getContext());
        if (isCroppedThumbnail(listViewHolder)) {
            setCorrectSizeFromOtherWays(listViewHolder);
        }
        if (isInvalidSize()) {
            setSizeFromBitmap(listViewHolder);
        }
        if (isRotated()) {
            swapWidthHeight();
        }
        changeSizeFitToRatio(viewGroup);
    }

    private void calcSizeForAlbum(ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        this.mWidth = context.getResources().getDimensionPixelSize(R.dimen.hovering_image_set_width);
        this.mHeight = context.getResources().getDimensionPixelSize(R.dimen.hovering_image_set_height);
        this.mAdjustX = WindowUtils.getSystemInsetsStart(viewGroup.getRootWindowInsets()) + context.getResources().getDimensionPixelSize(R.dimen.hovering_image_set_offset_xcoordinates_from_original_thumbnail);
        this.mBottomInset = WindowUtils.getSystemInsetsBottom(viewGroup.getRootWindowInsets());
        this.mAdjustY = context.getResources().getDimensionPixelSize(R.dimen.hovering_image_set_offset_ycoordinates_from_original_thumbnail);
    }

    private int calcTop() {
        int i2 = this.mViewPos.y;
        int i7 = this.mAdjustY;
        int i8 = this.mHeight;
        int i10 = i2 + i7 + i8;
        Rect rect = this.mParentRect;
        int i11 = i2 + i7;
        if (i10 >= rect.bottom - this.mBottomInset) {
            i11 -= i8;
        }
        if (i11 < 0) {
            return rect.top + i7;
        }
        return i11;
    }

    private void changeSizeFitToRatio(ViewGroup viewGroup) {
        int i2 = this.mHeight;
        int i7 = this.mWidth;
        if (i2 >= i7) {
            int hoverImageWidth = getHoverImageWidth(viewGroup.getContext());
            this.mHeight = hoverImageWidth;
            this.mWidth = (int) (((float) hoverImageWidth) * (((float) i7) / ((float) i2)));
        } else {
            int hoverImageWidth2 = getHoverImageWidth(viewGroup.getContext());
            this.mWidth = hoverImageWidth2;
            this.mHeight = (int) (((float) hoverImageWidth2) * (((float) i2) / ((float) i7)));
        }
        this.mWidth = Math.max(this.mWidth, this.mMinWidth);
    }

    private boolean isCroppedThumbnail(ListViewHolder listViewHolder) {
        if (listViewHolder.getThumbKind() == ThumbKind.SMALL_CROP_KIND) {
            return true;
        }
        return false;
    }

    private boolean isInvalidSize() {
        if (this.mWidth == 0 || this.mHeight == 0) {
            return true;
        }
        return false;
    }

    private boolean isRotated() {
        int i2 = this.mRotation;
        if (i2 == 90 || i2 == 270) {
            return true;
        }
        return false;
    }

    private void setCorrectSizeFromOtherWays(ListViewHolder listViewHolder) {
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem != null) {
            if (mediaItem.isImage()) {
                setSizeFromMediaItem(mediaItem);
                return;
            }
            setSizeFromMetaData(mediaItem);
            if (isInvalidSize()) {
                setSizeFromNormalSizeThumbnail(mediaItem);
            }
        }
    }

    private void setLayoutParams(ViewGroup viewGroup, ListViewHolder listViewHolder) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
        if (isRTL()) {
            marginLayoutParams.setMarginStart((this.mParentRect.right - calcLeft(listViewHolder)) - this.mWidth);
        } else {
            marginLayoutParams.setMarginStart(calcLeft(listViewHolder));
        }
        marginLayoutParams.topMargin = calcTop();
        marginLayoutParams.width = this.mWidth;
        marginLayoutParams.height = this.mHeight;
        viewGroup.setLayoutParams(marginLayoutParams);
    }

    private void setSizeFromBitmap(ListViewHolder listViewHolder) {
        this.mWidth = listViewHolder.getBitmap(false).getWidth();
        this.mHeight = listViewHolder.getBitmap(false).getHeight();
    }

    private void setSizeFromMediaItem(MediaItem mediaItem) {
        this.mWidth = mediaItem.getWidth();
        this.mHeight = mediaItem.getHeight();
    }

    private void setSizeFromMetaData(MediaItem mediaItem) {
        MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(mediaItem.getPath());
        this.mWidth = videoInfo.width;
        this.mHeight = videoInfo.height;
        this.mRotation = videoInfo.orientation;
    }

    private void setSizeFromNormalSizeThumbnail(MediaItem mediaItem) {
        Bitmap loadThumbnailSync = ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.MEDIUM_KIND);
        if (loadThumbnailSync != null) {
            this.mWidth = loadThumbnailSync.getWidth();
            this.mHeight = loadThumbnailSync.getHeight();
            this.mRotation = 0;
        }
    }

    private void swapWidthHeight() {
        int i2 = this.mWidth;
        this.mWidth = this.mHeight;
        this.mHeight = i2;
    }

    public int getAdjustX(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.hovering_image_offset_xcoordinates_from_original_thumbnail);
    }

    public int getAdjustY(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.hovering_image_offset_ycoordinates_from_original_thumbnail);
    }

    public int getHoverImageWidth(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.hovering_image_width);
    }

    public int getMinWidth(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.hovering_actionbar_button_gap) + (context.getResources().getDimensionPixelSize(R.dimen.hovering_actionbar_button_size) * 2);
        return (context.getResources().getDimensionPixelSize(R.dimen.hovering_actionbar_padding) * 2) + (context.getResources().getDimensionPixelSize(R.dimen.hovering_actionbar_margin_end) * 2) + dimensionPixelSize;
    }

    public boolean isRTL() {
        return Features.isEnabled(Features.IS_RTL);
    }

    public void set(ViewGroup viewGroup, ListViewHolder listViewHolder, Point point, Rect rect, int i2) {
        this.mViewPos = point;
        this.mParentRect = rect;
        this.mRotation = i2;
        calcSize(viewGroup, listViewHolder);
        setLayoutParams(viewGroup, listViewHolder);
    }

    public void setForAlbum(ViewGroup viewGroup, ListViewHolder listViewHolder, Point point, Rect rect) {
        this.mViewPos = point;
        this.mParentRect = rect;
        calcSizeForAlbum(viewGroup);
        setLayoutParams(viewGroup, listViewHolder);
    }
}
