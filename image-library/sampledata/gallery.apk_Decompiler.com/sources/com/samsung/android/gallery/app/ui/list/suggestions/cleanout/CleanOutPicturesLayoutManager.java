package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CleanOutPicturesLayoutManager extends PicturesLayoutManager {
    protected final Context mContext;
    private int[] mGridArray;
    boolean mIsDuplicatePictures;
    private float mItemAspectRatio;
    private int mItemBottomPadding;
    private int mItemLargeSidePadding;
    private float mItemLargeWidthRate;
    private int mItemSmallSidePadding;
    private float mItemSmallWidthRate;

    public CleanOutPicturesLayoutManager(RecyclerView recyclerView, int i2, boolean z) {
        super(recyclerView.getContext(), i2);
        this.mContext = recyclerView.getContext();
        this.mIsDuplicatePictures = z;
        initDimens(recyclerView.getContext());
        setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                return CleanOutPicturesLayoutManager.this.getHintStartSpan(i2, i7);
            }

            public int getSpanSize(int i2) {
                CleanOutPicturesLayoutManager cleanOutPicturesLayoutManager = CleanOutPicturesLayoutManager.this;
                return cleanOutPicturesLayoutManager.getHintColumnSpan(i2, cleanOutPicturesLayoutManager.getSpanCount());
            }
        });
    }

    private int getContentArea(int i2) {
        return (getRealGridSize(i2) * 2 * getItemSidePadding(i2)) + ((int) (((float) ResourceCompat.getCoarseWindowWidth(this.mContext.getResources())) * getItemWidthRate(i2) * ((float) getRealGridSize(i2))));
    }

    private int getItemSidePadding(int i2) {
        if (isLargeGrid(i2)) {
            return this.mItemLargeSidePadding;
        }
        return this.mItemSmallSidePadding;
    }

    private float getItemWidthRate(int i2) {
        if (isLargeGrid(i2)) {
            return this.mItemLargeWidthRate;
        }
        return this.mItemSmallWidthRate;
    }

    private boolean isLandscape(Resources resources) {
        if (resources.getConfiguration().orientation == 2) {
            return true;
        }
        return false;
    }

    private boolean isLargeGrid(int i2) {
        int realGridSize = getRealGridSize(i2);
        if (this.mIsDuplicatePictures) {
            return !isLandscape(this.mContext.getResources());
        }
        int[] iArr = this.mGridArray;
        if (iArr.length <= 1 || realGridSize != iArr[1]) {
            return false;
        }
        return true;
    }

    private void setThumbnailMargin(View view, int i2, int i7) {
        if (i2 == 0) {
            int itemSidePadding = getItemSidePadding(i7);
            View findViewById = view.findViewById(R.id.item_container);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) findViewById.getLayoutParams();
            marginLayoutParams.setMargins(itemSidePadding, itemSidePadding, itemSidePadding, itemSidePadding);
            findViewById.setLayoutParams(marginLayoutParams);
        }
    }

    public void addView(View view, int i2) {
        updateViewSize(view, getItemViewType(view), getSpanCount());
        super.addView(view, i2);
    }

    public void bindFakeHolder(ListViewHolder listViewHolder, int i2, int i7) {
        super.bindFakeHolder(listViewHolder, i2, i7);
        updateViewSize(listViewHolder.itemView, listViewHolder.getViewType(), i7);
    }

    public int getHintPaddingLeft(int i2) {
        int i7;
        if (isLayoutRTL()) {
            i7 = 0;
        } else {
            i7 = getExtraStartPadding(i2);
        }
        return getSpacing(i2) + i7;
    }

    public int getHintPaddingStart(int i2) {
        return getSpacing(i2);
    }

    public int getHintViewHeight(int i2, int i7) {
        return getItemHeight(i7);
    }

    public int getHintWidthSpace(int i2) {
        return getContentArea(i2);
    }

    public int getItemHeight(int i2) {
        return ((int) (((float) getItemWidth(i2)) / this.mItemAspectRatio)) + this.mItemBottomPadding;
    }

    public int getItemWidth(int i2) {
        return getHintWidthSpace(i2) / getRealGridSize(i2);
    }

    public int getPaddingRight() {
        int i2;
        if (isLayoutRTL()) {
            i2 = getExtraStartPadding(getSpanCount());
        } else {
            i2 = 0;
        }
        return getSpacing(getSpanCount()) + i2;
    }

    public int getPaddingStart() {
        return getSpacing(getSpanCount());
    }

    public int getSpacing(int i2) {
        return (getWidth() - getContentArea(i2)) / 2;
    }

    public void initDimens(Context context) {
        if (context != null && context.getResources() != null) {
            Resources resources = context.getResources();
            this.mItemLargeSidePadding = CleanOutDelegate.getItemLargeSidePadding(resources, this.mIsDuplicatePictures);
            this.mItemSmallSidePadding = CleanOutDelegate.getItemSmallSidePadding(resources, this.mIsDuplicatePictures);
            this.mItemBottomPadding = CleanOutDelegate.getItemBottomPadding(resources, this.mIsDuplicatePictures);
            this.mItemLargeWidthRate = CleanOutDelegate.getItemLargeWidthRate(resources, this.mIsDuplicatePictures);
            this.mItemSmallWidthRate = CleanOutDelegate.getItemSmallWidthRate(resources, this.mIsDuplicatePictures);
            this.mItemAspectRatio = CleanOutDelegate.getItemAspectRatio(resources, R.dimen.suggestion_pictures_item_aspect_ratio);
            this.mGridArray = CleanOutDelegate.getGridArray(resources, this.mIsDuplicatePictures);
        }
    }

    public void setViewHolderMargin(ListViewHolder listViewHolder, int i2) {
        setThumbnailMargin(listViewHolder.itemView, listViewHolder.getItemViewType(), i2);
    }

    public boolean updateTimelineWidth() {
        return false;
    }

    public void updateViewSize(View view, int i2, int i7) {
        if (i2 == 0) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = getItemWidth(i7);
            layoutParams.height = getItemHeight(i7);
            view.setLayoutParams(layoutParams);
            if (!this.mIsDuplicatePictures) {
                ViewMarginUtils.setBottomPadding(view, this.mItemBottomPadding);
            }
            setThumbnailMargin(view, i2, i7);
        }
    }
}
