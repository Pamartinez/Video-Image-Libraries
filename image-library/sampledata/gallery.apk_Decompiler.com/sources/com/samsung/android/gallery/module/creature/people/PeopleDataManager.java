package com.samsung.android.gallery.module.creature.people;

import A4.B;
import A4.c0;
import Ad.C0720a;
import B8.d;
import C3.C0392b;
import L5.b;
import N2.j;
import T3.e;
import V8.a;
import V8.c;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.CacheProviderHelper;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.helper.PeopleApi;
import com.samsung.android.gallery.database.dal.mp.helper.SimilarContactApi;
import com.samsung.android.gallery.module.creature.base.CreatureIndexingDelegate;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.base.MyQueryInterface;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.search.CreatureIndexingBuilder;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PeopleDataManager {
    private static final boolean SUPPORT_INDEXING = Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH);
    private static CreatureIndexingDelegate sIndexingDelegate = new PeopleIndexingDelegate();
    private static boolean sIsUpdating;
    private static final PeopleDataHolder sPeopleDataHolder = new PeopleDataHolder();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PeopleDataHolder {
        private final ConcurrentHashMap<Long, Data> mMap = new ConcurrentHashMap<>();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Data {
            /* access modifiers changed from: private */
            public final ArrayList<PeopleData> list;
            private long timeStamp;

            public Data(ArrayList<PeopleData> arrayList) {
                this.list = arrayList;
                if (arrayList == null) {
                    this.timeStamp = System.currentTimeMillis();
                }
            }

            public boolean valid() {
                if (this.list != null || System.currentTimeMillis() - this.timeStamp < 21600000) {
                    return true;
                }
                return false;
            }
        }

        private Data get(long j2) {
            return this.mMap.computeIfAbsent(Long.valueOf(j2), new c(this));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$load$0(long j2, Consumer consumer) {
            this.mMap.remove(Long.valueOf(j2));
            Data data = get(j2);
            if (consumer != null) {
                consumer.accept(data.list);
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$updateByGroup$1(long j2, String str, long j3, Long l, Data data) {
            if (data.list != null) {
                Iterator it = data.list.iterator();
                while (it.hasNext()) {
                    PeopleData peopleData = (PeopleData) it.next();
                    long j8 = j2;
                    String str2 = str;
                    long j10 = j3;
                    if (peopleData.getGroupId() == j2) {
                        peopleData.update(str2, j10, j8);
                    }
                    str = str2;
                    j3 = j10;
                    j2 = j8;
                }
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateByGroup$2(PeopleData peopleData, long j2) {
            if (j2 == peopleData.getGroupId()) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$updateByGroup$3(long[] jArr, String str, long j2, Long l, Data data) {
            long j3;
            String str2;
            if (data.list != null) {
                Iterator it = data.list.iterator();
                while (it.hasNext()) {
                    PeopleData peopleData = (PeopleData) it.next();
                    if (Arrays.stream(jArr).anyMatch(new b(peopleData, 1))) {
                        str2 = str;
                        j3 = j2;
                        peopleData.update(str2, j3, peopleData.getGroupId());
                    } else {
                        str2 = str;
                        j3 = j2;
                    }
                    str = str2;
                    j2 = j3;
                }
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$updateByPerson$4(long j2, String str, long j3, Long l, Data data) {
            long j8;
            String str2;
            if (data.list != null) {
                Iterator it = data.list.iterator();
                while (it.hasNext()) {
                    PeopleData peopleData = (PeopleData) it.next();
                    if (peopleData.getPersonId() == j2) {
                        str2 = str;
                        j8 = j3;
                        peopleData.update(str2, j8, -1);
                    } else {
                        str2 = str;
                        j8 = j3;
                    }
                    str = str2;
                    j3 = j8;
                }
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateByPerson$5(PeopleData peopleData, long j2) {
            if (j2 == peopleData.getPersonId()) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$updateByPerson$6(long[] jArr, String str, long j2, Long l, Data data) {
            long j3;
            String str2;
            if (data.list != null) {
                Iterator it = data.list.iterator();
                while (it.hasNext()) {
                    PeopleData peopleData = (PeopleData) it.next();
                    if (Arrays.stream(jArr).anyMatch(new b(peopleData, 2))) {
                        str2 = str;
                        j3 = j2;
                        peopleData.update(str2, j3, -1);
                    } else {
                        str2 = str;
                        j3 = j2;
                    }
                    str = str2;
                    j2 = j3;
                }
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateByUnifiedId$7(PeopleData peopleData, long j2) {
            if (j2 == peopleData.getRecommendedId()) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$updateByUnifiedId$8(long[] jArr, String str, long j2, Long l, Data data) {
            long j3;
            String str2;
            if (data.list != null) {
                Iterator it = data.list.iterator();
                while (it.hasNext()) {
                    PeopleData peopleData = (PeopleData) it.next();
                    if (Arrays.stream(jArr).anyMatch(new b(peopleData, 0))) {
                        str2 = str;
                        j3 = j2;
                        peopleData.update(str2, j3, -1);
                    } else {
                        str2 = str;
                        j3 = j2;
                    }
                    str = str2;
                    j2 = j3;
                }
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$updateByUnifiedId$9(long j2, String str, long j3, Long l, Data data) {
            long j8;
            String str2;
            if (data.list != null) {
                Iterator it = data.list.iterator();
                while (it.hasNext()) {
                    PeopleData peopleData = (PeopleData) it.next();
                    if (peopleData.getRecommendedId() == j2) {
                        str2 = str;
                        j8 = j3;
                        peopleData.update(str2, j8, -1);
                    } else {
                        str2 = str;
                        j8 = j3;
                    }
                    str = str2;
                    j3 = j8;
                }
            }
        }

        /* access modifiers changed from: private */
        public Data loadPeopleData(long j2) {
            Cursor query;
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            try {
                query = DbCompat.query("mp://People/files", new a(j2));
                if (query != null) {
                    if (query.moveToFirst()) {
                        int columnIndex = query.getColumnIndex("__absID");
                        do {
                            if (j2 == query.getLong(columnIndex)) {
                                arrayList.add(PeopleDataBuilder.createPeopleData(query));
                            }
                        } while (query.moveToNext());
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                Log.e((CharSequence) "PeopleDataManager", "loadPeopleData failed", (Throwable) e);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            StringBuilder j3 = j.j(j2, "loadPeopleData {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            j3.append(arrayList.size());
            j3.append("} +");
            j3.append(System.currentTimeMillis() - currentTimeMillis);
            Log.d("PeopleDataManager", j3.toString());
            if (arrayList.isEmpty()) {
                arrayList = null;
            }
            return new Data(arrayList);
            throw th;
        }

        public void clear() {
            this.mMap.clear();
        }

        public void load(long j2, Consumer<ArrayList<PeopleData>> consumer) {
            Data data = this.mMap.get(Long.valueOf(j2));
            if (data == null || !data.valid()) {
                SimpleThreadPool.getInstance().execute(new f(this, j2, consumer));
            } else if (consumer != null) {
                consumer.accept(data.list);
            }
        }

        public void remove(long j2) {
            this.mMap.remove(Long.valueOf(j2));
        }

        public void updateByGroup(long j2, long j3, String str) {
            this.mMap.forEach(new d(j2, str, j3, 1));
        }

        public void updateByPerson(long j2, long j3, String str) {
            this.mMap.forEach(new d(j2, str, j3, 0));
        }

        public void updateByUnifiedId(long[] jArr, long j2, String str) {
            this.mMap.forEach(new e(jArr, str, j2, 0));
        }

        public void updateByGroup(long[] jArr, long j2, String str) {
            if (jArr.length >= 1) {
                this.mMap.forEach(new e(jArr, str, j2, 2));
            }
        }

        public void updateByPerson(long[] jArr, long j2, String str) {
            if (jArr.length >= 1) {
                this.mMap.forEach(new e(jArr, str, j2, 1));
            }
        }

        public void updateByUnifiedId(long j2, long j3, String str) {
            this.mMap.forEach(new d(j2, str, j3, 2));
        }
    }

    public static long addName(String str, CreatureNameData creatureNameData) {
        long j2;
        sIsUpdating = true;
        long currentTimeMillis = System.currentTimeMillis();
        long identityId = IdentityCreatureUtil.getIdentityId(str);
        String[] personNameAndRelationship = getPersonNameAndRelationship(str);
        long updateDatabase = updateDatabase(str, creatureNameData);
        if (SUPPORT_INDEXING) {
            CreatureIndexingBuilder relationship = new CreatureIndexingBuilder().tagName(personNameAndRelationship[0], creatureNameData.getName()).relationship(personNameAndRelationship[1], creatureNameData.getRelationship());
            if (IdentityCreatureUtil.isAssignedCreature(str)) {
                j2 = identityId;
            } else {
                j2 = 1;
            }
            sIndexingDelegate.index(relationship.tagId(j2, updateDatabase).unifiedId(IdentityCreatureUtil.getUnifiedIdentityId(str), updateDatabase).targetTagId(updateDatabase));
        }
        updatePeopleDataHolder(str, updateDatabase, creatureNameData.getName());
        StringBuilder j3 = j.j(identityId, "addName {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        j3.append(updateDatabase);
        j3.append("} +");
        j3.append(System.currentTimeMillis() - currentTimeMillis);
        Log.d("PeopleDataManager", j3.toString());
        sIsUpdating = false;
        return updateDatabase;
    }

    private static String addShowCondition() {
        if (!PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE || PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            return "";
        }
        return " and IFNULL(hide, 0) = 0";
    }

    private static void appendIndexingOnMerge(String str, String str2, long j2) {
        if (SUPPORT_INDEXING) {
            sIndexingDelegate.index(new CreatureIndexingBuilder().tagName((String) null, str).relationship((String) null, str2).tagId(1, j2).unifiedId(Long.MIN_VALUE, j2).targetTagId(j2));
        }
    }

    private static void appendIndexingOnUnmerge(List<Long> list) {
        if (SUPPORT_INDEXING) {
            list.forEach(new e(24));
        }
    }

    public static void clear() {
        sPeopleDataHolder.clear();
    }

    public static void deletePeopleData(long j2) {
        sPeopleDataHolder.remove(j2);
    }

    public static void disableSimilarContact(CreatureNameData creatureNameData) {
        if (creatureNameData != null) {
            new SimilarContactApi().disableContactRecommendation(creatureNameData.getSimilarContactGroupId(), creatureNameData.getContactRawId());
        }
    }

    public static boolean existTaggedContact(long j2) {
        if (new PeopleApi().findPersonIdByRawId(j2) > 1) {
            return true;
        }
        return false;
    }

    public static boolean existTaggedFromMyProfile() {
        if (new PeopleApi().findPersonIdByRawId(-100) != 1) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String[] getAutoSelectCoverData(java.lang.String r4) {
        /*
            long r0 = com.samsung.android.gallery.support.utils.IdentityCreatureUtil.getUnifiedIdentityId(r4)
            com.samsung.android.gallery.database.dal.mp.helper.PeopleApi r4 = new com.samsung.android.gallery.database.dal.mp.helper.PeopleApi
            r4.<init>()
            android.database.Cursor r4 = r4.getAutoSelectCoverData(r0)
            if (r4 == 0) goto L_0x002a
            boolean r0 = r4.moveToFirst()     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x002a
            r0 = 0
            long r0 = r4.getLong(r0)     // Catch:{ all -> 0x0020 }
            r2 = 1
            long r2 = r4.getLong(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x002d
        L_0x0020:
            r0 = move-exception
            r4.close()     // Catch:{ all -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r4 = move-exception
            r0.addSuppressed(r4)
        L_0x0029:
            throw r0
        L_0x002a:
            r0 = -1
            r2 = r0
        L_0x002d:
            if (r4 == 0) goto L_0x0032
            r4.close()
        L_0x0032:
            java.lang.String r4 = java.lang.String.valueOf(r0)
            java.lang.String r0 = java.lang.String.valueOf(r2)
            java.lang.String[] r4 = new java.lang.String[]{r4, r0}
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.creature.people.PeopleDataManager.getAutoSelectCoverData(java.lang.String):java.lang.String[]");
    }

    public static void getFaceClusterData(String str, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        new PeopleApi().getFaceClusterData(str, arrayList, arrayList2);
    }

    public static List<Pair<String, List<String>>> getFaceClusterMergeAllData(ArrayList<Long> arrayList) {
        QueryParams queryParams = new QueryParams();
        queryParams.mRecommendedIds = TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList);
        return new PeopleApi(queryParams).getFaceClusterMergeAllData();
    }

    public static List<Long> getGroupIds(String str) {
        ArrayList arrayList = new ArrayList();
        if (IdentityCreatureUtil.isAssignedCreature(str)) {
            return new PeopleApi().getGroupIdsById(IdentityCreatureUtil.getIdentityId(str));
        }
        arrayList.add(Long.valueOf(IdentityCreatureUtil.getIdentityId(str)));
        return arrayList;
    }

    public static String getIncludedMediaIds(List<Long> list) {
        ArrayList arrayList = new ArrayList();
        new ArrayList(list).forEach(new d(arrayList, 17));
        return (String) arrayList.stream().distinct().map(new b(5)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
    }

    public static MediaItem getMeTaggedItem(MyQueryInterface myQueryInterface) {
        ArrayList arrayList = new ArrayList();
        Cursor meTaggedCursor = new PeopleApi().getMeTaggedCursor();
        if (meTaggedCursor != null) {
            try {
                if (meTaggedCursor.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemLoader.load(meTaggedCursor));
                    } while (meTaggedCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (meTaggedCursor != null) {
            meTaggedCursor.close();
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (!PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE) {
            return (MediaItem) arrayList.get(0);
        }
        ArrayList<String> top5List = myQueryInterface.getTop5List();
        if (top5List.isEmpty()) {
            return (MediaItem) arrayList.get(0);
        }
        String str = top5List.get(0);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem mediaItem = (MediaItem) it.next();
            if (str.equals(mediaItem.getSubCategory())) {
                return mediaItem;
            }
        }
        return null;
        throw th;
    }

    public static List<Long> getMediaIdListByUnifiedId(Long l) {
        sIsUpdating = true;
        ArrayList arrayList = new ArrayList();
        Cursor mediaIdListCursorByUnifiedId = new PeopleApi().getMediaIdListCursorByUnifiedId(l.longValue());
        if (mediaIdListCursorByUnifiedId != null) {
            try {
                if (mediaIdListCursorByUnifiedId.moveToFirst()) {
                    int columnIndex = mediaIdListCursorByUnifiedId.getColumnIndex("__absID");
                    do {
                        arrayList.add(Long.valueOf(mediaIdListCursorByUnifiedId.getLong(columnIndex)));
                    } while (mediaIdListCursorByUnifiedId.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (mediaIdListCursorByUnifiedId != null) {
            mediaIdListCursorByUnifiedId.close();
        }
        sIsUpdating = false;
        return arrayList;
        throw th;
    }

    private static String[] getPersonNameAndRelationship(String str) {
        Cursor nameCursor;
        boolean isAssignedCreature = IdentityCreatureUtil.isAssignedCreature(str);
        long identityId = IdentityCreatureUtil.getIdentityId(str);
        String[] strArr = new String[2];
        if (!isAssignedCreature) {
            identityId = new PeopleApi().findCreatureId(identityId);
        }
        try {
            nameCursor = new PeopleApi().getNameCursor(identityId);
            if (nameCursor != null) {
                if (nameCursor.moveToLast()) {
                    strArr[0] = nameCursor.getString(nameCursor.getColumnIndex("__creatureName"));
                    strArr[1] = nameCursor.getString(nameCursor.getColumnIndex("__creatureRelationship"));
                }
            }
            if (nameCursor != null) {
                nameCursor.close();
            }
            return strArr;
        } catch (Exception e) {
            j.v("getPersonNameAndRelationship failed : ", e, "PeopleDataManager");
            return strArr;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static List<String> getRelationships() {
        ArrayList arrayList = new ArrayList();
        Cursor relationshipCursor = new PeopleApi().getRelationshipCursor();
        if (relationshipCursor != null) {
            try {
                if (relationshipCursor.moveToFirst()) {
                    do {
                        String string = relationshipCursor.getString(relationshipCursor.getColumnIndex("__creatureRelationship"));
                        if (!TextUtils.isEmpty(string)) {
                            arrayList.add(string);
                        }
                    } while (relationshipCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (relationshipCursor != null) {
            relationshipCursor.close();
        }
        return arrayList;
        throw th;
    }

    public static long[] getSimilarContactData(String str) {
        List<Pair<Long, Long>> similarContactData = getSimilarContactData(getGroupIds(str));
        if (similarContactData.isEmpty()) {
            return null;
        }
        return new long[]{((Long) similarContactData.get(0).first).longValue(), ((Long) similarContactData.get(0).second).longValue()};
    }

    public static void hidePeople(List<Long> list, MyQueryInterface myQueryInterface) {
        if (!list.isEmpty()) {
            sIsUpdating = true;
            new PeopleApi().hideCreature(list);
            if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE) {
                ArrayList<String> top5List = myQueryInterface.getTop5List();
                if (!top5List.isEmpty()) {
                    String str = top5List.get(0);
                    ArrayList arrayList = (ArrayList) list.stream().map(new a(0)).collect(Collectors.toCollection(new C0720a(1)));
                    if (arrayList.stream().anyMatch(new C0392b(str, 14))) {
                        removeRelationship(str, "me");
                    }
                    myQueryInterface.removeTop5List(top5List, arrayList);
                }
            }
            sIsUpdating = false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadHeaderItem$6(String str, String str2, long j2, QueryParams queryParams) {
        queryParams.setSubCategory(str);
        queryParams.setFileIds(str2);
        queryParams.mFaceGroupId = j2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updatePeopleDataHolder$3(long j2) {
        return !IdentityCreatureUtil.isAssignedCreature(j2);
    }

    public static void load(long j2, Consumer<ArrayList<PeopleData>> consumer) {
        if (j2 > 0) {
            sPeopleDataHolder.load(j2, consumer);
        }
    }

    public static MediaItem loadHeaderItem(String str) {
        Cursor query;
        try {
            query = DbCompat.query("mp://People", new B(str, 15));
            if (query != null) {
                if (query.moveToFirst()) {
                    MediaItem cloneCreatureItem = MediaItemBuilder.cloneCreatureItem(MediaItemLoader.load(query));
                    query.close();
                    return cloneCreatureItem;
                }
            }
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static ArrayList<MediaItem> loadItemByGroupIds(List<String> list) {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        Cursor query = DbCompat.query("mp://People", new c0(1, list));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemBuilder.cloneCreatureItem(MediaItemLoader.load(query)));
                    } while (query.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
        throw th;
    }

    public static ArrayList<MediaItem> loadItems(ArrayList<String> arrayList) {
        Cursor query;
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        try {
            query = DbCompat.query("mp://People", new B(String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList), 16));
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        arrayList2.add(MediaItemLoader.load(query));
                    } while (query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
            }
            return arrayList2;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList2;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static ArrayList<MediaItem> loadItemsFromCache(ArrayList<String> arrayList) {
        Cursor query;
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        Cursor cursor = null;
        try {
            query = CacheProviderHelper.query("location://search/fileList/Category/People");
            if (query != null) {
                if (query.getCount() > 0 && query.moveToFirst() && (cursor = new CacheProviderHelper.CacheReader(query).recoverCursor()) != null && cursor.moveToFirst()) {
                    do {
                        if (arrayList.contains(String.valueOf(cursor.getInt(cursor.getColumnIndex("__creatureFaceRecommendedID"))))) {
                            arrayList2.add(MediaItemBuilder.cloneCreatureItem(MediaItemLoader.load(cursor)));
                        }
                    } while (cursor.moveToNext());
                }
            }
            if (query != null) {
                query.close();
            }
            Utils.closeSilently(cursor);
            return arrayList2;
        } catch (Exception e) {
            try {
                e.printStackTrace();
                Utils.closeSilently((Closeable) null);
                return arrayList2;
            } catch (Throwable th) {
                Utils.closeSilently((Closeable) null);
                throw th;
            }
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public static ArrayList<MediaItem> loadRelatedRepresentItem(long j2) {
        Cursor rawQuery;
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        try {
            SecMpQueryExecutor secMpQueryExecutor = new SecMpQueryExecutor();
            rawQuery = secMpQueryExecutor.rawQuery("select group_concat(DISTINCT recommended_id) from faces where sec_media_id = " + j2 + addShowCondition(), "CreatureConcatId_people");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    arrayList = loadItems(new ArrayList(Arrays.asList(rawQuery.getString(0).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX))));
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList;
        } catch (Exception e) {
            Log.e((CharSequence) "PeopleDataManager", "loadRelatedRepresentItem failed", (Throwable) e);
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static void merge(String[] strArr, String str, MyQueryInterface myQueryInterface) {
        sIsUpdating = true;
        long currentTimeMillis = System.currentTimeMillis();
        removeIndexingOnMerge(strArr);
        long unifiedIdentityId = IdentityCreatureUtil.getUnifiedIdentityId(str);
        long[] array = Arrays.stream(strArr).mapToLong(new E5.b(6)).toArray();
        new PeopleApi().updatePersonId(array, unifiedIdentityId);
        new PeopleApi().delete(array);
        String[] personNameAndRelationship = getPersonNameAndRelationship(str);
        updatePeopleDataHolder(array, unifiedIdentityId, personNameAndRelationship[0]);
        appendIndexingOnMerge(personNameAndRelationship[0], personNameAndRelationship[1], unifiedIdentityId);
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE) {
            myQueryInterface.mergeTop5List(new ArrayList(Arrays.asList(strArr)), str);
        }
        A.a.x(j.k("merge {", str, "} +"), currentTimeMillis, "PeopleDataManager");
        sIsUpdating = false;
    }

    public static void removeCustomRelationship(String str) {
        new PeopleApi().removeCustomRelationship(str);
    }

    private static void removeIndexingOnMerge(String[] strArr) {
        CreatureIndexingBuilder creatureIndexingBuilder;
        if (SUPPORT_INDEXING) {
            for (String str : strArr) {
                long identityId = IdentityCreatureUtil.getIdentityId(str);
                if (IdentityCreatureUtil.isAssignedCreature(str)) {
                    String[] personNameAndRelationship = getPersonNameAndRelationship(str);
                    creatureIndexingBuilder = new CreatureIndexingBuilder().tagName(personNameAndRelationship[0], (String) null).relationship(personNameAndRelationship[1], (String) null).tagId(identityId, 1).unifiedId(identityId, Long.MIN_VALUE).targetTagId(identityId);
                } else {
                    creatureIndexingBuilder = new CreatureIndexingBuilder().unifiedId(IdentityCreatureUtil.getUnifiedIdentityId(str), Long.MIN_VALUE).targetGroupId(identityId);
                }
                sIndexingDelegate.index(creatureIndexingBuilder);
            }
        }
    }

    private static void removeIndexingOnUnmerge(long j2, String str, String str2) {
        if (SUPPORT_INDEXING) {
            sIndexingDelegate.index(new CreatureIndexingBuilder().tagName(str, (String) null).relationship(str2, (String) null).tagId(j2, 1).unifiedId(j2, Long.MIN_VALUE).targetTagId(j2));
        }
    }

    public static void removeRelationship(String str, String str2) {
        sIsUpdating = true;
        long currentTimeMillis = System.currentTimeMillis();
        long identityId = IdentityCreatureUtil.getIdentityId(str);
        if (SUPPORT_INDEXING) {
            sIndexingDelegate.index(new CreatureIndexingBuilder().relationship(str2, (String) null).targetTagId(identityId));
        }
        new PeopleApi().deleteRelationship(identityId);
        Log.d("PeopleDataManager", "removeRelationship {" + identityId + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        sIsUpdating = false;
    }

    public static int removeTo(MediaItem mediaItem, String str) {
        return removeTo(mediaItem, str, true);
    }

    public static void setAsEssential(List<Long> list) {
        unHidePeople(list);
    }

    public static void unHidePeople(List<Long> list) {
        if (!list.isEmpty()) {
            sIsUpdating = true;
            new PeopleApi().unHideCreature(list);
            sIsUpdating = false;
        }
    }

    public static boolean unmerge(String str, List<Long> list, String str2, String str3, boolean z, MyQueryInterface myQueryInterface) {
        long j2;
        boolean z3;
        List<Long> list2 = list;
        MyQueryInterface myQueryInterface2 = myQueryInterface;
        sIsUpdating = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (IdentityCreatureUtil.isAssignedCreature(str)) {
            j2 = IdentityCreatureUtil.getIdentityId(str);
        } else {
            j2 = 1;
        }
        String str4 = str2;
        removeIndexingOnUnmerge(j2, str4, str3);
        if (IdentityCreatureUtil.isAssignedCreature(str)) {
            long identityId = IdentityCreatureUtil.getIdentityId(str);
            if (z) {
                z3 = new PeopleApi().resetCreatureIdValue(list2, identityId);
                sPeopleDataHolder.updateByPerson(identityId, identityId, str4);
            } else {
                z3 = new PeopleApi().resetCreatureIdValue(list2);
                sPeopleDataHolder.updateByPerson(identityId, identityId, (String) null);
            }
            new PeopleApi().delete(new long[]{identityId});
            appendIndexingOnUnmerge(list2);
            A.a.x(j.j(identityId, "undoMerge { person_id:", "} +"), currentTimeMillis, "PeopleDataManager");
        } else {
            long identityId2 = IdentityCreatureUtil.getIdentityId(str);
            z3 = new PeopleApi().resetCreatureIdValue(new ArrayList(Collections.singletonList(Long.valueOf(identityId2))));
            sPeopleDataHolder.updateByGroup(identityId2, 1, "");
            A.a.x(j.j(identityId2, "undoMerge { group_id:", "} +"), currentTimeMillis, "PeopleDataManager");
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE && myQueryInterface2.isTop5Creature(str, myQueryInterface2.getTop5List())) {
            myQueryInterface2.removeTop5List((ArrayList<String>) null, new ArrayList(Collections.singletonList(str)));
        }
        sIsUpdating = false;
        return z3;
    }

    public static void updateCustomRelationship(String str, String str2) {
        new PeopleApi().updateCustomRelationship(str, str2);
    }

    private static long updateDatabase(String str, CreatureNameData creatureNameData) {
        return new PeopleApi().editPeopleData(creatureNameData.getName(), creatureNameData.getRelationshipForDB(), str, creatureNameData.getCreatureId(), creatureNameData.getContactRawId(), creatureNameData.getCreatureUuid(), creatureNameData.isTaggedData(), creatureNameData.isMyProfile());
    }

    public static void updateFaceClusterExcludedSuggestion(String str, ArrayList<String> arrayList) {
        new PeopleApi().updateFaceClusterExcludedSuggestion(str, arrayList);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void updateNewCover(java.lang.String r6, long r7) {
        /*
            long r3 = com.samsung.android.gallery.support.utils.IdentityCreatureUtil.getUnifiedIdentityId(r6)
            com.samsung.android.gallery.database.dal.mp.helper.PeopleApi r6 = new com.samsung.android.gallery.database.dal.mp.helper.PeopleApi
            r6.<init>()
            android.database.Cursor r6 = r6.getMediaId(r7, r3)
            if (r6 == 0) goto L_0x0032
            boolean r0 = r6.moveToFirst()     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0032
            java.lang.String r0 = "__title_info"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ all -> 0x0026 }
        L_0x001b:
            int r1 = r6.getInt(r0)     // Catch:{ all -> 0x0026 }
            boolean r2 = r6.moveToNext()     // Catch:{ all -> 0x0026 }
            if (r2 != 0) goto L_0x001b
            goto L_0x0033
        L_0x0026:
            r0 = move-exception
            r7 = r0
            r6.close()     // Catch:{ all -> 0x002c }
            goto L_0x0031
        L_0x002c:
            r0 = move-exception
            r6 = r0
            r7.addSuppressed(r6)
        L_0x0031:
            throw r7
        L_0x0032:
            r1 = 0
        L_0x0033:
            if (r6 == 0) goto L_0x0038
            r6.close()
        L_0x0038:
            com.samsung.android.gallery.database.dal.mp.helper.PeopleApi r0 = new com.samsung.android.gallery.database.dal.mp.helper.PeopleApi
            r0.<init>()
            r6 = 1000000000(0x3b9aca00, float:0.0047237873)
            int r5 = r1 + r6
            r1 = r7
            r0.updateTitleInfo(r1, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.creature.people.PeopleDataManager.updateNewCover(java.lang.String, long):void");
    }

    public static void updateOldCover(String str) {
        Throwable th;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        long unifiedIdentityId = IdentityCreatureUtil.getUnifiedIdentityId(str);
        Cursor customCoverId = new PeopleApi().getCustomCoverId(unifiedIdentityId);
        if (customCoverId != null) {
            try {
                if (customCoverId.moveToFirst()) {
                    int columnIndex = customCoverId.getColumnIndex("__absID");
                    int columnIndex2 = customCoverId.getColumnIndex("__title_info");
                    do {
                        arrayList.add(Long.valueOf(customCoverId.getLong(columnIndex)));
                        arrayList2.add(Integer.valueOf(customCoverId.getInt(columnIndex2)));
                    } while (customCoverId.moveToNext());
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (customCoverId != null) {
            customCoverId.close();
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            new PeopleApi().updateTitleInfo(((Long) arrayList.get(i2)).longValue(), unifiedIdentityId, ((Integer) arrayList2.get(i2)).intValue() - CommonConstants.SupportedApiMinVersion.VERSION_10_0);
        }
        return;
        throw th;
    }

    private static void updatePeopleDataHolder(String str, long j2, String str2) {
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            sPeopleDataHolder.updateByUnifiedId(IdentityCreatureUtil.getUnifiedIdentityId(str), j2, str2);
            return;
        }
        long j3 = j2;
        String str3 = str2;
        long identityId = IdentityCreatureUtil.getIdentityId(str);
        if (IdentityCreatureUtil.isAssignedCreature(str)) {
            sPeopleDataHolder.updateByPerson(identityId, j3, str3);
        } else {
            sPeopleDataHolder.updateByGroup(identityId, j3, str3);
        }
    }

    public static int removeTo(MediaItem mediaItem, String str, boolean z) {
        int removeTo = new PeopleApi().removeTo(mediaItem, str);
        if (removeTo > 0) {
            deletePeopleData(mediaItem.getFileId());
            if (SUPPORT_INDEXING && z) {
                long identityId = IdentityCreatureUtil.getIdentityId(str);
                CreatureIndexingBuilder unifiedId = new CreatureIndexingBuilder().removeFileId(mediaItem.getFileId()).unifiedId(IdentityCreatureUtil.getUnifiedIdentityId(str), (-CreatureData.of(mediaItem).faceGroupId) - 100000);
                if (IdentityCreatureUtil.isAssignedCreature(str)) {
                    unifiedId.tagId(identityId, 1);
                }
                sIndexingDelegate.index(unifiedId);
            }
        }
        return removeTo;
    }

    public static List<Pair<Long, Long>> getSimilarContactData(List<Long> list) {
        Cursor similarContactTableCursor;
        long currentTimeMillis = System.currentTimeMillis();
        String joinIds = CursorHelper.joinIds(list);
        ArrayList arrayList = new ArrayList();
        try {
            similarContactTableCursor = new SimilarContactApi().getSimilarContactTableCursor(joinIds);
            if (similarContactTableCursor != null) {
                if (similarContactTableCursor.moveToFirst()) {
                    do {
                        long j2 = similarContactTableCursor.getLong(similarContactTableCursor.getColumnIndex("__creatureFaceGroupID"));
                        long j3 = similarContactTableCursor.getLong(similarContactTableCursor.getColumnIndex("name_raw_contact_id"));
                        Log.d("PeopleDataManager", "getSimilarContactData {" + j2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + j3 + "}");
                        arrayList.add(new Pair(Long.valueOf(j2), Long.valueOf(j3)));
                    } while (similarContactTableCursor.moveToNext());
                }
            }
            if (similarContactTableCursor != null) {
                similarContactTableCursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        Log.d("PeopleDataManager", "getSimilarContactData :", arrayList, " +" + (System.currentTimeMillis() - currentTimeMillis));
        return arrayList;
        throw th;
    }

    private static void updatePeopleDataHolder(long[] jArr, long j2, String str) {
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            sPeopleDataHolder.updateByUnifiedId(jArr, j2, str);
            return;
        }
        PeopleDataHolder peopleDataHolder = sPeopleDataHolder;
        peopleDataHolder.updateByPerson(Arrays.stream(jArr).filter(new c(2)).toArray(), j2, str);
        peopleDataHolder.updateByGroup(Arrays.stream(jArr).filter(new c(0)).toArray(), j2, str);
    }

    public static MediaItem loadHeaderItem(String str, String str2, long j2) {
        Cursor query;
        Throwable th;
        try {
            query = DbCompat.query("mp://People/files", new V8.b(str, str2, j2, 0));
            if (query != null) {
                if (query.moveToFirst()) {
                    MediaItem load = MediaItemLoader.load(query);
                    load.setCategory("People");
                    MediaItem cloneCreatureItem = MediaItemBuilder.cloneCreatureItem(load);
                    query.close();
                    return cloneCreatureItem;
                }
            }
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }
}
