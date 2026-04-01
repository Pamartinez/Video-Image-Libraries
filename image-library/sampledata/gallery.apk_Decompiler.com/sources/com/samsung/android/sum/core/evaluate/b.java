package com.samsung.android.sum.core.evaluate;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4074a;
    public final /* synthetic */ EvaluableMap b;

    public /* synthetic */ b(EvaluableMap evaluableMap, int i2) {
        this.f4074a = i2;
        this.b = evaluableMap;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4074a;
        EvaluableMap evaluableMap = this.b;
        Evaluator evaluator = (Evaluator) obj;
        switch (i2) {
            case 0:
                return evaluableMap.lambda$get$1(evaluator);
            default:
                return evaluableMap.lambda$getOr$3(evaluator);
        }
    }
}
