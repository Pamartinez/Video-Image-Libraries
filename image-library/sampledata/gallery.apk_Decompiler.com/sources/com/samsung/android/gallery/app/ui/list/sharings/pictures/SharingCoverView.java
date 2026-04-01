package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import B8.e;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SharingCoverView {
    private static final ThumbKind HEADER_THUMB_KIND = ThumbKind.MEDIUM_KIND;
    private final SharingPicturesCoverViewHolder mHeaderViewHolder;
    private final ISharingPicturesView mView;

    public SharingCoverView(ISharingPicturesView iSharingPicturesView, View view, MediaItem mediaItem) {
        this.mView = iSharingPicturesView;
        this.mHeaderViewHolder = new SharingPicturesCoverViewHolder(view, 0);
        bindItemToView(mediaItem);
    }

    /* access modifiers changed from: private */
    /* renamed from: bindImageToView */
    public void lambda$bindImageToView$0(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        SharingPicturesCoverViewHolder sharingPicturesCoverViewHolder = this.mHeaderViewHolder;
        if (sharingPicturesCoverViewHolder == null) {
            return;
        }
        if (ThreadUtil.isMainThread()) {
            sharingPicturesCoverViewHolder.bindImage(bitmap);
            this.mView.startEnlargeAnimation();
            return;
        }
        ThreadUtil.postOnUiThread(new b(this, bitmap, uniqueKey, thumbKind));
    }

    private void bindItemToView(MediaItem mediaItem) {
        this.mHeaderViewHolder.bind(mediaItem);
        if (TextUtils.isEmpty(mediaItem.getPath()) || TextUtils.isEmpty(mediaItem.getThumbCacheKey())) {
            Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(new ColorDrawable(getContext().getColor(R.color.shared_photo_cover_empty_color)), 50, 50);
            if (bitmapFromDrawable != null) {
                lambda$bindImageToView$0(bitmapFromDrawable, (UniqueKey) null, HEADER_THUMB_KIND);
                return;
            }
            return;
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        ThumbKind thumbKind = HEADER_THUMB_KIND;
        Bitmap memCache = instance.getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            lambda$bindImageToView$0(memCache, (UniqueKey) null, thumbKind);
        } else {
            instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new a(this));
        }
    }

    private Context getContext() {
        return this.mHeaderViewHolder.getRootView().getContext();
    }

    private void setGroup(MediaItem mediaItem) {
        setGroupInfo(mediaItem);
    }

    private void setGroupInfo(MediaItem mediaItem) {
        int i2;
        this.mHeaderViewHolder.getCountView().setText(StringCompat.toReadableCount(mediaItem.getCount()));
        if (this.mHeaderViewHolder.getMediaItem() != null) {
            ImageView ownerImageView = this.mHeaderViewHolder.getOwnerImageView();
            if (MediaItemMde.isOwnedByMe(this.mHeaderViewHolder.getMediaItem())) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            ownerImageView.setVisibility(i2);
        }
    }

    public View getView() {
        return this.mHeaderViewHolder.getRootView();
    }

    public void recycle() {
        SharingPicturesCoverViewHolder sharingPicturesCoverViewHolder = this.mHeaderViewHolder;
        if (sharingPicturesCoverViewHolder != null) {
            sharingPicturesCoverViewHolder.recycle();
        }
    }

    public void setDimming(float f) {
        this.mHeaderViewHolder.getDecoView(44).setAlpha(f);
    }

    public void setGroupInfoViewClickable(boolean z) {
        this.mHeaderViewHolder.getGroupInfoLayout().setClickable(z);
    }

    public void setSelectionMode(boolean z) {
        setGroupInfoViewClickable(!z);
    }

    public void updateGroup(MediaItem mediaItem) {
        setGroup(mediaItem);
    }

    public void updateItemCount(String str) {
        TextView subTitleView = this.mHeaderViewHolder.getSubTitleView();
        if (subTitleView != null) {
            subTitleView.setText(str);
        }
    }

    public void updateSpace(MediaItem mediaItem) {
        bindItemToView(mediaItem);
    }
}
