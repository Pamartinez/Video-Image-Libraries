package com.samsung.android.sum.core.functional;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Supplier {
    public final /* synthetic */ int d;

    public /* synthetic */ d(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return OpPriorityComputable.lambda$compute$1();
            default:
                return OperatorWrapper.lambda$new$1();
        }
    }
}
