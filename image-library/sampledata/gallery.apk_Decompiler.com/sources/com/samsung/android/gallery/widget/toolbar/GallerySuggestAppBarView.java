package com.samsung.android.gallery.widget.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.appbar.model.view.SuggestAppBarItemWhiteCaseView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GallerySuggestAppBarView extends SuggestAppBarItemWhiteCaseView {
    public GallerySuggestAppBarView(Context context, AttributeSet attributeSet) {
        super(context);
    }

    public /* bridge */ /* synthetic */ View getRootView() {
        return getRootView();
    }

    public void inflate() {
        int i2;
        View inflate = LayoutInflater.from(getContext()).inflate(R$layout.gallery_suggest_appbar_layout, this, false);
        if (Features.isEnabled(Features.IS_THEME_INSTALLED)) {
            i2 = R$drawable.gallery_suggest_appbar_bg_for_theme;
        } else {
            i2 = R$drawable.gallery_suggest_appbar_bg;
        }
        inflate.setBackgroundResource(i2);
        setTopImageView((ImageView) inflate.findViewById(R$id.suggest_app_bar_top_image));
        setTitleView((TextView) inflate.findViewById(R$id.suggest_app_bar_title));
        setSubTitleView((TextView) inflate.findViewById(R$id.suggest_app_bar_sub_title));
        setClose((ImageButton) inflate.findViewById(R$id.suggest_app_bar_close));
        setBottomLayout((ViewGroup) inflate.findViewById(R$id.suggest_app_bar_bottom_layout));
        updateResource(getContext());
        updatePadding();
        addView(inflate);
    }

    public void updatePadding() {
    }
}
