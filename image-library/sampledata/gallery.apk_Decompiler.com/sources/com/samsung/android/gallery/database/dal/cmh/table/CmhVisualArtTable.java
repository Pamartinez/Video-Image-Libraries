package com.samsung.android.gallery.database.dal.cmh.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhVisualArtTable extends DbTable {
    public CmhVisualArtTable(QueryParams queryParams) {
        super(queryParams);
    }

    public void filterCmhFileId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("VA.fileid=" + j2);
    }

    public String getTableNameRaw() {
        return "visualart_view";
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("VA.cover", "__absID");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "VA");
    }

    public String tag() {
        return "CmhVisualArtTable";
    }

    public void onConstruct() {
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
