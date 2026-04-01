package com.samsung.android.gallery.database.dal.mp.table;

import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MpCreatureFacesTable extends DbTable {
    public MpCreatureFacesTable(QueryParams queryParams) {
        super(queryParams);
    }

    public void addRecommendationIdProjection() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection(getAliasName() + ".recommended_id", "__creatureFaceRecommendedID");
    }

    public void filterCreatureId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getAliasName() + "." + getCreatureIdColumnName() + " = " + j2);
    }

    public void filterCustomCover() {
        this.mQueryBuilder.andCondition("__title_info>=900000000");
    }

    public void filterGroupId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getAliasName() + ".group_id = " + j2);
    }

    public void filterGroupIds(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getAliasName() + ".group_id in " + str);
    }

    public void filterHidden(int i2) {
        if (i2 == 0) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.andCondition("IFNULL(" + getAliasName() + ".hide, 0) = 0");
        } else if (i2 == 1) {
            QueryBuilder queryBuilder2 = this.mQueryBuilder;
            queryBuilder2.andCondition(getAliasName() + ".hide = 1");
        }
    }

    public void filterId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getFkForFiles() + "=" + j2);
    }

    public void filterNamed() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getAliasName() + "." + getCreatureIdColumnName() + " > 1");
    }

    public void filterRecommendedId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getAliasName() + ".recommended_id=" + j2);
    }

    public void filterUnifiedId(long j2) {
        String aliasName = getAliasName();
        StringBuilder sb2 = new StringBuilder("( ");
        sb2.append(aliasName + ".recommended_id=" + j2);
        sb2.append(" or ");
        if (j2 > 100000) {
            StringBuilder t = C0212a.t(aliasName, ".group_id=");
            t.append(j2 - 100000);
            sb2.append(t.toString());
        } else {
            StringBuilder t3 = C0212a.t(aliasName, ".");
            t3.append(getCreatureIdColumnName());
            t3.append("=");
            t3.append(j2);
            sb2.append(t3.toString());
        }
        sb2.append(" )");
        this.mQueryBuilder.andCondition(sb2.toString());
    }

    public void filterUnifiedIds(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getAliasName() + ".recommended_id in (" + str + ")");
    }

    public void filterUnnamed() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getAliasName() + "." + getCreatureIdColumnName() + " = 1");
    }

    public abstract String getAliasName();

    public abstract String getCreatureFaceDataColumnName();

    public abstract String getCreatureIdColumnName();

    public String getFkForFiles() {
        return getAliasName() + ".sec_media_id";
    }

    public void groupByGroupID() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.groupBy(getAliasName() + ".group_id");
    }

    public void groupByUnifiedId() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.groupBy(getAliasName() + ".recommended_id");
    }

    public void orderByFaceScore() {
        clearOrderBy();
        this.mQueryBuilder.addOrderBy("__title_info DESC");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addOrderBy(getAliasName() + ".sec_media_id DESC");
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.addOrderBy(getAliasName() + "._id ASC");
    }

    public void resetProjectionForAutoSelectCover() {
        this.mQueryBuilder.clearProjection();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection(getAliasName() + ".sec_media_id", "__absID");
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.addProjection(getAliasName() + ".group_id", "__creatureFaceGroupID");
        QueryBuilder queryBuilder3 = this.mQueryBuilder;
        queryBuilder3.addProjection("(CASE WHEN (" + getAliasName() + ".title_info>=900000000) THEN " + getAliasName() + ".title_info-1000000000 ELSE IFNULL(" + getAliasName() + ".title_info, 0) END)", "__title_info");
    }

    public void resetProjectionForGroupId() {
        this.mQueryBuilder.clearProjection();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection(getAliasName() + ".group_id");
    }

    public void resetProjectionForMediaId() {
        this.mQueryBuilder.clearProjection();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection(getAliasName() + ".sec_media_id", "__absID");
    }

    public void resetProjectionForTitleInfo() {
        this.mQueryBuilder.clearProjection();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection(getAliasName() + ".sec_media_id", "__absID");
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.addProjection("IFNULL(" + getAliasName() + ".title_info, 0)", "__title_info");
    }

    public void setDefaultCondition() {
        String aliasName = getAliasName();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        StringBuilder q = C0086a.q("(", aliasName, ".recommended_id > 1 and ", aliasName, ".recommended_id != 100000 and ");
        q.append(aliasName);
        q.append(".group_id > 0)");
        queryBuilder.andCondition(q.toString());
    }

    public void setDefaultOrder() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addOrderBy(getAliasName() + "._id DESC");
    }

    public void setDefaultProjection() {
        String aliasName = getAliasName();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection(aliasName + ".group_id", "__creatureFaceGroupID");
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        StringBuilder t = C0212a.t(aliasName, ".");
        t.append(getCreatureFaceDataColumnName());
        queryBuilder2.addProjection(t.toString(), "__creatureFaceData");
        QueryBuilder queryBuilder3 = this.mQueryBuilder;
        queryBuilder3.addProjection(aliasName + ".pos_ratio", "__creatureFacePosRatio");
        QueryBuilder queryBuilder4 = this.mQueryBuilder;
        StringBuilder t3 = C0212a.t(aliasName, ".");
        t3.append(getCreatureIdColumnName());
        queryBuilder4.addProjection(t3.toString(), "__creatureID");
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            addRecommendationIdProjection();
        }
        if (this.IS_GTE_T && PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
            QueryBuilder queryBuilder5 = this.mQueryBuilder;
            queryBuilder5.addProjection(aliasName + ".hide", "__creatureFaceHide");
        }
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), getAliasName());
    }
}
