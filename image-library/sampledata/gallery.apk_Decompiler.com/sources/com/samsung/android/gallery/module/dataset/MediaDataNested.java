package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.data.AlbumCursorHolder;
import com.samsung.android.gallery.module.data.CloudData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataAlbum;
import com.samsung.android.gallery.module.dataset.comparator.Comparators;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataNested extends MediaDataAlbum {
    public MediaDataNested(Blackboard blackboard, String str, boolean z, boolean z3) {
        super(blackboard, str, true, z, z3);
    }

    private void addNestedFolderToParentFolder(HashMap<Integer, FolderItem> hashMap, HashMap<Integer, Integer> hashMap2) {
        for (Map.Entry next : hashMap2.entrySet()) {
            FolderItem folderItem = hashMap.get(next.getKey());
            FolderItem folderItem2 = hashMap.get(next.getValue());
            if (!(folderItem2 == null || folderItem == null)) {
                folderItem2.addItem(folderItem);
            }
        }
    }

    private void fakeLoading(Cursor cursor, ArrayList<MediaItem> arrayList, HashMap<Integer, MediaItem> hashMap, HashMap<Integer, FolderItem> hashMap2, boolean z) {
        if (cursor == null) {
            Log.w(this.TAG, "fakeLoading skip. null cursor");
            return;
        }
        Log.d(this.TAG, "fakeLoading", Boolean.valueOf(isIncludeNameMerged()));
        if (cursor.moveToFirst()) {
            AlbumCursorHolder albumCursorHolder = new AlbumCursorHolder(cursor);
            do {
                MediaItem mediaItem = hashMap.get(Integer.valueOf(albumCursorHolder.getInt(albumCursorHolder.indexBucketId, 0)));
                if (mediaItem != null) {
                    int count = mediaItem.getCount();
                    AlbumType albumType = AlbumType.get(albumCursorHolder.getInt(albumCursorHolder.indexAlbumType, -1));
                    if (!isFolder(count, albumType) && (!z || isIncludeNameMerged() || !AlbumType.isMergedAlbum(albumType.toInt()))) {
                        MediaItem coverItem = getCoverItem(albumCursorHolder, mediaItem, mediaItem.getAlbumCover());
                        int i2 = albumCursorHolder.getInt(albumCursorHolder.indexFolderId, 0);
                        if (i2 != 0) {
                            FolderItem folderItem = hashMap2.get(Integer.valueOf(i2));
                            if (folderItem != null) {
                                folderItem.addItem(coverItem);
                                setFolderTranslatedName(folderItem, coverItem);
                            } else {
                                arrayList.add(coverItem);
                            }
                        } else {
                            if (isEmptyAlbumData(coverItem.getPath(), count, i2)) {
                                coverItem.setDateModified(albumCursorHolder.getLong(albumCursorHolder.indexDateModified, 0) / 1000);
                            }
                            arrayList.add(coverItem);
                        }
                    }
                }
            } while (cursor.moveToNext());
        }
        arrayList.sort(this.mAlbumDataHelper.getComparator());
    }

    private MediaItem getCoverItem(AlbumCursorHolder albumCursorHolder, MediaItem mediaItem, String str) {
        if (!TextUtils.isEmpty(str)) {
            mediaItem = AlbumDataHelper.createCoverItem(mediaItem, str, albumCursorHolder.getString(albumCursorHolder.indexCoverRect, (String) null));
        }
        mediaItem.setAlbumLevel(albumCursorHolder.getInt(albumCursorHolder.indexAlbumLevel, 0));
        mediaItem.setAlbumType(AlbumType.get(albumCursorHolder.getInt(albumCursorHolder.indexAlbumType, -1)));
        return mediaItem;
    }

    private FolderItem getFolderItem(AlbumCursorHolder albumCursorHolder, int i2, AlbumType albumType) {
        FolderItem folderItem;
        String string = albumCursorHolder.getString(albumCursorHolder.indexTitle, (String) null);
        long j2 = albumCursorHolder.getLong(albumCursorHolder.indexAlbumOrder, 0);
        if (albumType == AlbumType.Merged) {
            folderItem = this.mAlbumDataHelper.createNameMergedItem(i2, string, j2);
        } else {
            folderItem = this.mAlbumDataHelper.createFolderItem(i2, string, j2);
        }
        boolean z = false;
        folderItem.setAlbumLevel(albumCursorHolder.getInt(albumCursorHolder.indexAlbumLevel, 0));
        folderItem.setAlbumType(albumType);
        if (albumCursorHolder.getInt(albumCursorHolder.indexAlbumShowInfo, 0) < 1) {
            z = true;
        }
        folderItem.setAlbumShowInfo(z);
        String string2 = albumCursorHolder.getString(albumCursorHolder.indexCoverPath, (String) null);
        if (TextUtils.isEmpty(string2)) {
            return folderItem;
        }
        return this.mAlbumDataHelper.createFolderCoverItem(folderItem, string2, albumCursorHolder.getString(albumCursorHolder.indexCoverRect, (String) null));
    }

    private MediaItem getMediaItemFromMap(Cursor cursor, AlbumCursorHolder albumCursorHolder, HashMap<Integer, MediaItem> hashMap) {
        int i2 = albumCursorHolder.getInt(albumCursorHolder.indexFolderId, 0);
        int i7 = albumCursorHolder.getInt(albumCursorHolder.indexBucketId, 0);
        int i8 = albumCursorHolder.getInt(albumCursorHolder.indexAlbumCount, 0);
        String string = albumCursorHolder.getString(albumCursorHolder.indexDefaultCoverPath, (String) null);
        MediaItem mediaItem = hashMap.get(Integer.valueOf(i7));
        if (mediaItem != null || !isEmptyAlbumData(string, i8, i2)) {
            return mediaItem;
        }
        return this.mAlbumDataHelper.createEmptyItem(cursor);
    }

    private boolean isFolder(int i2, AlbumType albumType) {
        boolean z;
        if (i2 == -1) {
            z = true;
        } else {
            z = false;
        }
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return z;
        }
        if ((!z || albumType != AlbumType.None) && albumType != AlbumType.Folder && !AlbumType.isMergedAlbum(albumType, isIncludeNameMerged())) {
            return false;
        }
        return true;
    }

    private void loadFolderMap(Cursor cursor, ArrayList<MediaItem> arrayList, HashMap<Integer, FolderItem> hashMap) {
        if (cursor == null) {
            Log.w(this.TAG, "loadFolderMap skip. null cursor");
            return;
        }
        Log.w((CharSequence) this.TAG, "loadFolderMap", Boolean.valueOf(isIncludeNameMerged()));
        if (cursor.moveToFirst()) {
            AlbumCursorHolder albumCursorHolder = new AlbumCursorHolder(cursor);
            HashMap hashMap2 = new HashMap();
            do {
                int i2 = albumCursorHolder.getInt(albumCursorHolder.indexAlbumCount, 0);
                AlbumType albumType = AlbumType.get(albumCursorHolder.getInt(albumCursorHolder.indexAlbumType, -1));
                if (isFolder(i2, albumType)) {
                    int i7 = albumCursorHolder.getInt(albumCursorHolder.indexFolderId, 0);
                    int i8 = albumCursorHolder.getInt(albumCursorHolder.indexBucketId, 0);
                    FolderItem folderItem = getFolderItem(albumCursorHolder, i8, albumType);
                    hashMap.put(Integer.valueOf(i8), folderItem);
                    if (i7 != 0) {
                        hashMap2.put(Integer.valueOf(i8), Integer.valueOf(i7));
                    } else {
                        arrayList.add(folderItem);
                    }
                }
            } while (cursor.moveToNext());
            addNestedFolderToParentFolder(hashMap, hashMap2);
        }
    }

    private void loadItemFromDirectories(Cursor cursor, ArrayList<MediaItem> arrayList, HashMap<Integer, MediaItem> hashMap, HashMap<Integer, FolderItem> hashMap2) {
        if (cursor.moveToFirst()) {
            MediaDataAlbum.DataRefresher dataRefresher = new MediaDataAlbum.DataRefresher(this.mBlackboard);
            AlbumCursorHolder albumCursorHolder = new AlbumCursorHolder(cursor);
            do {
                MediaItem mediaItemFromMap = getMediaItemFromMap(cursor, albumCursorHolder, hashMap);
                if (mediaItemFromMap != null) {
                    MediaItem coverItem = getCoverItem(albumCursorHolder, mediaItemFromMap, albumCursorHolder.getString(albumCursorHolder.indexCoverPath, (String) null));
                    if (coverItem instanceof CoverItem) {
                        updateAlbumInfo(coverItem, mediaItemFromMap);
                    }
                    CloudData.of(coverItem).albumSyncStatus = albumCursorHolder.getInt(albumCursorHolder.indexSyncStatus, 0);
                    dataRefresher.update(coverItem);
                    MediaItemBuilder.updateAlbumOrder(coverItem, albumCursorHolder.getLong(albumCursorHolder.indexAlbumOrder, 0));
                    int updateFolderIdByAlbumType = updateFolderIdByAlbumType(coverItem, albumCursorHolder.getInt(albumCursorHolder.indexFolderId, 0));
                    if (updateFolderIdByAlbumType != 0) {
                        FolderItem folderItem = hashMap2.get(Integer.valueOf(updateFolderIdByAlbumType));
                        if (folderItem != null) {
                            folderItem.addItem(coverItem);
                            setFolderTranslatedName(folderItem, mediaItemFromMap);
                        } else {
                            resetFolderInfo(coverItem);
                            arrayList.add(coverItem);
                        }
                    } else {
                        arrayList.add(coverItem);
                    }
                }
            } while (cursor.moveToNext());
            dataRefresher.clear();
        }
        loadExtraItem(arrayList, hashMap, hashMap2);
    }

    /* JADX WARNING: type inference failed for: r8v1, types: [java.lang.Object, java.util.function.ToLongFunction] */
    private void updateDefaultAlbumOrder(ArrayList<MediaItem> arrayList) {
        long j2;
        arrayList.sort(Comparator.comparingLong(new Object()));
        int i2 = 0;
        long j3 = 0;
        while (i2 < arrayList.size() && arrayList.get(i2).getAlbumOrder() != 1000000000000000007L) {
            j3 = arrayList.get(i2).getAlbumOrder();
            i2++;
        }
        if (i2 != arrayList.size()) {
            while (i2 < arrayList.size()) {
                if (j2 == 0) {
                    j2 = 0;
                } else {
                    j2 += 1000000000;
                }
                MediaItemBuilder.updateAlbumOrder(arrayList.get(i2), j2);
                i2++;
            }
        }
    }

    public /* bridge */ /* synthetic */ boolean acquireWriteLock(String str) {
        return super.acquireWriteLock(str);
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public ArrayList<MediaItem> createFakeList(Cursor cursor, ArrayList<MediaItem> arrayList, boolean z) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        try {
            fakeLoadingMap(cursor, hashMap);
            loadFolderMap(cursor, arrayList2, hashMap2);
            fakeLoading(cursor, arrayList2, hashMap, hashMap2, z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayList.addAll(arrayList2);
        return arrayList2;
    }

    public /* bridge */ /* synthetic */ long createFolderAt(int i2, MediaItem mediaItem, int i7, String str) {
        return super.createFolderAt(i2, mediaItem, i7, str);
    }

    public ArrayList<MediaItem> createFullList(Cursor[] cursorArr, ArrayList<MediaItem> arrayList) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        preloadMap(cursorArr[0], hashMap);
        loadVirtualAlbumData(cursorArr, hashMap);
        loadAutoAlbumData(cursorArr, hashMap);
        loadFolderMap(cursorArr[1], arrayList2, hashMap2);
        loadExtraFolderMap(hashMap, arrayList2, hashMap2);
        preload(cursorArr, arrayList2, hashMap, hashMap2);
        arrayList.addAll(arrayList2);
        updateDataRefresher(hashMap2);
        return arrayList2;
    }

    public /* bridge */ /* synthetic */ int createNewItem(String str, String str2, int i2, String str3, int i7) {
        return super.createNewItem(str, str2, i2, str3, i7);
    }

    public /* bridge */ /* synthetic */ ArrayList getAllData() {
        return super.getAllData();
    }

    public /* bridge */ /* synthetic */ ClusterTable getClusterTable(int i2) {
        return super.getClusterTable(i2);
    }

    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public /* bridge */ /* synthetic */ int getDataVersion() {
        return super.getDataVersion();
    }

    public /* bridge */ /* synthetic */ ArrayList getFileIds() {
        return super.getFileIds();
    }

    public /* bridge */ /* synthetic */ ArrayList getFullData() {
        return super.getFullData();
    }

    public /* bridge */ /* synthetic */ String getLocationKey() {
        return super.getLocationKey();
    }

    public /* bridge */ /* synthetic */ String getLocationReference() {
        return super.getLocationReference();
    }

    public /* bridge */ /* synthetic */ int getPosition(int i2) {
        return super.getPosition(i2);
    }

    public /* bridge */ /* synthetic */ int getRealCount() {
        return super.getRealCount();
    }

    public /* bridge */ /* synthetic */ int getRefCount() {
        return super.getRefCount();
    }

    public /* bridge */ /* synthetic */ void insertItemAt(int i2, MediaItem mediaItem) {
        super.insertItemAt(i2, mediaItem);
    }

    public /* bridge */ /* synthetic */ boolean isDataAvailable() {
        return super.isDataAvailable();
    }

    public boolean isIncludeNameMerged() {
        return false;
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ MediaData open(String str, boolean z) {
        return super.open(str, z);
    }

    public void preload(Cursor[] cursorArr, ArrayList<MediaItem> arrayList, HashMap<Integer, MediaItem> hashMap, HashMap<Integer, FolderItem> hashMap2) {
        if (cursorArr != null && !cursorArr[1].isClosed()) {
            loadItemFromDirectories(cursorArr[1], arrayList, hashMap, hashMap2);
            updateDefaultAlbumOrder(arrayList);
            ArrayList<MediaItem> checkMissingItemsInFilesTable = checkMissingItemsInFilesTable(hashMap, arrayList);
            if (!checkMissingItemsInFilesTable.isEmpty()) {
                if (this.mAlbumDataHelper.isOrderUpdated(arrayList)) {
                    checkMissingItemsInFilesTable.sort(Comparators.getComparator(this.mLocationKey, 41));
                    addAllNewAlbums(arrayList, checkMissingItemsInFilesTable);
                } else {
                    arrayList.addAll(checkMissingItemsInFilesTable);
                }
            }
            loadMergeNameItem(arrayList, hashMap2);
            arrayList.sort(this.mAlbumDataHelper.getComparator());
        }
    }

    public /* bridge */ /* synthetic */ void reInit(String str) {
        super.reInit(str);
    }

    public /* bridge */ /* synthetic */ MediaItem read(int i2) {
        return super.read(i2);
    }

    public /* bridge */ /* synthetic */ void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        super.readAsync(i2, onDataReadListener, booleanSupplier);
    }

    public /* bridge */ /* synthetic */ MediaItem readById(long j2) {
        return super.readById(j2);
    }

    public /* bridge */ /* synthetic */ MediaItem readCache(int i2) {
        return super.readCache(i2);
    }

    public /* bridge */ /* synthetic */ void register(MediaData.OnDataChangeListener onDataChangeListener) {
        super.register(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ void releaseWriteLock(String str) {
        super.releaseWriteLock(str);
    }

    public /* bridge */ /* synthetic */ void removeItemAt(int i2) {
        super.removeItemAt(i2);
    }

    public /* bridge */ /* synthetic */ void reopen(String str) {
        super.reopen(str);
    }

    public /* bridge */ /* synthetic */ void reorderData(int i2, int i7) {
        super.reorderData(i2, i7);
    }

    public /* bridge */ /* synthetic */ void swap(Cursor[] cursorArr) {
        super.swap(cursorArr);
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ void unregister(MediaData.OnDataChangeListener onDataChangeListener) {
        super.unregister(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ int updateCoverItem(int i2, String str, String str2) {
        return super.updateCoverItem(i2, str, str2);
    }

    public /* bridge */ /* synthetic */ void updateDataStampByPpp(MediaItem mediaItem) {
        super.updateDataStampByPpp(mediaItem);
    }

    public /* bridge */ /* synthetic */ int updateFolderAt(int i2, int i7, String str) {
        return super.updateFolderAt(i2, i7, str);
    }

    public void resetFolderInfo(MediaItem mediaItem) {
    }

    public void updateDataRefresher(HashMap<Integer, FolderItem> hashMap) {
    }

    public void loadAutoAlbumData(Cursor[] cursorArr, HashMap<Integer, MediaItem> hashMap) {
    }

    public void loadMergeNameItem(ArrayList<MediaItem> arrayList, HashMap<Integer, FolderItem> hashMap) {
    }

    public void loadVirtualAlbumData(Cursor[] cursorArr, HashMap<Integer, MediaItem> hashMap) {
    }

    public int updateFolderIdByAlbumType(MediaItem mediaItem, int i2) {
        return i2;
    }

    public void loadExtraFolderMap(HashMap<Integer, MediaItem> hashMap, ArrayList<MediaItem> arrayList, HashMap<Integer, FolderItem> hashMap2) {
    }

    public void loadExtraItem(ArrayList<MediaItem> arrayList, HashMap<Integer, MediaItem> hashMap, HashMap<Integer, FolderItem> hashMap2) {
    }
}
