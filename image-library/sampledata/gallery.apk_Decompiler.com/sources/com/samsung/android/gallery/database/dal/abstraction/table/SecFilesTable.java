package com.samsung.android.gallery.database.dal.abstraction.table;

import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dbtype.DateType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SecFilesTable {
    void addDataStamp();

    void addGroupMediaCountProjection();

    void addGroupMediaCountProjection(QueryBuilder queryBuilder);

    void addOrderByDate();

    void addOrderByIds(String str, List<String> list);

    void addParentAlbumId(int i2);

    void addProjection(String str, String str2);

    void addProjectionDay();

    void addProjectionForCursorCount(boolean z);

    void addProjectionForMaxId();

    void addProjectionHideAlbum();

    void addProjectionSefFileTypes();

    void addSingleCount();

    void appendVolumeNameForFaces();

    Query buildSelectQuery();

    void clearOrderBy();

    void clearProjection();

    void clearSelection();

    void filter360Video();

    void filter3dCaptureShotMode();

    void filterAlbumID(int i2);

    void filterAlbumIDWithFavorites(int i2);

    void filterAlbumIDs(Collection<Integer> collection);

    void filterAlbumIDs(int[] iArr);

    void filterAlbumIDsWithFavorites(int[] iArr);

    void filterAlbumName(String str, boolean z);

    void filterAlbumsName(String str);

    void filterAllScreenShot();

    void filterAndGroupBurstShot();

    void filterAndGroupSefShotMode(boolean z);

    void filterAndGroupSelfieShotMode();

    void filterAndGroupSingleTaken();

    void filterApvShotMode();

    void filterBlurryImages();

    void filterBucketIds(Collection<Integer> collection, boolean z);

    void filterBurstShotOnly();

    void filterByFromTime(String str, boolean z);

    void filterByMap();

    void filterCloud();

    void filterCloudOnlyOnTransfer();

    void filterCreationTime(long j2, long j3);

    void filterCreationTimeForPdcFiles(long j2, long j3);

    void filterDataLike(String str);

    void filterFilePathOnly(String str);

    void filterFileStatus();

    void filterGalleryMedia();

    void filterGalleryMedia(String str);

    void filterGenerated();

    void filterGif();

    void filterGroupMediaBest(boolean z);

    void filterGroupMediaHasBest();

    void filterGroupMediaId(int i2, long j2);

    void filterGroupMediaIds(int i2, String str);

    void filterHidden();

    void filterId(long j2);

    void filterIds(String str);

    void filterImage();

    void filterIsFavorite();

    void filterIsPending();

    void filterLocalOnly(boolean z);

    void filterLogShotMode();

    void filterMediaID(long j2);

    void filterMediaIDs(String str);

    void filterMimeType(String... strArr);

    void filterMinResolution(int i2, int i7);

    void filterModifiedTime(long j2, long j3);

    void filterMountedVolume();

    void filterNoLocation();

    void filterNonDestructiveRecording(String str);

    void filterNullDate();

    void filterNullFileSize();

    void filterNullMediaSize();

    void filterPartitionIds(long j2, long j3);

    void filterPartitionIdsByMediaId(long j2, long j3);

    void filterRawShotMode();

    void filterRecentlyEdited();

    void filterRecordingMode(ArrayList<Integer> arrayList);

    void filterSefFileType(ArrayList<Integer> arrayList);

    void filterSingleTakeOnly();

    void filterStorageType();

    void filterVideo();

    void filterWrongDate();

    void filterWrongFileTime();

    String get360VideoQuery();

    String getAlbumOrderByQuery(int i2);

    String getAliasColumnName(String str);

    String getBurstShotOnlyQuery();

    String getColumnDateTaken();

    String getColumnGroupMediaId();

    String getColumnNameMediaId();

    String getDefaultIndex();

    String getFilterBlurryImages();

    String getFilterImage();

    String getFilterIsFavorite();

    String getFilterVideo();

    String getHavingBySort(int i2);

    ArrayList<String> getProjectionArray();

    QueryBuilder getQueryBuilder();

    String getSingleTakenQuery();

    String getTableName();

    String getTableNameRaw();

    String getWhere();

    void groupBy(String str, String str2);

    void groupByAlbum();

    void groupByMediaType();

    void groupForAlbum(boolean z);

    void includeIds(String str);

    void limit(int i2);

    void limit(int i2, int i7);

    void modifyForAllFilesData(DateType dateType, String str);

    void modifyForTimelineDateData(DateType dateType, String str);

    void modifyForTimelineDateData(DateType dateType, String str, int i2);

    void orderBy(String str);

    void orderByAlbumPictures(int i2);

    void orderByMap();

    void orderByModifiedTime();

    void orderByName();

    void orderByRecent();

    void orderForNewAlbumLabel();

    void removeBurstShotProjections();

    void replaceDateTakenFrom(long j2);

    void resetProjectionForAlbum();

    void resetProjectionForAutoComplete();

    void resetProjectionForCursorCount(boolean z);

    void resetProjectionForGroupMediaId();

    void resetProjectionForID();

    void resetProjectionForStoryAppBar();

    void setDefaultProjection();

    void setIndex(String str);

    void modifyQueryForYear() {
    }
}
