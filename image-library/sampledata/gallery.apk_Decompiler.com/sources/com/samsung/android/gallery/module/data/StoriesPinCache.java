package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinCache {
    private static volatile StoriesPinCache sInstance;
    private final AtomicBoolean mCached = new AtomicBoolean(false);
    private StoriesPinData mStoriesPinData;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ItemBuilder {
        final int favoriteIndex;
        final int scoringIndex;
        final int startTimeIndex;
        final int storyAlbumIdIndex;
        final int storyTypeIndex;

        public /* synthetic */ ItemBuilder(Cursor cursor, int i2) {
            this(cursor);
        }

        /* access modifiers changed from: private */
        public MediaItem build(Cursor cursor) {
            MediaItem mediaItem = new MediaItem();
            mediaItem.setAlbumID(getInt(cursor, this.storyAlbumIdIndex));
            MediaItemStory.setStoryType(mediaItem, getInt(cursor, this.storyTypeIndex));
            MediaItemStory.setStoryStartTime(mediaItem, getLong(cursor, this.startTimeIndex));
            MediaItemStory.setStoryScoring(mediaItem, getInt(cursor, this.scoringIndex));
            MediaItemStory.setStoryFavorite(mediaItem, getLong(cursor, this.favoriteIndex));
            return mediaItem;
        }

        private int getInt(Cursor cursor, int i2) {
            if (i2 > -1) {
                return cursor.getInt(i2);
            }
            return -1;
        }

        private long getLong(Cursor cursor, int i2) {
            if (i2 > -1) {
                return cursor.getLong(i2);
            }
            return -1;
        }

        private ItemBuilder(Cursor cursor) {
            this.storyAlbumIdIndex = cursor.getColumnIndex("__albumID");
            this.storyTypeIndex = cursor.getColumnIndex("__storyType");
            this.startTimeIndex = cursor.getColumnIndex("__startTime");
            this.scoringIndex = cursor.getColumnIndex("__story_scoring");
            this.favoriteIndex = cursor.getColumnIndex("__story_favorite");
        }
    }

    public static StoriesPinCache getInstance() {
        if (sInstance == null) {
            synchronized (StoriesPinCache.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new StoriesPinCache();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem lambda$loadComplete$0(StoriesPinData storiesPinData, Cursor cursor, HashMap hashMap, MediaItem mediaItem) {
        if (!storiesPinData.isDivider(mediaItem)) {
            return MediaItemLoader.loadMediaItem(cursor, ((Integer) hashMap.get(mediaItem)).intValue());
        }
        return mediaItem;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadComplete$1(StoriesPinData storiesPinData) {
        this.mStoriesPinData = storiesPinData;
        this.mCached.set(true);
        Log.d("MemoriesPinHelper", "pin cached {" + storiesPinData.size() + "}");
    }

    private void loadComplete(Cursor cursor, HashMap<MediaItem, Integer> hashMap) {
        StoriesPinData storiesPinData = new StoriesPinData(new ArrayList(hashMap.keySet()));
        storiesPinData.trimData(10);
        storiesPinData.getData().replaceAll(new l(storiesPinData, cursor, hashMap));
        ThreadUtil.postOnUiThread(new h(2, this, storiesPinData));
    }

    public StoriesPinData getPinData() {
        return this.mStoriesPinData;
    }

    public boolean isCached() {
        if (this.mStoriesPinData == null || !this.mCached.get()) {
            return false;
        }
        return true;
    }

    public void load(Cursor cursor) {
        ItemBuilder itemBuilder = new ItemBuilder(cursor, 0);
        int count = cursor.getCount();
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < count; i2++) {
            if (cursor.moveToPosition(i2)) {
                hashMap.put(itemBuilder.build(cursor), Integer.valueOf(i2));
            }
        }
        loadComplete(cursor, hashMap);
    }

    public void reset() {
        this.mStoriesPinData = null;
        this.mCached.set(false);
    }
}
