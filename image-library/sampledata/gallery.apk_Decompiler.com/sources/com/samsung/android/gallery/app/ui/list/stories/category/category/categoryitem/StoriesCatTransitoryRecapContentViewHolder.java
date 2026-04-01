package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import a6.g;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.story.transitory.IRecapContent;
import com.samsung.android.gallery.widget.story.transitory.PlayableViewPagerDelegate;
import com.samsung.android.gallery.widget.story.transitory.RecapEventListener;
import com.samsung.android.gallery.widget.story.transitory.RecapViewPagerDelegate;
import com.samsung.android.gallery.widget.story.transitory.ViewPagerItemFactory;
import com.samsung.android.gallery.widget.story.transitory.ViewPagerListener;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesCatTransitoryRecapContentViewHolder extends ListViewHolder implements ViewPagerListener, IRecapContent {
    private ViewGroup mContent;
    protected float mContentRatio;
    private int mContentWidth = -1;
    private RecapEventListener mListener;
    private MediaData mMediaData;
    private RecapViewPagerDelegate mViewPagerDelegate;

    public StoriesCatTransitoryRecapContentViewHolder(View view, int i2) {
        super(view, i2);
        view.setTag("recap");
        this.mContentRatio = getContentRatio(view.getContext());
        setViewSize();
    }

    private void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.close();
            this.mMediaData = null;
        }
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

    /* access modifiers changed from: private */
    public boolean isTouchable() {
        return true;
    }

    /* access modifiers changed from: private */
    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ListViewHolder.OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(listViewHolder, i2, mediaItem, i7);
        }
    }

    private MediaData openMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData == null) {
            return MediaDataFactory.getInstance(Blackboard.getInstance(this.itemView.getContext().toString())).open("location://stories/category/Recap");
        }
        return mediaData;
    }

    private void setPageViewInfo() {
        this.mViewPagerDelegate.setPageViewInfo(this.mContentWidth, 0, 0, 0);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        bindData(openMediaData(), (Bundle) null);
    }

    public void bindData(MediaData mediaData, Bundle bundle) {
        this.mMediaData = mediaData;
        this.mViewPagerDelegate.setItemClickLister(new b(this));
        this.mViewPagerDelegate.bindData(mediaData, bundle, new ViewPagerItemFactory() {
            public ListViewHolder createItemViewHolder(ViewGroup viewGroup, int i2) {
                return StoriesCatItemViewHolderFactory.createRecapItemViewHolder(viewGroup, i2);
            }

            public int getItemViewType(MediaItem mediaItem, boolean z) {
                return StoriesCatItemViewHolderFactory.getRecapContentItemViewType(mediaItem);
            }
        });
    }

    public void bindView(View view) {
        initView(view);
    }

    public ListViewHolder getPreviewableViewHolder() {
        return this.mViewPagerDelegate.getPreviewableViewHolder();
    }

    public void handleEvent(int i2, Object... objArr) {
        if (i2 == 1000) {
            this.mViewPagerDelegate.setParentDataCount(objArr[0].intValue());
        }
    }

    public void initView(View view) {
        this.mContent = (ViewGroup) view.findViewById(R.id.recap_content);
        RecapViewPagerDelegate recapViewPagerDelegate = new RecapViewPagerDelegate();
        this.mViewPagerDelegate = recapViewPagerDelegate;
        recapViewPagerDelegate.initView(this.mContent, new c(this));
        this.mViewPagerDelegate.setViewPagerListener(this);
    }

    public boolean moveToNext() {
        int currentItem = this.mViewPagerDelegate.getCurrentItem() + 1;
        if (currentItem < 0 || currentItem > this.mViewPagerDelegate.getItemCount() - 1) {
            return false;
        }
        this.mViewPagerDelegate.setCurrentItem(currentItem, true, 0);
        return true;
    }

    public void onPageSelected(int i2, int i7) {
        MediaItem mediaItem;
        if (this.mListener != null) {
            ListViewHolder currentViewHolder = this.mViewPagerDelegate.getCurrentViewHolder();
            RecapEventListener recapEventListener = this.mListener;
            if (currentViewHolder != null) {
                mediaItem = currentViewHolder.getMediaItem();
            } else {
                mediaItem = null;
            }
            ((PlayableViewPagerDelegate) ((g) recapEventListener).e).lambda$initAdapter$0(i2, i7, mediaItem);
        }
    }

    public void recycle() {
        super.recycle();
        closeMediaData();
    }

    public void setContentWidth(int i2) {
        this.mContentWidth = i2;
        setViewSize();
    }

    public void setRecapEventListener(RecapEventListener recapEventListener) {
        this.mListener = recapEventListener;
    }

    public void setViewSize() {
        setPageViewInfo();
    }

    public void transformPage(View view, float f) {
    }
}
