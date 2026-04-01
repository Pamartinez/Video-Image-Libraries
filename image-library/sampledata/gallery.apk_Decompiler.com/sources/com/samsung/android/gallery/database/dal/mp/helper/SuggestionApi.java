package com.samsung.android.gallery.database.dal.mp.helper;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.table.MpFacesView;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpSceneView;
import com.samsung.android.gallery.database.dal.mp.table.MpTagView;
import com.samsung.android.gallery.support.type.SuggestionKeyword;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionApi extends BaseImpl {
    public SuggestionApi(QueryParams queryParams) {
        super(queryParams);
    }

    private Query buildSelectQuery(DbTable dbTable, boolean z, String str, String str2, String str3) {
        dbTable.resetProjectionForCursorCount(z);
        dbTable.getQueryBuilder().replaceProjectionByAliasWithValue(str, "__mainCategory");
        dbTable.getQueryBuilder().replaceProjectionByAlias(str2, "__subCategory");
        dbTable.getQueryBuilder().replaceProjectionByAlias(str3, "__tagType");
        return dbTable.buildSelectQuery();
    }

    private boolean enabledLocation() {
        if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
            return true;
        }
        return PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth);
    }

    private Query getBlurredQuery() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterImage();
        mpFilesTable.filterBlurryImages();
        return buildSelectQuery(mpFilesTable, false, SuggestionKeyword.BLURRY.name(), (String) null, (String) null);
    }

    private Query getLatestTagQuery() {
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        mpSceneView.filterSceneOrSubScene();
        mpSceneView.filterRemainScenes();
        mpSceneView.orderLatestTag();
        mpSceneView.limit(1);
        return buildSelectQuery(mpSceneView, false, SuggestionKeyword.CATEGORY.name(), mpSceneView.getColumnNameTagData(), (String) null);
    }

    private Query getLocationQuery(long j2) {
        MpTagView mpTagView = new MpTagView(this.mParams);
        mpTagView.filterLocation();
        QueryBuilder queryBuilder = mpTagView.getQueryBuilder();
        queryBuilder.andCondition("A._id = " + j2);
        mpTagView.groupTagData();
        mpTagView.havingLatest();
        return buildSelectQuery(mpTagView, false, SuggestionKeyword.LOCATION.name(), "T.tag_data", "T.tag_type");
    }

    private Query getRecentlyAddedMediaQuery() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterGroupMediaBest(true);
        mpFilesTable.filterGalleryMedia();
        mpFilesTable.filterByRecentlyAdded();
        return buildSelectQuery(mpFilesTable, false, SuggestionKeyword.RECENTLY_ADDED.name(), Long.toString(new TimeUtil().startOf2DaysAgo()), (String) null);
    }

    private Query getSmileQuery() {
        MpFacesView mpFacesView = new MpFacesView(this.mParams);
        mpFacesView.filterSmile();
        return buildSelectQuery(mpFacesView, true, SuggestionKeyword.SMILES.name(), (String) null, (String) null);
    }

    private Query getTakenDurationQuery(long j2) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        QueryBuilder queryBuilder = mpFilesTable.getQueryBuilder();
        queryBuilder.andCondition("A._id = " + j2);
        String name = SuggestionKeyword.TIME.name();
        return buildSelectQuery(mpFilesTable, false, name, "A." + getDateTakenColumnName(), (String) null);
    }

    private Query getVideoQuery() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterVideo();
        return buildSelectQuery(mpFilesTable, false, SuggestionKeyword.VIDEOS.name(), (String) null, (String) null);
    }

    public Cursor getSuggestion(long j2) {
        Query recentlyAddedMediaQuery = getRecentlyAddedMediaQuery();
        Query smileQuery = getSmileQuery();
        Query blurredQuery = getBlurredQuery();
        Query videoQuery = getVideoQuery();
        Query latestTagQuery = getLatestTagQuery();
        Query takenDurationQuery = getTakenDurationQuery(j2);
        recentlyAddedMediaQuery.unionAll(smileQuery);
        recentlyAddedMediaQuery.unionAll(blurredQuery);
        if (enabledLocation()) {
            recentlyAddedMediaQuery.unionAll(new Query(getLocationQuery(j2)));
        }
        recentlyAddedMediaQuery.unionAll(videoQuery);
        recentlyAddedMediaQuery.unionAll(new Query(latestTagQuery));
        recentlyAddedMediaQuery.unionAll(takenDurationQuery);
        return getCursor(recentlyAddedMediaQuery, "Suggestion");
    }

    public String tag() {
        return "SuggestionApi";
    }
}
