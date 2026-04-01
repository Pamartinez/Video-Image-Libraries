package com.samsung.android.gallery.module.dataset.tables;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.DefaultRecord;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMediaDataTable<T extends DefaultRecord> {
    MediaItem get(int i2);

    ClusterIndexer getClusterIndexer(int i2) {
        return null;
    }

    int getLoadedCount();
}
