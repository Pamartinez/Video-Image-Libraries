package com.samsung.android.gallery.module.dataset;

import A.a;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.R$dimen;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.data.CloudData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MediaDataAlbum extends MediaDataCursor {
    protected AlbumDataHelper mAlbumDataHelper;
    private final boolean mCacheLoadingEnabled;
    protected ArrayList<MediaItem> mDataArray;
    private ArrayList<MediaItem> mFullDataArray;
    private final boolean mGroupingEnabled;
    private final boolean mHiddenDataIncluded;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DataRefresher {
        private Blackboard mBlackboard;
        private MediaItem mCurrentAlbum;
        private MediaItem mTargetAlbum;

        public DataRefresher(Blackboard blackboard) {
            this.mBlackboard = blackboard;
            this.mCurrentAlbum = (MediaItem) blackboard.read("data://albums/current");
            this.mTargetAlbum = (MediaItem) blackboard.read("data://albums/moveTo/target");
        }

        public void clear() {
            this.mCurrentAlbum = null;
            this.mTargetAlbum = null;
            this.mBlackboard = null;
        }

        public void replace(MediaItem mediaItem, MediaItem mediaItem2) {
            MediaItem mediaItem3 = this.mCurrentAlbum;
            if (mediaItem3 != null && mediaItem3.getAlbumID() == mediaItem.getAlbumID()) {
                this.mBlackboard.publish("data://albums/current", mediaItem2);
            }
            MediaItem mediaItem4 = this.mTargetAlbum;
            if (mediaItem4 != null && mediaItem4.getAlbumID() == mediaItem.getAlbumID()) {
                this.mBlackboard.publish("data://albums/moveTo/target", mediaItem2);
            }
        }

        public void update(MediaItem mediaItem) {
            MediaItem mediaItem2 = this.mCurrentAlbum;
            if (mediaItem2 != null && mediaItem2.getAlbumID() == mediaItem.getAlbumID()) {
                this.mBlackboard.publish("data://albums/current", mediaItem);
            }
            MediaItem mediaItem3 = this.mTargetAlbum;
            if (mediaItem3 != null && mediaItem3.getAlbumID() == mediaItem.getAlbumID()) {
                this.mBlackboard.publish("data://albums/moveTo/target", mediaItem);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UniqueKeyImpl implements UniqueKey {
        final int hashKey;

        public UniqueKeyImpl(FileItemInterface fileItemInterface) {
            this.hashKey = fileItemInterface.getSimpleHashCode();
        }

        public int getKey() {
            return this.hashKey;
        }

        public boolean preferAndroid() {
            return true;
        }
    }

    public MediaDataAlbum(Blackboard blackboard, String str, boolean z, boolean z3, boolean z7) {
        super(blackboard, str);
        this.mGroupingEnabled = z7 ? false : z;
        this.mCacheLoadingEnabled = z3;
        this.mHiddenDataIncluded = z7;
        this.mAlbumDataHelper = new AlbumDataHelper(str);
    }

    private void checkInvalidFolders(ArrayList<MediaItem> arrayList) {
        HashMap hashMap = new HashMap();
        Iterator<MediaItem> it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (next.isFolder()) {
                FolderItem folderItem = (FolderItem) next;
                if (folderItem.getItemCount() <= 1) {
                    hashMap.put(Integer.valueOf(i2), folderItem);
                }
            }
            i2++;
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            FolderItem folderItem2 = (FolderItem) entry.getValue();
            arrayList.remove(intValue);
            if (folderItem2.getItemCount() == 1) {
                arrayList.add(intValue, folderItem2.getFirst());
            }
        }
    }

    private void fakeLoading(Cursor cursor, ArrayList<MediaItem> arrayList, HashMap<Integer, MediaItem> hashMap) {
        if (cursor != null) {
            HashMap hashMap2 = new HashMap();
            if (cursor.moveToFirst()) {
                do {
                    int i2 = cursor.getInt(cursor.getColumnIndex("folder_id"));
                    String string = cursor.getString(cursor.getColumnIndex("folder_name"));
                    int i7 = cursor.getInt(cursor.getColumnIndex("__bucketID"));
                    long j2 = cursor.getLong(cursor.getColumnIndex("album_order"));
                    MediaItem mediaItem = hashMap.get(Integer.valueOf(i7));
                    if (mediaItem != null) {
                        String string2 = cursor.getString(cursor.getColumnIndex("cover_path"));
                        if (!TextUtils.isEmpty(string2)) {
                            mediaItem = AlbumDataHelper.createCoverItem(mediaItem, string2, cursor.getString(cursor.getColumnIndex("cover_rect")));
                        }
                        if (!isGroupingEnabled() || i2 == 0) {
                            arrayList.add(mediaItem);
                        } else {
                            FolderItem folderItem = (FolderItem) hashMap2.get(Integer.valueOf(i2));
                            if (folderItem != null) {
                                folderItem.addItem(mediaItem);
                                MediaItemBuilder.updateAlbumOrder(mediaItem, folderItem.getAlbumOrder());
                                folderItem.setFolderFirstFileId(folderItem.getFirst().getFileId());
                            } else {
                                FolderItem createFolderItem = this.mAlbumDataHelper.createFolderItem(i2, string, j2);
                                createFolderItem.addItem(mediaItem);
                                hashMap2.put(Integer.valueOf(i2), createFolderItem);
                                arrayList.add(createFolderItem);
                            }
                        }
                    }
                } while (cursor.moveToNext());
                checkInvalidFolders(arrayList);
            }
            arrayList.sort(this.mAlbumDataHelper.getComparator());
        }
    }

    private int getDrawerWidth(Context context) {
        Resources resources;
        int i2;
        if (!DrawerUtil.supportDrawerLayout(context)) {
            return 0;
        }
        if (((Boolean) this.mBlackboard.read("data://drawer_opened", Boolean.valueOf(DrawerUtil.isDrawerDefaultOpen(context)))).booleanValue()) {
            resources = context.getResources();
            i2 = R$dimen.drawer_tab_layout_width;
        } else {
            resources = context.getResources();
            i2 = R$dimen.drawer_collapsed_width;
        }
        return resources.getDimensionPixelOffset(i2);
    }

    private int getGridSize(Context context, int i2) {
        if (i2 == 1) {
            return context.getResources().getDimensionPixelSize(R$dimen.list_album_item_height);
        }
        return ((ResourceCompat.getWindowWidth(context) - getDrawerWidth(context)) / i2) + getGridTitleHeight(context);
    }

    private int[] getListViewInfo(Context context) {
        if (context != null) {
            try {
                GridHelper gridHelper = getGridHelper();
                int gridDepth = gridHelper.getGridDepth();
                int columnSize = gridHelper.getColumnSize(context, gridDepth);
                if (columnSize > 1 && isDrawerOpened(context)) {
                    columnSize--;
                }
                return new int[]{gridDepth, columnSize, getGridSize(context, columnSize)};
            } catch (Exception e) {
                a.r(e, new StringBuilder("getListViewInfo failed e="), this.TAG);
            }
        }
        return new int[]{1, 2, ThumbKind.MEDIUM_KIND.size()};
    }

    private int getPreloadCount(Context context, int i2, int i7, int i8) {
        try {
            return getRowSize(context, i8) * i7;
        } catch (Exception e) {
            a.r(e, new StringBuilder("getPreloadCount failed e="), this.TAG);
            return 9;
        }
    }

    private int getRowSize(Context context, int i2) {
        return (int) Math.ceil((double) (((float) (((ResourceCompat.getWindowHeight(context) - DeviceInfo.getStatusBarHeight()) - context.getResources().getDimensionPixelOffset(R$dimen.bottom_tab_floating_height)) - context.getResources().getDimensionPixelOffset(R$dimen.toolbar_height))) / ((float) i2)));
    }

    private void insertSystemAlbumToTop(ArrayList<MediaItem> arrayList, long j2, ArrayList<MediaItem> arrayList2) {
        long size = j2 / ((long) (arrayList2.size() + 1));
        long j3 = 1;
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            MediaItem mediaItem = arrayList2.get(i2);
            arrayList.add(0, mediaItem);
            MediaItemBuilder.updateAlbumOrder(mediaItem, j3 * size);
            j3++;
        }
    }

    private boolean isAlbumFirstTabLoading() {
        if (this.mBlackboard.pop("shortcut_album_loading") != null || !LocationKey.isAlbumsMatch(LocationKey.getCurrentLocation())) {
            return false;
        }
        return true;
    }

    private boolean isAlbumHide(MediaItem mediaItem) {
        if (!mediaItem.isAlbumHide() || BucketUtils.contains(mediaItem.getAlbumID())) {
            return false;
        }
        return true;
    }

    private boolean isCacheEmpty(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        if (arrayList == null && arrayList2.size() == 0) {
            return true;
        }
        return false;
    }

    private boolean isCacheLoadingEnabled() {
        return this.mCacheLoadingEnabled;
    }

    private boolean isDiffAlbum(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem.isCustomCover() == mediaItem2.isCustomCover() && mediaItem.getAlbumID() == mediaItem2.getAlbumID() && mediaItem.getCount() == mediaItem2.getCount() && mediaItem.getAlbumOrder() == mediaItem2.getAlbumOrder() && mediaItem.isEmptyAlbum() == mediaItem2.isEmptyAlbum() && CloudData.of(mediaItem).albumSyncStatus == CloudData.of(mediaItem2).albumSyncStatus) {
            return false;
        }
        return true;
    }

    private boolean isDiffFolder(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!mediaItem.isFolder() && !mediaItem2.isFolder()) {
            return false;
        }
        if ((mediaItem.isFolder() && !mediaItem2.isFolder()) || (!mediaItem.isFolder() && mediaItem2.isFolder())) {
            return true;
        }
        List asList = Arrays.asList(mediaItem.getItemsInFolder());
        List asList2 = Arrays.asList(mediaItem2.getItemsInFolder());
        if (mediaItem.getFolderID() != mediaItem2.getFolderID() || !equalLists(asList, asList2)) {
            return true;
        }
        return false;
    }

    private boolean isDiffGrouped(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!mediaItem.isMergedAlbum() && !mediaItem2.isMergedAlbum()) {
            return false;
        }
        if ((mediaItem.isMergedAlbum() && !mediaItem2.isMergedAlbum()) || (!mediaItem.isMergedAlbum() && mediaItem2.isMergedAlbum())) {
            return true;
        }
        List asList = Arrays.asList(mediaItem.getItemsInFolder());
        List asList2 = Arrays.asList(mediaItem2.getItemsInFolder());
        if (mediaItem.getFolderID() != mediaItem2.getFolderID() || !equalLists(asList, asList2)) {
            return true;
        }
        return false;
    }

    private boolean isDrawerOpened(Context context) {
        if (!DrawerUtil.supportDrawerLayout(context) || !((Boolean) this.mBlackboard.read("data://drawer_opened", Boolean.valueOf(DrawerUtil.isDrawerDefaultOpen(context)))).booleanValue()) {
            return false;
        }
        return true;
    }

    private boolean isGroupingEnabled() {
        return this.mGroupingEnabled;
    }

    private boolean isSame(MediaItem mediaItem, MediaItem mediaItem2) {
        if (isDiffAlbum(mediaItem, mediaItem2) || isDiffFile(mediaItem, mediaItem2) || isDiffFolder(mediaItem, mediaItem2) || isDiffGrouped(mediaItem, mediaItem2)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preloadThumbnail$10(MediaItem[] mediaItemArr, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        setBroken(mediaItemArr[0], bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preloadThumbnail$11(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        setBroken(mediaItem, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preloadThumbnail$12(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        setBroken(mediaItem, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$processCacheLoading$0(ArrayList arrayList, ArrayList arrayList2, boolean z, long j2) {
        swapInternal(arrayList, arrayList2);
        if (z) {
            notifyChanged();
        }
        this.mLock.releaseWriteLock();
        StringCompat stringCompat = this.TAG;
        Log.w(stringCompat, "swap {cache," + this.mLocationKey + ",changed=" + z + ",count=" + arrayList.size() + "} +" + (System.currentTimeMillis() - j2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$processFullLoading$2(ArrayList arrayList, ArrayList arrayList2, boolean z, boolean z3, int i2, int i7, long j2) {
        swapInternal(arrayList, arrayList2);
        if (z) {
            if (z3) {
                notifyDataRangeInserted(i2, i7 - i2);
            } else {
                notifyChanged();
            }
        }
        this.mLock.releaseWriteLock();
        if (!LocationKey.isAlbumHide(this.mLocationKey)) {
            this.mBlackboard.publish("album_data_swapped", Boolean.valueOf(z));
        }
        StringCompat stringCompat = this.TAG;
        Log.w(stringCompat, "swap {full," + this.mLocationKey + ",changed=" + z + ",inserted=" + z3 + ",count=" + arrayList.size() + "} +" + (System.currentTimeMillis() - j2));
        SimpleThreadPool.getInstance().execute(new C0605j(arrayList2, 0));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$reuseCachedData$3(MediaItem mediaItem) {
        if (!mediaItem.isBroken() || mediaItem.getCount() <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$reuseCachedData$5(HashMap hashMap, MediaItem mediaItem) {
        if (mediaItem.isFolder()) {
            mediaItem.getChildAlbums().stream().filter(new C0598e(0)).forEach(new C0600f(0, hashMap));
        } else if (mediaItem.isBroken() && mediaItem.getCount() > 0) {
            hashMap.put(mediaItem.getThumbCacheKey(), mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$reuseCachedData$6(HashMap hashMap, MediaItem mediaItem) {
        if (mediaItem.getCount() <= 0 || !hashMap.containsKey(mediaItem.getThumbCacheKey())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$reuseCachedData$8(HashMap hashMap, MediaItem mediaItem) {
        if (mediaItem.getRecordingMode() == 29) {
            return;
        }
        if (mediaItem.isFolder()) {
            mediaItem.getChildAlbums().stream().filter(new C0606k(0, hashMap)).forEach(new C0596d(0));
        } else if (mediaItem.getCount() > 0 && hashMap.containsKey(mediaItem.getThumbCacheKey())) {
            mediaItem.setBroken(true);
        }
    }

    /* access modifiers changed from: private */
    public void onCacheDataLoaded(Object obj, Bundle bundle) {
        onDataCursorChanged(obj, bundle);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: com.samsung.android.gallery.module.dataset.FolderItem} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: com.samsung.android.gallery.module.data.MediaUnit} */
    /* JADX WARNING: type inference failed for: r7v3, types: [com.samsung.android.gallery.module.data.MediaItem[], java.lang.Cloneable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void preloadThumbnail(java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem> r15) {
        /*
            r14 = this;
            java.lang.String r0 = ","
            java.lang.String r1 = "preloadThumbnail INFO{"
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = r14.mBlackboard
            long r3 = java.lang.System.currentTimeMillis()
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            java.lang.String r4 = "lifecycle://on_thumbnail_load_start"
            r2.publishIfEmpty(r4, r3)
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r2 = com.samsung.android.gallery.module.thumbnail.ThumbnailLoader.getInstance()     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r3 = com.samsung.android.gallery.module.thumbnail.type.ThumbKind.MEDIUM_KIND     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.support.blackboard.Blackboard r4 = r14.mBlackboard     // Catch:{ NullPointerException -> 0x00c2 }
            android.app.Activity r4 = com.samsung.android.gallery.module.utils.BlackboardUtils.readActivity((com.samsung.android.gallery.support.blackboard.Blackboard) r4)     // Catch:{ NullPointerException -> 0x00c2 }
            int[] r5 = r14.getListViewInfo(r4)     // Catch:{ NullPointerException -> 0x00c2 }
            r6 = 0
            r7 = r5[r6]     // Catch:{ NullPointerException -> 0x00c2 }
            r8 = 1
            r8 = r5[r8]     // Catch:{ NullPointerException -> 0x00c2 }
            r9 = 2
            r5 = r5[r9]     // Catch:{ NullPointerException -> 0x00c2 }
            int r4 = r14.getPreloadCount(r4, r7, r8, r5)     // Catch:{ NullPointerException -> 0x00c2 }
            int r9 = r15.size()     // Catch:{ NullPointerException -> 0x00c2 }
            int r9 = java.lang.Math.min(r9, r4)     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.support.blackboard.Blackboard r10 = r14.mBlackboard     // Catch:{ NullPointerException -> 0x00c2 }
            java.lang.String r11 = "data://preload_count"
            java.lang.Integer r12 = java.lang.Integer.valueOf(r9)     // Catch:{ NullPointerException -> 0x00c2 }
            r10.publish(r11, r12)     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.support.utils.StringCompat r10 = r14.TAG     // Catch:{ NullPointerException -> 0x00c2 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x00c2 }
            r11.<init>(r1)     // Catch:{ NullPointerException -> 0x00c2 }
            r11.append(r3)     // Catch:{ NullPointerException -> 0x00c2 }
            java.lang.String r1 = "#"
            r11.append(r1)     // Catch:{ NullPointerException -> 0x00c2 }
            r11.append(r9)     // Catch:{ NullPointerException -> 0x00c2 }
            r11.append(r0)     // Catch:{ NullPointerException -> 0x00c2 }
            r11.append(r7)     // Catch:{ NullPointerException -> 0x00c2 }
            r11.append(r0)     // Catch:{ NullPointerException -> 0x00c2 }
            r11.append(r8)     // Catch:{ NullPointerException -> 0x00c2 }
            r11.append(r0)     // Catch:{ NullPointerException -> 0x00c2 }
            r11.append(r5)     // Catch:{ NullPointerException -> 0x00c2 }
            r11.append(r0)     // Catch:{ NullPointerException -> 0x00c2 }
            r11.append(r4)     // Catch:{ NullPointerException -> 0x00c2 }
            java.lang.String r0 = "}"
            r11.append(r0)     // Catch:{ NullPointerException -> 0x00c2 }
            java.lang.String r0 = r11.toString()     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.support.utils.Log.d(r10, r0)     // Catch:{ NullPointerException -> 0x00c2 }
            r0 = r6
        L_0x007b:
            if (r0 >= r9) goto L_0x00ee
            java.lang.Object r1 = r15.get(r0)     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.module.data.MediaItem r1 = (com.samsung.android.gallery.module.data.MediaItem) r1     // Catch:{ NullPointerException -> 0x00c2 }
            if (r1 == 0) goto L_0x00eb
            boolean r4 = r1.isFolder()     // Catch:{ NullPointerException -> 0x00c2 }
            if (r4 == 0) goto L_0x00dd
            com.samsung.android.gallery.module.data.MediaItem[] r1 = r1.getItemsInFolder()     // Catch:{ NullPointerException -> 0x00c2 }
            int r4 = r1.length     // Catch:{ NullPointerException -> 0x00c2 }
            r5 = 4
            int r4 = java.lang.Math.min(r4, r5)     // Catch:{ NullPointerException -> 0x00c2 }
            r5 = r6
        L_0x0096:
            if (r5 >= r4) goto L_0x00eb
            r7 = r1[r5]     // Catch:{ NullPointerException -> 0x00c2 }
            boolean r8 = r7.isFolder()     // Catch:{ NullPointerException -> 0x00c2 }
            if (r8 == 0) goto L_0x00c4
            com.samsung.android.gallery.module.data.MediaItem[] r7 = r7.getAlbumsInFolder()     // Catch:{ NullPointerException -> 0x00c2 }
            int r8 = r7.length     // Catch:{ NullPointerException -> 0x00c2 }
            if (r8 <= 0) goto L_0x00da
            r8 = r7[r6]     // Catch:{ NullPointerException -> 0x00c2 }
            boolean r10 = r8.isCustomCover()     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r10 = com.samsung.android.gallery.module.thumbnail.type.ThumbKind.calculateChildInFolder(r4, r5, r10)     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.module.dataset.MediaDataAlbum$UniqueKeyImpl r11 = new com.samsung.android.gallery.module.dataset.MediaDataAlbum$UniqueKeyImpl     // Catch:{ NullPointerException -> 0x00c2 }
            r12 = r7[r6]     // Catch:{ NullPointerException -> 0x00c2 }
            r11.<init>(r12)     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.module.dataset.i r12 = new com.samsung.android.gallery.module.dataset.i     // Catch:{ NullPointerException -> 0x00c2 }
            r13 = 2
            r12.<init>(r14, r7, r13)     // Catch:{ NullPointerException -> 0x00c2 }
            r2.loadThumbnail(r8, r10, r11, r12)     // Catch:{ NullPointerException -> 0x00c2 }
            goto L_0x00da
        L_0x00c2:
            r15 = move-exception
            goto L_0x00ef
        L_0x00c4:
            boolean r8 = r7.isCustomCover()     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r8 = com.samsung.android.gallery.module.thumbnail.type.ThumbKind.calculateChildInFolder(r4, r5, r8)     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.module.dataset.MediaDataAlbum$UniqueKeyImpl r10 = new com.samsung.android.gallery.module.dataset.MediaDataAlbum$UniqueKeyImpl     // Catch:{ NullPointerException -> 0x00c2 }
            r10.<init>(r7)     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.module.dataset.i r11 = new com.samsung.android.gallery.module.dataset.i     // Catch:{ NullPointerException -> 0x00c2 }
            r12 = 0
            r11.<init>(r14, r7, r12)     // Catch:{ NullPointerException -> 0x00c2 }
            r2.loadThumbnail(r7, r8, r10, r11)     // Catch:{ NullPointerException -> 0x00c2 }
        L_0x00da:
            int r5 = r5 + 1
            goto L_0x0096
        L_0x00dd:
            com.samsung.android.gallery.module.dataset.MediaDataAlbum$UniqueKeyImpl r4 = new com.samsung.android.gallery.module.dataset.MediaDataAlbum$UniqueKeyImpl     // Catch:{ NullPointerException -> 0x00c2 }
            r4.<init>(r1)     // Catch:{ NullPointerException -> 0x00c2 }
            com.samsung.android.gallery.module.dataset.i r5 = new com.samsung.android.gallery.module.dataset.i     // Catch:{ NullPointerException -> 0x00c2 }
            r7 = 1
            r5.<init>(r14, r1, r7)     // Catch:{ NullPointerException -> 0x00c2 }
            r2.loadThumbnail(r1, r3, r4, r5)     // Catch:{ NullPointerException -> 0x00c2 }
        L_0x00eb:
            int r0 = r0 + 1
            goto L_0x007b
        L_0x00ee:
            return
        L_0x00ef:
            com.samsung.android.gallery.support.utils.StringCompat r14 = r14.TAG
            java.lang.String r0 = "preloadThumbnail failed"
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r14, (java.lang.String) r0, (java.lang.Throwable) r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataAlbum.preloadThumbnail(java.util.ArrayList):void");
    }

    private void processCacheLoading(Cursor[] cursorArr) {
        Exception exc;
        MediaDataAlbum mediaDataAlbum;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            ArrayList<MediaItem> createFakeList = createFakeList(cursorArr[1], arrayList, true);
            if (skipNotifyIfCacheEmpty(this.mDataArray, createFakeList, arrayList)) {
                try {
                    Log.e((CharSequence) this.TAG, "processCacheLoading skip notifying with empty cache", Integer.valueOf(createFakeList.size()), Integer.valueOf(arrayList.size()));
                } catch (Exception e) {
                    exc = e;
                    mediaDataAlbum = this;
                    exc.printStackTrace();
                    mediaDataAlbum.mLock.releaseWriteLock();
                }
            } else {
                cleanUpList(createFakeList);
                boolean z = !equalLists(this.mDataArray, createFakeList);
                if (this.mLock.acquireWriteLock()) {
                    mediaDataAlbum = this;
                    try {
                        mediaDataAlbum.runOnUiThread(new C0602g(mediaDataAlbum, createFakeList, arrayList, z, currentTimeMillis));
                    } catch (Exception e7) {
                        e = e7;
                        exc = e;
                        exc.printStackTrace();
                        mediaDataAlbum.mLock.releaseWriteLock();
                    }
                } else {
                    mediaDataAlbum = this;
                }
                mediaDataAlbum.preloadThumbnail(createFakeList);
            }
        } catch (Exception e8) {
            e = e8;
            mediaDataAlbum = this;
            exc = e;
            exc.printStackTrace();
            mediaDataAlbum.mLock.releaseWriteLock();
        }
    }

    private void setBroken(MediaItem mediaItem, Bitmap bitmap) {
        boolean z;
        if (bitmap != null || mediaItem.getCount() <= 0) {
            z = false;
        } else {
            z = true;
        }
        mediaItem.setBroken(z);
    }

    private void swapInternal(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        this.mDataArray = arrayList;
        this.mDataCount = arrayList.size();
        this.mFullDataArray = arrayList2;
    }

    private void updateDataForHide(ArrayList<MediaItem> arrayList) {
        if (!isHiddenDataIncluded()) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<MediaItem> it = arrayList.iterator();
            while (it.hasNext()) {
                MediaItem next = it.next();
                if (!next.isFolder() && isAlbumHide(next)) {
                    arrayList2.add(next);
                } else if (updateMergeNameAlbumForHide(next)) {
                    arrayList2.add(next);
                }
            }
            if (!arrayList2.isEmpty()) {
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "hide album count=" + arrayList2.size());
            }
            arrayList.removeAll(arrayList2);
        } else {
            ArrayList arrayList3 = new ArrayList();
            Iterator<MediaItem> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                MediaItem next2 = it2.next();
                if (next2.isFolder()) {
                    arrayList3.add(next2);
                }
            }
            arrayList.removeAll(arrayList3);
        }
        cleanUpList(arrayList);
    }

    public void addAllNewAlbums(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        long[] jArr = new long[2];
        this.mAlbumDataHelper.findMinMaxOrder(arrayList, jArr);
        long j2 = jArr[0];
        long j3 = jArr[1];
        ArrayList arrayList3 = new ArrayList();
        Iterator<MediaItem> it = arrayList2.iterator();
        long j8 = 1;
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (!isNewSystemAlbum(next)) {
                arrayList.add(next);
                MediaItemBuilder.updateAlbumOrder(next, (1000000000 * j8) + j3);
            } else {
                arrayList3.add(next);
            }
            j8++;
        }
        insertSystemAlbumToTop(arrayList, j2, arrayList3);
    }

    public boolean checkDataInserted(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        if (arrayList == null || arrayList.size() > arrayList2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (!isSame(arrayList.get(i2), arrayList2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<MediaItem> checkMissingItemsInFilesTable(HashMap<Integer, MediaItem> hashMap, ArrayList<MediaItem> arrayList) {
        HashSet hashSet = new HashSet();
        Iterator<MediaItem> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (next.isFolder() || next.isMergedAlbum()) {
                MediaItem[] albumsInFolder = next.getAlbumsInFolder();
                if (albumsInFolder != null) {
                    for (MediaItem albumID : albumsInFolder) {
                        hashSet.add(Integer.valueOf(albumID.getAlbumID()));
                    }
                    hashSet.add(Integer.valueOf(next.getFolderID()));
                }
            } else {
                hashSet.add(Integer.valueOf(next.getAlbumID()));
            }
        }
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        for (Map.Entry next2 : hashMap.entrySet()) {
            if (!hashSet.contains(next2.getKey())) {
                arrayList2.add((MediaItem) next2.getValue());
            }
        }
        return arrayList2;
    }

    public ArrayList<MediaItem> createFakeList(Cursor cursor, ArrayList<MediaItem> arrayList, boolean z) {
        HashMap hashMap = new HashMap();
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        try {
            fakeLoadingMap(cursor, hashMap);
            fakeLoading(cursor, arrayList2, hashMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayList.addAll(arrayList2);
        return arrayList2;
    }

    public long createFolderAt(int i2, MediaItem mediaItem, int i7, String str) {
        return this.mAlbumDataHelper.createFolderAt(this.mDataArray, i2, mediaItem, i7, str);
    }

    public abstract ArrayList<MediaItem> createFullList(Cursor[] cursorArr, ArrayList<MediaItem> arrayList);

    public int createNewItem(String str, String str2, int i2, String str3, int i7) {
        if (!TextUtils.isEmpty(str3)) {
            return -1;
        }
        AlbumDataHelper albumDataHelper = this.mAlbumDataHelper;
        ArrayList<MediaItem> arrayList = this.mDataArray;
        int createNewItem = albumDataHelper.createNewItem(arrayList, str, str2, albumDataHelper.isOrderUpdated(arrayList), i7);
        this.mDataCount++;
        return createNewItem;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo(DataKey.CACHED_DATA_CURSOR(this.mLocationKey), new C0594c(this, 0)).setTriggerPreloadedData().setWorkingCurrent());
    }

    public boolean equalLists(List<MediaItem> list, List<MediaItem> list2) {
        if (list == null || list.size() != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!isSame(list.get(i2), list2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public final void fakeLoadingMap(Cursor cursor, HashMap<Integer, MediaItem> hashMap) {
        if (cursor != null && cursor.moveToFirst()) {
            do {
                MediaItem createAlbumCover = MediaItemBuilder.createAlbumCover(cursor);
                setAlbumDisplayName(createAlbumCover);
                hashMap.put(Integer.valueOf(createAlbumCover.getAlbumID()), createAlbumCover);
            } while (cursor.moveToNext());
        }
    }

    public ArrayList<MediaItem> getAllData() {
        return this.mDataArray;
    }

    public ArrayList<MediaItem> getFullData() {
        return this.mFullDataArray;
    }

    public int getGridTitleHeight(Context context) {
        return context.getResources().getDimensionPixelSize(R$dimen.album_grid_count_text_size) + context.getResources().getDimensionPixelSize(R$dimen.album_grid_title_text_size);
    }

    public void insertItemAt(int i2, MediaItem mediaItem) {
        AlbumDataHelper albumDataHelper = this.mAlbumDataHelper;
        ArrayList<MediaItem> arrayList = this.mDataArray;
        albumDataHelper.insertItemAt(arrayList, i2, mediaItem, albumDataHelper.isOrderUpdated(arrayList));
        this.mDataCount = this.mDataArray.size();
    }

    public boolean isDataAvailable() {
        if (this.mDataArray == null || this.mDataCount < 0) {
            return false;
        }
        return true;
    }

    public boolean isDiffFile(MediaItem mediaItem, MediaItem mediaItem2) {
        String path = mediaItem2.getPath();
        if (mediaItem.getFileId() != mediaItem2.getFileId()) {
            return true;
        }
        if ((path == null || (mediaItem.getPath() != null && path.equals(mediaItem.getPath()))) && mediaItem2.getWidthInDB() == mediaItem.getWidthInDB() && mediaItem2.getHeightInDB() == mediaItem.getHeightInDB() && mediaItem2.getDateModified() == mediaItem.getDateModified() && mediaItem2.getSefFileType() == mediaItem.getSefFileType() && mediaItem2.isDrm() == mediaItem.isDrm() && mediaItem2.getFileDuration() == mediaItem.getFileDuration() && mediaItem2.getOrientation() == mediaItem.getOrientation() && mediaItem2.getCloudOriginalSize() == mediaItem.getCloudOriginalSize()) {
            return false;
        }
        return true;
    }

    public boolean isEmptyAlbumData(String str, int i2, int i7) {
        if (str == null || !str.endsWith("!$&Welcome@#Image.jpg") || (!FileUtils.isInExternalDir(str) && !FileUtils.isInRemovableStorage(str))) {
            return false;
        }
        if (this.mHiddenDataIncluded) {
            if (i2 == 0 && i7 == 0) {
                return true;
            }
            return false;
        } else if (i2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isHiddenDataIncluded() {
        return this.mHiddenDataIncluded;
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        if (super.isListeningEvent(eventMessage) || eventMessage.what == 104) {
            return true;
        }
        return false;
    }

    public boolean isNewSystemAlbum(MediaItem mediaItem) {
        return BucketUtils.isCameras(mediaItem.getAlbumID());
    }

    public boolean isValidPosition(int i2) {
        ArrayList<MediaItem> arrayList = this.mDataArray;
        if (arrayList == null || i2 < 0 || i2 >= arrayList.size()) {
            return false;
        }
        return true;
    }

    public void onDestroy() {
        ArrayList<MediaItem> arrayList = this.mDataArray;
        if (arrayList != null) {
            arrayList.clear();
            this.mDataArray = null;
        }
        ArrayList<MediaItem> arrayList2 = this.mFullDataArray;
        if (arrayList2 != null) {
            arrayList2.clear();
            this.mFullDataArray = null;
        }
        super.onDestroy();
    }

    public MediaData open(String str, boolean z) {
        LaunchIntent launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
        boolean z3 = this.mCacheLoadingEnabled;
        if (!z3 || (z3 && (!isAlbumFirstTabLoading() || (launchIntent != null && (launchIntent.isFromBixby() || launchIntent.isAlbumMultiPick() || launchIntent.isQuickShortcut()))))) {
            return super.open(str, z);
        }
        if (!z) {
            setLocationKey(str);
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "open {" + this.mLocationKey + GlobalPostProcInternalPPInterface.SPLIT_REGEX + (this.mRefCount.get() + 1) + "}");
        if (this.mRefCount.getAndIncrement() == 0) {
            onCreate();
        }
        return this;
    }

    public final void preloadMap(Cursor cursor, HashMap<Integer, MediaItem> hashMap) {
        if (cursor != null && !cursor.isClosed() && cursor.moveToFirst()) {
            do {
                MediaItem load = MediaItemLoader.load(cursor);
                setAlbumDisplayName(load);
                hashMap.put(Integer.valueOf(load.getAlbumID()), load);
            } while (cursor.moveToNext());
        }
    }

    public void processFullLoading(Cursor[] cursorArr) {
        Exception exc;
        MediaDataAlbum mediaDataAlbum;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            ArrayList<MediaItem> createFullList = createFullList(cursorArr, arrayList);
            boolean z = true;
            if (isCacheLoadingEnabled()) {
                try {
                    ArrayList arrayList2 = new ArrayList(arrayList);
                    ArrayList arrayList3 = new ArrayList();
                    createFakeList(cursorArr[1], arrayList3, false);
                    this.mBlackboard.publish("local_db_update_data", new Object[]{arrayList3, arrayList2});
                } catch (Exception e) {
                    exc = e;
                    mediaDataAlbum = this;
                }
            }
            updateDataForHide(createFullList);
            if (arrayList.size() != createFullList.size()) {
                Log.d(this.TAG, "updateHide for full list");
                updateDataForHide(arrayList);
            }
            updateClusterData(createFullList);
            reuseCachedData(this.mDataArray, createFullList);
            if (this.mLock.acquireWriteLock()) {
                if (equalLists(this.mDataArray, createFullList)) {
                    if (equalLists(this.mFullDataArray, arrayList)) {
                        z = false;
                    }
                }
                mediaDataAlbum = this;
                try {
                    mediaDataAlbum.runOnUiThread(new C0603h(mediaDataAlbum, createFullList, arrayList, z, checkDataInserted(this.mDataArray, createFullList), this.mDataCount, createFullList.size(), currentTimeMillis));
                    if (!mediaDataAlbum.mCacheLoadingEnabled) {
                        mediaDataAlbum.preloadThumbnail(createFullList);
                    }
                } catch (Exception e7) {
                    e = e7;
                    exc = e;
                    exc.printStackTrace();
                    mediaDataAlbum.mLock.releaseWriteLock();
                }
            }
        } catch (Exception e8) {
            e = e8;
            mediaDataAlbum = this;
            exc = e;
            exc.printStackTrace();
            mediaDataAlbum.mLock.releaseWriteLock();
        }
    }

    public MediaItem read(int i2) {
        if (isValidPosition(i2)) {
            return this.mDataArray.get(i2);
        }
        return null;
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        MediaItem mediaItem;
        if (isValidPosition(i2)) {
            mediaItem = this.mDataArray.get(i2);
        } else {
            mediaItem = null;
        }
        onDataReadListener.onDataReadCompleted(mediaItem);
    }

    public MediaItem readCache(int i2) {
        if (isValidPosition(i2)) {
            return this.mDataArray.get(i2);
        }
        return null;
    }

    public void removeItemAt(int i2) {
        this.mAlbumDataHelper.removeItemAt(this.mDataArray, i2);
        this.mDataCount = this.mDataArray.size();
    }

    public void reorderData(int i2, int i7) {
        AlbumDataHelper albumDataHelper = this.mAlbumDataHelper;
        ArrayList<MediaItem> arrayList = this.mDataArray;
        albumDataHelper.reorderData(arrayList, i2, i7, albumDataHelper.isOrderUpdated(arrayList));
    }

    public void reuseCachedData(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        if (arrayList != null && arrayList.size() > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            HashMap hashMap = new HashMap();
            arrayList.forEach(new C0600f(1, hashMap));
            arrayList2.forEach(new C0600f(2, hashMap));
            StringCompat stringCompat = this.TAG;
            C0212a.z(new Object[]{Integer.valueOf(hashMap.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("reuseData"), stringCompat);
        }
    }

    public void setAlbumDisplayName(MediaItem mediaItem) {
        if (mediaItem != null) {
            mediaItem.setDisplayName(AlbumTitleHelper.getAlbumTitle(mediaItem.getAlbumID(), mediaItem.getDisplayName()));
        }
    }

    public boolean skipNotifyIfCacheEmpty(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2, ArrayList<MediaItem> arrayList3) {
        return isCacheEmpty(arrayList, arrayList2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void swap(android.database.Cursor[] r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            android.database.Cursor[] r0 = r5.mCursors     // Catch:{ all -> 0x000f }
            if (r0 != r6) goto L_0x0011
            com.samsung.android.gallery.support.utils.StringCompat r6 = r5.TAG     // Catch:{ all -> 0x000f }
            java.lang.String r0 = "swap skip same cursors"
            com.samsung.android.gallery.support.utils.Log.e(r6, r0)     // Catch:{ all -> 0x000f }
            monitor-exit(r5)
            return
        L_0x000f:
            r6 = move-exception
            goto L_0x0063
        L_0x0011:
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0021
            r3 = r6[r2]     // Catch:{ all -> 0x000f }
            if (r3 != 0) goto L_0x0021
            boolean r3 = r5.isCacheLoadingEnabled()     // Catch:{ all -> 0x000f }
            if (r3 == 0) goto L_0x0021
            r3 = r1
            goto L_0x0022
        L_0x0021:
            r3 = r2
        L_0x0022:
            r4 = r6[r2]     // Catch:{ all -> 0x000f }
            if (r4 == 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r1 = r2
        L_0x0028:
            if (r3 != 0) goto L_0x0053
            if (r1 != 0) goto L_0x0053
            java.lang.String r1 = "location://albums"
            java.lang.String r2 = r5.mLocationKey     // Catch:{ all -> 0x000f }
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x000f }
            if (r1 == 0) goto L_0x0053
            com.samsung.android.gallery.support.blackboard.Blackboard r1 = com.samsung.android.gallery.support.blackboard.Blackboard.getApplicationInstance()     // Catch:{ all -> 0x000f }
            java.lang.String r2 = "data://user/SecurityExceptionOnSecMp"
            java.lang.Boolean r4 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x000f }
            java.lang.Object r1 = r1.read(r2, r4)     // Catch:{ all -> 0x000f }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x000f }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x000f }
            if (r1 != 0) goto L_0x0053
            com.samsung.android.gallery.support.utils.StringCompat r6 = r5.TAG     // Catch:{ all -> 0x000f }
            java.lang.String r0 = "Fake cursor swapped after full cursor already loaded. Ignore cursor."
            com.samsung.android.gallery.support.utils.Log.e(r6, r0)     // Catch:{ all -> 0x000f }
            monitor-exit(r5)
            return
        L_0x0053:
            r5.mCursors = r6     // Catch:{ all -> 0x000f }
            if (r3 == 0) goto L_0x005b
            r5.processCacheLoading(r6)     // Catch:{ all -> 0x000f }
            goto L_0x005e
        L_0x005b:
            r5.processFullLoading(r6)     // Catch:{ all -> 0x000f }
        L_0x005e:
            r5.closeCursors(r0)     // Catch:{ all -> 0x000f }
            monitor-exit(r5)
            return
        L_0x0063:
            monitor-exit(r5)     // Catch:{ all -> 0x000f }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataAlbum.swap(android.database.Cursor[]):void");
    }

    public final void updateAlbumInfo(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem2 != null) {
            mediaItem.setTag("album:modified-time", Long.valueOf(mediaItem2.getDateModified()));
            mediaItem.setTag("album:taken-time", Long.valueOf(mediaItem2.getDateTaken()));
            mediaItem.setTag("album:path", mediaItem2.getPath());
            mediaItem.setTag("album:file-id", Long.valueOf(mediaItem2.getFileId()));
        }
    }

    public int updateCoverItem(int i2, String str, String str2) {
        return this.mAlbumDataHelper.updateCoverItem(this.mBlackboard, this.mDataArray, i2, str, str2);
    }

    public int updateFolderAt(int i2, int i7, String str) {
        return this.mAlbumDataHelper.updateFolderAt(this.mDataArray, i2, i7, str);
    }

    public boolean updateMergeNameAlbumForHide(MediaItem mediaItem) {
        return false;
    }

    public ArrayList<Long> getFileIds() {
        if (this.mLock.acquireReadLock("MDA.getFileIds")) {
            try {
                ArrayList<Long> arrayList = new ArrayList<>();
                Iterator<MediaItem> it = this.mDataArray.iterator();
                while (it.hasNext()) {
                    arrayList.add(Long.valueOf(it.next().getFileId()));
                }
                return arrayList;
            } finally {
                this.mLock.releaseReadLock("MDA.getFileIds");
            }
        } else {
            Log.e(this.TAG, "fail to get lock - file ids");
            return new ArrayList<>();
        }
    }

    public void cleanUpList(ArrayList<MediaItem> arrayList) {
    }

    public void updateClusterData(ArrayList<MediaItem> arrayList) {
    }

    public void setFolderTranslatedName(FolderItem folderItem, MediaItem mediaItem) {
    }
}
