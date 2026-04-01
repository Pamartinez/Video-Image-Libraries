package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ArrayRow implements LinearSystem.Row {
    float constantValue = 0.0f;
    boolean isSimpleDefinition = false;
    boolean used = false;
    SolverVariable variable = null;
    public ArrayRowVariables variables;
    ArrayList<SolverVariable> variablesToUpdate = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ArrayRowVariables {
        void add(SolverVariable solverVariable, float f, boolean z);

        void clear();

        boolean contains(SolverVariable solverVariable);

        void divideByAmount(float f);

        float get(SolverVariable solverVariable);

        int getCurrentSize();

        SolverVariable getVariable(int i2);

        float getVariableValue(int i2);

        void invert();

        void put(SolverVariable solverVariable, float f);

        float remove(SolverVariable solverVariable, boolean z);

        float use(ArrayRow arrayRow, boolean z);
    }

    public ArrayRow() {
    }

    private boolean isNew(SolverVariable solverVariable, LinearSystem linearSystem) {
        if (solverVariable.usageInRowCount <= 1) {
            return true;
        }
        return false;
    }

    private SolverVariable pickPivotInVariables(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int currentSize = this.variables.getCurrentSize();
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        for (int i2 = 0; i2 < currentSize; i2++) {
            float variableValue = this.variables.getVariableValue(i2);
            if (variableValue < 0.0f) {
                SolverVariable variable2 = this.variables.getVariable(i2);
                if ((zArr == null || !zArr[variable2.id]) && variable2 != solverVariable && (((type = variable2.mType) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && variableValue < f)) {
                    f = variableValue;
                    solverVariable2 = variable2;
                }
            }
        }
        return solverVariable2;
    }

    public ArrayRow addError(LinearSystem linearSystem, int i2) {
        this.variables.put(linearSystem.createErrorVariable(i2, "ep"), 1.0f);
        this.variables.put(linearSystem.createErrorVariable(i2, "em"), -1.0f);
        return this;
    }

    public ArrayRow addSingleError(SolverVariable solverVariable, int i2) {
        this.variables.put(solverVariable, (float) i2);
        return this;
    }

    public boolean chooseSubject(LinearSystem linearSystem) {
        boolean z;
        SolverVariable chooseSubjectInVariables = chooseSubjectInVariables(linearSystem);
        if (chooseSubjectInVariables == null) {
            z = true;
        } else {
            pivot(chooseSubjectInVariables);
            z = false;
        }
        if (this.variables.getCurrentSize() == 0) {
            this.isSimpleDefinition = true;
        }
        return z;
    }

    public SolverVariable chooseSubjectInVariables(LinearSystem linearSystem) {
        boolean isNew;
        boolean isNew2;
        int currentSize = this.variables.getCurrentSize();
        SolverVariable solverVariable = null;
        float f = 0.0f;
        float f5 = 0.0f;
        boolean z = false;
        boolean z3 = false;
        SolverVariable solverVariable2 = null;
        for (int i2 = 0; i2 < currentSize; i2++) {
            float variableValue = this.variables.getVariableValue(i2);
            SolverVariable variable2 = this.variables.getVariable(i2);
            if (variable2.mType == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null) {
                    isNew2 = isNew(variable2, linearSystem);
                } else if (f > variableValue) {
                    isNew2 = isNew(variable2, linearSystem);
                } else if (!z && isNew(variable2, linearSystem)) {
                    f = variableValue;
                    solverVariable = variable2;
                    z = true;
                }
                z = isNew2;
                f = variableValue;
                solverVariable = variable2;
            } else if (solverVariable == null && variableValue < 0.0f) {
                if (solverVariable2 == null) {
                    isNew = isNew(variable2, linearSystem);
                } else if (f5 > variableValue) {
                    isNew = isNew(variable2, linearSystem);
                } else if (!z3 && isNew(variable2, linearSystem)) {
                    f5 = variableValue;
                    solverVariable2 = variable2;
                    z3 = true;
                }
                z3 = isNew;
                f5 = variableValue;
                solverVariable2 = variable2;
            }
        }
        if (solverVariable != null) {
            return solverVariable;
        }
        return solverVariable2;
    }

    public void clear() {
        this.variables.clear();
        this.variable = null;
        this.constantValue = 0.0f;
    }

    public ArrayRow createRowCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i7) {
        if (solverVariable2 == solverVariable3) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable2, -2.0f);
            return this;
        }
        if (f == 0.5f) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            if (i2 > 0 || i7 > 0) {
                this.constantValue = (float) ((-i2) + i7);
                return this;
            }
        } else if (f <= 0.0f) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.constantValue = (float) i2;
            return this;
        } else if (f >= 1.0f) {
            this.variables.put(solverVariable4, -1.0f);
            this.variables.put(solverVariable3, 1.0f);
            this.constantValue = (float) (-i7);
            return this;
        } else {
            float f5 = 1.0f - f;
            this.variables.put(solverVariable, f5 * 1.0f);
            this.variables.put(solverVariable2, f5 * -1.0f);
            this.variables.put(solverVariable3, -1.0f * f);
            this.variables.put(solverVariable4, 1.0f * f);
            if (i2 > 0 || i7 > 0) {
                this.constantValue = (((float) i7) * f) + (((float) (-i2)) * f5);
                return this;
            }
        }
        return this;
    }

    public ArrayRow createRowDefinition(SolverVariable solverVariable, int i2) {
        this.variable = solverVariable;
        float f = (float) i2;
        solverVariable.computedValue = f;
        this.constantValue = f;
        this.isSimpleDefinition = true;
        return this;
    }

    public ArrayRow createRowDimensionPercent(SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, f);
        return this;
    }

    public ArrayRow createRowDimensionRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, 1.0f);
        this.variables.put(solverVariable3, f);
        this.variables.put(solverVariable4, -f);
        return this;
    }

    public ArrayRow createRowEqualMatchDimensions(float f, float f5, float f8, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.constantValue = 0.0f;
        if (f5 == 0.0f || f == f8) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
            return this;
        } else if (f == 0.0f) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            return this;
        } else if (f8 == 0.0f) {
            this.variables.put(solverVariable3, 1.0f);
            this.variables.put(solverVariable4, -1.0f);
            return this;
        } else {
            float f10 = (f / f5) / (f8 / f5);
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, f10);
            this.variables.put(solverVariable3, -f10);
            return this;
        }
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, int i2) {
        if (i2 < 0) {
            this.constantValue = (float) (i2 * -1);
            this.variables.put(solverVariable, 1.0f);
            return this;
        }
        this.constantValue = (float) i2;
        this.variables.put(solverVariable, -1.0f);
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.constantValue = (float) i2;
        }
        if (!z) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.variables.put(solverVariable3, 1.0f);
            return this;
        }
        this.variables.put(solverVariable, 1.0f);
        this.variables.put(solverVariable2, -1.0f);
        this.variables.put(solverVariable3, -1.0f);
        return this;
    }

    public ArrayRow createRowLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.constantValue = (float) i2;
        }
        if (!z) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
            return this;
        }
        this.variables.put(solverVariable, 1.0f);
        this.variables.put(solverVariable2, -1.0f);
        this.variables.put(solverVariable3, 1.0f);
        return this;
    }

    public ArrayRow createRowWithAngle(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.variables.put(solverVariable3, 0.5f);
        this.variables.put(solverVariable4, 0.5f);
        this.variables.put(solverVariable, -0.5f);
        this.variables.put(solverVariable2, -0.5f);
        this.constantValue = -f;
        return this;
    }

    public void ensurePositiveConstant() {
        float f = this.constantValue;
        if (f < 0.0f) {
            this.constantValue = f * -1.0f;
            this.variables.invert();
        }
    }

    public SolverVariable getKey() {
        return this.variable;
    }

    public SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr) {
        return pickPivotInVariables(zArr, (SolverVariable) null);
    }

    public boolean hasKeyVariable() {
        SolverVariable solverVariable = this.variable;
        if (solverVariable == null) {
            return false;
        }
        if (solverVariable.mType == SolverVariable.Type.UNRESTRICTED || this.constantValue >= 0.0f) {
            return true;
        }
        return false;
    }

    public boolean hasVariable(SolverVariable solverVariable) {
        return this.variables.contains(solverVariable);
    }

    public void initFromRow(LinearSystem.Row row) {
        if (row instanceof ArrayRow) {
            ArrayRow arrayRow = (ArrayRow) row;
            this.variable = null;
            this.variables.clear();
            for (int i2 = 0; i2 < arrayRow.variables.getCurrentSize(); i2++) {
                this.variables.add(arrayRow.variables.getVariable(i2), arrayRow.variables.getVariableValue(i2), true);
            }
        }
    }

    public boolean isEmpty() {
        if (this.variable == null && this.constantValue == 0.0f && this.variables.getCurrentSize() == 0) {
            return true;
        }
        return false;
    }

    public SolverVariable pickPivot(SolverVariable solverVariable) {
        return pickPivotInVariables((boolean[]) null, solverVariable);
    }

    public void pivot(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.variable;
        if (solverVariable2 != null) {
            this.variables.put(solverVariable2, -1.0f);
            this.variable.definitionId = -1;
            this.variable = null;
        }
        float remove = this.variables.remove(solverVariable, true) * -1.0f;
        this.variable = solverVariable;
        if (remove != 1.0f) {
            this.constantValue /= remove;
            this.variables.divideByAmount(remove);
        }
    }

    public void reset() {
        this.variable = null;
        this.variables.clear();
        this.constantValue = 0.0f;
        this.isSimpleDefinition = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0047, code lost:
        r7 = r10.variables.getVariableValue(r3);
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0081  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toReadableString() {
        /*
            r10 = this;
            androidx.constraintlayout.solver.SolverVariable r0 = r10.variable
            if (r0 != 0) goto L_0x0007
            java.lang.String r0 = "0"
            goto L_0x0017
        L_0x0007:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ""
            r0.<init>(r1)
            androidx.constraintlayout.solver.SolverVariable r1 = r10.variable
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x0017:
            java.lang.String r1 = " = "
            java.lang.String r0 = i.C0212a.A(r0, r1)
            float r1 = r10.constantValue
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0035
            java.lang.StringBuilder r0 = i.C0212a.s(r0)
            float r1 = r10.constantValue
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = r4
            goto L_0x0036
        L_0x0035:
            r1 = r3
        L_0x0036:
            androidx.constraintlayout.solver.ArrayRow$ArrayRowVariables r5 = r10.variables
            int r5 = r5.getCurrentSize()
        L_0x003c:
            if (r3 >= r5) goto L_0x009c
            androidx.constraintlayout.solver.ArrayRow$ArrayRowVariables r6 = r10.variables
            androidx.constraintlayout.solver.SolverVariable r6 = r6.getVariable(r3)
            if (r6 != 0) goto L_0x0047
            goto L_0x0099
        L_0x0047:
            androidx.constraintlayout.solver.ArrayRow$ArrayRowVariables r7 = r10.variables
            float r7 = r7.getVariableValue(r3)
            int r8 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r8 != 0) goto L_0x0052
            goto L_0x0099
        L_0x0052:
            java.lang.String r6 = r6.toString()
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 != 0) goto L_0x0066
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 >= 0) goto L_0x0076
            java.lang.String r1 = "- "
            java.lang.String r0 = i.C0212a.A(r0, r1)
        L_0x0064:
            float r7 = r7 * r9
            goto L_0x0076
        L_0x0066:
            if (r8 <= 0) goto L_0x006f
            java.lang.String r1 = " + "
            java.lang.String r0 = i.C0212a.A(r0, r1)
            goto L_0x0076
        L_0x006f:
            java.lang.String r1 = " - "
            java.lang.String r0 = i.C0212a.A(r0, r1)
            goto L_0x0064
        L_0x0076:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x0081
            java.lang.String r0 = i.C0212a.A(r0, r6)
            goto L_0x0098
        L_0x0081:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = " "
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
        L_0x0098:
            r1 = r4
        L_0x0099:
            int r3 = r3 + 1
            goto L_0x003c
        L_0x009c:
            if (r1 != 0) goto L_0x00a5
            java.lang.String r10 = "0.0"
            java.lang.String r10 = i.C0212a.A(r0, r10)
            return r10
        L_0x00a5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.ArrayRow.toReadableString():java.lang.String");
    }

    public String toString() {
        return toReadableString();
    }

    public void updateFromFinalVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z) {
        if (solverVariable.isFinalValue) {
            float f = this.variables.get(solverVariable);
            this.constantValue = (solverVariable.computedValue * f) + this.constantValue;
            this.variables.remove(solverVariable, z);
            if (z) {
                solverVariable.removeFromRow(this);
            }
            if (LinearSystem.SIMPLIFY_SYNONYMS && this.variables.getCurrentSize() == 0) {
                this.isSimpleDefinition = true;
                linearSystem.hasSimpleDefinition = true;
            }
        }
    }

    public void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z) {
        float use = this.variables.use(arrayRow, z);
        this.constantValue = (arrayRow.constantValue * use) + this.constantValue;
        if (z) {
            arrayRow.variable.removeFromRow(this);
        }
        if (LinearSystem.SIMPLIFY_SYNONYMS && this.variable != null && this.variables.getCurrentSize() == 0) {
            this.isSimpleDefinition = true;
            linearSystem.hasSimpleDefinition = true;
        }
    }

    public void updateFromSynonymVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z) {
        if (solverVariable.isSynonym) {
            float f = this.variables.get(solverVariable);
            this.constantValue = (solverVariable.synonymDelta * f) + this.constantValue;
            this.variables.remove(solverVariable, z);
            if (z) {
                solverVariable.removeFromRow(this);
            }
            this.variables.add(linearSystem.mCache.mIndexedVariables[solverVariable.synonym], f, z);
            if (LinearSystem.SIMPLIFY_SYNONYMS && this.variables.getCurrentSize() == 0) {
                this.isSimpleDefinition = true;
                linearSystem.hasSimpleDefinition = true;
            }
        }
    }

    public void updateFromSystem(LinearSystem linearSystem) {
        if (linearSystem.mRows.length != 0) {
            boolean z = false;
            while (!z) {
                int currentSize = this.variables.getCurrentSize();
                for (int i2 = 0; i2 < currentSize; i2++) {
                    SolverVariable variable2 = this.variables.getVariable(i2);
                    if (variable2.definitionId != -1 || variable2.isFinalValue || variable2.isSynonym) {
                        this.variablesToUpdate.add(variable2);
                    }
                }
                int size = this.variablesToUpdate.size();
                if (size > 0) {
                    for (int i7 = 0; i7 < size; i7++) {
                        SolverVariable solverVariable = this.variablesToUpdate.get(i7);
                        if (solverVariable.isFinalValue) {
                            updateFromFinalVariable(linearSystem, solverVariable, true);
                        } else if (solverVariable.isSynonym) {
                            updateFromSynonymVariable(linearSystem, solverVariable, true);
                        } else {
                            updateFromRow(linearSystem, linearSystem.mRows[solverVariable.definitionId], true);
                        }
                    }
                    this.variablesToUpdate.clear();
                } else {
                    z = true;
                }
            }
            if (LinearSystem.SIMPLIFY_SYNONYMS && this.variable != null && this.variables.getCurrentSize() == 0) {
                this.isSimpleDefinition = true;
                linearSystem.hasSimpleDefinition = true;
            }
        }
    }

    public void addError(SolverVariable solverVariable) {
        int i2 = solverVariable.strength;
        float f = 1.0f;
        if (i2 != 1) {
            if (i2 == 2) {
                f = 1000.0f;
            } else if (i2 == 3) {
                f = 1000000.0f;
            } else if (i2 == 4) {
                f = 1.0E9f;
            } else if (i2 == 5) {
                f = 1.0E12f;
            }
        }
        this.variables.put(solverVariable, f);
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, SolverVariable solverVariable2, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.constantValue = (float) i2;
        }
        if (!z) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            return this;
        }
        this.variables.put(solverVariable, 1.0f);
        this.variables.put(solverVariable2, -1.0f);
        return this;
    }

    public ArrayRow(Cache cache) {
        this.variables = new ArrayLinkedVariables(this, cache);
    }
}
