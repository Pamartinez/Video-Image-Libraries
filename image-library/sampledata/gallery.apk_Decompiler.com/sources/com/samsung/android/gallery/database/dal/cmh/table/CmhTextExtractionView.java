package com.samsung.android.gallery.database.dal.cmh.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhTextExtractionView extends BaseView {
    public CmhTextExtractionView(QueryParams queryParams) {
        super(queryParams);
    }

    public SecFilesTable createFilesTable() {
        return new CmhFilesTable(this.mParams);
    }

    public void filterSecMediaId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A.sec_media_id=" + j2);
    }

    public void onConstruct() {
        super.onConstruct();
        this.mFilesTable.clearSelection();
        this.mFilesTable.filterMountedVolume();
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("is_liveText", "__is_live_text");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mFilesTable.getTableName());
    }

    public String tag() {
        return "CmhTextExtractionView";
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
