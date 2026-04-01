package com.samsung.android.gallery.database.dal.mp.table;

import N2.j;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dbtype.FacePriorityScore;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SqliteCaseBuilder;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import i.C0212a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MpCreatureView extends BaseView {
    protected static final boolean SUPPORT_ESSENTIAL_FACES = PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES;
    protected static final boolean USE_CREATURE_FACE_SCORE_ONLY = Features.isEnabled(Features.SUPPORT_FACE_ENGINE_SCORE_MAX_15);
    protected MpCreatureFacesTable mCreatureFacesTable;
    protected MpCreatureTagTable mCreatureTagTable;
    protected MpFacesGroupTable mFacesGroupTable;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum CreatureType {
        PEOPLE,
        PET
    }

    public MpCreatureView(QueryParams queryParams) {
        super(queryParams);
    }

    private void defaultOrderBy() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addOrderBy("A." + this.mFilesTable.getColumnDateTaken() + " DESC");
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.addOrderBy(this.mCreatureFacesTable.getAliasName() + "." + getMediaIdColumnName() + " DESC");
        QueryBuilder queryBuilder3 = this.mQueryBuilder;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mCreatureFacesTable.getAliasName());
        sb2.append("._id ASC");
        queryBuilder3.addOrderBy(sb2.toString());
    }

    public void addCreatureFaceScoreProjection() {
        this.mQueryBuilder.addProjection(getCreatureFaceScoreProjection(), "__faceScore");
    }

    public void addCreatureRecommendedIdProjection() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection(this.mCreatureFacesTable.getAliasName() + ".recommended_id", "__creatureFaceRecommendedID");
    }

    public Query buildSelectQuery() {
        if (this.IS_GTE_Q) {
            this.mFilesTable.appendVolumeNameForFaces();
        }
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        this.mQueryBuilder.andCondition(this.mCreatureFacesTable.getWhere());
        this.mQueryBuilder.andCondition(this.mCreatureTagTable.getWhere());
        if (SUPPORT_ESSENTIAL_FACES) {
            this.mQueryBuilder.andCondition(this.mFacesGroupTable.getWhere());
        }
        return super.buildSelectQuery();
    }

    public abstract MpCreatureFacesTable createCreatureFacesTable();

    public SecFilesTable createFilesTable() {
        return new MpFilesTable(this.mParams);
    }

    public abstract MpCreatureTagTable createMpCreatureTagTable();

    public void exceptUnifiedIds(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(this.mCreatureFacesTable.getAliasName() + ".recommended_id not in (" + str + ")");
    }

    public void filterBurstShotBestImage(boolean z) {
        this.mFilesTable.filterGroupMediaBest(true);
        if (z) {
            this.mFilesTable.addGroupMediaCountProjection(this.mQueryBuilder);
        }
    }

    public void filterCreationTime(long j2, long j3) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("( " + this.mFilesTable.getColumnDateTaken() + " > " + j2 + " AND " + this.mFilesTable.getColumnDateTaken() + " <= " + j3 + ")");
    }

    public void filterCreatureByIdentifyId(String str) {
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            filterUnifiedId(IdentityCreatureUtil.getUnifiedIdentityId(str));
            return;
        }
        long identityId = IdentityCreatureUtil.getIdentityId(str);
        if (IdentityCreatureUtil.isAssignedCreature(str)) {
            filterCreatureTagId(identityId);
        } else {
            filterGroupId(identityId);
        }
    }

    public void filterCreatureName(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(this.mCreatureTagTable.getAliasName() + ".name= \"" + str + "\"");
    }

    public void filterCreatureNames(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(this.mCreatureTagTable.getAliasName() + ".name in (" + str + ")");
    }

    public void filterCreatureTagId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(this.mCreatureFacesTable.getAliasName() + "." + this.mCreatureFacesTable.getCreatureIdColumnName() + "=" + j2);
    }

    public void filterCreatures() {
        if (!TextUtils.isEmpty(this.mParams.mCreatureFaceGroupIds)) {
            filterGroupIds(this.mParams.mCreatureFaceGroupIds);
        } else if (!TextUtils.isEmpty(this.mParams.getSubCategory())) {
            long identityId = IdentityCreatureUtil.getIdentityId(this.mParams.getSubCategory());
            if (IdentityCreatureUtil.isAssignedCreature(this.mParams.getSubCategory())) {
                filterCreatureTagId(identityId);
            } else {
                filterGroupId(identityId);
            }
        } else if (!TextUtils.isEmpty(this.mParams.mRecommendedIds)) {
            filterUnifiedIds(this.mParams.mRecommendedIds);
        }
        if (!TextUtils.isEmpty(this.mParams.mExceptRecommendedIds)) {
            exceptUnifiedIds(this.mParams.mExceptRecommendedIds);
        }
    }

    public void filterFacesGroupType(int i2) {
        if (SUPPORT_ESSENTIAL_FACES) {
            this.mFacesGroupTable.filterGroupType(i2);
        }
    }

    public void filterFileId(long j2) {
        this.mFilesTable.filterId(j2);
    }

    public void filterFileIds(String str) {
        this.mFilesTable.filterIds(str);
    }

    public void filterGroupId(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(this.mCreatureFacesTable.getAliasName() + ".group_id=" + j2);
    }

    public void filterGroupIds(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(this.mCreatureFacesTable.getAliasName() + ".group_id in (" + str + ")");
    }

    public void filterHidden(int i2) {
        String str;
        if (SUPPORT_ESSENTIAL_FACES) {
            MpFacesGroupTable mpFacesGroupTable = this.mFacesGroupTable;
            if (i2 < 2) {
                str = getEssentialGroupExtraOrCondition(i2);
            } else {
                str = "";
            }
            mpFacesGroupTable.filterHidden(i2, str);
            return;
        }
        this.mCreatureFacesTable.filterHidden(i2);
    }

    public void filterNamed() {
        this.mCreatureFacesTable.filterNamed();
    }

    public void filterNoRelationship() {
        this.mCreatureTagTable.filterNoRelationship();
    }

    public void filterTaggedNameFromUser() {
        this.mQueryBuilder.andCondition("__creatureName is not null");
    }

    public void filterUnifiedId(long j2) {
        String aliasName = this.mCreatureFacesTable.getAliasName();
        StringBuilder sb2 = new StringBuilder("( ");
        sb2.append(aliasName + ".recommended_id=" + j2);
        sb2.append(" or ");
        if (j2 > 100000) {
            StringBuilder t = C0212a.t(aliasName, ".group_id=");
            t.append(j2 - 100000);
            sb2.append(t.toString());
        } else {
            StringBuilder t3 = C0212a.t(aliasName, ".");
            t3.append(this.mCreatureFacesTable.getCreatureIdColumnName());
            t3.append("=");
            t3.append(j2);
            sb2.append(t3.toString());
        }
        sb2.append(" )");
        this.mQueryBuilder.andCondition(sb2.toString());
    }

    public void filterUnifiedIds(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(this.mCreatureFacesTable.getAliasName() + ".recommended_id in (" + str + ")");
    }

    public void filterUnnamed() {
        this.mCreatureFacesTable.filterUnnamed();
    }

    public void filterValidFace() {
        String aliasName = this.mCreatureFacesTable.getAliasName();
        C0212a.w("IFNULL(", aliasName, ".title_info, 0) >= 0", this.mQueryBuilder);
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(aliasName + ".pos_left >= 0");
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.andCondition(aliasName + ".pos_right >= 0");
        QueryBuilder queryBuilder3 = this.mQueryBuilder;
        queryBuilder3.andCondition(aliasName + ".pos_top >= 0");
        QueryBuilder queryBuilder4 = this.mQueryBuilder;
        queryBuilder4.andCondition(aliasName + ".pos_bottom >= 0");
    }

    public String getCreatureFaceScoreProjection() {
        StringBuilder sb2 = new StringBuilder();
        String aliasName = this.mCreatureFacesTable.getAliasName();
        String str = "A." + this.mFilesTable.getColumnDateTaken();
        SqliteCaseBuilder sqliteCaseBuilder = new SqliteCaseBuilder();
        sqliteCaseBuilder.whenThen(aliasName + ".title_info >= 900000000", 0);
        StringBuilder sb3 = new StringBuilder();
        sb3.append(aliasName);
        C0086a.z(sb3, ".pos_left < 0 or ", aliasName, ".pos_right < 0 or ", aliasName);
        sqliteCaseBuilder.whenThen(j.f(sb3, ".pos_top < 0 or ", aliasName, ".pos_bottom < 0"), -100);
        if (!USE_CREATURE_FACE_SCORE_ONLY) {
            sqliteCaseBuilder.whenThen(C0212a.m("(strftime('%s','now','localtime')*1000 - ", str, ") < 86400000"), FacePriorityScore.P_1);
            sqliteCaseBuilder.whenThen("(strftime('%s','now','localtime')*1000 - " + str + ") < 94608000000", FacePriorityScore.P_2);
        }
        sqliteCaseBuilder.elseThen(0);
        sb2.append(sqliteCaseBuilder.build());
        sb2.append("\n + IFNULL(" + aliasName + ".title_info, 0)");
        return sb2.toString();
    }

    public String getCreatureFaceTableAliasName() {
        return this.mCreatureFacesTable.getAliasName();
    }

    public String getCreatureHaving() {
        return "count(distinct __absID)>=5 AND min(__creatureFaceRecommendedID) > 100000 OR count(distinct __absID)>= 1 AND max(__creatureFaceRecommendedID) < 100000";
    }

    public abstract String getCreatureType();

    public abstract String getEssentialGroupExtraOrCondition(int i2);

    public abstract String getMainCategory();

    public String getMediaIdColumnName() {
        return "sec_media_id";
    }

    public void groupByCreatureID() {
        this.mQueryBuilder.groupBy("__creatureID");
    }

    public void includeFileIds(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.firstOrCondition("A._id IN (" + str + ")");
    }

    public void modifyForPictures(boolean z) {
        this.mQueryBuilder.groupBy("A._id");
        if (z) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.having("min(" + this.mCreatureFacesTable.getAliasName() + "._id)");
        }
        this.mQueryBuilder.removeProjectionByAlias("__mainCategory");
    }

    public void onConstruct() {
        super.onConstruct();
        this.mCreatureFacesTable = createCreatureFacesTable();
        this.mCreatureTagTable = createMpCreatureTagTable();
        this.mFacesGroupTable = new MpFacesGroupTable(this.mParams);
    }

    public void orderByASC() {
        clearOrderBy();
        String aliasName = this.mCreatureFacesTable.getAliasName();
        this.mQueryBuilder.addOrderBy("__dateTaken ASC");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        StringBuilder t = C0212a.t(aliasName, ".");
        t.append(getMediaIdColumnName());
        t.append(" ASC");
        queryBuilder.addOrderBy(t.toString());
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.addOrderBy(aliasName + "._id DESC");
    }

    public void orderByFaceScore() {
        clearOrderBy();
        this.mQueryBuilder.addOrderBy("__faceScore DESC");
        defaultOrderBy();
    }

    public void setCreatureHaving() {
        this.mQueryBuilder.having(getCreatureHaving());
    }

    public void setDefaultOrder() {
        defaultOrderBy();
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
        this.mQueryBuilder.addProjection((List<String>) this.mCreatureFacesTable.getProjectionArray());
        this.mQueryBuilder.addProjection((List<String>) this.mCreatureTagTable.getProjectionArray());
        if (SUPPORT_ESSENTIAL_FACES) {
            this.mQueryBuilder.addProjection((List<String>) this.mFacesGroupTable.getProjectionArray());
        }
        String aliasName = this.mCreatureFacesTable.getAliasName();
        StringBuilder q = C0086a.q("case when ", aliasName, ".pos_ratio is null then coalesce(cast(1.0*", aliasName, ".pos_left/A.width as text) || ',' || cast(1.0*");
        C0086a.z(q, aliasName, ".pos_top/A.height as text) ||',' || cast(1.0*", aliasName, ".pos_right/A.width as text) ||',' || cast( 1.0*");
        this.mQueryBuilder.replaceProjectionByAlias(C0212a.q(q, aliasName, ".pos_bottom/A.height as text),'1,1,1,1') else ", aliasName, ".pos_ratio end"), "__creatureFacePosRatio");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("'" + getMainCategory() + "'", "__mainCategory");
        if (this.mParams.SUPPORT_PET_CLUSTER) {
            this.mQueryBuilder.addProjection(getCreatureType(), "__creatureType");
        }
        if (this.mParams.mNeedFaceScore) {
            addCreatureFaceScoreProjection();
        }
        if (PreferenceFeatures.OneUi8x.SUPPORT_CREATURE_THUMB_FROM_VIDEO_FRAME) {
            QueryBuilder queryBuilder2 = this.mQueryBuilder;
            queryBuilder2.addProjection(this.mCreatureFacesTable.getAliasName() + ".frame_id", "__videoFrameId");
            this.mQueryBuilder.addProjection("VF.pos", "__videoFamePos");
        }
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mCreatureTagTable.getTableName());
        QueryBuilder queryBuilder = this.mQueryBuilder;
        String tableName = this.mCreatureFacesTable.getTableName();
        StringBuilder sb2 = new StringBuilder();
        MpCreatureFacesTable mpCreatureFacesTable = this.mCreatureFacesTable;
        sb2.append(mpCreatureFacesTable.getAliasColumnName(mpCreatureFacesTable.getCreatureIdColumnName()));
        sb2.append("=");
        sb2.append(this.mCreatureTagTable.getAliasColumnName("_id"));
        queryBuilder.addInnerJoin(tableName, sb2.toString());
        if (SUPPORT_ESSENTIAL_FACES) {
            QueryBuilder queryBuilder2 = this.mQueryBuilder;
            String tableName2 = this.mFacesGroupTable.getTableName();
            queryBuilder2.addInnerJoin(tableName2, this.mFacesGroupTable.getAliasColumnName(BundleKey.GROUP_ID) + "=" + this.mCreatureFacesTable.getAliasColumnName(BundleKey.GROUP_ID));
        }
        if (PreferenceFeatures.OneUi8x.SUPPORT_CREATURE_THUMB_FROM_VIDEO_FRAME) {
            QueryBuilder queryBuilder3 = this.mQueryBuilder;
            queryBuilder3.addLeftOuterJoin("video_frames AS VF", this.mCreatureFacesTable.getAliasName() + ".frame_id=VF._id");
        }
        QueryBuilder queryBuilder4 = this.mQueryBuilder;
        String tableName3 = this.mFilesTable.getTableName();
        queryBuilder4.addCrossJoin(tableName3, this.mCreatureFacesTable.getFkForFiles() + "=" + this.mFilesTable.getAliasColumnName("_id"));
    }

    public void filterRelationship(String str) {
    }
}
