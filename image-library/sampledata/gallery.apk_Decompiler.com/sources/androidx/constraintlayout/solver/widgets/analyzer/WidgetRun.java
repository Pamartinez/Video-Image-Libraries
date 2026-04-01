package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WidgetRun implements Dependency {
    DimensionDependency dimension = new DimensionDependency(this);
    protected ConstraintWidget.DimensionBehaviour dimensionBehavior;
    public DependencyNode end = new DependencyNode(this);
    protected RunType mRunType = RunType.NONE;
    public int matchConstraintsType;
    public int orientation = 0;
    boolean resolved = false;
    RunGroup runGroup;
    public DependencyNode start = new DependencyNode(this);
    ConstraintWidget widget;

    /* renamed from: androidx.constraintlayout.solver.widgets.analyzer.WidgetRun$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type[] r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type = r0
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BASELINE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x003e }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.WidgetRun.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.widget = constraintWidget;
    }

    private void resolveDimension(int i2, int i7) {
        WidgetRun widgetRun;
        float f;
        int i8;
        int i10 = this.matchConstraintsType;
        if (i10 == 0) {
            this.dimension.resolve(getLimitedDimension(i7, i2));
        } else if (i10 == 1) {
            this.dimension.resolve(Math.min(getLimitedDimension(this.dimension.wrapValue, i2), i7));
        } else if (i10 == 2) {
            ConstraintWidget parent = this.widget.getParent();
            if (parent != null) {
                if (i2 == 0) {
                    widgetRun = parent.horizontalRun;
                } else {
                    widgetRun = parent.verticalRun;
                }
                DimensionDependency dimensionDependency = widgetRun.dimension;
                if (dimensionDependency.resolved) {
                    ConstraintWidget constraintWidget = this.widget;
                    if (i2 == 0) {
                        f = constraintWidget.mMatchConstraintPercentWidth;
                    } else {
                        f = constraintWidget.mMatchConstraintPercentHeight;
                    }
                    this.dimension.resolve(getLimitedDimension((int) ((((float) dimensionDependency.value) * f) + 0.5f), i2));
                }
            }
        } else if (i10 == 3) {
            ConstraintWidget constraintWidget2 = this.widget;
            WidgetRun widgetRun2 = constraintWidget2.horizontalRun;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun2.dimensionBehavior;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour == dimensionBehaviour2 && widgetRun2.matchConstraintsType == 3) {
                VerticalWidgetRun verticalWidgetRun = constraintWidget2.verticalRun;
                if (verticalWidgetRun.dimensionBehavior == dimensionBehaviour2 && verticalWidgetRun.matchConstraintsType == 3) {
                    return;
                }
            }
            if (i2 == 0) {
                widgetRun2 = constraintWidget2.verticalRun;
            }
            if (widgetRun2.dimension.resolved) {
                float dimensionRatio = constraintWidget2.getDimensionRatio();
                if (i2 == 1) {
                    i8 = (int) ((((float) widgetRun2.dimension.value) / dimensionRatio) + 0.5f);
                } else {
                    i8 = (int) ((dimensionRatio * ((float) widgetRun2.dimension.value)) + 0.5f);
                }
                this.dimension.resolve(i8);
            }
        }
    }

    public final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2) {
        dependencyNode.targets.add(dependencyNode2);
        dependencyNode.margin = i2;
        dependencyNode2.dependencies.add(dependencyNode);
    }

    public abstract void apply();

    public abstract void applyToWidget();

    public abstract void clear();

    public final int getLimitedDimension(int i2, int i7) {
        if (i7 == 0) {
            ConstraintWidget constraintWidget = this.widget;
            int i8 = constraintWidget.mMatchConstraintMaxWidth;
            int max = Math.max(constraintWidget.mMatchConstraintMinWidth, i2);
            if (i8 > 0) {
                max = Math.min(i8, i2);
            }
            if (max != i2) {
                return max;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.widget;
            int i10 = constraintWidget2.mMatchConstraintMaxHeight;
            int max2 = Math.max(constraintWidget2.mMatchConstraintMinHeight, i2);
            if (i10 > 0) {
                max2 = Math.min(i10, i2);
            }
            if (max2 != i2) {
                return max2;
            }
        }
        return i2;
    }

    public final DependencyNode getTarget(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
        int i2 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[constraintAnchor2.mType.ordinal()];
        if (i2 == 1) {
            return constraintWidget.horizontalRun.start;
        }
        if (i2 == 2) {
            return constraintWidget.horizontalRun.end;
        }
        if (i2 == 3) {
            return constraintWidget.verticalRun.start;
        }
        if (i2 == 4) {
            return constraintWidget.verticalRun.baseline;
        }
        if (i2 != 5) {
            return null;
        }
        return constraintWidget.verticalRun.end;
    }

    public long getWrapDimension() {
        DimensionDependency dimensionDependency = this.dimension;
        if (dimensionDependency.resolved) {
            return (long) dimensionDependency.value;
        }
        return 0;
    }

    public boolean isResolved() {
        return this.resolved;
    }

    public abstract boolean supportsWrapComputation();

    public abstract void update(Dependency dependency);

    public void updateRunCenter(Dependency dependency, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        float f;
        DependencyNode target = getTarget(constraintAnchor);
        DependencyNode target2 = getTarget(constraintAnchor2);
        if (target.resolved && target2.resolved) {
            int margin = constraintAnchor.getMargin() + target.value;
            int margin2 = target2.value - constraintAnchor2.getMargin();
            int i7 = margin2 - margin;
            if (!this.dimension.resolved && this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                resolveDimension(i2, i7);
            }
            DimensionDependency dimensionDependency = this.dimension;
            if (dimensionDependency.resolved) {
                if (dimensionDependency.value == i7) {
                    this.start.resolve(margin);
                    this.end.resolve(margin2);
                    return;
                }
                ConstraintWidget constraintWidget = this.widget;
                if (i2 == 0) {
                    f = constraintWidget.getHorizontalBiasPercent();
                } else {
                    f = constraintWidget.getVerticalBiasPercent();
                }
                if (target == target2) {
                    margin = target.value;
                    margin2 = target2.value;
                    f = 0.5f;
                }
                this.start.resolve((int) ((((float) ((margin2 - margin) - this.dimension.value)) * f) + ((float) margin) + 0.5f));
                this.end.resolve(this.start.value + this.dimension.value);
            }
        }
    }

    public final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2, DimensionDependency dimensionDependency) {
        dependencyNode.targets.add(dependencyNode2);
        dependencyNode.targets.add(this.dimension);
        dependencyNode.marginFactor = i2;
        dependencyNode.marginDependency = dimensionDependency;
        dependencyNode2.dependencies.add(dependencyNode);
        dimensionDependency.dependencies.add(dependencyNode);
    }

    public final DependencyNode getTarget(ConstraintAnchor constraintAnchor, int i2) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
        WidgetRun widgetRun = i2 == 0 ? constraintWidget.horizontalRun : constraintWidget.verticalRun;
        int i7 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[constraintAnchor2.mType.ordinal()];
        if (i7 != 1) {
            if (i7 != 2) {
                if (i7 != 3) {
                    if (i7 != 5) {
                        return null;
                    }
                }
            }
            return widgetRun.end;
        }
        return widgetRun.start;
    }

    public void updateRunEnd(Dependency dependency) {
    }

    public void updateRunStart(Dependency dependency) {
    }
}
