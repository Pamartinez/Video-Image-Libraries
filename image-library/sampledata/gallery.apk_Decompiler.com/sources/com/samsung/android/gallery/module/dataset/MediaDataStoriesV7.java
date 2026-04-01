package com.samsung.android.gallery.module.dataset;

import B5.c;
import E5.b;
import android.database.Cursor;
import android.graphics.Bitmap;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.abstraction.StoryCategory;
import com.samsung.android.gallery.module.cache.MemoryCacheMgr;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.story.ondemand.OnDemandStory;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataStoriesV7 extends MediaDataCursor {
    protected final List<String> mCategoriesKey = Collections.synchronizedList(new ArrayList());
    protected final ConcurrentHashMap<String, MediaDataStoriesCategory> mChildData = new ConcurrentHashMap<>();
    protected List<MediaItem> mItems = Collections.synchronizedList(new ArrayList());
    protected int mLoadedCount;
    private final MediaItem mRecapMediaItem;

    public MediaDataStoriesV7(Blackboard blackboard, String str) {
        super(blackboard, str);
        MediaItem mediaItem = new MediaItem();
        this.mRecapMediaItem = mediaItem;
        MediaItemStory.setStoryType(mediaItem, StoryType.RECAP.getValue());
    }

    private MediaDataStoriesCategory computeChildMediaData(String str) {
        return this.mChildData.computeIfAbsent(str, new C0608m(8, this));
    }

    private boolean filterOutRecap(ArrayList<String> arrayList) {
        return arrayList.remove("location://stories/category/Recap");
    }

    private HashMap<String, ArrayList<Integer>> indexingPosition(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("__storyType");
        int columnIndex2 = cursor.getColumnIndex("__storySaType");
        HashMap<String, ArrayList<Integer>> hashMap = new HashMap<>();
        if (cursor.moveToFirst()) {
            while (true) {
                int i2 = -1;
                if (columnIndex2 != -1) {
                    i2 = cursor.getInt(columnIndex2);
                }
                String key = StoryCategory.getKey(cursor.getInt(columnIndex), i2);
                hashMap.computeIfAbsent(key, new K(22)).add(Integer.valueOf(cursor.getPosition()));
                if (!key.equals("location://stories/category/more")) {
                    if (!cursor.moveToNext()) {
                        break;
                    }
                } else {
                    int count = cursor.getCount() - 1;
                    int position = cursor.getPosition();
                    while (position < count) {
                        position++;
                        hashMap.get(key).add(Integer.valueOf(position));
                    }
                }
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaDataStoriesCategory lambda$computeChildMediaData$11(String str) {
        return (MediaDataStoriesCategory) MediaDataFactory.getInstance(this.mBlackboard).open(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$indexingPosition$5(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$loadDataFull$2(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadDataFull$3(HashMap hashMap, ArrayList arrayList, Cursor cursor, String str, ArrayList arrayList2) {
        int i2;
        if (arrayList2 != null) {
            ArrayList arrayList3 = (ArrayList) hashMap.get(str);
            boolean equals = "location://stories/category/more".equals(str);
            if (equals) {
                i2 = arrayList.size();
            } else if (arrayList3 != null) {
                i2 = arrayList3.size();
            } else {
                i2 = 0;
            }
            while (i2 < arrayList2.size()) {
                cursor.moveToPosition(((Integer) arrayList2.get(i2)).intValue());
                MediaItem loadMediaItem = loadMediaItem(cursor, cursor.getPosition());
                updateState(loadMediaItem, new ArrayList());
                if (equals) {
                    arrayList.add(loadMediaItem);
                } else {
                    ((ArrayList) hashMap.computeIfAbsent(str, new K(18))).add(loadMediaItem);
                }
                i2++;
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$loadDataPartial$6(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadDataPartial$7(Cursor cursor, List list, HashMap hashMap, ArrayList arrayList, String str, ArrayList arrayList2) {
        if (arrayList2 != null) {
            int maxPreloadCount = StoryCategory.getMaxPreloadCount(str);
            for (int i2 = 0; i2 < Math.min(arrayList2.size(), maxPreloadCount); i2++) {
                cursor.moveToPosition(((Integer) arrayList2.get(i2)).intValue());
                MediaItem loadMediaItem = loadMediaItem(cursor, cursor.getPosition());
                updateState(loadMediaItem, list);
                if (!"location://stories/category/more".equals(str)) {
                    ((ArrayList) hashMap.computeIfAbsent(str, new K(19))).add(loadMediaItem);
                } else {
                    arrayList.add(loadMediaItem);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadDataPartial$8(ArrayList arrayList, ArrayList arrayList2, int i2) {
        swapData(arrayList, arrayList2, i2);
        notifyChanged();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$readById$0(long j2, MediaItem mediaItem) {
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
    public static /* synthetic */ ArrayList lambda$setupCategories$9(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$1(Cursor[] cursorArr, Cursor[] cursorArr2, ArrayList arrayList, ArrayList arrayList2) {
        try {
            if (this.mLock.acquireWriteLock()) {
                if (isDataCursorAvailable(cursorArr)) {
                    synchronized (cursorArr[0]) {
                        swapInternal(cursorArr2, arrayList, arrayList2, arrayList2.size());
                    }
                } else {
                    swapInternal(cursorArr2, arrayList, arrayList2, arrayList2.size());
                }
            }
            this.mLock.releaseWriteLock();
            notifyChanged();
            closeCursors(cursorArr);
            Log.d(this.TAG, "swap > done", Integer.valueOf(this.mItems.size()), toDebug());
        } catch (Throwable th) {
            this.mLock.releaseWriteLock();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swapChildMediaData$10(HashMap hashMap, String str) {
        Vector vector;
        ArrayList arrayList = (ArrayList) hashMap.get(str);
        if (arrayList == null) {
            vector = new Vector();
        }
        computeChildMediaData(str).swap(vector, vector.size());
        if (PocFeatures.SUPPORT_RECAP_STACK_UI && "location://stories/category/Recap".equals(str)) {
            this.mRecapMediaItem.setCount(vector.size());
        }
    }

    private void loadDataFull(Cursor cursor, ArrayList<MediaItem> arrayList, HashMap<String, ArrayList<Integer>> hashMap, HashMap<String, ArrayList<MediaItem>> hashMap2) {
        Cursor cursor2 = cursor;
        hashMap.forEach(new S(this, hashMap2, arrayList, cursor2, 1));
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            ThreadPool.getInstance().submit(new s0(cursor2));
        }
    }

    private void loadDataPartial(Cursor cursor, ArrayList<String> arrayList, ArrayList<MediaItem> arrayList2, HashMap<String, ArrayList<MediaItem>> hashMap, HashMap<String, ArrayList<Integer>> hashMap2, boolean z) {
        List arrayList3;
        int i2;
        if (z) {
            arrayList3 = loadViewedIds();
        } else {
            arrayList3 = new ArrayList();
        }
        ArrayList<MediaItem> arrayList4 = arrayList2;
        HashMap<String, ArrayList<MediaItem>> hashMap3 = hashMap;
        hashMap2.forEach(new t0(this, cursor, arrayList3, hashMap3, arrayList4));
        setupCategories(arrayList, hashMap3);
        if (z) {
            Log.d(this.TAG, "notify partial loaded");
            ArrayList arrayList5 = hashMap2.get("location://stories/category/more");
            if (arrayList5 != null) {
                i2 = arrayList5.size();
            } else {
                i2 = 0;
            }
            swapChildMediaData(hashMap3);
            runOnUiThread(new C0595c0(this, arrayList, arrayList4, i2));
        }
    }

    private List<String> loadViewedIds() {
        if (!PreferenceFeatures.PERFORMANCE.STORIES_CURSOR_CACHE) {
            return new ArrayList();
        }
        List<String> list = (List) Arrays.stream(PreferenceCache.StoryViewedIds.getString().split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).collect(Collectors.toList());
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "loadViewedIds" + Logger.vt((long) list.size()));
        return list;
    }

    private void setupCategories(ArrayList<String> arrayList, HashMap<String, ArrayList<MediaItem>> hashMap) {
        boolean z;
        if (!SdkConfig.atLeast(SdkConfig.SEM.V) || !OnDemandStory.getInstance().computeLanguageIfAbsent(Locale.getDefault())) {
            z = false;
        } else {
            z = true;
        }
        arrayList.addAll(hashMap.keySet());
        boolean z3 = PreferenceFeatures.OneUi8x.SUPPORT_TRANSITORY_ON_DEMAND_STORY;
        if (z3 && z && !arrayList.contains("location://stories/category/transitory")) {
            arrayList.add("location://stories/category/transitory");
        }
        if (!z3 && z && !arrayList.contains("location://stories/category/creation")) {
            arrayList.add("location://stories/category/creation");
        }
        arrayList.sort(Comparator.comparingInt(new T(2)));
        if (filterOutTrip()) {
            arrayList.remove("location://stories/category/trip");
        } else if (!PocFeatures.SUPPORT_RECAP_STACK_UI || !filterOutRecap(arrayList)) {
            ArrayList arrayList2 = hashMap.get("location://stories/category/trip");
            if (arrayList2 != null) {
                arrayList2.sort(getChildSorter("location://stories/category/trip"));
            }
            ArrayList arrayList3 = hashMap.get("location://stories/category/transitory");
            if (arrayList3 != null) {
                arrayList3.sort(getChildSorter("location://stories/category/transitory"));
            }
        } else {
            hashMap.computeIfAbsent("location://stories/category/transitory", new K(20)).add(0, this.mRecapMediaItem);
        }
    }

    private void swapChildMediaData(HashMap<String, ArrayList<MediaItem>> hashMap) {
        StoryCategory.getSupportCategories().forEach(new I(5, this, hashMap));
    }

    private void swapData(ArrayList<String> arrayList, ArrayList<MediaItem> arrayList2, int i2) {
        this.mCategoriesKey.clear();
        this.mCategoriesKey.addAll(arrayList);
        this.mItems.clear();
        this.mItems.addAll(arrayList2);
        this.mLoadedCount = arrayList2.size();
        this.mDataCount = i2;
    }

    private void swapInternal(Cursor[] cursorArr, ArrayList<String> arrayList, ArrayList<MediaItem> arrayList2, int i2) {
        this.mCursors = cursorArr;
        swapData(arrayList, arrayList2, i2);
    }

    private String toDebug() {
        ArrayList arrayList;
        String str;
        StringJoiner stringJoiner = new StringJoiner(";");
        try {
            synchronized (this.mListener) {
                arrayList = new ArrayList(this.mListener);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                MediaData.OnDataChangeListener onDataChangeListener = (MediaData.OnDataChangeListener) it.next();
                if (onDataChangeListener != null) {
                    String[] split = onDataChangeListener.getClass().getName().split("\\.");
                    if (split.length > 0) {
                        str = split[split.length - 1];
                    } else {
                        str = "";
                    }
                    stringJoiner.add(str);
                }
            }
        } catch (Exception unused) {
        }
        return stringJoiner.toString();
    }

    private void updateState(MediaItem mediaItem, List<String> list) {
        if (PreferenceFeatures.PERFORMANCE.STORIES_CURSOR_CACHE && MediaItemStory.getStoryNotifyStatus(mediaItem) != 1 && list.contains(String.valueOf(MediaItemStory.getStoryId(mediaItem)))) {
            MediaItemStory.setStoryNotifyStatus(mediaItem, 1);
        }
    }

    public /* bridge */ /* synthetic */ boolean acquireWriteLock(String str) {
        return super.acquireWriteLock(str);
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public boolean filterOutTrip() {
        if (!Features.isEnabled(Features.IS_CHINESE_DEVICE) || PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            return false;
        }
        return true;
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
        return new ArrayList<>(this.mItems);
    }

    public int getChildCount() {
        return this.mCategoriesKey.size();
    }

    public MediaData getChildMediaData(int i2) {
        if (i2 >= 0 && i2 < this.mCategoriesKey.size()) {
            return getChildMediaData(this.mCategoriesKey.get(i2));
        }
        Log.e((CharSequence) this.TAG, "no mediaItem", Integer.valueOf(this.mCategoriesKey.size()), Integer.valueOf(i2));
        return null;
    }

    public Comparator<FileItemInterface> getChildSorter(String str) {
        if ("location://stories/category/trip".equals(str)) {
            return Comparator.comparingLong(new b(8)).reversed();
        }
        if ("location://stories/category/transitory".equals(str)) {
            return Comparator.comparing(new K(21), Comparator.reverseOrder());
        }
        return null;
    }

    public /* bridge */ /* synthetic */ ClusterTable getClusterTable(int i2) {
        return super.getClusterTable(i2);
    }

    public int getCount() {
        return this.mDataCount;
    }

    public /* bridge */ /* synthetic */ int getDataVersion() {
        return super.getDataVersion();
    }

    public /* bridge */ /* synthetic */ ArrayList getFileIds() {
        return super.getFileIds();
    }

    public ArrayList<MediaItem> getFullData() {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        Iterator it = new ArrayList(this.mCategoriesKey).iterator();
        while (it.hasNext()) {
            MediaData mediaData = this.mChildData.get((String) it.next());
            if (mediaData != null) {
                arrayList.addAll(mediaData.getAllData());
            }
        }
        arrayList.addAll(getAllData());
        return arrayList;
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

    public int getRealCount() {
        return this.mLoadedCount;
    }

    public /* bridge */ /* synthetic */ int getRefCount() {
        return super.getRefCount();
    }

    public boolean isDataAvailable() {
        if (super.isDataAvailable() || this.mDataCount >= 0) {
            return true;
        }
        return false;
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
        if (this.mLoadedCount == this.mDataCount) {
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

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mChildData.values().forEach(new C0596d(8));
        this.mChildData.clear();
        this.mLoadedCount = -1;
    }

    public /* bridge */ /* synthetic */ MediaData open(String str, boolean z) {
        return super.open(str, z);
    }

    public void preloadThumbnail(MemoryCacheMgr<Integer, MediaItem> memoryCacheMgr) {
        this.mBlackboard.publishIfEmpty("lifecycle://on_thumbnail_load_start", Long.valueOf(System.currentTimeMillis()));
        C0612q qVar = new C0612q(6);
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "preloadThumbnail INFO{" + ThumbKind.LARGE_KIND + "#" + memoryCacheMgr.getSize() + "}");
        for (int i2 = 0; i2 < memoryCacheMgr.getSize(); i2++) {
            MediaItem cache = memoryCacheMgr.getCache(Integer.valueOf(Math.min(getPosition(i2), memoryCacheMgr.getSize() - 1)));
            if (cache != null) {
                instance.loadThumbnail(cache, ThumbKind.LARGE_KIND, qVar);
            }
        }
    }

    public /* bridge */ /* synthetic */ void reInit(String str) {
        super.reInit(str);
    }

    public MediaItem read(int i2) {
        return readCache(i2);
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        onDataReadListener.onDataReadCompleted(readCache(i2));
    }

    public MediaItem readById(long j2) {
        return getAllData().stream().filter(new W4.b(j2, 4)).findFirst().orElse((Object) null);
    }

    public MediaItem readCache(int i2) {
        try {
            return this.mItems.get(i2);
        } catch (Exception unused) {
            Log.w((CharSequence) this.TAG, "wrong position is requested", Integer.valueOf(i2), Integer.valueOf(this.mDataCount), Integer.valueOf(this.mLoadedCount));
            return null;
        }
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

    public void requestIfRequired(Blackboard blackboard, String str) {
        if (ArgumentsUtil.getArgValue(str, "request_data_on_open", true)) {
            BlackboardUtils.publishDataRequest(blackboard, str);
        }
    }

    public void swap(Cursor[] cursorArr) {
        MediaDataStoriesV7 mediaDataStoriesV7;
        try {
            if (this.mLock.acquireWriteLock()) {
                Cursor[] cursorArr2 = this.mCursors;
                Cursor cursor = cursorArr[0];
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                HashMap hashMap = new HashMap();
                TimeTickLog timeTickLog = new TimeTickLog("MediaDataStoriesV701 swap");
                HashMap<String, ArrayList<Integer>> indexingPosition = indexingPosition(cursor);
                timeTickLog.tick("indexing position");
                try {
                    loadDataPartial(cursor, arrayList2, arrayList, hashMap, indexingPosition, isFirstLoading());
                    mediaDataStoriesV7 = this;
                } catch (Exception e) {
                    e = e;
                    mediaDataStoriesV7 = this;
                    Exception exc = e;
                    try {
                        Log.e((CharSequence) mediaDataStoriesV7.TAG, "fail to swap e=", exc.getMessage());
                        exc.printStackTrace();
                        mediaDataStoriesV7.mLock.releaseWriteLock();
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    mediaDataStoriesV7 = this;
                    Throwable th3 = th;
                    mediaDataStoriesV7.mLock.releaseWriteLock();
                    throw th3;
                }
                try {
                    timeTickLog.tick("partial");
                    mediaDataStoriesV7.loadDataFull(cursor, arrayList, indexingPosition, hashMap);
                    timeTickLog.tock(0);
                    mediaDataStoriesV7.swapChildMediaData(hashMap);
                    mediaDataStoriesV7.runOnUiThread(new c(mediaDataStoriesV7, cursorArr2, cursorArr, arrayList2, arrayList, 22));
                } catch (Exception e7) {
                    e = e7;
                    Exception exc2 = e;
                    Log.e((CharSequence) mediaDataStoriesV7.TAG, "fail to swap e=", exc2.getMessage());
                    exc2.printStackTrace();
                    mediaDataStoriesV7.mLock.releaseWriteLock();
                }
            } else {
                mediaDataStoriesV7 = this;
                Log.e(mediaDataStoriesV7.TAG, "fail to get lock");
            }
        } catch (Exception e8) {
            e = e8;
            mediaDataStoriesV7 = this;
            Exception exc22 = e;
            Log.e((CharSequence) mediaDataStoriesV7.TAG, "fail to swap e=", exc22.getMessage());
            exc22.printStackTrace();
            mediaDataStoriesV7.mLock.releaseWriteLock();
        } catch (Throwable th4) {
            th = th4;
            mediaDataStoriesV7 = this;
            Throwable th32 = th;
            mediaDataStoriesV7.mLock.releaseWriteLock();
            throw th32;
        }
        mediaDataStoriesV7.mLock.releaseWriteLock();
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

    public MediaData getChildMediaData(String str) {
        return this.mChildData.get(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$preloadThumbnail$12(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
    }
}
