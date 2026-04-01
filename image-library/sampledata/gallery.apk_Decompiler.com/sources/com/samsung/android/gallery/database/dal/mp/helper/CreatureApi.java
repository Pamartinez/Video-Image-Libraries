package com.samsung.android.gallery.database.dal.mp.helper;

import A.a;
import B5.e;
import N2.j;
import V8.c;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.impl.GroupMediaImpl;
import com.samsung.android.gallery.database.dal.mp.table.MpCreatureFacesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpCreatureTagTable;
import com.samsung.android.gallery.database.dal.mp.table.MpCreatureView;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CreatureApi extends BaseImpl {
    private static final Uri SEC_MP_FACES_GROUP_URI = Uri.parse("content://secmedia/cmh/faces_group");
    protected final String mCreatureType = getCreatureType();

    public CreatureApi() {
        super(new QueryParams());
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.util.function.LongFunction, java.lang.Object] */
    private String asList(long[] jArr) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        Arrays.stream(jArr).mapToObj(new Object()).forEach(new e(stringJoiner, 2));
        return stringJoiner.toString();
    }

    private SearchRemoveInfo composeRemoveInfo(FileItemInterface fileItemInterface, String str) {
        long j2;
        Object extra = fileItemInterface.getExtra(ExtrasID.FACE_GROUP_ID);
        if (extra == null) {
            Log.e(this.TAG, "composeRemoveInfo failed. face group id is null.");
            return null;
        }
        SearchRemoveInfo albumId = new SearchRemoveInfo().setFileId(fileItemInterface.getFileId()).setFaceGroupId(((Long) extra).longValue()).setAlbumId(fileItemInterface.getAlbumID());
        if (fileItemInterface.isBurstShot()) {
            j2 = fileItemInterface.getGroupMediaId();
        } else {
            j2 = 0;
        }
        return albumId.setBurstGroupId(j2).setIdentityInfo(str);
    }

    private String filterRecommendedIds(List<Long> list) {
        return "recommended_id IN " + CursorHelper.joinIds(list);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0059 A[SYNTHETIC, Splitter:B:19:0x0059] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getConcatFaceGroupIdsWith(java.util.List<java.lang.Long> r5) {
        /*
            r4 = this;
            java.lang.String r0 = "gerFaceGroupIds(concat):"
            java.lang.String r1 = ""
            java.lang.String r5 = r4.filterRecommendedIds(r5)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "select group_concat(DISTINCT group_id) from "
            r2.<init>(r3)
            java.lang.String r3 = r4.getFaceTableName()
            r2.append(r3)
            java.lang.String r3 = " where "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r2 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Error | Exception -> 0x0053 }
            r2.<init>()     // Catch:{ Error | Exception -> 0x0053 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Error | Exception -> 0x0053 }
            r3.<init>(r0)     // Catch:{ Error | Exception -> 0x0053 }
            java.lang.String r0 = r4.getCreatureType()     // Catch:{ Error | Exception -> 0x0053 }
            r3.append(r0)     // Catch:{ Error | Exception -> 0x0053 }
            java.lang.String r0 = r3.toString()     // Catch:{ Error | Exception -> 0x0053 }
            android.database.Cursor r5 = r2.rawQuery((java.lang.String) r5, (java.lang.String) r0)     // Catch:{ Error | Exception -> 0x0053 }
            if (r5 == 0) goto L_0x0056
            boolean r0 = r5.moveToFirst()     // Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x0056
            r0 = 0
            java.lang.String r0 = r5.getString(r0)     // Catch:{ all -> 0x0049 }
            goto L_0x0057
        L_0x0049:
            r0 = move-exception
            r5.close()     // Catch:{ all -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r5 = move-exception
            r0.addSuppressed(r5)     // Catch:{ Error | Exception -> 0x0053 }
        L_0x0052:
            throw r0     // Catch:{ Error | Exception -> 0x0053 }
        L_0x0053:
            r5 = move-exception
            r0 = r1
            goto L_0x005e
        L_0x0056:
            r0 = r1
        L_0x0057:
            if (r5 == 0) goto L_0x0065
            r5.close()     // Catch:{ Error | Exception -> 0x005d }
            goto L_0x0065
        L_0x005d:
            r5 = move-exception
        L_0x005e:
            java.lang.String r4 = r4.TAG
            java.lang.String r2 = "getConcatFaceGroupIds failed"
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r4, (java.lang.String) r2, (java.lang.Throwable) r5)
        L_0x0065:
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 == 0) goto L_0x006c
            goto L_0x006d
        L_0x006c:
            r1 = r0
        L_0x006d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.mp.helper.CreatureApi.getConcatFaceGroupIdsWith(java.util.List):java.lang.String");
    }

    private Cursor getCreatureCursor(long j2) {
        MpCreatureFacesTable createFacesTable = createFacesTable();
        createFacesTable.filterGroupId(j2);
        Query buildSelectQuery = createFacesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "GET_" + getCreatureType() + "_CURSOR_BY_GROUP_ID");
    }

    private Cursor getGroupIdsCursor(long j2) {
        MpCreatureFacesTable createFacesTable = createFacesTable();
        createFacesTable.filterCreatureId(j2);
        createFacesTable.resetProjectionForGroupId();
        createFacesTable.groupByGroupID();
        Query buildSelectQuery = createFacesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "GET_" + getCreatureType() + "_GROUP_IDS_BY_CREATURE_TAG_ID");
    }

    private ArrayList<ContentProviderOperation> getOperationsToRemove(Uri uri, SearchRemoveInfo searchRemoveInfo) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        arrayList.add(createContentProviderOperationBuilder(uri, searchRemoveInfo).build());
        return arrayList;
    }

    private boolean isPeople() {
        return TextUtils.equals(getCreatureType(), MpCreatureView.CreatureType.PEOPLE.name());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateByUnifiedId$1(long j2) {
        return !IdentityCreatureUtil.isAssignedCreature(j2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ long lambda$updateByUnifiedId$2(long j2) {
        return j2 - 100000;
    }

    private boolean updateFacesGroupTable(ContentValues contentValues, String str, String[] strArr) {
        if (this.mQueryExecutor.getContentResolver().update(SEC_MP_FACES_GROUP_URI, contentValues, str, strArr) > 0) {
            return true;
        }
        return false;
    }

    public ContentProviderOperation.Builder createContentProviderOperationBuilder(Uri uri, SearchRemoveInfo searchRemoveInfo) {
        ContentProviderOperation.Builder withValue = ContentProviderOperation.newUpdate(uri).withSelection(getSelection(searchRemoveInfo, getMediaIdColumn()), getSelectionArgs(searchRemoveInfo)).withValue(getCreatureIdColumn(), 1L).withValue(BundleKey.GROUP_ID, Long.valueOf(-searchRemoveInfo.getFaceGroupId()));
        if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
            withValue.withValue("recommended_id", Long.valueOf((-searchRemoveInfo.getFaceGroupId()) - 100000));
        }
        return withValue;
    }

    public abstract MpCreatureView createCreatureView();

    public abstract MpCreatureFacesTable createFacesTable();

    public abstract MpCreatureTagTable createTagTable();

    public long findCreatureId(long j2) {
        Cursor creatureCursor;
        try {
            creatureCursor = getCreatureCursor(j2);
            if (creatureCursor != null) {
                if (creatureCursor.moveToFirst()) {
                    long j3 = creatureCursor.getLong(creatureCursor.getColumnIndex("__creatureID"));
                    creatureCursor.close();
                    return j3;
                }
            }
            if (creatureCursor == null) {
                return 1;
            }
            creatureCursor.close();
            return 1;
        } catch (SQLiteException e) {
            String str = this.TAG;
            Log.d(str, "SQLiteException : " + e);
            return 1;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public Cursor getAutoSelectCoverData(long j2) {
        MpCreatureFacesTable createFacesTable = createFacesTable();
        createFacesTable.resetProjectionForAutoSelectCover();
        createFacesTable.filterRecommendedId(j2);
        createFacesTable.orderByFaceScore();
        Query buildSelectQuery = createFacesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "GET_AUTO_SELECT_COVER_MEDIA_DATA_FROM_" + getCreatureType() + "_RECOMMENDED_ID");
    }

    public String getBurstShotFileIds(int i2, long j2) {
        Cursor burstShotCursor;
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "(", ")");
        QueryParams queryParams = new QueryParams(DbKey.FILES_BURSTSHOT);
        queryParams.setGroupMediaFilter(i2, j2);
        queryParams.addGroupType(GroupType.BURST);
        try {
            burstShotCursor = new GroupMediaImpl(queryParams).getBurstShotCursor();
            if (burstShotCursor != null) {
                if (burstShotCursor.moveToFirst()) {
                    do {
                        stringJoiner.add(String.valueOf(burstShotCursor.getLong(burstShotCursor.getColumnIndex("__absID"))));
                    } while (burstShotCursor.moveToNext());
                }
            }
            if (burstShotCursor != null) {
                burstShotCursor.close();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("getBurstShotFileIds failed, e="), this.TAG);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return stringJoiner.toString();
        throw th;
    }

    public abstract Uri getCreatureFaceUri(boolean z);

    public abstract String getCreatureIdColumn();

    public abstract String getCreatureType();

    public Cursor getCustomCoverId(long j2) {
        MpCreatureFacesTable createFacesTable = createFacesTable();
        createFacesTable.resetProjectionForTitleInfo();
        createFacesTable.filterRecommendedId(j2);
        createFacesTable.filterCustomCover();
        Query buildSelectQuery = createFacesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "GET_CUSTOM_COVER_ID_LIST_FROM_" + getCreatureType() + "_RECOMMENDED_ID");
    }

    public abstract String getFaceTableName();

    public List<Long> getGroupIdsById(long j2) {
        Cursor groupIdsCursor;
        ArrayList arrayList = new ArrayList();
        try {
            groupIdsCursor = getGroupIdsCursor(j2);
            if (groupIdsCursor != null) {
                if (groupIdsCursor.moveToFirst()) {
                    do {
                        arrayList.add(Long.valueOf(groupIdsCursor.getLong(0)));
                    } while (groupIdsCursor.moveToNext());
                }
            }
            if (groupIdsCursor != null) {
                groupIdsCursor.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            String str = this.TAG;
            Log.d(str, "SQLiteException : " + e);
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public Cursor getMediaId(long j2, long j3) {
        MpCreatureFacesTable createFacesTable = createFacesTable();
        createFacesTable.resetProjectionForTitleInfo();
        createFacesTable.filterId(j2);
        createFacesTable.filterRecommendedId(j3);
        Query buildSelectQuery = createFacesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "GET_MEDIA_ID_LIST_FROM_" + getCreatureType() + "_RECOMMENDED_ID");
    }

    public String getMediaIdColumn() {
        return "sec_media_id";
    }

    public Cursor getMediaIdListCursor(long j2) {
        MpCreatureFacesTable createFacesTable = createFacesTable();
        createFacesTable.resetProjectionForMediaId();
        createFacesTable.filterCreatureId(j2);
        Query buildSelectQuery = createFacesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "GET_MEDIA_ID_LIST_FROM_" + getCreatureType() + "_ID");
    }

    public Cursor getMediaIdListCursorByGroupIds(List<Long> list) {
        MpCreatureFacesTable createFacesTable = createFacesTable();
        createFacesTable.resetProjectionForMediaId();
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "(", ")");
        list.forEach(new e(stringJoiner, 11));
        createFacesTable.filterGroupIds(stringJoiner.toString());
        Query buildSelectQuery = createFacesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "GET_MEDIA_ID_LIST_FROM_" + getCreatureType() + "_GROUP_ID(S)");
    }

    public Cursor getMediaIdListCursorByUnifiedId(long j2) {
        MpCreatureFacesTable createFacesTable = createFacesTable();
        createFacesTable.resetProjectionForMediaId();
        createFacesTable.filterUnifiedId(j2);
        Query buildSelectQuery = createFacesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "GET_MEDIA_ID_LIST_FROM_" + getCreatureType() + "_UNIFIED_ID");
    }

    public String getName(long j2) {
        Cursor nameCursor = getNameCursor(j2);
        if (nameCursor != null) {
            try {
                if (nameCursor.moveToLast()) {
                    String string = nameCursor.getString(nameCursor.getColumnIndex("__creatureName"));
                    nameCursor.close();
                    return string;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (nameCursor == null) {
            return "";
        }
        nameCursor.close();
        return "";
        throw th;
    }

    public String getNameByGroupId(long j2) {
        return getName(findCreatureId(j2));
    }

    public Cursor getNameCursor(long j2) {
        MpCreatureTagTable createTagTable = createTagTable();
        createTagTable.filterCreatureId(j2);
        Query buildSelectQuery = createTagTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "GET_" + getCreatureType() + "_NAME");
    }

    public String getSelection(SearchRemoveInfo searchRemoveInfo, String str) {
        if (searchRemoveInfo.isBurstShot()) {
            String burstShotFileIds = getBurstShotFileIds(searchRemoveInfo.getAlbumId(), searchRemoveInfo.getBurstGroupId());
            if (!TextUtils.isEmpty(burstShotFileIds)) {
                return str + " IN " + burstShotFileIds + " AND recommended_id = ?";
            }
        }
        StringBuilder t = C0212a.t(str, " = ");
        t.append(searchRemoveInfo.getFileId());
        t.append(" AND recommended_id = ?");
        return t.toString();
    }

    public String[] getSelectionArgs(SearchRemoveInfo searchRemoveInfo) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(searchRemoveInfo.getIdentityInfo())));
        return (String[]) arrayList.toArray(new String[0]);
    }

    public Cursor getTaggedNameList() {
        MpCreatureView createCreatureView = createCreatureView();
        if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE) {
            createCreatureView.addCreatureFaceScoreProjection();
            createCreatureView.orderByFaceScore();
        } else {
            createCreatureView.orderByASC();
        }
        createCreatureView.filterBurstShotBestImage(false);
        createCreatureView.filterTaggedNameFromUser();
        if (PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
            createCreatureView.filterHidden(0);
        }
        Query query = new Query(createCreatureView.buildSelectQuery());
        query.getQueryBuilder().groupBy("__creatureID");
        return getCursor(query, "GET_TAGGED_" + getCreatureType() + "_NAME_LIST");
    }

    public abstract int handleRemoveTo(SearchRemoveInfo searchRemoveInfo);

    public void hideCreature(List<Long> list) {
        String str;
        ContentValues contentValues = new ContentValues();
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            StringBuilder k = j.k("group_id IN (", getConcatFaceGroupIdsWith(list), ") and group_type=");
            if (isPeople()) {
                str = "1";
            } else {
                str = "2";
            }
            k.append(str);
            String sb2 = k.toString();
            contentValues.put("essential_group", -1);
            updateFacesGroupTable(contentValues, sb2, (String[]) null);
            return;
        }
        String filterRecommendedIds = filterRecommendedIds(list);
        contentValues.put("hide", 1);
        updateFace(contentValues, filterRecommendedIds, (String[]) null);
    }

    public boolean isDefault(long j2) {
        if (j2 == 1) {
            return true;
        }
        return false;
    }

    public int remove(Uri uri, SearchRemoveInfo searchRemoveInfo) {
        String authority = uri.getAuthority();
        try {
            ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
            if (authority == null || contentResolver == null) {
                return 0;
            }
            return contentResolver.applyBatch(authority, getOperationsToRemove(uri, searchRemoveInfo)).length;
        } catch (Exception e) {
            Log.d(this.TAG, "Failed to communicate with remote provider");
            e.printStackTrace();
            return 0;
        }
    }

    public int removeTo(FileItemInterface fileItemInterface, String str) {
        SearchRemoveInfo composeRemoveInfo = composeRemoveInfo(fileItemInterface, str);
        if (composeRemoveInfo == null) {
            return -1;
        }
        return handleRemoveTo(composeRemoveInfo);
    }

    public boolean resetCreatureIdAsGroupId(List<Long> list, boolean z) {
        Uri creatureFaceUri = getCreatureFaceUri(z);
        ArrayList arrayList = new ArrayList();
        for (Long longValue : list) {
            long longValue2 = longValue.longValue();
            ContentProviderOperation.Builder withValue = ContentProviderOperation.newUpdate(creatureFaceUri).withSelection(a.f("group_id = ", longValue2), (String[]) null).withValue(getCreatureIdColumn(), 1L);
            if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
                withValue.withValue("recommended_id", Long.valueOf(longValue2 + 100000));
            }
            arrayList.add(withValue.build());
        }
        if (arrayList.isEmpty()) {
            Log.w(this.TAG, "resetCreatureIdAsGroupId : batch operation is empty");
            return false;
        }
        String authority = creatureFaceUri.getAuthority();
        try {
            ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
            if (authority == null || contentResolver == null || contentResolver.applyBatch(authority, arrayList).length <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.d(this.TAG, "resetCreatureIdAsGroupId failed");
            e.printStackTrace();
        }
        return false;
    }

    public boolean resetCreatureIdValue(List<Long> list) {
        return resetCreatureIdValue(list, 1);
    }

    public void unHideCreature(List<Long> list) {
        String str;
        ContentValues contentValues = new ContentValues();
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            StringBuilder k = j.k("group_id IN (", getConcatFaceGroupIdsWith(list), ") and group_type=");
            if (isPeople()) {
                str = "1";
            } else {
                str = "2";
            }
            k.append(str);
            String sb2 = k.toString();
            contentValues.put("essential_group", 1);
            updateFacesGroupTable(contentValues, sb2, (String[]) null);
            return;
        }
        String filterRecommendedIds = filterRecommendedIds(list);
        contentValues.put("hide", 0);
        updateFace(contentValues, filterRecommendedIds, (String[]) null);
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [java.util.function.LongUnaryOperator, java.lang.Object] */
    public void updateByUnifiedId(long[] jArr, long j2) {
        String asList = asList(jArr);
        String asList2 = asList(Arrays.stream(jArr).filter(new c(1)).map(new Object()).toArray());
        String asList3 = asList(Arrays.stream(jArr).filter(new c(2)).toArray());
        StringBuilder sb2 = new StringBuilder(C0212a.m("(recommended_id in (", asList, ")"));
        if (!asList2.isEmpty()) {
            j.z(sb2, " or group_id in (", asList2, ")");
        }
        if (!asList3.isEmpty()) {
            sb2.append(" or ");
            sb2.append(getCreatureIdColumn());
            sb2.append(" in (");
            sb2.append(asList3);
            sb2.append(")");
        }
        sb2.append(")");
        ContentValues contentValues = new ContentValues();
        contentValues.put(getCreatureIdColumn(), Long.valueOf(j2));
        contentValues.put("recommended_id", Long.valueOf(j2));
        updateFace(contentValues, sb2.toString(), (String[]) null);
    }

    public abstract boolean updateFace(ContentValues contentValues, String str, String[] strArr);

    public void updateTitleInfo(long j2, long j3, int i2) {
        ContentValues contentValues = new ContentValues();
        StringBuilder j8 = j.j(j2, "sec_media_id=", " and recommended_id=");
        j8.append(j3);
        String sb2 = j8.toString();
        contentValues.put("title_info", Integer.valueOf(i2));
        updateFace(contentValues, sb2, (String[]) null);
    }

    public boolean resetCreatureIdValue(List<Long> list, long j2) {
        if (j2 == 1) {
            boolean resetCreatureIdAsGroupId = resetCreatureIdAsGroupId(list, true);
            if (resetCreatureIdAsGroupId && isPeople()) {
                C0212a.x("resetCreatureIdValue : ", this.TAG, resetCreatureIdAsGroupId(list, false));
            }
            return resetCreatureIdAsGroupId;
        }
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "(", ")");
        list.forEach(new e(stringJoiner, 12));
        String str = "group_id in " + stringJoiner;
        ContentValues contentValues = new ContentValues();
        contentValues.put(getCreatureIdColumn(), Long.valueOf(j2));
        contentValues.put("recommended_id", Long.valueOf(j2));
        return updateFace(contentValues, str, (String[]) null);
    }

    public CreatureApi(QueryParams queryParams) {
        super(queryParams);
    }

    public void updateByUnifiedId(long j2, long j3) {
        StringBuilder j8 = j.j(j2, "( recommended_id = ", " or ");
        if (j2 > 100000) {
            j8.append("group_id = ");
            j8.append(j2 - 100000);
        } else {
            j8.append(getCreatureIdColumn());
            j8.append(" = ");
            j8.append(j2);
        }
        j8.append(" )");
        ContentValues contentValues = new ContentValues();
        contentValues.put(getCreatureIdColumn(), Long.valueOf(j3));
        contentValues.put("recommended_id", Long.valueOf(j3));
        updateFace(contentValues, j8.toString(), (String[]) null);
    }
}
