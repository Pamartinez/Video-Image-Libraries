package com.samsung.android.gallery.database.dal.mp.impl;

import N2.j;
import android.database.Cursor;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecLocationView;
import com.samsung.android.gallery.database.dal.mp.table.MpLocationView;
import com.samsung.android.gallery.database.dal.mp.table.MpStoryAppBarView;
import com.samsung.android.gallery.database.dal.mp.table.MpStoryHideRuleView;
import com.samsung.android.gallery.database.dal.mp.table.MpStoryView;
import com.samsung.android.gallery.database.dal.util.QueryUtils;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import i.C0212a;

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

    private MpStoryView createBaseStoriesView(boolean z) {
        MpStoryView mpStoryView = new MpStoryView(this.mParams);
        mpStoryView.addStoryVisibilityCondition(true);
        mpStoryView.addStoryCoverData();
        mpStoryView.addFaceData();
        mpStoryView.filterInvalidStoryType();
        QueryParams queryParams = this.mParams;
        long j2 = queryParams.mStartTime;
        if (j2 != -1) {
            long j3 = queryParams.mEndTime;
            if (j3 != -1) {
                mpStoryView.filterCreationTime(j2, j3);
                mpStoryView.filterNewStoryType();
                mpStoryView.filterUnchecked();
            }
        }
        int i2 = this.mParams.mParentAlbumId;
        if (i2 > -1) {
            mpStoryView.filterStoryID(i2);
        }
        if (this.mParams.isStoryFavoriteType()) {
            mpStoryView.filterFavorite();
        }
        mpStoryView.groupByStoryAlbum();
        if (this.mParams.getStoryCategoryType() != -1) {
            mpStoryView.filterByCategoryType(this.mParams.getStoryCategoryType());
        }
        QueryParams queryParams2 = this.mParams;
        if (queryParams2.SUPPORT_STORIES_DATA_SEP13) {
            mpStoryView.addProjectionForPin();
        } else if (queryParams2.STORY_ONE_UI_50) {
            mpStoryView.addProjectionForFavorite();
        }
        mpStoryView.filterUserCuration();
        if (z) {
            mpStoryView.orderByStoryType();
        }
        return mpStoryView;
    }

    private MpStoryView createStoryAlbumFileView(int i2, boolean z) {
        MpStoryView mpStoryView = new MpStoryView(this.mParams);
        mpStoryView.addDataStamp();
        mpStoryView.addStoryFileData();
        if (this.mParams.SUPPORT_MEMORY_DATA) {
            mpStoryView.addProjectionForStreetName();
        }
        mpStoryView.addStoryVisibilityCondition(z);
        mpStoryView.filterStoryID(i2);
        mpStoryView.clearOrderBy();
        if (Features.isEnabled(Features.SUPPORT_STORY_REORDER)) {
            mpStoryView.orderByDisplayOrder();
        }
        mpStoryView.orderByDateTaken();
        return mpStoryView;
    }

    private Query getCreatureQuery(IdentityCreatureUtil.Category category) {
        if (IdentityCreatureUtil.Category.PEOPLE.equals(category)) {
            return new PeoplePetImpl(this.mParams).getUnifiedPeopleQuery();
        }
        return new PeoplePetImpl(this.mParams).getPetsQuery();
    }

    private Query getStoryAutoComplete() {
        MpStoryView mpStoryView = new MpStoryView(this.mParams);
        mpStoryView.addStoryCoverData();
        mpStoryView.groupByStoryTitle();
        mpStoryView.filterSearchAutoComplete();
        mpStoryView.resetProjectionForAutoCompleteV1();
        return mpStoryView.buildSelectQuery();
    }

    public Cursor getCreatureHideRuleCursor(boolean z) {
        QueryParams queryParams = this.mParams;
        IdentityCreatureUtil.Category category = IdentityCreatureUtil.Category.PEOPLE;
        IdentityCreatureUtil.Category category2 = (IdentityCreatureUtil.Category) queryParams.getTag("type", category);
        Query creatureQuery = getCreatureQuery(category2);
        if (!z) {
            return getCursor(creatureQuery, "getCreatureHideRuleCursor");
        }
        return getCursor(new MpStoryHideRuleView(this.mParams).getQueryWithCreature(creatureQuery, category.equals(category2)), "getCreatureHideRuleCursor filter");
    }

    public Cursor getDateHideRuleCursor() {
        MpStoryHideRuleView mpStoryHideRuleView = new MpStoryHideRuleView(this.mParams);
        mpStoryHideRuleView.initDateHideRule();
        mpStoryHideRuleView.getQueryBuilder().addOrderBy("__rule_id DESC");
        return getCursor(mpStoryHideRuleView.buildSelectQuery(), "getDateHideRuleCursor");
    }

    public Cursor getRelatedMemories() {
        MpStoryView createBaseStoriesView = createBaseStoriesView(false);
        createBaseStoriesView.modifyForRelatedMemoryQuery();
        return getCursor(createBaseStoriesView.buildSelectQuery(), "related : ");
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
        MpStoryView createStoryAlbumFileView = createStoryAlbumFileView(i2, this.mParams.isStoryVisible());
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
        MpStoryView createStoryAlbumFileView = createStoryAlbumFileView(i2, true);
        createStoryAlbumFileView.modifyForDateData();
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(createStoryAlbumFileView.buildSelectQuery(), "__dateTaken asc", (SecLocationView) new MpLocationView(this.mParams));
        return getCursor(updateQueryForMultipleLocations, "story : " + i2);
    }

    public Cursor getStoryAlbumFromIdsCursor(String str) {
        MpStoryView mpStoryView = new MpStoryView(this.mParams);
        mpStoryView.addStoryVisibilityCondition(true);
        mpStoryView.addStoryCoverData();
        mpStoryView.groupByStoryAlbum();
        mpStoryView.filterStoryIds(str);
        if (this.mParams.getLimitSize() > 0) {
            mpStoryView.limit(this.mParams.getLimitSize());
        }
        mpStoryView.filterUserCuration();
        Query buildSelectQuery = mpStoryView.buildSelectQuery();
        return getCursor(buildSelectQuery, "story:" + str);
    }

    public Cursor getStoryAlbumPickerCursor() {
        MpStoryView createBaseStoriesView = createBaseStoriesView(this.mParams.STORY_ONE_UI_70);
        createBaseStoriesView.filterForPicker();
        return getCursor(createBaseStoriesView.buildSelectQuery(), "getStoryAlbumPickerCursor");
    }

    public Cursor getStoryAppBar() {
        MpStoryAppBarView mpStoryAppBarView = new MpStoryAppBarView(this.mParams);
        mpStoryAppBarView.addStoryCoverData();
        mpStoryAppBarView.groupByStoryAlbum();
        mpStoryAppBarView.filterInvalidStoryType();
        return getCursor(mpStoryAppBarView.buildSelectQuery(), "getStoryAppBarData");
    }

    public Cursor getStoryNotificationFileCursor() {
        int i2 = this.mParams.getAlbumIdArray()[0];
        MpStoryView mpStoryView = new MpStoryView(this.mParams);
        mpStoryView.addStoryFileData();
        mpStoryView.addStoryProjectionForPrivate();
        mpStoryView.filterStoryID(i2);
        mpStoryView.clearOrderBy();
        mpStoryView.orderByStoryMapId();
        Query buildSelectQuery = mpStoryView.buildSelectQuery();
        return getCursor(buildSelectQuery, "storyNotificationFileCursor : " + i2);
    }

    public Cursor getStoryShareFileCursor() {
        String str;
        int i2 = this.mParams.getAlbumIdArray()[0];
        MpStoryView createStoryAlbumFileView = createStoryAlbumFileView(i2, this.mParams.isStoryVisible());
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

    public String tag() {
        return "StoriesImpl";
    }

    public Cursor getStoryAlbumCursor(long j2) {
        MpStoryView mpStoryView = new MpStoryView(this.mParams);
        mpStoryView.addStoryVisibilityCondition(true);
        mpStoryView.addStoryCoverData();
        mpStoryView.groupByStoryAlbum();
        mpStoryView.filterStoryCoverByFileID(j2);
        if (this.mParams.getLimitSize() > 0) {
            mpStoryView.limit(this.mParams.getLimitSize());
        }
        mpStoryView.filterUserCuration();
        mpStoryView.filterInvalidStoryType();
        Query buildSelectQuery = mpStoryView.buildSelectQuery();
        return getCursor(buildSelectQuery, "story:" + j2);
    }

    private Cursor getStoryAlbumCursor(String str) {
        MpStoryView mpStoryView = new MpStoryView(this.mParams);
        mpStoryView.addStoryVisibilityCondition(true);
        mpStoryView.addStoryCoverData();
        mpStoryView.groupByStoryAlbum();
        mpStoryView.filterStoryNames(str);
        mpStoryView.filterUserCuration();
        mpStoryView.filterInvalidStoryType();
        Query buildSelectQuery = mpStoryView.buildSelectQuery();
        return getCursor(buildSelectQuery, "story names: [" + str + "]");
    }
}
