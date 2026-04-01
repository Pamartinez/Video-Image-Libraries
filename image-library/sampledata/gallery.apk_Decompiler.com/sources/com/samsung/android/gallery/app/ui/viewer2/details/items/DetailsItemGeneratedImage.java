package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.View;
import android.view.ViewStub;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemGeneratedImage extends DetailsItem implements DetailsViewUpdater {
    public DetailsItemGeneratedImage(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    public int getLayoutId() {
        return R.id.moreinfo_generated_image;
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.DEFAULT, this);
    }

    public boolean supportItem(MediaItem mediaItem) {
        return mediaItem.hasSefFileTypes(3473);
    }

    public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        View view = this.mItemView;
        if (view instanceof ViewStub) {
            this.mItemView = ((ViewStub) view).inflate();
        }
    }
}
