package com.samsung.android.gallery.database.dal.cmh.helper;

import D6.a;
import N2.j;
import T8.C0578a;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.StoryApi;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.executor.CmhQueryExecutor;
import com.samsung.android.gallery.database.dal.cmh.table.CmhStoryColumns;
import com.samsung.android.gallery.database.dal.cmh.table.CmhStoryView;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.CmhUri;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.mobileservice.social.share.provider.SpaceContract;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import n5.e;
import n8.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhStoryApiImpl extends CmhHelperBaseImpl implements StoryApi {
    private static final boolean SUPPORT_DEFAULT_THEME = PreferenceFeatures.isEnabled(PreferenceFeatures.StoryDefaultTheme);

    public CmhStoryApiImpl(QueryParams queryParams) {
        super(queryParams);
    }

    private void appendStoryUpdateByUser(ContentValues contentValues, int i2, String str) {
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            contentValues.put("story_updated_by_user", Integer.valueOf(i2));
            Log.d(this.TAG, C0212a.l("appendStoryUpdateByUser:", str), Integer.valueOf(i2));
        }
    }

    private int getContentsCountCursor(CmhStoryView cmhStoryView) {
        Cursor cursor;
        try {
            cursor = getCursor(cmhStoryView.buildSelectQuery(), "getContentsCount");
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    int i2 = cursor.getInt(cursor.getColumnIndex("__count"));
                    cursor.close();
                    return i2;
                }
            }
            if (cursor == null) {
                return 0;
            }
            cursor.close();
            return 0;
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private long getCoverCmhFileId(long j2) {
        return getCmhFileId(j2);
    }

    private String getDeleteSelection(String str, int i2) {
        StringBuilder t = C0212a.t(str, " IN (");
        t.append(new String(new char[(i2 - 1)]).replaceAll("\u0000", "?,"));
        t.append("?)");
        return t.toString();
    }

    private String getFileIdColumnName() {
        if (this.IS_GTE_Q) {
            return "sec_media_id";
        }
        return "media_id";
    }

    private String getFileIdList(List<Map.Entry<Long, Long>> list) {
        StringBuilder sb2 = new StringBuilder();
        for (Map.Entry<Long, Long> key : list) {
            sb2.append(key.getKey());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        }
        if (sb2.length() > 0) {
            return sb2.substring(0, sb2.length() - 1);
        }
        return "";
    }

    private String getFileIdString(ArrayList<FileItemInterface> arrayList) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        Iterator<FileItemInterface> it = arrayList.iterator();
        while (it.hasNext()) {
            stringJoiner.add("" + it.next().getFileId());
        }
        return stringJoiner.toString();
    }

    private String getRemoveStoryItemSelection(FileItemInterface fileItemInterface) {
        return getFileIdColumnName() + "=" + fileItemInterface.getFileId();
    }

    private String getStringFromArray(Integer[] numArr) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        for (Integer intValue : numArr) {
            stringJoiner.add(String.valueOf(intValue.intValue()));
        }
        return stringJoiner.toString();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer[] lambda$deleteStory$2(int i2) {
        return new Integer[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$deleteStoryFromSaType$1(String[] strArr, int[] iArr, int i2) {
        strArr[i2] = String.valueOf(iArr[i2]);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getStoryIdsFromSaType$5(StringBuilder sb2, int i2) {
        sb2.append("sa_type=");
        sb2.append(i2);
        sb2.append(" or ");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStoryTotalCropInfo$4(ArrayList arrayList, Map.Entry entry) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("total_smartcrop_ratio", ((String[]) entry.getValue())[0]);
        contentValues.put("total_smartcrop_device_ratio", ((String[]) entry.getValue())[1]);
        arrayList.add(ContentProviderOperation.newUpdate(CmhUri.getFiles()).withSelection("media_id = ?", new String[]{Long.toString(((Long) entry.getKey()).longValue())}).withValues(contentValues).build());
    }

    private HashMap<Long, Long> makeIdTimeMap(ArrayList<FileItemInterface> arrayList) {
        HashMap<Long, Long> hashMap = new HashMap<>();
        Iterator<FileItemInterface> it = arrayList.iterator();
        while (it.hasNext()) {
            FileItemInterface next = it.next();
            hashMap.put(Long.valueOf(next.getFileId()), Long.valueOf(next.getDateTaken()));
        }
        return hashMap;
    }

    private int maskStoryUpdatedByUserFlag(int i2) {
        int i7;
        if (SUPPORT_DEFAULT_THEME) {
            i7 = getStoryUpdatedByUser(i2);
        } else {
            i7 = 0;
        }
        return i7 | 1;
    }

    private void searchIndexing(ArrayList<Long> arrayList, String str, IntelligentSearchIndex.IndexMode indexMode) {
        IntelligentSearchIndex.getInstance().indexing((Collection<Long>) arrayList, str, IntelligentSearchIndex.TagType.STORY, indexMode);
    }

    private List<Map.Entry<Long, Long>> sortOperation(HashMap<Long, Long> hashMap) {
        ArrayList arrayList = new ArrayList(hashMap.entrySet());
        arrayList.sort(new a(28));
        return arrayList;
    }

    private boolean supportChangeStoryType() {
        return !this.mParams.SUPPORT_STORIES_DATA_SEP11;
    }

    private boolean updateStory(int i2, ContentValues contentValues) {
        if (i2 <= 0 || contentValues.size() <= 0 || update(contentValues, C0086a.i(i2, "story_id="), (String[]) null) <= 0) {
            return false;
        }
        return true;
    }

    private void updateStoryUpdatedByUser(int i2, int i7, String str) {
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("story_updated_by_user", Integer.valueOf(i7));
                if (!updateStory(i2, contentValues)) {
                    Log.e((CharSequence) this.TAG, "fail to update", Integer.valueOf(i2));
                }
                String str2 = this.TAG;
                Log.d(str2, "updateStoryUpdatedByUser:" + str, Integer.valueOf(i7));
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "fail to update", Integer.valueOf(i2), e.getMessage());
            }
        }
    }

    private void updateTimeInfo(List<Map.Entry<Long, Long>> list, long[] jArr) {
        if (list == null) {
            Log.d(this.TAG, "updateTimeInfo(): idTimeArray is null");
        } else if (list.size() == 0) {
            Log.d(this.TAG, "updateTimeInfo(): idTimeArray size is 0");
        } else {
            if (((Long) list.get(0).getValue()).longValue() < jArr[0]) {
                jArr[0] = ((Long) list.get(0).getValue()).longValue();
            }
            if (((Long) ((Map.Entry) C0086a.f(1, list)).getValue()).longValue() > jArr[1]) {
                jArr[1] = ((Long) ((Map.Entry) C0086a.f(1, list)).getValue()).longValue();
            }
        }
    }

    public Object[] addContentsToStory(FileItemInterface fileItemInterface, ArrayList<FileItemInterface> arrayList, int i2, Supplier<long[]> supplier, long j2) {
        boolean z = true;
        int albumID = fileItemInterface.getAlbumID();
        String title = fileItemInterface.getTitle();
        long[] jArr = supplier.get();
        int count = fileItemInterface.getCount();
        ContentValues contentValues = new ContentValues();
        HashMap<Long, Long> makeIdTimeMap = makeIdTimeMap(arrayList);
        List<Map.Entry<Long, Long>> sortOperation = sortOperation(makeIdTimeMap);
        String fileIdList = getFileIdList(sortOperation);
        updateTimeInfo(sortOperation, jArr);
        if (supportChangeStoryType()) {
            contentValues.put("type", 1);
        }
        contentValues.put("story_id", Integer.valueOf(albumID));
        contentValues.put(CmhStoryColumns.getStoryContentId(), fileIdList);
        contentValues.put("is_recommended", 1);
        contentValues.put("title", title);
        contentValues.put("start_time", Long.valueOf(jArr[0]));
        contentValues.put("end_time", Long.valueOf(jArr[1]));
        Boolean bool = Boolean.FALSE;
        contentValues.put("owner", bool);
        contentValues.put("is_manual", 1);
        contentValues.put(SpaceContract.Space.MEDIA_COUNT, Integer.valueOf(i2 + count));
        contentValues.put("cover", Long.valueOf(j2));
        contentValues.put("is_visible", Boolean.TRUE);
        contentValues.put("is_suggestion", bool);
        appendStoryUpdateByUser(contentValues, maskStoryUpdatedByUserFlag(albumID), "addContentsToStory");
        searchIndexing((ArrayList<Long>) new ArrayList(makeIdTimeMap.keySet()), title, IntelligentSearchIndex.IndexMode.APPEND);
        if (update(contentValues, C0086a.i(albumID, "story_id="), (String[]) null) <= 0) {
            z = false;
        }
        return new Object[]{Boolean.valueOf(z), fileIdList};
    }

    public boolean addHideDateRule(long j2, long j3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("data_provider", 0);
        contentValues.put("rule_type", 0);
        contentValues.put("start_time", Long.valueOf(j2));
        contentValues.put("end_time", Long.valueOf(j3));
        Log.d(this.TAG, "addHideDateRule", Long.valueOf(j2), Long.valueOf(j3));
        if (getContentResolver().insert(CmhHelperBaseImpl.CMH_STORY_HIDE_RULE, contentValues) != null) {
            return true;
        }
        return false;
    }

    public boolean addHideSceneRule(FileItemInterface[] fileItemInterfaceArr, String[] strArr) {
        boolean z;
        boolean z3;
        Integer num;
        FileItemInterface[] fileItemInterfaceArr2 = fileItemInterfaceArr;
        boolean z7 = true;
        ArrayList arrayList = new ArrayList();
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        int length = fileItemInterfaceArr2.length;
        boolean z9 = false;
        int i2 = 0;
        int i7 = 0;
        while (i7 < length) {
            String subCategory = fileItemInterfaceArr2[i7].getSubCategory();
            if (IdentityCreatureUtil.isIdentityKey(subCategory)) {
                long identityId = IdentityCreatureUtil.getIdentityId(subCategory);
                if (identityId == -1) {
                    z = z7;
                } else {
                    z = z7;
                    if (IdentityCreatureUtil.isAssignedCreature(subCategory)) {
                        long j2 = identityId;
                        long createUnifiedId = IdentityCreatureUtil.createUnifiedId(0, j2, -1);
                        long j3 = j2;
                        ContentProviderOperation.Builder withValue = ContentProviderOperation.newInsert(CmhHelperBaseImpl.CMH_STORY_HIDE_RULE).withValue("data_provider", i2).withValue("rule_type", 1).withValue("person_id", Long.valueOf(j3));
                        if (this.IS_GTE_T) {
                            withValue.withValue("recommended_id", Long.valueOf(createUnifiedId));
                        }
                        arrayList.add(withValue.build());
                        stringJoiner.add("A/" + createUnifiedId + "/" + j3);
                    } else {
                        z3 = z9;
                        long createUnifiedId2 = IdentityCreatureUtil.createUnifiedId(identityId, 1, -1);
                        num = i2;
                        ContentProviderOperation.Builder withValue2 = ContentProviderOperation.newInsert(CmhHelperBaseImpl.CMH_STORY_HIDE_RULE).withValue("data_provider", num).withValue("rule_type", 1).withValue(BundleKey.GROUP_ID, Long.valueOf(identityId));
                        if (this.IS_GTE_T) {
                            withValue2.withValue("recommended_id", Long.valueOf(createUnifiedId2));
                        }
                        arrayList.add(withValue2.build());
                        stringJoiner.add("a/" + createUnifiedId2 + "/" + identityId);
                    }
                }
                z3 = z9;
                num = i2;
            } else {
                z = z7;
                z3 = z9;
                num = i2;
                if (!TextUtils.isEmpty(subCategory)) {
                    arrayList.add(ContentProviderOperation.newInsert(CmhHelperBaseImpl.CMH_STORY_HIDE_RULE).withValue("data_provider", num).withValue("rule_type", 2).withValue("private_data", subCategory).build());
                    stringJoiner.add("P/" + subCategory);
                }
            }
            i7++;
            z7 = z;
            i2 = num;
            z9 = z3;
            fileItemInterfaceArr2 = fileItemInterfaceArr;
        }
        boolean z10 = z7;
        boolean z11 = z9;
        Log.d(this.TAG, "addHideSceneRule", Integer.valueOf(arrayList.size()), stringJoiner.toString());
        if (applyBatch(arrayList) != null) {
            return z10;
        }
        return z11;
    }

    public boolean changeStoryCover(int i2, long j2, String str) {
        if (this.mParams.getOsVersion() < 30) {
            j2 = getCoverCmhFileId(j2);
        }
        if (j2 == 0) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("cover", Long.valueOf(j2));
        contentValues.put("coverRectRatio", str);
        appendStoryUpdateByUser(contentValues, maskStoryUpdatedByUserFlag(i2), "changeStoryCover");
        if (this.mParams.SUPPORT_MEMORY_DATA) {
            contentValues.putNull("cover_path");
        }
        return updateStory(i2, contentValues);
    }

    public int createStory(ArrayList<FileItemInterface> arrayList, String str, int i2, StoryType storyType, String str2, int i7) {
        int i8 = 1;
        if (!(arrayList == null || arrayList.size() == 0)) {
            HashMap<Long, Long> makeIdTimeMap = makeIdTimeMap(arrayList);
            List<Map.Entry<Long, Long>> sortOperation = sortOperation(makeIdTimeMap);
            if (sortOperation.size() != i7 || sortOperation.size() == 0) {
                Log.d(this.TAG, "item size is wrong size=" + sortOperation.size());
            } else {
                String fileIdList = getFileIdList(sortOperation);
                ContentValues contentValues = new ContentValues();
                contentValues.put(CmhStoryColumns.getStoryContentId(), fileIdList);
                StoryType storyType2 = StoryType.MANUAL;
                if (storyType == storyType2) {
                    contentValues.put("is_recommended", 1);
                }
                contentValues.put("title", str);
                contentValues.put("type", Integer.valueOf(storyType.getValue()));
                contentValues.put("start_time", (Long) sortOperation.get(0).getValue());
                contentValues.put("end_time", (Long) sortOperation.get(i7 - 1).getValue());
                contentValues.put("creation_time", Long.valueOf(System.currentTimeMillis()));
                contentValues.put("owner", Integer.valueOf(i2));
                if (storyType != storyType2) {
                    i8 = 0;
                }
                contentValues.put("is_manual", Integer.valueOf(i8));
                contentValues.put("ugci", str2);
                contentValues.put(SpaceContract.Space.MEDIA_COUNT, Integer.valueOf(i7));
                contentValues.put("cover", String.valueOf(0));
                contentValues.put("is_visible", Boolean.TRUE);
                contentValues.put("is_suggestion", Boolean.FALSE);
                contentValues.put("notify_status", 1);
                Uri insert = insert(contentValues);
                if (insert == null) {
                    Log.w(this.TAG, "createStory() : insertUri is null !!");
                    return -1;
                }
                searchIndexing((ArrayList<Long>) new ArrayList(makeIdTimeMap.keySet()), str, IntelligentSearchIndex.IndexMode.APPEND);
                return Integer.parseInt(insert.getLastPathSegment());
            }
        }
        return -1;
    }

    public boolean deleteStory(Integer[] numArr, boolean z) {
        String m = C0212a.m("story_id IN (", getStringFromArray(numArr), ")");
        if (z) {
            for (Integer intValue : numArr) {
                int intValue2 = intValue.intValue();
                searchIndexing(getStoryName(intValue2), intValue2, IntelligentSearchIndex.IndexMode.REMOVE);
            }
        }
        if (delete(m, (String[]) null) >= 0) {
            return true;
        }
        return false;
    }

    public boolean deleteStoryFromSaType(int[] iArr) {
        String[] strArr = new String[iArr.length];
        IntStream.range(0, iArr.length).forEach(new n8.a(strArr, iArr, 0));
        if (delete(getDeleteSelection("sa_type", iArr.length), strArr) >= 0) {
            return true;
        }
        return false;
    }

    public long getCmhFileId(long j2) {
        Cursor cursor;
        QueryBuilder g = C0212a.g("files");
        g.andCondition(getFileIdColumnName() + "=" + j2);
        g.addProjection("_id");
        try {
            cursor = getCursor(new Query(g), "getCmhFileId");
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    long j3 = cursor.getLong(0);
                    cursor.close();
                    return j3;
                }
            }
            if (cursor == null) {
                return 0;
            }
            cursor.close();
            return 0;
        } catch (Exception e) {
            Log.e("", e.toString());
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public Cursor getCollageCursor(int i2) {
        QueryBuilder g = C0212a.g("story");
        g.andCondition("story_id=" + i2);
        g.addProjection("collage_type");
        g.addProjection("collage_sec_media_ids");
        return getCursor(new Query(g), "getCollageCursor");
    }

    public int getContentsCount(int i2) {
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.addProjectionForCursorCount(false);
        cmhStoryView.addStoryFileData();
        cmhStoryView.filterStoryID(i2);
        return getContentsCountCursor(cmhStoryView);
    }

    public long getFileId(long j2) {
        Cursor cursor;
        QueryBuilder g = C0212a.g("files");
        g.andCondition("_id=" + j2);
        g.addProjection(getFileIdColumnName());
        try {
            cursor = getCursor(new Query(g), "getFileId");
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    long j3 = cursor.getLong(0);
                    cursor.close();
                    return j3;
                }
            }
            if (cursor == null) {
                return 0;
            }
            cursor.close();
            return 0;
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public List<Integer> getHideRuleIds(List<Integer> list) {
        return Collections.EMPTY_LIST;
    }

    public List<String> getRecapFilePath(Integer[] numArr) {
        return Collections.EMPTY_LIST;
    }

    public Long[] getStoryAlbumContentsIds(int i2) {
        ArrayList arrayList = new ArrayList();
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.addStoryFileData();
        cmhStoryView.filterStoryID(i2);
        Cursor cursor = getCursor(cmhStoryView.buildSelectQuery(), "getStoryAlbumContentsIds");
        if (cursor != null) {
            try {
                int columnIndex = cursor.getColumnIndex("__absID");
                while (cursor.moveToNext()) {
                    arrayList.add(Long.valueOf(cursor.getLong(columnIndex)));
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return (Long[]) arrayList.toArray(new Long[0]);
        throw th;
    }

    public long getStoryCoverCmhFileId(int i2) {
        Cursor cursor;
        QueryBuilder g = C0212a.g("story");
        g.andCondition("story_id=" + i2);
        g.addProjection("cover");
        try {
            cursor = getCursor(new Query(g), "getCoverCmhFileId");
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    long j2 = cursor.getLong(0);
                    cursor.close();
                    return j2;
                }
            }
            if (cursor == null) {
                return 0;
            }
            cursor.close();
            return 0;
        } catch (Exception e) {
            Log.e("", e.toString());
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public int getStoryIdByUgci(String str) {
        Cursor cursor;
        QueryBuilder g = C0212a.g("story");
        g.andCondition("cloud_sync_ugci='" + str + "'");
        g.addProjection("story_id");
        try {
            cursor = getCursor(new Query(g), "getStoryIdByUgci");
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    int i2 = cursor.getInt(0);
                    cursor.close();
                    return i2;
                }
            }
            if (cursor == null) {
                return -1;
            }
            cursor.close();
            return -1;
        } catch (Exception e) {
            Log.e("", e.toString());
            return -1;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public List<Integer> getStoryIdsFromSaType(int[] iArr) {
        Cursor cursor;
        QueryBuilder g = C0212a.g("story");
        StringBuilder sb2 = new StringBuilder();
        if (iArr != null) {
            Arrays.stream(iArr).forEach(new b(sb2, 0));
        }
        sb2.setLength(sb2.length() - 4);
        g.andCondition("(" + sb2 + ")");
        g.addProjection("story_id");
        ArrayList arrayList = new ArrayList();
        try {
            cursor = getCursor(new Query(g), "getStoryIdsFromType");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        arrayList.add(Integer.valueOf(cursor.getInt(0)));
                    } while (cursor.moveToNext());
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (Exception e) {
            Log.e("", e.toString());
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String getStoryName(int i2) {
        Cursor cursor;
        QueryBuilder g = C0212a.g("story");
        g.andCondition("story_id=" + i2);
        g.addProjection("title");
        String str = "";
        try {
            cursor = getCursor(new Query(g), "getStoryName");
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    String str2 = this.TAG;
                    Log.i(str2, "Stories : count = " + cursor.getCount());
                    str = cursor.getString(0);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return str;
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
            return str;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public long getStoryPeopleHeaderFileId(int i2) {
        Cursor cursor;
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.modifyForStoryPeopleHeader();
        cmhStoryView.addStoryVisibilityCondition(true);
        cmhStoryView.filterStoryID(i2);
        cmhStoryView.groupByStoryAlbum();
        try {
            cursor = getCursor(cmhStoryView.buildSelectQuery(), "getStoryPeopleFileId");
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    long j2 = cursor.getLong(cursor.getColumnIndex("__face_file_id"));
                    cursor.close();
                    return j2;
                }
            }
            if (cursor == null) {
                return 0;
            }
            cursor.close();
            return 0;
        } catch (Exception e) {
            Log.e("", e.toString());
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public Map<Long, String[]> getStoryTotalCropInfo(String str) {
        Cursor cursor;
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            QueryBuilder g = C0212a.g("files");
            C0212a.w("media_id in (", str, ")", g);
            g.addProjection(new String[]{"media_id", "total_smartcrop_ratio", "total_smartcrop_device_ratio"});
            try {
                cursor = getCursor(new Query(g), "getStoryTotalCropInfo");
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            String string = cursor.getString(1);
                            String string2 = cursor.getString(2);
                            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                                hashMap.put(Long.valueOf(cursor.getLong(0)), new String[]{string, string2});
                            }
                        } while (cursor.moveToNext());
                    }
                }
                if (cursor != null) {
                    cursor.close();
                    return hashMap;
                }
            } catch (Exception e) {
                Log.e(this.TAG, e.toString());
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return hashMap;
        throw th;
    }

    public int getStoryUpdatedByUser(int i2) {
        Cursor cursor;
        QueryBuilder g = C0212a.g("story");
        g.andCondition("story_id=" + i2);
        g.addProjection("story_updated_by_user");
        try {
            cursor = getCursor(new Query(g), "getStoryUpdatedByUser");
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    int i7 = cursor.getInt(0);
                    cursor.close();
                    return i7;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return 0;
        throw th;
    }

    public boolean isEnableFeatureState(String str) {
        Cursor rawQuery = new CmhQueryExecutor().rawQuery("select value from feature where key='" + str + "'", "isEnableFeatureState");
        boolean z = false;
        if (rawQuery != null) {
            try {
                if (rawQuery.moveToFirst()) {
                    if (Integer.parseInt(rawQuery.getString(0)) == 1) {
                        z = true;
                    }
                    rawQuery.close();
                    return z;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        return false;
        throw th;
    }

    public boolean removeHideRule(int i2) {
        Log.d(this.TAG, "removeHideRule", Integer.valueOf(i2));
        StringBuilder sb2 = new StringBuilder("ruleId=");
        sb2.append(i2);
        return getContentResolver().delete(CmhHelperBaseImpl.CMH_STORY_HIDE_RULE, sb2.toString(), (String[]) null) > 0;
    }

    public boolean removeItemsFromStory(ArrayList<FileItemInterface> arrayList) {
        int size = arrayList.size();
        int albumID = arrayList.get(0).getAlbumID();
        ArrayList arrayList2 = new ArrayList();
        Iterator<FileItemInterface> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(Long.valueOf(it.next().getFileId()));
        }
        StringBuilder sb2 = new StringBuilder();
        for (int i2 = 0; i2 < size; i2++) {
            sb2.append(getRemoveStoryItemSelection(arrayList.get(i2)));
            if (albumID != -1) {
                sb2.append(" AND story_id=");
                sb2.append(albumID);
            }
            if (i2 < size - 1) {
                sb2.append(" OR ");
            }
        }
        String storyName = getStoryName(albumID);
        int delete = delete(sb2.toString(), (String[]) null);
        if (delete >= 0) {
            updateStoryUpdatedByUser(albumID, maskStoryUpdatedByUserFlag(albumID), "removeItemsFromStory");
            searchIndexing((ArrayList<Long>) arrayList2, storyName, IntelligentSearchIndex.IndexMode.REMOVE);
        }
        if (delete >= 0) {
            return true;
        }
        return false;
    }

    public boolean renameStory(String str, String str2, int i2) {
        ContentValues c5 = C0086a.c("title", str);
        appendStoryUpdateByUser(c5, maskStoryUpdatedByUserFlag(i2), "renameStory");
        boolean updateStory = updateStory(i2, c5);
        if (updateStory) {
            searchIndexing(str2, i2, IntelligentSearchIndex.IndexMode.REMOVE);
            searchIndexing(str, i2, IntelligentSearchIndex.IndexMode.APPEND);
        }
        return updateStory;
    }

    public void resetStoryCover(int i2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("cover", String.valueOf(0));
            contentValues.put("coverRectRatio", "0,0,0,0");
            updateStory(i2, contentValues);
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
        }
    }

    public String tag() {
        return "CmhStoryApiImpl";
    }

    public boolean updateCollageInfo(int i2, int i7, ArrayList<Long> arrayList) {
        if (i2 < 1 || arrayList == null || arrayList.isEmpty()) {
            Log.w((CharSequence) this.TAG, "updateCollageInfo fail", Integer.valueOf(i2), arrayList);
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("collage_type", Integer.valueOf(i7));
        contentValues.put("collage_sec_media_ids", StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList));
        return updateStory(i2, contentValues);
    }

    public boolean updateContentOrder(int i2, List<FileItemInterface> list) {
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        for (int i7 = 0; i7 < list.size(); i7++) {
            arrayList.add(ContentProviderOperation.newUpdate(CmhHelperBaseImpl.CMH_STORY_TABLE_URI).withSelection("fk_story_id=? AND fk_file_id=?", new String[]{Integer.toString(i2), Long.toString(getCmhFileId(list.get(i7).getFileId()))}).withValue("story_display_order", Long.valueOf(currentTimeMillis - ((long) i7))).build());
        }
        if (arrayList.isEmpty() || applyBatch(arrayList) == null) {
            return false;
        }
        return true;
    }

    public boolean updateFeatureState(String str, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", String.valueOf(i2));
        if (getContentResolver().update(CmhUri.getFeature(), contentValues, "key = ?", new String[]{str}) > 0) {
            return true;
        }
        return false;
    }

    public boolean updateHideRuleOption(List<Integer> list, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("individual", Integer.valueOf(i2));
        if (getContentResolver().update(CmhHelperBaseImpl.CMH_STORY_HIDE_RULE, contentValues, (String) null, (String[]) null) > 0) {
            return true;
        }
        return false;
    }

    public void updateMergedCreaturesToHideRule(String str, String str2, String[] strArr, String str3) {
        Log.d(this.TAG, "[updateMergedCreaturesToHideRule] not support by cmh");
    }

    public boolean updateStoryBgmInfo(int i2, String str, int i7) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("story_bgm_info", str);
            appendStoryUpdateByUser(contentValues, i7, "updateStoryBgmInfo");
            String str2 = this.TAG;
            Log.d(str2, "updateStoryBgmInfo", Logger.getEncodedString("[" + i2 + "] [" + str + "] [" + i7 + "]"));
            return updateStory(i2, contentValues);
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
            return false;
        }
    }

    public ContentProviderResult[] updateStoryFavoriteInfo(FileItemInterface[] fileItemInterfaceArr, Map<Integer, Integer> map, boolean z) {
        long j2;
        ArrayList arrayList = new ArrayList();
        for (FileItemInterface fileItemInterface : fileItemInterfaceArr) {
            if (fileItemInterface != null) {
                if (z) {
                    j2 = System.currentTimeMillis();
                } else {
                    j2 = -1;
                }
                ContentProviderOperation.Builder withValue = ContentProviderOperation.newUpdate(CmhHelperBaseImpl.CMH_STORY_TABLE_URI).withSelection("story_id=?", new String[]{Integer.toString(fileItemInterface.getAlbumID())}).withValue("story_favorite", Long.valueOf(j2));
                Integer num = map.get(Integer.valueOf(fileItemInterface.getAlbumID()));
                if (num != null && num.intValue() > 0) {
                    withValue.withValue("type", num);
                }
                arrayList.add(withValue.build());
            }
        }
        if (!arrayList.isEmpty()) {
            return applyBatch(arrayList);
        }
        return new ContentProviderResult[0];
    }

    public boolean updateStoryFilter(int i2, String str, int i7) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("story_filter", str);
            appendStoryUpdateByUser(contentValues, i7, "updateStoryBgmInfo");
            String str2 = this.TAG;
            Log.d(str2, "updateStoryFilter", Logger.getEncodedString("[" + i2 + "] [" + str + "]"));
            return updateStory(i2, contentValues);
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
            return false;
        }
    }

    public <T extends FileItemInterface> ArrayList<T> updateStoryPin(T[] tArr, List<Long> list) {
        Integer num;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < tArr.length; i2++) {
            Long l = list.get(i2);
            l.longValue();
            arrayList.add(ContentProviderOperation.newUpdate(CmhHelperBaseImpl.CMH_STORY_TABLE_URI).withSelection("story_id=?", new String[]{Integer.toString(tArr[i2].getAlbumID())}).withValue("story_favorite", l).build());
        }
        ContentProviderResult[] applyBatch = applyBatch(arrayList);
        ArrayList<T> arrayList2 = new ArrayList<>();
        if (applyBatch != null) {
            for (int i7 = 0; i7 < applyBatch.length; i7++) {
                ContentProviderResult contentProviderResult = applyBatch[i7];
                if (!(contentProviderResult == null || (num = contentProviderResult.count) == null || num.intValue() <= 0)) {
                    arrayList2.add(tArr[i7]);
                }
            }
        }
        return arrayList2;
    }

    public boolean updateStoryThemeAndBgm(int i2, String str, String str2, String str3) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("story_theme", str);
            contentValues.put("story_filter", str2);
            contentValues.put("story_bgm_info", str3);
            appendStoryUpdateByUser(contentValues, 0, "updateStoryThemeAndBgm");
            return updateStory(i2, contentValues);
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
            return false;
        }
    }

    public boolean updateStoryThemeInfo(int i2, HashMap<String, String> hashMap, int i7) {
        return updateStoryThemeInfo(i2, hashMap.get("story_theme"), hashMap.get("story_filter"), hashMap.get("story_bgm_info"), i7);
    }

    public void updateStoryTime(int i2) {
        Cursor cursor;
        CmhStoryView cmhStoryView = new CmhStoryView(this.mParams);
        cmhStoryView.addStoryFileData();
        cmhStoryView.filterStoryID(i2);
        cmhStoryView.clearOrderBy();
        cmhStoryView.orderByDateTakenDesc();
        try {
            cursor = getCursor(cmhStoryView.buildSelectQuery(), "updateStoryTime");
            ContentValues contentValues = new ContentValues();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    contentValues.put("end_time", Long.valueOf(cursor.getLong(cursor.getColumnIndex("__dateTaken"))));
                }
            }
            if (cursor != null && cursor.moveToLast()) {
                contentValues.put("start_time", Long.valueOf(cursor.getLong(cursor.getColumnIndex("__dateTaken"))));
            }
            updateStory(i2, contentValues);
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void updateStoryTotalCropInfo(Map<Long, String[]> map) {
        ArrayList arrayList = new ArrayList();
        map.entrySet().forEach(new f4.a(arrayList, 16));
        try {
            ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
            if (contentResolver != null) {
                contentResolver.applyBatch(CmhHelperBaseImpl.CMH_AUTHORITY_URI.getAuthority(), arrayList);
            }
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
        }
    }

    public boolean updateStoryType(Integer[] numArr, int i2) {
        try {
            ArrayList arrayList = new ArrayList();
            for (Integer intValue : numArr) {
                arrayList.add(ContentProviderOperation.newUpdate(CmhHelperBaseImpl.CMH_STORY_TABLE_URI).withSelection("story_id=?", new String[]{String.valueOf(intValue.intValue())}).withValue("type", Integer.valueOf(i2)).build());
            }
            if (i2 == StoryType.DELETED_STORY.getValue()) {
                for (Integer intValue2 : numArr) {
                    int intValue3 = intValue2.intValue();
                    searchIndexing(getStoryName(intValue3), intValue3, IntelligentSearchIndex.IndexMode.REMOVE);
                }
            }
            if (applyBatch(arrayList) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("updateStoryType failed "), this.TAG);
            return false;
        }
    }

    public void updateUnmergedCreatureToHideRule(String str, String[] strArr, String[] strArr2) {
        Log.d(this.TAG, "[updateUnmergedCreatureToHideRule] not support by cmh");
    }

    public boolean updateUserCuration(int i2, ArrayList<FileItemInterface> arrayList, int i7) {
        boolean z = false;
        if (arrayList == null || arrayList.isEmpty()) {
            Log.w(this.TAG, "no updateUserCuration items");
            return false;
        }
        String fileIdString = getFileIdString(arrayList);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_curation", Integer.valueOf(i7));
        StringBuilder sb2 = new StringBuilder("story_id = ");
        sb2.append(i2);
        if (update(contentValues, j.f(sb2, " AND sec_media_id in (", fileIdString, ")"), (String[]) null) > 0) {
            z = true;
        }
        if (z && i7 != 2) {
            updateStoryUpdatedByUser(i2, maskStoryUpdatedByUserFlag(i2), "updateUserCuration");
        }
        return z;
    }

    public void updateUserEnter(int i2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("user_enter", String.valueOf(2));
            updateStory(i2, contentValues);
            Log.d(this.TAG, "updateUserEnter", Integer.valueOf(i2));
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
        }
    }

    private void searchIndexing(String str, int i2, IntelligentSearchIndex.IndexMode indexMode) {
        if (Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH)) {
            IntelligentSearchIndex.getInstance().indexing((Collection<Long>) new ArrayList(Arrays.asList(getStoryAlbumContentsIds(i2))), str, IntelligentSearchIndex.TagType.STORY, indexMode);
        }
    }

    public boolean removeHideRule(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int valueOf : iArr) {
            arrayList.add(ContentProviderOperation.newDelete(CmhHelperBaseImpl.CMH_STORY_HIDE_RULE).withSelection("ruleId=?", new String[]{String.valueOf(valueOf)}).build());
        }
        Log.d(this.TAG, "removeHideRule id", Arrays.stream(iArr).boxed().collect(Collectors.toList()));
        if (applyBatch(arrayList) != null) {
            return true;
        }
        return false;
    }

    public boolean updateStoryThemeInfo(int i2, String str, String str2, String str3, int i7) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("story_theme", str);
            contentValues.put("story_filter", str2);
            contentValues.put("story_bgm_info", str3);
            appendStoryUpdateByUser(contentValues, i7, "updateStoryThemeInfo");
            String str4 = this.TAG;
            Log.d(str4, "updateStoryThemeInfo", Logger.getEncodedString("[" + i2 + "] [" + str + "] [" + str2 + "] [" + str3 + "] [" + i7 + "]"));
            return updateStory(i2, contentValues);
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
            return false;
        }
    }

    public boolean deleteStory(FileItemInterface[] fileItemInterfaceArr, boolean z) {
        return deleteStory((Integer[]) Arrays.stream(fileItemInterfaceArr).map(new e(4)).toArray(new C0578a(23)), z);
    }
}
