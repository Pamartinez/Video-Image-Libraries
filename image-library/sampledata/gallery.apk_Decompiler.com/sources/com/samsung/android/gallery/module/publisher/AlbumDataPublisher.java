package com.samsung.android.gallery.module.publisher;

import B5.e;
import B8.d;
import M9.a;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.LocalAlbumsApi;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.database.lockedalbum.LockedAlbum;
import com.samsung.android.gallery.module.screenshotfilter.ScreenShotFilterManager;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumDataPublisher extends CursorPublisher {
    private final boolean USE_MX_ALBUMS = PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbums);
    private final AtomicInteger mAlbumsQueryRetryCounter = new AtomicInteger(0);
    private Context mAppContext;
    private final AtomicBoolean mMdeCacheLoading = new AtomicBoolean(true);
    protected final Runnable[] mReloadFullCallbacks = new Runnable[3];
    private final AtomicBoolean mThumbnailLoadDone = new AtomicBoolean(false);

    public AlbumDataPublisher(Blackboard blackboard) {
        super(blackboard);
    }

    private ArrayList<Integer> addExcludedList(ArrayList<Integer> arrayList) {
        File externalFilesDir = this.mAppContext.getExternalFilesDir(".share");
        if (externalFilesDir != null) {
            arrayList.add(Integer.valueOf(FileUtils.getBucketId(externalFilesDir.getAbsolutePath())));
        }
        return arrayList;
    }

    private Cursor getAlbumHideCursor(ArrayList<Integer> arrayList) {
        QueryParams createAlbumQueryParams = createAlbumQueryParams((Bundle) null);
        ArrayList<Integer> addExcludedList = addExcludedList(arrayList);
        Objects.requireNonNull(createAlbumQueryParams);
        addExcludedList.forEach(new a(0, createAlbumQueryParams));
        return DbCompat.query(createAlbumQueryParams.setDbKey(DbKey.ALBUMS).setShowHidden(true));
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.util.function.Predicate, java.lang.Object] */
    private ArrayList<Integer> getFolderBucketIdsFromCursor(Cursor cursor) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            HashMap hashMap = new HashMap();
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex("__bucketID");
                int columnIndex2 = cursor.getColumnIndex("folder_id");
                do {
                    int i2 = cursor.getInt(columnIndex2);
                    if (i2 != 0) {
                        ((ArrayList) hashMap.computeIfAbsent(Integer.valueOf(i2), new C0632l(1))).add(Integer.valueOf(cursor.getInt(columnIndex)));
                    }
                } while (cursor.moveToNext());
                hashMap.values().stream().filter(new Object()).forEach(new d(arrayList, 10));
            }
            return arrayList;
        } catch (SQLiteException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$0(Object obj, Bundle bundle) {
        publishScreenShotFilterData("location://albums/fileList/ScreenShotFilter");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$1(Object obj, Bundle bundle) {
        publishScreenShotFilterData("location://albums/AlbumSetting/ScreenShotFilterCustom");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$getFolderBucketIdsFromCursor$22(Integer num) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getFolderBucketIdsFromCursor$23(ArrayList arrayList) {
        if (arrayList != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishAlbumFileData$10(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[3] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAlbumFileData$11(QueryParams queryParams, Cursor[] cursorArr, long j2, Object obj, Bundle bundle, boolean z) {
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("publishAlbumFileData(R) ");
        sb2.append(queryParams);
        sb2.append(" ");
        A.a.y(sb2, getCursorListInfo(cursorArr, j2), stringCompat);
        publishAlbumFileCursorArray(obj, bundle, z, cursorArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAlbumFileData$12(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[5] = loadFileIdCursor(queryParams, DbKey.ALBUM_FILE_IDS_ORDERED);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishAlbumFileData$2(Cursor[] cursorArr, int i2, int i7) {
        cursorArr[0] = DbCompat.autoAlbumApi().getAutoAlbumPicturesCursor(i2, i7);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAlbumFileData$3(Cursor[] cursorArr, long j2) {
        A.a.y(new StringBuilder("publishAutoAlbumPicturesData "), getCursorListInfo(cursorArr, j2), this.TAG);
        publishCursorArray("location://albums/fileList", cursorArr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishAlbumFileData$4(Cursor[] cursorArr, int i2, int i7) {
        cursorArr[1] = DbCompat.autoAlbumApi().getAutoAlbumPicturesDateCursor(i2, i7);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishAlbumFileData$6(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[1] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAlbumFileData$7(QueryParams queryParams, Cursor[] cursorArr, long j2, Object obj, Bundle bundle, boolean z) {
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("publishAlbumFileData(T) ");
        sb2.append(queryParams);
        sb2.append(" ");
        A.a.y(sb2, getCursorListInfo(cursorArr, j2), stringCompat);
        publishAlbumFileCursorArray(obj, bundle, z, cursorArr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishAlbumFileData$8(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[3] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAlbumFileData$9(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[5] = loadFileIdCursor(queryParams, DbKey.ALBUM_FILE_IDS_ORDERED);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAlbumHideData$24(Cursor[] cursorArr) {
        Cursor validFolderCursor = new LocalAlbumsApi().getValidFolderCursor();
        try {
            cursorArr[0] = getAlbumHideCursor(getFolderBucketIdsFromCursor(validFolderCursor));
            if (validFolderCursor != null) {
                validFolderCursor.close();
                return;
            }
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAlbumHideData$25(Cursor[] cursorArr) {
        cursorArr[1] = new LocalAlbumsApi().getAlbumCursor(true, false, false, PickerUtil.isSpecificPathIncluded(this.mBlackboard));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAlbumHideData$26(Cursor[] cursorArr, long j2) {
        A.a.y(new StringBuilder("publishAlbumHideData : "), getCursorListInfo(cursorArr, j2), this.TAG);
        publishCursorArray("location://albums/hide", cursorArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAlbumsData$15() {
        this.mBlackboard.publish("command://LoadFullMenu", (Object) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishAlbumsData$16(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[0] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAlbumsData$17(Cursor[] cursorArr) {
        cursorArr[1] = new LocalAlbumsApi().getAlbumCursor(true, true, false, PickerUtil.isSpecificPathIncluded(this.mBlackboard));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAlbumsData$18(Cursor[] cursorArr, QueryParams queryParams, long j2) {
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishAlbumsData(" + SortByType.toDebugString(SortByType.getAlbumsOrder()) + ") " + queryParams + " " + getCursorListInfo(cursorArr, j2));
        this.mBlackboard.erase(DataKey.CACHED_DATA_CURSOR("location://albums"));
        publishCursorArray("location://albums", cursorArr);
        dumpDataCount(PreferenceName.LAST_ALBUM_COUNT, getCursorCount(cursorArr[0]));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishAlbumsData$19(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[2] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishAlbumsData$20(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[3] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishAlbumsData$21(Cursor[] cursorArr) {
        cursorArr[4] = DbCompat.autoAlbumApi().getAutoAlbumCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishMxAlbumsFileData$13(Bundle bundle, HashMap hashMap, ArrayList arrayList, String str, int[] iArr) {
        if (LocationKey.isAlbumPictures(str)) {
            arrayList.add(DbCompat.query(createQueryParams(bundle).setDbKey(DbKey.ALBUM_FILES).addAlbumIds((int[]) hashMap.get(str))));
        } else if (LocationKey.isFavoritePictures(str)) {
            arrayList.add(DbCompat.query(new QueryParams(DbKey.VIRTUAL_ALBUM_FAVORITE)));
        } else if (LocationKey.isRecentlyPictures(str)) {
            arrayList.add(DbCompat.query(new QueryParams(DbKey.VIRTUAL_ALBUM_RECENT).setWithEmptyAlbums(false)));
        } else if (LocationKey.isAutoAlbumPictures(str)) {
            arrayList.add(DbCompat.autoAlbumApi().getAutoAlbumPicturesCursor((Collection<Integer>) (List) Arrays.stream((int[]) hashMap.get(str)).boxed().collect(Collectors.toList()), 0));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Cursor[] lambda$publishMxAlbumsFileData$14(int i2) {
        return new Cursor[i2];
    }

    /* access modifiers changed from: private */
    public void loadAlbumsFullData() {
        Runnable runnable;
        if (this.mThumbnailLoadDone.get() || this.mAlbumsQueryRetryCounter.getAndIncrement() >= 4 || (runnable = this.mReloadFullCallbacks[1]) == null) {
            this.mReloadFullCallbacks[1] = null;
            this.mBlackboard.erase(CommandKey.DATA_REQUEST("location://albums/cache"));
            String str = "location://albums";
            if (!PickerUtil.isNormalLaunchMode(this.mBlackboard)) {
                str = PickerUtil.appendPickerArgs(this.mBlackboard, str);
            }
            this.mBlackboard.publish(CommandKey.DATA_REQUEST(str), (Object) null);
            return;
        }
        Log.w((CharSequence) this.TAG, "loadAlbumsFullData delayed", this.mAlbumsQueryRetryCounter);
        ThreadUtil.postOnBgThreadDelayed(runnable, 100);
    }

    /* access modifiers changed from: private */
    public void onContext(Object obj, Bundle bundle) {
        this.mAppContext = BlackboardUtils.readAppContext(this.mBlackboard);
    }

    /* access modifiers changed from: private */
    public void onThumbnailLoadDone(Object obj, Bundle bundle) {
        this.mThumbnailLoadDone.set(true);
        Runnable runnable = this.mReloadFullCallbacks[1];
        if (runnable != null) {
            ThreadUtil.removeCallbackOnBgThread(runnable);
            StringCompat stringCompat = this.TAG;
            Log.p(stringCompat, "onThumbnailLoadDone#albums#" + this.mAlbumsQueryRetryCounter.get());
            ThreadUtil.postOnBgThreadDelayed(runnable, 100);
        }
        Runnable runnable2 = this.mReloadFullCallbacks[0];
        if (runnable2 != null) {
            ThreadUtil.removeCallbackOnBgThread(runnable2);
            Log.p(this.TAG, "onThumbnailLoadDone#timeline");
            runnable2.run();
        }
    }

    private void publishAlbumFileCursorArray(Object obj, Bundle bundle, boolean z, Cursor[] cursorArr) {
        String str;
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER && z) {
            ScreenShotFilterManager instance = ScreenShotFilterManager.getInstance(this.mBlackboard);
            if (instance.needResetFilteredList(cursorArr)) {
                instance.resetFilter();
                this.mBlackboard.postEvent(EventMessage.obtain(2014));
                publishAlbumFileData(obj, bundle);
                return;
            }
        }
        if (BundleWrapper.getBoolean(bundle, "cluster_album_item")) {
            str = ArgumentsUtil.removeArgs(getLocationKeyFromReq(bundle));
        } else {
            str = "location://albums/fileList";
        }
        publishCursorArray(str, cursorArr);
    }

    /* access modifiers changed from: private */
    public void publishAlbumFileData(Object obj, Bundle bundle) {
        boolean z;
        AlbumDataPublisher albumDataPublisher = this;
        Bundle bundle2 = bundle;
        long currentTimeMillis = System.currentTimeMillis();
        albumDataPublisher.assertArgumentId(bundle2);
        int i2 = BundleWrapper.getInt(bundle2, "id", 0);
        if (i2 == 0 || !BucketUtils.isVirtualAlbum(i2)) {
            if (PocFeatures.isEnabled(PocFeatures.WifiGalleryClient)) {
                AlbumDataPublisher albumDataPublisher2 = albumDataPublisher;
                albumDataPublisher = albumDataPublisher2;
                if (new RemoteAlbumPublisher().exec(albumDataPublisher2.mBlackboard, (long) i2, bundle2, albumDataPublisher2)) {
                    return;
                }
            }
            if (!albumDataPublisher.USE_MX_ALBUMS || !AlbumType.isAutoAlbum(BundleWrapper.getInt(bundle2, "type", AlbumType.None.toInt()))) {
                long j2 = currentTimeMillis;
                QueryParams createQueryParams = albumDataPublisher.createQueryParams(bundle2);
                QueryParams dbKey = createQueryParams.setDbKey(DbKey.ALBUM_FILES);
                QueryParams dbKey2 = createQueryParams.clone().setDbKey(DbKey.ALBUM_FILE_DAY);
                QueryParams dbKey3 = createQueryParams.clone().setDbKey(DbKey.ALBUM_FILE_REALRATIO);
                if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
                    if (BucketUtils.isScreenshot(i2)) {
                        z = true;
                    } else if (!albumDataPublisher.USE_MX_ALBUMS || !AlbumType.isMergedAlbum(BundleWrapper.getInt(bundle2, "type", AlbumType.None.toInt()))) {
                        z = false;
                    } else {
                        z = ((Boolean) Optional.ofNullable(BundleWrapper.getString(bundle2, "ids", (String) null)).map(new C0632l(0)).orElse(Boolean.FALSE)).booleanValue();
                    }
                    if (z) {
                        String filteredScene = ScreenShotFilterManager.getInstance(albumDataPublisher.mBlackboard).getFilteredScene();
                        createQueryParams.setSubCategory(filteredScene);
                        dbKey.setSubCategory(filteredScene);
                        dbKey2.setSubCategory(filteredScene);
                        dbKey3.setSubCategory(filteredScene);
                    }
                } else {
                    z = false;
                }
                if (albumDataPublisher.supportTimelineInAlbumPictures(bundle2)) {
                    Cursor[] cursorArr = {null, null, null, null, null, null};
                    QueryParams queryParams = dbKey;
                    boolean z3 = z;
                    Cursor[] cursorArr2 = cursorArr;
                    QueryParams queryParams2 = queryParams;
                    LatchBuilder postExecutor = new LatchBuilder("publishAlbumFileData(T)").addWorker(new C0625e(cursorArr, 3, dbKey2)).setPostExecutor((Runnable) new C0633m(albumDataPublisher, queryParams, cursorArr2, j2, obj, bundle2, z3, 0));
                    if (CursorPublisher.SUPPORT_REAL_RATIO) {
                        postExecutor.addWorker(new C0625e(cursorArr2, 4, dbKey3));
                    }
                    postExecutor.setCurrent(new C0631k(albumDataPublisher, cursorArr2, queryParams2, 1));
                    postExecutor.start();
                    return;
                }
                QueryParams queryParams3 = dbKey;
                boolean z7 = z;
                if (albumDataPublisher.supportRealRatioInAlbumPictures(bundle2)) {
                    Cursor[] cursorArr3 = {null, null, null, null, null, null};
                    QueryParams queryParams4 = queryParams3;
                    QueryParams queryParams5 = queryParams4;
                    LatchBuilder postExecutor2 = new LatchBuilder("publishAlbumFileData(R)").addWorker(new C0625e(cursorArr3, 5, dbKey3)).setPostExecutor((Runnable) new C0633m(albumDataPublisher, queryParams4, cursorArr3, j2, obj, bundle2, z7, 1));
                    postExecutor2.setCurrent(new C0631k(albumDataPublisher, cursorArr3, queryParams5, 0));
                    postExecutor2.start();
                    return;
                }
                Object obj2 = obj;
                if (Features.isEnabled(Features.SUPPORT_PPP_V3) || !BundleWrapper.getBoolean(bundle2, "quick_view", false)) {
                    Cursor[] cursorArr4 = {null, null, null, null, null, null};
                    cursorArr4[5] = albumDataPublisher.loadFileIdCursor(queryParams3, DbKey.ALBUM_FILE_IDS_ORDERED);
                    StringCompat stringCompat = albumDataPublisher.TAG;
                    StringBuilder sb2 = new StringBuilder("publishAlbumFileData ");
                    sb2.append(queryParams3);
                    sb2.append(" ");
                    A.a.y(sb2, albumDataPublisher.getCursorListInfo(cursorArr4, j2), stringCompat);
                    albumDataPublisher.publishAlbumFileCursorArray(obj2, bundle2, z7, cursorArr4);
                    return;
                }
                Cursor[] cursorArr5 = {DbCompat.query(queryParams3)};
                StringCompat stringCompat2 = albumDataPublisher.TAG;
                StringBuilder sb3 = new StringBuilder("publishAlbumFileData ");
                sb3.append(queryParams3);
                sb3.append(" ");
                A.a.y(sb3, albumDataPublisher.getCursorListInfo(cursorArr5, j2), stringCompat2);
                albumDataPublisher.publishAlbumFileCursorArray(obj2, bundle2, z7, cursorArr5);
                return;
            }
            Cursor[] cursorArr6 = new Cursor[2];
            int loadSortBy = GalleryPreference.getInstance().loadSortBy(String.valueOf(i2), 12);
            LatchBuilder postExecutor3 = new LatchBuilder("publishAutoAlbumPicturesData(T)").addWorker(new C0630j(cursorArr6, i2, loadSortBy, 0)).setPostExecutor((Runnable) new C0629i(albumDataPublisher, cursorArr6, currentTimeMillis, 1));
            if (albumDataPublisher.supportTimelineInAlbumPictures(bundle2)) {
                postExecutor3.addWorker(new C0630j(cursorArr6, i2, loadSortBy, 1));
            }
            postExecutor3.start();
        } else if (BucketUtils.isFavourite(i2)) {
            albumDataPublisher.publishFavoriteFileData(bundle2, i2);
        } else if (BucketUtils.isRecent(i2)) {
            albumDataPublisher.publishRecentFileData(bundle2);
        } else {
            Log.e(albumDataPublisher.TAG, "publishAlbumFileData(virtual) failed. wrong args=" + bundle2);
        }
    }

    /* access modifiers changed from: private */
    public void publishAlbumHideData(Object obj, Bundle bundle) {
        Cursor[] cursorArr = new Cursor[2];
        new LatchBuilder("publishAlbumHideData").addWorker(new C0626f(this, cursorArr, 1)).setCurrent(new C0626f(this, cursorArr, 2)).setPostExecutor((Runnable) new C0629i(this, cursorArr, System.currentTimeMillis(), 0)).start();
    }

    /* access modifiers changed from: private */
    public void publishAlbumsData(Object obj, Bundle bundle) {
        if (!this.mBlackboard.isEmpty(CommandKey.DATA_REQUEST("location://albums/cache"))) {
            Log.w(this.TAG, "publishAlbumsData postponed while cache loading");
            return;
        }
        ThreadUtil.postOnBgThread(new C0622b(this, 1));
        try {
            Trace.beginSection("publishAlbumsData");
            long currentTimeMillis = System.currentTimeMillis();
            QueryParams createAlbumQueryParams = createAlbumQueryParams(bundle);
            String str = DbKey.ALBUMS;
            QueryParams dbKey = createAlbumQueryParams.setDbKey(str);
            QueryParams dbKey2 = dbKey.clone().setDbKey(str);
            dbKey2.setShowHidden(true);
            Cursor[] cursorArr = new Cursor[5];
            LatchBuilder postExecutor = new LatchBuilder("publishAlbumsData").addWorker(new C0625e(cursorArr, 0, dbKey2)).setCurrent(new C0626f(this, cursorArr, 0)).setPostExecutor((Runnable) new C0627g(this, cursorArr, dbKey2, currentTimeMillis));
            if (this.USE_MX_ALBUMS) {
                if (PocFeatures.SHOW_VIRTUAL_ALBUMS && PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums)) {
                    QueryParams dbKey3 = dbKey.clone().setDbKey(DbKey.VIRTUAL_ALBUM_FAVORITE_ALBUM);
                    GroupType groupType = GroupType.BURST;
                    QueryParams groupTypes = dbKey3.setGroupTypes(groupType);
                    if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
                        groupTypes.excludeAlbumId(LockedAlbum.getInstance().getBucketList());
                    }
                    int loadSortBy = GalleryPreference.getInstance().loadSortBy(String.valueOf(FileUtils.getBucketId("Virtual/Favourites")), 12);
                    if (loadSortBy != 12) {
                        groupTypes.setSortBy(loadSortBy);
                    }
                    QueryParams addGroupType = dbKey.clone().setDbKey(DbKey.VIRTUAL_ALBUM_RECENT_ALBUM).setGroupTypes(groupType).addGroupType(GroupType.SINGLE_TAKEN);
                    if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
                        addGroupType.excludeAlbumId(LockedAlbum.getInstance().getBucketList());
                    }
                    postExecutor.addWorker(new C0625e(cursorArr, 1, groupTypes)).addWorker(new C0625e(cursorArr, 2, addGroupType));
                }
                if (Features.isEnabled(Features.SUPPORT_AUTO_UPDATING_ALBUM)) {
                    postExecutor.addWorker(new C0628h(cursorArr, 0));
                }
            }
            postExecutor.start();
            Trace.endSection();
        } catch (Throwable th) {
            Throwable th2 = th;
            Trace.endSection();
            throw th2;
        }
    }

    /* access modifiers changed from: private */
    public void publishAlbumsDataCache(Object obj, Bundle bundle) {
        try {
            Trace.beginSection("publishAlbumsData#cache");
            long currentTimeMillis = System.currentTimeMillis();
            this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
            Cursor albumCursor = new LocalAlbumsApi().getAlbumCursor(false, false, false, PickerUtil.isSpecificPathIncluded(this.mBlackboard));
            StringCompat stringCompat = this.TAG;
            Log.p(stringCompat, "publishAlbumsData(cache) " + getCursorInfo(albumCursor, currentTimeMillis));
            publishCachedCursorArray("location://albums", new Cursor[]{null, albumCursor});
            this.mBlackboard.erase(CommandKey.DATA_REQUEST("location://albums/cache"));
            if (this.mThumbnailLoadDone.get()) {
                loadAlbumsFullData();
            } else {
                C0622b bVar = new C0622b(this, 0);
                this.mReloadFullCallbacks[1] = bVar;
                ThreadUtil.postOnBgThreadDelayed(bVar, 200);
            }
        } catch (Exception e) {
            StringCompat stringCompat2 = this.TAG;
            Log.e(stringCompat2, "publishAlbumsData(cache) failed e=" + e.getMessage());
            this.mBlackboard.erase(CommandKey.DATA_REQUEST("location://albums/cache"));
            publishAlbumsData(obj, bundle);
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public void publishAlbumsFileData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor[] cursorArr = {DbCompat.query(createQueryParams(bundle).setDbKey(DbKey.ALBUM_FILES).addAlbumIds((int[]) ((HashMap) obj).get("location://albums/fileList")))};
        A.a.y(new StringBuilder("publishAlbumsFileData : "), getCursorListInfo(cursorArr, currentTimeMillis), this.TAG);
        publishCursorArray("location://albums/fileList/multiple", cursorArr);
    }

    /* JADX WARNING: type inference failed for: r9v6, types: [java.lang.Object, java.util.function.IntFunction] */
    /* access modifiers changed from: private */
    public void publishMxAlbumsFileData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        HashMap hashMap = (HashMap) obj;
        ArrayList arrayList = new ArrayList();
        Cursor[] cursorArr = new Cursor[0];
        try {
            hashMap.forEach(new C0623c(this, bundle, hashMap, arrayList));
            Cursor[] cursorArr2 = (Cursor[]) arrayList.stream().toArray(new Object());
            try {
                StringCompat stringCompat = this.TAG;
                Log.p(stringCompat, "publishMxAlbumsFileData : " + getCursorListInfo(cursorArr2, currentTimeMillis));
                publishCursorArray("location://albums/fileList/mx/multiple", cursorArr2);
            } catch (Exception e) {
                e = e;
                cursorArr = cursorArr2;
                try {
                    Log.e(this.TAG, e.getMessage());
                    publishCursorArray("location://albums/fileList/mx/multiple", cursorArr);
                } catch (Throwable th) {
                    th = th;
                    publishCursorArray("location://albums/fileList/mx/multiple", cursorArr);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                cursorArr = cursorArr2;
                publishCursorArray("location://albums/fileList/mx/multiple", cursorArr);
                throw th;
            }
        } catch (Exception e7) {
            e = e7;
            Log.e(this.TAG, e.getMessage());
            publishCursorArray("location://albums/fileList/mx/multiple", cursorArr);
        }
    }

    private void publishScreenShotFilterData(String str) {
        String l = C0086a.l(new StringBuilder("where S.parent_name is 'capture' and S.scene_name is not 'cap_others' and S.sec_media_id in (select _id from files where bucket_id="), StorageInfo.getDefault().buckets().screenShot, ")");
        StringJoiner stringJoiner = new StringJoiner("','", "('", "')");
        ScreenShotFilterType.sSubCategoryInfo.keySet().forEach(new e(stringJoiner, 2));
        String m = C0212a.m("select S.scene_name as _name, count(S.scene_name) as _count, S.parent_name as _parent from scene as S ", l + "and S.scene_name in " + stringJoiner, " group by S.scene_name");
        publishCursorArray(str, new Cursor[]{new SecMpQueryExecutor().rawQuery("select *, " + ScreenShotFilterType.projection() + " as _order from (" + m + ") order by _order ASC", "queryScreenShotFilterData")});
    }

    private boolean supportTimelineInAlbumPictures(Bundle bundle) {
        if (BundleWrapper.getBoolean(bundle, "disableTimeline", false)) {
            return false;
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.AlbumTimeline)) {
            return true;
        }
        if (this.USE_MX_ALBUMS) {
            String string = bundle.getString("mergedAlbumId", (String) null);
            if (!TextUtils.isEmpty(string)) {
                return SortByType.isGroupByDate(string);
            }
        }
        return SortByType.isGroupByDate(bundle.getString("id", (String) null));
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("data://app_context", new C0621a(this, 1)));
        arrayList.add(new SubscriberInfo("lifecycle://on_thumbnail_load_done", new C0621a(this, 5)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://albums"), new C0621a(this, 6)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://albums/cache"), new C0621a(this, 7)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://albums/fileList"), new C0621a(this, 8)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://albums/fileList/mxVirtual/favorite"), new C0621a(this, 8)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://albums/fileList/mxVirtual/recent"), new C0621a(this, 8)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://albums/fileList/fromSearchCluster"), new C0621a(this, 8)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://albums/fileList/multiple"), new C0621a(this, 9)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://albums/fileList/mx/multiple"), new C0621a(this, 0)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://albums/hide"), new C0621a(this, 2)));
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://albums/fileList/ScreenShotFilter"), new C0621a(this, 3)));
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://albums/AlbumSetting/ScreenShotFilterCustom"), new C0621a(this, 4)));
        }
    }
}
