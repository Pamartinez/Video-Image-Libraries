package com.samsung.android.gallery.module.dataset;

import Ad.C0720a;
import android.database.Cursor;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.StoryItemCuration;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.EffectItemBuilder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataEntire;
import com.samsung.android.gallery.module.dataset.comparator.ComparatorEx;
import com.samsung.android.gallery.module.dataset.tables.DataTable;
import com.samsung.android.gallery.module.dataset.tables.RealRatioIndexer;
import com.samsung.android.gallery.module.dataset.tables.SortedDataTable;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import h4.C0464a;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataStoryHighlight extends MediaDataEntire {
    private int mCurationCount;
    protected final List<MediaItem> mCurationItems = new ArrayList();
    protected Closeable[] mExtraIndexers = new Closeable[getExtraTableCount()];
    protected final List<MediaItem> mFullItems = new ArrayList();
    protected ItemCurationProcessor mItemCurationProcessor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ItemCurationProcessor implements ComparatorEx<MediaItem> {
        private static final int MAX_CURATION = StoryItemCuration.MAX_CURATION;
        private final List<MediaItem> mCurationItems = new ArrayList();
        private final List<MediaItem> mFullItems = new ArrayList();

        /* access modifiers changed from: private */
        public MediaItem buildEffectItem(MediaItem mediaItem) {
            if (PreferenceFeatures.OneUi7x.SUPPORT_STORY_LIVE_EFFECT_TYPE && MediaItemStory.hasLiveEffectData(mediaItem)) {
                return EffectItemBuilder.buildLiveEffectItem(mediaItem);
            }
            if (PreferenceFeatures.OneUi8x.STORY_AI_EDIT_VI && MediaItemStory.isAiEditEffect(mediaItem)) {
                MediaItem buildEffectItem = EffectItemBuilder.buildEffectItem(mediaItem);
                if (buildEffectItem != null) {
                    MediaItemStory.setEffectItem(mediaItem, buildEffectItem);
                    return mediaItem;
                }
                MediaItemStory.setEffectType(mediaItem, -1);
            }
            return mediaItem;
        }

        /* access modifiers changed from: private */
        public List<MediaItem> getCurationItems() {
            return this.mCurationItems;
        }

        /* access modifiers changed from: private */
        public List<MediaItem> getFullItems() {
            return this.mFullItems;
        }

        private boolean isUserCurated(ArrayList<MediaItem> arrayList) {
            return arrayList.stream().anyMatch(new C0464a(8));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$makeCurationData$0(ArrayList arrayList, FileItemInterface fileItemInterface) {
            MediaItemStory.setUserCuration(fileItemInterface, true);
            arrayList.add((MediaItem) fileItemInterface);
        }

        private void makeCurationData(ArrayList<MediaItem> arrayList) {
            this.mCurationItems.clear();
            this.mFullItems.clear();
            if (!arrayList.isEmpty()) {
                this.mFullItems.addAll(arrayList);
                if (SdkConfig.atLeast(SdkConfig.GED.T)) {
                    if (!MediaItemStory.isUserEnter(arrayList.get(0))) {
                        DbCompat.storyApi().updateUserEnter(arrayList.get(0).getAlbumID());
                    }
                    if (!isUserCurated(arrayList)) {
                        ArrayList arrayList2 = new ArrayList();
                        ArrayList arrayList3 = new ArrayList(arrayList.subList(0, Math.min(arrayList.size(), MAX_CURATION)));
                        arrayList3.forEach(new Q(arrayList2, 1));
                        long currentTimeMillis = System.currentTimeMillis();
                        boolean updateUserCuration = DbCompat.storyApi().updateUserCuration(MediaItemStory.getStoryId((FileItemInterface) arrayList3.get(0)), arrayList3, 2);
                        Log.d("ItemCurationProcessor", "makeCurationData - updateUserCuration {" + arrayList2.size() + ",+" + updateUserCuration + "} + " + (System.currentTimeMillis() - currentTimeMillis));
                        this.mCurationItems.addAll(arrayList2);
                        return;
                    }
                    this.mCurationItems.addAll((Collection) arrayList.stream().filter(new C0464a(8)).collect(Collectors.toList()));
                    return;
                }
                this.mCurationItems.addAll(arrayList);
            }
        }

        public void complete(ArrayList<MediaItem> arrayList) {
            if (!isPickerMode()) {
                arrayList = (ArrayList) arrayList.stream().map(new C0608m(9, this)).collect(Collectors.toCollection(new C0720a(1)));
            }
            makeCurationData(arrayList);
        }

        public int getCount() {
            return this.mCurationItems.size();
        }

        public boolean isPickerMode() {
            return false;
        }

        public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
            int compare;
            long storyCoverId = MediaItemStory.getStoryCoverId(mediaItem);
            if (Features.isEnabled(Features.SUPPORT_STORY_REORDER) && (compare = Long.compare(MediaItemStory.getDisplayOrder(mediaItem2), MediaItemStory.getDisplayOrder(mediaItem))) != 0) {
                return compare;
            }
            int i2 = (storyCoverId > 0 ? 1 : (storyCoverId == 0 ? 0 : -1));
            if (i2 > 0 && mediaItem.getFileId() == storyCoverId) {
                return -1;
            }
            if (i2 <= 0 || mediaItem2.getFileId() != storyCoverId) {
                return Long.compare(mediaItem.getDateTaken(), mediaItem2.getDateTaken());
            }
            return 1;
        }

        public void prepare(ArrayList<MediaItem> arrayList) {
        }
    }

    public MediaDataStoryHighlight(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private void createRealRatioIndexer(Closeable[] closeableArr, DataTable dataTable, int i2) {
        closeableArr[0] = new RealRatioIndexer(dataTable, (ConcurrentHashMap<Integer, ClusterItem>) new ConcurrentHashMap(), 0, i2);
    }

    private int getCurationCount() {
        try {
            acquireReadLock("getCount");
            return this.mCurationCount;
        } finally {
            releaseReadLock("getCount");
        }
    }

    private int getExtraTableCount() {
        return 1;
    }

    private ItemCurationProcessor getItemCurationProcessor() {
        return getSorter();
    }

    private int getLoadedCount() {
        return getItemCurationProcessor().getCount();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$0(Cursor[] cursorArr, DataTable dataTable, List list) {
        swapTable(cursorArr, dataTable);
        close(list);
        terminateSwapProcessing();
    }

    public /* bridge */ /* synthetic */ void acquireReadLock(String str) {
        super.acquireReadLock(str);
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public DataTable createDataTable(Cursor cursor) {
        return new SortedDataTable(cursor, getSorter());
    }

    public ItemCurationProcessor createItemCurationProcessor() {
        return new ItemCurationProcessor() {
            public boolean isPickerMode() {
                return PickerUtil.isPickerMode(MediaDataStoryHighlight.this.getBlackboard());
            }
        };
    }

    public /* bridge */ /* synthetic */ int findPosition(long j2) {
        return super.findPosition(j2);
    }

    public /* bridge */ /* synthetic */ int findPositionByFileId(long j2) {
        return super.findPositionByFileId(j2);
    }

    public ArrayList<MediaItem> getAllData() {
        return new ArrayList<>(this.mCurationItems);
    }

    public int getCount() {
        return getCurationCount();
    }

    public /* bridge */ /* synthetic */ int getDataVersion() {
        return super.getDataVersion();
    }

    public /* bridge */ /* synthetic */ List getFileIds() {
        return super.getFileIds();
    }

    public /* bridge */ /* synthetic */ String getLocationKey() {
        return super.getLocationKey();
    }

    public /* bridge */ /* synthetic */ String getLocationReference() {
        return super.getLocationReference();
    }

    public int getRealCount() {
        return getCurationCount();
    }

    public RealRatioIndexer getRealRatioIndexer() {
        return (RealRatioIndexer) this.mExtraIndexers[0];
    }

    public /* bridge */ /* synthetic */ int getRefCount() {
        return super.getRefCount();
    }

    public /* bridge */ /* synthetic */ boolean isDataAvailable() {
        return super.isDataAvailable();
    }

    public boolean isFilteredEvent(EventMessage eventMessage) {
        return false;
    }

    public /* bridge */ /* synthetic */ boolean isFullyLoaded() {
        return super.isFullyLoaded();
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 102) {
            return true;
        }
        if (!this.mForceSwap || i2 != 101) {
            return false;
        }
        return true;
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
        MediaDataFactory.getInstance(this.mBlackboard).remove(this.mLocationKey);
        this.mCurationItems.clear();
        this.mFullItems.clear();
        this.mCurationCount = 0;
        Utils.closeSilently(this.mExtraIndexers[0]);
        this.mExtraIndexers[0] = null;
    }

    public void onInitDone() {
        if (this.mDataTable != null) {
            swapItems();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "onInitDone count = " + getLoadedCount());
            for (int i2 = 0; i2 < getExtraTableCount(); i2++) {
                Utils.closeSilently(this.mExtraIndexers[i2]);
            }
            Closeable[] closeableArr = new Closeable[getExtraTableCount()];
            this.mExtraIndexers = closeableArr;
            createRealRatioIndexer(closeableArr, this.mDataTable, getLoadedCount());
            StringCompat stringCompat2 = this.TAG;
            Log.d(stringCompat2, "createRealRatioIndexer init done. " + this.mExtraIndexers[0]);
        }
    }

    public /* bridge */ /* synthetic */ MediaData open(String str, boolean z) {
        return super.open(str, z);
    }

    public /* bridge */ /* synthetic */ void reInit(String str) {
        super.reInit(str);
    }

    public MediaItem read(int i2) {
        return readInternal(i2, "read");
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        onDataReadListener.onDataReadCompleted(readInternal(i2, "readAsync"));
    }

    public MediaItem readCache(int i2) {
        return readInternal(i2, "readCache");
    }

    public MediaItem readInternal(int i2, String str) {
        try {
            acquireReadLock("readInternal");
            if (i2 < this.mCurationCount && i2 >= 0) {
                return this.mCurationItems.get(i2);
            }
            StringCompat stringCompat = this.TAG;
            Log.w(stringCompat, "fail to read from " + str + "{" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mCurationCount + "}");
            releaseReadLock("readInternal");
            return null;
        } finally {
            releaseReadLock("readInternal");
        }
    }

    public /* bridge */ /* synthetic */ void register(MediaData.OnDataChangeListener onDataChangeListener) {
        super.register(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ void releaseReadLock(String str) {
        super.releaseReadLock(str);
    }

    public /* bridge */ /* synthetic */ void reopen(String str) {
        super.reopen(str);
    }

    public void swap(Cursor[] cursorArr) {
        if (this.mDataTable.isDuplicated(cursorArr[0])) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "swap > skipped by same cursor (" + this.mLocationKey + ")");
            return;
        }
        StringCompat stringCompat2 = this.TAG;
        Log.i(stringCompat2, "swap > start " + cursorArr.length);
        DataTable createDataTable = createDataTable(cursorArr[0]);
        if (this.mForceSwap) {
            Log.d(this.TAG, "swap > forceSwap. set by data change event arg1 == 1");
            this.mForceSwap = false;
        } else if (this.mDataTable.equals(createDataTable)) {
            StringCompat stringCompat3 = this.TAG;
            Log.d(stringCompat3, "swap > skip by same data (" + this.mLocationKey + ")");
            closeCursors(cursorArr);
            return;
        }
        beginSwapProcessing();
        createDataTable.fillDataRecords(this.mDataTable);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Collections.singletonList(this.mDataTable));
        arrayList.addAll(Arrays.asList(this.mExtraIndexers));
        Closeable[] closeableArr = new Closeable[getExtraTableCount()];
        this.mExtraIndexers = closeableArr;
        createRealRatioIndexer(closeableArr, createDataTable, getCount());
        runOnUiThread(new u0(this, cursorArr, createDataTable, arrayList));
    }

    public void swapItems() {
        this.mCurationItems.clear();
        this.mCurationItems.addAll(getItemCurationProcessor().getCurationItems());
        this.mCurationCount = this.mCurationItems.size();
        this.mFullItems.clear();
        this.mFullItems.addAll(getItemCurationProcessor().getFullItems());
    }

    public void swapTable(Cursor[] cursorArr, DataTable dataTable) {
        if (this.mRwLock.acquireWriteLock()) {
            int i2 = this.mCurationCount;
            swapItems();
            StringCompat stringCompat = this.TAG;
            StringBuilder o2 = C0086a.o(i2, "swapTable filterItem count {", "->");
            o2.append(this.mCurationCount);
            o2.append("}");
            Log.d(stringCompat, o2.toString());
            this.mRwLock.releaseWriteLock();
        }
        super.swapTable(cursorArr, dataTable);
    }

    public /* bridge */ /* synthetic */ String toDebugString() {
        return super.toDebugString();
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

    public boolean useSortedTable() {
        return true;
    }

    private void close(List<Closeable> list) {
        for (Closeable closeSilently : list) {
            Utils.closeSilently(closeSilently);
        }
    }

    public ItemCurationProcessor getSorter() {
        if (this.mItemCurationProcessor == null) {
            this.mItemCurationProcessor = createItemCurationProcessor();
        }
        return this.mItemCurationProcessor;
    }

    public void initExtraTable(Cursor[] cursorArr, CountDownLatch countDownLatch, MediaDataEntire.Candidates candidates) {
    }
}
