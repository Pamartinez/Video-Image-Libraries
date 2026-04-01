package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.functional.OpPriorityComputable;
import com.samsung.android.sum.core.functional.OperatorMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements OpPriorityComputable.ComputeBridge {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4104a;

    public /* synthetic */ e(int i2) {
        this.f4104a = i2;
    }

    public final float compute(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat, OpPriorityCompute opPriorityCompute) {
        switch (this.f4104a) {
            case 0:
                return OpPriorityComputable.lambda$compute$0(mutableMediaFormat, mediaFormat, opPriorityCompute);
            case 1:
                return OperatorMap.AnonymousClass1.lambda$new$2(mutableMediaFormat, mediaFormat, opPriorityCompute);
            case 2:
                return OperatorMap.AnonymousClass1.lambda$new$3(mutableMediaFormat, mediaFormat, opPriorityCompute);
            case 3:
                return OperatorMap.AnonymousClass1.lambda$new$4(mutableMediaFormat, mediaFormat, opPriorityCompute);
            case 4:
                return OperatorMap.AnonymousClass1.lambda$new$5(mutableMediaFormat, mediaFormat, opPriorityCompute);
            case 5:
                return OperatorMap.AnonymousClass1.lambda$new$6(mutableMediaFormat, mediaFormat, opPriorityCompute);
            case 6:
                return OperatorMap.AnonymousClass1.lambda$new$7(mutableMediaFormat, mediaFormat, opPriorityCompute);
            case 7:
                return OperatorMap.AnonymousClass1.lambda$new$8(mutableMediaFormat, mediaFormat, opPriorityCompute);
            case 8:
                return OperatorMap.AnonymousClass1.lambda$new$9(mutableMediaFormat, mediaFormat, opPriorityCompute);
            default:
                return OperatorMap.AnonymousClass1.lambda$new$10(mutableMediaFormat, mediaFormat, opPriorityCompute);
        }
    }
}
