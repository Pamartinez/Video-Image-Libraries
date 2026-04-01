package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VirtualLayout extends HelperWidget {
    protected BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    private int mMeasuredHeight = 0;
    private int mMeasuredWidth = 0;
    BasicMeasure.Measurer mMeasurer = null;
    private boolean mNeedsCallFromSolver = false;
    private int mPaddingBottom = 0;
    private int mPaddingEnd = 0;
    private int mPaddingLeft = 0;
    private int mPaddingRight = 0;
    private int mPaddingStart = 0;
    private int mPaddingTop = 0;
    private int mResolvedPaddingLeft = 0;
    private int mResolvedPaddingRight = 0;

    public void applyRtl(boolean z) {
        int i2 = this.mPaddingStart;
        if (i2 <= 0 && this.mPaddingEnd <= 0) {
            return;
        }
        if (z) {
            this.mResolvedPaddingLeft = this.mPaddingEnd;
            this.mResolvedPaddingRight = i2;
            return;
        }
        this.mResolvedPaddingLeft = i2;
        this.mResolvedPaddingRight = this.mPaddingEnd;
    }

    public void captureWidgets() {
        for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
            ConstraintWidget constraintWidget = this.mWidgets[i2];
            if (constraintWidget != null) {
                constraintWidget.setInVirtualLayout(true);
            }
        }
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getPaddingLeft() {
        return this.mResolvedPaddingLeft;
    }

    public int getPaddingRight() {
        return this.mResolvedPaddingRight;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public abstract void measure(int i2, int i7, int i8, int i10);

    public void measure(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i2, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i7) {
        while (this.mMeasurer == null && getParent() != null) {
            this.mMeasurer = ((ConstraintWidgetContainer) getParent()).getMeasurer();
        }
        BasicMeasure.Measure measure = this.mMeasure;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i2;
        measure.verticalDimension = i7;
        this.mMeasurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    public boolean measureChildren() {
        BasicMeasure.Measurer measurer;
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null) {
            measurer = ((ConstraintWidgetContainer) constraintWidget).getMeasurer();
        } else {
            measurer = null;
        }
        if (measurer == null) {
            return false;
        }
        for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i2];
            if (constraintWidget2 != null && !(constraintWidget2 instanceof Guideline)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget2.getDimensionBehaviour(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget2.getDimensionBehaviour(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour != dimensionBehaviour3 || constraintWidget2.mMatchConstraintDefaultWidth == 1 || dimensionBehaviour2 != dimensionBehaviour3 || constraintWidget2.mMatchConstraintDefaultHeight == 1) {
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    BasicMeasure.Measure measure = this.mMeasure;
                    measure.horizontalBehavior = dimensionBehaviour;
                    measure.verticalBehavior = dimensionBehaviour2;
                    measure.horizontalDimension = constraintWidget2.getWidth();
                    this.mMeasure.verticalDimension = constraintWidget2.getHeight();
                    measurer.measure(constraintWidget2, this.mMeasure);
                    constraintWidget2.setWidth(this.mMeasure.measuredWidth);
                    constraintWidget2.setHeight(this.mMeasure.measuredHeight);
                    constraintWidget2.setBaselineDistance(this.mMeasure.measuredBaseline);
                }
            }
        }
        return true;
    }

    public boolean needSolverPass() {
        return this.mNeedsCallFromSolver;
    }

    public void needsCallbackFromSolver(boolean z) {
        this.mNeedsCallFromSolver = z;
    }

    public void setMeasure(int i2, int i7) {
        this.mMeasuredWidth = i2;
        this.mMeasuredHeight = i7;
    }

    public void setPadding(int i2) {
        this.mPaddingLeft = i2;
        this.mPaddingTop = i2;
        this.mPaddingRight = i2;
        this.mPaddingBottom = i2;
        this.mPaddingStart = i2;
        this.mPaddingEnd = i2;
    }

    public void setPaddingBottom(int i2) {
        this.mPaddingBottom = i2;
    }

    public void setPaddingEnd(int i2) {
        this.mPaddingEnd = i2;
    }

    public void setPaddingLeft(int i2) {
        this.mPaddingLeft = i2;
        this.mResolvedPaddingLeft = i2;
    }

    public void setPaddingRight(int i2) {
        this.mPaddingRight = i2;
        this.mResolvedPaddingRight = i2;
    }

    public void setPaddingStart(int i2) {
        this.mPaddingStart = i2;
        this.mResolvedPaddingLeft = i2;
        this.mResolvedPaddingRight = i2;
    }

    public void setPaddingTop(int i2) {
        this.mPaddingTop = i2;
    }

    public void updateConstraints(ConstraintWidgetContainer constraintWidgetContainer) {
        captureWidgets();
    }
}
