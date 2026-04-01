package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BaselineLayout extends ViewGroup {
    public int d = -1;

    public BaselineLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public int getBaseline() {
        return this.d;
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int i11;
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = ((i8 - i2) - getPaddingRight()) - paddingLeft;
        int paddingTop = getPaddingTop();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int D = C0086a.D(paddingRight, measuredWidth, 2, paddingLeft);
                if (this.d == -1 || childAt.getBaseline() == -1) {
                    i11 = paddingTop;
                } else {
                    i11 = (this.d + paddingTop) - childAt.getBaseline();
                }
                childAt.layout(D, i11, measuredWidth + D, measuredHeight + i11);
            }
        }
    }

    public final void onMeasure(int i2, int i7) {
        int childCount = getChildCount();
        int i8 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = -1;
        int i13 = -1;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i2, i7);
                int baseline = childAt.getBaseline();
                if (baseline != -1) {
                    i12 = Math.max(i12, baseline);
                    i13 = Math.max(i13, childAt.getMeasuredHeight() - baseline);
                }
                i10 = Math.max(i10, childAt.getMeasuredWidth());
                i8 = Math.max(i8, childAt.getMeasuredHeight());
                i11 = View.combineMeasuredStates(i11, childAt.getMeasuredState());
            }
        }
        if (i12 != -1) {
            i8 = Math.max(i8, Math.max(i13, getPaddingBottom()) + i12);
            this.d = i12;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(i10, getSuggestedMinimumWidth()), i2, i11), View.resolveSizeAndState(Math.max(i8, getSuggestedMinimumHeight()), i7, i11 << 16));
    }
}
