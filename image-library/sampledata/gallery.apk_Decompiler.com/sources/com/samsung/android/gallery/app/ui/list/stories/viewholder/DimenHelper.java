package com.samsung.android.gallery.app.ui.list.stories.viewholder;

import android.content.res.Resources;
import android.util.TypedValue;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DimenHelper {
    protected DimenValue<Integer> mBorderRadius;
    protected DimenValue<Integer> mFavoritePadding;
    protected DimenValue<Integer> mFavoriteStartMargin;
    protected Float mFavoriteTopEndMarginRatio;
    protected DimenValue<Integer> mHeaderBottomMargin;
    protected DimenValue<Float> mItemRatio;
    private Resources mRes;
    protected DimenValue<Integer> mSideGap;
    protected DimenValue<Integer> mSubTitleTextSize;
    protected DimenValue<Float> mTitleBottomMargin;
    protected DimenValue<Integer> mTitleGap;
    protected DimenValue<Float> mTitleHorizontalMargin;
    protected DimenValue<Integer> mTitleTextSize;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DimenValue<T> {
        T mDrawerOpenValue;
        T[] mPinchDepthValue;
    }

    public DimenHelper(Resources resources) {
        this.mRes = resources;
    }

    public abstract int getBorderRadius(int i2, boolean z);

    public int getDimen(int i2) {
        return this.mRes.getDimensionPixelSize(i2);
    }

    public int getFavoritePadding(int i2, boolean z) {
        Integer num;
        if (this.mFavoritePadding == null) {
            DimenValue<Integer> dimenValue = new DimenValue<>();
            this.mFavoritePadding = dimenValue;
            dimenValue.mDrawerOpenValue = Integer.valueOf(getDimen(R.dimen.stories_view_favorite_padding_1_depth));
            this.mFavoritePadding.mPinchDepthValue = new Integer[]{Integer.valueOf(getDimen(R.dimen.stories_view_favorite_padding_1_depth)), Integer.valueOf(getDimen(R.dimen.stories_view_favorite_padding_2_depth))};
        }
        DimenValue<Integer> dimenValue2 = this.mFavoritePadding;
        if (z) {
            num = (Integer) dimenValue2.mDrawerOpenValue;
        } else {
            num = ((Integer[]) dimenValue2.mPinchDepthValue)[i2];
        }
        return num.intValue();
    }

    public float getRatioDimen(int i2) {
        TypedValue typedValue = new TypedValue();
        this.mRes.getValue(i2, typedValue, true);
        return typedValue.getFloat();
    }

    public abstract int getSideGap(int i2, boolean z);

    public abstract int getSubTitleTextSize(int i2, boolean z);

    public abstract float getTitleBottomMargin(int i2, boolean z);

    public int getTitleGap(int i2, boolean z) {
        Integer num;
        if (this.mTitleGap == null) {
            DimenValue<Integer> dimenValue = new DimenValue<>();
            this.mTitleGap = dimenValue;
            dimenValue.mDrawerOpenValue = Integer.valueOf(getDimen(R.dimen.stories_view_title_bottom_margin_1_depth));
            this.mTitleGap.mPinchDepthValue = new Integer[]{Integer.valueOf(getDimen(R.dimen.stories_view_title_bottom_margin_1_depth)), Integer.valueOf(getDimen(R.dimen.stories_view_title_bottom_margin_2_depth))};
        }
        DimenValue<Integer> dimenValue2 = this.mTitleGap;
        if (z) {
            num = (Integer) dimenValue2.mDrawerOpenValue;
        } else {
            num = ((Integer[]) dimenValue2.mPinchDepthValue)[i2];
        }
        return num.intValue();
    }

    public abstract float getTitleHorizontalMargin(int i2, boolean z);

    public abstract int getTitleTextSize(int i2, boolean z);

    public float getTopEndDecoMarginRatio() {
        if (this.mFavoriteTopEndMarginRatio == null) {
            this.mFavoriteTopEndMarginRatio = Float.valueOf(getRatioDimen(R.dimen.stories_view_favorite_top_end_margin_ratio));
        }
        return this.mFavoriteTopEndMarginRatio.floatValue();
    }

    public void reset() {
        this.mTitleTextSize = null;
        this.mSubTitleTextSize = null;
        this.mTitleHorizontalMargin = null;
        this.mTitleBottomMargin = null;
        this.mTitleGap = null;
        this.mSideGap = null;
        this.mBorderRadius = null;
        this.mFavoritePadding = null;
        this.mFavoriteStartMargin = null;
        this.mItemRatio = null;
        this.mHeaderBottomMargin = null;
        this.mFavoriteTopEndMarginRatio = null;
    }
}
