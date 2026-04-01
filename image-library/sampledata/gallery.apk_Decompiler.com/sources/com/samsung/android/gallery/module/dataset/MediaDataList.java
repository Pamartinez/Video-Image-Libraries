package com.samsung.android.gallery.module.dataset;

import Fa.C0571z;
import N2.j;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.concurrent.RwLock;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.DataStamp;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.GmpCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataList extends Subscriber implements MediaData {
    private final RwLock mListLock = new RwLock();
    private final String mLocationKey;
    private final ArrayList<MediaDataArray> mMediaDataArrayList = new ArrayList<>();

    public MediaDataList(Blackboard blackboard, String str) {
        super(blackboard);
        this.mLocationKey = str;
        onCreate();
    }

    /* access modifiers changed from: private */
    public void onDataChanged(Object obj, Bundle bundle) {
        EventMessage eventMessage = (EventMessage) obj;
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onDataChanged " + eventMessage);
        if (this.mListLock.acquireReadLock("MDL.onDataChanged")) {
            try {
                Iterator it = new ArrayList(this.mMediaDataArrayList).iterator();
                while (it.hasNext()) {
                    ((MediaDataArray) it.next()).notifyDataChanged(eventMessage);
                }
            } finally {
                this.mListLock.releaseReadLock("MDL.onDataChanged");
            }
        }
    }

    /* access modifiers changed from: private */
    public void remove(MediaDataArray mediaDataArray) {
        if (this.mListLock.acquireWriteLock()) {
            try {
                this.mMediaDataArrayList.remove(mediaDataArray);
            } finally {
                this.mListLock.releaseWriteLock();
            }
        }
    }

    public MediaDataArray createArray(String str) {
        return new MediaDataArray(this.mBlackboard, str, this);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("command://event/DataChanged", new D(this, 0)));
        if (PocFeatures.isEnabled(PocFeatures.GmpAll)) {
            arrayList.add(new SubscriberInfo("command://gmp/files/changed", new D(this, 1)));
        }
    }

    public int getCount() {
        if (!this.mListLock.acquireReadLock("MDL.getCount")) {
            return 0;
        }
        try {
            return this.mMediaDataArrayList.size();
        } finally {
            this.mListLock.releaseReadLock("MDL.getCount");
        }
    }

    public String getLocationKey() {
        return this.mLocationKey;
    }

    public boolean isDataAvailable() {
        if (getCount() > 0) {
            return true;
        }
        return false;
    }

    public void onDataChangedGmp(Object obj, Bundle bundle) {
        GmpCompat.GmpDataChangeDetails gmpDataChangeDetails;
        Log.d(this.TAG, "onDataChangedGmp(executed) ");
        if (obj != null) {
            gmpDataChangeDetails = (GmpCompat.GmpDataChangeDetails) obj;
        } else {
            gmpDataChangeDetails = null;
        }
        if (this.mListLock.acquireReadLock("MDL.onDataChanged")) {
            try {
                Iterator it = new ArrayList(this.mMediaDataArrayList).iterator();
                while (it.hasNext()) {
                    ((MediaDataArray) it.next()).notifyDataChangedGmp(gmpDataChangeDetails);
                }
            } finally {
                this.mListLock.releaseReadLock("MDL.onDataChanged");
            }
        }
    }

    public MediaData open(String str, boolean z) {
        MediaDataArray createArray = createArray(str);
        if (this.mListLock.acquireWriteLock()) {
            try {
                this.mMediaDataArrayList.add(createArray);
            } finally {
                this.mListLock.releaseWriteLock();
            }
        }
        createArray.open(str);
        return createArray;
    }

    public MediaItem read(int i2) {
        return null;
    }

    public MediaItem readCache(int i2) {
        return null;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaDataArray extends MediaDataRef {
        private final boolean mIsSuggestion;
        private final CopyOnWriteArrayList<MediaItem> mList = new CopyOnWriteArrayList<>();
        private final String mLocalLocationKey;
        private final MediaDataList mParent;
        private final PppUpdater mPppUpdater = new PppUpdater();
        private QueryParams mQueryParams;
        private final RwLock mRwLock = new RwLock();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum RemoveType {
            REMOVE,
            KEEP,
            SKIP
        }

        public MediaDataArray(Blackboard blackboard, String str, MediaDataList mediaDataList) {
            super(blackboard, ArgumentsUtil.removeArgs(str));
            this.mParent = mediaDataList;
            this.mIsSuggestion = ArgumentsUtil.getArgValue(str, "suggestion", false);
            this.mLocalLocationKey = str;
            onCreate();
        }

        private boolean dependentOnPath() {
            LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent");
            if (launchIntent == null || !launchIntent.isFromPictureFrame()) {
                return false;
            }
            return true;
        }

        private void forceUpdateList() {
            int size = this.mList.size();
            TimeTickLog timeTickLog = new TimeTickLog(C0086a.i(size, "forceUpdateList:"));
            HashMap hashMap = new HashMap();
            StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            for (int i2 = 0; i2 < size; i2++) {
                MediaItem mediaItem = this.mList.get(i2);
                if (!(mediaItem == null || mediaItem.getMediaType() == MediaType.UnlockScreen)) {
                    long fileId = mediaItem.getFileId();
                    if (fileId > 0) {
                        hashMap.put(Long.valueOf(fileId), Integer.valueOf(i2));
                        stringJoiner.add(Long.toString(fileId));
                    }
                }
            }
            if (!hashMap.isEmpty()) {
                updateList((HashMap<Long, Integer>) hashMap, stringJoiner.toString());
            }
            timeTickLog.tock(100);
        }

        private MediaItem get(int i2) {
            if (!isValid(i2) || !this.mRwLock.acquireReadLock("MDL.get")) {
                return null;
            }
            try {
                return this.mList.get(i2);
            } catch (Exception unused) {
                return null;
            } finally {
                this.mRwLock.releaseReadLock("MDL.get");
            }
        }

        private String getContentUriString(MediaItem mediaItem) {
            if (!SdkConfig.atLeast(SdkConfig.GED.R) || !mediaItem.isPostProcessing()) {
                return ContentUri.getUriString(mediaItem);
            }
            Uri secUri = ContentUri.getSecUri(mediaItem);
            if (secUri != null) {
                return secUri.toString();
            }
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "ppp fail get sec content uri " + mediaItem);
            return null;
        }

        /* JADX WARNING: Removed duplicated region for block: B:21:0x002d A[Catch:{ all -> 0x001e, all -> 0x0023, Exception -> 0x0028 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean isAllBurstShotRemoved(com.samsung.android.gallery.module.data.MediaItem r4) {
            /*
                r3 = this;
                r3 = 1
                java.lang.String r0 = com.samsung.android.gallery.database.dal.abstraction.DbKey.FILES_BURSTSHOT     // Catch:{ Exception -> 0x0028 }
                com.samsung.android.gallery.module.dataset.G r1 = new com.samsung.android.gallery.module.dataset.G     // Catch:{ Exception -> 0x0028 }
                r2 = 1
                r1.<init>(r4, r2)     // Catch:{ Exception -> 0x0028 }
                android.database.Cursor r4 = com.samsung.android.gallery.database.dal.DbCompat.query(r0, r1)     // Catch:{ Exception -> 0x0028 }
                if (r4 == 0) goto L_0x002a
                boolean r0 = r4.moveToFirst()     // Catch:{ all -> 0x001e }
                if (r0 == 0) goto L_0x002a
                int r0 = r4.getCount()     // Catch:{ all -> 0x001e }
                if (r0 > 0) goto L_0x001c
                goto L_0x002a
            L_0x001c:
                r0 = 0
                goto L_0x002b
            L_0x001e:
                r0 = move-exception
                r4.close()     // Catch:{ all -> 0x0023 }
                goto L_0x0027
            L_0x0023:
                r4 = move-exception
                r0.addSuppressed(r4)     // Catch:{ Exception -> 0x0028 }
            L_0x0027:
                throw r0     // Catch:{ Exception -> 0x0028 }
            L_0x0028:
                r4 = move-exception
                goto L_0x0031
            L_0x002a:
                r0 = r3
            L_0x002b:
                if (r4 == 0) goto L_0x0030
                r4.close()     // Catch:{ Exception -> 0x0028 }
            L_0x0030:
                return r0
            L_0x0031:
                r4.printStackTrace()
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataList.MediaDataArray.isAllBurstShotRemoved(com.samsung.android.gallery.module.data.MediaItem):boolean");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
            if (r0 != null) goto L_0x0043;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.samsung.android.gallery.module.dataset.MediaDataList.MediaDataArray.RemoveType isAllSingleTakeShotRemoved(java.util.concurrent.ConcurrentHashMap<com.samsung.android.gallery.module.data.MediaItem, java.lang.Boolean> r4, com.samsung.android.gallery.module.data.MediaItem r5) {
            /*
                r3 = this;
                java.lang.String r0 = com.samsung.android.gallery.database.dal.abstraction.DbKey.FILES_SINGLETAKE     // Catch:{ Exception -> 0x0052 }
                com.samsung.android.gallery.module.dataset.G r1 = new com.samsung.android.gallery.module.dataset.G     // Catch:{ Exception -> 0x0052 }
                r2 = 0
                r1.<init>(r5, r2)     // Catch:{ Exception -> 0x0052 }
                android.database.Cursor r0 = com.samsung.android.gallery.database.dal.DbCompat.query(r0, r1)     // Catch:{ Exception -> 0x0052 }
                if (r0 == 0) goto L_0x003f
                boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x0031 }
                if (r1 != 0) goto L_0x0015
                goto L_0x003f
            L_0x0015:
                int r1 = r0.getCount()     // Catch:{ all -> 0x0031 }
                r2 = 1
                if (r1 != r2) goto L_0x0033
                com.samsung.android.gallery.module.data.MediaItem r1 = com.samsung.android.gallery.module.data.MediaItemLoader.load(r0)     // Catch:{ all -> 0x0031 }
                java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.gallery.module.data.MediaItem> r3 = r3.mList     // Catch:{ all -> 0x0031 }
                int r5 = r3.indexOf(r5)     // Catch:{ all -> 0x0031 }
                r3.set(r5, r1)     // Catch:{ all -> 0x0031 }
                java.lang.Boolean r3 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0031 }
                r4.put(r1, r3)     // Catch:{ all -> 0x0031 }
                com.samsung.android.gallery.module.dataset.MediaDataList$MediaDataArray$RemoveType r3 = com.samsung.android.gallery.module.dataset.MediaDataList.MediaDataArray.RemoveType.SKIP     // Catch:{ all -> 0x0031 }
                goto L_0x0043
            L_0x0031:
                r3 = move-exception
                goto L_0x0047
            L_0x0033:
                int r3 = r0.getCount()     // Catch:{ all -> 0x0031 }
                if (r3 > 0) goto L_0x003c
                com.samsung.android.gallery.module.dataset.MediaDataList$MediaDataArray$RemoveType r3 = com.samsung.android.gallery.module.dataset.MediaDataList.MediaDataArray.RemoveType.REMOVE     // Catch:{ all -> 0x0031 }
                goto L_0x0043
            L_0x003c:
                com.samsung.android.gallery.module.dataset.MediaDataList$MediaDataArray$RemoveType r3 = com.samsung.android.gallery.module.dataset.MediaDataList.MediaDataArray.RemoveType.KEEP     // Catch:{ all -> 0x0031 }
                goto L_0x0043
            L_0x003f:
                com.samsung.android.gallery.module.dataset.MediaDataList$MediaDataArray$RemoveType r3 = com.samsung.android.gallery.module.dataset.MediaDataList.MediaDataArray.RemoveType.REMOVE     // Catch:{ all -> 0x0031 }
                if (r0 == 0) goto L_0x0046
            L_0x0043:
                r0.close()     // Catch:{ Exception -> 0x0052 }
            L_0x0046:
                return r3
            L_0x0047:
                if (r0 == 0) goto L_0x0051
                r0.close()     // Catch:{ all -> 0x004d }
                goto L_0x0051
            L_0x004d:
                r4 = move-exception
                r3.addSuppressed(r4)     // Catch:{ Exception -> 0x0052 }
            L_0x0051:
                throw r3     // Catch:{ Exception -> 0x0052 }
            L_0x0052:
                r3 = move-exception
                r3.printStackTrace()
                com.samsung.android.gallery.module.dataset.MediaDataList$MediaDataArray$RemoveType r3 = com.samsung.android.gallery.module.dataset.MediaDataList.MediaDataArray.RemoveType.REMOVE
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataList.MediaDataArray.isAllSingleTakeShotRemoved(java.util.concurrent.ConcurrentHashMap, com.samsung.android.gallery.module.data.MediaItem):com.samsung.android.gallery.module.dataset.MediaDataList$MediaDataArray$RemoveType");
        }

        private boolean isCropViewKey(String str) {
            return str.startsWith("location://cropView");
        }

        private boolean isFileKey(String str) {
            return str.startsWith("location://file");
        }

        private boolean isMapPictures() {
            if (getLocationKey().startsWith("location://map/filtered") || getLocationKey().startsWith("location://selectedItems")) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        /* renamed from: isRemovable */
        public boolean lambda$notifyDataChangedGmp$0(MediaItem mediaItem, GmpCompat.GmpDataChangeDetails gmpDataChangeDetails) {
            if (mediaItem == null) {
                return true;
            }
            if (mediaItem.getMediaType() == MediaType.UnlockScreen) {
                return false;
            }
            String path = mediaItem.getPath();
            if (mediaItem.isBurstShot()) {
                return isAllBurstShotRemoved(mediaItem);
            }
            return !FileUtils.exists(path);
        }

        private static boolean isStoryRemovable(MediaItem mediaItem) {
            if (mediaItem == null || DbCompat.storyApi().getContentsCount(MediaItemStory.getStoryId(mediaItem)) <= 0) {
                return true;
            }
            return false;
        }

        private boolean isSwapAvailable() {
            if (LocationKey.isDynamicViewList(this.mLocalLocationKey) || LocationKey.isColorCorrectView(this.mLocalLocationKey)) {
                return false;
            }
            return true;
        }

        private boolean isValid(int i2) {
            if (i2 < 0 || i2 >= this.mDataCount) {
                return false;
            }
            return true;
        }

        private boolean isViewOriginQuickViewItem() {
            return ArgumentsUtil.getArgValue(this.mLocalLocationKey, "view_origin_item", false);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$notifyDataChanged$1(ConcurrentHashMap concurrentHashMap, int i2, boolean z) {
            if (this.mRwLock.acquireWriteLock()) {
                try {
                    this.mList.removeIf(new C0571z(7));
                    CopyOnWriteArrayList<MediaItem> copyOnWriteArrayList = this.mList;
                    Objects.requireNonNull(concurrentHashMap);
                    copyOnWriteArrayList.removeIf(new C0606k(3, concurrentHashMap));
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    this.mRwLock.releaseWriteLock();
                    throw th;
                }
                this.mRwLock.releaseWriteLock();
            }
            StringCompat stringCompat = this.TAG;
            StringBuilder o2 = C0086a.o(i2, "swap > updated count : ", " > ");
            o2.append(this.mList.size());
            Log.i(stringCompat, o2.toString());
            if (i2 != this.mList.size() || z || this.mForceSwap) {
                this.mBlackboard.publish("command://event/QuickViewDataChanged", this.mList);
                this.mDataCount = this.mList.size();
                notifyChanged();
                this.mForceSwap = false;
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$updateList$2(String str, QueryParams queryParams) {
            if (isMapPictures()) {
                setMapPicturesGroupTypes(queryParams);
            } else if (ArgumentsUtil.getArgValue(this.mLocalLocationKey, "with_group", false)) {
                queryParams.addGroupType(GroupType.SINGLE_TAKEN);
            } else {
                QueryParams queryParams2 = this.mQueryParams;
                if (queryParams2 != null) {
                    queryParams.setGroupTypes(queryParams2.getGroupTypes());
                }
            }
            queryParams.setFileIds(str);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$updateList$3(boolean z, MediaItem mediaItem, QueryParams queryParams) {
            if (z) {
                queryParams.addGroupType(GroupType.SINGLE_TAKEN);
            } else {
                QueryParams queryParams2 = this.mQueryParams;
                if (queryParams2 != null) {
                    queryParams.setGroupTypes(queryParams2.getGroupTypes());
                }
            }
            if (mediaItem.getFileId() > 0) {
                queryParams.addUri(ContentUri.getSecUri(mediaItem));
                return;
            }
            String contentUriString = getContentUriString(mediaItem);
            if (!TextUtils.isEmpty(contentUriString)) {
                queryParams.addUri(contentUriString);
            }
        }

        private MediaItem loadBurstShotItem(MediaItem mediaItem) {
            Cursor query = DbCompat.query(DbKey.FILES_BURSTSHOT, new G(mediaItem, 2));
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        MediaItem load = MediaItemLoader.load(query, 0);
                        query.close();
                        return load;
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
            throw th;
        }

        private MediaItem[] loadMediaItem(String str) {
            if (isFileKey(str)) {
                MediaItem mediaItem = (MediaItem) this.mBlackboard.read(ArgumentsUtil.getArgValue(str, "media_item"));
                if (mediaItem == null) {
                    return new MediaItem[0];
                }
                return new MediaItem[]{mediaItem};
            } else if (isCropViewKey(str)) {
                MediaItem mediaItem2 = (MediaItem) this.mBlackboard.read(DataKey.DATA(this.mLocationKey));
                this.mBlackboard.erase(DataKey.DATA(this.mLocationKey));
                if (mediaItem2 == null) {
                    return new MediaItem[0];
                }
                return new MediaItem[]{mediaItem2};
            } else {
                MediaItem[] mediaItemArr = (MediaItem[]) this.mBlackboard.read(DataKey.DATA(this.mLocationKey));
                this.mBlackboard.erase(DataKey.DATA(this.mLocationKey));
                if (mediaItemArr == null || mediaItemArr.length == 0) {
                    MediaItem mediaItem3 = (MediaItem) this.mBlackboard.read("data://viewer_first_data");
                    StringCompat stringCompat = this.TAG;
                    Log.d(stringCompat, "loadMediaItem first " + MediaItemUtil.getDebugLog(mediaItem3));
                    if (mediaItem3 != null) {
                        return new MediaItem[]{mediaItem3};
                    }
                }
                if (mediaItemArr != null) {
                    return mediaItemArr;
                }
                return new MediaItem[0];
            }
        }

        private boolean refreshNecessary() {
            boolean z;
            int size = this.mList.size();
            boolean z3 = false;
            for (int i2 = 0; i2 < size; i2++) {
                MediaItem mediaItem = this.mList.get(i2);
                if (mediaItem != null && !MediaItemStory.isRecap(mediaItem)) {
                    boolean z7 = true;
                    if (MediaItemUtil.isRemastering(mediaItem) || VideoPropData.of(mediaItem).longExposure) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (mediaItem.getSefFileType() <= 0 && !mediaItem.isGroupShot() && !isViewOriginQuickViewItem() && mediaItem.getMediaId() != 0) {
                        z7 = false;
                    }
                    if (z && z7) {
                        z3 = updateList(i2, mediaItem);
                    }
                }
            }
            return z3;
        }

        private void setMapPicturesGroupTypes(QueryParams queryParams) {
            String argValue = ArgumentsUtil.getArgValue(this.mLocalLocationKey, "add_group_types");
            if (!TextUtils.isEmpty(argValue)) {
                for (String str : argValue.split(NumericEnum.SEP)) {
                    GroupType groupType = GroupType.SIMILAR;
                    if (groupType.type.equals(str)) {
                        queryParams.addGroupType(groupType);
                    } else {
                        GroupType groupType2 = GroupType.SINGLE_TAKEN;
                        if (groupType2.type.equals(str)) {
                            queryParams.addGroupType(groupType2);
                        }
                    }
                }
            }
        }

        private boolean updateList(Uri uri) {
            if ((!isSwapAvailable() || !this.mForceSwap) && !isMapPictures()) {
                return refreshNecessary();
            }
            forceUpdateList();
            return true;
        }

        public void changeOrder(Comparator<MediaItem> comparator) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.mRwLock.acquireReadLock("changeOrder")) {
                try {
                    this.mList.sort(comparator);
                } finally {
                    this.mRwLock.releaseReadLock("changeOrder");
                }
            }
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "changeOrder elapsed +" + (System.currentTimeMillis() - currentTimeMillis));
        }

        public void clearList() {
            if (this.mRwLock.acquireWriteLock()) {
                try {
                    this.mDataCount = 0;
                    this.mList.clear();
                } finally {
                    this.mRwLock.releaseWriteLock();
                }
            }
        }

        public void close() {
            this.mParent.remove(this);
            clearList();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "close (" + this.mLocationKey + ", size=" + this.mDataCount + ", parentCount=" + this.mParent.getCount());
            if (this.mRefCount.decrementAndGet() == 0) {
                onDestroy();
            }
        }

        public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
            super.createSubscriberList(arrayList);
            subscribePppCompleted(arrayList);
            arrayList.add(new SubscriberInfo("command://event/DataDirty", new C0594c(this, 2)));
        }

        public int findPosition(long j2) {
            for (int i2 = 0; i2 < this.mList.size(); i2++) {
                MediaItem mediaItem = this.mList.get(i2);
                if (mediaItem != null && mediaItem.getMediaId() == j2) {
                    return i2;
                }
            }
            StringCompat stringCompat = this.TAG;
            StringBuilder j3 = j.j(j2, "findPosition failed {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            j3.append(this.mList.size());
            j3.append("}");
            Log.e(stringCompat, j3.toString());
            return -1;
        }

        public int findPositionByFileId(long j2) {
            for (int i2 = 0; i2 < this.mList.size(); i2++) {
                MediaItem mediaItem = this.mList.get(i2);
                if (mediaItem != null && mediaItem.getFileId() == j2) {
                    return i2;
                }
            }
            StringCompat stringCompat = this.TAG;
            StringBuilder j3 = j.j(j2, "findPositionByFileId failed {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            j3.append(this.mList.size());
            j3.append("}");
            Log.e(stringCompat, j3.toString());
            return -1;
        }

        public ArrayList<MediaItem> getAllData() {
            return new ArrayList<>(this.mList);
        }

        public List<Long> getFileIds() {
            ArrayList arrayList = new ArrayList();
            if (!this.mRwLock.acquireReadLock("MDL.getFileIds")) {
                return arrayList;
            }
            try {
                Iterator<MediaItem> it = this.mList.iterator();
                while (it.hasNext()) {
                    arrayList.add(Long.valueOf(it.next().getFileId()));
                }
                return arrayList;
            } finally {
                this.mRwLock.releaseReadLock("MDL.getFileIds");
            }
        }

        public void insertItemAt(int i2, MediaItem mediaItem) {
            super.insertItemAt(i2, mediaItem);
            if (i2 >= 0 && this.mRwLock.acquireWriteLock()) {
                try {
                    this.mList.add(i2, mediaItem);
                    this.mDataCount = this.mList.size();
                    ThreadUtil.postOnUiThread(new r(1, this));
                } finally {
                    this.mRwLock.releaseWriteLock();
                }
            }
        }

        public boolean isFilteredEvent(EventMessage eventMessage) {
            return this.mPppUpdater.isFilteredEvent(eventMessage);
        }

        public void notifyDataChanged(EventMessage eventMessage) {
            boolean z;
            if (this.mRwLock.acquireWriteLock()) {
                try {
                    if (this.mIsSuggestion && eventMessage.what == 102) {
                        if (getCount() > 0 && isStoryRemovable(this.mList.get(0))) {
                            this.mList.clear();
                            this.mDataCount = 0;
                            Log.i(this.TAG, "swap (story notifications) size=1>" + this.mDataCount);
                            notifyChanged();
                            this.mRwLock.releaseWriteLock();
                        }
                    }
                    if (!isMapPictures() || eventMessage.what == 101) {
                        Uri uri = (Uri) eventMessage.obj;
                        boolean updateList = updateList(uri);
                        int count = getCount();
                        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                        Iterator<MediaItem> it = this.mList.iterator();
                        while (it.hasNext()) {
                            MediaItem next = it.next();
                            if (next != null) {
                                if (isRemovable(concurrentHashMap, next, uri) == RemoveType.REMOVE) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                concurrentHashMap.put(next, Boolean.valueOf(z));
                            }
                        }
                        ThreadUtil.postOnUiThread(new F(this, concurrentHashMap, count, updateList));
                        this.mRwLock.releaseWriteLock();
                    }
                    Log.v(this.TAG, "prevent updating list by data changing not related with map.");
                    this.mRwLock.releaseWriteLock();
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    this.mRwLock.releaseWriteLock();
                    throw th;
                }
            }
        }

        public void notifyDataChangedGmp(GmpCompat.GmpDataChangeDetails gmpDataChangeDetails) {
            if (this.mRwLock.acquireWriteLock()) {
                try {
                    boolean refreshNecessary = refreshNecessary();
                    int count = getCount();
                    this.mList.removeIf(new E(this, gmpDataChangeDetails, 0));
                    StringCompat stringCompat = this.TAG;
                    Log.i(stringCompat, "swap > updated count : " + count + " > " + this.mList.size());
                    if (count != this.mList.size() || refreshNecessary) {
                        this.mDataCount = this.mList.size();
                        notifyChanged();
                    }
                } finally {
                    this.mRwLock.releaseWriteLock();
                }
            }
        }

        public void onDestroy() {
            this.mPppUpdater.clear();
            super.onDestroy();
        }

        public MediaData open(String str, boolean z) {
            if (this.mRefCount.getAndIncrement() == 0) {
                openInternal(str);
            }
            return this;
        }

        public void openInternal(String str) {
            MediaItem[] loadMediaItem = loadMediaItem(str);
            MediaItem mediaItem = null;
            String argValue = ArgumentsUtil.getArgValue(str, "QueryParams", (String) null);
            if (argValue != null) {
                this.mQueryParams = (QueryParams) this.mBlackboard.read(argValue);
            }
            StringCompat stringCompat = this.TAG;
            Log.i(stringCompat, "open {" + this.mLocationKey + "} " + loadMediaItem.length + ", parent=" + this.mParent.getCount());
            if (this.mRwLock.acquireWriteLock()) {
                try {
                    Collections.addAll(this.mList, loadMediaItem);
                    this.mDataCount = loadMediaItem.length;
                } finally {
                    this.mRwLock.releaseWriteLock();
                }
            } else {
                Log.e(this.TAG, "open failed");
            }
            if (!this.mList.isEmpty()) {
                mediaItem = this.mList.get(0);
            }
            if (mediaItem != null) {
                String dataStamp = mediaItem.getDataStamp();
                if (!TextUtils.isEmpty(dataStamp)) {
                    this.mLastDataStamp = new DataStamp(dataStamp);
                    this.mBlackboard.post("data://DataStamp", dataStamp);
                }
                StringCompat stringCompat2 = this.TAG;
                Log.d(stringCompat2, "DATA_STAMP > " + Logger.v(dataStamp, mediaItem));
                return;
            }
            Log.d(this.TAG, "DATA_STAMP notify fail ");
        }

        public MediaItem read(int i2) {
            return get(i2);
        }

        public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
            onDataReadListener.onDataReadCompleted(get(i2));
        }

        public MediaItem readCache(int i2) {
            return get(i2);
        }

        public void removeItemAt(int i2) {
            if (isValid(i2) && this.mRwLock.acquireWriteLock()) {
                try {
                    this.mList.remove(i2);
                    this.mDataCount = this.mList.size();
                    ThreadUtil.postOnUiThread(new r(1, this));
                } finally {
                    this.mRwLock.releaseWriteLock();
                }
            }
        }

        public void reopen(String str) {
            clearList();
            openInternal(str);
        }

        public void replaceItemAt(int i2, MediaItem mediaItem) {
            if (isValid(i2) && this.mRwLock.acquireWriteLock()) {
                try {
                    this.mList.set(i2, mediaItem);
                    this.mDataCount = this.mList.size();
                    ThreadUtil.postOnUiThread(new r(1, this));
                } finally {
                    this.mRwLock.releaseWriteLock();
                }
            }
        }

        private void updateList(HashMap<Long, Integer> hashMap, String str) {
            Cursor query = DbCompat.query(DbKey.ALL_PICTURES, new I(0, this, str));
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        do {
                            MediaItem load = MediaItemLoader.load(query);
                            Integer num = hashMap.get(Long.valueOf(load.getFileId()));
                            if (num != null) {
                                this.mList.set(num.intValue(), load);
                            }
                        } while (query.moveToNext());
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (query != null) {
                query.close();
                return;
            }
            return;
            throw th;
        }

        private RemoveType isRemovable(ConcurrentHashMap<MediaItem, Boolean> concurrentHashMap, MediaItem mediaItem, Uri uri) {
            if (mediaItem == null) {
                return RemoveType.REMOVE;
            }
            if (mediaItem.getMediaType() == MediaType.UnlockScreen) {
                return RemoveType.KEEP;
            }
            String path = mediaItem.getPath();
            if (path != null && path.startsWith("content://")) {
                return new FilesApi().isUriItemValid(path, uri) ? RemoveType.KEEP : RemoveType.REMOVE;
            }
            if (path != null && path.startsWith("file://")) {
                return RemoveType.KEEP;
            }
            if (mediaItem.isBurstShot() && !"location://BurstShotSelectviewer".equals(getLocationKey())) {
                return isAllBurstShotRemoved(mediaItem) ? RemoveType.REMOVE : RemoveType.KEEP;
            }
            if (mediaItem.isSingleTakenShot()) {
                return isAllSingleTakeShotRemoved(concurrentHashMap, mediaItem);
            }
            if (FileUtils.exists(path) || mediaItem.isPostProcessing()) {
                return RemoveType.KEEP;
            }
            Log.w(this.TAG, "file doesn't exist");
            return RemoveType.REMOVE;
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x0044 A[Catch:{ all -> 0x0023, all -> 0x0058 }] */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0045 A[Catch:{ all -> 0x0023, all -> 0x0058 }] */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x004e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean updateList(int r5, com.samsung.android.gallery.module.data.MediaItem r6) {
            /*
                r4 = this;
                java.lang.String r0 = r4.mLocalLocationKey
                java.lang.String r1 = "with_group"
                r2 = 0
                boolean r0 = com.samsung.android.gallery.support.utils.ArgumentsUtil.getArgValue((java.lang.String) r0, (java.lang.String) r1, (boolean) r2)
                java.lang.String r1 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALL_PICTURES
                com.samsung.android.gallery.module.dataset.H r3 = new com.samsung.android.gallery.module.dataset.H
                r3.<init>(r4, r0, r6)
                android.database.Cursor r0 = com.samsung.android.gallery.database.dal.DbCompat.query(r1, r3)
                r1 = 0
                if (r0 == 0) goto L_0x0025
                int r3 = r0.getCount()     // Catch:{ all -> 0x0023 }
                if (r3 <= 0) goto L_0x0025
                com.samsung.android.gallery.module.data.MediaItem r2 = com.samsung.android.gallery.module.data.MediaItemLoader.load(r0, r2)     // Catch:{ all -> 0x0023 }
                goto L_0x0026
            L_0x0023:
                r4 = move-exception
                goto L_0x0052
            L_0x0025:
                r2 = r1
            L_0x0026:
                if (r2 != 0) goto L_0x0032
                boolean r3 = r6.isBurstShot()     // Catch:{ all -> 0x0023 }
                if (r3 == 0) goto L_0x0032
                com.samsung.android.gallery.module.data.MediaItem r2 = r4.loadBurstShotItem(r6)     // Catch:{ all -> 0x0023 }
            L_0x0032:
                if (r2 == 0) goto L_0x0045
                boolean r3 = r4.dependentOnPath()     // Catch:{ all -> 0x0023 }
                if (r3 == 0) goto L_0x0045
                int r6 = r6.getBucketID()     // Catch:{ all -> 0x0023 }
                int r3 = r2.getBucketID()     // Catch:{ all -> 0x0023 }
                if (r6 == r3) goto L_0x0045
                goto L_0x0046
            L_0x0045:
                r1 = r2
            L_0x0046:
                java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.gallery.module.data.MediaItem> r4 = r4.mList     // Catch:{ all -> 0x0023 }
                r4.set(r5, r1)     // Catch:{ all -> 0x0023 }
                r4 = 1
                if (r0 == 0) goto L_0x0051
                r0.close()
            L_0x0051:
                return r4
            L_0x0052:
                if (r0 == 0) goto L_0x005c
                r0.close()     // Catch:{ all -> 0x0058 }
                goto L_0x005c
            L_0x0058:
                r5 = move-exception
                r4.addSuppressed(r5)
            L_0x005c:
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataList.MediaDataArray.updateList(int, com.samsung.android.gallery.module.data.MediaItem):boolean");
        }

        public void requestData(String str) {
        }
    }

    public void close() {
    }

    public void register(MediaData.OnDataChangeListener onDataChangeListener) {
    }

    public void unregister(MediaData.OnDataChangeListener onDataChangeListener) {
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
    }
}
