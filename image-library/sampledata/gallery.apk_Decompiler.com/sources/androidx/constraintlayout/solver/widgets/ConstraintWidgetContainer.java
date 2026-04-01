package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyGraph;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConstraintWidgetContainer extends WidgetContainer {
    private WeakReference<ConstraintAnchor> horizontalWrapMax = null;
    private WeakReference<ConstraintAnchor> horizontalWrapMin = null;
    BasicMeasure mBasicMeasureSolver = new BasicMeasure(this);
    int mDebugSolverPassCount = 0;
    public DependencyGraph mDependencyGraph = new DependencyGraph(this);
    public boolean mGroupsWrapOptimized = false;
    private boolean mHeightMeasuredTooSmall = false;
    ChainHead[] mHorizontalChainsArray = new ChainHead[4];
    public int mHorizontalChainsSize = 0;
    public boolean mHorizontalWrapOptimized = false;
    private boolean mIsRtl = false;
    public BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    protected BasicMeasure.Measurer mMeasurer = null;
    public Metrics mMetrics;
    private int mOptimizationLevel = 257;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    public boolean mSkipSolver = false;
    protected LinearSystem mSystem = new LinearSystem();
    ChainHead[] mVerticalChainsArray = new ChainHead[4];
    public int mVerticalChainsSize = 0;
    public boolean mVerticalWrapOptimized = false;
    private boolean mWidthMeasuredTooSmall = false;
    public int mWrapFixedHeight = 0;
    public int mWrapFixedWidth = 0;
    private WeakReference<ConstraintAnchor> verticalWrapMax = null;
    private WeakReference<ConstraintAnchor> verticalWrapMin = null;

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        int i2 = this.mHorizontalChainsSize + 1;
        ChainHead[] chainHeadArr = this.mHorizontalChainsArray;
        if (i2 >= chainHeadArr.length) {
            this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = new ChainHead(constraintWidget, 0, isRtl());
        this.mHorizontalChainsSize++;
    }

    private void addMaxWrap(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.mSystem.addGreaterThan(solverVariable, this.mSystem.createObjectVariable(constraintAnchor), 0, 5);
    }

    private void addMinWrap(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.mSystem.addGreaterThan(this.mSystem.createObjectVariable(constraintAnchor), solverVariable, 0, 5);
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        int i2 = this.mVerticalChainsSize + 1;
        ChainHead[] chainHeadArr = this.mVerticalChainsArray;
        if (i2 >= chainHeadArr.length) {
            this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = new ChainHead(constraintWidget, 1, isRtl());
        this.mVerticalChainsSize++;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    public void addChain(ConstraintWidget constraintWidget, int i2) {
        if (i2 == 0) {
            addHorizontalChain(constraintWidget);
        } else if (i2 == 1) {
            addVerticalChain(constraintWidget);
        }
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem) {
        LinearSystem linearSystem2;
        ConstraintWidgetContainer constraintWidgetContainer;
        int i2;
        boolean optimizeFor = optimizeFor(64);
        addToSolver(linearSystem, optimizeFor);
        int size = this.mChildren.size();
        boolean z = false;
        for (int i7 = 0; i7 < size; i7++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i7);
            constraintWidget.setInBarrier(0, false);
            constraintWidget.setInBarrier(1, false);
            if (constraintWidget instanceof Barrier) {
                z = true;
            }
        }
        if (z) {
            for (int i8 = 0; i8 < size; i8++) {
                ConstraintWidget constraintWidget2 = this.mChildren.get(i8);
                if (constraintWidget2 instanceof Barrier) {
                    ((Barrier) constraintWidget2).markWidgets();
                }
            }
        }
        for (int i10 = 0; i10 < size; i10++) {
            ConstraintWidget constraintWidget3 = this.mChildren.get(i10);
            if (constraintWidget3.addFirst()) {
                constraintWidget3.addToSolver(linearSystem, optimizeFor);
            }
        }
        if (LinearSystem.USE_DEPENDENCY_ORDERING) {
            HashSet hashSet = new HashSet();
            for (int i11 = 0; i11 < size; i11++) {
                ConstraintWidget constraintWidget4 = this.mChildren.get(i11);
                if (!constraintWidget4.addFirst()) {
                    hashSet.add(constraintWidget4);
                }
            }
            if (getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            constraintWidgetContainer = this;
            linearSystem2 = linearSystem;
            constraintWidgetContainer.addChildrenToSolverByDependency(this, linearSystem2, hashSet, i2, false);
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget5 = (ConstraintWidget) it.next();
                Optimizer.checkMatchParent(constraintWidgetContainer, linearSystem2, constraintWidget5);
                constraintWidget5.addToSolver(linearSystem2, optimizeFor);
            }
        } else {
            constraintWidgetContainer = this;
            linearSystem2 = linearSystem;
            for (int i12 = 0; i12 < size; i12++) {
                ConstraintWidget constraintWidget6 = constraintWidgetContainer.mChildren.get(i12);
                if (constraintWidget6 instanceof ConstraintWidgetContainer) {
                    ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget6.mListDimensionBehaviors;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        constraintWidget6.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        constraintWidget6.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    constraintWidget6.addToSolver(linearSystem2, optimizeFor);
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        constraintWidget6.setHorizontalDimensionBehaviour(dimensionBehaviour);
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        constraintWidget6.setVerticalDimensionBehaviour(dimensionBehaviour2);
                    }
                } else {
                    Optimizer.checkMatchParent(constraintWidgetContainer, linearSystem2, constraintWidget6);
                    if (!constraintWidget6.addFirst()) {
                        constraintWidget6.addToSolver(linearSystem2, optimizeFor);
                    }
                }
            }
        }
        if (constraintWidgetContainer.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem2, (ArrayList<ConstraintWidget>) null, 0);
        }
        if (constraintWidgetContainer.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem2, (ArrayList<ConstraintWidget>) null, 1);
        }
        return true;
    }

    public void addHorizontalWrapMaxVariable(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.horizontalWrapMax;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > this.horizontalWrapMax.get().getFinalValue()) {
            this.horizontalWrapMax = new WeakReference<>(constraintAnchor);
        }
    }

    public void addHorizontalWrapMinVariable(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.horizontalWrapMin;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > this.horizontalWrapMin.get().getFinalValue()) {
            this.horizontalWrapMin = new WeakReference<>(constraintAnchor);
        }
    }

    public void addVerticalWrapMaxVariable(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.verticalWrapMax;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > this.verticalWrapMax.get().getFinalValue()) {
            this.verticalWrapMax = new WeakReference<>(constraintAnchor);
        }
    }

    public void addVerticalWrapMinVariable(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.verticalWrapMin;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > this.verticalWrapMin.get().getFinalValue()) {
            this.verticalWrapMin = new WeakReference<>(constraintAnchor);
        }
    }

    public boolean directMeasure(boolean z) {
        return this.mDependencyGraph.directMeasure(z);
    }

    public boolean directMeasureSetup(boolean z) {
        return this.mDependencyGraph.directMeasureSetup(z);
    }

    public boolean directMeasureWithOrientation(boolean z, int i2) {
        return this.mDependencyGraph.directMeasureWithOrientation(z, i2);
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.mSystem.fillMetrics(metrics);
    }

    public BasicMeasure.Measurer getMeasurer() {
        return this.mMeasurer;
    }

    public int getOptimizationLevel() {
        return this.mOptimizationLevel;
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public void invalidateGraph() {
        this.mDependencyGraph.invalidateGraph();
    }

    public void invalidateMeasures() {
        this.mDependencyGraph.invalidateMeasures();
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v7, resolved type: int} */
    /* JADX WARNING: type inference failed for: r2v9, types: [boolean] */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r2v31 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0218  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0220  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02a3  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x02b5  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02d1  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02df  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layout() {
        /*
            r20 = this;
            r1 = r20
            r2 = 0
            r1.mX = r2
            r1.mY = r2
            r1.mWidthMeasuredTooSmall = r2
            r1.mHeightMeasuredTooSmall = r2
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r1.mChildren
            int r3 = r0.size()
            int r0 = r1.getWidth()
            int r0 = java.lang.Math.max(r2, r0)
            int r4 = r1.getHeight()
            int r4 = java.lang.Math.max(r2, r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r1.mListDimensionBehaviors
            r6 = 1
            r7 = r5[r6]
            r5 = r5[r2]
            androidx.constraintlayout.solver.Metrics r8 = r1.mMetrics
            if (r8 == 0) goto L_0x0033
            long r9 = r8.layouts
            r11 = 1
            long r9 = r9 + r11
            r8.layouts = r9
        L_0x0033:
            int r8 = r1.mOptimizationLevel
            boolean r8 = androidx.constraintlayout.solver.widgets.Optimizer.enabled(r8, r6)
            if (r8 == 0) goto L_0x008b
            androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure$Measurer r8 = r1.getMeasurer()
            androidx.constraintlayout.solver.widgets.analyzer.Direct.solvingPass(r1, r8)
            r8 = r2
        L_0x0043:
            if (r8 >= r3) goto L_0x008b
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r9 = r1.mChildren
            java.lang.Object r9 = r9.get(r8)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r9
            boolean r10 = r9.isMeasureRequested()
            if (r10 == 0) goto L_0x0088
            boolean r10 = r9 instanceof androidx.constraintlayout.solver.widgets.Guideline
            if (r10 != 0) goto L_0x0088
            boolean r10 = r9 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r10 != 0) goto L_0x0088
            boolean r10 = r9 instanceof androidx.constraintlayout.solver.widgets.VirtualLayout
            if (r10 != 0) goto L_0x0088
            boolean r10 = r9.isInVirtualLayout()
            if (r10 != 0) goto L_0x0088
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = r9.getDimensionBehaviour(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = r9.getDimensionBehaviour(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r10 != r12) goto L_0x007c
            int r10 = r9.mMatchConstraintDefaultWidth
            if (r10 == r6) goto L_0x007c
            if (r11 != r12) goto L_0x007c
            int r10 = r9.mMatchConstraintDefaultHeight
            if (r10 == r6) goto L_0x007c
            goto L_0x0088
        L_0x007c:
            androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure$Measure r10 = new androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure$Measure
            r10.<init>()
            androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure$Measurer r11 = r1.mMeasurer
            int r12 = androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            measure(r9, r11, r10, r12)
        L_0x0088:
            int r8 = r8 + 1
            goto L_0x0043
        L_0x008b:
            r8 = 2
            if (r3 <= r8) goto L_0x00d4
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 == r9) goto L_0x0094
            if (r7 != r9) goto L_0x00d4
        L_0x0094:
            int r10 = r1.mOptimizationLevel
            r11 = 1024(0x400, float:1.435E-42)
            boolean r10 = androidx.constraintlayout.solver.widgets.Optimizer.enabled(r10, r11)
            if (r10 == 0) goto L_0x00d4
            androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure$Measurer r10 = r1.getMeasurer()
            boolean r10 = androidx.constraintlayout.solver.widgets.analyzer.Grouping.simpleSolvingPass(r1, r10)
            if (r10 == 0) goto L_0x00d4
            if (r5 != r9) goto L_0x00bc
            int r10 = r1.getWidth()
            if (r0 >= r10) goto L_0x00b8
            if (r0 <= 0) goto L_0x00b8
            r1.setWidth(r0)
            r1.mWidthMeasuredTooSmall = r6
            goto L_0x00bc
        L_0x00b8:
            int r0 = r1.getWidth()
        L_0x00bc:
            if (r7 != r9) goto L_0x00d0
            int r9 = r1.getHeight()
            if (r4 >= r9) goto L_0x00cc
            if (r4 <= 0) goto L_0x00cc
            r1.setHeight(r4)
            r1.mHeightMeasuredTooSmall = r6
            goto L_0x00d0
        L_0x00cc:
            int r4 = r1.getHeight()
        L_0x00d0:
            r9 = r4
            r4 = r0
            r0 = r6
            goto L_0x00d7
        L_0x00d4:
            r9 = r4
            r4 = r0
            r0 = r2
        L_0x00d7:
            r10 = 64
            boolean r11 = r1.optimizeFor(r10)
            if (r11 != 0) goto L_0x00ea
            r11 = 128(0x80, float:1.794E-43)
            boolean r11 = r1.optimizeFor(r11)
            if (r11 == 0) goto L_0x00e8
            goto L_0x00ea
        L_0x00e8:
            r11 = r2
            goto L_0x00eb
        L_0x00ea:
            r11 = r6
        L_0x00eb:
            androidx.constraintlayout.solver.LinearSystem r12 = r1.mSystem
            r12.graphOptimizer = r2
            r12.newgraphOptimizer = r2
            int r13 = r1.mOptimizationLevel
            if (r13 == 0) goto L_0x00f9
            if (r11 == 0) goto L_0x00f9
            r12.newgraphOptimizer = r6
        L_0x00f9:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r11 = r1.mChildren
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = r1.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r12 == r13) goto L_0x010c
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = r1.getVerticalDimensionBehaviour()
            if (r12 != r13) goto L_0x010a
            goto L_0x010c
        L_0x010a:
            r12 = r2
            goto L_0x010d
        L_0x010c:
            r12 = r6
        L_0x010d:
            r1.resetChains()
            r13 = r2
        L_0x0111:
            if (r13 >= r3) goto L_0x0127
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r14 = r1.mChildren
            java.lang.Object r14 = r14.get(r13)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r14 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r14
            boolean r15 = r14 instanceof androidx.constraintlayout.solver.widgets.WidgetContainer
            if (r15 == 0) goto L_0x0124
            androidx.constraintlayout.solver.widgets.WidgetContainer r14 = (androidx.constraintlayout.solver.widgets.WidgetContainer) r14
            r14.layout()
        L_0x0124:
            int r13 = r13 + 1
            goto L_0x0111
        L_0x0127:
            boolean r10 = r1.optimizeFor(r10)
            r13 = r0
            r0 = r2
            r14 = r6
        L_0x012e:
            if (r14 == 0) goto L_0x0326
            int r15 = r0 + 1
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x01f9 }
            r0.reset()     // Catch:{ Exception -> 0x01f9 }
            r1.resetChains()     // Catch:{ Exception -> 0x01f9 }
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x01f9 }
            r1.createObjectVariables(r0)     // Catch:{ Exception -> 0x01f9 }
            r0 = r2
        L_0x0140:
            if (r0 >= r3) goto L_0x0163
            r16 = r2
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r2 = r1.mChildren     // Catch:{ Exception -> 0x015f }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ Exception -> 0x015f }
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r2     // Catch:{ Exception -> 0x015f }
            r17 = r8
            androidx.constraintlayout.solver.LinearSystem r8 = r1.mSystem     // Catch:{ Exception -> 0x015a }
            r2.createObjectVariables(r8)     // Catch:{ Exception -> 0x015a }
            int r0 = r0 + 1
            r2 = r16
            r8 = r17
            goto L_0x0140
        L_0x015a:
            r0 = move-exception
        L_0x015b:
            r18 = r6
            goto L_0x0200
        L_0x015f:
            r0 = move-exception
            r17 = r8
            goto L_0x015b
        L_0x0163:
            r16 = r2
            r17 = r8
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x015a }
            boolean r14 = r1.addChildrenToSolver(r0)     // Catch:{ Exception -> 0x015a }
            java.lang.ref.WeakReference<androidx.constraintlayout.solver.widgets.ConstraintAnchor> r0 = r1.verticalWrapMin     // Catch:{ Exception -> 0x015a }
            r2 = 0
            if (r0 == 0) goto L_0x0192
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x015a }
            if (r0 == 0) goto L_0x0192
            java.lang.ref.WeakReference<androidx.constraintlayout.solver.widgets.ConstraintAnchor> r0 = r1.verticalWrapMin     // Catch:{ Exception -> 0x015a }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x015a }
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r0     // Catch:{ Exception -> 0x015a }
            androidx.constraintlayout.solver.LinearSystem r8 = r1.mSystem     // Catch:{ Exception -> 0x015a }
            r18 = r6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r1.mTop     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.SolverVariable r6 = r8.createObjectVariable(r6)     // Catch:{ Exception -> 0x0190 }
            r1.addMinWrap(r0, r6)     // Catch:{ Exception -> 0x0190 }
            r1.verticalWrapMin = r2     // Catch:{ Exception -> 0x0190 }
            goto L_0x0194
        L_0x0190:
            r0 = move-exception
            goto L_0x0200
        L_0x0192:
            r18 = r6
        L_0x0194:
            java.lang.ref.WeakReference<androidx.constraintlayout.solver.widgets.ConstraintAnchor> r0 = r1.verticalWrapMax     // Catch:{ Exception -> 0x0190 }
            if (r0 == 0) goto L_0x01b3
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0190 }
            if (r0 == 0) goto L_0x01b3
            java.lang.ref.WeakReference<androidx.constraintlayout.solver.widgets.ConstraintAnchor> r0 = r1.verticalWrapMax     // Catch:{ Exception -> 0x0190 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r0     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.LinearSystem r6 = r1.mSystem     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r1.mBottom     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.SolverVariable r6 = r6.createObjectVariable(r8)     // Catch:{ Exception -> 0x0190 }
            r1.addMaxWrap(r0, r6)     // Catch:{ Exception -> 0x0190 }
            r1.verticalWrapMax = r2     // Catch:{ Exception -> 0x0190 }
        L_0x01b3:
            java.lang.ref.WeakReference<androidx.constraintlayout.solver.widgets.ConstraintAnchor> r0 = r1.horizontalWrapMin     // Catch:{ Exception -> 0x0190 }
            if (r0 == 0) goto L_0x01d2
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0190 }
            if (r0 == 0) goto L_0x01d2
            java.lang.ref.WeakReference<androidx.constraintlayout.solver.widgets.ConstraintAnchor> r0 = r1.horizontalWrapMin     // Catch:{ Exception -> 0x0190 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r0     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.LinearSystem r6 = r1.mSystem     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r1.mLeft     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.SolverVariable r6 = r6.createObjectVariable(r8)     // Catch:{ Exception -> 0x0190 }
            r1.addMinWrap(r0, r6)     // Catch:{ Exception -> 0x0190 }
            r1.horizontalWrapMin = r2     // Catch:{ Exception -> 0x0190 }
        L_0x01d2:
            java.lang.ref.WeakReference<androidx.constraintlayout.solver.widgets.ConstraintAnchor> r0 = r1.horizontalWrapMax     // Catch:{ Exception -> 0x0190 }
            if (r0 == 0) goto L_0x01f1
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0190 }
            if (r0 == 0) goto L_0x01f1
            java.lang.ref.WeakReference<androidx.constraintlayout.solver.widgets.ConstraintAnchor> r0 = r1.horizontalWrapMax     // Catch:{ Exception -> 0x0190 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r0     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.LinearSystem r6 = r1.mSystem     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r1.mRight     // Catch:{ Exception -> 0x0190 }
            androidx.constraintlayout.solver.SolverVariable r6 = r6.createObjectVariable(r8)     // Catch:{ Exception -> 0x0190 }
            r1.addMaxWrap(r0, r6)     // Catch:{ Exception -> 0x0190 }
            r1.horizontalWrapMax = r2     // Catch:{ Exception -> 0x0190 }
        L_0x01f1:
            if (r14 == 0) goto L_0x0216
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x0190 }
            r0.minimize()     // Catch:{ Exception -> 0x0190 }
            goto L_0x0216
        L_0x01f9:
            r0 = move-exception
            r16 = r2
            r18 = r6
            r17 = r8
        L_0x0200:
            r0.printStackTrace()
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r8 = "EXCEPTION : "
            r6.<init>(r8)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r2.println(r0)
        L_0x0216:
            if (r14 == 0) goto L_0x0220
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem
            boolean[] r2 = androidx.constraintlayout.solver.widgets.Optimizer.flags
            r1.updateChildrenFromSolver(r0, r2)
            goto L_0x0239
        L_0x0220:
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem
            r1.updateFromSolver(r0, r10)
            r0 = r16
        L_0x0227:
            if (r0 >= r3) goto L_0x0239
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r2 = r1.mChildren
            java.lang.Object r2 = r2.get(r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r2
            androidx.constraintlayout.solver.LinearSystem r6 = r1.mSystem
            r2.updateFromSolver(r6, r10)
            int r0 = r0 + 1
            goto L_0x0227
        L_0x0239:
            if (r12 == 0) goto L_0x02a3
            r0 = 8
            if (r15 >= r0) goto L_0x02a3
            boolean[] r0 = androidx.constraintlayout.solver.widgets.Optimizer.flags
            boolean r0 = r0[r17]
            if (r0 == 0) goto L_0x02a3
            r0 = r16
            r2 = r0
            r6 = r2
        L_0x0249:
            if (r0 >= r3) goto L_0x026d
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r8 = r1.mChildren
            java.lang.Object r8 = r8.get(r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r8
            int r14 = r8.mX
            int r19 = r8.getWidth()
            int r14 = r19 + r14
            int r2 = java.lang.Math.max(r2, r14)
            int r14 = r8.mY
            int r8 = r8.getHeight()
            int r8 = r8 + r14
            int r6 = java.lang.Math.max(r6, r8)
            int r0 = r0 + 1
            goto L_0x0249
        L_0x026d:
            int r0 = r1.mMinWidth
            int r0 = java.lang.Math.max(r0, r2)
            int r2 = r1.mMinHeight
            int r2 = java.lang.Math.max(r2, r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 != r6) goto L_0x028e
            int r8 = r1.getWidth()
            if (r8 >= r0) goto L_0x028e
            r1.setWidth(r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r0[r16] = r6
            r0 = r18
            r13 = r0
            goto L_0x0290
        L_0x028e:
            r0 = r16
        L_0x0290:
            if (r7 != r6) goto L_0x02a5
            int r8 = r1.getHeight()
            if (r8 >= r2) goto L_0x02a5
            r1.setHeight(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r0[r18] = r6
            r0 = r18
            r13 = r0
            goto L_0x02a5
        L_0x02a3:
            r0 = r16
        L_0x02a5:
            int r2 = r1.mMinWidth
            int r6 = r1.getWidth()
            int r2 = java.lang.Math.max(r2, r6)
            int r6 = r1.getWidth()
            if (r2 <= r6) goto L_0x02c1
            r1.setWidth(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r16] = r2
            r0 = r18
            r13 = r0
        L_0x02c1:
            int r2 = r1.mMinHeight
            int r6 = r1.getHeight()
            int r2 = java.lang.Math.max(r2, r6)
            int r6 = r1.getHeight()
            if (r2 <= r6) goto L_0x02dd
            r1.setHeight(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r18] = r2
            r0 = r18
            r13 = r0
        L_0x02dd:
            if (r13 != 0) goto L_0x031d
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r1.mListDimensionBehaviors
            r2 = r2[r16]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r6) goto L_0x02ff
            if (r4 <= 0) goto L_0x02ff
            int r2 = r1.getWidth()
            if (r2 <= r4) goto L_0x02ff
            r2 = r18
            r1.mWidthMeasuredTooSmall = r2
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r16] = r8
            r1.setWidth(r4)
            r0 = r2
            r13 = r0
            goto L_0x0301
        L_0x02ff:
            r2 = r18
        L_0x0301:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r8 = r1.mListDimensionBehaviors
            r8 = r8[r2]
            if (r8 != r6) goto L_0x031d
            if (r9 <= 0) goto L_0x031d
            int r6 = r1.getHeight()
            if (r6 <= r9) goto L_0x031d
            r1.mHeightMeasuredTooSmall = r2
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r2] = r6
            r1.setHeight(r9)
            r13 = 1
            r14 = 1
            goto L_0x031e
        L_0x031d:
            r14 = r0
        L_0x031e:
            r0 = r15
            r2 = r16
            r8 = r17
            r6 = 1
            goto L_0x012e
        L_0x0326:
            r16 = r2
            r1.mChildren = r11
            if (r13 == 0) goto L_0x0334
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r0[r16] = r5
            r18 = 1
            r0[r18] = r7
        L_0x0334:
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem
            androidx.constraintlayout.solver.Cache r0 = r0.getCache()
            r1.resetSolverVariables(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer.layout():void");
    }

    public long measure(int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14, int i15) {
        int i16 = i14;
        this.mPaddingLeft = i16;
        int i17 = i15;
        this.mPaddingTop = i17;
        return this.mBasicMeasureSolver.solverMeasure(this, i2, i16, i17, i7, i8, i10, i11, i12, i13);
    }

    public boolean optimizeFor(int i2) {
        if ((this.mOptimizationLevel & i2) == i2) {
            return true;
        }
        return false;
    }

    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mSkipSolver = false;
        super.reset();
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.mMeasurer = measurer;
        this.mDependencyGraph.setMeasurer(measurer);
    }

    public void setOptimizationLevel(int i2) {
        this.mOptimizationLevel = i2;
        LinearSystem.USE_DEPENDENCY_ORDERING = optimizeFor(512);
    }

    public void setRtl(boolean z) {
        this.mIsRtl = z;
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, boolean[] zArr) {
        zArr[2] = false;
        boolean optimizeFor = optimizeFor(64);
        updateFromSolver(linearSystem, optimizeFor);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mChildren.get(i2).updateFromSolver(linearSystem, optimizeFor);
        }
    }

    public void updateFromRuns(boolean z, boolean z3) {
        super.updateFromRuns(z, z3);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mChildren.get(i2).updateFromRuns(z, z3);
        }
    }

    public void updateHierarchy() {
        this.mBasicMeasureSolver.updateHierarchy(this);
    }

    public static boolean measure(ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, BasicMeasure.Measure measure, int i2) {
        int i7;
        int i8;
        if (measurer == null) {
            return false;
        }
        measure.horizontalBehavior = constraintWidget.getHorizontalDimensionBehaviour();
        measure.verticalBehavior = constraintWidget.getVerticalDimensionBehaviour();
        measure.horizontalDimension = constraintWidget.getWidth();
        measure.verticalDimension = constraintWidget.getHeight();
        measure.measuredNeedsSolverPass = false;
        measure.measureStrategy = i2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure.horizontalBehavior;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z = dimensionBehaviour == dimensionBehaviour2;
        boolean z3 = measure.verticalBehavior == dimensionBehaviour2;
        boolean z7 = z && constraintWidget.mDimensionRatio > 0.0f;
        boolean z9 = z3 && constraintWidget.mDimensionRatio > 0.0f;
        if (z && constraintWidget.hasDanglingDimension(0) && constraintWidget.mMatchConstraintDefaultWidth == 0 && !z7) {
            measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z3 && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z = false;
        }
        if (z3 && constraintWidget.hasDanglingDimension(1) && constraintWidget.mMatchConstraintDefaultHeight == 0 && !z9) {
            measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z3 = false;
        }
        if (constraintWidget.isResolvedHorizontally()) {
            measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            z = false;
        }
        if (constraintWidget.isResolvedVertically()) {
            measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            z3 = false;
        }
        if (z7) {
            if (constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
                measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z3) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = measure.verticalBehavior;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                if (dimensionBehaviour3 == dimensionBehaviour4) {
                    i8 = measure.verticalDimension;
                } else {
                    measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    measurer.measure(constraintWidget, measure);
                    i8 = measure.measuredHeight;
                }
                measure.horizontalBehavior = dimensionBehaviour4;
                int i10 = constraintWidget.mDimensionRatioSide;
                if (i10 == 0 || i10 == -1) {
                    measure.horizontalDimension = (int) (constraintWidget.getDimensionRatio() * ((float) i8));
                } else {
                    measure.horizontalDimension = (int) (constraintWidget.getDimensionRatio() / ((float) i8));
                }
            }
        }
        if (z9) {
            if (constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
                measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = measure.horizontalBehavior;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.FIXED;
                if (dimensionBehaviour5 == dimensionBehaviour6) {
                    i7 = measure.horizontalDimension;
                } else {
                    measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    measurer.measure(constraintWidget, measure);
                    i7 = measure.measuredWidth;
                }
                measure.verticalBehavior = dimensionBehaviour6;
                int i11 = constraintWidget.mDimensionRatioSide;
                if (i11 == 0 || i11 == -1) {
                    measure.verticalDimension = (int) (((float) i7) / constraintWidget.getDimensionRatio());
                } else {
                    measure.verticalDimension = (int) (constraintWidget.getDimensionRatio() * ((float) i7));
                }
            }
        }
        measurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(measure.measuredWidth);
        constraintWidget.setHeight(measure.measuredHeight);
        constraintWidget.setHasBaseline(measure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(measure.measuredBaseline);
        measure.measureStrategy = BasicMeasure.Measure.SELF_DIMENSIONS;
        return measure.measuredNeedsSolverPass;
    }
}
