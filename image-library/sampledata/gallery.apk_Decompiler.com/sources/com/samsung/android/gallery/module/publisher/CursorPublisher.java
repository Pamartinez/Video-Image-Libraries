package com.samsung.android.gallery.module.publisher;

import A.a;
import A4.C0368c;
import D3.j;
import M9.b;
import M9.c;
import M9.d;
import M9.e;
import M9.f;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.database.lockedalbum.LockedAlbum;
import com.samsung.android.gallery.module.quicksearch.QuickSearchManager;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.Def;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CursorPublisher extends Subscriber {
    protected static boolean SUPPORT_REAL_RATIO = Features.isEnabled(Features.SUPPORT_REAL_RATIO);

    public CursorPublisher(Blackboard blackboard) {
        super(blackboard);
    }

    private int[] getAlbumIds(Bundle bundle) {
        String string = bundle.getString("id", (String) null);
        if (!TextUtils.isEmpty(string)) {
            return new int[]{UnsafeCast.toInt(string)};
        }
        String string2 = bundle.getString("ids", (String) null);
        if (TextUtils.isEmpty(string2)) {
            return new int[]{0};
        }
        String[] split = string2.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        int[] iArr = new int[split.length];
        for (int i2 = 0; i2 < split.length; i2++) {
            iArr[i2] = UnsafeCast.toInt(split[i2]);
        }
        return iArr;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$dumpDataCount$12(PreferenceName preferenceName, int i2) {
        try {
            GalleryPreference.getInstanceDebug().saveState(preferenceName.key() + PackageMonitorCompat.getVersionCode(), TimeUtil.getTimestamp() + " = " + i2);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishFavoriteFileData$0(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[3] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishFavoriteFileData$1(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[5] = loadFileIdCursor(queryParams, DbKey.VIRTUAL_ALBUM_FAVORITE_FILE_IDS_ORDERED);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishFavoriteFileData$2(boolean z, Cursor[] cursorArr, long j2, String str) {
        String str2;
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("publishFavoriteFileData");
        if (z) {
            str2 = "(T) ";
        } else {
            str2 = " ";
        }
        sb2.append(str2);
        a.y(sb2, getCursorListInfo(cursorArr, j2), stringCompat);
        publishCursorArray(str, cursorArr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishFavoriteFileData$3(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[1] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishRecentFileData$10(Cursor[] cursorArr, long j2, Bundle bundle, String str) {
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishRecentFileData " + getCursorListInfo(cursorArr, j2) + " from QuickView ? " + BundleWrapper.getBoolean(bundle, "quick_view", false));
        publishCursorArray(str, cursorArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishRecentFileData$4(Cursor[] cursorArr, Bundle bundle, QueryParams queryParams) {
        Cursor cursor;
        if (supportRealRatioInAlbumPictures(bundle)) {
            cursor = DbCompat.query(queryParams);
        } else {
            cursor = null;
        }
        cursorArr[3] = cursor;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishRecentFileData$5(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[1] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishRecentFileData$6(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[5] = loadFileIdCursor(queryParams, DbKey.ALBUM_FILE_IDS_ORDERED);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishRecentFileData$7(Cursor[] cursorArr, long j2, String str) {
        a.y(new StringBuilder("publishRecentFileData[IDS] "), getCursorListInfo(cursorArr, j2), this.TAG);
        publishCursorArray(str, cursorArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishRecentFileData$8(Cursor[] cursorArr, Bundle bundle, QueryParams queryParams) {
        Cursor cursor;
        if (supportRealRatioInAlbumPictures(bundle)) {
            cursor = DbCompat.query(queryParams);
        } else {
            cursor = null;
        }
        cursorArr[3] = cursor;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishRecentFileData$9(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[0] = DbCompat.query(queryParams.setDbKey(DbKey.VIRTUAL_ALBUM_RECENT));
    }

    public void assertArgumentId(Bundle bundle) {
        if (bundle.get("id") == null && bundle.get("ids") == null) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "assertionArgumentId no album id\n" + ThreadUtil.getCallStack());
        }
    }

    public void closeCursors(Cursor[] cursorArr) {
        for (Cursor cursor : cursorArr) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public final QueryParams createAlbumQueryParams(Bundle bundle) {
        return createQueryParams(bundle, false);
    }

    public final QueryParams createQueryParams(Bundle bundle) {
        QueryParams createQueryParams = createQueryParams(bundle, false);
        if (PocFeatures.SUPPORT_LOCKED_ALBUM && createQueryParams.getAlbumIdCount() == 0) {
            createQueryParams.excludeAlbumId(LockedAlbum.getInstance().getBucketList());
        }
        return createQueryParams;
    }

    public final QueryParams createTimelineQueryParams(Bundle bundle) {
        QueryParams createQueryParams = createQueryParams(bundle, true);
        if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
            createQueryParams.excludeAlbumId(LockedAlbum.getInstance().getBucketList());
        }
        if (PocFeatures.QUICK_SEARCH) {
            QuickSearchManager instance = QuickSearchManager.getInstance(this.mBlackboard);
            if (instance.getImageFilterViewEnabled()) {
                createQueryParams.setMediaTypeFilter(MediaFilterType.IMAGE_ONLY.toString());
            } else if (instance.getVideoFilterViewEnabled()) {
                createQueryParams.setMediaTypeFilter(MediaFilterType.VIDEO_ONLY.toString());
            }
            if (instance.getDateFiltered()) {
                createQueryParams.setCreationTimeLimit(instance.getStartDate(), instance.getEndDate());
            }
            if (instance.hasFilteredPeople() || instance.hasFilteredLocation()) {
                createQueryParams.setMediaIdsFilter(instance.getFilteredMediaIds());
            }
        }
        return createQueryParams;
    }

    public final QueryParams createVirtualQueryParams(Bundle bundle) {
        QueryParams showHidden = createQueryParams(bundle, false).setShowHidden(false);
        if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
            showHidden.excludeAlbumId(LockedAlbum.getInstance().getBucketList());
        }
        return showHidden;
    }

    public void dumpDataCount(PreferenceName preferenceName, int i2) {
        if (i2 != -1) {
            ThreadUtil.postOnBgThreadDelayed(new C0368c(preferenceName, i2, 11), Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
        }
    }

    public int getCursorCount(Cursor cursor) {
        if (cursor == null) {
            return -1;
        }
        try {
            return cursor.getCount();
        } catch (Exception unused) {
            return -1;
        }
    }

    public String getCursorInfo(Cursor cursor, long j2) {
        return Logger.toString(cursor) + " +" + (System.currentTimeMillis() - j2);
    }

    public String getCursorListInfo(Cursor[] cursorArr, long j2) {
        return C0086a.j(j2, Logger.toString(cursorArr), " +", new StringBuilder());
    }

    public final String getLocationKeyFromReq(Bundle bundle) {
        return CommandKey.DATA_REQUEST_TO_LOCATION(ArgumentsUtil.getSubscribeKey(bundle));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0084, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b7, code lost:
        if (r5 != null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ba, code lost:
        r11 = r1.toString().hashCode() + "_" + r2.getCount();
        r12 = new android.os.Bundle();
        r12.putString("dataHash", r11);
        r2.setExtras(r12);
        i.C0212a.z(new java.lang.Object[]{r11, r0}, new java.lang.StringBuilder("loadFileIdCursor"), r10.TAG);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00f7, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.database.Cursor loadFileIdCursor(com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r11, java.lang.String r12) {
        /*
            r10 = this;
            com.samsung.android.gallery.support.utils.TimeTickLog r0 = new com.samsung.android.gallery.support.utils.TimeTickLog
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r11 = r11.clone()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r11 = r11.setDbKey(r12)
            r12 = 250000(0x3d090, float:3.50325E-40)
            r11.setLimit(r12)
            java.util.ArrayList r2 = r11.getAlbumIdList()
            if (r2 == 0) goto L_0x0040
            int r3 = com.samsung.android.gallery.support.utils.BucketUtils.VirtualBucketHolder.recent
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            boolean r4 = r2.contains(r4)
            if (r4 == 0) goto L_0x0031
            r11.clearAlbumIds()
            r11.addAlbumId(r3)
            goto L_0x0040
        L_0x0031:
            int r3 = com.samsung.android.gallery.support.utils.BucketUtils.VirtualBucketHolder.favorite
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            boolean r2 = r2.contains(r3)
            if (r2 == 0) goto L_0x0040
            r11.setIncludeFavorite()
        L_0x0040:
            com.samsung.android.gallery.support.utils.CollectionCursor r2 = new com.samsung.android.gallery.support.utils.CollectionCursor
            java.lang.String r3 = "__absID"
            r2.<init>((java.lang.String) r3)
            r3 = 0
            r4 = r3
        L_0x0049:
            int r5 = r4 * r12
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r5 = r11.setLimit(r5, r12)
            android.database.Cursor r5 = com.samsung.android.gallery.database.dal.DbCompat.query((com.samsung.android.gallery.database.dal.abstraction.query.QueryParams) r5)
            r0.tick()     // Catch:{ all -> 0x006f }
            if (r5 == 0) goto L_0x00b7
            boolean r6 = r5.moveToFirst()     // Catch:{ all -> 0x006f }
            if (r6 != 0) goto L_0x005f
            goto L_0x00b7
        L_0x005f:
            java.lang.String r6 = r5.getString(r3)     // Catch:{ all -> 0x006f }
            r1.append(r6)     // Catch:{ all -> 0x006f }
            if (r6 == 0) goto L_0x0072
            java.lang.String r7 = ","
            java.lang.String[] r6 = r6.split(r7)     // Catch:{ all -> 0x006f }
            goto L_0x0074
        L_0x006f:
            r10 = move-exception
            goto L_0x00f8
        L_0x0072:
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ all -> 0x006f }
        L_0x0074:
            L5.b r7 = new L5.b     // Catch:{ all -> 0x006f }
            r8 = 9
            r7.<init>(r8)     // Catch:{ all -> 0x006f }
            r2.addAll(r6, r7)     // Catch:{ all -> 0x006f }
            r0.tick()     // Catch:{ all -> 0x006f }
            int r7 = r6.length     // Catch:{ all -> 0x006f }
            if (r7 >= r12) goto L_0x0088
        L_0x0084:
            r5.close()
            goto L_0x00ba
        L_0x0088:
            int r7 = r6.length     // Catch:{ all -> 0x006f }
            int r7 = r7 + -1
            r6 = r6[r7]     // Catch:{ all -> 0x006f }
            long r6 = java.lang.Long.parseLong(r6)     // Catch:{ all -> 0x006f }
            r8 = -1
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 != 0) goto L_0x00a2
            com.samsung.android.gallery.support.exception.InternalException r11 = new com.samsung.android.gallery.support.exception.InternalException     // Catch:{ all -> 0x006f }
            java.lang.String r12 = "loadFileIdCursor failed. get next all file ids"
            r11.<init>(r12)     // Catch:{ all -> 0x006f }
            r11.post()     // Catch:{ all -> 0x006f }
            goto L_0x0084
        L_0x00a2:
            r5.close()
            com.samsung.android.gallery.support.utils.StringCompat r5 = r10.TAG
            int r4 = r4 + 1
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r7 = "loadFileIdCursor load next"
            com.samsung.android.gallery.support.utils.Log.d(r5, r7, r6)
            goto L_0x0049
        L_0x00b7:
            if (r5 == 0) goto L_0x00ba
            goto L_0x0084
        L_0x00ba:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = r1.toString()
            int r12 = r12.hashCode()
            r11.append(r12)
            java.lang.String r12 = "_"
            r11.append(r12)
            int r12 = r2.getCount()
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            android.os.Bundle r12 = new android.os.Bundle
            r12.<init>()
            java.lang.String r1 = "dataHash"
            r12.putString(r1, r11)
            r2.setExtras(r12)
            com.samsung.android.gallery.support.utils.StringCompat r10 = r10.TAG
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r1 = "loadFileIdCursor"
            r12.<init>(r1)
            java.lang.Object[] r11 = new java.lang.Object[]{r11, r0}
            i.C0212a.z(r11, r12, r10)
            return r2
        L_0x00f8:
            if (r5 == 0) goto L_0x0102
            r5.close()     // Catch:{ all -> 0x00fe }
            goto L_0x0102
        L_0x00fe:
            r11 = move-exception
            r10.addSuppressed(r11)
        L_0x0102:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.publisher.CursorPublisher.loadFileIdCursor(com.samsung.android.gallery.database.dal.abstraction.query.QueryParams, java.lang.String):android.database.Cursor");
    }

    public boolean publishCachedCursorArray(String str, Cursor[] cursorArr) {
        String CACHED_DATA_CURSOR = DataKey.CACHED_DATA_CURSOR(str);
        if (!this.mBlackboard.isEmpty(CACHED_DATA_CURSOR)) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "Cached loading should be done once" + CACHED_DATA_CURSOR);
            closeCursors(cursorArr);
            return false;
        }
        this.mBlackboard.publishIfEmpty("debug://TimeDoneQuery", Long.valueOf(System.currentTimeMillis()));
        this.mBlackboard.publish(CACHED_DATA_CURSOR, cursorArr);
        return true;
    }

    public boolean publishCursorArray(String str, Cursor[] cursorArr) {
        return publishCursorArray(str, cursorArr, CommandKey.DATA_REQUEST(str));
    }

    public void publishFavoriteFileData(Bundle bundle, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor[] cursorArr = {null, null, null, null, null, null};
        boolean isGroupByDate = SortByType.isGroupByDate(bundle.getString("id", (String) null));
        QueryParams dbKey = createVirtualQueryParams(bundle).setDbKey(DbKey.VIRTUAL_ALBUM_FAVORITE);
        dbKey.setParentAlbumId(i2);
        LatchBuilder postExecutor = new LatchBuilder("publishFavoriteFileData").addWorker(new c(cursorArr, 3, dbKey.clone().setDbKey(DbKey.VIRTUAL_ALBUM_FAVORITE_REAL_RATIO))).setCurrent(new d(this, cursorArr, dbKey, 1)).setPostExecutor((Runnable) new f(this, isGroupByDate, cursorArr, currentTimeMillis, getLocationKeyFromReq(bundle)));
        if (isGroupByDate) {
            postExecutor.addWorker(new c(cursorArr, 0, dbKey.clone().setDbKey(DbKey.VIRTUAL_ALBUM_FAVORITE_DAY)));
        }
        postExecutor.start();
    }

    public void publishRecentFileData(Bundle bundle) {
        QueryParams queryParams;
        Bundle bundle2 = bundle;
        long currentTimeMillis = System.currentTimeMillis();
        String locationKeyFromReq = getLocationKeyFromReq(bundle);
        QueryParams createVirtualQueryParams = createVirtualQueryParams(bundle);
        Cursor[] cursorArr = {null, null, null, null, null, null};
        if (!BucketUtils.isRecent(BundleWrapper.getInt(bundle2, "id", 0)) || BundleWrapper.getBoolean(bundle2, "quick_view", false)) {
            new LatchBuilder("publishRecentFileData").addWorker(new b(this, cursorArr, bundle, createVirtualQueryParams.clone().setDbKey(DbKey.ALBUM_FILE_REALRATIO), 1)).setCurrent(new c(cursorArr, 2, createVirtualQueryParams)).setPostExecutor((Runnable) new e(this, cursorArr, currentTimeMillis, bundle, locationKeyFromReq)).start();
            return;
        }
        createVirtualQueryParams.setSortBy(70);
        createVirtualQueryParams.setWithEmptyAlbums(false);
        QueryParams dbKey = createVirtualQueryParams.clone().setDbKey(DbKey.ALBUM_FILE_REALRATIO);
        c cVar = null;
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.RecentAlbumTimeline)) {
            queryParams = createVirtualQueryParams.clone().setDbKey(DbKey.VIRTUAL_ALBUM_FILES_DAY);
        } else {
            queryParams = null;
        }
        LatchBuilder addWorker = new LatchBuilder("publishRecentFileData").addWorker(new b(this, cursorArr, bundle2, dbKey, 0));
        if (queryParams != null) {
            cVar = new c(cursorArr, 1, queryParams);
        }
        addWorker.addWorker(cVar).setCurrent(new d(this, cursorArr, createVirtualQueryParams, 0)).setPostExecutor((Runnable) new j((Object) this, (Object) cursorArr, currentTimeMillis, (Object) locationKeyFromReq, 2)).start();
    }

    public void releaseRequestKey(Bundle bundle) {
        if (bundle != null) {
            this.mBlackboard.erase(ArgumentsUtil.getSubscribeKey(bundle));
        }
    }

    public boolean supportRealRatioInAlbumPictures(Bundle bundle) {
        if (!SUPPORT_REAL_RATIO || BundleWrapper.getBoolean(bundle, "disableRealRatio", false)) {
            return false;
        }
        return true;
    }

    public boolean publishCursorArray(String str, Cursor[] cursorArr, String str2) {
        String DATA_CURSOR = DataKey.DATA_CURSOR(str);
        if (!this.mBlackboard.isEmpty(DATA_CURSOR)) {
            StringCompat stringCompat = this.TAG;
            Log.w(stringCompat, "publish cursor skip. Cursor{" + DATA_CURSOR + "}");
            closeCursors(cursorArr);
            if (str2 == null) {
                return false;
            }
            this.mBlackboard.erase(str2);
            return false;
        }
        this.mBlackboard.publishIfEmpty("debug://TimeDoneQuery", Long.valueOf(System.currentTimeMillis()));
        this.mBlackboard.publish(DATA_CURSOR, cursorArr);
        if (str2 == null) {
            return true;
        }
        this.mBlackboard.erase(str2);
        return true;
    }

    private QueryParams createQueryParams(Bundle bundle, boolean z) {
        int loadSortBy;
        int loadSortBy2;
        QueryParams groupTypes = new QueryParams().setGroupTypes(GroupType.BURST);
        if (z && SimilarPhotoHelper.isSimilarPhotoMode() && PickerUtil.isNormalLaunchMode(this.mBlackboard)) {
            groupTypes.addGroupType(GroupType.SIMILAR).addGroupType(GroupType.SINGLE_TAKEN);
        } else if ((bundle == null || BundleWrapper.getBoolean(bundle, "with_group", true)) && PickerUtil.isNormalLaunchMode(this.mBlackboard)) {
            groupTypes.addGroupType(GroupType.SINGLE_TAKEN);
        }
        if (bundle != null) {
            groupTypes.setMediaTypeFilter(bundle.getString("filterMediaType", (String) null));
            int i2 = BundleWrapper.getInt(bundle, "filterStorageType", 0);
            if (i2 > 0) {
                QueryParams.DbStorageType dbStorageType = QueryParams.DbStorageType.All;
                groupTypes.setStorageTypes(new QueryParams.DbStorageType[]{dbStorageType, QueryParams.DbStorageType.LocalOnly, QueryParams.DbStorageType.CloudOnly, dbStorageType}[Math.min(3, i2)]);
            } else {
                groupTypes.setStorageTypes(BundleWrapper.getBoolean(bundle, "filterLocalOnly", false) ? QueryParams.DbStorageType.LocalOnly : QueryParams.DbStorageType.All);
            }
            if (BundleWrapper.getBoolean(bundle, "fromNow", false)) {
                groupTypes.setTimeLimit(System.currentTimeMillis());
            }
            if (BundleWrapper.getString(bundle, "showHidden", (String) null) != null) {
                groupTypes.setShowHidden(BundleWrapper.getBoolean(bundle, "showHidden", false));
            }
            String string = BundleWrapper.getString(bundle, "filterMinResolution", (String) null);
            if (string != null) {
                try {
                    String[] split = string.split("x");
                    int parseInt = Integer.parseInt(split[0]);
                    int parseInt2 = Integer.parseInt(split[1]);
                    groupTypes.minSize = Math.min(parseInt, parseInt2);
                    groupTypes.minResolution = parseInt * parseInt2;
                } catch (Exception e) {
                    a.r(e, new StringBuilder("createQueryParams#filterMinResolution failed. e="), this.TAG);
                }
            }
            if (bundle.get("id") != null) {
                boolean z3 = BundleWrapper.getBoolean(bundle, "withEmpty", false);
                int i7 = BundleWrapper.getInt(bundle, "count", 0);
                boolean z7 = BundleWrapper.getBoolean(bundle, "showHidden", true);
                groupTypes.setWithEmptyAlbums(z3);
                groupTypes.setShowHidden(z7);
                groupTypes.setAlbumCount(i7);
                int i8 = getAlbumIds(bundle)[0];
                groupTypes.addAlbumId(i8);
                if (!(i8 == 0 || (loadSortBy2 = GalleryPreference.getInstance().loadSortBy(String.valueOf(i8), 12)) == 12)) {
                    groupTypes.setSortBy(loadSortBy2);
                }
            } else if (bundle.get("ids") != null) {
                if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                    String string2 = bundle.getString("mergedAlbumId", (String) null);
                    if (!TextUtils.isEmpty(string2) && (loadSortBy = GalleryPreference.getInstance().loadSortBy(string2, 12)) != 12) {
                        groupTypes.setSortBy(loadSortBy);
                    }
                }
                groupTypes.addAlbumIds(getAlbumIds(bundle));
            }
        }
        return groupTypes;
    }
}
