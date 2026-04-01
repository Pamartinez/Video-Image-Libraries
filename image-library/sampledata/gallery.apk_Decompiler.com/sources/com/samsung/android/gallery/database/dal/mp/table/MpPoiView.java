package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpPoiView extends BaseView {
    public MpPoiView(QueryParams queryParams) {
        super(queryParams);
    }

    public Query buildSelectQuery() {
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        return super.buildSelectQuery();
    }

    public SecFilesTable createFilesTable() {
        return new MpFilesTable(this.mParams);
    }

    public void filterBurstShotBestImage() {
        this.mFilesTable.filterGroupMediaBest(true);
    }

    public void filterFileId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A._id = " + j2);
    }

    public String getFilesJoinCondition() {
        return "A.latitude=P.latitude AND A.longitude=P.longitude";
    }

    public String getTableNameRaw() {
        return "poi";
    }

    public void groupByPoi() {
        this.mQueryBuilder.groupBy("P.poi_name");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.having("max(A." + this.mFilesTable.getColumnDateTaken() + ") and A.media_type =1");
    }

    public void onConstruct() {
        super.onConstruct();
        this.mFilesTable.clearSelection();
        this.mFilesTable.filterHidden();
        this.mFilesTable.filterFileStatus();
        this.mFilesTable.filterMountedVolume();
    }

    public void setDefaultCondition() {
        this.mQueryBuilder.andCondition("P.poi_name is not null");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
        this.mQueryBuilder.addProjection("P.poi_name", "__subCategory");
        this.mQueryBuilder.addProjection("7", "__tagType");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mFilesTable.getTableName());
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addInnerJoin(getTableNameRaw() + " as P", getFilesJoinCondition());
    }

    public String tag() {
        return "MpPoiView";
    }

    public void setDefaultOrder() {
    }
}
