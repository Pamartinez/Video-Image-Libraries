package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.functional.OperatorMap;
import com.samsung.android.sum.core.types.SplitType;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4106a;

    public /* synthetic */ i(int i2) {
        this.f4106a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f4106a) {
            case 0:
                return OperatorMap.AnonymousClass1.lambda$new$1((SplitType) obj);
            default:
                return OperatorMap.lambda$inferOperations$4((Map.Entry) obj);
        }
    }
}
