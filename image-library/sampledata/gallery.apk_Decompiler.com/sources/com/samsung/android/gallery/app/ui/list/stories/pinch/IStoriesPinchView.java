package com.samsung.android.gallery.app.ui.list.stories.pinch;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesView;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.DimenHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IStoriesPinchView extends IStoriesView {
    int getDepthFromGridSize(int i2);

    DimenHelper getDimenHelper(Context context);

    GalleryGridLayoutManager getLayoutManager();

    int getTotalSelectableCount();

    boolean hasData();

    boolean isDrawerOpen();

    void onListItemFavoriteClick(View view, MediaItem mediaItem, int i2);

    void updateFavorite(int i2, int i7, boolean z);
}
