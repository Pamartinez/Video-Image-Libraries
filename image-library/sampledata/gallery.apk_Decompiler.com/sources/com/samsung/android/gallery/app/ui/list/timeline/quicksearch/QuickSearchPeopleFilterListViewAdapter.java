package com.samsung.android.gallery.app.ui.list.timeline.quicksearch;

import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class QuickSearchPeopleFilterListViewAdapter extends AbsQuickSearchFilterListViewAdapter {
    public QuickSearchPeopleFilterListViewAdapter(Blackboard blackboard) {
        super(blackboard);
    }

    public boolean getChecked(MediaItem mediaItem) {
        return this.mQuickSearchManager.isPeopleFiltered(IdentityCreatureUtil.getUnifiedIdentityId(mediaItem.getSubCategory()));
    }

    public int getLayoutId() {
        return R.layout.recycler_item_quick_search_people_filter_list_layout;
    }

    public ImageTitleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        ImageTitleViewHolder onCreateViewHolder = super.onCreateViewHolder(viewGroup, i2);
        onCreateViewHolder.setThumbnailShape(0, 0.0f);
        return onCreateViewHolder;
    }
}
