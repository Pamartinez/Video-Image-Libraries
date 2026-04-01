package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ButtonBarLayout extends LinearLayout {
    private boolean mAllowStacking;
    private final int mButtonBarBottomMargin;
    private int mLastWidthSize = -1;
    private boolean mStacked;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int[] iArr = R$styleable.ButtonBarLayout;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        ViewCompat.saveAttributeDataForStyleable(this, context, iArr, attributeSet, obtainStyledAttributes, 0, 0);
        this.mAllowStacking = obtainStyledAttributes.getBoolean(R$styleable.ButtonBarLayout_allowStacking, true);
        obtainStyledAttributes.recycle();
        if (getOrientation() == 1) {
            setStacked(this.mAllowStacking);
        }
        this.mButtonBarBottomMargin = (int) getResources().getDimension(R$dimen.sesl_dialog_button_bar_margin_bottom);
    }

    private void applyButtonMargin() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof Button) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    layoutParams.width = -1;
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    if (i2 < childCount - 1) {
                        marginLayoutParams.setMargins(0, 0, 0, this.mButtonBarBottomMargin);
                    }
                    childAt.setLayoutParams(marginLayoutParams);
                }
            }
        }
    }

    private void clearButtonMargin() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof Button) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                layoutParams.width = -2;
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    if (i2 < childCount - 1) {
                        marginLayoutParams.setMargins(0, 0, 0, 0);
                    }
                    childAt.setLayoutParams(marginLayoutParams);
                }
            }
        }
    }

    private int getNextVisibleChildIndex(int i2) {
        int childCount = getChildCount();
        while (i2 < childCount) {
            if (getChildAt(i2).getVisibility() == 0 && (getChildAt(i2) instanceof Button)) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    private boolean isStacked() {
        return this.mStacked;
    }

    private void setDividerInvisible(int i2) {
        int childCount = getChildCount();
        while (i2 < childCount) {
            if (!(getChildAt(i2) instanceof Button)) {
                getChildAt(i2).setVisibility(8);
            }
            i2++;
        }
    }

    private void setDividerVisible(int i2) {
        int i7;
        int childCount = getChildCount();
        while (i2 < childCount) {
            if (!(getChildAt(i2) instanceof Button) && (i7 = i2 + 1) < childCount && (getChildAt(i7) instanceof Button) && getChildAt(i7).getVisibility() == 0) {
                getChildAt(i2).setVisibility(0);
            }
            i2++;
        }
    }

    private void setStacked(boolean z) {
        int i2;
        if (this.mStacked == z) {
            return;
        }
        if (!z || this.mAllowStacking) {
            this.mStacked = z;
            setOrientation(z ? 1 : 0);
            if (z) {
                i2 = 8388613;
            } else {
                i2 = 80;
            }
            setGravity(i2);
        }
    }

    public void onMeasure(int i2, int i7) {
        boolean z;
        int i8;
        boolean z3;
        int size = View.MeasureSpec.getSize(i2);
        int i10 = 0;
        if (this.mAllowStacking) {
            if (size > this.mLastWidthSize && isStacked()) {
                setStacked(false);
                setDividerVisible(getNextVisibleChildIndex(0));
            }
            this.mLastWidthSize = size;
        }
        if (isStacked() || View.MeasureSpec.getMode(i2) != 1073741824) {
            i8 = i2;
            z = false;
        } else {
            i8 = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z = true;
        }
        super.onMeasure(i8, i7);
        if (this.mAllowStacking && !isStacked()) {
            if ((getMeasuredWidthAndState() & -16777216) == 16777216) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                setStacked(true);
                setDividerInvisible(0);
                setGravity(17);
                z = true;
            }
            if (z3) {
                applyButtonMargin();
            } else {
                clearButtonMargin();
            }
        }
        if (z) {
            super.onMeasure(i2, i7);
        }
        int nextVisibleChildIndex = getNextVisibleChildIndex(0);
        if (nextVisibleChildIndex >= 0) {
            View childAt = getChildAt(nextVisibleChildIndex);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            i10 = childAt.getMeasuredHeight() + getPaddingTop() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (isStacked()) {
                int nextVisibleChildIndex2 = getNextVisibleChildIndex(nextVisibleChildIndex + 1);
                if (nextVisibleChildIndex2 >= 0) {
                    i10 = getChildAt(nextVisibleChildIndex2).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f)) + i10;
                }
            } else {
                i10 += getPaddingBottom();
            }
        }
        if (ViewCompat.getMinimumHeight(this) != i10) {
            setMinimumHeight(i10);
            if (i7 == 0 || z) {
                super.onMeasure(i2, i7);
            }
        }
        if (z) {
            super.onMeasure(i2, i7);
        }
    }

    public void setAllowStacking(boolean z) {
        if (this.mAllowStacking != z) {
            this.mAllowStacking = z;
            if (!z && isStacked()) {
                setStacked(false);
            }
            requestLayout();
        }
    }
}
