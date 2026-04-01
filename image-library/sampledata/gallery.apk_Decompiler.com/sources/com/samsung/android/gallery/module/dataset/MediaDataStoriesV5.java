package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.database.StaleDataException;
import com.samsung.android.gallery.database.dbtype.StoryLevel2Cat;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.EffectItemBuilder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataStoriesV5 extends MediaDataStoriesBase {
    private final MediaDataStoriesCategory mChildMediaData;
    private final AtomicBoolean mViewedIdsLoaded = new AtomicBoolean();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DataInfo {
        Map<Integer, MediaItem> mAlbumMappingTable = new HashMap();
        Vector<MediaItem> mChildData = new Vector<>();
        Cursor mCursor;
        Map<Integer, MediaItem> mData = new ConcurrentHashMap();

        public DataInfo(Cursor cursor) {
            this.mCursor = cursor;
        }
    }

    public MediaDataStoriesV5(Blackboard blackboard, String str) {
        super(blackboard, str);
        this.mChildMediaData = new MediaDataStoriesCategory(blackboard, "location://stories/category/transitory");
    }

    private void addItem(DataInfo dataInfo, int i2, MediaItem mediaItem) {
        if (PreferenceFeatures.OneUi7x.SUPPORT_STORY_LIVE_EFFECT_TYPE && MediaItemStory.hasLiveEffectData(mediaItem)) {
            mediaItem = EffectItemBuilder.buildLiveEffectItem(mediaItem);
        }
        if (StoryType.isTransitoryType(MediaItemStory.getStoryType(mediaItem))) {
            dataInfo.mChildData.add(mediaItem);
            return;
        }
        dataInfo.mData.put(Integer.valueOf(i2), mediaItem);
        dataInfo.mAlbumMappingTable.put(Integer.valueOf(mediaItem.getAlbumID()), mediaItem);
    }

    private List<ArrayList<Integer>> getClassifiedCursorPositions(Cursor cursor) {
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (cursor.moveToFirst()) {
                do {
                    if (StoryType.isTransitoryType(cursor.getInt(cursor.getColumnIndex("__storyType")))) {
                        arrayList.add(Integer.valueOf(cursor.getPosition()));
                    } else {
                        arrayList2.add(Integer.valueOf(cursor.getPosition()));
                    }
                } while (cursor.moveToNext());
            }
            return Arrays.asList(new ArrayList[]{arrayList, arrayList2});
        } catch (StaleDataException e) {
            Log.e((CharSequence) this.TAG, "getClassifiedCursorPositions failed", e.getMessage());
            return Arrays.asList(new ArrayList[]{new ArrayList(), new ArrayList()});
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadData$0(DataInfo dataInfo, Cursor cursor, List list, Integer num) {
        updateItem(dataInfo, num.intValue(), loadMediaItem(cursor, num.intValue()), list);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$swap$1(DataInfo dataInfo, AtomicInteger atomicInteger, CountDownLatch countDownLatch, ThreadPool.JobContext jobContext) {
        return loadData(dataInfo, atomicInteger, countDownLatch);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$2(Cursor[] cursorArr, Cursor[] cursorArr2, DataInfo dataInfo, int i2) {
        if (isDataCursorAvailable(cursorArr)) {
            synchronized (cursorArr[0]) {
                swapInternal(cursorArr2, dataInfo.mData, dataInfo.mAlbumMappingTable, i2);
            }
        } else {
            swapInternal(cursorArr2, dataInfo.mData, dataInfo.mAlbumMappingTable, i2);
        }
        this.mLock.releaseWriteLock();
        notifyChanged();
    }

    private Object loadData(DataInfo dataInfo, AtomicInteger atomicInteger, CountDownLatch countDownLatch) {
        Cursor cursor = dataInfo.mCursor;
        if (cursor != null) {
            List<String> loadViewedIds = loadViewedIds();
            List<ArrayList<Integer>> classifiedCursorPositions = getClassifiedCursorPositions(cursor);
            ArrayList arrayList = classifiedCursorPositions.get(1);
            atomicInteger.set(arrayList.size());
            classifiedCursorPositions.get(0).forEach(new r0(this, dataInfo, cursor, loadViewedIds));
            boolean z = false;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                MediaItem loadMediaItem = loadMediaItem(cursor, ((Integer) arrayList.get(i2)).intValue());
                if (loadMediaItem != null) {
                    updateItem(dataInfo, i2, loadMediaItem, loadViewedIds);
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

    private List<String> loadViewedIds() {
        if (!PreferenceFeatures.PERFORMANCE.STORIES_CURSOR_CACHE || !PreferenceFeatures.OneUi5x.STORY_ONE_UI_50 || this.mViewedIdsLoaded.getAndSet(true)) {
            return new ArrayList();
        }
        List<String> list = (List) Arrays.stream(PreferenceCache.StoryViewedIds.getString().split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).collect(Collectors.toList());
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "loadViewedIds" + Logger.vt((long) list.size()));
        return list;
    }

    private void swapChild(Vector<MediaItem> vector) {
        this.mChildMediaData.swap(vector, vector.size());
    }

    private void updateItem(DataInfo dataInfo, int i2, MediaItem mediaItem, List<String> list) {
        long j2;
        long j3;
        int storySaType = MediaItemStory.getStorySaType(mediaItem);
        if (StoryLevel2Cat.isDailyWithYear(storySaType)) {
            if (mediaItem.getDateLocal() > 0) {
                j3 = mediaItem.getDateLocal();
            } else {
                j3 = mediaItem.getDateTaken();
            }
            MediaItemStory.setStoryTimeDuration(mediaItem, TimeUtil.getYearString(j3));
        } else if (StoryLevel2Cat.isDailyWithDate(storySaType)) {
            if (mediaItem.getDateLocal() > 0) {
                j2 = mediaItem.getDateLocal();
            } else {
                j2 = mediaItem.getDateTaken();
            }
            MediaItemStory.setStoryTimeDuration(mediaItem, TimeUtil.toLocalDate(j2, "YYMD"));
        }
        if (PreferenceFeatures.PERFORMANCE.STORIES_CURSOR_CACHE && MediaItemStory.getStoryNotifyStatus(mediaItem) != 1 && list.contains(String.valueOf(MediaItemStory.getStoryId(mediaItem)))) {
            MediaItemStory.setStoryNotifyStatus(mediaItem, 1);
        }
        addItem(dataInfo, i2, mediaItem);
    }

    public boolean compare(MediaItem mediaItem, MediaItem mediaItem2) {
        boolean z;
        if (!super.compare(mediaItem, mediaItem2) || mediaItem.getCount() != mediaItem2.getCount()) {
            z = false;
        } else {
            z = true;
        }
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            if (!z || mediaItem.getDateModified() != mediaItem2.getDateModified()) {
                z = false;
            } else {
                z = true;
            }
        }
        if (PreferenceFeatures.OneUi40.SUPPORT_STORIES_PIN) {
            if (!z || MediaItemStory.getStoryFavorite(mediaItem) != MediaItemStory.getStoryFavorite(mediaItem2)) {
                z = false;
            } else {
                z = true;
            }
        }
        if (!SdkConfig.atLeast(SdkConfig.GED.T)) {
            return z;
        }
        if (!z || !Objects.equals(MediaItemStory.getTotalSmartCropRatio(mediaItem), MediaItemStory.getTotalSmartCropRatio(mediaItem2)) || !Objects.equals(MediaItemStory.getTotalSmartCropDeviceRatio(mediaItem), MediaItemStory.getTotalSmartCropDeviceRatio(mediaItem2))) {
            return false;
        }
        return true;
    }

    public MediaData getChildMediaData(String str) {
        return this.mChildMediaData;
    }

    public int getMaxPreloadThumbCount() {
        GridHelper instance = GridHelper.getInstance(getLocationKey());
        int columnSize = instance.getColumnSize(Blackboard.getContext(), instance.getGridDepth());
        if (columnSize == 2) {
            return 6;
        }
        if (columnSize == 1) {
            return 2;
        }
        return super.getMaxPreloadThumbCount();
    }

    public void onCreate() {
        super.onCreate();
        this.mChildMediaData.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mChildMediaData.onDestroy();
    }

    public void swap(Cursor[] cursorArr) {
        if (this.mLock.acquireWriteLock()) {
            Cursor[] cursorArr2 = this.mCursors;
            Cursor cursor = cursorArr[0];
            CountDownLatch countDownLatch = new CountDownLatch(1);
            AtomicInteger atomicInteger = new AtomicInteger(getCursorCount(cursor));
            DataInfo dataInfo = new DataInfo(cursor);
            getThreadPool().submit(new q0(0, atomicInteger, this, dataInfo, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                InterruptedException interruptedException = e;
                StringCompat stringCompat = this.TAG;
                Log.i(stringCompat, "swap > interrupted (" + this.mLocationKey + ") e=" + interruptedException.getMessage());
            }
            swapChild(dataInfo.mChildData);
            int i2 = atomicInteger.get();
            if (isFirstLoading()) {
                preloadThumbnail(dataInfo.mData);
            } else {
                ArrayList arrayList = new ArrayList();
                int compareData = compareData(dataInfo.mData, arrayList);
                if (compareData == 0) {
                    StringCompat stringCompat2 = this.TAG;
                    Log.d(stringCompat2, "swap > ignored by comparison (" + this.mLocationKey + ")");
                    swapInternal(cursorArr, dataInfo.mData, dataInfo.mAlbumMappingTable, i2);
                    this.mLock.releaseWriteLock();
                    return;
                } else if (compareData > 0 && "location://story/albums".equals(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard))) {
                    swapInternal(cursorArr, dataInfo.mData, dataInfo.mAlbumMappingTable, i2);
                    this.mLock.releaseWriteLock();
                    notifyDataRangeChanged(((Integer) arrayList.get(0)).intValue(), (((Integer) arrayList.get(arrayList.size() - 1)).intValue() - ((Integer) arrayList.get(0)).intValue()) + 1);
                    return;
                }
            }
            runOnUiThread(new C0610o(this, cursorArr2, cursorArr, dataInfo, i2));
            return;
        }
        Log.e(this.TAG, "fail to get lock");
    }
}
