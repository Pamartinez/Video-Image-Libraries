package com.samsung.android.app.sdk.deepsky.textextraction.util;

import Ae.b;
import Ae.c;
import L2.a;
import Vf.A;
import kotlin.Metadata;
import kotlin.jvm.internal.u;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "", "<anonymous>", "(LVf/A;)Ljava/lang/Void;"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher$Chain$doTask$2", f = "SingleThreadCoroutineSwitcher.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SingleThreadCoroutineSwitcher$Chain$doTask$2 extends i implements c {
    final /* synthetic */ Param $param;
    final /* synthetic */ b $task;
    final /* synthetic */ u $tempResult;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SingleThreadCoroutineSwitcher$Chain$doTask$2(u uVar, b bVar, Param param, C1227c cVar) {
        super(2, cVar);
        this.$tempResult = uVar;
        this.$task = bVar;
        this.$param = param;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new SingleThreadCoroutineSwitcher$Chain$doTask$2(this.$tempResult, this.$task, this.$param, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((SingleThreadCoroutineSwitcher$Chain$doTask$2) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            a.A(obj);
            try {
                this.$tempResult.d = this.$task.invoke(this.$param);
                th = null;
            } catch (Throwable th) {
                th = th;
            }
            if (th == null) {
                return null;
            }
            throw th;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
