package com.samsung.android.gallery.module.publisher;

import A.a;
import A4.C0368c;
import D3.j;
import H.b;
import M9.c;
import M9.e;
import M9.m;
import M9.n;
import M9.o;
import android.database.Cursor;
import android.database.MergeCursor;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.SortableMergeCursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.CacheProviderHelper;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.StoryCategory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesDataPublisher extends CursorPublisher {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CacheCursorWrapper extends MergeCursor {
        HashMap<String, Integer> indexing = new HashMap<>();
        private final List<Info> infos;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Info {
            int cursorPos;

            public /* synthetic */ Info(Cursor cursor, int i2) {
                this(cursor);
            }

            private Info(Cursor cursor) {
                this.cursorPos = cursor.getPosition();
            }
        }

        private CacheCursorWrapper(Cursor[] cursorArr) {
            super(cursorArr);
            this.infos = loadPosition(cursorArr[0]);
        }

        /* access modifiers changed from: private */
        public static CacheCursorWrapper create(Cursor cursor) {
            return new CacheCursorWrapper(new Cursor[]{cursor});
        }

        private int getCursorPosition(int i2) {
            Info info = this.infos.get(i2);
            if (info != null) {
                return info.cursorPos;
            }
            return -1;
        }

        /* access modifiers changed from: private */
        public int getSize() {
            return this.infos.size();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Integer lambda$loadPosition$0(String str, Integer num) {
            int i2;
            if (num == null) {
                i2 = 0;
            } else {
                i2 = num.intValue() + 1;
            }
            return Integer.valueOf(i2);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Integer lambda$loadPosition$1(String str, Integer num) {
            int i2;
            if (num == null) {
                i2 = 0;
            } else {
                i2 = num.intValue() + 1;
            }
            return Integer.valueOf(i2);
        }

        private List<Info> loadPosition(Cursor cursor) {
            String str;
            int i2;
            ArrayList arrayList = new ArrayList();
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex("__storyType");
                int columnIndex2 = cursor.getColumnIndex("__storySaType");
                do {
                    int i7 = cursor.getInt(columnIndex);
                    if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
                        int i8 = -1;
                        if (columnIndex2 != -1) {
                            i8 = cursor.getInt(columnIndex2);
                        }
                        String key = StoryCategory.getKey(i7, i8);
                        if (StoryCategory.getMaxPreloadCount(key) > this.indexing.compute(key, new e0(0)).intValue()) {
                            arrayList.add(new Info(cursor, 0));
                        }
                    } else {
                        if (StoryType.isTransitoryType(i7)) {
                            str = "location://stories/category/transitory";
                        } else {
                            str = "location://stories/category/more";
                        }
                        int intValue = this.indexing.compute(str, new e0(1)).intValue();
                        if ("location://stories/category/transitory".equals(str)) {
                            i2 = Integer.MAX_VALUE;
                        } else {
                            i2 = 12;
                        }
                        if (i2 > intValue) {
                            arrayList.add(new Info(cursor, 0));
                        }
                    }
                } while (cursor.moveToNext());
            }
            return arrayList;
        }

        public boolean onMove(int i2, int i7) {
            int cursorPosition = getCursorPosition(i7);
            if (cursorPosition != -1) {
                return super.onMove(i2, cursorPosition);
            }
            return super.onMove(i2, i7);
        }
    }

    public StoriesDataPublisher(Blackboard blackboard) {
        super(blackboard);
    }

    private void cacheStoriesCursor(Cursor cursor) {
        if (PreferenceFeatures.PERFORMANCE.STORIES_CURSOR_CACHE && cursor != null && cursor.getCount() > 0) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                CacheCursorWrapper g = CacheCursorWrapper.create(cursor);
                int f = g.getSize();
                CacheProviderHelper.cacheCursor("location://story/albums", g, f);
                StringCompat stringCompat = this.TAG;
                StringBuilder sb2 = new StringBuilder("cacheStoriesCursor =");
                sb2.append(Logger.vt(f + "/" + cursor.getCount(), Long.valueOf(currentTimeMillis)));
                Log.d(stringCompat, sb2.toString());
            } catch (Exception e) {
                a.r(e, new StringBuilder("cacheStoriesCursor failed e="), this.TAG);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishHideSceneSelectionData$10(Cursor[] cursorArr) {
        cursorArr[2] = DbCompat.query(new QueryParams(DbKey.STORY_HIDE_SCENE_SELECTION).setTag("type", "unnamed"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishHideSceneSelectionData$11(Cursor[] cursorArr, long j2) {
        SortableMergeCursor sortableMergeCursor = new SortableMergeCursor(cursorArr);
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishHideSceneSelectionData : " + getCursorInfo(sortableMergeCursor, j2));
        publishCursorArray("location://stories/hideSceneSelection", new Cursor[]{sortableMergeCursor});
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishHideSceneSelectionData$8(Cursor[] cursorArr) {
        cursorArr[0] = DbCompat.query(new QueryParams(DbKey.STORY_HIDE_SCENE_SELECTION).setTag("type", "scene"));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishHideSceneSelectionData$9(Cursor[] cursorArr) {
        cursorArr[1] = DbCompat.query(new QueryParams(DbKey.STORY_HIDE_SCENE_SELECTION).setTag("type", "named"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishStoriesCacheData$0() {
        PreferenceCache.StoryViewedIds.clear();
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open("location://story/albums", true);
        try {
            open.reopen("location://story/albums");
            open.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishStoryAlbumFileData$1(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[0] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishStoryAlbumFileData$2(Cursor[] cursorArr, int i2) {
        cursorArr[1] = DbCompat.query(new QueryParams(DbKey.STORY_FILES_CHAPTER).addAlbumId(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishStoryAlbumFileData$3(QueryParams queryParams, Cursor[] cursorArr, long j2, String str) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "publishStoryAlbumFileData " + queryParams + " " + getCursorListInfo(cursorArr, j2));
        publishCursorArray(str, cursorArr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishStoryHideRuleData$4(Cursor[] cursorArr) {
        cursorArr[0] = DbCompat.query(new QueryParams(DbKey.STORY_HIDE_RULE_SCENE).setTag("type", "scene"));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishStoryHideRuleData$5(Cursor[] cursorArr) {
        cursorArr[1] = DbCompat.query(new QueryParams(DbKey.STORY_HIDE_RULE_SCENE).setTag("type", "named"));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishStoryHideRuleData$6(Cursor[] cursorArr) {
        cursorArr[2] = DbCompat.query(new QueryParams(DbKey.STORY_HIDE_RULE_SCENE).setTag("type", "unnamed"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishStoryHideRuleData$7(Cursor[] cursorArr, Cursor[] cursorArr2, long j2) {
        cursorArr[1] = new SortableMergeCursor(cursorArr2);
        a.y(new StringBuilder("publishStoryHideRuleData : "), getCursorListInfo(cursorArr, j2), this.TAG);
        publishCursorArray("location://stories/hideRule", cursorArr);
    }

    private void publishStoriesCacheData(Object obj, Bundle bundle) {
        Cursor recoverCursor;
        Trace.beginSection("publishStoriesCacheData");
        long currentTimeMillis = System.currentTimeMillis();
        bundle.remove("stories_cursor_cache");
        Cursor query = CacheProviderHelper.query("location://story/albums");
        if (query != null) {
            try {
                if (query.getCount() > 0 && query.moveToFirst() && (recoverCursor = new CacheProviderHelper.CacheReader(query).recoverCursor()) != null && recoverCursor.getCount() > 0) {
                    publishCursorArray("location://story/albums", new Cursor[]{recoverCursor});
                    StringCompat stringCompat = this.TAG;
                    Log.d(stringCompat, "publishStoriesCacheData " + Logger.vt(Integer.valueOf(recoverCursor.getCount()), Long.valueOf(currentTimeMillis)));
                    ThreadUtil.postOnBgThreadDelayed(new o(0, this), 100);
                    query.close();
                    return;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        publishStoriesData(obj, bundle);
        Trace.endSection();
        return;
        throw th;
    }

    /* access modifiers changed from: private */
    public void publishStoriesData(Object obj, Bundle bundle) {
        if (!PickerUtil.isNormalLaunchMode(this.mBlackboard)) {
            publishStoriesPickerData(obj, bundle);
        } else if (BundleWrapper.getBoolean(bundle, "stories_cursor_cache")) {
            publishStoriesCacheData(obj, bundle);
        } else {
            publishStoriesFullData(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void publishStoriesFavoriteData(Object obj, Bundle bundle) {
        Trace.beginSection("publishStoryFavoriteData");
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        QueryParams storyFavoriteType = new QueryParams(DbKey.STORIES).setStoryFavoriteType(true);
        if (bundle != null) {
            storyFavoriteType.setTag("fromPictureFrame", Boolean.valueOf(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(bundle.getString("fromPictureFrame", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE))));
        }
        Cursor query = DbCompat.query(storyFavoriteType);
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishStoryFavoriteData " + storyFavoriteType + " " + getCursorInfo(query, currentTimeMillis));
        publishCursorArray("location://stories/favorite", new Cursor[]{query});
        Trace.endSection();
    }

    private void publishStoriesFullData(Object obj, Bundle bundle) {
        Trace.beginSection("publishStoriesFullData");
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        QueryParams queryParams = new QueryParams(DbKey.STORIES);
        Cursor query = DbCompat.query(queryParams);
        cacheStoriesCursor(query);
        publishCursorArray("location://story/albums", new Cursor[]{query});
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishStoriesFullData " + queryParams + " " + getCursorInfo(query, currentTimeMillis));
        Trace.endSection();
    }

    private void publishStoriesPickerData(Object obj, Bundle bundle) {
        QueryParams.DbStorageType dbStorageType;
        Trace.beginSection("publishStoriesPickerData");
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        QueryParams queryParams = new QueryParams(DbKey.STORIES_PICKER);
        LaunchIntent launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isLocalContentOnly()) {
            dbStorageType = QueryParams.DbStorageType.All;
        } else {
            dbStorageType = QueryParams.DbStorageType.LocalOnly;
        }
        queryParams.setStorageTypes(dbStorageType);
        queryParams.setMediaTypeFilter(bundle.getString("filterMediaType", (String) null));
        Cursor query = DbCompat.query(queryParams);
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishStoriesPickerData " + queryParams + " " + getCursorInfo(query, currentTimeMillis));
        publishCursorArray("location://story/albums", new Cursor[]{query});
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public void publishStoryAlbumFileData(Object obj, Bundle bundle) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        String DATA_REQUEST_TO_LOCATION = CommandKey.DATA_REQUEST_TO_LOCATION(BundleWrapper.getKey(bundle));
        assertArgumentId(bundle);
        int parseInt = Integer.parseInt(bundle.getString("id", "0"));
        QueryParams addAlbumId = new QueryParams(DbKey.STORY_FILES).addAlbumId(parseInt);
        Cursor[] cursorArr = {null, null};
        if (!PreferenceFeatures.OneUi30.MEMORIES || !Features.isEnabled(Features.SUPPORT_MEMORY_DATA)) {
            if (PreferenceFeatures.OneUi30.MEMORIES || BundleWrapper.getBoolean(bundle, "disableTimeline", false)) {
                z = true;
            } else {
                z = false;
            }
            cursorArr[0] = DbCompat.query(addAlbumId);
            if (!z) {
                cursorArr[1] = DbCompat.query(new QueryParams(DbKey.STORY_FILES_DAY).addAlbumId(parseInt));
            }
            Log.d(this.TAG, "publishStoryAlbumFileData " + addAlbumId + " " + getCursorListInfo(cursorArr, currentTimeMillis));
            publishCursorArray(DATA_REQUEST_TO_LOCATION, cursorArr);
            return;
        }
        new LatchBuilder("publishStoryAlbumFileData").addWorker(new c(cursorArr, 6, addAlbumId)).addWorker(new C0368c(cursorArr, parseInt, 12)).setPostExecutor((Runnable) new e(this, addAlbumId, cursorArr, currentTimeMillis, DATA_REQUEST_TO_LOCATION)).start();
    }

    /* access modifiers changed from: private */
    public void publishStoryHighlightData(Object obj, Bundle bundle) {
        publishStoryHighlightData(obj, bundle, DbKey.STORY_FILES_CURATION);
    }

    /* access modifiers changed from: private */
    public void publishStoryHighlightListData(Object obj, Bundle bundle) {
        publishStoryHighlightData(obj, bundle, DbKey.STORY_FILES);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://story/albums"), new m(this, 0)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://stories/favorite"), new m(this, 1)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://story/albums/fileList/#"), new m(this, 2)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://story/albums/storyHighlight/#"), new m(this, 3)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://story/albums/storyHighlightList/#"), new m(this, 4)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://stories/hideRule"), new m(this, 5)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://stories/hideSceneSelection"), new m(this, 6)));
    }

    public void publishHideSceneSelectionData(Object obj, Bundle bundle) {
        Cursor[] cursorArr = new Cursor[3];
        new LatchBuilder("publishHideSceneSelectionData").addWorker(new n(cursorArr, 3)).addWorker(new n(cursorArr, 4)).addWorker(new n(cursorArr, 5)).setPostExecutor((Runnable) new b(this, cursorArr, System.currentTimeMillis(), 1)).start();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.database.Cursor[], java.io.Serializable] */
    public void publishStoryHideRuleData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor[] cursorArr = {null, null};
        cursorArr[0] = DbCompat.query(new QueryParams(DbKey.STORY_HIDE_RULE_DATE));
        ? r32 = new Cursor[3];
        new LatchBuilder("publishStoryHideRuleData").addWorker(new n(r32, 0)).addWorker(new n(r32, 1)).addWorker(new n(r32, 2)).setPostExecutor((Runnable) new j((Object) this, (Object) cursorArr, (Serializable) r32, currentTimeMillis, 3)).start();
    }

    private void publishStoryHighlightData(Object obj, Bundle bundle, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        String DATA_REQUEST_TO_LOCATION = CommandKey.DATA_REQUEST_TO_LOCATION(BundleWrapper.getKey(bundle));
        assertArgumentId(bundle);
        QueryParams addAlbumId = new QueryParams(str).addAlbumId(Integer.parseInt(bundle.getString("id", "0")));
        Cursor[] cursorArr = {DbCompat.query(addAlbumId)};
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "publishStoryHighlightData " + addAlbumId + " " + getCursorListInfo(cursorArr, currentTimeMillis));
        publishCursorArray(DATA_REQUEST_TO_LOCATION, cursorArr);
    }
}
