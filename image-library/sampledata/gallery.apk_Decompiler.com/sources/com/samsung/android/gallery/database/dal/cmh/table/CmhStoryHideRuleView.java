package com.samsung.android.gallery.database.dal.cmh.table;

import N2.j;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dbtype.FacePriorityScore;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import i.C0212a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhStoryHideRuleView extends BaseView {
    private boolean mFilterHideRule;
    private CmhStoryHideRuleTable mHideRuleTable;

    public CmhStoryHideRuleView(QueryParams queryParams) {
        super(queryParams);
    }

    private void addFaceScoreProjection() {
        StringBuilder sb2 = new StringBuilder();
        if (Features.isEnabled(Features.SUPPORT_FACE_ENGINE_SCORE_MAX_15)) {
            sb2.append("(CASE  WHEN (F.pos_left < 0 or F.pos_right < 0 or F.pos_top < 0 or F.pos_bottom < 0) THEN -100 ELSE IFNULL(F.title_info, 0) END)");
        } else {
            String str = "A." + this.mFilesTable.getColumnDateTaken();
            StringBuilder k = j.k("(CASE  WHEN (F.pos_left < 0 or F.pos_right < 0 or F.pos_top < 0 or F.pos_bottom < 0) THEN -100 WHEN ((strftime('%s','now','localtime')*1000 - ", str, ") < 86400000) THEN ");
            k.append(FacePriorityScore.P_1);
            k.append(" WHEN ((strftime('%s','now','localtime')*1000 - ");
            k.append(str);
            k.append(") < 94608000000) THEN ");
            k.append(FacePriorityScore.P_2);
            k.append(" ELSE 0 END)");
            sb2.append(k.toString());
            sb2.append("\n + IFNULL(F.title_info, 0)");
        }
        this.mQueryBuilder.addProjection(sb2.toString(), "__faceScore");
    }

    private void addHideRuleTypeCondition(int i2) {
        this.mHideRuleTable.setRuleType(i2);
        this.mQueryBuilder.andCondition(this.mHideRuleTable.getWhere());
    }

    private void addPersonsProjection() {
        this.mQueryBuilder.addProjection("F.group_id", "__creatureFaceGroupID");
        this.mQueryBuilder.addProjection("F.face_data", "__creatureFaceData");
        this.mQueryBuilder.addProjection("F.pos_ratio", "__creatureFacePosRatio");
        this.mQueryBuilder.addProjection("F.person_id", "__creatureID");
        this.mQueryBuilder.addProjection("P.name", "__creatureName");
        this.mQueryBuilder.replaceProjectionByAlias("case when F.pos_ratio is null then coalesce(cast(1.0*F.pos_left/A.width as text) || ',' || cast(1.0*F.pos_top/A.height as text) ||',' || cast(1.0*F.pos_right/A.width as text) ||',' || cast( 1.0*F.pos_bottom/A.height as text),'1,1,1,1') else F.pos_ratio end", "__creatureFacePosRatio");
        this.mQueryBuilder.addProjection("'People'", "__mainCategory");
        this.mQueryBuilder.addProjection("''", "__subCategory");
        this.mQueryBuilder.addProjection("-1", "__scene_score");
        if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE) {
            addFaceScoreProjection();
        }
    }

    private void filterHideRuleTableForPeople(boolean z) {
        String str;
        if (z) {
            str = "H.person_id=F.person_id";
        } else {
            str = "H.group_id=F.group_id";
        }
        this.mQueryBuilder.addInnerJoin("hide_rule as H", str);
        this.mQueryBuilder.addProjection((List<String>) this.mHideRuleTable.getProjectionArray());
        addHideRuleTypeCondition(1);
    }

    private void filterHideRuleTableForScene() {
        this.mQueryBuilder.addInnerJoin("hide_rule AS H", "H.private_data=S.scene_name");
        this.mQueryBuilder.addProjection((List<String>) this.mHideRuleTable.getProjectionArray());
        addHideRuleTypeCondition(2);
    }

    private void joinFacesTable() {
        this.mQueryBuilder.addInnerJoin("faces as F", "F.sec_media_id=A.sec_media_id");
    }

    private void joinPersonsTable() {
        this.mQueryBuilder.addInnerJoin("persons as P", "F.person_id=P._id");
    }

    private void joinSceneTable() {
        this.mQueryBuilder.addInnerJoin("scene AS S", "S.tag_id=T.fk_tag_id AND S.parent_id > 0 AND S.parent_id in (SELECT scene_id From scene WHERE scene_name COLLATE NOCASE in ('dogs', 'cats'))");
    }

    private void joinTagMapTable() {
        this.mQueryBuilder.addInnerJoin("tag_map AS T", "T.fk_file_id=A._id");
    }

    private void replaceTable(boolean z) {
        String str;
        if (z) {
            str = "H.group_id=F.group_id";
        } else {
            str = "H.person_id=F.person_id";
        }
        this.mFilesTable.getQueryBuilder().replaceTable(C0212a.m("(select * from files where sec_media_id in (select F.sec_media_id from hide_rule as H INNER JOIN faces as F ON ", str, ")) as A "), "", "");
    }

    private void setPeopleProjection() {
        addPersonsProjection();
        this.mQueryBuilder.replaceProjectionByAlias("A.sec_media_id", "__absID");
    }

    private void setSceneOrderBy() {
        this.mQueryBuilder.addOrderBy("__dateTaken DESC, A.sec_media_id DESC, T.fk_tag_id ASC");
    }

    private void setSceneProjection() {
        this.mQueryBuilder.addProjection("-1", "__creatureFaceGroupID");
        this.mQueryBuilder.addProjection("''", "__creatureFaceData");
        this.mQueryBuilder.addProjection("''", "__creatureFacePosRatio");
        this.mQueryBuilder.addProjection("-1", "__creatureID");
        this.mQueryBuilder.addProjection("''", "__creatureName");
        this.mQueryBuilder.addProjection("'Scenery'", "__mainCategory");
        this.mQueryBuilder.addProjection("S.scene_name", "__subCategory");
        this.mQueryBuilder.addProjection("T.scene_score", "__scene_score");
        this.mQueryBuilder.replaceProjectionByAlias("CASE WHEN T.scene_region_ratio is null THEN A.smartcrop_rect_ratio ELSE T.scene_region_ratio END", "__sceneRegion");
        this.mQueryBuilder.replaceProjectionByAlias("A.sec_media_id", "__absID");
    }

    private void setSceneWhere() {
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
    }

    public SecFilesTable createFilesTable() {
        return new CmhFilesTable(this.mParams);
    }

    public void filterBurstShotBestImage(boolean z) {
        this.mFilesTable.filterGroupMediaBest(true);
        if (z) {
            this.mFilesTable.addGroupMediaCountProjection(this.mQueryBuilder);
        }
    }

    public void initDateHideRule() {
        this.mQueryBuilder.clearFrom();
        this.mQueryBuilder.clearProjection();
        addHideRuleTypeCondition(0);
        this.mQueryBuilder.addTable(this.mHideRuleTable.getTableName());
        this.mQueryBuilder.addProjection((List<String>) this.mHideRuleTable.getProjectionArray());
    }

    public void initPeopleHideRule(boolean z) {
        if (this.mFilterHideRule) {
            replaceTable(z);
        }
        joinFacesTable();
        joinPersonsTable();
        if (this.mFilterHideRule) {
            filterHideRuleTableForPeople(z);
        }
        filterBurstShotBestImage(false);
        setPeopleProjection();
        setPeopleWhere(z);
        setPeopleOrderBy();
    }

    public void initPetsHideRule() {
        joinTagMapTable();
        joinSceneTable();
        if (this.mFilterHideRule) {
            filterHideRuleTableForScene();
        }
        filterBurstShotBestImage(false);
        setSceneProjection();
        setSceneWhere();
        setSceneOrderBy();
    }

    public void onConstruct() {
        super.onConstruct();
        this.mHideRuleTable = new CmhStoryHideRuleTable(this.mParams);
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mFilesTable.getTableName());
    }

    public void setPeopleOrderBy() {
        if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE) {
            this.mQueryBuilder.addOrderBy("__faceScore DESC");
        }
        this.mQueryBuilder.addOrderBy("__dateTaken DESC, F.sec_media_id DESC, F._id ASC");
    }

    public void setPeopleWhere(boolean z) {
        String str;
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        this.mQueryBuilder.andCondition("F.group_id > 0");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        if (z) {
            str = "F.person_id > 1";
        } else {
            str = "F.person_id = 1";
        }
        queryBuilder.andCondition(str);
        if (this.IS_GTE_T && PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
            this.mQueryBuilder.andCondition("F.hide < 1");
        }
    }

    public String tag() {
        return "CmhStoryHideRuleView";
    }

    public CmhStoryHideRuleView(QueryParams queryParams, boolean z) {
        super(queryParams);
        this.mFilterHideRule = z;
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
