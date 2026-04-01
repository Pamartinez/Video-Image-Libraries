package androidx.appfunctions.service;

import Ae.c;
import L2.a;
import Vf.A;
import androidx.appfunctions.AppFunctionContext;
import androidx.appfunctions.ExecuteAppFunctionRequest;
import androidx.appfunctions.service.internal.AggregatedAppFunctionInvoker;
import java.util.Map;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "", "<anonymous>", "(LVf/A;)Ljava/lang/Object;"}, k = 3, mv = {2, 0, 0})
@C1273e(c = "androidx.appfunctions.service.AppFunctionServiceDelegate$unsafeInvokeFunction$result$1", f = "AppFunctionServiceDelegate.kt", l = {142}, m = "invokeSuspend", v = 1)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionServiceDelegate$unsafeInvokeFunction$result$1 extends i implements c {
    final /* synthetic */ Map<String, Object> $parameters;
    final /* synthetic */ ExecuteAppFunctionRequest $request;
    int label;
    final /* synthetic */ AppFunctionServiceDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionServiceDelegate$unsafeInvokeFunction$result$1(AppFunctionServiceDelegate appFunctionServiceDelegate, ExecuteAppFunctionRequest executeAppFunctionRequest, Map<String, ? extends Object> map, C1227c cVar) {
        super(2, cVar);
        this.this$0 = appFunctionServiceDelegate;
        this.$request = executeAppFunctionRequest;
        this.$parameters = map;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new AppFunctionServiceDelegate$unsafeInvokeFunction$result$1(this.this$0, this.$request, this.$parameters, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((AppFunctionServiceDelegate$unsafeInvokeFunction$result$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            AggregatedAppFunctionInvoker access$getAggregatedInvoker$p = this.this$0.aggregatedInvoker;
            AppFunctionContext access$buildAppFunctionContext = this.this$0.buildAppFunctionContext();
            String functionIdentifier = this.$request.getFunctionIdentifier();
            Map<String, Object> map = this.$parameters;
            this.label = 1;
            Object unsafeInvoke = access$getAggregatedInvoker$p.unsafeInvoke(access$buildAppFunctionContext, functionIdentifier, map, this);
            if (unsafeInvoke == aVar) {
                return aVar;
            }
            return unsafeInvoke;
        } else if (i2 == 1) {
            a.A(obj);
            return obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
