package com.samsung.android.gallery.app.ui.list.albums.viewholder;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumTitleCountReorderHolder extends AlbumTitleCountReorderHolder {
    public MxAlbumTitleCountReorderHolder(View view, int i2, boolean z, boolean z3) {
        super(view, i2, z, z3);
    }

    public void bindCountView(MediaItem mediaItem) {
        if (mediaItem.isSharing()) {
            this.mCountText.setText(MdeGroupHelper.getCountString(getContext(), mediaItem, -1));
        } else {
            super.bindCountView(mediaItem);
        }
    }

    public void bindImage(Bitmap bitmap) {
        MediaItem mediaItem;
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || (mediaItem = this.mMediaItem) == null || !mediaItem.isSharing() || (this.mMediaItem.getCount() != 0 && !TextUtils.isEmpty(this.mMediaItem.getPath()))) {
            super.bindImage(bitmap);
        } else {
            super.bindImage(ThumbnailLoader.getInstance().getDefaultAlbumImage(false));
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mAlbumExpiryNoticeView = view.findViewById(R.id.album_expiry_notice_icon);
    }

    public void setFocusedFilterOnAdvancedMouseEvent(boolean z) {
        if (!PreferenceFeatures.OneUi7x.SUPPORT_MOUSE_USABILITY_V2 || !this.mMediaItem.isSharing()) {
            super.setFocusedFilterOnAdvancedMouseEvent(z);
        }
    }

    public void setNewLabel(int i2) {
        boolean z;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || !mediaItem.isSharing()) {
            super.setNewLabel(i2);
            return;
        }
        if (MediaItemMde.getUnreadCount(this.mMediaItem) > 0) {
            z = true;
        } else {
            z = false;
        }
        updateNewLabelVisibility(z);
    }

    public void setViewMatrix() {
        int i2;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || !mediaItem.isSharing()) {
            super.setViewMatrix();
            return;
        }
        if (mediaItem.isVideo() || mediaItem.isBroken()) {
            i2 = 0;
        } else {
            i2 = mediaItem.getOrientation();
        }
        if (TextUtils.isEmpty(mediaItem.getPath()) || !MediaItemMde.isUserCoverItem(mediaItem)) {
            setViewMatrix((RectF) null, i2);
        } else {
            setViewMatrix(RectUtils.stringToRectF(MediaItemMde.getSpaceCoverRectRatio(mediaItem)), i2);
        }
    }
}
