package androidx.constraintlayout.solver.widgets.analyzer;

import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DependencyNode implements Dependency {
    public boolean delegateToWidgetRun = false;
    List<Dependency> dependencies = new ArrayList();
    int margin;
    DimensionDependency marginDependency = null;
    int marginFactor = 1;
    public boolean readyToSolve = false;
    public boolean resolved = false;
    WidgetRun run;
    List<DependencyNode> targets = new ArrayList();
    Type type = Type.UNKNOWN;
    public Dependency updateDelegate = null;
    public int value;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        UNKNOWN,
        HORIZONTAL_DIMENSION,
        VERTICAL_DIMENSION,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE
    }

    public DependencyNode(WidgetRun widgetRun) {
        this.run = widgetRun;
    }

    public void addDependency(Dependency dependency) {
        this.dependencies.add(dependency);
        if (this.resolved) {
            dependency.update(dependency);
        }
    }

    public void clear() {
        this.targets.clear();
        this.dependencies.clear();
        this.resolved = false;
        this.value = 0;
        this.readyToSolve = false;
        this.delegateToWidgetRun = false;
    }

    public void resolve(int i2) {
        if (!this.resolved) {
            this.resolved = true;
            this.value = i2;
            for (Dependency next : this.dependencies) {
                next.update(next);
            }
        }
    }

    public String toString() {
        Object obj;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.run.widget.getDebugName());
        sb2.append(NumericEnum.SEP);
        sb2.append(this.type);
        sb2.append("(");
        if (this.resolved) {
            obj = Integer.valueOf(this.value);
        } else {
            obj = "unresolved";
        }
        sb2.append(obj);
        sb2.append(") <t=");
        sb2.append(this.targets.size());
        sb2.append(":d=");
        sb2.append(this.dependencies.size());
        sb2.append(">");
        return sb2.toString();
    }

    public void update(Dependency dependency) {
        for (DependencyNode dependencyNode : this.targets) {
            if (!dependencyNode.resolved) {
                return;
            }
        }
        this.readyToSolve = true;
        Dependency dependency2 = this.updateDelegate;
        if (dependency2 != null) {
            dependency2.update(this);
        }
        if (this.delegateToWidgetRun) {
            this.run.update(this);
            return;
        }
        DependencyNode dependencyNode2 = null;
        int i2 = 0;
        for (DependencyNode next : this.targets) {
            if (!(next instanceof DimensionDependency)) {
                i2++;
                dependencyNode2 = next;
            }
        }
        if (dependencyNode2 != null && i2 == 1 && dependencyNode2.resolved) {
            DimensionDependency dimensionDependency = this.marginDependency;
            if (dimensionDependency != null) {
                if (dimensionDependency.resolved) {
                    this.margin = this.marginFactor * dimensionDependency.value;
                } else {
                    return;
                }
            }
            resolve(dependencyNode2.value + this.margin);
        }
        Dependency dependency3 = this.updateDelegate;
        if (dependency3 != null) {
            dependency3.update(this);
        }
    }
}
