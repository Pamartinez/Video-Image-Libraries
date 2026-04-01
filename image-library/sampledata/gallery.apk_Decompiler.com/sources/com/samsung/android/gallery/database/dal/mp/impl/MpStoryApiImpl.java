package com.samsung.android.gallery.database.dal.mp.impl;

import A4.P;
import Ad.C0720a;
import Ka.c;
import N2.j;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.StoryApi;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.table.MpStoryColumns;
import com.samsung.android.gallery.database.dal.mp.table.MpStoryView;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.provider.SpaceContract;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import n5.e;
import q8.a;
import t4.C0517a;
import t8.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpStoryApiImpl extends StoryHelperBaseImpl implements StoryApi {
    private static final boolean SUPPORT_DEFAULT_THEME = PreferenceFeatures.isEnabled(PreferenceFeatures.StoryDefaultTheme);

    public MpStoryApiImpl(QueryParams queryParams) {
        super(queryParams);
    }

    private void appendStoryUpdateByUser(ContentValues contentValues, int i2, String str) {
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            contentValues.put("story_updated_by_user", Integer.valueOf(i2));
            Log.d(this.TAG, C0212a.l("appendStoryUpdateByUser:", str), Integer.valueOf(i2));
        }
    }

    private int getContentsCountCursor(MpStoryView mpStoryView) {
        Cursor cursor;
        try {
            cursor = getCursor(mpStoryView.buildSelectQuery(), "getContentsCount");
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

    private String getStringFromArray(Integer[] numArr) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        for (Integer intValue : numArr) {
            stringJoiner.add(String.valueOf(intValue.intValue()));
        }
        return stringJoiner.toString();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$addHideSceneRule$3(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer[] lambda$deleteStory$0(int i2) {
        return new Integer[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$deleteStoryFromSaType$2(String[] strArr, int[] iArr, int i2) {
        strArr[i2] = String.valueOf(iArr[i2]);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getRecapFilePath$7(StringBuilder sb2, Integer num) {
        sb2.append("_id=");
        sb2.append(num);
        sb2.append(" or ");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getStoryIdsFromSaType$6(StringBuilder sb2, int i2) {
        sb2.append("sa_type=");
        sb2.append(i2);
        sb2.append(" or ");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStoryTotalCropInfo$5(ArrayList arrayList, Long l, String[] strArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("total_smartcrop_ratio", strArr[0]);
        contentValues.put("total_smartcrop_device_ratio", strArr[1]);
        arrayList.add(ContentProviderOperation.newUpdate(MpAnalyzeInfoUtil.URI).withSelection("sec_media_id = ?", new String[]{Long.toString(l.longValue())}).withValues(contentValues).build());
    }

    private HashMap<Long, Long> makeIdTimeMap(ArrayList<FileItemInterface> arrayList) {
        long j2;
        HashMap<Long, Long> hashMap = new HashMap<>();
        Iterator<FileItemInterface> it = arrayList.iterator();
        while (it.hasNext()) {
            FileItemInterface next = it.next();
            Long valueOf = Long.valueOf(next.getFileId());
            if (next.getDateLocal() > 0) {
                j2 = next.getDateLocal();
            } else {
                j2 = next.getDateTaken();
            }
            hashMap.put(valueOf, Long.valueOf(j2));
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
        arrayList.sort(new b(0));
        return arrayList;
    }

    private boolean supportChangeStoryType() {
        return !this.mParams.SUPPORT_STORIES_DATA_SEP11;
    }

    private boolean updateStory(int i2, ContentValues contentValues) {
        if (i2 > 0 && !contentValues.isEmpty()) {
            contentValues.put("modification_time", Long.valueOf(System.currentTimeMillis()));
            if (update(contentValues, MpStoryColumns.getEditableStoryId() + "=" + i2, (String[]) null) > 0) {
                return true;
            }
        }
        return false;
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
        boolean z;
        int albumID = fileItemInterface.getAlbumID();
        String title = fileItemInterface.getTitle();
        long[] jArr = supplier.get();
        int count = fileItemInterface.getCount();
        HashMap<Long, Long> makeIdTimeMap = makeIdTimeMap(arrayList);
        List<Map.Entry<Long, Long>> sortOperation = sortOperation(makeIdTimeMap);
        String fileIdList = getFileIdList(sortOperation);
        ContentValues contentValues = new ContentValues();
        contentValues.put(MpStoryColumns.getStoryContentId(), fileIdList);
        contentValues.put(MpStoryColumns.getEditableStoryId(), Integer.valueOf(albumID));
        if (insert(contentValues) != null) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return new Object[]{Boolean.FALSE, fileIdList};
        }
        ContentValues contentValues2 = new ContentValues();
        updateTimeInfo(sortOperation, jArr);
        if (supportChangeStoryType()) {
            contentValues2.put("story_type", 1);
        }
        contentValues2.put("title", title);
        contentValues2.put("start_time", Long.valueOf(jArr[0]));
        contentValues2.put("end_time", Long.valueOf(jArr[1]));
        contentValues2.put("is_manual", 1);
        contentValues2.put(SpaceContract.Space.MEDIA_COUNT, Integer.valueOf(i2 + count));
        contentValues2.put("cover_id", Long.valueOf(j2));
        contentValues2.put("is_visible", Boolean.TRUE);
        appendStoryUpdateByUser(contentValues2, maskStoryUpdatedByUserFlag(albumID), "addContentsToStory");
        searchIndexing((ArrayList<Long>) new ArrayList(makeIdTimeMap.keySet()), title, IntelligentSearchIndex.IndexMode.APPEND);
        updateStory(albumID, contentValues2);
        return new Object[]{Boolean.valueOf(z), fileIdList};
    }

    public boolean addHideDateRule(long j2, long j3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("data_provider", 0);
        contentValues.put("rule_type", 0);
        contentValues.put("start_time", Long.valueOf(j2));
        contentValues.put("end_time", Long.valueOf(j3));
        Log.d(this.TAG, "addHideDateRule", Long.valueOf(j2), Long.valueOf(j3));
        if (insertToHideRule(contentValues) != null) {
            return true;
        }
        return false;
    }

    public boolean addHideSceneRule(FileItemInterface[] fileItemInterfaceArr, String[] strArr) {
        return addHideSceneRule((String[]) Arrays.stream(fileItemInterfaceArr).map(new a(8)).toArray(new C0517a(4)), strArr);
    }

    public boolean changeStoryCover(int i2, long j2, String str) {
        if (this.mParams.getOsVersion() < 30) {
            j2 = getCoverCmhFileId(j2);
        }
        if (j2 == 0) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("cover_id", Long.valueOf(j2));
        contentValues.put("cover_rect_ratio", str);
        appendStoryUpdateByUser(contentValues, maskStoryUpdatedByUserFlag(i2), "changeStoryCover");
        if (this.mParams.SUPPORT_MEMORY_DATA) {
            contentValues.putNull("cover_path");
        }
        return updateStory(i2, contentValues);
    }

    public int createStory(ArrayList<FileItemInterface> arrayList, String str, int i2, StoryType storyType, String str2, int i7) {
        int i8;
        if (!(arrayList == null || arrayList.size() == 0)) {
            HashMap<Long, Long> makeIdTimeMap = makeIdTimeMap(arrayList);
            List<Map.Entry<Long, Long>> sortOperation = sortOperation(makeIdTimeMap);
            if (sortOperation.size() != i7 || sortOperation.size() == 0) {
                Log.d(this.TAG, "item size is wrong size=" + sortOperation.size());
            } else {
                String fileIdList = getFileIdList(sortOperation);
                long currentTimeMillis = System.currentTimeMillis();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MpStoryColumns.getStoryContentId(), fileIdList);
                contentValues.put("title", str);
                contentValues.put("story_type", Integer.valueOf(storyType.getValue()));
                contentValues.put("start_time", (Long) sortOperation.get(0).getValue());
                contentValues.put("end_time", (Long) sortOperation.get(i7 - 1).getValue());
                contentValues.put("creation_time", Long.valueOf(currentTimeMillis));
                contentValues.put("modification_time", Long.valueOf(currentTimeMillis));
                if (storyType == StoryType.MANUAL) {
                    i8 = 1;
                } else {
                    i8 = 0;
                }
                contentValues.put("is_manual", Integer.valueOf(i8));
                contentValues.put("cloud_sync_unique_id", str2);
                contentValues.put(SpaceContract.Space.MEDIA_COUNT, Integer.valueOf(i7));
                contentValues.put("cover_id", String.valueOf(0));
                contentValues.put("is_visible", Boolean.TRUE);
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
        String m = C0212a.m("_id IN (", getStringFromArray(numArr), ")");
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
        IntStream.range(0, iArr.length).forEach(new n8.a(strArr, iArr, 1));
        if (delete(getDeleteSelection("sa_type", iArr.length), strArr) >= 0) {
            return true;
        }
        return false;
    }

    public Cursor getCollageCursor(int i2) {
        QueryBuilder g = C0212a.g("story");
        g.andCondition("_id=" + i2);
        g.addProjection("collage_type");
        g.addProjection("collage_sec_media_ids");
        return getCursor(new Query(g), "getCollageCursor");
    }

    public int getContentsCount(int i2) {
        MpStoryView mpStoryView = new MpStoryView(this.mParams);
        mpStoryView.addProjectionForCursorCount(false);
        mpStoryView.addStoryFileData();
        mpStoryView.filterStoryID(i2);
        return getContentsCountCursor(mpStoryView);
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
        QueryBuilder g = C0212a.g("hide_rule");
        g.andCondition("rule_type in (" + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, list) + ")");
        g.addProjection(new String[]{"_id"});
        ArrayList arrayList = new ArrayList();
        Cursor cursor = getCursor(new Query(g), "getHideRuleIds");
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        int i2 = cursor.getInt(0);
                        if (i2 > 0) {
                            arrayList.add(Integer.valueOf(i2));
                        }
                    } while (cursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
        throw th;
    }

    public List<String> getRecapFilePath(Integer[] numArr) {
        Cursor cursor;
        QueryBuilder g = C0212a.g("story");
        StringBuilder sb2 = new StringBuilder();
        if (numArr != null) {
            Arrays.stream(numArr).forEach(new P(sb2, 7));
        }
        sb2.setLength(sb2.length() - 4);
        g.andCondition("(" + sb2 + ")");
        StringBuilder sb3 = new StringBuilder("story_type=");
        sb3.append(StoryType.RECAP.getValue());
        g.andCondition(sb3.toString());
        g.addProjection("story_enhanced_cover_applied");
        ArrayList arrayList = new ArrayList();
        try {
            cursor = getCursor(new Query(g), "getRecapFilePath");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        arrayList.add(cursor.getString(0));
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

    public Long[] getStoryAlbumContentsIds(int i2) {
        ArrayList arrayList = new ArrayList();
        MpStoryView mpStoryView = new MpStoryView(this.mParams);
        mpStoryView.addStoryFileData();
        mpStoryView.filterStoryID(i2);
        Cursor cursor = getCursor(mpStoryView.buildSelectQuery(), "getStoryAlbumContentsIds");
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
        g.andCondition("_id=" + i2);
        g.addProjection("cover_id");
        try {
            cursor = getCursor(new Query(g), "getStoryCoverFileId");
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
        g.andCondition("cloud_sync_unique_id='" + str + "'");
        g.addProjection("_id");
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
            Arrays.stream(iArr).forEach(new n8.b(sb2, 1));
        }
        sb2.setLength(sb2.length() - 4);
        g.andCondition("(" + sb2 + ")");
        g.addProjection("_id");
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
        g.andCondition("_id=" + i2);
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
        MpStoryView mpStoryView = new MpStoryView(this.mParams);
        mpStoryView.modifyForStoryPeopleHeader();
        mpStoryView.addStoryVisibilityCondition(true);
        mpStoryView.filterStoryID(i2);
        mpStoryView.groupByStoryAlbum();
        try {
            cursor = getCursor(mpStoryView.buildSelectQuery(), "getStoryPeopleFileId");
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
            QueryBuilder g = C0212a.g("analyze_info");
            C0212a.w("sec_media_id in (", str, ")", g);
            g.addProjection(new String[]{"sec_media_id", "total_smartcrop_ratio", "total_smartcrop_device_ratio"});
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
        g.andCondition("_id=" + i2);
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
        throw new UnsupportedOperationException("not supported by secmp");
    }

    public boolean removeHideRule(int i2) {
        Log.d(this.TAG, "removeHideRule", Integer.valueOf(i2));
        ContentValues contentValues = new ContentValues();
        contentValues.put("rule_type", 1001);
        StringBuilder sb2 = new StringBuilder("_id=");
        sb2.append(i2);
        return deleteToHideRule(contentValues, sb2.toString(), (String[]) null) > 0;
    }

    public boolean removeItemsFromStory(ArrayList<FileItemInterface> arrayList) {
        arrayList.size();
        int albumID = arrayList.get(0).getAlbumID();
        ArrayList arrayList2 = new ArrayList();
        Iterator<FileItemInterface> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(Long.valueOf(it.next().getFileId()));
        }
        String storyName = getStoryName(albumID);
        int deleteItems = deleteItems(albumID, MpStoryColumns.getStoryContentId() + " IN (" + TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList2) + ")", (String[]) null);
        if (deleteItems >= 0) {
            updateStoryUpdatedByUser(albumID, maskStoryUpdatedByUserFlag(albumID), "removeItemsFromStory");
            searchIndexing((ArrayList<Long>) arrayList2, storyName, IntelligentSearchIndex.IndexMode.REMOVE);
        }
        if (deleteItems >= 0) {
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
            contentValues.put("cover_id", String.valueOf(0));
            contentValues.put("cover_rect_ratio", "0,0,0,0");
            updateStory(i2, contentValues);
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
        }
    }

    public String tag() {
        return "MpStoryApiImpl";
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
            long fileId = list.get(i7).getFileId();
            arrayList.add(ContentProviderOperation.newUpdate(StoryHelperBaseImpl.CMH_STORY_TABLE_URI).withSelection(MpStoryColumns.getEditableStoryId() + "=? AND " + MpStoryColumns.getStoryContentId() + "=?", new String[]{String.valueOf(i2), String.valueOf(fileId)}).withValue("display_order", Long.valueOf(currentTimeMillis - ((long) i7))).build());
        }
        if (arrayList.isEmpty() || applyBatch(arrayList, 1) == null) {
            return false;
        }
        return true;
    }

    public boolean updateFeatureState(String str, int i2) {
        throw new UnsupportedOperationException("not supported by secmp");
    }

    public boolean updateHideRuleOption(List<Integer> list, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("individual", Integer.valueOf(i2));
        if (updateToHideRule(contentValues, "_id in (" + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, list) + ")", (String[]) null, new ArrayList(list)) > 0) {
            return true;
        }
        return false;
    }

    public void updateMergedCreaturesToHideRule(String str, String str2, String[] strArr, String str3) {
        String str4;
        int i2;
        int i7;
        int i8;
        String str5 = str2;
        String[] strArr2 = strArr;
        try {
            if (TextUtils.isEmpty(str)) {
                str4 = str5;
            } else {
                str4 = str;
            }
            long unifiedIdentityId = IdentityCreatureUtil.getUnifiedIdentityId(str4);
            boolean isPerson = IdentityCreatureUtil.isPerson(str4);
            if (!str5.equals(str4)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("recommended_id", Long.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(str5)));
                if (!TextUtils.isEmpty(str3)) {
                    contentValues.put("private_data", str3);
                }
                StringBuilder sb2 = new StringBuilder("recommended_id=");
                sb2.append(unifiedIdentityId);
                sb2.append(" AND rule_type=");
                if (isPerson) {
                    i8 = 1;
                } else {
                    i8 = 3;
                }
                sb2.append(i8);
                updateToHideRule(contentValues, sb2.toString(), (String[]) null, (ArrayList<Integer>) null);
            }
            if (strArr2 != null && strArr2.length > 0) {
                List list = (List) Arrays.stream(strArr2).collect(Collectors.toList());
                list.remove(str4);
                if (!list.isEmpty()) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("rule_type", 1001);
                    StringBuilder sb3 = new StringBuilder("recommended_id IN (");
                    sb3.append(IdentityCreatureUtil.getUnifiedIdentityIds(list, isPerson));
                    sb3.append(") AND rule_type=");
                    if (isPerson) {
                        i7 = 1;
                    } else {
                        i7 = 3;
                    }
                    sb3.append(i7);
                    i2 = deleteToHideRule(contentValues2, sb3.toString(), (String[]) null);
                    Log.d(this.TAG, "updateMergedCreaturesToHideRule", str, str5, Boolean.valueOf(isPerson), Integer.valueOf(i2), "");
                }
            }
            i2 = 0;
            Log.d(this.TAG, "updateMergedCreaturesToHideRule", str, str5, Boolean.valueOf(isPerson), Integer.valueOf(i2), "");
        } catch (Exception e) {
            j.s(e, new StringBuilder("updateMergedCreaturesToHideRule failed. e="), this.TAG);
        }
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
                ContentProviderOperation.Builder withValue = ContentProviderOperation.newUpdate(StoryHelperBaseImpl.CMH_STORY_TABLE_URI).withSelection(MpStoryColumns.getEditableStoryId() + "=?", new String[]{String.valueOf(fileItemInterface.getAlbumID())}).withValue("story_favorite", Long.valueOf(j2)).withValue("modification_time", Long.valueOf(System.currentTimeMillis()));
                Integer num = map.get(Integer.valueOf(fileItemInterface.getAlbumID()));
                if (num != null && num.intValue() > 0) {
                    withValue.withValue("story_type", num);
                }
                arrayList.add(withValue.build());
            }
        }
        if (!arrayList.isEmpty()) {
            return applyBatch(arrayList, 1);
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
            arrayList.add(ContentProviderOperation.newUpdate(StoryHelperBaseImpl.CMH_STORY_TABLE_URI).withSelection(MpStoryColumns.getEditableStoryId() + "=?", new String[]{String.valueOf(tArr[i2].getAlbumID())}).withValue("story_favorite", l).withValue("modification_time", Long.valueOf(System.currentTimeMillis())).build());
        }
        ContentProviderResult[] applyBatch = applyBatch(arrayList, 1);
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
        MpStoryView mpStoryView = new MpStoryView(this.mParams);
        mpStoryView.addStoryFileData();
        mpStoryView.filterStoryID(i2);
        mpStoryView.clearOrderBy();
        mpStoryView.orderByDateTakenDesc();
        try {
            cursor = getCursor(mpStoryView.buildSelectQuery(), "updateStoryTime");
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
        map.forEach(new c(arrayList, 3));
        try {
            applyBatch(arrayList, 1);
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
        }
    }

    public boolean updateStoryType(Integer[] numArr, int i2) {
        try {
            ArrayList arrayList = new ArrayList();
            for (Integer intValue : numArr) {
                arrayList.add(ContentProviderOperation.newUpdate(StoryHelperBaseImpl.CMH_STORY_TABLE_URI).withSelection(MpStoryColumns.getEditableStoryId() + "=?", new String[]{String.valueOf(intValue.intValue())}).withValue("story_type", Integer.valueOf(i2)).build());
            }
            if (i2 == StoryType.DELETED_STORY.getValue()) {
                for (Integer intValue2 : numArr) {
                    int intValue3 = intValue2.intValue();
                    searchIndexing(getStoryName(intValue3), intValue3, IntelligentSearchIndex.IndexMode.REMOVE);
                }
            }
            if (applyBatch(arrayList, 1) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("updateStoryType failed "), this.TAG);
            return false;
        }
    }

    public void updateUnmergedCreatureToHideRule(String str, String[] strArr, String[] strArr2) {
        int i2;
        try {
            long unifiedIdentityId = IdentityCreatureUtil.getUnifiedIdentityId(str);
            boolean isPerson = IdentityCreatureUtil.isPerson(str);
            ContentValues contentValues = new ContentValues();
            contentValues.put("rule_type", 1001);
            StringBuilder sb2 = new StringBuilder("recommended_id IN (");
            sb2.append(unifiedIdentityId);
            sb2.append(") AND rule_type=");
            if (isPerson) {
                i2 = 1;
            } else {
                i2 = 3;
            }
            sb2.append(i2);
            int deleteToHideRule = deleteToHideRule(contentValues, sb2.toString(), (String[]) null);
            if (deleteToHideRule <= 0 || strArr == null || strArr2 == null) {
                Log.d(this.TAG, "updateUnmergedCreatureToHideRule", Boolean.FALSE);
                return;
            }
            Log.d(this.TAG, "updateUnmergedCreatureToHideRule", Boolean.TRUE, Integer.valueOf(deleteToHideRule), Boolean.valueOf(addHideSceneRule(strArr, strArr2)));
        } catch (Exception e) {
            j.s(e, new StringBuilder("updateUnmergedCreatureToHideRule failed. e="), this.TAG);
        }
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

    public boolean addHideSceneRule(String[] strArr, String[] strArr2) {
        ArrayList arrayList = new ArrayList();
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        int i2 = 0;
        while (true) {
            int i7 = 1;
            if (i2 >= strArr.length) {
                break;
            }
            String str = strArr[i2];
            long unifiedIdentityId = IdentityCreatureUtil.getUnifiedIdentityId(str);
            if (unifiedIdentityId != -1) {
                boolean isPerson = IdentityCreatureUtil.isPerson(str);
                boolean atLeastSystem = SdkConfig.atLeastSystem(SdkConfig.SEM.B_MR5);
                if (!isPerson) {
                    i7 = atLeastSystem ? 3 : 2;
                }
                arrayList.add(ContentProviderOperation.newInsert(StoryHelperBaseImpl.CMH_STORY_HIDE_RULE).withValue("data_provider", 0).withValue("rule_type", Integer.valueOf(i7)).withValue("recommended_id", Long.valueOf(unifiedIdentityId)).withValue("private_data", strArr2[i2]).build());
                StringBuilder sb2 = new StringBuilder();
                j.z(sb2, isPerson ? "People/" : "Pet/", str, ",Uuid:");
                sb2.append(strArr2[i2]);
                stringJoiner.add(sb2.toString());
            }
            i2++;
        }
        Log.d(this.TAG, "addHideSceneRule", Integer.valueOf(arrayList.size()), stringJoiner.toString());
        if (applyBatchToHideRule(arrayList, 0) != null) {
            return true;
        }
        return false;
    }

    public boolean removeHideRule(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int valueOf : iArr) {
            arrayList.add(ContentProviderOperation.newUpdate(StoryHelperBaseImpl.CMH_STORY_HIDE_RULE).withValue("rule_type", 1001).withSelection("_id=?", new String[]{String.valueOf(valueOf)}).build());
        }
        Log.d(this.TAG, "removeHideRule id", (ArrayList) Arrays.stream(iArr).boxed().collect(Collectors.toCollection(new C0720a(1))));
        if (applyBatchToHideRule(arrayList, 2) != null) {
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
        return deleteStory((Integer[]) Arrays.stream(fileItemInterfaceArr).map(new e(4)).toArray(new C0517a(5)), z);
    }

    public long getCmhFileId(long j2) {
        return j2;
    }
}
