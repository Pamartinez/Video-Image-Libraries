package com.samsung.android.sdk.sgpl.content.story;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.provider.SpaceContract;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CmhStoryApiImpl implements IStoryApi {
    private static final Uri CMH_AUTHORITY_URI = Uri.parse("content://com.samsung.cmh");
    private static final String CMH_COLUMN_DATA = "_data";
    private static final String CMH_COLUMN_DATE_TAKEN = "datetaken";
    private static final String CMH_COLUMN_FILE_ID = "_id";
    private static final String CMH_COLUMN_GED_MEDIA_ID = "media_id";
    private static final String CMH_COLUMN_SEC_MEDIA_ID = "sec_media_id";
    private static final Uri CMH_FILES_TABLE_URI = Uri.parse("content://com.samsung.cmh/files");
    private static final Uri CMH_STORY_TABLE_URI = Uri.parse("content://com.samsung.cmh/story");
    private static final String EMPTY_VALUE = " ";
    private static final String[] PROJECTION = {"_id", "sec_media_id", "media_id", "datetaken", CMH_COLUMN_DATA};
    private static final String TAG = "CmhStoryApi";
    private static final int WAIT_INTERVAL = 200;
    private static final int WAIT_TIMEOUT = 2000;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Content {
        String displayOrder;
        /* access modifiers changed from: package-private */
        public int index;
        boolean isCover;
        /* access modifiers changed from: package-private */
        public String mediaId;
        String smartCropDeviceRatio;
        String smartCropRatio;

        public /* synthetic */ Content(int i2) {
            this();
        }

        private Content() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CursorValues {
        long cmhId;
        long datetaken;
        long mediaId;
        String path;
        long secMediaId;
        /* access modifiers changed from: package-private */
        public boolean useMediaId;

        public /* synthetic */ CursorValues(long j2) {
            this(j2, true);
        }

        public /* synthetic */ CursorValues(Cursor cursor, IndexHolder indexHolder, int i2) {
            this(cursor, indexHolder);
        }

        private CursorValues(Cursor cursor, IndexHolder indexHolder) {
            this.cmhId = cursor.getLong(indexHolder.get(cursor, "_id"));
            this.secMediaId = cursor.getLong(indexHolder.get(cursor, "sec_media_id"));
            this.mediaId = cursor.getLong(indexHolder.get(cursor, "media_id"));
            this.datetaken = cursor.getLong(indexHolder.get(cursor, "datetaken"));
            this.path = cursor.getString(indexHolder.get(cursor, CmhStoryApiImpl.CMH_COLUMN_DATA));
        }

        private CursorValues(long j2, boolean z) {
            this.mediaId = j2;
            this.useMediaId = z;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class IndexHolder {
        private final HashMap<String, Integer> indexHolder;

        public /* synthetic */ IndexHolder(int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public int get(Cursor cursor, String str) {
            HashMap<String, Integer> hashMap = this.indexHolder;
            Objects.requireNonNull(cursor);
            return hashMap.computeIfAbsent(str, new g(1, cursor)).intValue();
        }

        private IndexHolder() {
            this.indexHolder = new HashMap<>();
        }
    }

    private static ContentProviderResult[] applyBatch(ContentResolver contentResolver, Uri uri, ArrayList<ContentProviderOperation> arrayList) {
        try {
            String authority = uri.getAuthority();
            Objects.requireNonNull(authority);
            return contentResolver.applyBatch(authority, arrayList);
        } catch (Exception e) {
            Log.e(TAG, "applyBatch failed e =" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<Content> buildContents(MetaData metaData, List<String> list, HashMap<String, String> hashMap) {
        ArrayList<Content> arrayList = new ArrayList<>();
        for (String next : list) {
            Content content = new Content(0);
            String str = hashMap.get(next);
            if (!TextUtils.isEmpty(str)) {
                content.isCover = str.equals(metaData.coverUri);
                content.mediaId = Utils.parseId(next);
                int indexOf = metaData.sendUris.indexOf(str);
                content.index = indexOf;
                content.smartCropRatio = metaData.smartCropRatioList.get(indexOf);
                content.smartCropDeviceRatio = metaData.smartCropDeviceRatioList.get(indexOf);
                content.displayOrder = metaData.displayOrders.get(indexOf);
            } else {
                content.mediaId = Utils.parseId(next);
            }
            arrayList.add(content);
        }
        arrayList.sort(Comparator.comparingInt(new e(0)));
        return arrayList;
    }

    private static Uri cmhRawQueryUri(String str) {
        return CMH_AUTHORITY_URI.buildUpon().appendPath("rawquery").appendQueryParameter("rawquery", str).build();
    }

    private static int createStory(ContentResolver contentResolver, MetaData metaData, ArrayList<CursorValues> arrayList, long j2) {
        String str;
        MetaData metaData2 = metaData;
        boolean anyMatch = arrayList.stream().anyMatch(new c(0));
        List list = (List) arrayList.stream().map(new d(anyMatch, 0)).collect(Collectors.toList());
        Iterator<CursorValues> it = arrayList.iterator();
        long j3 = 0;
        long j8 = Long.MAX_VALUE;
        long j10 = Long.MIN_VALUE;
        long j11 = 0;
        while (it.hasNext()) {
            CursorValues next = it.next();
            long j12 = next.datetaken;
            if (j8 > j12) {
                j8 = j12;
            }
            if (j10 < j12) {
                j10 = j12;
            }
            if (j2 == next.mediaId) {
                j11 = next.secMediaId;
            }
        }
        if (anyMatch) {
            j11 = j2;
            j10 = 0;
        } else {
            j3 = j8;
        }
        ContentValues contentValues = new ContentValues();
        if (anyMatch) {
            str = "media_id";
        } else {
            str = "sec_media_id";
        }
        contentValues.put(str, String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, list));
        contentValues.put("start_time", Long.valueOf(j3));
        contentValues.put("end_time", Long.valueOf(j10));
        contentValues.put("creation_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("title", metaData2.title);
        contentValues.put("type", 10);
        contentValues.put("owner", -99);
        contentValues.put("is_manual", 0);
        contentValues.put(SpaceContract.Space.MEDIA_COUNT, Integer.valueOf(arrayList.size()));
        contentValues.put("cover", String.valueOf(j11));
        contentValues.put("is_visible", Boolean.TRUE);
        contentValues.put("is_suggestion", Boolean.FALSE);
        contentValues.put("notify_status", 1);
        if (Utils.supportTheme()) {
            contentValues.put("story_theme", metaData2.theme);
            contentValues.put("story_filter", metaData2.filter);
            contentValues.put("story_bgm_info", metaData2.bgm);
        }
        Uri insertToCmh = insertToCmh(contentResolver, contentValues);
        if (insertToCmh != null) {
            return Integer.parseInt(insertToCmh.getLastPathSegment());
        }
        return -1;
    }

    private static Cursor getCmhCursor(ContentResolver contentResolver, String str) {
        try {
            return contentResolver.query(cmhRawQueryUri(str), (String[]) null, str, (String[]) null, (String) null);
        } catch (Exception e) {
            Exception exc = e;
            Log.e(TAG, "getCmhCursor failed e =" + exc.getMessage());
            exc.printStackTrace();
            return null;
        }
    }

    private static long getCoverMediaId(ArrayList<Content> arrayList) {
        Iterator<Content> it = arrayList.iterator();
        while (it.hasNext()) {
            Content next = it.next();
            if (next.isCover) {
                return Long.parseLong(next.mediaId);
            }
        }
        return -1;
    }

    private static Content getLastContent(ArrayList<Content> arrayList) {
        return arrayList.stream().max(Comparator.comparingLong(new f(0))).orElse((Object) null);
    }

    private static String getQueryString(List<String> list) {
        return "SELECT " + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, PROJECTION) + " FROM files WHERE media_id IN (" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, list) + ")";
    }

    private static Uri insertToCmh(ContentResolver contentResolver, ContentValues contentValues) {
        try {
            return contentResolver.insert(CMH_STORY_TABLE_URI, contentValues);
        } catch (Exception e) {
            Log.e(TAG, "insertToChm failed e = " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$createStory$3(boolean z, CursorValues cursorValues) {
        long j2;
        if (z) {
            j2 = cursorValues.mediaId;
        } else {
            j2 = cursorValues.secMediaId;
        }
        return String.valueOf(j2);
    }

    private static boolean notEmpty(String str) {
        if (EMPTY_VALUE.equals(str) || TextUtils.isEmpty(str)) {
            return false;
        }
        return true;
    }

    private static ArrayList<CursorValues> queryFilesWithIds(ContentResolver contentResolver, ArrayList<Content> arrayList) {
        ArrayList<CursorValues> arrayList2 = new ArrayList<>();
        IndexHolder indexHolder = new IndexHolder(0);
        Cursor cmhCursor = getCmhCursor(contentResolver, getQueryString((List) arrayList.stream().map(new a(1)).collect(Collectors.toList())));
        if (cmhCursor != null) {
            while (cmhCursor.moveToNext()) {
                try {
                    arrayList2.add(new CursorValues(cmhCursor, indexHolder, 0));
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
        }
        if (cmhCursor != null) {
            cmhCursor.close();
        }
        return arrayList2;
        throw th;
    }

    private static void updateDisplayOrder(ContentResolver contentResolver, int i2, ArrayList<Content> arrayList, ArrayList<CursorValues> arrayList2) {
        Object obj;
        CursorValues cursorValues;
        HashMap hashMap = new HashMap();
        arrayList2.forEach(new b(0, hashMap));
        ArrayList arrayList3 = new ArrayList();
        Iterator<Content> it = arrayList.iterator();
        while (it.hasNext()) {
            Content next = it.next();
            long parseLong = Long.parseLong(next.mediaId);
            if (parseLong > 0 && hashMap.get(Long.valueOf(parseLong)) != null && (cursorValues = (CursorValues) hashMap.get(Long.valueOf(parseLong))) != null && !TextUtils.isEmpty(next.displayOrder)) {
                long parseLong2 = Long.parseLong(next.displayOrder);
                if (parseLong2 > 0) {
                    arrayList3.add(ContentProviderOperation.newUpdate(CMH_STORY_TABLE_URI).withSelection("fk_story_id=? AND fk_file_id=?", new String[]{Integer.toString(i2), Long.toString(cursorValues.cmhId)}).withValue("story_display_order", Long.valueOf(parseLong2)).build());
                }
            }
        }
        if (!arrayList3.isEmpty()) {
            ContentProviderResult[] applyBatch = applyBatch(contentResolver, CMH_AUTHORITY_URI, arrayList3);
            StringBuilder sb2 = new StringBuilder("updateDisplayOrder[result=");
            if (applyBatch != null) {
                obj = Integer.valueOf(applyBatch.length);
            } else {
                obj = "null";
            }
            sb2.append(obj);
            sb2.append(" , req=");
            sb2.append(arrayList3.size());
            sb2.append("]");
            Log.i(TAG, sb2.toString());
            return;
        }
        Log.i(TAG, "updateDisplayOrder #no record");
    }

    private static void updateSmartCrop(ContentResolver contentResolver, ArrayList<Content> arrayList, ArrayList<CursorValues> arrayList2) {
        Object obj;
        CursorValues cursorValues;
        ArrayList arrayList3 = new ArrayList();
        HashMap hashMap = new HashMap();
        arrayList2.forEach(new b(1, hashMap));
        Iterator<Content> it = arrayList.iterator();
        while (it.hasNext()) {
            Content next = it.next();
            long parseLong = Long.parseLong(next.mediaId);
            if (!(parseLong <= 0 || hashMap.get(Long.valueOf(parseLong)) == null || (cursorValues = (CursorValues) hashMap.get(Long.valueOf(parseLong))) == null)) {
                ContentProviderOperation.Builder withSelection = ContentProviderOperation.newUpdate(CMH_FILES_TABLE_URI).withSelection("sec_media_id =?", new String[]{Long.toString(cursorValues.secMediaId)});
                if (notEmpty(next.smartCropRatio)) {
                    withSelection.withValue("total_smartcrop_ratio", next.smartCropRatio);
                }
                if (notEmpty(next.smartCropDeviceRatio)) {
                    withSelection.withValue("total_smartcrop_device_ratio", next.smartCropDeviceRatio);
                }
                if (notEmpty(next.smartCropRatio) || notEmpty(next.smartCropDeviceRatio)) {
                    arrayList3.add(withSelection.build());
                }
            }
        }
        if (!arrayList3.isEmpty()) {
            ContentProviderResult[] applyBatch = applyBatch(contentResolver, CMH_AUTHORITY_URI, arrayList3);
            StringBuilder sb2 = new StringBuilder("updateSmartCrop[result=");
            if (applyBatch != null) {
                obj = Integer.valueOf(applyBatch.length);
            } else {
                obj = "null";
            }
            sb2.append(obj);
            sb2.append(" , req=");
            sb2.append(arrayList3.size());
            sb2.append("]");
            Log.i(TAG, sb2.toString());
            return;
        }
        Log.i(TAG, "updateSmartCrop #no record");
    }

    private static void verifyMpCmhSync(ArrayList<CursorValues> arrayList, ArrayList<Content> arrayList2) {
        if (arrayList.isEmpty()) {
            List<String> list = (List) arrayList2.stream().map(new a(0)).collect(Collectors.toList());
            Log.e(TAG, "cmh may not be synced with mp, size=" + list.size() + ", handle =" + Utils.supportCreateWithMediaId());
            if (Utils.supportCreateWithMediaId()) {
                for (String parseLong : list) {
                    arrayList.add(new CursorValues(Long.parseLong(parseLong)));
                }
            }
        }
    }

    private static void waitUtilCmhMpSync(ContentResolver contentResolver, ArrayList<Content> arrayList) {
        StringBuilder sb2;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            waitUtilCmhMpSync(contentResolver, getLastContent(arrayList), System.currentTimeMillis(), 2000, 10);
            sb2 = new StringBuilder("wait done +");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            sb2 = new StringBuilder("wait done +");
        } catch (Throwable th) {
            Throwable th2 = th;
            Log.d(TAG, "wait done +" + (System.currentTimeMillis() - currentTimeMillis));
            throw th2;
        }
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        Log.d(TAG, sb2.toString());
    }

    public int createStoryAlbum(Context context, List<String> list, String str, HashMap<String, String> hashMap) {
        String str2;
        Log.i(TAG, "support[" + Utils.supportCreateStory() + ",SEM=" + Utils.SEM_PLATFORM_INT + "]");
        if (!Utils.supportCreateStory()) {
            return -1;
        }
        Log.d(TAG, "received =" + list + "\n" + hashMap);
        if (list == null || list.isEmpty()) {
            Log.d(TAG, "createStory fail #no uris");
            return -1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        MetaData from = MetaData.from(str);
        Log.d(TAG, "Receive[" + list.size() + "]," + from);
        ArrayList<Content> buildContents = buildContents(from, list, hashMap);
        if (buildContents.isEmpty()) {
            Log.d(TAG, "createStory fail #no contents");
            return -1;
        }
        ContentResolver contentResolver = context.getContentResolver();
        waitUtilCmhMpSync(contentResolver, buildContents);
        ArrayList<CursorValues> queryFilesWithIds = queryFilesWithIds(contentResolver, buildContents);
        Log.i(TAG, "query files=" + Utils.tick(Integer.valueOf(queryFilesWithIds.size()), currentTimeMillis));
        verifyMpCmhSync(queryFilesWithIds, buildContents);
        int createStory = createStory(contentResolver, from, queryFilesWithIds, getCoverMediaId(buildContents));
        Log.d(TAG, "createStory id=" + Utils.tick(Integer.valueOf(createStory), currentTimeMillis));
        if (createStory > 0) {
            if (Utils.supportSmartCrop()) {
                updateSmartCrop(contentResolver, buildContents, queryFilesWithIds);
                Log.i(TAG, "updateSmartCrop" + Utils.tick(currentTimeMillis));
            }
            if (Utils.supportDisplayOrder()) {
                updateDisplayOrder(contentResolver, createStory, buildContents, queryFilesWithIds);
                Log.i(TAG, "updateDisplayOrder" + Utils.tick(currentTimeMillis));
            }
        } else {
            Log.e(TAG, "createStory #insertUri is null !!" + from);
        }
        StringBuilder sb2 = new StringBuilder("createStoryAlbum result = ");
        if (createStory > 0) {
            str2 = "success";
        } else {
            str2 = "fail";
        }
        sb2.append(str2);
        sb2.append(" , storyId = ");
        sb2.append(createStory);
        Log.d(TAG, sb2.toString());
        return createStory;
    }

    private static boolean waitUtilCmhMpSync(ContentResolver contentResolver, Content content, long j2, int i2, int i7) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(content);
        if (!queryFilesWithIds(contentResolver, arrayList).isEmpty()) {
            return true;
        }
        if (System.currentTimeMillis() - j2 > ((long) i2) || i7 == 0) {
            Log.e(TAG, "fail to find from cmh = " + content.mediaId + ", retry=" + i7);
            return false;
        }
        Utils.wait(200);
        return waitUtilCmhMpSync(contentResolver, content, j2, i2, i7 - 1);
    }
}
