package com.samsung.android.gallery.module.dataset.comparator;

import com.samsung.android.gallery.module.data.MediaItem;
import java.io.Serializable;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IAlbumComparator extends Comparator<MediaItem>, Serializable {
    IAlbumComparator setCollator() {
        return this;
    }
}
