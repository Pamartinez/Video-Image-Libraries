package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MpCreatureTagTable extends DbTable {
    public MpCreatureTagTable(QueryParams queryParams) {
        super(queryParams);
    }

    public void addContactRawIdProjection() {
        this.mQueryBuilder.addProjection("(NULL)", "__creatureContactRawId");
    }

    public void addRelationshipProjection() {
        this.mQueryBuilder.addProjection("(NULL)", "__creatureRelationship");
    }

    public void addUuidProjection() {
        this.mQueryBuilder.addProjection("(NULL)", "__creatureContactUuid");
    }

    public void filterCreatureId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("__creatureID = " + j2);
    }

    public abstract String getAliasName();

    public void setDefaultProjection() {
        String aliasName = getAliasName();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection(aliasName + "._id", "__creatureID");
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.addProjection(aliasName + ".name", "__creatureName");
        addContactRawIdProjection();
        if (Features.isEnabled(Features.SUPPORT_RELATIONSHIP_SEARCH)) {
            addRelationshipProjection();
        }
        if (Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK)) {
            addUuidProjection();
        }
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), getAliasName());
    }

    public void filterNoRelationship() {
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
