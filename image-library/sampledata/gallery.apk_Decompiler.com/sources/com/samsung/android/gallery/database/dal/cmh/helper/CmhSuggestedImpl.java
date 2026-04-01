package com.samsung.android.gallery.database.dal.cmh.helper;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.SuggestedApi;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.table.SuggestedCleanOutView;
import com.samsung.android.gallery.database.dal.cmh.table.SuggestedPortraitView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhSuggestedImpl extends CmhHelperBaseImpl implements SuggestedApi {
    public CmhSuggestedImpl(QueryParams queryParams) {
        super(queryParams);
    }

    public Cursor getCleanOutDuplicatedCoverCursor() {
        SuggestedCleanOutView suggestedCleanOutView = new SuggestedCleanOutView(this.mParams);
        suggestedCleanOutView.resetProjectionForDuplicateCover();
        suggestedCleanOutView.filterDeleteType(3);
        suggestedCleanOutView.filterDeleteGroupID();
        suggestedCleanOutView.limit(8);
        suggestedCleanOutView.groupByFileId();
        suggestedCleanOutView.orderByGroupID();
        return this.mQueryExecutor.getCursor(suggestedCleanOutView.buildSelectQuery(), "getCleanOutDuplicatedCoverCursor");
    }

    public Cursor getCleanOutDuplicatedCursor() {
        SuggestedCleanOutView suggestedCleanOutView = new SuggestedCleanOutView(this.mParams);
        suggestedCleanOutView.addProjectionForDuplicate();
        suggestedCleanOutView.filterDeleteType(3);
        suggestedCleanOutView.filterDeleteGroupID();
        suggestedCleanOutView.groupByFileId();
        suggestedCleanOutView.orderByGroupID();
        return this.mQueryExecutor.getCursor(suggestedCleanOutView.buildSelectQuery(), "getCleanOutDuplicatedCursor");
    }

    public Cursor getCleanOutSuggestedCoverCursor(boolean z, int i2) {
        SuggestedCleanOutView suggestedCleanOutView = new SuggestedCleanOutView(this.mParams);
        if (i2 == -1) {
            suggestedCleanOutView.resetProjectionForCover();
        } else {
            suggestedCleanOutView.resetProjectionForCoverV2(i2);
            suggestedCleanOutView.filterDeleteType(i2);
        }
        if (z) {
            suggestedCleanOutView.limit(8);
        }
        return this.mQueryExecutor.getCursor(suggestedCleanOutView.buildSelectQuery(), "getCleanOutSuggestedCoverCursor");
    }

    public Cursor getCleanOutSuggestedCursor(int i2) {
        SuggestedCleanOutView suggestedCleanOutView = new SuggestedCleanOutView(this.mParams);
        suggestedCleanOutView.filterDeleteType(i2);
        return this.mQueryExecutor.getCursor(suggestedCleanOutView.buildSelectQuery(), "getCleanOutSuggestedCursor");
    }

    public Cursor getPortraitCoverCursor() {
        SuggestedPortraitView suggestedPortraitView = new SuggestedPortraitView(this.mParams);
        suggestedPortraitView.resetProjectionForCover();
        suggestedPortraitView.limit(8);
        return this.mQueryExecutor.getCursor(suggestedPortraitView.buildSelectQuery(), "getPortraitCoverCursor");
    }

    public Cursor getPortraitCursor() {
        return this.mQueryExecutor.getCursor(new SuggestedPortraitView(this.mParams).buildSelectQuery(), "getPortraitCursor");
    }

    public String tag() {
        return "SuggestedApi";
    }
}
