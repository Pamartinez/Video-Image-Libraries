package com.samsung.android.gallery.module.dataset;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.concurrent.RwLock;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UnsupportedApiException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataAlbumChoice extends MediaDataRef {
    protected ArrayList<MediaItem> mDataList;
    protected int mFolderId;
    protected RwLock mLock = new RwLock();
    protected MediaData mMediaData;
    private final MediaData.OnDataChangeListener mMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            Log.d(MediaDataAlbumChoice.this.TAG, "onDataChanged");
            MediaDataAlbumChoice.this.updateData();
        }

        public void onDataRangeInserted(int i2, int i7) {
            StringCompat access$100 = MediaDataAlbumChoice.this.TAG;
            Log.d(access$100, "onDataRangeInserted : from=" + i2 + ", count=" + i7);
            MediaDataAlbumChoice.this.updateData();
        }
    };
    private boolean mUseTopAlbums;

    public MediaDataAlbumChoice(Blackboard blackboard, String str) {
        super(blackboard, str);
        boolean z;
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbums) || !PreferenceFeatures.EssentialAlbums.isEnabled() || !PickerUtil.isPickerMode(blackboard)) {
            z = false;
        } else {
            z = true;
        }
        this.mUseTopAlbums = z;
        this.mFolderId = ArgumentsUtil.getArgValue(str, "id", -1);
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
            this.mFolderId = -1;
        }
    }

    public void fetchDataForNested(ArrayList<MediaItem> arrayList) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            MediaItem findFolder = findFolder((List<MediaItem>) this.mMediaData.getFullData());
            if (findFolder != null) {
                arrayList.addAll(Arrays.asList(findFolder.getItemsInFolder()));
                return;
            }
            return;
        }
        MediaItem findFolder2 = findFolder(this.mMediaData);
        if (findFolder2 != null) {
            arrayList.addAll(Arrays.asList(findFolder2.getItemsInFolder()));
        }
    }

    public void fetchDataForRoot(ArrayList<MediaItem> arrayList) {
        ArrayList<MediaItem> arrayList2;
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            if (this.mUseTopAlbums) {
                arrayList2 = this.mMediaData.getAllData();
            } else {
                arrayList2 = this.mMediaData.getFullData();
            }
            if (arrayList2 != null) {
                arrayList.addAll(arrayList2);
            } else {
                Log.e(this.TAG, "fetchDataForRoot : dataList is null");
            }
        } else {
            int count = this.mMediaData.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                MediaItem read = this.mMediaData.read(i2);
                if (read == null) {
                    Log.e(this.TAG, "updateData null item ignored 0");
                } else {
                    arrayList.add(read);
                }
            }
        }
    }

    public final MediaItem findFolder(MediaData mediaData) {
        if (mediaData == null) {
            return null;
        }
        int count = mediaData.getCount();
        for (int i2 = 0; i2 < count; i2++) {
            MediaItem read = mediaData.read(i2);
            if (read != null && read.isFolder()) {
                if (read.getFolderID() != this.mFolderId) {
                    read = findFolder(read);
                }
                if (read != null) {
                    return read;
                }
            }
        }
        return null;
    }

    public MediaData open(String str, boolean z) {
        LaunchIntent launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
        String str2 = "location://albums";
        if (launchIntent != null && launchIntent.isAlbumMultiPick()) {
            str2 = PickerUtil.appendPickerArgs(this.mBlackboard, str2);
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "open {" + this.mLocationKey + ArcCommonLog.TAG_COMMA + (this.mRefCount.get() + 1) + "} redirected to " + str2);
        if (this.mRefCount.getAndIncrement() == 0) {
            MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(str2);
            this.mMediaData = open;
            open.register(this.mMediaDataChangeListener);
        }
        return this;
    }

    public void reInit(String str) {
        setLocationKey(str);
        this.mFolderId = ArgumentsUtil.getArgValue(str, "id", -1);
        this.mUseTopAlbums = !ArgumentsUtil.getArgValue(str, "ViewAll", true);
        updateData();
    }

    public MediaItem read(int i2) {
        ArrayList<MediaItem> arrayList = this.mDataList;
        if (arrayList == null || i2 < 0 || i2 >= arrayList.size()) {
            return null;
        }
        return this.mDataList.get(i2);
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        onDataReadListener.onDataReadCompleted(read(i2));
    }

    public MediaItem readById(long j2) {
        int i2;
        try {
            i2 = findPosition(j2);
        } catch (UnsupportedApiException unused) {
            i2 = -1;
        }
        if (i2 < 0) {
            return null;
        }
        return read(i2);
    }

    public MediaItem readCache(int i2) {
        return read(i2);
    }

    public void updateData() {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        if (this.mFolderId == -1) {
            fetchDataForRoot(arrayList);
        } else {
            fetchDataForNested(arrayList);
        }
        if (this.mLock.acquireWriteLock()) {
            this.mDataList = arrayList;
            this.mDataCount = arrayList.size();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "data refreshed : count = " + this.mDataCount);
            try {
                notifyChanged();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "updateData#notifyChanged failed", e.getMessage());
            }
            this.mLock.releaseWriteLock();
        }
    }

    public final MediaItem findFolder(List<MediaItem> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            MediaItem mediaItem = list.get(i2);
            if (mediaItem != null && mediaItem.isFolder()) {
                if (mediaItem.getFolderID() != this.mFolderId) {
                    mediaItem = findFolder(mediaItem);
                }
                if (mediaItem != null) {
                    return mediaItem;
                }
            }
        }
        return null;
    }

    public final MediaItem findFolder(MediaItem mediaItem) {
        for (MediaItem mediaItem2 : mediaItem.getItemsInFolder()) {
            if (mediaItem2 != null && mediaItem2.isFolder()) {
                if (mediaItem2.getFolderID() != this.mFolderId) {
                    mediaItem2 = findFolder(mediaItem2);
                }
                if (mediaItem2 != null) {
                    return mediaItem2;
                }
            }
        }
        return null;
    }

    public void requestData(String str) {
    }
}
