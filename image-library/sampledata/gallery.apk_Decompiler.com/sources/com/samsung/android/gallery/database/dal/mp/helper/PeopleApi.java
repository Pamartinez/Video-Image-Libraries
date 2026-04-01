package com.samsung.android.gallery.database.dal.mp.helper;

import A.a;
import B5.e;
import V8.c;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.table.MpCreatureFacesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpCreatureTagTable;
import com.samsung.android.gallery.database.dal.mp.table.MpCreatureView;
import com.samsung.android.gallery.database.dal.mp.table.MpFacesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpPeopleView;
import com.samsung.android.gallery.database.dal.mp.table.MpPersonTable;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.CmhUri;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PeopleApi extends CreatureApi {
    private static final Uri CMH_FACES_URI = Uri.parse("content://com.samsung.cmh/external/faces");
    private static final Uri CMH_PERSONS_URI = Uri.parse("content://com.samsung.cmh/internal/persons");

    public PeopleApi() {
        super(new QueryParams());
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.util.function.LongFunction, java.lang.Object] */
    private String asList(long[] jArr) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        Arrays.stream(jArr).mapToObj(new Object()).forEach(new e(stringJoiner, 2));
        return stringJoiner.toString();
    }

    private long findPeronIdForContact(long j2, long j3, String str, String str2, String str3, boolean z) {
        long j8;
        long findPersonIdByRawId = findPersonIdByRawId(j2);
        if (isDefault(findPersonIdByRawId)) {
            j8 = j3;
        } else {
            j8 = findPersonIdByRawId;
        }
        if (isDefault(j8)) {
            return insertPeople(z, j2, str, str2, str3);
        }
        return updatePeople(j8, j2, str, str2, str3);
    }

    private long findPersonId(String str, String str2, long j2, long j3, String str3, boolean z, boolean z3) {
        if (z) {
            return findPersonIdForTagged(j2, str, str2, str3);
        }
        return findPeronIdForContact(j3, j2, str, str2, str3, z3);
    }

    private long findPersonIdForTagged(long j2, String str, String str2, String str3) {
        if (isDefault(j2)) {
            return insertPeople(false, -1, str, str2, str3);
        }
        return updatePeople(j2, 0, str, str2, str3);
    }

    private Cursor getFaceClusterCursor(String str) {
        MpFacesTable mpFacesTable = new MpFacesTable(this.mParams);
        mpFacesTable.resetProjectionForFaceCluster();
        long identityId = IdentityCreatureUtil.getIdentityId(str);
        if (IdentityCreatureUtil.isAssignedCreature(str)) {
            mpFacesTable.filterCreatureId(identityId);
        } else {
            mpFacesTable.filterGroupId(identityId);
            mpFacesTable.limit(1);
        }
        return getCursor(mpFacesTable.buildSelectQuery(), "GET_FACE_CLUSTER");
    }

    private Cursor getFaceClusterMergeAllDataCursor() {
        MpFacesTable mpFacesTable = new MpFacesTable(this.mParams);
        mpFacesTable.resetProjectionForFaceCluster();
        mpFacesTable.addProjection("F.recommended_id", "__creatureFaceRecommendedID");
        if (!TextUtils.isEmpty(this.mParams.mRecommendedIds)) {
            mpFacesTable.filterUnifiedIds(this.mParams.mRecommendedIds);
        }
        if (this.mParams.hasLimit()) {
            mpFacesTable.limit(this.mParams.getLimitSize());
        }
        mpFacesTable.groupByUnifiedId();
        return getCursor(mpFacesTable.buildSelectQuery(), "GET_FACE_CLUSTER_ALL");
    }

    private Cursor getPersonIdCursor(long j2) {
        MpPersonTable mpPersonTable = new MpPersonTable(this.mParams);
        mpPersonTable.filterContactRawId(j2);
        return getCursor(mpPersonTable.buildSelectQuery(), "GET_PERSON_ID_BY_RAW_ID");
    }

    private long insertPeople(boolean z, long j2, String str, String str2, String str3) {
        ContentValues c5 = C0086a.c("name", str);
        if (!TextUtils.isEmpty(str2)) {
            c5.put("relationship", str2);
        }
        c5.put("flag", Integer.valueOf(z ? 1 : 0));
        if (j2 != -1) {
            c5.put("contact_raw_id", Long.valueOf(j2));
        }
        if (!TextUtils.isEmpty(str3)) {
            c5.put("uuid", str3);
        }
        ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
        Uri insert = contentResolver.insert(getPersonUri(true), c5);
        if (insert == null || this.mParams.mTargetDb == QueryParams.TargetDbTypes.GMP) {
            return -1;
        }
        long parseId = ContentUris.parseId(insert);
        c5.put("_id", Long.valueOf(parseId));
        contentResolver.insert(getPersonUri(false), c5);
        return parseId;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updatePersonId$0(long j2) {
        return !IdentityCreatureUtil.isAssignedCreature(j2);
    }

    private void removeFromCmh(SearchRemoveInfo searchRemoveInfo) {
        remove(getFacesUri(false), searchRemoveInfo);
    }

    private void updateByGroupId(long j2, long j3) {
        String f = a.f("group_id = ", j2);
        ContentValues contentValues = new ContentValues();
        contentValues.put("person_id", Long.valueOf(j3));
        if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
            contentValues.put("recommended_id", Long.valueOf(j3));
        }
        updateFace(contentValues, f, (String[]) null);
    }

    private void updateByPersonId(long j2, long j3) {
        String f = a.f("person_id = ", j2);
        ContentValues contentValues = new ContentValues();
        contentValues.put("person_id", Long.valueOf(j3));
        if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
            contentValues.put("recommended_id", Long.valueOf(j3));
        }
        updateFace(contentValues, f, (String[]) null);
    }

    private void updatePerson(ContentValues contentValues, String str, String[] strArr) {
        ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
        contentResolver.update(getPersonUri(true), contentValues, str, strArr);
        if (this.mParams.mTargetDb != QueryParams.TargetDbTypes.GMP) {
            contentResolver.update(getPersonUri(false), contentValues, str, strArr);
        }
    }

    public ContentProviderOperation.Builder createContentProviderOperationBuilder(Uri uri, SearchRemoveInfo searchRemoveInfo) {
        ContentProviderOperation.Builder createContentProviderOperationBuilder = super.createContentProviderOperationBuilder(uri, searchRemoveInfo);
        if (PreferenceFeatures.OneUi5x.SUPPORT_FACE_CLUSTER && SdkConfig.atLeast(SdkConfig.GED.T)) {
            createContentProviderOperationBuilder.withValue("excluded_facegroup_suggestion", (Object) null);
        }
        return createContentProviderOperationBuilder;
    }

    public MpCreatureView createCreatureView() {
        return new MpPeopleView(this.mParams);
    }

    public MpCreatureFacesTable createFacesTable() {
        return new MpFacesTable(this.mParams);
    }

    public MpCreatureTagTable createTagTable() {
        return new MpPersonTable(this.mParams);
    }

    public void delete(long[] jArr) {
        String p6 = C0212a.p(new StringBuilder("_id in ("), asList(jArr), ")");
        ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
        contentResolver.delete(getPersonUri(true), p6, (String[]) null);
        if (this.mParams.mTargetDb != QueryParams.TargetDbTypes.GMP) {
            contentResolver.delete(getPersonUri(false), p6, (String[]) null);
        }
    }

    public void deleteRelationship(long j2) {
        if (Features.isEnabled(Features.SUPPORT_RELATIONSHIP_SEARCH)) {
            ContentValues contentValues = new ContentValues();
            contentValues.putNull("relationship");
            updatePerson(contentValues, "_id = ? ", new String[]{String.valueOf(j2)});
        }
    }

    public long editPeopleData(String str, String str2, String str3, long j2, long j3, String str4, boolean z, boolean z3) {
        String str5;
        long j8;
        long identityId = IdentityCreatureUtil.getIdentityId(str3);
        if (!IdentityCreatureUtil.isAssignedCreature(str3)) {
            identityId = 1;
        }
        if (!Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK) || !TextUtils.isEmpty(str4) || !isDefault(identityId)) {
            str5 = str4;
        } else {
            str5 = UUID.randomUUID().toString();
        }
        if (j2 > 1) {
            j8 = j2;
        } else {
            j8 = findPersonId(str, str2, identityId, j3, str5, z, z3);
        }
        updatePersonId(str3, j8);
        return j8;
    }

    public String findCreatureUuidByRawId(long j2) {
        Cursor personIdCursor;
        String str = null;
        try {
            personIdCursor = getPersonIdCursor(j2);
            if (personIdCursor != null) {
                if (personIdCursor.moveToFirst()) {
                    str = personIdCursor.getString(personIdCursor.getColumnIndex("__creatureContactUuid"));
                }
            }
            if (personIdCursor != null) {
                personIdCursor.close();
            }
            return str;
        } catch (SQLiteException e) {
            String str2 = this.TAG;
            Log.d(str2, "SQLiteException : " + e);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public long findPersonIdByRawId(long j2) {
        Cursor personIdCursor;
        long j3 = 1;
        try {
            personIdCursor = getPersonIdCursor(j2);
            if (personIdCursor != null) {
                if (personIdCursor.moveToFirst()) {
                    j3 = personIdCursor.getLong(personIdCursor.getColumnIndex("__creatureID"));
                }
            }
            if (personIdCursor != null) {
                personIdCursor.close();
            }
            return j3;
        } catch (SQLiteException e) {
            String str = this.TAG;
            Log.d(str, "SQLiteException : " + e);
            return 1;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public Uri getCreatureFaceUri(boolean z) {
        return getFacesUri(z);
    }

    public String getCreatureIdColumn() {
        return "person_id";
    }

    public String getCreatureType() {
        return MpCreatureView.CreatureType.PEOPLE.name();
    }

    public void getFaceClusterData(String str, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        Cursor faceClusterCursor;
        try {
            faceClusterCursor = getFaceClusterCursor(str);
            if (faceClusterCursor != null) {
                if (faceClusterCursor.moveToFirst()) {
                    String string = faceClusterCursor.getString(faceClusterCursor.getColumnIndex("__groupFacedataValues"));
                    if (string != null) {
                        for (String str2 : string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
                            if (!arrayList.contains(str2)) {
                                arrayList.add(str2);
                            }
                        }
                        for (String remove : faceClusterCursor.getString(faceClusterCursor.getColumnIndex("__creatureFaceGroupIDs")).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
                            arrayList.remove(remove);
                        }
                        String string2 = faceClusterCursor.getString(faceClusterCursor.getColumnIndex("__excludedFacegroupSuggestion"));
                        if (string2 != null) {
                            for (String str3 : string2.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
                                if (!arrayList2.contains(str3)) {
                                    arrayList2.add(str3);
                                }
                                arrayList.remove(str3);
                            }
                        }
                    }
                    faceClusterCursor.close();
                    return;
                }
            }
            if (faceClusterCursor == null) {
                return;
            }
            faceClusterCursor.close();
            return;
        } catch (SQLiteException e) {
            Log.e(this.TAG, "getFaceClusterData : " + e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public List<Pair<String, List<String>>> getFaceClusterMergeAllData() {
        Cursor faceClusterMergeAllDataCursor;
        ArrayList arrayList = new ArrayList();
        try {
            faceClusterMergeAllDataCursor = getFaceClusterMergeAllDataCursor();
            if (faceClusterMergeAllDataCursor != null) {
                if (faceClusterMergeAllDataCursor.moveToFirst()) {
                    do {
                        String string = faceClusterMergeAllDataCursor.getString(faceClusterMergeAllDataCursor.getColumnIndex("__groupFacedataValues"));
                        if (string != null) {
                            long j2 = faceClusterMergeAllDataCursor.getLong(faceClusterMergeAllDataCursor.getColumnIndex("__creatureFaceRecommendedID"));
                            ArrayList arrayList2 = new ArrayList();
                            ArrayList arrayList3 = new ArrayList();
                            for (String str : string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
                                if (!arrayList2.contains(str)) {
                                    arrayList2.add(str);
                                }
                            }
                            for (String remove : faceClusterMergeAllDataCursor.getString(faceClusterMergeAllDataCursor.getColumnIndex("__creatureFaceGroupIDs")).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
                                arrayList2.remove(remove);
                            }
                            String string2 = faceClusterMergeAllDataCursor.getString(faceClusterMergeAllDataCursor.getColumnIndex("__excludedFacegroupSuggestion"));
                            if (string2 != null) {
                                for (String str2 : string2.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
                                    if (!arrayList3.contains(str2)) {
                                        arrayList3.add(str2);
                                    }
                                    arrayList2.remove(str2);
                                }
                            }
                            if (!arrayList2.isEmpty()) {
                                arrayList.add(new Pair(String.valueOf(j2), arrayList2));
                            }
                        }
                    } while (faceClusterMergeAllDataCursor.moveToNext());
                    Log.e(this.TAG, "result : " + arrayList);
                }
            }
            if (faceClusterMergeAllDataCursor != null) {
                faceClusterMergeAllDataCursor.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            Log.e(this.TAG, "getFaceClusterMergeAllData : " + e);
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String getFaceTableName() {
        return "faces";
    }

    public final Uri getFacesUri(boolean z) {
        if (z) {
            return CmhUri.getFaces();
        }
        return CMH_FACES_URI;
    }

    public Cursor getMeTaggedCursor() {
        MpCreatureView createCreatureView = createCreatureView();
        if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE) {
            createCreatureView.addCreatureFaceScoreProjection();
            createCreatureView.orderByFaceScore();
        }
        createCreatureView.filterBurstShotBestImage(false);
        createCreatureView.filterRelationship("me");
        if (PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
            createCreatureView.filterHidden(0);
        }
        Query query = new Query(createCreatureView.buildSelectQuery());
        query.getQueryBuilder().groupBy("__creatureFaceRecommendedID");
        query.getQueryBuilder().having(createCreatureView.getCreatureHaving());
        query.getQueryBuilder().addProjection(new String[]{"*", "count(distinct __absID) as __count"});
        return getCursor(query, "GET_ME_TAGGED");
    }

    public String getMediaIdColumn() {
        if (Features.isEnabled(Features.USE_SEC_MP)) {
            return super.getMediaIdColumn();
        }
        return "media_id";
    }

    public final Uri getPersonUri(boolean z) {
        if (z) {
            return CmhUri.getPersons();
        }
        return CMH_PERSONS_URI;
    }

    public HashMap<Long, Long> getRecommendedIds(long[] jArr) {
        HashMap<Long, Long> hashMap = new HashMap<>();
        Cursor rawQuery = new SecMpQueryExecutor().rawQuery(C0212a.p(new StringBuilder("select group_id, recommended_id from faces where group_id in ("), asList(jArr), ") group by group_id"), "getRecommendedIds");
        if (rawQuery != null) {
            try {
                if (rawQuery.moveToFirst()) {
                    do {
                        hashMap.put(Long.valueOf(rawQuery.getLong(rawQuery.getColumnIndex(BundleKey.GROUP_ID))), Long.valueOf(rawQuery.getLong(rawQuery.getColumnIndex("recommended_id"))));
                    } while (rawQuery.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        return hashMap;
        throw th;
    }

    public Cursor getRelationshipCursor() {
        MpPersonTable mpPersonTable = new MpPersonTable(this.mParams);
        mpPersonTable.resetProjectionForRelationship();
        mpPersonTable.groupByRelationship();
        return getCursor(mpPersonTable.buildSelectQuery(), "GET_PERSON_RELATIONSHIP");
    }

    public String getSelection(SearchRemoveInfo searchRemoveInfo, String str) {
        if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
            return super.getSelection(searchRemoveInfo, str);
        }
        if (searchRemoveInfo.isBurstShot()) {
            String burstShotFileIds = getBurstShotFileIds(searchRemoveInfo.getAlbumId(), searchRemoveInfo.getBurstGroupId());
            if (!TextUtils.isEmpty(burstShotFileIds)) {
                if (IdentityCreatureUtil.isAssignedCreature(searchRemoveInfo.getIdentityInfo())) {
                    return str + " IN " + burstShotFileIds + " AND person_id = ?";
                }
                return str + " IN " + burstShotFileIds + " AND group_id = ?";
            }
        }
        if (IdentityCreatureUtil.isAssignedCreature(searchRemoveInfo.getIdentityInfo())) {
            StringBuilder t = C0212a.t(str, " = ");
            t.append(searchRemoveInfo.getFileId());
            t.append(" AND person_id = ?");
            return t.toString();
        }
        StringBuilder t3 = C0212a.t(str, " = ");
        t3.append(searchRemoveInfo.getFileId());
        t3.append(" AND group_id = ?");
        return t3.toString();
    }

    public String[] getSelectionArgs(SearchRemoveInfo searchRemoveInfo) {
        if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
            return super.getSelectionArgs(searchRemoveInfo);
        }
        ArrayList arrayList = new ArrayList();
        if (IdentityCreatureUtil.isAssignedCreature(searchRemoveInfo.getIdentityInfo())) {
            arrayList.add(String.valueOf(IdentityCreatureUtil.getIdentityId(searchRemoveInfo.getIdentityInfo())));
        } else {
            arrayList.add(String.valueOf(searchRemoveInfo.getFaceGroupId()));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public int handleRemoveTo(SearchRemoveInfo searchRemoveInfo) {
        if (this.mParams.mTargetDb != QueryParams.TargetDbTypes.GMP) {
            removeFromCmh(searchRemoveInfo);
        }
        return remove(CmhUri.getFaces(), searchRemoveInfo);
    }

    public void removeCustomRelationship(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("relationship");
        updatePerson(contentValues, C0212a.m("relationship is '", str, "'"), (String[]) null);
    }

    public String tag() {
        return "PeopleApi";
    }

    public void updateCustomRelationship(String str, String str2) {
        updatePerson(C0086a.c("relationship", str2), C0212a.m("relationship is '", str, "'"), (String[]) null);
    }

    public boolean updateFace(ContentValues contentValues, String str, String[] strArr) {
        int i2;
        ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
        int update = contentResolver.update(getFacesUri(true), contentValues, str, strArr);
        if (this.mParams.mTargetDb != QueryParams.TargetDbTypes.GMP) {
            i2 = contentResolver.update(getFacesUri(false), contentValues, str, strArr);
        } else {
            i2 = 0;
        }
        if (update <= 0 || i2 <= 0) {
            return false;
        }
        return true;
    }

    public void updateFaceClusterExcludedSuggestion(String str, ArrayList<String> arrayList) {
        String str2;
        ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
        if (contentResolver != null) {
            long identityId = IdentityCreatureUtil.getIdentityId(str);
            if (IdentityCreatureUtil.isAssignedCreature(str)) {
                str2 = a.f("person_id = ", identityId);
            } else {
                str2 = a.f("group_id = ", identityId);
            }
            ContentValues c5 = C0086a.c("excluded_facegroup_suggestion", String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList));
            contentResolver.update(getFacesUri(true), c5, str2, (String[]) null);
            if (this.mParams.mTargetDb != QueryParams.TargetDbTypes.GMP) {
                contentResolver.update(getFacesUri(false), c5, str2, (String[]) null);
            }
        }
    }

    public long updatePeople(long j2, long j3, String str, String str2, String str3) {
        String f = a.f("_id = ", j2);
        ContentValues c5 = C0086a.c("name", str);
        c5.put("contact_raw_id", Long.valueOf(j3));
        c5.put("relationship", str2);
        updatePerson(c5, f, (String[]) null);
        return j2;
    }

    public void updatePersonId(String str, long j2) {
        if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
            updateByUnifiedId(IdentityCreatureUtil.getUnifiedIdentityId(str), j2);
            return;
        }
        long identityId = IdentityCreatureUtil.getIdentityId(str);
        if (IdentityCreatureUtil.isAssignedCreature(str)) {
            updateByPersonId(identityId, j2);
        } else {
            updateByGroupId(identityId, j2);
        }
    }

    public PeopleApi(QueryParams queryParams) {
        super(queryParams);
    }

    public void updatePersonId(long[] jArr, long j2) {
        if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
            updateByUnifiedId(jArr, j2);
            return;
        }
        updateByPersonId(Arrays.stream(jArr).filter(new c(2)).toArray(), j2);
        updateByGroupId(Arrays.stream(jArr).filter(new c(3)).toArray(), j2);
    }

    private void updateByGroupId(long[] jArr, long j2) {
        if (jArr.length >= 1) {
            String p6 = C0212a.p(new StringBuilder("group_id in ("), asList(jArr), ")");
            ContentValues contentValues = new ContentValues();
            contentValues.put("person_id", Long.valueOf(j2));
            if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
                contentValues.put("recommended_id", Long.valueOf(j2));
            }
            updateFace(contentValues, p6, (String[]) null);
        }
    }

    private void updateByPersonId(long[] jArr, long j2) {
        if (jArr.length >= 1) {
            String p6 = C0212a.p(new StringBuilder("person_id in ("), asList(jArr), ")");
            ContentValues contentValues = new ContentValues();
            contentValues.put("person_id", Long.valueOf(j2));
            if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
                contentValues.put("recommended_id", Long.valueOf(j2));
            }
            updateFace(contentValues, p6, (String[]) null);
        }
    }
}
