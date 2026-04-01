package com.samsung.android.app.sdk.deepsky.textextraction.util;

import Ae.b;
import L2.a;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lme/x;", "<anonymous>", "()V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher$newChain$1", f = "SingleThreadCoroutineSwitcher.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SingleThreadCoroutineSwitcher$newChain$1 extends i implements b {
    int label;

    public SingleThreadCoroutineSwitcher$newChain$1(C1227c cVar) {
        super(1, cVar);
    }

    public final C1227c create(C1227c cVar) {
        return new SingleThreadCoroutineSwitcher$newChain$1(cVar);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            a.A(obj);
            return x.f4917a;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(C1227c cVar) {
        return ((SingleThreadCoroutineSwitcher$newChain$1) create(cVar)).invokeSuspend(x.f4917a);
    }
}
