package com.samsung.android.gallery.database.dal.abstraction.table;

import N2.j;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.AddressBundleWrapper;
import i.C0212a;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SecLocationView extends BaseView {
    protected String mFileJoinCondition;

    public SecLocationView(QueryParams queryParams) {
        super(queryParams);
    }

    public void addFileJoingCondition(String str) {
        this.mFileJoinCondition = j.f(new StringBuilder(), this.mFileJoinCondition, " AND ", str);
    }

    public Query buildSelectQuery() {
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        return super.buildSelectQuery();
    }

    public void filterBurstShotBestImage() {
        this.mQueryBuilder.andCondition("(A.group_id <= 0 OR (A.group_id > 0 AND A.best_image = 1))");
    }

    public void filterClusterNames(String str) {
        C0212a.w("L.locality in (", str, ")", this.mQueryBuilder);
    }

    public void filterFileId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("A._id = " + j2);
    }

    public String getFilesJoinCondition() {
        return "A.latitude=L.latitude AND A.longitude=L.longitude AND L.locale='" + Locale.getDefault() + "'";
    }

    public String getTableNameRaw() {
        return "location";
    }

    public abstract String getTagTypeColumnName();

    public void groupByLocality() {
        this.mQueryBuilder.groupBy(AddressBundleWrapper.BUNDLE_KEY_LOCALITY);
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.having("max(A." + this.mFilesTable.getColumnDateTaken() + ")");
    }

    public void setDefaultCondition() {
        this.mQueryBuilder.andCondition("L.locality is not null and L.locality != '' ");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
        this.mQueryBuilder.addProjection("L.locality", "__subCategory");
        this.mQueryBuilder.addProjection(getTagTypeColumnName(), "__tagType");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mFilesTable.getTableName());
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addInnerJoin(getTableNameRaw() + " as L", getFilesJoinCondition());
    }

    public void setDefaultOrder() {
    }
}
