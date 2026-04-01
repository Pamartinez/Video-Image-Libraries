package com.samsung.android.gallery.database.dal.mp.table;

import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpStoryAppBarView extends MpStoryView {
    public MpStoryAppBarView(QueryParams queryParams) {
        super(queryParams);
    }

    public Query buildSelectQuery() {
        this.mQueryBuilder.andCondition("A.bucket_id NOT IN (select bucket_id from (select * from files where is_hide=1) where media_type in (1,3) and bucket_id not in (-1739773001,-508399832,-1313584517,540528482,-1117673071) group by bucket_id)");
        return new Query(this.mQueryBuilder);
    }

    public void groupByStoryAlbum() {
        this.mQueryBuilder.groupBy("S._id");
        String aliasColumnName = this.mFilesTable.getAliasColumnName(IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME);
        QueryBuilder queryBuilder = this.mQueryBuilder;
        StringBuilder q = C0086a.q("min(case when ", aliasColumnName, "=0 or ", aliasColumnName, " is null then 1 else ");
        q.append(aliasColumnName);
        q.append(" end)");
        queryBuilder.having(q.toString());
        this.mQueryBuilder.replaceProjectionByAlias("S.title", "__Title");
    }

    public void setDefaultCondition() {
        this.mQueryBuilder.andCondition("S.is_visible=1");
    }

    public void setDefaultProjection() {
        this.mFilesTable.resetProjectionForStoryAppBar();
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
        this.mQueryBuilder.addProjection("S._id", "__albumID");
        this.mQueryBuilder.addProjection("S.creation_time", "creation_time");
        this.mQueryBuilder.addProjection("S.coverRectRatio", "__storyCoverRectRatio");
    }

    public String tag() {
        return "MpStoryAppBarView";
    }
}
