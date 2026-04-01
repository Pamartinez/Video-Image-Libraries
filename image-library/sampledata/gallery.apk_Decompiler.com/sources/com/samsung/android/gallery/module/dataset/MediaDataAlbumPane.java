package com.samsung.android.gallery.module.dataset;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.concurrent.RwLock;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataAlbumPane extends MediaDataRef {
    protected ArrayList<MediaItem> mDataList;
    protected HashMap<Integer, Integer> mDataMap;
    private final RwLock mLock = new RwLock();
    protected MediaData mMediaData;
    protected MediaData.OnDataChangeListener mMediaDataChangeListener;
    private final String mRedirectKey;

    public MediaDataAlbumPane(Blackboard blackboard, String str) {
        super(blackboard, str);
        String str2;
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            str2 = "location://albums/all";
        } else {
            str2 = "location://albums";
        }
        this.mRedirectKey = str2;
        this.mMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
            public void onDataChanged() {
                Log.d(MediaDataAlbumPane.this.TAG, "onDataChanged");
                MediaDataAlbumPane.this.updateData();
            }

            public void onDataRangeInserted(int i2, int i7) {
                StringCompat access$100 = MediaDataAlbumPane.this.TAG;
                Log.d(access$100, "onDataRangeInserted : from=" + i2 + ", count=" + i7);
                MediaDataAlbumPane.this.updateData();
            }
        };
    }

    private MediaItem getAlbumsItem(MediaItem mediaItem, int i2) {
        if (mediaItem.isFolder()) {
            for (MediaItem mediaItem2 : mediaItem.getAlbumsInFolder()) {
                if (mediaItem2.getAlbumID() == i2) {
                    return mediaItem2;
                }
            }
            return null;
        } else if (mediaItem.getAlbumID() == i2) {
            return mediaItem;
        } else {
            return null;
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
        }
    }

    public int findPosition(long j2) {
        Integer num;
        HashMap<Integer, Integer> hashMap = this.mDataMap;
        if (hashMap == null || (num = hashMap.get(Integer.valueOf((int) j2))) == null) {
            return -1;
        }
        return num.intValue();
    }

    public ArrayList<MediaItem> getAllData() {
        return this.mDataList;
    }

    public MediaData open(String str, boolean z) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "open {" + this.mLocationKey + ArcCommonLog.TAG_COMMA + (this.mRefCount.get() + 1) + "} redirected to " + this.mRedirectKey);
        if (this.mRefCount.getAndIncrement() == 0) {
            MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(this.mRedirectKey);
            this.mMediaData = open;
            open.register(this.mMediaDataChangeListener);
        }
        return this;
    }

    public MediaItem read(int i2) {
        try {
            ArrayList<MediaItem> arrayList = this.mDataList;
            if (arrayList == null || i2 < 0 || i2 >= arrayList.size()) {
                return null;
            }
            return this.mDataList.get(i2);
        } catch (IndexOutOfBoundsException unused) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "read failed. position#" + i2);
            return null;
        }
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        onDataReadListener.onDataReadCompleted(read(i2));
    }

    public MediaItem readById(long j2) {
        int findPosition = findPosition(j2);
        if (findPosition < 0) {
            return null;
        }
        return read(findPosition);
    }

    public MediaItem readCache(int i2) {
        return read(i2);
    }

    public int updateCoverItem(int i2, String str, String str2) {
        MediaItem albumsItem;
        int findPosition;
        MediaItem read = this.mMediaData.read(this.mMediaData.updateCoverItem(i2, str, str2));
        if (read == null || (albumsItem = getAlbumsItem(read, i2)) == null || (findPosition = findPosition((long) i2)) < 0) {
            return -1;
        }
        MediaItem read2 = read(findPosition);
        if (read2 != null) {
            albumsItem.setFolderPosition(read2.getFolderPosition());
        }
        if (this.mLock.acquireWriteLock()) {
            this.mDataList.remove(findPosition);
            this.mDataList.add(findPosition, albumsItem);
            this.mLock.releaseWriteLock();
        }
        return findPosition;
    }

    public final void updateData() {
        MediaItem.FolderPosition folderPosition;
        MediaItem.FolderPosition folderPosition2;
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int count = this.mMediaData.getCount();
        int i2 = 0;
        for (int i7 = 0; i7 < count; i7++) {
            MediaItem read = this.mMediaData.read(i7);
            if (read == null) {
                Log.e(this.TAG, "updateData null item ignored " + i2);
            } else if (read.isFolder()) {
                Iterator<MediaItem> it = read.getChildAlbums().iterator();
                MediaItem mediaItem = null;
                int i8 = 0;
                while (it.hasNext()) {
                    mediaItem = it.next();
                    arrayList.add(mediaItem);
                    int i10 = i2 + 1;
                    hashMap.put(Integer.valueOf(mediaItem.getAlbumID()), Integer.valueOf(i2));
                    int i11 = i8 + 1;
                    if (i8 == 0) {
                        folderPosition2 = MediaItem.FolderPosition.FIRST;
                    } else {
                        folderPosition2 = MediaItem.FolderPosition.MIDDLE;
                    }
                    mediaItem.setFolderPosition(folderPosition2);
                    i8 = i11;
                    i2 = i10;
                }
                if (mediaItem != null) {
                    if (i8 < 2) {
                        folderPosition = MediaItem.FolderPosition.ALL;
                    } else {
                        folderPosition = MediaItem.FolderPosition.LAST;
                    }
                    mediaItem.setFolderPosition(folderPosition);
                }
            } else {
                arrayList.add(read);
                hashMap.put(Integer.valueOf(read.getAlbumID()), Integer.valueOf(i2));
                i2++;
            }
        }
        if (this.mLock.acquireWriteLock()) {
            this.mDataList = arrayList;
            this.mDataMap = hashMap;
            this.mDataCount = arrayList.size();
            try {
                notifyChanged();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "updateData#notifyChanged failed", e.getMessage());
            }
            this.mLock.releaseWriteLock();
        }
    }

    public void requestData(String str) {
    }
}
