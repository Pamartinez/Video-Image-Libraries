package com.samsung.android.gallery.app.ui.list.stories.pictures.cover;

import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CoverSlideHandler<V extends IStoryPicturesView> {
    private final AtomicBoolean mFirstPlay = new AtomicBoolean(true);
    private final AtomicInteger mHeaderDataIndex = new AtomicInteger(-1);
    private Consumer<MediaItem> mOnMoveNextListener;
    /* access modifiers changed from: private */
    public final AtomicInteger mSlideDataIndex = new AtomicInteger(-1);
    private Timer mSlideShowTimer;
    /* access modifiers changed from: private */
    public final AtomicInteger mSlideTimerCount = new AtomicInteger(0);
    private V mView;

    public CoverSlideHandler(V v) {
        this.mView = v;
    }

    private int getCoverIndex() {
        MediaItem mediaItem;
        int i2;
        V v = this.mView;
        if (v != null) {
            mediaItem = v.getHeaderItem();
        } else {
            mediaItem = null;
        }
        if (mediaItem == null) {
            return -1;
        }
        MediaData mediaData = this.mView.getMediaData((String) null);
        if (mediaData != null) {
            i2 = mediaData.getCount();
        } else {
            i2 = 0;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            MediaItem read = mediaData.read(i7);
            if (read != null && read.getFileId() == mediaItem.getFileId()) {
                return i7;
            }
        }
        return -1;
    }

    private MediaItem getMediaItem(int i2) {
        if (this.mView == null) {
            return null;
        }
        int headerDataIndex = getHeaderDataIndex();
        V v = this.mView;
        if (i2 == headerDataIndex) {
            return v.getHeaderItem();
        }
        return v.getMediaData((String) null).read(i2);
    }

    private int getVideoDelay(MediaItem mediaItem) {
        int i2;
        if (mediaItem != null) {
            i2 = Math.max(mediaItem.getFileDuration() / 1000, 1);
        } else {
            i2 = 5;
        }
        return Math.min(i2, 5);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$operateSlideShow$0(int i2) {
        this.mOnMoveNextListener.accept(getMediaItem(i2));
    }

    private boolean needUpdateNextScene(MediaItem mediaItem, int i2) {
        boolean z;
        if (!PreviewFactory.isPreviewableFormat(mediaItem) || mediaItem.isImage()) {
            z = true;
        } else {
            z = false;
        }
        if ((i2 != 1 || !this.mFirstPlay.getAndSet(false)) && ((!z || i2 != 3) && (z || i2 != getVideoDelay(mediaItem)))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public int operateSlideShow(int i2) {
        int currentSlideDataIndex;
        if (this.mView == null || (currentSlideDataIndex = getCurrentSlideDataIndex()) < 0) {
            return -1;
        }
        preloadBitmap(getNextSlideDataIndex());
        MediaItem mediaItem = getMediaItem(currentSlideDataIndex);
        if (mediaItem == null || !needUpdateNextScene(mediaItem, i2)) {
            return -1;
        }
        int nextSlideDataIndex = getNextSlideDataIndex();
        setCurrentSlideDataIndex(nextSlideDataIndex);
        ThreadUtil.postOnUiThread(new a(this, nextSlideDataIndex));
        return nextSlideDataIndex;
    }

    private void preloadBitmap(int i2) {
        ThumbKind thumbKind;
        MediaItem mediaItem = getMediaItem(i2);
        if (mediaItem != null) {
            if (!mediaItem.isHeif()) {
                thumbKind = ThumbKind.STORY_COVER;
            } else {
                thumbKind = ThumbKind.MEDIUM_KIND;
            }
            if (ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind) == null) {
                ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new b(i2));
            }
        }
    }

    public void destroy() {
        stopSlideShow();
        this.mView = null;
    }

    public int getCurrentSlideDataIndex() {
        return this.mSlideDataIndex.get();
    }

    public int getHeaderDataIndex() {
        return this.mHeaderDataIndex.get();
    }

    public int getNextSlideDataIndex() {
        int i2;
        V v = this.mView;
        if (v == null) {
            return 0;
        }
        MediaData mediaData = v.getMediaData((String) null);
        if (mediaData != null) {
            i2 = mediaData.getCount();
        } else {
            i2 = 0;
        }
        if (this.mSlideDataIndex.get() + 1 >= i2) {
            return 0;
        }
        return this.mSlideDataIndex.get() + 1;
    }

    public void resetSlideShowDataInfo(boolean z) {
        if (z) {
            this.mHeaderDataIndex.set(getCoverIndex());
        }
        this.mSlideTimerCount.set(0);
        this.mSlideDataIndex.set(this.mHeaderDataIndex.get());
    }

    public void setCurrentSlideDataIndex(int i2) {
        this.mSlideDataIndex.set(i2);
    }

    public void setOnMoveNextListener(Consumer<MediaItem> consumer) {
        this.mOnMoveNextListener = consumer;
    }

    public void startSlideShow() {
        if (this.mSlideShowTimer == null) {
            Timer timer = new Timer();
            this.mSlideShowTimer = timer;
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if (CoverSlideHandler.this.mSlideDataIndex.get() == -1) {
                        CoverSlideHandler.this.resetSlideShowDataInfo(true);
                    }
                    CoverSlideHandler coverSlideHandler = CoverSlideHandler.this;
                    int e = coverSlideHandler.operateSlideShow(coverSlideHandler.mSlideTimerCount.getAndIncrement());
                    if (e >= 0) {
                        CoverSlideHandler.this.mSlideTimerCount.set(1);
                        Log.d("CoverSlideHandler", "moveNext=" + e);
                    }
                }
            }, 0, 1000);
        }
    }

    public void stopSlideShow() {
        Timer timer = this.mSlideShowTimer;
        if (timer != null) {
            timer.cancel();
        }
        this.mSlideShowTimer = null;
    }
}
