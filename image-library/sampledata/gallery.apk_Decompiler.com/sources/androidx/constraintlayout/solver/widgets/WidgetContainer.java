package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WidgetContainer extends ConstraintWidget {
    public ArrayList<ConstraintWidget> mChildren = new ArrayList<>();

    public void add(ConstraintWidget constraintWidget) {
        this.mChildren.add(constraintWidget);
        if (constraintWidget.getParent() != null) {
            ((WidgetContainer) constraintWidget.getParent()).remove(constraintWidget);
        }
        constraintWidget.setParent(this);
    }

    public ArrayList<ConstraintWidget> getChildren() {
        return this.mChildren;
    }

    public abstract void layout();

    public void remove(ConstraintWidget constraintWidget) {
        this.mChildren.remove(constraintWidget);
        constraintWidget.reset();
    }

    public void removeAllChildren() {
        this.mChildren.clear();
    }

    public void reset() {
        this.mChildren.clear();
        super.reset();
    }

    public void resetSolverVariables(Cache cache) {
        super.resetSolverVariables(cache);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mChildren.get(i2).resetSolverVariables(cache);
        }
    }
}
