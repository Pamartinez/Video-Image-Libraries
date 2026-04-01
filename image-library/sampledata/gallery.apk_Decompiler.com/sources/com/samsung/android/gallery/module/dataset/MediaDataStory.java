package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.EffectItemBuilder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.StoryData;
import com.samsung.android.gallery.module.dataset.MediaDataEntire;
import com.samsung.android.gallery.module.dataset.comparator.ComparatorEx;
import com.samsung.android.gallery.module.dataset.tables.ChapterIndexer;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.dataset.tables.DataTable;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.module.dataset.tables.SortedDataTable;
import com.samsung.android.gallery.module.dataset.tables.SpanIndexer;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.Utils;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataStory extends MediaDataStoryBase {
    ChapterCursorHolder mChapterCursorHolder;
    private ConcurrentHashMap<Long, ChapterData> mChapterDataMap;
    private ItemPreProcessor mItemPreprocessor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ChapterCursorHolder implements Closeable {
        Cursor mCursor;

        public /* synthetic */ ChapterCursorHolder(Cursor cursor, int i2) {
            this(cursor);
        }

        public void close() {
            Utils.closeSilently(this.mCursor);
        }

        public void swap(Cursor cursor) {
            close();
            this.mCursor = cursor;
        }

        private ChapterCursorHolder(Cursor cursor) {
            this.mCursor = cursor;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ChapterData {
        int chapterId;
        String chapterTitle;
        String chunkData;
        int chunkId;
        int chunkType;
        long fileId;

        public ChapterData(Cursor cursor) {
            this.chapterId = cursor.getInt(cursor.getColumnIndex("chapter_id"));
            this.chapterTitle = cursor.getString(cursor.getColumnIndex("chapter_title"));
            this.chunkId = cursor.getInt(cursor.getColumnIndex("chunk_id"));
            this.chunkType = cursor.getInt(cursor.getColumnIndex("chunkType"));
            this.chunkData = cursor.getString(cursor.getColumnIndex("chunkData"));
            this.fileId = cursor.getLong(cursor.getColumnIndex("__fileMediaId"));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ItemPreProcessor implements ComparatorEx<MediaItem> {
        private HashMap<String, Long> mChapter;
        private ConcurrentHashMap<Long, ChapterData> mChapterMap;
        private HashMap<String, Long> mChunk;

        public /* synthetic */ ItemPreProcessor(ConcurrentHashMap concurrentHashMap, int i2) {
            this(concurrentHashMap);
        }

        private void fillChapterData(MediaItem mediaItem) {
            ConcurrentHashMap<Long, ChapterData> concurrentHashMap;
            ChapterData chapterData;
            if (mediaItem != null && (concurrentHashMap = this.mChapterMap) != null && (chapterData = concurrentHashMap.get(Long.valueOf(mediaItem.getFileId()))) != null) {
                StoryData.of(mediaItem).setChapter(chapterData.chapterId, chapterData.chapterTitle).setChunk(chapterData.chunkId, chapterData.chunkData, chapterData.chunkType);
            }
        }

        private long getChapterDataTaken(MediaItem mediaItem) {
            Long l = this.mChapter.get(getChapterKey(mediaItem));
            if (l != null) {
                return l.longValue();
            }
            return mediaItem.getDateTaken();
        }

        private String getChapterKey(MediaItem mediaItem) {
            if (MediaItemStory.getStoryChapterId(mediaItem) <= 0) {
                return "";
            }
            return "/chapter=" + MediaItemStory.getStoryChapterId(mediaItem);
        }

        private long getChunkDataTaken(MediaItem mediaItem) {
            Long l = this.mChunk.get(getChunkKey(mediaItem));
            if (l != null) {
                return l.longValue();
            }
            return mediaItem.getDateTaken();
        }

        private String getChunkKey(MediaItem mediaItem) {
            if (MediaItemStory.getStoryChunkId(mediaItem) <= 0) {
                return "";
            }
            return "/chunk=" + MediaItemStory.getStoryChunkId(mediaItem);
        }

        private void prepareCluster(ArrayList<MediaItem> arrayList) {
            Long l;
            Long l8;
            this.mChapter = new HashMap<>();
            this.mChunk = new HashMap<>();
            Iterator<MediaItem> it = arrayList.iterator();
            while (it.hasNext()) {
                MediaItem next = it.next();
                fillChapterData(next);
                String chapterKey = getChapterKey(next);
                if (!TextUtils.isEmpty(chapterKey) && ((l8 = this.mChapter.get(chapterKey)) == null || l8.longValue() > next.getDateTaken())) {
                    this.mChapter.put(chapterKey, Long.valueOf(next.getDateTaken()));
                }
                String chunkKey = getChunkKey(next);
                if (!TextUtils.isEmpty(chunkKey) && ((l = this.mChunk.get(chunkKey)) == null || l.longValue() > next.getDateTaken())) {
                    this.mChunk.put(chunkKey, Long.valueOf(next.getDateTaken()));
                }
            }
        }

        private void prepareSuggestedEffect(ArrayList<MediaItem> arrayList) {
            Iterator<MediaItem> it = arrayList.iterator();
            while (it.hasNext()) {
                MediaItem next = it.next();
                MediaItemStory.setEffectItem(next, EffectItemBuilder.build(next));
            }
        }

        /* access modifiers changed from: private */
        public void updateChapterMap(ConcurrentHashMap<Long, ChapterData> concurrentHashMap) {
            if (concurrentHashMap != null) {
                ConcurrentHashMap<Long, ChapterData> concurrentHashMap2 = new ConcurrentHashMap<>();
                this.mChapterMap = concurrentHashMap2;
                concurrentHashMap2.putAll(concurrentHashMap);
            }
        }

        public void prepare(ArrayList<MediaItem> arrayList) {
            prepareCluster(arrayList);
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.SuggestedEffectOnStory)) {
                prepareSuggestedEffect(arrayList);
            }
        }

        private ItemPreProcessor(ConcurrentHashMap<Long, ChapterData> concurrentHashMap) {
            updateChapterMap(concurrentHashMap);
        }

        public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
            long chapterDataTaken = getChapterDataTaken(mediaItem);
            long chapterDataTaken2 = getChapterDataTaken(mediaItem2);
            if (chapterDataTaken != chapterDataTaken2) {
                return Long.compare(chapterDataTaken, chapterDataTaken2);
            }
            long chunkDataTaken = getChunkDataTaken(mediaItem);
            long chunkDataTaken2 = getChunkDataTaken(mediaItem2);
            if (chunkDataTaken != chunkDataTaken2) {
                return Long.compare(chunkDataTaken, chunkDataTaken2);
            }
            return Long.compare(mediaItem.getDateTaken(), mediaItem2.getDateTaken());
        }

        public void complete(ArrayList<MediaItem> arrayList) {
        }
    }

    public MediaDataStory(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private ItemPreProcessor createItemPreprocessor() {
        return new ItemPreProcessor(this.mChapterDataMap, 0);
    }

    private Cursor getChapterCursor(Cursor[] cursorArr) {
        if (cursorArr == null || cursorArr.length <= 1) {
            return null;
        }
        return cursorArr[1];
    }

    private void swapChapterCursor(Cursor[] cursorArr, CountDownLatch countDownLatch) {
        Cursor cursor;
        if (cursorArr.length > 1 && (cursor = cursorArr[1]) != null) {
            ChapterCursorHolder chapterCursorHolder = this.mChapterCursorHolder;
            if (chapterCursorHolder == null) {
                this.mChapterCursorHolder = new ChapterCursorHolder(cursor, 0);
            } else {
                chapterCursorHolder.swap(cursor);
            }
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    public DataTable createDataTable(Cursor cursor) {
        return new SortedDataTable(cursor, getSorter());
    }

    public void createSecondExtraIndexer(Closeable[] closeableArr, int i2, DefaultTable[] defaultTableArr, DataTable dataTable) {
        closeableArr[1] = dataTable.getChapterIndexer();
    }

    public ChapterIndexer getChapterIndexer() {
        return (ChapterIndexer) this.mExtraIndexers[1];
    }

    public int getExtraTableCount() {
        return 2;
    }

    public SpanIndexer getSpanIndexer() {
        return null;
    }

    public void initExtraTable(Cursor[] cursorArr, CountDownLatch countDownLatch, MediaDataEntire.Candidates candidates) {
        swapChapterCursor(cursorArr, countDownLatch);
    }

    public void onDestroy() {
        super.onDestroy();
        Utils.closeSilently(this.mExtraIndexers[1]);
        this.mExtraIndexers[1] = null;
        Utils.closeSilently(this.mChapterCursorHolder);
        this.mChapterCursorHolder = null;
    }

    public void preprocessCursors(Cursor[] cursorArr, MediaDataEntire.Candidates candidates) {
        Cursor chapterCursor = getChapterCursor(cursorArr);
        if (chapterCursor != null) {
            long currentTimeMillis = System.currentTimeMillis();
            ConcurrentHashMap<Long, ChapterData> concurrentHashMap = new ConcurrentHashMap<>();
            while (chapterCursor.moveToNext()) {
                ChapterData chapterData = new ChapterData(chapterCursor);
                concurrentHashMap.put(Long.valueOf(chapterData.fileId), chapterData);
            }
            this.mChapterDataMap = concurrentHashMap;
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "preprocessCursors{" + concurrentHashMap.size() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    public boolean supportDayCluster() {
        return false;
    }

    public boolean supportMonthCluster() {
        return false;
    }

    public boolean supportYearCluster() {
        return false;
    }

    public void swapClusterTables(Cursor[] cursorArr, ClusterTable[] clusterTableArr, DefaultTable[] defaultTableArr, int i2, CountDownLatch countDownLatch) {
        getSorter().updateChapterMap(this.mChapterDataMap);
        swapChapterCursor(cursorArr, countDownLatch);
    }

    public boolean useSortedTable() {
        return true;
    }

    public ItemPreProcessor getSorter() {
        if (this.mItemPreprocessor == null) {
            this.mItemPreprocessor = createItemPreprocessor();
        }
        return this.mItemPreprocessor;
    }
}
