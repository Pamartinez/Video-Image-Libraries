package com.samsung.android.gallery.module.dataset;

import A4.C0375j;
import A8.C0545b;
import Fa.C0571z;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.album.EssentialAlbum;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataAlbum;
import com.samsung.android.gallery.module.dataset.comparator.Comparators;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.UriBuilder;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import ld.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataMxAlbum extends MediaDataNested {
    private static final String[] CHILD_LOCATION_KEY = {"location://sharing/albums"};
    public static final int SCREENSHOT_GROUP_ID = BucketUtils.VirtualBucketHolder.screenShots;
    private final ConcurrentHashMap<String, MediaData> mChildMap = new ConcurrentHashMap<>();
    private final boolean mIsSpecificPathIncluded;
    private final MediaData.OnDataChangeListener mMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            if (MediaDataMxAlbum.this.getDataVersion() > 0) {
                MediaDataMxAlbum.this.notifyChanged();
            } else {
                Log.e(MediaDataMxAlbum.this.TAG, "ChildDataChanged skip. album data not ready");
            }
        }

        public void onDataRangeInserted(int i2, int i7) {
            onDataChanged();
        }
    };
    private final boolean mPickerMode;
    private final boolean mSupportTopAlbum;

    public MediaDataMxAlbum(Blackboard blackboard, String str, boolean z, boolean z3) {
        super(blackboard, str, z, z3);
        boolean z7;
        this.mPickerMode = PickerUtil.isPickerMode(blackboard);
        this.mIsSpecificPathIncluded = PickerUtil.isSpecificPathIncluded(blackboard);
        if (!supportTopAlbum() || !DrawerUtil.supportEssentialAlbumsLayout(this.mBlackboard)) {
            z7 = false;
        } else {
            z7 = true;
        }
        this.mSupportTopAlbum = z7;
        if (supportSharing() && LocationKey.isSharings(CHILD_LOCATION_KEY[0])) {
            BlackboardUtils.publishDataRequest(this.mBlackboard, new UriBuilder("location://sharing/albums").appendArg("sharing_cache_preferred", true).build());
        }
    }

    private Collection<MediaItem> buildMergedNameList(ArrayList<MediaItem> arrayList, HashMap<Integer, FolderItem> hashMap) {
        TimeTickLog timeTickLog = new TimeTickLog();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashMap hashMap2 = new HashMap();
        Iterator<MediaItem> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (next.isMergedAlbum()) {
                ((ArrayList) hashMap2.computeIfAbsent(Integer.valueOf(next.getAlbumID()), new K(10))).addAll(next.getChildList());
            } else if (isMergeNameCandidate(next)) {
                hashSet2.add(next);
            } else {
                hashSet.add(next);
            }
        }
        timeTickLog.tick();
        hashSet2.forEach(new C0600f(3, hashMap2));
        timeTickLog.tick();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        hashMap2.forEach(new S(this, hashMap, hashSet2, atomicInteger, 0));
        timeTickLog.tick();
        hashSet.addAll(hashSet2);
        timeTickLog.tick();
        StringCompat stringCompat = this.TAG;
        C0212a.z(new Object[]{Integer.valueOf(arrayList.size()), atomicInteger, Integer.valueOf(hashSet.size()), timeTickLog}, new StringBuilder("buildMergedNameList"), stringCompat);
        return hashSet;
    }

    private boolean existInFolder(HashMap<Integer, FolderItem> hashMap, MediaItem mediaItem) {
        Iterator<FolderItem> it = hashMap.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                return false;
            }
            FolderItem next = it.next();
            if (!next.isMergedAlbum()) {
                for (MediaItem albumID : next.getAlbumsInFolder()) {
                    if (albumID.getAlbumID() == mediaItem.getAlbumID()) {
                        return true;
                    }
                }
                continue;
            }
        }
    }

    private MediaItem findRecentAlbum(List<MediaItem> list) {
        ArrayList arrayList = new ArrayList();
        for (MediaItem next : list) {
            if (next.isFolder()) {
                arrayList.add(next);
            } else if (next.isVirtualAlbum() && BucketUtils.isRecent(next.getAlbumID())) {
                return next;
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem findRecentAlbum = findRecentAlbum(((MediaItem) it.next()).getChildList());
            if (findRecentAlbum != null) {
                return findRecentAlbum;
            }
        }
        return null;
    }

    private HashSet<Integer> findTopByOrder(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2, int i2) {
        HashSet hashSet = (HashSet) arrayList2.stream().mapToInt(new C0545b(2)).boxed().collect(Collectors.toCollection(new b(7)));
        ArrayList arrayList3 = new ArrayList(arrayList);
        arrayList3.sort(Comparators.getComparator("location://albums", i2));
        HashSet<Integer> hashSet2 = new HashSet<>();
        int max = Math.max(Math.min(arrayList.size(), 9) - arrayList2.size(), 0);
        if (max > 0) {
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                MediaItem mediaItem = (MediaItem) it.next();
                if (!mediaItem.isAlbumHide() && !mediaItem.isFolder() && !hashSet.contains(Integer.valueOf(mediaItem.getAlbumID())) && hashSet2.add(Integer.valueOf(mediaItem.getAlbumID())) && hashSet2.size() >= max) {
                    break;
                }
            }
        }
        return hashSet2;
    }

    private int getAlbumIndex(int i2) {
        return (i2 / 10000000) - 1;
    }

    private MediaData getSpaceData() {
        return (MediaData) Optional.ofNullable(getChildMediaData("location://sharing/albums")).map(new K(13)).orElse((Object) null);
    }

    private boolean isCacheEmpty(Cursor cursor, Cursor[] cursorArr) {
        if (cursor != null) {
            if (cursor.getCount() == 0) {
                return true;
            }
            return false;
        } else if (cursorArr == null || cursorArr.length <= 1 || cursorArr[1].getCount() != 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isInvalidMergeAlbumChildCount(int i2) {
        if (i2 == 1) {
            return true;
        }
        return false;
    }

    private boolean isMergeNameCandidate(MediaItem mediaItem) {
        if (mediaItem.isFolder() || mediaItem.getFolderID() != 0 || AlbumType.isAutoAlbum(mediaItem.getAlbumType().toInt()) || mediaItem.isVirtualAlbum()) {
            return false;
        }
        return true;
    }

    private boolean isMergeNameEnabled() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbumsMergeNames) || this.mIsSpecificPathIncluded) {
            return false;
        }
        return true;
    }

    private boolean isSharings(int i2) {
        if (i2 == 0) {
            return true;
        }
        return false;
    }

    private boolean isShowDefaultAlbum(boolean z, boolean z3) {
        if (z3 || GalleryPreference.getInstance().loadBoolean(PreferenceName.MX_ALBUM_SHOW_DEFAULT_NUM, false)) {
            return true;
        }
        return false;
    }

    private boolean isShowEssentialAlbum(boolean z, int i2) {
        if (z || !EssentialAlbum.getInstance().contains(i2)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$buildMergedNameList$11(Integer num) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$buildMergedNameList$12(Integer num) {
        return new ArrayList();
    }

    /* JADX WARNING: type inference failed for: r12v4, types: [java.lang.Object, java.util.function.ToLongFunction] */
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$buildMergedNameList$14(HashMap hashMap, HashSet hashSet, AtomicInteger atomicInteger, Integer num, ArrayList arrayList) {
        FolderItem folderItem;
        FolderItem folderItem2;
        boolean z;
        long j2;
        boolean z3 = true;
        if (arrayList.size() > 1) {
            MediaItem mediaItem = (MediaItem) hashMap.get(num);
            if (mediaItem instanceof FolderCoverItem) {
                folderItem2 = this.mAlbumDataHelper.createFolderCoverItem((FolderItem) mediaItem, mediaItem.getAlbumCover(), (String) null);
            } else {
                folderItem2 = this.mAlbumDataHelper.createNameMergedItem(num.intValue(), ((MediaItem) arrayList.get(0)).getTitle(), 0);
            }
            setFolderTranslatedName(folderItem2, (MediaItem) arrayList.get(0));
            Iterator it = arrayList.iterator();
            loop0:
            while (true) {
                z = false;
                while (true) {
                    if (!it.hasNext()) {
                        break loop0;
                    }
                    MediaItem mediaItem2 = (MediaItem) it.next();
                    folderItem2.addItem(mediaItem2);
                    hashSet.remove(mediaItem2);
                    if (z || mediaItem2.isAlbumLvFirst()) {
                        z = true;
                    }
                }
            }
            if (z) {
                folderItem2.setAlbumLevel(1);
            }
            if (mediaItem != null) {
                j2 = mediaItem.getAlbumOrder();
            } else {
                j2 = folderItem2.getChildList().stream().mapToLong(new Object()).min().orElse(0);
            }
            folderItem2.setAlbumOrder(j2);
            if (mediaItem != null) {
                MediaItemBuilder.copyCoverData(mediaItem.getAlbumCover(), mediaItem, folderItem2);
            }
            if (mediaItem != null && !mediaItem.isAlbumShowInfo()) {
                z3 = false;
            }
            folderItem2.setAlbumShowInfo(z3);
            hashSet.add(folderItem2);
            atomicInteger.incrementAndGet();
        } else if (isInvalidMergeAlbumChildCount(arrayList.size()) && (folderItem = (FolderItem) hashMap.get(num)) != null && folderItem.isMergedAlbum()) {
            MediaItem first = folderItem.getFirst();
            if (!hashSet.contains(first) && !existInFolder(hashMap, first)) {
                first.setAlbumOrder(folderItem.getAlbumOrder());
                hashSet.add(first);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ FolderItem lambda$cleanUpList$15(MediaItem mediaItem) {
        return (FolderItem) mediaItem;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$2(Object obj, Bundle bundle) {
        recall(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$3(Object obj, Bundle bundle) {
        recall(false);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadTopAlbums$18(ArrayList arrayList, MediaItem mediaItem) {
        mediaItem.setAlbumLevel(1);
        arrayList.add(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaData lambda$onCreate$0(String str) {
        return MediaDataFactory.getInstance(this.mBlackboard).open(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDestroy$1(String str, MediaData mediaData) {
        mediaData.unregister(this.mMediaDataChangeListener);
        mediaData.close();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaItem lambda$read$6(int i2, MediaData mediaData) {
        return mediaData.read(removeAlbumIndex(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaItem lambda$read$7(int i2, MediaData mediaData) {
        return mediaData.read(removeAlbumIndex(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$readAsync$8(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier, MediaData mediaData) {
        mediaData.readAsync(removeAlbumIndex(i2), onDataReadListener, booleanSupplier);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$readAsync$9(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier, MediaData mediaData) {
        mediaData.readAsync(removeAlbumIndex(i2), onDataReadListener, booleanSupplier);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaItem lambda$readCache$4(int i2, MediaData mediaData) {
        return mediaData.readCache(removeAlbumIndex(i2));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$updateMergeNameAlbumForHide$16(Integer num) {
        return new ArrayList();
    }

    private void loadTopAlbums(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2, boolean z, boolean z3) {
        if (!useTopAlbum()) {
            long currentTimeMillis = System.currentTimeMillis();
            arrayList2.addAll(arrayList);
            StringCompat stringCompat = this.TAG;
            C0212a.z(new Object[]{Integer.valueOf(arrayList2.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("loadTopAlbums(FULL)"), stringCompat);
            return;
        }
        TimeTickLog timeTickLog = new TimeTickLog();
        Iterator<MediaItem> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (next.isAlbumLvFirst()) {
                arrayList2.add(next);
            } else if (isShowEssentialAlbum(z3, next.getAlbumID())) {
                arrayList2.add(next);
                next.setAlbumLevel(1);
            }
        }
        timeTickLog.tick();
        if (isShowDefaultAlbum(z3, z)) {
            if (!z3) {
                GalleryPreference.getInstance().removeState(PreferenceName.MX_ALBUM_SHOW_DEFAULT_NUM);
            }
            int size = arrayList2.size();
            HashSet<Integer> findTopByOrder = findTopByOrder(arrayList, arrayList2, 52);
            timeTickLog.tick();
            arrayList.stream().filter(new C0606k(4, findTopByOrder)).forEach(new Q(arrayList2, 0));
            timeTickLog.tick();
            Log.d(this.TAG, "loadTopAlbums(52)", Integer.valueOf(arrayList2.size()), Integer.valueOf(size), Integer.valueOf(findTopByOrder.size()));
        }
        StringCompat stringCompat2 = this.TAG;
        C0212a.z(new Object[]{Integer.valueOf(arrayList2.size()), Boolean.valueOf(z), Boolean.valueOf(z3), timeTickLog}, new StringBuilder("loadTopAlbums"), stringCompat2);
    }

    private MediaItem loadVirtualMediaItem(Cursor cursor, int i2, String str) {
        MediaItem load = MediaItemLoader.load(cursor);
        load.setTitle(str);
        load.setDisplayName(str);
        load.setAlbumID(i2);
        setAlbumDisplayName(load);
        load.setAlbumType(AlbumType.Virtual);
        load.setAlbumLevel(1);
        load.setVolumeName("external_primary");
        return load;
    }

    private int removeAlbumIndex(int i2) {
        return i2 % 10000000;
    }

    private void retrieveMergeAlbumInFolder(List<MediaItem> list, MediaItem mediaItem) {
        for (MediaItem next : mediaItem.getChildItems()) {
            if (next.isFolder()) {
                retrieveMergeAlbumInFolder(list, next);
            } else if (next.isMergedAlbum()) {
                list.add(next);
            }
        }
    }

    private boolean supportSharing() {
        if (this.mPickerMode) {
            return false;
        }
        if (PreferenceFeatures.OneUi8x.REMOVE_SHARED_DRAWER_TAB_MENU || !DrawerUtil.supportDrawerLayout(this.mBlackboard)) {
            return true;
        }
        return false;
    }

    private boolean supportTopAlbum() {
        if (!this.mPickerMode || PreferenceFeatures.EssentialAlbums.isEnabled()) {
            return true;
        }
        return false;
    }

    private void updateRecentAlbum(ArrayList<MediaItem> arrayList) {
        if (PocFeatures.SHOW_VIRTUAL_ALBUMS) {
            long currentTimeMillis = System.currentTimeMillis();
            MediaItem findRecentAlbum = findRecentAlbum(arrayList);
            if (findRecentAlbum != null) {
                int sum = arrayList.stream().mapToInt(new T(0)).sum();
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "updateRecentAlbum" + Logger.vt(Integer.valueOf(findRecentAlbum.getCount()), Integer.valueOf(sum), Long.valueOf(currentTimeMillis)));
                findRecentAlbum.setCount(sum);
                return;
            }
            StringCompat stringCompat2 = this.TAG;
            Log.e(stringCompat2, "updateRecentAlbum failed. no recent album" + Logger.vt(currentTimeMillis));
        }
    }

    private boolean useTopAlbum() {
        if (!this.mSupportTopAlbum || !PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums)) {
            return false;
        }
        return true;
    }

    public boolean checkDataInserted(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        return false;
    }

    public void cleanUpList(ArrayList<MediaItem> arrayList) {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums) || !PocFeatures.SHOW_VIRTUAL_ALBUMS || !DrawerUtil.supportEssentialAlbumsLayout(this.mBlackboard)) {
            arrayList.removeIf(new C0375j(6));
            arrayList.stream().filter(new C0571z(12)).map(new K(9)).forEach(new C0596d(2));
        }
    }

    public ArrayList<MediaItem> createFakeList(Cursor cursor, ArrayList<MediaItem> arrayList, boolean z) {
        ArrayList<MediaItem> createFakeList = super.createFakeList(cursor, arrayList, z);
        if (!z) {
            return createFakeList;
        }
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        loadTopAlbums(createFakeList, arrayList2, isCacheEmpty(cursor, (Cursor[]) null), true);
        this.mBlackboard.publish("data://top/albums/count", Integer.valueOf(arrayList2.size()));
        return arrayList2;
    }

    public ArrayList<MediaItem> createFullList(Cursor[] cursorArr, ArrayList<MediaItem> arrayList) {
        ArrayList<MediaItem> createFullList = super.createFullList(cursorArr, arrayList);
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        loadTopAlbums(createFullList, arrayList2, isCacheEmpty((Cursor) null, cursorArr), false);
        updateRecentAlbum(arrayList);
        return arrayList2;
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("global://setting/albums/mergeAlbums", new W(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/albums/lockedAlbums", new W(this, 1)).setWorkingOnUI());
    }

    public MediaData getChildMediaData(String str) {
        return this.mChildMap.get(str);
    }

    public int getGridTitleHeight(Context context) {
        return 0;
    }

    public boolean isDiffFile(MediaItem mediaItem, MediaItem mediaItem2) {
        if (super.isDiffFile(mediaItem, mediaItem2) || !Objects.equals(mediaItem.getTitle(), mediaItem2.getTitle())) {
            return true;
        }
        return false;
    }

    public boolean isIncludeNameMerged() {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !isMergeNameEnabled() || isHiddenDataIncluded()) {
            return false;
        }
        return true;
    }

    public boolean isNewSystemAlbum(MediaItem mediaItem) {
        if (PreferenceFeatures.EssentialAlbums.isEnabled()) {
            if (BucketUtils.isRecent(mediaItem.getAlbumID()) || BucketUtils.isFavourite(mediaItem.getAlbumID())) {
                return true;
            }
            return false;
        } else if (super.isNewSystemAlbum(mediaItem) || BucketUtils.isRecent(mediaItem.getAlbumID()) || BucketUtils.isFavourite(mediaItem.getAlbumID())) {
            return true;
        } else {
            return false;
        }
    }

    public void loadAutoAlbumData(Cursor[] cursorArr, HashMap<Integer, MediaItem> hashMap) {
        Cursor cursor;
        if (cursorArr.length > 4) {
            cursor = cursorArr[4];
        } else {
            cursor = null;
        }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    MediaItem load = MediaItemLoader.load(cursor);
                    load.setAlbumType(AlbumType.AutoUpdated);
                    load.setVolumeName("external_primary");
                    hashMap.put(Integer.valueOf(load.getAlbumID()), load);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    public void loadMergeNameItem(ArrayList<MediaItem> arrayList, HashMap<Integer, FolderItem> hashMap) {
        if (!isMergeNameEnabled()) {
            Log.i(this.TAG, "loadMergeNameItem skip");
            return;
        }
        Collection<MediaItem> buildMergedNameList = buildMergedNameList(arrayList, hashMap);
        if (!buildMergedNameList.isEmpty()) {
            arrayList.clear();
            arrayList.addAll(buildMergedNameList);
        }
        for (FolderItem next : hashMap.values()) {
            if (next.isFolder() && next.getItemCount() > 0) {
                for (MediaItem next2 : next.getChildList()) {
                    if (next2.isMergedAlbum() && next2.getItemCount() == 0) {
                        next.removeItem(next2);
                    }
                }
            }
        }
    }

    public void loadVirtualAlbumData(Cursor[] cursorArr, HashMap<Integer, MediaItem> hashMap) {
        Cursor cursor;
        if (PocFeatures.SHOW_VIRTUAL_ALBUMS) {
            Cursor cursor2 = null;
            if (cursorArr.length > 2) {
                cursor = cursorArr[2];
            } else {
                cursor = null;
            }
            if (cursorArr.length > 3) {
                cursor2 = cursorArr[3];
            }
            if (cursor == null || !cursor.moveToFirst()) {
                int bucketId = FileUtils.getBucketId("Virtual/Favourites");
                MediaItem createVirtualEmptyItem = this.mAlbumDataHelper.createVirtualEmptyItem(bucketId, "Favorites");
                setAlbumDisplayName(createVirtualEmptyItem);
                hashMap.put(Integer.valueOf(bucketId), createVirtualEmptyItem);
            } else {
                int bucketId2 = FileUtils.getBucketId("Virtual/Favourites");
                hashMap.put(Integer.valueOf(bucketId2), loadVirtualMediaItem(cursor, bucketId2, "Favorites"));
            }
            if (cursor2 == null || !cursor2.moveToFirst()) {
                int bucketId3 = FileUtils.getBucketId("Virtual/Recently");
                MediaItem createVirtualEmptyItem2 = this.mAlbumDataHelper.createVirtualEmptyItem(bucketId3, "Recents");
                setAlbumDisplayName(createVirtualEmptyItem2);
                hashMap.put(Integer.valueOf(bucketId3), createVirtualEmptyItem2);
                return;
            }
            int bucketId4 = FileUtils.getBucketId("Virtual/Recently");
            hashMap.put(Integer.valueOf(bucketId4), loadVirtualMediaItem(cursor2, bucketId4, "Recents"));
        }
    }

    public void onCreate() {
        super.onCreate();
        if (supportSharing()) {
            for (String computeIfAbsent : CHILD_LOCATION_KEY) {
                this.mChildMap.computeIfAbsent(computeIfAbsent, new C0608m(4, this)).register(this.mMediaDataChangeListener);
            }
        }
    }

    public void onDestroy() {
        this.mChildMap.forEach(new l0(this, 2));
        this.mChildMap.clear();
        super.onDestroy();
    }

    public MediaItem read(int i2) {
        int albumIndex = getAlbumIndex(i2);
        if (albumIndex < 0) {
            return super.read(i2);
        }
        if (isSharings(albumIndex)) {
            return (MediaItem) Optional.ofNullable(getSpaceData()).map(new P(this, i2, 0)).orElse((Object) null);
        }
        return (MediaItem) Optional.ofNullable(getChildMediaData(albumIndex)).map(new P(this, i2, 1)).orElse((Object) null);
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        int albumIndex = getAlbumIndex(i2);
        if (albumIndex < 0) {
            super.readAsync(i2, onDataReadListener, booleanSupplier);
        } else if (isSharings(albumIndex)) {
            Optional.ofNullable(getSpaceData()).ifPresent(new U(this, i2, onDataReadListener, booleanSupplier, 0));
        } else {
            MediaData.OnDataReadListener onDataReadListener2 = onDataReadListener;
            int i7 = i2;
            Optional.ofNullable(getChildMediaData(albumIndex)).ifPresent(new U(this, i7, onDataReadListener2, booleanSupplier, 1));
        }
    }

    public MediaItem readCache(int i2) {
        int albumIndex = getAlbumIndex(i2);
        if (albumIndex < 0) {
            return super.readCache(i2);
        }
        if (isSharings(albumIndex)) {
            return (MediaItem) Optional.ofNullable(getSpaceData()).map(new P(this, i2, 2)).orElse((Object) null);
        }
        return (MediaItem) Optional.ofNullable(getChildMediaData(albumIndex)).map(new X(i2)).orElse((Object) null);
    }

    public void recall(boolean z) {
        String str;
        if (z) {
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("recall#query ");
            Cursor[] cursorArr = this.mCursors;
            if (cursorArr != null) {
                str = Logger.toString(cursorArr);
            } else {
                str = "null";
            }
            sb2.append(str);
            Log.d(stringCompat, sb2.toString());
            requestData(this.mLocationReference);
            return;
        }
        StringCompat stringCompat2 = this.TAG;
        Log.d(stringCompat2, "recall#processing " + Logger.toString(this.mCursors));
        processFullLoading(this.mCursors);
    }

    public void resetFolderInfo(MediaItem mediaItem) {
        if (!PreferenceFeatures.MxAlbumsMergeNames.isEnabled()) {
            mediaItem.setFolderInfo(-10, (String) null);
        }
    }

    public void setFolderTranslatedName(FolderItem folderItem, MediaItem mediaItem) {
        if (folderItem.isMergedAlbum()) {
            String albumTitle = AlbumTitleHelper.getAlbumTitle(folderItem.getFolderID());
            if (albumTitle == null) {
                albumTitle = AlbumTitleHelper.getAlbumTitle(mediaItem.getAlbumID());
            }
            folderItem.setTranslatedName(albumTitle);
        }
    }

    public boolean skipNotifyIfCacheEmpty(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2, ArrayList<MediaItem> arrayList3) {
        if (arrayList3.size() != 0 || !super.skipNotifyIfCacheEmpty(arrayList, arrayList2, arrayList3)) {
            return false;
        }
        return true;
    }

    public void updateDataRefresher(HashMap<Integer, FolderItem> hashMap) {
        MediaDataAlbum.DataRefresher dataRefresher = new MediaDataAlbum.DataRefresher(this.mBlackboard);
        for (FolderItem next : hashMap.values()) {
            if (next.isMergedAlbum()) {
                if (isInvalidMergeAlbumChildCount(next.getItemCount())) {
                    dataRefresher.replace(next, next.getFirst());
                } else {
                    dataRefresher.update(next);
                }
            }
        }
        dataRefresher.clear();
    }

    public boolean updateMergeNameAlbumForHide(MediaItem mediaItem) {
        if (isMergeNameEnabled() && (mediaItem.isMergedAlbum() || mediaItem.isFolder())) {
            ArrayList arrayList = new ArrayList();
            if (mediaItem.isFolder()) {
                retrieveMergeAlbumInFolder(arrayList, mediaItem);
            } else {
                arrayList.add(mediaItem);
            }
            HashMap hashMap = new HashMap();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                MediaItem mediaItem2 = (MediaItem) it.next();
                List list = (List) hashMap.computeIfAbsent(Integer.valueOf(mediaItem2.getAlbumID()), new K(11));
                for (MediaItem next : mediaItem2.getChildItems()) {
                    if (next.isAlbumHide()) {
                        list.add(next);
                    }
                }
            }
            if (!mediaItem.isFolder() && mediaItem.getItemCount() == ((Integer) Optional.ofNullable((ArrayList) hashMap.get(Integer.valueOf(mediaItem.getAlbumID()))).map(new L5.b(16)).orElse(0)).intValue()) {
                return true;
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                MediaItem mediaItem3 = (MediaItem) it2.next();
                List<MediaItem> list2 = (List) hashMap.get(Integer.valueOf(mediaItem3.getAlbumID()));
                if (list2 != null) {
                    for (MediaItem removeItem : list2) {
                        ((FolderItem) mediaItem3).removeItem(removeItem);
                    }
                }
            }
        }
        return false;
    }

    public MediaData getChildMediaData(int i2) {
        if (i2 >= 0) {
            String[] strArr = CHILD_LOCATION_KEY;
            if (i2 < strArr.length) {
                return getChildMediaData(strArr[i2]);
            }
        }
        Log.e((CharSequence) this.TAG, "getChildMediaData failed", Integer.valueOf(i2));
        return null;
    }

    public int updateFolderIdByAlbumType(MediaItem mediaItem, int i2) {
        return i2;
    }

    public void loadExtraFolderMap(HashMap<Integer, MediaItem> hashMap, ArrayList<MediaItem> arrayList, HashMap<Integer, FolderItem> hashMap2) {
    }

    public void loadExtraItem(ArrayList<MediaItem> arrayList, HashMap<Integer, MediaItem> hashMap, HashMap<Integer, FolderItem> hashMap2) {
    }
}
