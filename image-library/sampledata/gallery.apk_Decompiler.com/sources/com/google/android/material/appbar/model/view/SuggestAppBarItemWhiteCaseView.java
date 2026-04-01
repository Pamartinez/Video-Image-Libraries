package com.google.android.material.appbar.model.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.util.SeslMisc;
import androidx.appcompat.util.theme.SeslThemeResourceHelper;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$OpenThemeResourceColor;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$ThemeResourceColor;
import androidx.appcompat.util.theme.resource.SeslThemeResourceDrawable$OpenThemeResourceDrawable;
import androidx.appcompat.util.theme.resource.SeslThemeResourceDrawable$ThemeResourceDrawable;
import com.sec.android.gallery3d.R;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\f\u0010\u000bJ\u000f\u0010\r\u001a\u00020\tH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0005¨\u0006\u0011"}, d2 = {"Lcom/google/android/material/appbar/model/view/SuggestAppBarItemWhiteCaseView;", "Lcom/google/android/material/appbar/model/view/SuggestAppBarItemView;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/graphics/drawable/Drawable;", "getCloseDrawable", "(Landroid/content/Context;)Landroid/graphics/drawable/Drawable;", "", "getViewPagerItemBackgroundWhiteWhiteCaseColor", "(Landroid/content/Context;)I", "getSuggestTitleWithWhiteCaseColor", "getSuggestButtonTextColorWithWhiteCase", "()I", "Lme/x;", "updateResource", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestAppBarItemWhiteCaseView extends SuggestAppBarItemView {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SuggestAppBarItemWhiteCaseView(Context context) {
        super(context, (AttributeSet) null, 2, (e) null);
        j.e(context, "context");
    }

    private final Drawable getCloseDrawable(Context context) {
        return SeslThemeResourceHelper.Companion.getDrawable(context, new SeslThemeResourceDrawable$OpenThemeResourceDrawable(new SeslThemeResourceDrawable$ThemeResourceDrawable(R.drawable.sesl_close_button_recoil_background_with_white_case, R.drawable.sesl_close_button_recoil_background_dark), new SeslThemeResourceDrawable$ThemeResourceDrawable(R.drawable.sesl_close_button_recoil_background_with_white_case_for_theme, R.drawable.sesl_close_button_recoil_background_dark_for_theme)));
    }

    private final int getSuggestButtonTextColorWithWhiteCase() {
        SeslThemeResourceHelper.Companion companion = SeslThemeResourceHelper.Companion;
        Context context = getContext();
        j.d(context, "context");
        return companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_suggest_button_text_color_with_white_case, R.color.sesl_suggest_button_text_color_dark), new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_suggest_button_text_color_with_white_case, R.color.sesl_suggest_button_text_color_dark_for_theme)));
    }

    private final int getSuggestTitleWithWhiteCaseColor(Context context) {
        return SeslThemeResourceHelper.Companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_appbar_suggest_title_with_white_case, R.color.sesl_appbar_suggest_title_dark), new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_appbar_suggest_title_with_white_case, R.color.sesl_appbar_suggest_title_dark_for_theme)));
    }

    private final int getViewPagerItemBackgroundWhiteWhiteCaseColor(Context context) {
        return SeslThemeResourceHelper.Companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_viewpager_item_background_with_white_case, R.color.sesl_viewpager_item_background_dark), new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_viewpager_item_background_with_white_case_for_theme, R.color.sesl_viewpager_item_background_dark)));
    }

    public void updateResource(Context context) {
        j.e(context, "context");
        super.updateResource(context);
        SeslMisc.isLightTheme(context);
        ViewGroup rootView = getRootView();
        if (rootView != null) {
            rootView.setBackgroundTintList(ColorStateList.valueOf(getViewPagerItemBackgroundWhiteWhiteCaseColor(context)));
        }
        TextView titleView = getTitleView();
        if (titleView != null) {
            titleView.setTextColor(getSuggestTitleWithWhiteCaseColor(context));
        }
        ImageButton close = getClose();
        if (close != null) {
            close.setBackground(getCloseDrawable(context));
        }
        updateButtons(getButtons());
    }
}
