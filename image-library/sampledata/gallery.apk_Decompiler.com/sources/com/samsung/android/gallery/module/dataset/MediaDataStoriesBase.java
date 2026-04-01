package com.samsung.android.gallery.module.dataset;

import Ad.C0720a;
import J3.a;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.StaleDataException;
import android.graphics.Bitmap;
import android.text.TextUtils;
import b4.C0422b;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataStoriesBase extends MediaDataCursor {
    private Map<Integer, MediaItem> mAlbumMap = new ConcurrentHashMap();
    private Map<Integer, MediaItem> mItemMap = new ConcurrentHashMap();

    public MediaDataStoriesBase(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$readAsync$0(MediaData.OnDataReadListener onDataReadListener, int i2, ThreadPool.JobContext jobContext) {
        try {
            onDataReadListener.onDataReadCompleted(loadInternal(i2));
            return null;
        } catch (CursorIndexOutOfBoundsException | StaleDataException | ArrayIndexOutOfBoundsException | IllegalStateException e) {
            Log.e((CharSequence) this.TAG, "swap > failed. BG error(cursor maybe closed on another thread. ignore exception)", e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$swap$2(Map map, Map map2, Cursor cursor, CountDownLatch countDownLatch, ThreadPool.JobContext jobContext) {
        return loadData(map, map2, cursor, countDownLatch);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$3(Cursor[] cursorArr, Cursor[] cursorArr2, Map map, Map map2, int i2) {
        if (isDataCursorAvailable(cursorArr)) {
            synchronized (cursorArr[0]) {
                swapInternal(cursorArr2, map, map2, i2);
            }
        } else {
            swapInternal(cursorArr2, map, map2, i2);
        }
        this.mLock.releaseWriteLock();
        notifyChanged();
    }

    private Object loadData(Map<Integer, MediaItem> map, Map<Integer, MediaItem> map2, Cursor cursor, CountDownLatch countDownLatch) {
        if (cursor != null) {
            preprocessCursor(cursor, isFirstLoading());
            int count = cursor.getCount();
            boolean z = false;
            for (int i2 = 0; i2 < count; i2++) {
                MediaItem loadMediaItem = loadMediaItem(cursor, i2);
                if (loadMediaItem != null) {
                    updateItem(map, map2, i2, loadMediaItem);
                }
                if (isFirstLoading() && !z && i2 > 6) {
                    countDownLatch.countDown();
                    z = true;
                }
            }
            if (z) {
                notifyChanged();
            }
            cursor.close();
        }
        if (countDownLatch.getCount() <= 0) {
            return null;
        }
        countDownLatch.countDown();
        return null;
    }

    public /* bridge */ /* synthetic */ boolean acquireWriteLock(String str) {
        return super.acquireWriteLock(str);
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public boolean compare(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || mediaItem.getFileId() != mediaItem2.getFileId() || MediaItemStory.getStoryId(mediaItem) != MediaItemStory.getStoryId(mediaItem2) || MediaItemStory.hasStoryHighlightVideo(mediaItem) != MediaItemStory.hasStoryHighlightVideo(mediaItem2) || !TextUtils.equals(MediaItemStory.getStoryTimeDuration(mediaItem), MediaItemStory.getStoryTimeDuration(mediaItem2)) || !TextUtils.equals(mediaItem.getPath(), mediaItem2.getPath()) || !TextUtils.equals(mediaItem.getTitle(), mediaItem2.getTitle()) || !TextUtils.equals(MediaItemStory.getStoryCoverRectRatio(mediaItem), MediaItemStory.getStoryCoverRectRatio(mediaItem2))) {
            return false;
        }
        return true;
    }

    public int compareData(Map<Integer, MediaItem> map, ArrayList<Integer> arrayList) {
        Map<Integer, MediaItem> map2 = this.mItemMap;
        if (map2 == null || map2.size() != map.size()) {
            return -1;
        }
        int size = map.size();
        int i2 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            if (!compare(this.mItemMap.get(Integer.valueOf(i7)), map.get(Integer.valueOf(i7)))) {
                i2++;
                if (arrayList == null) {
                    return -1;
                }
                arrayList.add(Integer.valueOf(i7));
                if (i2 > 3) {
                    return -1;
                }
            }
        }
        Log.d(this.TAG, "compareData {" + i2 + "}");
        return i2;
    }

    public int findPosition(long j2) {
        for (Map.Entry next : this.mItemMap.entrySet()) {
            if (((long) ((MediaItem) next.getValue()).getAlbumID()) == j2) {
                return ((Integer) next.getKey()).intValue();
            }
        }
        return -1;
    }

    public ArrayList<MediaItem> getAllData() {
        Map<Integer, MediaItem> map = this.mItemMap;
        if (map != null) {
            return new ArrayList<>(map.values());
        }
        return new ArrayList<>();
    }

    public /* bridge */ /* synthetic */ ClusterTable getClusterTable(int i2) {
        return super.getClusterTable(i2);
    }

    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public ThumbKind getCoverThumbKind() {
        return ThumbKind.STORY_COVER;
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

    public int getMaxPreloadThumbCount() {
        return 3;
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

    public /* bridge */ /* synthetic */ boolean isDataAvailable() {
        return super.isDataAvailable();
    }

    public boolean isDataCursorAvailable(Cursor[] cursorArr) {
        if (!super.isDataCursorAvailable(cursorArr) || cursorArr[0].isClosed()) {
            return false;
        }
        return true;
    }

    public boolean isFilteredEvent(EventMessage eventMessage) {
        return false;
    }

    public boolean isFirstLoading() {
        if (this.mCursors == null) {
            return true;
        }
        return false;
    }

    public boolean isFullyLoaded() {
        if (this.mItemMap.size() == this.mDataCount) {
            return true;
        }
        return false;
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        if (eventMessage.what == 102) {
            return true;
        }
        return false;
    }

    public MediaItem loadInternal(int i2) {
        MediaItem mediaItem;
        Cursor[] cursorArr = this.mCursors;
        if (cursorArr == null) {
            mediaItem = null;
        } else {
            mediaItem = loadMediaItem(cursorArr[0], i2);
        }
        if (mediaItem != null) {
            updateItem(this.mItemMap, this.mAlbumMap, i2, mediaItem);
        }
        return mediaItem;
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mItemMap.clear();
        this.mAlbumMap.clear();
    }

    public /* bridge */ /* synthetic */ MediaData open(String str, boolean z) {
        return super.open(str, z);
    }

    public void preloadThumbnail(Map<Integer, MediaItem> map) {
        this.mBlackboard.publishIfEmpty("lifecycle://on_thumbnail_load_start", Long.valueOf(System.currentTimeMillis()));
        C0612q qVar = new C0612q(5);
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        int min = Math.min(getMaxPreloadThumbCount(), map.size());
        publishPreloadCount(min);
        Log.d(this.TAG, "preloadThumbnail", Integer.valueOf(min), getCoverThumbKind());
        for (int i2 = 0; i2 < min; i2++) {
            MediaItem mediaItem = map.get(Integer.valueOf(i2));
            if (mediaItem != null) {
                instance.loadThumbnail(mediaItem, getCoverThumbKind(), qVar);
            }
        }
    }

    public /* bridge */ /* synthetic */ void reInit(String str) {
        super.reInit(str);
    }

    public MediaItem read(int i2) {
        MediaItem mediaItem = this.mItemMap.get(Integer.valueOf(i2));
        if (mediaItem != null) {
            return mediaItem;
        }
        return loadInternal(i2);
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        MediaItem readCache = readCache(i2);
        if (readCache != null) {
            onDataReadListener.onDataReadCompleted(readCache);
        } else {
            getThreadPool().submit(new J(i2, 1, onDataReadListener, this));
        }
    }

    public MediaItem readById(long j2) {
        return this.mAlbumMap.get(Integer.valueOf((int) j2));
    }

    public MediaItem readCache(int i2) {
        return this.mItemMap.get(Integer.valueOf(i2));
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

    /* JADX WARNING: type inference failed for: r3v0, types: [java.lang.Cloneable, android.database.Cursor[]] */
    public void swap(Cursor[] cursorArr) {
        if (this.mLock.acquireWriteLock()) {
            ? r32 = this.mCursors;
            Cursor cursor = cursorArr[0];
            int cursorCount = getCursorCount(cursor);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            HashMap hashMap = new HashMap();
            ConcurrentHashMap concurrentHashMap2 = concurrentHashMap;
            getThreadPool().submit(new a(this, concurrentHashMap2, hashMap, cursor, countDownLatch, 2));
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                InterruptedException interruptedException = e;
                StringCompat stringCompat = this.TAG;
                Log.i(stringCompat, "swap > interrupted (" + this.mLocationKey + ") e=" + interruptedException.getMessage());
            }
            if (isFirstLoading()) {
                preloadThumbnail(concurrentHashMap2);
            } else {
                ArrayList arrayList = new ArrayList();
                int compareData = compareData(concurrentHashMap2, arrayList);
                if (compareData == 0) {
                    StringCompat stringCompat2 = this.TAG;
                    Log.d(stringCompat2, "swap > ignored by comparison (" + this.mLocationKey + ")");
                    swapInternal(cursorArr, concurrentHashMap2, hashMap, cursorCount);
                    this.mLock.releaseWriteLock();
                    return;
                } else if (compareData == 1 && "location://story/albums".equals(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard))) {
                    swapInternal(cursorArr, concurrentHashMap2, hashMap, cursorCount);
                    this.mLock.releaseWriteLock();
                    notifyDataRangeChanged(((Integer) arrayList.get(0)).intValue(), 1);
                    return;
                }
            }
            HashMap hashMap2 = hashMap;
            int i2 = cursorCount;
            runOnUiThread(new C0422b(this, r32, cursorArr, concurrentHashMap2, hashMap2, i2, 1));
            return;
        }
        Log.e(this.TAG, "fail to get lock");
    }

    public void swapInternal(Cursor[] cursorArr, Map<Integer, MediaItem> map, Map<Integer, MediaItem> map2, int i2) {
        this.mCursors = cursorArr;
        this.mItemMap = map;
        this.mAlbumMap = map2;
        this.mDataCount = i2;
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

    public void updateItem(Map<Integer, MediaItem> map, Map<Integer, MediaItem> map2, int i2, MediaItem mediaItem) {
        map.put(Integer.valueOf(i2), mediaItem);
        map2.put(Integer.valueOf(mediaItem.getAlbumID()), mediaItem);
    }

    public ArrayList<Long> getFileIds() {
        return (ArrayList) this.mItemMap.values().stream().map(new K(17)).collect(Collectors.toCollection(new C0720a(1)));
    }

    public void publishPreloadCount(int i2) {
    }

    public void preprocessCursor(Cursor cursor, boolean z) {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$preloadThumbnail$4(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
    }
}
