package com.google.android.material.oneui.floatingactioncontainer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.SeslScrollable;
import com.google.android.material.appbar.AppBarLayout;
import com.sec.android.gallery3d.R;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import q2.u;
import r2.C0269a;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0017\u0018\u00002\u00020\u0001:\u0001\u0017B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00138VX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/google/android/material/oneui/floatingactioncontainer/FloatingBottomLayout;", "Lq2/u;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;", "getBehavior", "()Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;", "Landroid/view/View;", "getBottomBar", "()Landroid/view/View;", "", "offset", "Lme/x;", "setCustomGoToTopOffset", "(Ljava/lang/Integer;)V", "", "getLogTag", "()Ljava/lang/String;", "logTag", "FloatingBottomBarBehavior", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingBottomLayout extends u {
    public Integer L;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0019\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/google/android/material/oneui/floatingactioncontainer/FloatingBottomLayout$FloatingBottomBarBehavior;", "T", "Lcom/google/android/material/oneui/floatingactioncontainer/FloatingBottomLayout;", "Lcom/google/android/material/oneui/floatingactioncontainer/FloatingGroupLayout$FloatingActionBehavior;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FloatingBottomBarBehavior<T extends FloatingBottomLayout> extends FloatingGroupLayout$FloatingActionBehavior<T> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FloatingBottomBarBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            j.e(context, "context");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FloatingBottomLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
    }

    public final void c() {
        int i2;
        super.c();
        C0269a floatingScrollableManager$material_release = getFloatingScrollableManager$material_release();
        floatingScrollableManager$material_release.m = p(getVisibility());
        floatingScrollableManager$material_release.k();
        if (getFloatingScrollableManager$material_release().e.g() != null) {
            getFloatingScrollableManager$material_release().h(-1, getMeasuredHeight(), -1);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_floating_bottom_layout_top_bottom_padding_for_go_to_top);
            C0269a floatingScrollableManager$material_release2 = getFloatingScrollableManager$material_release();
            if (getVisibility() == 0) {
                i2 = (getHeight() - getPaddingBottom()) + dimensionPixelSize;
            } else {
                i2 = 0;
            }
            floatingScrollableManager$material_release2.i(i2);
        }
    }

    public CoordinatorLayout.Behavior<?> getBehavior() {
        Context context = getContext();
        j.d(context, "context");
        return new FloatingBottomBarBehavior(context, getAttrs());
    }

    public final View getBottomBar() {
        View childAt = getChildAt(0);
        j.d(childAt, "getChildAt(0)");
        return childAt;
    }

    public String getLogTag() {
        return "FloatingBottomLayout";
    }

    public /* bridge */ /* synthetic */ String getPrefix() {
        return "";
    }

    public /* bridge */ /* synthetic */ String getVersion() {
        return "[sesl8-material:2.0.67]";
    }

    public final void k(AppBarLayout appBarLayout, int i2) {
        if (i2 != 0) {
            i();
        }
    }

    public final void onDetachedFromWindow() {
        getFloatingScrollableManager$material_release().h(-1, 0, -1);
        getFloatingScrollableManager$material_release().i(0);
        super.onDetachedFromWindow();
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        c();
        super.onLayout(z, i2, i7, i8, i10);
    }

    public final void onVisibilityChanged(View view, int i2) {
        int i7;
        j.e(view, "changedView");
        super.onVisibilityChanged(view, i2);
        if (view.equals(this)) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_floating_bottom_layout_top_bottom_padding_for_go_to_top);
            C0269a floatingScrollableManager$material_release = getFloatingScrollableManager$material_release();
            floatingScrollableManager$material_release.m = p(i2);
            floatingScrollableManager$material_release.k();
            C0269a floatingScrollableManager$material_release2 = getFloatingScrollableManager$material_release();
            if (i2 == 0) {
                i7 = (getHeight() - getPaddingBottom()) + dimensionPixelSize;
            } else {
                i7 = 0;
            }
            floatingScrollableManager$material_release2.i(i7);
        }
    }

    public final int p(int i2) {
        int i7;
        Integer num;
        SeslScrollable g = getFloatingScrollableManager$material_release().e.g();
        if (g != null) {
            i7 = g.seslGetGoToTopDefaultBottomPadding();
        } else {
            i7 = 0;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_floating_bottom_layout_top_bottom_padding_for_go_to_top);
        Integer num2 = this.L;
        if (num2 != null) {
            num = Integer.valueOf(num2.intValue() - i7);
        } else {
            num = null;
        }
        int measuredHeight = (dimensionPixelSize * 2) + (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - i7);
        if (num != null) {
            return num.intValue();
        }
        if (i2 == 0) {
            return measuredHeight;
        }
        return 0;
    }

    public final void setCustomGoToTopOffset(Integer num) {
        this.L = num;
        C0269a floatingScrollableManager$material_release = getFloatingScrollableManager$material_release();
        floatingScrollableManager$material_release.m = p(getVisibility());
        floatingScrollableManager$material_release.k();
    }
}
