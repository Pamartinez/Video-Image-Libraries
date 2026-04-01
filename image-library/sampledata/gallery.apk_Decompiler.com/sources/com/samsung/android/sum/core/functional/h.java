package com.samsung.android.sum.core.functional;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ OperatorMap d;

    public /* synthetic */ h(OperatorMap operatorMap) {
        this.d = operatorMap;
    }

    public final void accept(Object obj) {
        this.d.lambda$config$2((OpPriorityComputable) obj);
    }
}
