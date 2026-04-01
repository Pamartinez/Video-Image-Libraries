package com.samsung.android.gallery.app.ui.list.search;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.story.LaunchOnDemandStoryCmd;
import com.samsung.android.gallery.app.controller.story.SaveRecapVideoCmd;
import com.samsung.android.gallery.app.controller.story.SaveTransitoryToOthersCmd;
import com.samsung.android.gallery.app.controller.story.UpdateStoryFavoriteCmd;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StorySharedTransitionHelper;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.ItemProperty;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.StoryLauncher;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.story.ondemand.OnDemandStory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.story.transitory.PlayableViewPagerDelegate;
import com.samsung.android.gallery.widget.story.transitory.ViewPagerItemFactory;
import com.samsung.android.gallery.widget.story.transitory.ViewPagerListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CategoryStoriesTransitoryHolder extends ListViewHolder implements ViewPagerListener, View.OnApplyWindowInsetsListener {
    private ViewGroup mContent;
    protected float mContentRatio;
    private final AtomicInteger mLatestWidthSpec = new AtomicInteger();
    private final MediaData.SimpleDataChangeListener mListener = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0() {
            CategoryStoriesTransitoryHolder.this.onDataChangedOnUi();
        }

        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new m(1, this));
        }
    };
    private MediaData mMediaData;
    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (i2 == 0 && CategoryStoriesTransitoryHolder.this.mViewPagerDelegate != null) {
                RecyclerView recyclerView2 = CategoryStoriesTransitoryHolder.this.mViewPagerDelegate.getRecyclerView();
                if (recyclerView2 == null || !recyclerView2.isAttachedToWindow()) {
                    CategoryStoriesTransitoryHolder.this.mViewPagerDelegate.stop();
                } else if (!CategoryStoriesTransitoryHolder.this.mView.isViewResumed() || !ViewUtils.isInVisibleRange(recyclerView2, CategoryStoriesTransitoryHolder.this.mView.getListView(), 0.65f)) {
                    CategoryStoriesTransitoryHolder.this.mViewPagerDelegate.pause();
                } else {
                    CategoryStoriesTransitoryHolder.this.mViewPagerDelegate.resume();
                }
            }
        }
    };
    ISearchView mView;
    /* access modifiers changed from: private */
    public PlayableViewPagerDelegate mViewPagerDelegate;
    private ViewPagerListener mViewPagerListener;

    public CategoryStoriesTransitoryHolder(View view, int i2) {
        super(view, i2);
        this.mContentRatio = getContentRatio(view.getContext());
    }

    private int getContentWidth(Context context, int i2) {
        int i7 = ItemProperty.getItemSize(context, "location://search/fileList/Category/Stories/Transitory")[0];
        if (i2 <= 0 || i2 - i7 > 0) {
            return i7;
        }
        return i2 - 32;
    }

    private int[] getSizeInfo(Context context) {
        int widthSpace = getWidthSpace();
        int contentWidth = getContentWidth(context, widthSpace);
        int i2 = (widthSpace - contentWidth) / 2;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(getPageQueueMarginResId());
        int i7 = ItemProperty.getItemHorizontalMargin(context, "location://search/fileList/Category/Stories/Transitory")[0];
        int i8 = i7 - dimensionPixelSize;
        if (i2 < i7) {
            if (dimensionPixelSize > i8) {
                dimensionPixelSize = Math.max(i2 / 2, i2 - i8);
            } else {
                dimensionPixelSize = Math.min(i2 / 2, dimensionPixelSize);
            }
        }
        return new int[]{contentWidth, i2, dimensionPixelSize, i2 - i7};
    }

    private int getWidthSpace() {
        GalleryListView galleryListView;
        ISearchView iSearchView = this.mView;
        GalleryListView galleryListView2 = null;
        if (iSearchView == null || iSearchView.getAdapter() == null) {
            galleryListView = null;
        } else {
            galleryListView = this.mView.getListView();
        }
        if (galleryListView == null || galleryListView.getWidth() == 0) {
            ISearchView iSearchView2 = this.mView;
            if (iSearchView2 != null) {
                galleryListView2 = iSearchView2.getListView();
            }
            galleryListView = galleryListView2;
        }
        if (galleryListView == null || galleryListView.getWidth() <= 0) {
            return ResourceCompat.getWindowWidth(this.itemView.getContext());
        }
        return (galleryListView.getWidth() - galleryListView.getPaddingStart()) - galleryListView.getPaddingEnd();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onApplyWindowInsets$0() {
        invalidateLayout(false);
    }

    private void launchDemandStory(ListViewHolder listViewHolder) {
        Object[] objArr;
        if (this.mView != null && (objArr = (Object[]) listViewHolder.itemView.getTag()) != null) {
            Integer num = (Integer) objArr[0];
            num.intValue();
            listViewHolder.itemView.setTag((Object) null);
            new LaunchOnDemandStoryCmd().execute(this.mView.getPresenter(), (String) objArr[1], num);
        }
    }

    /* access modifiers changed from: private */
    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        Blackboard blackboard;
        ISearchView iSearchView = this.mView;
        if (iSearchView != null) {
            blackboard = iSearchView.getBlackboard();
        } else {
            blackboard = null;
        }
        if (blackboard != null) {
            if (i7 != 1002) {
                boolean z = false;
                if (i7 == 6) {
                    new UpdateStoryFavoriteCmd().addParameter("dataKey", "location://story/albums").execute(this.mView.getPresenter(), new MediaItem[]{mediaItem}, 0);
                } else if (i7 == 1003) {
                    launchDemandStory(listViewHolder);
                } else {
                    StoryLauncher data = new StoryLauncher(this.mView).setData(mediaItem, i2);
                    if (PreferenceFeatures.OneUi8x.SUPPORT_RECAP && MediaItemStory.isRecap(mediaItem)) {
                        z = true;
                    }
                    data.setRecap(z).setFromLocation("location://search/fileList/Category/Stories/Transitory").execute();
                    if (PocFeatures.RECAP_SHARED_TRANSITION) {
                        StorySharedTransitionHelper.addStoryAlbumTransition(this.mView.getBlackboard(), listViewHolder, listViewHolder.getMediaItem());
                    }
                }
            } else if (MediaItemStory.isRecap(mediaItem)) {
                new SaveRecapVideoCmd().execute(this.mView.getPresenter(), mediaItem, Boolean.FALSE);
            } else {
                new SaveTransitoryToOthersCmd().execute(this.mView.getPresenter(), mediaItem);
            }
        }
    }

    private void openMediaData(Blackboard blackboard) {
        if (this.mMediaData == null) {
            MediaData open = MediaDataFactory.getInstance(blackboard).open("location://search/fileList/Category/Stories/Transitory");
            this.mMediaData = open;
            open.register(this.mListener);
        }
    }

    private void registerListeners() {
        GalleryListView galleryListView;
        ISearchView iSearchView = this.mView;
        if (iSearchView != null) {
            galleryListView = iSearchView.getListView();
        } else {
            galleryListView = null;
        }
        if (galleryListView != null) {
            galleryListView.removeOnScrollListener(this.mScrollListener);
            galleryListView.addOnScrollListener(this.mScrollListener);
        }
        this.itemView.setOnApplyWindowInsetsListener(this);
    }

    private void setContentWidth() {
        ViewUtils.setWidth(this.mContent, -1);
    }

    private void setPageViewInfo() {
        int[] sizeInfo = getSizeInfo(this.itemView.getContext());
        this.mViewPagerDelegate.setPageViewInfo(sizeInfo[0], sizeInfo[1], sizeInfo[2], sizeInfo[3]);
    }

    private void slideshow(boolean z) {
        if (z) {
            this.mViewPagerDelegate.start();
        } else {
            this.mViewPagerDelegate.stop();
        }
    }

    private void unregister() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mListener);
            this.mMediaData = null;
        }
    }

    private void unregisterListener() {
        GalleryListView galleryListView;
        ISearchView iSearchView = this.mView;
        if (iSearchView != null) {
            galleryListView = iSearchView.getListView();
        } else {
            galleryListView = null;
        }
        if (galleryListView != null) {
            galleryListView.removeOnScrollListener(this.mScrollListener);
        }
        this.itemView.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
    }

    private void updateVisibility() {
        boolean z;
        int itemCount = this.mViewPagerDelegate.getItemCount();
        if (itemCount != -1) {
            ViewGroup viewGroup = this.mContent;
            if (itemCount > 0) {
                z = true;
            } else {
                z = false;
            }
            ViewUtils.setVisibleOrGone(viewGroup, z);
        }
    }

    private void updateVisibilityWithData() {
        MediaData mediaData;
        boolean z;
        if (!OnDemandStory.getInstance().isEnabled() && (mediaData = this.mMediaData) != null) {
            ViewGroup viewGroup = this.mContent;
            if (mediaData.getCount() > 0) {
                z = true;
            } else {
                z = false;
            }
            ViewUtils.setVisibleOrGone(viewGroup, z);
        }
    }

    public void bindData(ISearchView iSearchView, Bundle bundle) {
        this.mView = iSearchView;
        setViewSize();
        openMediaData(iSearchView.getBlackboard());
        Optional.ofNullable(this.mViewPagerDelegate).ifPresent(new n(0));
        registerListeners();
        updateVisibilityWithData();
        this.mViewPagerDelegate.setItemClickLister(new h(this, 2));
        this.mViewPagerDelegate.bindData(this.mMediaData, bundle, new ViewPagerItemFactory() {
            public ListViewHolder createItemViewHolder(ViewGroup viewGroup, int i2) {
                return StoriesCatItemViewHolderFactory.createTransitoryItemViewHolder(viewGroup, i2);
            }

            public int getItemViewType(MediaItem mediaItem, boolean z) {
                return StoriesCatItemViewHolderFactory.getTransitoryItemViewType(mediaItem, z);
            }
        });
    }

    public void bindView(View view) {
        initView(view.findViewById(R.id.transitory_container));
    }

    public void clear() {
        unregisterListener();
        this.mViewPagerDelegate.clear();
    }

    public void clearView() {
        this.mViewPagerDelegate.clearView();
    }

    public void destroy() {
        unregister();
        clear();
    }

    public float getContentRatio(Context context) {
        Resources resources = context.getResources();
        return ((float) resources.getDimensionPixelOffset(R.dimen.stories_category_transitory_content_height_v85)) / ((float) resources.getDimensionPixelOffset(R.dimen.stories_category_transitory_content_width_v85));
    }

    public int getCurrentItem() {
        PlayableViewPagerDelegate playableViewPagerDelegate = this.mViewPagerDelegate;
        if (playableViewPagerDelegate != null) {
            return playableViewPagerDelegate.getCurrentItem();
        }
        return -1;
    }

    public ListViewHolder getCurrentViewHolder() {
        PlayableViewPagerDelegate playableViewPagerDelegate = this.mViewPagerDelegate;
        if (playableViewPagerDelegate != null) {
            return playableViewPagerDelegate.getCurrentViewHolder();
        }
        return null;
    }

    public int getPageQueueMarginResId() {
        return R.dimen.stories_category_transitory_side_queue_margin_v85;
    }

    public PreviewViewHolder getPreviewableViewHolder(View view) {
        PlayableViewPagerDelegate playableViewPagerDelegate = this.mViewPagerDelegate;
        if (playableViewPagerDelegate != null && ViewUtils.isInVisibleRange(playableViewPagerDelegate.getRecyclerView(), view, 0.65f)) {
            ListViewHolder previewableViewHolder = this.mViewPagerDelegate.getPreviewableViewHolder();
            if (previewableViewHolder instanceof PreviewViewHolder) {
                return (PreviewViewHolder) previewableViewHolder;
            }
        }
        return null;
    }

    public void initView(View view) {
        this.mContent = (ViewGroup) view.findViewById(R.id.transitory_content);
        PlayableViewPagerDelegate playableViewPagerDelegate = new PlayableViewPagerDelegate();
        this.mViewPagerDelegate = playableViewPagerDelegate;
        playableViewPagerDelegate.initView(view, (Supplier<Boolean>) null);
        this.mViewPagerDelegate.setViewPagerListener(this);
        setPageViewInfo();
    }

    public void invalidateLayout() {
        invalidateLayout(true);
    }

    public boolean isPlayable() {
        ISearchView iSearchView = this.mView;
        if (iSearchView == null || !iSearchView.isViewResumed() || !this.mView.isViewActive() || this.mView.isSelectionMode()) {
            return false;
        }
        return true;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        this.itemView.post(new m(0, this));
        return windowInsets;
    }

    public void onChildPageSelected(int i2, int i7, MediaItem mediaItem) {
        ViewPagerListener viewPagerListener = this.mViewPagerListener;
        if (viewPagerListener != null) {
            viewPagerListener.onChildPageSelected(i2, i7, mediaItem);
        }
    }

    public void onDataChangedOnUi() {
        this.mViewPagerDelegate.onDataChangedOnUi(this.mMediaData);
        updateVisibility();
    }

    public boolean onHandleInternalEvent(String str, Object... objArr) {
        boolean z = false;
        if (InternalEvent.SHOW_SLIDESHOW.name().equals(str)) {
            slideshow(objArr[0].booleanValue());
            return true;
        } else if (InternalEvent.UPDATE_EXTRA_PADDING.name().equals(str)) {
            float floatValue = objArr[0].floatValue();
            if (floatValue == 0.0f || floatValue == 1.0f) {
                z = true;
            }
            slideshow(z);
            setViewSize();
            this.mViewPagerDelegate.notifyItemRangeChanged(str);
            return true;
        } else {
            if (InternalEvent.SET_CURRENT_ITEM.name().equals(str)) {
                this.mViewPagerDelegate.setCurrentItem(objArr[0].intValue(), objArr[1].booleanValue(), 0);
            } else {
                InternalEvent internalEvent = InternalEvent.REDUCED_TRANSPARENCY_CHANGED;
                if (internalEvent.name().equals(str)) {
                    this.mViewPagerDelegate.notifyItemRangeChanged(internalEvent.name());
                    return true;
                } else if (InternalEvent.UPDATE_BADGE.name().equals(str)) {
                    this.mViewPagerDelegate.notifyItemRangeChanged("PAYLOAD_UPDATE_BADGE");
                    return true;
                } else {
                    InternalEvent internalEvent2 = InternalEvent.ON_DATE_TIME_CHANGED;
                    if (internalEvent2.name().equals(str)) {
                        this.mViewPagerDelegate.notifyItemRangeChanged(internalEvent2.name());
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public void onPageSelected(int i2, int i7) {
        ViewPagerListener viewPagerListener = this.mViewPagerListener;
        if (viewPagerListener != null) {
            viewPagerListener.onPageSelected(i2, i7);
        }
    }

    public void pause() {
        this.mViewPagerDelegate.pause();
    }

    public void recycle() {
        super.recycle();
        unregister();
        this.mLatestWidthSpec.set(0);
    }

    public void resume() {
        this.mViewPagerDelegate.resume();
    }

    public void setViewPagerLister(ViewPagerListener viewPagerListener) {
        this.mViewPagerListener = viewPagerListener;
    }

    public void setViewSize() {
        setContentWidth();
        setPageViewInfo();
    }

    public void stop() {
        this.mViewPagerDelegate.stop();
    }

    public void transformPage(View view, float f) {
        ViewPagerListener viewPagerListener = this.mViewPagerListener;
        if (viewPagerListener != null) {
            viewPagerListener.transformPage(view, f);
        }
    }

    public void invalidateLayout(boolean z) {
        int width = this.itemView.getWidth();
        if (z || !this.mLatestWidthSpec.compareAndSet(width, width)) {
            this.mLatestWidthSpec.set(width);
            this.mContentRatio = getContentRatio(this.itemView.getContext());
            setViewSize();
            this.mViewPagerDelegate.invalidateLayout(this.mMediaData);
        }
    }
}
