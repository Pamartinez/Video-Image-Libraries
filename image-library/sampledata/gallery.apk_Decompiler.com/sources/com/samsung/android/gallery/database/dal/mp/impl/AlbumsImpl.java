package com.samsung.android.gallery.database.dal.mp.impl;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.support.utils.BucketUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsImpl extends BaseImpl {
    public AlbumsImpl(QueryParams queryParams) {
        super(queryParams);
    }

    private SecFilesTable createAlbumFilesCountTable(int[] iArr) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.resetProjectionForCursorCount(true);
        mpFilesTable.filterStorageType();
        mpFilesTable.filterAlbumIDs(iArr);
        if (this.mParams.getFromNow() != -1) {
            mpFilesTable.replaceDateTakenFrom(this.mParams.getFromNow());
        }
        return mpFilesTable;
    }

    public Cursor getAlbumAutoCompleteCursor() {
        return getCursor(getAlbumAutoCompleteQuery(), "AutoComplete Album");
    }

    public Query getAlbumAutoCompleteQuery() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterGroupMediaBest(true);
        mpFilesTable.filterGalleryMedia();
        mpFilesTable.groupByAlbum();
        QueryBuilder queryBuilder = mpFilesTable.getQueryBuilder();
        queryBuilder.clearProjection();
        queryBuilder.addProjection("A._data", "__absPath");
        queryBuilder.addProjection("A.smartcrop_rect_ratio", "__sceneRegion");
        queryBuilder.addProjection("A.media_type", "__mediaType");
        queryBuilder.addProjection("A.orientation", "__orientation");
        queryBuilder.addProjection("''", "cover_rect");
        queryBuilder.addProjection("A.is_cloud", "__storageType");
        queryBuilder.replaceProjectionByAlias("A.bucket_display_name", "__Title");
        queryBuilder.replaceProjectionByAlias("A.bucket_display_name", "__subCategory");
        queryBuilder.replaceProjectionByAliasWithValue("Search Auto Complete", "__mainCategory");
        return mpFilesTable.buildSelectQuery();
    }

    public int getAlbumCount(MediaType mediaType) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.resetProjectionForCursorCount(true);
        if (this.mParams.getAlbumIdCount() > 1) {
            mpFilesTable.filterAlbumIDs((Collection<Integer>) this.mParams.getAlbumIdList());
        } else {
            int intValue = this.mParams.getAlbumIdList().get(0).intValue();
            if (BucketUtils.isRecent(intValue)) {
                mpFilesTable.filterHidden();
            } else {
                mpFilesTable.filterAlbumID(intValue);
            }
        }
        mpFilesTable.filterGroupMediaBest(false);
        if (mediaType == MediaType.Video) {
            mpFilesTable.filterVideo();
        } else if (mediaType == MediaType.Image) {
            mpFilesTable.filterImage();
        } else {
            mpFilesTable.filterGalleryMedia();
        }
        Query buildSelectQuery = mpFilesTable.buildSelectQuery();
        Cursor cursor = getCursor(buildSelectQuery, "getAlbumCount#" + mediaType);
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
        if (cursor != null) {
            cursor.close();
        }
        return 0;
        throw th;
    }

    public Cursor getAlbumCountCursor() {
        int[] albumIdArray = this.mParams.getAlbumIdArray();
        Query buildSelectQuery = createAlbumFilesCountTable(albumIdArray).buildSelectQuery();
        return getCursor(buildSelectQuery, "albumCount : " + Arrays.toString(albumIdArray));
    }

    public Cursor getAlbumCursor() {
        int albumIdCount = this.mParams.getAlbumIdCount();
        if (albumIdCount >= 1 && this.mParams.getDbStorageType() == QueryParams.DbStorageType.includeCloud) {
            return getCloudAlbumListCursor(this.mParams.getAlbumIdList());
        }
        if (albumIdCount == 1) {
            return getAlbumCursor(this.mParams.getAlbumIdArray()[0]);
        }
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.clearSelection();
        mpFilesTable.filterIsPending();
        mpFilesTable.resetProjectionForAlbum();
        mpFilesTable.filterFileStatus();
        mpFilesTable.filterGalleryMedia(this.mParams.getMediaTypeFilter());
        mpFilesTable.filterMountedVolume();
        mpFilesTable.filterStorageType();
        mpFilesTable.filterGroupMediaBest(true);
        mpFilesTable.filterCloudOnlyOnTransfer();
        if (this.mParams.getLimitSize() > 0) {
            mpFilesTable.limit(this.mParams.getLimitSize());
        }
        if (!TextUtils.isEmpty(this.mParams.getNames())) {
            mpFilesTable.filterAlbumsName(this.mParams.getNames());
        } else if (!TextUtils.isEmpty(this.mParams.getAlbumDisplayName())) {
            mpFilesTable.filterAlbumName(this.mParams.getAlbumDisplayName(), false);
        }
        ArrayList<Integer> excludeAlbumIdList = this.mParams.getExcludeAlbumIdList();
        if (excludeAlbumIdList != null) {
            mpFilesTable.filterBucketIds(excludeAlbumIdList, false);
        }
        ArrayList<Integer> albumIdList = this.mParams.getAlbumIdList();
        if (albumIdList != null && albumIdList.size() > 0) {
            mpFilesTable.filterBucketIds(albumIdList, true);
        }
        QueryParams queryParams = this.mParams;
        int i2 = queryParams.minResolution;
        if (i2 > 0) {
            mpFilesTable.filterMinResolution(i2, queryParams.minSize);
        }
        if (this.mParams.isShowHidden()) {
            mpFilesTable.addProjectionHideAlbum();
        }
        mpFilesTable.groupForAlbum(this.mParams.isShowHidden());
        mpFilesTable.clearOrderBy();
        setAlbumIndex(mpFilesTable);
        return getCursor(mpFilesTable.buildSelectQuery(), "getAlbumData");
    }

    public Cursor getCloudAlbumListCursor(ArrayList<Integer> arrayList) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterBucketIds(arrayList, true);
        mpFilesTable.filterCloud();
        mpFilesTable.groupForAlbum(false);
        return getCursor(mpFilesTable.buildSelectQuery(), "getCloudAlbumListData");
    }

    public Cursor getNewAlbumCursor() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterGroupMediaBest(true);
        mpFilesTable.filterStorageType();
        mpFilesTable.clearOrderBy();
        mpFilesTable.orderForNewAlbumLabel();
        mpFilesTable.limit(1);
        return getCursor(mpFilesTable.buildSelectQuery(), "getNewAlbumCursor");
    }

    public void setAlbumIndex(SecFilesTable secFilesTable) {
        if (this.mParams.getOsVersion() >= 33 || this.mParams.getOsVersion() < 29) {
            secFilesTable.setIndex("bucket_name");
        }
    }

    public String tag() {
        return "AlbumsImpl";
    }

    public int[] getAlbumCount() {
        return new int[]{getAlbumCount(MediaType.Image), getAlbumCount(MediaType.Video)};
    }

    public Cursor getAlbumCursor(int i2) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterStorageType();
        mpFilesTable.filterGroupMediaBest(true);
        if (this.mParams.isShowHidden()) {
            mpFilesTable.addProjectionHideAlbum();
        }
        mpFilesTable.filterCloudOnlyOnTransfer();
        mpFilesTable.groupForAlbum(this.mParams.isShowHidden());
        mpFilesTable.clearOrderBy();
        mpFilesTable.filterAlbumID(i2);
        Query buildSelectQuery = mpFilesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "getAlbumData : " + i2);
    }
}
