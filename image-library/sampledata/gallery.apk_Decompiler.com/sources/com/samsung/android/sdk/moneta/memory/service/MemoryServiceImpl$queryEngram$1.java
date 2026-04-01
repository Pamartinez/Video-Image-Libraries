package com.samsung.android.sdk.moneta.memory.service;

import com.samsung.android.sdk.moneta.memory.option.EngramQueryOption;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@C1273e(c = "com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl", f = "MemoryServiceImpl.kt", l = {104, 440}, m = "queryEngram")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MemoryServiceImpl$queryEngram$1 extends C1271c {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MemoryServiceImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MemoryServiceImpl$queryEngram$1(MemoryServiceImpl memoryServiceImpl, C1227c cVar) {
        super(cVar);
        this.this$0 = memoryServiceImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.queryEngram((EngramQueryOption) null, this);
    }
}
