package com.samsung.android.gallery.app.ui.list.stories.pictures.cover;

import A4.C0384t;
import A6.c;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IViewCache;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryCoverViewPagerHolder;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.gallery.widget.simpleslideshow.ISimpleSlideShowAdapter;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryViewPagerCover<V extends IStoryPicturesView> extends StoryCover<V> {
    private ISimpleSlideShowAdapter mAdapter;
    private StoryViewPager mViewPager;

    public StoryViewPagerCover(V v, IViewCache iViewCache) {
        super(v, iViewCache);
        Optional.ofNullable(getView().getAppbarLayout()).ifPresent(new c(this, 0));
    }

    private int getCoverDataPosition() {
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

    private void initViewPager() {
        this.mAdapter = new StoryViewPagerAdapter();
        StoryViewPager storyViewPager = new StoryViewPager((ViewGroup) getRootView().findViewById(R.id.viewpager_layout));
        this.mViewPager = storyViewPager;
        storyViewPager.setPreviewableConsumer(new c(this, 1));
        this.mViewPager.setAdapter(this.mAdapter);
    }

    private boolean isAppbarCollapsed() {
        return ((Boolean) Optional.ofNullable(getView().getAppbarLayout()).map(new C0384t(15)).orElse(Boolean.FALSE)).booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(GalleryAppBarLayout galleryAppBarLayout) {
        galleryAppBarLayout.setOnCoverVisibilityChangeListener(new c(this, 2));
    }

    private boolean needUpdate(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2.getFileId() != mediaItem.getFileId() || !TextUtils.equals(MediaItemStory.getStoryCoverRectRatio(mediaItem2), MediaItemStory.getStoryCoverRectRatio(mediaItem)) || mediaItem2.getCount() != mediaItem.getCount()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onCoverVisibilityChanged(boolean z) {
        if (!isDestroyed()) {
            if (!z) {
                this.mViewPager.pause();
            } else if (this.mViewPager.isRunning()) {
                this.mViewPager.resume();
            } else {
                this.mViewPager.start(true);
            }
            stopPreview();
            Log.d("StoryViewPagerCover", "cover open state : " + z);
        }
    }

    /* access modifiers changed from: private */
    public void requestPreview(MediaItem mediaItem) {
        if (PreviewFactory.isPreviewableFormat(mediaItem) && this.mViewPager.isRunning()) {
            startPreview();
        }
    }

    private void updateData() {
        int i2;
        if (this.mView != null) {
            StoryViewPagerAdapter storyViewPagerAdapter = (StoryViewPagerAdapter) this.mAdapter;
            MediaItem coverItem = storyViewPagerAdapter.getCoverItem();
            MediaItem headerItem = this.mView.getHeaderItem();
            if (headerItem == null) {
                Log.e("StoryViewPagerCover", "header item null");
            } else if (needUpdate(coverItem, headerItem)) {
                int coverDataPosition = getCoverDataPosition();
                if (coverDataPosition < 0) {
                    MediaData mediaData = this.mView.getMediaData((String) null);
                    if (mediaData != null) {
                        i2 = mediaData.getCount();
                    } else {
                        i2 = 0;
                    }
                    if (i2 > 0) {
                        MediaItem read = mediaData.read(0);
                        Log.e("StoryViewPagerCover", "header is not found, first item is used for header");
                        headerItem = read;
                        coverDataPosition = 0;
                    } else {
                        Log.e("StoryViewPagerCover", "updateData failed, dataPosition < 0");
                        return;
                    }
                }
                storyViewPagerAdapter.setEntryDataPosition(coverDataPosition);
                storyViewPagerAdapter.setCoverItem(headerItem);
                storyViewPagerAdapter.setData(this.mView.getMediaData((String) null));
                if (!isAppbarCollapsed()) {
                    this.mViewPager.start(true);
                }
            }
        }
    }

    public void destroy() {
        super.destroy();
        this.mViewPager.destroy();
    }

    public int getCoverLayoutId() {
        return R.layout.recycler_item_story_slideshow_cover_layout;
    }

    public PreviewViewHolder getCoverViewHolder() {
        if (this.mCoverViewHolder == null) {
            this.mCoverViewHolder = new StoryCoverViewPagerHolder(inflateCoverView(), 0);
        }
        return this.mCoverViewHolder;
    }

    public PreviewViewHolder getPreviewableViewHolder() {
        if (!this.mViewPager.isRunning()) {
            return null;
        }
        return (PreviewViewHolder) this.mViewPager.getPreviewableViewHolder();
    }

    public void onDataChangedOnUi() {
        updateData();
    }

    public void onViewCreated(View view, MediaItem mediaItem) {
        super.onViewCreated(view, mediaItem);
        MediaItem mediaItem2 = getCoverViewHolder().getMediaItem();
        if (this.mViewPager == null || !isEquals(mediaItem, mediaItem2)) {
            initViewPager();
        }
    }

    public void pause() {
        this.mViewPager.stop();
    }

    public void resume() {
        if (!this.mViewPager.isRunning()) {
            this.mViewPager.start(!isAppbarCollapsed());
        }
    }

    public void startPreview() {
        if (!getView().isDestroyed()) {
            getView().getPresenter().checkPreviewCandidate();
        }
    }

    public void stopPreview() {
        if (!getView().isDestroyed()) {
            getView().getPresenter().stopAllPreview(false);
        }
    }

    public void updateCoverInternal(MediaItem mediaItem) {
        super.updateCoverInternal(mediaItem);
        updateData();
    }

    public void updateLayout() {
        setCoverViewCustomHeight(getRootView());
        this.mViewPager.start(!isAppbarCollapsed());
    }
}
