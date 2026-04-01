package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import q1.C0265a;
import q1.c;
import q1.d;
import q1.f;
import q1.h;
import q1.i;
import q1.j;
import q1.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlexboxLayoutManager extends RecyclerView.LayoutManager implements C0265a, RecyclerView.SmoothScroller.ScrollVectorProvider {
    private static final Rect TEMP_RECT = new Rect();
    private int mAlignItems;
    private h mAnchorInfo = new h(this);
    private final Context mContext;
    private int mDirtyPosition = -1;
    /* access modifiers changed from: private */
    public int mFlexDirection;
    /* access modifiers changed from: private */
    public List<c> mFlexLines = new ArrayList();
    private d mFlexLinesResult = new Object();
    /* access modifiers changed from: private */
    public int mFlexWrap;
    /* access modifiers changed from: private */
    public final f mFlexboxHelper = new f(this);
    private boolean mFromBottomToTop;
    /* access modifiers changed from: private */
    public boolean mIsRtl;
    private int mJustifyContent;
    private int mLastHeight = Integer.MIN_VALUE;
    private int mLastWidth = Integer.MIN_VALUE;
    private j mLayoutState;
    private int mMaxLine = -1;
    /* access modifiers changed from: private */
    public OrientationHelper mOrientationHelper;
    private View mParent;
    private k mPendingSavedState;
    private int mPendingScrollPosition = -1;
    private int mPendingScrollPositionOffset = Integer.MIN_VALUE;
    private boolean mRecycleChildrenOnDetach;
    private RecyclerView.Recycler mRecycler;
    private RecyclerView.State mState;
    /* access modifiers changed from: private */
    public OrientationHelper mSubOrientationHelper;
    private SparseArray<View> mViewCache = new SparseArray<>();

    /* JADX WARNING: type inference failed for: r0v1, types: [q1.d, java.lang.Object] */
    public FlexboxLayoutManager(Context context) {
        setFlexDirection(0);
        setFlexWrap(1);
        setAlignItems(4);
        this.mContext = context;
    }

    public static boolean isMeasurementUpToDate(int i2, int i7, int i8) {
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        if (i8 > 0 && i2 != i8) {
            return false;
        }
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                return true;
            }
            if (mode == 1073741824 && size == i2) {
                return true;
            }
            return false;
        } else if (size >= i2) {
            return true;
        } else {
            return false;
        }
    }

    public final void a() {
        if (this.mOrientationHelper == null) {
            if (isMainAxisDirectionHorizontal()) {
                if (this.mFlexWrap == 0) {
                    this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                    this.mSubOrientationHelper = OrientationHelper.createVerticalHelper(this);
                    return;
                }
                this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createHorizontalHelper(this);
            } else if (this.mFlexWrap == 0) {
                this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createHorizontalHelper(this);
            } else {
                this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createVerticalHelper(this);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:114:0x02c3  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0119  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int b(androidx.recyclerview.widget.RecyclerView.Recycler r30, androidx.recyclerview.widget.RecyclerView.State r31, q1.j r32) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            r2 = r32
            int r3 = r2.f
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 == r4) goto L_0x0016
            int r5 = r2.f1872a
            if (r5 >= 0) goto L_0x0013
            int r3 = r3 + r5
            r2.f = r3
        L_0x0013:
            r0.k(r1, r2)
        L_0x0016:
            int r3 = r2.f1872a
            boolean r5 = r0.isMainAxisDirectionHorizontal()
            r7 = r3
            r8 = 0
        L_0x001e:
            if (r7 > 0) goto L_0x002b
            q1.j r9 = r0.mLayoutState
            boolean r9 = r9.b
            if (r9 == 0) goto L_0x0027
            goto L_0x002b
        L_0x0027:
            r21 = r3
            goto L_0x0410
        L_0x002b:
            java.util.List<q1.c> r9 = r0.mFlexLines
            int r10 = r2.d
            if (r10 < 0) goto L_0x0027
            int r11 = r31.getItemCount()
            if (r10 >= r11) goto L_0x0027
            int r10 = r2.f1873c
            if (r10 < 0) goto L_0x0027
            int r9 = r9.size()
            if (r10 >= r9) goto L_0x0027
            java.util.List<q1.c> r9 = r0.mFlexLines
            int r10 = r2.f1873c
            java.lang.Object r9 = r9.get(r10)
            r12 = r9
            q1.c r12 = (q1.c) r12
            int r9 = r12.f1858o
            r2.d = r9
            boolean r9 = r0.isMainAxisDirectionHorizontal()
            r18 = 32
            java.lang.String r11 = "Invalid justifyContent is set: "
            r15 = 3
            r6 = 2
            r10 = -1
            r4 = 1
            r17 = 1073741824(0x40000000, float:2.0)
            if (r9 == 0) goto L_0x0205
            int r9 = r0.getPaddingLeft()
            int r20 = r0.getPaddingRight()
            int r21 = r0.getWidth()
            int r13 = r2.e
            int r14 = r2.f1874h
            if (r14 != r10) goto L_0x0075
            int r10 = r12.g
            int r13 = r13 - r10
        L_0x0075:
            r24 = r13
            int r10 = r2.d
            int r13 = r0.mJustifyContent
            if (r13 == 0) goto L_0x0100
            if (r13 == r4) goto L_0x00f4
            if (r13 == r6) goto L_0x00e4
            if (r13 == r15) goto L_0x00cc
            r6 = 4
            if (r13 == r6) goto L_0x00b4
            r6 = 5
            if (r13 != r6) goto L_0x00a0
            int r6 = r12.f1855h
            if (r6 == 0) goto L_0x0098
            int r11 = r12.e
            int r11 = r21 - r11
            float r11 = (float) r11
            int r6 = r6 + 1
            float r6 = (float) r6
            float r6 = r11 / r6
            goto L_0x0099
        L_0x0098:
            r6 = 0
        L_0x0099:
            float r9 = (float) r9
            float r9 = r9 + r6
            int r11 = r21 - r20
            float r11 = (float) r11
            float r11 = r11 - r6
            goto L_0x0105
        L_0x00a0:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r11)
            int r0 = r0.mJustifyContent
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x00b4:
            int r6 = r12.f1855h
            if (r6 == 0) goto L_0x00c1
            int r11 = r12.e
            int r11 = r21 - r11
            float r11 = (float) r11
            float r6 = (float) r6
            float r6 = r11 / r6
            goto L_0x00c2
        L_0x00c1:
            r6 = 0
        L_0x00c2:
            float r9 = (float) r9
            float r11 = r6 / r17
            float r9 = r9 + r11
            int r13 = r21 - r20
            float r13 = (float) r13
            float r11 = r13 - r11
            goto L_0x0105
        L_0x00cc:
            float r9 = (float) r9
            int r6 = r12.f1855h
            if (r6 == r4) goto L_0x00d7
            int r6 = r6 + -1
            float r6 = (float) r6
            r16 = r6
            goto L_0x00d9
        L_0x00d7:
            r16 = 1065353216(0x3f800000, float:1.0)
        L_0x00d9:
            int r6 = r12.e
            int r6 = r21 - r6
            float r6 = (float) r6
            float r6 = r6 / r16
            int r11 = r21 - r20
            float r11 = (float) r11
            goto L_0x0105
        L_0x00e4:
            float r6 = (float) r9
            int r9 = r12.e
            int r9 = r21 - r9
            float r9 = (float) r9
            float r9 = r9 / r17
            float r6 = r6 + r9
            int r11 = r21 - r20
            float r11 = (float) r11
            float r11 = r11 - r9
            r9 = r6
        L_0x00f2:
            r6 = 0
            goto L_0x0105
        L_0x00f4:
            int r6 = r12.e
            int r21 = r21 - r6
            int r11 = r21 + r20
            float r11 = (float) r11
            int r6 = r6 - r9
            float r6 = (float) r6
            r9 = r11
            r11 = r6
            goto L_0x00f2
        L_0x0100:
            float r9 = (float) r9
            int r6 = r21 - r20
            float r11 = (float) r6
            goto L_0x00f2
        L_0x0105:
            q1.h r13 = r0.mAnchorInfo
            int r13 = r13.d
            float r13 = (float) r13
            float r9 = r9 - r13
            float r11 = r11 - r13
            r13 = 0
            float r6 = java.lang.Math.max(r6, r13)
            int r13 = r12.f1855h
            r14 = r10
            r15 = 0
        L_0x0115:
            int r4 = r10 + r13
            if (r14 >= r4) goto L_0x01f6
            r4 = r11
            android.view.View r11 = r0.getFlexItemAt(r14)
            if (r11 != 0) goto L_0x012a
            r21 = r3
            r3 = r10
            r22 = r13
            r23 = r14
        L_0x0127:
            r11 = r4
            goto L_0x01ed
        L_0x012a:
            r21 = r3
            int r3 = r2.f1874h
            r16 = r4
            r4 = 1
            if (r3 != r4) goto L_0x013d
            android.graphics.Rect r3 = TEMP_RECT
            r0.calculateItemDecorationsForChild(r11, r3)
            r0.addView(r11)
        L_0x013b:
            r3 = r15
            goto L_0x0148
        L_0x013d:
            android.graphics.Rect r3 = TEMP_RECT
            r0.calculateItemDecorationsForChild(r11, r3)
            r0.addView(r11, r15)
            int r15 = r15 + 1
            goto L_0x013b
        L_0x0148:
            q1.f r4 = r0.mFlexboxHelper
            long[] r4 = r4.d
            r17 = r3
            r3 = r4[r14]
            int r15 = (int) r3
            long r3 = r3 >> r18
            int r3 = (int) r3
            android.view.ViewGroup$LayoutParams r4 = r11.getLayoutParams()
            q1.i r4 = (q1.i) r4
            boolean r19 = r0.m(r11, r15, r3, r4)
            if (r19 == 0) goto L_0x0163
            r11.measure(r15, r3)
        L_0x0163:
            int r3 = r4.leftMargin
            int r15 = r0.getLeftDecorationWidth(r11)
            int r15 = r15 + r3
            float r3 = (float) r15
            float r9 = r9 + r3
            int r3 = r4.rightMargin
            int r15 = r0.getRightDecorationWidth(r11)
            int r15 = r15 + r3
            float r3 = (float) r15
            float r3 = r16 - r3
            int r15 = r0.getTopDecorationHeight(r11)
            int r15 = r15 + r24
            r19 = r3
            boolean r3 = r0.mIsRtl
            if (r3 == 0) goto L_0x01aa
            r3 = r10
            q1.f r10 = r0.mFlexboxHelper
            int r16 = java.lang.Math.round(r19)
            int r22 = r11.getMeasuredWidth()
            int r16 = r16 - r22
            r22 = r14
            r14 = r15
            int r15 = java.lang.Math.round(r19)
            int r23 = r11.getMeasuredHeight()
            int r23 = r23 + r14
            r28 = r22
            r22 = r13
            r13 = r16
            r16 = r23
            r23 = r28
            r10.o(r11, r12, r13, r14, r15, r16)
            goto L_0x01c9
        L_0x01aa:
            r3 = r10
            r22 = r13
            r23 = r14
            r14 = r15
            q1.f r10 = r0.mFlexboxHelper
            int r13 = java.lang.Math.round(r9)
            int r15 = java.lang.Math.round(r9)
            int r16 = r11.getMeasuredWidth()
            int r15 = r16 + r15
            int r16 = r11.getMeasuredHeight()
            int r16 = r16 + r14
            r10.o(r11, r12, r13, r14, r15, r16)
        L_0x01c9:
            int r10 = r11.getMeasuredWidth()
            int r13 = r4.rightMargin
            int r10 = r10 + r13
            int r13 = r0.getRightDecorationWidth(r11)
            int r13 = r13 + r10
            float r10 = (float) r13
            float r10 = r10 + r6
            float r10 = r10 + r9
            int r9 = r11.getMeasuredWidth()
            int r4 = r4.leftMargin
            int r9 = r9 + r4
            int r4 = r0.getLeftDecorationWidth(r11)
            int r4 = r4 + r9
            float r4 = (float) r4
            float r4 = r4 + r6
            float r4 = r19 - r4
            r9 = r10
            r15 = r17
            goto L_0x0127
        L_0x01ed:
            int r14 = r23 + 1
            r10 = r3
            r3 = r21
            r13 = r22
            goto L_0x0115
        L_0x01f6:
            r21 = r3
            q1.j r3 = r0.mLayoutState
            int r3 = r3.f1874h
            int r4 = r2.f1873c
            int r4 = r4 + r3
            r2.f1873c = r4
            int r3 = r12.g
            goto L_0x03eb
        L_0x0205:
            r21 = r3
            int r3 = r0.getPaddingTop()
            int r4 = r0.getPaddingBottom()
            int r9 = r0.getHeight()
            int r13 = r2.e
            int r14 = r2.f1874h
            if (r14 != r10) goto L_0x0223
            int r10 = r12.g
            int r14 = r13 - r10
            int r13 = r13 + r10
            r25 = r13
            r24 = r14
            goto L_0x0227
        L_0x0223:
            r24 = r13
            r25 = r24
        L_0x0227:
            int r10 = r2.d
            int r13 = r0.mJustifyContent
            if (r13 == 0) goto L_0x02ab
            r14 = 1
            if (r13 == r14) goto L_0x02a0
            if (r13 == r6) goto L_0x0292
            if (r13 == r15) goto L_0x027a
            r6 = 4
            if (r13 == r6) goto L_0x0264
            r6 = 5
            if (r13 != r6) goto L_0x0250
            int r6 = r12.f1855h
            if (r6 == 0) goto L_0x0249
            int r11 = r12.e
            int r11 = r9 - r11
            float r11 = (float) r11
            int r6 = r6 + 1
            float r6 = (float) r6
            float r13 = r11 / r6
            goto L_0x024a
        L_0x0249:
            r13 = 0
        L_0x024a:
            float r3 = (float) r3
            float r3 = r3 + r13
            int r9 = r9 - r4
            float r4 = (float) r9
            float r4 = r4 - r13
            goto L_0x02af
        L_0x0250:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r11)
            int r0 = r0.mJustifyContent
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x0264:
            int r6 = r12.f1855h
            if (r6 == 0) goto L_0x0271
            int r11 = r12.e
            int r11 = r9 - r11
            float r11 = (float) r11
            float r6 = (float) r6
            float r13 = r11 / r6
            goto L_0x0272
        L_0x0271:
            r13 = 0
        L_0x0272:
            float r3 = (float) r3
            float r6 = r13 / r17
            float r3 = r3 + r6
            int r9 = r9 - r4
            float r4 = (float) r9
            float r4 = r4 - r6
            goto L_0x02af
        L_0x027a:
            float r3 = (float) r3
            int r6 = r12.f1855h
            r14 = 1
            if (r6 == r14) goto L_0x0286
            int r6 = r6 + -1
            float r6 = (float) r6
            r16 = r6
            goto L_0x0288
        L_0x0286:
            r16 = 1065353216(0x3f800000, float:1.0)
        L_0x0288:
            int r6 = r12.e
            int r6 = r9 - r6
            float r6 = (float) r6
            float r13 = r6 / r16
            int r9 = r9 - r4
            float r4 = (float) r9
            goto L_0x02af
        L_0x0292:
            float r3 = (float) r3
            int r6 = r12.e
            int r6 = r9 - r6
            float r6 = (float) r6
            float r6 = r6 / r17
            float r3 = r3 + r6
            int r9 = r9 - r4
            float r4 = (float) r9
            float r4 = r4 - r6
        L_0x029e:
            r13 = 0
            goto L_0x02af
        L_0x02a0:
            int r6 = r12.e
            int r9 = r9 - r6
            int r9 = r9 + r4
            float r4 = (float) r9
            int r6 = r6 - r3
            float r3 = (float) r6
            r13 = r4
            r4 = r3
            r3 = r13
            goto L_0x029e
        L_0x02ab:
            float r3 = (float) r3
            int r9 = r9 - r4
            float r4 = (float) r9
            goto L_0x029e
        L_0x02af:
            q1.h r6 = r0.mAnchorInfo
            int r6 = r6.d
            float r6 = (float) r6
            float r3 = r3 - r6
            float r4 = r4 - r6
            r6 = 0
            float r6 = java.lang.Math.max(r13, r6)
            int r9 = r12.f1855h
            r11 = r10
            r13 = 0
        L_0x02bf:
            int r14 = r10 + r9
            if (r11 >= r14) goto L_0x03e0
            r14 = r11
            android.view.View r11 = r0.getFlexItemAt(r14)
            if (r11 != 0) goto L_0x02d2
            r26 = r10
            r23 = r14
            r10 = r3
            r3 = 1
            goto L_0x03d9
        L_0x02d2:
            q1.f r15 = r0.mFlexboxHelper
            long[] r15 = r15.d
            r16 = r3
            r17 = r4
            r3 = r15[r14]
            int r15 = (int) r3
            long r3 = r3 >> r18
            int r3 = (int) r3
            android.view.ViewGroup$LayoutParams r4 = r11.getLayoutParams()
            q1.i r4 = (q1.i) r4
            boolean r19 = r0.m(r11, r15, r3, r4)
            if (r19 == 0) goto L_0x02ef
            r11.measure(r15, r3)
        L_0x02ef:
            int r3 = r4.topMargin
            int r15 = r0.getTopDecorationHeight(r11)
            int r15 = r15 + r3
            float r3 = (float) r15
            float r3 = r16 + r3
            int r15 = r4.rightMargin
            int r16 = r0.getBottomDecorationHeight(r11)
            int r15 = r16 + r15
            float r15 = (float) r15
            float r19 = r17 - r15
            int r15 = r2.f1874h
            r22 = r3
            r3 = 1
            if (r15 != r3) goto L_0x0316
            android.graphics.Rect r15 = TEMP_RECT
            r0.calculateItemDecorationsForChild(r11, r15)
            r0.addView(r11)
        L_0x0313:
            r20 = r13
            goto L_0x0321
        L_0x0316:
            android.graphics.Rect r15 = TEMP_RECT
            r0.calculateItemDecorationsForChild(r11, r15)
            r0.addView(r11, r13)
            int r13 = r13 + 1
            goto L_0x0313
        L_0x0321:
            int r13 = r0.getLeftDecorationWidth(r11)
            int r13 = r13 + r24
            int r15 = r0.getRightDecorationWidth(r11)
            int r16 = r25 - r15
            r15 = r14
            r14 = r13
            boolean r13 = r0.mIsRtl
            if (r13 == 0) goto L_0x037a
            boolean r14 = r0.mFromBottomToTop
            if (r14 == 0) goto L_0x035c
            r14 = r10
            q1.f r10 = r0.mFlexboxHelper
            int r17 = r11.getMeasuredWidth()
            int r17 = r16 - r17
            int r23 = java.lang.Math.round(r19)
            int r26 = r11.getMeasuredHeight()
            int r23 = r23 - r26
            r26 = r14
            r14 = r17
            int r17 = java.lang.Math.round(r19)
            r28 = r23
            r23 = r15
            r15 = r28
            r10.p(r11, r12, r13, r14, r15, r16, r17)
            goto L_0x03b5
        L_0x035c:
            r26 = r10
            r23 = r15
            q1.f r10 = r0.mFlexboxHelper
            int r14 = r11.getMeasuredWidth()
            int r14 = r16 - r14
            int r15 = java.lang.Math.round(r22)
            int r17 = java.lang.Math.round(r22)
            int r27 = r11.getMeasuredHeight()
            int r17 = r27 + r17
            r10.p(r11, r12, r13, r14, r15, r16, r17)
            goto L_0x03b5
        L_0x037a:
            r26 = r10
            r23 = r15
            boolean r10 = r0.mFromBottomToTop
            if (r10 == 0) goto L_0x039c
            q1.f r10 = r0.mFlexboxHelper
            int r15 = java.lang.Math.round(r19)
            int r16 = r11.getMeasuredHeight()
            int r15 = r15 - r16
            int r16 = r11.getMeasuredWidth()
            int r16 = r16 + r14
            int r17 = java.lang.Math.round(r19)
            r10.p(r11, r12, r13, r14, r15, r16, r17)
            goto L_0x03b5
        L_0x039c:
            q1.f r10 = r0.mFlexboxHelper
            int r15 = java.lang.Math.round(r22)
            int r16 = r11.getMeasuredWidth()
            int r16 = r16 + r14
            int r17 = java.lang.Math.round(r22)
            int r27 = r11.getMeasuredHeight()
            int r17 = r27 + r17
            r10.p(r11, r12, r13, r14, r15, r16, r17)
        L_0x03b5:
            int r10 = r11.getMeasuredHeight()
            int r13 = r4.topMargin
            int r10 = r10 + r13
            int r13 = r0.getBottomDecorationHeight(r11)
            int r13 = r13 + r10
            float r10 = (float) r13
            float r10 = r10 + r6
            float r10 = r10 + r22
            int r13 = r11.getMeasuredHeight()
            int r4 = r4.bottomMargin
            int r13 = r13 + r4
            int r4 = r0.getTopDecorationHeight(r11)
            int r4 = r4 + r13
            float r4 = (float) r4
            float r4 = r4 + r6
            float r19 = r19 - r4
            r4 = r19
            r13 = r20
        L_0x03d9:
            int r11 = r23 + 1
            r3 = r10
            r10 = r26
            goto L_0x02bf
        L_0x03e0:
            q1.j r3 = r0.mLayoutState
            int r3 = r3.f1874h
            int r4 = r2.f1873c
            int r4 = r4 + r3
            r2.f1873c = r4
            int r3 = r12.g
        L_0x03eb:
            int r8 = r8 + r3
            if (r5 != 0) goto L_0x03fd
            boolean r3 = r0.mIsRtl
            if (r3 == 0) goto L_0x03fd
            int r3 = r12.g
            int r4 = r2.f1874h
            int r3 = r3 * r4
            int r4 = r2.e
            int r4 = r4 - r3
            r2.e = r4
            goto L_0x0407
        L_0x03fd:
            int r3 = r12.g
            int r4 = r2.f1874h
            int r3 = r3 * r4
            int r4 = r2.e
            int r4 = r4 + r3
            r2.e = r4
        L_0x0407:
            int r3 = r12.g
            int r7 = r7 - r3
            r3 = r21
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x001e
        L_0x0410:
            int r3 = r2.f1872a
            int r3 = r3 - r8
            r2.f1872a = r3
            int r4 = r2.f
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r4 == r5) goto L_0x0426
            int r4 = r4 + r8
            r2.f = r4
            if (r3 >= 0) goto L_0x0423
            int r4 = r4 + r3
            r2.f = r4
        L_0x0423:
            r0.k(r1, r2)
        L_0x0426:
            int r0 = r2.f1872a
            int r3 = r21 - r0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.b(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, q1.j):int");
    }

    public final View c(int i2) {
        int i7;
        View h5 = h(0, getChildCount(), i2);
        if (h5 == null || (i7 = this.mFlexboxHelper.f1862c[getPosition(h5)]) == -1) {
            return null;
        }
        return d(h5, this.mFlexLines.get(i7));
    }

    public boolean canScrollHorizontally() {
        int i2;
        if (this.mFlexWrap == 0) {
            return isMainAxisDirectionHorizontal();
        }
        if (!isMainAxisDirectionHorizontal()) {
            return true;
        }
        int width = getWidth();
        View view = this.mParent;
        if (view != null) {
            i2 = view.getWidth();
        } else {
            i2 = 0;
        }
        if (width > i2) {
            return true;
        }
        return false;
    }

    public boolean canScrollVertically() {
        int i2;
        if (this.mFlexWrap == 0) {
            return !isMainAxisDirectionHorizontal();
        }
        if (!isMainAxisDirectionHorizontal()) {
            int height = getHeight();
            View view = this.mParent;
            if (view != null) {
                i2 = view.getHeight();
            } else {
                i2 = 0;
            }
            if (height > i2) {
                return true;
            }
            return false;
        }
        return true;
    }

    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof i;
    }

    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public final int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        a();
        View c5 = c(itemCount);
        View e = e(itemCount);
        if (state.getItemCount() == 0 || c5 == null || e == null) {
            return 0;
        }
        return Math.min(this.mOrientationHelper.getTotalSpace(), this.mOrientationHelper.getDecoratedEnd(e) - this.mOrientationHelper.getDecoratedStart(c5));
    }

    public final int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View c5 = c(itemCount);
        View e = e(itemCount);
        if (!(state.getItemCount() == 0 || c5 == null || e == null)) {
            int position = getPosition(c5);
            int position2 = getPosition(e);
            int abs = Math.abs(this.mOrientationHelper.getDecoratedEnd(e) - this.mOrientationHelper.getDecoratedStart(c5));
            int[] iArr = this.mFlexboxHelper.f1862c;
            int i2 = iArr[position];
            if (!(i2 == 0 || i2 == -1)) {
                return Math.round((((float) i2) * (((float) abs) / ((float) ((iArr[position2] - i2) + 1)))) + ((float) (this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(c5))));
            }
        }
        return 0;
    }

    public final int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View c5 = c(itemCount);
        View e = e(itemCount);
        if (state.getItemCount() == 0 || c5 == null || e == null) {
            return 0;
        }
        int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
        return (int) ((((float) Math.abs(this.mOrientationHelper.getDecoratedEnd(e) - this.mOrientationHelper.getDecoratedStart(c5))) / ((float) ((findLastVisibleItemPosition() - findFirstVisibleItemPosition) + 1))) * ((float) state.getItemCount()));
    }

    public PointF computeScrollVectorForPosition(int i2) {
        View childAt;
        int i7;
        if (getChildCount() == 0 || (childAt = getChildAt(0)) == null) {
            return null;
        }
        if (i2 < getPosition(childAt)) {
            i7 = -1;
        } else {
            i7 = 1;
        }
        if (isMainAxisDirectionHorizontal()) {
            return new PointF(0.0f, (float) i7);
        }
        return new PointF((float) i7, 0.0f);
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public final View d(View view, c cVar) {
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int i2 = cVar.f1855h;
        for (int i7 = 1; i7 < i2; i7++) {
            View childAt = getChildAt(i7);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                if (!this.mIsRtl || isMainAxisDirectionHorizontal) {
                    if (this.mOrientationHelper.getDecoratedStart(view) <= this.mOrientationHelper.getDecoratedStart(childAt)) {
                    }
                } else if (this.mOrientationHelper.getDecoratedEnd(view) >= this.mOrientationHelper.getDecoratedEnd(childAt)) {
                }
                view = childAt;
            }
        }
        return view;
    }

    public final View e(int i2) {
        View h5 = h(getChildCount() - 1, -1, i2);
        if (h5 == null) {
            return null;
        }
        return f(h5, this.mFlexLines.get(this.mFlexboxHelper.f1862c[getPosition(h5)]));
    }

    public final View f(View view, c cVar) {
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int childCount = (getChildCount() - cVar.f1855h) - 1;
        for (int childCount2 = getChildCount() - 2; childCount2 > childCount; childCount2--) {
            View childAt = getChildAt(childCount2);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                if (!this.mIsRtl || isMainAxisDirectionHorizontal) {
                    if (this.mOrientationHelper.getDecoratedEnd(view) >= this.mOrientationHelper.getDecoratedEnd(childAt)) {
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(view) <= this.mOrientationHelper.getDecoratedStart(childAt)) {
                }
                view = childAt;
            }
        }
        return view;
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View g = g(0, getChildCount(), true);
        if (g == null) {
            return -1;
        }
        return getPosition(g);
    }

    public int findFirstVisibleItemPosition() {
        View g = g(0, getChildCount(), false);
        if (g == null) {
            return -1;
        }
        return getPosition(g);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View g = g(getChildCount() - 1, -1, true);
        if (g == null) {
            return -1;
        }
        return getPosition(g);
    }

    public int findLastVisibleItemPosition() {
        View g = g(getChildCount() - 1, -1, false);
        if (g == null) {
            return -1;
        }
        return getPosition(g);
    }

    public final int fixLayoutEndGap(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i7;
        int endAfterPadding;
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i2;
            if (endAfterPadding2 <= 0) {
                return 0;
            }
            i7 = -i(-endAfterPadding2, recycler, state);
        } else {
            int startAfterPadding = i2 - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            i7 = i(startAfterPadding, recycler, state);
        }
        int i8 = i2 + i7;
        if (!z || (endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i8) <= 0) {
            return i7;
        }
        this.mOrientationHelper.offsetChildren(endAfterPadding);
        return endAfterPadding + i7;
    }

    public final int fixLayoutStartGap(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i7;
        int startAfterPadding;
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            int startAfterPadding2 = i2 - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding2 <= 0) {
                return 0;
            }
            i7 = -i(startAfterPadding2, recycler, state);
        } else {
            int endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i2;
            if (endAfterPadding <= 0) {
                return 0;
            }
            i7 = i(-endAfterPadding, recycler, state);
        }
        int i8 = i2 + i7;
        if (!z || (startAfterPadding = i8 - this.mOrientationHelper.getStartAfterPadding()) <= 0) {
            return i7;
        }
        this.mOrientationHelper.offsetChildren(-startAfterPadding);
        return i7 - startAfterPadding;
    }

    public final View g(int i2, int i7, boolean z) {
        int i8;
        boolean z3;
        boolean z7;
        boolean z9;
        int i10 = i7;
        int i11 = i2;
        if (i10 > i11) {
            i8 = 1;
        } else {
            i8 = -1;
        }
        while (i11 != i10) {
            View childAt = getChildAt(i11);
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            int decoratedLeft = getDecoratedLeft(childAt) - ((RecyclerView.LayoutParams) childAt.getLayoutParams()).leftMargin;
            int decoratedTop = getDecoratedTop(childAt) - ((RecyclerView.LayoutParams) childAt.getLayoutParams()).topMargin;
            int decoratedRight = getDecoratedRight(childAt) + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).rightMargin;
            int decoratedBottom = getDecoratedBottom(childAt) + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            boolean z10 = false;
            if (paddingLeft > decoratedLeft || width < decoratedRight) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (decoratedLeft >= width || decoratedRight >= paddingLeft) {
                z7 = true;
            } else {
                z7 = false;
            }
            if (paddingTop > decoratedTop || height < decoratedBottom) {
                z9 = false;
            } else {
                z9 = true;
            }
            if (decoratedTop >= height || decoratedBottom >= paddingTop) {
                z10 = true;
            }
            if (z) {
                if (z3 && z9) {
                }
                i11 += i8;
            } else {
                if (z7 && z10) {
                }
                i11 += i8;
            }
            return childAt;
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [androidx.recyclerview.widget.RecyclerView$LayoutParams, q1.i] */
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        ? layoutParams = new RecyclerView.LayoutParams(-2, -2);
        layoutParams.d = 0.0f;
        layoutParams.e = 1.0f;
        layoutParams.f = -1;
        layoutParams.g = -1.0f;
        layoutParams.f1871j = 16777215;
        layoutParams.k = 16777215;
        return layoutParams;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [androidx.recyclerview.widget.RecyclerView$LayoutParams, q1.i] */
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        ? layoutParams = new RecyclerView.LayoutParams(context, attributeSet);
        layoutParams.d = 0.0f;
        layoutParams.e = 1.0f;
        layoutParams.f = -1;
        layoutParams.g = -1.0f;
        layoutParams.f1871j = 16777215;
        layoutParams.k = 16777215;
        return layoutParams;
    }

    public int getAlignContent() {
        return 5;
    }

    public int getAlignItems() {
        return this.mAlignItems;
    }

    public int getChildHeightMeasureSpec(int i2, int i7, int i8) {
        return RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), i7, i8, canScrollVertically());
    }

    public int getChildWidthMeasureSpec(int i2, int i7, int i8) {
        return RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), i7, i8, canScrollHorizontally());
    }

    public int getDecorationLengthCrossAxis(View view) {
        int leftDecorationWidth;
        int rightDecorationWidth;
        if (isMainAxisDirectionHorizontal()) {
            leftDecorationWidth = getTopDecorationHeight(view);
            rightDecorationWidth = getBottomDecorationHeight(view);
        } else {
            leftDecorationWidth = getLeftDecorationWidth(view);
            rightDecorationWidth = getRightDecorationWidth(view);
        }
        return rightDecorationWidth + leftDecorationWidth;
    }

    public int getDecorationLengthMainAxis(View view, int i2, int i7) {
        int topDecorationHeight;
        int bottomDecorationHeight;
        if (isMainAxisDirectionHorizontal()) {
            topDecorationHeight = getLeftDecorationWidth(view);
            bottomDecorationHeight = getRightDecorationWidth(view);
        } else {
            topDecorationHeight = getTopDecorationHeight(view);
            bottomDecorationHeight = getBottomDecorationHeight(view);
        }
        return bottomDecorationHeight + topDecorationHeight;
    }

    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    public View getFlexItemAt(int i2) {
        View view = this.mViewCache.get(i2);
        if (view != null) {
            return view;
        }
        return this.mRecycler.getViewForPosition(i2);
    }

    public int getFlexItemCount() {
        return this.mState.getItemCount();
    }

    public List<c> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.mFlexLines.size());
        int size = this.mFlexLines.size();
        for (int i2 = 0; i2 < size; i2++) {
            c cVar = this.mFlexLines.get(i2);
            if (cVar.f1855h != 0) {
                arrayList.add(cVar);
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

    public int getLargestMainSize() {
        if (this.mFlexLines.size() == 0) {
            return 0;
        }
        int size = this.mFlexLines.size();
        int i2 = Integer.MIN_VALUE;
        for (int i7 = 0; i7 < size; i7++) {
            i2 = Math.max(i2, this.mFlexLines.get(i7).e);
        }
        return i2;
    }

    public int getMaxLine() {
        return this.mMaxLine;
    }

    public View getReorderedFlexItemAt(int i2) {
        return getFlexItemAt(i2);
    }

    public int getSumOfCrossSize() {
        int size = this.mFlexLines.size();
        int i2 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            i2 += this.mFlexLines.get(i7).g;
        }
        return i2;
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [q1.j, java.lang.Object] */
    public final View h(int i2, int i7, int i8) {
        int position;
        a();
        int i10 = 1;
        if (this.mLayoutState == null) {
            ? obj = new Object();
            obj.f1874h = 1;
            this.mLayoutState = obj;
        }
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        if (i7 <= i2) {
            i10 = -1;
        }
        View view = null;
        View view2 = null;
        while (i2 != i7) {
            View childAt = getChildAt(i2);
            if (childAt != null && (position = getPosition(childAt)) >= 0 && position < i8) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(childAt) >= startAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) <= endAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i2 += i10;
        }
        if (view != null) {
            return view;
        }
        return view2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x01f0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int i(int r20, androidx.recyclerview.widget.RecyclerView.Recycler r21, androidx.recyclerview.widget.RecyclerView.State r22) {
        /*
            r19 = this;
            r0 = r19
            int r1 = r0.getChildCount()
            r2 = 0
            if (r1 == 0) goto L_0x0209
            if (r20 != 0) goto L_0x000d
            goto L_0x0209
        L_0x000d:
            r0.a()
            q1.j r1 = r0.mLayoutState
            r3 = 1
            r1.f1875i = r3
            boolean r1 = r0.isMainAxisDirectionHorizontal()
            if (r1 != 0) goto L_0x0021
            boolean r1 = r0.mIsRtl
            if (r1 == 0) goto L_0x0021
            r1 = r3
            goto L_0x0022
        L_0x0021:
            r1 = r2
        L_0x0022:
            r4 = -1
            if (r1 == 0) goto L_0x002b
            if (r20 >= 0) goto L_0x0029
        L_0x0027:
            r5 = r3
            goto L_0x002e
        L_0x0029:
            r5 = r4
            goto L_0x002e
        L_0x002b:
            if (r20 <= 0) goto L_0x0029
            goto L_0x0027
        L_0x002e:
            int r6 = java.lang.Math.abs(r20)
            q1.j r7 = r0.mLayoutState
            r7.f1874h = r5
            boolean r7 = r0.isMainAxisDirectionHorizontal()
            int r8 = r0.getWidth()
            int r9 = r0.getWidthMode()
            int r12 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r9)
            int r8 = r0.getHeight()
            int r9 = r0.getHeightMode()
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r9)
            if (r7 != 0) goto L_0x005a
            boolean r8 = r0.mIsRtl
            if (r8 == 0) goto L_0x005a
            r8 = r3
            goto L_0x005b
        L_0x005a:
            r8 = r2
        L_0x005b:
            if (r5 != r3) goto L_0x0143
            int r9 = r0.getChildCount()
            int r9 = r9 - r3
            android.view.View r9 = r0.getChildAt(r9)
            if (r9 != 0) goto L_0x006a
            goto L_0x01e0
        L_0x006a:
            q1.j r10 = r0.mLayoutState
            androidx.recyclerview.widget.OrientationHelper r11 = r0.mOrientationHelper
            int r11 = r11.getDecoratedEnd(r9)
            r10.e = r11
            int r10 = r0.getPosition(r9)
            q1.f r11 = r0.mFlexboxHelper
            int[] r11 = r11.f1862c
            r11 = r11[r10]
            java.util.List<q1.c> r14 = r0.mFlexLines
            java.lang.Object r11 = r14.get(r11)
            q1.c r11 = (q1.c) r11
            android.view.View r9 = r0.f(r9, r11)
            q1.j r11 = r0.mLayoutState
            r11.getClass()
            int r10 = r10 + r3
            r11.d = r10
            q1.f r14 = r0.mFlexboxHelper
            int[] r14 = r14.f1862c
            int r15 = r14.length
            if (r15 > r10) goto L_0x009c
            r11.f1873c = r4
            goto L_0x00a0
        L_0x009c:
            r10 = r14[r10]
            r11.f1873c = r10
        L_0x00a0:
            if (r8 == 0) goto L_0x00c7
            androidx.recyclerview.widget.OrientationHelper r8 = r0.mOrientationHelper
            int r8 = r8.getDecoratedStart(r9)
            r11.e = r8
            q1.j r8 = r0.mLayoutState
            androidx.recyclerview.widget.OrientationHelper r10 = r0.mOrientationHelper
            int r9 = r10.getDecoratedStart(r9)
            int r9 = -r9
            androidx.recyclerview.widget.OrientationHelper r10 = r0.mOrientationHelper
            int r10 = r10.getStartAfterPadding()
            int r10 = r10 + r9
            r8.f = r10
            q1.j r8 = r0.mLayoutState
            int r9 = r8.f
            int r9 = java.lang.Math.max(r9, r2)
            r8.f = r9
            goto L_0x00e0
        L_0x00c7:
            androidx.recyclerview.widget.OrientationHelper r8 = r0.mOrientationHelper
            int r8 = r8.getDecoratedEnd(r9)
            r11.e = r8
            q1.j r8 = r0.mLayoutState
            androidx.recyclerview.widget.OrientationHelper r10 = r0.mOrientationHelper
            int r9 = r10.getDecoratedEnd(r9)
            androidx.recyclerview.widget.OrientationHelper r10 = r0.mOrientationHelper
            int r10 = r10.getEndAfterPadding()
            int r9 = r9 - r10
            r8.f = r9
        L_0x00e0:
            q1.j r8 = r0.mLayoutState
            int r8 = r8.f1873c
            if (r8 == r4) goto L_0x00ef
            java.util.List<q1.c> r4 = r0.mFlexLines
            int r4 = r4.size()
            int r4 = r4 - r3
            if (r8 <= r4) goto L_0x01d8
        L_0x00ef:
            q1.j r3 = r0.mLayoutState
            int r3 = r3.d
            int r4 = r0.getFlexItemCount()
            if (r3 > r4) goto L_0x01d8
            q1.j r3 = r0.mLayoutState
            int r4 = r3.f
            int r14 = r6 - r4
            q1.d r11 = r0.mFlexLinesResult
            r4 = 0
            r11.f1860a = r4
            r11.b = r2
            if (r14 <= 0) goto L_0x01d8
            if (r7 == 0) goto L_0x0118
            q1.f r10 = r0.mFlexboxHelper
            int r15 = r3.d
            java.util.List<q1.c> r3 = r0.mFlexLines
            r16 = -1
            r17 = r3
            r10.b(r11, r12, r13, r14, r15, r16, r17)
            goto L_0x012f
        L_0x0118:
            q1.f r10 = r0.mFlexboxHelper
            int r15 = r3.d
            java.util.List<q1.c> r3 = r0.mFlexLines
            r16 = -1
            r17 = r13
            r13 = r12
            r12 = r17
            r17 = r3
            r10.b(r11, r12, r13, r14, r15, r16, r17)
            r18 = r13
            r13 = r12
            r12 = r18
        L_0x012f:
            q1.f r3 = r0.mFlexboxHelper
            q1.j r4 = r0.mLayoutState
            int r4 = r4.d
            r3.h(r12, r13, r4)
            q1.f r3 = r0.mFlexboxHelper
            q1.j r4 = r0.mLayoutState
            int r4 = r4.d
            r3.u(r4)
            goto L_0x01d8
        L_0x0143:
            android.view.View r7 = r0.getChildAt(r2)
            if (r7 != 0) goto L_0x014b
            goto L_0x01e0
        L_0x014b:
            q1.j r9 = r0.mLayoutState
            androidx.recyclerview.widget.OrientationHelper r10 = r0.mOrientationHelper
            int r10 = r10.getDecoratedStart(r7)
            r9.e = r10
            int r9 = r0.getPosition(r7)
            q1.f r10 = r0.mFlexboxHelper
            int[] r10 = r10.f1862c
            r10 = r10[r9]
            java.util.List<q1.c> r11 = r0.mFlexLines
            java.lang.Object r10 = r11.get(r10)
            q1.c r10 = (q1.c) r10
            android.view.View r7 = r0.d(r7, r10)
            q1.j r10 = r0.mLayoutState
            r10.getClass()
            q1.f r11 = r0.mFlexboxHelper
            int[] r11 = r11.f1862c
            r11 = r11[r9]
            if (r11 != r4) goto L_0x0179
            r11 = r2
        L_0x0179:
            if (r11 <= 0) goto L_0x018d
            java.util.List<q1.c> r4 = r0.mFlexLines
            int r10 = r11 + -1
            java.lang.Object r4 = r4.get(r10)
            q1.c r4 = (q1.c) r4
            q1.j r10 = r0.mLayoutState
            int r4 = r4.f1855h
            int r9 = r9 - r4
            r10.d = r9
            goto L_0x018f
        L_0x018d:
            r10.d = r4
        L_0x018f:
            q1.j r4 = r0.mLayoutState
            if (r11 <= 0) goto L_0x0195
            int r11 = r11 - r3
            goto L_0x0196
        L_0x0195:
            r11 = r2
        L_0x0196:
            r4.f1873c = r11
            if (r8 == 0) goto L_0x01be
            androidx.recyclerview.widget.OrientationHelper r3 = r0.mOrientationHelper
            int r3 = r3.getDecoratedEnd(r7)
            r4.e = r3
            q1.j r3 = r0.mLayoutState
            androidx.recyclerview.widget.OrientationHelper r4 = r0.mOrientationHelper
            int r4 = r4.getDecoratedEnd(r7)
            androidx.recyclerview.widget.OrientationHelper r7 = r0.mOrientationHelper
            int r7 = r7.getEndAfterPadding()
            int r4 = r4 - r7
            r3.f = r4
            q1.j r3 = r0.mLayoutState
            int r4 = r3.f
            int r4 = java.lang.Math.max(r4, r2)
            r3.f = r4
            goto L_0x01d8
        L_0x01be:
            androidx.recyclerview.widget.OrientationHelper r3 = r0.mOrientationHelper
            int r3 = r3.getDecoratedStart(r7)
            r4.e = r3
            q1.j r3 = r0.mLayoutState
            androidx.recyclerview.widget.OrientationHelper r4 = r0.mOrientationHelper
            int r4 = r4.getDecoratedStart(r7)
            int r4 = -r4
            androidx.recyclerview.widget.OrientationHelper r7 = r0.mOrientationHelper
            int r7 = r7.getStartAfterPadding()
            int r7 = r7 + r4
            r3.f = r7
        L_0x01d8:
            q1.j r3 = r0.mLayoutState
            int r4 = r3.f
            int r4 = r6 - r4
            r3.f1872a = r4
        L_0x01e0:
            q1.j r3 = r0.mLayoutState
            int r4 = r3.f
            r7 = r21
            r8 = r22
            int r3 = r0.b(r7, r8, r3)
            int r3 = r3 + r4
            if (r3 >= 0) goto L_0x01f0
            goto L_0x0209
        L_0x01f0:
            if (r1 == 0) goto L_0x01fa
            if (r6 <= r3) goto L_0x01f7
            int r1 = -r5
            int r1 = r1 * r3
            goto L_0x01fe
        L_0x01f7:
            r1 = r20
            goto L_0x01fe
        L_0x01fa:
            if (r6 <= r3) goto L_0x01f7
            int r1 = r5 * r3
        L_0x01fe:
            androidx.recyclerview.widget.OrientationHelper r2 = r0.mOrientationHelper
            int r3 = -r1
            r2.offsetChildren(r3)
            q1.j r0 = r0.mLayoutState
            r0.g = r1
            return r1
        L_0x0209:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.i(int, androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State):int");
    }

    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public boolean isMainAxisDirectionHorizontal() {
        int i2 = this.mFlexDirection;
        if (i2 == 0 || i2 == 1) {
            return true;
        }
        return false;
    }

    public final int j(int i2) {
        int i7;
        int i8;
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        a();
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        View view = this.mParent;
        if (isMainAxisDirectionHorizontal) {
            i7 = view.getWidth();
        } else {
            i7 = view.getHeight();
        }
        if (isMainAxisDirectionHorizontal) {
            i8 = getWidth();
        } else {
            i8 = getHeight();
        }
        if (getLayoutDirection() == 1) {
            int abs = Math.abs(i2);
            if (i2 < 0) {
                return -Math.min((i8 + this.mAnchorInfo.d) - i7, abs);
            }
            int i10 = this.mAnchorInfo.d;
            if (i10 + i2 > 0) {
                return -i10;
            }
        } else if (i2 > 0) {
            return Math.min((i8 - this.mAnchorInfo.d) - i7, i2);
        } else {
            int i11 = this.mAnchorInfo.d;
            if (i11 + i2 < 0) {
                return -i11;
            }
        }
        return i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r2 = r0 - 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void k(androidx.recyclerview.widget.RecyclerView.Recycler r10, q1.j r11) {
        /*
            r9 = this;
            boolean r0 = r11.f1875i
            if (r0 != 0) goto L_0x0006
            goto L_0x0110
        L_0x0006:
            int r0 = r11.f1874h
            r1 = -1
            if (r0 != r1) goto L_0x008b
            int r0 = r11.f
            if (r0 >= 0) goto L_0x0011
            goto L_0x0110
        L_0x0011:
            int r0 = r9.getChildCount()
            if (r0 != 0) goto L_0x0019
            goto L_0x0110
        L_0x0019:
            int r2 = r0 + -1
            android.view.View r3 = r9.getChildAt(r2)
            if (r3 != 0) goto L_0x0023
            goto L_0x0110
        L_0x0023:
            q1.f r4 = r9.mFlexboxHelper
            int[] r4 = r4.f1862c
            int r3 = r9.getPosition(r3)
            r3 = r4[r3]
            if (r3 != r1) goto L_0x0031
            goto L_0x0110
        L_0x0031:
            java.util.List<q1.c> r1 = r9.mFlexLines
            java.lang.Object r1 = r1.get(r3)
            q1.c r1 = (q1.c) r1
            r4 = r2
        L_0x003a:
            if (r4 < 0) goto L_0x0083
            android.view.View r5 = r9.getChildAt(r4)
            if (r5 != 0) goto L_0x0043
            goto L_0x0080
        L_0x0043:
            int r6 = r11.f
            boolean r7 = r9.isMainAxisDirectionHorizontal()
            if (r7 != 0) goto L_0x0058
            boolean r7 = r9.mIsRtl
            if (r7 == 0) goto L_0x0058
            androidx.recyclerview.widget.OrientationHelper r7 = r9.mOrientationHelper
            int r7 = r7.getDecoratedEnd(r5)
            if (r7 > r6) goto L_0x0083
            goto L_0x0067
        L_0x0058:
            androidx.recyclerview.widget.OrientationHelper r7 = r9.mOrientationHelper
            int r7 = r7.getDecoratedStart(r5)
            androidx.recyclerview.widget.OrientationHelper r8 = r9.mOrientationHelper
            int r8 = r8.getEnd()
            int r8 = r8 - r6
            if (r7 < r8) goto L_0x0083
        L_0x0067:
            int r6 = r1.f1858o
            int r5 = r9.getPosition(r5)
            if (r6 != r5) goto L_0x0080
            if (r3 > 0) goto L_0x0073
            r0 = r4
            goto L_0x0083
        L_0x0073:
            int r0 = r11.f1874h
            int r3 = r3 + r0
            java.util.List<q1.c> r0 = r9.mFlexLines
            java.lang.Object r0 = r0.get(r3)
            q1.c r0 = (q1.c) r0
            r1 = r0
            r0 = r4
        L_0x0080:
            int r4 = r4 + -1
            goto L_0x003a
        L_0x0083:
            if (r2 < r0) goto L_0x0110
            r9.removeAndRecycleViewAt(r2, r10)
            int r2 = r2 + -1
            goto L_0x0083
        L_0x008b:
            int r0 = r11.f
            if (r0 >= 0) goto L_0x0091
            goto L_0x0110
        L_0x0091:
            int r0 = r9.getChildCount()
            if (r0 != 0) goto L_0x0099
            goto L_0x0110
        L_0x0099:
            r2 = 0
            android.view.View r3 = r9.getChildAt(r2)
            if (r3 != 0) goto L_0x00a2
            goto L_0x0110
        L_0x00a2:
            q1.f r4 = r9.mFlexboxHelper
            int[] r4 = r4.f1862c
            int r3 = r9.getPosition(r3)
            r3 = r4[r3]
            if (r3 != r1) goto L_0x00af
            goto L_0x0110
        L_0x00af:
            java.util.List<q1.c> r4 = r9.mFlexLines
            java.lang.Object r4 = r4.get(r3)
            q1.c r4 = (q1.c) r4
        L_0x00b7:
            if (r2 >= r0) goto L_0x0108
            android.view.View r5 = r9.getChildAt(r2)
            if (r5 != 0) goto L_0x00c0
            goto L_0x0105
        L_0x00c0:
            int r6 = r11.f
            boolean r7 = r9.isMainAxisDirectionHorizontal()
            if (r7 != 0) goto L_0x00dc
            boolean r7 = r9.mIsRtl
            if (r7 == 0) goto L_0x00dc
            androidx.recyclerview.widget.OrientationHelper r7 = r9.mOrientationHelper
            int r7 = r7.getEnd()
            androidx.recyclerview.widget.OrientationHelper r8 = r9.mOrientationHelper
            int r8 = r8.getDecoratedStart(r5)
            int r7 = r7 - r8
            if (r7 > r6) goto L_0x0108
            goto L_0x00e4
        L_0x00dc:
            androidx.recyclerview.widget.OrientationHelper r7 = r9.mOrientationHelper
            int r7 = r7.getDecoratedEnd(r5)
            if (r7 > r6) goto L_0x0108
        L_0x00e4:
            int r6 = r4.f1859p
            int r5 = r9.getPosition(r5)
            if (r6 != r5) goto L_0x0105
            java.util.List<q1.c> r1 = r9.mFlexLines
            int r1 = r1.size()
            int r1 = r1 + -1
            if (r3 < r1) goto L_0x00f8
            r1 = r2
            goto L_0x0108
        L_0x00f8:
            int r1 = r11.f1874h
            int r3 = r3 + r1
            java.util.List<q1.c> r1 = r9.mFlexLines
            java.lang.Object r1 = r1.get(r3)
            q1.c r1 = (q1.c) r1
            r4 = r1
            r1 = r2
        L_0x0105:
            int r2 = r2 + 1
            goto L_0x00b7
        L_0x0108:
            if (r1 < 0) goto L_0x0110
            r9.removeAndRecycleViewAt(r1, r10)
            int r1 = r1 + -1
            goto L_0x0108
        L_0x0110:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.k(androidx.recyclerview.widget.RecyclerView$Recycler, q1.j):void");
    }

    public final void l() {
        int i2;
        boolean z;
        if (isMainAxisDirectionHorizontal()) {
            i2 = getHeightMode();
        } else {
            i2 = getWidthMode();
        }
        j jVar = this.mLayoutState;
        if (i2 == 0 || i2 == Integer.MIN_VALUE) {
            z = true;
        } else {
            z = false;
        }
        jVar.b = z;
    }

    public final boolean m(View view, int i2, int i7, i iVar) {
        if (view.isLayoutRequested() || !isMeasurementCacheEnabled() || !isMeasurementUpToDate(view.getWidth(), i2, iVar.width) || !isMeasurementUpToDate(view.getHeight(), i7, iVar.height)) {
            return true;
        }
        return false;
    }

    public final void n(int i2) {
        if (i2 < findLastVisibleItemPosition()) {
            int childCount = getChildCount();
            this.mFlexboxHelper.j(childCount);
            this.mFlexboxHelper.k(childCount);
            this.mFlexboxHelper.i(childCount);
            if (i2 < this.mFlexboxHelper.f1862c.length) {
                this.mDirtyPosition = i2;
                View childAt = getChildAt(0);
                if (childAt != null) {
                    this.mPendingScrollPosition = getPosition(childAt);
                    if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
                        this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedStart(childAt) - this.mOrientationHelper.getStartAfterPadding();
                        return;
                    }
                    this.mPendingScrollPositionOffset = this.mOrientationHelper.getEndPadding() + this.mOrientationHelper.getDecoratedEnd(childAt);
                }
            }
        }
    }

    public final void o(h hVar, boolean z, boolean z3) {
        int i2;
        if (z3) {
            l();
        } else {
            this.mLayoutState.b = false;
        }
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            this.mLayoutState.f1872a = this.mOrientationHelper.getEndAfterPadding() - hVar.f1867c;
        } else {
            this.mLayoutState.f1872a = hVar.f1867c - getPaddingRight();
        }
        j jVar = this.mLayoutState;
        jVar.d = hVar.f1866a;
        jVar.f1874h = 1;
        jVar.e = hVar.f1867c;
        jVar.f = Integer.MIN_VALUE;
        jVar.f1873c = hVar.b;
        if (z && this.mFlexLines.size() > 1 && (i2 = hVar.b) >= 0 && i2 < this.mFlexLines.size() - 1) {
            j jVar2 = this.mLayoutState;
            jVar2.f1873c++;
            jVar2.d += this.mFlexLines.get(hVar.b).f1855h;
        }
    }

    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
    }

    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mParent = (View) recyclerView.getParent();
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    public void onItemsAdded(RecyclerView recyclerView, int i2, int i7) {
        super.onItemsAdded(recyclerView, i2, i7);
        n(i2);
    }

    public void onItemsMoved(RecyclerView recyclerView, int i2, int i7, int i8) {
        super.onItemsMoved(recyclerView, i2, i7, i8);
        n(Math.min(i2, i7));
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i2, int i7) {
        super.onItemsRemoved(recyclerView, i2, i7);
        n(i2);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i2, int i7, Object obj) {
        super.onItemsUpdated(recyclerView, i2, i7, obj);
        n(i2);
    }

    /* JADX WARNING: type inference failed for: r4v35, types: [q1.j, java.lang.Object] */
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        boolean z;
        int i2;
        boolean z3;
        int i7;
        int i8;
        int i10;
        View view;
        OrientationHelper orientationHelper;
        int i11;
        int i12;
        View childAt;
        boolean z7;
        int i13;
        int i14;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        this.mRecycler = recycler2;
        this.mState = state2;
        int itemCount = state2.getItemCount();
        if (itemCount != 0 || !state2.isPreLayout()) {
            int layoutDirection = getLayoutDirection();
            int i15 = this.mFlexDirection;
            if (i15 == 0) {
                if (layoutDirection == 1) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                this.mIsRtl = z9;
                if (this.mFlexWrap == 2) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                this.mFromBottomToTop = z10;
            } else if (i15 == 1) {
                if (layoutDirection != 1) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                this.mIsRtl = z11;
                if (this.mFlexWrap == 2) {
                    z12 = true;
                } else {
                    z12 = false;
                }
                this.mFromBottomToTop = z12;
            } else if (i15 == 2) {
                if (layoutDirection == 1) {
                    z13 = true;
                } else {
                    z13 = false;
                }
                this.mIsRtl = z13;
                if (this.mFlexWrap == 2) {
                    this.mIsRtl = !z13;
                }
                this.mFromBottomToTop = false;
            } else if (i15 != 3) {
                this.mIsRtl = false;
                this.mFromBottomToTop = false;
            } else {
                if (layoutDirection == 1) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                this.mIsRtl = z14;
                if (this.mFlexWrap == 2) {
                    this.mIsRtl = !z14;
                }
                this.mFromBottomToTop = true;
            }
            a();
            if (this.mLayoutState == null) {
                ? obj = new Object();
                obj.f1874h = 1;
                this.mLayoutState = obj;
            }
            this.mFlexboxHelper.j(itemCount);
            this.mFlexboxHelper.k(itemCount);
            this.mFlexboxHelper.i(itemCount);
            this.mLayoutState.f1875i = false;
            k kVar = this.mPendingSavedState;
            if (kVar != null && (i14 = kVar.d) >= 0 && i14 < itemCount) {
                this.mPendingScrollPosition = i14;
            }
            h hVar = this.mAnchorInfo;
            if (!(hVar.f && this.mPendingScrollPosition == -1 && kVar == null)) {
                h.b(hVar);
                h hVar2 = this.mAnchorInfo;
                k kVar2 = this.mPendingSavedState;
                if (!state2.isPreLayout() && (i12 = this.mPendingScrollPosition) != -1) {
                    if (i12 < 0 || i12 >= state2.getItemCount()) {
                        this.mPendingScrollPosition = -1;
                        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
                    } else {
                        int i16 = this.mPendingScrollPosition;
                        hVar2.f1866a = i16;
                        hVar2.b = this.mFlexboxHelper.f1862c[i16];
                        k kVar3 = this.mPendingSavedState;
                        if (kVar3 != null) {
                            int itemCount2 = state2.getItemCount();
                            int i17 = kVar3.d;
                            if (i17 >= 0 && i17 < itemCount2) {
                                hVar2.f1867c = this.mOrientationHelper.getStartAfterPadding() + kVar2.e;
                                hVar2.g = true;
                                hVar2.b = -1;
                                this.mAnchorInfo.f = true;
                            }
                        }
                        if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                            View findViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                            if (findViewByPosition == null) {
                                if (getChildCount() > 0 && (childAt = getChildAt(0)) != null) {
                                    if (this.mPendingScrollPosition < getPosition(childAt)) {
                                        z7 = true;
                                    } else {
                                        z7 = false;
                                    }
                                    hVar2.e = z7;
                                }
                                h.a(hVar2);
                            } else if (this.mOrientationHelper.getDecoratedMeasurement(findViewByPosition) > this.mOrientationHelper.getTotalSpace()) {
                                h.a(hVar2);
                            } else if (this.mOrientationHelper.getDecoratedStart(findViewByPosition) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                                hVar2.f1867c = this.mOrientationHelper.getStartAfterPadding();
                                hVar2.e = false;
                            } else if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(findViewByPosition) < 0) {
                                hVar2.f1867c = this.mOrientationHelper.getEndAfterPadding();
                                hVar2.e = true;
                            } else {
                                if (hVar2.e) {
                                    i13 = this.mOrientationHelper.getTotalSpaceChange() + this.mOrientationHelper.getDecoratedEnd(findViewByPosition);
                                } else {
                                    i13 = this.mOrientationHelper.getDecoratedStart(findViewByPosition);
                                }
                                hVar2.f1867c = i13;
                            }
                        } else if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
                            hVar2.f1867c = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
                        } else {
                            hVar2.f1867c = this.mPendingScrollPositionOffset - this.mOrientationHelper.getEndPadding();
                        }
                        this.mAnchorInfo.f = true;
                    }
                }
                if (getChildCount() != 0) {
                    if (hVar2.e) {
                        view = e(state2.getItemCount());
                    } else {
                        view = c(state2.getItemCount());
                    }
                    if (view != null) {
                        FlexboxLayoutManager flexboxLayoutManager = hVar2.f1868h;
                        if (flexboxLayoutManager.mFlexWrap == 0) {
                            orientationHelper = flexboxLayoutManager.mSubOrientationHelper;
                        } else {
                            orientationHelper = flexboxLayoutManager.mOrientationHelper;
                        }
                        if (flexboxLayoutManager.isMainAxisDirectionHorizontal() || !flexboxLayoutManager.mIsRtl) {
                            if (hVar2.e) {
                                hVar2.f1867c = orientationHelper.getTotalSpaceChange() + orientationHelper.getDecoratedEnd(view);
                            } else {
                                hVar2.f1867c = orientationHelper.getDecoratedStart(view);
                            }
                        } else if (hVar2.e) {
                            hVar2.f1867c = orientationHelper.getTotalSpaceChange() + orientationHelper.getDecoratedStart(view);
                        } else {
                            hVar2.f1867c = orientationHelper.getDecoratedEnd(view);
                        }
                        hVar2.f1866a = flexboxLayoutManager.getPosition(view);
                        hVar2.g = false;
                        int[] iArr = flexboxLayoutManager.mFlexboxHelper.f1862c;
                        int i18 = hVar2.f1866a;
                        if (i18 == -1) {
                            i18 = 0;
                        }
                        int i19 = iArr[i18];
                        if (i19 == -1) {
                            i19 = 0;
                        }
                        hVar2.b = i19;
                        if (flexboxLayoutManager.mFlexLines.size() > hVar2.b) {
                            hVar2.f1866a = ((c) flexboxLayoutManager.mFlexLines.get(hVar2.b)).f1858o;
                        }
                        if (!state2.isPreLayout() && supportsPredictiveItemAnimations() && (this.mOrientationHelper.getDecoratedStart(view) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(view) < this.mOrientationHelper.getStartAfterPadding())) {
                            if (hVar2.e) {
                                i11 = this.mOrientationHelper.getEndAfterPadding();
                            } else {
                                i11 = this.mOrientationHelper.getStartAfterPadding();
                            }
                            hVar2.f1867c = i11;
                        }
                        this.mAnchorInfo.f = true;
                    }
                }
                h.a(hVar2);
                hVar2.f1866a = 0;
                hVar2.b = 0;
                this.mAnchorInfo.f = true;
            }
            detachAndScrapAttachedViews(recycler);
            h hVar3 = this.mAnchorInfo;
            if (hVar3.e) {
                p(hVar3, false, true);
            } else {
                o(hVar3, false, true);
            }
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
            int width = getWidth();
            int height = getHeight();
            if (isMainAxisDirectionHorizontal()) {
                int i20 = this.mLastWidth;
                if (i20 == Integer.MIN_VALUE || i20 == width) {
                    z = false;
                } else {
                    z = true;
                }
                j jVar = this.mLayoutState;
                if (jVar.b) {
                    i2 = this.mContext.getResources().getDisplayMetrics().heightPixels;
                } else {
                    i2 = jVar.f1872a;
                }
            } else {
                int i21 = this.mLastHeight;
                if (i21 == Integer.MIN_VALUE || i21 == height) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                j jVar2 = this.mLayoutState;
                if (jVar2.b) {
                    i2 = this.mContext.getResources().getDisplayMetrics().widthPixels;
                } else {
                    i2 = jVar2.f1872a;
                }
            }
            int i22 = i2;
            this.mLastWidth = width;
            this.mLastHeight = height;
            int i23 = this.mDirtyPosition;
            if (i23 != -1 || (this.mPendingScrollPosition == -1 && !z)) {
                if (i23 != -1) {
                    i10 = Math.min(i23, this.mAnchorInfo.f1866a);
                } else {
                    i10 = this.mAnchorInfo.f1866a;
                }
                d dVar = this.mFlexLinesResult;
                dVar.f1860a = null;
                dVar.b = 0;
                if (isMainAxisDirectionHorizontal()) {
                    if (this.mFlexLines.size() > 0) {
                        this.mFlexboxHelper.d(i10, this.mFlexLines);
                        this.mFlexboxHelper.b(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i22, i10, this.mAnchorInfo.f1866a, this.mFlexLines);
                    } else {
                        this.mFlexboxHelper.i(itemCount);
                        this.mFlexboxHelper.b(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i22, 0, -1, this.mFlexLines);
                    }
                } else if (this.mFlexLines.size() > 0) {
                    this.mFlexboxHelper.d(i10, this.mFlexLines);
                    int i24 = makeMeasureSpec2;
                    int i25 = makeMeasureSpec;
                    int i26 = i24;
                    int i27 = i10;
                    this.mFlexboxHelper.b(this.mFlexLinesResult, i26, i25, i22, i27, this.mAnchorInfo.f1866a, this.mFlexLines);
                    int i28 = i25;
                    makeMeasureSpec2 = i26;
                    makeMeasureSpec = i28;
                    i10 = i27;
                } else {
                    this.mFlexboxHelper.i(itemCount);
                    int i29 = makeMeasureSpec2;
                    int i30 = makeMeasureSpec;
                    int i31 = i29;
                    this.mFlexboxHelper.b(this.mFlexLinesResult, i31, i30, i22, 0, -1, this.mFlexLines);
                    int i32 = i30;
                    makeMeasureSpec2 = i31;
                    makeMeasureSpec = i32;
                }
                this.mFlexLines = this.mFlexLinesResult.f1860a;
                this.mFlexboxHelper.h(makeMeasureSpec, makeMeasureSpec2, i10);
                this.mFlexboxHelper.u(i10);
            } else if (!this.mAnchorInfo.e) {
                this.mFlexLines.clear();
                d dVar2 = this.mFlexLinesResult;
                dVar2.f1860a = null;
                dVar2.b = 0;
                if (isMainAxisDirectionHorizontal()) {
                    this.mFlexboxHelper.b(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i22, 0, this.mAnchorInfo.f1866a, this.mFlexLines);
                } else {
                    int i33 = makeMeasureSpec2;
                    int i34 = makeMeasureSpec;
                    int i35 = i33;
                    this.mFlexboxHelper.b(this.mFlexLinesResult, i35, i34, i22, 0, this.mAnchorInfo.f1866a, this.mFlexLines);
                    int i36 = i34;
                    makeMeasureSpec2 = i35;
                    makeMeasureSpec = i36;
                }
                this.mFlexLines = this.mFlexLinesResult.f1860a;
                this.mFlexboxHelper.h(makeMeasureSpec, makeMeasureSpec2, 0);
                this.mFlexboxHelper.u(0);
                h hVar4 = this.mAnchorInfo;
                int i37 = this.mFlexboxHelper.f1862c[hVar4.f1866a];
                hVar4.b = i37;
                this.mLayoutState.f1873c = i37;
            }
            b(recycler2, state2, this.mLayoutState);
            h hVar5 = this.mAnchorInfo;
            if (hVar5.e) {
                i7 = this.mLayoutState.e;
                o(hVar5, true, false);
                b(recycler2, state2, this.mLayoutState);
                i8 = this.mLayoutState.e;
            } else {
                int i38 = this.mLayoutState.e;
                p(hVar5, true, false);
                b(recycler2, state2, this.mLayoutState);
                int i39 = i38;
                i7 = this.mLayoutState.e;
                i8 = i39;
            }
            if (getChildCount() <= 0) {
                return;
            }
            if (this.mAnchorInfo.e) {
                fixLayoutStartGap(fixLayoutEndGap(i8, recycler2, state2, true) + i7, recycler2, state2, false);
            } else {
                fixLayoutEndGap(fixLayoutStartGap(i7, recycler2, state2, true) + i8, recycler2, state2, false);
            }
        }
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mDirtyPosition = -1;
        h.b(this.mAnchorInfo);
        this.mViewCache.clear();
    }

    public void onNewFlexItemAdded(View view, int i2, int i7, c cVar) {
        calculateItemDecorationsForChild(view, TEMP_RECT);
        if (isMainAxisDirectionHorizontal()) {
            int rightDecorationWidth = getRightDecorationWidth(view) + getLeftDecorationWidth(view);
            cVar.e += rightDecorationWidth;
            cVar.f += rightDecorationWidth;
            return;
        }
        int bottomDecorationHeight = getBottomDecorationHeight(view) + getTopDecorationHeight(view);
        cVar.e += bottomDecorationHeight;
        cVar.f += bottomDecorationHeight;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof k) {
            this.mPendingSavedState = (k) parcelable;
            requestLayout();
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [android.os.Parcelable, q1.k, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v4, types: [android.os.Parcelable, q1.k, java.lang.Object] */
    public Parcelable onSaveInstanceState() {
        k kVar = this.mPendingSavedState;
        if (kVar != null) {
            ? obj = new Object();
            obj.d = kVar.d;
            obj.e = kVar.e;
            return obj;
        }
        ? obj2 = new Object();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            obj2.d = getPosition(childAt);
            obj2.e = this.mOrientationHelper.getDecoratedStart(childAt) - this.mOrientationHelper.getStartAfterPadding();
            return obj2;
        }
        obj2.d = -1;
        return obj2;
    }

    public final void p(h hVar, boolean z, boolean z3) {
        int i2;
        if (z3) {
            l();
        } else {
            this.mLayoutState.b = false;
        }
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            this.mLayoutState.f1872a = hVar.f1867c - this.mOrientationHelper.getStartAfterPadding();
        } else {
            this.mLayoutState.f1872a = (this.mParent.getWidth() - hVar.f1867c) - this.mOrientationHelper.getStartAfterPadding();
        }
        j jVar = this.mLayoutState;
        jVar.d = hVar.f1866a;
        jVar.f1874h = -1;
        jVar.e = hVar.f1867c;
        jVar.f = Integer.MIN_VALUE;
        int i7 = hVar.b;
        jVar.f1873c = i7;
        if (z && i7 > 0 && this.mFlexLines.size() > (i2 = hVar.b)) {
            j jVar2 = this.mLayoutState;
            jVar2.f1873c--;
            jVar2.d -= this.mFlexLines.get(i2).f1855h;
        }
    }

    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!isMainAxisDirectionHorizontal() || this.mFlexWrap == 0) {
            int i7 = i(i2, recycler, state);
            this.mViewCache.clear();
            return i7;
        }
        int j2 = j(i2);
        this.mAnchorInfo.d += j2;
        this.mSubOrientationHelper.offsetChildren(-j2);
        return j2;
    }

    public void scrollToPosition(int i2) {
        this.mPendingScrollPosition = i2;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        k kVar = this.mPendingSavedState;
        if (kVar != null) {
            kVar.d = -1;
        }
        requestLayout();
    }

    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (isMainAxisDirectionHorizontal() || (this.mFlexWrap == 0 && !isMainAxisDirectionHorizontal())) {
            int i7 = i(i2, recycler, state);
            this.mViewCache.clear();
            return i7;
        }
        int j2 = j(i2);
        this.mAnchorInfo.d += j2;
        this.mSubOrientationHelper.offsetChildren(-j2);
        return j2;
    }

    public void setAlignItems(int i2) {
        int i7 = this.mAlignItems;
        if (i7 != i2) {
            if (i7 == 4 || i2 == 4) {
                removeAllViews();
                this.mFlexLines.clear();
                h.b(this.mAnchorInfo);
                this.mAnchorInfo.d = 0;
            }
            this.mAlignItems = i2;
            requestLayout();
        }
    }

    public void setFlexDirection(int i2) {
        if (this.mFlexDirection != i2) {
            removeAllViews();
            this.mFlexDirection = i2;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            this.mFlexLines.clear();
            h.b(this.mAnchorInfo);
            this.mAnchorInfo.d = 0;
            requestLayout();
        }
    }

    public void setFlexLines(List<c> list) {
        this.mFlexLines = list;
    }

    public void setFlexWrap(int i2) {
        if (i2 != 2) {
            int i7 = this.mFlexWrap;
            if (i7 != i2) {
                if (i7 == 0 || i2 == 0) {
                    removeAllViews();
                    this.mFlexLines.clear();
                    h.b(this.mAnchorInfo);
                    this.mAnchorInfo.d = 0;
                }
                this.mFlexWrap = i2;
                this.mOrientationHelper = null;
                this.mSubOrientationHelper = null;
                requestLayout();
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
    }

    public void setJustifyContent(int i2) {
        if (this.mJustifyContent != i2) {
            this.mJustifyContent = i2;
            requestLayout();
        }
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i2);
        startSmoothScroll(linearSmoothScroller);
    }

    public void updateViewCache(int i2, View view) {
        this.mViewCache.put(i2, view);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i2, int i7) {
        super.onItemsUpdated(recyclerView, i2, i7);
        n(i2);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [q1.d, java.lang.Object] */
    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i2, int i7) {
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i2, i7);
        int i8 = properties.orientation;
        if (i8 != 0) {
            if (i8 == 1) {
                if (properties.reverseLayout) {
                    setFlexDirection(3);
                } else {
                    setFlexDirection(2);
                }
            }
        } else if (properties.reverseLayout) {
            setFlexDirection(1);
        } else {
            setFlexDirection(0);
        }
        setFlexWrap(1);
        setAlignItems(4);
        this.mContext = context;
    }

    public void onNewFlexLineAdded(c cVar) {
    }
}
