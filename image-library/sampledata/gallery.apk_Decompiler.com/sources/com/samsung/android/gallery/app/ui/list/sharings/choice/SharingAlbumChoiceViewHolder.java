package com.samsung.android.gallery.app.ui.list.sharings.choice;

import M5.a;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumChoiceViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingAlbumChoiceViewHolder extends AlbumChoiceViewHolder {
    public SharingAlbumChoiceViewHolder(View view, int i2) {
        super(view, i2);
    }

    private Bitmap getReplacedThumbnail() {
        this.mMediaItem.setBroken(true);
        return ThumbnailLoader.getInstance().getDefaultAlbumImage(true ^ PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindImage$0(Bitmap bitmap) {
        int i2;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            boolean isEmpty = TextUtils.isEmpty(mediaItem.getPath());
            if (isEmpty) {
                bitmap = getReplacedThumbnail();
            }
            super.bindImage(bitmap);
            if (this.mMediaItem.getOrientation() < 0) {
                return;
            }
            if (!isEmpty) {
                RectF stringToRectF = RectUtils.stringToRectF(MediaItemMde.getSpaceCoverRectRatio(this.mMediaItem));
                if (this.mMediaItem.isVideo()) {
                    i2 = 0;
                } else {
                    i2 = this.mMediaItem.getOrientation();
                }
                setViewMatrix(stringToRectF, i2);
                return;
            }
            setViewMatrix(RectUtils.stringToRectF(""), this.mMediaItem.getOrientation());
        }
    }

    public void bindImage(Bitmap bitmap) {
        getImage().post(new a(2, this, bitmap));
    }
}
