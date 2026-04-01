package androidx.constraintlayout.solver;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Cache {
    Pools$Pool<ArrayRow> arrayRowPool = new Pools$SimplePool(256);
    SolverVariable[] mIndexedVariables = new SolverVariable[32];
    Pools$Pool<ArrayRow> optimizedArrayRowPool = new Pools$SimplePool(256);
    Pools$Pool<SolverVariable> solverVariablePool = new Pools$SimplePool(256);
}
