package com.samsung.android.gallery.database.dal.mp.impl;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.abstraction.table.SecLocationView;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpLocationView;
import com.samsung.android.gallery.database.dal.util.QueryUtils;
import com.samsung.android.gallery.database.dbtype.DateType;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import i.C0212a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VirtualAlbumsImpl extends BaseImpl {
    private static final boolean SUPPORT_FAVORITE_INDEX = Features.isEnabled(Features.SUPPORT_FAVORITE_INDEX);

    public VirtualAlbumsImpl(QueryParams queryParams) {
        super(queryParams);
    }

    private void addGroupMediaIdProjection(SecFilesTable secFilesTable) {
        if (PreferenceFeatures.OneUi41.SUPPORT_DIRECTORS_VIEW) {
            secFilesTable.getQueryBuilder().addProjection(secFilesTable.getColumnGroupMediaId(), "__GroupMediaID");
        }
    }

    private SecFilesTable createAlbumVirtualFavoriteFilesTable() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterStorageType();
        mpFilesTable.filterIsFavorite();
        mpFilesTable.orderByAlbumPictures(this.mParams.getSortBy());
        return mpFilesTable;
    }

    private SecFilesTable createAlbumVirtualFilesTable() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterGroupMediaBest(true);
        String str = this.mParams.mDataLike;
        if (str != null) {
            mpFilesTable.filterDataLike(str);
        }
        mpFilesTable.orderByAlbumPictures(this.mParams.getSortBy());
        return mpFilesTable;
    }

    private SecFilesTable createAlbumVirtualRecentTable() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterStorageType();
        GroupType[] groupTypes = this.mParams.getGroupTypes();
        if (groupTypes != null && groupTypes.length > 0) {
            mpFilesTable.filterGroupMediaBest(true);
        }
        if (this.mParams.getLimitSize() > 0) {
            mpFilesTable.limit(this.mParams.getLimitSize());
        }
        mpFilesTable.orderByRecent();
        return mpFilesTable;
    }

    private SecFilesTable createAlbumVirtualVideoFilesTable() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterVideo();
        mpFilesTable.filterStorageType();
        mpFilesTable.filterHidden();
        QueryParams queryParams = this.mParams;
        if (!(queryParams.mStartTime == -1 || queryParams.mEndTime == -1)) {
            if (SortByType.getSortBy(queryParams.getSortBy()) == 20) {
                QueryParams queryParams2 = this.mParams;
                mpFilesTable.filterModifiedTime(queryParams2.mStartTime, queryParams2.mEndTime);
            } else {
                QueryParams queryParams3 = this.mParams;
                mpFilesTable.filterCreationTime(queryParams3.mStartTime, queryParams3.mEndTime);
            }
        }
        mpFilesTable.orderByAlbumPictures(this.mParams.getSortBy());
        return mpFilesTable;
    }

    private SecFilesTable createVirtualRecentShareTable() {
        this.mParams.setWithEmptyAlbums(false).setGroupTypes(new GroupType[0]);
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterStorageType();
        if (this.mParams.getLimitSize() > 0) {
            mpFilesTable.limit(this.mParams.getLimitSize());
        }
        mpFilesTable.orderByRecent();
        return mpFilesTable;
    }

    private void modifyForTimelineDateData(SecFilesTable secFilesTable) {
        secFilesTable.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName(), SortByType.getSortBy(this.mParams.getSortBy()));
    }

    private Query updateQueryForLocations(SecFilesTable secFilesTable) {
        Query buildSelectQuery = secFilesTable.buildSelectQuery();
        String replace = secFilesTable.getAlbumOrderByQuery(this.mParams.getSortBy()).replace("A.date_modified", "__dateModified");
        return QueryUtils.updateQueryForMultipleLocations(buildSelectQuery, replace.replace("A." + getDateTakenColumnName() + "", "__dateTaken").replace(", A._ID DESC", "").replace(", A._ID ASC", ""), (SecLocationView) new MpLocationView(this.mParams));
    }

    public SecFilesTable createVirtualRepairFilesTable() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        String str = (String) this.mParams.getTag("repair-data-type", "invalid time");
        mpFilesTable.filterLocalOnly(true);
        mpFilesTable.filterGroupMediaBest(true);
        if ("invalid time".equals(str)) {
            mpFilesTable.filterImage();
            mpFilesTable.filterMimeType("image/jpeg", "image/jpg");
            mpFilesTable.filterWrongDate();
            return mpFilesTable;
        } else if ("no location".equals(str)) {
            mpFilesTable.filterImage();
            mpFilesTable.filterMimeType("image/jpeg", "image/jpg");
            mpFilesTable.filterNoLocation();
            return mpFilesTable;
        } else if ("0-size".equals(str)) {
            mpFilesTable.filterGalleryMedia();
            mpFilesTable.filterNullFileSize();
            return mpFilesTable;
        } else if ("wrong width/height".equals(str)) {
            mpFilesTable.filterGalleryMedia();
            mpFilesTable.filterNullMediaSize();
            return mpFilesTable;
        } else if ("no date/time".equals(str)) {
            mpFilesTable.filterNullDate();
            return mpFilesTable;
        } else {
            if ("wrong file time".equals(str)) {
                mpFilesTable.filterWrongFileTime();
            }
            return mpFilesTable;
        }
    }

    public Cursor getAlbumVirtualFavoriteAlbumCursor() {
        SecFilesTable createAlbumVirtualFavoriteFilesTable = createAlbumVirtualFavoriteFilesTable();
        int sortBy = this.mParams.getSortBy();
        if (SortByType.getSortBy(sortBy) == 30) {
            createAlbumVirtualFavoriteFilesTable.clearProjection();
            createAlbumVirtualFavoriteFilesTable.addProjection("A._id", "__absID");
            String query = createAlbumVirtualFavoriteFilesTable.buildSelectQuery().toString();
            SecFilesTable createAlbumVirtualFavoriteFilesTable2 = createAlbumVirtualFavoriteFilesTable();
            createAlbumVirtualFavoriteFilesTable2.orderByAlbumPictures(sortBy);
            createAlbumVirtualFavoriteFilesTable2.addProjection("(select count(*) from (" + query + "))", "__count");
            createAlbumVirtualFavoriteFilesTable2.limit(1);
            if (SUPPORT_FAVORITE_INDEX) {
                createAlbumVirtualFavoriteFilesTable2.setIndex("favorite_idx");
            }
            return getCursor(createAlbumVirtualFavoriteFilesTable2.buildSelectQuery(), "getAlbumVirtualFavoriteAlbumCursor");
        }
        createAlbumVirtualFavoriteFilesTable.addProjectionForCursorCount(true);
        createAlbumVirtualFavoriteFilesTable.groupBy(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE, createAlbumVirtualFavoriteFilesTable.getHavingBySort(sortBy));
        createAlbumVirtualFavoriteFilesTable.clearOrderBy();
        if (SUPPORT_FAVORITE_INDEX) {
            createAlbumVirtualFavoriteFilesTable.setIndex("favorite_idx");
        }
        return getCursor(createAlbumVirtualFavoriteFilesTable.buildSelectQuery(), "getAlbumVirtualFavoriteAlbumCursor");
    }

    public int[] getAlbumVirtualFavoriteCount() {
        SecFilesTable createAlbumVirtualFavoriteFilesTable = createAlbumVirtualFavoriteFilesTable();
        createAlbumVirtualFavoriteFilesTable.resetProjectionForCursorCount(false);
        createAlbumVirtualFavoriteFilesTable.groupByMediaType();
        return QueryUtils.getMediaCount(this.mQueryExecutor, createAlbumVirtualFavoriteFilesTable.buildSelectQuery());
    }

    public Cursor getAlbumVirtualFavoriteCursor() {
        SecFilesTable createAlbumVirtualFavoriteFilesTable = createAlbumVirtualFavoriteFilesTable();
        createAlbumVirtualFavoriteFilesTable.orderByAlbumPictures(this.mParams.getSortBy());
        if (SUPPORT_FAVORITE_INDEX) {
            createAlbumVirtualFavoriteFilesTable.setIndex("favorite_idx");
        }
        int i2 = this.mParams.mParentAlbumId;
        if (i2 != -1) {
            createAlbumVirtualFavoriteFilesTable.addParentAlbumId(i2);
        }
        return getCursor(createAlbumVirtualFavoriteFilesTable.buildSelectQuery(), "getAlbumVirtualFavoriteCursor");
    }

    public Cursor getAlbumVirtualFavoriteDateCursor() {
        SecFilesTable createAlbumVirtualFavoriteFilesTable = createAlbumVirtualFavoriteFilesTable();
        modifyForTimelineDateData(createAlbumVirtualFavoriteFilesTable);
        createAlbumVirtualFavoriteFilesTable.orderByAlbumPictures(this.mParams.getSortBy());
        createAlbumVirtualFavoriteFilesTable.getQueryBuilder().addProjection("A.date_modified", "__dateModified");
        if (SUPPORT_FAVORITE_INDEX) {
            createAlbumVirtualFavoriteFilesTable.setIndex("favorite_idx");
        }
        return getCursor(updateQueryForLocations(createAlbumVirtualFavoriteFilesTable), "getAlbumVirtualFavoriteDateCursor");
    }

    public Cursor getAlbumVirtualFavoriteFileIdsOrdered() {
        SecFilesTable createAlbumVirtualFavoriteFilesTable = createAlbumVirtualFavoriteFilesTable();
        if (QueryParams.TargetDbTypes.SEC.equals(this.mParams.mTargetDb) && this.mParams.getOsVersion() >= 30 && SortByType.getSortBy(this.mParams.getSortBy()) == 10) {
            createAlbumVirtualFavoriteFilesTable.setIndex("datetime_id_idx");
        }
        Query fileIdOnlyQuery = ImplHelper.getFileIdOnlyQuery(this.mParams, createAlbumVirtualFavoriteFilesTable.getQueryBuilder());
        return getCursor(fileIdOnlyQuery, "getAlbumVirtualFavoriteFileIdsOrdered " + Arrays.toString(this.mParams.getAlbumIdArray()) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mParams.getLimitOffset() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mParams.getLimitSize());
    }

    public Cursor getAlbumVirtualFavoriteRealRatioCursor() {
        return ImplHelper.getRealRatioMergedCursor(createAlbumVirtualFavoriteFilesTable(), this.mQueryExecutor);
    }

    public Cursor getAlbumVirtualFilesCursor() {
        return getCursor(createAlbumVirtualFilesTable().buildSelectQuery(), "getAlbumVirtualFilesCursor");
    }

    public Cursor getAlbumVirtualFilesDateCursor() {
        SecFilesTable createAlbumVirtualFilesTable = createAlbumVirtualFilesTable();
        createAlbumVirtualFilesTable.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName(), this.mParams.getSortBy());
        return getCursor(createAlbumVirtualFilesTable.buildSelectQuery(), "getAlbumVirtualFilesDateCursor");
    }

    public Cursor getAlbumVirtualFilesRealRatioCursor() {
        return ImplHelper.getRealRatioMergedCursor(createAlbumVirtualFilesTable(), this.mQueryExecutor);
    }

    public Cursor getAlbumVirtualRecentAlbumCursor() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterStorageType();
        GroupType[] groupTypes = this.mParams.getGroupTypes();
        if (groupTypes != null && groupTypes.length > 0) {
            mpFilesTable.filterGroupMediaBest(false);
        }
        if (!this.mParams.SUPPORT_RECENT_PRIMARY) {
            mpFilesTable.setIndex("volume_id_idx");
        }
        mpFilesTable.orderByRecent();
        mpFilesTable.limit(1);
        return getCursor(mpFilesTable.buildSelectQuery(), "getAlbumVirtualRecentAlbumCursor");
    }

    public int[] getAlbumVirtualRecentCount(long j2) {
        SecFilesTable createAlbumVirtualRecentTable = createAlbumVirtualRecentTable();
        createAlbumVirtualRecentTable.resetProjectionForCursorCount(false);
        QueryBuilder queryBuilder = createAlbumVirtualRecentTable.getQueryBuilder();
        queryBuilder.andCondition("A._id >= " + j2);
        createAlbumVirtualRecentTable.groupByMediaType();
        return QueryUtils.getMediaCount(this.mQueryExecutor, createAlbumVirtualRecentTable.buildSelectQuery());
    }

    public Cursor getAlbumVirtualRecentCursor() {
        return getCursor(createAlbumVirtualRecentTable().buildSelectQuery(), "getAlbumVirtualRecentCursor");
    }

    public Cursor getAlbumVirtualRepairCursor() {
        return getCursor(createVirtualRepairFilesTable().buildSelectQuery(), "getAlbumVirtualRepairCursor");
    }

    public Cursor getAlbumVirtualRepairDateCursor() {
        SecFilesTable createVirtualRepairFilesTable = createVirtualRepairFilesTable();
        createVirtualRepairFilesTable.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName());
        return getCursor(QueryUtils.updateQueryForMultipleLocations(createVirtualRepairFilesTable.buildSelectQuery(), new MpLocationView(this.mParams)), "getAlbumVirtualRepairDateCursor");
    }

    public Cursor getAlbumVirtualRepairRealRatioCursor() {
        return ImplHelper.getRealRatioMergedCursor(createVirtualRepairFilesTable(), this.mQueryExecutor);
    }

    public int[] getAlbumVirtualVideoCount() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterVideo();
        mpFilesTable.resetProjectionForCursorCount(false);
        mpFilesTable.groupByMediaType();
        return QueryUtils.getMediaCount(this.mQueryExecutor, mpFilesTable.buildSelectQuery());
    }

    public Cursor getAlbumVirtualVideoCursor() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.addDataStamp();
        mpFilesTable.resetProjectionForGroupMediaId();
        addGroupMediaIdProjection(mpFilesTable);
        mpFilesTable.addSingleCount();
        mpFilesTable.filterVideo();
        mpFilesTable.filterStorageType();
        mpFilesTable.filterHidden();
        mpFilesTable.orderByAlbumPictures(this.mParams.getSortBy());
        return getCursor(mpFilesTable.buildSelectQuery(), "getAlbumVirtualVideoCursor");
    }

    public Cursor getAlbumVirtualVideoDateCursor() {
        SecFilesTable createAlbumVirtualVideoFilesTable = createAlbumVirtualVideoFilesTable();
        modifyForTimelineDateData(createAlbumVirtualVideoFilesTable);
        createAlbumVirtualVideoFilesTable.getQueryBuilder().addProjection("A.date_modified", "__dateModified");
        return getCursor(updateQueryForLocations(createAlbumVirtualVideoFilesTable), "getAlbumVirtualVideoDateCursor");
    }

    public Cursor getAlbumVirtualVideoFileIdsOrdered() {
        SecFilesTable createAlbumVirtualVideoFilesTable = createAlbumVirtualVideoFilesTable();
        if (QueryParams.TargetDbTypes.SEC.equals(this.mParams.mTargetDb) && this.mParams.getOsVersion() >= 30 && SortByType.getSortBy(this.mParams.getSortBy()) == 10) {
            createAlbumVirtualVideoFilesTable.setIndex("datetime_id_idx");
        }
        return getCursor(ImplHelper.getFileIdOnlyQuery(this.mParams, createAlbumVirtualVideoFilesTable.getQueryBuilder()), "getAlbumVirtualVideoFileIdsOrdered");
    }

    public Cursor getAlbumVirtualVideoRealRatioCursor() {
        return ImplHelper.getRealRatioMergedCursor(createAlbumVirtualVideoFilesTable(), this.mQueryExecutor);
    }

    public int getVirtualRecentShareCount() {
        SecFilesTable createVirtualRecentShareTable = createVirtualRecentShareTable();
        createVirtualRecentShareTable.resetProjectionForCursorCount(false);
        return QueryUtils.getCount(this.mQueryExecutor, createVirtualRecentShareTable.buildSelectQuery());
    }

    public Cursor getVirtualRecentShareDataCursor() {
        String str;
        SecFilesTable createVirtualRecentShareTable = createVirtualRecentShareTable();
        if (Features.isEnabled(Features.USE_SEC_MP)) {
            str = C0212a.m("CASE WHEN A.is_cloud = 2 THEN ", "\"" + MediaUri.getInstance().getSecPickerUriString() + "\" || (CASE WHEN A.media_type = 1 THEN \"image/\" ELSE \"video/\" END) || A.cloud_server_id || \"/\" || A._id", " ELSE \"content://media/external/\" || (CASE WHEN A.media_type = 1 THEN \"images/media/\" ELSE \"video/media/\" END) || A.media_id END");
        } else if (Features.isEnabled(Features.USE_NEWMP)) {
            str = C0212a.m("CASE WHEN A.is_cloud = 2 THEN ", "\"" + MediaUri.getInstance().getSecPickerUriString() + "\" || (CASE WHEN A.media_type = 1 THEN \"image/\" ELSE \"video/\" END) || A.media_id", " ELSE \"content://media/external/\" || (CASE WHEN A.media_type = 1 THEN \"images/media/\" ELSE \"video/media/\" END) || A.media_id END");
        } else {
            str = "\"content://media/external/\" || (CASE WHEN A.media_type = 1 THEN \"images/media/\" ELSE \"video/media/\" END) || A.media_id";
        }
        createVirtualRecentShareTable.clearProjection();
        createVirtualRecentShareTable.addProjection(str, "share_uri");
        createVirtualRecentShareTable.addProjection("A.bucket_id", "share_bucket_id");
        createVirtualRecentShareTable.addProjection("COALESCE(A.bucket_display_name,'')", "share_bucket_name");
        return getCursor(createVirtualRecentShareTable.buildSelectQuery(), "getVirtualRecentShareDataCursor");
    }

    public String tag() {
        return "VirtualAlbumsImpl";
    }
}
