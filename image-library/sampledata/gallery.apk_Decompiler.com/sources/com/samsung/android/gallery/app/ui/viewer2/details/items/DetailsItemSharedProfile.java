package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.mde.MdeMediaItemHelper;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemSharedProfile extends DetailsItem implements DetailsViewUpdater {
    private String mCreatedTime;
    private String mCreatorName;
    private boolean mModifiedBy;
    private String mModifiedTime;
    private String mModifierName;

    public DetailsItemSharedProfile(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    public int getLayoutId() {
        return R.id.moreinfo_shared_profile;
    }

    public void refinedItemInternal(MediaItem mediaItem) {
        this.mModifierName = MdeMediaItemHelper.getLastModifierName(mediaItem);
        this.mModifiedTime = TimeUtil.getLocalizedDateTime(MediaItemMde.getModifiedTime(mediaItem));
        this.mModifiedBy = MediaItemMde.isModified(mediaItem);
        this.mCreatorName = MdeMediaItemHelper.getCreatorName(mediaItem);
        this.mCreatedTime = TimeUtil.getLocalizedDateTime(MediaItemMde.getCreatedTime(mediaItem));
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.DEFAULT, this);
    }

    public boolean supportItem(MediaItem mediaItem) {
        return mediaItem.isSharing();
    }

    public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        setTextAndVisibility((int) R.id.creator_name, (CharSequence) this.mCreatorName);
        setTextAndVisibility((int) R.id.created_time, (CharSequence) this.mCreatedTime);
        if (this.mModifiedBy) {
            ViewUtils.setVisibility(getView(R.id.modified_by), 0);
            setTextAndVisibility((int) R.id.modifier_name, (CharSequence) this.mModifierName);
            setTextAndVisibility((int) R.id.modified_time, (CharSequence) this.mModifiedTime);
            return;
        }
        ViewUtils.setVisibility(getView(R.id.modified_by), 8);
    }
}
