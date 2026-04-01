package com.samsung.android.gallery.database.dal.mp.impl;

import A.a;
import android.database.Cursor;
import android.database.MergeCursor;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecLocationView;
import com.samsung.android.gallery.database.dal.local.CacheProviderHelper;
import com.samsung.android.gallery.database.dal.mp.table.MpCreatureView;
import com.samsung.android.gallery.database.dal.mp.table.MpLocationView;
import com.samsung.android.gallery.database.dal.mp.table.MpPeopleView;
import com.samsung.android.gallery.database.dal.mp.table.MpPetView;
import com.samsung.android.gallery.database.dal.util.QueryUtils;
import com.samsung.android.gallery.database.dbtype.DateType;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.Utils;
import java.util.ArrayList;
import t8.c;
import t8.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PeoplePetImpl extends CategoriesImpl {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum CreatureDisplayType {
        SHOWN,
        HIDDEN,
        ALL
    }

    public PeoplePetImpl(QueryParams queryParams) {
        super(queryParams);
    }

    private Query getAllPetsQuery() {
        return getCreatureQuery(CreatureDisplayType.ALL, true, IdentityCreatureUtil.Category.PET);
    }

    private Cursor getCreatureCountCursor(CreatureDisplayType creatureDisplayType, IdentityCreatureUtil.Category category) {
        Query creatureCountQuery = getCreatureCountQuery(creatureDisplayType, category);
        return getCursor(creatureCountQuery, category.name() + "_COUNT");
    }

    private Query getCreatureCountQuery(CreatureDisplayType creatureDisplayType, IdentityCreatureUtil.Category category) {
        if (!supportCreatureType(category)) {
            Log.w("PeoplePetImpl", "getCreatureCountQuery: failed");
            return new Query(new QueryBuilder());
        }
        MpCreatureView creatureView = getCreatureView(creatureDisplayType, category);
        creatureView.resetProjectionForID();
        creatureView.clearOrderBy();
        creatureView.addCreatureRecommendedIdProjection();
        creatureView.groupByCreatureID();
        creatureView.getQueryBuilder().groupBy("__creatureFaceRecommendedID");
        if (!isRelated() && TextUtils.isEmpty(this.mParams.getSubCategory())) {
            creatureView.setCreatureHaving();
        }
        Query query = new Query(creatureView.buildSelectQuery());
        query.getQueryBuilder().addProjection("count(*) as __count");
        return query;
    }

    private MpCreatureView getCreatureFileView(String str, String str2, IdentityCreatureUtil.Category category) {
        String str3;
        MpCreatureView creatureViewBase = getCreatureViewBase(category);
        creatureViewBase.filterBurstShotBestImage(true);
        if (str == null && str2 == null && (str3 = this.mParams.mCreatureFaceGroupIds) != null) {
            creatureViewBase.filterGroupIds(str3);
            creatureViewBase.modifyForPictures(false);
            QueryBuilder queryBuilder = creatureViewBase.getQueryBuilder();
            queryBuilder.addProjection("group_concat(distinct " + creatureViewBase.getCreatureFaceTableAliasName() + ".group_id)", "__creatureFaceGroupIDs");
            QueryBuilder queryBuilder2 = creatureViewBase.getQueryBuilder();
            queryBuilder2.replaceProjectionByAlias("count(distinct " + creatureViewBase.getCreatureFaceTableAliasName() + ".group_id)", "__count");
            if (this.mParams.mMinFaceCount > 0) {
                QueryBuilder queryBuilder3 = creatureViewBase.getQueryBuilder();
                queryBuilder3.having("__count>" + this.mParams.mMinFaceCount);
            }
        } else {
            creatureViewBase.modifyForPictures(true);
            if (TextUtils.isEmpty(str)) {
                creatureViewBase.filterCreatures();
            } else {
                creatureViewBase.filterCreatureByIdentifyId(str);
            }
            if (!TextUtils.isEmpty(str2)) {
                creatureViewBase.filterCreatureName(str2.replaceAll("\"", "\"\""));
            }
        }
        ArrayList<Integer> excludeAlbumIdList = this.mParams.getExcludeAlbumIdList();
        if (excludeAlbumIdList != null) {
            creatureViewBase.filterBucketIds(excludeAlbumIdList, false);
        }
        String excludeFileIds = this.mParams.getExcludeFileIds();
        if (excludeFileIds != null) {
            creatureViewBase.excludeFileIds(excludeFileIds);
        }
        if (!TextUtils.isEmpty(this.mParams.getIncludeFileIds())) {
            creatureViewBase.includeFileIds(this.mParams.getIncludeFileIds());
        }
        return creatureViewBase;
    }

    private Query getCreatureQuery(CreatureDisplayType creatureDisplayType, boolean z, IdentityCreatureUtil.Category category) {
        if (!supportCreatureType(category)) {
            Log.w("PeoplePetImpl", "getCreatureQuery: failed");
            return new Query(new QueryBuilder());
        }
        MpCreatureView creatureView = getCreatureView(creatureDisplayType, category);
        if (this.mParams.mNoRelationshipCreature) {
            creatureView.filterNoRelationship();
        }
        long j2 = this.mParams.mFaceGroupId;
        if (j2 != -1) {
            creatureView.filterGroupId(j2);
        }
        Query query = new Query(creatureView.buildSelectQuery());
        if (z) {
            query.getQueryBuilder().groupBy("__creatureFaceRecommendedID");
            if (!isRelated() && TextUtils.isEmpty(this.mParams.getSubCategory())) {
                query.getQueryBuilder().having(creatureView.getCreatureHaving());
            }
        }
        query.getQueryBuilder().addProjection(new String[]{"*", "count(distinct __absID) as __count"});
        return query;
    }

    private MpCreatureView getCreatureView(CreatureDisplayType creatureDisplayType, IdentityCreatureUtil.Category category) {
        MpCreatureView creatureViewBase = getCreatureViewBase(category);
        if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE) {
            creatureViewBase.addCreatureFaceScoreProjection();
            creatureViewBase.orderByFaceScore();
        }
        creatureViewBase.filterBurstShotBestImage(false);
        if (isRelated()) {
            creatureViewBase.filterFileId(this.mParams.getFileId());
            creatureViewBase.filterValidFace();
        }
        creatureViewBase.filterCreatures();
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster) && !TextUtils.isEmpty(this.mParams.getNames())) {
            creatureViewBase.filterCreatureNames(this.mParams.getNames());
        }
        if (PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
            creatureViewBase.filterHidden(creatureDisplayType.ordinal());
        }
        creatureViewBase.filterFacesGroupType(category.ordinal() + 1);
        return creatureViewBase;
    }

    private MpCreatureView getCreatureViewBase(IdentityCreatureUtil.Category category) {
        if (category == IdentityCreatureUtil.Category.PEOPLE) {
            return new MpPeopleView(this.mParams);
        }
        return new MpPetView(this.mParams);
    }

    private int getCursorCount(Cursor cursor, String str) {
        int i2;
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
                        i2 = cursor.getInt(cursor.getColumnIndex("__count"));
                    } else {
                        i2 = cursor.getCount();
                    }
                    Utils.closeSilently(cursor);
                    return i2;
                }
            } catch (Exception e) {
                Log.e("PeoplePetImpl", "getCursorCount[" + str + "] failed. e=" + e.getMessage());
            } catch (Throwable th) {
                Utils.closeSilently(cursor);
                throw th;
            }
        }
        Utils.closeSilently(cursor);
        return 0;
    }

    private CreatureDisplayType getDefaultDisplayType() {
        if (!PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES || this.mParams.essentialFaceOnly) {
            return CreatureDisplayType.SHOWN;
        }
        return CreatureDisplayType.ALL;
    }

    private Query getNamedPeopleQuery() {
        return getNamedPeopleQuery(this.mParams.getFileId(), true);
    }

    private Cursor getRelatedPeopleCursor(long j2, boolean z) {
        Query query;
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            query = getUnifiedPeopleQuery();
        } else {
            query = getNamedPeopleQuery(j2, z).unionAll(getUnnamedPeopleQuery(j2, z));
        }
        return getCursor(query, "PEOPLE_" + j2);
    }

    private Cursor getRelatedPetCursor() {
        return getPetsCursor();
    }

    private Query getUnifiedAllPeopleQuery() {
        return getCreatureQuery(CreatureDisplayType.ALL, true, IdentityCreatureUtil.Category.PEOPLE);
    }

    private Query getUnnamedPeopleQuery() {
        return getUnnamedPeopleQuery(this.mParams.getFileId(), true);
    }

    private boolean hasItem(Cursor cursor) {
        if (cursor == null || cursor.getCount() <= 0) {
            return false;
        }
        return true;
    }

    private boolean isRelated() {
        if (this.mParams.getFileId() != -1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getAllPeopleAndPetsCursor$0(Cursor[] cursorArr, Query query) {
        cursorArr[0] = getCursor(query, "CrAllPets");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getAllPeopleAndPetsCursor$1(Cursor[] cursorArr, Query query) {
        cursorArr[1] = getCursor(query, "CrAllPeople");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getHiddenPeopleAndPetsCursor$2(Cursor[] cursorArr, Query query) {
        cursorArr[0] = getCursor(query, "CrHiddenPets");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getHiddenPeopleAndPetsCursor$3(Cursor[] cursorArr, Query query) {
        cursorArr[1] = getCursor(query, "CrHiddenPeople");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getPeopleAndPetsCursor$4(Cursor[] cursorArr, Query query, String str) {
        Cursor cursor = getCursor(query, "CrPets" + str);
        cursorArr[0] = cursor;
        if (hasItem(cursor)) {
            PreferenceCache.SearchPetClusterRecognized.compareAndSet(false, true);
        }
        if (TextUtils.isEmpty(str)) {
            CacheProviderHelper.cacheCursor("location://search/fileList/Category/Pet", cursorArr[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getPeopleAndPetsCursor$5(Cursor[] cursorArr, Query query, String str) {
        cursorArr[1] = getCursor(query, "CrPeople" + str);
        if (TextUtils.isEmpty(str)) {
            CacheProviderHelper.cacheCursor("location://search/fileList/Category/People", cursorArr[1]);
        }
    }

    private boolean supportCreatureType(IdentityCreatureUtil.Category category) {
        if (this.mParams.SUPPORT_PET_CLUSTER || !category.equals(IdentityCreatureUtil.Category.PET)) {
            return true;
        }
        return false;
    }

    public Cursor getAllPeopleAndPetsCursor() {
        long currentTimeMillis = System.currentTimeMillis();
        Query unifiedAllPeopleQuery = getUnifiedAllPeopleQuery();
        Cursor[] cursorArr = new Cursor[2];
        new LatchBuilder("PeopleAndPetsAllQuery").setCurrent(new c(this, cursorArr, getAllPetsQuery(), 2)).addWorker(new c(this, cursorArr, unifiedAllPeopleQuery, 3)).start();
        Log.d("PeoplePetImpl", C0086a.j(currentTimeMillis, Logger.toString(cursorArr), " +", new StringBuilder("getAllPeopleAndPetsCursor ")));
        return new MergeCursor(cursorArr);
    }

    public Cursor getAllPeopleCursor() {
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            return getCursor(getUnifiedAllPeopleQuery(), "PEOPLE_ALL");
        }
        return getPeopleCursor();
    }

    public Cursor getCreatureFileCursor(IdentityCreatureUtil.Category category) {
        MpCreatureView mpCreatureView;
        if (!supportCreatureType(category)) {
            Log.w("PeoplePetImpl", "getCreatureFileCursor: failed");
            return null;
        } else if (this.mParams.getFileId() == -1) {
            QueryParams queryParams = this.mParams;
            if (queryParams.mCreatureFaceGroupIds != null) {
                mpCreatureView = getCreatureFileView((String) null, (String) null, category);
            } else {
                mpCreatureView = getCreatureFileView(queryParams.getSubCategory(), this.mParams.mCreatureTagName, category);
            }
            if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
                mpCreatureView.filterFileIds(this.mParams.getFileIds());
            }
            if (PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
                mpCreatureView.filterHidden(getDefaultDisplayType().ordinal());
            }
            if (this.mParams.mIsForOnDemandQuery) {
                mpCreatureView.resetProjectionForID();
            }
            if (this.mParams.getOrder() != null) {
                mpCreatureView.getQueryBuilder().clearOrderBy().addOrderBy(this.mParams.getOrder());
            }
            QueryParams queryParams2 = this.mParams;
            long j2 = queryParams2.mStartTime;
            if (j2 != -1) {
                long j3 = queryParams2.mEndTime;
                if (j3 != -1) {
                    mpCreatureView.filterCreationTime(j2 - 1, j3);
                }
            }
            if (this.mParams.getLimitSize() > 0) {
                mpCreatureView.limit(this.mParams.getLimitSize());
            }
            if (!TextUtils.isEmpty(this.mParams.mFileNamePrefixExclude)) {
                QueryBuilder queryBuilder = mpCreatureView.getQueryBuilder();
                queryBuilder.andCondition("title not like '" + this.mParams.mFileNamePrefixExclude + "%'");
            }
            if (this.mParams.mUseFileIdsConcat) {
                Query buildSelectQuery = mpCreatureView.buildSelectQuery();
                return getFileIdsConcatCursor(buildSelectQuery, "Creature file[" + category + "] : " + this.mParams.getSubCategory());
            }
            Query buildSelectQuery2 = mpCreatureView.buildSelectQuery();
            return getCursor(buildSelectQuery2, "Creature file[" + category + "] : " + this.mParams.getSubCategory());
        } else if (category == IdentityCreatureUtil.Category.PEOPLE) {
            return getRelatedPeopleCursor(this.mParams.getFileId(), false);
        } else {
            return getRelatedPetCursor();
        }
    }

    public Cursor getCreatureFileDateCursor(IdentityCreatureUtil.Category category) {
        if (!supportCreatureType(category)) {
            Log.w("PeoplePetImpl", "getCreatureFileDateCursor: failed");
            return null;
        }
        MpCreatureView creatureFileView = getCreatureFileView(this.mParams.getSubCategory(), this.mParams.mCreatureTagName, category);
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            creatureFileView.filterFileIds(this.mParams.getFileIds());
        }
        if (PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
            creatureFileView.filterHidden(getDefaultDisplayType().ordinal());
        }
        creatureFileView.getQueryBuilder().clearOrderBy();
        creatureFileView.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName());
        creatureFileView.distinctIdCountForTimelineDateData();
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(creatureFileView.buildSelectQuery(), (SecLocationView) new MpLocationView(this.mParams), PreferenceFeatures.OneUi6x.SUPPORT_QOD_SEARCH);
        return getCursor(updateQueryForMultipleLocations, "Creature file date[" + category + "] : " + this.mParams.getSubCategory());
    }

    public Cursor getCreatureFileRealRatioCursor(IdentityCreatureUtil.Category category) {
        MpCreatureView mpCreatureView;
        if (!supportCreatureType(category)) {
            Log.w("PeoplePetImpl", "getCreatureFileRealRatioCursor: failed");
            return null;
        }
        QueryParams queryParams = this.mParams;
        if (queryParams.mCreatureFaceGroupIds != null) {
            mpCreatureView = getCreatureFileView((String) null, (String) null, category);
        } else {
            mpCreatureView = getCreatureFileView(queryParams.getSubCategory(), this.mParams.mCreatureTagName, category);
        }
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            mpCreatureView.filterFileIds(this.mParams.getFileIds());
        }
        if (PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
            mpCreatureView.filterHidden(getDefaultDisplayType().ordinal());
        }
        if (this.mParams.getOrder() != null) {
            mpCreatureView.getQueryBuilder().clearOrderBy().addOrderBy(this.mParams.getOrder());
        }
        QueryParams queryParams2 = this.mParams;
        long j2 = queryParams2.mStartTime;
        if (j2 != -1) {
            long j3 = queryParams2.mEndTime;
            if (j3 != -1) {
                mpCreatureView.filterCreationTime(j2 - 1, j3);
            }
        }
        if (this.mParams.getLimitSize() > 0) {
            mpCreatureView.limit(this.mParams.getLimitSize());
        }
        if (!TextUtils.isEmpty(this.mParams.mFileNamePrefixExclude)) {
            QueryBuilder queryBuilder = mpCreatureView.getQueryBuilder();
            queryBuilder.andCondition("title not like '" + this.mParams.mFileNamePrefixExclude + "%'");
        }
        return ImplHelper.getRealRatioMergedCursor((BaseView) mpCreatureView, this.mQueryExecutor);
    }

    public Cursor getHiddenPeopleAndPetsCursor() {
        long currentTimeMillis = System.currentTimeMillis();
        CreatureDisplayType creatureDisplayType = CreatureDisplayType.HIDDEN;
        Query creatureQuery = getCreatureQuery(creatureDisplayType, true, IdentityCreatureUtil.Category.PEOPLE);
        Cursor[] cursorArr = new Cursor[2];
        new LatchBuilder("HiddenPeopleAndPetsQuery").setCurrent(new c(this, cursorArr, getCreatureQuery(creatureDisplayType, true, IdentityCreatureUtil.Category.PET), 0)).addWorker(new c(this, cursorArr, creatureQuery, 1)).start();
        Log.d("PeoplePetImpl", C0086a.j(currentTimeMillis, Logger.toString(cursorArr), " +", new StringBuilder("getHiddenPeopleAndPetsCursor ")));
        return new MergeCursor(cursorArr);
    }

    public int getHiddenPeopleCount() {
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            return getCursorCount(getCreatureCountCursor(CreatureDisplayType.HIDDEN, IdentityCreatureUtil.Category.PEOPLE), "HIDDEN_PEOPLE_COUNT");
        }
        return 0;
    }

    public Cursor getHiddenPeopleCursor() {
        return getCursor(getCreatureQuery(CreatureDisplayType.HIDDEN, true, IdentityCreatureUtil.Category.PEOPLE), "CrHiddenPeople");
    }

    public int getHiddenPetCount() {
        if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            return getCursorCount(getCreatureCountCursor(CreatureDisplayType.HIDDEN, IdentityCreatureUtil.Category.PET), "HIDDEN_PET_COUNT");
        }
        return 0;
    }

    public Cursor getPeopleAndPetsCursor() {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        Query unifiedPeopleQuery = getUnifiedPeopleQuery();
        Query petsQuery = getPetsQuery();
        long fileId = this.mParams.getFileId();
        if (fileId != -1) {
            str = a.f("_", fileId);
        } else {
            str = "";
        }
        Cursor[] cursorArr = new Cursor[2];
        String str2 = str;
        new LatchBuilder("PeopleAndPetsQuery").setCurrent(new d(this, cursorArr, petsQuery, str2, 0)).addWorker(new d(this, cursorArr, unifiedPeopleQuery, str2, 1)).start();
        Log.d("PeoplePetImpl", C0086a.j(currentTimeMillis, Logger.toString(cursorArr), " +", new StringBuilder("getPeopleAndPetsCursor ")));
        return new MergeCursor(cursorArr);
    }

    public int getPeopleCount() {
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            return getCursorCount(getCreatureCountCursor(getDefaultDisplayType(), IdentityCreatureUtil.Category.PEOPLE), "PEOPLE_COUNT");
        }
        return getCursorCount(getPeopleCursor(), "PEOPLE_COUNT");
    }

    public Cursor getPeopleCursor() {
        Query query;
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            query = getUnifiedPeopleQuery();
        } else {
            Query namedPeopleQuery = getNamedPeopleQuery();
            Query unnamedPeopleQuery = getUnnamedPeopleQuery();
            if (this.mParams.VISUAL_SEARCH_ONEUI_30) {
                String[] strArr = {"*", "count(distinct __absID) as __count"};
                namedPeopleQuery.getQueryBuilder().addProjection(strArr);
                unnamedPeopleQuery.getQueryBuilder().addProjection(strArr);
            }
            query = namedPeopleQuery.unionAll(unnamedPeopleQuery);
        }
        if (this.mParams.getLimitSize() > 0) {
            query.getQueryBuilder().limit(String.valueOf(this.mParams.getLimitSize()));
        }
        return getCursor(query, "PEOPLE");
    }

    public Cursor getPeopleFileCursor() {
        return getCreatureFileCursor(IdentityCreatureUtil.Category.PEOPLE);
    }

    public Cursor getPeopleFileDateCursor() {
        return getCreatureFileDateCursor(IdentityCreatureUtil.Category.PEOPLE);
    }

    public Cursor getPeopleFileRealRatioCursor() {
        return getCreatureFileRealRatioCursor(IdentityCreatureUtil.Category.PEOPLE);
    }

    public int getPeopleNoRelationshipCount() {
        return getCursorCount(getPeopleNoRelationshipCursor(), "PEOPLE_NO_RELATIONSHIP_COUNT");
    }

    public Cursor getPeopleNoRelationshipCursor() {
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            this.mParams.mNoRelationshipCreature = true;
            return getCursor(getUnifiedPeopleQuery(), "PEOPLE_NO_RELATIONSHIP");
        }
        this.mParams.mNoRelationshipCreature = true;
        return getPeopleCursor();
    }

    public int getPetCount() {
        if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            return getCursorCount(getCreatureCountCursor(getDefaultDisplayType(), IdentityCreatureUtil.Category.PET), "PET_COUNT");
        }
        return 0;
    }

    public Cursor getPetsCursor() {
        return getCursor(getPetsQuery(), "PETS");
    }

    public Cursor getPetsFileCursor() {
        return getCreatureFileCursor(IdentityCreatureUtil.Category.PET);
    }

    public Cursor getPetsFileDateCursor() {
        return getCreatureFileDateCursor(IdentityCreatureUtil.Category.PET);
    }

    public Cursor getPetsFileRealRatioCursor() {
        return getCreatureFileRealRatioCursor(IdentityCreatureUtil.Category.PET);
    }

    public Query getPetsQuery() {
        return getCreatureQuery(getDefaultDisplayType(), true, IdentityCreatureUtil.Category.PET);
    }

    public Query getUnifiedPeopleQuery() {
        return getCreatureQuery(getDefaultDisplayType(), true, IdentityCreatureUtil.Category.PEOPLE);
    }

    private Query getNamedPeopleQuery(long j2, boolean z) {
        boolean z3 = j2 != -1;
        MpPeopleView mpPeopleView = new MpPeopleView(this.mParams);
        if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE) {
            mpPeopleView.addCreatureFaceScoreProjection();
            mpPeopleView.orderByFaceScore();
        }
        mpPeopleView.filterBurstShotBestImage(false);
        mpPeopleView.filterNamed();
        mpPeopleView.filterCreatures();
        if (!SdkConfig.atLeast(SdkConfig.GED.R)) {
            mpPeopleView.orderByASC();
        }
        if (z3) {
            mpPeopleView.filterFileId(j2);
        }
        if (this.mParams.mNoRelationshipCreature) {
            mpPeopleView.filterNoRelationship();
        }
        long j3 = this.mParams.mFaceGroupId;
        if (j3 != -1) {
            mpPeopleView.filterGroupId(j3);
        }
        Query query = new Query(mpPeopleView.buildSelectQuery());
        if (z) {
            query.getQueryBuilder().groupBy("__creatureID");
        }
        return query;
    }

    private Query getUnnamedPeopleQuery(long j2, boolean z) {
        boolean z3 = j2 != -1;
        MpPeopleView mpPeopleView = new MpPeopleView(this.mParams);
        if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE) {
            mpPeopleView.addCreatureFaceScoreProjection();
            mpPeopleView.orderByFaceScore();
        }
        mpPeopleView.filterBurstShotBestImage(false);
        mpPeopleView.filterUnnamed();
        mpPeopleView.filterCreatures();
        if (!SdkConfig.atLeast(SdkConfig.GED.R)) {
            mpPeopleView.orderByASC();
        }
        if (z3) {
            mpPeopleView.filterFileId(j2);
        }
        if (this.mParams.mNoRelationshipCreature) {
            mpPeopleView.filterNoRelationship();
        }
        long j3 = this.mParams.mFaceGroupId;
        if (j3 != -1) {
            mpPeopleView.filterGroupId(j3);
        }
        Query query = new Query(mpPeopleView.buildSelectQuery());
        if (z) {
            query.getQueryBuilder().groupBy("__creatureFaceGroupID");
            if (!z3) {
                query.getQueryBuilder().having("count(distinct __absID)>=5");
            }
        }
        return query;
    }
}
