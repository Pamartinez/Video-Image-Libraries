package com.samsung.android.gallery.widget.remaster;

import android.app.Activity;
import android.content.res.Resources;
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
public class NormalRemasterLayout extends AbstractRemasterLayout {
    public NormalRemasterLayout(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
    }

    public AbstractRemasterLayout.LayoutType getLayoutType() {
        return AbstractRemasterLayout.LayoutType.NORMAL;
    }

    public void updateCircleHandler(boolean z) {
        Resources resources = this.mActivity.getResources();
        int measuredHeight = this.mCircleHandler.getMeasuredHeight();
        if (measuredHeight <= 0) {
            measuredHeight = resources.getDimensionPixelSize(R$dimen.remaster_viewer2_circle_handler_size);
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mCircleHandler.getLayoutParams();
        marginLayoutParams.topMargin = getCircleHandlerTopMargin(z, measuredHeight);
        this.mCircleHandler.setLayoutParams(marginLayoutParams);
    }

    public void updateTableModeBG(boolean z) {
        this.mTableModeBG.setVisibility(8);
    }

    public void updateVerticalLine(RectF rectF, boolean z) {
        int i2;
        int i7 = (int) rectF.top;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mVerticalLine.getLayoutParams();
        if (i7 < 0) {
            i2 = this.mPhotoViewContainer.getLayoutParams().height;
        } else {
            i2 = this.mPhotoViewContainer.getLayoutParams().height - (i7 * 2);
        }
        marginLayoutParams.height = i2;
        marginLayoutParams.topMargin = Math.max(i7, 0);
        marginLayoutParams.bottomMargin = getHandlerBottomMargin(z);
        this.mVerticalLine.setLayoutParams(marginLayoutParams);
        ViewUtils.setVisibility(this.mVerticalLine.findViewById(R$id.vertical_line_up), 4);
        View findViewById = this.mVerticalLine.findViewById(R$id.vertical_line_bottom);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) findViewById.getLayoutParams();
        marginLayoutParams2.height = -1;
        findViewById.setLayoutParams(marginLayoutParams2);
    }

    public void updateViewContainerLayout(View view, boolean z, boolean z3) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mPhotoViewContainer.getLayoutParams();
        if (z) {
            this.mTargetMargin = getLandBottomMargin();
            layoutParams.height = getPhotoViewHeight(DeviceInfo.getDisplayWidth(view.getContext()), view.getHeight(), true);
        } else {
            int height = view.getHeight();
            layoutParams.topMargin = 0;
            layoutParams.height = getPhotoViewHeight(view.getWidth(), height, false);
            this.mTargetMargin = getPortBottomMargin(view.getWidth(), height);
        }
        layoutParams.setMarginEnd(getContentRightMargin(z));
        if (z3) {
            this.mSourceMargin = layoutParams.bottomMargin;
            this.mPhotoViewContainer.startAnimation(this.mMarginAnim);
        } else {
            cancelAnim();
            layoutParams.bottomMargin = this.mTargetMargin;
        }
        this.mPhotoViewContainer.setLayoutParams(layoutParams);
        this.mPhotoViewContainer.setVisibility(0);
    }
}
