package com.samsung.android.gallery.app.ui.list.stories.pictures.legacy;

import A4.O;
import A6.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesBaseView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryHeaderViewListener;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryPicturesBasePresenter;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryPicturesBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryPicturesLegacyCoverViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryPicturesLegacyHeaderViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryPicturesLegacyViewHolderFactory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryPicturesLegacyFragment<V extends IStoryPicturesBaseView, P extends StoryPicturesBasePresenter> extends PicturesFragment<V, P> implements IStoryPicturesBaseView {
    private static final ThumbKind HEADER_THUMB_KIND = ThumbKind.STORY_COVER;
    private StoryPicturesLegacyCoverViewHolder mCoverViewHolder;
    private StoryPicturesLegacyHeaderViewHolder mHeaderViewHolder;

    private View createCoverView() {
        MediaItem headerItem = ((StoryPicturesBasePresenter) this.mPresenter).getHeaderItem();
        StoryPicturesLegacyCoverViewHolder coverViewHolder = getCoverViewHolder();
        View rootView = coverViewHolder.getRootView();
        MediaItem mediaItem = coverViewHolder.getMediaItem();
        if (mediaItem != null && ((StoryPicturesBasePresenter) this.mPresenter).equalsItem(mediaItem, headerItem)) {
            Log.e(this.TAG, "createHeaderView MediaItem already bound");
            return rootView;
        } else if (headerItem == null) {
            coverViewHolder.setTransitionName();
            startEnlargeAnimation();
            return rootView;
        } else {
            coverViewHolder.bind(headerItem);
            ThumbKind thumbKind = HEADER_THUMB_KIND;
            coverViewHolder.setThumbKind(thumbKind);
            Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(headerItem, thumbKind);
            if (memCache != null) {
                lambda$onThumbnailLoadCompleted$0(memCache, (UniqueKey) null, thumbKind);
                return rootView;
            }
            ThumbnailLoader.getInstance().loadThumbnail(headerItem, thumbKind, new O(17, this));
            return rootView;
        }
    }

    private StoryPicturesLegacyCoverViewHolder getCoverViewHolder() {
        if (this.mCoverViewHolder == null) {
            StoryPicturesLegacyCoverViewHolder storyPicturesLegacyCoverViewHolder = new StoryPicturesLegacyCoverViewHolder(loadOrCreateView(R.layout.recycler_item_image_title_duration_layout_for_cover), 0);
            this.mCoverViewHolder = storyPicturesLegacyCoverViewHolder;
            storyPicturesLegacyCoverViewHolder.setListener((StoryHeaderViewListener) this.mPresenter);
        }
        return this.mCoverViewHolder;
    }

    private StoryPicturesLegacyHeaderViewHolder getHeaderViewHolder() {
        if (this.mHeaderViewHolder == null) {
            StoryPicturesLegacyHeaderViewHolder storyPicturesLegacyHeaderViewHolder = new StoryPicturesLegacyHeaderViewHolder(loadOrCreateView(R.layout.recycler_item_story_pictures_header_layout_legacy), 0);
            this.mHeaderViewHolder = storyPicturesLegacyHeaderViewHolder;
            storyPicturesLegacyHeaderViewHolder.setListener((StoryHeaderViewListener) this.mPresenter);
            MediaItem headerItem = ((StoryPicturesBasePresenter) this.mPresenter).getHeaderItem();
            if (headerItem != null) {
                this.mHeaderViewHolder.updateDuration(getContext(), headerItem);
                this.mHeaderViewHolder.bind(headerItem);
            } else {
                Log.d(this.TAG, "header item is not ready yet");
            }
        }
        return this.mHeaderViewHolder;
    }

    private void initCoverView() {
        if (getAppbarLayout() != null) {
            getAppbarLayout().setCoverView(createCoverView());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onThumbnailLoadCompleted */
    public void lambda$onThumbnailLoadCompleted$0(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        if (isDestroyed()) {
            Log.e(this.TAG, "onThumbnailLoaderCompleted destroyed");
        } else if (ThreadUtil.isMainThread()) {
            getCoverViewHolder().bindImage(bitmap);
            startEnlargeAnimation();
        } else {
            ThreadUtil.postOnUiThread(new a((Object) this, (Object) bitmap, (Object) uniqueKey, (Object) thumbKind, 4));
        }
    }

    private void startEnlargeAnimation() {
        startPostponedEnterTransition();
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new PicturesViewDummyAdapter(getListView(), getColumnCount()) {
            public PicturesViewHolderFactory createViewHolderFactory(Context context) {
                return new StoryPicturesLegacyViewHolderFactory(context);
            }
        };
    }

    public MediaItem getHeaderItem() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((StoryPicturesBasePresenter) p6).getHeaderItem();
        }
        return null;
    }

    public int getLayoutId() {
        return R.layout.fragment_story_pictures_layout_legacy;
    }

    public int getPreferenceDefault() {
        return GridHelper.STORY_PICTURES_DEFAULT_DEPTH;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.STORY_PICTURES_GRID_SIZE;
    }

    public String getScreenId() {
        if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_EVENT_DETAIL_VIEW_SELECTION.toString();
        }
        return AnalyticsScreenId.SCREEN_EVENT_DETAIL_VIEW_NORMAL.toString();
    }

    public ArrayList<View> getSharedViewList(Blackboard blackboard) {
        return (ArrayList) blackboard.pop("data://shared_view/story");
    }

    public int getStartPinchDepth() {
        return loadPinchDepth();
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        StoryPicturesLegacyCoverViewHolder storyPicturesLegacyCoverViewHolder = this.mCoverViewHolder;
        if (storyPicturesLegacyCoverViewHolder != null) {
            storyPicturesLegacyCoverViewHolder.setListener((StoryHeaderViewListener) null);
            this.mCoverViewHolder = null;
            initCoverView();
        }
        setEnabledHeader(!isSelectionMode());
        getLayoutManager().setDisplayCutLeft(0);
        getLayoutManager().setDisplayCutRight(0);
        getListView().setScrollerExtraPadding(0);
    }

    public void initView(View view) {
        super.initView(view);
        initCoverView();
    }

    public void loadArguments(Bundle bundle) {
        super.loadArguments(bundle);
    }

    public int[] loadPinchColumnResource() {
        return getResources().getIntArray(R.array.story_pictures_legacy_column_count);
    }

    public boolean onBackPressed() {
        SharedTransition.setReturnPosition(this.mBlackboard, ArgumentsUtil.getArgValue(getLocationKey(), Message.KEY_POSITION, -1));
        return super.onBackPressed();
    }

    public void onDestroy() {
        super.onDestroy();
        StoryPicturesLegacyCoverViewHolder storyPicturesLegacyCoverViewHolder = this.mCoverViewHolder;
        if (storyPicturesLegacyCoverViewHolder != null) {
            storyPicturesLegacyCoverViewHolder.recycle();
            restoreLayout(R.layout.recycler_item_image_title_duration_layout_for_cover, this.mCoverViewHolder.getRootView());
            this.mCoverViewHolder = null;
        }
        StoryPicturesLegacyHeaderViewHolder storyPicturesLegacyHeaderViewHolder = this.mHeaderViewHolder;
        if (storyPicturesLegacyHeaderViewHolder != null) {
            storyPicturesLegacyHeaderViewHolder.recycle();
            restoreLayout(R.layout.recycler_item_story_pictures_header_layout_legacy, this.mHeaderViewHolder.getRootView());
            this.mHeaderViewHolder = null;
        }
    }

    public void onEnterTransitionEndV2() {
        super.onEnterTransitionEndV2();
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((StoryPicturesBasePresenter) p6).onTransitionEnd();
        }
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            savePinchDepth(i2);
            super.onGridChanged(i2, i7);
        }
    }

    public void setEnabledHeader(boolean z) {
        StoryPicturesLegacyCoverViewHolder storyPicturesLegacyCoverViewHolder = this.mCoverViewHolder;
        if (storyPicturesLegacyCoverViewHolder != null) {
            storyPicturesLegacyCoverViewHolder.setEnabledCover(z);
        }
        StoryPicturesLegacyHeaderViewHolder storyPicturesLegacyHeaderViewHolder = this.mHeaderViewHolder;
        if (storyPicturesLegacyHeaderViewHolder != null) {
            storyPicturesLegacyHeaderViewHolder.setEnabledHeader(z);
        }
    }

    public boolean supportTimeline() {
        return true;
    }

    public void updateHeader(MediaItem mediaItem) {
        if (isDestroyed()) {
            Log.e(this.TAG, "Fragment is already detached");
            return;
        }
        StoryPicturesLegacyCoverViewHolder coverViewHolder = getCoverViewHolder();
        coverViewHolder.bind(mediaItem);
        ThumbKind thumbKind = HEADER_THUMB_KIND;
        coverViewHolder.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            lambda$onThumbnailLoadCompleted$0(memCache, (UniqueKey) null, thumbKind);
        } else {
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new O(17, this));
        }
        getHeaderViewHolder().updateDuration(getContext(), mediaItem);
        getHeaderViewHolder().bind(mediaItem);
    }

    public StoryPicturesBaseViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new StoryPicturesLegacyViewAdapter(this, getLocationKey(), getHeaderViewHolder().getRootView(), isRealRatioSupported());
    }

    public StoryPicturesBasePresenter createPresenter(IStoryPicturesBaseView iStoryPicturesBaseView) {
        return new StoryPicturesLegacyPresenter(this.mBlackboard, iStoryPicturesBaseView);
    }

    public void updateHeaderContents(MediaItem mediaItem) {
    }
}
