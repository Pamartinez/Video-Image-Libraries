package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Flow extends VirtualLayout {
    private ConstraintWidget[] mAlignedBiggestElementsInCols = null;
    private ConstraintWidget[] mAlignedBiggestElementsInRows = null;
    private int[] mAlignedDimensions = null;
    private ArrayList<WidgetsList> mChainList = new ArrayList<>();
    /* access modifiers changed from: private */
    public ConstraintWidget[] mDisplayedWidgets;
    /* access modifiers changed from: private */
    public int mDisplayedWidgetsCount = 0;
    /* access modifiers changed from: private */
    public float mFirstHorizontalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mFirstHorizontalStyle = -1;
    /* access modifiers changed from: private */
    public float mFirstVerticalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mFirstVerticalStyle = -1;
    /* access modifiers changed from: private */
    public int mHorizontalAlign = 2;
    /* access modifiers changed from: private */
    public float mHorizontalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mHorizontalGap = 0;
    /* access modifiers changed from: private */
    public int mHorizontalStyle = -1;
    /* access modifiers changed from: private */
    public float mLastHorizontalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mLastHorizontalStyle = -1;
    /* access modifiers changed from: private */
    public float mLastVerticalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mLastVerticalStyle = -1;
    private int mMaxElementsWrap = -1;
    private int mOrientation = 0;
    /* access modifiers changed from: private */
    public int mVerticalAlign = 2;
    /* access modifiers changed from: private */
    public float mVerticalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mVerticalGap = 0;
    /* access modifiers changed from: private */
    public int mVerticalStyle = -1;
    private int mWrapMode = 0;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class WidgetsList {
        /* access modifiers changed from: private */
        public ConstraintWidget biggest = null;
        int biggestDimension = 0;
        private ConstraintAnchor mBottom;
        private int mCount = 0;
        private int mHeight = 0;
        private ConstraintAnchor mLeft;
        private int mMax = 0;
        private int mNbMatchConstraintsWidgets = 0;
        private int mOrientation;
        private int mPaddingBottom = 0;
        private int mPaddingLeft = 0;
        private int mPaddingRight = 0;
        private int mPaddingTop = 0;
        private ConstraintAnchor mRight;
        private int mStartIndex = 0;
        private ConstraintAnchor mTop;
        private int mWidth = 0;

        public WidgetsList(int i2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i7) {
            this.mOrientation = i2;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = Flow.this.getPaddingLeft();
            this.mPaddingTop = Flow.this.getPaddingTop();
            this.mPaddingRight = Flow.this.getPaddingRight();
            this.mPaddingBottom = Flow.this.getPaddingBottom();
            this.mMax = i7;
        }

        private void recomputeDimensions() {
            this.mWidth = 0;
            this.mHeight = 0;
            this.biggest = null;
            this.biggestDimension = 0;
            int i2 = this.mCount;
            int i7 = 0;
            while (i7 < i2 && this.mStartIndex + i7 < Flow.this.mDisplayedWidgetsCount) {
                ConstraintWidget constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i7];
                if (this.mOrientation == 0) {
                    int width = constraintWidget.getWidth();
                    int access$000 = Flow.this.mHorizontalGap;
                    if (constraintWidget.getVisibility() == 8) {
                        access$000 = 0;
                    }
                    this.mWidth = width + access$000 + this.mWidth;
                    int access$300 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                    if (this.biggest == null || this.biggestDimension < access$300) {
                        this.biggest = constraintWidget;
                        this.biggestDimension = access$300;
                        this.mHeight = access$300;
                    }
                } else {
                    int access$200 = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                    int access$3002 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                    int access$100 = Flow.this.mVerticalGap;
                    if (constraintWidget.getVisibility() == 8) {
                        access$100 = 0;
                    }
                    this.mHeight = access$3002 + access$100 + this.mHeight;
                    if (this.biggest == null || this.biggestDimension < access$200) {
                        this.biggest = constraintWidget;
                        this.biggestDimension = access$200;
                        this.mWidth = access$200;
                    }
                }
                i7++;
            }
        }

        public void add(ConstraintWidget constraintWidget) {
            int i2 = 0;
            if (this.mOrientation == 0) {
                int access$200 = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    access$200 = 0;
                }
                int access$000 = Flow.this.mHorizontalGap;
                if (constraintWidget.getVisibility() != 8) {
                    i2 = access$000;
                }
                this.mWidth = access$200 + i2 + this.mWidth;
                int access$300 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                if (this.biggest == null || this.biggestDimension < access$300) {
                    this.biggest = constraintWidget;
                    this.biggestDimension = access$300;
                    this.mHeight = access$300;
                }
            } else {
                int access$2002 = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                int access$3002 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    access$3002 = 0;
                }
                int access$100 = Flow.this.mVerticalGap;
                if (constraintWidget.getVisibility() != 8) {
                    i2 = access$100;
                }
                this.mHeight = access$3002 + i2 + this.mHeight;
                if (this.biggest == null || this.biggestDimension < access$2002) {
                    this.biggest = constraintWidget;
                    this.biggestDimension = access$2002;
                    this.mWidth = access$2002;
                }
            }
            this.mCount++;
        }

        public void clear() {
            this.biggestDimension = 0;
            this.biggest = null;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mStartIndex = 0;
            this.mCount = 0;
            this.mNbMatchConstraintsWidgets = 0;
        }

        /* JADX WARNING: Removed duplicated region for block: B:55:0x00df  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void createConstraints(boolean r18, int r19, boolean r20) {
            /*
                r17 = this;
                r0 = r17
                int r1 = r0.mCount
                r2 = 0
                r3 = r2
            L_0x0006:
                if (r3 >= r1) goto L_0x0027
                int r4 = r0.mStartIndex
                int r4 = r4 + r3
                androidx.constraintlayout.solver.widgets.Flow r5 = androidx.constraintlayout.solver.widgets.Flow.this
                int r5 = r5.mDisplayedWidgetsCount
                if (r4 < r5) goto L_0x0014
                goto L_0x0027
            L_0x0014:
                androidx.constraintlayout.solver.widgets.Flow r4 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r4 = r4.mDisplayedWidgets
                int r5 = r0.mStartIndex
                int r5 = r5 + r3
                r4 = r4[r5]
                if (r4 == 0) goto L_0x0024
                r4.resetAnchors()
            L_0x0024:
                int r3 = r3 + 1
                goto L_0x0006
            L_0x0027:
                if (r1 == 0) goto L_0x0361
                androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r0.biggest
                if (r3 != 0) goto L_0x002f
                goto L_0x0361
            L_0x002f:
                if (r20 == 0) goto L_0x0035
                if (r19 != 0) goto L_0x0035
                r4 = 1
                goto L_0x0036
            L_0x0035:
                r4 = r2
            L_0x0036:
                r5 = -1
                r6 = r2
                r7 = r5
                r8 = r7
            L_0x003a:
                if (r6 >= r1) goto L_0x0067
                if (r18 == 0) goto L_0x0042
                int r9 = r1 + -1
                int r9 = r9 - r6
                goto L_0x0043
            L_0x0042:
                r9 = r6
            L_0x0043:
                int r10 = r0.mStartIndex
                int r10 = r10 + r9
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mDisplayedWidgetsCount
                if (r10 < r11) goto L_0x004f
                goto L_0x0067
            L_0x004f:
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r10 = r10.mDisplayedWidgets
                int r11 = r0.mStartIndex
                int r11 = r11 + r9
                r9 = r10[r11]
                int r9 = r9.getVisibility()
                if (r9 != 0) goto L_0x0064
                if (r7 != r5) goto L_0x0063
                r7 = r6
            L_0x0063:
                r8 = r6
            L_0x0064:
                int r6 = r6 + 1
                goto L_0x003a
            L_0x0067:
                int r6 = r0.mOrientation
                r9 = 0
                if (r6 != 0) goto L_0x01f0
                androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r0.biggest
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                int r10 = r10.mVerticalStyle
                r6.setVerticalChainStyle(r10)
                int r10 = r0.mPaddingTop
                if (r19 <= 0) goto L_0x0082
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mVerticalGap
                int r10 = r10 + r11
            L_0x0082:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r6.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r0.mTop
                r11.connect(r12, r10)
                if (r20 == 0) goto L_0x0094
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r6.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mBottom
                int r12 = r0.mPaddingBottom
                r10.connect(r11, r12)
            L_0x0094:
                if (r19 <= 0) goto L_0x00a1
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r0.mTop
                androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r10.mOwner
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r10.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r6.mTop
                r10.connect(r11, r2)
            L_0x00a1:
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                int r10 = r10.mVerticalAlign
                r11 = 3
                if (r10 != r11) goto L_0x00db
                boolean r10 = r6.hasBaseline()
                if (r10 != 0) goto L_0x00db
                r10 = r2
            L_0x00b1:
                if (r10 >= r1) goto L_0x00db
                if (r18 == 0) goto L_0x00b9
                int r12 = r1 + -1
                int r12 = r12 - r10
                goto L_0x00ba
            L_0x00b9:
                r12 = r10
            L_0x00ba:
                int r13 = r0.mStartIndex
                int r13 = r13 + r12
                androidx.constraintlayout.solver.widgets.Flow r14 = androidx.constraintlayout.solver.widgets.Flow.this
                int r14 = r14.mDisplayedWidgetsCount
                if (r13 < r14) goto L_0x00c6
                goto L_0x00db
            L_0x00c6:
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r13 = r13.mDisplayedWidgets
                int r14 = r0.mStartIndex
                int r14 = r14 + r12
                r12 = r13[r14]
                boolean r13 = r12.hasBaseline()
                if (r13 == 0) goto L_0x00d8
                goto L_0x00dc
            L_0x00d8:
                int r10 = r10 + 1
                goto L_0x00b1
            L_0x00db:
                r12 = r6
            L_0x00dc:
                r10 = r2
            L_0x00dd:
                if (r10 >= r1) goto L_0x0361
                if (r18 == 0) goto L_0x00e5
                int r13 = r1 + -1
                int r13 = r13 - r10
                goto L_0x00e6
            L_0x00e5:
                r13 = r10
            L_0x00e6:
                int r14 = r0.mStartIndex
                int r14 = r14 + r13
                androidx.constraintlayout.solver.widgets.Flow r15 = androidx.constraintlayout.solver.widgets.Flow.this
                int r15 = r15.mDisplayedWidgetsCount
                if (r14 < r15) goto L_0x00f3
                goto L_0x0361
            L_0x00f3:
                androidx.constraintlayout.solver.widgets.Flow r14 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r14 = r14.mDisplayedWidgets
                int r15 = r0.mStartIndex
                int r15 = r15 + r13
                r14 = r14[r15]
                if (r10 != 0) goto L_0x010c
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r15 = r14.mLeft
                r16 = 1
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.mLeft
                int r11 = r0.mPaddingLeft
                r14.connect(r15, r3, r11)
                goto L_0x010e
            L_0x010c:
                r16 = 1
            L_0x010e:
                if (r13 != 0) goto L_0x0151
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mHorizontalStyle
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                float r11 = r11.mHorizontalBias
                int r13 = r0.mStartIndex
                if (r13 != 0) goto L_0x0135
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mFirstHorizontalStyle
                if (r13 == r5) goto L_0x0135
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mFirstHorizontalStyle
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                float r11 = r11.mFirstHorizontalBias
                goto L_0x014b
            L_0x0135:
                if (r20 == 0) goto L_0x014b
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mLastHorizontalStyle
                if (r13 == r5) goto L_0x014b
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mLastHorizontalStyle
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                float r11 = r11.mLastHorizontalBias
            L_0x014b:
                r14.setHorizontalChainStyle(r3)
                r14.setHorizontalBiasPercent(r11)
            L_0x0151:
                int r3 = r1 + -1
                if (r10 != r3) goto L_0x015e
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mRight
                int r13 = r0.mPaddingRight
                r14.connect(r3, r11, r13)
            L_0x015e:
                if (r9 == 0) goto L_0x0188
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mRight
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mHorizontalGap
                r3.connect(r11, r13)
                if (r10 != r7) goto L_0x0176
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mLeft
                int r11 = r0.mPaddingLeft
                r3.setGoneMargin(r11)
            L_0x0176:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r9.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r14.mLeft
                r3.connect(r11, r2)
                int r3 = r8 + 1
                if (r10 != r3) goto L_0x0188
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r9.mRight
                int r9 = r0.mPaddingRight
                r3.setGoneMargin(r9)
            L_0x0188:
                if (r14 == r6) goto L_0x01e9
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mVerticalAlign
                r9 = 3
                if (r3 != r9) goto L_0x01a9
                boolean r3 = r12.hasBaseline()
                if (r3 == 0) goto L_0x01a9
                if (r14 == r12) goto L_0x01a9
                boolean r3 = r14.hasBaseline()
                if (r3 == 0) goto L_0x01a9
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mBaseline
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r12.mBaseline
                r3.connect(r11, r2)
                goto L_0x01ea
            L_0x01a9:
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mVerticalAlign
                if (r3 == 0) goto L_0x01e1
                r11 = r16
                if (r3 == r11) goto L_0x01d9
                if (r4 == 0) goto L_0x01ca
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mTop
                int r13 = r0.mPaddingTop
                r3.connect(r11, r13)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mBottom
                int r13 = r0.mPaddingBottom
                r3.connect(r11, r13)
                goto L_0x01ea
            L_0x01ca:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r6.mTop
                r3.connect(r11, r2)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r6.mBottom
                r3.connect(r11, r2)
                goto L_0x01ea
            L_0x01d9:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r6.mBottom
                r3.connect(r11, r2)
                goto L_0x01ea
            L_0x01e1:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r6.mTop
                r3.connect(r11, r2)
                goto L_0x01ea
            L_0x01e9:
                r9 = 3
            L_0x01ea:
                int r10 = r10 + 1
                r11 = r9
                r9 = r14
                goto L_0x00dd
            L_0x01f0:
                androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r0.biggest
                androidx.constraintlayout.solver.widgets.Flow r6 = androidx.constraintlayout.solver.widgets.Flow.this
                int r6 = r6.mHorizontalStyle
                r3.setHorizontalChainStyle(r6)
                int r6 = r0.mPaddingLeft
                if (r19 <= 0) goto L_0x0206
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                int r10 = r10.mHorizontalGap
                int r6 = r6 + r10
            L_0x0206:
                if (r18 == 0) goto L_0x0228
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r3.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mRight
                r10.connect(r11, r6)
                if (r20 == 0) goto L_0x021a
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r3.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r0.mLeft
                int r11 = r0.mPaddingRight
                r6.connect(r10, r11)
            L_0x021a:
                if (r19 <= 0) goto L_0x0247
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r0.mRight
                androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r6.mOwner
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r6.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r3.mRight
                r6.connect(r10, r2)
                goto L_0x0247
            L_0x0228:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r3.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mLeft
                r10.connect(r11, r6)
                if (r20 == 0) goto L_0x023a
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r3.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r0.mRight
                int r11 = r0.mPaddingRight
                r6.connect(r10, r11)
            L_0x023a:
                if (r19 <= 0) goto L_0x0247
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r0.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r6.mOwner
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r6.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r3.mLeft
                r6.connect(r10, r2)
            L_0x0247:
                r6 = r2
            L_0x0248:
                if (r6 >= r1) goto L_0x0361
                int r10 = r0.mStartIndex
                int r10 = r10 + r6
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mDisplayedWidgetsCount
                if (r10 < r11) goto L_0x0257
                goto L_0x0361
            L_0x0257:
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r10 = r10.mDisplayedWidgets
                int r11 = r0.mStartIndex
                int r11 = r11 + r6
                r10 = r10[r11]
                if (r6 != 0) goto L_0x02ae
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r0.mTop
                int r13 = r0.mPaddingTop
                r10.connect(r11, r12, r13)
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mVerticalStyle
                androidx.constraintlayout.solver.widgets.Flow r12 = androidx.constraintlayout.solver.widgets.Flow.this
                float r12 = r12.mVerticalBias
                int r13 = r0.mStartIndex
                if (r13 != 0) goto L_0x0292
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mFirstVerticalStyle
                if (r13 == r5) goto L_0x0292
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mFirstVerticalStyle
                androidx.constraintlayout.solver.widgets.Flow r12 = androidx.constraintlayout.solver.widgets.Flow.this
                float r12 = r12.mFirstVerticalBias
                goto L_0x02a8
            L_0x0292:
                if (r20 == 0) goto L_0x02a8
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mLastVerticalStyle
                if (r13 == r5) goto L_0x02a8
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mLastVerticalStyle
                androidx.constraintlayout.solver.widgets.Flow r12 = androidx.constraintlayout.solver.widgets.Flow.this
                float r12 = r12.mLastVerticalBias
            L_0x02a8:
                r10.setVerticalChainStyle(r11)
                r10.setVerticalBiasPercent(r12)
            L_0x02ae:
                int r11 = r1 + -1
                if (r6 != r11) goto L_0x02bb
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r0.mBottom
                int r13 = r0.mPaddingBottom
                r10.connect(r11, r12, r13)
            L_0x02bb:
                if (r9 == 0) goto L_0x02e7
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r9.mBottom
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mVerticalGap
                r11.connect(r12, r13)
                if (r6 != r7) goto L_0x02d3
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mTop
                int r12 = r0.mPaddingTop
                r11.setGoneMargin(r12)
            L_0x02d3:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r10.mTop
                r11.connect(r12, r2)
                r16 = 1
                int r11 = r8 + 1
                if (r6 != r11) goto L_0x02e7
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.mBottom
                int r11 = r0.mPaddingBottom
                r9.setGoneMargin(r11)
            L_0x02e7:
                if (r10 == r3) goto L_0x0318
                r9 = 2
                if (r18 == 0) goto L_0x031a
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mHorizontalAlign
                if (r11 == 0) goto L_0x0311
                r12 = 1
                if (r11 == r12) goto L_0x0309
                if (r11 == r9) goto L_0x02fa
                goto L_0x0318
            L_0x02fa:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mLeft
                r9.connect(r11, r2)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mRight
                r9.connect(r11, r2)
                goto L_0x0318
            L_0x0309:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mLeft
                r9.connect(r11, r2)
                goto L_0x0318
            L_0x0311:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mRight
                r9.connect(r11, r2)
            L_0x0318:
                r12 = 1
                goto L_0x035c
            L_0x031a:
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mHorizontalAlign
                if (r11 == 0) goto L_0x0354
                r12 = 1
                if (r11 == r12) goto L_0x034c
                if (r11 == r9) goto L_0x0328
                goto L_0x035c
            L_0x0328:
                if (r4 == 0) goto L_0x033d
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mLeft
                int r13 = r0.mPaddingLeft
                r9.connect(r11, r13)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mRight
                int r13 = r0.mPaddingRight
                r9.connect(r11, r13)
                goto L_0x035c
            L_0x033d:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mLeft
                r9.connect(r11, r2)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mRight
                r9.connect(r11, r2)
                goto L_0x035c
            L_0x034c:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mRight
                r9.connect(r11, r2)
                goto L_0x035c
            L_0x0354:
                r12 = 1
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mLeft
                r9.connect(r11, r2)
            L_0x035c:
                int r6 = r6 + 1
                r9 = r10
                goto L_0x0248
            L_0x0361:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Flow.WidgetsList.createConstraints(boolean, int, boolean):void");
        }

        public int getHeight() {
            if (this.mOrientation == 1) {
                return this.mHeight - Flow.this.mVerticalGap;
            }
            return this.mHeight;
        }

        public int getWidth() {
            if (this.mOrientation == 0) {
                return this.mWidth - Flow.this.mHorizontalGap;
            }
            return this.mWidth;
        }

        public void measureMatchConstraints(int i2) {
            int i7 = this.mNbMatchConstraintsWidgets;
            if (i7 != 0) {
                int i8 = this.mCount;
                int i10 = i2 / i7;
                int i11 = 0;
                while (i11 < i8 && this.mStartIndex + i11 < Flow.this.mDisplayedWidgetsCount) {
                    ConstraintWidget constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i11];
                    if (this.mOrientation == 0) {
                        if (constraintWidget != null && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                            Flow.this.measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i10, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                        }
                    } else if (constraintWidget != null && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                        int i12 = i10;
                        Flow.this.measure(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i12);
                        i10 = i12;
                    }
                    i11++;
                }
                recomputeDimensions();
            }
        }

        public void setStartIndex(int i2) {
            this.mStartIndex = i2;
        }

        public void setup(int i2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i7, int i8, int i10, int i11, int i12) {
            this.mOrientation = i2;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = i7;
            this.mPaddingTop = i8;
            this.mPaddingRight = i10;
            this.mPaddingBottom = i11;
            this.mMax = i12;
        }
    }

    private void createAlignedConstraints(boolean z) {
        ConstraintWidget constraintWidget;
        int i2;
        if (this.mAlignedDimensions != null && this.mAlignedBiggestElementsInCols != null && this.mAlignedBiggestElementsInRows != null) {
            for (int i7 = 0; i7 < this.mDisplayedWidgetsCount; i7++) {
                this.mDisplayedWidgets[i7].resetAnchors();
            }
            int[] iArr = this.mAlignedDimensions;
            int i8 = iArr[0];
            int i10 = iArr[1];
            ConstraintWidget constraintWidget2 = null;
            for (int i11 = 0; i11 < i8; i11++) {
                if (z) {
                    i2 = (i8 - i11) - 1;
                } else {
                    i2 = i11;
                }
                ConstraintWidget constraintWidget3 = this.mAlignedBiggestElementsInCols[i2];
                if (!(constraintWidget3 == null || constraintWidget3.getVisibility() == 8)) {
                    if (i11 == 0) {
                        constraintWidget3.connect(constraintWidget3.mLeft, this.mLeft, getPaddingLeft());
                        constraintWidget3.setHorizontalChainStyle(this.mHorizontalStyle);
                        constraintWidget3.setHorizontalBiasPercent(this.mHorizontalBias);
                    }
                    if (i11 == i8 - 1) {
                        constraintWidget3.connect(constraintWidget3.mRight, this.mRight, getPaddingRight());
                    }
                    if (i11 > 0) {
                        constraintWidget3.connect(constraintWidget3.mLeft, constraintWidget2.mRight, this.mHorizontalGap);
                        constraintWidget2.connect(constraintWidget2.mRight, constraintWidget3.mLeft, 0);
                    }
                    constraintWidget2 = constraintWidget3;
                }
            }
            for (int i12 = 0; i12 < i10; i12++) {
                ConstraintWidget constraintWidget4 = this.mAlignedBiggestElementsInRows[i12];
                if (!(constraintWidget4 == null || constraintWidget4.getVisibility() == 8)) {
                    if (i12 == 0) {
                        constraintWidget4.connect(constraintWidget4.mTop, this.mTop, getPaddingTop());
                        constraintWidget4.setVerticalChainStyle(this.mVerticalStyle);
                        constraintWidget4.setVerticalBiasPercent(this.mVerticalBias);
                    }
                    if (i12 == i10 - 1) {
                        constraintWidget4.connect(constraintWidget4.mBottom, this.mBottom, getPaddingBottom());
                    }
                    if (i12 > 0) {
                        constraintWidget4.connect(constraintWidget4.mTop, constraintWidget2.mBottom, this.mVerticalGap);
                        constraintWidget2.connect(constraintWidget2.mBottom, constraintWidget4.mTop, 0);
                    }
                    constraintWidget2 = constraintWidget4;
                }
            }
            for (int i13 = 0; i13 < i8; i13++) {
                for (int i14 = 0; i14 < i10; i14++) {
                    int i15 = (i14 * i8) + i13;
                    if (this.mOrientation == 1) {
                        i15 = (i13 * i10) + i14;
                    }
                    ConstraintWidget[] constraintWidgetArr = this.mDisplayedWidgets;
                    if (!(i15 >= constraintWidgetArr.length || (constraintWidget = constraintWidgetArr[i15]) == null || constraintWidget.getVisibility() == 8)) {
                        ConstraintWidget constraintWidget5 = this.mAlignedBiggestElementsInCols[i13];
                        ConstraintWidget constraintWidget6 = this.mAlignedBiggestElementsInRows[i14];
                        if (constraintWidget != constraintWidget5) {
                            constraintWidget.connect(constraintWidget.mLeft, constraintWidget5.mLeft, 0);
                            constraintWidget.connect(constraintWidget.mRight, constraintWidget5.mRight, 0);
                        }
                        if (constraintWidget != constraintWidget6) {
                            constraintWidget.connect(constraintWidget.mTop, constraintWidget6.mTop, 0);
                            constraintWidget.connect(constraintWidget.mBottom, constraintWidget6.mBottom, 0);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final int getWidgetHeight(ConstraintWidget constraintWidget, int i2) {
        ConstraintWidget constraintWidget2;
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i7 = constraintWidget.mMatchConstraintDefaultHeight;
            if (i7 == 0) {
                return 0;
            }
            if (i7 == 2) {
                int i8 = (int) (constraintWidget.mMatchConstraintPercentHeight * ((float) i2));
                if (i8 != constraintWidget.getHeight()) {
                    constraintWidget.setMeasureRequested(true);
                    measure(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i8);
                }
                return i8;
            }
            constraintWidget2 = constraintWidget;
            if (i7 == 1) {
                return constraintWidget2.getHeight();
            }
            if (i7 == 3) {
                return (int) ((((float) constraintWidget2.getWidth()) * constraintWidget2.mDimensionRatio) + 0.5f);
            }
        } else {
            constraintWidget2 = constraintWidget;
        }
        return constraintWidget2.getHeight();
    }

    /* access modifiers changed from: private */
    public final int getWidgetWidth(ConstraintWidget constraintWidget, int i2) {
        ConstraintWidget constraintWidget2;
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i7 = constraintWidget.mMatchConstraintDefaultWidth;
            if (i7 == 0) {
                return 0;
            }
            if (i7 == 2) {
                int i8 = (int) (constraintWidget.mMatchConstraintPercentWidth * ((float) i2));
                if (i8 != constraintWidget.getWidth()) {
                    constraintWidget.setMeasureRequested(true);
                    measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i8, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                }
                return i8;
            }
            constraintWidget2 = constraintWidget;
            if (i7 == 1) {
                return constraintWidget2.getWidth();
            }
            if (i7 == 3) {
                return (int) ((((float) constraintWidget2.getHeight()) * constraintWidget2.mDimensionRatio) + 0.5f);
            }
        } else {
            constraintWidget2 = constraintWidget;
        }
        return constraintWidget2.getWidth();
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x011d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x005e  */
    private void measureAligned(androidx.constraintlayout.solver.widgets.ConstraintWidget[] r11, int r12, int r13, int r14, int[] r15) {
        /*
            r10 = this;
            r0 = 0
            if (r13 != 0) goto L_0x0026
            int r1 = r10.mMaxElementsWrap
            if (r1 > 0) goto L_0x0023
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x000a:
            if (r2 >= r12) goto L_0x0023
            if (r2 <= 0) goto L_0x0011
            int r4 = r10.mHorizontalGap
            int r3 = r3 + r4
        L_0x0011:
            r4 = r11[r2]
            if (r4 != 0) goto L_0x0016
            goto L_0x0020
        L_0x0016:
            int r4 = r10.getWidgetWidth(r4, r14)
            int r3 = r3 + r4
            if (r3 <= r14) goto L_0x001e
            goto L_0x0023
        L_0x001e:
            int r1 = r1 + 1
        L_0x0020:
            int r2 = r2 + 1
            goto L_0x000a
        L_0x0023:
            r2 = r1
            r1 = r0
            goto L_0x0047
        L_0x0026:
            int r1 = r10.mMaxElementsWrap
            if (r1 > 0) goto L_0x0046
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x002d:
            if (r2 >= r12) goto L_0x0046
            if (r2 <= 0) goto L_0x0034
            int r4 = r10.mVerticalGap
            int r3 = r3 + r4
        L_0x0034:
            r4 = r11[r2]
            if (r4 != 0) goto L_0x0039
            goto L_0x0043
        L_0x0039:
            int r4 = r10.getWidgetHeight(r4, r14)
            int r3 = r3 + r4
            if (r3 <= r14) goto L_0x0041
            goto L_0x0046
        L_0x0041:
            int r1 = r1 + 1
        L_0x0043:
            int r2 = r2 + 1
            goto L_0x002d
        L_0x0046:
            r2 = r0
        L_0x0047:
            int[] r3 = r10.mAlignedDimensions
            if (r3 != 0) goto L_0x0050
            r3 = 2
            int[] r3 = new int[r3]
            r10.mAlignedDimensions = r3
        L_0x0050:
            r3 = 1
            if (r1 != 0) goto L_0x0055
            if (r13 == r3) goto L_0x0059
        L_0x0055:
            if (r2 != 0) goto L_0x005b
            if (r13 != 0) goto L_0x005b
        L_0x0059:
            r4 = r3
            goto L_0x005c
        L_0x005b:
            r4 = r0
        L_0x005c:
            if (r4 != 0) goto L_0x011d
            if (r13 != 0) goto L_0x006a
            float r1 = (float) r12
            float r5 = (float) r2
            float r1 = r1 / r5
            double r5 = (double) r1
            double r5 = java.lang.Math.ceil(r5)
            int r1 = (int) r5
            goto L_0x0073
        L_0x006a:
            float r2 = (float) r12
            float r5 = (float) r1
            float r2 = r2 / r5
            double r5 = (double) r2
            double r5 = java.lang.Math.ceil(r5)
            int r2 = (int) r5
        L_0x0073:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r5 = r10.mAlignedBiggestElementsInCols
            r6 = 0
            if (r5 == 0) goto L_0x0080
            int r7 = r5.length
            if (r7 >= r2) goto L_0x007c
            goto L_0x0080
        L_0x007c:
            java.util.Arrays.fill(r5, r6)
            goto L_0x0084
        L_0x0080:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r5 = new androidx.constraintlayout.solver.widgets.ConstraintWidget[r2]
            r10.mAlignedBiggestElementsInCols = r5
        L_0x0084:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r5 = r10.mAlignedBiggestElementsInRows
            if (r5 == 0) goto L_0x0090
            int r7 = r5.length
            if (r7 >= r1) goto L_0x008c
            goto L_0x0090
        L_0x008c:
            java.util.Arrays.fill(r5, r6)
            goto L_0x0094
        L_0x0090:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r5 = new androidx.constraintlayout.solver.widgets.ConstraintWidget[r1]
            r10.mAlignedBiggestElementsInRows = r5
        L_0x0094:
            r5 = r0
        L_0x0095:
            if (r5 >= r2) goto L_0x00d9
            r6 = r0
        L_0x0098:
            if (r6 >= r1) goto L_0x00d6
            int r7 = r6 * r2
            int r7 = r7 + r5
            if (r13 != r3) goto L_0x00a2
            int r7 = r5 * r1
            int r7 = r7 + r6
        L_0x00a2:
            int r8 = r11.length
            if (r7 < r8) goto L_0x00a6
            goto L_0x00d3
        L_0x00a6:
            r7 = r11[r7]
            if (r7 != 0) goto L_0x00ab
            goto L_0x00d3
        L_0x00ab:
            int r8 = r10.getWidgetWidth(r7, r14)
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r9 = r10.mAlignedBiggestElementsInCols
            r9 = r9[r5]
            if (r9 == 0) goto L_0x00bb
            int r9 = r9.getWidth()
            if (r9 >= r8) goto L_0x00bf
        L_0x00bb:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r8 = r10.mAlignedBiggestElementsInCols
            r8[r5] = r7
        L_0x00bf:
            int r8 = r10.getWidgetHeight(r7, r14)
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r9 = r10.mAlignedBiggestElementsInRows
            r9 = r9[r6]
            if (r9 == 0) goto L_0x00cf
            int r9 = r9.getHeight()
            if (r9 >= r8) goto L_0x00d3
        L_0x00cf:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r8 = r10.mAlignedBiggestElementsInRows
            r8[r6] = r7
        L_0x00d3:
            int r6 = r6 + 1
            goto L_0x0098
        L_0x00d6:
            int r5 = r5 + 1
            goto L_0x0095
        L_0x00d9:
            r5 = r0
            r6 = r5
        L_0x00db:
            if (r5 >= r2) goto L_0x00f0
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r7 = r10.mAlignedBiggestElementsInCols
            r7 = r7[r5]
            if (r7 == 0) goto L_0x00ed
            if (r5 <= 0) goto L_0x00e8
            int r8 = r10.mHorizontalGap
            int r6 = r6 + r8
        L_0x00e8:
            int r7 = r10.getWidgetWidth(r7, r14)
            int r6 = r6 + r7
        L_0x00ed:
            int r5 = r5 + 1
            goto L_0x00db
        L_0x00f0:
            r5 = r0
            r7 = r5
        L_0x00f2:
            if (r5 >= r1) goto L_0x0107
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r8 = r10.mAlignedBiggestElementsInRows
            r8 = r8[r5]
            if (r8 == 0) goto L_0x0104
            if (r5 <= 0) goto L_0x00ff
            int r9 = r10.mVerticalGap
            int r7 = r7 + r9
        L_0x00ff:
            int r8 = r10.getWidgetHeight(r8, r14)
            int r7 = r7 + r8
        L_0x0104:
            int r5 = r5 + 1
            goto L_0x00f2
        L_0x0107:
            r15[r0] = r6
            r15[r3] = r7
            if (r13 != 0) goto L_0x0115
            if (r6 <= r14) goto L_0x0059
            if (r2 <= r3) goto L_0x0059
            int r2 = r2 + -1
            goto L_0x005c
        L_0x0115:
            if (r7 <= r14) goto L_0x0059
            if (r1 <= r3) goto L_0x0059
            int r1 = r1 + -1
            goto L_0x005c
        L_0x011d:
            int[] r10 = r10.mAlignedDimensions
            r10[r0] = r2
            r10[r3] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Flow.measureAligned(androidx.constraintlayout.solver.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    private void measureChainWrap(ConstraintWidget[] constraintWidgetArr, int i2, int i7, int i8, int[] iArr) {
        int i10;
        boolean z;
        Flow flow;
        int i11;
        boolean z3;
        ConstraintAnchor constraintAnchor;
        boolean z7;
        int i12;
        Flow flow2 = this;
        int i13 = i2;
        if (i13 != 0) {
            flow2.mChainList.clear();
            int i14 = i8;
            WidgetsList widgetsList = new WidgetsList(i7, flow2.mLeft, flow2.mTop, flow2.mRight, flow2.mBottom, i14);
            flow2.mChainList.add(widgetsList);
            if (i7 == 0) {
                i10 = 0;
                int i15 = 0;
                int i16 = 0;
                while (i16 < i13) {
                    ConstraintWidget constraintWidget = constraintWidgetArr[i16];
                    int widgetWidth = flow2.getWidgetWidth(constraintWidget, i14);
                    if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        i10++;
                    }
                    int i17 = i10;
                    if ((i15 == i14 || flow2.mHorizontalGap + i15 + widgetWidth > i14) && widgetsList.biggest != null) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    if (!z7 && i16 > 0 && (i12 = flow2.mMaxElementsWrap) > 0 && i16 % i12 == 0) {
                        z7 = true;
                    }
                    if (z7) {
                        widgetsList = new WidgetsList(i7, flow2.mLeft, flow2.mTop, flow2.mRight, flow2.mBottom, i14);
                        widgetsList.setStartIndex(i16);
                        flow2.mChainList.add(widgetsList);
                    } else if (i16 > 0) {
                        i15 = flow2.mHorizontalGap + widgetWidth + i15;
                        widgetsList.add(constraintWidget);
                        i16++;
                        i10 = i17;
                    }
                    i15 = widgetWidth;
                    widgetsList.add(constraintWidget);
                    i16++;
                    i10 = i17;
                }
            } else {
                int i18 = 0;
                int i19 = 0;
                int i20 = 0;
                while (i20 < i13) {
                    ConstraintWidget constraintWidget2 = constraintWidgetArr[i20];
                    int widgetHeight = flow2.getWidgetHeight(constraintWidget2, i14);
                    if (constraintWidget2.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        i10++;
                    }
                    int i21 = i10;
                    if ((i19 == i14 || flow2.mVerticalGap + i19 + widgetHeight > i14) && widgetsList.biggest != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (!z && i20 > 0 && (i11 = flow2.mMaxElementsWrap) > 0 && i20 % i11 == 0) {
                        z = true;
                    }
                    if (z) {
                        widgetsList = new WidgetsList(i7, flow2.mLeft, flow2.mTop, flow2.mRight, flow2.mBottom, i14);
                        flow = flow2;
                        widgetsList.setStartIndex(i20);
                        flow.mChainList.add(widgetsList);
                    } else {
                        flow = flow2;
                        if (i20 > 0) {
                            i19 = flow.mVerticalGap + widgetHeight + i19;
                            widgetsList.add(constraintWidget2);
                            i20++;
                            i14 = i8;
                            i18 = i21;
                            flow2 = flow;
                        }
                    }
                    i19 = widgetHeight;
                    widgetsList.add(constraintWidget2);
                    i20++;
                    i14 = i8;
                    i18 = i21;
                    flow2 = flow;
                }
            }
            Flow flow3 = flow2;
            int size = flow3.mChainList.size();
            ConstraintAnchor constraintAnchor2 = flow3.mLeft;
            ConstraintAnchor constraintAnchor3 = flow3.mTop;
            ConstraintAnchor constraintAnchor4 = flow3.mRight;
            ConstraintAnchor constraintAnchor5 = flow3.mBottom;
            int paddingLeft = flow3.getPaddingLeft();
            int paddingTop = flow3.getPaddingTop();
            int paddingRight = flow3.getPaddingRight();
            int paddingBottom = flow3.getPaddingBottom();
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = flow3.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (horizontalDimensionBehaviour == dimensionBehaviour || flow3.getVerticalDimensionBehaviour() == dimensionBehaviour) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (i10 > 0 && z3) {
                for (int i22 = 0; i22 < size; i22++) {
                    WidgetsList widgetsList2 = flow3.mChainList.get(i22);
                    if (i7 == 0) {
                        widgetsList2.measureMatchConstraints(i8 - widgetsList2.getWidth());
                    } else {
                        widgetsList2.measureMatchConstraints(i8 - widgetsList2.getHeight());
                    }
                }
            }
            ConstraintAnchor constraintAnchor6 = constraintAnchor2;
            int i23 = paddingBottom;
            int i24 = 0;
            int i25 = paddingRight;
            int i26 = paddingTop;
            int i27 = paddingLeft;
            ConstraintAnchor constraintAnchor7 = constraintAnchor5;
            ConstraintAnchor constraintAnchor8 = constraintAnchor4;
            ConstraintAnchor constraintAnchor9 = constraintAnchor3;
            int i28 = 0;
            for (int i29 = 0; i29 < size; i29++) {
                WidgetsList widgetsList3 = flow3.mChainList.get(i29);
                if (i7 == 0) {
                    if (i29 < size - 1) {
                        constraintAnchor7 = flow3.mChainList.get(i29 + 1).biggest.mTop;
                        i23 = 0;
                    } else {
                        constraintAnchor7 = flow3.mBottom;
                        i23 = flow3.getPaddingBottom();
                    }
                    int i30 = i24;
                    WidgetsList widgetsList4 = widgetsList3;
                    ConstraintAnchor constraintAnchor10 = widgetsList3.biggest.mBottom;
                    int i31 = i28;
                    widgetsList4.setup(i7, constraintAnchor6, constraintAnchor9, constraintAnchor8, constraintAnchor7, i27, i26, i25, i23, i8);
                    int max = Math.max(i30, widgetsList4.getWidth());
                    int height = widgetsList4.getHeight() + i31;
                    if (i29 > 0) {
                        height += flow3.mVerticalGap;
                    }
                    int i32 = max;
                    i28 = height;
                    i24 = i32;
                    constraintAnchor9 = constraintAnchor10;
                    i26 = 0;
                } else {
                    int i33 = i24;
                    int i34 = i28;
                    WidgetsList widgetsList5 = widgetsList3;
                    if (i29 < size - 1) {
                        constraintAnchor = flow3.mChainList.get(i29 + 1).biggest.mLeft;
                        i25 = 0;
                    } else {
                        constraintAnchor = flow3.mRight;
                        i25 = flow3.getPaddingRight();
                    }
                    constraintAnchor8 = constraintAnchor;
                    ConstraintAnchor constraintAnchor11 = widgetsList5.biggest.mRight;
                    widgetsList5.setup(i7, constraintAnchor6, constraintAnchor9, constraintAnchor8, constraintAnchor7, i27, i26, i25, i23, i8);
                    int width = widgetsList5.getWidth() + i33;
                    int max2 = Math.max(i34, widgetsList5.getHeight());
                    if (i29 > 0) {
                        width += flow3.mHorizontalGap;
                    }
                    int i35 = width;
                    i28 = max2;
                    i24 = i35;
                    i27 = 0;
                    constraintAnchor6 = constraintAnchor11;
                }
            }
            iArr[0] = i24;
            iArr[1] = i28;
        }
    }

    private void measureNoWrap(ConstraintWidget[] constraintWidgetArr, int i2, int i7, int i8, int[] iArr) {
        WidgetsList widgetsList;
        int i10 = i2;
        if (i10 != 0) {
            if (this.mChainList.size() == 0) {
                widgetsList = new WidgetsList(i7, this.mLeft, this.mTop, this.mRight, this.mBottom, i8);
                this.mChainList.add(widgetsList);
            } else {
                WidgetsList widgetsList2 = this.mChainList.get(0);
                widgetsList2.clear();
                widgetsList2.setup(i7, this.mLeft, this.mTop, this.mRight, this.mBottom, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom(), i8);
                widgetsList = widgetsList2;
            }
            for (int i11 = 0; i11 < i10; i11++) {
                widgetsList.add(constraintWidgetArr[i11]);
            }
            iArr[0] = widgetsList.getWidth();
            iArr[1] = widgetsList.getHeight();
        }
    }

    public void addToSolver(LinearSystem linearSystem, boolean z) {
        boolean z3;
        boolean z7;
        super.addToSolver(linearSystem, z);
        if (getParent() != null) {
            z3 = ((ConstraintWidgetContainer) getParent()).isRtl();
        } else {
            z3 = false;
        }
        int i2 = this.mWrapMode;
        if (i2 != 0) {
            if (i2 == 1) {
                int size = this.mChainList.size();
                for (int i7 = 0; i7 < size; i7++) {
                    WidgetsList widgetsList = this.mChainList.get(i7);
                    if (i7 == size - 1) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    widgetsList.createConstraints(z3, i7, z7);
                }
            } else if (i2 == 2) {
                createAlignedConstraints(z3);
            }
        } else if (this.mChainList.size() > 0) {
            this.mChainList.get(0).createConstraints(z3, 0, true);
        }
        needsCallbackFromSolver(false);
    }

    public void measure(int i2, int i7, int i8, int i10) {
        int i11;
        ConstraintWidget[] constraintWidgetArr;
        boolean z;
        int i12 = i2;
        int i13 = i7;
        int i14 = i8;
        int i15 = i10;
        if (this.mWidgetsCount <= 0 || measureChildren()) {
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int[] iArr = new int[2];
            int i16 = (i13 - paddingLeft) - paddingRight;
            int i17 = this.mOrientation;
            if (i17 == 1) {
                i16 = (i15 - paddingTop) - paddingBottom;
            }
            int i18 = i16;
            if (i17 == 0) {
                if (this.mHorizontalStyle == -1) {
                    this.mHorizontalStyle = 0;
                }
                if (this.mVerticalStyle == -1) {
                    this.mVerticalStyle = 0;
                }
            } else {
                if (this.mHorizontalStyle == -1) {
                    this.mHorizontalStyle = 0;
                }
                if (this.mVerticalStyle == -1) {
                    this.mVerticalStyle = 0;
                }
            }
            ConstraintWidget[] constraintWidgetArr2 = this.mWidgets;
            int i19 = 0;
            int i20 = 0;
            while (true) {
                i11 = this.mWidgetsCount;
                if (i19 >= i11) {
                    break;
                }
                if (this.mWidgets[i19].getVisibility() == 8) {
                    i20++;
                }
                i19++;
            }
            if (i20 > 0) {
                ConstraintWidget[] constraintWidgetArr3 = new ConstraintWidget[(i11 - i20)];
                int i21 = 0;
                i11 = 0;
                while (i21 < this.mWidgetsCount) {
                    ConstraintWidget constraintWidget = this.mWidgets[i21];
                    ConstraintWidget[] constraintWidgetArr4 = constraintWidgetArr3;
                    if (constraintWidget.getVisibility() != 8) {
                        constraintWidgetArr4[i11] = constraintWidget;
                        i11++;
                    }
                    i21++;
                    constraintWidgetArr3 = constraintWidgetArr4;
                }
                constraintWidgetArr = constraintWidgetArr3;
            } else {
                constraintWidgetArr = constraintWidgetArr2;
            }
            int i22 = i11;
            this.mDisplayedWidgets = constraintWidgetArr;
            this.mDisplayedWidgetsCount = i22;
            int i23 = this.mWrapMode;
            if (i23 == 0) {
                measureNoWrap(constraintWidgetArr, i22, this.mOrientation, i18, iArr);
            } else if (i23 == 1) {
                measureChainWrap(constraintWidgetArr, i22, this.mOrientation, i18, iArr);
            } else if (i23 == 2) {
                measureAligned(constraintWidgetArr, i22, this.mOrientation, i18, iArr);
            }
            int i24 = iArr[0] + paddingLeft + paddingRight;
            int i25 = iArr[1] + paddingTop + paddingBottom;
            if (i12 == 1073741824) {
                i24 = i13;
            } else if (i12 == Integer.MIN_VALUE) {
                i24 = Math.min(i24, i13);
            } else if (i12 != 0) {
                i24 = 0;
            }
            if (i14 == 1073741824) {
                i25 = i15;
            } else if (i14 == Integer.MIN_VALUE) {
                i25 = Math.min(i25, i15);
            } else if (i14 != 0) {
                i25 = 0;
            }
            setMeasure(i24, i25);
            setWidth(i24);
            setHeight(i25);
            if (this.mWidgetsCount > 0) {
                z = true;
            } else {
                z = false;
            }
            needsCallbackFromSolver(z);
            return;
        }
        setMeasure(0, 0);
        needsCallbackFromSolver(false);
    }

    public void setFirstHorizontalBias(float f) {
        this.mFirstHorizontalBias = f;
    }

    public void setFirstHorizontalStyle(int i2) {
        this.mFirstHorizontalStyle = i2;
    }

    public void setFirstVerticalBias(float f) {
        this.mFirstVerticalBias = f;
    }

    public void setFirstVerticalStyle(int i2) {
        this.mFirstVerticalStyle = i2;
    }

    public void setHorizontalAlign(int i2) {
        this.mHorizontalAlign = i2;
    }

    public void setHorizontalBias(float f) {
        this.mHorizontalBias = f;
    }

    public void setHorizontalGap(int i2) {
        this.mHorizontalGap = i2;
    }

    public void setHorizontalStyle(int i2) {
        this.mHorizontalStyle = i2;
    }

    public void setLastHorizontalBias(float f) {
        this.mLastHorizontalBias = f;
    }

    public void setLastHorizontalStyle(int i2) {
        this.mLastHorizontalStyle = i2;
    }

    public void setLastVerticalBias(float f) {
        this.mLastVerticalBias = f;
    }

    public void setLastVerticalStyle(int i2) {
        this.mLastVerticalStyle = i2;
    }

    public void setMaxElementsWrap(int i2) {
        this.mMaxElementsWrap = i2;
    }

    public void setOrientation(int i2) {
        this.mOrientation = i2;
    }

    public void setVerticalAlign(int i2) {
        this.mVerticalAlign = i2;
    }

    public void setVerticalBias(float f) {
        this.mVerticalBias = f;
    }

    public void setVerticalGap(int i2) {
        this.mVerticalGap = i2;
    }

    public void setVerticalStyle(int i2) {
        this.mVerticalStyle = i2;
    }

    public void setWrapMode(int i2) {
        this.mWrapMode = i2;
    }
}
