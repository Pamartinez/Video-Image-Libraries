package com.samsung.android.gallery.app.ui.list.stories.category;

import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.IStoriesCategoryView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface IStoriesCategory2View extends IStoriesPinchView, IStoriesCategoryView {
    void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z);

    StoriesCategory2Header getHeader();

    int getTransitionRadius();
}
