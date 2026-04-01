package com.samsung.android.app.sdk.deepsky.textextraction.util;

import Ae.b;
import com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher;
import kotlin.Metadata;
import qe.C1227c;
import qe.C1232h;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher$Chain", f = "SingleThreadCoroutineSwitcher.kt", l = {35, 36}, m = "doTask")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SingleThreadCoroutineSwitcher$Chain$doTask$1<Return> extends C1271c {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SingleThreadCoroutineSwitcher.Chain<Param> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SingleThreadCoroutineSwitcher$Chain$doTask$1(SingleThreadCoroutineSwitcher.Chain<? extends Param> chain, C1227c cVar) {
        super(cVar);
        this.this$0 = chain;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doTask((C1232h) null, (b) null, this);
    }
}
