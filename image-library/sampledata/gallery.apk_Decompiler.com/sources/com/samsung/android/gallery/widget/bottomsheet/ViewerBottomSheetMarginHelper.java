package com.samsung.android.gallery.widget.bottomsheet;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.Size;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.widget.NestedScrollView;
import com.samsung.android.gallery.module.R$dimen;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.MarginParams;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerBottomSheetMarginHelper {
    private final Context mContext;
    private final BooleanSupplier mIsBottomSheetLandscapeMode;
    private final BooleanSupplier mIsLargeScreenUX;
    private final BooleanSupplier mIsTableMode;
    private final BooleanSupplier mIsTargetViewHalfOfWidth;
    private final Supplier<Size> mRootViewSize;

    public ViewerBottomSheetMarginHelper(Context context, Supplier<Size> supplier, BooleanSupplier booleanSupplier, BooleanSupplier booleanSupplier2, BooleanSupplier booleanSupplier3, BooleanSupplier booleanSupplier4) {
        this.mContext = context;
        this.mRootViewSize = supplier;
        this.mIsBottomSheetLandscapeMode = booleanSupplier;
        this.mIsTargetViewHalfOfWidth = booleanSupplier2;
        this.mIsTableMode = booleanSupplier3;
        this.mIsLargeScreenUX = booleanSupplier4;
    }

    private int getAdjustedValue(float f, int i2) {
        return Math.min(i2, (int) (((float) i2) * f));
    }

    private int getPartialExpandedOffset() {
        if (!isInMultiWindowMode() || this.mIsBottomSheetLandscapeMode.getAsBoolean() || isDexMode()) {
            return getTargetSize().getHeight();
        }
        return this.mRootViewSize.get().getHeight() / 2;
    }

    private int getSlideTopPadding(float f) {
        if (this.mIsTableMode.getAsBoolean() || this.mIsBottomSheetLandscapeMode.getAsBoolean() || this.mIsLargeScreenUX.getAsBoolean()) {
            return 0;
        }
        return getAdjustedValue(f, getPartialExpandedOffset());
    }

    private int getTargetBottomMargin() {
        if (!isInMultiWindowMode() || this.mIsBottomSheetLandscapeMode.getAsBoolean()) {
            return this.mRootViewSize.get().getHeight() - getTargetSize().getHeight();
        }
        return this.mRootViewSize.get().getHeight() / 2;
    }

    private int getTargetRightMargin() {
        return Math.max(0, this.mRootViewSize.get().getWidth() - getTargetSize().getWidth());
    }

    private boolean isDexMode() {
        return DeviceInfo.isDexMode(this.mContext);
    }

    private boolean isInMultiWindowMode() {
        Context context = this.mContext;
        if (!(context instanceof Activity) || !((Activity) context).isInMultiWindowMode()) {
            return false;
        }
        return true;
    }

    public void applyBottomSheetTransform(View view, NestedScrollView nestedScrollView, float f, int i2, Rect rect) {
        int i7;
        ViewMarginUtils.setTopPadding(nestedScrollView, getSlideTopPadding(f));
        int i8 = rect.right;
        int i10 = rect.bottom + i2;
        if (!this.mIsBottomSheetLandscapeMode.getAsBoolean()) {
            i7 = rect.left;
        } else if (this.mIsTargetViewHalfOfWidth.getAsBoolean()) {
            i7 = this.mRootViewSize.get().getWidth() / 2;
        } else {
            i7 = getTargetSize().getWidth();
        }
        ViewMarginUtils.setMargin(view, Integer.valueOf(i7), 0, Integer.valueOf(i8), Integer.valueOf(i10));
    }

    public void applyTargetViewTransform(View view, NestedScrollView nestedScrollView, float f, MarginParams marginParams) {
        float translationY = getTranslationY(nestedScrollView);
        if (translationY > 0.0f) {
            view.setTranslationY(-translationY);
        } else if (view != null && (view.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            int i2 = marginParams.topMargin;
            layoutParams.topMargin = Math.max(i2 - getAdjustedValue(f, i2), 0);
            layoutParams.bottomMargin = Math.min(marginParams.bottomMargin + getAdjustedValue(f, getTargetBottomMargin() - marginParams.bottomMargin), getTargetBottomMargin());
            layoutParams.rightMargin = getAdjustedValue(f, getTargetRightMargin());
            view.setTranslationY(0.0f);
            view.setLayoutParams(layoutParams);
        }
    }

    public Size getTargetSize() {
        int i2;
        int i7;
        if (this.mIsTableMode.getAsBoolean()) {
            return new Size(this.mRootViewSize.get().getWidth(), this.mRootViewSize.get().getHeight() / 2);
        }
        if (this.mIsBottomSheetLandscapeMode.getAsBoolean()) {
            if (this.mIsLargeScreenUX.getAsBoolean()) {
                i7 = this.mRootViewSize.get().getWidth() - ResourceCompat.getDimensionPixelOffset(this.mContext, R$dimen.details_width_in_large_gui_landscape, this.mRootViewSize.get().getWidth() / 2);
            } else if (this.mIsTargetViewHalfOfWidth.getAsBoolean()) {
                i7 = this.mRootViewSize.get().getWidth() / 2;
            } else {
                i7 = this.mRootViewSize.get().getHeight();
            }
            i2 = this.mRootViewSize.get().getHeight();
        } else {
            i7 = this.mRootViewSize.get().getWidth();
            if (isInMultiWindowMode()) {
                i2 = this.mRootViewSize.get().getHeight() / 2;
            } else if (this.mIsLargeScreenUX.getAsBoolean()) {
                i2 = this.mRootViewSize.get().getHeight() - ResourceCompat.getDimensionPixelOffset(this.mContext, R$dimen.details_height_in_large_gui_portrait, this.mRootViewSize.get().getHeight() / 2);
            } else {
                i2 = this.mRootViewSize.get().getWidth();
            }
        }
        return new Size(i7, i2);
    }

    public float getTranslationY(NestedScrollView nestedScrollView) {
        if (!this.mIsLargeScreenUX.getAsBoolean() && !this.mIsBottomSheetLandscapeMode.getAsBoolean()) {
            return (float) nestedScrollView.getScrollY();
        }
        return 0.0f;
    }
}
