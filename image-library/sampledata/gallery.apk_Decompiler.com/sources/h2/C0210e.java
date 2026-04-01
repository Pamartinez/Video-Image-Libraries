package h2;

import android.view.View;
import android.view.ViewGroup;
import c2.j;

/* renamed from: h2.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0210e extends ViewGroup {
    public int d;
    public int e;
    public boolean f;

    public int getItemSpacing() {
        return this.e;
    }

    public int getLineSpacing() {
        return this.d;
    }

    public int getRowCount() {
        return 0;
    }

    public void onMeasure(int i2, int i7) {
        int i8;
        int i10;
        int i11;
        int i12;
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i7);
        int mode2 = View.MeasureSpec.getMode(i7);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            i8 = size;
        } else {
            i8 = Integer.MAX_VALUE;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = i8 - getPaddingRight();
        int i13 = paddingTop;
        int i14 = 0;
        for (int i15 = 0; i15 < getChildCount(); i15++) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() == 8) {
                int i16 = i2;
                int i17 = i7;
            } else {
                measureChild(childAt, i2, i7);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i11 = marginLayoutParams.leftMargin;
                    i12 = marginLayoutParams.rightMargin;
                } else {
                    i12 = 0;
                    i11 = 0;
                }
                int i18 = i12;
                if (childAt.getMeasuredWidth() + paddingLeft + i11 > paddingRight && !((j) this).f) {
                    paddingLeft = getPaddingLeft();
                    i13 = paddingTop + this.d;
                }
                int measuredWidth = childAt.getMeasuredWidth() + paddingLeft + i11;
                int measuredHeight = childAt.getMeasuredHeight() + i13;
                if (measuredWidth > i14) {
                    i14 = measuredWidth;
                }
                int measuredWidth2 = childAt.getMeasuredWidth() + i11 + i18 + this.e + paddingLeft;
                if (i15 == getChildCount() - 1) {
                    i14 += i18;
                }
                paddingLeft = measuredWidth2;
                paddingTop = measuredHeight;
            }
        }
        int paddingRight2 = getPaddingRight() + i14;
        int paddingBottom = getPaddingBottom() + paddingTop;
        if (mode != Integer.MIN_VALUE) {
            i10 = 1073741824;
            if (mode != 1073741824) {
                size = paddingRight2;
            }
        } else {
            i10 = 1073741824;
            size = Math.min(paddingRight2, size);
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(paddingBottom, size2);
        } else if (mode2 != i10) {
            size2 = paddingBottom;
        }
        setMeasuredDimension(size, size2);
    }

    public void setItemSpacing(int i2) {
        this.e = i2;
    }

    public void setLineSpacing(int i2) {
        this.d = i2;
    }

    public void setSingleLine(boolean z) {
        this.f = z;
    }
}
