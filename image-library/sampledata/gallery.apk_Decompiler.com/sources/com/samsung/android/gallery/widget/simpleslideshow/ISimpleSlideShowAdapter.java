package com.samsung.android.gallery.widget.simpleslideshow;

import com.samsung.android.gallery.module.creature.people.PeopleData;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISimpleSlideShowAdapter {
    int getDataPosition(int i2);

    MediaItem getMediaItem(int i2);

    List<PeopleData> getPeopleData(int i2);

    boolean isDataPrepared();

    boolean prepareNext(int i2);

    void release();
}
