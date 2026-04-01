package com.google.android.material.appbar.model.view;

import S1.m;
import T1.a;
import T1.d;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.R$color;
import androidx.appcompat.R$drawable;
import androidx.appcompat.util.theme.SeslThemeResourceHelper;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$OpenThemeResourceColor;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$ThemeResourceColor;
import androidx.appcompat.widget.SeslIndicator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.sec.android.gallery3d.R;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0017\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010#\u001a\u0004\u0018\u00010\u001c8\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b#\u0010\u001e\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"R$\u0010&\u001a\u0004\u0018\u00010\u001c8\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b&\u0010\u001e\u001a\u0004\b'\u0010 \"\u0004\b(\u0010\"R$\u0010*\u001a\u0004\u0018\u00010)8\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/¨\u00060"}, d2 = {"Lcom/google/android/material/appbar/model/view/ViewPagerAppBarView;", "LT1/a;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Landroid/content/res/ColorStateList;", "getViewPagerBackgroundColorStateList", "(Landroid/content/Context;)Landroid/content/res/ColorStateList;", "", "getViewPagerIndicatorOffColor", "(Landroid/content/Context;)I", "getViewPagerIndicatorOnColor", "Lme/x;", "adjustViewPagerLayout", "()V", "inflate", "updateResource", "(Landroid/content/Context;)V", "Landroidx/viewpager2/widget/ViewPager2;", "viewpager", "Landroidx/viewpager2/widget/ViewPager2;", "getViewpager", "()Landroidx/viewpager2/widget/ViewPager2;", "setViewpager", "(Landroidx/viewpager2/widget/ViewPager2;)V", "Landroid/view/ViewGroup;", "viewPagerContainer", "Landroid/view/ViewGroup;", "getViewPagerContainer", "()Landroid/view/ViewGroup;", "setViewPagerContainer", "(Landroid/view/ViewGroup;)V", "viewPagerParent", "getViewPagerParent", "setViewPagerParent", "bottomLayout", "getBottomLayout", "setBottomLayout", "Landroidx/appcompat/widget/SeslIndicator;", "indicator", "Landroidx/appcompat/widget/SeslIndicator;", "getIndicator", "()Landroidx/appcompat/widget/SeslIndicator;", "setIndicator", "(Landroidx/appcompat/widget/SeslIndicator;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPagerAppBarView extends a {
    private ViewGroup bottomLayout;
    private SeslIndicator indicator;
    private ViewGroup viewPagerContainer;
    private ViewGroup viewPagerParent;
    private ViewPager2 viewpager;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ViewPagerAppBarView(Context context) {
        this(context, (AttributeSet) null, 2, (e) null);
        j.e(context, "context");
    }

    private final void adjustViewPagerLayout() {
        i iVar;
        ViewGroup.LayoutParams layoutParams;
        Context context = getContext();
        j.d(context, "context");
        int c5 = m.c(this);
        int i2 = context.getResources().getDisplayMetrics().widthPixels;
        if (c5 < m.a(411)) {
            iVar = new i(0, Integer.valueOf(context.getResources().getDimensionPixelSize(R.dimen.sesl_appbar_viewpager_height)));
        } else if (i2 <= m.a(291)) {
            iVar = new i(0, Integer.valueOf(context.getResources().getDimensionPixelSize(R.dimen.sesl_appbar_viewpager_height)));
        } else if (i2 <= m.a(599)) {
            iVar = new i(Integer.valueOf(m.a(272)), Integer.valueOf(m.a(150)));
        } else if (i2 <= m.a(1580)) {
            iVar = new i(Integer.valueOf(m.a(360)), Integer.valueOf(m.a(164)));
        } else {
            iVar = new i(Integer.valueOf(m.a(440)), Integer.valueOf(m.a(200)));
        }
        int intValue = ((Number) iVar.d).intValue();
        int intValue2 = ((Number) iVar.e).intValue();
        if (intValue == 0) {
            intValue = -1;
        }
        if (intValue2 == 0) {
            intValue2 = -1;
        }
        ViewPager2 viewPager2 = this.viewpager;
        if (viewPager2 != null) {
            viewPager2.setLayoutParams(new LinearLayout.LayoutParams(intValue, intValue2));
        }
        if (intValue == -1) {
            ViewGroup viewGroup = this.viewPagerParent;
            if (viewGroup != null) {
                layoutParams = viewGroup.getLayoutParams();
            } else {
                layoutParams = null;
            }
            j.c(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.setMarginStart(0);
            marginLayoutParams.setMarginEnd(0);
        }
    }

    private final ColorStateList getViewPagerBackgroundColorStateList(Context context) {
        ColorStateList valueOf = ColorStateList.valueOf(SeslThemeResourceHelper.Companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_viewpager_background, R.color.sesl_viewpager_background_dark), new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_viewpager_background_for_theme))));
        j.d(valueOf, "valueOf(\n            Ses…)\n            )\n        )");
        return valueOf;
    }

    private final int getViewPagerIndicatorOffColor(Context context) {
        return SeslThemeResourceHelper.Companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R$color.sesl_appbar_viewpager_indicator_off, R$color.sesl_appbar_viewpager_indicator_off_dark), new SeslThemeResourceColor$ThemeResourceColor(R$color.sesl_appbar_viewpager_indicator_off_for_theme, R$color.sesl_appbar_viewpager_indicator_off_dark_for_theme)));
    }

    private final int getViewPagerIndicatorOnColor(Context context) {
        return SeslThemeResourceHelper.Companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R$color.sesl_appbar_viewpager_indicator_on), new SeslThemeResourceColor$ThemeResourceColor(R$color.sesl_appbar_viewpager_indicator_on_for_theme)));
    }

    public final ViewGroup getBottomLayout() {
        return this.bottomLayout;
    }

    public final SeslIndicator getIndicator() {
        return this.indicator;
    }

    public final ViewGroup getViewPagerContainer() {
        return this.viewPagerContainer;
    }

    public final ViewGroup getViewPagerParent() {
        return this.viewPagerParent;
    }

    public final ViewPager2 getViewpager() {
        return this.viewpager;
    }

    public void inflate() {
        ViewGroup viewGroup;
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int i2;
        ViewGroup.MarginLayoutParams marginLayoutParams2;
        Drawable drawable;
        int i7 = 0;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.sesl_app_bar_viewpager, this, false);
        ViewGroup.LayoutParams layoutParams = null;
        if (inflate instanceof ViewGroup) {
            viewGroup = (ViewGroup) inflate;
        } else {
            viewGroup = null;
        }
        if (viewGroup != null) {
            this.viewpager = (ViewPager2) viewGroup.findViewById(R.id.app_bar_viewpager);
            this.viewPagerContainer = (ViewGroup) viewGroup.findViewById(R.id.app_bar_viewpager_container);
            this.viewPagerParent = (ViewGroup) viewGroup.findViewById(R.id.app_bar_viewpager_parent);
            this.bottomLayout = (ViewGroup) viewGroup.findViewById(R.id.bottom_layout);
            Context context = getContext();
            j.d(context, "context");
            SeslIndicator seslIndicator = new SeslIndicator(context, (AttributeSet) null, 2, (e) null);
            seslIndicator.setOnItemClickListener(new d(this));
            this.indicator = seslIndicator;
            ViewPager2 viewPager2 = this.viewpager;
            if (viewPager2 != null) {
                viewPager2.seslSetSuggestionPaging(true);
                Drawable drawable2 = viewPager2.getContext().getDrawable(R.drawable.sesl_viewpager_background);
                if (drawable2 != null) {
                    drawable = drawable2.mutate();
                } else {
                    drawable = null;
                }
                viewPager2.setBackground(drawable);
            }
            ViewPager2 viewPager22 = this.viewpager;
            j.b(viewPager22);
            ViewCompat.setAccessibilityDelegate(viewPager22, new T1.e(this));
            adjustViewPagerLayout();
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.sesl_appbar_viewpager_indicator_height);
            ViewGroup viewGroup2 = this.bottomLayout;
            if (viewGroup2 != null) {
                ViewGroup.LayoutParams layoutParams2 = viewGroup2.getLayoutParams();
                if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                    marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams2;
                } else {
                    marginLayoutParams = null;
                }
                if (marginLayoutParams != null) {
                    i2 = marginLayoutParams.bottomMargin;
                } else {
                    i2 = 0;
                }
                ViewGroup viewGroup3 = this.bottomLayout;
                j.b(viewGroup3);
                ViewGroup.LayoutParams layoutParams3 = viewGroup3.getLayoutParams();
                if (layoutParams3 instanceof ViewGroup.MarginLayoutParams) {
                    marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams3;
                } else {
                    marginLayoutParams2 = null;
                }
                if (marginLayoutParams2 != null) {
                    i7 = marginLayoutParams2.topMargin;
                }
                i7 += i2;
            }
            int i8 = dimensionPixelSize + i7;
            ViewGroup viewGroup4 = this.viewPagerParent;
            if (viewGroup4 != null) {
                layoutParams = viewGroup4.getLayoutParams();
            }
            j.c(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = i8;
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams4.gravity = 17;
            ViewGroup viewGroup5 = this.bottomLayout;
            if (viewGroup5 != null) {
                viewGroup5.addView(this.indicator, layoutParams4);
            }
            Context context2 = getContext();
            j.d(context2, "context");
            updateResource(context2);
            addView(viewGroup);
        }
    }

    public final void setBottomLayout(ViewGroup viewGroup) {
        this.bottomLayout = viewGroup;
    }

    public final void setIndicator(SeslIndicator seslIndicator) {
        this.indicator = seslIndicator;
    }

    public final void setViewPagerContainer(ViewGroup viewGroup) {
        this.viewPagerContainer = viewGroup;
    }

    public final void setViewPagerParent(ViewGroup viewGroup) {
        this.viewPagerParent = viewGroup;
    }

    public final void setViewpager(ViewPager2 viewPager2) {
        this.viewpager = viewPager2;
    }

    public void updateResource(Context context) {
        Drawable drawable;
        Drawable mutate;
        j.e(context, "context");
        ViewPager2 viewPager2 = this.viewpager;
        if (viewPager2 != null) {
            adjustViewPagerLayout();
            viewPager2.setBackgroundTintList(getViewPagerBackgroundColorStateList(context));
            RecyclerView.Adapter adapter = viewPager2.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
        SeslIndicator seslIndicator = this.indicator;
        if (seslIndicator != null) {
            int i2 = R$drawable.sesl_viewpager_indicator_on_off;
            Drawable drawable2 = context.getDrawable(i2);
            Drawable drawable3 = null;
            if (drawable2 == null || (drawable = drawable2.mutate()) == null) {
                drawable = null;
            } else {
                drawable.setTint(getViewPagerIndicatorOffColor(context));
            }
            seslIndicator.setDefaultCircle(drawable);
            Drawable drawable4 = context.getDrawable(i2);
            if (!(drawable4 == null || (mutate = drawable4.mutate()) == null)) {
                mutate.setTint(getViewPagerIndicatorOnColor(context));
                drawable3 = mutate;
            }
            seslIndicator.setSelectCircle(drawable3);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewPagerAppBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
        inflate();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ViewPagerAppBarView(Context context, AttributeSet attributeSet, int i2, e eVar) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }
}
