package com.samsung.android.gallery.module.dataset;

import W4.b;
import android.text.TextUtils;
import com.samsung.android.gallery.module.concurrent.RwLock;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataStoriesCategory extends MediaDataRef {
    private final List<MediaItem> mDataArray = new Vector();
    private final RwLock mLock = new RwLock();

    public MediaDataStoriesCategory(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private boolean compare(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem != null && mediaItem2 != null && mediaItem.getFileId() == mediaItem2.getFileId() && MediaItemStory.getStoryId(mediaItem) == MediaItemStory.getStoryId(mediaItem2) && TextUtils.equals(mediaItem.getPath(), mediaItem2.getPath()) && TextUtils.equals(mediaItem.getTitle(), mediaItem2.getTitle()) && TextUtils.equals(MediaItemStory.getStoryCoverRectRatio(mediaItem), MediaItemStory.getStoryCoverRectRatio(mediaItem2)) && MediaItemStory.getStoryFavorite(mediaItem) == MediaItemStory.getStoryFavorite(mediaItem2) && MediaItemStory.getStoryStartTime(mediaItem) == MediaItemStory.getStoryStartTime(mediaItem2) && MediaItemStory.getStoryEndTime(mediaItem) == MediaItemStory.getStoryEndTime(mediaItem2) && mediaItem.getWidth() == mediaItem2.getWidth() && mediaItem.getHeight() == mediaItem2.getHeight() && mediaItem.getFileSize() != mediaItem2.getFileSize()) {
            return true;
        }
        return false;
    }

    private boolean compareData(List<MediaItem> list) {
        if (this.mDataArray.size() != list.size()) {
            return false;
        }
        for (int i2 = 0; i2 < this.mDataArray.size(); i2++) {
            if (!compare(this.mDataArray.get(i2), list.get(i2))) {
                Log.d(this.TAG, "compareData[change]", Integer.valueOf(this.mDataArray.size()), this.mLocationKey);
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$readById$1(long j2, MediaItem mediaItem) {
        int i2;
        if (mediaItem != null) {
            i2 = mediaItem.getAlbumID();
        } else {
            i2 = -1;
        }
        if (j2 == ((long) i2)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$0(List list, int i2) {
        swapInternal(list, i2);
        this.mLock.releaseWriteLock();
        notifyChanged();
    }

    private void swapInternal(List<MediaItem> list, int i2) {
        int i7 = this.mDataCount;
        this.mDataArray.clear();
        this.mDataArray.addAll(list);
        this.mDataCount = i2;
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("swapInternal@");
        sb2.append(hashCode());
        sb2.append("[");
        String p6 = C0212a.p(sb2, this.mLocationKey, ")");
        Log.d(stringCompat, p6, i7 + "->" + this.mDataCount, Boolean.valueOf(isFullyLoaded()), Integer.valueOf(this.mDataArray.size()));
    }

    public void close() {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "close {" + this.mLocationKey + "}");
    }

    public int findPosition(long j2) {
        ArrayList<MediaItem> allData = getAllData();
        for (int i2 = 0; i2 < allData.size(); i2++) {
            if (((long) allData.get(i2).getAlbumID()) == j2) {
                return i2;
            }
        }
        return -1;
    }

    public ArrayList<MediaItem> getAllData() {
        return new ArrayList<>(this.mDataArray);
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

    public /* bridge */ /* synthetic */ boolean isDataAvailable() {
        return super.isDataAvailable();
    }

    public boolean isFullyLoaded() {
        if (this.mDataArray.size() == this.mDataCount) {
            return true;
        }
        return false;
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mDataArray.clear();
    }

    public MediaData open(String str, boolean z) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "open {" + this.mLocationKey + "}");
        return this;
    }

    public /* bridge */ /* synthetic */ void reInit(String str) {
        super.reInit(str);
    }

    public MediaItem read(int i2) {
        if (i2 < 0 || i2 >= this.mDataArray.size()) {
            return null;
        }
        return this.mDataArray.get(i2);
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        onDataReadListener.onDataReadCompleted(read(i2));
    }

    public MediaItem readById(long j2) {
        return getAllData().stream().filter(new b(j2, 3)).findFirst().orElse((Object) null);
    }

    public MediaItem readCache(int i2) {
        return read(i2);
    }

    public /* bridge */ /* synthetic */ void register(MediaData.OnDataChangeListener onDataChangeListener) {
        super.register(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ void reopen(String str) {
        super.reopen(str);
    }

    public void swap(List<MediaItem> list, int i2) {
        ThreadUtil.assertBgThread("MediaDataStoriesCategory::swap should run on work thread");
        if (!this.mLock.acquireWriteLock()) {
            return;
        }
        if (!compareData(list)) {
            runOnUiThread(new C0613s(this, list, i2, 1));
        } else {
            this.mLock.releaseWriteLock();
        }
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ void unregister(MediaData.OnDataChangeListener onDataChangeListener) {
        super.unregister(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ void updateDataStampByPpp(MediaItem mediaItem) {
        super.updateDataStampByPpp(mediaItem);
    }

    public void requestData(String str) {
    }
}
