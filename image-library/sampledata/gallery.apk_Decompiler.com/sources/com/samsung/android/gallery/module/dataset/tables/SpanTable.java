package com.samsung.android.gallery.module.dataset.tables;

import android.database.Cursor;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpanTable extends DefaultTable<DefaultRecord> {
    public SpanTable(Cursor cursor) {
        super(cursor);
    }

    public MediaItem createMediaItem(Cursor cursor, int i2) {
        if (cursor.moveToPosition(i2)) {
            return MediaItemBuilder.createSpanCluster(cursor);
        }
        throw new ArrayIndexOutOfBoundsException(C0086a.i(i2, "fail to move : "));
    }

    public DefaultRecord createRecordInstance(MediaItem mediaItem) {
        return new DefaultRecord(mediaItem);
    }

    public int getFakeLoadingCount() {
        return 120;
    }

    public int getMaxPreloadCount() {
        return 9999999;
    }

    public String tag() {
        return "SpanTable";
    }

    public SpanTable(Cursor cursor, DefaultTable.OnLoadDoneListener onLoadDoneListener) {
        super(cursor, onLoadDoneListener);
    }
}
