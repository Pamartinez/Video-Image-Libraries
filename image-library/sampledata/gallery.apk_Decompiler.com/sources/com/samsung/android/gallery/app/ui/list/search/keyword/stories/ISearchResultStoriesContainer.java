package com.samsung.android.gallery.app.ui.list.search.keyword.stories;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISearchResultStoriesContainer {
    MediaData getMediaData();

    void onDataChangedOnUi(MediaData mediaData);

    void onStoriesClicked(ListViewHolder listViewHolder, MediaItem mediaItem);
}
