package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import c0.C0086a;
import java.util.ArrayList;
import java.util.List;
import q1.C0265a;
import q1.b;
import q1.c;
import q1.d;
import q1.e;
import q1.f;
import q1.g;
import q1.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlexboxLayout extends ViewGroup implements C0265a {
    private int mAlignContent;
    private int mAlignItems;
    private Drawable mDividerDrawableHorizontal;
    private Drawable mDividerDrawableVertical;
    private int mDividerHorizontalHeight;
    private int mDividerVerticalWidth;
    private int mFlexDirection;
    private List<c> mFlexLines = new ArrayList();
    private d mFlexLinesResult = new Object();
    private int mFlexWrap;
    private f mFlexboxHelper = new f(this);
    private int mJustifyContent;
    private int mMaxLine = -1;
    private SparseIntArray mOrderCache;
    private int[] mReorderedIndices;
    private int mShowDividerHorizontal;
    private int mShowDividerVertical;

    /* JADX WARNING: type inference failed for: r2v2, types: [q1.d, java.lang.Object] */
    public FlexboxLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.f1876a, 0, 0);
        this.mFlexDirection = obtainStyledAttributes.getInt(5, 0);
        this.mFlexWrap = obtainStyledAttributes.getInt(6, 0);
        this.mJustifyContent = obtainStyledAttributes.getInt(7, 0);
        this.mAlignItems = obtainStyledAttributes.getInt(1, 0);
        this.mAlignContent = obtainStyledAttributes.getInt(0, 0);
        this.mMaxLine = obtainStyledAttributes.getInt(8, -1);
        Drawable drawable = obtainStyledAttributes.getDrawable(2);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(3);
        if (drawable2 != null) {
            setDividerDrawableHorizontal(drawable2);
        }
        Drawable drawable3 = obtainStyledAttributes.getDrawable(4);
        if (drawable3 != null) {
            setDividerDrawableVertical(drawable3);
        }
        int i2 = obtainStyledAttributes.getInt(9, 0);
        if (i2 != 0) {
            this.mShowDividerVertical = i2;
            this.mShowDividerHorizontal = i2;
        }
        int i7 = obtainStyledAttributes.getInt(11, 0);
        if (i7 != 0) {
            this.mShowDividerVertical = i7;
        }
        int i8 = obtainStyledAttributes.getInt(10, 0);
        if (i8 != 0) {
            this.mShowDividerHorizontal = i8;
        }
        obtainStyledAttributes.recycle();
    }

    public final void a(Canvas canvas, boolean z, boolean z3) {
        int i2;
        int i7;
        int i8;
        int i10;
        int paddingLeft = getPaddingLeft();
        int max = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.mFlexLines.size();
        for (int i11 = 0; i11 < size; i11++) {
            c cVar = this.mFlexLines.get(i11);
            for (int i12 = 0; i12 < cVar.f1855h; i12++) {
                int i13 = cVar.f1858o + i12;
                View reorderedChildAt = getReorderedChildAt(i13);
                if (!(reorderedChildAt == null || reorderedChildAt.getVisibility() == 8)) {
                    g gVar = (g) reorderedChildAt.getLayoutParams();
                    if (e(i13, i12)) {
                        if (z) {
                            i10 = reorderedChildAt.getRight() + gVar.rightMargin;
                        } else {
                            i10 = (reorderedChildAt.getLeft() - gVar.leftMargin) - this.mDividerVerticalWidth;
                        }
                        d(canvas, i10, cVar.b, cVar.g);
                    }
                    if (i12 == cVar.f1855h - 1 && (this.mShowDividerVertical & 4) > 0) {
                        if (z) {
                            i8 = (reorderedChildAt.getLeft() - gVar.leftMargin) - this.mDividerVerticalWidth;
                        } else {
                            i8 = reorderedChildAt.getRight() + gVar.rightMargin;
                        }
                        d(canvas, i8, cVar.b, cVar.g);
                    }
                }
            }
            if (f(i11)) {
                if (z3) {
                    i7 = cVar.d;
                } else {
                    i7 = cVar.b - this.mDividerHorizontalHeight;
                }
                c(canvas, paddingLeft, i7, max);
            }
            if (g(i11) && (this.mShowDividerHorizontal & 4) > 0) {
                if (z3) {
                    i2 = cVar.b - this.mDividerHorizontalHeight;
                } else {
                    i2 = cVar.d;
                }
                c(canvas, paddingLeft, i2, max);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [q1.e, java.lang.Object] */
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(getChildCount());
        }
        f fVar = this.mFlexboxHelper;
        SparseIntArray sparseIntArray = this.mOrderCache;
        C0265a aVar = fVar.f1861a;
        int flexItemCount = aVar.getFlexItemCount();
        ArrayList f = fVar.f(flexItemCount);
        ? obj = new Object();
        if (view == null || !(layoutParams instanceof b)) {
            obj.e = 1;
        } else {
            obj.e = ((b) layoutParams).getOrder();
        }
        if (i2 == -1 || i2 == flexItemCount) {
            obj.d = flexItemCount;
        } else if (i2 < aVar.getFlexItemCount()) {
            obj.d = i2;
            for (int i7 = i2; i7 < flexItemCount; i7++) {
                ((e) f.get(i7)).d++;
            }
        } else {
            obj.d = flexItemCount;
        }
        f.add(obj);
        this.mReorderedIndices = f.r(flexItemCount + 1, f, sparseIntArray);
        super.addView(view, i2, layoutParams);
    }

    public final void b(Canvas canvas, boolean z, boolean z3) {
        int i2;
        int i7;
        int i8;
        int i10;
        int paddingTop = getPaddingTop();
        int max = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.mFlexLines.size();
        for (int i11 = 0; i11 < size; i11++) {
            c cVar = this.mFlexLines.get(i11);
            for (int i12 = 0; i12 < cVar.f1855h; i12++) {
                int i13 = cVar.f1858o + i12;
                View reorderedChildAt = getReorderedChildAt(i13);
                if (!(reorderedChildAt == null || reorderedChildAt.getVisibility() == 8)) {
                    g gVar = (g) reorderedChildAt.getLayoutParams();
                    if (e(i13, i12)) {
                        if (z3) {
                            i10 = reorderedChildAt.getBottom() + gVar.bottomMargin;
                        } else {
                            i10 = (reorderedChildAt.getTop() - gVar.topMargin) - this.mDividerHorizontalHeight;
                        }
                        c(canvas, cVar.f1853a, i10, cVar.g);
                    }
                    if (i12 == cVar.f1855h - 1 && (this.mShowDividerHorizontal & 4) > 0) {
                        if (z3) {
                            i8 = (reorderedChildAt.getTop() - gVar.topMargin) - this.mDividerHorizontalHeight;
                        } else {
                            i8 = reorderedChildAt.getBottom() + gVar.bottomMargin;
                        }
                        c(canvas, cVar.f1853a, i8, cVar.g);
                    }
                }
            }
            if (f(i11)) {
                if (z) {
                    i7 = cVar.f1854c;
                } else {
                    i7 = cVar.f1853a - this.mDividerVerticalWidth;
                }
                d(canvas, i7, paddingTop, max);
            }
            if (g(i11) && (this.mShowDividerVertical & 4) > 0) {
                if (z) {
                    i2 = cVar.f1853a - this.mDividerVerticalWidth;
                } else {
                    i2 = cVar.f1854c;
                }
                d(canvas, i2, paddingTop, max);
            }
        }
    }

    public final void c(Canvas canvas, int i2, int i7, int i8) {
        Drawable drawable = this.mDividerDrawableHorizontal;
        if (drawable != null) {
            drawable.setBounds(i2, i7, i8 + i2, this.mDividerHorizontalHeight + i7);
            this.mDividerDrawableHorizontal.draw(canvas);
        }
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof g;
    }

    public final void d(Canvas canvas, int i2, int i7, int i8) {
        Drawable drawable = this.mDividerDrawableVertical;
        if (drawable != null) {
            drawable.setBounds(i2, i7, this.mDividerVerticalWidth + i2, i8 + i7);
            this.mDividerDrawableVertical.draw(canvas);
        }
    }

    public final boolean e(int i2, int i7) {
        int i8 = 1;
        while (i8 <= i7) {
            View reorderedChildAt = getReorderedChildAt(i2 - i8);
            if (reorderedChildAt == null || reorderedChildAt.getVisibility() == 8) {
                i8++;
            } else if (isMainAxisDirectionHorizontal()) {
                if ((this.mShowDividerVertical & 2) != 0) {
                    return true;
                }
                return false;
            } else if ((this.mShowDividerHorizontal & 2) != 0) {
                return true;
            } else {
                return false;
            }
        }
        if (isMainAxisDirectionHorizontal()) {
            if ((this.mShowDividerVertical & 1) != 0) {
                return true;
            }
            return false;
        } else if ((this.mShowDividerHorizontal & 1) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public final boolean f(int i2) {
        if (i2 >= 0 && i2 < this.mFlexLines.size()) {
            int i7 = 0;
            while (i7 < i2) {
                if (this.mFlexLines.get(i7).a() <= 0) {
                    i7++;
                } else if (isMainAxisDirectionHorizontal()) {
                    if ((this.mShowDividerHorizontal & 2) != 0) {
                        return true;
                    }
                    return false;
                } else if ((this.mShowDividerVertical & 2) != 0) {
                    return true;
                } else {
                    return false;
                }
            }
            if (isMainAxisDirectionHorizontal()) {
                if ((this.mShowDividerHorizontal & 1) != 0) {
                    return true;
                }
                return false;
            } else if ((this.mShowDividerVertical & 1) != 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean g(int i2) {
        if (i2 >= 0 && i2 < this.mFlexLines.size()) {
            for (int i7 = i2 + 1; i7 < this.mFlexLines.size(); i7++) {
                if (this.mFlexLines.get(i7).a() > 0) {
                    return false;
                }
            }
            if (isMainAxisDirectionHorizontal()) {
                if ((this.mShowDividerHorizontal & 4) != 0) {
                    return true;
                }
                return false;
            } else if ((this.mShowDividerVertical & 4) != 0) {
                return true;
            }
        }
        return false;
    }

    public int getAlignContent() {
        return this.mAlignContent;
    }

    public int getAlignItems() {
        return this.mAlignItems;
    }

    public int getChildHeightMeasureSpec(int i2, int i7, int i8) {
        return ViewGroup.getChildMeasureSpec(i2, i7, i8);
    }

    public int getChildWidthMeasureSpec(int i2, int i7, int i8) {
        return ViewGroup.getChildMeasureSpec(i2, i7, i8);
    }

    public int getDecorationLengthCrossAxis(View view) {
        return 0;
    }

    public int getDecorationLengthMainAxis(View view, int i2, int i7) {
        int i8;
        int i10 = 0;
        if (isMainAxisDirectionHorizontal()) {
            if (e(i2, i7)) {
                i10 = this.mDividerVerticalWidth;
            }
            if ((this.mShowDividerVertical & 4) <= 0) {
                return i10;
            }
            i8 = this.mDividerVerticalWidth;
        } else {
            if (e(i2, i7)) {
                i10 = this.mDividerHorizontalHeight;
            }
            if ((this.mShowDividerHorizontal & 4) <= 0) {
                return i10;
            }
            i8 = this.mDividerHorizontalHeight;
        }
        return i10 + i8;
    }

    public Drawable getDividerDrawableHorizontal() {
        return this.mDividerDrawableHorizontal;
    }

    public Drawable getDividerDrawableVertical() {
        return this.mDividerDrawableVertical;
    }

    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    public View getFlexItemAt(int i2) {
        return getChildAt(i2);
    }

    public int getFlexItemCount() {
        return getChildCount();
    }

    public List<c> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.mFlexLines.size());
        for (c next : this.mFlexLines) {
            if (next.a() != 0) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public List<c> getFlexLinesInternal() {
        return this.mFlexLines;
    }

    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    public int getJustifyContent() {
        return this.mJustifyContent;
    }

    public int getLargestMainSize() {
        int i2 = Integer.MIN_VALUE;
        for (c cVar : this.mFlexLines) {
            i2 = Math.max(i2, cVar.e);
        }
        return i2;
    }

    public int getMaxLine() {
        return this.mMaxLine;
    }

    public View getReorderedChildAt(int i2) {
        if (i2 < 0) {
            return null;
        }
        int[] iArr = this.mReorderedIndices;
        if (i2 >= iArr.length) {
            return null;
        }
        return getChildAt(iArr[i2]);
    }

    public View getReorderedFlexItemAt(int i2) {
        return getReorderedChildAt(i2);
    }

    public int getShowDividerHorizontal() {
        return this.mShowDividerHorizontal;
    }

    public int getShowDividerVertical() {
        return this.mShowDividerVertical;
    }

    public int getSumOfCrossSize() {
        int i2;
        int i7;
        int size = this.mFlexLines.size();
        int i8 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            c cVar = this.mFlexLines.get(i10);
            if (f(i10)) {
                if (isMainAxisDirectionHorizontal()) {
                    i7 = this.mDividerHorizontalHeight;
                } else {
                    i7 = this.mDividerVerticalWidth;
                }
                i8 += i7;
            }
            if (g(i10)) {
                if (isMainAxisDirectionHorizontal()) {
                    i2 = this.mDividerHorizontalHeight;
                } else {
                    i2 = this.mDividerVerticalWidth;
                }
                i8 += i2;
            }
            i8 += cVar.g;
        }
        return i8;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void h(int r25, int r26, int r27, int r28, boolean r29) {
        /*
            r24 = this;
            r0 = r24
            int r1 = r0.getPaddingLeft()
            int r2 = r0.getPaddingRight()
            int r3 = r28 - r26
            int r4 = r27 - r25
            int r5 = r0.getPaddingBottom()
            int r3 = r3 - r5
            int r5 = r0.getPaddingTop()
            java.util.List<q1.c> r6 = r0.mFlexLines
            int r6 = r6.size()
            r8 = 0
        L_0x001e:
            if (r8 >= r6) goto L_0x020d
            java.util.List<q1.c> r9 = r0.mFlexLines
            java.lang.Object r9 = r9.get(r8)
            r12 = r9
            q1.c r12 = (q1.c) r12
            boolean r9 = r0.f(r8)
            if (r9 == 0) goto L_0x0033
            int r9 = r0.mDividerHorizontalHeight
            int r3 = r3 - r9
            int r5 = r5 + r9
        L_0x0033:
            r16 = r3
            int r3 = r0.mJustifyContent
            r9 = 4
            r10 = 2
            r11 = 0
            r13 = 1
            if (r3 == 0) goto L_0x00c7
            if (r3 == r13) goto L_0x00bd
            r14 = 1073741824(0x40000000, float:2.0)
            if (r3 == r10) goto L_0x00ab
            r15 = 3
            if (r3 == r15) goto L_0x0094
            if (r3 == r9) goto L_0x007a
            r14 = 5
            if (r3 != r14) goto L_0x0064
            int r3 = r12.a()
            if (r3 == 0) goto L_0x005b
            int r14 = r12.e
            int r14 = r4 - r14
            float r14 = (float) r14
            int r3 = r3 + 1
            float r3 = (float) r3
            float r14 = r14 / r3
            goto L_0x005c
        L_0x005b:
            r14 = r11
        L_0x005c:
            float r3 = (float) r1
            float r3 = r3 + r14
            int r15 = r4 - r2
            float r15 = (float) r15
            float r15 = r15 - r14
            goto L_0x00cc
        L_0x0064:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Invalid justifyContent is set: "
            r2.<init>(r3)
            int r0 = r0.mJustifyContent
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x007a:
            int r3 = r12.a()
            if (r3 == 0) goto L_0x0088
            int r15 = r12.e
            int r15 = r4 - r15
            float r15 = (float) r15
            float r3 = (float) r3
            float r15 = r15 / r3
            goto L_0x0089
        L_0x0088:
            r15 = r11
        L_0x0089:
            float r3 = (float) r1
            float r14 = r15 / r14
            float r3 = r3 + r14
            int r7 = r4 - r2
            float r7 = (float) r7
            float r7 = r7 - r14
            r14 = r15
            r15 = r7
            goto L_0x00cc
        L_0x0094:
            float r3 = (float) r1
            int r7 = r12.a()
            if (r7 == r13) goto L_0x009f
            int r7 = r7 + -1
            float r7 = (float) r7
            goto L_0x00a1
        L_0x009f:
            r7 = 1065353216(0x3f800000, float:1.0)
        L_0x00a1:
            int r14 = r12.e
            int r14 = r4 - r14
            float r14 = (float) r14
            float r14 = r14 / r7
            int r7 = r4 - r2
            float r15 = (float) r7
            goto L_0x00cc
        L_0x00ab:
            float r3 = (float) r1
            int r7 = r12.e
            int r15 = r4 - r7
            float r15 = (float) r15
            float r15 = r15 / r14
            float r3 = r3 + r15
            int r15 = r4 - r2
            float r15 = (float) r15
            int r7 = r4 - r7
            float r7 = (float) r7
            float r7 = r7 / r14
            float r15 = r15 - r7
        L_0x00bb:
            r14 = r11
            goto L_0x00cc
        L_0x00bd:
            int r3 = r12.e
            int r7 = r4 - r3
            int r7 = r7 + r2
            float r7 = (float) r7
            int r3 = r3 - r1
            float r15 = (float) r3
            r3 = r7
            goto L_0x00bb
        L_0x00c7:
            float r3 = (float) r1
            int r7 = r4 - r2
            float r15 = (float) r7
            goto L_0x00bb
        L_0x00cc:
            float r7 = java.lang.Math.max(r14, r11)
            r11 = 0
        L_0x00d1:
            int r14 = r12.f1855h
            if (r11 >= r14) goto L_0x0202
            int r14 = r12.f1858o
            int r14 = r14 + r11
            android.view.View r17 = r0.getReorderedChildAt(r14)
            r26 = r9
            if (r17 == 0) goto L_0x01f2
            int r9 = r17.getVisibility()
            r27 = r13
            r13 = 8
            if (r9 != r13) goto L_0x00f4
            r22 = r27
        L_0x00ec:
            r21 = r10
            r17 = r11
            r23 = r16
            goto L_0x01f6
        L_0x00f4:
            android.view.ViewGroup$LayoutParams r9 = r17.getLayoutParams()
            q1.g r9 = (q1.g) r9
            int r13 = r9.leftMargin
            float r13 = (float) r13
            float r3 = r3 + r13
            int r13 = r9.rightMargin
            float r13 = (float) r13
            float r15 = r15 - r13
            boolean r13 = r0.e(r14, r11)
            if (r13 == 0) goto L_0x0112
            int r13 = r0.mDividerVerticalWidth
            float r14 = (float) r13
            float r3 = r3 + r14
            float r15 = r15 - r14
            r18 = r13
        L_0x010f:
            r19 = r15
            goto L_0x0115
        L_0x0112:
            r18 = 0
            goto L_0x010f
        L_0x0115:
            int r13 = r12.f1855h
            int r13 = r13 + -1
            if (r11 != r13) goto L_0x0126
            int r13 = r0.mShowDividerVertical
            r13 = r13 & 4
            if (r13 <= 0) goto L_0x0126
            int r13 = r0.mDividerVerticalWidth
            r20 = r13
            goto L_0x0128
        L_0x0126:
            r20 = 0
        L_0x0128:
            int r13 = r0.mFlexWrap
            if (r13 != r10) goto L_0x017a
            if (r29 == 0) goto L_0x0156
            r13 = r10
            q1.f r10 = r0.mFlexboxHelper
            int r14 = java.lang.Math.round(r19)
            int r15 = r17.getMeasuredWidth()
            int r14 = r14 - r15
            int r15 = r17.getMeasuredHeight()
            int r15 = r16 - r15
            r21 = r13
            r13 = r14
            r14 = r15
            int r15 = java.lang.Math.round(r19)
            r22 = r17
            r17 = r11
            r11 = r22
            r22 = r27
            r10.o(r11, r12, r13, r14, r15, r16)
        L_0x0153:
            r23 = r16
            goto L_0x01bc
        L_0x0156:
            r21 = r17
            r17 = r11
            r11 = r21
            r22 = r27
            r21 = r10
            q1.f r10 = r0.mFlexboxHelper
            int r13 = java.lang.Math.round(r3)
            int r14 = r11.getMeasuredHeight()
            int r14 = r16 - r14
            int r15 = java.lang.Math.round(r3)
            int r23 = r11.getMeasuredWidth()
            int r15 = r23 + r15
            r10.o(r11, r12, r13, r14, r15, r16)
            goto L_0x0153
        L_0x017a:
            r21 = r17
            r17 = r11
            r11 = r21
            r22 = r27
            r21 = r10
            r23 = r16
            if (r29 == 0) goto L_0x01a2
            q1.f r10 = r0.mFlexboxHelper
            int r13 = java.lang.Math.round(r19)
            int r14 = r11.getMeasuredWidth()
            int r13 = r13 - r14
            int r15 = java.lang.Math.round(r19)
            int r14 = r11.getMeasuredHeight()
            int r16 = r14 + r5
            r14 = r5
            r10.o(r11, r12, r13, r14, r15, r16)
            goto L_0x01bc
        L_0x01a2:
            r14 = r5
            q1.f r10 = r0.mFlexboxHelper
            int r13 = java.lang.Math.round(r3)
            int r5 = java.lang.Math.round(r3)
            int r15 = r11.getMeasuredWidth()
            int r15 = r15 + r5
            int r5 = r11.getMeasuredHeight()
            int r16 = r5 + r14
            r10.o(r11, r12, r13, r14, r15, r16)
            r5 = r14
        L_0x01bc:
            int r10 = r11.getMeasuredWidth()
            float r10 = (float) r10
            float r10 = r10 + r7
            int r13 = r9.rightMargin
            float r13 = (float) r13
            float r10 = r10 + r13
            float r3 = r3 + r10
            int r10 = r11.getMeasuredWidth()
            float r10 = (float) r10
            float r10 = r10 + r7
            int r9 = r9.leftMargin
            float r9 = (float) r9
            float r10 = r10 + r9
            float r19 = r19 - r10
            if (r29 == 0) goto L_0x01e1
            r13 = 0
            r15 = 0
            r10 = r12
            r14 = r18
            r12 = r20
            r10.b(r11, r12, r13, r14, r15)
        L_0x01df:
            r12 = r10
            goto L_0x01ef
        L_0x01e1:
            r14 = r18
            r13 = r20
            r9 = 0
            r15 = 0
            r10 = r12
            r12 = r14
            r14 = r13
            r13 = r9
            r10.b(r11, r12, r13, r14, r15)
            goto L_0x01df
        L_0x01ef:
            r15 = r19
            goto L_0x01f6
        L_0x01f2:
            r22 = r13
            goto L_0x00ec
        L_0x01f6:
            int r11 = r17 + 1
            r9 = r26
            r10 = r21
            r13 = r22
            r16 = r23
            goto L_0x00d1
        L_0x0202:
            r23 = r16
            int r3 = r12.g
            int r5 = r5 + r3
            int r3 = r23 - r3
            int r8 = r8 + 1
            goto L_0x001e
        L_0x020d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.h(int, int, int, int, boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void i(int r25, int r26, int r27, int r28, boolean r29, boolean r30) {
        /*
            r24 = this;
            r0 = r24
            int r1 = r0.getPaddingTop()
            int r2 = r0.getPaddingBottom()
            int r3 = r0.getPaddingRight()
            int r4 = r0.getPaddingLeft()
            int r5 = r27 - r25
            int r6 = r28 - r26
            int r5 = r5 - r3
            java.util.List<q1.c> r3 = r0.mFlexLines
            int r3 = r3.size()
            r8 = 0
        L_0x001e:
            if (r8 >= r3) goto L_0x01ef
            java.util.List<q1.c> r9 = r0.mFlexLines
            java.lang.Object r9 = r9.get(r8)
            r12 = r9
            q1.c r12 = (q1.c) r12
            boolean r9 = r0.f(r8)
            if (r9 == 0) goto L_0x0033
            int r9 = r0.mDividerVerticalWidth
            int r4 = r4 + r9
            int r5 = r5 - r9
        L_0x0033:
            r16 = r5
            int r5 = r0.mJustifyContent
            r9 = 4
            r10 = 0
            r11 = 1
            if (r5 == 0) goto L_0x00c2
            if (r5 == r11) goto L_0x00b8
            r13 = 2
            r14 = 1073741824(0x40000000, float:2.0)
            if (r5 == r13) goto L_0x00aa
            r13 = 3
            if (r5 == r13) goto L_0x0092
            if (r5 == r9) goto L_0x0079
            r13 = 5
            if (r5 != r13) goto L_0x0063
            int r5 = r12.a()
            if (r5 == 0) goto L_0x005b
            int r13 = r12.e
            int r13 = r6 - r13
            float r13 = (float) r13
            int r5 = r5 + 1
            float r5 = (float) r5
            float r13 = r13 / r5
            goto L_0x005c
        L_0x005b:
            r13 = r10
        L_0x005c:
            float r5 = (float) r1
            float r5 = r5 + r13
            int r14 = r6 - r2
            float r14 = (float) r14
            float r14 = r14 - r13
            goto L_0x00c7
        L_0x0063:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Invalid justifyContent is set: "
            r2.<init>(r3)
            int r0 = r0.mJustifyContent
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x0079:
            int r5 = r12.a()
            if (r5 == 0) goto L_0x0087
            int r13 = r12.e
            int r13 = r6 - r13
            float r13 = (float) r13
            float r5 = (float) r5
            float r13 = r13 / r5
            goto L_0x0088
        L_0x0087:
            r13 = r10
        L_0x0088:
            float r5 = (float) r1
            float r14 = r13 / r14
            float r5 = r5 + r14
            int r15 = r6 - r2
            float r15 = (float) r15
            float r14 = r15 - r14
            goto L_0x00c7
        L_0x0092:
            float r5 = (float) r1
            int r13 = r12.a()
            if (r13 == r11) goto L_0x009d
            int r13 = r13 + -1
            float r13 = (float) r13
            goto L_0x009f
        L_0x009d:
            r13 = 1065353216(0x3f800000, float:1.0)
        L_0x009f:
            int r14 = r12.e
            int r14 = r6 - r14
            float r14 = (float) r14
            float r13 = r14 / r13
            int r14 = r6 - r2
            float r14 = (float) r14
            goto L_0x00c7
        L_0x00aa:
            float r5 = (float) r1
            int r13 = r12.e
            int r13 = r6 - r13
            float r13 = (float) r13
            float r13 = r13 / r14
            float r5 = r5 + r13
            int r14 = r6 - r2
            float r14 = (float) r14
            float r14 = r14 - r13
        L_0x00b6:
            r13 = r10
            goto L_0x00c7
        L_0x00b8:
            int r5 = r12.e
            int r13 = r6 - r5
            int r13 = r13 + r2
            float r13 = (float) r13
            int r5 = r5 - r1
            float r14 = (float) r5
            r5 = r13
            goto L_0x00b6
        L_0x00c2:
            float r5 = (float) r1
            int r13 = r6 - r2
            float r14 = (float) r13
            goto L_0x00b6
        L_0x00c7:
            float r18 = java.lang.Math.max(r13, r10)
            r10 = 0
        L_0x00cc:
            int r13 = r12.f1855h
            if (r10 >= r13) goto L_0x01e4
            int r13 = r12.f1858o
            int r13 = r13 + r10
            r15 = r11
            android.view.View r11 = r0.getReorderedChildAt(r13)
            if (r11 == 0) goto L_0x01d6
            int r7 = r11.getVisibility()
            r26 = r9
            r9 = 8
            if (r7 != r9) goto L_0x00ec
        L_0x00e4:
            r22 = r10
            r21 = r15
            r23 = r16
            goto L_0x01da
        L_0x00ec:
            android.view.ViewGroup$LayoutParams r7 = r11.getLayoutParams()
            q1.g r7 = (q1.g) r7
            int r9 = r7.topMargin
            float r9 = (float) r9
            float r5 = r5 + r9
            int r9 = r7.bottomMargin
            float r9 = (float) r9
            float r14 = r14 - r9
            boolean r9 = r0.e(r13, r10)
            if (r9 == 0) goto L_0x0108
            int r9 = r0.mDividerHorizontalHeight
            float r13 = (float) r9
            float r5 = r5 + r13
            float r14 = r14 - r13
        L_0x0105:
            r19 = r14
            goto L_0x010a
        L_0x0108:
            r9 = 0
            goto L_0x0105
        L_0x010a:
            int r13 = r12.f1855h
            int r13 = r13 - r15
            if (r10 != r13) goto L_0x011a
            int r13 = r0.mShowDividerHorizontal
            r13 = r13 & 4
            if (r13 <= 0) goto L_0x011a
            int r13 = r0.mDividerHorizontalHeight
            r20 = r13
            goto L_0x011c
        L_0x011a:
            r20 = 0
        L_0x011c:
            if (r29 == 0) goto L_0x0163
            if (r30 == 0) goto L_0x0144
            r13 = r10
            q1.f r10 = r0.mFlexboxHelper
            int r14 = r11.getMeasuredWidth()
            int r14 = r16 - r14
            int r17 = java.lang.Math.round(r19)
            int r21 = r11.getMeasuredHeight()
            int r17 = r17 - r21
            r21 = r15
            r15 = r17
            int r17 = java.lang.Math.round(r19)
            r22 = r13
            r13 = 1
            r10.p(r11, r12, r13, r14, r15, r16, r17)
        L_0x0141:
            r23 = r16
            goto L_0x01a3
        L_0x0144:
            r22 = r10
            r21 = r15
            q1.f r10 = r0.mFlexboxHelper
            int r13 = r11.getMeasuredWidth()
            int r14 = r16 - r13
            int r15 = java.lang.Math.round(r5)
            int r13 = java.lang.Math.round(r5)
            int r17 = r11.getMeasuredHeight()
            int r17 = r17 + r13
            r13 = 1
            r10.p(r11, r12, r13, r14, r15, r16, r17)
            goto L_0x0141
        L_0x0163:
            r22 = r10
            r21 = r15
            r23 = r16
            if (r30 == 0) goto L_0x0187
            q1.f r10 = r0.mFlexboxHelper
            int r13 = java.lang.Math.round(r19)
            int r14 = r11.getMeasuredHeight()
            int r15 = r13 - r14
            int r13 = r11.getMeasuredWidth()
            int r16 = r13 + r4
            int r17 = java.lang.Math.round(r19)
            r13 = 0
            r14 = r4
            r10.p(r11, r12, r13, r14, r15, r16, r17)
            goto L_0x01a3
        L_0x0187:
            r14 = r4
            q1.f r10 = r0.mFlexboxHelper
            int r15 = java.lang.Math.round(r5)
            int r4 = r11.getMeasuredWidth()
            int r16 = r4 + r14
            int r4 = java.lang.Math.round(r5)
            int r13 = r11.getMeasuredHeight()
            int r17 = r13 + r4
            r13 = 0
            r10.p(r11, r12, r13, r14, r15, r16, r17)
            r4 = r14
        L_0x01a3:
            int r10 = r11.getMeasuredHeight()
            float r10 = (float) r10
            float r10 = r10 + r18
            int r13 = r7.bottomMargin
            float r13 = (float) r13
            float r10 = r10 + r13
            float r5 = r5 + r10
            int r10 = r11.getMeasuredHeight()
            float r10 = (float) r10
            float r10 = r10 + r18
            int r7 = r7.topMargin
            float r7 = (float) r7
            float r10 = r10 + r7
            float r19 = r19 - r10
            if (r30 == 0) goto L_0x01c9
            r10 = r12
            r12 = 0
            r14 = 0
            r15 = r9
            r13 = r20
            r10.b(r11, r12, r13, r14, r15)
        L_0x01c7:
            r12 = r10
            goto L_0x01d3
        L_0x01c9:
            r13 = r9
            r10 = r12
            r15 = r20
            r12 = 0
            r14 = 0
            r10.b(r11, r12, r13, r14, r15)
            goto L_0x01c7
        L_0x01d3:
            r14 = r19
            goto L_0x01da
        L_0x01d6:
            r26 = r9
            goto L_0x00e4
        L_0x01da:
            int r10 = r22 + 1
            r9 = r26
            r11 = r21
            r16 = r23
            goto L_0x00cc
        L_0x01e4:
            r23 = r16
            int r5 = r12.g
            int r4 = r4 + r5
            int r5 = r23 - r5
            int r8 = r8 + 1
            goto L_0x001e
        L_0x01ef:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.i(int, int, int, int, boolean, boolean):void");
    }

    public boolean isMainAxisDirectionHorizontal() {
        int i2 = this.mFlexDirection;
        if (i2 == 0 || i2 == 1) {
            return true;
        }
        return false;
    }

    public final void j(int i2, int i7, int i8, int i10) {
        int i11;
        int i12;
        int i13;
        int i14;
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        int mode2 = View.MeasureSpec.getMode(i8);
        int size2 = View.MeasureSpec.getSize(i8);
        if (i2 == 0 || i2 == 1) {
            i11 = getPaddingBottom() + getPaddingTop() + getSumOfCrossSize();
            i12 = getLargestMainSize();
        } else if (i2 == 2 || i2 == 3) {
            i11 = getLargestMainSize();
            i12 = getPaddingRight() + getPaddingLeft() + getSumOfCrossSize();
        } else {
            throw new IllegalArgumentException(C0086a.i(i2, "Invalid flex direction: "));
        }
        if (mode == Integer.MIN_VALUE) {
            if (size < i12) {
                i10 = View.combineMeasuredStates(i10, 16777216);
            } else {
                size = i12;
            }
            i13 = View.resolveSizeAndState(size, i7, i10);
        } else if (mode == 0) {
            i13 = View.resolveSizeAndState(i12, i7, i10);
        } else if (mode == 1073741824) {
            if (size < i12) {
                i10 = View.combineMeasuredStates(i10, 16777216);
            }
            i13 = View.resolveSizeAndState(size, i7, i10);
        } else {
            throw new IllegalStateException(C0086a.i(mode, "Unknown width mode is set: "));
        }
        if (mode2 == Integer.MIN_VALUE) {
            if (size2 < i11) {
                i10 = View.combineMeasuredStates(i10, 256);
            } else {
                size2 = i11;
            }
            i14 = View.resolveSizeAndState(size2, i8, i10);
        } else if (mode2 == 0) {
            i14 = View.resolveSizeAndState(i11, i8, i10);
        } else if (mode2 == 1073741824) {
            if (size2 < i11) {
                i10 = View.combineMeasuredStates(i10, 256);
            }
            i14 = View.resolveSizeAndState(size2, i8, i10);
        } else {
            throw new IllegalStateException(C0086a.i(mode2, "Unknown height mode is set: "));
        }
        setMeasuredDimension(i13, i14);
    }

    public void onDraw(Canvas canvas) {
        boolean z;
        boolean z3;
        if (this.mDividerDrawableVertical != null || this.mDividerDrawableHorizontal != null) {
            if (this.mShowDividerHorizontal != 0 || this.mShowDividerVertical != 0) {
                int layoutDirection = ViewCompat.getLayoutDirection(this);
                int i2 = this.mFlexDirection;
                boolean z7 = false;
                boolean z9 = true;
                if (i2 == 0) {
                    if (layoutDirection == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (this.mFlexWrap == 2) {
                        z7 = true;
                    }
                    a(canvas, z, z7);
                } else if (i2 == 1) {
                    if (layoutDirection != 1) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (this.mFlexWrap == 2) {
                        z7 = true;
                    }
                    a(canvas, z3, z7);
                } else if (i2 == 2) {
                    if (layoutDirection != 1) {
                        z9 = false;
                    }
                    if (this.mFlexWrap == 2) {
                        z9 = !z9;
                    }
                    b(canvas, z9, false);
                } else if (i2 == 3) {
                    if (layoutDirection == 1) {
                        z7 = true;
                    }
                    if (this.mFlexWrap == 2) {
                        z7 = !z7;
                    }
                    b(canvas, z7, true);
                }
            }
        }
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int i11 = this.mFlexDirection;
        boolean z3 = false;
        if (i11 == 0) {
            int i12 = i2;
            int i13 = i7;
            int i14 = i8;
            int i15 = i10;
            boolean z7 = false;
            if (layoutDirection == 1) {
                z7 = true;
            }
            h(i12, i13, i14, i15, z7);
        } else if (i11 == 1) {
            int i16 = i2;
            int i17 = i7;
            int i18 = i8;
            int i19 = i10;
            boolean z9 = false;
            if (layoutDirection != 1) {
                z9 = true;
            }
            h(i16, i17, i18, i19, z9);
        } else if (i11 == 2) {
            int i20 = i2;
            int i21 = i7;
            int i22 = i8;
            int i23 = i10;
            boolean z10 = false;
            if (layoutDirection == 1) {
                z10 = true;
            }
            if (this.mFlexWrap == 2) {
                z10 = !z10;
            }
            i(i20, i21, i22, i23, z10, false);
        } else if (i11 == 3) {
            if (layoutDirection == 1) {
                z3 = true;
            }
            if (this.mFlexWrap == 2) {
                z3 = !z3;
            }
            i(i2, i7, i8, i10, z3, true);
        } else {
            throw new IllegalStateException("Invalid flex direction is set: " + this.mFlexDirection);
        }
    }

    public void onMeasure(int i2, int i7) {
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(getChildCount());
        }
        f fVar = this.mFlexboxHelper;
        SparseIntArray sparseIntArray = this.mOrderCache;
        C0265a aVar = fVar.f1861a;
        int flexItemCount = aVar.getFlexItemCount();
        if (sparseIntArray.size() == flexItemCount) {
            int i8 = 0;
            while (true) {
                if (i8 >= flexItemCount) {
                    break;
                }
                View flexItemAt = aVar.getFlexItemAt(i8);
                if (flexItemAt != null && ((b) flexItemAt.getLayoutParams()).getOrder() != sparseIntArray.get(i8)) {
                    break;
                }
                i8++;
            }
        }
        f fVar2 = this.mFlexboxHelper;
        SparseIntArray sparseIntArray2 = this.mOrderCache;
        int flexItemCount2 = fVar2.f1861a.getFlexItemCount();
        this.mReorderedIndices = f.r(flexItemCount2, fVar2.f(flexItemCount2), sparseIntArray2);
        int i10 = this.mFlexDirection;
        if (i10 == 0 || i10 == 1) {
            this.mFlexLines.clear();
            d dVar = this.mFlexLinesResult;
            dVar.f1860a = null;
            dVar.b = 0;
            this.mFlexboxHelper.b(dVar, i2, i7, Integer.MAX_VALUE, 0, -1, (List) null);
            this.mFlexLines = this.mFlexLinesResult.f1860a;
            this.mFlexboxHelper.h(i2, i7, 0);
            if (this.mAlignItems == 3) {
                for (c next : this.mFlexLines) {
                    int i11 = Integer.MIN_VALUE;
                    for (int i12 = 0; i12 < next.f1855h; i12++) {
                        View reorderedChildAt = getReorderedChildAt(next.f1858o + i12);
                        if (!(reorderedChildAt == null || reorderedChildAt.getVisibility() == 8)) {
                            g gVar = (g) reorderedChildAt.getLayoutParams();
                            if (this.mFlexWrap != 2) {
                                i11 = Math.max(i11, reorderedChildAt.getMeasuredHeight() + Math.max(next.l - reorderedChildAt.getBaseline(), gVar.topMargin) + gVar.bottomMargin);
                            } else {
                                i11 = Math.max(i11, reorderedChildAt.getMeasuredHeight() + gVar.topMargin + Math.max(reorderedChildAt.getBaseline() + (next.l - reorderedChildAt.getMeasuredHeight()), gVar.bottomMargin));
                            }
                        }
                    }
                    next.g = i11;
                }
            }
            this.mFlexboxHelper.g(i2, i7, getPaddingBottom() + getPaddingTop());
            this.mFlexboxHelper.u(0);
            j(this.mFlexDirection, i2, i7, this.mFlexLinesResult.b);
        } else if (i10 == 2 || i10 == 3) {
            this.mFlexLines.clear();
            d dVar2 = this.mFlexLinesResult;
            dVar2.f1860a = null;
            dVar2.b = 0;
            int i13 = i7;
            this.mFlexboxHelper.b(dVar2, i13, i2, Integer.MAX_VALUE, 0, -1, (List) null);
            int i14 = i13;
            this.mFlexLines = this.mFlexLinesResult.f1860a;
            this.mFlexboxHelper.h(i2, i7, 0);
            this.mFlexboxHelper.g(i2, i7, getPaddingRight() + getPaddingLeft());
            this.mFlexboxHelper.u(0);
            j(this.mFlexDirection, i2, i7, this.mFlexLinesResult.b);
        } else {
            throw new IllegalStateException("Invalid value for the flex direction is set: " + this.mFlexDirection);
        }
    }

    public void onNewFlexItemAdded(View view, int i2, int i7, c cVar) {
        if (!e(i2, i7)) {
            return;
        }
        if (isMainAxisDirectionHorizontal()) {
            int i8 = cVar.e;
            int i10 = this.mDividerVerticalWidth;
            cVar.e = i8 + i10;
            cVar.f += i10;
            return;
        }
        int i11 = cVar.e;
        int i12 = this.mDividerHorizontalHeight;
        cVar.e = i11 + i12;
        cVar.f += i12;
    }

    public void onNewFlexLineAdded(c cVar) {
        if (isMainAxisDirectionHorizontal()) {
            if ((this.mShowDividerVertical & 4) > 0) {
                int i2 = cVar.e;
                int i7 = this.mDividerVerticalWidth;
                cVar.e = i2 + i7;
                cVar.f += i7;
            }
        } else if ((this.mShowDividerHorizontal & 4) > 0) {
            int i8 = cVar.e;
            int i10 = this.mDividerHorizontalHeight;
            cVar.e = i8 + i10;
            cVar.f += i10;
        }
    }

    public void setAlignContent(int i2) {
        if (this.mAlignContent != i2) {
            this.mAlignContent = i2;
            requestLayout();
        }
    }

    public void setAlignItems(int i2) {
        if (this.mAlignItems != i2) {
            this.mAlignItems = i2;
            requestLayout();
        }
    }

    public void setDividerDrawable(Drawable drawable) {
        setDividerDrawableHorizontal(drawable);
        setDividerDrawableVertical(drawable);
    }

    public void setDividerDrawableHorizontal(Drawable drawable) {
        if (drawable != this.mDividerDrawableHorizontal) {
            this.mDividerDrawableHorizontal = drawable;
            if (drawable != null) {
                this.mDividerHorizontalHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerHorizontalHeight = 0;
            }
            if (this.mDividerDrawableHorizontal == null && this.mDividerDrawableVertical == null) {
                setWillNotDraw(true);
            } else {
                setWillNotDraw(false);
            }
            requestLayout();
        }
    }

    public void setDividerDrawableVertical(Drawable drawable) {
        if (drawable != this.mDividerDrawableVertical) {
            this.mDividerDrawableVertical = drawable;
            if (drawable != null) {
                this.mDividerVerticalWidth = drawable.getIntrinsicWidth();
            } else {
                this.mDividerVerticalWidth = 0;
            }
            if (this.mDividerDrawableHorizontal == null && this.mDividerDrawableVertical == null) {
                setWillNotDraw(true);
            } else {
                setWillNotDraw(false);
            }
            requestLayout();
        }
    }

    public void setFlexDirection(int i2) {
        if (this.mFlexDirection != i2) {
            this.mFlexDirection = i2;
            requestLayout();
        }
    }

    public void setFlexLines(List<c> list) {
        this.mFlexLines = list;
    }

    public void setFlexWrap(int i2) {
        if (this.mFlexWrap != i2) {
            this.mFlexWrap = i2;
            requestLayout();
        }
    }

    public void setJustifyContent(int i2) {
        if (this.mJustifyContent != i2) {
            this.mJustifyContent = i2;
            requestLayout();
        }
    }

    public void setMaxLine(int i2) {
        if (this.mMaxLine != i2) {
            this.mMaxLine = i2;
            requestLayout();
        }
    }

    public void setShowDivider(int i2) {
        setShowDividerVertical(i2);
        setShowDividerHorizontal(i2);
    }

    public void setShowDividerHorizontal(int i2) {
        if (i2 != this.mShowDividerHorizontal) {
            this.mShowDividerHorizontal = i2;
            requestLayout();
        }
    }

    public void setShowDividerVertical(int i2) {
        if (i2 != this.mShowDividerVertical) {
            this.mShowDividerVertical = i2;
            requestLayout();
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.view.ViewGroup$MarginLayoutParams, q1.g] */
    public g generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        ? marginLayoutParams = new ViewGroup.MarginLayoutParams(context, attributeSet);
        marginLayoutParams.d = 1;
        marginLayoutParams.e = 0.0f;
        marginLayoutParams.f = 1.0f;
        marginLayoutParams.g = -1;
        marginLayoutParams.f1863h = -1.0f;
        marginLayoutParams.f1864i = -1;
        marginLayoutParams.f1865j = -1;
        marginLayoutParams.k = 16777215;
        marginLayoutParams.l = 16777215;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.b);
        marginLayoutParams.d = obtainStyledAttributes.getInt(8, 1);
        marginLayoutParams.e = obtainStyledAttributes.getFloat(2, 0.0f);
        marginLayoutParams.f = obtainStyledAttributes.getFloat(3, 1.0f);
        marginLayoutParams.g = obtainStyledAttributes.getInt(0, -1);
        marginLayoutParams.f1863h = obtainStyledAttributes.getFraction(1, 1, 1, -1.0f);
        marginLayoutParams.f1864i = obtainStyledAttributes.getDimensionPixelSize(7, -1);
        marginLayoutParams.f1865j = obtainStyledAttributes.getDimensionPixelSize(6, -1);
        marginLayoutParams.k = obtainStyledAttributes.getDimensionPixelSize(5, 16777215);
        marginLayoutParams.l = obtainStyledAttributes.getDimensionPixelSize(4, 16777215);
        marginLayoutParams.m = obtainStyledAttributes.getBoolean(9, false);
        obtainStyledAttributes.recycle();
        return marginLayoutParams;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [android.view.ViewGroup$LayoutParams, android.view.ViewGroup$MarginLayoutParams, q1.g] */
    /* JADX WARNING: type inference failed for: r6v4, types: [android.view.ViewGroup$LayoutParams, android.view.ViewGroup$MarginLayoutParams, q1.g] */
    /* JADX WARNING: type inference failed for: r6v5, types: [android.view.ViewGroup$LayoutParams, android.view.ViewGroup$MarginLayoutParams, q1.g] */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof g) {
            g gVar = (g) layoutParams;
            ? marginLayoutParams = new ViewGroup.MarginLayoutParams(gVar);
            marginLayoutParams.d = 1;
            marginLayoutParams.e = 0.0f;
            marginLayoutParams.f = 1.0f;
            marginLayoutParams.g = -1;
            marginLayoutParams.f1863h = -1.0f;
            marginLayoutParams.f1864i = -1;
            marginLayoutParams.f1865j = -1;
            marginLayoutParams.k = 16777215;
            marginLayoutParams.l = 16777215;
            marginLayoutParams.d = gVar.d;
            marginLayoutParams.e = gVar.e;
            marginLayoutParams.f = gVar.f;
            marginLayoutParams.g = gVar.g;
            marginLayoutParams.f1863h = gVar.f1863h;
            marginLayoutParams.f1864i = gVar.f1864i;
            marginLayoutParams.f1865j = gVar.f1865j;
            marginLayoutParams.k = gVar.k;
            marginLayoutParams.l = gVar.l;
            marginLayoutParams.m = gVar.m;
            return marginLayoutParams;
        } else if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ? marginLayoutParams2 = new ViewGroup.MarginLayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            marginLayoutParams2.d = 1;
            marginLayoutParams2.e = 0.0f;
            marginLayoutParams2.f = 1.0f;
            marginLayoutParams2.g = -1;
            marginLayoutParams2.f1863h = -1.0f;
            marginLayoutParams2.f1864i = -1;
            marginLayoutParams2.f1865j = -1;
            marginLayoutParams2.k = 16777215;
            marginLayoutParams2.l = 16777215;
            return marginLayoutParams2;
        } else {
            ? marginLayoutParams3 = new ViewGroup.MarginLayoutParams(layoutParams);
            marginLayoutParams3.d = 1;
            marginLayoutParams3.e = 0.0f;
            marginLayoutParams3.f = 1.0f;
            marginLayoutParams3.g = -1;
            marginLayoutParams3.f1863h = -1.0f;
            marginLayoutParams3.f1864i = -1;
            marginLayoutParams3.f1865j = -1;
            marginLayoutParams3.k = 16777215;
            marginLayoutParams3.l = 16777215;
            return marginLayoutParams3;
        }
    }

    public void updateViewCache(int i2, View view) {
    }
}
