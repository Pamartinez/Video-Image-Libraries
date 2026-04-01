package com.samsung.android.gallery.app.ui.list.suggestions.fixup;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PortraitPicturesViewHolder extends PreviewViewHolder {
    protected float mRoundRadius;

    public PortraitPicturesViewHolder(View view, int i2) {
        super(view, i2);
        this.mRoundRadius = view.getResources().getDimension(R.dimen.suggestions_item_round_radius);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        setThumbnailShape(1, this.mRoundRadius);
        addThumbnailBorder(getContext().getDrawable(R.drawable.sharings_thumbnail_border));
    }
}
