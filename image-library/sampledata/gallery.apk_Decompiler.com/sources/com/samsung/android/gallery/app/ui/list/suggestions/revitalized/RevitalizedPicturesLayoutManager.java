package com.samsung.android.gallery.app.ui.list.suggestions.revitalized;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RevitalizedPicturesLayoutManager extends PinchLayoutManager {
    protected int[] mLargeGridItemPadding;
    private RevitalizedPicturesViewAdapter mListAdapter;
    protected int[] mSmallGridItemPadding;
    protected int mSpacingForLargeGrid = 0;
    protected int mSpacingForSmallGrid = 0;

    public RevitalizedPicturesLayoutManager(Context context, int i2) {
        super(context, i2);
        initDimens(context);
    }

    private int[] getLargeGridItemPadding(Context context) {
        return new int[]{context.getResources().getDimensionPixelOffset(getLargeGridItemSpacing()), context.getResources().getDimensionPixelOffset(R.dimen.revitalized_card_padding_top), context.getResources().getDimensionPixelOffset(getLargeGridItemSpacing()), context.getResources().getDimensionPixelOffset(R.dimen.revitalized_card_padding_bottom)};
    }

    private int[] getSmallGridItemPadding(Context context) {
        return new int[]{context.getResources().getDimensionPixelOffset(getSmallGridItemSpacing()), context.getResources().getDimensionPixelOffset(R.dimen.revitalized_card_padding_top), context.getResources().getDimensionPixelOffset(getSmallGridItemSpacing()), context.getResources().getDimensionPixelOffset(R.dimen.revitalized_card_padding_bottom)};
    }

    private void initDimens(Context context) {
        if (context != null && context.getResources() != null) {
            int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(getLargeGridItemSpacing());
            int dimensionPixelOffset2 = context.getResources().getDimensionPixelOffset(getSmallGridItemSpacing());
            int dimensionPixelOffset3 = context.getResources().getDimensionPixelOffset(getLargeGridViewSideGap());
            int dimensionPixelOffset4 = context.getResources().getDimensionPixelOffset(getSmallGridViewSideGap());
            this.mLargeGridItemPadding = getLargeGridItemPadding(context);
            this.mSmallGridItemPadding = getSmallGridItemPadding(context);
            this.mSpacingForLargeGrid = dimensionPixelOffset3 - dimensionPixelOffset;
            this.mSpacingForSmallGrid = dimensionPixelOffset4 - dimensionPixelOffset2;
        }
    }

    public void addView(View view, int i2) {
        updateViewSize(view, getItemViewType(view), getSpanCount());
        super.addView(view, i2);
    }

    public void bindHolder(ListViewHolder listViewHolder, int i2) {
        this.mListAdapter.onBindViewHolder(listViewHolder, i2);
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        return (ListViewHolder) this.mListAdapter.createViewHolder(viewGroup, i2);
    }

    public int getHintItemViewType(int i2, int i7) {
        return this.mListAdapter.getItemViewType(getRealGridSize(i2));
    }

    public int getHintWidthSpace(int i2) {
        return getWidth() - (getSpacing(i2) * 2);
    }

    public int getLargeGridItemSpacing() {
        return R.dimen.large_grid_revitalized_card_item_side_offset;
    }

    public int getLargeGridViewSideGap() {
        return R.dimen.large_grid_revitalized_card_side_gap;
    }

    public int getSmallGridItemSpacing() {
        return R.dimen.small_grid_revitalized_card_item_side_offset;
    }

    public int getSmallGridViewSideGap() {
        return R.dimen.small_grid_revitalized_card_side_gap;
    }

    public int getSpacing(int i2) {
        if (isDefaultDepth(i2)) {
            return this.mSpacingForSmallGrid;
        }
        return this.mSpacingForLargeGrid;
    }

    public void handleResolutionChange(Context context) {
        initDimens(context);
    }

    public boolean isDefaultDepth(int i2) {
        return this.mListAdapter.isDefaultDepth(getRealGridSize(i2));
    }

    public boolean isSelectionMode() {
        return this.mListAdapter.isSelectionMode();
    }

    public boolean isSingleSelectionMode() {
        return this.mListAdapter.isSingleSelectionMode();
    }

    public void onAdapterChangedInternal(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        this.mListAdapter = (RevitalizedPicturesViewAdapter) adapter2;
    }

    public void updateViewBorders(ListViewHolder listViewHolder, int i2) {
        this.mListAdapter.updateBorder(listViewHolder);
    }

    public void updateViewSize(View view, int i2, int i7) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = getHintWidthSpace(i7) / getRealGridSize(i7);
        layoutParams.height = -2;
        view.setLayoutParams(layoutParams);
        if (isDefaultDepth(i7)) {
            int[] iArr = this.mSmallGridItemPadding;
            view.setPadding(iArr[0], iArr[1], iArr[2], iArr[3]);
            return;
        }
        int[] iArr2 = this.mLargeGridItemPadding;
        view.setPadding(iArr2[0], iArr2[1], iArr2[2], iArr2[3]);
    }
}
