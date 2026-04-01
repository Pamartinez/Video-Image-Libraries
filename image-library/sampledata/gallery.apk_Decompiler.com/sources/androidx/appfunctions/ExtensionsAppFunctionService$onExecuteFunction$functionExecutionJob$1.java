package androidx.appfunctions;

import Ae.c;
import Vf.A;
import android.os.OutcomeReceiver;
import com.android.extensions.appfunctions.AppFunctionException;
import com.android.extensions.appfunctions.ExecuteAppFunctionRequest;
import com.android.extensions.appfunctions.ExecuteAppFunctionResponse;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 0, 0})
@C1273e(c = "androidx.appfunctions.ExtensionsAppFunctionService$onExecuteFunction$functionExecutionJob$1", f = "ExtensionsAppFunctionService.kt", l = {86, 93}, m = "invokeSuspend", v = 1)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExtensionsAppFunctionService$onExecuteFunction$functionExecutionJob$1 extends i implements c {
    final /* synthetic */ OutcomeReceiver<ExecuteAppFunctionResponse, AppFunctionException> $callback;
    final /* synthetic */ String $callingPackage;
    final /* synthetic */ ExecuteAppFunctionRequest $request;
    int label;
    final /* synthetic */ ExtensionsAppFunctionService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtensionsAppFunctionService$onExecuteFunction$functionExecutionJob$1(ExtensionsAppFunctionService extensionsAppFunctionService, ExecuteAppFunctionRequest executeAppFunctionRequest, String str, OutcomeReceiver<ExecuteAppFunctionResponse, AppFunctionException> outcomeReceiver, C1227c cVar) {
        super(2, cVar);
        this.this$0 = extensionsAppFunctionService;
        this.$request = executeAppFunctionRequest;
        this.$callingPackage = str;
        this.$callback = outcomeReceiver;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new ExtensionsAppFunctionService$onExecuteFunction$functionExecutionJob$1(this.this$0, this.$request, this.$callingPackage, this.$callback, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((ExtensionsAppFunctionService$onExecuteFunction$functionExecutionJob$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0057, code lost:
        if (r8 == r1) goto L_0x0059;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.String r0 = "No function found with identifier: "
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r7.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0020
            if (r2 == r4) goto L_0x001c
            if (r2 != r3) goto L_0x0014
            L2.a.A(r8)     // Catch:{ AppFunctionException -> 0x0012 }
            goto L_0x005a
        L_0x0012:
            r8 = move-exception
            goto L_0x0083
        L_0x0014:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x001c:
            L2.a.A(r8)     // Catch:{ AppFunctionException -> 0x0012 }
            goto L_0x003d
        L_0x0020:
            L2.a.A(r8)
            androidx.appfunctions.internal.AppFunctionMetadataUtils r8 = androidx.appfunctions.internal.AppFunctionMetadataUtils.INSTANCE     // Catch:{ AppFunctionException -> 0x0012 }
            androidx.appfunctions.ExtensionsAppFunctionService r2 = r7.this$0     // Catch:{ AppFunctionException -> 0x0012 }
            android.content.Context r2 = (android.content.Context) r2     // Catch:{ AppFunctionException -> 0x0012 }
            com.android.extensions.appfunctions.ExecuteAppFunctionRequest r5 = r7.$request     // Catch:{ AppFunctionException -> 0x0012 }
            java.lang.String r5 = r5.getFunctionIdentifier()     // Catch:{ AppFunctionException -> 0x0012 }
            java.lang.String r6 = "getFunctionIdentifier(...)"
            kotlin.jvm.internal.j.d(r5, r6)     // Catch:{ AppFunctionException -> 0x0012 }
            r7.label = r4     // Catch:{ AppFunctionException -> 0x0012 }
            java.lang.Object r8 = r8.getAppFunctionMetadata(r2, r5, r7)     // Catch:{ AppFunctionException -> 0x0012 }
            if (r8 != r1) goto L_0x003d
            goto L_0x0059
        L_0x003d:
            androidx.appfunctions.metadata.AppFunctionMetadata r8 = (androidx.appfunctions.metadata.AppFunctionMetadata) r8     // Catch:{ AppFunctionException -> 0x0012 }
            if (r8 == 0) goto L_0x005d
            androidx.appfunctions.internal.Dispatchers r0 = androidx.appfunctions.internal.Dispatchers.INSTANCE     // Catch:{ AppFunctionException -> 0x0012 }
            Vf.x r0 = r0.getMain()     // Catch:{ AppFunctionException -> 0x0012 }
            androidx.appfunctions.ExtensionsAppFunctionService$onExecuteFunction$functionExecutionJob$1$result$1 r2 = new androidx.appfunctions.ExtensionsAppFunctionService$onExecuteFunction$functionExecutionJob$1$result$1     // Catch:{ AppFunctionException -> 0x0012 }
            androidx.appfunctions.ExtensionsAppFunctionService r4 = r7.this$0     // Catch:{ AppFunctionException -> 0x0012 }
            com.android.extensions.appfunctions.ExecuteAppFunctionRequest r5 = r7.$request     // Catch:{ AppFunctionException -> 0x0012 }
            r6 = 0
            r2.<init>(r4, r5, r8, r6)     // Catch:{ AppFunctionException -> 0x0012 }
            r7.label = r3     // Catch:{ AppFunctionException -> 0x0012 }
            java.lang.Object r8 = Vf.D.w(r0, r2, r7)     // Catch:{ AppFunctionException -> 0x0012 }
            if (r8 != r1) goto L_0x005a
        L_0x0059:
            return r1
        L_0x005a:
            androidx.appfunctions.ExecuteAppFunctionResponse r8 = (androidx.appfunctions.ExecuteAppFunctionResponse) r8     // Catch:{ AppFunctionException -> 0x0012 }
            goto L_0x0089
        L_0x005d:
            androidx.appfunctions.AppFunctionFunctionNotFoundException r8 = new androidx.appfunctions.AppFunctionFunctionNotFoundException     // Catch:{ AppFunctionException -> 0x0012 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ AppFunctionException -> 0x0012 }
            r1.<init>(r0)     // Catch:{ AppFunctionException -> 0x0012 }
            com.android.extensions.appfunctions.ExecuteAppFunctionRequest r0 = r7.$request     // Catch:{ AppFunctionException -> 0x0012 }
            java.lang.String r0 = r0.getFunctionIdentifier()     // Catch:{ AppFunctionException -> 0x0012 }
            r1.append(r0)     // Catch:{ AppFunctionException -> 0x0012 }
            java.lang.String r0 = " in package: "
            r1.append(r0)     // Catch:{ AppFunctionException -> 0x0012 }
            androidx.appfunctions.ExtensionsAppFunctionService r0 = r7.this$0     // Catch:{ AppFunctionException -> 0x0012 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ AppFunctionException -> 0x0012 }
            r1.append(r0)     // Catch:{ AppFunctionException -> 0x0012 }
            java.lang.String r0 = r1.toString()     // Catch:{ AppFunctionException -> 0x0012 }
            r8.<init>(r0)     // Catch:{ AppFunctionException -> 0x0012 }
            throw r8     // Catch:{ AppFunctionException -> 0x0012 }
        L_0x0083:
            androidx.appfunctions.ExecuteAppFunctionResponse$Error r0 = new androidx.appfunctions.ExecuteAppFunctionResponse$Error
            r0.<init>(r8)
            r8 = r0
        L_0x0089:
            boolean r0 = r8 instanceof androidx.appfunctions.ExecuteAppFunctionResponse.Success
            if (r0 == 0) goto L_0x00a2
            androidx.appfunctions.ExecuteAppFunctionResponse$Success r8 = (androidx.appfunctions.ExecuteAppFunctionResponse.Success) r8
            androidx.appfunctions.ExtensionsAppFunctionService r0 = r7.this$0
            android.content.Context r0 = (android.content.Context) r0
            java.lang.String r1 = r7.$callingPackage
            r8.grantUriAccess$appfunctions(r0, r1)
            android.os.OutcomeReceiver<com.android.extensions.appfunctions.ExecuteAppFunctionResponse, com.android.extensions.appfunctions.AppFunctionException> r7 = r7.$callback
            com.android.extensions.appfunctions.ExecuteAppFunctionResponse r8 = r8.toPlatformExtensionClass$appfunctions()
            r7.onResult(r8)
            goto L_0x00b7
        L_0x00a2:
            boolean r0 = r8 instanceof androidx.appfunctions.ExecuteAppFunctionResponse.Error
            if (r0 == 0) goto L_0x00ba
            android.os.OutcomeReceiver<com.android.extensions.appfunctions.ExecuteAppFunctionResponse, com.android.extensions.appfunctions.AppFunctionException> r7 = r7.$callback
            androidx.appfunctions.ExecuteAppFunctionResponse$Error r8 = (androidx.appfunctions.ExecuteAppFunctionResponse.Error) r8
            androidx.appfunctions.AppFunctionException r8 = r8.getError()
            com.android.extensions.appfunctions.AppFunctionException r8 = r8.toPlatformExtensionsClass()
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            r7.onError(r8)
        L_0x00b7:
            me.x r7 = me.x.f4917a
            return r7
        L_0x00ba:
            Dd.a r7 = new Dd.a
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.ExtensionsAppFunctionService$onExecuteFunction$functionExecutionJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
