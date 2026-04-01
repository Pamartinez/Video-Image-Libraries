package com.samsung.android.gallery.app.ui.list.stories.category.category;

import U3.a;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.search.n;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.IStoriesCategoryView;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.ItemProperty;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.story.transitory.PlayableViewPagerDelegate;
import com.samsung.android.gallery.widget.story.transitory.ViewPagerItemFactory;
import com.samsung.android.gallery.widget.story.transitory.ViewPagerListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesCatTransitoryViewHolder extends StoriesCatBaseViewHolder implements ViewPagerListener {
    private ViewGroup mContent;
    protected float mContentRatio;
    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            PlayableViewPagerDelegate playableViewPagerDelegate;
            if (i2 == 0 && (playableViewPagerDelegate = StoriesCatTransitoryViewHolder.this.mViewPagerDelegate) != null) {
                RecyclerView recyclerView2 = playableViewPagerDelegate.getRecyclerView();
                if (recyclerView2 == null || !recyclerView2.isAttachedToWindow()) {
                    StoriesCatTransitoryViewHolder.this.mViewPagerDelegate.stop();
                } else if (!StoriesCatTransitoryViewHolder.this.mView.isViewResumed() || !ViewUtils.isInVisibleRange(StoriesCatTransitoryViewHolder.this.mViewPagerDelegate.getRecyclerView(), StoriesCatTransitoryViewHolder.this.mView.getListView(), 0.65f)) {
                    StoriesCatTransitoryViewHolder.this.mViewPagerDelegate.pause();
                } else {
                    StoriesCatTransitoryViewHolder.this.mViewPagerDelegate.resume();
                }
            }
        }
    };
    PlayableViewPagerDelegate mViewPagerDelegate;

    public StoriesCatTransitoryViewHolder(IStoriesCategoryView iStoriesCategoryView, View view, int i2) {
        super(iStoriesCategoryView, view, i2);
        this.mContentRatio = getContentRatio(view.getContext());
        setViewSize();
    }

    private void addScrollListenerToRootList() {
        Optional.ofNullable(this.mView.getListView()).ifPresent(new i(this, 1));
    }

    private float getContentRatio(Context context) {
        float dimensionPixelOffset;
        int dimensionPixelOffset2;
        Resources resources = context.getResources();
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_85) {
            dimensionPixelOffset = (float) resources.getDimensionPixelOffset(R.dimen.stories_category_transitory_content_height_v85);
            dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.stories_category_transitory_content_width_v85);
        } else if (!PreferenceFeatures.OneUi8x.STORY_ONE_UI_80) {
            return 1.0716981f;
        } else {
            dimensionPixelOffset = (float) resources.getDimensionPixelOffset(R.dimen.stories_category_transitory_content_height_v80);
            dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.stories_category_transitory_content_width_v80);
        }
        return dimensionPixelOffset / ((float) dimensionPixelOffset2);
    }

    private int getContentWidth(Context context, int i2) {
        int i7 = ItemProperty.getItemSize(context, "location://stories/category/transitory")[0];
        if (i2 <= 0 || i2 - i7 > 0) {
            return i7;
        }
        return i2 - 32;
    }

    private int getPageQueueMarginResId() {
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_85) {
            return R.dimen.stories_category_transitory_side_queue_margin_v85;
        }
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_80) {
            return R.dimen.stories_category_transitory_side_queue_margin_v80;
        }
        return R.dimen.stories_category_transitory_side_queue_margin;
    }

    private int[] getSizeInfo(Context context) {
        int widthSpace = getWidthSpace();
        int contentWidth = getContentWidth(context, widthSpace);
        int i2 = (widthSpace - contentWidth) / 2;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(getPageQueueMarginResId());
        int i7 = ItemProperty.getItemHorizontalMargin(context, "location://stories/category/transitory")[0];
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
        View view;
        IStoriesCategoryView iStoriesCategoryView = this.mView;
        View view2 = null;
        if (iStoriesCategoryView == null || iStoriesCategoryView.getAdapter() == null) {
            view = null;
        } else {
            view = this.mView.getAdapter().getHeaderView();
        }
        if (view == null || view.getWidth() == 0) {
            IStoriesCategoryView iStoriesCategoryView2 = this.mView;
            if (iStoriesCategoryView2 != null) {
                view2 = iStoriesCategoryView2.getListView();
            }
            view = view2;
        }
        if (view == null || view.getWidth() <= 0) {
            return ResourceCompat.getWindowWidth(this.itemView.getContext());
        }
        return (view.getWidth() - view.getPaddingStart()) - view.getPaddingEnd();
    }

    /* access modifiers changed from: private */
    public boolean isTouchable() {
        IStoriesCategoryView iStoriesCategoryView = this.mView;
        if (iStoriesCategoryView == null || iStoriesCategoryView.isSelectionMode()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addScrollListenerToRootList$0(GalleryListView galleryListView) {
        galleryListView.removeOnScrollListener(this.mScrollListener);
        galleryListView.addOnScrollListener(this.mScrollListener);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHandleInternalEvent$2(int i2) {
        if (this.itemView.getWidth() != i2) {
            setViewSize();
            this.mViewPagerDelegate.notifyItemRangeChanged(InternalEvent.UPDATE_EXTRA_PADDING.toString());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$removeScrollListenerToRootList$1(GalleryListView galleryListView) {
        galleryListView.removeOnScrollListener(this.mScrollListener);
    }

    private void removeScrollListenerToRootList() {
        Optional.ofNullable(this.mView.getListView()).ifPresent(new i(this, 0));
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

    public void bindData(MediaData mediaData, Bundle bundle) {
        Optional.ofNullable(this.mViewPagerDelegate).ifPresent(new n(0));
        super.bindData(mediaData, bundle);
        addScrollListenerToRootList();
        PlayableViewPagerDelegate playableViewPagerDelegate = this.mViewPagerDelegate;
        IStoriesCategoryView iStoriesCategoryView = this.mView;
        Objects.requireNonNull(iStoriesCategoryView);
        playableViewPagerDelegate.setItemClickLister(new a(6, iStoriesCategoryView));
        this.mViewPagerDelegate.bindData(mediaData, bundle, new ViewPagerItemFactory() {
            public ListViewHolder createItemViewHolder(ViewGroup viewGroup, int i2) {
                return StoriesCatItemViewHolderFactory.createTransitoryItemViewHolder(viewGroup, i2);
            }

            public int getItemViewType(MediaItem mediaItem, boolean z) {
                return StoriesCatItemViewHolderFactory.getTransitoryItemViewType(mediaItem, z);
            }
        });
    }

    public void bindView(View view) {
        super.bindView(view);
        initView(view);
        View view2 = this.mHeader;
        if (view2 != null) {
            view2.setVisibility(8);
        }
    }

    public void clear() {
        super.clear();
        removeScrollListenerToRootList();
        this.mViewPagerDelegate.clear();
    }

    public RecyclerView getListView() {
        return this.mViewPagerDelegate.getRecyclerView();
    }

    public ArrayList<PreviewViewHolder> getPlayableVH() {
        if (ViewUtils.isInVisibleRange(this.mViewPagerDelegate.getRecyclerView(), this.mView.getListView(), 0.65f)) {
            ListViewHolder previewableViewHolder = this.mViewPagerDelegate.getPreviewableViewHolder();
            if ((previewableViewHolder instanceof PreviewViewHolder) && StoryHelper.isVideoItem(previewableViewHolder.getMediaItem())) {
                return new ArrayList<>(Collections.singletonList((PreviewViewHolder) previewableViewHolder));
            }
        }
        return super.getPlayableVH();
    }

    public void initView(View view) {
        this.mContent = (ViewGroup) view.findViewById(R.id.transitory_content);
        PlayableViewPagerDelegate playableViewPagerDelegate = new PlayableViewPagerDelegate();
        this.mViewPagerDelegate = playableViewPagerDelegate;
        playableViewPagerDelegate.initView(this.mContent, new h(this));
        this.mViewPagerDelegate.setViewPagerListener(this);
    }

    public void invalidateLayout() {
        this.mContentRatio = getContentRatio(this.itemView.getContext());
        setViewSize();
        this.mViewPagerDelegate.invalidateLayout(this.mMediaData);
    }

    public boolean isPlayable() {
        if (!this.mView.isViewResumed() || !this.mView.isViewActive() || this.mView.isSelectionMode()) {
            return false;
        }
        return true;
    }

    public void onChildPageSelected(int i2, int i7, MediaItem mediaItem) {
        this.mView.requestPreview();
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        this.mViewPagerDelegate.onDataChangedOnUi(this.mMediaData);
    }

    public boolean onHandleInternalEvent(InternalEvent internalEvent, Object... objArr) {
        boolean z = false;
        if (internalEvent == InternalEvent.SHOW_SLIDESHOW) {
            slideshow(objArr[0].booleanValue());
            return true;
        }
        InternalEvent internalEvent2 = InternalEvent.UPDATE_EXTRA_PADDING;
        if (internalEvent == internalEvent2) {
            float floatValue = objArr[0].floatValue();
            if (floatValue == 0.0f || floatValue == 1.0f) {
                z = true;
            }
            slideshow(z);
            return true;
        } else if (internalEvent == InternalEvent.SELECT_MODE_CHANGE) {
            ViewUtils.setViewEnabled(this.itemView, !this.mView.isSelectionMode());
            slideshow(!this.mView.isSelectionMode());
            return true;
        } else if (internalEvent == InternalEvent.ON_HEADER_ATTACHED) {
            this.itemView.post(new j(this, this.itemView.getWidth()));
            return true;
        } else if (internalEvent == InternalEvent.HEADER_SIZE_CHANGED) {
            setViewSize();
            this.mViewPagerDelegate.notifyItemRangeChanged(internalEvent2.toString());
            return true;
        } else {
            if (internalEvent == InternalEvent.SET_CURRENT_ITEM) {
                this.mViewPagerDelegate.setCurrentItem(objArr[0].intValue(), objArr[1].booleanValue(), 0);
            } else {
                InternalEvent internalEvent3 = InternalEvent.REDUCED_TRANSPARENCY_CHANGED;
                if (internalEvent == internalEvent3) {
                    this.mViewPagerDelegate.notifyItemRangeChanged(internalEvent3.toString());
                    return true;
                }
                InternalEvent internalEvent4 = InternalEvent.ON_DATE_TIME_CHANGED;
                if (internalEvent == internalEvent4) {
                    this.mViewPagerDelegate.notifyItemRangeChanged(internalEvent4.toString());
                    return true;
                }
            }
            return super.onHandleInternalEvent(internalEvent, objArr);
        }
    }

    public void onPageSelected(int i2, int i7) {
        this.mView.requestPreview();
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesTopColorEffect)) {
            this.mView.internalEvent(InternalEvent.TRANSITORY_PAGE_SELECTED, Integer.valueOf(i2), this.mMediaData.readCache(i2));
        }
    }

    public void pause() {
        super.pause();
        this.mViewPagerDelegate.pause();
    }

    public void recycle() {
        super.recycle();
    }

    public void resume() {
        super.resume();
        this.mViewPagerDelegate.resume();
    }

    public void saveState(Bundle bundle) {
        super.saveState(bundle);
        PlayableViewPagerDelegate playableViewPagerDelegate = this.mViewPagerDelegate;
        if (playableViewPagerDelegate != null) {
            bundle.putInt(Message.KEY_POSITION, playableViewPagerDelegate.getCurrentItem());
        }
    }

    public void setViewSize() {
        setContentWidth();
        setPageViewInfo();
    }

    public void stop() {
        super.stop();
        this.mViewPagerDelegate.stop();
    }

    public void transformPage(View view, float f) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesTopColorEffect)) {
            this.mView.internalEvent(InternalEvent.TRANSITORY_TRANSFORM_PAGE, Integer.valueOf(((RecyclerView.LayoutParams) view.getLayoutParams()).getAbsoluteAdapterPosition()), Float.valueOf(f));
        }
    }

    public void updateBadge() {
        this.mViewPagerDelegate.updateBadge();
    }
}
