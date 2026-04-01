package com.samsung.android.gallery.app.remote.v2;

import A4.Q;
import B8.e;
import R6.c;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PresentationViewPagerAdapter extends RecyclerView.Adapter<PresentationViewPagerHolder> {
    private final LruCache<Integer, MediaItem> mCache = new LruCache<>(7);
    private int mCount = -1;
    private MediaData mMediaData;

    public PresentationViewPagerAdapter(Context context, MediaData mediaData) {
        this.mMediaData = mediaData;
        setHasStableIds(true);
    }

    private MediaItem getItem(int i2) {
        MediaItem mediaItem = this.mCache.get(Integer.valueOf(i2));
        if (mediaItem == null && (mediaItem = this.mMediaData.read(i2)) != null) {
            this.mCache.put(Integer.valueOf(i2), mediaItem);
        }
        return mediaItem;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadOrDecode$0(PresentationViewPagerHolder presentationViewPagerHolder, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        onBindImage(presentationViewPagerHolder, bitmap, mediaItem);
    }

    private void loadOrDecode(PresentationViewPagerHolder presentationViewPagerHolder, MediaItem mediaItem) {
        if (presentationViewPagerHolder != null) {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            ThumbKind thumbKind = ThumbKind.FREE_KIND;
            Bitmap memCache = instance.getMemCache(mediaItem, thumbKind);
            if (memCache != null) {
                onBindImage(presentationViewPagerHolder, memCache, mediaItem);
                return;
            }
            ThumbnailLoader instance2 = ThumbnailLoader.getInstance();
            Objects.requireNonNull(mediaItem);
            instance2.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new Q((Object) this, (Object) presentationViewPagerHolder, (Object) mediaItem, 13));
        }
    }

    private void onBindImage(PresentationViewPagerHolder presentationViewPagerHolder, Bitmap bitmap, MediaItem mediaItem) {
        ThreadUtil.runOnUiThread(new c(presentationViewPagerHolder, bitmap, mediaItem, 27));
    }

    public void clearCache() {
        this.mCache.evictAll();
        this.mCount = -1;
    }

    public int getDataPosition(int i2) {
        int count = this.mMediaData.getCount();
        if (count == 0) {
            return 0;
        }
        return i2 % count;
    }

    public int getItemCount() {
        if (this.mCount < 0) {
            this.mCount = this.mMediaData.getCount();
        }
        return this.mCount;
    }

    public long getItemId(int i2) {
        int dataPosition = getDataPosition(i2);
        MediaItem item = getItem(dataPosition);
        if (item != null) {
            return item.getFileId();
        }
        return super.getItemId(dataPosition);
    }

    public int getItemViewType(int i2) {
        MediaItem item = getItem(i2);
        if (item == null) {
            return 10;
        }
        if (item.isVideo()) {
            if (PreferenceFeatures.Remote.PLAY_VIDEO_IN_PRESENTATION) {
                return 20;
            }
            return 21;
        } else if (item.isImage()) {
            return 1;
        } else {
            return 10;
        }
    }

    public PresentationViewPagerHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new PresentationViewPagerHolder(C0086a.d(viewGroup, R.layout.mirroring_presentation_v2, viewGroup, false), i2);
    }

    public void onBindViewHolder(PresentationViewPagerHolder presentationViewPagerHolder, int i2) {
        loadOrDecode(presentationViewPagerHolder, getItem(getDataPosition(i2)));
    }

    public void onViewRecycled(PresentationViewPagerHolder presentationViewPagerHolder) {
        presentationViewPagerHolder.recycle();
        super.onViewRecycled(presentationViewPagerHolder);
    }
}
