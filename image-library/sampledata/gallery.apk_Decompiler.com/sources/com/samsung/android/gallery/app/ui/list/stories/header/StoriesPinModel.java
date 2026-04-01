package com.samsung.android.gallery.app.ui.list.stories.header;

import A.a;
import B8.d;
import B8.j;
import Fa.C0571z;
import R6.c;
import U5.b;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import b6.e;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.StoriesPinCache;
import com.samsung.android.gallery.module.data.StoriesPinData;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinModel extends Subscriber implements IModel {
    private final List<MediaItem> mData = new ArrayList();
    protected PinModelListener mDataChangeListener;
    private int mDividerPos = -1;
    protected final AtomicBoolean mFirstLoading = new AtomicBoolean(true);
    protected MediaData mMediaData;
    protected final MediaData.OnDataChangeListener mMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            StoriesPinModel.this.reloadData();
        }

        public void onDataRangeChanged(int i2, int i7) {
            StoriesPinModel.this.notifyDataRangeChanged(i2, i7);
        }
    };

    public StoriesPinModel(Context context) {
        super(Blackboard.getInstance(context.toString()));
        onCreate();
    }

    private boolean equalItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem.getAlbumID() == mediaItem2.getAlbumID() && TextUtils.equals(mediaItem.getTitle(), mediaItem2.getTitle()) && mediaItem.getFileId() == mediaItem2.getFileId() && MediaItemStory.getStoryFavorite(mediaItem) == MediaItemStory.getStoryFavorite(mediaItem2) && MediaItemStory.getStoryCoverRectRatio(mediaItem).equals(MediaItemStory.getStoryCoverRectRatio(mediaItem2))) {
            return true;
        }
        return false;
    }

    private void findDirtyPosition(List<Integer> list, List<Integer> list2) {
        for (int i2 = 0; i2 < this.mData.size(); i2++) {
            if (list.contains(Integer.valueOf(this.mData.get(i2).getAlbumID()))) {
                list2.add(Integer.valueOf(i2));
            }
        }
    }

    private MediaData getMediaData() {
        if (!PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return MediaDataFactory.getInstance(this.mBlackboard).open("location://story/albums");
        }
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open("location://story/albums");
        try {
            MediaData open2 = open.getChildMediaData((String) null).open("location://stories/category/transitory");
            open.close();
            return open2;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private boolean isDataChanged(List<MediaItem> list) {
        if (list.size() != this.mData.size()) {
            return true;
        }
        int size = this.mData.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!equalItem(this.mData.get(i2), list.get(i2))) {
                return true;
            }
        }
        return false;
    }

    private boolean isReminder(MediaItem mediaItem) {
        if (MediaItemStory.getStoryFavorite(mediaItem) <= 0) {
            return true;
        }
        return false;
    }

    private boolean isValidRange(int i2, int i7) {
        int realCount = this.mMediaData.getRealCount();
        if (i2 < 0 || i2 >= realCount || i2 + i7 > realCount) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getMediaItemByAlbumId$9(int i2, MediaItem mediaItem) {
        if (mediaItem.getAlbumID() == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCachedData$5(StoriesPinData storiesPinData, StoriesPinCache storiesPinCache) {
        swapData(storiesPinData.getData(), storiesPinData.getDividerPos(), true);
        storiesPinCache.reset();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadData$4(StoriesPinData storiesPinData) {
        swapData(storiesPinData.getData(), storiesPinData.getDividerPos(), true);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$notifyDataRangeChanged$0(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem2.getAlbumID() == mediaItem.getAlbumID()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem lambda$notifyDataRangeChanged$1(List list, MediaItem mediaItem) {
        return (MediaItem) list.stream().filter(new j(mediaItem, 6)).findFirst().orElse(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyDataRangeChanged$2(List list, MediaItem mediaItem) {
        onDataRangeChanged(list.indexOf(Integer.valueOf(mediaItem.getAlbumID())));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyDataRangeChanged$3(List list, List list2, List list3) {
        swapData(list, this.mDividerPos, false);
        list2.forEach(new b(18, this, list3));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateFavoriteToMediaData$6(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem.getAlbumID() == mediaItem2.getAlbumID()) {
            return true;
        }
        return false;
    }

    private void loadCachedData() {
        StoriesPinCache instance = StoriesPinCache.getInstance();
        if (instance.isCached()) {
            ThreadUtil.runOnUiThread(new c(this, instance.getPinData(), instance, 29));
        } else {
            Log.w("StoriesPinModel", "preload is not completed");
        }
    }

    private void loadData() {
        this.mFirstLoading.set(false);
        ArrayList<MediaItem> allData = this.mMediaData.getAllData();
        if (allData != null) {
            long currentTimeMillis = System.currentTimeMillis();
            ThreadUtil.runOnUiThread(new androidx.window.embedding.c(4, this, new StoriesPinData(allData)));
            a.x(new StringBuilder("loaded +"), currentTimeMillis, "StoriesPinModel");
        }
    }

    private void onDataRangeChanged(int i2) {
        PinModelListener pinModelListener = this.mDataChangeListener;
        if (pinModelListener != null) {
            pinModelListener.onDataRangeChanged(i2, 1);
        }
    }

    /* access modifiers changed from: private */
    public void reloadData() {
        if (useCachedData()) {
            loadCachedData();
        } else {
            loadData();
        }
    }

    private void swapData(List<MediaItem> list, int i2, boolean z) {
        PinModelListener pinModelListener;
        boolean isDataChanged = isDataChanged(list);
        Log.d("StoriesPinModel", "swapData {" + list.size() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2 + "}," + z + GlobalPostProcInternalPPInterface.SPLIT_REGEX + isDataChanged);
        if (!isDataChanged) {
            Log.d("StoriesPinModel", "swapData ignored");
            return;
        }
        this.mData.clear();
        this.mData.addAll(list);
        this.mDividerPos = i2;
        if (z && (pinModelListener = this.mDataChangeListener) != null) {
            pinModelListener.onDataChanged();
        }
    }

    private void updateFavoriteToMediaData(List<MediaItem> list, ArrayList<MediaItem> arrayList) {
        if (list != null && arrayList != null) {
            list.stream().filter(new C0571z(4)).forEach(new d(arrayList, 25));
        }
    }

    private boolean useCachedData() {
        if (!this.mFirstLoading.get() || this.mMediaData.isFullyLoaded()) {
            return false;
        }
        return true;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("command://StoriesFavoritePin", new b6.d(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://StoriesFavoriteUnpin", new b6.d(this, 1)).setWorkingOnUI());
    }

    public List<Integer> getAllAlbumIds() {
        return (List) this.mData.stream().map(new Gb.a(7)).collect(Collectors.toList());
    }

    public int getCount() {
        return this.mData.size();
    }

    public int getDividerPosition() {
        return this.mDividerPos;
    }

    public MediaItem getMediaItem(int i2) {
        return this.mData.get(i2);
    }

    public MediaItem getMediaItemByAlbumId(int i2) {
        return this.mData.stream().filter(new B8.b(i2, 5)).findFirst().orElse((Object) null);
    }

    public Integer getYear(MediaItem mediaItem) {
        if (isReminder(mediaItem)) {
            return Integer.valueOf(StoriesPinData.ReminderDate.getYearAgo((FileItemInterface) mediaItem));
        }
        return null;
    }

    public boolean isDivider(int i2) {
        if (i2 == this.mDividerPos) {
            return true;
        }
        return false;
    }

    public void notifyDataRangeChanged(int i2, int i7) {
        ArrayList<MediaItem> allData = this.mMediaData.getAllData();
        if (allData != null && isValidRange(i2, i7)) {
            ArrayList arrayList = new ArrayList(allData.subList(i2, i7 + i2));
            ArrayList arrayList2 = new ArrayList(this.mData);
            arrayList2.replaceAll(new e(arrayList));
            ThreadUtil.postOnUiThread(new a8.d((Object) this, (Object) arrayList2, (Object) arrayList, (Object) (List) arrayList2.stream().filter(new C0571z(4)).map(new Gb.a(7)).collect(Collectors.toList()), 2));
        }
    }

    public void onDestroy() {
        super.onDestroy();
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mMediaDataChangeListener);
            this.mMediaData.close();
            this.mMediaData = null;
        }
        this.mDataChangeListener = null;
    }

    public final void onPinned(Object obj, Bundle bundle) {
        boolean z;
        int i2;
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList == null || arrayList.size() == 0) {
            Log.w("StoriesPinModel", "pin fail");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        List list = (List) arrayList.stream().filter(new C0571z(4)).map(new Gb.a(7)).collect(Collectors.toList());
        updateFavoriteToMediaData(arrayList, this.mMediaData.getAllData());
        if (this.mDividerPos > 0) {
            z = true;
        } else {
            z = false;
        }
        reloadData();
        ArrayList arrayList2 = new ArrayList();
        findDirtyPosition(list, arrayList2);
        if (!z && (i2 = this.mDividerPos) > 0) {
            arrayList2.add(Integer.valueOf(i2));
        }
        PinModelListener pinModelListener = this.mDataChangeListener;
        if (pinModelListener != null) {
            pinModelListener.onPinDataDirty(arrayList2);
        }
        Log.d("StoriesPinModel", "onPinned notified {" + list.size() + "}+" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public final void onUnpinned(Object obj, Bundle bundle) {
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList == null || arrayList.size() == 0) {
            Log.w("StoriesPinModel", "unpin fail");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        updateFavoriteToMediaData(arrayList, this.mMediaData.getAllData());
        reloadData();
        Log.d("StoriesPinModel", "unPinned notified {" + arrayList.size() + "}+" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public void open() {
        if (this.mMediaData == null) {
            MediaData mediaData = getMediaData();
            this.mMediaData = mediaData;
            mediaData.register(this.mMediaDataChangeListener);
        }
    }

    public void setDataChangeListener(PinModelListener pinModelListener) {
        this.mDataChangeListener = pinModelListener;
    }
}
