package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DependencyGraph {
    private ConstraintWidgetContainer container;
    private ConstraintWidgetContainer mContainer;
    ArrayList<RunGroup> mGroups = new ArrayList<>();
    private BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    private BasicMeasure.Measurer mMeasurer = null;
    private boolean mNeedBuildGraph = true;
    private boolean mNeedRedoMeasures = true;
    private ArrayList<WidgetRun> mRuns = new ArrayList<>();
    private ArrayList<RunGroup> runGroups = new ArrayList<>();

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer) {
        this.container = constraintWidgetContainer;
        this.mContainer = constraintWidgetContainer;
    }

    private void applyGroup(DependencyNode dependencyNode, int i2, int i7, DependencyNode dependencyNode2, ArrayList<RunGroup> arrayList, RunGroup runGroup) {
        ArrayList<RunGroup> arrayList2;
        DependencyNode dependencyNode3;
        int i8;
        DependencyGraph dependencyGraph;
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun.runGroup == null) {
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            if (widgetRun != constraintWidgetContainer.horizontalRun && widgetRun != constraintWidgetContainer.verticalRun) {
                if (runGroup == null) {
                    runGroup = new RunGroup(widgetRun, i7);
                    arrayList.add(runGroup);
                }
                RunGroup runGroup2 = runGroup;
                widgetRun.runGroup = runGroup2;
                runGroup2.add(widgetRun);
                for (Dependency next : widgetRun.start.dependencies) {
                    if (next instanceof DependencyNode) {
                        dependencyGraph = this;
                        i8 = i2;
                        dependencyNode3 = dependencyNode2;
                        arrayList2 = arrayList;
                        dependencyGraph.applyGroup((DependencyNode) next, i8, 0, dependencyNode3, arrayList2, runGroup2);
                    } else {
                        dependencyGraph = this;
                        i8 = i2;
                        dependencyNode3 = dependencyNode2;
                        arrayList2 = arrayList;
                    }
                    this = dependencyGraph;
                    i2 = i8;
                    dependencyNode2 = dependencyNode3;
                    arrayList = arrayList2;
                }
                DependencyGraph dependencyGraph2 = this;
                int i10 = i2;
                DependencyNode dependencyNode4 = dependencyNode2;
                ArrayList<RunGroup> arrayList3 = arrayList;
                for (Dependency next2 : widgetRun.end.dependencies) {
                    if (next2 instanceof DependencyNode) {
                        dependencyGraph2.applyGroup((DependencyNode) next2, i10, 1, dependencyNode4, arrayList3, runGroup2);
                    }
                }
                if (i10 == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                    for (Dependency next3 : ((VerticalWidgetRun) widgetRun).baseline.dependencies) {
                        if (next3 instanceof DependencyNode) {
                            dependencyGraph2.applyGroup((DependencyNode) next3, i10, 2, dependencyNode4, arrayList3, runGroup2);
                        }
                    }
                }
                for (DependencyNode next4 : widgetRun.start.targets) {
                    if (next4 == dependencyNode4) {
                        runGroup2.dual = true;
                    }
                    dependencyGraph2.applyGroup(next4, i10, 0, dependencyNode4, arrayList3, runGroup2);
                }
                for (DependencyNode next5 : widgetRun.end.targets) {
                    if (next5 == dependencyNode4) {
                        runGroup2.dual = true;
                    }
                    dependencyGraph2.applyGroup(next5, i10, 1, dependencyNode4, arrayList3, runGroup2);
                }
                if (i10 == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                    for (DependencyNode applyGroup : ((VerticalWidgetRun) widgetRun).baseline.targets) {
                        dependencyGraph2.applyGroup(applyGroup, i10, 2, dependencyNode4, arrayList3, runGroup2);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x01b9  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01b6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean basicMeasureWidgets(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r20) {
        /*
            r19 = this;
            r0 = r20
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r1 = r0.mChildren
            java.util.Iterator r1 = r1.iterator()
        L_0x0008:
            boolean r2 = r1.hasNext()
            r3 = 0
            if (r2 == 0) goto L_0x0342
            java.lang.Object r2 = r1.next()
            r5 = r2
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r5
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r5.mListDimensionBehaviors
            r4 = r2[r3]
            r10 = 1
            r2 = r2[r10]
            int r6 = r5.getVisibility()
            r7 = 8
            if (r6 != r7) goto L_0x0028
            r5.measured = r10
            goto L_0x0008
        L_0x0028:
            float r6 = r5.mMatchConstraintPercentWidth
            r11 = 1065353216(0x3f800000, float:1.0)
            int r6 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            r7 = 2
            if (r6 >= 0) goto L_0x0037
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r6) goto L_0x0037
            r5.mMatchConstraintDefaultWidth = r7
        L_0x0037:
            float r6 = r5.mMatchConstraintPercentHeight
            int r6 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r6 >= 0) goto L_0x0043
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r6) goto L_0x0043
            r5.mMatchConstraintDefaultHeight = r7
        L_0x0043:
            float r6 = r5.getDimensionRatio()
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            r8 = 3
            if (r6 <= 0) goto L_0x0079
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r6) goto L_0x005c
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 == r9) goto L_0x0059
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r2 != r9) goto L_0x005c
        L_0x0059:
            r5.mMatchConstraintDefaultWidth = r8
            goto L_0x0079
        L_0x005c:
            if (r2 != r6) goto L_0x0069
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r4 == r9) goto L_0x0066
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r4 != r9) goto L_0x0069
        L_0x0066:
            r5.mMatchConstraintDefaultHeight = r8
            goto L_0x0079
        L_0x0069:
            if (r4 != r6) goto L_0x0079
            if (r2 != r6) goto L_0x0079
            int r6 = r5.mMatchConstraintDefaultWidth
            if (r6 != 0) goto L_0x0073
            r5.mMatchConstraintDefaultWidth = r8
        L_0x0073:
            int r6 = r5.mMatchConstraintDefaultHeight
            if (r6 != 0) goto L_0x0079
            r5.mMatchConstraintDefaultHeight = r8
        L_0x0079:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r6) goto L_0x008f
            int r9 = r5.mMatchConstraintDefaultWidth
            if (r9 != r10) goto L_0x008f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r5.mLeft
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 == 0) goto L_0x008d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r5.mRight
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 != 0) goto L_0x008f
        L_0x008d:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
        L_0x008f:
            if (r2 != r6) goto L_0x00a3
            int r9 = r5.mMatchConstraintDefaultHeight
            if (r9 != r10) goto L_0x00a3
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r5.mTop
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 == 0) goto L_0x00a1
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r5.mBottom
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 != 0) goto L_0x00a3
        L_0x00a1:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
        L_0x00a3:
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r9 = r5.horizontalRun
            r9.dimensionBehavior = r4
            int r12 = r5.mMatchConstraintDefaultWidth
            r9.matchConstraintsType = r12
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r9 = r5.verticalRun
            r9.dimensionBehavior = r2
            int r13 = r5.mMatchConstraintDefaultHeight
            r9.matchConstraintsType = r13
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r4 == r9) goto L_0x00bf
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r4 == r14) goto L_0x00bf
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r4 != r14) goto L_0x00cd
        L_0x00bf:
            if (r2 == r9) goto L_0x00c9
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r2 == r14) goto L_0x00c9
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r14) goto L_0x00cd
        L_0x00c9:
            r8 = r2
            r6 = r4
            goto L_0x02f3
        L_0x00cd:
            r14 = 1056964608(0x3f000000, float:0.5)
            if (r4 != r6) goto L_0x01a3
            r15 = r6
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r16 = r3
            if (r2 == r6) goto L_0x00e6
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r2 != r3) goto L_0x00dd
            goto L_0x00e6
        L_0x00dd:
            r17 = r8
            r8 = r2
            r2 = r17
        L_0x00e2:
            r17 = r11
            goto L_0x01ab
        L_0x00e6:
            if (r12 != r8) goto L_0x011e
            if (r2 != r6) goto L_0x00f2
            r7 = 0
            r9 = 0
            r8 = r6
            r4 = r19
            r4.measure(r5, r6, r7, r8, r9)
        L_0x00f2:
            int r9 = r5.getHeight()
            float r2 = (float) r9
            float r3 = r5.mDimensionRatio
            float r2 = r2 * r3
            float r2 = r2 + r14
            int r7 = (int) r2
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r8 = r6
            r4 = r19
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getWidth()
            r2.resolve(r3)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r5.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getHeight()
            r2.resolve(r3)
            r5.measured = r10
            goto L_0x0008
        L_0x011e:
            if (r12 != r10) goto L_0x0134
            r7 = 0
            r9 = 0
            r4 = r19
            r8 = r2
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getWidth()
            r2.wrapValue = r3
            goto L_0x0008
        L_0x0134:
            r18 = r8
            r8 = r2
            r2 = r18
            if (r12 != r7) goto L_0x0172
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r0.mListDimensionBehaviors
            r3 = r3[r16]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r3 == r6) goto L_0x0145
            if (r3 != r9) goto L_0x00e2
        L_0x0145:
            float r2 = r5.mMatchConstraintPercentWidth
            int r3 = r0.getWidth()
            float r3 = (float) r3
            float r2 = r2 * r3
            float r2 = r2 + r14
            int r7 = (int) r2
            int r9 = r5.getHeight()
            r4 = r19
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getWidth()
            r2.resolve(r3)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r5.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getHeight()
            r2.resolve(r3)
            r5.measured = r10
            goto L_0x0008
        L_0x0172:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r5.mListAnchors
            r17 = r11
            r11 = r3[r16]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            if (r11 == 0) goto L_0x0182
            r3 = r3[r10]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x01ab
        L_0x0182:
            r7 = 0
            r9 = 0
            r4 = r19
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getWidth()
            r2.resolve(r3)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r5.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getHeight()
            r2.resolve(r3)
            r5.measured = r10
            goto L_0x0008
        L_0x01a3:
            r15 = r8
            r8 = r2
            r2 = r15
            r16 = r3
            r15 = r6
            goto L_0x00e2
        L_0x01ab:
            if (r8 != r15) goto L_0x01b6
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r4 == r6) goto L_0x01b9
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r4 != r3) goto L_0x01b6
            goto L_0x01b9
        L_0x01b6:
            r6 = r4
            goto L_0x0282
        L_0x01b9:
            if (r13 != r2) goto L_0x01fa
            if (r4 != r6) goto L_0x01c5
            r7 = 0
            r9 = 0
            r8 = r6
            r4 = r19
            r4.measure(r5, r6, r7, r8, r9)
        L_0x01c5:
            int r7 = r5.getWidth()
            float r2 = r5.mDimensionRatio
            int r3 = r5.getDimensionRatioSide()
            r4 = -1
            if (r3 != r4) goto L_0x01d4
            float r2 = r17 / r2
        L_0x01d4:
            float r3 = (float) r7
            float r3 = r3 * r2
            float r3 = r3 + r14
            int r9 = (int) r3
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r8 = r6
            r4 = r19
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getWidth()
            r2.resolve(r3)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r5.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getHeight()
            r2.resolve(r3)
            r5.measured = r10
            goto L_0x0008
        L_0x01fa:
            if (r13 != r10) goto L_0x0211
            r7 = 0
            r9 = 0
            r8 = r6
            r6 = r4
            r4 = r19
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r5.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getHeight()
            r2.wrapValue = r3
            goto L_0x0008
        L_0x0211:
            r3 = r6
            r6 = r4
            if (r13 != r7) goto L_0x0250
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r0.mListDimensionBehaviors
            r2 = r2[r10]
            r4 = r8
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r2 == r8) goto L_0x0223
            if (r2 != r9) goto L_0x0221
            goto L_0x0223
        L_0x0221:
            r8 = r4
            goto L_0x0282
        L_0x0223:
            float r2 = r5.mMatchConstraintPercentHeight
            int r7 = r5.getWidth()
            int r3 = r0.getHeight()
            float r3 = (float) r3
            float r2 = r2 * r3
            float r2 = r2 + r14
            int r9 = (int) r2
            r4 = r19
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getWidth()
            r2.resolve(r3)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r5.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getHeight()
            r2.resolve(r3)
            r5.measured = r10
            goto L_0x0008
        L_0x0250:
            r4 = r8
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r5.mListAnchors
            r9 = r8[r7]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 == 0) goto L_0x025f
            r2 = r8[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 != 0) goto L_0x0221
        L_0x025f:
            r7 = 0
            r9 = 0
            r6 = r3
            r8 = r4
            r4 = r19
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getWidth()
            r2.resolve(r3)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r5.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getHeight()
            r2.resolve(r3)
            r5.measured = r10
            goto L_0x0008
        L_0x0282:
            if (r6 != r15) goto L_0x0008
            if (r8 != r15) goto L_0x0008
            if (r12 == r10) goto L_0x02d3
            if (r13 != r10) goto L_0x028b
            goto L_0x02d3
        L_0x028b:
            if (r13 != r7) goto L_0x0008
            if (r12 != r7) goto L_0x0008
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r0.mListDimensionBehaviors
            r3 = r2[r16]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r3 == r6) goto L_0x0299
            if (r3 != r6) goto L_0x0008
        L_0x0299:
            r2 = r2[r10]
            if (r2 == r6) goto L_0x029f
            if (r2 != r6) goto L_0x0008
        L_0x029f:
            float r2 = r5.mMatchConstraintPercentWidth
            float r3 = r5.mMatchConstraintPercentHeight
            int r4 = r0.getWidth()
            float r4 = (float) r4
            float r2 = r2 * r4
            float r2 = r2 + r14
            int r7 = (int) r2
            int r2 = r0.getHeight()
            float r2 = (float) r2
            float r3 = r3 * r2
            float r3 = r3 + r14
            int r9 = (int) r3
            r8 = r6
            r4 = r19
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getWidth()
            r2.resolve(r3)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r5.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getHeight()
            r2.resolve(r3)
            r5.measured = r10
            goto L_0x0008
        L_0x02d3:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r7 = 0
            r9 = 0
            r8 = r6
            r4 = r19
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getWidth()
            r2.wrapValue = r3
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r5.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getHeight()
            r2.wrapValue = r3
            goto L_0x0008
        L_0x02f3:
            int r2 = r5.getWidth()
            if (r6 != r9) goto L_0x030a
            int r2 = r0.getWidth()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r5.mLeft
            int r3 = r3.mMargin
            int r2 = r2 - r3
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r5.mRight
            int r3 = r3.mMargin
            int r2 = r2 - r3
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r6 = r4
        L_0x030a:
            r7 = r2
            int r2 = r5.getHeight()
            if (r8 != r9) goto L_0x0322
            int r2 = r0.getHeight()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r5.mTop
            int r3 = r3.mMargin
            int r2 = r2 - r3
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r5.mBottom
            int r3 = r3.mMargin
            int r2 = r2 - r3
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r8 = r3
        L_0x0322:
            r4 = r19
            r9 = r2
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getWidth()
            r2.resolve(r3)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r5.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r3 = r5.getHeight()
            r2.resolve(r3)
            r5.measured = r10
            goto L_0x0008
        L_0x0342:
            r16 = r3
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.DependencyGraph.basicMeasureWidgets(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer):boolean");
    }

    private int computeWrap(ConstraintWidgetContainer constraintWidgetContainer, int i2) {
        int size = this.mGroups.size();
        long j2 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            j2 = Math.max(j2, this.mGroups.get(i7).computeWrapSize(constraintWidgetContainer, i2));
        }
        return (int) j2;
    }

    private void findGroup(WidgetRun widgetRun, int i2, ArrayList<RunGroup> arrayList) {
        WidgetRun widgetRun2 = widgetRun;
        for (Dependency next : widgetRun2.start.dependencies) {
            if (next instanceof DependencyNode) {
                applyGroup((DependencyNode) next, i2, 0, widgetRun2.end, arrayList, (RunGroup) null);
            } else if (next instanceof WidgetRun) {
                applyGroup(((WidgetRun) next).start, i2, 0, widgetRun2.end, arrayList, (RunGroup) null);
            }
        }
        for (Dependency next2 : widgetRun2.end.dependencies) {
            if (next2 instanceof DependencyNode) {
                applyGroup((DependencyNode) next2, i2, 1, widgetRun2.start, arrayList, (RunGroup) null);
            } else if (next2 instanceof WidgetRun) {
                applyGroup(((WidgetRun) next2).end, i2, 1, widgetRun2.start, arrayList, (RunGroup) null);
            }
        }
        int i7 = i2;
        if (i7 == 1) {
            for (Dependency next3 : ((VerticalWidgetRun) widgetRun2).baseline.dependencies) {
                if (next3 instanceof DependencyNode) {
                    applyGroup((DependencyNode) next3, i7, 2, (DependencyNode) null, arrayList, (RunGroup) null);
                }
                i7 = i2;
            }
        }
    }

    private void measure(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i2, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i7) {
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

    public void buildGraph() {
        buildGraph(this.mRuns);
        this.mGroups.clear();
        RunGroup.index = 0;
        findGroup(this.container.horizontalRun, 0, this.mGroups);
        findGroup(this.container.verticalRun, 1, this.mGroups);
        this.mNeedBuildGraph = false;
    }

    public boolean directMeasure(boolean z) {
        boolean z3;
        boolean z7 = false;
        if (this.mNeedBuildGraph || this.mNeedRedoMeasures) {
            Iterator<ConstraintWidget> it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                next.ensureWidgetRuns();
                next.measured = false;
                next.horizontalRun.reset();
                next.verticalRun.reset();
            }
            this.container.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            constraintWidgetContainer.measured = false;
            constraintWidgetContainer.horizontalRun.reset();
            this.container.verticalRun.reset();
            this.mNeedRedoMeasures = false;
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.container.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.container.getDimensionBehaviour(1);
        if (this.mNeedBuildGraph) {
            buildGraph();
        }
        int x9 = this.container.getX();
        int y = this.container.getY();
        this.container.horizontalRun.start.resolve(x9);
        this.container.verticalRun.start.resolve(y);
        measureWidgets();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour == dimensionBehaviour3 || dimensionBehaviour2 == dimensionBehaviour3) {
            if (z) {
                Iterator<WidgetRun> it2 = this.mRuns.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (!it2.next().supportsWrapComputation()) {
                            z = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (z && dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer2 = this.container;
                constraintWidgetContainer2.setWidth(computeWrap(constraintWidgetContainer2, 0));
                ConstraintWidgetContainer constraintWidgetContainer3 = this.container;
                constraintWidgetContainer3.horizontalRun.dimension.resolve(constraintWidgetContainer3.getWidth());
            }
            if (z && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer4 = this.container;
                constraintWidgetContainer4.setHeight(computeWrap(constraintWidgetContainer4, 1));
                ConstraintWidgetContainer constraintWidgetContainer5 = this.container;
                constraintWidgetContainer5.verticalRun.dimension.resolve(constraintWidgetContainer5.getHeight());
            }
        }
        ConstraintWidgetContainer constraintWidgetContainer6 = this.container;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = constraintWidgetContainer6.mListDimensionBehaviors[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour4 == dimensionBehaviour5 || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int width = constraintWidgetContainer6.getWidth() + x9;
            this.container.horizontalRun.end.resolve(width);
            this.container.horizontalRun.dimension.resolve(width - x9);
            measureWidgets();
            ConstraintWidgetContainer constraintWidgetContainer7 = this.container;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = constraintWidgetContainer7.mListDimensionBehaviors[1];
            if (dimensionBehaviour6 == dimensionBehaviour5 || dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height = constraintWidgetContainer7.getHeight() + y;
                this.container.verticalRun.end.resolve(height);
                this.container.verticalRun.dimension.resolve(height - y);
            }
            measureWidgets();
            z3 = true;
        } else {
            z3 = false;
        }
        Iterator<WidgetRun> it3 = this.mRuns.iterator();
        while (it3.hasNext()) {
            WidgetRun next2 = it3.next();
            if (next2.widget != this.container || next2.resolved) {
                next2.applyToWidget();
            }
        }
        Iterator<WidgetRun> it4 = this.mRuns.iterator();
        while (true) {
            if (!it4.hasNext()) {
                z7 = true;
                break;
            }
            WidgetRun next3 = it4.next();
            if ((z3 || next3.widget != this.container) && (!next3.start.resolved || ((!next3.end.resolved && !(next3 instanceof GuidelineReference)) || (!next3.dimension.resolved && !(next3 instanceof ChainRun) && !(next3 instanceof GuidelineReference))))) {
                break;
            }
        }
        this.container.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.container.setVerticalDimensionBehaviour(dimensionBehaviour2);
        return z7;
    }

    public boolean directMeasureSetup(boolean z) {
        if (this.mNeedBuildGraph) {
            Iterator<ConstraintWidget> it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                next.ensureWidgetRuns();
                next.measured = false;
                HorizontalWidgetRun horizontalWidgetRun = next.horizontalRun;
                horizontalWidgetRun.dimension.resolved = false;
                horizontalWidgetRun.resolved = false;
                horizontalWidgetRun.reset();
                VerticalWidgetRun verticalWidgetRun = next.verticalRun;
                verticalWidgetRun.dimension.resolved = false;
                verticalWidgetRun.resolved = false;
                verticalWidgetRun.reset();
            }
            this.container.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            constraintWidgetContainer.measured = false;
            HorizontalWidgetRun horizontalWidgetRun2 = constraintWidgetContainer.horizontalRun;
            horizontalWidgetRun2.dimension.resolved = false;
            horizontalWidgetRun2.resolved = false;
            horizontalWidgetRun2.reset();
            VerticalWidgetRun verticalWidgetRun2 = this.container.verticalRun;
            verticalWidgetRun2.dimension.resolved = false;
            verticalWidgetRun2.resolved = false;
            verticalWidgetRun2.reset();
            buildGraph();
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        this.container.horizontalRun.start.resolve(0);
        this.container.verticalRun.start.resolve(0);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x013e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean directMeasureWithOrientation(boolean r10, int r11) {
        /*
            r9 = this;
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r0 = r9.container
            r1 = 0
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = r0.getDimensionBehaviour(r1)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r2 = r9.container
            r3 = 1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = r2.getDimensionBehaviour(r3)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r4 = r9.container
            int r4 = r4.getX()
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r5 = r9.container
            int r5 = r5.getY()
            if (r10 == 0) goto L_0x0088
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 == r6) goto L_0x0022
            if (r2 != r6) goto L_0x0088
        L_0x0022:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r6 = r9.mRuns
            java.util.Iterator r6 = r6.iterator()
        L_0x0028:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x003f
            java.lang.Object r7 = r6.next()
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r7 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r7
            int r8 = r7.orientation
            if (r8 != r11) goto L_0x0028
            boolean r7 = r7.supportsWrapComputation()
            if (r7 != 0) goto L_0x0028
            r10 = r1
        L_0x003f:
            if (r11 != 0) goto L_0x0065
            if (r10 == 0) goto L_0x0088
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r10) goto L_0x0088
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r10.setHorizontalDimensionBehaviour(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            int r6 = r9.computeWrap(r10, r1)
            r10.setWidth(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r6 = r10.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r6 = r6.dimension
            int r10 = r10.getWidth()
            r6.resolve(r10)
            goto L_0x0088
        L_0x0065:
            if (r10 == 0) goto L_0x0088
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r10) goto L_0x0088
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r10.setVerticalDimensionBehaviour(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            int r6 = r9.computeWrap(r10, r3)
            r10.setHeight(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r6 = r10.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r6 = r6.dimension
            int r10 = r10.getHeight()
            r6.resolve(r10)
        L_0x0088:
            if (r11 != 0) goto L_0x00b2
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r10.mListDimensionBehaviors
            r5 = r5[r1]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r5 == r6) goto L_0x0098
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r5 != r6) goto L_0x00c1
        L_0x0098:
            int r10 = r10.getWidth()
            int r10 = r10 + r4
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r5 = r9.container
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r5 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r5 = r5.end
            r5.resolve(r10)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r5 = r9.container
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r5 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r5 = r5.dimension
            int r10 = r10 - r4
            r5.resolve(r10)
        L_0x00b0:
            r10 = r3
            goto L_0x00dc
        L_0x00b2:
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r10.mListDimensionBehaviors
            r4 = r4[r3]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r4 == r6) goto L_0x00c3
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r4 != r6) goto L_0x00c1
            goto L_0x00c3
        L_0x00c1:
            r10 = r1
            goto L_0x00dc
        L_0x00c3:
            int r10 = r10.getHeight()
            int r10 = r10 + r5
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r4 = r9.container
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r4 = r4.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r4 = r4.end
            r4.resolve(r10)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r4 = r9.container
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r4 = r4.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r4 = r4.dimension
            int r10 = r10 - r5
            r4.resolve(r10)
            goto L_0x00b0
        L_0x00dc:
            r9.measureWidgets()
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r4 = r9.mRuns
            java.util.Iterator r4 = r4.iterator()
        L_0x00e5:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0105
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r5 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r5
            int r6 = r5.orientation
            if (r6 == r11) goto L_0x00f6
            goto L_0x00e5
        L_0x00f6:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r5.widget
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r7 = r9.container
            if (r6 != r7) goto L_0x0101
            boolean r6 = r5.resolved
            if (r6 != 0) goto L_0x0101
            goto L_0x00e5
        L_0x0101:
            r5.applyToWidget()
            goto L_0x00e5
        L_0x0105:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r4 = r9.mRuns
            java.util.Iterator r4 = r4.iterator()
        L_0x010b:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x013e
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r5 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r5
            int r6 = r5.orientation
            if (r6 == r11) goto L_0x011c
            goto L_0x010b
        L_0x011c:
            if (r10 != 0) goto L_0x0125
            androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r5.widget
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r7 = r9.container
            if (r6 != r7) goto L_0x0125
            goto L_0x010b
        L_0x0125:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r6 = r5.start
            boolean r6 = r6.resolved
            if (r6 != 0) goto L_0x012c
            goto L_0x013f
        L_0x012c:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r6 = r5.end
            boolean r6 = r6.resolved
            if (r6 != 0) goto L_0x0133
            goto L_0x013f
        L_0x0133:
            boolean r6 = r5 instanceof androidx.constraintlayout.solver.widgets.analyzer.ChainRun
            if (r6 != 0) goto L_0x010b
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r5 = r5.dimension
            boolean r5 = r5.resolved
            if (r5 != 0) goto L_0x010b
            goto L_0x013f
        L_0x013e:
            r1 = r3
        L_0x013f:
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            r10.setHorizontalDimensionBehaviour(r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r9 = r9.container
            r9.setVerticalDimensionBehaviour(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.DependencyGraph.directMeasureWithOrientation(boolean, int):boolean");
    }

    public void invalidateGraph() {
        this.mNeedBuildGraph = true;
    }

    public void invalidateMeasures() {
        this.mNeedRedoMeasures = true;
    }

    public void measureWidgets() {
        boolean z;
        DependencyGraph dependencyGraph;
        DimensionDependency dimensionDependency;
        Iterator<ConstraintWidget> it = this.container.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            if (!next.measured) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = next.mListDimensionBehaviors;
                boolean z3 = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i2 = next.mMatchConstraintDefaultWidth;
                int i7 = next.mMatchConstraintDefaultHeight;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                if (dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1)) {
                    z = true;
                } else {
                    z = false;
                }
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i7 == 1)) {
                    z3 = true;
                }
                DimensionDependency dimensionDependency2 = next.horizontalRun.dimension;
                boolean z7 = dimensionDependency2.resolved;
                DimensionDependency dimensionDependency3 = next.verticalRun.dimension;
                boolean z9 = dimensionDependency3.resolved;
                if (z7 && z9) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    dependencyGraph = this;
                    dependencyGraph.measure(next, dimensionBehaviour4, dimensionDependency2.value, dimensionBehaviour4, dimensionDependency3.value);
                    next.measured = true;
                } else if (!z7 || !z3) {
                    dependencyGraph = this;
                    if (z9 && z) {
                        dependencyGraph.measure(next, dimensionBehaviour3, dimensionDependency2.value, ConstraintWidget.DimensionBehaviour.FIXED, dimensionDependency3.value);
                        if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                            next.horizontalRun.dimension.wrapValue = next.getWidth();
                        } else {
                            next.horizontalRun.dimension.resolve(next.getWidth());
                            next.measured = true;
                        }
                    }
                } else {
                    dependencyGraph = this;
                    dependencyGraph.measure(next, ConstraintWidget.DimensionBehaviour.FIXED, dimensionDependency2.value, dimensionBehaviour3, dimensionDependency3.value);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        next.verticalRun.dimension.wrapValue = next.getHeight();
                    } else {
                        next.verticalRun.dimension.resolve(next.getHeight());
                        next.measured = true;
                    }
                }
                if (next.measured && (dimensionDependency = next.verticalRun.baselineDimension) != null) {
                    dimensionDependency.resolve(next.getBaselineDistance());
                }
                this = dependencyGraph;
            }
        }
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.mMeasurer = measurer;
    }

    public void buildGraph(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.mContainer.horizontalRun.clear();
        this.mContainer.verticalRun.clear();
        arrayList.add(this.mContainer.horizontalRun);
        arrayList.add(this.mContainer.verticalRun);
        Iterator<ConstraintWidget> it = this.mContainer.mChildren.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            if (next instanceof Guideline) {
                arrayList.add(new GuidelineReference(next));
            } else {
                if (next.isInHorizontalChain()) {
                    if (next.horizontalChainRun == null) {
                        next.horizontalChainRun = new ChainRun(next, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(next.horizontalChainRun);
                } else {
                    arrayList.add(next.horizontalRun);
                }
                if (next.isInVerticalChain()) {
                    if (next.verticalChainRun == null) {
                        next.verticalChainRun = new ChainRun(next, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(next.verticalChainRun);
                } else {
                    arrayList.add(next.verticalRun);
                }
                if (next instanceof HelperWidget) {
                    arrayList.add(new HelperReferences(next));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<WidgetRun> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            it2.next().clear();
        }
        Iterator<WidgetRun> it3 = arrayList.iterator();
        while (it3.hasNext()) {
            WidgetRun next2 = it3.next();
            if (next2.widget != this.mContainer) {
                next2.apply();
            }
        }
    }
}
