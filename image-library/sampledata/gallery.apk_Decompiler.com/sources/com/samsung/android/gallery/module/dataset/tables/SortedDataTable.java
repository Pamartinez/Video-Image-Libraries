package com.samsung.android.gallery.module.dataset.tables;

import android.database.Cursor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.comparator.ComparatorEx;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SortedDataTable<T extends ComparatorEx<MediaItem>> extends DataTable {
    private SortedCursorReader<T> mSortedCursorReader;

    public SortedDataTable(Cursor cursor, T t) {
        super(cursor);
        SortedCursorReader<T> sortedCursorReader = this.mSortedCursorReader;
        if (sortedCursorReader != null) {
            sortedCursorReader.sort(t);
        }
    }

    public CursorReader createCursorReader(Cursor cursor) {
        SortedCursorReader<T> sortedCursorReader = new SortedCursorReader<>(cursor);
        this.mSortedCursorReader = sortedCursorReader;
        return sortedCursorReader;
    }

    public String tag() {
        return "SortedDataTable";
    }
}
