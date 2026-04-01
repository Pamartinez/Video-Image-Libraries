package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.ArrayRow;
import i.C0212a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SolverVariableValues implements ArrayRow.ArrayRowVariables {
    private static float epsilon = 0.001f;
    private int HASH_SIZE = 16;
    private final int NONE = -1;
    private int SIZE = 16;
    int head = -1;
    int[] keys = new int[16];
    protected final Cache mCache;
    int mCount = 0;
    private final ArrayRow mRow;
    int[] next = new int[16];
    int[] nextKeys = new int[16];
    int[] previous = new int[16];
    float[] values = new float[16];
    int[] variables = new int[16];

    public SolverVariableValues(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.mCache = cache;
        clear();
    }

    private void addToHashMap(SolverVariable solverVariable, int i2) {
        int[] iArr;
        int i7 = solverVariable.id % this.HASH_SIZE;
        int[] iArr2 = this.keys;
        int i8 = iArr2[i7];
        if (i8 == -1) {
            iArr2[i7] = i2;
        } else {
            while (true) {
                iArr = this.nextKeys;
                int i10 = iArr[i8];
                if (i10 == -1) {
                    break;
                }
                i8 = i10;
            }
            iArr[i8] = i2;
        }
        this.nextKeys[i2] = -1;
    }

    private void addVariable(int i2, SolverVariable solverVariable, float f) {
        this.variables[i2] = solverVariable.id;
        this.values[i2] = f;
        this.previous[i2] = -1;
        this.next[i2] = -1;
        solverVariable.addToRow(this.mRow);
        solverVariable.usageInRowCount++;
        this.mCount++;
    }

    private int findEmptySlot() {
        for (int i2 = 0; i2 < this.SIZE; i2++) {
            if (this.variables[i2] == -1) {
                return i2;
            }
        }
        return -1;
    }

    private void increaseSize() {
        int i2 = this.SIZE * 2;
        this.variables = Arrays.copyOf(this.variables, i2);
        this.values = Arrays.copyOf(this.values, i2);
        this.previous = Arrays.copyOf(this.previous, i2);
        this.next = Arrays.copyOf(this.next, i2);
        this.nextKeys = Arrays.copyOf(this.nextKeys, i2);
        for (int i7 = this.SIZE; i7 < i2; i7++) {
            this.variables[i7] = -1;
            this.nextKeys[i7] = -1;
        }
        this.SIZE = i2;
    }

    private void insertVariable(int i2, SolverVariable solverVariable, float f) {
        int findEmptySlot = findEmptySlot();
        addVariable(findEmptySlot, solverVariable, f);
        if (i2 != -1) {
            this.previous[findEmptySlot] = i2;
            int[] iArr = this.next;
            iArr[findEmptySlot] = iArr[i2];
            iArr[i2] = findEmptySlot;
        } else {
            this.previous[findEmptySlot] = -1;
            if (this.mCount > 0) {
                this.next[findEmptySlot] = this.head;
                this.head = findEmptySlot;
            } else {
                this.next[findEmptySlot] = -1;
            }
        }
        int i7 = this.next[findEmptySlot];
        if (i7 != -1) {
            this.previous[i7] = findEmptySlot;
        }
        addToHashMap(solverVariable, findEmptySlot);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void removeFromHashMap(androidx.constraintlayout.solver.SolverVariable r6) {
        /*
            r5 = this;
            int r6 = r6.id
            int r0 = r5.HASH_SIZE
            int r0 = r6 % r0
            int[] r1 = r5.keys
            r2 = r1[r0]
            r3 = -1
            if (r2 != r3) goto L_0x000e
            goto L_0x0039
        L_0x000e:
            int[] r4 = r5.variables
            r4 = r4[r2]
            if (r4 != r6) goto L_0x001d
            int[] r5 = r5.nextKeys
            r6 = r5[r2]
            r1[r0] = r6
            r5[r2] = r3
            return
        L_0x001d:
            int[] r0 = r5.nextKeys
            r1 = r0[r2]
            if (r1 == r3) goto L_0x002b
            int[] r4 = r5.variables
            r4 = r4[r1]
            if (r4 == r6) goto L_0x002b
            r2 = r1
            goto L_0x001d
        L_0x002b:
            if (r1 == r3) goto L_0x0039
            int[] r5 = r5.variables
            r5 = r5[r1]
            if (r5 != r6) goto L_0x0039
            r5 = r0[r1]
            r0[r2] = r5
            r0[r1] = r3
        L_0x0039:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.SolverVariableValues.removeFromHashMap(androidx.constraintlayout.solver.SolverVariable):void");
    }

    public void add(SolverVariable solverVariable, float f, boolean z) {
        float f5 = epsilon;
        if (f <= (-f5) || f >= f5) {
            int indexOf = indexOf(solverVariable);
            if (indexOf == -1) {
                put(solverVariable, f);
                return;
            }
            float[] fArr = this.values;
            float f8 = fArr[indexOf] + f;
            fArr[indexOf] = f8;
            float f10 = epsilon;
            if (f8 > (-f10) && f8 < f10) {
                fArr[indexOf] = 0.0f;
                remove(solverVariable, z);
            }
        }
    }

    public void clear() {
        int i2 = this.mCount;
        for (int i7 = 0; i7 < i2; i7++) {
            SolverVariable variable = getVariable(i7);
            if (variable != null) {
                variable.removeFromRow(this.mRow);
            }
        }
        for (int i8 = 0; i8 < this.SIZE; i8++) {
            this.variables[i8] = -1;
            this.nextKeys[i8] = -1;
        }
        for (int i10 = 0; i10 < this.HASH_SIZE; i10++) {
            this.keys[i10] = -1;
        }
        this.mCount = 0;
        this.head = -1;
    }

    public boolean contains(SolverVariable solverVariable) {
        if (indexOf(solverVariable) != -1) {
            return true;
        }
        return false;
    }

    public void divideByAmount(float f) {
        int i2 = this.mCount;
        int i7 = this.head;
        int i8 = 0;
        while (i8 < i2) {
            float[] fArr = this.values;
            fArr[i7] = fArr[i7] / f;
            i7 = this.next[i7];
            if (i7 != -1) {
                i8++;
            } else {
                return;
            }
        }
    }

    public float get(SolverVariable solverVariable) {
        int indexOf = indexOf(solverVariable);
        if (indexOf != -1) {
            return this.values[indexOf];
        }
        return 0.0f;
    }

    public int getCurrentSize() {
        return this.mCount;
    }

    public SolverVariable getVariable(int i2) {
        int i7 = this.mCount;
        if (i7 == 0) {
            return null;
        }
        int i8 = this.head;
        for (int i10 = 0; i10 < i7; i10++) {
            if (i10 == i2 && i8 != -1) {
                return this.mCache.mIndexedVariables[this.variables[i8]];
            }
            i8 = this.next[i8];
            if (i8 == -1) {
                break;
            }
        }
        return null;
    }

    public float getVariableValue(int i2) {
        int i7 = this.mCount;
        int i8 = this.head;
        for (int i10 = 0; i10 < i7; i10++) {
            if (i10 == i2) {
                return this.values[i8];
            }
            i8 = this.next[i8];
            if (i8 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0032 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int indexOf(androidx.constraintlayout.solver.SolverVariable r4) {
        /*
            r3 = this;
            int r0 = r3.mCount
            r1 = -1
            if (r0 == 0) goto L_0x0033
            if (r4 != 0) goto L_0x0008
            goto L_0x0033
        L_0x0008:
            int r4 = r4.id
            int r0 = r3.HASH_SIZE
            int r0 = r4 % r0
            int[] r2 = r3.keys
            r0 = r2[r0]
            if (r0 != r1) goto L_0x0015
            return r1
        L_0x0015:
            int[] r2 = r3.variables
            r2 = r2[r0]
            if (r2 != r4) goto L_0x001c
            return r0
        L_0x001c:
            int[] r2 = r3.nextKeys
            r0 = r2[r0]
            if (r0 == r1) goto L_0x0029
            int[] r2 = r3.variables
            r2 = r2[r0]
            if (r2 == r4) goto L_0x0029
            goto L_0x001c
        L_0x0029:
            if (r0 != r1) goto L_0x002c
            return r1
        L_0x002c:
            int[] r3 = r3.variables
            r3 = r3[r0]
            if (r3 != r4) goto L_0x0033
            return r0
        L_0x0033:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.SolverVariableValues.indexOf(androidx.constraintlayout.solver.SolverVariable):int");
    }

    public void invert() {
        int i2 = this.mCount;
        int i7 = this.head;
        int i8 = 0;
        while (i8 < i2) {
            float[] fArr = this.values;
            fArr[i7] = fArr[i7] * -1.0f;
            i7 = this.next[i7];
            if (i7 != -1) {
                i8++;
            } else {
                return;
            }
        }
    }

    public void put(SolverVariable solverVariable, float f) {
        float f5 = epsilon;
        if (f <= (-f5) || f >= f5) {
            if (this.mCount == 0) {
                addVariable(0, solverVariable, f);
                addToHashMap(solverVariable, 0);
                this.head = 0;
                return;
            }
            int indexOf = indexOf(solverVariable);
            if (indexOf != -1) {
                this.values[indexOf] = f;
                return;
            }
            if (this.mCount + 1 >= this.SIZE) {
                increaseSize();
            }
            int i2 = this.mCount;
            int i7 = this.head;
            int i8 = -1;
            for (int i10 = 0; i10 < i2; i10++) {
                int i11 = this.variables[i7];
                int i12 = solverVariable.id;
                if (i11 == i12) {
                    this.values[i7] = f;
                    return;
                }
                if (i11 < i12) {
                    i8 = i7;
                }
                i7 = this.next[i7];
                if (i7 == -1) {
                    break;
                }
            }
            insertVariable(i8, solverVariable, f);
            return;
        }
        remove(solverVariable, true);
    }

    public float remove(SolverVariable solverVariable, boolean z) {
        int indexOf = indexOf(solverVariable);
        if (indexOf == -1) {
            return 0.0f;
        }
        removeFromHashMap(solverVariable);
        float f = this.values[indexOf];
        if (this.head == indexOf) {
            this.head = this.next[indexOf];
        }
        this.variables[indexOf] = -1;
        int[] iArr = this.previous;
        int i2 = iArr[indexOf];
        if (i2 != -1) {
            int[] iArr2 = this.next;
            iArr2[i2] = iArr2[indexOf];
        }
        int i7 = this.next[indexOf];
        if (i7 != -1) {
            iArr[i7] = iArr[indexOf];
        }
        this.mCount--;
        solverVariable.usageInRowCount--;
        if (z) {
            solverVariable.removeFromRow(this.mRow);
        }
        return f;
    }

    public String toString() {
        String str;
        String str2;
        String str3 = hashCode() + " { ";
        int i2 = this.mCount;
        for (int i7 = 0; i7 < i2; i7++) {
            SolverVariable variable = getVariable(i7);
            if (variable != null) {
                String str4 = str3 + variable + " = " + getVariableValue(i7) + " ";
                int indexOf = indexOf(variable);
                String A10 = C0212a.A(str4, "[p: ");
                if (this.previous[indexOf] != -1) {
                    StringBuilder s = C0212a.s(A10);
                    s.append(this.mCache.mIndexedVariables[this.variables[this.previous[indexOf]]]);
                    str = s.toString();
                } else {
                    str = C0212a.A(A10, "none");
                }
                String A11 = C0212a.A(str, ", n: ");
                if (this.next[indexOf] != -1) {
                    StringBuilder s5 = C0212a.s(A11);
                    s5.append(this.mCache.mIndexedVariables[this.variables[this.next[indexOf]]]);
                    str2 = s5.toString();
                } else {
                    str2 = C0212a.A(A11, "none");
                }
                str3 = C0212a.A(str2, "]");
            }
        }
        return C0212a.A(str3, " }");
    }

    public float use(ArrayRow arrayRow, boolean z) {
        float f = get(arrayRow.variable);
        remove(arrayRow.variable, z);
        SolverVariableValues solverVariableValues = (SolverVariableValues) arrayRow.variables;
        int currentSize = solverVariableValues.getCurrentSize();
        int i2 = 0;
        int i7 = 0;
        while (i2 < currentSize) {
            int i8 = solverVariableValues.variables[i7];
            if (i8 != -1) {
                add(this.mCache.mIndexedVariables[i8], solverVariableValues.values[i7] * f, z);
                i2++;
            }
            i7++;
        }
        return f;
    }
}
