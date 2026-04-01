package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$id;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.ViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlertDialogLayout extends LinearLayoutCompat {
    public AlertDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void forceUniformWidth(int i2, int i7) {
        int i8;
        AlertDialogLayout alertDialogLayout;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        int i10 = 0;
        while (i10 < i2) {
            View childAt = this.getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) childAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i11 = layoutParams.height;
                    layoutParams.height = childAt.getMeasuredHeight();
                    alertDialogLayout = this;
                    i8 = i7;
                    alertDialogLayout.measureChildWithMargins(childAt, makeMeasureSpec, 0, i8, 0);
                    layoutParams.height = i11;
                    i10++;
                    this = alertDialogLayout;
                    i7 = i8;
                }
            }
            alertDialogLayout = this;
            i8 = i7;
            i10++;
            this = alertDialogLayout;
            i7 = i8;
        }
    }

    private static int resolveMinimumHeight(View view) {
        int minimumHeight = ViewCompat.getMinimumHeight(view);
        if (minimumHeight > 0) {
            return minimumHeight;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                return resolveMinimumHeight(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }

    private void setChildFrame(View view, int i2, int i7, int i8, int i10) {
        view.layout(i2, i7, i8 + i2, i10 + i7);
    }

    private boolean tryOnMeasure(int i2, int i7) {
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14 = i2;
        int i15 = i7;
        int childCount = getChildCount();
        View view = null;
        View view2 = null;
        View view3 = null;
        for (int i16 = 0; i16 < childCount; i16++) {
            View childAt = getChildAt(i16);
            if (childAt.getVisibility() != 8) {
                int id = childAt.getId();
                if (id == R$id.topPanel) {
                    view = childAt;
                } else if (id == R$id.buttonPanel) {
                    view2 = childAt;
                } else if ((id != R$id.contentPanel && id != R$id.customPanel) || view3 != null) {
                    return false;
                } else {
                    view3 = childAt;
                }
            }
        }
        int mode = View.MeasureSpec.getMode(i15);
        int size = View.MeasureSpec.getSize(i15);
        int mode2 = View.MeasureSpec.getMode(i14);
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (view != null) {
            view.measure(i14, 0);
            paddingBottom += view.getMeasuredHeight();
            i8 = View.combineMeasuredStates(0, view.getMeasuredState());
        } else {
            i8 = 0;
        }
        if (view2 != null) {
            view2.measure(i14, 0);
            i11 = resolveMinimumHeight(view2);
            i10 = view2.getMeasuredHeight() - i11;
            paddingBottom += i11;
            i8 = View.combineMeasuredStates(i8, view2.getMeasuredState());
        } else {
            i11 = 0;
            i10 = 0;
        }
        if (view3 != null) {
            if (mode == 0) {
                i13 = 0;
            } else {
                i13 = View.MeasureSpec.makeMeasureSpec(Math.max(0, size - paddingBottom), mode);
            }
            view3.measure(i14, i13);
            i12 = view3.getMeasuredHeight();
            paddingBottom += i12;
            i8 = View.combineMeasuredStates(i8, view3.getMeasuredState());
        } else {
            i12 = 0;
        }
        int i17 = size - paddingBottom;
        if (view2 != null) {
            int i18 = paddingBottom - i11;
            int min = Math.min(i17, i10);
            if (min > 0) {
                i17 -= min;
                i11 += min;
            }
            view2.measure(i14, View.MeasureSpec.makeMeasureSpec(i11, 1073741824));
            paddingBottom = i18 + view2.getMeasuredHeight();
            i8 = View.combineMeasuredStates(i8, view2.getMeasuredState());
        }
        if (view3 != null && i17 > 0) {
            view3.measure(i14, View.MeasureSpec.makeMeasureSpec(i12 + i17, mode));
            paddingBottom = (paddingBottom - i12) + view3.getMeasuredHeight();
            i8 = View.combineMeasuredStates(i8, view3.getMeasuredState());
        }
        int i19 = 0;
        for (int i20 = 0; i20 < childCount; i20++) {
            View childAt2 = getChildAt(i20);
            if (childAt2.getVisibility() != 8) {
                i19 = Math.max(i19, childAt2.getMeasuredWidth());
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(getPaddingRight() + getPaddingLeft() + i19, i14, i8), View.resolveSizeAndState(paddingBottom, i15, 0));
        if (mode2 == 1073741824) {
            return true;
        }
        forceUniformWidth(childCount, i15);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r18, int r19, int r20, int r21, int r22) {
        /*
            r17 = this;
            r0 = r17
            int r6 = r0.getPaddingLeft()
            int r1 = r21 - r19
            int r2 = r0.getPaddingRight()
            int r7 = r1 - r2
            int r1 = r1 - r6
            int r2 = r0.getPaddingRight()
            int r8 = r1 - r2
            int r1 = r0.getMeasuredHeight()
            int r9 = r0.getChildCount()
            int r2 = r0.getGravity()
            r3 = r2 & 112(0x70, float:1.57E-43)
            r4 = 8388615(0x800007, float:1.1754953E-38)
            r10 = r2 & r4
            r2 = 16
            r11 = 2
            if (r3 == r2) goto L_0x0041
            r2 = 80
            if (r3 == r2) goto L_0x0036
            int r1 = r0.getPaddingTop()
            goto L_0x004b
        L_0x0036:
            int r2 = r0.getPaddingTop()
            int r2 = r2 + r22
            int r2 = r2 - r20
            int r1 = r2 - r1
            goto L_0x004b
        L_0x0041:
            int r2 = r0.getPaddingTop()
            int r3 = r22 - r20
            int r3 = r3 - r1
            int r3 = r3 / r11
            int r1 = r3 + r2
        L_0x004b:
            android.graphics.drawable.Drawable r2 = r0.getDividerDrawable()
            r3 = 0
            if (r2 != 0) goto L_0x0054
            r12 = r3
            goto L_0x0059
        L_0x0054:
            int r2 = r2.getIntrinsicHeight()
            r12 = r2
        L_0x0059:
            r13 = r3
        L_0x005a:
            if (r13 >= r9) goto L_0x00c1
            r2 = r1
            android.view.View r1 = r0.getChildAt(r13)
            if (r1 == 0) goto L_0x00bb
            int r3 = r1.getVisibility()
            r4 = 8
            if (r3 == r4) goto L_0x00bb
            int r4 = r1.getMeasuredWidth()
            int r5 = r1.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r3 = r1.getLayoutParams()
            r14 = r3
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r14 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r14
            int r3 = r14.gravity
            if (r3 >= 0) goto L_0x007f
            r3 = r10
        L_0x007f:
            int r15 = r0.getLayoutDirection()
            int r3 = androidx.core.view.GravityCompat.getAbsoluteGravity(r3, r15)
            r3 = r3 & 7
            r15 = 1
            if (r3 == r15) goto L_0x0099
            r15 = 5
            if (r3 == r15) goto L_0x0093
            int r3 = r14.leftMargin
            int r3 = r3 + r6
            goto L_0x00a3
        L_0x0093:
            int r3 = r7 - r4
            int r15 = r14.rightMargin
        L_0x0097:
            int r3 = r3 - r15
            goto L_0x00a3
        L_0x0099:
            int r3 = c0.C0086a.D(r8, r4, r11, r6)
            int r15 = r14.leftMargin
            int r3 = r3 + r15
            int r15 = r14.rightMargin
            goto L_0x0097
        L_0x00a3:
            boolean r15 = r0.hasDividerBeforeChildAt(r13)
            if (r15 == 0) goto L_0x00aa
            int r2 = r2 + r12
        L_0x00aa:
            int r15 = r14.topMargin
            int r2 = r2 + r15
            r16 = r3
            r3 = r2
            r2 = r16
            r0.setChildFrame(r1, r2, r3, r4, r5)
            int r0 = r14.bottomMargin
            int r5 = r5 + r0
            int r5 = r5 + r3
            r1 = r5
            goto L_0x00bc
        L_0x00bb:
            r1 = r2
        L_0x00bc:
            int r13 = r13 + 1
            r0 = r17
            goto L_0x005a
        L_0x00c1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AlertDialogLayout.onLayout(boolean, int, int, int, int):void");
    }

    public void onMeasure(int i2, int i7) {
        if (!tryOnMeasure(i2, i7)) {
            super.onMeasure(i2, i7);
        }
    }
}
