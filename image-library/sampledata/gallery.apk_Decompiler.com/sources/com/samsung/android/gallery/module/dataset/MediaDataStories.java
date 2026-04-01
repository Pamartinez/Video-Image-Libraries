package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.abstraction.StoryCoverData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.StoriesPinCache;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataStories extends MediaDataStoriesBase {
    public MediaDataStories(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private void convertToCollageItem(MediaItem mediaItem) {
        MediaItemStory.setStoryOriginCoverData(mediaItem, new StoryCoverData(mediaItem));
        mediaItem.setPath(MediaItemStory.getStoryCollagePath(mediaItem));
        mediaItem.setOrientation(0);
        BitmapOptions parse = BitmapOptionsFactory.parse(mediaItem.getPath());
        mediaItem.setSize(parse.outWidth, parse.outHeight);
        if (mediaItem.isVideo()) {
            mediaItem.setMediaType(MediaType.Image);
        }
    }

    public boolean compare(MediaItem mediaItem, MediaItem mediaItem2) {
        boolean z;
        if (!super.compare(mediaItem, mediaItem2) || mediaItem.getCount() != mediaItem2.getCount()) {
            z = false;
        } else {
            z = true;
        }
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            if (!z || mediaItem.getDateModified() != mediaItem2.getDateModified()) {
                z = false;
            } else {
                z = true;
            }
        }
        if (!PreferenceFeatures.OneUi40.SUPPORT_STORIES_PIN) {
            return z;
        }
        if (!z || MediaItemStory.getStoryFavorite(mediaItem) != MediaItemStory.getStoryFavorite(mediaItem2)) {
            return false;
        }
        return true;
    }

    public void preprocessCursor(Cursor cursor, boolean z) {
        if (!z) {
            return;
        }
        if ((PreferenceFeatures.OneUi40.SUPPORT_STORIES_PIN || PreferenceFeatures.OneUi40.SUPPORT_STORIES_REMINDER) && this.mLock.acquireReadLock("readPinData")) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                StoriesPinCache.getInstance().load(cursor);
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "process pin data=" + (System.currentTimeMillis() - currentTimeMillis));
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                this.mLock.releaseReadLock("readPinData");
                throw th;
            }
            this.mLock.releaseReadLock("readPinData");
        }
    }

    public void updateItem(Map<Integer, MediaItem> map, Map<Integer, MediaItem> map2, int i2, MediaItem mediaItem) {
        if (MediaItemStory.isCollageStory(mediaItem)) {
            if (FileUtils.exists(MediaItemStory.getStoryCollagePath(mediaItem))) {
                convertToCollageItem(mediaItem);
            } else {
                MediaItemStory.setStoryCollagePath(mediaItem, "");
            }
        }
        super.updateItem(map, map2, i2, mediaItem);
    }
}
