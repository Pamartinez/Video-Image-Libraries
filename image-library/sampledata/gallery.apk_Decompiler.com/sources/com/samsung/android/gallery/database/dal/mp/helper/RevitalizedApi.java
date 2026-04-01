package com.samsung.android.gallery.database.dal.mp.helper;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.table.MpRevitalizationView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RevitalizedApi extends BaseImpl {
    public RevitalizedApi() {
        super(new QueryParams());
    }

    public Cursor getRevitalizedCoverCursor() {
        MpRevitalizationView mpRevitalizationView = new MpRevitalizationView(this.mParams);
        mpRevitalizationView.resetDefaultProjectionForCover();
        return this.mQueryExecutor.getCursor(mpRevitalizationView.buildSelectQuery(), "getRevitalizedCoverCursor");
    }

    public Cursor getRevitalizedCursor() {
        return this.mQueryExecutor.getCursor(new MpRevitalizationView(this.mParams).buildSelectQuery(), "getRevitalizedCursor");
    }

    public String tag() {
        return "RevitalizedApi";
    }

    public Cursor getRevitalizedCursor(long j2) {
        MpRevitalizationView mpRevitalizationView = new MpRevitalizationView(this.mParams);
        mpRevitalizationView.filterFileId(j2);
        return this.mQueryExecutor.getCursor(mpRevitalizationView.buildSelectQuery(), "getRevitalizedCursor");
    }
}
