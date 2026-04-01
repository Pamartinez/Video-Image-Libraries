package com.samsung.android.gallery.database.dal.mp.impl;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.SuggestedApi;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.table.MpSuggestedCleanOutView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpSuggestedImpl extends BaseImpl implements SuggestedApi {
    public MpSuggestedImpl(QueryParams queryParams) {
        super(queryParams);
    }

    public Cursor getCleanOutDuplicatedCoverCursor() {
        MpSuggestedCleanOutView mpSuggestedCleanOutView = new MpSuggestedCleanOutView(this.mParams);
        mpSuggestedCleanOutView.resetProjectionForDuplicateCover();
        mpSuggestedCleanOutView.filterDeleteType(3);
        mpSuggestedCleanOutView.filterDeleteGroupID();
        mpSuggestedCleanOutView.limit(8);
        mpSuggestedCleanOutView.groupByFileId();
        mpSuggestedCleanOutView.orderByGroupID();
        return this.mQueryExecutor.getCursor(mpSuggestedCleanOutView.buildSelectQuery(), "getCleanOutDuplicatedCoverCursor");
    }

    public Cursor getCleanOutDuplicatedCursor() {
        MpSuggestedCleanOutView mpSuggestedCleanOutView = new MpSuggestedCleanOutView(this.mParams);
        mpSuggestedCleanOutView.addProjectionForDuplicate();
        mpSuggestedCleanOutView.filterDeleteType(3);
        mpSuggestedCleanOutView.filterDeleteGroupID();
        mpSuggestedCleanOutView.groupByFileId();
        mpSuggestedCleanOutView.orderByGroupID();
        return this.mQueryExecutor.getCursor(mpSuggestedCleanOutView.buildSelectQuery(), "getCleanOutDuplicatedCursor");
    }

    public Cursor getCleanOutSuggestedCoverCursor(boolean z, int i2) {
        MpSuggestedCleanOutView mpSuggestedCleanOutView = new MpSuggestedCleanOutView(this.mParams);
        if (i2 == -1) {
            mpSuggestedCleanOutView.resetProjectionForCover();
        } else {
            mpSuggestedCleanOutView.resetProjectionForCoverV2(i2);
            mpSuggestedCleanOutView.filterDeleteType(i2);
        }
        if (z) {
            mpSuggestedCleanOutView.limit(8);
        }
        return this.mQueryExecutor.getCursor(mpSuggestedCleanOutView.buildSelectQuery(), "getCleanOutSuggestedCoverCursor");
    }

    public Cursor getCleanOutSuggestedCursor(int i2) {
        MpSuggestedCleanOutView mpSuggestedCleanOutView = new MpSuggestedCleanOutView(this.mParams);
        mpSuggestedCleanOutView.filterDeleteType(i2);
        return this.mQueryExecutor.getCursor(mpSuggestedCleanOutView.buildSelectQuery(), "getCleanOutSuggestedCursor");
    }

    public Cursor getPortraitCoverCursor() {
        throw new UnsupportedOperationException("not supported by secmp");
    }

    public Cursor getPortraitCursor() {
        throw new UnsupportedOperationException("not supported by secmp");
    }

    public String tag() {
        return "MpSuggestedImpl";
    }
}
