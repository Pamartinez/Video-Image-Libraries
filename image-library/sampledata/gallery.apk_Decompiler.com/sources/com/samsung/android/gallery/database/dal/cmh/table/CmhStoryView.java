package com.samsung.android.gallery.database.dal.cmh.table;

import N2.j;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.abstraction.table.SecSearchableView;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhStoryView extends SecSearchableView {
    private CmhStoryTable mStoryTable;

    public CmhStoryView(QueryParams queryParams) {
        super(queryParams);
    }

    private String getAlbumHavingClause() {
        String aliasColumnName = this.mFilesTable.getAliasColumnName(IParameterKey.DATE_TAKEN);
        if (Features.isEnabled(Features.SUPPORT_STORY_REORDER)) {
            return C0212a.p(C0086a.q("max(case when SM.story_display_order > 0 then SM.story_display_order else (case when ", aliasColumnName, "=0 or ", aliasColumnName, " is null then 1 else + "), aliasColumnName, "* -1 end) end)");
        }
        return C0212a.p(C0086a.q("min(case when ", aliasColumnName, "=0 or ", aliasColumnName, " is null then 1 else "), aliasColumnName, " end)");
    }

    private String getFileTableName() {
        return this.mFilesTable.getTableNameRaw() + " AS A";
    }

    private static String getStoriesCategoryQueryCondition(int i2) {
        ArrayList<Integer> typesByCategory = StoryType.getTypesByCategory(i2);
        if (typesByCategory.isEmpty()) {
            return "S.type = " + StoryType.MANUAL.getValue();
        }
        StringJoiner stringJoiner = new StringJoiner(" OR ");
        Iterator<Integer> it = typesByCategory.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            stringJoiner.add("S.type=" + intValue);
        }
        return stringJoiner.toString();
    }

    public void addDataStamp() {
        this.mFilesTable.addDataStamp();
    }

    public void addFaceData() {
        if (!this.mParams.SUPPORT_STORIES_DATA_SEP11) {
            if (this.USE_GMP) {
                this.mQueryBuilder.addLeftOuterJoin("faces as f", "f.sec_media_id=A._id");
            } else {
                this.mQueryBuilder.addLeftOuterJoin("faces as f", "f.image_id=A._id");
            }
            this.mQueryBuilder.addProjection("\n((min(f.pos_left)+0.0)/(A.width+0.0))\n|| ',' || ( (0.0+min(f.pos_top))/(A.height+0.0) || '')  \n|| ',' || ( (0.0+max(f.pos_right))/(A.width+0.0) || '') \n|| ',' || ( (0.0+max(f.pos_bottom))/(A.height+0.0) || '') ", "__sceneRegion");
        }
    }

    public void addMemoryCollageData() {
        this.mQueryBuilder.addProjection("S.cover_path", "cover_path");
        this.mQueryBuilder.addProjection("S.collage_type", "collage_type");
        this.mQueryBuilder.addProjection("S.collage_video_index", "collage_video_index");
    }

    public void addProjectionForFavorite() {
        if (this.IS_GTE_S) {
            this.mQueryBuilder.addProjection("S.story_favorite", "__story_favorite");
        }
    }

    public void addProjectionForPin() {
        this.mQueryBuilder.addProjection("S.story_favorite", "__story_favorite");
        this.mQueryBuilder.addProjection("S.story_scoring", "__story_scoring");
    }

    public void addProjectionForStreetName() {
        if (PreferenceFeatures.OneUi30.MEMORIES) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.addLeftOuterJoin("location as L", "A.latitude=L.latitude AND A.longitude=L.longitude AND L.locale='" + Locale.getDefault() + "'");
            this.mQueryBuilder.addProjection("L.street_name", "__streetName");
        }
    }

    public void addStoryCoverData() {
        String str;
        String str2;
        if (!this.IS_GTE_R || this.USE_GMP) {
            str = "_id";
        } else {
            str = "sec_media_id";
        }
        if (Features.isEnabled(Features.SUPPORT_STORY_REORDER)) {
            str2 = "(SM.story_display_order > 0 AND SM.fk_file_id = " + this.mFilesTable.getAliasColumnName("_id") + ") OR ";
        } else {
            str2 = "";
        }
        StringBuilder t = C0212a.t(str2, "(S.cover = ");
        t.append(this.mFilesTable.getAliasColumnName(str));
        t.append(") OR ((S.cover = 0 OR NOT EXISTS (SELECT ");
        t.append(str);
        t.append(" FROM ");
        t.append(getFileTableName());
        t.append(" WHERE S.cover = ");
        t.append(this.mFilesTable.getAliasColumnName(str));
        t.append(" AND (A.is_hide is null OR A.is_hide < 1))) AND SM.fk_file_id = ");
        t.append(this.mFilesTable.getAliasColumnName("_id"));
        t.append(")");
        this.mQueryBuilder.addLeftOuterJoin(getFileTableName(), t.toString());
        if (Features.isEnabled(Features.SUPPORT_DYNAMIC_VIEW) && PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSave)) {
            this.mQueryBuilder.addProjection("A.dynamic_view_info", "__dynamicViewInfo");
        }
    }

    public void addStoryFileData() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        String fileTableName = getFileTableName();
        queryBuilder.addLeftOuterJoin(fileTableName, "SM.fk_file_id=" + this.mFilesTable.getAliasColumnName("_id"));
        if (Features.isEnabled(Features.SUPPORT_DYNAMIC_VIEW) && (!this.mParams.STORY_ONE_UI_50 || PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSave))) {
            this.mQueryBuilder.addProjection("A.dynamic_view_info", "__dynamicViewInfo");
        }
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            this.mQueryBuilder.addProjection("SM.user_curation", "__story_user_curation");
        }
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.andCondition("S.type != " + StoryType.DELETED_STORY.getValue());
    }

    public void addStoryProjectionForPrivate() {
        this.mQueryBuilder.addProjection("S.private_data", "__private_data");
        this.mQueryBuilder.addProjection("S.additional_private_data", "__additional_private_data");
    }

    public void addStoryVisibilityCondition(boolean z) {
        String str;
        if (z) {
            str = "1";
        } else {
            str = "0";
        }
        this.mStoryTable.getQueryBuilder().andCondition("S.is_visible=".concat(str));
    }

    public Query buildSelectQuery() {
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        this.mQueryBuilder.andCondition(this.mStoryTable.getWhere());
        return super.buildSelectQuery();
    }

    public SecFilesTable createFilesTable() {
        if (this.USE_GMP) {
            return new MpFilesTable(this.mParams);
        }
        return new CmhFilesTable(this.mParams);
    }

    public void filterByCategoryType(int i2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("(" + getStoriesCategoryQueryCondition(i2) + ")");
    }

    public void filterCreationTime(long j2, long j3) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        StringBuilder j8 = j.j(j2, "( S.creation_time > ", " AND S.creation_time <= ");
        j8.append(j3);
        j8.append(")");
        queryBuilder.andCondition(j8.toString());
    }

    public void filterFavorite() {
        this.mQueryBuilder.andCondition("( S.story_favorite > 0 )");
    }

    public void filterForPicker() {
        this.mQueryBuilder.andCondition("S.type < 100");
        this.mQueryBuilder.andCondition("S.type != 85");
    }

    public void filterInvalidStoryType() {
        this.mQueryBuilder.andCondition("S.type < 1000");
    }

    public void filterNewStoryType() {
        this.mQueryBuilder.andCondition("( S.type == 1 OR S.type == 4 OR S.type == 33 OR S.type == 50 )");
    }

    public void filterSearchAutoComplete() {
        if (this.mParams.SUPPORT_STORIES_DATA_SEP11) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.andCondition("S.type = " + StoryType.MANUAL.getValue());
            return;
        }
        notInStoryNotifications();
    }

    public void filterStoryCoverByCmhFileID(long j2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("(S.story_id in (select fk_story_id from story_map where fk_file_id = '" + j2 + "'))");
    }

    public void filterStoryID(int i2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("S.story_id = '" + i2 + "'");
    }

    public void filterStoryIds(String str) {
        C0212a.w("S.story_id in (", str, ")", this.mQueryBuilder);
    }

    public void filterStoryNames(String str) {
        C0212a.w("S.title in (", str, ")", this.mQueryBuilder);
    }

    public void filterUnchecked() {
        this.mQueryBuilder.andCondition("( S.is_manual <> 1 AND S.notify_status <> 1 )");
    }

    public void filterUserCuration() {
        if (this.IS_GTE_T && this.mParams.STORY_ONE_UI_50) {
            this.mQueryBuilder.andCondition("(S.user_enter IS NULL OR (S.user_enter > 1 AND SM.user_curation > 0))");
        }
    }

    public void filterVisualArtType() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("(S.type = " + StoryType.COLLAGE.getValue() + " or S.type = " + StoryType.AGIF.getValue() + " or S.type = " + StoryType.COLLAGE_THEN_AND_NOW.getValue() + " or S.type = " + StoryType.REDISCOVER_DAY.getValue() + ")");
    }

    public String getFileIdColumnName() {
        if (this.USE_GMP) {
            return "_id";
        }
        if (this.IS_GTE_Q) {
            return "sec_media_id";
        }
        return "media_id";
    }

    public void groupByStoryAlbum() {
        this.mQueryBuilder.groupBy("S.story_id");
        this.mQueryBuilder.having(getAlbumHavingClause());
        this.mQueryBuilder.addProjection("count(*)", "__count");
        this.mQueryBuilder.replaceProjectionByAlias("S.title", "__Title");
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.replaceProjectionByAlias("count(distinct(SM.fk_file_id))", "__count");
        }
    }

    public void groupByStoryTitle() {
        this.mQueryBuilder.groupBy("S.title");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.having("min(" + this.mFilesTable.getAliasColumnName(IParameterKey.DATE_TAKEN) + ")");
        this.mQueryBuilder.addProjection("count(*)", "__count");
        this.mQueryBuilder.replaceProjectionByAlias("S.title", "__Title");
    }

    public String hashTagSearch(String str) {
        return "";
    }

    public void modifyForDateData() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("A._id", "__absID");
        this.mQueryBuilder.addProjection("A.datetaken", "__dateTaken");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("strftime('%Y-%m-%d', (A.datetaken / 1000)+" + DbTable.TIMEZONE_OFFSET_SECOND + ", 'unixepoch')", "__day");
        this.mQueryBuilder.addProjection("count(A._id)", "__count");
        this.mQueryBuilder.addProjection("A.addr", "__Address");
        this.mQueryBuilder.addProjection("A.latitude", "__latitude");
        this.mQueryBuilder.addProjection("A.longitude", "__longitude");
        this.mQueryBuilder.groupBy("__day");
    }

    public void modifyForRelatedMemoryQuery() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("S.story_id");
        this.mQueryBuilder.addProjection("S.type");
        this.mQueryBuilder.addProjection("S.notify_status");
        this.mQueryBuilder.addProjection("group_concat(distinct(F.group_id))");
        this.mQueryBuilder.addProjection("group_concat(distinct(F.person_id))");
        this.mQueryBuilder.addProjection("group_concat(distinct(POI.poi_name))");
        this.mQueryBuilder.addProjection("group_concat(distinct(L.locality))");
        this.mQueryBuilder.addProjection("group_concat(distinct(L.country_name))");
        if (Features.isEnabled(Features.SUPPORT_CMH_STORY_SA_TYPE)) {
            this.mQueryBuilder.addProjection("S.sa_type");
        }
        this.mQueryBuilder.removeJoin("files");
        this.mQueryBuilder.removeJoin("faces");
        this.mQueryBuilder.removeJoin("location");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addLeftOuterJoin(this.mFilesTable.getTableNameRaw() + " AS A", "SM.fk_file_id = A._id and SM.fk_file_id IS NOT NULL");
        if (this.USE_GMP) {
            this.mQueryBuilder.addLeftOuterJoin("faces AS F", "F.sec_media_id=SM.fk_file_id and (F.group_id > 0 or F.person_id > 1)");
        } else {
            this.mQueryBuilder.addLeftOuterJoin("faces AS F", "F.image_id=SM.fk_file_id and (F.group_id > 0 or F.person_id > 1)");
        }
        this.mQueryBuilder.addLeftOuterJoin("persons AS P", "P._id=F.person_id and P.name NOT IN ('null')");
        this.mQueryBuilder.addLeftOuterJoin("poi AS POI", "A.latitude=POI.latitude AND A.longitude=POI.longitude");
        this.mQueryBuilder.addLeftOuterJoin("location AS L", "A.latitude=L.latitude AND A.longitude=L.longitude");
        this.mQueryBuilder.addLeftOuterJoin("android_metadata AS AM", "L.locale=AM.locale");
    }

    public void modifyForStoryPeopleHeader() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.removeJoin("files");
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.addLeftOuterJoin(this.mFilesTable.getTableNameRaw() + " AS A", "SM.fk_file_id = A._id");
            this.mQueryBuilder.andCondition("SM.file_added_status IS NULL");
            this.mQueryBuilder.clearProjection();
            this.mQueryBuilder.addProjection("A.sec_media_id", "__face_file_id");
        }
    }

    public void notInStoryNotifications() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("(S.type not in (" + StoryType.AGIF.getValue() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + StoryType.VIDEO_COLLAGE.getValue() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + StoryType.COLLAGE.getValue() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + StoryType.COLLAGE_THEN_AND_NOW.getValue() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + StoryType.REDISCOVER_DAY.getValue() + "))");
    }

    public void onConstruct() {
        super.onConstruct();
        this.mStoryTable = new CmhStoryTable(this.mParams);
    }

    public void orderByDateTaken() {
        this.mQueryBuilder.addOrderBy("A.datetaken");
    }

    public void orderByDateTakenDesc() {
        this.mQueryBuilder.addOrderBy("A.datetaken DESC");
    }

    public void orderByDisplayOrder() {
        this.mQueryBuilder.addOrderBy("__story_display_order DESC");
    }

    public void orderByStoryMapId() {
        this.mQueryBuilder.addOrderBy("SM.storymap_id");
    }

    public void orderByStoryType() {
        if (this.mParams.STORY_ONE_UI_70) {
            this.mQueryBuilder.addProjection("(case when S.type>=100 then 0 when S.type=1 then 1 when S.type=39 then 2 else 3 end)", "__story_type_order");
            this.mQueryBuilder.addOrderByFirst("__story_type_order");
        }
    }

    public void replaceProjectionForCmhId() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.replaceProjectionByAlias("A." + getFileIdColumnName(), "__absID");
    }

    public void resetProjectionForAutoCompleteV1() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection(" IFNULL(A._data, A.cloud_thumb_path)", "__absPath");
        this.mQueryBuilder.addProjection("A.smartcrop_rect_ratio", "__sceneRegion");
        this.mQueryBuilder.addProjection("A.media_type", "__mediaType");
        this.mQueryBuilder.addProjection("A.orientation", "__orientation");
        this.mQueryBuilder.addProjection("''", "cover_rect");
        this.mQueryBuilder.addProjection("A.is_cloud", "__storageType");
        this.mQueryBuilder.addProjection("S.title", "__Title");
        this.mQueryBuilder.addProjection("S.title", "__subCategory");
        this.mQueryBuilder.replaceProjectionByAliasWithValue("Search Auto Complete", "__mainCategory");
    }

    public void resetProjectionForVisualArt() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("S.story_id", "__albumID");
        this.mQueryBuilder.addProjection("S.cover", "__absPath");
        this.mQueryBuilder.addProjection("S.title", "__Title");
        this.mQueryBuilder.addProjection("S.notify_status", "__storyNotifyStatus");
        this.mQueryBuilder.addProjection("S.type", "__storyType");
        this.mQueryBuilder.addProjection("S.coverRectRatio", "__storyCoverRectRatio");
        StringBuilder sb2 = new StringBuilder("CASE S.type WHEN ");
        sb2.append(StoryType.AGIF.getValue());
        sb2.append(" THEN ");
        MediaType mediaType = MediaType.Image;
        sb2.append(mediaType.toInt());
        sb2.append(" WHEN ");
        sb2.append(StoryType.COLLAGE.getValue());
        sb2.append(" THEN ");
        sb2.append(mediaType.toInt());
        sb2.append(" WHEN ");
        sb2.append(StoryType.VIDEO_COLLAGE.getValue());
        sb2.append(" THEN ");
        sb2.append(MediaType.Video.toInt());
        sb2.append(" WHEN ");
        sb2.append(StoryType.COLLAGE_THEN_AND_NOW.getValue());
        sb2.append(" THEN ");
        sb2.append(mediaType.toInt());
        sb2.append(" WHEN ");
        sb2.append(StoryType.REDISCOVER_DAY.getValue());
        sb2.append(" THEN ");
        sb2.append(mediaType.toInt());
        sb2.append(" END");
        this.mQueryBuilder.addProjection(sb2.toString(), "__mediaType");
    }

    public void setDefaultCondition() {
        if (this.USE_GMP) {
            this.mQueryBuilder.andCondition(" (SM.fk_file_id IS NOT NULL) ");
        } else {
            this.mQueryBuilder.andCondition(" (SM.fk_file_id IS NOT NULL AND A.uri IS NOT NULL) ");
        }
        this.mQueryBuilder.andCondition("SM.is_recommended!=0");
    }

    public void setDefaultOrder() {
        this.mQueryBuilder.addOrderBy("S.creation_time DESC, S.story_id DESC");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mStoryTable.getProjectionArray());
        this.mFilesTable.getQueryBuilder().removeProjectionByAlias("__albumID");
        this.mFilesTable.getQueryBuilder().removeProjectionByAlias("__GroupMediaID");
        this.mFilesTable.getQueryBuilder().removeProjectionByAlias("__GroupMediaBest");
        this.mFilesTable.getQueryBuilder().removeProjectionByAlias("__group_type");
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
        this.mQueryBuilder.addProjection("A.bucket_id", "__bucketID");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.replaceProjectionByAlias("A." + getFileIdColumnName(), "__absID");
        if (this.IS_GTE_S && PreferenceFeatures.isEnabled(PreferenceFeatures.SuggestedEffectOnStory)) {
            this.mQueryBuilder.removeProjectionByAlias("revitalized_path");
            this.mQueryBuilder.addProjection("A.revitalized_path", "revitalized_path");
            if (SdkConfig.atLeast(SdkConfig.SEM.S_MR5)) {
                this.mQueryBuilder.addProjection("A.portrait_path", "__portrait_path");
            }
        }
        if (this.IS_GTE_T) {
            this.mQueryBuilder.addProjection("A.total_smartcrop_ratio", "__total_smartcrop_ratio");
            this.mQueryBuilder.addProjection("A.total_smartcrop_device_ratio", "__total_smartcrop_device_ratio");
        }
        if (Features.isEnabled(Features.SUPPORT_STORY_REORDER)) {
            this.mQueryBuilder.addProjection("(case when SM.story_display_order > 0 then SM.story_display_order else 0 end)", "__story_display_order");
            this.mQueryBuilder.addProjection("A._id", "__cmh_file_id");
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_STORY_LIVE_EFFECT_TYPE) {
            this.mQueryBuilder.addProjection("A.effect_type", "__effect_type");
            this.mQueryBuilder.addProjection("SM.file_added_status", "__file_added_status");
        }
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mStoryTable.getTableName());
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addLeftOuterJoin("story_map as SM", "SM.fk_story_id=" + this.mStoryTable.getAliasColumnName("story_id"));
    }

    public String stringSearch(SearchFilter searchFilter) {
        return likeWild("S.title", searchFilter.getRawKeyword());
    }

    public String tag() {
        return "CmhStoryView";
    }
}
