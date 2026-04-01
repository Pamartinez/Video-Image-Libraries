package com.samsung.android.gallery.app.ui.list.albums.choice;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFolderChoiceViewHolder extends AlbumChoiceViewHolder {
    public AlbumFolderChoiceViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void setEnable(boolean z) {
        float f;
        if (this.mMediaItem != null) {
            ImageView imageView = this.mImageView;
            float f5 = 0.5f;
            if (z) {
                f = 1.0f;
            } else {
                f = 0.5f;
            }
            ViewUtils.setAlpha(imageView, f);
            ViewGroup viewGroup = this.mTitleContainer;
            if (z) {
                f5 = 1.0f;
            }
            ViewUtils.setAlpha(viewGroup, f5);
            if (this.mMediaItem.isEmptyAlbum()) {
                this.itemView.setEnabled(true);
            } else {
                this.itemView.setEnabled(z);
            }
            if (this.mCheckboxView == null) {
                return;
            }
            if (this.mMediaItem.isFolder()) {
                ViewUtils.setVisibleOrInvisible(this.mCheckboxView, false);
            } else {
                this.mCheckboxView.setEnabled(z);
            }
        }
    }
}
