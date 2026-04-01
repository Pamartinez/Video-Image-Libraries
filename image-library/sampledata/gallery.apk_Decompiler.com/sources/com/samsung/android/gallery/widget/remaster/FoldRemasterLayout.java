package com.samsung.android.gallery.widget.remaster;

import android.app.Activity;
import android.graphics.RectF;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FoldRemasterLayout extends NormalRemasterLayout {
    public FoldRemasterLayout(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
    }

    public int getCircleHandlerTopMargin(boolean z, int i2) {
        return -(getHandlerBottomMargin(z) + i2);
    }

    public int getContentRightMargin(boolean z) {
        return 0;
    }

    public int getLandBottomMargin() {
        int i2;
        int i7;
        if (AbstractRemasterLayout.isInMultiWindowMode(this.mActivity)) {
            i2 = this.mResources.getDimensionPixelSize(R$dimen.fast_menu_imageview_height);
        } else if (DeviceInfo.isDexMode(this.mActivity) || this.mIsTabletDpi) {
            i2 = getStableInsetBottom();
        } else {
            i2 = 0;
        }
        if (hasFocusRoi()) {
            i7 = getMinBottomMargin(true);
        } else {
            i7 = getMinBottomMargin(true) / 2;
        }
        return Math.max(i2, getNavigationBarHeight() + i7);
    }

    public int getLandPhotoViewHeight(int i2) {
        int landPhotoViewHeight = super.getLandPhotoViewHeight(i2);
        if (supportFocusView()) {
            return landPhotoViewHeight - getMinBottomMargin(true);
        }
        return landPhotoViewHeight;
    }

    public AbstractRemasterLayout.LayoutType getLayoutType() {
        return AbstractRemasterLayout.LayoutType.FOLD;
    }

    public int getMinBottomMargin(boolean z) {
        if (!supportFocusView()) {
            return 0;
        }
        return getFastOptionHeight(false) + this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_bottom_margin);
    }

    public void updateFocusView(boolean z) {
        int i2;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mFocusView.getLayoutParams();
        int footerHeight = getFooterHeight() + this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_focus_bottom_margin);
        if (AbstractRemasterLayout.isInMultiWindowMode(this.mActivity)) {
            i2 = 0;
        } else {
            i2 = getNavigationBarHeight();
        }
        layoutParams.bottomMargin = footerHeight + i2;
        layoutParams.topMargin = 0;
        layoutParams.removeRule(21);
        layoutParams.addRule(12);
        layoutParams.addRule(13);
        this.mFocusView.setLayoutParams(layoutParams);
    }

    public void updateVerticalLine(RectF rectF, boolean z) {
        super.updateVerticalLine(rectF, z);
        ((RelativeLayout.LayoutParams) this.mEffectHandlerView.getLayoutParams()).addRule(3, R$id.remaster_header_view);
    }
}
