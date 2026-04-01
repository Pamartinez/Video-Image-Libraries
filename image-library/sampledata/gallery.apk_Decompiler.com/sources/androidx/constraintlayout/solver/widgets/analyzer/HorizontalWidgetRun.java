package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HorizontalWidgetRun extends WidgetRun {
    private static int[] tempDimensions = new int[2];

    /* renamed from: androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$widgets$analyzer$WidgetRun$RunType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                androidx.constraintlayout.solver.widgets.analyzer.WidgetRun$RunType[] r0 = androidx.constraintlayout.solver.widgets.analyzer.WidgetRun.RunType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$constraintlayout$solver$widgets$analyzer$WidgetRun$RunType = r0
                androidx.constraintlayout.solver.widgets.analyzer.WidgetRun$RunType r1 = androidx.constraintlayout.solver.widgets.analyzer.WidgetRun.RunType.START     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$analyzer$WidgetRun$RunType     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.constraintlayout.solver.widgets.analyzer.WidgetRun$RunType r1 = androidx.constraintlayout.solver.widgets.analyzer.WidgetRun.RunType.END     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$analyzer$WidgetRun$RunType     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.constraintlayout.solver.widgets.analyzer.WidgetRun$RunType r1 = androidx.constraintlayout.solver.widgets.analyzer.WidgetRun.RunType.CENTER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun.AnonymousClass1.<clinit>():void");
        }
    }

    public HorizontalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.start.type = DependencyNode.Type.LEFT;
        this.end.type = DependencyNode.Type.RIGHT;
        this.orientation = 0;
    }

    private void computeInsetRatio(int[] iArr, int i2, int i7, int i8, int i10, float f, int i11) {
        int i12 = i7 - i2;
        int i13 = i10 - i8;
        if (i11 == -1) {
            int i14 = (int) ((((float) i13) * f) + 0.5f);
            int i15 = (int) ((((float) i12) / f) + 0.5f);
            if (i14 <= i12) {
                iArr[0] = i14;
                iArr[1] = i13;
            } else if (i15 <= i13) {
                iArr[0] = i12;
                iArr[1] = i15;
            }
        } else if (i11 == 0) {
            iArr[0] = (int) ((((float) i13) * f) + 0.5f);
            iArr[1] = i13;
        } else if (i11 == 1) {
            iArr[0] = i12;
            iArr[1] = (int) ((((float) i12) * f) + 0.5f);
        }
    }

    public void apply() {
        ConstraintWidget parent;
        ConstraintWidget parent2;
        ConstraintWidget constraintWidget = this.widget;
        if (constraintWidget.measured) {
            this.dimension.resolve(constraintWidget.getWidth());
        }
        if (!this.dimension.resolved) {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = this.widget.getHorizontalDimensionBehaviour();
            this.dimensionBehavior = horizontalDimensionBehaviour;
            if (horizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (horizontalDimensionBehaviour == dimensionBehaviour && (((parent2 = this.widget.getParent()) != null && parent2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) || parent2.getHorizontalDimensionBehaviour() == dimensionBehaviour)) {
                    int width = (parent2.getWidth() - this.widget.mLeft.getMargin()) - this.widget.mRight.getMargin();
                    addTarget(this.start, parent2.horizontalRun.start, this.widget.mLeft.getMargin());
                    addTarget(this.end, parent2.horizontalRun.end, -this.widget.mRight.getMargin());
                    this.dimension.resolve(width);
                    return;
                } else if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.dimension.resolve(this.widget.getWidth());
                }
            }
        } else {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.dimensionBehavior;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour2 == dimensionBehaviour3 && (((parent = this.widget.getParent()) != null && parent.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) || parent.getHorizontalDimensionBehaviour() == dimensionBehaviour3)) {
                addTarget(this.start, parent.horizontalRun.start, this.widget.mLeft.getMargin());
                addTarget(this.end, parent.horizontalRun.end, -this.widget.mRight.getMargin());
                return;
            }
        }
        DimensionDependency dimensionDependency = this.dimension;
        if (dimensionDependency.resolved) {
            ConstraintWidget constraintWidget2 = this.widget;
            if (constraintWidget2.measured) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.mListAnchors;
                ConstraintAnchor constraintAnchor = constraintAnchorArr[0];
                ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
                if (constraintAnchor2 == null || constraintAnchorArr[1].mTarget == null) {
                    if (constraintAnchor2 != null) {
                        DependencyNode target = getTarget(constraintAnchor);
                        if (target != null) {
                            addTarget(this.start, target, this.widget.mListAnchors[0].getMargin());
                            addTarget(this.end, this.start, this.dimension.value);
                            return;
                        }
                        return;
                    }
                    ConstraintAnchor constraintAnchor3 = constraintAnchorArr[1];
                    if (constraintAnchor3.mTarget != null) {
                        DependencyNode target2 = getTarget(constraintAnchor3);
                        if (target2 != null) {
                            addTarget(this.end, target2, -this.widget.mListAnchors[1].getMargin());
                            addTarget(this.start, this.end, -this.dimension.value);
                            return;
                        }
                        return;
                    } else if (!(constraintWidget2 instanceof Helper) && constraintWidget2.getParent() != null && this.widget.getAnchor(ConstraintAnchor.Type.CENTER).mTarget == null) {
                        addTarget(this.start, this.widget.getParent().horizontalRun.start, this.widget.getX());
                        addTarget(this.end, this.start, this.dimension.value);
                        return;
                    } else {
                        return;
                    }
                } else if (constraintWidget2.isInHorizontalChain()) {
                    this.start.margin = this.widget.mListAnchors[0].getMargin();
                    this.end.margin = -this.widget.mListAnchors[1].getMargin();
                    return;
                } else {
                    DependencyNode target3 = getTarget(this.widget.mListAnchors[0]);
                    if (target3 != null) {
                        addTarget(this.start, target3, this.widget.mListAnchors[0].getMargin());
                    }
                    DependencyNode target4 = getTarget(this.widget.mListAnchors[1]);
                    if (target4 != null) {
                        addTarget(this.end, target4, -this.widget.mListAnchors[1].getMargin());
                    }
                    this.start.delegateToWidgetRun = true;
                    this.end.delegateToWidgetRun = true;
                    return;
                }
            }
        }
        if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.widget;
            int i2 = constraintWidget3.mMatchConstraintDefaultWidth;
            if (i2 == 2) {
                ConstraintWidget parent3 = constraintWidget3.getParent();
                if (parent3 != null) {
                    DimensionDependency dimensionDependency2 = parent3.verticalRun.dimension;
                    this.dimension.targets.add(dimensionDependency2);
                    dimensionDependency2.dependencies.add(this.dimension);
                    DimensionDependency dimensionDependency3 = this.dimension;
                    dimensionDependency3.delegateToWidgetRun = true;
                    dimensionDependency3.dependencies.add(this.start);
                    this.dimension.dependencies.add(this.end);
                }
            } else if (i2 == 3) {
                if (constraintWidget3.mMatchConstraintDefaultHeight == 3) {
                    this.start.updateDelegate = this;
                    this.end.updateDelegate = this;
                    VerticalWidgetRun verticalWidgetRun = constraintWidget3.verticalRun;
                    verticalWidgetRun.start.updateDelegate = this;
                    verticalWidgetRun.end.updateDelegate = this;
                    dimensionDependency.updateDelegate = this;
                    if (constraintWidget3.isInVerticalChain()) {
                        this.dimension.targets.add(this.widget.verticalRun.dimension);
                        this.widget.verticalRun.dimension.dependencies.add(this.dimension);
                        VerticalWidgetRun verticalWidgetRun2 = this.widget.verticalRun;
                        verticalWidgetRun2.dimension.updateDelegate = this;
                        this.dimension.targets.add(verticalWidgetRun2.start);
                        this.dimension.targets.add(this.widget.verticalRun.end);
                        this.widget.verticalRun.start.dependencies.add(this.dimension);
                        this.widget.verticalRun.end.dependencies.add(this.dimension);
                    } else if (this.widget.isInHorizontalChain()) {
                        this.widget.verticalRun.dimension.targets.add(this.dimension);
                        this.dimension.dependencies.add(this.widget.verticalRun.dimension);
                    } else {
                        this.widget.verticalRun.dimension.targets.add(this.dimension);
                    }
                } else {
                    DimensionDependency dimensionDependency4 = constraintWidget3.verticalRun.dimension;
                    dimensionDependency.targets.add(dimensionDependency4);
                    dimensionDependency4.dependencies.add(this.dimension);
                    this.widget.verticalRun.start.dependencies.add(this.dimension);
                    this.widget.verticalRun.end.dependencies.add(this.dimension);
                    DimensionDependency dimensionDependency5 = this.dimension;
                    dimensionDependency5.delegateToWidgetRun = true;
                    dimensionDependency5.dependencies.add(this.start);
                    this.dimension.dependencies.add(this.end);
                    this.start.targets.add(this.dimension);
                    this.end.targets.add(this.dimension);
                }
            }
        }
        ConstraintWidget constraintWidget4 = this.widget;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.mListAnchors;
        ConstraintAnchor constraintAnchor4 = constraintAnchorArr2[0];
        ConstraintAnchor constraintAnchor5 = constraintAnchor4.mTarget;
        if (constraintAnchor5 == null || constraintAnchorArr2[1].mTarget == null) {
            if (constraintAnchor5 != null) {
                DependencyNode target5 = getTarget(constraintAnchor4);
                if (target5 != null) {
                    addTarget(this.start, target5, this.widget.mListAnchors[0].getMargin());
                    addTarget(this.end, this.start, 1, this.dimension);
                    return;
                }
                return;
            }
            ConstraintAnchor constraintAnchor6 = constraintAnchorArr2[1];
            if (constraintAnchor6.mTarget != null) {
                DependencyNode target6 = getTarget(constraintAnchor6);
                if (target6 != null) {
                    addTarget(this.end, target6, -this.widget.mListAnchors[1].getMargin());
                    addTarget(this.start, this.end, -1, this.dimension);
                }
            } else if (!(constraintWidget4 instanceof Helper) && constraintWidget4.getParent() != null) {
                addTarget(this.start, this.widget.getParent().horizontalRun.start, this.widget.getX());
                addTarget(this.end, this.start, 1, this.dimension);
            }
        } else if (constraintWidget4.isInHorizontalChain()) {
            this.start.margin = this.widget.mListAnchors[0].getMargin();
            this.end.margin = -this.widget.mListAnchors[1].getMargin();
        } else {
            DependencyNode target7 = getTarget(this.widget.mListAnchors[0]);
            DependencyNode target8 = getTarget(this.widget.mListAnchors[1]);
            target7.addDependency(this);
            target8.addDependency(this);
            this.mRunType = WidgetRun.RunType.CENTER;
        }
    }

    public void applyToWidget() {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.resolved) {
            this.widget.setX(dependencyNode.value);
        }
    }

    public void clear() {
        this.runGroup = null;
        this.start.clear();
        this.end.clear();
        this.dimension.clear();
        this.resolved = false;
    }

    public void reset() {
        this.resolved = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.dimension.resolved = false;
    }

    public boolean supportsWrapComputation() {
        if (this.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.widget.mMatchConstraintDefaultWidth == 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "HorizontalRun " + this.widget.getDebugName();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:104:0x02a7, code lost:
        if (r7 != 1) goto L_0x030b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void update(androidx.constraintlayout.solver.widgets.analyzer.Dependency r14) {
        /*
            r13 = this;
            int[] r1 = androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun.AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$analyzer$WidgetRun$RunType
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun$RunType r2 = r13.mRunType
            int r2 = r2.ordinal()
            r1 = r1[r2]
            r2 = 2
            r3 = 3
            r8 = 0
            r9 = 1
            if (r1 == r9) goto L_0x0023
            if (r1 == r2) goto L_0x001f
            if (r1 == r3) goto L_0x0015
            goto L_0x0026
        L_0x0015:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r1.mLeft
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.mRight
            r13.updateRunCenter(r14, r2, r1, r8)
            return
        L_0x001f:
            r13.updateRunEnd(r14)
            goto L_0x0026
        L_0x0023:
            r13.updateRunStart(r14)
        L_0x0026:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            boolean r1 = r1.resolved
            r10 = 1056964608(0x3f000000, float:0.5)
            if (r1 != 0) goto L_0x030b
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = r13.dimensionBehavior
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r4) goto L_0x030b
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            int r4 = r1.mMatchConstraintDefaultWidth
            if (r4 == r2) goto L_0x02ee
            if (r4 == r3) goto L_0x003e
            goto L_0x030b
        L_0x003e:
            int r2 = r1.mMatchConstraintDefaultHeight
            r4 = -1
            if (r2 == 0) goto L_0x0089
            if (r2 != r3) goto L_0x0046
            goto L_0x0089
        L_0x0046:
            int r1 = r1.getDimensionRatioSide()
            if (r1 == r4) goto L_0x0074
            if (r1 == 0) goto L_0x0063
            if (r1 == r9) goto L_0x0052
            r1 = r8
            goto L_0x0082
        L_0x0052:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r2 = r2.value
            float r2 = (float) r2
            float r1 = r1.getDimensionRatio()
        L_0x005f:
            float r1 = r1 * r2
            float r1 = r1 + r10
            int r1 = (int) r1
            goto L_0x0082
        L_0x0063:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r2 = r2.value
            float r2 = (float) r2
            float r1 = r1.getDimensionRatio()
            float r2 = r2 / r1
            float r2 = r2 + r10
            int r1 = (int) r2
            goto L_0x0082
        L_0x0074:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            int r2 = r2.value
            float r2 = (float) r2
            float r1 = r1.getDimensionRatio()
            goto L_0x005f
        L_0x0082:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r13.dimension
            r2.resolve(r1)
            goto L_0x030b
        L_0x0089:
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r11 = r2.start
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r12 = r2.end
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r1.mLeft
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0097
            r2 = r9
            goto L_0x0098
        L_0x0097:
            r2 = r8
        L_0x0098:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r1.mTop
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x00a0
            r3 = r9
            goto L_0x00a1
        L_0x00a0:
            r3 = r8
        L_0x00a1:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r1.mRight
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x00a9
            r5 = r9
            goto L_0x00aa
        L_0x00a9:
            r5 = r8
        L_0x00aa:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r1.mBottom
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x00b2
            r6 = r9
            goto L_0x00b3
        L_0x00b2:
            r6 = r8
        L_0x00b3:
            int r7 = r1.getDimensionRatioSide()
            if (r2 == 0) goto L_0x01ed
            if (r3 == 0) goto L_0x01ed
            if (r5 == 0) goto L_0x01ed
            if (r6 == 0) goto L_0x01ed
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            float r6 = r1.getDimensionRatio()
            boolean r1 = r11.resolved
            if (r1 == 0) goto L_0x0124
            boolean r1 = r12.resolved
            if (r1 == 0) goto L_0x0124
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.start
            boolean r2 = r1.readyToSolve
            if (r2 == 0) goto L_0x042c
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r13.end
            boolean r2 = r2.readyToSolve
            if (r2 != 0) goto L_0x00db
            goto L_0x042c
        L_0x00db:
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r13.start
            int r2 = r2.margin
            int r2 = r2 + r1
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.end
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r13.end
            int r3 = r3.margin
            int r3 = r1 - r3
            int r1 = r11.value
            int r4 = r11.margin
            int r4 = r4 + r1
            int r1 = r12.value
            int r5 = r12.margin
            int r5 = r1 - r5
            int[] r1 = tempDimensions
            r0 = r13
            r0.computeInsetRatio(r1, r2, r3, r4, r5, r6, r7)
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            int[] r2 = tempDimensions
            r2 = r2[r8]
            r1.resolve(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r13.widget
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r0.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r0 = r0.dimension
            int[] r1 = tempDimensions
            r1 = r1[r9]
            r0.resolve(r1)
            return
        L_0x0124:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.start
            boolean r2 = r1.resolved
            if (r2 == 0) goto L_0x017d
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r13.end
            boolean r3 = r2.resolved
            if (r3 == 0) goto L_0x017d
            boolean r3 = r11.readyToSolve
            if (r3 == 0) goto L_0x042c
            boolean r3 = r12.readyToSolve
            if (r3 != 0) goto L_0x013a
            goto L_0x042c
        L_0x013a:
            int r3 = r1.value
            int r1 = r1.margin
            int r3 = r3 + r1
            int r1 = r2.value
            int r2 = r2.margin
            int r1 = r1 - r2
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r11.targets
            java.lang.Object r2 = r2.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r2
            int r2 = r2.value
            int r4 = r11.margin
            int r4 = r4 + r2
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r12.targets
            java.lang.Object r2 = r2.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r2
            int r2 = r2.value
            int r5 = r12.margin
            int r5 = r2 - r5
            r2 = r3
            r3 = r1
            int[] r1 = tempDimensions
            r0 = r13
            r0.computeInsetRatio(r1, r2, r3, r4, r5, r6, r7)
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            int[] r2 = tempDimensions
            r2 = r2[r8]
            r1.resolve(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r1.dimension
            int[] r2 = tempDimensions
            r2 = r2[r9]
            r1.resolve(r2)
        L_0x017d:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.start
            boolean r2 = r1.readyToSolve
            if (r2 == 0) goto L_0x042c
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r13.end
            boolean r2 = r2.readyToSolve
            if (r2 == 0) goto L_0x042c
            boolean r2 = r11.readyToSolve
            if (r2 == 0) goto L_0x042c
            boolean r2 = r12.readyToSolve
            if (r2 != 0) goto L_0x0193
            goto L_0x042c
        L_0x0193:
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r13.start
            int r2 = r2.margin
            int r2 = r2 + r1
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.end
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r13.end
            int r3 = r3.margin
            int r3 = r1 - r3
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r1 = r11.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            int r4 = r11.margin
            int r4 = r4 + r1
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r1 = r12.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            int r5 = r12.margin
            int r5 = r1 - r5
            int[] r1 = tempDimensions
            r0 = r13
            r0.computeInsetRatio(r1, r2, r3, r4, r5, r6, r7)
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            int[] r2 = tempDimensions
            r2 = r2[r8]
            r1.resolve(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r1.dimension
            int[] r2 = tempDimensions
            r2 = r2[r9]
            r1.resolve(r2)
            goto L_0x030b
        L_0x01ed:
            if (r2 == 0) goto L_0x0275
            if (r5 == 0) goto L_0x0275
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.start
            boolean r1 = r1.readyToSolve
            if (r1 == 0) goto L_0x042c
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.end
            boolean r1 = r1.readyToSolve
            if (r1 != 0) goto L_0x01ff
            goto L_0x042c
        L_0x01ff:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            float r1 = r1.getDimensionRatio()
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r13.start
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r2.targets
            java.lang.Object r2 = r2.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r2
            int r2 = r2.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r13.start
            int r3 = r3.margin
            int r2 = r2 + r3
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r13.end
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r3 = r3.targets
            java.lang.Object r3 = r3.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r3
            int r3 = r3.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r5 = r13.end
            int r5 = r5.margin
            int r3 = r3 - r5
            if (r7 == r4) goto L_0x0252
            if (r7 == 0) goto L_0x0252
            if (r7 == r9) goto L_0x022f
            goto L_0x030b
        L_0x022f:
            int r3 = r3 - r2
            int r2 = r13.getLimitedDimension(r3, r8)
            float r3 = (float) r2
            float r3 = r3 / r1
            float r3 = r3 + r10
            int r3 = (int) r3
            int r4 = r13.getLimitedDimension(r3, r9)
            if (r3 == r4) goto L_0x0242
            float r2 = (float) r4
            float r2 = r2 * r1
            float r2 = r2 + r10
            int r2 = (int) r2
        L_0x0242:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            r1.resolve(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r1.dimension
            r1.resolve(r4)
            goto L_0x030b
        L_0x0252:
            int r3 = r3 - r2
            int r2 = r13.getLimitedDimension(r3, r8)
            float r3 = (float) r2
            float r3 = r3 * r1
            float r3 = r3 + r10
            int r3 = (int) r3
            int r4 = r13.getLimitedDimension(r3, r9)
            if (r3 == r4) goto L_0x0265
            float r2 = (float) r4
            float r2 = r2 / r1
            float r2 = r2 + r10
            int r2 = (int) r2
        L_0x0265:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            r1.resolve(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r1.dimension
            r1.resolve(r4)
            goto L_0x030b
        L_0x0275:
            if (r3 == 0) goto L_0x030b
            if (r6 == 0) goto L_0x030b
            boolean r1 = r11.readyToSolve
            if (r1 == 0) goto L_0x042c
            boolean r1 = r12.readyToSolve
            if (r1 != 0) goto L_0x0283
            goto L_0x042c
        L_0x0283:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            float r1 = r1.getDimensionRatio()
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r11.targets
            java.lang.Object r2 = r2.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r2
            int r2 = r2.value
            int r3 = r11.margin
            int r2 = r2 + r3
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r3 = r12.targets
            java.lang.Object r3 = r3.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r3
            int r3 = r3.value
            int r5 = r12.margin
            int r3 = r3 - r5
            if (r7 == r4) goto L_0x02cc
            if (r7 == 0) goto L_0x02aa
            if (r7 == r9) goto L_0x02cc
            goto L_0x030b
        L_0x02aa:
            int r3 = r3 - r2
            int r2 = r13.getLimitedDimension(r3, r9)
            float r3 = (float) r2
            float r3 = r3 * r1
            float r3 = r3 + r10
            int r3 = (int) r3
            int r4 = r13.getLimitedDimension(r3, r8)
            if (r3 == r4) goto L_0x02bd
            float r2 = (float) r4
            float r2 = r2 / r1
            float r2 = r2 + r10
            int r2 = (int) r2
        L_0x02bd:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            r1.resolve(r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r1.dimension
            r1.resolve(r2)
            goto L_0x030b
        L_0x02cc:
            int r3 = r3 - r2
            int r2 = r13.getLimitedDimension(r3, r9)
            float r3 = (float) r2
            float r3 = r3 / r1
            float r3 = r3 + r10
            int r3 = (int) r3
            int r4 = r13.getLimitedDimension(r3, r8)
            if (r3 == r4) goto L_0x02df
            float r2 = (float) r4
            float r2 = r2 * r1
            float r2 = r2 + r10
            int r2 = (int) r2
        L_0x02df:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            r1.resolve(r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r1.dimension
            r1.resolve(r2)
            goto L_0x030b
        L_0x02ee:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r1.getParent()
            if (r1 == 0) goto L_0x030b
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r1 = r1.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r1.dimension
            boolean r2 = r1.resolved
            if (r2 == 0) goto L_0x030b
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r13.widget
            float r2 = r2.mMatchConstraintPercentWidth
            int r1 = r1.value
            float r1 = (float) r1
            float r1 = r1 * r2
            float r1 = r1 + r10
            int r1 = (int) r1
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r13.dimension
            r2.resolve(r1)
        L_0x030b:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.start
            boolean r2 = r1.readyToSolve
            if (r2 == 0) goto L_0x042c
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r13.end
            boolean r3 = r2.readyToSolve
            if (r3 != 0) goto L_0x0319
            goto L_0x042c
        L_0x0319:
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x0329
            boolean r1 = r2.resolved
            if (r1 == 0) goto L_0x0329
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x0329
            goto L_0x042c
        L_0x0329:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x0373
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = r13.dimensionBehavior
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r2) goto L_0x0373
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.widget
            int r2 = r1.mMatchConstraintDefaultWidth
            if (r2 != 0) goto L_0x0373
            boolean r1 = r1.isInHorizontalChain()
            if (r1 != 0) goto L_0x0373
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.start
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r1
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r13.end
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r2.targets
            java.lang.Object r2 = r2.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r2
            int r1 = r1.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r13.start
            int r4 = r3.margin
            int r1 = r1 + r4
            int r2 = r2.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r4 = r13.end
            int r4 = r4.margin
            int r2 = r2 + r4
            int r4 = r2 - r1
            r3.resolve(r1)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.end
            r1.resolve(r2)
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r0 = r13.dimension
            r0.resolve(r4)
            return
        L_0x0373:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x03d7
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = r13.dimensionBehavior
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r2) goto L_0x03d7
            int r1 = r13.matchConstraintsType
            if (r1 != r9) goto L_0x03d7
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.start
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r1 = r1.targets
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x03d7
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.end
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r1 = r1.targets
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x03d7
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.start
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r1
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r13.end
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r2.targets
            java.lang.Object r2 = r2.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r2
            int r1 = r1.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r13.start
            int r3 = r3.margin
            int r1 = r1 + r3
            int r2 = r2.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r13.end
            int r3 = r3.margin
            int r2 = r2 + r3
            int r2 = r2 - r1
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            int r1 = r1.wrapValue
            int r1 = java.lang.Math.min(r2, r1)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r13.widget
            int r3 = r2.mMatchConstraintMaxWidth
            int r2 = r2.mMatchConstraintMinWidth
            int r1 = java.lang.Math.max(r2, r1)
            if (r3 <= 0) goto L_0x03d2
            int r1 = java.lang.Math.min(r3, r1)
        L_0x03d2:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r13.dimension
            r2.resolve(r1)
        L_0x03d7:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x03de
            goto L_0x042c
        L_0x03de:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.start
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r1
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r13.end
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r2.targets
            java.lang.Object r2 = r2.get(r8)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = (androidx.constraintlayout.solver.widgets.analyzer.DependencyNode) r2
            int r3 = r1.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r4 = r13.start
            int r4 = r4.margin
            int r3 = r3 + r4
            int r4 = r2.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r5 = r13.end
            int r5 = r5.margin
            int r4 = r4 + r5
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r13.widget
            float r5 = r5.getHorizontalBiasPercent()
            if (r1 != r2) goto L_0x040d
            int r3 = r1.value
            int r4 = r2.value
            r5 = r10
        L_0x040d:
            int r4 = r4 - r3
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            int r1 = r1.value
            int r4 = r4 - r1
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.start
            float r2 = (float) r3
            float r2 = r2 + r10
            float r3 = (float) r4
            float r3 = r3 * r5
            float r3 = r3 + r2
            int r2 = (int) r3
            r1.resolve(r2)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r13.end
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r13.start
            int r2 = r2.value
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r0 = r13.dimension
            int r0 = r0.value
            int r2 = r2 + r0
            r1.resolve(r2)
        L_0x042c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun.update(androidx.constraintlayout.solver.widgets.analyzer.Dependency):void");
    }
}
