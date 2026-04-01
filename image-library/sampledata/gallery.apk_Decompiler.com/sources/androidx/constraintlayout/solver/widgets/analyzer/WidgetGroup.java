package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.Chain;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import c0.C0086a;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestList;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WidgetGroup {
    static int count;
    boolean authoritative = false;
    int id;
    private int moveTo = -1;
    int orientation;
    ArrayList<MeasureResult> results = null;
    ArrayList<ConstraintWidget> widgets = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MeasureResult {
        int baseline;
        int bottom;
        int left;
        int orientation;
        int right;
        int top;
        WeakReference<ConstraintWidget> widgetRef;

        public MeasureResult(ConstraintWidget constraintWidget, LinearSystem linearSystem, int i2) {
            this.widgetRef = new WeakReference<>(constraintWidget);
            this.left = linearSystem.getObjectVariableValue(constraintWidget.mLeft);
            this.top = linearSystem.getObjectVariableValue(constraintWidget.mTop);
            this.right = linearSystem.getObjectVariableValue(constraintWidget.mRight);
            this.bottom = linearSystem.getObjectVariableValue(constraintWidget.mBottom);
            this.baseline = linearSystem.getObjectVariableValue(constraintWidget.mBaseline);
            this.orientation = i2;
        }
    }

    public WidgetGroup(int i2) {
        int i7 = count;
        count = i7 + 1;
        this.id = i7;
        this.orientation = i2;
    }

    private String getOrientationString() {
        int i2 = this.orientation;
        if (i2 == 0) {
            return "Horizontal";
        }
        if (i2 == 1) {
            return "Vertical";
        }
        if (i2 == 2) {
            return "Both";
        }
        return C2paManifestList.UNKNOWN_VALUE;
    }

    private int solverMeasure(LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i2) {
        int objectVariableValue;
        int objectVariableValue2;
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) arrayList.get(0).getParent();
        linearSystem.reset();
        constraintWidgetContainer.addToSolver(linearSystem, false);
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            arrayList.get(i7).addToSolver(linearSystem, false);
        }
        if (i2 == 0 && constraintWidgetContainer.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 0);
        }
        if (i2 == 1 && constraintWidgetContainer.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 1);
        }
        try {
            linearSystem.minimize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.results = new ArrayList<>();
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            this.results.add(new MeasureResult(arrayList.get(i8), linearSystem, i2));
        }
        if (i2 == 0) {
            objectVariableValue = linearSystem.getObjectVariableValue(constraintWidgetContainer.mLeft);
            objectVariableValue2 = linearSystem.getObjectVariableValue(constraintWidgetContainer.mRight);
            linearSystem.reset();
        } else {
            objectVariableValue = linearSystem.getObjectVariableValue(constraintWidgetContainer.mTop);
            objectVariableValue2 = linearSystem.getObjectVariableValue(constraintWidgetContainer.mBottom);
            linearSystem.reset();
        }
        return objectVariableValue2 - objectVariableValue;
    }

    public boolean add(ConstraintWidget constraintWidget) {
        if (this.widgets.contains(constraintWidget)) {
            return false;
        }
        this.widgets.add(constraintWidget);
        return true;
    }

    public void cleanup(ArrayList<WidgetGroup> arrayList) {
        int size = this.widgets.size();
        if (this.moveTo != -1 && size > 0) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                WidgetGroup widgetGroup = arrayList.get(i2);
                if (this.moveTo == widgetGroup.id) {
                    moveTo(this.orientation, widgetGroup);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public int getId() {
        return this.id;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public int measureWrap(LinearSystem linearSystem, int i2) {
        if (this.widgets.size() == 0) {
            return 0;
        }
        return solverMeasure(linearSystem, this.widgets, i2);
    }

    public void moveTo(int i2, WidgetGroup widgetGroup) {
        Iterator<ConstraintWidget> it = this.widgets.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            widgetGroup.add(next);
            if (i2 == 0) {
                next.horizontalGroup = widgetGroup.getId();
            } else {
                next.verticalGroup = widgetGroup.getId();
            }
        }
        this.moveTo = widgetGroup.id;
    }

    public void setAuthoritative(boolean z) {
        this.authoritative = z;
    }

    public void setOrientation(int i2) {
        this.orientation = i2;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getOrientationString());
        sb2.append(" [");
        String l = C0086a.l(sb2, this.id, "] <");
        Iterator<ConstraintWidget> it = this.widgets.iterator();
        while (it.hasNext()) {
            StringBuilder t = C0212a.t(l, " ");
            t.append(it.next().getDebugName());
            l = t.toString();
        }
        return C0212a.A(l, " >");
    }
}
