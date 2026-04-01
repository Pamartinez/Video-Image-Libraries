package com.samsung.android.gallery.widget.filmstrip3;

import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripSnapHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BitmapPreLoaderByFilmSnap implements FilmStripSnapHelper.OnFindTargetSnapPosition {
    final Blackboard mBlackboard;
    private final LruCache<String, String> mLru = new LruCache<String, String>(3) {
        public void entryRemoved(boolean z, String str, String str2, String str3) {
            BitmapPreLoaderByFilmSnap.this.removedCache(str);
        }
    };
    final FilmStripView mView;

    public BitmapPreLoaderByFilmSnap(Blackboard blackboard, FilmStripView filmStripView) {
        this.mBlackboard = blackboard;
        this.mView = filmStripView;
    }

    /* access modifiers changed from: private */
    public void removedCache(String str) {
        if (!TextUtils.isEmpty(str) && !this.mBlackboard.isEmpty(str) && !this.mLru.containsKey(str)) {
            BlackboardUtils.cancelAndEraseViewerBitmap(this.mBlackboard, str);
        }
    }

    public void destroy() {
        if (this.mLru.size() > 0) {
            this.mLru.evictAll();
        }
    }

    public void onFindTargetSnapPosition(int i2) {
        MediaItem mediaItem;
        String requestViewerBitmap;
        FilmStripAdapter filmStripAdapter = (FilmStripAdapter) this.mView.getAdapter();
        if (i2 > -1 && filmStripAdapter != null && (mediaItem = filmStripAdapter.getMediaItem(i2)) != null && mediaItem.isImage() && (requestViewerBitmap = BlackboardUtils.requestViewerBitmap(this.mBlackboard, mediaItem, false)) != null) {
            this.mLru.put(requestViewerBitmap, requestViewerBitmap);
        }
    }
}
