package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.ArrayRow;
import i.C0212a;
import java.util.Arrays;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PriorityGoalRow extends ArrayRow {
    private int TABLE_SIZE = 128;
    GoalVariableAccessor accessor = new GoalVariableAccessor(this);
    private SolverVariable[] arrayGoals = new SolverVariable[128];
    Cache mCache;
    private int numGoals = 0;
    private SolverVariable[] sortArray = new SolverVariable[128];

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class GoalVariableAccessor implements Comparable {
        PriorityGoalRow row;
        SolverVariable variable;

        public GoalVariableAccessor(PriorityGoalRow priorityGoalRow) {
            this.row = priorityGoalRow;
        }

        public boolean addToGoal(SolverVariable solverVariable, float f) {
            boolean z = true;
            if (this.variable.inGoal) {
                for (int i2 = 0; i2 < 9; i2++) {
                    float[] fArr = this.variable.goalStrengthVector;
                    float f5 = (solverVariable.goalStrengthVector[i2] * f) + fArr[i2];
                    fArr[i2] = f5;
                    if (Math.abs(f5) < 1.0E-4f) {
                        this.variable.goalStrengthVector[i2] = 0.0f;
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    PriorityGoalRow.this.removeGoal(this.variable);
                }
                return false;
            }
            for (int i7 = 0; i7 < 9; i7++) {
                float f8 = solverVariable.goalStrengthVector[i7];
                if (f8 != 0.0f) {
                    float f10 = f8 * f;
                    if (Math.abs(f10) < 1.0E-4f) {
                        f10 = 0.0f;
                    }
                    this.variable.goalStrengthVector[i7] = f10;
                } else {
                    this.variable.goalStrengthVector[i7] = 0.0f;
                }
            }
            return true;
        }

        public int compareTo(Object obj) {
            return this.variable.id - ((SolverVariable) obj).id;
        }

        public void init(SolverVariable solverVariable) {
            this.variable = solverVariable;
        }

        public final boolean isNegative() {
            for (int i2 = 8; i2 >= 0; i2--) {
                float f = this.variable.goalStrengthVector[i2];
                if (f > 0.0f) {
                    return false;
                }
                if (f < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isSmallerThan(SolverVariable solverVariable) {
            int i2 = 8;
            while (true) {
                if (i2 < 0) {
                    break;
                }
                float f = solverVariable.goalStrengthVector[i2];
                float f5 = this.variable.goalStrengthVector[i2];
                if (f5 == f) {
                    i2--;
                } else if (f5 < f) {
                    return true;
                }
            }
            return false;
        }

        public void reset() {
            Arrays.fill(this.variable.goalStrengthVector, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.variable != null) {
                for (int i2 = 0; i2 < 9; i2++) {
                    StringBuilder s = C0212a.s(str);
                    s.append(this.variable.goalStrengthVector[i2]);
                    s.append(" ");
                    str = s.toString();
                }
            }
            StringBuilder t = C0212a.t(str, "] ");
            t.append(this.variable);
            return t.toString();
        }
    }

    public PriorityGoalRow(Cache cache) {
        super(cache);
        this.mCache = cache;
    }

    private final void addToGoal(SolverVariable solverVariable) {
        int i2;
        int i7 = this.numGoals + 1;
        SolverVariable[] solverVariableArr = this.arrayGoals;
        if (i7 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.arrayGoals = solverVariableArr2;
            this.sortArray = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.arrayGoals;
        int i8 = this.numGoals;
        solverVariableArr3[i8] = solverVariable;
        int i10 = i8 + 1;
        this.numGoals = i10;
        if (i10 > 1 && solverVariableArr3[i8].id > solverVariable.id) {
            int i11 = 0;
            while (true) {
                i2 = this.numGoals;
                if (i11 >= i2) {
                    break;
                }
                this.sortArray[i11] = this.arrayGoals[i11];
                i11++;
            }
            Arrays.sort(this.sortArray, 0, i2, new Comparator<SolverVariable>() {
                public int compare(SolverVariable solverVariable, SolverVariable solverVariable2) {
                    return solverVariable.id - solverVariable2.id;
                }
            });
            for (int i12 = 0; i12 < this.numGoals; i12++) {
                this.arrayGoals[i12] = this.sortArray[i12];
            }
        }
        solverVariable.inGoal = true;
        solverVariable.addToRow(this);
    }

    /* access modifiers changed from: private */
    public final void removeGoal(SolverVariable solverVariable) {
        int i2 = 0;
        while (i2 < this.numGoals) {
            if (this.arrayGoals[i2] == solverVariable) {
                while (true) {
                    int i7 = this.numGoals;
                    if (i2 < i7 - 1) {
                        SolverVariable[] solverVariableArr = this.arrayGoals;
                        int i8 = i2 + 1;
                        solverVariableArr[i2] = solverVariableArr[i8];
                        i2 = i8;
                    } else {
                        this.numGoals = i7 - 1;
                        solverVariable.inGoal = false;
                        return;
                    }
                }
            } else {
                i2++;
            }
        }
    }

    public void addError(SolverVariable solverVariable) {
        this.accessor.init(solverVariable);
        this.accessor.reset();
        solverVariable.goalStrengthVector[solverVariable.strength] = 1.0f;
        addToGoal(solverVariable);
    }

    public void clear() {
        this.numGoals = 0;
        this.constantValue = 0.0f;
    }

    public SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr) {
        int i2 = -1;
        for (int i7 = 0; i7 < this.numGoals; i7++) {
            SolverVariable solverVariable = this.arrayGoals[i7];
            if (!zArr[solverVariable.id]) {
                this.accessor.init(solverVariable);
                if (i2 == -1) {
                    if (!this.accessor.isNegative()) {
                    }
                } else if (!this.accessor.isSmallerThan(this.arrayGoals[i2])) {
                }
                i2 = i7;
            }
        }
        if (i2 == -1) {
            return null;
        }
        return this.arrayGoals[i2];
    }

    public boolean isEmpty() {
        if (this.numGoals == 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str = " goal -> (" + this.constantValue + ") : ";
        for (int i2 = 0; i2 < this.numGoals; i2++) {
            this.accessor.init(this.arrayGoals[i2]);
            StringBuilder s = C0212a.s(str);
            s.append(this.accessor);
            s.append(" ");
            str = s.toString();
        }
        return str;
    }

    public void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z) {
        SolverVariable solverVariable = arrayRow.variable;
        if (solverVariable != null) {
            ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.variables;
            int currentSize = arrayRowVariables.getCurrentSize();
            for (int i2 = 0; i2 < currentSize; i2++) {
                SolverVariable variable = arrayRowVariables.getVariable(i2);
                float variableValue = arrayRowVariables.getVariableValue(i2);
                this.accessor.init(variable);
                if (this.accessor.addToGoal(solverVariable, variableValue)) {
                    addToGoal(variable);
                }
                this.constantValue = (arrayRow.constantValue * variableValue) + this.constantValue;
            }
            removeGoal(solverVariable);
        }
    }
}
