package androidx.constraintlayout.solver;

import java.util.Arrays;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SolverVariable {
    private static int uniqueErrorId = 1;
    public float computedValue;
    int definitionId = -1;
    float[] goalStrengthVector = new float[9];
    public int id = -1;
    public boolean inGoal;
    HashSet<ArrayRow> inRows = null;
    public boolean isFinalValue = false;
    boolean isSynonym = false;
    ArrayRow[] mClientEquations = new ArrayRow[16];
    int mClientEquationsCount = 0;
    private String mName;
    Type mType;
    public int strength = 0;
    float[] strengthVector = new float[9];
    int synonym = -1;
    float synonymDelta = 0.0f;
    public int usageInRowCount = 0;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(Type type, String str) {
        this.mType = type;
    }

    public static void increaseErrorId() {
        uniqueErrorId++;
    }

    public final void addToRow(ArrayRow arrayRow) {
        int i2 = 0;
        while (true) {
            int i7 = this.mClientEquationsCount;
            if (i2 >= i7) {
                ArrayRow[] arrayRowArr = this.mClientEquations;
                if (i7 >= arrayRowArr.length) {
                    this.mClientEquations = (ArrayRow[]) Arrays.copyOf(arrayRowArr, arrayRowArr.length * 2);
                }
                ArrayRow[] arrayRowArr2 = this.mClientEquations;
                int i8 = this.mClientEquationsCount;
                arrayRowArr2[i8] = arrayRow;
                this.mClientEquationsCount = i8 + 1;
                return;
            } else if (this.mClientEquations[i2] != arrayRow) {
                i2++;
            } else {
                return;
            }
        }
    }

    public final void removeFromRow(ArrayRow arrayRow) {
        int i2 = this.mClientEquationsCount;
        int i7 = 0;
        while (i7 < i2) {
            if (this.mClientEquations[i7] == arrayRow) {
                while (i7 < i2 - 1) {
                    ArrayRow[] arrayRowArr = this.mClientEquations;
                    int i8 = i7 + 1;
                    arrayRowArr[i7] = arrayRowArr[i8];
                    i7 = i8;
                }
                this.mClientEquationsCount--;
                return;
            }
            i7++;
        }
    }

    public void reset() {
        this.mName = null;
        this.mType = Type.UNKNOWN;
        this.strength = 0;
        this.id = -1;
        this.definitionId = -1;
        this.computedValue = 0.0f;
        this.isFinalValue = false;
        this.isSynonym = false;
        this.synonym = -1;
        this.synonymDelta = 0.0f;
        int i2 = this.mClientEquationsCount;
        for (int i7 = 0; i7 < i2; i7++) {
            this.mClientEquations[i7] = null;
        }
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.inGoal = false;
        Arrays.fill(this.goalStrengthVector, 0.0f);
    }

    public void setFinalValue(LinearSystem linearSystem, float f) {
        this.computedValue = f;
        this.isFinalValue = true;
        this.isSynonym = false;
        this.synonym = -1;
        this.synonymDelta = 0.0f;
        int i2 = this.mClientEquationsCount;
        this.definitionId = -1;
        for (int i7 = 0; i7 < i2; i7++) {
            this.mClientEquations[i7].updateFromFinalVariable(linearSystem, this, false);
        }
        this.mClientEquationsCount = 0;
    }

    public void setType(Type type, String str) {
        this.mType = type;
    }

    public String toString() {
        if (this.mName != null) {
            return "" + this.mName;
        }
        return "" + this.id;
    }

    public final void updateReferencesWithNewDefinition(LinearSystem linearSystem, ArrayRow arrayRow) {
        int i2 = this.mClientEquationsCount;
        for (int i7 = 0; i7 < i2; i7++) {
            this.mClientEquations[i7].updateFromRow(linearSystem, arrayRow, false);
        }
        this.mClientEquationsCount = 0;
    }
}
