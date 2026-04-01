package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.support.config.SdkConfig;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpStoryHideRuleView extends BaseView {
    private MpStoryHideRuleTable mHideRuleTable;

    public MpStoryHideRuleView(QueryParams queryParams) {
        super(queryParams);
    }

    private void addHideRuleProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mHideRuleTable.getProjectionArray());
    }

    private void addHideRuleTypeCondition(int i2) {
        this.mHideRuleTable.setRuleType(i2);
        this.mQueryBuilder.andCondition(this.mHideRuleTable.getWhere());
    }

    public SecFilesTable createFilesTable() {
        return new MpFilesTable(this.mParams);
    }

    public Query getQueryWithCreature(Query query, boolean z) {
        int i2;
        boolean atLeastSystem = SdkConfig.atLeastSystem(SdkConfig.SEM.B_MR5);
        if (z) {
            i2 = 1;
        } else if (atLeastSystem) {
            i2 = 3;
        } else {
            i2 = 2;
        }
        Query query2 = new Query(new QueryBuilder().addFromSelect(query.buildSql()));
        QueryBuilder queryBuilder = query2.getQueryBuilder();
        queryBuilder.addCrossJoin(getTableNameRaw() + " AS H", " H.recommended_id =__creatureFaceRecommendedID");
        queryBuilder.addProjection("*");
        queryBuilder.addProjection((List<String>) getProjectionArray());
        queryBuilder.andCondition("H.rule_type = " + i2);
        queryBuilder.groupBy("H.recommended_id");
        queryBuilder.addOrderBy("H.rule_type DESC");
        return query2;
    }

    public String getTableNameRaw() {
        return this.mHideRuleTable.getTableNameRaw();
    }

    public void initDateHideRule() {
        this.mQueryBuilder.clearFrom();
        this.mQueryBuilder.clearProjection();
        addHideRuleTypeCondition(0);
        this.mQueryBuilder.addTable(this.mHideRuleTable.getTableName());
        addHideRuleProjection();
    }

    public void onConstruct() {
        super.onConstruct();
        this.mHideRuleTable = new MpStoryHideRuleTable(this.mParams);
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mHideRuleTable.getProjectionArray());
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mFilesTable.getTableName());
    }

    public String tag() {
        return "MpStoryHideRuleView";
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
