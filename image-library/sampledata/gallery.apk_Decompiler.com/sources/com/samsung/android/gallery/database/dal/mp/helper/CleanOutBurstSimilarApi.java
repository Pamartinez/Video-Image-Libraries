package com.samsung.android.gallery.database.dal.mp.helper;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.table.MpCleanOutBurstSimilarView;
import com.samsung.android.gallery.database.dbtype.GroupType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CleanOutBurstSimilarApi extends BaseImpl {
    public CleanOutBurstSimilarApi() {
        super(new QueryParams());
        this.mParams.addGroupType(GroupType.BURST);
        this.mParams.addGroupType(GroupType.SIMILAR);
    }

    public Cursor getCleanOutBurstSimilarCoverCursor() {
        MpCleanOutBurstSimilarView mpCleanOutBurstSimilarView = new MpCleanOutBurstSimilarView(this.mParams);
        mpCleanOutBurstSimilarView.setProjectionForCover();
        mpCleanOutBurstSimilarView.limit(8);
        return this.mQueryExecutor.getCursor(mpCleanOutBurstSimilarView.buildSelectQuery(), "getCleanOutBurstSimilarCoverCursor");
    }

    public Cursor getCleanOutBurstSimilarCursor() {
        MpCleanOutBurstSimilarView mpCleanOutBurstSimilarView = new MpCleanOutBurstSimilarView(this.mParams);
        mpCleanOutBurstSimilarView.addProjectionForDuplicate();
        return this.mQueryExecutor.getCursor(mpCleanOutBurstSimilarView.buildSelectQuery(), "getCleanOutBurstSimilarCursor");
    }

    public String tag() {
        return "CleanOutBurstSimilarApi";
    }
}
