package com.samsung.android.gallery.app.ui.list.stories;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IPinView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesView;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesLayoutManager<V extends IStoriesView> extends GalleryGridLayoutManager {
    private boolean mDrawerStateDirty = false;
    private int mDrawerWidth = -1;
    private int[] mItemPadding;
    private float mProgress = 0.0f;
    RecyclerView mRecyclerView;
    private int mSideSpacing;
    private V mView;

    public StoriesLayoutManager(V v, RecyclerView recyclerView, int i2) {
        super(recyclerView.getContext(), i2);
        this.mView = v;
        this.mRecyclerView = recyclerView;
        initDimen(recyclerView.getContext());
    }

    private int calculateSideSpacing(int i2) {
        if (!DrawerUtil.supportDrawerLayout(this.mRecyclerView.getContext()) || !this.mDrawerStateDirty) {
            return i2;
        }
        this.mDrawerStateDirty = false;
        this.mItemPadding = getItemPadding(this.mRecyclerView.getContext());
        return getDrawerClosedSideSpacing() - ((int) (((float) (getDrawerClosedSideSpacing() - getDrawerOpenedSideSpacing(this.mDrawerWidth))) * this.mProgress));
    }

    private int getDimen(Resources resources, int i2) {
        return resources.getDimensionPixelOffset(i2);
    }

    private int getDrawerClosedSideSpacing() {
        return getSideSpacing(false);
    }

    private int getDrawerOpenedSideSpacing(int i2) {
        return (-((getWidth() - i2) - (getSpanCount() * getItemWidth(this.mRecyclerView.getResources(), true)))) / 2;
    }

    private int getItemBottomPadding(Resources resources, boolean z) {
        int i2;
        if (z) {
            i2 = R.dimen.stories_view_recycler_item_bottom_padding_drawer;
        } else {
            i2 = R.dimen.stories_view_recycler_item_bottom_padding;
        }
        return getDimen(resources, i2);
    }

    private int[] getItemPadding(Context context) {
        int itemSidePadding = getItemSidePadding(context.getResources(), false);
        int itemSidePadding2 = (int) (((float) itemSidePadding) - (this.mProgress * ((float) (itemSidePadding - getItemSidePadding(context.getResources(), true)))));
        int itemBottomPadding = getItemBottomPadding(context.getResources(), false);
        return new int[]{itemSidePadding2, 0, itemSidePadding2, (int) (((float) itemBottomPadding) - (this.mProgress * ((float) (itemBottomPadding - getItemBottomPadding(context.getResources(), true)))))};
    }

    private int getItemSidePadding(Resources resources, boolean z) {
        int i2;
        if (z) {
            i2 = R.dimen.stories_view_recycler_item_side_padding_drawer;
        } else {
            i2 = R.dimen.stories_view_recycler_item_side_padding;
        }
        return getDimen(resources, i2);
    }

    private int getItemWidth(Resources resources, boolean z) {
        float itemWidthRatio = getItemWidthRatio(resources, z);
        return (getItemSidePadding(resources, z) * 2) + ((int) (((float) ResourceCompat.getCoarseWindowWidth(resources)) * itemWidthRatio));
    }

    private float getItemWidthRatio(Resources resources, boolean z) {
        int i2;
        if (z) {
            i2 = R.dimen.stories_item_width_ratio_drawer;
        } else {
            i2 = R.dimen.stories_item_width_ratio;
        }
        return getRatioDimen(resources, i2);
    }

    private float getRatioDimen(Resources resources, int i2) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i2, typedValue, true);
        return typedValue.getFloat();
    }

    private int getSideSpacing(boolean z) {
        Resources resources = this.mRecyclerView.getResources();
        return (-(ResourceCompat.getCoarseWindowWidth(resources) - (getSpanCount() * getItemWidth(resources, z)))) / 2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateViewSize$0(int i2, IPinView iPinView) {
        int widthSpace = getWidthSpace() / getSpanCount();
        int[] iArr = this.mItemPadding;
        int i7 = iArr[2];
        int i8 = (int) (((float) (widthSpace - (i7 * 2))) * 0.0148f);
        int i10 = -i2;
        iPinView.setPaddingForAlignBaseList(iArr[0] + i10 + i8, i10 + i7 + i8);
    }

    private void setClip(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
        }
    }

    private void updateViewSize(View view) {
        ListViewHolder listViewHolder = (ListViewHolder) this.mRecyclerView.findContainingViewHolder(view);
        if (listViewHolder != null) {
            updateViewSize(listViewHolder, view);
        }
    }

    public void addView(View view, int i2) {
        super.addView(view, i2);
        updateViewSize(view);
    }

    public int getHintPaddingLeft(int i2) {
        int i7;
        if (isLayoutRTL()) {
            i7 = 0;
        } else {
            i7 = getExtraStartPadding(i2);
        }
        return i7 - getSpacing(i2);
    }

    public int getPaddingRight() {
        int i2;
        if (isLayoutRTL()) {
            i2 = getExtraStartPadding(getSpanCount());
        } else {
            i2 = 0;
        }
        return i2 - getSpacing(getSpanCount());
    }

    public int getSpacing(int i2) {
        int calculateSideSpacing = calculateSideSpacing(this.mSideSpacing);
        this.mSideSpacing = calculateSideSpacing;
        return calculateSideSpacing;
    }

    public int getWidthSpace() {
        return ((getSpacing(1) * 2) + getWidth()) - getExtraStartPadding(getSpanCount());
    }

    public void initDimen(Context context) {
        if (context != null) {
            this.mItemPadding = getItemPadding(context);
            this.mSideSpacing = getSideSpacing(this.mIsDrawerOpened);
            this.mDrawerStateDirty = true;
        }
    }

    public boolean updateExtraStartPadding(int i2, int i7, float f, boolean z, boolean z3) {
        boolean updateExtraStartPadding = super.updateExtraStartPadding(i2, i7, f, z, z3);
        if (this.mDrawerWidth != i2) {
            this.mDrawerWidth = i2;
            this.mDrawerStateDirty = true;
        }
        if (this.mProgress != f) {
            this.mProgress = f;
            this.mDrawerStateDirty = true;
        }
        this.mExtraStartPadding = (int) (((float) i2) * f);
        return updateExtraStartPadding;
    }

    private void updateViewSize(ListViewHolder listViewHolder, View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (listViewHolder.getViewType() == 0) {
            layoutParams.width = getWidthSpace() / getSpanCount();
            layoutParams.height = -2;
            int i2 = 0;
            boolean z = MediaItemStory.getStoryOriginCoverData(listViewHolder.getMediaItem()) != null;
            int[] iArr = this.mItemPadding;
            view.setPadding(iArr[0], iArr[1], iArr[2], iArr[3]);
            View decoView = listViewHolder.getDecoView(56);
            if (decoView != null && decoView.getLayoutParams() != null) {
                ViewGroup.LayoutParams layoutParams2 = decoView.getLayoutParams();
                int i7 = layoutParams.width;
                int i8 = this.mItemPadding[2] * 2;
                int i10 = i7 - i8;
                layoutParams2.width = i10;
                layoutParams2.height = layoutParams.width - i8;
                if (!z) {
                    i2 = (int) (((float) i10) * 0.0148f);
                }
                ViewMarginUtils.setPadding(decoView, i2);
                ViewMarginUtils.setStartPadding(listViewHolder.getDecoView(57), (int) (((float) layoutParams2.width) * 0.0148f));
                setClip(view);
                return;
            }
            return;
        }
        layoutParams.width = getWidth() - getExtraStartPadding(getSpanCount());
        layoutParams.height = -2;
        int spacing = getSpacing(getSpanCount());
        layoutParams.setMarginStart(spacing);
        layoutParams.setMarginEnd(-spacing);
        view.setLayoutParams(layoutParams);
        Optional.ofNullable(this.mView.getPinView()).ifPresent(new b(this, spacing));
    }
}
