package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpPersonTable extends MpCreatureTagTable {
    public MpPersonTable(QueryParams queryParams) {
        super(queryParams);
    }

    public void addContactRawIdProjection() {
        this.mQueryBuilder.addProjection("P.contact_raw_id", "__creatureContactRawId");
    }

    public void addRelationshipProjection() {
        this.mQueryBuilder.addProjection("P.relationship", "__creatureRelationship");
    }

    public void addUuidProjection() {
        this.mQueryBuilder.addProjection("P.uuid", "__creatureContactUuid");
    }

    public void filterContactRawId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("__creatureContactRawId = " + j2);
    }

    public void filterNoRelationship() {
        this.mQueryBuilder.andCondition("(P.relationship is null or P.relationship = '' )");
    }

    public String getAliasName() {
        return "P";
    }

    public String getTableNameRaw() {
        return "persons";
    }

    public void groupByRelationship() {
        this.mQueryBuilder.groupBy("relationship");
    }

    public void resetProjectionForRelationship() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("P.relationship", "__creatureRelationship");
    }

    public String tag() {
        return "MpPersonTable";
    }

    public void onConstruct() {
    }
}
