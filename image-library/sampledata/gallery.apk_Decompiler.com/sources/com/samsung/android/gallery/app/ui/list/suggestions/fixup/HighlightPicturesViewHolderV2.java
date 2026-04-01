package com.samsung.android.gallery.app.ui.list.suggestions.fixup;

import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighlightPicturesViewHolderV2 extends HighlightPicturesViewHolder {
    public HighlightPicturesViewHolderV2(View view, int i2) {
        super(view, i2);
    }

    public void bindView(View view) {
        super.bindView(view);
        ViewUtils.setVisibleOrGone(view.findViewById(R.id.title_container), false);
    }

    public int getPreviewDuration() {
        MediaItem mediaItem = getMediaItem();
        if (MediaItemUtil.isHighLightClip(mediaItem)) {
            return (int) ((MediaItemUtil.getHighLightClipEndTime(mediaItem) / 1000) - (mediaItem.getVideoThumbStartTime() / 1000));
        }
        return super.getPreviewDuration();
    }

    public String updateItemInfo(MediaItem mediaItem) {
        return "";
    }

    public void bindTitle(String str) {
    }
}
