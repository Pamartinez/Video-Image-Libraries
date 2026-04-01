package com.samsung.android.gallery.bixby.bixbycard;

import android.content.Context;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sum.core.descriptor.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryDataFetcher extends AbsDataFetcher {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final StoryDataFetcher INSTANCE = new StoryDataFetcher();
    }

    public static StoryDataFetcher getInstance() {
        return LazyHolder.INSTANCE;
    }

    private GalleryCardData getStoryDetails(Context context, MediaItem mediaItem) {
        GalleryCardData galleryCardData = null;
        if (mediaItem == null) {
            Log.bx("StoryDataFetcher", "getStoryDetails failed. item is null");
            return null;
        }
        try {
            Log.bx("StoryDataFetcher", "getStoryDetails start");
            int storyId = MediaItemStory.getStoryId(mediaItem);
            String title = mediaItem.getTitle();
            String uriString = ContentUri.getUriString(mediaItem);
            String mimeType = mediaItem.getMimeType();
            if (!(uriString == null || uriString.isEmpty() || title == null)) {
                GalleryCardData galleryCardData2 = new GalleryCardData(uriString, mimeType);
                try {
                    galleryCardData2.setTitle(title);
                    galleryCardData2.setStoryId(storyId);
                    Log.bx("StoryDataFetcher", "card is generated [" + storyId + "][" + Logger.getEncodedString(title) + "]");
                    galleryCardData = galleryCardData2;
                } catch (Exception e) {
                    e = e;
                    galleryCardData = galleryCardData2;
                    try {
                        Log.bxe("StoryDataFetcher", e.getMessage());
                        Log.bx("StoryDataFetcher", "getStoryDetails [" + galleryCardData + "] end");
                        return galleryCardData;
                    } catch (Throwable th) {
                        th = th;
                        Log.bx("StoryDataFetcher", "getStoryDetails [" + galleryCardData + "] end");
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    galleryCardData = galleryCardData2;
                    Log.bx("StoryDataFetcher", "getStoryDetails [" + galleryCardData + "] end");
                    throw th;
                }
            }
            Log.bx("StoryDataFetcher", "getStoryDetails [" + galleryCardData + "] end");
            return galleryCardData;
        } catch (Exception e7) {
            e = e7;
            Log.bxe("StoryDataFetcher", e.getMessage());
            Log.bx("StoryDataFetcher", "getStoryDetails [" + galleryCardData + "] end");
            return galleryCardData;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getStoryData$0(QueryParams queryParams) {
        queryParams.setCreationTimeLimit(getTodayStartTime(), getTodayEndTime()).isShowLocal();
    }

    public GalleryCardData getStoryData(Context context) {
        StringBuilder sb2;
        Cursor query;
        Log.bx("StoryDataFetcher", "getStoryData start");
        GalleryCardData galleryCardData = null;
        try {
            query = DbCompat.query(DbKey.STORIES, new b(18, this));
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        galleryCardData = getStoryDetails(context, MediaItemLoader.load(query));
                        if (galleryCardData != null) {
                            break;
                        }
                    } while (!query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
            }
            sb2 = new StringBuilder("getStoryData end [");
        } catch (Error | Exception e) {
            try {
                Log.bxe("StoryDataFetcher", e.getMessage());
                sb2 = new StringBuilder("getStoryData end [");
            } catch (Throwable th) {
                Log.bx("StoryDataFetcher", "getStoryData end [" + null + "]");
                throw th;
            }
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        sb2.append(galleryCardData);
        sb2.append("]");
        Log.bx("StoryDataFetcher", sb2.toString());
        return galleryCardData;
        throw th;
    }
}
