package com.samsung.android.sdk.moneta.memory.service;

import com.samsung.android.sdk.moneta.memory.option.EngramSearchContentStatOption;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@C1273e(c = "com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl", f = "MemorySearchServiceImpl.kt", l = {253, 536}, m = "searchContentStat")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MemorySearchServiceImpl$searchContentStat$1 extends C1271c {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MemorySearchServiceImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MemorySearchServiceImpl$searchContentStat$1(MemorySearchServiceImpl memorySearchServiceImpl, C1227c cVar) {
        super(cVar);
        this.this$0 = memorySearchServiceImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.searchContentStat((EngramSearchContentStatOption) null, this);
    }
}
