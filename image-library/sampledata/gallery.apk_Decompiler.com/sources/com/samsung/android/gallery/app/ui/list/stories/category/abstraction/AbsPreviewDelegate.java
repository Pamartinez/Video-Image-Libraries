package com.samsung.android.gallery.app.ui.list.stories.category.abstraction;

import O3.o;
import androidx.core.util.Supplier;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.videopreview.VideoPreviewHandler;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.Timer;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsPreviewDelegate implements VideoPreviewHandler.VideoPreviewProvider {
    private PlayInfo mCurrent;
    private final VideoPreviewHandler mHandler;
    protected GalleryListView mList;
    private Supplier<Boolean> mPlayable;
    private final List<Integer> mPlayedList = Collections.synchronizedList(new ArrayList());
    private final List<PreviewViewHolder> mQueue = Collections.synchronizedList(new ArrayList());
    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            AbsPreviewDelegate.this.onScrollStateChangedInternal(recyclerView, i2);
        }
    };
    private final int mTimerId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PlayInfo {
        MediaItem item;
        int storyId;
        PreviewViewHolder vh;

        public PlayInfo(PreviewViewHolder previewViewHolder) {
            this.vh = previewViewHolder;
            MediaItem mediaItem = previewViewHolder.getMediaItem();
            this.item = mediaItem;
            this.storyId = MediaItemStory.getStoryId(mediaItem);
        }

        public boolean equals(Object obj) {
            if (obj instanceof PlayInfo) {
                PlayInfo playInfo = (PlayInfo) obj;
                if (playInfo.storyId == this.storyId && this.vh == playInfo.vh) {
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    public AbsPreviewDelegate(int i2) {
        this.mTimerId = i2;
        VideoPreviewHandler videoPreviewHandler = new VideoPreviewHandler(this, false);
        this.mHandler = videoPreviewHandler;
        videoPreviewHandler.setPreviewDelay(60);
    }

    private boolean blockPreview() {
        Supplier<Boolean> supplier = this.mPlayable;
        if (supplier == null || supplier.get().booleanValue()) {
            return false;
        }
        return true;
    }

    private void invalidatePlayedList() {
        this.mPlayedList.retainAll((Collection) this.mQueue.stream().map(new o(29)).collect(Collectors.toList()));
    }

    /* access modifiers changed from: private */
    public void onScrollStateChangedInternal(RecyclerView recyclerView, int i2) {
        if (i2 == 0) {
            requestPreview();
        }
    }

    private void resetList() {
        GalleryListView galleryListView = this.mList;
        if (galleryListView != null) {
            galleryListView.removeOnScrollListener(this.mScrollListener);
        }
    }

    private void shiftCurrentToTop() {
        PlayInfo playInfo = this.mCurrent;
        if (playInfo != null && this.mQueue.contains(playInfo.vh)) {
            this.mQueue.remove(this.mCurrent.vh);
            this.mQueue.add(0, this.mCurrent.vh);
        }
    }

    private void shiftPlayedList(List<Integer> list) {
        invalidatePlayedList();
        ArrayList arrayList = new ArrayList();
        for (Integer intValue : list) {
            int intValue2 = intValue.intValue();
            Iterator<PreviewViewHolder> it = this.mQueue.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PreviewViewHolder next = it.next();
                if (intValue2 == MediaItemStory.getStoryId(next.getMediaItem())) {
                    arrayList.add(next);
                    break;
                }
            }
        }
        if (this.mQueue.size() != arrayList.size()) {
            this.mQueue.removeAll(arrayList);
            this.mQueue.addAll(arrayList);
            return;
        }
        this.mQueue.clear();
        this.mQueue.addAll(arrayList);
        this.mPlayedList.clear();
    }

    public void attach(GalleryListView galleryListView) {
        resetList();
        this.mList = galleryListView;
        galleryListView.addOnScrollListener(this.mScrollListener);
    }

    public void clear() {
        this.mQueue.clear();
        resetCurrent();
    }

    public void destroy() {
        this.mHandler.destroy();
        resetList();
    }

    public abstract PreviewViewHolder findHighPriorityPreviewHolder();

    public String getFilterPath() {
        return "";
    }

    public PreviewViewHolder getVideoPreviewHolder() {
        this.mQueue.clear();
        makeCandidates();
        PreviewViewHolder findHighPriorityPreviewHolder = findHighPriorityPreviewHolder();
        if (findHighPriorityPreviewHolder != null) {
            return findHighPriorityPreviewHolder;
        }
        if (!this.mQueue.isEmpty()) {
            return this.mQueue.get(0);
        }
        return null;
    }

    public void makeCandidates(RecyclerView recyclerView, List<PreviewViewHolder> list) {
    }

    public void onComplete(PreviewViewHolder previewViewHolder) {
        resetCurrent();
        requestPreview();
    }

    public void onStart(PreviewViewHolder previewViewHolder) {
        Timer.stopTimer(this.mTimerId);
        this.mCurrent = new PlayInfo(previewViewHolder);
        onStartInternal(previewViewHolder);
    }

    public void pause() {
        this.mHandler.stopVideoPreview();
        Timer.stopTimer(this.mTimerId);
    }

    public void requestPreview() {
        if (!blockPreview()) {
            this.mHandler.requestVideoPreview();
        }
    }

    public void resetCurrent() {
        this.mCurrent = null;
    }

    public void resume() {
        if (!blockPreview()) {
            requestPreview();
        }
    }

    public void setPlayable(Supplier<Boolean> supplier) {
        this.mPlayable = supplier;
    }

    public final void setPlayedList(MediaItem mediaItem) {
        int storyId = MediaItemStory.getStoryId(mediaItem);
        if (!this.mPlayedList.contains(Integer.valueOf(storyId))) {
            this.mPlayedList.add(Integer.valueOf(storyId));
        }
    }

    public void stop() {
        this.mHandler.stopVideoPreview();
    }

    public void makeCandidates() {
        makeCandidates(this.mList, this.mQueue);
        shiftPlayedList(this.mPlayedList);
        shiftCurrentToTop();
    }

    public void onStartInternal(PreviewViewHolder previewViewHolder) {
    }
}
