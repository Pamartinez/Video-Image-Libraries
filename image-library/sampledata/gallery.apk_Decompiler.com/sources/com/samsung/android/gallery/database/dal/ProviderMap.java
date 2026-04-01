package com.samsung.android.gallery.database.dal;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.CmhCompat;
import com.samsung.android.gallery.database.dal.mp.SecMpCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ProviderMap {
    private Cursor getCmhCursor(QueryParams queryParams) {
        return new CmhCompat(queryParams).query();
    }

    private Cursor getSecMpCursor(QueryParams queryParams) {
        return new SecMpCompat(queryParams).query();
    }

    public Cursor query(QueryParams queryParams) {
        String dbKey = queryParams.getDbKey();
        if (dbKey.startsWith("mp://")) {
            return getSecMpCursor(queryParams);
        }
        if (dbKey.startsWith("cmh://")) {
            return getCmhCursor(queryParams);
        }
        return null;
    }
}
