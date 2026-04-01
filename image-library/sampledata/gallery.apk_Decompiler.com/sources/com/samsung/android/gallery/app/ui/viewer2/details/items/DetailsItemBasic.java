package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.View;
import android.view.ViewStub;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsDataRefiner;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.details.DetailsUtil;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemBasic extends DetailsItem {
    /* access modifiers changed from: private */
    public String mDate;
    /* access modifiers changed from: private */
    public String mDisplayName;
    /* access modifiers changed from: private */
    public String mPath;

    public DetailsItemBasic(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    private DetailsViewUpdater createDateUpdater() {
        return new DetailsViewUpdater() {
            public void refinedItemInternal(MediaItem mediaItem) {
                DetailsItemBasic.this.mDate = DetailsDataRefiner.getDateString(mediaItem, "ㆍ", AppResources.getString(R.string.no_date_information));
            }

            public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
                DetailsItemBasic detailsItemBasic = DetailsItemBasic.this;
                detailsItemBasic.setTextAndVisibility((int) R.id.moreinfo_date, (CharSequence) detailsItemBasic.mDate);
            }
        };
    }

    private DetailsViewUpdater createDefaultUpdater() {
        return new DetailsViewUpdater() {
            public void refinedItemInternal(MediaItem mediaItem) {
                if (!DetailsUtil.supportFileDataLoad(mediaItem)) {
                    DetailsItemBasic.this.mDate = DetailsDataRefiner.getDateString(mediaItem, "ㆍ", AppResources.getString(R.string.no_date_information));
                }
                if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !mediaItem.isPrivateItem()) {
                    DetailsItemBasic.this.mDisplayName = DetailsDataRefiner.getDisplayName(mediaItem);
                    DetailsItemBasic.this.mPath = DetailsDataRefiner.getPathString(mediaItem);
                    return;
                }
                DetailsItemBasic.this.mDisplayName = mediaItem.getDisplayName();
                DetailsItemBasic.this.mPath = DetailsDataRefiner.getPathString(mediaItem);
            }

            public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
                if (!DetailsUtil.supportFileDataLoad(mediaItem)) {
                    DetailsItemBasic detailsItemBasic = DetailsItemBasic.this;
                    detailsItemBasic.setTextAndVisibility((int) R.id.moreinfo_date, (CharSequence) detailsItemBasic.mDate);
                }
                DetailsItemBasic detailsItemBasic2 = DetailsItemBasic.this;
                detailsItemBasic2.setTextAndVisibility((int) R.id.moreinfo_title, (CharSequence) detailsItemBasic2.mDisplayName);
                DetailsItemBasic detailsItemBasic3 = DetailsItemBasic.this;
                detailsItemBasic3.setTextAndVisibility((int) R.id.moreinfo_path, (CharSequence) detailsItemBasic3.mPath);
                DetailsItemBasic.this.updateDateViewPadding();
            }
        };
    }

    /* access modifiers changed from: private */
    public void updateDateViewPadding() {
        int i2;
        if (!(this.mItemView instanceof ViewStub)) {
            View findViewById = this.mDetailsView.findViewById(R.id.moreinfo_item_edit_btn);
            View findViewById2 = this.mItemView.findViewById(R.id.moreinfo_date);
            if (supportLargeScreenHorizontalGui()) {
                i2 = 0;
            } else {
                i2 = ViewUtils.getMeasuredWidth(findViewById);
            }
            ViewMarginUtils.setEndPadding(findViewById2, i2);
        }
    }

    public int getLayoutId() {
        return R.id.moreinfo_basic_info_simple;
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.DEFAULT, createDefaultUpdater());
        this.mViewUpdaterMap.put(DetailsUpdateKey.FILE_DATA, createDateUpdater());
    }

    public boolean supportItem(MediaItem mediaItem) {
        return true;
    }

    public void updateLayout() {
        super.updateLayout();
        updateDateViewPadding();
    }
}
