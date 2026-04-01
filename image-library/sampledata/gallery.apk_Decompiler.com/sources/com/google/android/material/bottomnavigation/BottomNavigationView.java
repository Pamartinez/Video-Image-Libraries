package com.google.android.material.bottomnavigation;

import Q1.a;
import X1.c;
import X1.e;
import X1.f;
import X1.g;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.appcompat.oneui.common.BlurSupportable;
import androidx.appcompat.oneui.common.internal.policy.BlurInfoState;
import androidx.appcompat.oneui.common.internal.semblurinfo.SemBlurInfoStateBuilder;
import androidx.appcompat.oneui.common.internal.util.ContextHelperKt;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.oneui.common.internal.semblurinfo.SemBlurInfoState;
import com.sec.android.gallery3d.R;
import h2.p;
import k2.h;
import k2.s;
import l2.C0230c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomNavigationView extends s implements BlurSupportable {
    private Drawable mBackgroundDrawable;
    private SemBlurInfoState mBlurInfo;
    private int mBlurMode = 2;
    boolean mIsFloatingStyle;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListenerForTD;

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Context context2 = getContext();
        p.a(context2, attributeSet, R.attr.bottomNavigationStyle, com.samsung.android.photoremaster.engine.R.style.Widget_Design_BottomNavigationView);
        int[] iArr = a.d;
        AttributeSet attributeSet2 = attributeSet;
        p.b(context2, attributeSet2, iArr, R.attr.bottomNavigationStyle, com.samsung.android.photoremaster.engine.R.style.Widget_Design_BottomNavigationView, new int[0]);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context2, attributeSet2, iArr, R.attr.bottomNavigationStyle, com.samsung.android.photoremaster.engine.R.style.Widget_Design_BottomNavigationView);
        setItemHorizontalTranslationEnabled(obtainStyledAttributes.getBoolean(5, true));
        obtainStyledAttributes.getBoolean(4, true);
        boolean z = obtainStyledAttributes.getBoolean(8, false);
        boolean z3 = obtainStyledAttributes.getBoolean(6, false);
        if (z3) {
            applyBlurInfo(context2);
        }
        this.mIsFloatingStyle = z;
        if (z && z3 && !ContextHelperKt.isDefaultTheme(context2)) {
            setBackground(context2.getDrawable(R.drawable.sesl_bottom_navigation_floating_background_for_theme));
        }
        MenuView menuView = getMenuView();
        if (menuView instanceof c) {
            c cVar = (c) menuView;
            cVar.f895f0 = z;
            if (this.mIsFloatingStyle && !ContextHelperKt.isDefaultTheme(context2)) {
                ColorStateList valueOf = ColorStateList.valueOf(getResources().getColor(R.color.sesl_bottom_navigation_floating_icon_text_for_theme));
                cVar.setIconTintList(valueOf);
                setItemTextColor(valueOf);
            }
            cVar.setViewTypeChangeListener(new G0.c(9, (Object) this));
            a(cVar.getViewType());
        }
        obtainStyledAttributes.recycle();
    }

    public final void a(int i2) {
        C0230c cVar;
        boolean z = this.mIsFloatingStyle;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    cVar = new C0230c(0);
                } else {
                    cVar = new C0230c(2);
                }
            } else if (z) {
                cVar = new C0230c(1);
            } else {
                cVar = new C0230c(1);
            }
        } else if (z) {
            cVar = new C0230c(0);
        } else {
            cVar = new C0230c(0);
        }
        cVar.a(this);
        MenuView menuView = getMenuView();
        if (menuView instanceof c) {
            ((c) menuView).setStrategy(cVar);
            if (cVar.i()) {
                setClipToPadding(false);
                setClipChildren(false);
            }
        }
    }

    public boolean applyBlurInfo(Context context) {
        if (Build.VERSION.SDK_INT < 35) {
            return false;
        }
        clearBlurInfo(context);
        float dimension = context.getResources().getDimension(R.dimen.sesl_bottom_navigation_icon_only_mode_background_radius);
        SemBlurInfoStateBuilder generateFloatingComponentBlurInfoStateBuilder = BlurInfoState.INSTANCE.generateFloatingComponentBlurInfoStateBuilder(context, this.mBlurMode);
        Drawable drawable = this.mBackgroundDrawable;
        if (drawable != null) {
            generateFloatingComponentBlurInfoStateBuilder.nonBlurBackground(drawable);
        }
        SemBlurInfoState build = generateFloatingComponentBlurInfoStateBuilder.cornerRadius(dimension).build();
        if (build.applyBlurInfo(this)) {
            this.mBlurInfo = build;
        }
        return false;
    }

    public void clearBlurInfo(Context context) {
        SemBlurInfoState semBlurInfoState = this.mBlurInfo;
        if (semBlurInfoState != null) {
            semBlurInfoState.clearBlurInfo(this);
            this.mBlurInfo = null;
        }
    }

    public h createNavigationBarMenuView(Context context) {
        return new c(context);
    }

    public int getMaxItemCount() {
        return 5;
    }

    public void onMeasure(int i2, int i7) {
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(getPaddingBottom() + getPaddingTop() + getSuggestedMinimumHeight(), 1073741824));
    }

    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0) {
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver != null && this.mOnGlobalLayoutListenerForTD == null) {
                e eVar = new e(this, 0);
                this.mOnGlobalLayoutListenerForTD = eVar;
                viewTreeObserver.addOnGlobalLayoutListener(eVar);
            }
        } else if (this.mOnGlobalLayoutListenerForTD != null) {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.mOnGlobalLayoutListenerForTD);
            this.mOnGlobalLayoutListenerForTD = null;
        }
    }

    public void seslSetGroupDividerEnabled(boolean z) {
        super.seslSetGroupDividerEnabled(z);
    }

    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
        this.mBackgroundDrawable = drawable;
    }

    public void setBlurMode(int i2) {
        this.mBlurMode = i2;
        applyBlurInfo(getContext());
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        c cVar = (c) getMenuView();
        if (cVar.l0 != z) {
            cVar.setItemHorizontalTranslationEnabled(z);
            getPresenter().updateMenuView(false);
        }
    }

    @Deprecated
    public void setOnNavigationItemReselectedListener(f fVar) {
        setOnItemReselectedListener(fVar);
    }

    @Deprecated
    public void setOnNavigationItemSelectedListener(g gVar) {
        setOnItemSelectedListener(gVar);
    }
}
