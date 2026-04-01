package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import i.C0212a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpUserTagView extends BaseView {
    public MpUserTagView(QueryParams queryParams) {
        super(queryParams);
    }

    public void addOrderByCount() {
        this.mQueryBuilder.addOrderBy("count(A._id) DESC");
    }

    public void addOrderByDate() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addOrderBy("A." + this.mFilesTable.getColumnDateTaken() + " DESC, A._id DESC");
    }

    public void addProjectionForCount() {
        this.mQueryBuilder.addProjection("count(distinct A._id)", "__count");
    }

    public Query buildSelectQuery() {
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        return super.buildSelectQuery();
    }

    public SecFilesTable createFilesTable() {
        return new MpFilesTable(this.mParams);
    }

    public void filterBurstShotBestImage(boolean z) {
        this.mFilesTable.filterGroupMediaBest(true);
        if (z) {
            this.mFilesTable.addGroupMediaCountProjection(this.mQueryBuilder);
        }
    }

    public void filterFileIds(String str) {
        this.mFilesTable.filterIds(str);
    }

    public void filterTagData(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("U.tag = '" + QueryBuilder.escapeString(str) + "'");
    }

    public void filterTagNames(String str) {
        C0212a.w("U.tag in (", str, ")", this.mQueryBuilder);
    }

    public void filterValidTagData() {
        this.mQueryBuilder.andCondition("LENGTH(TRIM(U.tag))>0");
    }

    public void groupTagData() {
        this.mQueryBuilder.groupBy("U.tag");
    }

    public void havingLatestMedia() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.having("max(A." + this.mFilesTable.getColumnDateTaken() + ") and A.media_type in (1,3)");
    }

    public void includeFileIds(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.firstOrCondition("A._id IN (" + str + ")");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
        this.mQueryBuilder.addProjection("U.tag", "__subCategory");
        this.mQueryBuilder.addProjection("0", "__tagType");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable("usertag as U");
        this.mQueryBuilder.addCrossJoin(this.mFilesTable.getTableName(), "A._id=U.sec_media_id");
    }

    public String tag() {
        return "MpUserTagView";
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
