package com.samsung.android.gallery.database.dal.cmh.impl;

import N2.j;
import android.database.Cursor;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecLocationView;
import com.samsung.android.gallery.database.dal.cmh.table.CmhFilesTable;
import com.samsung.android.gallery.database.dal.cmh.table.CmhLocationView;
import com.samsung.android.gallery.database.dal.cmh.table.CmhStoryAppBarView;
import com.samsung.android.gallery.database.dal.cmh.table.CmhStoryClusterTable;
import com.samsung.android.gallery.database.dal.cmh.table.CmhStoryHideRuleView;
import com.samsung.android.gallery.database.dal.cmh.table.CmhStoryView;
import com.samsung.android.gallery.database.dal.cmh.table.CmhVisualArtTable;
import com.samsung.android.gallery.database.dal.util.QueryUtils;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import i.C0212a;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesImpl extends BaseImpl {
    public StoriesImpl(QueryParams queryParams) {
        super(queryParams);
    }

    public static String appendConcatBuilder(String str, String str2) {
        return C0212a.p(C0086a.q("(CASE WHEN ", str2, " is null THEN '' ELSE ',\"", str, "\":\"' || "), str2, " || '\"' END)");
    }

    public static String concatBuilder(String str, String str2) {
        return j.d("'\"", str, "\":\"' || ", str2, " || '\"'");
    }

    private CmhStoryView createBaseStoriesView(boolean z) {
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.addStoryVisibilityCondition(true);
        cmhStoryView.addStoryCoverData();
        cmhStoryView.addFaceData();
        cmhStoryView.filterInvalidStoryType();
        if (this.mParams.SUPPORT_MEMORY_DATA) {
            cmhStoryView.addMemoryCollageData();
        }
        QueryParams queryParams = this.mParams;
        long j2 = queryParams.mStartTime;
        if (j2 != -1) {
            long j3 = queryParams.mEndTime;
            if (j3 != -1) {
                cmhStoryView.filterCreationTime(j2, j3);
                cmhStoryView.filterNewStoryType();
                cmhStoryView.filterUnchecked();
            }
        }
        int i2 = this.mParams.mParentAlbumId;
        if (i2 > -1) {
            cmhStoryView.filterStoryID(i2);
        }
        if (this.mParams.isStoryFavoriteType()) {
            cmhStoryView.filterFavorite();
        }
        cmhStoryView.groupByStoryAlbum();
        if (this.mParams.getStoryCategoryType() != -1) {
            cmhStoryView.filterByCategoryType(this.mParams.getStoryCategoryType());
        }
        QueryParams queryParams2 = this.mParams;
        if (queryParams2.SUPPORT_STORIES_DATA_SEP13) {
            cmhStoryView.addProjectionForPin();
        } else if (queryParams2.STORY_ONE_UI_50) {
            cmhStoryView.addProjectionForFavorite();
        }
        cmhStoryView.filterUserCuration();
        if (z) {
            cmhStoryView.orderByStoryType();
        }
        return cmhStoryView;
    }

    private CmhStoryView createStoryAlbumFileView(int i2, boolean z) {
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.addDataStamp();
        cmhStoryView.addStoryFileData();
        if (this.mParams.SUPPORT_MEMORY_DATA) {
            cmhStoryView.addProjectionForStreetName();
        }
        cmhStoryView.addStoryVisibilityCondition(z);
        cmhStoryView.filterStoryID(i2);
        cmhStoryView.clearOrderBy();
        if (Features.isEnabled(Features.SUPPORT_STORY_REORDER)) {
            cmhStoryView.orderByDisplayOrder();
        }
        cmhStoryView.orderByDateTaken();
        return cmhStoryView;
    }

    private Query getPersonNamedHideRuleCursor(boolean z) {
        CmhStoryHideRuleView cmhStoryHideRuleView = new CmhStoryHideRuleView(this.mParams, z);
        cmhStoryHideRuleView.initPeopleHideRule(true);
        Query query = new Query(cmhStoryHideRuleView.buildSelectQuery());
        query.getQueryBuilder().groupBy("__creatureID");
        query.getQueryBuilder().having("count(distinct __absID)>=1");
        return query;
    }

    private Query getPersonUnnamedHideRuleCursor(boolean z) {
        CmhStoryHideRuleView cmhStoryHideRuleView = new CmhStoryHideRuleView(this.mParams, z);
        cmhStoryHideRuleView.initPeopleHideRule(false);
        Query query = new Query(cmhStoryHideRuleView.buildSelectQuery());
        query.getQueryBuilder().groupBy("__creatureFaceGroupID");
        query.getQueryBuilder().having("count(distinct __absID)>=1");
        return query;
    }

    private Query getPetsHideRuleCursor(boolean z) {
        CmhStoryHideRuleView cmhStoryHideRuleView = new CmhStoryHideRuleView(this.mParams, z);
        cmhStoryHideRuleView.initPetsHideRule();
        Query query = new Query(cmhStoryHideRuleView.buildSelectQuery());
        query.getQueryBuilder().groupBy("LOWER(__subCategory)");
        query.getQueryBuilder().having("max(__scene_score)");
        return query;
    }

    private Query getStoryAutoComplete() {
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.addStoryCoverData();
        cmhStoryView.groupByStoryTitle();
        cmhStoryView.filterSearchAutoComplete();
        cmhStoryView.resetProjectionForAutoCompleteV1();
        return cmhStoryView.buildSelectQuery();
    }

    private Query getStoryQuery(SearchFilter searchFilter) {
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.addStoryFileData();
        cmhStoryView.applySearchStorySubFilter(searchFilter);
        cmhStoryView.projectionForSearchResult();
        cmhStoryView.replaceProjectionForCmhId();
        cmhStoryView.filterMediaType(searchFilter.getMediaType());
        cmhStoryView.notInStoryNotifications();
        return cmhStoryView.buildSelectQuery();
    }

    public Cursor getAutoCreatedVisualArtCursor() {
        long fileId = this.mParams.getFileId();
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.resetProjectionForVisualArt();
        cmhStoryView.addStoryCoverData();
        cmhStoryView.groupByStoryAlbum();
        cmhStoryView.filterVisualArtType();
        cmhStoryView.filterStoryCoverByCmhFileID(fileId);
        Query buildSelectQuery = cmhStoryView.buildSelectQuery();
        return getCursor(buildSelectQuery, "cmhId : " + fileId);
    }

    public Cursor getDateHideRuleCursor() {
        CmhStoryHideRuleView cmhStoryHideRuleView = new CmhStoryHideRuleView(this.mParams);
        cmhStoryHideRuleView.initDateHideRule();
        cmhStoryHideRuleView.getQueryBuilder().addOrderBy("__rule_id DESC");
        return getCursor(cmhStoryHideRuleView.buildSelectQuery(), "getDateHideRuleCursor");
    }

    public Cursor getRelatedMemories() {
        CmhStoryView createBaseStoriesView = createBaseStoriesView(false);
        createBaseStoriesView.modifyForRelatedMemoryQuery();
        return getCursor(createBaseStoriesView.buildSelectQuery(), "related : ");
    }

    public Cursor getSceneHideRuleCursor(boolean z) {
        Query query;
        String str = (String) this.mParams.getTag("type", "");
        if ("scene".equals(str)) {
            query = getPetsHideRuleCursor(z);
        } else if ("named".equals(str)) {
            query = getPersonNamedHideRuleCursor(z);
        } else {
            query = getPersonUnnamedHideRuleCursor(z);
        }
        if ("scene".equals(str)) {
            if (z) {
                query.getQueryBuilder().addOrderBy("__rule_id DESC");
            }
            query.getQueryBuilder().addOrderBy("__dateTaken DESC, __absID DESC");
        }
        query.getQueryBuilder().addProjection(new String[]{"*", "count(distinct __absID) as __count", "__dateTaken", "__absID"});
        return getCursor(query, "getSceneHideRuleCursor");
    }

    public Cursor getSearchAutoCompleteStoryCursor() {
        return getCursor(getStoryAutoComplete(), "AutoComplete Story");
    }

    public Cursor getStoryAlbumCursor() {
        if (this.mParams.getNames() != null) {
            return getStoryAlbumCursor(this.mParams.getNames());
        }
        if (this.mParams.getFileId() != -1) {
            return getStoryAlbumCursor(this.mParams.getFileId());
        }
        if (this.mParams.getStoryIds() != null) {
            return getStoryAlbumFromIdsCursor(this.mParams.getStoryIds());
        }
        return getCursor(createBaseStoriesView(this.mParams.STORY_ONE_UI_70).buildSelectQuery(), "getStoryData");
    }

    public Cursor getStoryAlbumFileCurationCursor() {
        int i2 = this.mParams.getAlbumIdArray()[0];
        CmhStoryView createStoryAlbumFileView = createStoryAlbumFileView(i2, this.mParams.isStoryVisible());
        createStoryAlbumFileView.filterUserCuration();
        Query buildSelectQuery = createStoryAlbumFileView.buildSelectQuery();
        return getCursor(buildSelectQuery, "story : " + i2);
    }

    public Cursor getStoryAlbumFileCursor() {
        int i2 = this.mParams.getAlbumIdArray()[0];
        Query buildSelectQuery = createStoryAlbumFileView(i2, this.mParams.isStoryVisible()).buildSelectQuery();
        return getCursor(buildSelectQuery, "story : " + i2);
    }

    public Cursor getStoryAlbumFileDateCursor() {
        int i2 = this.mParams.getAlbumIdArray()[0];
        CmhStoryView createStoryAlbumFileView = createStoryAlbumFileView(i2, true);
        createStoryAlbumFileView.modifyForDateData();
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(createStoryAlbumFileView.buildSelectQuery(), "__dateTaken asc", (SecLocationView) new CmhLocationView(this.mParams));
        return getCursor(updateQueryForMultipleLocations, "story : " + i2);
    }

    public Cursor getStoryAlbumFromIdsCursor(String str) {
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.addStoryVisibilityCondition(true);
        cmhStoryView.addStoryCoverData();
        cmhStoryView.groupByStoryAlbum();
        cmhStoryView.filterStoryIds(str);
        if (this.mParams.SUPPORT_MEMORY_DATA) {
            cmhStoryView.addMemoryCollageData();
        }
        if (this.mParams.getLimitSize() > 0) {
            cmhStoryView.limit(this.mParams.getLimitSize());
        }
        cmhStoryView.filterUserCuration();
        Query buildSelectQuery = cmhStoryView.buildSelectQuery();
        return getCursor(buildSelectQuery, "story:" + str);
    }

    public Cursor getStoryAlbumPickerCursor() {
        CmhStoryView createBaseStoriesView = createBaseStoriesView(this.mParams.STORY_ONE_UI_70);
        createBaseStoriesView.filterForPicker();
        return getCursor(createBaseStoriesView.buildSelectQuery(), "getStoryPickerData");
    }

    public Cursor getStoryAppBar() {
        CmhStoryAppBarView cmhStoryAppBarView = new CmhStoryAppBarView(this.mParams);
        cmhStoryAppBarView.addStoryCoverData();
        cmhStoryAppBarView.groupByStoryAlbum();
        cmhStoryAppBarView.filterInvalidStoryType();
        return getCursor(cmhStoryAppBarView.buildSelectQuery(), "getStoryAppBarData");
    }

    public Cursor getStoryChapterCursor() {
        CmhStoryClusterTable cmhStoryClusterTable = new CmhStoryClusterTable(this.mParams);
        cmhStoryClusterTable.filterStoryId(this.mParams.getAlbumIdArray());
        return getCursor(cmhStoryClusterTable.buildSelectQuery(), "getStoryChapterCursor");
    }

    public Cursor getStoryNotificationFileCursor() {
        int i2 = this.mParams.getAlbumIdArray()[0];
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.addStoryFileData();
        cmhStoryView.addStoryProjectionForPrivate();
        cmhStoryView.filterStoryID(i2);
        cmhStoryView.clearOrderBy();
        cmhStoryView.orderByStoryMapId();
        Query buildSelectQuery = cmhStoryView.buildSelectQuery();
        return getCursor(buildSelectQuery, "storyNotificationFileCursor : " + i2);
    }

    public Cursor getStoryShareFileCursor() {
        String str;
        int i2 = this.mParams.getAlbumIdArray()[0];
        CmhStoryView createStoryAlbumFileView = createStoryAlbumFileView(i2, this.mParams.isStoryVisible());
        createStoryAlbumFileView.clearProjection();
        if (Features.isEnabled(Features.USE_SEC_MP)) {
            str = C0212a.m("CASE WHEN A.is_cloud = 2 THEN ", "\"" + MediaUri.getInstance().getSecPickerUriString() + "\" || (CASE WHEN A.media_type = 1 THEN \"image/\" ELSE \"video/\" END) || A.cloud_server_id || \"/\" || A.sec_media_id", " ELSE \"content://media/external/\" || (CASE WHEN A.media_type = 1 THEN \"images/media/\" ELSE \"video/media/\" END) || A.media_id END");
        } else if (Features.isEnabled(Features.USE_NEWMP)) {
            str = C0212a.m("CASE WHEN A.is_cloud = 2 THEN ", "\"" + MediaUri.getInstance().getSecPickerUriString() + "\" || (CASE WHEN A.media_type = 1 THEN \"image/\" ELSE \"video/\" END) || A.media_id", " ELSE \"content://media/external/\" || (CASE WHEN A.media_type = 1 THEN \"images/media/\" ELSE \"video/media/\" END) || A.media_id END");
        } else {
            str = "\"content://media/external/\" || (CASE WHEN A.media_type = 1 THEN \"images/media/\" ELSE \"video/media/\" END) || A.media_id";
        }
        createStoryAlbumFileView.addProjection(str, "share_uri");
        createStoryAlbumFileView.addProjection("A.bucket_id", "share_bucket_id");
        createStoryAlbumFileView.addProjection("COALESCE(A.bucket_display_name,'')", "share_bucket_name");
        createStoryAlbumFileView.addProjection("'{' || " + concatBuilder("crop", "COALESCE(S.coverRectRatio,'0,0,0,0')") + " || " + appendConcatBuilder("smart_crop", "A.total_smartcrop_ratio") + " || " + appendConcatBuilder("smart_crop_device", "A.total_smartcrop_device_ratio") + " || '}'", KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
        Query buildSelectQuery = createStoryAlbumFileView.buildSelectQuery();
        return getCursor(buildSelectQuery, "story : " + i2);
    }

    public Cursor getVisualArtCursor() {
        long fileId = this.mParams.getFileId();
        CmhVisualArtTable cmhVisualArtTable = new CmhVisualArtTable(this.mParams);
        cmhVisualArtTable.filterCmhFileId(fileId);
        CmhFilesTable cmhFilesTable = new CmhFilesTable(this.mParams);
        cmhFilesTable.getQueryBuilder().replaceProjectionByAlias("A.sec_media_id", "__absID");
        QueryBuilder queryBuilder = cmhFilesTable.getQueryBuilder();
        queryBuilder.andCondition("A._id in (" + cmhVisualArtTable.buildSelectQuery().buildSql() + ")");
        Query buildSelectQuery = cmhFilesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "cmhId : " + fileId);
    }

    public Cursor searchStoryAlbum(SearchFilter searchFilter) {
        int size = searchFilter.getSubFilterList().size();
        Query query = null;
        if (size == 0) {
            Log.w(this.TAG, "searchStoryAlbum, no sub filter");
            return null;
        }
        Iterator<SearchFilter> it = searchFilter.getSubFilterList().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Query storyQuery = getStoryQuery(it.next());
            if (i2 == 0) {
                query = storyQuery;
            }
            if (size > 1 && i2 > 0) {
                query.intersect(storyQuery);
            }
            i2++;
        }
        return getCursor(query, "SearchStoryAlbum");
    }

    public String tag() {
        return "StoriesImpl";
    }

    public Cursor getStoryAlbumCursor(long j2) {
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.addStoryVisibilityCondition(true);
        cmhStoryView.addStoryCoverData();
        cmhStoryView.groupByStoryAlbum();
        if (this.mParams.SUPPORT_MEMORY_DATA) {
            cmhStoryView.addMemoryCollageData();
        }
        cmhStoryView.filterStoryCoverByCmhFileID(j2);
        if (this.mParams.getLimitSize() > 0) {
            cmhStoryView.limit(this.mParams.getLimitSize());
        }
        cmhStoryView.filterUserCuration();
        cmhStoryView.filterInvalidStoryType();
        Query buildSelectQuery = cmhStoryView.buildSelectQuery();
        return getCursor(buildSelectQuery, "story:" + j2);
    }

    private Cursor getStoryAlbumCursor(String str) {
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.addStoryVisibilityCondition(true);
        cmhStoryView.addStoryCoverData();
        cmhStoryView.groupByStoryAlbum();
        cmhStoryView.filterStoryNames(str);
        cmhStoryView.filterUserCuration();
        cmhStoryView.filterInvalidStoryType();
        Query buildSelectQuery = cmhStoryView.buildSelectQuery();
        return getCursor(buildSelectQuery, "story names: [" + str + "]");
    }
}
