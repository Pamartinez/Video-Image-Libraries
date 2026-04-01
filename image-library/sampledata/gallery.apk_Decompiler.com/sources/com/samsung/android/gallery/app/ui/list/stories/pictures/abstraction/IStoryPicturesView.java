package com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IStoryPicturesView extends IStoryPicturesHeaderView {
    PreviewViewHolder getPreviewableViewHolder();

    MediaItem getStoryAlbumById(int i2);

    boolean isEqualItem(MediaItem mediaItem, MediaItem mediaItem2);

    boolean isOptionMenuOpened();

    void notifyFooterChanged(View view);

    void notifyStoriesDataChanged();

    boolean onHandleOptionMenu(ListViewHolder listViewHolder, int i2);

    void onOptionSaveSelected(ListViewHolder listViewHolder);

    void onOptionShareSelected(ListViewHolder listViewHolder);

    void onRelatedItemClick(ListViewHolder listViewHolder, MediaItem mediaItem);

    void onTagViewSelected(MediaItem mediaItem);

    void onTransitionEnd();
}
