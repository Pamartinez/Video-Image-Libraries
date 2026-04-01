package com.samsung.android.gallery.app.ui.list.suggestions;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionsLayoutManager extends GridLayoutManager {
    private int mCardDividerLayoutMarginBottom;
    private int mCardDividerLayoutMarginTop;
    private int mCardDividerMarginBottom;
    private int mCardDividerMarginTop;
    private float mDrawerOpenSideSpaceRatio;
    private float mProgress;
    private final RecyclerView mRecyclerView;
    private float mSideSpaceRatio;

    public SuggestionsLayoutManager(RecyclerView recyclerView, int i2) {
        super(recyclerView.getContext(), i2);
        this.mRecyclerView = recyclerView;
        initDimen(recyclerView.getContext());
    }

    private int getDimenPixelOffset(Resources resources, int i2) {
        return resources.getDimensionPixelOffset(i2);
    }

    private float getRatioDimen(Resources resources, int i2) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i2, typedValue, true);
        return typedValue.getFloat();
    }

    private float getSideSpaceRatio() {
        if (!DrawerUtil.supportDrawerLayout(this.mRecyclerView.getContext())) {
            return this.mSideSpaceRatio;
        }
        float f = this.mSideSpaceRatio;
        return f - ((f - this.mDrawerOpenSideSpaceRatio) * this.mProgress);
    }

    private int getSpacing() {
        return ((int) (getSideSpaceRatio() * ((float) ResourceCompat.getCoarseWindowWidth(this.mRecyclerView.getResources())))) / 2;
    }

    private void updateCardDividerMargin(ListViewHolder listViewHolder) {
        ViewGroup viewGroup = (ViewGroup) listViewHolder.getDecoView(70);
        if (viewGroup != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
            marginLayoutParams.topMargin = this.mCardDividerLayoutMarginTop;
            marginLayoutParams.bottomMargin = this.mCardDividerLayoutMarginBottom;
            viewGroup.setLayoutParams(marginLayoutParams);
            View childAt = viewGroup.getChildAt(0);
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            marginLayoutParams2.topMargin = this.mCardDividerMarginTop;
            marginLayoutParams2.bottomMargin = this.mCardDividerMarginBottom;
            childAt.setLayoutParams(marginLayoutParams2);
        }
    }

    private void updateViewSize(ListViewHolder listViewHolder) {
        updateCardDividerMargin(listViewHolder);
    }

    public void addView(View view, int i2) {
        super.addView(view, i2);
        ListViewHolder listViewHolder = (ListViewHolder) this.mRecyclerView.findContainingViewHolder(view);
        if (listViewHolder != null) {
            updateViewSize(listViewHolder);
        }
    }

    public int getPaddingLeft() {
        return getSpacing();
    }

    public int getPaddingRight() {
        return getSpacing();
    }

    public int getPaddingStart() {
        return getSpacing();
    }

    public void initDimen(Context context) {
        if (context != null) {
            this.mSideSpaceRatio = 1.0f - getRatioDimen(context.getResources(), R.dimen.suggestions_main_layout_width_ratio);
            float f = 0.0f;
            if (DrawerUtil.supportDrawerLayout(context) && this.mSideSpaceRatio > 0.0f) {
                f = 1.0f - getRatioDimen(context.getResources(), R.dimen.suggestions_main_layout_width_ratio_for_drawer_close);
            }
            this.mDrawerOpenSideSpaceRatio = f;
            this.mCardDividerLayoutMarginTop = getDimenPixelOffset(context.getResources(), R.dimen.suggestions_card_divider_layout_margin_top);
            this.mCardDividerLayoutMarginBottom = getDimenPixelOffset(context.getResources(), R.dimen.suggestions_card_divider_layout_margin_bottom);
            this.mCardDividerMarginTop = getDimenPixelOffset(context.getResources(), R.dimen.suggestions_card_divider_margin_top);
            this.mCardDividerMarginBottom = getDimenPixelOffset(context.getResources(), R.dimen.suggestions_card_divider_margin_bottom);
        }
    }

    public void updateExtraStartPadding(float f) {
        this.mProgress = f;
    }
}
