package com.samsung.android.gallery.database.dal.mp.impl;

import A.a;
import android.database.Cursor;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.abstraction.table.SecLocationView;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpLocationView;
import com.samsung.android.gallery.database.dal.util.QueryUtils;
import com.samsung.android.gallery.database.dbtype.DateType;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFilesImpl extends BaseImpl {
    public AlbumFilesImpl(QueryParams queryParams) {
        super(queryParams);
    }

    private void addIndex(SecFilesTable secFilesTable) {
        if (QueryParams.TargetDbTypes.SEC.equals(this.mParams.mTargetDb) && this.mParams.getOsVersion() >= 30 && SortByType.getSortBy(this.mParams.getSortBy()) == 10) {
            secFilesTable.setIndex("datetime_id_idx");
        }
    }

    private void applySortBy(boolean z, SecFilesTable secFilesTable) {
        secFilesTable.orderByAlbumPictures(this.mParams.getSortBy());
        if (z && SortByType.getSortBy(this.mParams.getSortBy()) == 10) {
            String defaultIndex = secFilesTable.getDefaultIndex();
            if (!TextUtils.isEmpty(defaultIndex)) {
                secFilesTable.setIndex(defaultIndex);
            }
        }
    }

    private SecFilesTable createAlbumFilesTable() {
        boolean isWithEmptyAlbums = this.mParams.isWithEmptyAlbums();
        boolean isShowHidden = this.mParams.isShowHidden();
        boolean useBigAlbumIndex = this.mParams.useBigAlbumIndex();
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        boolean z = true;
        mpFilesTable.filterGroupMediaBest(true);
        if (this.mParams.getAlbumIdCount() != 1) {
            z = false;
        }
        if (z) {
            int i2 = this.mParams.getAlbumIdArray()[0];
            if (BucketUtils.isRecent(i2)) {
                mpFilesTable.filterHidden();
            } else if (!this.mParams.isIncludeFavorite() || !BucketUtils.isFavourite(i2)) {
                mpFilesTable.filterAlbumID(i2);
            } else {
                mpFilesTable.filterAlbumIDWithFavorites(i2);
            }
        } else if (!this.mParams.isIncludeFavorite() || !BucketUtils.containsFavourite(this.mParams.getAlbumIdArray())) {
            mpFilesTable.filterAlbumIDs(this.mParams.getAlbumIdArray());
        } else {
            mpFilesTable.filterAlbumIDsWithFavorites(this.mParams.getAlbumIdArray());
        }
        if (ScreenShotFilterType.All.key().equals(this.mParams.getSubCategory())) {
            mpFilesTable.filterAllScreenShot();
        }
        if (this.mParams.getFileIds() != null) {
            mpFilesTable.filterIds(this.mParams.getFileIds());
        }
        setDateTakenFrom(mpFilesTable);
        if (z) {
            if (!isWithEmptyAlbums || !isShowHidden) {
                applySortBy(useBigAlbumIndex, mpFilesTable);
            } else {
                mpFilesTable.addOrderByDate();
            }
        } else if (PreferenceFeatures.OneUi5x.MX_ALBUMS && this.mParams.getSortBy() != 12) {
            applySortBy(useBigAlbumIndex, mpFilesTable);
        }
        QueryParams queryParams = this.mParams;
        long j2 = queryParams.mStartTime;
        if (j2 != -1) {
            long j3 = queryParams.mEndTime;
            if (j3 != -1) {
                if (queryParams.mPdcFiles) {
                    mpFilesTable.filterCreationTimeForPdcFiles(j2, j3);
                } else if (SortByType.getSortBy(queryParams.getSortBy()) == 20) {
                    QueryParams queryParams2 = this.mParams;
                    mpFilesTable.filterModifiedTime(queryParams2.mStartTime, queryParams2.mEndTime);
                } else {
                    QueryParams queryParams3 = this.mParams;
                    mpFilesTable.filterCreationTime(queryParams3.mStartTime, queryParams3.mEndTime);
                    if (!this.mParams.mIsOrderImmutable) {
                        mpFilesTable.orderBy("ASC");
                    }
                }
            }
        }
        int limitSize = this.mParams.getLimitSize();
        if (limitSize > 0) {
            mpFilesTable.limit(limitSize);
        }
        if (this.mParams.needDataStamp()) {
            mpFilesTable.addDataStamp();
        }
        if (z && BucketUtils.isRecent(this.mParams.getAlbumIdArray()[0])) {
            mpFilesTable.orderByRecent();
        }
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER && isSceneFilter(this.mParams.getSubCategory())) {
            mpFilesTable.getQueryBuilder().addInnerJoin("scene AS S", "A._id = S.sec_media_id");
            C0212a.w("S.scene_name COLLATE NOCASE in ('", QueryBuilder.escapeString(this.mParams.getSubCategory()), "')", mpFilesTable.getQueryBuilder());
            if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT) {
                mpFilesTable.getQueryBuilder().addProjection("S.scene_name", "__subCategory");
            }
        }
        return mpFilesTable;
    }

    private boolean isSceneFilter(String str) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER || TextUtils.isEmpty(str) || ScreenShotFilterType.All.key().equals(str)) {
            return false;
        }
        return true;
    }

    public SecFilesTable createAlbumFilesCountTable(int[] iArr) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.resetProjectionForCursorCount(true);
        mpFilesTable.filterStorageType();
        mpFilesTable.filterAlbumIDs(iArr);
        setDateTakenFrom(mpFilesTable);
        return mpFilesTable;
    }

    public Cursor getAlbumCountCursor() {
        int[] albumIdArray = this.mParams.getAlbumIdArray();
        Query buildSelectQuery = createAlbumFilesCountTable(albumIdArray).buildSelectQuery();
        return getCursor(buildSelectQuery, "albumCount : " + Arrays.toString(albumIdArray));
    }

    public Cursor getAlbumFileCursor() {
        SecFilesTable createAlbumFilesTable = createAlbumFilesTable();
        addIndex(createAlbumFilesTable);
        Query buildSelectQuery = createAlbumFilesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "albumFile : " + Arrays.toString(this.mParams.getAlbumIdArray()));
    }

    public Cursor getAlbumFileDateCursor() {
        if (!SortByType.isByTime(this.mParams.getSortBy())) {
            return null;
        }
        SecFilesTable createAlbumFilesTable = createAlbumFilesTable();
        createAlbumFilesTable.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName(), SortByType.getSortBy(this.mParams.getSortBy()));
        createAlbumFilesTable.getQueryBuilder().addProjection("A.date_modified", "__dateModified");
        Query buildSelectQuery = createAlbumFilesTable.buildSelectQuery();
        String replace = createAlbumFilesTable.getAlbumOrderByQuery(this.mParams.getSortBy()).replace("A.date_modified", "__dateModified");
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(buildSelectQuery, replace.replace("A." + getDateTakenColumnName() + "", "__dateTaken").replace(", A._ID DESC", "").replace(", A._ID ASC", ""), (SecLocationView) new MpLocationView(this.mParams));
        return getCursor(updateQueryForMultipleLocations, "albumDate : " + Arrays.toString(this.mParams.getAlbumIdArray()));
    }

    public Cursor getAlbumFileIds() {
        return getCursor(ImplHelper.getFileIds(this.mParams, createAlbumFilesTable()), "getAlbumFileIds");
    }

    public Cursor getAlbumFileIdsOrdered() {
        SecFilesTable createAlbumFilesTable = createAlbumFilesTable();
        addIndex(createAlbumFilesTable);
        Query fileIdOnlyQuery = ImplHelper.getFileIdOnlyQuery(this.mParams, createAlbumFilesTable.getQueryBuilder());
        return getCursor(fileIdOnlyQuery, "getAlbumFileIdsOrdered " + Arrays.toString(this.mParams.getAlbumIdArray()) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mParams.getLimitOffset() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mParams.getLimitSize());
    }

    public Cursor getAlbumFileRealRatioCursor() {
        return ImplHelper.getRealRatioMergedCursor(createAlbumFilesTable(), this.mQueryExecutor);
    }

    public int getPositionByAlbum(int[] iArr, long j2, long j3, long j8) {
        Throwable th;
        if (iArr.length == 1 && BucketUtils.isVirtualAlbum(iArr[0])) {
            return getPositionByVirtualAlbum(iArr, j3, j8);
        }
        long j10 = j8;
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.resetProjectionForCursorCount(false);
        if (BucketUtils.containsFavourite(iArr)) {
            mpFilesTable.filterAlbumIDsWithFavorites(iArr);
        } else {
            mpFilesTable.filterAlbumIDs(iArr);
        }
        if (j2 == 0) {
            long j11 = j3;
            mpFilesTable.filterPartitionIds(j3, j10);
        } else {
            mpFilesTable.filterPartitionIdsByMediaId(j2, j10);
        }
        mpFilesTable.filterGroupMediaBest(true);
        mpFilesTable.addOrderByDate();
        Cursor cursor = getCursor(mpFilesTable.buildSelectQuery(), "getPositionByAlbum");
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int i2 = cursor.getInt(0);
                    cursor.close();
                    return i2;
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (cursor == null) {
            return -1;
        }
        cursor.close();
        return -1;
        throw th;
    }

    public int getPositionByVirtualAlbum(int[] iArr, long j2, long j3) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.resetProjectionForCursorCount(false);
        if (!BucketUtils.isRecent(iArr[0])) {
            mpFilesTable.filterPartitionIds(j2, j3);
        } else if (this.mParams.SUPPORT_RECENT_PRIMARY) {
            String f = a.f("A._id > ", j2);
            String e = a.e(j2, "(select recent_primary from files where _id=", ")");
            mpFilesTable.getQueryBuilder().andCondition(C0212a.p(C0086a.q("(A.recent_primary > ", e, " OR (A.recent_primary = ", e, " AND "), f, "))"));
        } else {
            mpFilesTable.filterPartitionIds(j2, -1);
        }
        mpFilesTable.filterGroupMediaBest(true);
        if (BucketUtils.isRecent(iArr[0])) {
            mpFilesTable.orderByRecent();
        } else {
            mpFilesTable.filterIsFavorite();
            mpFilesTable.addOrderByDate();
        }
        Cursor cursor = getCursor(mpFilesTable.buildSelectQuery(), "getPositionByVirtualAlbum");
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int i2 = cursor.getInt(0);
                    cursor.close();
                    return i2;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (cursor == null) {
            return -1;
        }
        cursor.close();
        return -1;
        throw th;
    }

    public String tag() {
        return "AlbumFilesApi";
    }
}
