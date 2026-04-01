package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Direct {
    private static BasicMeasure.Measure measure = new BasicMeasure.Measure();

    private static boolean canMeasure(ConstraintWidget constraintWidget) {
        ConstraintWidgetContainer constraintWidgetContainer;
        boolean z;
        boolean z3;
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidget.getVerticalDimensionBehaviour();
        if (constraintWidget.getParent() != null) {
            constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget.getParent();
        } else {
            constraintWidgetContainer = null;
        }
        if (constraintWidgetContainer != null) {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour2 = constraintWidgetContainer.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (constraintWidgetContainer != null) {
            ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour2 = constraintWidgetContainer.getVerticalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (horizontalDimensionBehaviour == dimensionBehaviour3 || horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || ((horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 0 && constraintWidget.mDimensionRatio == 0.0f && constraintWidget.hasDanglingDimension(0)) || constraintWidget.isResolvedHorizontally())) {
            z = true;
        } else {
            z = false;
        }
        if (verticalDimensionBehaviour == dimensionBehaviour3 || verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || ((verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 0 && constraintWidget.mDimensionRatio == 0.0f && constraintWidget.hasDanglingDimension(1)) || constraintWidget.isResolvedVertically())) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (constraintWidget.mDimensionRatio > 0.0f && (z || z3)) {
            return true;
        }
        if (!z || !z3) {
            return false;
        }
        return true;
    }

    private static void horizontalSolvingPass(ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, boolean z) {
        boolean z3;
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        ConstraintAnchor constraintAnchor5;
        if (!(constraintWidget instanceof ConstraintWidgetContainer) && constraintWidget.isMeasureRequested() && canMeasure(constraintWidget)) {
            ConstraintWidgetContainer.measure(constraintWidget, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
        }
        ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor anchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT);
        int finalValue = anchor.getFinalValue();
        int finalValue2 = anchor2.getFinalValue();
        if (anchor.getDependents() != null && anchor.hasFinalValue()) {
            Iterator<ConstraintAnchor> it = anchor.getDependents().iterator();
            while (it.hasNext()) {
                ConstraintAnchor next = it.next();
                ConstraintWidget constraintWidget2 = next.mOwner;
                boolean canMeasure = canMeasure(constraintWidget2);
                if (constraintWidget2.isMeasureRequested() && canMeasure) {
                    ConstraintWidgetContainer.measure(constraintWidget2, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
                ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget2.getHorizontalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (horizontalDimensionBehaviour != dimensionBehaviour || canMeasure) {
                    if (!constraintWidget2.isMeasureRequested()) {
                        ConstraintAnchor constraintAnchor6 = constraintWidget2.mLeft;
                        if (next == constraintAnchor6 && constraintWidget2.mRight.mTarget == null) {
                            int margin = constraintAnchor6.getMargin() + finalValue;
                            constraintWidget2.setFinalHorizontal(margin, constraintWidget2.getWidth() + margin);
                            horizontalSolvingPass(constraintWidget2, measurer, z);
                        } else {
                            ConstraintAnchor constraintAnchor7 = constraintWidget2.mRight;
                            if (next == constraintAnchor7 && constraintAnchor6.mTarget == null) {
                                int margin2 = finalValue - constraintAnchor7.getMargin();
                                constraintWidget2.setFinalHorizontal(margin2 - constraintWidget2.getWidth(), margin2);
                                horizontalSolvingPass(constraintWidget2, measurer, z);
                            } else if (next == constraintAnchor6 && (constraintAnchor3 = constraintAnchor7.mTarget) != null && constraintAnchor3.hasFinalValue() && !constraintWidget2.isInHorizontalChain()) {
                                solveHorizontalCenterConstraints(measurer, constraintWidget2, z);
                            }
                        }
                    }
                } else if (constraintWidget2.getHorizontalDimensionBehaviour() == dimensionBehaviour && constraintWidget2.mMatchConstraintMaxWidth >= 0 && constraintWidget2.mMatchConstraintMinWidth >= 0) {
                    if ((constraintWidget2.getVisibility() == 8 || (constraintWidget2.mMatchConstraintDefaultWidth == 0 && constraintWidget2.getDimensionRatio() == 0.0f)) && !constraintWidget2.isInHorizontalChain() && !constraintWidget2.isInVirtualLayout()) {
                        if (((next == constraintWidget2.mLeft && (constraintAnchor5 = constraintWidget2.mRight.mTarget) != null && constraintAnchor5.hasFinalValue()) || (next == constraintWidget2.mRight && (constraintAnchor4 = constraintWidget2.mLeft.mTarget) != null && constraintAnchor4.hasFinalValue())) && !constraintWidget2.isInHorizontalChain()) {
                            solveHorizontalMatchConstraint(constraintWidget, measurer, constraintWidget2, z);
                        }
                    }
                }
            }
        }
        if (!(constraintWidget instanceof Guideline) && anchor2.getDependents() != null && anchor2.hasFinalValue()) {
            Iterator<ConstraintAnchor> it2 = anchor2.getDependents().iterator();
            while (it2.hasNext()) {
                ConstraintAnchor next2 = it2.next();
                ConstraintWidget constraintWidget3 = next2.mOwner;
                boolean canMeasure2 = canMeasure(constraintWidget3);
                if (constraintWidget3.isMeasureRequested() && canMeasure2) {
                    ConstraintWidgetContainer.measure(constraintWidget3, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
                if ((next2 != constraintWidget3.mLeft || (constraintAnchor2 = constraintWidget3.mRight.mTarget) == null || !constraintAnchor2.hasFinalValue()) && (next2 != constraintWidget3.mRight || (constraintAnchor = constraintWidget3.mLeft.mTarget) == null || !constraintAnchor.hasFinalValue())) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour2 = constraintWidget3.getHorizontalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (horizontalDimensionBehaviour2 != dimensionBehaviour2 || canMeasure2) {
                    if (!constraintWidget3.isMeasureRequested()) {
                        ConstraintAnchor constraintAnchor8 = constraintWidget3.mLeft;
                        if (next2 == constraintAnchor8 && constraintWidget3.mRight.mTarget == null) {
                            int margin3 = constraintAnchor8.getMargin() + finalValue2;
                            constraintWidget3.setFinalHorizontal(margin3, constraintWidget3.getWidth() + margin3);
                            horizontalSolvingPass(constraintWidget3, measurer, z);
                        } else {
                            ConstraintAnchor constraintAnchor9 = constraintWidget3.mRight;
                            if (next2 == constraintAnchor9 && constraintAnchor8.mTarget == null) {
                                int margin4 = finalValue2 - constraintAnchor9.getMargin();
                                constraintWidget3.setFinalHorizontal(margin4 - constraintWidget3.getWidth(), margin4);
                                horizontalSolvingPass(constraintWidget3, measurer, z);
                            } else if (z3 && !constraintWidget3.isInHorizontalChain()) {
                                solveHorizontalCenterConstraints(measurer, constraintWidget3, z);
                            }
                        }
                    }
                } else if (constraintWidget3.getHorizontalDimensionBehaviour() == dimensionBehaviour2 && constraintWidget3.mMatchConstraintMaxWidth >= 0 && constraintWidget3.mMatchConstraintMinWidth >= 0) {
                    if ((constraintWidget3.getVisibility() == 8 || (constraintWidget3.mMatchConstraintDefaultWidth == 0 && constraintWidget3.getDimensionRatio() == 0.0f)) && !constraintWidget3.isInHorizontalChain() && !constraintWidget3.isInVirtualLayout() && z3 && !constraintWidget3.isInHorizontalChain()) {
                        solveHorizontalMatchConstraint(constraintWidget, measurer, constraintWidget3, z);
                    }
                }
            }
        }
    }

    private static void solveBarrier(Barrier barrier, BasicMeasure.Measurer measurer, int i2, boolean z) {
        if (!barrier.allSolved()) {
            return;
        }
        if (i2 == 0) {
            horizontalSolvingPass(barrier, measurer, z);
        } else {
            verticalSolvingPass(barrier, measurer);
        }
    }

    private static void solveHorizontalCenterConstraints(BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget, boolean z) {
        float horizontalBiasPercent = constraintWidget.getHorizontalBiasPercent();
        int finalValue = constraintWidget.mLeft.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mRight.mTarget.getFinalValue();
        int margin = constraintWidget.mLeft.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mRight.getMargin();
        if (finalValue == finalValue2) {
            horizontalBiasPercent = 0.5f;
        } else {
            finalValue = margin;
            finalValue2 = margin2;
        }
        int width = constraintWidget.getWidth();
        int i2 = (finalValue2 - finalValue) - width;
        if (finalValue > finalValue2) {
            i2 = (finalValue - finalValue2) - width;
        }
        int i7 = ((int) ((horizontalBiasPercent * ((float) i2)) + 0.5f)) + finalValue;
        int i8 = i7 + width;
        if (finalValue > finalValue2) {
            i8 = i7 - width;
        }
        constraintWidget.setFinalHorizontal(i7, i8);
        horizontalSolvingPass(constraintWidget, measurer, z);
    }

    private static void solveHorizontalMatchConstraint(ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2, boolean z) {
        int i2;
        float horizontalBiasPercent = constraintWidget2.getHorizontalBiasPercent();
        int margin = constraintWidget2.mLeft.getMargin() + constraintWidget2.mLeft.mTarget.getFinalValue();
        int finalValue = constraintWidget2.mRight.mTarget.getFinalValue() - constraintWidget2.mRight.getMargin();
        if (finalValue >= margin) {
            int width = constraintWidget2.getWidth();
            if (constraintWidget2.getVisibility() != 8) {
                int i7 = constraintWidget2.mMatchConstraintDefaultWidth;
                if (i7 == 2) {
                    if (constraintWidget instanceof ConstraintWidgetContainer) {
                        i2 = constraintWidget.getWidth();
                    } else {
                        i2 = constraintWidget.getParent().getWidth();
                    }
                    width = (int) (constraintWidget2.getHorizontalBiasPercent() * 0.5f * ((float) i2));
                } else if (i7 == 0) {
                    width = finalValue - margin;
                }
                width = Math.max(constraintWidget2.mMatchConstraintMinWidth, width);
                int i8 = constraintWidget2.mMatchConstraintMaxWidth;
                if (i8 > 0) {
                    width = Math.min(i8, width);
                }
            }
            int i10 = margin + ((int) ((horizontalBiasPercent * ((float) ((finalValue - margin) - width))) + 0.5f));
            constraintWidget2.setFinalHorizontal(i10, width + i10);
            horizontalSolvingPass(constraintWidget2, measurer, z);
        }
    }

    private static void solveVerticalCenterConstraints(BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget) {
        float verticalBiasPercent = constraintWidget.getVerticalBiasPercent();
        int finalValue = constraintWidget.mTop.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mBottom.mTarget.getFinalValue();
        int margin = constraintWidget.mTop.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mBottom.getMargin();
        if (finalValue == finalValue2) {
            verticalBiasPercent = 0.5f;
        } else {
            finalValue = margin;
            finalValue2 = margin2;
        }
        int height = constraintWidget.getHeight();
        int i2 = (finalValue2 - finalValue) - height;
        if (finalValue > finalValue2) {
            i2 = (finalValue - finalValue2) - height;
        }
        int i7 = (int) ((verticalBiasPercent * ((float) i2)) + 0.5f);
        int i8 = finalValue + i7;
        int i10 = i8 + height;
        if (finalValue > finalValue2) {
            i8 = finalValue - i7;
            i10 = i8 - height;
        }
        constraintWidget.setFinalVertical(i8, i10);
        verticalSolvingPass(constraintWidget, measurer);
    }

    private static void solveVerticalMatchConstraint(ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2) {
        int i2;
        float verticalBiasPercent = constraintWidget2.getVerticalBiasPercent();
        int margin = constraintWidget2.mTop.getMargin() + constraintWidget2.mTop.mTarget.getFinalValue();
        int finalValue = constraintWidget2.mBottom.mTarget.getFinalValue() - constraintWidget2.mBottom.getMargin();
        if (finalValue >= margin) {
            int height = constraintWidget2.getHeight();
            if (constraintWidget2.getVisibility() != 8) {
                int i7 = constraintWidget2.mMatchConstraintDefaultHeight;
                if (i7 == 2) {
                    if (constraintWidget instanceof ConstraintWidgetContainer) {
                        i2 = constraintWidget.getHeight();
                    } else {
                        i2 = constraintWidget.getParent().getHeight();
                    }
                    height = (int) (verticalBiasPercent * 0.5f * ((float) i2));
                } else if (i7 == 0) {
                    height = finalValue - margin;
                }
                height = Math.max(constraintWidget2.mMatchConstraintMinHeight, height);
                int i8 = constraintWidget2.mMatchConstraintMaxHeight;
                if (i8 > 0) {
                    height = Math.min(i8, height);
                }
            }
            int i10 = margin + ((int) ((verticalBiasPercent * ((float) ((finalValue - margin) - height))) + 0.5f));
            constraintWidget2.setFinalVertical(i10, height + i10);
            verticalSolvingPass(constraintWidget2, measurer);
        }
    }

    public static void solvingPass(ConstraintWidgetContainer constraintWidgetContainer, BasicMeasure.Measurer measurer) {
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidgetContainer.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidgetContainer.getVerticalDimensionBehaviour();
        constraintWidgetContainer.resetFinalResolution();
        ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
        int size = children.size();
        for (int i2 = 0; i2 < size; i2++) {
            children.get(i2).resetFinalResolution();
        }
        boolean isRtl = constraintWidgetContainer.isRtl();
        if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.setFinalHorizontal(0, constraintWidgetContainer.getWidth());
        } else {
            constraintWidgetContainer.setFinalLeft(0);
        }
        boolean z = false;
        boolean z3 = false;
        for (int i7 = 0; i7 < size; i7++) {
            ConstraintWidget constraintWidget = children.get(i7);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    if (guideline.getRelativeBegin() != -1) {
                        guideline.setFinalValue(guideline.getRelativeBegin());
                    } else if (guideline.getRelativeEnd() != -1 && constraintWidgetContainer.isResolvedHorizontally()) {
                        guideline.setFinalValue(constraintWidgetContainer.getWidth() - guideline.getRelativeEnd());
                    } else if (constraintWidgetContainer.isResolvedHorizontally()) {
                        guideline.setFinalValue((int) ((guideline.getRelativePercent() * ((float) constraintWidgetContainer.getWidth())) + 0.5f));
                    }
                    z = true;
                }
            } else if ((constraintWidget instanceof Barrier) && ((Barrier) constraintWidget).getOrientation() == 0) {
                z3 = true;
            }
        }
        if (z) {
            for (int i8 = 0; i8 < size; i8++) {
                ConstraintWidget constraintWidget2 = children.get(i8);
                if (constraintWidget2 instanceof Guideline) {
                    Guideline guideline2 = (Guideline) constraintWidget2;
                    if (guideline2.getOrientation() == 1) {
                        horizontalSolvingPass(guideline2, measurer, isRtl);
                    }
                }
            }
        }
        horizontalSolvingPass(constraintWidgetContainer, measurer, isRtl);
        if (z3) {
            for (int i10 = 0; i10 < size; i10++) {
                ConstraintWidget constraintWidget3 = children.get(i10);
                if (constraintWidget3 instanceof Barrier) {
                    Barrier barrier = (Barrier) constraintWidget3;
                    if (barrier.getOrientation() == 0) {
                        solveBarrier(barrier, measurer, 0, isRtl);
                    }
                }
            }
        }
        if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.setFinalVertical(0, constraintWidgetContainer.getHeight());
        } else {
            constraintWidgetContainer.setFinalTop(0);
        }
        boolean z7 = false;
        boolean z9 = false;
        for (int i11 = 0; i11 < size; i11++) {
            ConstraintWidget constraintWidget4 = children.get(i11);
            if (constraintWidget4 instanceof Guideline) {
                Guideline guideline3 = (Guideline) constraintWidget4;
                if (guideline3.getOrientation() == 0) {
                    if (guideline3.getRelativeBegin() != -1) {
                        guideline3.setFinalValue(guideline3.getRelativeBegin());
                    } else if (guideline3.getRelativeEnd() != -1 && constraintWidgetContainer.isResolvedVertically()) {
                        guideline3.setFinalValue(constraintWidgetContainer.getHeight() - guideline3.getRelativeEnd());
                    } else if (constraintWidgetContainer.isResolvedVertically()) {
                        guideline3.setFinalValue((int) ((guideline3.getRelativePercent() * ((float) constraintWidgetContainer.getHeight())) + 0.5f));
                    }
                    z7 = true;
                }
            } else if ((constraintWidget4 instanceof Barrier) && ((Barrier) constraintWidget4).getOrientation() == 1) {
                z9 = true;
            }
        }
        if (z7) {
            for (int i12 = 0; i12 < size; i12++) {
                ConstraintWidget constraintWidget5 = children.get(i12);
                if (constraintWidget5 instanceof Guideline) {
                    Guideline guideline4 = (Guideline) constraintWidget5;
                    if (guideline4.getOrientation() == 0) {
                        verticalSolvingPass(guideline4, measurer);
                    }
                }
            }
        }
        verticalSolvingPass(constraintWidgetContainer, measurer);
        if (z9) {
            for (int i13 = 0; i13 < size; i13++) {
                ConstraintWidget constraintWidget6 = children.get(i13);
                if (constraintWidget6 instanceof Barrier) {
                    Barrier barrier2 = (Barrier) constraintWidget6;
                    if (barrier2.getOrientation() == 1) {
                        solveBarrier(barrier2, measurer, 1, isRtl);
                    }
                }
            }
        }
        for (int i14 = 0; i14 < size; i14++) {
            ConstraintWidget constraintWidget7 = children.get(i14);
            if (constraintWidget7.isMeasureRequested() && canMeasure(constraintWidget7)) {
                ConstraintWidgetContainer.measure(constraintWidget7, measurer, measure, BasicMeasure.Measure.SELF_DIMENSIONS);
                horizontalSolvingPass(constraintWidget7, measurer, isRtl);
                verticalSolvingPass(constraintWidget7, measurer);
            }
        }
    }

    private static void verticalSolvingPass(ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer) {
        boolean z;
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        ConstraintAnchor constraintAnchor5;
        if (!(constraintWidget instanceof ConstraintWidgetContainer) && constraintWidget.isMeasureRequested() && canMeasure(constraintWidget)) {
            ConstraintWidgetContainer.measure(constraintWidget, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
        }
        ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor anchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM);
        int finalValue = anchor.getFinalValue();
        int finalValue2 = anchor2.getFinalValue();
        if (anchor.getDependents() != null && anchor.hasFinalValue()) {
            Iterator<ConstraintAnchor> it = anchor.getDependents().iterator();
            while (it.hasNext()) {
                ConstraintAnchor next = it.next();
                ConstraintWidget constraintWidget2 = next.mOwner;
                boolean canMeasure = canMeasure(constraintWidget2);
                if (constraintWidget2.isMeasureRequested() && canMeasure) {
                    ConstraintWidgetContainer.measure(constraintWidget2, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
                ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidget2.getVerticalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (verticalDimensionBehaviour != dimensionBehaviour || canMeasure) {
                    if (!constraintWidget2.isMeasureRequested()) {
                        ConstraintAnchor constraintAnchor6 = constraintWidget2.mTop;
                        if (next == constraintAnchor6 && constraintWidget2.mBottom.mTarget == null) {
                            int margin = constraintAnchor6.getMargin() + finalValue;
                            constraintWidget2.setFinalVertical(margin, constraintWidget2.getHeight() + margin);
                            verticalSolvingPass(constraintWidget2, measurer);
                        } else {
                            ConstraintAnchor constraintAnchor7 = constraintWidget2.mBottom;
                            if (next == constraintAnchor7 && constraintAnchor7.mTarget == null) {
                                int margin2 = finalValue - constraintAnchor7.getMargin();
                                constraintWidget2.setFinalVertical(margin2 - constraintWidget2.getHeight(), margin2);
                                verticalSolvingPass(constraintWidget2, measurer);
                            } else if (next == constraintAnchor6 && (constraintAnchor3 = constraintAnchor7.mTarget) != null && constraintAnchor3.hasFinalValue()) {
                                solveVerticalCenterConstraints(measurer, constraintWidget2);
                            }
                        }
                    }
                } else if (constraintWidget2.getVerticalDimensionBehaviour() == dimensionBehaviour && constraintWidget2.mMatchConstraintMaxHeight >= 0 && constraintWidget2.mMatchConstraintMinHeight >= 0) {
                    if ((constraintWidget2.getVisibility() == 8 || (constraintWidget2.mMatchConstraintDefaultHeight == 0 && constraintWidget2.getDimensionRatio() == 0.0f)) && !constraintWidget2.isInVerticalChain() && !constraintWidget2.isInVirtualLayout()) {
                        if (((next == constraintWidget2.mTop && (constraintAnchor5 = constraintWidget2.mBottom.mTarget) != null && constraintAnchor5.hasFinalValue()) || (next == constraintWidget2.mBottom && (constraintAnchor4 = constraintWidget2.mTop.mTarget) != null && constraintAnchor4.hasFinalValue())) && !constraintWidget2.isInVerticalChain()) {
                            solveVerticalMatchConstraint(constraintWidget, measurer, constraintWidget2);
                        }
                    }
                }
            }
        }
        if (!(constraintWidget instanceof Guideline)) {
            if (anchor2.getDependents() != null && anchor2.hasFinalValue()) {
                Iterator<ConstraintAnchor> it2 = anchor2.getDependents().iterator();
                while (it2.hasNext()) {
                    ConstraintAnchor next2 = it2.next();
                    ConstraintWidget constraintWidget3 = next2.mOwner;
                    boolean canMeasure2 = canMeasure(constraintWidget3);
                    if (constraintWidget3.isMeasureRequested() && canMeasure2) {
                        ConstraintWidgetContainer.measure(constraintWidget3, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                    }
                    if ((next2 != constraintWidget3.mTop || (constraintAnchor2 = constraintWidget3.mBottom.mTarget) == null || !constraintAnchor2.hasFinalValue()) && (next2 != constraintWidget3.mBottom || (constraintAnchor = constraintWidget3.mTop.mTarget) == null || !constraintAnchor.hasFinalValue())) {
                        z = false;
                    } else {
                        z = true;
                    }
                    ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour2 = constraintWidget3.getVerticalDimensionBehaviour();
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (verticalDimensionBehaviour2 != dimensionBehaviour2 || canMeasure2) {
                        if (!constraintWidget3.isMeasureRequested()) {
                            ConstraintAnchor constraintAnchor8 = constraintWidget3.mTop;
                            if (next2 == constraintAnchor8 && constraintWidget3.mBottom.mTarget == null) {
                                int margin3 = constraintAnchor8.getMargin() + finalValue2;
                                constraintWidget3.setFinalVertical(margin3, constraintWidget3.getHeight() + margin3);
                                verticalSolvingPass(constraintWidget3, measurer);
                            } else {
                                ConstraintAnchor constraintAnchor9 = constraintWidget3.mBottom;
                                if (next2 == constraintAnchor9 && constraintAnchor8.mTarget == null) {
                                    int margin4 = finalValue2 - constraintAnchor9.getMargin();
                                    constraintWidget3.setFinalVertical(margin4 - constraintWidget3.getHeight(), margin4);
                                    verticalSolvingPass(constraintWidget3, measurer);
                                } else if (z && !constraintWidget3.isInVerticalChain()) {
                                    solveVerticalCenterConstraints(measurer, constraintWidget3);
                                }
                            }
                        }
                    } else if (constraintWidget3.getVerticalDimensionBehaviour() == dimensionBehaviour2 && constraintWidget3.mMatchConstraintMaxHeight >= 0 && constraintWidget3.mMatchConstraintMinHeight >= 0) {
                        if ((constraintWidget3.getVisibility() == 8 || (constraintWidget3.mMatchConstraintDefaultHeight == 0 && constraintWidget3.getDimensionRatio() == 0.0f)) && !constraintWidget3.isInVerticalChain() && !constraintWidget3.isInVirtualLayout() && z && !constraintWidget3.isInVerticalChain()) {
                            solveVerticalMatchConstraint(constraintWidget, measurer, constraintWidget3);
                        }
                    }
                }
            }
            ConstraintAnchor anchor3 = constraintWidget.getAnchor(ConstraintAnchor.Type.BASELINE);
            if (anchor3.getDependents() != null && anchor3.hasFinalValue()) {
                int finalValue3 = anchor3.getFinalValue();
                Iterator<ConstraintAnchor> it3 = anchor3.getDependents().iterator();
                while (it3.hasNext()) {
                    ConstraintAnchor next3 = it3.next();
                    ConstraintWidget constraintWidget4 = next3.mOwner;
                    boolean canMeasure3 = canMeasure(constraintWidget4);
                    if (constraintWidget4.isMeasureRequested() && canMeasure3) {
                        ConstraintWidgetContainer.measure(constraintWidget4, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                    }
                    if ((constraintWidget4.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || canMeasure3) && !constraintWidget4.isMeasureRequested() && next3 == constraintWidget4.mBaseline) {
                        constraintWidget4.setFinalBaseline(finalValue3);
                        verticalSolvingPass(constraintWidget4, measurer);
                    }
                }
            }
        }
    }
}
