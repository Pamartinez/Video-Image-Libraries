package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ViewHolderCreatures extends ImageTitleViewHolder {
    public ViewHolderCreatures(View view, int i2) {
        super(view, i2);
        setThumbnailShape(0, 0.0f);
    }

    public void bind(MediaItem mediaItem) {
        String str;
        super.bind(mediaItem);
        this.mTitleText.setVisibility(0);
        TextView textView = this.mTitleText;
        if (TextUtils.isEmpty(mediaItem.getTitle())) {
            str = "?";
        } else {
            str = mediaItem.getTitle();
        }
        textView.setText(str);
    }

    public String getContentDescription() {
        MediaItem mediaItem = getMediaItem();
        if (mediaItem == null || TextUtils.isEmpty(mediaItem.getTitle())) {
            return getString(R.string.untagged_face);
        }
        return super.getContentDescription();
    }
}
