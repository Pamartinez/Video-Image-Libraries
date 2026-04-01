package com.samsung.android.gallery.app.ui.list.stories.pictures.cover;

import A6.b;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IViewCache;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StorySlideShowCover<V extends IStoryPicturesView> extends StoryCover<V> {
    private final CoverSlideHandler<V> mCoverSlideHandler;

    public StorySlideShowCover(V v, IViewCache iViewCache) {
        super(v, iViewCache);
        CoverSlideHandler<V> coverSlideHandler = new CoverSlideHandler<>(v);
        this.mCoverSlideHandler = coverSlideHandler;
        coverSlideHandler.setOnMoveNextListener(new b(this, 0));
        Optional.ofNullable(getView().getAppbarLayout()).ifPresent(new b(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(GalleryAppBarLayout galleryAppBarLayout) {
        galleryAppBarLayout.setOnCoverVisibilityChangeListener(new b(this, 2));
    }

    /* access modifiers changed from: private */
    public void onCoverVisibilityChanged(boolean z) {
        if (!isDestroyed()) {
            if (z) {
                if (PreviewFactory.isPreviewableFormat(getCoverViewHolder().getMediaItem())) {
                    startPreview();
                }
                this.mCoverSlideHandler.startSlideShow();
            } else {
                stopPreview();
                this.mCoverSlideHandler.stopSlideShow();
            }
            C0212a.x("cover open state : ", "StorySlideShowCover", z);
        }
    }

    /* access modifiers changed from: private */
    public void onMoveNext(MediaItem mediaItem) {
        onBindView(mediaItem);
    }

    public void destroy() {
        super.destroy();
        this.mCoverSlideHandler.destroy();
    }

    public void destroyView() {
        super.destroyView();
        this.mCoverSlideHandler.stopSlideShow();
    }

    public int getCoverLayoutId() {
        return R.layout.recycler_item_story_slideshow_cover_layout;
    }

    public boolean isCustomCoverRatio() {
        if (this.mCoverSlideHandler.getHeaderDataIndex() < 0 || this.mCoverSlideHandler.getHeaderDataIndex() == this.mCoverSlideHandler.getCurrentSlideDataIndex()) {
            return true;
        }
        return false;
    }

    public void onDataChangedOnUi() {
        this.mCoverSlideHandler.startSlideShow();
    }

    public void pause() {
        this.mCoverSlideHandler.stopSlideShow();
    }

    public void resume() {
        GalleryAppBarLayout appbarLayout;
        super.resume();
        if (this.mView.isDataPrepared() && (appbarLayout = this.mView.getAppbarLayout()) != null && !appbarLayout.seslIsCollapsed()) {
            this.mCoverSlideHandler.startSlideShow();
        }
    }

    public void updateCoverInternal(MediaItem mediaItem) {
        if (!isDestroyed()) {
            this.mCoverSlideHandler.resetSlideShowDataInfo(true);
            onBindView(mediaItem);
        }
    }

    public void updateLayout() {
        this.mCoverSlideHandler.resetSlideShowDataInfo(false);
        super.updateLayout();
    }
}
