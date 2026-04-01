package com.samsung.android.gallery.app.ui.appbar;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.toolbar.GallerySuggestAppBarView;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UsbAttachSuggestAppbarView extends GallerySuggestAppBarView {
    public UsbAttachSuggestAppbarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void updatePadding() {
        Activity activity = Blackboard.getActivity();
        if (activity != null) {
            ViewMarginUtils.setHorizontalPadding(this, getResources().getDimensionPixelOffset(R.dimen.sesl_appbar_extended_title_padding));
            ViewMarginUtils.setTopPadding(this, SystemUi.getStatusBarHeight(activity));
            ViewMarginUtils.setBottomPadding(this, SystemUi.getToolBarHeight(activity));
        }
    }

    public void updateResource(Context context) {
        super.updateResource(context);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.sesl_appbar_suggest_close_button_size) * 2;
        ViewUtils.setTouchArea(getClose(), (ViewGroup) getClose().getParent(), dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
        if (getTitleView() != null) {
            getTitleView().setTextSize(0, getResources().getDimension(R.dimen.suggest_app_bar_title_text_size));
        }
        for (Button textSize : getButtons()) {
            textSize.setTextSize(0, getResources().getDimension(R.dimen.suggest_app_bar_button_text_size));
        }
    }
}
