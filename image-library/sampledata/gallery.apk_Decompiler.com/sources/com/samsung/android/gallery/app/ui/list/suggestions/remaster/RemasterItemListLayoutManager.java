package com.samsung.android.gallery.app.ui.list.suggestions.remaster;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterItemListLayoutManager extends GridLayoutManager {
    private int mItemGap;
    private float mItemViewAspectRatio;
    private float mItemWidthRatio;

    public RemasterItemListLayoutManager(Context context, int i2) {
        super(context, i2);
        setOrientation(0);
        initDimens(context);
    }

    private int getDimenPixelOffset(Resources resources, int i2) {
        return resources.getDimensionPixelOffset(i2);
    }

    private float getRatioDimen(Resources resources, int i2) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i2, typedValue, true);
        return typedValue.getFloat();
    }

    private void initDimens(Context context) {
        if (context != null && context.getResources() != null) {
            this.mItemWidthRatio = getRatioDimen(context.getResources(), R.dimen.remaster_list_item_width_ratio_nby);
            this.mItemViewAspectRatio = getRatioDimen(context.getResources(), R.dimen.remaster_list_item_view_aspect_ratio_nby);
            this.mItemGap = getDimenPixelOffset(context.getResources(), R.dimen.remaster_list_item_gap);
        }
    }

    private void updateViewSize(View view) {
        int coarseWindowWidth = (this.mItemGap * 2) + ((int) (((float) ResourceCompat.getCoarseWindowWidth(view.getContext().getResources())) * this.mItemWidthRatio));
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = coarseWindowWidth;
        layoutParams.height = (int) (((float) coarseWindowWidth) / this.mItemViewAspectRatio);
        view.setLayoutParams(layoutParams);
        int i2 = this.mItemGap;
        view.setPadding(i2, i2, i2, i2);
    }

    public void addView(View view, int i2) {
        updateViewSize(view);
        super.addView(view, i2);
    }

    public void updateLayout(Context context) {
        initDimens(context);
    }
}
