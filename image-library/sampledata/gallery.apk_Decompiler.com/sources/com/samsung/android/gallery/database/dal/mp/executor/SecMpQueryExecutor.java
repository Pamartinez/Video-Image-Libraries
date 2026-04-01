package com.samsung.android.gallery.database.dal.mp.executor;

import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.RawQueryExecutor;
import com.samsung.android.gallery.support.providers.MediaUri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SecMpQueryExecutor extends RawQueryExecutor {
    public Uri getRawQueryUri(String str) {
        return MediaUri.getInstance().getRawQueryUri(str);
    }

    public Cursor rawQuery(String str, String str2) {
        if (str.length() <= 1) {
            return null;
        }
        return query(getRawQueryUri(""), (String[]) null, str, (String[]) null, (String) null, str2);
    }

    public String tag() {
        return "SecMpQueryExecutor";
    }

    public Cursor rawQuery(Query query, String str) {
        return query(getRawQueryUri(query), (String[]) null, query.buildSql(), query.getQueryBuilder().getArgs(), (String) null, str);
    }
}
