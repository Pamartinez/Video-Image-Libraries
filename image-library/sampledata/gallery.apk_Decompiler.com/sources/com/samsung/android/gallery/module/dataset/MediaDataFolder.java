package com.samsung.android.gallery.module.dataset;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.concurrent.RwLock;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataFolder extends MediaDataRef {
    protected AlbumDataHelper mAlbumDataHelper;
    private ArrayList<MediaItem> mDataList;
    private int mFolderId;
    private final RwLock mLock = new RwLock();
    protected MediaData mMediaData;
    private final MediaData.OnDataChangeListener mMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            Log.d(MediaDataFolder.this.TAG, "onDataChanged");
            MediaDataFolder.this.updateData();
        }

        public void onDataRangeInserted(int i2, int i7) {
            StringCompat access$100 = MediaDataFolder.this.TAG;
            Log.d(access$100, "onDataRangeInserted : from=" + i2 + ", count=" + i7);
            MediaDataFolder.this.updateData();
        }
    };

    public MediaDataFolder(Blackboard blackboard, String str) {
        super(blackboard, str);
        this.mAlbumDataHelper = new AlbumDataHelper(str);
        this.mFolderId = ArgumentsUtil.getArgValue(str, "id", 0);
    }

    private MediaItem findFolder(MediaItem mediaItem) {
        MediaItem mediaItem2 = null;
        for (MediaItem mediaItem3 : mediaItem.getItemsInFolder()) {
            if (mediaItem3.isFolder()) {
                if (mediaItem3.getFolderID() != this.mFolderId) {
                    mediaItem3 = findFolder(mediaItem3);
                }
                if (mediaItem3 != null) {
                    return mediaItem3;
                }
                mediaItem2 = mediaItem3;
            }
        }
        return mediaItem2;
    }

    private MediaItem readItem(ArrayList<MediaItem> arrayList, int i2) {
        if (arrayList == null || i2 < 0 || i2 >= arrayList.size()) {
            return null;
        }
        return arrayList.get(i2);
    }

    /* access modifiers changed from: private */
    public void updateData() {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        ArrayList<MediaItem> fullData = this.mMediaData.getFullData();
        int size = fullData.size();
        MediaItem mediaItem = null;
        for (int i2 = 0; i2 < size; i2++) {
            MediaItem readItem = readItem(fullData, i2);
            if (readItem == null) {
                StringCompat stringCompat = this.TAG;
                Log.e(stringCompat, "updateData null item ignored " + i2);
            } else if (!readItem.isFolder()) {
                continue;
            } else {
                if (readItem.getFolderID() == this.mFolderId) {
                    mediaItem = readItem;
                } else {
                    mediaItem = findFolder(readItem);
                }
                if (mediaItem != null) {
                    break;
                }
            }
        }
        if (mediaItem != null) {
            this.mBlackboard.publish("data://current_folder", mediaItem);
            arrayList.addAll(Arrays.asList(mediaItem.getItemsInFolder()));
        }
        if (this.mLock.acquireWriteLock()) {
            this.mDataList = arrayList;
            this.mDataCount = arrayList.size();
            try {
                notifyChanged();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "updateData#notifyChanged failed", e.getMessage());
            }
            this.mLock.releaseWriteLock();
        }
    }

    public void close() {
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("close {");
        sb2.append(this.mLocationKey);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.mRefCount.get() - 1);
        sb2.append("} redirected to location://albums");
        Log.d(stringCompat, sb2.toString());
        if (this.mRefCount.decrementAndGet() == 0) {
            this.mMediaData.unregister(this.mMediaDataChangeListener);
            this.mMediaData.close();
            this.mMediaData = null;
            ArrayList<MediaItem> arrayList = this.mDataList;
            if (arrayList != null) {
                arrayList.clear();
                this.mDataList = null;
            }
        }
        this.mBlackboard.erase("data://current_folder");
    }

    public long createFolderAt(int i2, MediaItem mediaItem, int i7, String str) {
        return this.mAlbumDataHelper.createFolderAt(this.mDataList, i2, mediaItem, i7, str);
    }

    public int createNewItem(String str, String str2, int i2, String str3, int i7) {
        if (this.mFolderId != i2) {
            return -1;
        }
        int createNewItem = this.mAlbumDataHelper.createNewItem(this.mDataList, str, str2, true, i7);
        this.mDataCount++;
        return createNewItem;
    }

    public ArrayList<MediaItem> getAllData() {
        return this.mDataList;
    }

    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public /* bridge */ /* synthetic */ int getDataVersion() {
        return super.getDataVersion();
    }

    public /* bridge */ /* synthetic */ String getLocationKey() {
        return super.getLocationKey();
    }

    public /* bridge */ /* synthetic */ String getLocationReference() {
        return super.getLocationReference();
    }

    public /* bridge */ /* synthetic */ int getRealCount() {
        return super.getRealCount();
    }

    public /* bridge */ /* synthetic */ int getRefCount() {
        return super.getRefCount();
    }

    public void insertItemAt(int i2, MediaItem mediaItem) {
        this.mAlbumDataHelper.insertItemAt(this.mDataList, i2, mediaItem, false);
        this.mDataCount = this.mDataList.size();
    }

    public boolean isDataAvailable() {
        if (this.mDataList == null || this.mDataCount < 0) {
            return false;
        }
        return true;
    }

    public boolean isValidPosition(int i2) {
        ArrayList<MediaItem> arrayList = this.mDataList;
        if (arrayList == null || i2 < 0 || i2 >= arrayList.size()) {
            return false;
        }
        return true;
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    public MediaData open(String str, boolean z) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "open {" + this.mLocationKey + ArcCommonLog.TAG_COMMA + (this.mRefCount.get() + 1) + "} redirected to location://albums");
        this.mFolderId = ArgumentsUtil.getArgValue(str, "id", 0);
        if (this.mRefCount.getAndIncrement() == 0) {
            MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open("location://albums");
            this.mMediaData = open;
            open.register(this.mMediaDataChangeListener);
        }
        return this;
    }

    public void reInit(String str) {
        setLocationKey(str);
        this.mFolderId = ArgumentsUtil.getArgValue(str, "id", 0);
        updateData();
    }

    public MediaItem read(int i2) {
        if (isValidPosition(i2)) {
            return this.mDataList.get(i2);
        }
        return null;
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        onDataReadListener.onDataReadCompleted(read(i2));
    }

    public MediaItem readCache(int i2) {
        if (isValidPosition(i2)) {
            return this.mDataList.get(i2);
        }
        return null;
    }

    public /* bridge */ /* synthetic */ void register(MediaData.OnDataChangeListener onDataChangeListener) {
        super.register(onDataChangeListener);
    }

    public void removeItemAt(int i2) {
        this.mAlbumDataHelper.removeItemAt(this.mDataList, i2);
        this.mDataCount = this.mDataList.size();
    }

    public void reopen(String str) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "reopen {" + str + "} redirect to {location://albums}");
        this.mMediaData.reopen("location://albums");
    }

    public void reorderData(int i2, int i7) {
        this.mAlbumDataHelper.reorderData(this.mDataList, i2, i7, false);
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ void unregister(MediaData.OnDataChangeListener onDataChangeListener) {
        super.unregister(onDataChangeListener);
    }

    public int updateCoverItem(int i2, String str, String str2) {
        return this.mAlbumDataHelper.updateCoverItem(this.mBlackboard, this.mDataList, i2, str, str2);
    }

    public /* bridge */ /* synthetic */ void updateDataStampByPpp(MediaItem mediaItem) {
        super.updateDataStampByPpp(mediaItem);
    }

    public int updateFolderAt(int i2, int i7, String str) {
        return this.mAlbumDataHelper.updateFolderAt(this.mDataList, i2, i7, str);
    }

    public ArrayList<Long> getFileIds() {
        if (this.mLock.acquireReadLock("MDA.getFileIds")) {
            try {
                ArrayList<Long> arrayList = new ArrayList<>();
                Iterator<MediaItem> it = this.mDataList.iterator();
                while (it.hasNext()) {
                    arrayList.add(Long.valueOf(it.next().getFileId()));
                }
                return arrayList;
            } finally {
                this.mLock.releaseReadLock("MDA.getFileIds");
            }
        } else {
            Log.e(this.TAG, "fail to get lock - file ids");
            return new ArrayList<>();
        }
    }

    public void requestData(String str) {
    }
}
