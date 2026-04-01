package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Barrier extends HelperWidget {
    private boolean mAllowsGoneWidget = true;
    private int mBarrierType = 0;
    private int mMargin = 0;
    boolean resolved = false;

    public void addToSolver(LinearSystem linearSystem, boolean z) {
        ConstraintAnchor[] constraintAnchorArr;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        ConstraintAnchor[] constraintAnchorArr2 = this.mListAnchors;
        constraintAnchorArr2[0] = this.mLeft;
        constraintAnchorArr2[2] = this.mTop;
        constraintAnchorArr2[1] = this.mRight;
        constraintAnchorArr2[3] = this.mBottom;
        int i12 = 0;
        while (true) {
            constraintAnchorArr = this.mListAnchors;
            if (i12 >= constraintAnchorArr.length) {
                break;
            }
            ConstraintAnchor constraintAnchor = constraintAnchorArr[i12];
            constraintAnchor.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor);
            i12++;
        }
        int i13 = this.mBarrierType;
        if (i13 >= 0 && i13 < 4) {
            ConstraintAnchor constraintAnchor2 = constraintAnchorArr[i13];
            if (!this.resolved) {
                allSolved();
            }
            if (this.resolved) {
                this.resolved = false;
                int i14 = this.mBarrierType;
                if (i14 == 0 || i14 == 1) {
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mX);
                    linearSystem.addEquality(this.mRight.mSolverVariable, this.mX);
                } else if (i14 == 2 || i14 == 3) {
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mY);
                    linearSystem.addEquality(this.mBottom.mSolverVariable, this.mY);
                }
            } else {
                int i15 = 0;
                while (true) {
                    if (i15 >= this.mWidgetsCount) {
                        z3 = false;
                        break;
                    }
                    ConstraintWidget constraintWidget = this.mWidgets[i15];
                    if ((this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) && ((((i10 = this.mBarrierType) == 0 || i10 == 1) && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mLeft.mTarget != null && constraintWidget.mRight.mTarget != null) || (((i11 = this.mBarrierType) == 2 || i11 == 3) && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null))) {
                        z3 = true;
                    } else {
                        i15++;
                    }
                }
                z3 = true;
                if (this.mLeft.hasCenteredDependents() || this.mRight.hasCenteredDependents()) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                if (this.mTop.hasCenteredDependents() || this.mBottom.hasCenteredDependents()) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                if (z3 || (((i8 = this.mBarrierType) != 0 || !z7) && ((i8 != 2 || !z9) && ((i8 != 1 || !z7) && (i8 != 3 || !z9))))) {
                    z10 = false;
                } else {
                    z10 = true;
                }
                if (!z10) {
                    i2 = 4;
                } else {
                    i2 = 5;
                }
                for (int i16 = 0; i16 < this.mWidgetsCount; i16++) {
                    ConstraintWidget constraintWidget2 = this.mWidgets[i16];
                    if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                        SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintWidget2.mListAnchors[this.mBarrierType]);
                        ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.mListAnchors;
                        int i17 = this.mBarrierType;
                        ConstraintAnchor constraintAnchor3 = constraintAnchorArr3[i17];
                        constraintAnchor3.mSolverVariable = createObjectVariable;
                        ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
                        if (constraintAnchor4 == null || constraintAnchor4.mOwner != this) {
                            i7 = 0;
                        } else {
                            i7 = constraintAnchor3.mMargin;
                        }
                        if (i17 == 0 || i17 == 2) {
                            linearSystem.addLowerBarrier(constraintAnchor2.mSolverVariable, createObjectVariable, this.mMargin - i7, z3);
                        } else {
                            linearSystem.addGreaterBarrier(constraintAnchor2.mSolverVariable, createObjectVariable, this.mMargin + i7, z3);
                        }
                        linearSystem.addEquality(constraintAnchor2.mSolverVariable, createObjectVariable, this.mMargin + i7, i2);
                    }
                }
                int i18 = this.mBarrierType;
                if (i18 == 0) {
                    linearSystem.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 8);
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 4);
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 0);
                } else if (i18 == 1) {
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 8);
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 4);
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 0);
                } else if (i18 == 2) {
                    linearSystem.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 8);
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 4);
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 0);
                } else if (i18 == 3) {
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 8);
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 4);
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 0);
                }
            }
        }
    }

    public boolean allSolved() {
        int i2;
        int i7;
        int i8;
        boolean z = true;
        int i10 = 0;
        while (true) {
            i2 = this.mWidgetsCount;
            if (i10 >= i2) {
                break;
            }
            ConstraintWidget constraintWidget = this.mWidgets[i10];
            if ((this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) && ((((i7 = this.mBarrierType) == 0 || i7 == 1) && !constraintWidget.isResolvedHorizontally()) || (((i8 = this.mBarrierType) == 2 || i8 == 3) && !constraintWidget.isResolvedVertically()))) {
                z = false;
            }
            i10++;
        }
        if (!z || i2 <= 0) {
            return false;
        }
        int i11 = 0;
        boolean z3 = false;
        for (int i12 = 0; i12 < this.mWidgetsCount; i12++) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i12];
            if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                if (!z3) {
                    int i13 = this.mBarrierType;
                    if (i13 == 0) {
                        i11 = constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT).getFinalValue();
                    } else if (i13 == 1) {
                        i11 = constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).getFinalValue();
                    } else if (i13 == 2) {
                        i11 = constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP).getFinalValue();
                    } else if (i13 == 3) {
                        i11 = constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getFinalValue();
                    }
                    z3 = true;
                }
                int i14 = this.mBarrierType;
                if (i14 == 0) {
                    i11 = Math.min(i11, constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT).getFinalValue());
                } else if (i14 == 1) {
                    i11 = Math.max(i11, constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).getFinalValue());
                } else if (i14 == 2) {
                    i11 = Math.min(i11, constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP).getFinalValue());
                } else if (i14 == 3) {
                    i11 = Math.max(i11, constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getFinalValue());
                }
            }
        }
        int i15 = i11 + this.mMargin;
        int i16 = this.mBarrierType;
        if (i16 == 0 || i16 == 1) {
            setFinalHorizontal(i15, i15);
        } else {
            setFinalVertical(i15, i15);
        }
        this.resolved = true;
        return true;
    }

    public boolean allowedInBarrier() {
        return true;
    }

    public boolean allowsGoneWidget() {
        return this.mAllowsGoneWidget;
    }

    public int getBarrierType() {
        return this.mBarrierType;
    }

    public int getMargin() {
        return this.mMargin;
    }

    public int getOrientation() {
        int i2 = this.mBarrierType;
        if (i2 == 0 || i2 == 1) {
            return 0;
        }
        if (i2 == 2 || i2 == 3) {
            return 1;
        }
        return -1;
    }

    public boolean isResolvedHorizontally() {
        return this.resolved;
    }

    public boolean isResolvedVertically() {
        return this.resolved;
    }

    public void markWidgets() {
        for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
            ConstraintWidget constraintWidget = this.mWidgets[i2];
            int i7 = this.mBarrierType;
            if (i7 == 0 || i7 == 1) {
                constraintWidget.setInBarrier(0, true);
            } else if (i7 == 2 || i7 == 3) {
                constraintWidget.setInBarrier(1, true);
            }
        }
    }

    public void setAllowsGoneWidget(boolean z) {
        this.mAllowsGoneWidget = z;
    }

    public void setBarrierType(int i2) {
        this.mBarrierType = i2;
    }

    public void setMargin(int i2) {
        this.mMargin = i2;
    }

    public String toString() {
        String str = "[Barrier] " + getDebugName() + " {";
        for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
            ConstraintWidget constraintWidget = this.mWidgets[i2];
            if (i2 > 0) {
                str = C0212a.A(str, ArcCommonLog.TAG_COMMA);
            }
            StringBuilder s = C0212a.s(str);
            s.append(constraintWidget.getDebugName());
            str = s.toString();
        }
        return C0212a.A(str, "}");
    }
}
