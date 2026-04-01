package com.samsung.android.app.sdk.deepsky.textextraction.util;

import Ae.b;
import Ae.c;
import L2.a;
import Vf.A;
import com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher$Chain$start$1", f = "SingleThreadCoroutineSwitcher.kt", l = {54}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SingleThreadCoroutineSwitcher$Chain$start$1 extends i implements c {
    final /* synthetic */ b $onError;
    final /* synthetic */ b $onSuccess;
    int label;
    final /* synthetic */ SingleThreadCoroutineSwitcher.Chain<Param> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SingleThreadCoroutineSwitcher$Chain$start$1(SingleThreadCoroutineSwitcher.Chain<? extends Param> chain, b bVar, b bVar2, C1227c cVar) {
        super(2, cVar);
        this.this$0 = chain;
        this.$onSuccess = bVar;
        this.$onError = bVar2;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new SingleThreadCoroutineSwitcher$Chain$start$1(this.this$0, this.$onSuccess, this.$onError, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((SingleThreadCoroutineSwitcher$Chain$start$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            b access$getPriorTask$p = this.this$0.priorTask;
            this.label = 1;
            obj = access$getPriorTask$p.invoke(this);
            if (obj == aVar) {
                return aVar;
            }
        } else if (i2 == 1) {
            try {
                a.A(obj);
            } catch (Throwable th) {
                b bVar = this.$onError;
                if (bVar != null) {
                    bVar.invoke(th);
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        b bVar2 = this.$onSuccess;
        if (bVar2 != null) {
            bVar2.invoke(obj);
        }
        return x.f4917a;
    }
}
