package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.Optimizer;
import androidx.constraintlayout.solver.widgets.VirtualLayout;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BasicMeasure {
    private ConstraintWidgetContainer constraintWidgetContainer;
    private Measure mMeasure = new Measure();
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Measure {
        public static int SELF_DIMENSIONS = 0;
        public static int TRY_GIVEN_DIMENSIONS = 1;
        public static int USE_GIVEN_DIMENSIONS = 2;
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measureStrategy;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer2) {
        this.constraintWidgetContainer = constraintWidgetContainer2;
    }

    private boolean measure(Measurer measurer, ConstraintWidget constraintWidget, int i2) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        this.mMeasure.horizontalBehavior = constraintWidget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = constraintWidget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = constraintWidget.getWidth();
        this.mMeasure.verticalDimension = constraintWidget.getHeight();
        Measure measure = this.mMeasure;
        measure.measuredNeedsSolverPass = false;
        measure.measureStrategy = i2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure.horizontalBehavior;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2) {
            z = true;
        } else {
            z = false;
        }
        if (measure.verticalBehavior == dimensionBehaviour2) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z || constraintWidget.mDimensionRatio <= 0.0f) {
            z7 = false;
        } else {
            z7 = true;
        }
        if (!z3 || constraintWidget.mDimensionRatio <= 0.0f) {
            z9 = false;
        } else {
            z9 = true;
        }
        if (z7 && constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
            measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z9 && constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
            measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
        Measure measure2 = this.mMeasure;
        measure2.measureStrategy = Measure.SELF_DIMENSIONS;
        return measure2.measuredNeedsSolverPass;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008e, code lost:
        if (r8 != r9) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0095, code lost:
        if (r5.mDimensionRatio <= 0.0f) goto L_0x0098;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void measureChildren(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r13) {
        /*
            r12 = this;
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r13.mChildren
            int r0 = r0.size()
            r1 = 64
            boolean r1 = r13.optimizeFor(r1)
            androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure$Measurer r2 = r13.getMeasurer()
            r3 = 0
            r4 = r3
        L_0x0012:
            if (r4 >= r0) goto L_0x00b0
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r5 = r13.mChildren
            java.lang.Object r5 = r5.get(r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r5
            boolean r6 = r5 instanceof androidx.constraintlayout.solver.widgets.Guideline
            if (r6 == 0) goto L_0x0022
            goto L_0x00ac
        L_0x0022:
            boolean r6 = r5 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r6 == 0) goto L_0x0028
            goto L_0x00ac
        L_0x0028:
            boolean r6 = r5.isInVirtualLayout()
            if (r6 == 0) goto L_0x0030
            goto L_0x00ac
        L_0x0030:
            if (r1 == 0) goto L_0x0048
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r6 = r5.horizontalRun
            if (r6 == 0) goto L_0x0048
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r7 = r5.verticalRun
            if (r7 == 0) goto L_0x0048
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r6 = r6.dimension
            boolean r6 = r6.resolved
            if (r6 == 0) goto L_0x0048
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r6 = r7.dimension
            boolean r6 = r6.resolved
            if (r6 == 0) goto L_0x0048
            goto L_0x00ac
        L_0x0048:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = r5.getDimensionBehaviour(r3)
            r7 = 1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = r5.getDimensionBehaviour(r7)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r9) goto L_0x0061
            int r10 = r5.mMatchConstraintDefaultWidth
            if (r10 == r7) goto L_0x0061
            if (r8 != r9) goto L_0x0061
            int r10 = r5.mMatchConstraintDefaultHeight
            if (r10 == r7) goto L_0x0061
            r10 = r7
            goto L_0x0062
        L_0x0061:
            r10 = r3
        L_0x0062:
            if (r10 != 0) goto L_0x0098
            boolean r11 = r13.optimizeFor(r7)
            if (r11 == 0) goto L_0x0098
            boolean r11 = r5 instanceof androidx.constraintlayout.solver.widgets.VirtualLayout
            if (r11 != 0) goto L_0x0098
            if (r6 != r9) goto L_0x007d
            int r11 = r5.mMatchConstraintDefaultWidth
            if (r11 != 0) goto L_0x007d
            if (r8 == r9) goto L_0x007d
            boolean r11 = r5.isInHorizontalChain()
            if (r11 != 0) goto L_0x007d
            r10 = r7
        L_0x007d:
            if (r8 != r9) goto L_0x008c
            int r11 = r5.mMatchConstraintDefaultHeight
            if (r11 != 0) goto L_0x008c
            if (r6 == r9) goto L_0x008c
            boolean r11 = r5.isInHorizontalChain()
            if (r11 != 0) goto L_0x008c
            r10 = r7
        L_0x008c:
            if (r6 == r9) goto L_0x0090
            if (r8 != r9) goto L_0x0098
        L_0x0090:
            float r6 = r5.mDimensionRatio
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 <= 0) goto L_0x0098
            goto L_0x0099
        L_0x0098:
            r7 = r10
        L_0x0099:
            if (r7 == 0) goto L_0x009c
            goto L_0x00ac
        L_0x009c:
            int r6 = androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            r12.measure(r2, r5, r6)
            androidx.constraintlayout.solver.Metrics r5 = r13.mMetrics
            if (r5 == 0) goto L_0x00ac
            long r6 = r5.measuredWidgets
            r8 = 1
            long r6 = r6 + r8
            r5.measuredWidgets = r6
        L_0x00ac:
            int r4 = r4 + 1
            goto L_0x0012
        L_0x00b0:
            r2.didMeasures()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.measureChildren(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer):void");
    }

    private void solveLinearSystem(ConstraintWidgetContainer constraintWidgetContainer2, String str, int i2, int i7) {
        int minWidth = constraintWidgetContainer2.getMinWidth();
        int minHeight = constraintWidgetContainer2.getMinHeight();
        constraintWidgetContainer2.setMinWidth(0);
        constraintWidgetContainer2.setMinHeight(0);
        constraintWidgetContainer2.setWidth(i2);
        constraintWidgetContainer2.setHeight(i7);
        constraintWidgetContainer2.setMinWidth(minWidth);
        constraintWidgetContainer2.setMinHeight(minHeight);
        this.constraintWidgetContainer.layout();
    }

    public long solverMeasure(ConstraintWidgetContainer constraintWidgetContainer2, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14, int i15) {
        long j2;
        int i16;
        boolean z;
        long j3;
        boolean z3;
        boolean z7;
        int i17;
        Measurer measurer;
        boolean z9;
        boolean z10;
        int i18;
        int i19;
        boolean z11;
        Metrics metrics;
        ConstraintWidgetContainer constraintWidgetContainer3 = constraintWidgetContainer2;
        int i20 = i2;
        int i21 = i10;
        int i22 = i12;
        Measurer measurer2 = constraintWidgetContainer3.getMeasurer();
        int size = constraintWidgetContainer3.mChildren.size();
        int width = constraintWidgetContainer3.getWidth();
        int height = constraintWidgetContainer3.getHeight();
        boolean enabled = Optimizer.enabled(i20, 128);
        boolean z12 = enabled || Optimizer.enabled(i20, 64);
        if (z12) {
            int i23 = 0;
            while (true) {
                if (i23 >= size) {
                    break;
                }
                ConstraintWidget constraintWidget = constraintWidgetContainer3.mChildren.get(i23);
                ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z13 = (horizontalDimensionBehaviour == dimensionBehaviour) && (constraintWidget.getVerticalDimensionBehaviour() == dimensionBehaviour) && constraintWidget.getDimensionRatio() > 0.0f;
                if ((!constraintWidget.isInHorizontalChain() || !z13) && ((!constraintWidget.isInVerticalChain() || !z13) && !(constraintWidget instanceof VirtualLayout) && !constraintWidget.isInHorizontalChain() && !constraintWidget.isInVerticalChain())) {
                    i23++;
                }
            }
            z12 = false;
        }
        if (z12 && (metrics = LinearSystem.sMetrics) != null) {
            metrics.measures++;
        }
        boolean z14 = z12 & ((i21 == 1073741824 && i22 == 1073741824) || enabled);
        int i24 = 2;
        if (z14) {
            j2 = 1;
            int min = Math.min(constraintWidgetContainer3.getMaxWidth(), i11);
            int min2 = Math.min(constraintWidgetContainer3.getMaxHeight(), i13);
            if (i21 == 1073741824 && constraintWidgetContainer3.getWidth() != min) {
                constraintWidgetContainer3.setWidth(min);
                constraintWidgetContainer3.invalidateGraph();
            }
            if (i22 == 1073741824 && constraintWidgetContainer3.getHeight() != min2) {
                constraintWidgetContainer3.setHeight(min2);
                constraintWidgetContainer3.invalidateGraph();
            }
            if (i21 == 1073741824 && i22 == 1073741824) {
                z = constraintWidgetContainer3.directMeasure(enabled);
                i16 = 2;
            } else {
                boolean directMeasureSetup = constraintWidgetContainer3.directMeasureSetup(enabled);
                if (i21 == 1073741824) {
                    directMeasureSetup &= constraintWidgetContainer3.directMeasureWithOrientation(enabled, 0);
                    i16 = 1;
                } else {
                    i16 = 0;
                }
                if (i22 == 1073741824) {
                    z = constraintWidgetContainer3.directMeasureWithOrientation(enabled, 1) & directMeasureSetup;
                    i16++;
                } else {
                    z = directMeasureSetup;
                }
            }
            if (z) {
                constraintWidgetContainer3.updateFromRuns(i21 == 1073741824, i22 == 1073741824);
            }
        } else {
            j2 = 1;
            z = false;
            i16 = 0;
        }
        if (z && i16 == 2) {
            return 0;
        }
        int optimizationLevel = constraintWidgetContainer3.getOptimizationLevel();
        if (size > 0) {
            measureChildren(constraintWidgetContainer2);
        }
        updateHierarchy(constraintWidgetContainer2);
        int size2 = this.mVariableDimensionsWidgets.size();
        if (size > 0) {
            solveLinearSystem(constraintWidgetContainer3, "First pass", width, height);
        }
        if (size2 > 0) {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour2 = constraintWidgetContainer3.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z15 = horizontalDimensionBehaviour2 == dimensionBehaviour2;
            boolean z16 = constraintWidgetContainer3.getVerticalDimensionBehaviour() == dimensionBehaviour2;
            int max = Math.max(constraintWidgetContainer3.getWidth(), this.constraintWidgetContainer.getMinWidth());
            j3 = 0;
            int max2 = Math.max(constraintWidgetContainer3.getHeight(), this.constraintWidgetContainer.getMinHeight());
            int i25 = 0;
            boolean z17 = false;
            while (i25 < size2) {
                ConstraintWidget constraintWidget2 = this.mVariableDimensionsWidgets.get(i25);
                if (!(constraintWidget2 instanceof VirtualLayout)) {
                    z11 = z14;
                    i18 = i25;
                    z10 = z16;
                    i19 = width;
                } else {
                    int width2 = constraintWidget2.getWidth();
                    z11 = z14;
                    int height2 = constraintWidget2.getHeight();
                    i18 = i25;
                    boolean measure = measure(measurer2, constraintWidget2, Measure.TRY_GIVEN_DIMENSIONS) | z17;
                    Metrics metrics2 = constraintWidgetContainer3.mMetrics;
                    z10 = z16;
                    i19 = width;
                    if (metrics2 != null) {
                        metrics2.measuredMatchWidgets += j2;
                    }
                    int width3 = constraintWidget2.getWidth();
                    int height3 = constraintWidget2.getHeight();
                    if (width3 != width2) {
                        constraintWidget2.setWidth(width3);
                        if (z15 && constraintWidget2.getRight() > max) {
                            max = Math.max(max, constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin() + constraintWidget2.getRight());
                        }
                        measure = true;
                    }
                    if (height3 != height2) {
                        constraintWidget2.setHeight(height3);
                        if (z10 && constraintWidget2.getBottom() > max2) {
                            max2 = Math.max(max2, constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin() + constraintWidget2.getBottom());
                        }
                        measure = true;
                    }
                    z17 = measure | ((VirtualLayout) constraintWidget2).needSolverPass();
                }
                i25 = i18 + 1;
                z16 = z10;
                z14 = z11;
                width = i19;
                i24 = 2;
            }
            boolean z18 = z14;
            boolean z19 = z16;
            int i26 = width;
            int i27 = i24;
            int i28 = 0;
            while (i28 < i27) {
                int i29 = 0;
                while (i29 < size2) {
                    ConstraintWidget constraintWidget3 = this.mVariableDimensionsWidgets.get(i29);
                    if ((!(constraintWidget3 instanceof Helper) || (constraintWidget3 instanceof VirtualLayout)) && !(constraintWidget3 instanceof Guideline) && constraintWidget3.getVisibility() != 8 && ((!z18 || !constraintWidget3.horizontalRun.dimension.resolved || !constraintWidget3.verticalRun.dimension.resolved) && !(constraintWidget3 instanceof VirtualLayout))) {
                        int width4 = constraintWidget3.getWidth();
                        int height4 = constraintWidget3.getHeight();
                        int baselineDistance = constraintWidget3.getBaselineDistance();
                        int i30 = Measure.TRY_GIVEN_DIMENSIONS;
                        i17 = i29;
                        if (i28 == 1) {
                            i30 = Measure.USE_GIVEN_DIMENSIONS;
                        }
                        boolean measure2 = measure(measurer2, constraintWidget3, i30) | z17;
                        Metrics metrics3 = constraintWidgetContainer3.mMetrics;
                        measurer = measurer2;
                        boolean z20 = measure2;
                        if (metrics3 != null) {
                            metrics3.measuredMatchWidgets += j2;
                        }
                        int width5 = constraintWidget3.getWidth();
                        int height5 = constraintWidget3.getHeight();
                        if (width5 != width4) {
                            constraintWidget3.setWidth(width5);
                            if (z15 && constraintWidget3.getRight() > max) {
                                max = Math.max(max, constraintWidget3.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin() + constraintWidget3.getRight());
                            }
                            z9 = true;
                        } else {
                            z9 = z20;
                        }
                        if (height5 != height4) {
                            constraintWidget3.setHeight(height5);
                            if (z19 && constraintWidget3.getBottom() > max2) {
                                max2 = Math.max(max2, constraintWidget3.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin() + constraintWidget3.getBottom());
                            }
                            z9 = true;
                        }
                        z17 = (!constraintWidget3.hasBaseline() || baselineDistance == constraintWidget3.getBaselineDistance()) ? z9 : true;
                    } else {
                        measurer = measurer2;
                        i17 = i29;
                    }
                    i29 = i17 + 1;
                    measurer2 = measurer;
                }
                Measurer measurer3 = measurer2;
                if (!z17) {
                    break;
                }
                solveLinearSystem(constraintWidgetContainer3, "intermediate pass", i26, height);
                i28++;
                measurer2 = measurer3;
                i27 = 2;
                z17 = false;
            }
            int i31 = i26;
            if (z17) {
                solveLinearSystem(constraintWidgetContainer3, "2nd pass", i31, height);
                if (constraintWidgetContainer3.getWidth() < max) {
                    constraintWidgetContainer3.setWidth(max);
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (constraintWidgetContainer3.getHeight() < max2) {
                    constraintWidgetContainer3.setHeight(max2);
                    z7 = true;
                } else {
                    z7 = z3;
                }
                if (z7) {
                    solveLinearSystem(constraintWidgetContainer3, "3rd pass", i31, height);
                }
            }
        } else {
            j3 = 0;
        }
        constraintWidgetContainer3.setOptimizationLevel(optimizationLevel);
        return j3;
    }

    public void updateHierarchy(ConstraintWidgetContainer constraintWidgetContainer2) {
        this.mVariableDimensionsWidgets.clear();
        int size = constraintWidgetContainer2.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = constraintWidgetContainer2.mChildren.get(i2);
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (horizontalDimensionBehaviour == dimensionBehaviour || constraintWidget.getVerticalDimensionBehaviour() == dimensionBehaviour) {
                this.mVariableDimensionsWidgets.add(constraintWidget);
            }
        }
        constraintWidgetContainer2.invalidateGraph();
    }
}
