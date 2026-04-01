package com.google.android.material.appbar.model.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.R$color;
import androidx.appcompat.R$drawable;
import androidx.appcompat.util.theme.SeslThemeResourceHelper;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$OpenThemeResourceColor;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$ThemeResourceColor;
import androidx.appcompat.widget.SeslIndicator;
import androidx.viewpager2.widget.ViewPager2;
import com.sec.android.gallery3d.R;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000e\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/google/android/material/appbar/model/view/BasicViewPagerAppBarWhiteCaseView;", "Lcom/google/android/material/appbar/model/view/BasicViewPagerAppBarView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Landroid/content/res/ColorStateList;", "getViewPagerBackgroundColorStateList", "(Landroid/content/Context;)Landroid/content/res/ColorStateList;", "", "getViewPagerIndicatorOffWithWhiteCaseColor", "(Landroid/content/Context;)I", "getViewPagerIndicatorOnWithWhiteCaseColor", "Lme/x;", "updateResource", "(Landroid/content/Context;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BasicViewPagerAppBarWhiteCaseView extends BasicViewPagerAppBarView {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BasicViewPagerAppBarWhiteCaseView(Context context) {
        this(context, (AttributeSet) null, 2, (e) null);
        j.e(context, "context");
    }

    private final ColorStateList getViewPagerBackgroundColorStateList(Context context) {
        ColorStateList valueOf = ColorStateList.valueOf(SeslThemeResourceHelper.Companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_viewpager_background, R.color.sesl_viewpager_background_dark), new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_viewpager_background_for_theme))));
        j.d(valueOf, "valueOf(\n            Ses…)\n            )\n        )");
        return valueOf;
    }

    private final int getViewPagerIndicatorOffWithWhiteCaseColor(Context context) {
        return SeslThemeResourceHelper.Companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_appbar_viewpager_indicator_off_with_white_case, R$color.sesl_appbar_viewpager_indicator_off_dark), new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_appbar_viewpager_indicator_off_with_white_case_for_theme, R$color.sesl_appbar_viewpager_indicator_off_dark_for_theme)));
    }

    private final int getViewPagerIndicatorOnWithWhiteCaseColor(Context context) {
        return SeslThemeResourceHelper.Companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_appbar_viewpager_indicator_on_with_white_case), new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_appbar_viewpager_indicator_on_with_white_case_for_theme)));
    }

    public void updateResource(Context context) {
        Drawable drawable;
        Drawable mutate;
        j.e(context, "context");
        super.updateResource(context);
        ViewPager2 viewpager = getViewpager();
        if (viewpager != null) {
            viewpager.setBackgroundTintList(getViewPagerBackgroundColorStateList(context));
        }
        SeslIndicator indicator = getIndicator();
        if (indicator != null) {
            int i2 = R$drawable.sesl_viewpager_indicator_on_off;
            Drawable drawable2 = context.getDrawable(i2);
            Drawable drawable3 = null;
            if (drawable2 == null || (drawable = drawable2.mutate()) == null) {
                drawable = null;
            } else {
                drawable.setTint(getViewPagerIndicatorOffWithWhiteCaseColor(context));
            }
            indicator.setDefaultCircle(drawable);
            Drawable drawable4 = context.getDrawable(i2);
            if (!(drawable4 == null || (mutate = drawable4.mutate()) == null)) {
                mutate.setTint(getViewPagerIndicatorOnWithWhiteCaseColor(context));
                drawable3 = mutate;
            }
            indicator.setSelectCircle(drawable3);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BasicViewPagerAppBarWhiteCaseView(Context context, AttributeSet attributeSet, int i2, e eVar) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BasicViewPagerAppBarWhiteCaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
    }
}
