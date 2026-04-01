package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimilarContactTable extends DbTable {
    public SimilarContactTable(QueryParams queryParams) {
        super(queryParams);
    }

    public void filterGroupIds(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("C.face_group_id IN " + str);
    }

    public String getTableNameRaw() {
        return "contacts_recommendation";
    }

    public void setDefaultCondition() {
        this.mQueryBuilder.andCondition("C.contacts_id is not null");
        this.mQueryBuilder.andCondition("C.face_group_id > 0");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("C._id", "_id");
        this.mQueryBuilder.addProjection("C.contacts_id", "name_raw_contact_id");
        this.mQueryBuilder.addProjection("C.face_group_id", "__creatureFaceGroupID");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "C");
    }

    public String tag() {
        return "SimilarContactTable";
    }

    public void onConstruct() {
    }

    public void setDefaultOrder() {
    }
}
