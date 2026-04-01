package com.google.android.material.chip;

import B2.k;
import Fb.f;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.appcompat.R$interpolator;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import c2.j;
import c2.n;
import c2.o;
import c2.p;
import com.sec.android.gallery3d.R;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslChipGroup extends j {
    public static final /* synthetic */ int r = 0;
    public boolean m = true;
    public final LayoutTransition n;

    /* renamed from: o  reason: collision with root package name */
    public int f1443o;

    /* renamed from: p  reason: collision with root package name */
    public int f1444p;
    public int q;

    public SeslChipGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutTransition layoutTransition = new LayoutTransition();
        this.n = layoutTransition;
        this.q = 0;
        getResources().getDimension(R.dimen.chip_start_width);
        setLayoutDirection(TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()));
        layoutTransition.enableTransitionType(2);
        layoutTransition.enableTransitionType(3);
        layoutTransition.enableTransitionType(4);
        layoutTransition.enableTransitionType(0);
        layoutTransition.enableTransitionType(1);
        layoutTransition.setStartDelay(2, 0);
        layoutTransition.setStartDelay(3, 0);
        layoutTransition.setStartDelay(4, 0);
        layoutTransition.setStartDelay(0, 0);
        layoutTransition.setStartDelay(1, 0);
        int integer = getContext().getResources().getInteger(R.integer.sesl_chip_default_anim_duration);
        p b = p.b(0.0f, 1.0f);
        long j2 = (long) integer;
        b.setDuration(j2);
        b.setStartDelay(0);
        b.addUpdateListener(new f(1));
        b.addListener(getAddRemAnimListener());
        layoutTransition.setAnimator(2, b);
        p b5 = p.b(1.0f, 0.0f);
        b5.setDuration(j2);
        b5.addUpdateListener(new f(2));
        b5.addListener(getAddRemAnimListener());
        layoutTransition.setAnimator(3, b5);
        Interpolator loadInterpolator = AnimationUtils.loadInterpolator(getContext(), R$interpolator.sesl_chip_default_interpolator);
        layoutTransition.setInterpolator(3, loadInterpolator);
        layoutTransition.setInterpolator(2, loadInterpolator);
        layoutTransition.setInterpolator(4, loadInterpolator);
        layoutTransition.setInterpolator(0, loadInterpolator);
        layoutTransition.setInterpolator(1, loadInterpolator);
        layoutTransition.addTransitionListener(getChipTransitionListener());
        setLayoutTransition((LayoutTransition) null);
    }

    private AnimatorListenerAdapter getAddRemAnimListener() {
        return new AnimatorListenerAdapter();
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [android.animation.LayoutTransition$TransitionListener, java.lang.Object] */
    private LayoutTransition.TransitionListener getChipTransitionListener() {
        return new Object();
    }

    public final void a() {
        if (c()) {
            int height = getHeight();
            int b = b((float) getWidth()) - height;
            if (((float) Math.abs(b)) >= getContext().getResources().getDimension(R.dimen.chip_height)) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
                ofFloat.setDuration((long) getContext().getResources().getInteger(R.integer.sesl_chip_default_anim_duration));
                ofFloat.setInterpolator(AnimationUtils.loadInterpolator(getContext(), R$interpolator.sesl_chip_default_interpolator));
                ofFloat.addListener(new k(3, this));
                ofFloat.addUpdateListener(new c2.k(this, height, b, 0));
                ofFloat.start();
                return;
            }
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = -2;
        this.q = 0;
        setLayoutParams(layoutParams);
    }

    public final void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            setLayoutTransition(this.n);
        } else {
            setLayoutTransition((LayoutTransition) null);
        }
        super.addView(view, i2, layoutParams);
        if (c()) {
            setLayoutTransition((LayoutTransition) null);
        }
        a();
        if (view instanceof Chip) {
            Chip chip = (Chip) view;
            if (this.m) {
                int i7 = this.f1443o;
                if (i7 > 0) {
                    chip.setMaxWidth(i7);
                }
                chip.setEllipsize(TextUtils.TruncateAt.END);
            }
        }
    }

    public final int b(float f) {
        int i2;
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        int paddingStart = getPaddingStart();
        int paddingEnd = getPaddingEnd();
        int chipSpacingHorizontal = getChipSpacingHorizontal();
        int width = getChildAt(0).getWidth() + paddingStart + paddingEnd + chipSpacingHorizontal;
        int i7 = 1;
        for (int i8 = 1; i8 < childCount; i8++) {
            int intrinsicWidth = ((Chip) getChildAt(i8)).getChipDrawable().getIntrinsicWidth();
            if (((float) (width + intrinsicWidth)) < f) {
                i2 = intrinsicWidth + chipSpacingHorizontal + width;
            } else {
                i2 = intrinsicWidth + chipSpacingHorizontal + paddingStart + paddingEnd;
                i7++;
            }
            width = i2;
        }
        int chipSpacingVertical = getChipSpacingVertical();
        return (getPaddingTop() + (getPaddingBottom() + ((getChildAt(0).getHeight() + chipSpacingVertical) * i7))) - chipSpacingVertical;
    }

    public final boolean c() {
        if (getHeight() == b((float) getWidth())) {
            return false;
        }
        boolean z = this.f;
        if (!z) {
            return true;
        }
        if (!z || getChildCount() != 0) {
            return false;
        }
        return true;
    }

    public final void d() {
        this.q = getHeight();
    }

    public int getRowCount() {
        return this.f1444p;
    }

    public int getTotalWidth() {
        int paddingEnd = getPaddingEnd() + getPaddingStart();
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i2 = 0; i2 < childCount; i2++) {
                paddingEnd += getChildAt(i2).getWidth();
            }
            if (childCount > 1) {
                return ((childCount - 2) * getChipSpacingHorizontal()) + paddingEnd;
            }
        }
        return paddingEnd;
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        boolean z3;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15 = 0;
        if (getChildCount() == 0) {
            this.f1444p = 0;
            return;
        }
        boolean z7 = true;
        this.f1444p = 1;
        if (ViewCompat.getLayoutDirection(this) == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            i11 = getPaddingRight();
        } else {
            i11 = getPaddingLeft();
        }
        if (z3) {
            i12 = getPaddingLeft();
        } else {
            i12 = getPaddingRight();
        }
        int paddingTop = getPaddingTop();
        int lineSpacing = getLineSpacing();
        int itemSpacing = getItemSpacing();
        int i16 = i8 - i2;
        int i17 = i16 - i12;
        if (!z3) {
            i16 = i17;
        }
        int i18 = 0;
        int i19 = i11;
        int i20 = paddingTop;
        while (i18 < getChildCount()) {
            View childAt = getChildAt(i18);
            boolean z9 = z7;
            if (childAt.getVisibility() == 8) {
                childAt.setTag(R.id.row_index_key, -1);
            } else {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i13 = MarginLayoutParamsCompat.getMarginStart(marginLayoutParams);
                    i14 = MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams);
                } else {
                    i14 = i15;
                    i13 = i14;
                }
                int measuredWidth = childAt.getMeasuredWidth() + i19 + i13;
                if (!this.f && measuredWidth > i17) {
                    i20 = paddingTop + lineSpacing;
                    this.f1444p++;
                    i19 = i11;
                }
                childAt.setTag(R.id.row_index_key, Integer.valueOf(this.f1444p - 1));
                int i21 = i19 + i13;
                int measuredWidth2 = childAt.getMeasuredWidth() + i21;
                paddingTop = childAt.getMeasuredHeight() + i20;
                if (z3) {
                    childAt.layout(i16 - measuredWidth2, i20, (i16 - i19) - i13, paddingTop);
                } else {
                    childAt.layout(i21, i20, measuredWidth2, paddingTop);
                }
                i19 += childAt.getMeasuredWidth() + i13 + i14 + itemSpacing;
            }
            i18++;
            z7 = z9;
            i15 = 0;
        }
    }

    public final void onMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
        if (getChildCount() <= 0) {
            setMeasuredDimension(getWidth(), this.q);
        }
    }

    public final void removeAllViews() {
        d();
        super.removeAllViews();
        a();
    }

    public final void removeAllViewsInLayout() {
        d();
        super.removeAllViewsInLayout();
        a();
    }

    public final void removeView(View view) {
        if (getChildCount() > 1) {
            setLayoutTransition(this.n);
        } else {
            setLayoutTransition((LayoutTransition) null);
        }
        d();
        super.removeView(view);
        a();
    }

    public final void removeViewAt(int i2) {
        d();
        super.removeViewAt(i2);
        a();
    }

    public final void removeViewInLayout(View view) {
        d();
        super.removeViewInLayout(view);
        a();
    }

    public final void removeViews(int i2, int i7) {
        d();
        super.removeViews(i2, i7);
        a();
    }

    public final void removeViewsInLayout(int i2, int i7) {
        d();
        super.removeViewsInLayout(i2, i7);
        a();
    }

    public void setDynamicTruncation(boolean z) {
        this.m = z;
        Log.i("SeslChipGroup", "dynamic truncation state: " + z);
    }

    public void setMaxChipWidth(int i2) {
        this.f1443o = i2 - ((getPaddingEnd() + getPaddingStart()) + getResources().getDimensionPixelSize(R.dimen.expansion_button_size));
    }

    public void setOnChipAddListener(n nVar) {
    }

    public void setOnChipRemovedListener(o oVar) {
    }
}
