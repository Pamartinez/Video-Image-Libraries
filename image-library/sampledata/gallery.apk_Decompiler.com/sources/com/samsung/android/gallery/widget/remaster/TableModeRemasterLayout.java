package com.samsung.android.gallery.widget.remaster;

import Qb.e;
import android.app.Activity;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TableModeRemasterLayout extends AbstractRemasterLayout {
    public TableModeRemasterLayout(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
    }

    private int getFocusViewHeight() {
        if (hasFocusRoi()) {
            return this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_focus_height_with_margin);
        }
        return 0;
    }

    private int getTableModeTouchPadBottomMargin() {
        int i2;
        if (isLandscape()) {
            i2 = 0;
        } else {
            i2 = this.mResources.getDimensionPixelSize(R$dimen.fast_menu_imageview_height);
        }
        return getStableInsetBottom() + this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_table_mode_bg_bottom_margin) + i2 + getFocusViewHeight();
    }

    private int getTableModeTouchPadHeight() {
        return ((getDeviceHeight() / 2) - getTableModeTouchPadBottomMargin()) - this.mActionbarHeight;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateTableModeBG$0() {
        this.mTableModeBG.setVisibility(0);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mTableModeBG.getLayoutParams();
        marginLayoutParams.bottomMargin = getTableModeTouchPadBottomMargin();
        marginLayoutParams.height = getTableModeTouchPadHeight();
        this.mTableModeBG.setLayoutParams(marginLayoutParams);
    }

    public int getBottomMargin(int i2, int i7, boolean z) {
        return getPhotoViewHeight(i2, i7, z);
    }

    public int getContentRightMargin(boolean z) {
        return 0;
    }

    public int getHandlerBottomMargin(boolean z) {
        return (this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer2_circle_handler_size) / 2) + (getTableModeTouchPadHeight() / 2) + getTableModeTouchPadBottomMargin();
    }

    public int getHeaderTextBaseTop(int i2) {
        int statusBarHeight = DeviceInfo.getStatusBarHeight();
        if (i2 - statusBarHeight > 0) {
            return i2;
        }
        return statusBarHeight;
    }

    public AbstractRemasterLayout.LayoutType getLayoutType() {
        return AbstractRemasterLayout.LayoutType.TABLE_MODE;
    }

    public int getMinBottomMargin(boolean z) {
        if (supportFocusView()) {
            return this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_bottom_margin);
        }
        return 0;
    }

    public int getPhotoViewHeight(int i2, int i7, boolean z) {
        return DeviceInfo.getDisplayHeight(this.mActivity) / 2;
    }

    public void prepareFocusRoiAnim(RectF rectF) {
        boolean isLandscape = isLandscape();
        updateTableModeBG(!isLandscape);
        updateHandlerLayout(rectF, isLandscape);
    }

    public void updateCircleHandler(boolean z) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mCircleHandler.getLayoutParams();
        marginLayoutParams.topMargin = -getHandlerBottomMargin(z);
        this.mCircleHandler.setLayoutParams(marginLayoutParams);
    }

    public void updateFocusView(boolean z) {
        int i2;
        int i7;
        int i8;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mFocusView.getLayoutParams();
        if (z) {
            i2 = 0;
        } else {
            i2 = this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_focus_bottom_margin);
        }
        if (z) {
            i7 = 0;
        } else {
            i7 = getFooterHeight();
        }
        int i10 = i2 + i7;
        if (AbstractRemasterLayout.isInMultiWindowMode(this.mActivity)) {
            i8 = 0;
        } else {
            i8 = getNavigationBarHeight();
        }
        layoutParams.bottomMargin = i10 + i8;
        layoutParams.topMargin = 0;
        layoutParams.removeRule(21);
        layoutParams.addRule(12);
        layoutParams.addRule(13);
        this.mFocusView.setLayoutParams(layoutParams);
    }

    public void updateTableModeBG(boolean z) {
        ViewUtils.post(this.mPhotoViewContainer, new e(13, this));
    }

    public void updateVerticalLine(RectF rectF, boolean z) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mVerticalLine.getLayoutParams();
        marginLayoutParams.height = getDeviceHeight();
        marginLayoutParams.bottomMargin = getTableModeTouchPadBottomMargin();
        marginLayoutParams.topMargin = (int) Math.max(0.0f, rectF.top);
        this.mVerticalLine.setLayoutParams(marginLayoutParams);
        ((RelativeLayout.LayoutParams) this.mEffectHandlerView.getLayoutParams()).removeRule(3);
        View findViewById = this.mVerticalLine.findViewById(R$id.vertical_line_bottom);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) findViewById.getLayoutParams();
        marginLayoutParams2.height = getTableModeTouchPadHeight();
        findViewById.setLayoutParams(marginLayoutParams2);
        View findViewById2 = this.mVerticalLine.findViewById(R$id.vertical_line_up);
        ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) findViewById2.getLayoutParams();
        marginLayoutParams3.height = Math.min((int) rectF.height(), getDeviceHeight() / 2);
        findViewById2.setLayoutParams(marginLayoutParams3);
        findViewById2.setVisibility(0);
    }

    public void updateViewContainerLayout(View view, boolean z, boolean z3) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mPhotoViewContainer.getLayoutParams();
        int photoViewHeight = getPhotoViewHeight(view.getWidth(), view.getHeight(), z);
        this.mTargetMargin = photoViewHeight;
        if (z3) {
            this.mSourceMargin = layoutParams.bottomMargin;
            this.mPhotoViewContainer.startAnimation(this.mMarginAnim);
        } else {
            cancelAnim();
            layoutParams.height = photoViewHeight;
            layoutParams.bottomMargin = this.mTargetMargin;
            this.mPhotoViewContainer.setLayoutParams(layoutParams);
        }
        layoutParams.setMarginEnd(getContentRightMargin(z));
        this.mPhotoViewContainer.setVisibility(0);
    }
}
