package com.samsung.android.gallery.app.ui.list.stories.pinch;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBaseLayoutManager;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.DimenHelper;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinchLayoutManagerV2<V extends IStoriesPinchView> extends StoriesBaseLayoutManager {
    private final ILayoutUpdater mCallback;
    private final DimenHelper mDimenHelper;
    private int mListBottomPadding;

    public StoriesPinchLayoutManagerV2(Context context, ILayoutUpdater iLayoutUpdater, int i2, DimenHelper dimenHelper) {
        super(context, i2);
        this.mCallback = iLayoutUpdater;
        this.mDimenHelper = dimenHelper;
        initDimen(context);
    }

    private int getItemWidth(int i2) {
        return getHintWidthSpace(i2) / getRealGridSize(i2);
    }

    public void bindFakeHolder(ListViewHolder listViewHolder, int i2, int i7) {
        super.bindFakeHolder(listViewHolder, i2, i7);
        if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
            updateDecoView(listViewHolder.itemView, i2, i7);
        }
    }

    public int getHeaderBottomMargin(int i2) {
        if (getHeaderView() != null) {
            return getItemPadding(i2);
        }
        return 0;
    }

    public int getHintViewHeight(int i2, int i7) {
        if (this.mListAdapter.isHeader(i2)) {
            return this.mListAdapter.getHeaderViewHeight();
        }
        return getItemHeight(getItemWidth(i7), i7);
    }

    public int getItemHeight(int i2, int i7) {
        return (int) (((float) i2) * 1.13f);
    }

    public int getItemPadding(int i2) {
        return this.mContext.getResources().getDimensionPixelOffset(R.dimen.stories_item_padding);
    }

    public int getPaddingBottom() {
        return (super.getPaddingBottom() + this.mListBottomPadding) - getItemPadding(getSpanCount());
    }

    public int getSpacing(int i2) {
        return this.mContext.getResources().getDimensionPixelOffset(R.dimen.stories_side_padding);
    }

    public float getThumbnailRadius(int i2) {
        int i7;
        DimenHelper dimenHelper = this.mDimenHelper;
        ILayoutUpdater iLayoutUpdater = this.mCallback;
        if (iLayoutUpdater != null) {
            i7 = iLayoutUpdater.getPinchDepth(i2);
        } else {
            i7 = 0;
        }
        return (float) dimenHelper.getBorderRadius(i7, this.mIsDrawerOpened);
    }

    public View getViewForHolderMargin(ViewGroup viewGroup) {
        return viewGroup.findViewById(R.id.thumbnail_container);
    }

    public boolean hasHeader() {
        return true;
    }

    public void initDimen(Context context) {
        this.mListBottomPadding = context.getResources().getDimensionPixelOffset(R.dimen.stories_list_bottom_padding);
    }

    public void setSpanCount(int i2) {
        int spanCount = super.getSpanCount();
        super.setSpanCount(i2);
        ILayoutUpdater iLayoutUpdater = this.mCallback;
        if (iLayoutUpdater != null) {
            iLayoutUpdater.gridChanged(i2, spanCount);
        }
    }

    public void updateDecoView(View view, int i2, int i7) {
        ILayoutUpdater iLayoutUpdater = this.mCallback;
        if (iLayoutUpdater != null) {
            iLayoutUpdater.updateDecoView(view, i2, i7);
        }
    }

    public void updateViewSize(View view, int i2, int i7) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (i2 == 0) {
            int itemWidth = getItemWidth(i7);
            layoutParams.width = itemWidth;
            layoutParams.height = getItemHeight(itemWidth, i7);
            view.setLayoutParams(layoutParams);
            setViewHolderMargin((ViewGroup) view, i7);
        } else if (ViewHolderValue.isHeader(i2)) {
            int itemPadding = getItemPadding(i7);
            int spacing = getSpacing(i7);
            int i8 = -spacing;
            ViewMarginUtils.setMarginRelative(view, Integer.valueOf(i8), 0, Integer.valueOf(i8), Integer.valueOf(getHeaderBottomMargin(i7)));
            int i10 = itemPadding + spacing;
            ViewMarginUtils.setHorizontalPadding(view.findViewById(R.id.pin_list), i10);
            ViewMarginUtils.setHorizontalPadding(view.findViewById(R.id.text_divider), i10);
        } else {
            layoutParams.width = getHintWidthSpace(i7);
            layoutParams.height = -2;
            view.setLayoutParams(layoutParams);
        }
    }
}
