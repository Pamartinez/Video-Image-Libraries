package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DimensionDependency extends DependencyNode {
    public int wrapValue;

    public DimensionDependency(WidgetRun widgetRun) {
        super(widgetRun);
        if (widgetRun instanceof HorizontalWidgetRun) {
            this.type = DependencyNode.Type.HORIZONTAL_DIMENSION;
        } else {
            this.type = DependencyNode.Type.VERTICAL_DIMENSION;
        }
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
}
