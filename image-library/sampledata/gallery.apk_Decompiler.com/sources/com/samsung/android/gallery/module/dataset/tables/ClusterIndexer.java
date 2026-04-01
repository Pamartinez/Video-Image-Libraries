package com.samsung.android.gallery.module.dataset.tables;

import A4.P;
import D6.a;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.support.utils.DataCounter;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterIndexer {
    private int mClusteredItemCount;
    protected int mCount;
    private int mDataCount;
    private long[] mDateTaken;
    private boolean mDayMerge;
    private int[] mScrollIndex;
    private ScrollText[] mScrollIndexTag;
    protected final ArrayList<Integer> mTimelineIdxList = new ArrayList<>();
    protected final ConcurrentHashMap<Integer, ClusterItem> mTimelineItemMap = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum COMPARE_RESULT {
        SKIP,
        MERGE,
        DIFF
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FeaturesHolder {
        static final boolean DAY_MERGE = PreferenceFeatures.isEnabled(PreferenceFeatures.DayMerge);
    }

    public ClusterIndexer(boolean z) {
        boolean z3;
        boolean z7 = FeaturesHolder.DAY_MERGE;
        this.mDayMerge = z7;
        if (!z7 || !z) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.mDayMerge = z3;
    }

    private ScrollText getDateLocationTag(ClusterItem clusterItem, int i2) {
        return new ScrollText().setDateTaken(clusterItem.getDateTaken()).setLocation(clusterItem.getLocation()).setDate(clusterItem.getDate());
    }

    private String[] getMergedAddress(ArrayList<MediaItem> arrayList) {
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        Iterator<MediaItem> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (sb2.length() > 0) {
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb3.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            }
            sb2.append(next.getLatitudeList());
            sb3.append(next.getLongitudeList());
        }
        return new String[]{sb2.toString(), sb3.toString()};
    }

    private boolean isInvalidDate(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getDateRaw() == null) {
            return true;
        }
        return false;
    }

    private boolean isMergeable(MediaItem mediaItem, MediaItem mediaItem2) {
        if (isInvalidDate(mediaItem) || isInvalidDate(mediaItem2) || mediaItem.getCount() != 1 || mediaItem2.getCount() != 1 || !mediaItem.getDateRaw().substring(0, 7).equals(mediaItem2.getDateRaw().substring(0, 7)) || TimeUtil.isInToday(mediaItem.getDateTaken())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getLog$1(StringBuilder sb2, Map.Entry entry) {
        sb2.append("key=");
        sb2.append(entry.getKey());
        sb2.append(",item=");
        sb2.append(entry.getValue());
        sb2.append(10);
    }

    private void reset(int i2) {
        this.mCount = i2;
        this.mScrollIndex = new int[i2];
        this.mDateTaken = new long[i2];
        this.mScrollIndexTag = new ScrollText[i2];
    }

    /* JADX WARNING: type inference failed for: r5v8, types: [com.samsung.android.gallery.module.dataset.tables.DateClusterItem] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void calculate(com.samsung.android.gallery.module.dataset.tables.ClusterTable r19, int r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            int r2 = r1.getLoadedCount()
            r0.reset(r2)
            r2 = r20
            r0.mDataCount = r2
            r2 = 0
            r0.mClusteredItemCount = r2
            int r3 = r0.mCount
            java.lang.String r4 = "ClusterIndexer"
            if (r3 <= 0) goto L_0x00e9
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            com.samsung.android.gallery.support.utils.DataCounter r3 = new com.samsung.android.gallery.support.utils.DataCounter
            r3.<init>()
            r7 = r2
            r13 = r7
            r14 = r13
            r15 = r14
            r5 = 0
        L_0x0027:
            int r8 = r0.mCount
            if (r13 >= r8) goto L_0x00e6
            if (r5 != 0) goto L_0x0032
            com.samsung.android.gallery.module.data.MediaItem r8 = r1.get(r13)
            goto L_0x0033
        L_0x0032:
            r8 = r5
        L_0x0033:
            if (r8 != 0) goto L_0x003c
            java.lang.String r8 = "calculate - currItem is null"
            com.samsung.android.gallery.support.utils.Log.w(r4, r8)
            goto L_0x00e2
        L_0x003c:
            if (r7 != 0) goto L_0x0042
            int r7 = r8.getCount()
        L_0x0042:
            r16 = r7
            boolean r7 = r0.mDayMerge
            r17 = 1
            if (r7 == 0) goto L_0x00ab
            java.lang.String r5 = r8.getLocation()
            if (r5 == 0) goto L_0x005b
            java.lang.String r7 = "null"
            boolean r7 = r5.equals(r7)
            if (r7 != 0) goto L_0x005b
            r3.add(r5)
        L_0x005b:
            int r5 = r13 + 1
            int r7 = r0.mCount
            if (r5 >= r7) goto L_0x0066
            com.samsung.android.gallery.module.data.MediaItem r5 = r0.getCurrentItem(r1, r5)
            goto L_0x0067
        L_0x0066:
            r5 = 0
        L_0x0067:
            boolean r7 = r0.isMergeable(r8, r5)
            if (r7 == 0) goto L_0x0077
            r6.add(r8)
            int r7 = r8.getCount()
            int r7 = r7 + r16
            goto L_0x00e2
        L_0x0077:
            r6.add(r8)
            java.lang.String[] r7 = r0.getMergedAddress(r6)
            r8 = r5
            com.samsung.android.gallery.module.dataset.tables.DateClusterItem r5 = new com.samsung.android.gallery.module.dataset.tables.DateClusterItem
            r9 = r8
            java.util.List r8 = r3.getSortedList()
            java.util.stream.Stream r10 = r6.stream()
            A8.b r11 = new A8.b
            r12 = 18
            r11.<init>(r12)
            java.util.stream.IntStream r10 = r10.mapToInt(r11)
            int r10 = r10.sum()
            r12 = r9
            r9 = r10
            r10 = r7[r2]
            r11 = r7[r17]
            r7 = 0
            r5.<init>((java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem>) r6, (int) r7, (java.util.List<java.lang.String>) r8, (int) r9, (java.lang.String) r10, (java.lang.String) r11)
            r6.clear()
            r3.clear()
            r8 = r5
            r5 = r12
        L_0x00ab:
            int[] r7 = r0.mScrollIndex
            r7[r14] = r15
            java.util.concurrent.ConcurrentHashMap<java.lang.Integer, com.samsung.android.gallery.module.data.ClusterItem> r7 = r0.mTimelineItemMap
            java.lang.Integer r9 = java.lang.Integer.valueOf(r15)
            r7.put(r9, r8)
            java.util.ArrayList<java.lang.Integer> r7 = r0.mTimelineIdxList
            java.lang.Integer r9 = java.lang.Integer.valueOf(r15)
            r7.add(r9)
            long[] r7 = r0.mDateTaken
            int r9 = r0.mCount
            int r9 = r9 + -1
            int r9 = r9 - r14
            long r10 = r8.getDateTaken()
            r7[r9] = r10
            com.samsung.android.gallery.module.data.ScrollText[] r7 = r0.mScrollIndexTag
            com.samsung.android.gallery.module.data.ScrollText r8 = r0.getDateLocationTag(r8, r2)
            r7[r14] = r8
            int r7 = r16 + 1
            int r15 = r15 + r7
            int r7 = r0.mClusteredItemCount
            int r7 = r7 + r16
            r0.mClusteredItemCount = r7
            int r14 = r14 + 1
            r7 = r2
        L_0x00e2:
            int r13 = r13 + 1
            goto L_0x0027
        L_0x00e6:
            r0.mCount = r14
            r2 = r14
        L_0x00e9:
            java.util.concurrent.ConcurrentHashMap<java.lang.Integer, com.samsung.android.gallery.module.data.ClusterItem> r1 = r0.mTimelineItemMap
            int r1 = r1.size()
            java.util.ArrayList<java.lang.Integer> r3 = r0.mTimelineIdxList
            int r3 = r3.size()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "calculate {"
            r5.<init>(r6)
            int r6 = r0.mCount
            r5.append(r6)
            int r6 = r0.mCount
            java.lang.String r7 = ""
            if (r6 == r1) goto L_0x010e
            java.lang.String r6 = ",m="
            java.lang.String r1 = c0.C0086a.i(r1, r6)
            goto L_0x010f
        L_0x010e:
            r1 = r7
        L_0x010f:
            r5.append(r1)
            int r1 = r0.mCount
            if (r1 == r3) goto L_0x011d
            java.lang.String r1 = ",l="
            java.lang.String r1 = c0.C0086a.i(r3, r1)
            goto L_0x011e
        L_0x011d:
            r1 = r7
        L_0x011e:
            r5.append(r1)
            java.lang.String r1 = "}"
            r5.append(r1)
            int r3 = r0.mClusteredItemCount
            int r6 = r0.mDataCount
            if (r3 == r6) goto L_0x0144
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r6 = " InvalidCluster{"
            r3.<init>(r6)
            int r6 = r0.mDataCount
            r3.append(r6)
            java.lang.String r6 = ","
            r3.append(r6)
            int r6 = r0.mClusteredItemCount
            java.lang.String r7 = c0.C0086a.l(r3, r6, r1)
        L_0x0144:
            N2.j.y(r5, r7, r4)
            int[] r1 = r0.mScrollIndex
            int r3 = r1.length
            if (r3 <= r2) goto L_0x015c
            int[] r1 = java.util.Arrays.copyOf(r1, r2)
            r0.mScrollIndex = r1
            com.samsung.android.gallery.module.data.ScrollText[] r1 = r0.mScrollIndexTag
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r2)
            com.samsung.android.gallery.module.data.ScrollText[] r1 = (com.samsung.android.gallery.module.data.ScrollText[]) r1
            r0.mScrollIndexTag = r1
        L_0x015c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.tables.ClusterIndexer.calculate(com.samsung.android.gallery.module.dataset.tables.ClusterTable, int):void");
    }

    public int getCount() {
        return this.mCount;
    }

    public MediaItem getCurrentItem(DefaultTable defaultTable, int i2) {
        MediaItem mediaItem = defaultTable.get(i2);
        if (mediaItem == null) {
            mediaItem = defaultTable.loadAndGet(i2);
        }
        if (mediaItem.getDateRaw() != null) {
            return mediaItem;
        }
        Log.e("ClusterIndexer", "null date raw. : " + mediaItem);
        MediaItem loadAndGet = defaultTable.loadAndGet(i2);
        Log.e("ClusterIndexer", "reload : " + loadAndGet);
        return loadAndGet;
    }

    public long[] getDateTaken() {
        return this.mDateTaken;
    }

    public String getLog() {
        String str = "Cluster=" + this.mCount + ",ClusteredItem=" + this.mClusteredItemCount + ",Total=" + this.mDataCount;
        if (isValid()) {
            return str;
        }
        int i2 = this.mDataCount;
        if (i2 == 3000 && this.mClusteredItemCount > i2) {
            return C0212a.A(str, "(PartialQuery)");
        }
        if (i2 == 120 && this.mClusteredItemCount > i2) {
            return C0212a.A(str, "(FakeLoading)");
        }
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(10);
        try {
            this.mTimelineItemMap.entrySet().stream().sorted(new a(12)).forEach(new P(sb2, 4));
        } catch (Exception unused) {
        }
        return sb2.toString();
    }

    public int[] getScrollIndex() {
        return this.mScrollIndex;
    }

    public ScrollText[] getScrollIndexTag() {
        return this.mScrollIndexTag;
    }

    public ArrayList<Integer> getTimelineIdxList() {
        return this.mTimelineIdxList;
    }

    public ConcurrentHashMap<Integer, ClusterItem> getTimelineItemMap() {
        return this.mTimelineItemMap;
    }

    public COMPARE_RESULT isDiff(MediaItem mediaItem, MediaItem mediaItem2, MediaItem mediaItem3, int i2, int i7, int i8) {
        if (isInvalidDate(mediaItem) || isInvalidDate(mediaItem2)) {
            Log.w("ClusterIndexer", "diff fail. old = " + mediaItem + ", curr = " + mediaItem2);
            return COMPARE_RESULT.SKIP;
        }
        int i10 = 7;
        if (i2 != 0) {
            if (i2 != 1) {
                i10 = 4;
            }
            if (!mediaItem.getDateRaw().substring(0, i10).equals(mediaItem2.getDateRaw().substring(0, i10))) {
                return COMPARE_RESULT.DIFF;
            }
            return COMPARE_RESULT.SKIP;
        } else if (mediaItem.getDateRaw().equals(mediaItem2.getDateRaw())) {
            return COMPARE_RESULT.SKIP;
        } else {
            if (!this.mDayMerge || i7 != i8 + 1 || !mediaItem.getDateRaw().substring(0, 7).equals(mediaItem2.getDateRaw().substring(0, 7)) || isInvalidDate(mediaItem3) || mediaItem2.getDateRaw().equals(mediaItem3.getDateRaw()) || TimeUtil.isInToday(mediaItem.getDateTaken())) {
                return COMPARE_RESULT.DIFF;
            }
            return COMPARE_RESULT.MERGE;
        }
    }

    public boolean isValid() {
        if (this.mClusteredItemCount == this.mDataCount) {
            return true;
        }
        return false;
    }

    public void updateAddress(MediaItem mediaItem, StringBuilder sb2, StringBuilder sb3, DataCounter<String> dataCounter) {
        if (MapUtil.isValidLocation(mediaItem.getLatitude(), mediaItem.getLongitude())) {
            if (sb2.length() > 0) {
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb3.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            }
            sb2.append(mediaItem.getLatitude());
            sb3.append(mediaItem.getLongitude());
        }
        String location = mediaItem.getLocation();
        if (location != null && !location.equals("null")) {
            dataCounter.add(location);
        }
    }

    public void updateAddressMonth(MediaItem mediaItem, StringBuilder sb2, StringBuilder sb3, DataCounter<String> dataCounter) {
        String str;
        String str2;
        String latitudeList = mediaItem.getLatitudeList();
        String longitudeList = mediaItem.getLongitudeList();
        if (!TextUtils.isEmpty(latitudeList) && !TextUtils.isEmpty(longitudeList)) {
            if (sb2.length() > 0) {
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb3.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            }
            sb2.append(latitudeList);
            sb3.append(longitudeList);
        }
        String location = mediaItem.getLocation();
        if (location != null && !location.equals("null")) {
            if (Features.isEnabled(Features.IS_JAPAN_DEVICE)) {
                str2 = "、";
                str = "...";
            } else {
                str2 = " & ";
                str = "&...";
            }
            String replace = location.replace(str, "");
            if (replace.contains(str2)) {
                for (String add : replace.split(str2)) {
                    dataCounter.add(add);
                }
                return;
            }
            dataCounter.add(replace);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x0243  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x015f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:108:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x013c A[Catch:{ NullPointerException -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0144 A[Catch:{ NullPointerException -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x014e A[Catch:{ NullPointerException -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0151 A[Catch:{ NullPointerException -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0156 A[Catch:{ NullPointerException -> 0x004f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void calculate(com.samsung.android.gallery.module.dataset.tables.DefaultTable r24, int r25, int r26) {
        /*
            r23 = this;
            r0 = r23
            r7 = r24
            java.lang.String r8 = "ClusterIndexer"
            r9 = 0
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ NullPointerException -> 0x0238 }
            int r1 = r7.getLoadedCount()     // Catch:{ NullPointerException -> 0x0238 }
            r0.reset(r1)     // Catch:{ NullPointerException -> 0x0238 }
            r1 = r26
            r0.mDataCount = r1     // Catch:{ NullPointerException -> 0x0238 }
            r0.mClusteredItemCount = r9     // Catch:{ NullPointerException -> 0x0238 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x0238 }
            r12 = 1024(0x400, float:1.435E-42)
            r1.<init>(r12)     // Catch:{ NullPointerException -> 0x0238 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x0238 }
            r2.<init>(r12)     // Catch:{ NullPointerException -> 0x0238 }
            com.samsung.android.gallery.support.utils.DataCounter r13 = new com.samsung.android.gallery.support.utils.DataCounter     // Catch:{ NullPointerException -> 0x0238 }
            r13.<init>()     // Catch:{ NullPointerException -> 0x0238 }
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ NullPointerException -> 0x0238 }
            r14.<init>()     // Catch:{ NullPointerException -> 0x0238 }
            r16 = 0
            r17 = r1
            r18 = r2
            r1 = r9
            r2 = r1
            r6 = r2
            r19 = r6
            r4 = r16
            r5 = r4
            r3 = -1
        L_0x003d:
            int r9 = r0.mCount     // Catch:{ NullPointerException -> 0x004e }
            if (r1 > r9) goto L_0x0172
            if (r1 != r9) goto L_0x0046
            int r9 = r1 + -1
            goto L_0x0047
        L_0x0046:
            r9 = r1
        L_0x0047:
            if (r4 != 0) goto L_0x0052
            com.samsung.android.gallery.module.data.MediaItem r4 = r0.getCurrentItem(r7, r9)     // Catch:{ NullPointerException -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r14 = r0
        L_0x004f:
            r0 = 0
            goto L_0x023a
        L_0x0052:
            if (r5 != 0) goto L_0x0055
            r5 = r4
        L_0x0055:
            int r9 = r9 + 1
            int r12 = r0.mCount     // Catch:{ NullPointerException -> 0x004e }
            if (r9 >= r12) goto L_0x0063
            com.samsung.android.gallery.module.data.MediaItem r9 = r0.getCurrentItem(r7, r9)     // Catch:{ NullPointerException -> 0x004e }
        L_0x005f:
            r12 = r1
            r1 = r5
            r5 = r6
            goto L_0x0066
        L_0x0063:
            r9 = r16
            goto L_0x005f
        L_0x0066:
            int r6 = r14.size()     // Catch:{ NullPointerException -> 0x004e }
            r15 = r12
            r12 = r3
            r3 = r9
            r9 = r2
            r2 = r4
            r4 = r25
            com.samsung.android.gallery.module.dataset.tables.ClusterIndexer$COMPARE_RESULT r6 = r0.isDiff(r1, r2, r3, r4, r5, r6)     // Catch:{ NullPointerException -> 0x004e }
            r21 = r3
            r4 = r5
            com.samsung.android.gallery.module.dataset.tables.ClusterIndexer$COMPARE_RESULT r3 = com.samsung.android.gallery.module.dataset.tables.ClusterIndexer.COMPARE_RESULT.MERGE     // Catch:{ NullPointerException -> 0x004e }
            if (r6 != r3) goto L_0x0080
            r14.add(r1)     // Catch:{ NullPointerException -> 0x004e }
            r1 = r2
        L_0x0080:
            com.samsung.android.gallery.module.dataset.tables.ClusterIndexer$COMPARE_RESULT r3 = com.samsung.android.gallery.module.dataset.tables.ClusterIndexer.COMPARE_RESULT.DIFF     // Catch:{ NullPointerException -> 0x004e }
            if (r6 == r3) goto L_0x0098
            int r3 = r0.mCount     // Catch:{ NullPointerException -> 0x004e }
            if (r15 != r3) goto L_0x0089
            goto L_0x0098
        L_0x0089:
            r6 = r4
            r3 = r12
            r22 = r14
            r4 = r17
            r14 = r0
            r12 = r2
            r0 = r9
            r9 = r18
            r2 = r25
            goto L_0x0136
        L_0x0098:
            java.util.ArrayList<java.lang.Integer> r3 = r0.mTimelineIdxList     // Catch:{ NullPointerException -> 0x004e }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r19)     // Catch:{ NullPointerException -> 0x004e }
            r3.add(r5)     // Catch:{ NullPointerException -> 0x004e }
            boolean r3 = r0.mDayMerge     // Catch:{ NullPointerException -> 0x004e }
            if (r3 == 0) goto L_0x00c9
            if (r25 != 0) goto L_0x00c9
            r14.add(r1)     // Catch:{ NullPointerException -> 0x004e }
            com.samsung.android.gallery.module.dataset.tables.DateClusterItem r0 = new com.samsung.android.gallery.module.dataset.tables.DateClusterItem     // Catch:{ NullPointerException -> 0x00c6 }
            java.util.List r3 = r13.getSortedList()     // Catch:{ NullPointerException -> 0x00c6 }
            java.lang.String r5 = r17.toString()     // Catch:{ NullPointerException -> 0x00c6 }
            java.lang.String r6 = r18.toString()     // Catch:{ NullPointerException -> 0x00c6 }
            r12 = r2
            r1 = r14
            r14 = r23
            r2 = r25
            r0.<init>((java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem>) r1, (int) r2, (java.util.List<java.lang.String>) r3, (int) r4, (java.lang.String) r5, (java.lang.String) r6)     // Catch:{ NullPointerException -> 0x004f }
            r22 = r1
            r2 = r25
            goto L_0x00e0
        L_0x00c6:
            r14 = r23
            goto L_0x004f
        L_0x00c9:
            r12 = r2
            r22 = r14
            r14 = r0
            com.samsung.android.gallery.module.dataset.tables.DateClusterItem r0 = new com.samsung.android.gallery.module.dataset.tables.DateClusterItem     // Catch:{ NullPointerException -> 0x004f }
            java.util.List r3 = r13.getSortedList()     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r5 = r17.toString()     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r6 = r18.toString()     // Catch:{ NullPointerException -> 0x004f }
            r2 = r25
            r0.<init>((com.samsung.android.gallery.module.data.MediaItem) r1, (int) r2, (java.util.List<java.lang.String>) r3, (int) r4, (java.lang.String) r5, (java.lang.String) r6)     // Catch:{ NullPointerException -> 0x004f }
        L_0x00e0:
            int[] r1 = r14.mScrollIndex     // Catch:{ NullPointerException -> 0x004f }
            r3 = 2
            if (r2 == r3) goto L_0x00e8
            r5 = r19
            goto L_0x00f2
        L_0x00e8:
            r5 = 1
            if (r9 >= r5) goto L_0x00ed
            r5 = 0
            goto L_0x00f2
        L_0x00ed:
            int r5 = r9 + -1
            r5 = r1[r5]     // Catch:{ NullPointerException -> 0x004f }
            int r5 = r5 + r3
        L_0x00f2:
            r1[r9] = r5     // Catch:{ NullPointerException -> 0x004f }
            java.util.concurrent.ConcurrentHashMap<java.lang.Integer, com.samsung.android.gallery.module.data.ClusterItem> r1 = r14.mTimelineItemMap     // Catch:{ NullPointerException -> 0x004f }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)     // Catch:{ NullPointerException -> 0x004f }
            r1.put(r3, r0)     // Catch:{ NullPointerException -> 0x004f }
            int r1 = r14.mCount     // Catch:{ NullPointerException -> 0x004f }
            r20 = 1
            int r1 = r1 + -1
            int r3 = r1 - r9
            long[] r1 = r14.mDateTaken     // Catch:{ NullPointerException -> 0x004f }
            long r5 = r0.getDateTaken()     // Catch:{ NullPointerException -> 0x004f }
            r1[r3] = r5     // Catch:{ NullPointerException -> 0x004f }
            com.samsung.android.gallery.module.data.ScrollText[] r1 = r14.mScrollIndexTag     // Catch:{ NullPointerException -> 0x004f }
            com.samsung.android.gallery.module.data.ScrollText r0 = r14.getDateLocationTag(r0, r2)     // Catch:{ NullPointerException -> 0x004f }
            r1[r9] = r0     // Catch:{ NullPointerException -> 0x004f }
            int r0 = r9 + 1
            int r6 = r4 + 1
            int r19 = r6 + r19
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x004f }
            r5 = 1024(0x400, float:1.435E-42)
            r1.<init>(r5)     // Catch:{ NullPointerException -> 0x004f }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x004f }
            r6.<init>(r5)     // Catch:{ NullPointerException -> 0x004f }
            r13.clear()     // Catch:{ NullPointerException -> 0x004f }
            r22.clear()     // Catch:{ NullPointerException -> 0x004f }
            int r9 = r14.mClusteredItemCount     // Catch:{ NullPointerException -> 0x004f }
            int r9 = r9 + r4
            r14.mClusteredItemCount = r9     // Catch:{ NullPointerException -> 0x004f }
            r4 = r1
            r9 = r6
            r1 = r12
            r6 = 0
        L_0x0136:
            int r5 = r12.getCount()     // Catch:{ NullPointerException -> 0x004f }
            if (r2 == 0) goto L_0x0144
            r17 = r0
            boolean r0 = r7 instanceof com.samsung.android.gallery.module.dataset.tables.ClusterTable     // Catch:{ NullPointerException -> 0x004f }
            if (r0 == 0) goto L_0x0146
            r0 = 1
            goto L_0x0147
        L_0x0144:
            r17 = r0
        L_0x0146:
            r0 = 0
        L_0x0147:
            r18 = r0
            if (r0 == 0) goto L_0x0151
            r0 = 1
            if (r5 <= r0) goto L_0x0151
            int r6 = r6 + r5
        L_0x014f:
            r0 = 2
            goto L_0x0154
        L_0x0151:
            int r6 = r6 + 1
            goto L_0x014f
        L_0x0154:
            if (r2 == r0) goto L_0x015f
            if (r18 == 0) goto L_0x015c
            r14.updateAddressMonth(r12, r4, r9, r13)     // Catch:{ NullPointerException -> 0x004f }
            goto L_0x015f
        L_0x015c:
            r14.updateAddress(r12, r4, r9, r13)     // Catch:{ NullPointerException -> 0x004f }
        L_0x015f:
            int r0 = r15 + 1
            r5 = r1
            r18 = r9
            r2 = r17
            r12 = 1024(0x400, float:1.435E-42)
            r1 = r0
            r17 = r4
            r0 = r14
            r4 = r21
            r14 = r22
            goto L_0x003d
        L_0x0172:
            r14 = r0
            r9 = r2
            r12 = r3
            r2 = r25
            r14.mCount = r9     // Catch:{ NullPointerException -> 0x004f }
            r0 = -1
            if (r12 == r0) goto L_0x0189
            long[] r0 = r14.mDateTaken     // Catch:{ NullPointerException -> 0x004f }
            int r1 = r0.length     // Catch:{ NullPointerException -> 0x004f }
            r20 = 1
            int r1 = r1 + -1
            long[] r0 = java.util.Arrays.copyOfRange(r0, r12, r1)     // Catch:{ NullPointerException -> 0x004f }
            r14.mDateTaken = r0     // Catch:{ NullPointerException -> 0x004f }
        L_0x0189:
            int[] r0 = r14.mScrollIndex     // Catch:{ NullPointerException -> 0x004f }
            int r1 = r0.length     // Catch:{ NullPointerException -> 0x004f }
            if (r1 <= r9) goto L_0x019e
            int[] r0 = java.util.Arrays.copyOf(r0, r9)     // Catch:{ NullPointerException -> 0x004f }
            r14.mScrollIndex = r0     // Catch:{ NullPointerException -> 0x004f }
            com.samsung.android.gallery.module.data.ScrollText[] r0 = r14.mScrollIndexTag     // Catch:{ NullPointerException -> 0x004f }
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r9)     // Catch:{ NullPointerException -> 0x004f }
            com.samsung.android.gallery.module.data.ScrollText[] r0 = (com.samsung.android.gallery.module.data.ScrollText[]) r0     // Catch:{ NullPointerException -> 0x004f }
            r14.mScrollIndexTag = r0     // Catch:{ NullPointerException -> 0x004f }
        L_0x019e:
            java.util.concurrent.ConcurrentHashMap<java.lang.Integer, com.samsung.android.gallery.module.data.ClusterItem> r0 = r14.mTimelineItemMap     // Catch:{ NullPointerException -> 0x004f }
            int r0 = r0.size()     // Catch:{ NullPointerException -> 0x004f }
            java.util.ArrayList<java.lang.Integer> r1 = r14.mTimelineIdxList     // Catch:{ NullPointerException -> 0x004f }
            int r1 = r1.size()     // Catch:{ NullPointerException -> 0x004f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x004f }
            r3.<init>()     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r4 = "calculateDayMonth "
            r3.append(r4)     // Catch:{ NullPointerException -> 0x004f }
            if (r2 != 0) goto L_0x01b9
            java.lang.String r2 = "DAY"
            goto L_0x01d6
        L_0x01b9:
            r5 = 1
            if (r2 != r5) goto L_0x01bf
            java.lang.String r2 = "MONTH"
            goto L_0x01d6
        L_0x01bf:
            r4 = 2
            if (r2 != r4) goto L_0x01c5
            java.lang.String r2 = "YEAR"
            goto L_0x01d6
        L_0x01c5:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x004f }
            r4.<init>()     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r5 = "UNKNOWN#"
            r4.append(r5)     // Catch:{ NullPointerException -> 0x004f }
            r4.append(r2)     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r2 = r4.toString()     // Catch:{ NullPointerException -> 0x004f }
        L_0x01d6:
            r3.append(r2)     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r2 = "{"
            r3.append(r2)     // Catch:{ NullPointerException -> 0x004f }
            int r2 = r14.mDataCount     // Catch:{ NullPointerException -> 0x004f }
            r3.append(r2)     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r2 = ":c="
            r3.append(r2)     // Catch:{ NullPointerException -> 0x004f }
            int r2 = r14.mCount     // Catch:{ NullPointerException -> 0x004f }
            r3.append(r2)     // Catch:{ NullPointerException -> 0x004f }
            int r2 = r14.mCount     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r4 = ""
            if (r2 == r0) goto L_0x0206
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x004f }
            r2.<init>()     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r5 = ",m="
            r2.append(r5)     // Catch:{ NullPointerException -> 0x004f }
            r2.append(r0)     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r0 = r2.toString()     // Catch:{ NullPointerException -> 0x004f }
            goto L_0x0207
        L_0x0206:
            r0 = r4
        L_0x0207:
            r3.append(r0)     // Catch:{ NullPointerException -> 0x004f }
            int r0 = r14.mCount     // Catch:{ NullPointerException -> 0x004f }
            if (r0 == r1) goto L_0x021f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x004f }
            r0.<init>()     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r2 = ",l="
            r0.append(r2)     // Catch:{ NullPointerException -> 0x004f }
            r0.append(r1)     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r4 = r0.toString()     // Catch:{ NullPointerException -> 0x004f }
        L_0x021f:
            r3.append(r4)     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r0 = "} +"
            r3.append(r0)     // Catch:{ NullPointerException -> 0x004f }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ NullPointerException -> 0x004f }
            long r0 = r0 - r10
            r3.append(r0)     // Catch:{ NullPointerException -> 0x004f }
            java.lang.String r0 = r3.toString()     // Catch:{ NullPointerException -> 0x004f }
            com.samsung.android.gallery.support.utils.Log.d(r8, r0)     // Catch:{ NullPointerException -> 0x004f }
            goto L_0x023d
        L_0x0238:
            r14 = r0
            r0 = r9
        L_0x023a:
            r14.reset(r0)
        L_0x023d:
            boolean r0 = r14.isValid()
            if (r0 != 0) goto L_0x0266
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "InvalidCluster{"
            r0.<init>(r1)
            int r1 = r14.mDataCount
            r0.append(r1)
            java.lang.String r1 = ","
            r0.append(r1)
            int r1 = r14.mClusteredItemCount
            r0.append(r1)
            java.lang.String r1 = "}"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.e(r8, r0)
        L_0x0266:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.tables.ClusterIndexer.calculate(com.samsung.android.gallery.module.dataset.tables.DefaultTable, int, int):void");
    }
}
