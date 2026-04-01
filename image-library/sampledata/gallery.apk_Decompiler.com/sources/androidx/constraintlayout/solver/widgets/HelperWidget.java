package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.analyzer.Grouping;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetGroup;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class HelperWidget extends ConstraintWidget implements Helper {
    public ConstraintWidget[] mWidgets = new ConstraintWidget[4];
    public int mWidgetsCount = 0;

    public void add(ConstraintWidget constraintWidget) {
        if (constraintWidget != this && constraintWidget != null) {
            int i2 = this.mWidgetsCount + 1;
            ConstraintWidget[] constraintWidgetArr = this.mWidgets;
            if (i2 > constraintWidgetArr.length) {
                this.mWidgets = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr, constraintWidgetArr.length * 2);
            }
            ConstraintWidget[] constraintWidgetArr2 = this.mWidgets;
            int i7 = this.mWidgetsCount;
            constraintWidgetArr2[i7] = constraintWidget;
            this.mWidgetsCount = i7 + 1;
        }
    }

    public void addDependents(ArrayList<WidgetGroup> arrayList, int i2, WidgetGroup widgetGroup) {
        for (int i7 = 0; i7 < this.mWidgetsCount; i7++) {
            widgetGroup.add(this.mWidgets[i7]);
        }
        for (int i8 = 0; i8 < this.mWidgetsCount; i8++) {
            Grouping.findDependents(this.mWidgets[i8], i2, arrayList, widgetGroup);
        }
    }

    public int findGroupInDependents(int i2) {
        int i7;
        int i8;
        for (int i10 = 0; i10 < this.mWidgetsCount; i10++) {
            ConstraintWidget constraintWidget = this.mWidgets[i10];
            if (i2 == 0 && (i8 = constraintWidget.horizontalGroup) != -1) {
                return i8;
            }
            if (i2 == 1 && (i7 = constraintWidget.verticalGroup) != -1) {
                return i7;
            }
        }
        return -1;
    }

    public void removeAllIds() {
        this.mWidgetsCount = 0;
        Arrays.fill(this.mWidgets, (Object) null);
    }

    public void updateConstraints(ConstraintWidgetContainer constraintWidgetContainer) {
    }
}
