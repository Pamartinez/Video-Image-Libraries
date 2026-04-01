package com.samsung.android.gallery.database.dal.abstraction;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface CursorProvider {
    Cursor query(QueryParams queryParams);
}
