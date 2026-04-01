package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.functional.OperatorMap;
import java.io.Serializable;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4107a;
    public final /* synthetic */ Serializable b;

    public /* synthetic */ j(Serializable serializable, int i2) {
        this.f4107a = i2;
        this.b = serializable;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4107a;
        Serializable serializable = this.b;
        switch (i2) {
            case 0:
                return OperatorMap.AnonymousClass1.lambda$new$0((MediaFormat) serializable, (String) obj);
            default:
                return OperatorWrapper.lambda$of$2((Enum) serializable, (Operator) obj);
        }
    }
}
