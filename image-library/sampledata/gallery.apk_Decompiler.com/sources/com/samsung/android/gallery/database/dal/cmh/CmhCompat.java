package com.samsung.android.gallery.database.dal.cmh;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.CursorProvider;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.executor.CmhQueryExecutor;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhCompat {
    CmhMethodMap mMethodMap;
    QueryParams mQueryParams;

    public CmhCompat(QueryParams queryParams) {
        this.mQueryParams = queryParams;
        if (queryParams.inQueryExecutor == null) {
            queryParams.inQueryExecutor = new CmhQueryExecutor();
        }
        this.mMethodMap = new CmhMethodMap(this.mQueryParams);
    }

    public void dump(String str) {
        CmhQueryExecutor cmhQueryExecutor = new CmhQueryExecutor();
        cmhQueryExecutor.dumpTableToFile("files", str);
        cmhQueryExecutor.dumpTableToFile("visualart", str);
        cmhQueryExecutor.dumpTableToFile("story", str);
        cmhQueryExecutor.dumpTableToFile("story_map", str);
        cmhQueryExecutor.dumpTableToFile("delete_recommendation", str);
        if (Features.isEnabled(Features.SUPPORT_RECOMMEND_SIMILAR_CONTACT)) {
            cmhQueryExecutor.dumpTableToFile("contacts_recommendation", str);
        }
        if (Features.isEnabled(Features.SUPPORT_AUTO_UPDATING_ALBUM)) {
            cmhQueryExecutor.dumpTableToFile("auto_album", str);
            cmhQueryExecutor.dumpTableToFile("auto_album_map", str);
        }
    }

    public Cursor query() {
        CursorProvider cursorProvider = (CursorProvider) this.mMethodMap.get(this.mQueryParams.getDbKey());
        if (cursorProvider != null) {
            return cursorProvider.query(this.mQueryParams);
        }
        return null;
    }
}
