package com.samsung.android.app.sdk.deepsky.textextraction.util;

import Ae.b;
import L2.a;
import Vf.Y;
import com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001H\n"}, d2 = {"<anonymous>", "Return"}, k = 3, mv = {2, 1, 0}, xi = 48)
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher$Chain$onBackground$1", f = "SingleThreadCoroutineSwitcher.kt", l = {26}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SingleThreadCoroutineSwitcher$Chain$onBackground$1 extends i implements b {
    final /* synthetic */ b $task;
    int label;
    final /* synthetic */ SingleThreadCoroutineSwitcher.Chain<Param> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SingleThreadCoroutineSwitcher$Chain$onBackground$1(SingleThreadCoroutineSwitcher.Chain<? extends Param> chain, b bVar, C1227c cVar) {
        super(1, cVar);
        this.this$0 = chain;
        this.$task = bVar;
    }

    public final C1227c create(C1227c cVar) {
        return new SingleThreadCoroutineSwitcher$Chain$onBackground$1(this.this$0, this.$task, cVar);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            SingleThreadCoroutineSwitcher.Chain<Param> chain = this.this$0;
            Y access$getThreadContext$p = SingleThreadCoroutineSwitcher.threadContext;
            b bVar = this.$task;
            this.label = 1;
            Object access$doTask = chain.doTask(access$getThreadContext$p, bVar, this);
            if (access$doTask == aVar) {
                return aVar;
            }
            return access$doTask;
        } else if (i2 == 1) {
            a.A(obj);
            return obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invoke(C1227c cVar) {
        return ((SingleThreadCoroutineSwitcher$Chain$onBackground$1) create(cVar)).invokeSuspend(x.f4917a);
    }
}
