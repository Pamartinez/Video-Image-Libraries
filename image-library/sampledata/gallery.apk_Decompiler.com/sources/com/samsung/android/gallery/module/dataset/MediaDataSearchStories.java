package com.samsung.android.gallery.module.dataset;

import Ad.C0720a;
import W4.b;
import android.database.Cursor;
import com.samsung.android.gallery.module.abstraction.StoryCategory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.story.ondemand.OnDemandStory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataSearchStories extends MediaDataCursor {
    private final Map<String, MediaDataStoriesCategory> mChildData = new ConcurrentHashMap();
    private List<String> mChildKey = Collections.synchronizedList(new ArrayList());
    private int mCount;
    private final List<MediaItem> mItems = Collections.synchronizedList(new ArrayList());
    private final MediaData.SimpleDataChangeListener mListener = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0() {
            MediaDataSearchStories.this.swapData();
        }

        public void onDataChanged() {
            ThreadUtil.runOnBgThread(new r(2, this));
        }
    };
    private MediaDataCursor mMediaData;
    private final boolean mRegisterSubscriber;

    public MediaDataSearchStories(Blackboard blackboard, String str) {
        super(blackboard, str);
        this.mRegisterSubscriber = !LocationKey.isSearchCategoryStories(str);
    }

    /* access modifiers changed from: private */
    /* renamed from: belong */
    public boolean lambda$updateChildData$1(String str, MediaItem mediaItem) {
        int storyType = MediaItemStory.getStoryType(mediaItem);
        int storySaType = MediaItemStory.getStorySaType(mediaItem);
        String str2 = "location://search/fileList/Category/Stories/Transitory";
        if (!"location://stories/category/transitory".equals(StoryCategory.getKey(storyType, storySaType)) && (!PocFeatures.SUPPORT_RECAP_STACK_UI || !"location://stories/category/Recap".equals(StoryCategory.getKey(storyType, storySaType)))) {
            str2 = "location://search/fileList/Category/Stories/Others";
        }
        return str.equals(str2);
    }

    /* access modifiers changed from: private */
    public int childOrder(String str) {
        str.getClass();
        if (!str.equals("location://search/fileList/Category/Stories/Transitory")) {
            return 1;
        }
        return 0;
    }

    private void closeData() {
        MediaDataCursor mediaDataCursor = this.mMediaData;
        if (mediaDataCursor != null) {
            mediaDataCursor.unregister(this.mListener);
            this.mMediaData.close();
            this.mMediaData = null;
            this.mChildData.clear();
        }
    }

    private void computeTransitoryChildIfAbsent() {
        if (SdkConfig.atLeast(SdkConfig.SEM.V) && OnDemandStory.getInstance().computeLanguageIfAbsent(Locale.getDefault())) {
            this.mChildData.computeIfAbsent("location://search/fileList/Category/Stories/Transitory", new C0608m(7, this));
        }
    }

    private MediaItem getItem(int i2) {
        if (i2 < 0 || i2 >= this.mItems.size()) {
            return null;
        }
        return this.mItems.get(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$findPosition$3(long j2, int i2) {
        if (j2 == this.mItems.get(i2).getFileId()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$readById$2(long j2, MediaItem mediaItem) {
        if (mediaItem.getFileId() == j2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swapData$0(ArrayList arrayList) {
        swapInternal(arrayList);
        this.mLock.releaseWriteLock();
        notifyChanged();
    }

    /* access modifiers changed from: private */
    public MediaDataStoriesCategory openChildData(String str) {
        return (MediaDataStoriesCategory) MediaDataFactory.getInstance(this.mBlackboard).open(str);
    }

    private void openData(String str) {
        MediaDataCursor mediaDataCursor = (MediaDataCursor) MediaDataFactory.getInstance(this.mBlackboard).open(str);
        this.mMediaData = mediaDataCursor;
        mediaDataCursor.register(this.mListener);
    }

    private void swapChildData(ArrayList<MediaItem> arrayList) {
        computeTransitoryChildIfAbsent();
        updateChildData("location://search/fileList/Category/Stories/Transitory", arrayList);
        updateChildData("location://search/fileList/Category/Stories/Others", arrayList);
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList(this.mChildData.keySet()));
        synchronizedList.sort(Comparator.comparingInt(new v0(this, 1)));
        this.mChildKey = synchronizedList;
    }

    /* access modifiers changed from: private */
    public void swapData() {
        if (this.mLock.acquireWriteLock()) {
            MediaDataCursor mediaDataCursor = this.mMediaData;
            if (mediaDataCursor != null) {
                ArrayList<MediaItem> fullData = mediaDataCursor.getFullData();
                swapChildData(fullData);
                runOnUiThread(new B(6, this, fullData));
                return;
            }
            this.mLock.releaseWriteLock();
        }
    }

    private void swapInternal(ArrayList<MediaItem> arrayList) {
        this.mItems.clear();
        this.mItems.addAll(arrayList);
        this.mCount = this.mItems.size();
    }

    private void updateChildData(String str, List<MediaItem> list) {
        List list2 = (List) list.stream().filter(new E(this, str, 1)).collect(Collectors.toCollection(new C0720a(1)));
        this.mChildData.computeIfAbsent(str, new C0608m(7, this)).swap(list2, list2.size());
    }

    public /* bridge */ /* synthetic */ boolean acquireWriteLock(String str) {
        return super.acquireWriteLock(str);
    }

    public void close() {
        super.close();
        closeData();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        if (this.mRegisterSubscriber) {
            super.createSubscriberList(arrayList);
        }
    }

    public int findPosition(long j2) {
        return IntStream.range(0, this.mItems.size()).filter(new p0(this, j2)).findFirst().orElse(-1);
    }

    public ArrayList<MediaItem> getAllData() {
        return new ArrayList<>(this.mItems);
    }

    public int getChildCount() {
        return this.mChildKey.size();
    }

    public MediaData getChildMediaData(String str) {
        return this.mChildData.get(str);
    }

    public /* bridge */ /* synthetic */ ClusterTable getClusterTable(int i2) {
        return super.getClusterTable(i2);
    }

    public int getCount() {
        return this.mCount;
    }

    public /* bridge */ /* synthetic */ int getDataVersion() {
        return super.getDataVersion();
    }

    public /* bridge */ /* synthetic */ ArrayList getFileIds() {
        return super.getFileIds();
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
        return this.mCount;
    }

    public /* bridge */ /* synthetic */ int getRefCount() {
        return super.getRefCount();
    }

    public boolean isDataAvailable() {
        return !this.mItems.isEmpty();
    }

    public boolean isFullyLoaded() {
        if (this.mCount > 0) {
            return true;
        }
        return false;
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
        closeData();
        this.mChildData.clear();
    }

    public /* bridge */ /* synthetic */ MediaData open(String str, boolean z) {
        return super.open(str, z);
    }

    public /* bridge */ /* synthetic */ void reInit(String str) {
        super.reInit(str);
    }

    public MediaItem read(int i2) {
        return getItem(i2);
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        onDataReadListener.onDataReadCompleted(getItem(i2));
    }

    public MediaItem readById(long j2) {
        return this.mItems.stream().filter(new b(j2, 2)).findFirst().orElse((Object) null);
    }

    public MediaItem readCache(int i2) {
        return getItem(i2);
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

    public void swap(Cursor[] cursorArr) {
        MediaDataCursor mediaDataCursor = this.mMediaData;
        if (mediaDataCursor != null) {
            mediaDataCursor.swap(cursorArr);
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

    public MediaDataSearchStories wrap(String str) {
        openData(ArgumentsUtil.appendArgs(str, "request_data_on_open", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE));
        return this;
    }

    public MediaData getChildMediaData(int i2) {
        return this.mChildData.get(this.mChildKey.get(i2));
    }
}
