package com.samsung.android.gallery.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NoItemCustomView extends NoItemView {
    private LinearLayout mCustomContainer;

    public NoItemCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mCustomContainer = (LinearLayout) view.findViewById(R$id.custom_container);
    }

    public int getLayoutId() {
        return R$layout.noitem_custom_layout;
    }

    public void setCustomView(View view, Integer num) {
        int i2;
        if (view == null) {
            ViewUtils.setVisibleOrGone(this.mCustomContainer, false);
            return;
        }
        ViewUtils.removeSelf(view);
        this.mCustomContainer.removeAllViews();
        this.mCustomContainer.addView(view);
        ViewUtils.setVisibleOrGone(this.mCustomContainer, true);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mCustomContainer.getLayoutParams();
        if (ViewUtils.isVisible(this.mDescriptionView)) {
            i2 = R$id.description;
        } else {
            i2 = R$id.label;
        }
        layoutParams.addRule(3, i2);
        layoutParams.addRule(14, R$id.container);
        if (num != null) {
            layoutParams.setMargins(layoutParams.getMarginStart(), num.intValue(), layoutParams.getMarginEnd(), layoutParams.bottomMargin);
        }
    }
}
