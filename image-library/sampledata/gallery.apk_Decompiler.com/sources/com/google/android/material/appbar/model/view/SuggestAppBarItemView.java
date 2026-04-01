package com.google.android.material.appbar.model.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.oneui.common.internal.util.MaxFontScaleRatio;
import androidx.appcompat.oneui.common.internal.util.TextViewHelperKt;
import androidx.appcompat.util.SeslMisc;
import androidx.appcompat.util.theme.SeslThemeResourceHelper;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$OpenThemeResourceColor;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$ThemeResourceColor;
import androidx.core.content.ContextCompat;
import androidx.reflect.view.SeslViewReflector;
import androidx.reflect.widget.SeslHoverPopupWindowReflector;
import com.sec.android.gallery3d.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0017\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0017\u001a\u00020\n2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0015H\u0004¢\u0006\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Lcom/google/android/material/appbar/model/view/SuggestAppBarItemView;", "Lcom/google/android/material/appbar/model/view/SuggestAppBarView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Landroid/widget/Button;", "button", "Lme/x;", "updateButton", "(Landroid/widget/Button;)V", "", "getSuggestButtonTextColor", "()I", "getButtonTextColor", "inflate", "()V", "updateResource", "(Landroid/content/Context;)V", "", "buttons", "updateButtons", "(Ljava/util/List;)V", "Landroid/view/ViewGroup;", "rootView", "Landroid/view/ViewGroup;", "getRootView", "()Landroid/view/ViewGroup;", "setRootView", "(Landroid/view/ViewGroup;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestAppBarItemView extends SuggestAppBarView {
    private ViewGroup rootView;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SuggestAppBarItemView(Context context) {
        this(context, (AttributeSet) null, 2, (e) null);
        j.e(context, "context");
    }

    private final int getButtonTextColor() {
        SeslThemeResourceHelper.Companion companion = SeslThemeResourceHelper.Companion;
        Context context = getContext();
        j.d(context, "context");
        return companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_button_text_color, R.color.sesl_button_text_color_dark), new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_button_text_color, R.color.sesl_button_text_color_dark_for_theme)));
    }

    private final int getSuggestButtonTextColor() {
        SeslThemeResourceHelper.Companion companion = SeslThemeResourceHelper.Companion;
        Context context = getContext();
        j.d(context, "context");
        return companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_suggest_button_text_color, R.color.sesl_suggest_button_text_color_dark), new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_suggest_button_text_color, R.color.sesl_suggest_button_text_color_dark_for_theme)));
    }

    private final void updateButton(Button button) {
        button.setTextColor(getButtonTextColor());
        TextViewHelperKt.checkMaxFontScale(button, R.dimen.sesl_appbar_button_text_size, MaxFontScaleRatio.MEDIUM);
    }

    public final ViewGroup getRootView() {
        return this.rootView;
    }

    public void inflate() {
        ViewGroup viewGroup;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.sesl_app_bar_suggest_in_viewpager, this, false);
        ImageButton imageButton = null;
        if (inflate instanceof ViewGroup) {
            viewGroup = (ViewGroup) inflate;
        } else {
            viewGroup = null;
        }
        if (viewGroup != null) {
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.sesl_appbar_suggest_in_viewpager);
            viewGroup2.setBackgroundResource(R.drawable.sesl_viewpager_item_background);
            this.rootView = viewGroup2;
            setTopImageView((ImageView) viewGroup.findViewById(R.id.suggest_app_bar_top_image));
            setTitleView((TextView) viewGroup.findViewById(R.id.suggest_app_bar_title));
            setSubTitleView((TextView) viewGroup.findViewById(R.id.suggest_app_bar_sub_title));
            ImageButton imageButton2 = (ImageButton) viewGroup.findViewById(R.id.suggest_app_bar_close);
            if (imageButton2 != null) {
                SeslViewReflector.semSetHoverPopupType(imageButton2, SeslHoverPopupWindowReflector.getField_TYPE_NONE());
                imageButton = imageButton2;
            }
            setClose(imageButton);
            setBottomLayout((ViewGroup) viewGroup.findViewById(R.id.suggest_app_bar_bottom_layout));
            Context context = getContext();
            j.d(context, "context");
            updateResource(context);
            addView(viewGroup);
        }
    }

    public final void setRootView(ViewGroup viewGroup) {
        this.rootView = viewGroup;
    }

    public final void updateButtons(List<? extends Button> list) {
        j.e(list, "buttons");
        for (Button updateButton : list) {
            updateButton(updateButton);
        }
    }

    public void updateResource(Context context) {
        int i2;
        j.e(context, "context");
        super.updateResource(context);
        boolean isLightTheme = SeslMisc.isLightTheme(context);
        ViewGroup viewGroup = this.rootView;
        if (viewGroup != null) {
            if (isLightTheme) {
                i2 = R.color.sesl_viewpager_item_background;
            } else {
                i2 = R.color.sesl_viewpager_item_background_dark;
            }
            viewGroup.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, i2)));
        }
        TextView titleView = getTitleView();
        if (titleView != null) {
            TextViewHelperKt.checkMaxFontScale(titleView, R.dimen.sesl_appbar_suggest_title_text_size, MaxFontScaleRatio.SMALL);
        }
        TextView subTitleView = getSubTitleView();
        if (subTitleView != null) {
            TextViewHelperKt.checkMaxFontScale(subTitleView, R.dimen.sesl_appbar_suggest_sub_title_size, MaxFontScaleRatio.SMALL);
        }
        updateButtons(getButtons());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SuggestAppBarItemView(Context context, AttributeSet attributeSet, int i2, e eVar) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SuggestAppBarItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
        inflate();
    }
}
