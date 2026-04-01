package androidx.appfunctions;

import Ae.c;
import L2.a;
import Vf.A;
import android.app.appfunctions.ExecuteAppFunctionRequest;
import androidx.appfunctions.metadata.AppFunctionMetadata;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Landroidx/appfunctions/ExecuteAppFunctionResponse;", "<anonymous>", "(LVf/A;)Landroidx/appfunctions/ExecuteAppFunctionResponse;"}, k = 3, mv = {2, 0, 0})
@C1273e(c = "androidx.appfunctions.AppFunctionService$onExecuteFunction$functionExecutionJob$1$result$1", f = "AppFunctionService.kt", l = {99}, m = "invokeSuspend", v = 1)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionService$onExecuteFunction$functionExecutionJob$1$result$1 extends i implements c {
    final /* synthetic */ AppFunctionMetadata $appFunctionMetadata;
    final /* synthetic */ ExecuteAppFunctionRequest $request;
    int label;
    final /* synthetic */ AppFunctionService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionService$onExecuteFunction$functionExecutionJob$1$result$1(AppFunctionService appFunctionService, ExecuteAppFunctionRequest executeAppFunctionRequest, AppFunctionMetadata appFunctionMetadata, C1227c cVar) {
        super(2, cVar);
        this.this$0 = appFunctionService;
        this.$request = executeAppFunctionRequest;
        this.$appFunctionMetadata = appFunctionMetadata;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new AppFunctionService$onExecuteFunction$functionExecutionJob$1$result$1(this.this$0, this.$request, this.$appFunctionMetadata, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((AppFunctionService$onExecuteFunction$functionExecutionJob$1$result$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            AppFunctionService appFunctionService = this.this$0;
            ExecuteAppFunctionRequest fromPlatformClass$appfunctions = ExecuteAppFunctionRequest.Companion.fromPlatformClass$appfunctions(this.$request, this.$appFunctionMetadata);
            this.label = 1;
            Object executeFunction = appFunctionService.executeFunction(fromPlatformClass$appfunctions, this);
            if (executeFunction == aVar) {
                return aVar;
            }
            return executeFunction;
        } else if (i2 == 1) {
            a.A(obj);
            return obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
