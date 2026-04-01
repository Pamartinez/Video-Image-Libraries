package com.samsung.android.gallery.module.publisher;

import A.a;
import L5.b;
import M9.c;
import M9.h;
import M9.i;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.LocationApi;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.CollectionCursor;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MapDataPublisher extends ListDataPublisher {
    public MapDataPublisher(Blackboard blackboard) {
        super(blackboard);
    }

    private QueryParams getMapQueryParams(Bundle bundle) {
        QueryParams queryParams = new QueryParams();
        String string = BundleWrapper.getString(bundle, "dataKey", "");
        if (!TextUtils.isEmpty(string)) {
            long currentTimeMillis = System.currentTimeMillis();
            MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(string, true);
            List<Long> fileIds = open.getFileIds();
            if (fileIds != null && !fileIds.isEmpty()) {
                queryParams.setFileIds(TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, fileIds));
            }
            open.close();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "getMapQueryParams (getFileIds) : " + Logger.vt(currentTimeMillis));
        }
        int i2 = BundleWrapper.getInt(bundle, "id", 0);
        if (i2 != 0) {
            if (BucketUtils.isFavourite(i2)) {
                queryParams.setIncludeFavorite();
            } else if (BucketUtils.isVideos(i2)) {
                queryParams.setMediaTypeFilter(MediaFilterType.VIDEO_ONLY.toString());
            } else {
                queryParams.addAlbumId(i2);
            }
        }
        String string2 = BundleWrapper.getString(bundle, "ids", "");
        if (!TextUtils.isEmpty(string2)) {
            queryParams.addAlbumIds((Collection<Integer>) (Collection) Arrays.stream(string2.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new b(10)).map(new e(18)).collect(Collectors.toList()));
        }
        String string3 = bundle.getString("sort_by", "");
        if (!TextUtils.isEmpty(string3)) {
            queryParams.setSortBy(Integer.parseInt(string3));
        }
        return queryParams;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$queryFilteredMapData$2(Cursor[] cursorArr, QueryParams queryParams, String str, long j2, long j3, String str2) {
        cursorArr[0] = new LocationApi(queryParams).getLocationFileCursor(str, j2, j3, str2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$queryFilteredMapData$3(Cursor[] cursorArr, QueryParams queryParams, String str, long j2, long j3, String str2) {
        cursorArr[7] = new LocationApi(queryParams).getLocationFileGpsCursor(str, j2, j3, str2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$queryMapData$0(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[0] = new LocationApi(queryParams).getGpsFileCursor();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$queryMapData$1(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[7] = new LocationApi(queryParams).getGpsFileGpsCursor();
    }

    private Cursor loadMapClusterFileIdCursor(QueryParams queryParams, String str) {
        int i2;
        Cursor query;
        String[] strArr;
        TimeTickLog timeTickLog = new TimeTickLog();
        StringBuilder sb2 = new StringBuilder();
        QueryParams dbKey = queryParams.clone().setDbKey(str);
        dbKey.setLimit(250000);
        CollectionCursor collectionCursor = new CollectionCursor("__absID");
        String fileIds = queryParams.getFileIds();
        if (!TextUtils.isEmpty(fileIds)) {
            i2 = fileIds.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX).length;
        } else {
            i2 = 0;
        }
        int i7 = i2 / 10000;
        int i8 = 0;
        while (true) {
            if (i8 > i7) {
                break;
            }
            if (!TextUtils.isEmpty(fileIds)) {
                int i10 = i8 * 10000;
                dbKey.setFileIds(String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, (CharSequence[]) Arrays.copyOfRange(fileIds.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX), i10, Math.min(i10 + 10000, i2))));
            }
            query = DbCompat.query(dbKey);
            try {
                timeTickLog.tick();
                if (query == null) {
                    break;
                } else if (!query.moveToFirst()) {
                    break;
                } else {
                    String string = query.getString(0);
                    sb2.append(string);
                    if (string != null) {
                        strArr = string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    } else {
                        strArr = new String[0];
                    }
                    collectionCursor.addAll(strArr, new b(11));
                    timeTickLog.tick();
                    query.close();
                    Log.d(this.TAG, "loadFileIdWithLocation load next", Integer.valueOf(i8));
                    i8++;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        String str2 = sb2.toString().hashCode() + "_" + collectionCursor.getCount();
        Bundle bundle = new Bundle();
        bundle.putString("dataHash", str2);
        collectionCursor.setExtras(bundle);
        C0212a.z(new Object[]{str2, timeTickLog}, new StringBuilder("loadFileIdWithLocation"), this.TAG);
        return collectionCursor;
        throw th;
    }

    /* access modifiers changed from: private */
    public void publishFilteredMapData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        String string = bundle.getString("cluster_map_date");
        long[] array = string != null ? Arrays.stream(string.split("-")).mapToLong(new E5.b(7)).toArray() : new long[]{-1, -1};
        Cursor[] queryFilteredMapData = queryFilteredMapData((String) null, array[0], array[1], (String) null, getMapQueryParams(bundle));
        a.y(new StringBuilder("publishFilteredMapData "), getCursorListInfo(queryFilteredMapData, currentTimeMillis), this.TAG);
        publishCursorArray("location://map/filtered", queryFilteredMapData);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00e3 A[Catch:{ all -> 0x0139 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00f0 A[Catch:{ all -> 0x0139 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f2 A[Catch:{ all -> 0x0139 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f5 A[Catch:{ all -> 0x0139 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00fc A[Catch:{ all -> 0x0139 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void publishMapClusterData2(java.lang.Object r17, android.os.Bundle r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            java.lang.String r2 = "publishMapClusterData2 "
            java.lang.String r3 = "publishMapClusterData2"
            com.samsung.android.gallery.support.trace.Trace.beginSection(r3)     // Catch:{ all -> 0x0139 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0139 }
            java.lang.String r5 = com.samsung.android.gallery.support.utils.BundleWrapper.getKey(r1)     // Catch:{ all -> 0x0139 }
            java.lang.String r5 = com.samsung.android.gallery.support.blackboard.key.CommandKey.DATA_REQUEST_TO_LOCATION(r5)     // Catch:{ all -> 0x0139 }
            java.lang.String r6 = "file_ids"
            r7 = 0
            java.lang.String r6 = r1.getString(r6, r7)     // Catch:{ all -> 0x0139 }
            java.lang.String r8 = "sub"
            java.lang.String r8 = r1.getString(r8, r7)     // Catch:{ all -> 0x0139 }
            java.lang.String r9 = "start_time"
            java.lang.String r9 = r1.getString(r9, r7)     // Catch:{ all -> 0x0139 }
            java.lang.String r10 = "end_time"
            java.lang.String r10 = r1.getString(r10, r7)     // Catch:{ all -> 0x0139 }
            java.lang.String r11 = "gps_range"
            java.lang.String r11 = r1.getString(r11, r7)     // Catch:{ all -> 0x0139 }
            java.lang.String r12 = "id"
            java.lang.String r12 = r1.getString(r12, r7)     // Catch:{ all -> 0x0139 }
            java.lang.String r13 = "ids"
            java.lang.String r13 = r1.getString(r13, r7)     // Catch:{ all -> 0x0139 }
            java.lang.String r14 = "sort_by"
            java.lang.String r1 = r1.getString(r14, r7)     // Catch:{ all -> 0x0139 }
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r7 = new com.samsung.android.gallery.database.dal.abstraction.query.QueryParams     // Catch:{ all -> 0x0139 }
            r7.<init>()     // Catch:{ all -> 0x0139 }
            boolean r14 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0139 }
            if (r14 != 0) goto L_0x0059
            r7.setFileIds(r6)     // Catch:{ all -> 0x0139 }
        L_0x0059:
            boolean r6 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0139 }
            if (r6 != 0) goto L_0x0062
            r7.setSubCategory(r8)     // Catch:{ all -> 0x0139 }
        L_0x0062:
            boolean r6 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x0139 }
            r14 = 1
            if (r6 != 0) goto L_0x007e
            boolean r6 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x0139 }
            if (r6 != 0) goto L_0x007e
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r6 = r7.setOrderImmutable(r14)     // Catch:{ all -> 0x0139 }
            long r14 = java.lang.Long.parseLong(r9)     // Catch:{ all -> 0x0139 }
            long r9 = java.lang.Long.parseLong(r10)     // Catch:{ all -> 0x0139 }
            r6.setCreationTimeLimit(r14, r9)     // Catch:{ all -> 0x0139 }
        L_0x007e:
            boolean r6 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x0139 }
            if (r6 != 0) goto L_0x0087
            r7.setGpsRange(r11)     // Catch:{ all -> 0x0139 }
        L_0x0087:
            boolean r6 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x0139 }
            r9 = 0
            if (r6 != 0) goto L_0x00b1
            int r6 = java.lang.Integer.parseInt(r12)     // Catch:{ all -> 0x0139 }
            boolean r10 = com.samsung.android.gallery.support.utils.BucketUtils.isFavourite(r6)     // Catch:{ all -> 0x0139 }
            if (r10 == 0) goto L_0x009c
            r7.setIncludeFavorite()     // Catch:{ all -> 0x0139 }
            goto L_0x00dc
        L_0x009c:
            boolean r10 = com.samsung.android.gallery.support.utils.BucketUtils.isVideos(r6)     // Catch:{ all -> 0x0139 }
            if (r10 == 0) goto L_0x00ad
            com.samsung.android.gallery.support.type.MediaFilterType r6 = com.samsung.android.gallery.support.type.MediaFilterType.VIDEO_ONLY     // Catch:{ all -> 0x0139 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0139 }
            r7.setMediaTypeFilter(r6)     // Catch:{ all -> 0x0139 }
            r6 = 1
            goto L_0x00dd
        L_0x00ad:
            r7.addAlbumId(r6)     // Catch:{ all -> 0x0139 }
            goto L_0x00dc
        L_0x00b1:
            boolean r6 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x0139 }
            if (r6 != 0) goto L_0x00dc
            java.lang.String r6 = ","
            java.lang.String[] r6 = r13.split(r6)     // Catch:{ all -> 0x0139 }
            java.util.stream.Stream r6 = java.util.Arrays.stream(r6)     // Catch:{ all -> 0x0139 }
            A8.b r10 = new A8.b     // Catch:{ all -> 0x0139 }
            r11 = 3
            r10.<init>(r11)     // Catch:{ all -> 0x0139 }
            java.util.stream.IntStream r6 = r6.mapToInt(r10)     // Catch:{ all -> 0x0139 }
            java.util.stream.Stream r6 = r6.boxed()     // Catch:{ all -> 0x0139 }
            java.util.stream.Collector r10 = java.util.stream.Collectors.toList()     // Catch:{ all -> 0x0139 }
            java.lang.Object r6 = r6.collect(r10)     // Catch:{ all -> 0x0139 }
            java.util.List r6 = (java.util.List) r6     // Catch:{ all -> 0x0139 }
            r7.addAlbumIds((java.util.Collection<java.lang.Integer>) r6)     // Catch:{ all -> 0x0139 }
        L_0x00dc:
            r6 = r9
        L_0x00dd:
            boolean r10 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0139 }
            if (r10 != 0) goto L_0x00ea
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x0139 }
            r7.setSortBy(r1)     // Catch:{ all -> 0x0139 }
        L_0x00ea:
            int r1 = r7.getAlbumIdCount()     // Catch:{ all -> 0x0139 }
            if (r1 <= 0) goto L_0x00f2
            r14 = 1
            goto L_0x00f3
        L_0x00f2:
            r14 = r9
        L_0x00f3:
            if (r8 == 0) goto L_0x00fc
            java.lang.String r1 = "mp://Location/fileIds"
            android.database.Cursor r1 = r0.loadFileIdWithLocation(r7, r1)     // Catch:{ all -> 0x0139 }
            goto L_0x010c
        L_0x00fc:
            if (r6 == 0) goto L_0x0101
            java.lang.String r1 = com.samsung.android.gallery.database.dal.abstraction.DbKey.VIRTUAL_ALBUM_VIDEO_FILE_IDS_ORDERED     // Catch:{ all -> 0x0139 }
            goto L_0x0108
        L_0x0101:
            if (r14 == 0) goto L_0x0106
            java.lang.String r1 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALBUM_FILE_IDS_ORDERED     // Catch:{ all -> 0x0139 }
            goto L_0x0108
        L_0x0106:
            java.lang.String r1 = com.samsung.android.gallery.database.dal.abstraction.DbKey.TIMELINE_FILE_IDS_ORDERED     // Catch:{ all -> 0x0139 }
        L_0x0108:
            android.database.Cursor r1 = r0.loadMapClusterFileIdCursor(r7, r1)     // Catch:{ all -> 0x0139 }
        L_0x010c:
            com.samsung.android.gallery.module.publisher.retrieval.SQLiteRetrieval r6 = new com.samsung.android.gallery.module.publisher.retrieval.SQLiteRetrieval     // Catch:{ all -> 0x0139 }
            r6.<init>(r7)     // Catch:{ all -> 0x0139 }
            android.database.Cursor[] r1 = r6.getTimelineFileIds(r1)     // Catch:{ all -> 0x0139 }
            com.samsung.android.gallery.support.utils.StringCompat r6 = r0.TAG     // Catch:{ all -> 0x0139 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0139 }
            r8.<init>(r2)     // Catch:{ all -> 0x0139 }
            r8.append(r7)     // Catch:{ all -> 0x0139 }
            java.lang.String r2 = " "
            r8.append(r2)     // Catch:{ all -> 0x0139 }
            java.lang.String r2 = r0.getCursorListInfo(r1, r3)     // Catch:{ all -> 0x0139 }
            r8.append(r2)     // Catch:{ all -> 0x0139 }
            java.lang.String r2 = r8.toString()     // Catch:{ all -> 0x0139 }
            com.samsung.android.gallery.support.utils.Log.p(r6, r2)     // Catch:{ all -> 0x0139 }
            r0.publishCursorArray(r5, r1)     // Catch:{ all -> 0x0139 }
            com.samsung.android.gallery.support.trace.Trace.endSection()
            return
        L_0x0139:
            r0 = move-exception
            com.samsung.android.gallery.support.trace.Trace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.publisher.MapDataPublisher.publishMapClusterData2(java.lang.Object, android.os.Bundle):void");
    }

    /* access modifiers changed from: private */
    public void publishMapData(Object obj, Bundle bundle) {
        Cursor[] cursorArr;
        MapDataPublisher mapDataPublisher;
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = BundleWrapper.getBoolean(bundle, "no_locality");
        QueryParams mapQueryParams = getMapQueryParams(bundle);
        if (z) {
            cursorArr = queryMapData(mapQueryParams);
            mapDataPublisher = this;
        } else {
            mapDataPublisher = this;
            cursorArr = mapDataPublisher.queryFilteredMapData((String) null, -1, -1, (String) null, mapQueryParams);
        }
        a.y(new StringBuilder("publishMapData "), mapDataPublisher.getCursorListInfo(cursorArr, currentTimeMillis), mapDataPublisher.TAG);
        mapDataPublisher.publishCursorArray("location://map", cursorArr);
    }

    private Cursor[] queryFilteredMapData(String str, long j2, long j3, String str2, QueryParams queryParams) {
        Cursor[] cursorArr = new Cursor[9];
        String str3 = str;
        long j8 = j2;
        long j10 = j3;
        String str4 = str2;
        QueryParams queryParams2 = queryParams;
        new LatchBuilder("FilteredMapData").setCurrent(new i(cursorArr, queryParams2, str3, j8, j10, str4, 0)).addWorker(new i(cursorArr, queryParams2, str3, j8, j10, str4, 1)).start();
        return cursorArr;
    }

    private Cursor[] queryMapData(QueryParams queryParams) {
        Cursor[] cursorArr = new Cursor[9];
        new LatchBuilder("MapData").setCurrent(new c(cursorArr, 4, queryParams)).addWorker(new c(cursorArr, 5, queryParams)).start();
        return cursorArr;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://map"), new h(this, 0)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://map/filtered"), new h(this, 1)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://map/cluster/#"), new h(this, 2)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a8, code lost:
        if (r8 == null) goto L_0x00ab;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.database.Cursor loadFileIdWithLocation(com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r14, java.lang.String r15) {
        /*
            r13 = this;
            com.samsung.android.gallery.support.utils.TimeTickLog r0 = new com.samsung.android.gallery.support.utils.TimeTickLog
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r2 = r14.clone()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r15 = r2.setDbKey(r15)
            com.samsung.android.gallery.support.utils.CollectionCursor r2 = new com.samsung.android.gallery.support.utils.CollectionCursor
            java.lang.String r3 = "__absID"
            r2.<init>((java.lang.String) r3)
            java.lang.String r14 = r14.getFileIds()
            boolean r3 = android.text.TextUtils.isEmpty(r14)
            java.lang.String r4 = ","
            r5 = 0
            if (r3 != 0) goto L_0x002c
            java.lang.String[] r3 = r14.split(r4)
            int r3 = r3.length
            goto L_0x002d
        L_0x002c:
            r3 = r5
        L_0x002d:
            if (r3 <= 0) goto L_0x0032
            r6 = 10000(0x2710, float:1.4013E-41)
            goto L_0x0035
        L_0x0032:
            r6 = 250000(0x3d090, float:3.50325E-40)
        L_0x0035:
            r15.setLimit(r6)
            r6 = r5
        L_0x0039:
            if (r3 <= 0) goto L_0x0054
            int r7 = r6 * 10000
            int r8 = r7 + 10000
            java.lang.String[] r9 = r14.split(r4)
            int r8 = java.lang.Math.min(r8, r3)
            java.lang.Object[] r7 = java.util.Arrays.copyOfRange(r9, r7, r8)
            java.lang.CharSequence[] r7 = (java.lang.CharSequence[]) r7
            java.lang.String r7 = java.lang.String.join(r4, r7)
            r15.setFileIds(r7)
        L_0x0054:
            int r7 = r15.getLimitSize()
            int r8 = r6 * r7
            r15.setLimit(r8, r7)
            android.database.Cursor r8 = com.samsung.android.gallery.database.dal.DbCompat.query((com.samsung.android.gallery.database.dal.abstraction.query.QueryParams) r15)
            r0.tick()     // Catch:{ all -> 0x00a6 }
            if (r8 == 0) goto L_0x00a8
            boolean r9 = r8.moveToFirst()     // Catch:{ all -> 0x00a6 }
            if (r9 != 0) goto L_0x006d
            goto L_0x00a8
        L_0x006d:
            r9 = r5
        L_0x006e:
            java.lang.String r10 = r8.getString(r5)     // Catch:{ all -> 0x00a6 }
            long r11 = java.lang.Long.parseLong(r10)     // Catch:{ all -> 0x00a6 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x00a6 }
            r2.add(r11)     // Catch:{ all -> 0x00a6 }
            r1.append(r10)     // Catch:{ all -> 0x00a6 }
            int r9 = r9 + 1
            boolean r10 = r8.moveToNext()     // Catch:{ all -> 0x00a6 }
            if (r10 != 0) goto L_0x006e
            r0.tick()     // Catch:{ all -> 0x00a6 }
            if (r9 >= r7) goto L_0x0091
        L_0x008d:
            r8.close()
            goto L_0x00ab
        L_0x0091:
            r8.close()
            com.samsung.android.gallery.support.utils.StringCompat r7 = r13.TAG
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            java.lang.String r9 = "loadFileIdWithLocation load next"
            com.samsung.android.gallery.support.utils.Log.d(r7, r9, r8)
            int r6 = r6 + 1
            goto L_0x0039
        L_0x00a6:
            r13 = move-exception
            goto L_0x00e9
        L_0x00a8:
            if (r8 == 0) goto L_0x00ab
            goto L_0x008d
        L_0x00ab:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = r1.toString()
            int r15 = r15.hashCode()
            r14.append(r15)
            java.lang.String r15 = "_"
            r14.append(r15)
            int r15 = r2.getCount()
            r14.append(r15)
            java.lang.String r14 = r14.toString()
            android.os.Bundle r15 = new android.os.Bundle
            r15.<init>()
            java.lang.String r1 = "dataHash"
            r15.putString(r1, r14)
            r2.setExtras(r15)
            com.samsung.android.gallery.support.utils.StringCompat r13 = r13.TAG
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r1 = "loadFileIdWithLocation"
            r15.<init>(r1)
            java.lang.Object[] r14 = new java.lang.Object[]{r14, r0}
            i.C0212a.z(r14, r15, r13)
            return r2
        L_0x00e9:
            if (r8 == 0) goto L_0x00f3
            r8.close()     // Catch:{ all -> 0x00ef }
            goto L_0x00f3
        L_0x00ef:
            r14 = move-exception
            r13.addSuppressed(r14)
        L_0x00f3:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.publisher.MapDataPublisher.loadFileIdWithLocation(com.samsung.android.gallery.database.dal.abstraction.query.QueryParams, java.lang.String):android.database.Cursor");
    }
}
