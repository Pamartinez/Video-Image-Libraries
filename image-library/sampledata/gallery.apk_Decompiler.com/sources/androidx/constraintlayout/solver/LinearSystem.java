package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LinearSystem {
    public static long ARRAY_ROW_CREATION = 0;
    public static long OPTIMIZED_ARRAY_ROW_CREATION = 0;
    public static boolean OPTIMIZED_ENGINE = false;
    private static int POOL_SIZE = 1000;
    public static boolean SIMPLIFY_SYNONYMS = true;
    public static boolean SKIP_COLUMNS = true;
    public static boolean USE_BASIC_SYNONYMS = true;
    public static boolean USE_DEPENDENCY_ORDERING = false;
    public static Metrics sMetrics;
    private int TABLE_SIZE;
    public boolean graphOptimizer;
    public boolean hasSimpleDefinition;
    private boolean[] mAlreadyTestedCandidates;
    final Cache mCache;
    private Row mGoal;
    private int mMaxColumns;
    private int mMaxRows;
    int mNumColumns;
    int mNumRows;
    private SolverVariable[] mPoolVariables;
    private int mPoolVariablesCount;
    ArrayRow[] mRows;
    private Row mTempGoal;
    private HashMap<String, SolverVariable> mVariables;
    int mVariablesID;
    public boolean newgraphOptimizer;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Row {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(Row row);

        boolean isEmpty();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ValuesRow extends ArrayRow {
        public ValuesRow(Cache cache) {
            this.variables = new SolverVariableValues(this, cache);
        }
    }

    public LinearSystem() {
        this.hasSimpleDefinition = false;
        this.mVariablesID = 0;
        this.mVariables = null;
        this.TABLE_SIZE = 32;
        this.mMaxColumns = 32;
        this.mRows = null;
        this.graphOptimizer = false;
        this.newgraphOptimizer = false;
        this.mAlreadyTestedCandidates = new boolean[32];
        this.mNumColumns = 1;
        this.mNumRows = 0;
        this.mMaxRows = 32;
        this.mPoolVariables = new SolverVariable[POOL_SIZE];
        this.mPoolVariablesCount = 0;
        this.mRows = new ArrayRow[32];
        releaseRows();
        Cache cache = new Cache();
        this.mCache = cache;
        this.mGoal = new PriorityGoalRow(cache);
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(cache);
        } else {
            this.mTempGoal = new ArrayRow(cache);
        }
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type type, String str) {
        SolverVariable acquire = this.mCache.solverVariablePool.acquire();
        if (acquire == null) {
            acquire = new SolverVariable(type, str);
            acquire.setType(type, str);
        } else {
            acquire.reset();
            acquire.setType(type, str);
        }
        int i2 = this.mPoolVariablesCount;
        int i7 = POOL_SIZE;
        if (i2 >= i7) {
            int i8 = i7 * 2;
            POOL_SIZE = i8;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, i8);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i10 = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i10 + 1;
        solverVariableArr[i10] = acquire;
        return acquire;
    }

    private final void addRow(ArrayRow arrayRow) {
        int i2;
        if (!SIMPLIFY_SYNONYMS || !arrayRow.isSimpleDefinition) {
            ArrayRow[] arrayRowArr = this.mRows;
            int i7 = this.mNumRows;
            arrayRowArr[i7] = arrayRow;
            SolverVariable solverVariable = arrayRow.variable;
            solverVariable.definitionId = i7;
            this.mNumRows = i7 + 1;
            solverVariable.updateReferencesWithNewDefinition(this, arrayRow);
        } else {
            arrayRow.variable.setFinalValue(this, arrayRow.constantValue);
        }
        if (SIMPLIFY_SYNONYMS && this.hasSimpleDefinition) {
            int i8 = 0;
            while (i8 < this.mNumRows) {
                if (this.mRows[i8] == null) {
                    System.out.println("WTF");
                }
                ArrayRow arrayRow2 = this.mRows[i8];
                if (arrayRow2 != null && arrayRow2.isSimpleDefinition) {
                    arrayRow2.variable.setFinalValue(this, arrayRow2.constantValue);
                    if (OPTIMIZED_ENGINE) {
                        this.mCache.optimizedArrayRowPool.release(arrayRow2);
                    } else {
                        this.mCache.arrayRowPool.release(arrayRow2);
                    }
                    this.mRows[i8] = null;
                    int i10 = i8 + 1;
                    int i11 = i10;
                    while (true) {
                        i2 = this.mNumRows;
                        if (i10 >= i2) {
                            break;
                        }
                        ArrayRow[] arrayRowArr2 = this.mRows;
                        int i12 = i10 - 1;
                        ArrayRow arrayRow3 = arrayRowArr2[i10];
                        arrayRowArr2[i12] = arrayRow3;
                        SolverVariable solverVariable2 = arrayRow3.variable;
                        if (solverVariable2.definitionId == i10) {
                            solverVariable2.definitionId = i12;
                        }
                        i11 = i10;
                        i10++;
                    }
                    if (i11 < i2) {
                        this.mRows[i11] = null;
                    }
                    this.mNumRows = i2 - 1;
                    i8--;
                }
                i8++;
            }
            this.hasSimpleDefinition = false;
        }
    }

    private void computeValues() {
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            ArrayRow arrayRow = this.mRows[i2];
            arrayRow.variable.computedValue = arrayRow.constantValue;
        }
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        return linearSystem.createRow().createRowDimensionPercent(solverVariable, solverVariable2, f);
    }

    private int enforceBFS(Row row) {
        long j2;
        float f;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            ArrayRow arrayRow = this.mRows[i2];
            if (arrayRow.variable.mType != SolverVariable.Type.UNRESTRICTED) {
                float f5 = 0.0f;
                if (arrayRow.constantValue < 0.0f) {
                    boolean z = false;
                    int i7 = 0;
                    while (!z) {
                        Metrics metrics = sMetrics;
                        long j3 = 1;
                        if (metrics != null) {
                            metrics.bfs++;
                        }
                        i7++;
                        float f8 = Float.MAX_VALUE;
                        int i8 = 0;
                        int i10 = -1;
                        int i11 = -1;
                        int i12 = 0;
                        while (true) {
                            if (i8 >= this.mNumRows) {
                                break;
                            }
                            ArrayRow arrayRow2 = this.mRows[i8];
                            if (arrayRow2.variable.mType != SolverVariable.Type.UNRESTRICTED && !arrayRow2.isSimpleDefinition && arrayRow2.constantValue < f5) {
                                if (SKIP_COLUMNS) {
                                    int currentSize = arrayRow2.variables.getCurrentSize();
                                    int i13 = 0;
                                    while (i13 < currentSize) {
                                        float f10 = f5;
                                        SolverVariable variable = arrayRow2.variables.getVariable(i13);
                                        long j8 = j3;
                                        float f11 = arrayRow2.variables.get(variable);
                                        if (f11 > f10) {
                                            for (int i14 = 0; i14 < 9; i14++) {
                                                float f12 = variable.strengthVector[i14] / f11;
                                                if ((f12 < f8 && i14 == i12) || i14 > i12) {
                                                    i12 = i14;
                                                    i11 = variable.id;
                                                    i10 = i8;
                                                    f8 = f12;
                                                }
                                            }
                                        }
                                        i13++;
                                        f5 = f10;
                                        j3 = j8;
                                    }
                                } else {
                                    f = f5;
                                    j2 = j3;
                                    for (int i15 = 1; i15 < this.mNumColumns; i15++) {
                                        SolverVariable solverVariable = this.mCache.mIndexedVariables[i15];
                                        float f13 = arrayRow2.variables.get(solverVariable);
                                        if (f13 > f) {
                                            for (int i16 = 0; i16 < 9; i16++) {
                                                float f14 = solverVariable.strengthVector[i16] / f13;
                                                if ((f14 < f8 && i16 == i12) || i16 > i12) {
                                                    i12 = i16;
                                                    f8 = f14;
                                                    i10 = i8;
                                                    i11 = i15;
                                                }
                                            }
                                        }
                                    }
                                    i8++;
                                    f5 = f;
                                    j3 = j2;
                                }
                            }
                            f = f5;
                            j2 = j3;
                            i8++;
                            f5 = f;
                            j3 = j2;
                        }
                        float f15 = f5;
                        long j10 = j3;
                        if (i10 != -1) {
                            ArrayRow arrayRow3 = this.mRows[i10];
                            arrayRow3.variable.definitionId = -1;
                            Metrics metrics2 = sMetrics;
                            if (metrics2 != null) {
                                metrics2.pivots += j10;
                            }
                            arrayRow3.pivot(this.mCache.mIndexedVariables[i11]);
                            SolverVariable solverVariable2 = arrayRow3.variable;
                            solverVariable2.definitionId = i10;
                            solverVariable2.updateReferencesWithNewDefinition(this, arrayRow3);
                        } else {
                            z = true;
                        }
                        if (i7 > this.mNumColumns / 2) {
                            z = true;
                        }
                        f5 = f15;
                    }
                    return i7;
                }
            }
        }
        return 0;
    }

    public static Metrics getMetrics() {
        return sMetrics;
    }

    private void increaseTableSize() {
        int i2 = this.TABLE_SIZE * 2;
        this.TABLE_SIZE = i2;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, i2);
        Cache cache = this.mCache;
        cache.mIndexedVariables = (SolverVariable[]) Arrays.copyOf(cache.mIndexedVariables, this.TABLE_SIZE);
        int i7 = this.TABLE_SIZE;
        this.mAlreadyTestedCandidates = new boolean[i7];
        this.mMaxColumns = i7;
        this.mMaxRows = i7;
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.tableSizeIncrease++;
            metrics.maxTableSize = Math.max(metrics.maxTableSize, (long) i7);
            Metrics metrics2 = sMetrics;
            metrics2.lastTableSize = metrics2.maxTableSize;
        }
    }

    private final int optimize(Row row, boolean z) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.optimize++;
        }
        for (int i2 = 0; i2 < this.mNumColumns; i2++) {
            this.mAlreadyTestedCandidates[i2] = false;
        }
        boolean z3 = false;
        int i7 = 0;
        while (!z3) {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.iterations++;
            }
            i7++;
            if (i7 < this.mNumColumns * 2) {
                if (row.getKey() != null) {
                    this.mAlreadyTestedCandidates[row.getKey().id] = true;
                }
                SolverVariable pivotCandidate = row.getPivotCandidate(this, this.mAlreadyTestedCandidates);
                if (pivotCandidate != null) {
                    boolean[] zArr = this.mAlreadyTestedCandidates;
                    int i8 = pivotCandidate.id;
                    if (!zArr[i8]) {
                        zArr[i8] = true;
                    }
                }
                if (pivotCandidate != null) {
                    float f = Float.MAX_VALUE;
                    int i10 = -1;
                    for (int i11 = 0; i11 < this.mNumRows; i11++) {
                        ArrayRow arrayRow = this.mRows[i11];
                        if (arrayRow.variable.mType != SolverVariable.Type.UNRESTRICTED && !arrayRow.isSimpleDefinition && arrayRow.hasVariable(pivotCandidate)) {
                            float f5 = arrayRow.variables.get(pivotCandidate);
                            if (f5 < 0.0f) {
                                float f8 = (-arrayRow.constantValue) / f5;
                                if (f8 < f) {
                                    i10 = i11;
                                    f = f8;
                                }
                            }
                        }
                    }
                    if (i10 > -1) {
                        ArrayRow arrayRow2 = this.mRows[i10];
                        arrayRow2.variable.definitionId = -1;
                        Metrics metrics3 = sMetrics;
                        if (metrics3 != null) {
                            metrics3.pivots++;
                        }
                        arrayRow2.pivot(pivotCandidate);
                        SolverVariable solverVariable = arrayRow2.variable;
                        solverVariable.definitionId = i10;
                        solverVariable.updateReferencesWithNewDefinition(this, arrayRow2);
                    }
                } else {
                    z3 = true;
                }
            }
            return i7;
        }
        return i7;
    }

    private void releaseRows() {
        int i2 = 0;
        if (OPTIMIZED_ENGINE) {
            while (i2 < this.mNumRows) {
                ArrayRow arrayRow = this.mRows[i2];
                if (arrayRow != null) {
                    this.mCache.optimizedArrayRowPool.release(arrayRow);
                }
                this.mRows[i2] = null;
                i2++;
            }
            return;
        }
        while (i2 < this.mNumRows) {
            ArrayRow arrayRow2 = this.mRows[i2];
            if (arrayRow2 != null) {
                this.mCache.arrayRowPool.release(arrayRow2);
            }
            this.mRows[i2] = null;
            i2++;
        }
    }

    public void addCenterPoint(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f, int i2) {
        ConstraintWidget constraintWidget3 = constraintWidget;
        ConstraintWidget constraintWidget4 = constraintWidget2;
        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
        SolverVariable createObjectVariable = createObjectVariable(constraintWidget3.getAnchor(type));
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
        SolverVariable createObjectVariable2 = createObjectVariable(constraintWidget3.getAnchor(type2));
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
        SolverVariable createObjectVariable3 = createObjectVariable(constraintWidget3.getAnchor(type3));
        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
        SolverVariable createObjectVariable4 = createObjectVariable(constraintWidget3.getAnchor(type4));
        SolverVariable createObjectVariable5 = createObjectVariable(constraintWidget4.getAnchor(type));
        SolverVariable createObjectVariable6 = createObjectVariable(constraintWidget4.getAnchor(type2));
        SolverVariable createObjectVariable7 = createObjectVariable(constraintWidget4.getAnchor(type3));
        SolverVariable createObjectVariable8 = createObjectVariable(constraintWidget4.getAnchor(type4));
        ArrayRow createRow = createRow();
        double d = (double) f;
        SolverVariable solverVariable = createObjectVariable5;
        double d2 = (double) i2;
        createRow.createRowWithAngle(createObjectVariable2, createObjectVariable4, createObjectVariable6, createObjectVariable8, (float) (Math.sin(d) * d2));
        addConstraint(createRow);
        ArrayRow createRow2 = createRow();
        SolverVariable solverVariable2 = createObjectVariable3;
        createRow2.createRowWithAngle(createObjectVariable, solverVariable2, solverVariable, createObjectVariable7, (float) (Math.cos(d) * d2));
        addConstraint(createRow2);
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i7, int i8) {
        int i10 = i8;
        ArrayRow createRow = createRow();
        createRow.createRowCentering(solverVariable, solverVariable2, i2, f, solverVariable3, solverVariable4, i7);
        if (i10 != 8) {
            createRow.addError(this, i10);
        }
        addConstraint(createRow);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addConstraint(androidx.constraintlayout.solver.ArrayRow r8) {
        /*
            r7 = this;
            if (r8 != 0) goto L_0x0004
            goto L_0x00a8
        L_0x0004:
            androidx.constraintlayout.solver.Metrics r0 = sMetrics
            r1 = 1
            if (r0 == 0) goto L_0x0018
            long r3 = r0.constraints
            long r3 = r3 + r1
            r0.constraints = r3
            boolean r3 = r8.isSimpleDefinition
            if (r3 == 0) goto L_0x0018
            long r3 = r0.simpleconstraints
            long r3 = r3 + r1
            r0.simpleconstraints = r3
        L_0x0018:
            int r0 = r7.mNumRows
            r3 = 1
            int r0 = r0 + r3
            int r4 = r7.mMaxRows
            if (r0 >= r4) goto L_0x0027
            int r0 = r7.mNumColumns
            int r0 = r0 + r3
            int r4 = r7.mMaxColumns
            if (r0 < r4) goto L_0x002a
        L_0x0027:
            r7.increaseTableSize()
        L_0x002a:
            boolean r0 = r8.isSimpleDefinition
            r4 = 0
            if (r0 != 0) goto L_0x00a3
            r8.updateFromSystem(r7)
            boolean r0 = r8.isEmpty()
            if (r0 == 0) goto L_0x003a
            goto L_0x00a8
        L_0x003a:
            r8.ensurePositiveConstant()
            boolean r0 = r8.chooseSubject(r7)
            if (r0 == 0) goto L_0x009a
            androidx.constraintlayout.solver.SolverVariable r0 = r7.createExtraVariable()
            r8.variable = r0
            int r5 = r7.mNumRows
            r7.addRow(r8)
            int r6 = r7.mNumRows
            int r5 = r5 + r3
            if (r6 != r5) goto L_0x009a
            androidx.constraintlayout.solver.LinearSystem$Row r4 = r7.mTempGoal
            r4.initFromRow(r8)
            androidx.constraintlayout.solver.LinearSystem$Row r4 = r7.mTempGoal
            r7.optimize(r4, r3)
            int r4 = r0.definitionId
            r5 = -1
            if (r4 != r5) goto L_0x009b
            androidx.constraintlayout.solver.SolverVariable r4 = r8.variable
            if (r4 != r0) goto L_0x0078
            androidx.constraintlayout.solver.SolverVariable r0 = r8.pickPivot(r0)
            if (r0 == 0) goto L_0x0078
            androidx.constraintlayout.solver.Metrics r4 = sMetrics
            if (r4 == 0) goto L_0x0075
            long r5 = r4.pivots
            long r5 = r5 + r1
            r4.pivots = r5
        L_0x0075:
            r8.pivot(r0)
        L_0x0078:
            boolean r0 = r8.isSimpleDefinition
            if (r0 != 0) goto L_0x0081
            androidx.constraintlayout.solver.SolverVariable r0 = r8.variable
            r0.updateReferencesWithNewDefinition(r7, r8)
        L_0x0081:
            boolean r0 = OPTIMIZED_ENGINE
            if (r0 == 0) goto L_0x008d
            androidx.constraintlayout.solver.Cache r0 = r7.mCache
            androidx.constraintlayout.solver.Pools$Pool<androidx.constraintlayout.solver.ArrayRow> r0 = r0.optimizedArrayRowPool
            r0.release(r8)
            goto L_0x0094
        L_0x008d:
            androidx.constraintlayout.solver.Cache r0 = r7.mCache
            androidx.constraintlayout.solver.Pools$Pool<androidx.constraintlayout.solver.ArrayRow> r0 = r0.arrayRowPool
            r0.release(r8)
        L_0x0094:
            int r0 = r7.mNumRows
            int r0 = r0 - r3
            r7.mNumRows = r0
            goto L_0x009b
        L_0x009a:
            r3 = r4
        L_0x009b:
            boolean r0 = r8.hasKeyVariable()
            if (r0 != 0) goto L_0x00a2
            goto L_0x00a8
        L_0x00a2:
            r4 = r3
        L_0x00a3:
            if (r4 != 0) goto L_0x00a8
            r7.addRow(r8)
        L_0x00a8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.LinearSystem.addConstraint(androidx.constraintlayout.solver.ArrayRow):void");
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i7) {
        if (!USE_BASIC_SYNONYMS || i7 != 8 || !solverVariable2.isFinalValue || solverVariable.definitionId != -1) {
            ArrayRow createRow = createRow();
            createRow.createRowEquals(solverVariable, solverVariable2, i2);
            if (i7 != 8) {
                createRow.addError(this, i7);
            }
            addConstraint(createRow);
            return createRow;
        }
        solverVariable.setFinalValue(this, solverVariable2.computedValue + ((float) i2));
        return null;
    }

    public void addGreaterBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i2);
        addConstraint(createRow);
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i7) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i2);
        if (i7 != 8) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f), i7);
        }
        addConstraint(createRow);
    }

    public void addLowerBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i2);
        addConstraint(createRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i7) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i2);
        if (i7 != 8) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f), i7);
        }
        addConstraint(createRow);
    }

    public void addRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f, int i2) {
        ArrayRow createRow = createRow();
        createRow.createRowDimensionRatio(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        if (i2 != 8) {
            createRow.addError(this, i2);
        }
        addConstraint(createRow);
    }

    public void addSingleError(ArrayRow arrayRow, int i2, int i7) {
        arrayRow.addSingleError(createErrorVariable(i7, (String) null), i2);
    }

    public SolverVariable createErrorVariable(int i2, String str) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.errors++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.ERROR, str);
        int i7 = this.mVariablesID + 1;
        this.mVariablesID = i7;
        this.mNumColumns++;
        acquireSolverVariable.id = i7;
        acquireSolverVariable.strength = i2;
        this.mCache.mIndexedVariables[i7] = acquireSolverVariable;
        this.mGoal.addError(acquireSolverVariable);
        return acquireSolverVariable;
    }

    public SolverVariable createExtraVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.extravariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, (String) null);
        int i2 = this.mVariablesID + 1;
        this.mVariablesID = i2;
        this.mNumColumns++;
        acquireSolverVariable.id = i2;
        this.mCache.mIndexedVariables[i2] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    public SolverVariable createObjectVariable(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.getSolverVariable();
            if (solverVariable == null) {
                constraintAnchor.resetSolverVariable(this.mCache);
                solverVariable = constraintAnchor.getSolverVariable();
            }
            int i2 = solverVariable.id;
            if (i2 != -1 && i2 <= this.mVariablesID && this.mCache.mIndexedVariables[i2] != null) {
                return solverVariable;
            }
            if (i2 != -1) {
                solverVariable.reset();
            }
            int i7 = this.mVariablesID + 1;
            this.mVariablesID = i7;
            this.mNumColumns++;
            solverVariable.id = i7;
            solverVariable.mType = SolverVariable.Type.UNRESTRICTED;
            this.mCache.mIndexedVariables[i7] = solverVariable;
        }
        return solverVariable;
    }

    public ArrayRow createRow() {
        ArrayRow arrayRow;
        if (OPTIMIZED_ENGINE) {
            arrayRow = this.mCache.optimizedArrayRowPool.acquire();
            if (arrayRow == null) {
                arrayRow = new ValuesRow(this.mCache);
                OPTIMIZED_ARRAY_ROW_CREATION++;
            } else {
                arrayRow.reset();
            }
        } else {
            arrayRow = this.mCache.arrayRowPool.acquire();
            if (arrayRow == null) {
                arrayRow = new ArrayRow(this.mCache);
                ARRAY_ROW_CREATION++;
            } else {
                arrayRow.reset();
            }
        }
        SolverVariable.increaseErrorId();
        return arrayRow;
    }

    public SolverVariable createSlackVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.slackvariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, (String) null);
        int i2 = this.mVariablesID + 1;
        this.mVariablesID = i2;
        this.mNumColumns++;
        acquireSolverVariable.id = i2;
        this.mCache.mIndexedVariables[i2] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    public void fillMetrics(Metrics metrics) {
        sMetrics = metrics;
    }

    public Cache getCache() {
        return this.mCache;
    }

    public int getObjectVariableValue(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).getSolverVariable();
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    public void minimize() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimize++;
        }
        if (this.mGoal.isEmpty()) {
            computeValues();
        } else if (this.graphOptimizer || this.newgraphOptimizer) {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.graphOptimizer++;
            }
            for (int i2 = 0; i2 < this.mNumRows; i2++) {
                if (!this.mRows[i2].isSimpleDefinition) {
                    minimizeGoal(this.mGoal);
                    return;
                }
            }
            Metrics metrics3 = sMetrics;
            if (metrics3 != null) {
                metrics3.fullySolved++;
            }
            computeValues();
        } else {
            minimizeGoal(this.mGoal);
        }
    }

    public void minimizeGoal(Row row) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimizeGoal++;
            metrics.maxVariables = Math.max(metrics.maxVariables, (long) this.mNumColumns);
            Metrics metrics2 = sMetrics;
            metrics2.maxRows = Math.max(metrics2.maxRows, (long) this.mNumRows);
        }
        enforceBFS(row);
        optimize(row, false);
        computeValues();
    }

    public void reset() {
        Cache cache;
        int i2 = 0;
        while (true) {
            cache = this.mCache;
            SolverVariable[] solverVariableArr = cache.mIndexedVariables;
            if (i2 >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i2];
            if (solverVariable != null) {
                solverVariable.reset();
            }
            i2++;
        }
        cache.solverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, (Object) null);
        HashMap<String, SolverVariable> hashMap = this.mVariables;
        if (hashMap != null) {
            hashMap.clear();
        }
        this.mVariablesID = 0;
        this.mGoal.clear();
        this.mNumColumns = 1;
        for (int i7 = 0; i7 < this.mNumRows; i7++) {
            ArrayRow arrayRow = this.mRows[i7];
            if (arrayRow != null) {
                arrayRow.used = false;
            }
        }
        releaseRows();
        this.mNumRows = 0;
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(this.mCache);
        } else {
            this.mTempGoal = new ArrayRow(this.mCache);
        }
    }

    public void addEquality(SolverVariable solverVariable, int i2) {
        if (!USE_BASIC_SYNONYMS || solverVariable.definitionId != -1) {
            int i7 = solverVariable.definitionId;
            if (i7 != -1) {
                ArrayRow arrayRow = this.mRows[i7];
                if (arrayRow.isSimpleDefinition) {
                    arrayRow.constantValue = (float) i2;
                } else if (arrayRow.variables.getCurrentSize() == 0) {
                    arrayRow.isSimpleDefinition = true;
                    arrayRow.constantValue = (float) i2;
                } else {
                    ArrayRow createRow = createRow();
                    createRow.createRowEquals(solverVariable, i2);
                    addConstraint(createRow);
                }
            } else {
                ArrayRow createRow2 = createRow();
                createRow2.createRowDefinition(solverVariable, i2);
                addConstraint(createRow2);
            }
        } else {
            float f = (float) i2;
            solverVariable.setFinalValue(this, f);
            for (int i8 = 0; i8 < this.mVariablesID + 1; i8++) {
                SolverVariable solverVariable2 = this.mCache.mIndexedVariables[i8];
                if (solverVariable2 != null && solverVariable2.isSynonym && solverVariable2.synonym == solverVariable.id) {
                    solverVariable2.setFinalValue(this, solverVariable2.synonymDelta + f);
                }
            }
        }
    }
}
