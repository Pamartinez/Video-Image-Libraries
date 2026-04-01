package com.samsung.android.gallery.module.dataset;

import A.a;
import E3.c;
import N2.j;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.text.TextUtils;
import c0.C0086a;
import com.google.gson.reflect.TypeToken;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataEntire;
import com.samsung.android.gallery.module.dataset.tables.ClusterIndexer;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.dataset.tables.DataTable;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.module.dataset.tables.RealRatioIndexer;
import com.samsung.android.gallery.module.dataset.tables.SpanIndexer;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.CollectionCursor;
import com.samsung.android.gallery.support.utils.GsonTool;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.Def;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataTimeline2 extends MediaDataTimeline {
    private static final String[] COLUMNS = {"__absID"};
    private final Semaphore IDS_LOCK = new Semaphore(1);
    private boolean isQueryOnDemand = false;
    private String mDataHash = null;
    private String mDataRequestPended = null;
    private MediaItem mEmptyItem;
    private final ArrayList<Long> mListFileIds = new ArrayList<>();
    private ArrayList<MediaItem> remoteItems;

    public MediaDataTimeline2(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private HashMap<Long, MediaItem> buildTempDataMap(String str, BooleanSupplier booleanSupplier, Blackboard blackboard, String str2) {
        HashMap<Long, MediaItem> hashMap = new HashMap<>();
        QueryParams groupTypes = new QueryParams(DbKey.ALL_PICTURES).setGroupTypes(GroupType.BURST);
        boolean isNormalLaunchMode = PickerUtil.isNormalLaunchMode(blackboard);
        boolean isTimeline = LocationKey.isTimeline(str2);
        boolean isAlbumPictures = LocationKey.isAlbumPictures(str2);
        boolean isVirtualPictures = LocationKey.isVirtualPictures(str2);
        if (isNormalLaunchMode && SimilarPhotoHelper.isSimilarPhotoMode() && isTimeline) {
            groupTypes.addGroupType(GroupType.SIMILAR).addGroupType(GroupType.SINGLE_TAKEN);
        } else if (isNormalLaunchMode && (isTimeline || isAlbumPictures || isVirtualPictures)) {
            if (BucketUtils.isFavourite(ArgumentsUtil.getArgValue(this.mLocationReference, "id", -1))) {
                groupTypes.setDbKey(DbKey.ALL_PICTURES_NO_GROUP);
            } else {
                groupTypes.addGroupType(GroupType.SINGLE_TAKEN);
            }
        }
        groupTypes.setFileIds(str);
        if (LocationKey.isAlbumPictures(this.mLocationReference)) {
            int argValue = ArgumentsUtil.getArgValue(this.mLocationReference, "id", -1);
            int argValue2 = ArgumentsUtil.getArgValue(this.mLocationReference, "mergedAlbumId", -1);
            if (argValue2 != -1) {
                argValue = argValue2;
            }
            groupTypes.setParentAlbumId(argValue);
        }
        if (ArgumentsUtil.getArgValue(getLocationReference(), "showHidden", false)) {
            groupTypes.setShowHidden(true);
        }
        Cursor query = DbCompat.query(groupTypes);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    while (true) {
                        MediaItem load = MediaItemLoader.load(query);
                        hashMap.put(Long.valueOf(load.getFileId()), load);
                        if (!query.moveToNext() || (booleanSupplier != null && booleanSupplier.getAsBoolean())) {
                            break;
                        }
                    }
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        return hashMap;
        throw th;
    }

    private int calculateOnDemandQueryStartingPosition(int i2) {
        int max = Math.max(0, i2 - 200);
        while (readCache(max) != null) {
            max++;
        }
        return max;
    }

    private ArrayList<Long> createOrderedIdList(StringJoiner stringJoiner, int i2, ArrayList<Long> arrayList) {
        int i7;
        ArrayList<Long> arrayList2 = new ArrayList<>();
        int i8 = 0;
        while (true) {
            if (i8 >= 400 || (i7 = i2 + i8) >= arrayList.size()) {
                break;
            }
            Long l = arrayList.get(i7);
            if (l == null) {
                Log.e((CharSequence) this.TAG, "preLoad no file id ", Integer.valueOf(i2), Integer.valueOf(i8));
                break;
            }
            stringJoiner.add("" + l);
            arrayList2.add(l);
            i8++;
        }
        return arrayList2;
    }

    private String[] getDataIdListFromCursor(Cursor cursor, StringBuilder sb2, TimeTickLog timeTickLog) {
        String str;
        String string = cursor.getString(0);
        if (string == null) {
            Log.e(this.TAG, "onDemand : cursor {null value}");
            return new String[0];
        }
        sb2.append(string);
        String[] split = string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        StringCompat stringCompat = this.TAG;
        StringBuilder sb3 = new StringBuilder("onDemand : cursor {size=");
        sb3.append(split.length);
        if (split.length > 0) {
            str = ",first=" + split[0] + ",last=" + split[split.length - 1];
        } else {
            str = "";
        }
        sb3.append(str);
        sb3.append("} +");
        sb3.append(timeTickLog.getElapsedTime());
        Log.d(stringCompat, sb3.toString());
        return split;
    }

    private MediaItem getEmptyItem() {
        if (this.mEmptyItem == null) {
            this.mEmptyItem = MediaItemBuilder.createEmpty();
        }
        return this.mEmptyItem;
    }

    private boolean hasFileIdsCursor(Cursor[] cursorArr) {
        if (cursorArr.length <= 5 || cursorArr[5] == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$beginSwapProcessing$4() {
        String str = this.mDataRequestPended;
        if (str != null) {
            requestData(str);
            this.mDataRequestPended = null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$read$1(MediaItem[] mediaItemArr, int i2, String str) {
        try {
            mediaItemArr[0] = readOnDemand(i2);
        } catch (Exception e) {
            String dataHash = getDataHash();
            Log.w((CharSequence) this.TAG, "onDemand read fail on swap", str, dataHash);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(dataHash) && str.equals(dataHash)) {
                throw e;
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$readOnDemand$2(MediaItem[] mediaItemArr, MediaItem mediaItem) {
        mediaItemArr[0] = mediaItem;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$readOnDemand$3() {
        return false;
    }

    private void loadFileIds(Cursor[] cursorArr, MediaDataEntire.Candidates candidates) {
        if (hasFileIdsCursor(cursorArr)) {
            Cursor cursor = cursorArr[5];
            TimeTickLog timeTickLog = new TimeTickLog("onDemand : build id list " + cursor.getCount());
            long currentTimeMillis = System.currentTimeMillis();
            StringBuilder sb2 = new StringBuilder();
            if (cursor.moveToFirst()) {
                do {
                    for (String parseLong : getDataIdListFromCursor(cursor, sb2, timeTickLog)) {
                        candidates.listFileIds.add(Long.valueOf(Long.parseLong(parseLong)));
                    }
                    Log.d(this.TAG, j.g(new StringBuilder("onDemand : build id list onProgress : "), candidates.listFileIds), " +" + (System.currentTimeMillis() - currentTimeMillis));
                } while (cursor.moveToNext());
            } else {
                Log.e(this.TAG, "onDemand : build list fail");
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2.toString().hashCode());
            sb3.append("_");
            candidates.dataHash = j.g(sb3, candidates.listFileIds);
            Log.d(this.TAG, "onDemand : build id list done : " + candidates.dataHash, " +" + (System.currentTimeMillis() - currentTimeMillis));
            timeTickLog.tock(-1);
        }
    }

    private void preLoad(int i2, DataTable dataTable, MediaDataEntire.Candidates candidates, Blackboard blackboard, String str) {
        int i7 = i2;
        MediaDataEntire.Candidates candidates2 = candidates;
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        int i8 = 0;
        int max = Math.max(0, i7 - 200);
        ArrayList<Long> createOrderedIdList = createOrderedIdList(stringJoiner, max, candidates2.listFileIds);
        int length = stringJoiner.length();
        if (length > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            HashMap<Long, MediaItem> buildTempDataMapInternal = buildTempDataMapInternal(stringJoiner.toString(), (BooleanSupplier) null, blackboard, str);
            while (true) {
                if (i8 >= createOrderedIdList.size()) {
                    break;
                }
                Long l = createOrderedIdList.get(i8);
                long longValue = l.longValue();
                MediaItem mediaItem = buildTempDataMapInternal.get(l);
                int i10 = max + i8;
                try {
                    if (candidates2.listFileIds.get(i10).longValue() != longValue) {
                        Log.e(this.TAG, "preLoad id verify failed");
                        break;
                    }
                    if (mediaItem != null) {
                        dataTable.set(i10, mediaItem);
                    } else {
                        DataTable dataTable2 = dataTable;
                        StringCompat stringCompat = this.TAG;
                        Log.e((CharSequence) stringCompat, "preLoad null: " + i10 + " for " + i7, l, Integer.valueOf(buildTempDataMapInternal.size()));
                    }
                    i8++;
                } catch (Exception e) {
                    a.r(e, new StringBuilder("preLoad fail get id. e="), this.TAG);
                }
            }
            createOrderedIdList.clear();
            buildTempDataMapInternal.clear();
            StringCompat stringCompat2 = this.TAG;
            Log.d(stringCompat2, "preLoad +" + (System.currentTimeMillis() - currentTimeMillis), Integer.valueOf(length));
            return;
        }
        Log.e(this.TAG, "preLoad load fail ");
    }

    /* JADX WARNING: Removed duplicated region for block: B:73:0x023f  */
    /* JADX WARNING: Removed duplicated region for block: B:87:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readAsyncOnDemand(int r26, com.samsung.android.gallery.module.dataset.MediaData.OnDataReadListener r27, java.util.function.BooleanSupplier r28) {
        /*
            r25 = this;
            r1 = r25
            r2 = r26
            r3 = r27
            r0 = r28
            java.lang.String r4 = "msec"
            java.lang.String r5 = "from -"
            java.lang.String r6 = "readAsyncOnDemand null: "
            long r7 = java.lang.System.currentTimeMillis()
            java.lang.String r9 = r1.mDataHash
            com.samsung.android.gallery.support.utils.PocFeatures r10 = com.samsung.android.gallery.support.utils.PocFeatures.WifiGalleryClient
            boolean r10 = com.samsung.android.gallery.support.utils.PocFeatures.isEnabled(r10)
            if (r10 == 0) goto L_0x0038
            java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem> r10 = r1.remoteItems
            if (r10 == 0) goto L_0x0038
            java.util.Iterator r10 = r10.iterator()
            r12 = 0
        L_0x0025:
            boolean r13 = r10.hasNext()
            if (r13 == 0) goto L_0x0038
            java.lang.Object r13 = r10.next()
            com.samsung.android.gallery.module.data.MediaItem r13 = (com.samsung.android.gallery.module.data.MediaItem) r13
            int r14 = r12 + 1
            r1.putMediaItem(r12, r13)
            r12 = r14
            goto L_0x0025
        L_0x0038:
            com.samsung.android.gallery.module.data.MediaItem r10 = r25.readCache(r26)
            if (r10 == 0) goto L_0x0042
            r3.onDataReadCompleted(r10)
            return
        L_0x0042:
            if (r0 == 0) goto L_0x025a
            boolean r10 = r0.getAsBoolean()
            if (r10 != 0) goto L_0x025a
            java.util.StringJoiner r10 = new java.util.StringJoiner
            java.lang.String r12 = ","
            r10.<init>(r12)
            int r12 = r25.calculateOnDemandQueryStartingPosition(r26)
            java.util.ArrayList<java.lang.Long> r13 = r1.mListFileIds
            java.util.ArrayList r13 = r1.createOrderedIdList(r10, r12, r13)
            int r14 = r10.length()
            if (r14 <= 0) goto L_0x0253
            long r14 = java.lang.System.currentTimeMillis()
            java.lang.String r11 = r10.toString()
            r16 = r7
            com.samsung.android.gallery.support.blackboard.Blackboard r7 = r1.mBlackboard
            java.lang.String r8 = r1.getLocationKey()
            java.util.HashMap r7 = r1.buildTempDataMapInternal(r11, r0, r7, r8)
            boolean r8 = r0.getAsBoolean()
            if (r8 != 0) goto L_0x0231
            r8 = 0
        L_0x007c:
            int r11 = r13.size()
            if (r8 >= r11) goto L_0x0221
            java.lang.Object r11 = r13.get(r8)
            java.lang.Long r11 = (java.lang.Long) r11
            long r18 = r11.longValue()
            java.lang.Object r20 = r7.get(r11)
            r21 = r8
            r8 = r20
            com.samsung.android.gallery.module.data.MediaItem r8 = (com.samsung.android.gallery.module.data.MediaItem) r8
            r20 = r12
            int r12 = r20 + r21
            boolean r22 = r0.getAsBoolean()     // Catch:{ Exception -> 0x00aa }
            if (r9 == 0) goto L_0x00ad
            java.lang.String r0 = r1.mDataHash     // Catch:{ Exception -> 0x00aa }
            boolean r0 = r9.equals(r0)     // Catch:{ Exception -> 0x00aa }
            if (r0 != 0) goto L_0x00ad
            r0 = 1
            goto L_0x00ae
        L_0x00aa:
            r0 = move-exception
            goto L_0x01b9
        L_0x00ad:
            r0 = 0
        L_0x00ae:
            if (r22 != 0) goto L_0x00b2
            if (r0 == 0) goto L_0x00b6
        L_0x00b2:
            r23 = r14
            goto L_0x01a5
        L_0x00b6:
            if (r8 == 0) goto L_0x012e
            r1.putMediaItem(r12, r8)     // Catch:{ Exception -> 0x00aa }
            java.util.ArrayList<java.lang.Long> r0 = r1.mListFileIds     // Catch:{ Exception -> 0x0116 }
            java.lang.Object r0 = r0.get(r12)     // Catch:{ Exception -> 0x0116 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ Exception -> 0x0116 }
            long r22 = r0.longValue()     // Catch:{ Exception -> 0x0116 }
            int r0 = (r22 > r18 ? 1 : (r22 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x011a
            int r0 = r2 - r20
            java.lang.Object r0 = r13.get(r0)     // Catch:{ Exception -> 0x0116 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ Exception -> 0x0116 }
            r0.getClass()     // Catch:{ Exception -> 0x0116 }
            java.lang.Object r6 = r7.get(r0)     // Catch:{ Exception -> 0x0116 }
            com.samsung.android.gallery.module.data.MediaItem r6 = (com.samsung.android.gallery.module.data.MediaItem) r6     // Catch:{ Exception -> 0x0116 }
            com.samsung.android.gallery.support.utils.StringCompat r8 = r1.TAG     // Catch:{ Exception -> 0x0116 }
            java.lang.String r9 = "onDemand id verify failed"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r21)     // Catch:{ Exception -> 0x0116 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0116 }
            r11.<init>()     // Catch:{ Exception -> 0x0116 }
            r11.append(r5)     // Catch:{ Exception -> 0x0116 }
            long r18 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0116 }
            r23 = r14
            long r14 = r18 - r16
            r11.append(r14)     // Catch:{ Exception -> 0x0114 }
            r11.append(r4)     // Catch:{ Exception -> 0x0114 }
            java.lang.String r4 = r11.toString()     // Catch:{ Exception -> 0x0114 }
            java.lang.Object[] r0 = new java.lang.Object[]{r10, r0, r6, r4}     // Catch:{ Exception -> 0x0114 }
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r8, (java.lang.String) r9, (java.lang.Object[]) r0)     // Catch:{ Exception -> 0x0114 }
            com.samsung.android.gallery.module.dataset.tables.DataTable r0 = r1.mDataTable     // Catch:{ Exception -> 0x0114 }
            r0.remove(r12)     // Catch:{ Exception -> 0x0114 }
            r13.clear()     // Catch:{ Exception -> 0x0114 }
            r3.onDataReadCompleted(r6)     // Catch:{ Exception -> 0x0114 }
            r7.clear()     // Catch:{ Exception -> 0x0114 }
            return
        L_0x0114:
            r0 = move-exception
            goto L_0x0120
        L_0x0116:
            r0 = move-exception
            r23 = r14
            goto L_0x0120
        L_0x011a:
            r23 = r14
            r19 = r9
            goto L_0x0199
        L_0x0120:
            com.samsung.android.gallery.support.utils.StringCompat r4 = r1.TAG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "onDemand fail get id : "
            r5.<init>(r6)
            A.a.r(r0, r5, r4)
            goto L_0x0223
        L_0x012e:
            r23 = r14
            com.samsung.android.gallery.support.utils.StringCompat r0 = r1.TAG     // Catch:{ Exception -> 0x00aa }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00aa }
            r14.<init>()     // Catch:{ Exception -> 0x00aa }
            r14.append(r6)     // Catch:{ Exception -> 0x00aa }
            r14.append(r12)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r15 = " for "
            r14.append(r15)     // Catch:{ Exception -> 0x00aa }
            r14.append(r2)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x00aa }
            int r15 = r1.getCount()     // Catch:{ Exception -> 0x00aa }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ Exception -> 0x00aa }
            int r18 = r7.size()     // Catch:{ Exception -> 0x00aa }
            r19 = r9
            java.lang.Integer r9 = java.lang.Integer.valueOf(r18)     // Catch:{ Exception -> 0x00aa }
            java.lang.Object[] r9 = new java.lang.Object[]{r11, r15, r9}     // Catch:{ Exception -> 0x00aa }
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r0, (java.lang.String) r14, (java.lang.Object[]) r9)     // Catch:{ Exception -> 0x00aa }
            com.samsung.android.gallery.module.data.MediaItem r0 = r1.getEmptyItem()     // Catch:{ Exception -> 0x00aa }
            r1.putMediaItem(r12, r0)     // Catch:{ Exception -> 0x00aa }
            if (r12 != r2) goto L_0x0195
            com.samsung.android.gallery.support.utils.StringCompat r0 = r1.TAG     // Catch:{ Exception -> 0x00aa }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00aa }
            r9.<init>()     // Catch:{ Exception -> 0x00aa }
            r9.append(r6)     // Catch:{ Exception -> 0x00aa }
            r9.append(r10)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x00aa }
            com.samsung.android.gallery.support.utils.Log.e(r0, r9)     // Catch:{ Exception -> 0x00aa }
            com.samsung.android.gallery.support.utils.StringCompat r0 = r1.TAG     // Catch:{ Exception -> 0x00aa }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00aa }
            r9.<init>()     // Catch:{ Exception -> 0x00aa }
            r9.append(r6)     // Catch:{ Exception -> 0x00aa }
            r9.append(r7)     // Catch:{ Exception -> 0x00aa }
            java.lang.String r6 = r9.toString()     // Catch:{ Exception -> 0x00aa }
            com.samsung.android.gallery.support.utils.Log.e(r0, r6)     // Catch:{ Exception -> 0x00aa }
            goto L_0x0223
        L_0x0195:
            if (r12 <= r2) goto L_0x0199
            goto L_0x0223
        L_0x0199:
            int r8 = r21 + 1
            r0 = r28
            r9 = r19
            r12 = r20
            r14 = r23
            goto L_0x007c
        L_0x01a5:
            com.samsung.android.gallery.support.utils.StringCompat r6 = r1.TAG     // Catch:{ Exception -> 0x00aa }
            java.lang.String r9 = "readAsyncOnDemand failed by int/dc"
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r22)     // Catch:{ Exception -> 0x00aa }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ Exception -> 0x00aa }
            java.lang.Object[] r0 = new java.lang.Object[]{r10, r0}     // Catch:{ Exception -> 0x00aa }
            com.samsung.android.gallery.support.utils.Log.w((java.lang.CharSequence) r6, (java.lang.String) r9, (java.lang.Object[]) r0)     // Catch:{ Exception -> 0x00aa }
            goto L_0x0223
        L_0x01b9:
            com.samsung.android.gallery.support.utils.StringCompat r6 = r1.TAG
            java.lang.String r9 = "onDemand error: "
            java.lang.String r9 = c0.C0086a.i(r12, r9)
            int r10 = r1.getCount()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            int r11 = r7.size()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.Object[] r8 = new java.lang.Object[]{r10, r8, r11}
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r6, (java.lang.String) r9, (java.lang.Object[]) r8)
            boolean r6 = r0 instanceof java.lang.NullPointerException
            if (r6 != 0) goto L_0x01e6
            boolean r6 = r0 instanceof java.lang.IndexOutOfBoundsException
            if (r6 != 0) goto L_0x01e6
            java.lang.String r6 = r1.mDataHash
            if (r6 != 0) goto L_0x01e5
            goto L_0x01e6
        L_0x01e5:
            throw r0
        L_0x01e6:
            int r0 = r2 - r20
            java.lang.Object r0 = r13.get(r0)
            java.lang.Long r0 = (java.lang.Long) r0
            r0.getClass()
            r13.clear()
            java.lang.Object r2 = r7.get(r0)
            com.samsung.android.gallery.module.data.MediaItem r2 = (com.samsung.android.gallery.module.data.MediaItem) r2
            com.samsung.android.gallery.support.utils.StringCompat r1 = r1.TAG
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            long r8 = java.lang.System.currentTimeMillis()
            long r8 = r8 - r16
            r6.append(r8)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2, r4}
            java.lang.String r4 = "maybe destroyed"
            com.samsung.android.gallery.support.utils.Log.i(r1, r4, r0)
            r3.onDataReadCompleted(r2)
            r7.clear()
            return
        L_0x0221:
            r23 = r14
        L_0x0223:
            r13.clear()
            r7.clear()
            com.samsung.android.gallery.module.data.MediaItem r0 = r25.readCache(r26)
            r3.onDataReadCompleted(r0)
            goto L_0x0233
        L_0x0231:
            r23 = r14
        L_0x0233:
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r23
            r4 = 300(0x12c, double:1.48E-321)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x025a
            com.samsung.android.gallery.support.utils.StringCompat r0 = r1.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "readAsyncOnDemand#sluggish +"
            r1.<init>(r4)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.samsung.android.gallery.support.utils.Log.d(r0, r1)
            goto L_0x025a
        L_0x0253:
            com.samsung.android.gallery.support.utils.StringCompat r0 = r1.TAG
            java.lang.String r1 = "readAsyncOnDemand load fail "
            com.samsung.android.gallery.support.utils.Log.e(r0, r1)
        L_0x025a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataTimeline2.readAsyncOnDemand(int, com.samsung.android.gallery.module.dataset.MediaData$OnDataReadListener, java.util.function.BooleanSupplier):void");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Object, java.util.function.BooleanSupplier] */
    private MediaItem readOnDemand(int i2) {
        MediaItem[] mediaItemArr = new MediaItem[1];
        readAsyncOnDemand(i2, new C0614t(mediaItemArr), new Object());
        if (mediaItemArr[0] == null) {
            Log.e(this.TAG, "OnDemand : read fail");
        }
        return mediaItemArr[0];
    }

    private void swapFileIDs(MediaDataEntire.Candidates candidates) {
        try {
            if (this.IDS_LOCK.tryAcquire(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
                this.mListFileIds.clear();
                if (candidates.listFileIds.isEmpty()) {
                    Log.w(this.TAG, "file ids empty");
                }
                this.mListFileIds.addAll(candidates.listFileIds);
                this.mDataHash = candidates.dataHash + "_" + this.mDataTable.hashCode();
                StringCompat stringCompat = this.TAG;
                StringBuilder sb2 = new StringBuilder("swap hash ");
                sb2.append(this.mDataHash);
                Log.d(stringCompat, sb2.toString());
                this.IDS_LOCK.release();
                return;
            }
            Log.e(this.TAG, "swap file ids fail");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public /* bridge */ /* synthetic */ void acquireReadLock(String str) {
        super.acquireReadLock(str);
    }

    public boolean beginSwapProcessing() {
        if (this.mDataRequestPended == null) {
            return super.beginSwapProcessing();
        }
        runOnUiThread(new r(3, this), 300);
        return false;
    }

    public HashMap<Long, MediaItem> buildTempDataMapInternal(String str, BooleanSupplier booleanSupplier, Blackboard blackboard, String str2) {
        return buildTempDataMap(str, booleanSupplier, blackboard, str2);
    }

    public boolean checkDataMismatching(Cursor[] cursorArr, DataTable dataTable, ClusterTable[] clusterTableArr, MediaDataEntire.Candidates candidates) {
        ArrayList<Long> arrayList;
        boolean checkDataMismatching = super.checkDataMismatching(cursorArr, dataTable, clusterTableArr, candidates);
        if (!this.isQueryOnDemand || checkDataMismatching || candidates == null || (arrayList = candidates.listFileIds) == null || arrayList.size() == cursorArr[0].getCount()) {
            return checkDataMismatching;
        }
        StringBuilder sb2 = new StringBuilder("file id size not matched : ");
        C0086a.A(sb2, candidates.listFileIds, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(cursorArr[0].getCount());
        Log.e(this.TAG, sb2.toString());
        handleDataMisMatching("fileId", (ClusterTable) null);
        return true;
    }

    public void close() {
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("close {");
        sb2.append(this.mLocationKey);
        sb2.append(',');
        sb2.append(this.mRefCount.get() - 1);
        sb2.append('}');
        Log.d(stringCompat, sb2.toString());
        try {
            if (this.IDS_LOCK.tryAcquire(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
                if (this.mRefCount.decrementAndGet() == 0) {
                    onDestroy();
                }
                this.IDS_LOCK.release();
                return;
            }
            Log.e(this.TAG, "close fail");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public DataTable createDataTable(Cursor cursor) {
        boolean z = this.isQueryOnDemand;
        DataTable createDataTable = super.createDataTable(cursor);
        return z ? createDataTable.setQueryOnDemand() : createDataTable;
    }

    public int findPosition(long j2) {
        if (!this.isQueryOnDemand) {
            return super.findPosition(j2);
        }
        AtomicLong atomicLong = new AtomicLong(-1);
        new LatchBuilder("findPosition").addWorker(new c(atomicLong, j2, 4)).setTimeout(1000).start();
        return this.mListFileIds.indexOf(Long.valueOf(atomicLong.get()));
    }

    public int findPositionByFileId(long j2) {
        if (this.isQueryOnDemand) {
            return this.mListFileIds.indexOf(Long.valueOf(j2));
        }
        return super.findPositionByFileId(j2);
    }

    public /* bridge */ /* synthetic */ ArrayList getAllData() {
        return super.getAllData();
    }

    public /* bridge */ /* synthetic */ ClusterIndexer getClusterIndexer(int i2) {
        return super.getClusterIndexer(i2);
    }

    public /* bridge */ /* synthetic */ ClusterTable getClusterTable(int i2) {
        return super.getClusterTable(i2);
    }

    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public String getDataHash() {
        if (this.isQueryOnDemand) {
            return this.mDataHash;
        }
        return super.getDataHash();
    }

    public /* bridge */ /* synthetic */ int getDataVersion() {
        return super.getDataVersion();
    }

    public List<Long> getFileIds() {
        if (this.isQueryOnDemand) {
            return this.mListFileIds;
        }
        return super.getFileIds();
    }

    public /* bridge */ /* synthetic */ String getLocationKey() {
        return super.getLocationKey();
    }

    public /* bridge */ /* synthetic */ String getLocationReference() {
        return super.getLocationReference();
    }

    public int getLockSize(Cursor[] cursorArr) {
        if (hasFileIdsCursor(cursorArr)) {
            return super.getLockSize(cursorArr) - 1;
        }
        return super.getLockSize(cursorArr);
    }

    public /* bridge */ /* synthetic */ int getRealCount() {
        return super.getRealCount();
    }

    public /* bridge */ /* synthetic */ RealRatioIndexer getRealRatioIndexer() {
        return super.getRealRatioIndexer();
    }

    public /* bridge */ /* synthetic */ int getRefCount() {
        return super.getRefCount();
    }

    public /* bridge */ /* synthetic */ SpanIndexer getSpanIndexer() {
        super.getSpanIndexer();
        return null;
    }

    public boolean initDataTable() {
        return this.mDataTable.init(this.isQueryOnDemand);
    }

    public void initExtraTable(Cursor[] cursorArr, CountDownLatch countDownLatch, MediaDataEntire.Candidates candidates) {
        super.initExtraTable(cursorArr, countDownLatch, candidates);
        if (hasFileIdsCursor(cursorArr) && this.mDataTable != null && candidates != null && candidates.listFileIds != null) {
            swapFileIDs(candidates);
        }
    }

    public /* bridge */ /* synthetic */ boolean isDataAvailable() {
        return super.isDataAvailable();
    }

    public /* bridge */ /* synthetic */ boolean isFullyLoaded() {
        return super.isFullyLoaded();
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        if (this.remoteItems != null || !super.isListeningEvent(eventMessage)) {
            return false;
        }
        return true;
    }

    public /* bridge */ /* synthetic */ void notifyIndexingDone() {
        super.notifyIndexingDone();
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mListFileIds.clear();
        this.mDataHash = null;
        this.isQueryOnDemand = false;
    }

    public void onInitDone() {
        super.onInitDone();
    }

    public void onSwapDone(MediaDataEntire.Candidates candidates) {
        if (this.isQueryOnDemand) {
            swapFileIDs(candidates);
        }
    }

    public /* bridge */ /* synthetic */ MediaData open(String str, boolean z) {
        return super.open(str, z);
    }

    public void preloadMediaItemOnInit() {
        if (!DeviceConfig.UNIT_TEST && this.isQueryOnDemand && isFirstFakeLoaded()) {
            read(0);
        }
    }

    public void prepareSwap(MediaDataEntire.Candidates candidates, DataTable dataTable, int i2) {
        if (this.isQueryOnDemand) {
            DataTable dataTable2 = dataTable;
            int i7 = i2;
            preLoad(i7, dataTable2, candidates, this.mBlackboard, getLocationKey());
        }
    }

    public void preprocessCursors(Cursor[] cursorArr, MediaDataEntire.Candidates candidates) {
        if (hasFileIdsCursor(cursorArr)) {
            this.isQueryOnDemand = true;
            Bundle extras = cursorArr[5].getExtras();
            if (extras == null || !extras.containsKey("dataHash") || !(cursorArr[5] instanceof CollectionCursor)) {
                loadFileIds(cursorArr, candidates);
                if (candidates.listFileIds != null) {
                    TimeTickLog timeTickLog = new TimeTickLog(j.g(new StringBuilder("onDemand : build data cursor "), candidates.listFileIds));
                    MatrixCursor matrixCursor = new MatrixCursor(COLUMNS, candidates.listFileIds.size());
                    candidates.listFileIds.forEach(new O(3, matrixCursor));
                    cursorArr[0] = matrixCursor;
                    timeTickLog.tock(-1);
                    return;
                }
                Log.e(this.TAG, "fail create candidate ids");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            candidates.listFileIds = cursorArr[5].getRawData();
            candidates.dataHash = extras.getString("dataHash");
            cursorArr[0] = new CollectionCursor(cursorArr[5]);
            if (PocFeatures.isEnabled(PocFeatures.WifiGalleryClient)) {
                this.remoteItems = (ArrayList) GsonTool.fromJsonString(new TypeToken<ArrayList<MediaItem>>() {
                }, extras.getString("all"));
            }
            StringCompat stringCompat = this.TAG;
            C0212a.z(new Object[]{Integer.valueOf(candidates.listFileIds.size()), candidates.dataHash, Long.valueOf(currentTimeMillis)}, new StringBuilder("onDemand : build data cursor"), stringCompat);
        }
    }

    public void putMediaItem(int i2, MediaItem mediaItem) {
        if (this.isQueryOnDemand) {
            this.mDataTable.set(i2, mediaItem);
        }
    }

    public synchronized void reInit(String str) {
        this.mListFileIds.clear();
        this.mDataHash = null;
        this.isQueryOnDemand = false;
        super.reInit(str);
        this.mDataRequestPended = null;
    }

    public MediaItem read(int i2) {
        if (this.mListFileIds.isEmpty()) {
            return super.read(i2);
        }
        if (!ThreadUtil.isMainThread()) {
            return readOnDemand(i2);
        }
        MediaItem readCache = readCache(i2);
        if (readCache != null) {
            return readCache;
        }
        MediaItem[] mediaItemArr = new MediaItem[1];
        String dataHash = getDataHash();
        try {
            if (this.IDS_LOCK.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
                new LatchBuilder("onDemand MediaData.read").addWorker(new C0595c0(this, mediaItemArr, i2, dataHash, 2)).setTimeout(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS).start();
                this.IDS_LOCK.release();
            } else {
                Log.e((CharSequence) this.TAG, "read fail", Integer.valueOf(i2));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mediaItemArr[0];
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        if (this.mListFileIds.isEmpty()) {
            super.readAsync(i2, onDataReadListener, booleanSupplier);
        } else {
            readAsyncOnDemand(i2, onDataReadListener, booleanSupplier);
        }
    }

    public /* bridge */ /* synthetic */ MediaItem readCache(int i2) {
        return super.readCache(i2);
    }

    public /* bridge */ /* synthetic */ void register(MediaData.OnDataChangeListener onDataChangeListener) {
        super.register(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ void releaseReadLock(String str) {
        super.releaseReadLock(str);
    }

    public /* bridge */ /* synthetic */ void removeItemAt(int i2) {
        super.removeItemAt(i2);
    }

    public /* bridge */ /* synthetic */ void reopen(String str) {
        super.reopen(str);
    }

    public /* bridge */ /* synthetic */ void replaceItemAt(int i2, MediaItem mediaItem) {
        super.replaceItemAt(i2, mediaItem);
    }

    public void requestData(String str) {
        if (BlackboardUtils.publishDataRequestForce(this.mBlackboard, str)) {
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("requestData {");
            if (str.equals(this.mLocationReference)) {
                str = ArgumentsUtil.removeArgs(str);
            }
            sb2.append(str);
            sb2.append("}");
            Log.d(stringCompat, sb2.toString());
            return;
        }
        this.mDataRequestPended = str;
        Log.w((CharSequence) this.TAG, "requestData pended", str);
    }

    public /* bridge */ /* synthetic */ boolean supportDayCluster() {
        return super.supportDayCluster();
    }

    public /* bridge */ /* synthetic */ boolean supportMonthCluster() {
        return super.supportMonthCluster();
    }

    public /* bridge */ /* synthetic */ boolean supportMonthXsCluster() {
        return super.supportMonthXsCluster();
    }

    public /* bridge */ /* synthetic */ boolean supportYearCluster() {
        return super.supportYearCluster();
    }

    public /* bridge */ /* synthetic */ String toDebugString() {
        return super.toDebugString();
    }

    public String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ void unregister(MediaData.OnDataChangeListener onDataChangeListener) {
        super.unregister(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ void updateDataStampByPpp(MediaItem mediaItem) {
        super.updateDataStampByPpp(mediaItem);
    }

    public DataTable createDataTable(Cursor cursor, DefaultTable.OnLoadDoneListener onLoadDoneListener, int i2) {
        boolean z = this.isQueryOnDemand;
        DataTable createDataTable = super.createDataTable(cursor, onLoadDoneListener, i2);
        return z ? createDataTable.setQueryOnDemand() : createDataTable;
    }
}
