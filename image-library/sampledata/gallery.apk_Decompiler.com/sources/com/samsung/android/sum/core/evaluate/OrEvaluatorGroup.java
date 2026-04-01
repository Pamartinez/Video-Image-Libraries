package com.samsung.android.sum.core.evaluate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class OrEvaluatorGroup extends EvaluatorGroup {
    public OrEvaluatorGroup(Evaluator... evaluatorArr) {
        super(evaluatorArr);
    }

    public <V> boolean evaluate(V v) {
        return stream().anyMatch(new a(1, v));
    }
}
