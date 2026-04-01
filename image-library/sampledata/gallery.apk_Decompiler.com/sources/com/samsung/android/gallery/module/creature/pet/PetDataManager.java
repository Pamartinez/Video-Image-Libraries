package com.samsung.android.gallery.module.creature.pet;

import A4.B;
import Ad.C0720a;
import B8.d;
import L5.b;
import N2.j;
import V8.a;
import W8.C0579a;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.CacheProviderHelper;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.helper.PetApi;
import com.samsung.android.gallery.module.creature.base.CreatureIndexingDelegate;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.base.MyQueryInterface;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.support.search.CreatureIndexingBuilder;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PetDataManager {
    private static final boolean SUPPORT_INDEXING = Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH);
    private static CreatureIndexingDelegate sIndexingDelegate = new PetIndexingDelegate();

    public static long addName(String str, CreatureNameData creatureNameData) {
        String str2;
        long j2;
        long currentTimeMillis = System.currentTimeMillis();
        boolean isAssignedCreature = IdentityCreatureUtil.isAssignedCreature(str);
        long identityId = IdentityCreatureUtil.getIdentityId(str);
        if (isAssignedCreature) {
            str2 = getPetName(identityId);
        } else {
            str2 = getPetNameByGroupId(identityId);
        }
        long updateDatabase = updateDatabase(str, creatureNameData);
        if (SUPPORT_INDEXING) {
            CreatureIndexingBuilder tagName = new CreatureIndexingBuilder().tagName(str2, creatureNameData.getName());
            if (isAssignedCreature) {
                j2 = identityId;
            } else {
                j2 = 1;
            }
            sIndexingDelegate.index(tagName.tagId(j2, updateDatabase).unifiedId(IdentityCreatureUtil.getUnifiedIdentityId(str), updateDatabase).targetTagId(updateDatabase));
        }
        StringBuilder j3 = j.j(identityId, "addName {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        j3.append(updateDatabase);
        j3.append("} +");
        j3.append(System.currentTimeMillis() - currentTimeMillis);
        Log.d("PetDataManager", j3.toString());
        return updateDatabase;
    }

    private static String addShowCondition() {
        if (!PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE || PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            return "";
        }
        return " and IFNULL(hide, 0) = 0";
    }

    private static void appendIndexingOnMerge(String str, long j2) {
        if (SUPPORT_INDEXING) {
            sIndexingDelegate.index(new CreatureIndexingBuilder().tagName((String) null, str).tagId(1, j2).unifiedId(Long.MIN_VALUE, j2).targetTagId(j2));
        }
    }

    private static void appendIndexingOnUnmerge(List<Long> list) {
        if (SUPPORT_INDEXING) {
            list.forEach(new C0579a(0));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String[] getAutoSelectCoverData(java.lang.String r4) {
        /*
            long r0 = com.samsung.android.gallery.support.utils.IdentityCreatureUtil.getUnifiedIdentityId(r4)
            com.samsung.android.gallery.database.dal.mp.helper.PetApi r4 = new com.samsung.android.gallery.database.dal.mp.helper.PetApi
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
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.creature.pet.PetDataManager.getAutoSelectCoverData(java.lang.String):java.lang.String[]");
    }

    public static List<Long> getGroupIds(String str) {
        ArrayList arrayList = new ArrayList();
        if (IdentityCreatureUtil.isAssignedCreature(str)) {
            return new PetApi().getGroupIdsById(IdentityCreatureUtil.getIdentityId(str));
        }
        arrayList.add(Long.valueOf(IdentityCreatureUtil.getIdentityId(str)));
        return arrayList;
    }

    public static String getIncludedMediaIds(List<Long> list) {
        ArrayList arrayList = new ArrayList();
        new ArrayList(list).forEach(new d(arrayList, 19));
        return (String) arrayList.stream().distinct().map(new b(5)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
    }

    public static List<Long> getMediaIdListByUnifiedId(Long l) {
        ArrayList arrayList = new ArrayList();
        Cursor mediaIdListCursorByUnifiedId = new PetApi().getMediaIdListCursorByUnifiedId(l.longValue());
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
        return arrayList;
        throw th;
    }

    private static String getPetName(long j2) {
        return new PetApi().getName(j2);
    }

    private static String getPetNameByGroupId(long j2) {
        return new PetApi().getNameByGroupId(j2);
    }

    public static void hidePet(List<Long> list, MyQueryInterface myQueryInterface) {
        new PetApi().hideCreature(list);
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE) {
            myQueryInterface.removeTop5List((ArrayList<String>) null, (ArrayList) list.stream().map(new a(8)).collect(Collectors.toCollection(new C0720a(1))));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadHeaderItem$3(String str, String str2, long j2, QueryParams queryParams) {
        queryParams.setSubCategory(str);
        queryParams.setFileIds(str2);
        queryParams.mFaceGroupId = j2;
    }

    public static MediaItem loadHeaderItem(String str) {
        Cursor query;
        try {
            query = DbCompat.query("mp://Pets", new B(str, 18));
            if (query != null) {
                if (query.moveToFirst()) {
                    MediaItem load = MediaItemLoader.load(query);
                    load.setCategory("Pet");
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
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static ArrayList<MediaItem> loadItems(ArrayList<String> arrayList) {
        Cursor query;
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        try {
            query = DbCompat.query("mp://Pets", new B((String) arrayList.stream().collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), 19));
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
            query = CacheProviderHelper.query("location://search/fileList/Category/Pet");
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
            rawQuery = secMpQueryExecutor.rawQuery("select group_concat(DISTINCT recommended_id) from cluster_rect where sec_media_id = " + j2 + addShowCondition(), "CreatureConcatId_pets");
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
            Log.e((CharSequence) "PetDataManager", "loadRelatedRepresentItem failed", (Throwable) e);
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static void merge(String[] strArr, String str, MyQueryInterface myQueryInterface) {
        long currentTimeMillis = System.currentTimeMillis();
        removeIndexingOnMerge(strArr);
        long[] array = Arrays.stream(strArr).mapToLong(new E5.b(6)).toArray();
        long identityId = IdentityCreatureUtil.getIdentityId(str);
        new PetApi().updateId(array, identityId);
        new PetApi().delete(array);
        appendIndexingOnMerge(getPetName(identityId), identityId);
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE) {
            myQueryInterface.mergeTop5List(new ArrayList(Arrays.asList(strArr)), str);
        }
        A.a.x(j.k("merge {", str, "} +"), currentTimeMillis, "PetDataManager");
    }

    private static void removeIndexingOnMerge(String[] strArr) {
        CreatureIndexingBuilder creatureIndexingBuilder;
        if (SUPPORT_INDEXING) {
            for (String str : strArr) {
                long identityId = IdentityCreatureUtil.getIdentityId(str);
                if (IdentityCreatureUtil.isAssignedCreature(str)) {
                    creatureIndexingBuilder = new CreatureIndexingBuilder().tagName(getPetName(identityId), (String) null).tagId(identityId, 1).unifiedId(identityId, Long.MIN_VALUE).targetTagId(identityId);
                } else {
                    creatureIndexingBuilder = new CreatureIndexingBuilder().unifiedId(IdentityCreatureUtil.getUnifiedIdentityId(str), Long.MIN_VALUE).targetGroupId(identityId);
                }
                sIndexingDelegate.index(creatureIndexingBuilder);
            }
        }
    }

    private static void removeIndexingOnUnmerge(long j2, String str) {
        if (SUPPORT_INDEXING) {
            sIndexingDelegate.index(new CreatureIndexingBuilder().tagName(str, (String) null).tagId(j2, 1).unifiedId(j2, Long.MIN_VALUE).targetTagId(j2));
        }
    }

    public static int removeTo(MediaItem mediaItem, String str) {
        return removeTo(mediaItem, str, true);
    }

    public static boolean unmerge(String str, List<Long> list, String str2, MyQueryInterface myQueryInterface) {
        return unmerge(str, list, str2, false, myQueryInterface);
    }

    private static long updateDatabase(String str, CreatureNameData creatureNameData) {
        return new PetApi().edit(creatureNameData.getName(), str, creatureNameData.getCreatureId());
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void updateNewCover(java.lang.String r6, long r7) {
        /*
            long r3 = com.samsung.android.gallery.support.utils.IdentityCreatureUtil.getUnifiedIdentityId(r6)
            com.samsung.android.gallery.database.dal.mp.helper.PetApi r6 = new com.samsung.android.gallery.database.dal.mp.helper.PetApi
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
            com.samsung.android.gallery.database.dal.mp.helper.PetApi r0 = new com.samsung.android.gallery.database.dal.mp.helper.PetApi
            r0.<init>()
            r6 = 1000000000(0x3b9aca00, float:0.0047237873)
            int r5 = r1 + r6
            r1 = r7
            r0.updateTitleInfo(r1, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.creature.pet.PetDataManager.updateNewCover(java.lang.String, long):void");
    }

    public static void updateOldCover(String str) {
        Throwable th;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        long unifiedIdentityId = IdentityCreatureUtil.getUnifiedIdentityId(str);
        Cursor customCoverId = new PetApi().getCustomCoverId(unifiedIdentityId);
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
            new PetApi().updateTitleInfo(((Long) arrayList.get(i2)).longValue(), unifiedIdentityId, ((Integer) arrayList2.get(i2)).intValue() - CommonConstants.SupportedApiMinVersion.VERSION_10_0);
        }
        return;
        throw th;
    }

    public static void updatePetHideState(ArrayList<Long> arrayList, ArrayList<Long> arrayList2) {
        new PetApi().updateCreatureHideState(arrayList, arrayList2);
    }

    public static int removeTo(MediaItem mediaItem, String str, boolean z) {
        int removeTo = new PetApi().removeTo(mediaItem, str);
        if (removeTo > 0) {
            deletePetData(mediaItem.getFileId());
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

    public static boolean unmerge(String str, List<Long> list, String str2, boolean z, MyQueryInterface myQueryInterface) {
        boolean z3;
        long currentTimeMillis = System.currentTimeMillis();
        removeIndexingOnUnmerge(IdentityCreatureUtil.isAssignedCreature(str) ? IdentityCreatureUtil.getIdentityId(str) : 1, str2);
        if (IdentityCreatureUtil.isAssignedCreature(str)) {
            long identityId = IdentityCreatureUtil.getIdentityId(str);
            if (z) {
                z3 = new PetApi().resetCreatureIdValue(list, identityId);
            } else {
                z3 = new PetApi().resetCreatureIdValue(list);
            }
            new PetApi().delete(new long[]{identityId});
            appendIndexingOnUnmerge(list);
            A.a.x(j.j(identityId, "undoMerge { cluster_info_id:", "} +"), currentTimeMillis, "PetDataManager");
        } else {
            long identityId2 = IdentityCreatureUtil.getIdentityId(str);
            boolean resetCreatureIdValue = new PetApi().resetCreatureIdValue(new ArrayList(Collections.singletonList(Long.valueOf(identityId2))));
            A.a.x(j.j(identityId2, "undoMerge { group_id:", "} +"), currentTimeMillis, "PetDataManager");
            z3 = resetCreatureIdValue;
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE && myQueryInterface.isTop5Creature(str, myQueryInterface.getTop5List())) {
            myQueryInterface.removeTop5List((ArrayList<String>) null, new ArrayList(Collections.singletonList(str)));
        }
        return z3;
    }

    public static MediaItem loadHeaderItem(String str, String str2, long j2) {
        Cursor query;
        Throwable th;
        try {
            query = DbCompat.query("mp://Pets/files", new V8.b(str, str2, j2, 1));
            if (query != null) {
                if (query.moveToFirst()) {
                    MediaItem load = MediaItemLoader.load(query);
                    load.setCategory("Pet");
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

    public static void deletePetData(long j2) {
    }
}
