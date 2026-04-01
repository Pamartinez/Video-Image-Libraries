package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import i.C0212a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RunGroup {
    public static int index;
    int direction;
    public boolean dual = false;
    WidgetRun firstRun = null;
    int groupIndex;
    WidgetRun lastRun = null;
    public int position = 0;
    ArrayList<WidgetRun> runs = new ArrayList<>();

    public RunGroup(WidgetRun widgetRun, int i2) {
        int i7 = index;
        this.groupIndex = i7;
        index = i7 + 1;
        this.firstRun = widgetRun;
        this.lastRun = widgetRun;
        this.direction = i2;
    }

    private long traverseEnd(DependencyNode dependencyNode, long j2) {
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun instanceof HelperReferences) {
            return j2;
        }
        int size = dependencyNode.dependencies.size();
        long j3 = j2;
        for (int i2 = 0; i2 < size; i2++) {
            Dependency dependency = dependencyNode.dependencies.get(i2);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.run != widgetRun) {
                    j3 = Math.min(j3, traverseEnd(dependencyNode2, ((long) dependencyNode2.margin) + j2));
                }
            }
        }
        if (dependencyNode != widgetRun.end) {
            return j3;
        }
        long wrapDimension = j2 - widgetRun.getWrapDimension();
        return Math.min(Math.min(j3, traverseEnd(widgetRun.start, wrapDimension)), wrapDimension - ((long) widgetRun.start.margin));
    }

    private long traverseStart(DependencyNode dependencyNode, long j2) {
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun instanceof HelperReferences) {
            return j2;
        }
        int size = dependencyNode.dependencies.size();
        long j3 = j2;
        for (int i2 = 0; i2 < size; i2++) {
            Dependency dependency = dependencyNode.dependencies.get(i2);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.run != widgetRun) {
                    j3 = Math.max(j3, traverseStart(dependencyNode2, ((long) dependencyNode2.margin) + j2));
                }
            }
        }
        if (dependencyNode != widgetRun.start) {
            return j3;
        }
        long wrapDimension = j2 + widgetRun.getWrapDimension();
        return Math.max(Math.max(j3, traverseStart(widgetRun.end, wrapDimension)), wrapDimension - ((long) widgetRun.end.margin));
    }

    public void add(WidgetRun widgetRun) {
        this.runs.add(widgetRun);
        this.lastRun = widgetRun;
    }

    public long computeWrapSize(ConstraintWidgetContainer constraintWidgetContainer, int i2) {
        WidgetRun widgetRun;
        WidgetRun widgetRun2;
        long wrapDimension;
        int i7;
        WidgetRun widgetRun3 = this.firstRun;
        long j2 = 0;
        if (widgetRun3 instanceof ChainRun) {
            if (((ChainRun) widgetRun3).orientation != i2) {
                return 0;
            }
        } else if (i2 == 0) {
            if (!(widgetRun3 instanceof HorizontalWidgetRun)) {
                return 0;
            }
        } else if (!(widgetRun3 instanceof VerticalWidgetRun)) {
            return 0;
        }
        if (i2 == 0) {
            widgetRun = constraintWidgetContainer.horizontalRun;
        } else {
            widgetRun = constraintWidgetContainer.verticalRun;
        }
        DependencyNode dependencyNode = widgetRun.start;
        if (i2 == 0) {
            widgetRun2 = constraintWidgetContainer.horizontalRun;
        } else {
            widgetRun2 = constraintWidgetContainer.verticalRun;
        }
        DependencyNode dependencyNode2 = widgetRun2.end;
        boolean contains = widgetRun3.start.targets.contains(dependencyNode);
        boolean contains2 = this.firstRun.end.targets.contains(dependencyNode2);
        long wrapDimension2 = this.firstRun.getWrapDimension();
        if (contains && contains2) {
            long traverseStart = traverseStart(this.firstRun.start, 0);
            long traverseEnd = traverseEnd(this.firstRun.end, 0);
            long j3 = traverseStart - wrapDimension2;
            WidgetRun widgetRun4 = this.firstRun;
            int i8 = widgetRun4.end.margin;
            if (j3 >= ((long) (-i8))) {
                j3 += (long) i8;
            }
            int i10 = widgetRun4.start.margin;
            long j8 = ((-traverseEnd) - wrapDimension2) - ((long) i10);
            if (j8 >= ((long) i10)) {
                j8 -= (long) i10;
            }
            float biasPercent = widgetRun4.widget.getBiasPercent(i2);
            if (biasPercent > 0.0f) {
                j2 = (long) ((((float) j3) / (1.0f - biasPercent)) + (((float) j8) / biasPercent));
            }
            float f = (float) j2;
            long a7 = ((long) ((f * biasPercent) + 0.5f)) + wrapDimension2 + ((long) C0212a.a(1.0f, biasPercent, f, 0.5f));
            WidgetRun widgetRun5 = this.firstRun;
            wrapDimension = ((long) widgetRun5.start.margin) + a7;
            i7 = widgetRun5.end.margin;
        } else if (contains) {
            DependencyNode dependencyNode3 = this.firstRun.start;
            return Math.max(traverseStart(dependencyNode3, (long) dependencyNode3.margin), ((long) this.firstRun.start.margin) + wrapDimension2);
        } else if (contains2) {
            DependencyNode dependencyNode4 = this.firstRun.end;
            return Math.max(-traverseEnd(dependencyNode4, (long) dependencyNode4.margin), ((long) (-this.firstRun.end.margin)) + wrapDimension2);
        } else {
            WidgetRun widgetRun6 = this.firstRun;
            wrapDimension = widgetRun6.getWrapDimension() + ((long) widgetRun6.start.margin);
            i7 = this.firstRun.end.margin;
        }
        return wrapDimension - ((long) i7);
    }
}
