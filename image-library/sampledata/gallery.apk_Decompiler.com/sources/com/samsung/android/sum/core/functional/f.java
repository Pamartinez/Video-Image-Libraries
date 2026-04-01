package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.format.Shape;
import com.samsung.android.sum.core.types.ImgpType;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4105a;

    public /* synthetic */ f(int i2) {
        this.f4105a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f4105a) {
            case 0:
                return OperatorChain.lambda$new$1((Operator) obj);
            case 1:
                return OperatorChain.lambda$new$0((Operator) obj);
            case 2:
                return (Operator) ((Map.Entry) obj).getValue();
            case 3:
                return OperatorMap.lambda$config$1((Operator) obj);
            case 4:
                return (ImgpType) ((Map.Entry) obj).getKey();
            case 5:
                return (Enum) ((Map.Entry) obj).getKey();
            case 6:
                return OperatorWrapper.of((Enum) ((Map.Entry) obj).getKey(), (Operator) ((Map.Entry) obj).getValue());
            default:
                return Integer.valueOf(((Shape) obj).getDimension());
        }
    }
}
