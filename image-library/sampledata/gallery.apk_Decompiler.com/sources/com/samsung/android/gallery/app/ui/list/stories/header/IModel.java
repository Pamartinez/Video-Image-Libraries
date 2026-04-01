package com.samsung.android.gallery.app.ui.list.stories.header;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IModel {
    List<Integer> getAllAlbumIds();

    int getCount();

    int getDividerPosition();

    MediaItem getMediaItem(int i2);

    MediaItem getMediaItemByAlbumId(int i2);

    Integer getYear(MediaItem mediaItem);

    boolean isDivider(int i2);

    void open();

    void setDataChangeListener(PinModelListener pinModelListener);
}
