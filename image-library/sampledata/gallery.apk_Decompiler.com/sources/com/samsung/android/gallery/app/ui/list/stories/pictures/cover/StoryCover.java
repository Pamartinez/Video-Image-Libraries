package com.samsung.android.gallery.app.ui.list.stories.pictures.cover;

import A4.O;
import A6.a;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IViewCache;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryCoverViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.sec.android.gallery3d.R;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryCover<V extends IStoryPicturesView> {
    private ListViewHolder.OnItemClickListener mCoverClickListener;
    protected PreviewViewHolder mCoverViewHolder;
    private final AtomicBoolean mPreviewChecking = new AtomicBoolean(false);
    protected V mView;
    private final IViewCache mViewCache;

    public StoryCover(V v, IViewCache iViewCache) {
        this.mView = v;
        this.mViewCache = iViewCache;
    }

    private boolean supportStoryThumbnailKind(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isHeif()) {
            return false;
        }
        return true;
    }

    public void bindThumbnail(MediaItem mediaItem, ListViewHolder listViewHolder) {
        ThumbKind coverThumbKind = getCoverThumbKind(mediaItem);
        listViewHolder.setThumbKind(coverThumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, coverThumbKind);
        if (memCache != null) {
            lambda$onThumbnailLoadCompleted$0(memCache, (UniqueKey) null, coverThumbKind);
        } else {
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, coverThumbKind, new O(6, this));
        }
    }

    public void cache() {
        PreviewViewHolder previewViewHolder = this.mCoverViewHolder;
        if (previewViewHolder != null) {
            previewViewHolder.recycleToBackup();
            this.mCoverViewHolder.recycle();
            this.mViewCache.restore(getCoverLayoutId(), this.mCoverViewHolder.getRootView());
        }
    }

    public void destroy() {
        cache();
        this.mView = null;
        this.mCoverClickListener = null;
    }

    public int getCoverLayoutId() {
        return R.layout.recycler_item_story_pictures_cover_layout;
    }

    public ThumbKind getCoverThumbKind(MediaItem mediaItem) {
        if (supportStoryThumbnailKind(mediaItem)) {
            return ThumbKind.STORY_COVER;
        }
        return ThumbKind.MEDIUM_KIND;
    }

    public PreviewViewHolder getCoverViewHolder() {
        if (this.mCoverViewHolder == null) {
            this.mCoverViewHolder = new StoryCoverViewHolder(inflateCoverView(), 0);
        }
        return this.mCoverViewHolder;
    }

    public PreviewViewHolder getPreviewableViewHolder() {
        return getCoverViewHolder();
    }

    public final ViewGroup getRootView() {
        return (ViewGroup) getCoverViewHolder().getDecoView(58);
    }

    public final V getView() {
        return this.mView;
    }

    public final void handleDensityChange(int i2) {
        updateLayout();
    }

    public final void handleResolutionChange(Configuration configuration) {
        GalleryAppBarLayout appbarLayout = this.mView.getAppbarLayout();
        if (appbarLayout != null) {
            appbarLayout.dispatchConfigurationChanged(configuration);
        }
        updateLayout();
    }

    public final View inflateCoverView() {
        return this.mViewCache.loadOrCreate(getCoverLayoutId());
    }

    public void initCoverView() {
        GalleryAppBarLayout appbarLayout = this.mView.getAppbarLayout();
        if (appbarLayout != null && !isDestroyed()) {
            PreviewViewHolder coverViewHolder = getCoverViewHolder();
            View rootView = coverViewHolder.getRootView();
            MediaItem headerItem = getView().getHeaderItem();
            onViewCreated(rootView, headerItem);
            appbarLayout.setCoverView(rootView);
            coverViewHolder.setOnItemClickListener(this.mCoverClickListener);
            if (headerItem == null) {
                Log.e("StoryCover", "header is not exist");
            }
            if (!isEquals(headerItem, coverViewHolder.getMediaItem())) {
                onBindView(headerItem);
            }
        }
    }

    public boolean isCustomCoverRatio() {
        return true;
    }

    public final boolean isDestroyed() {
        if (this.mView == null) {
            return true;
        }
        return false;
    }

    public final boolean isEquals(MediaItem mediaItem, MediaItem mediaItem2) {
        V v = this.mView;
        if (v == null || !v.isEqualItem(mediaItem, mediaItem2)) {
            return false;
        }
        return true;
    }

    public void onBindView(MediaItem mediaItem) {
        StoryCoverViewHolder storyCoverViewHolder = (StoryCoverViewHolder) getCoverViewHolder();
        if (mediaItem != null && !isDestroyed()) {
            storyCoverViewHolder.recycle();
            storyCoverViewHolder.setUseCustomCoverRatio(isCustomCoverRatio());
            storyCoverViewHolder.bind(mediaItem);
            storyCoverViewHolder.setOnItemClickListener(this.mCoverClickListener);
            if (PreviewFactory.isPreviewableFormat(mediaItem)) {
                startPreview();
            } else {
                stopPreview();
            }
            bindThumbnail(mediaItem, storyCoverViewHolder);
        }
    }

    /* renamed from: onThumbnailLoadCompleted */
    public void lambda$onThumbnailLoadCompleted$0(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        if (isDestroyed()) {
            Log.e("StoryCover", "onThumbnailLoaderCompleted destroyed");
            return;
        }
        PreviewViewHolder coverViewHolder = getCoverViewHolder();
        if (ThreadUtil.isMainThread()) {
            coverViewHolder.bindImage(bitmap);
        } else {
            ThreadUtil.postOnUiThread(new a((Object) this, (Object) bitmap, (Object) uniqueKey, (Object) thumbKind, 0));
        }
    }

    public void onViewCreated(View view, MediaItem mediaItem) {
        setCoverViewCustomHeight(getRootView());
    }

    public void setCoverClickListener(ListViewHolder.OnItemClickListener onItemClickListener) {
        this.mCoverClickListener = onItemClickListener;
        PreviewViewHolder previewViewHolder = this.mCoverViewHolder;
        if (previewViewHolder != null) {
            previewViewHolder.setOnItemClickListener(onItemClickListener);
        }
    }

    public void setCoverViewCustomHeight(View view) {
        if (view != null && this.mView.getAppbarLayout() != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = this.mView.getAppbarLayout().getLayoutParams().height;
            view.setLayoutParams(layoutParams);
        }
    }

    public void startPreview() {
        if (!this.mPreviewChecking.getAndSet(true)) {
            getView().getPresenter().checkPreviewCandidate();
        }
    }

    public void stopPreview() {
        if (this.mPreviewChecking.getAndSet(false)) {
            getView().getPresenter().stopAllPreview(false);
        }
    }

    public final void updateCover(MediaItem mediaItem) {
        if (isEquals(mediaItem, getCoverViewHolder().getMediaItem())) {
            Log.d("StoryCover", "same items");
            return;
        }
        setCoverViewCustomHeight((ViewGroup) getCoverViewHolder().getDecoView(58));
        updateCoverInternal(mediaItem);
    }

    public void updateCoverInternal(MediaItem mediaItem) {
        onBindView(mediaItem);
    }

    public void updateLayout() {
        PreviewViewHolder previewViewHolder = this.mCoverViewHolder;
        if (previewViewHolder != null) {
            previewViewHolder.recycleToBackup();
            this.mCoverViewHolder.recycle();
            this.mViewCache.restore(getCoverLayoutId(), this.mCoverViewHolder.getRootView());
            this.mCoverViewHolder = null;
            initCoverView();
        }
    }

    public void destroyView() {
    }

    public void onDataChangedOnUi() {
    }

    public void pause() {
    }

    public void resume() {
    }
}
