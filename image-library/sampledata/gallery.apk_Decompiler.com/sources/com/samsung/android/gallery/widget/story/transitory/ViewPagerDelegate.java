package com.samsung.android.gallery.widget.story.transitory;

import A4.C0368c;
import W8.C0579a;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import bc.C0584a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.message.Message;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewPagerDelegate implements ViewPagerListener {
    public final String TAG = getClass().getSimpleName();
    protected StoriesViewPagerAdapter mAdapter;
    private ListViewHolder.OnItemClickListener mItemClickLister;
    private View mParent;
    protected StoriesViewPager mViewPager;
    protected ViewPagerListener mViewPagerListener;

    private int findFirstNewStoryIndex(MediaData mediaData) {
        if (mediaData != null) {
            ArrayList<MediaItem> allData = mediaData.getAllData();
            for (int i2 = 0; i2 < allData.size(); i2++) {
                if (StoryHelper.isNewStory(MediaItemStory.getStoryNotifyStatus(allData.get(i2)))) {
                    Log.d(this.TAG, "initial index", Integer.valueOf(i2));
                    return i2;
                }
            }
        }
        return 0;
    }

    private boolean handleUpdate(MediaData mediaData) {
        int i2;
        MediaItem mediaItem;
        ListViewHolder currentViewHolder = this.mViewPager.getCurrentViewHolder();
        if (currentViewHolder != null) {
            i2 = currentViewHolder.getAbsoluteAdapterPosition();
        } else {
            i2 = 0;
        }
        if (currentViewHolder != null) {
            mediaItem = currentViewHolder.getMediaItem();
        } else {
            mediaItem = null;
        }
        if (mediaItem == null || mediaData.getCount() == 0) {
            return false;
        }
        ArrayList<MediaItem> allData = mediaData.getAllData();
        int i7 = 0;
        while (true) {
            if (i7 >= allData.size()) {
                i7 = -1;
                break;
            } else if (MediaItemStory.getStoryId(mediaItem) == MediaItemStory.getStoryId(allData.get(i7))) {
                break;
            } else {
                i7++;
            }
        }
        if (i7 > -1) {
            int i8 = i7 - i2;
            if (i8 > 0) {
                for (int i10 = 0; i10 < i8; i10++) {
                    this.mAdapter.notifyItemInserted(i10);
                }
            } else {
                for (int i11 = 0; i11 < i2 - i7; i11++) {
                    this.mAdapter.notifyItemRemoved(i11);
                }
            }
            if (mediaItem.getFileId() != allData.get(i7).getFileId()) {
                this.mAdapter.notifyItemChanged(i7);
            }
        } else {
            i7 = Math.min(i2, allData.size() - 1);
            this.mAdapter.notifyItemChanged(i7);
            this.mViewPager.setCurrentItem(i7, -1);
        }
        this.mAdapter.notifyItemRangeChanged(0, i7);
        this.mAdapter.notifyItemRangeChanged(i7 + 1, (mediaData.getCount() - i7) - 1);
        ThreadUtil.runOnUiThread(new C0584a(3, this));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDataInternal$1(int i2) {
        this.mViewPager.setCurrentItem(i2, 0);
        this.mViewPager.refreshTransformer();
    }

    private void updateDecoView() {
        ListViewHolder currentViewHolder = this.mViewPager.getCurrentViewHolder();
        if (currentViewHolder != null) {
            currentViewHolder.handleEvent(1003, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void updateDecoViewSelf() {
        if (this.mViewPager != null) {
            updateDecoView();
        }
    }

    public void bindData(MediaData mediaData, Bundle bundle, ViewPagerItemFactory viewPagerItemFactory) {
        int i2;
        if (bundle != null) {
            i2 = bundle.getInt(Message.KEY_POSITION, -1);
        } else {
            i2 = -1;
        }
        if (this.mAdapter == null) {
            if (i2 == -1) {
                i2 = findFirstNewStoryIndex(mediaData);
            }
            initAdapter(viewPagerItemFactory);
        }
        this.mViewPager.setViewPagerCallback(this);
        this.mViewPager.registerOnPageChangeCallback();
        updateData(mediaData, i2);
        if (bundle != null) {
            bundle.remove(Message.KEY_POSITION);
        }
    }

    public void clear() {
        this.mViewPager.destroy();
        this.mViewPager.unregisterOnPageChangeCallback();
        this.mViewPager.setViewPagerCallback((ViewPagerListener) null);
    }

    public void clearView() {
        this.mViewPager.getRecyclerView().removeAllViews();
        this.mViewPager.getRecyclerView().getRecycledViewPool().clear();
    }

    public StoriesViewPagerAdapter createAdapter(ViewPagerItemFactory viewPagerItemFactory) {
        return new StoriesViewPagerAdapter(viewPagerItemFactory);
    }

    public abstract StoriesViewPager createViewPager(View view);

    public int getCurrentItem() {
        return this.mViewPager.getCurrentItem();
    }

    public ListViewHolder getCurrentViewHolder() {
        return this.mViewPager.getCurrentViewHolder();
    }

    public int getItemCount() {
        StoriesViewPagerAdapter storiesViewPagerAdapter = this.mAdapter;
        if (storiesViewPagerAdapter != null) {
            return storiesViewPagerAdapter.getItemCount();
        }
        return -1;
    }

    public ListViewHolder getPreviewableViewHolder() {
        return this.mViewPager.getPreviewableViewHolder();
    }

    public RecyclerView getRecyclerView() {
        StoriesViewPager storiesViewPager = this.mViewPager;
        if (storiesViewPager != null) {
            return storiesViewPager.getRecyclerView();
        }
        return null;
    }

    public void initAdapter(ViewPagerItemFactory viewPagerItemFactory) {
        StoriesViewPagerAdapter createAdapter = createAdapter(viewPagerItemFactory);
        this.mAdapter = createAdapter;
        createAdapter.setOnItemClickListener(this.mItemClickLister);
        this.mAdapter.setSupportFooter(supportFooter());
        this.mViewPager.setAdapter(this.mAdapter);
        this.mViewPager.initAccessibilityState();
    }

    public void initView(View view, Supplier<Boolean> supplier) {
        this.mParent = view;
        StoriesViewPager createViewPager = createViewPager(view);
        this.mViewPager = createViewPager;
        createViewPager.setTouchable(supplier);
    }

    public void invalidateLayout(MediaData mediaData) {
        int currentItem = this.mViewPager.getCurrentItem();
        ViewUtils.removeAllViews(this.mViewPager.getRecyclerView());
        this.mViewPager.getRecyclerView().getRecycledViewPool().clear();
        updateData(mediaData, currentItem);
    }

    public boolean isPlayable() {
        return false;
    }

    public void notifyItemRangeChanged(String str) {
        this.mViewPager.notifyItemRangeChanged(str);
    }

    public void onDataChangedOnUi(MediaData mediaData) {
        updateData(mediaData, -1);
    }

    public void onPageSelected(int i2, int i7) {
        updateDecoView();
        ViewPagerListener viewPagerListener = this.mViewPagerListener;
        if (viewPagerListener != null) {
            viewPagerListener.onPageSelected(i2, i7);
        }
    }

    public void setCurrentItem(int i2, boolean z, int i7) {
        this.mViewPager.setCurrentItem(i2, z, i7);
    }

    public void setItemClickLister(ListViewHolder.OnItemClickListener onItemClickListener) {
        this.mItemClickLister = onItemClickListener;
    }

    public void setPageViewInfo(int i2, int i7, int i8, int i10) {
        this.mViewPager.setPageViewInfo(i2, i7, i8, i10);
    }

    public void setViewPagerListener(ViewPagerListener viewPagerListener) {
        this.mViewPagerListener = viewPagerListener;
    }

    public boolean supportFooter() {
        return false;
    }

    public void transformPage(View view, float f) {
        ViewPagerListener viewPagerListener = this.mViewPagerListener;
        if (viewPagerListener != null) {
            viewPagerListener.transformPage(view, f);
        }
    }

    public void updateBadge() {
        Optional.ofNullable(this.mAdapter).ifPresent(new C0579a(22));
    }

    public void updateData(MediaData mediaData, int i2) {
        updateDataInternal(mediaData, i2);
    }

    public void updateDataInternal(MediaData mediaData, int i2) {
        if (this.mAdapter != null) {
            if (mediaData != null && !handleUpdate(mediaData)) {
                this.mAdapter.setData(mediaData);
                if (i2 <= -1 || i2 >= mediaData.getCount()) {
                    if (mediaData.getCount() > 0) {
                        i2 = 0;
                    } else {
                        i2 = -1;
                    }
                }
                ThreadUtil.postOnUiThread(new C0368c(this, i2, 20));
                updateDecoView();
            }
            this.mAdapter.invalidateData();
        }
    }
}
