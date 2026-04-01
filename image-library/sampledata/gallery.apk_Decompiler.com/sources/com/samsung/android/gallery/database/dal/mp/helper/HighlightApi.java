package com.samsung.android.gallery.database.dal.mp.helper;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.table.MpHighlightView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighlightApi extends BaseImpl {
    public HighlightApi() {
        super(new QueryParams());
    }

    public Cursor getHighlightCoverCursor() {
        MpHighlightView mpHighlightView = new MpHighlightView(this.mParams);
        mpHighlightView.resetDefaultProjectionForCover();
        mpHighlightView.limit(8);
        return this.mQueryExecutor.getCursor(mpHighlightView.buildSelectQuery(), "getHighlightCoverCursor");
    }

    public Cursor getHighlightCursor() {
        return this.mQueryExecutor.getCursor(new MpHighlightView(this.mParams).buildSelectQuery(), "getHighlightCursor");
    }

    public String tag() {
        return "HighlightApi";
    }
}
