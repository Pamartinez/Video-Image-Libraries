package com.samsung.android.gallery.widget.listview.pinch.v3;

import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.ViewPoolInjector;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FakeViewParent extends FrameLayout {
    private final PinchLayoutManager mLayoutManager;
    private AnimPositionCache mPositionCache;
    private RecyclerView.RecycledViewPool mRecycledViewPool;

    public FakeViewParent(IPinchRecyclerView iPinchRecyclerView, PinchLayoutManager pinchLayoutManager, AnimPositionCache animPositionCache) {
        super(iPinchRecyclerView.getContext());
        this.mLayoutManager = pinchLayoutManager;
        this.mPositionCache = animPositionCache;
        setClipChildren(false);
        setClipToPadding(false);
        ((ViewGroup) iPinchRecyclerView.getParent()).addView(this, 0);
        setVisibility(4);
    }

    private void attachHeaderViewFromRecyclerView(ListViewHolder listViewHolder) {
        if (this.mLayoutManager.getHeaderView() != null) {
            ViewUtils.removeSelf(this.mLayoutManager.getHeaderView());
            ((ViewGroup) listViewHolder.getRootView()).addView(this.mLayoutManager.getHeaderView(), 0);
        }
    }

    private ListViewHolder createFakeViewHolderInternal(int i2, int i7) {
        ListViewHolder listViewHolder = (ListViewHolder) this.mRecycledViewPool.getRecycledView(i7);
        if (!PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL) {
            if (listViewHolder == null && i7 == 0) {
                listViewHolder = (ListViewHolder) this.mRecycledViewPool.getRecycledView(3);
            }
            if (listViewHolder == null && i7 == 3) {
                listViewHolder = (ListViewHolder) this.mRecycledViewPool.getRecycledView(0);
            }
        }
        if (listViewHolder == null) {
            listViewHolder = this.mLayoutManager.createHintListViewHolder(this, i7, i2);
        }
        if (needToAttachHeaderView(i7)) {
            attachHeaderViewFromRecyclerView(listViewHolder);
        }
        return listViewHolder;
    }

    private ListViewHolder createViewHolder(int i2, int i7) {
        ListViewHolder createFakeViewHolderInternal = createFakeViewHolderInternal(i2, i7);
        View rootView = createFakeViewHolderInternal.getRootView();
        removeIfHasParent(rootView, createFakeViewHolderInternal.getViewType());
        addView(rootView);
        return createFakeViewHolderInternal;
    }

    private int getRealGridSize(int i2) {
        return GridValue.revert(i2);
    }

    private boolean isGridData(int i2) {
        if (i2 == 2) {
            return true;
        }
        return false;
    }

    private boolean isYearData(int i2) {
        if (i2 == 4) {
            return true;
        }
        return false;
    }

    private boolean needToAttachHeaderView(int i2) {
        if (!ViewHolderValue.isHeader(i2) || this.mLayoutManager.findFirstVisibleItemPosition() <= 0) {
            return false;
        }
        return true;
    }

    private void putRecycledViewPool(ListViewHolder listViewHolder) {
        ViewPoolInjector.inject(this.mRecycledViewPool, listViewHolder, listViewHolder.getViewType());
    }

    private void removeIfHasParent(View view, int i2) {
        if (view.getParent() != null) {
            Log.e("FakeViewParent", "invalid parent - remove from parent : " + view.getParent() + " , " + i2);
            ((ViewGroup) view.getParent()).removeView(view);
            new InternalException("createFakeViewHolderInternal - invalid parent").post();
        }
    }

    public ListViewHolder createFakeViewHolder(PinchItem pinchItem, int i2, int i7) {
        int i8;
        Integer viewType = pinchItem.getViewType();
        if (viewType == null) {
            viewType = 0;
            new InternalException("createFakeViewHolder - null view type").post();
        }
        ListViewHolder createViewHolder = createViewHolder(i2, viewType.intValue());
        View rootView = createViewHolder.getRootView();
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        if (ViewHolderValue.isData(viewType.intValue())) {
            RectF rect = pinchItem.getRect(i2);
            if (rect != null) {
                int i10 = i7 * 2;
                layoutParams.width = Math.min(this.mLayoutManager.getHintWidthSpace(i2), ((int) rect.width()) + i10);
                layoutParams.height = ((int) rect.height()) + i10;
            } else if (isGridData(viewType.intValue())) {
                layoutParams.width = this.mLayoutManager.getHintWidthSpace(i2) / getRealGridSize(i2);
                layoutParams.height = -2;
            } else {
                int viewPosition = pinchItem.getViewPosition(i2);
                int hintSpanCount = this.mPositionCache.getHintSpanCount(this.mLayoutManager, i2);
                int hintColumnSpan = this.mPositionCache.getHintColumnSpan(this.mLayoutManager, viewPosition, i2);
                int hintViewHeight = this.mPositionCache.getHintViewHeight(this.mLayoutManager, viewPosition, i2);
                if (isYearData(viewType.intValue())) {
                    i8 = this.mLayoutManager.getHintWidthSpace(i2);
                } else {
                    i8 = (int) (((((float) this.mLayoutManager.getHintWidthSpace(i2)) / ((float) hintSpanCount)) * ((float) hintColumnSpan)) + 0.5f);
                }
                layoutParams.width = i8;
                layoutParams.height = hintViewHeight;
            }
        } else if (ViewHolderValue.isDivider(viewType.intValue())) {
            layoutParams.width = this.mLayoutManager.getHintWidthSpace(i2);
        } else if (ViewHolderValue.isHeader(viewType.intValue())) {
            layoutParams.width = this.mLayoutManager.getHeaderWidth(i2);
        }
        rootView.setLayoutParams(layoutParams);
        return createViewHolder;
    }

    public void recycleHolder(ListViewHolder listViewHolder) {
        if (listViewHolder != null) {
            ViewUtils.removeSelf(listViewHolder.getRootView());
            listViewHolder.recycle();
            putRecycledViewPool(listViewHolder);
        }
    }

    public void setRecyclerViewPool(RecyclerView.RecycledViewPool recycledViewPool) {
        this.mRecycledViewPool = recycledViewPool;
    }
}
