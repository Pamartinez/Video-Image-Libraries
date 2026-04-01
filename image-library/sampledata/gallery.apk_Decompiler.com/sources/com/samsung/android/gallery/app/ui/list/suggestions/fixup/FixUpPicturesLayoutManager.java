package com.samsung.android.gallery.app.ui.list.suggestions.fixup;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FixUpPicturesLayoutManager extends PicturesLayoutManager {
    protected final Context mContext;
    private int mItemBottomGap;
    private int mItemGap;
    private int mItemTitleHeight;
    private float mItemViewAspectRatio;
    private int mViewSideGap;

    public FixUpPicturesLayoutManager(Context context, int i2, String str) {
        super(context, i2);
        this.mContext = context;
        initDimens(context, str);
    }

    private int getContentArea() {
        return ResourceCompat.getCoarseWindowWidth(this.mContext.getResources()) - this.mViewSideGap;
    }

    private float getRatioDimen(Resources resources, int i2) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i2, typedValue, true);
        return typedValue.getFloat();
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

    public int getHintWidthSpace(int i2) {
        return getContentArea();
    }

    public int getItemHeight(int i2, int i7) {
        return ((int) (((float) (getHintWidthSpace(i7) / getRealGridSize(i7))) / this.mItemViewAspectRatio)) + this.mItemBottomGap + this.mItemTitleHeight;
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
        return (getWidth() - getContentArea()) / 2;
    }

    public void initDimens(Context context, String str) {
        boolean z;
        int i2;
        if (context != null && context.getResources() != null) {
            int i7 = 0;
            if (PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL || !LocationKey.isHighLightPictures(str)) {
                z = false;
            } else {
                z = true;
            }
            Resources resources = context.getResources();
            this.mItemViewAspectRatio = getRatioDimen(resources, R.dimen.suggestion_pictures_item_aspect_ratio);
            this.mItemGap = resources.getDimensionPixelOffset(R.dimen.suggestion_fix_up_pictures_item_gap);
            this.mViewSideGap = resources.getDimensionPixelOffset(R.dimen.suggestion_fix_up_pictures_view_side_gap) * 2;
            if (z) {
                i2 = R.dimen.suggestion_highlight_pictures_grid_vertical_gap;
            } else {
                i2 = R.dimen.suggestion_fix_up_pictures_item_bottom_gap;
            }
            this.mItemBottomGap = resources.getDimensionPixelOffset(i2);
            if (z) {
                i7 = resources.getDimensionPixelOffset(R.dimen.suggestion_highlight_pictures_item_title_height);
            }
            this.mItemTitleHeight = i7;
        }
    }

    public void updateViewSize(View view, int i2, int i7) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int itemHeight = getItemHeight(0, i7);
        layoutParams.width = getHintWidthSpace(i7) / getRealGridSize(i7);
        layoutParams.height = itemHeight;
        view.setLayoutParams(layoutParams);
        int i8 = this.mItemGap;
        view.setPadding(i8, i8, i8, this.mItemBottomGap + this.mItemTitleHeight);
    }
}
