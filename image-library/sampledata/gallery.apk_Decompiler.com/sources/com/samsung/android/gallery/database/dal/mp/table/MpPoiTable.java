package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpPoiTable extends DbTable {
    public MpPoiTable(QueryParams queryParams) {
        super(queryParams);
    }

    public void filterLocation(double d, double d2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("P.latitude = " + d);
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.andCondition("P.longitude = " + d2);
    }

    public String getTableNameRaw() {
        return "poi";
    }

    public void setDefaultCondition() {
        this.mQueryBuilder.andCondition("P.poi_name is not null");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("P.poi_name", "__poiName");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "P");
    }

    public String tag() {
        return "MpPoiTable";
    }

    public void onConstruct() {
    }

    public void setDefaultOrder() {
    }
}
