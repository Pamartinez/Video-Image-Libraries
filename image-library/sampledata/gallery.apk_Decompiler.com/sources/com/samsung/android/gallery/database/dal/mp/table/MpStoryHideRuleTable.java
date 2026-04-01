package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpStoryHideRuleTable extends DbTable {
    public MpStoryHideRuleTable(QueryParams queryParams) {
        super(queryParams);
    }

    public String getTableNameRaw() {
        return "hide_rule";
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("H._id", "__rule_id");
        this.mQueryBuilder.addProjection("H.data_provider", "__rule_provider");
        this.mQueryBuilder.addProjection("H.rule_type", "__rule_type");
        this.mQueryBuilder.addProjection("H.start_time", "__rule_startTime");
        this.mQueryBuilder.addProjection("H.end_time", "__rule_endTime");
        this.mQueryBuilder.addProjection("H.person_id", "__rule_personId");
        this.mQueryBuilder.addProjection("H.group_id", "__rule_groupId");
        this.mQueryBuilder.addProjection("H.private_data", "__rule_private_data");
        this.mQueryBuilder.addProjection("H.recommended_id", "__rule_recommended_id");
        if (PreferenceFeatures.OneUi6x.SUPPORT_NEW_HIDE_SCENE_SELECTION) {
            this.mQueryBuilder.addProjection("H.individual", "__rule_individual");
        }
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "H");
    }

    public void setRuleType(int i2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("H.rule_type=" + i2);
    }

    public String tag() {
        return "CmhStoryHideRuleTable";
    }

    public void onConstruct() {
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
