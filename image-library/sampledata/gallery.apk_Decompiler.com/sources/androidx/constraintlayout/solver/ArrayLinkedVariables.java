package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.ArrayRow;
import i.C0212a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ArrayLinkedVariables implements ArrayRow.ArrayRowVariables {
    private static float epsilon = 0.001f;
    private int ROW_SIZE = 8;
    private SolverVariable candidate = null;
    int currentSize = 0;
    private int[] mArrayIndices = new int[8];
    private int[] mArrayNextIndices = new int[8];
    private float[] mArrayValues = new float[8];
    protected final Cache mCache;
    private boolean mDidFillOnce = false;
    private int mHead = -1;
    private int mLast = -1;
    private final ArrayRow mRow;

    public ArrayLinkedVariables(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.mCache = cache;
    }

    public void add(SolverVariable solverVariable, float f, boolean z) {
        float f5 = epsilon;
        if (f <= (-f5) || f >= f5) {
            int i2 = this.mHead;
            if (i2 == -1) {
                this.mHead = 0;
                this.mArrayValues[0] = f;
                this.mArrayIndices[0] = solverVariable.id;
                this.mArrayNextIndices[0] = -1;
                solverVariable.usageInRowCount++;
                solverVariable.addToRow(this.mRow);
                this.currentSize++;
                if (!this.mDidFillOnce) {
                    int i7 = this.mLast + 1;
                    this.mLast = i7;
                    int[] iArr = this.mArrayIndices;
                    if (i7 >= iArr.length) {
                        this.mDidFillOnce = true;
                        this.mLast = iArr.length - 1;
                        return;
                    }
                    return;
                }
                return;
            }
            int i8 = 0;
            int i10 = -1;
            while (i2 != -1 && i8 < this.currentSize) {
                int i11 = this.mArrayIndices[i2];
                int i12 = solverVariable.id;
                if (i11 == i12) {
                    float[] fArr = this.mArrayValues;
                    float f8 = fArr[i2] + f;
                    float f10 = epsilon;
                    if (f8 > (-f10) && f8 < f10) {
                        f8 = 0.0f;
                    }
                    fArr[i2] = f8;
                    if (f8 == 0.0f) {
                        if (i2 == this.mHead) {
                            this.mHead = this.mArrayNextIndices[i2];
                        } else {
                            int[] iArr2 = this.mArrayNextIndices;
                            iArr2[i10] = iArr2[i2];
                        }
                        if (z) {
                            solverVariable.removeFromRow(this.mRow);
                        }
                        if (this.mDidFillOnce) {
                            this.mLast = i2;
                        }
                        solverVariable.usageInRowCount--;
                        this.currentSize--;
                        return;
                    }
                    return;
                }
                if (i11 < i12) {
                    i10 = i2;
                }
                i2 = this.mArrayNextIndices[i2];
                i8++;
            }
            int i13 = this.mLast;
            int i14 = i13 + 1;
            if (this.mDidFillOnce) {
                int[] iArr3 = this.mArrayIndices;
                if (iArr3[i13] != -1) {
                    i13 = iArr3.length;
                }
            } else {
                i13 = i14;
            }
            int[] iArr4 = this.mArrayIndices;
            if (i13 >= iArr4.length && this.currentSize < iArr4.length) {
                int i15 = 0;
                while (true) {
                    int[] iArr5 = this.mArrayIndices;
                    if (i15 >= iArr5.length) {
                        break;
                    } else if (iArr5[i15] == -1) {
                        i13 = i15;
                        break;
                    } else {
                        i15++;
                    }
                }
            }
            int[] iArr6 = this.mArrayIndices;
            if (i13 >= iArr6.length) {
                i13 = iArr6.length;
                int i16 = this.ROW_SIZE * 2;
                this.ROW_SIZE = i16;
                this.mDidFillOnce = false;
                this.mLast = i13 - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, i16);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            }
            this.mArrayIndices[i13] = solverVariable.id;
            this.mArrayValues[i13] = f;
            if (i10 != -1) {
                int[] iArr7 = this.mArrayNextIndices;
                iArr7[i13] = iArr7[i10];
                iArr7[i10] = i13;
            } else {
                this.mArrayNextIndices[i13] = this.mHead;
                this.mHead = i13;
            }
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
            }
            int i17 = this.mLast;
            int[] iArr8 = this.mArrayIndices;
            if (i17 >= iArr8.length) {
                this.mDidFillOnce = true;
                this.mLast = iArr8.length - 1;
            }
        }
    }

    public final void clear() {
        int i2 = this.mHead;
        int i7 = 0;
        while (i2 != -1 && i7 < this.currentSize) {
            SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i2]];
            if (solverVariable != null) {
                solverVariable.removeFromRow(this.mRow);
            }
            i2 = this.mArrayNextIndices[i2];
            i7++;
        }
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.currentSize = 0;
    }

    public boolean contains(SolverVariable solverVariable) {
        int i2 = this.mHead;
        if (i2 == -1) {
            return false;
        }
        int i7 = 0;
        while (i2 != -1 && i7 < this.currentSize) {
            if (this.mArrayIndices[i2] == solverVariable.id) {
                return true;
            }
            i2 = this.mArrayNextIndices[i2];
            i7++;
        }
        return false;
    }

    public void divideByAmount(float f) {
        int i2 = this.mHead;
        int i7 = 0;
        while (i2 != -1 && i7 < this.currentSize) {
            float[] fArr = this.mArrayValues;
            fArr[i2] = fArr[i2] / f;
            i2 = this.mArrayNextIndices[i2];
            i7++;
        }
    }

    public final float get(SolverVariable solverVariable) {
        int i2 = this.mHead;
        int i7 = 0;
        while (i2 != -1 && i7 < this.currentSize) {
            if (this.mArrayIndices[i2] == solverVariable.id) {
                return this.mArrayValues[i2];
            }
            i2 = this.mArrayNextIndices[i2];
            i7++;
        }
        return 0.0f;
    }

    public int getCurrentSize() {
        return this.currentSize;
    }

    public SolverVariable getVariable(int i2) {
        int i7 = this.mHead;
        int i8 = 0;
        while (i7 != -1 && i8 < this.currentSize) {
            if (i8 == i2) {
                return this.mCache.mIndexedVariables[this.mArrayIndices[i7]];
            }
            i7 = this.mArrayNextIndices[i7];
            i8++;
        }
        return null;
    }

    public float getVariableValue(int i2) {
        int i7 = this.mHead;
        int i8 = 0;
        while (i7 != -1 && i8 < this.currentSize) {
            if (i8 == i2) {
                return this.mArrayValues[i7];
            }
            i7 = this.mArrayNextIndices[i7];
            i8++;
        }
        return 0.0f;
    }

    public void invert() {
        int i2 = this.mHead;
        int i7 = 0;
        while (i2 != -1 && i7 < this.currentSize) {
            float[] fArr = this.mArrayValues;
            fArr[i2] = fArr[i2] * -1.0f;
            i2 = this.mArrayNextIndices[i2];
            i7++;
        }
    }

    public final void put(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            remove(solverVariable, true);
            return;
        }
        int i2 = this.mHead;
        if (i2 == -1) {
            this.mHead = 0;
            this.mArrayValues[0] = f;
            this.mArrayIndices[0] = solverVariable.id;
            this.mArrayNextIndices[0] = -1;
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            this.currentSize++;
            if (!this.mDidFillOnce) {
                int i7 = this.mLast + 1;
                this.mLast = i7;
                int[] iArr = this.mArrayIndices;
                if (i7 >= iArr.length) {
                    this.mDidFillOnce = true;
                    this.mLast = iArr.length - 1;
                    return;
                }
                return;
            }
            return;
        }
        int i8 = 0;
        int i10 = -1;
        while (i2 != -1 && i8 < this.currentSize) {
            int i11 = this.mArrayIndices[i2];
            int i12 = solverVariable.id;
            if (i11 == i12) {
                this.mArrayValues[i2] = f;
                return;
            }
            if (i11 < i12) {
                i10 = i2;
            }
            i2 = this.mArrayNextIndices[i2];
            i8++;
        }
        int i13 = this.mLast;
        int i14 = i13 + 1;
        if (this.mDidFillOnce) {
            int[] iArr2 = this.mArrayIndices;
            if (iArr2[i13] != -1) {
                i13 = iArr2.length;
            }
        } else {
            i13 = i14;
        }
        int[] iArr3 = this.mArrayIndices;
        if (i13 >= iArr3.length && this.currentSize < iArr3.length) {
            int i15 = 0;
            while (true) {
                int[] iArr4 = this.mArrayIndices;
                if (i15 >= iArr4.length) {
                    break;
                } else if (iArr4[i15] == -1) {
                    i13 = i15;
                    break;
                } else {
                    i15++;
                }
            }
        }
        int[] iArr5 = this.mArrayIndices;
        if (i13 >= iArr5.length) {
            i13 = iArr5.length;
            int i16 = this.ROW_SIZE * 2;
            this.ROW_SIZE = i16;
            this.mDidFillOnce = false;
            this.mLast = i13 - 1;
            this.mArrayValues = Arrays.copyOf(this.mArrayValues, i16);
            this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
            this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
        }
        this.mArrayIndices[i13] = solverVariable.id;
        this.mArrayValues[i13] = f;
        if (i10 != -1) {
            int[] iArr6 = this.mArrayNextIndices;
            iArr6[i13] = iArr6[i10];
            iArr6[i10] = i13;
        } else {
            this.mArrayNextIndices[i13] = this.mHead;
            this.mHead = i13;
        }
        solverVariable.usageInRowCount++;
        solverVariable.addToRow(this.mRow);
        int i17 = this.currentSize + 1;
        this.currentSize = i17;
        if (!this.mDidFillOnce) {
            this.mLast++;
        }
        int[] iArr7 = this.mArrayIndices;
        if (i17 >= iArr7.length) {
            this.mDidFillOnce = true;
        }
        if (this.mLast >= iArr7.length) {
            this.mDidFillOnce = true;
            this.mLast = iArr7.length - 1;
        }
    }

    public final float remove(SolverVariable solverVariable, boolean z) {
        if (this.candidate == solverVariable) {
            this.candidate = null;
        }
        int i2 = this.mHead;
        if (i2 == -1) {
            return 0.0f;
        }
        int i7 = 0;
        int i8 = -1;
        while (i2 != -1 && i7 < this.currentSize) {
            if (this.mArrayIndices[i2] == solverVariable.id) {
                if (i2 == this.mHead) {
                    this.mHead = this.mArrayNextIndices[i2];
                } else {
                    int[] iArr = this.mArrayNextIndices;
                    iArr[i8] = iArr[i2];
                }
                if (z) {
                    solverVariable.removeFromRow(this.mRow);
                }
                solverVariable.usageInRowCount--;
                this.currentSize--;
                this.mArrayIndices[i2] = -1;
                if (this.mDidFillOnce) {
                    this.mLast = i2;
                }
                return this.mArrayValues[i2];
            }
            i7++;
            i8 = i2;
            i2 = this.mArrayNextIndices[i2];
        }
        return 0.0f;
    }

    public String toString() {
        int i2 = this.mHead;
        String str = "";
        int i7 = 0;
        while (i2 != -1 && i7 < this.currentSize) {
            StringBuilder s = C0212a.s(C0212a.A(str, " -> "));
            s.append(this.mArrayValues[i2]);
            s.append(" : ");
            StringBuilder s5 = C0212a.s(s.toString());
            s5.append(this.mCache.mIndexedVariables[this.mArrayIndices[i2]]);
            str = s5.toString();
            i2 = this.mArrayNextIndices[i2];
            i7++;
        }
        return str;
    }

    public float use(ArrayRow arrayRow, boolean z) {
        float f = get(arrayRow.variable);
        remove(arrayRow.variable, z);
        ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.variables;
        int currentSize2 = arrayRowVariables.getCurrentSize();
        for (int i2 = 0; i2 < currentSize2; i2++) {
            SolverVariable variable = arrayRowVariables.getVariable(i2);
            add(variable, arrayRowVariables.get(variable) * f, z);
        }
        return f;
    }
}
