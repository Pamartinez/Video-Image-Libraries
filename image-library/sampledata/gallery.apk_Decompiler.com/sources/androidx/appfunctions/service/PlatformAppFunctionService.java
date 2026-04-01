package androidx.appfunctions.service;

import Vf.C0886x;
import androidx.appfunctions.AppFunctionService;
import androidx.appfunctions.internal.AggregatedAppFunctionInventory;
import androidx.appfunctions.internal.Dependencies;
import androidx.appfunctions.internal.Dispatchers;
import androidx.appfunctions.internal.TranslatorSelector;
import androidx.appfunctions.service.internal.AggregatedAppFunctionInvoker;
import androidx.appfunctions.service.internal.ServiceDependencies;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0003J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H@¢\u0006\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X.¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/appfunctions/service/PlatformAppFunctionService;", "Landroidx/appfunctions/AppFunctionService;", "<init>", "()V", "Lme/x;", "onCreate", "Landroidx/appfunctions/ExecuteAppFunctionRequest;", "request", "Landroidx/appfunctions/ExecuteAppFunctionResponse;", "executeFunction", "(Landroidx/appfunctions/ExecuteAppFunctionRequest;Lqe/c;)Ljava/lang/Object;", "Landroidx/appfunctions/service/AppFunctionServiceDelegate;", "delegate", "Landroidx/appfunctions/service/AppFunctionServiceDelegate;", "appfunctions-service"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PlatformAppFunctionService extends AppFunctionService {
    private AppFunctionServiceDelegate delegate;

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object executeFunction(androidx.appfunctions.ExecuteAppFunctionRequest r5, qe.C1227c r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.appfunctions.service.PlatformAppFunctionService$executeFunction$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            androidx.appfunctions.service.PlatformAppFunctionService$executeFunction$1 r0 = (androidx.appfunctions.service.PlatformAppFunctionService$executeFunction$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.appfunctions.service.PlatformAppFunctionService$executeFunction$1 r0 = new androidx.appfunctions.service.PlatformAppFunctionService$executeFunction$1
            r0.<init>(r4, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x002f
            if (r2 != r3) goto L_0x0027
            L2.a.A(r6)     // Catch:{ AppFunctionException -> 0x0059, Exception -> 0x0049 }
            goto L_0x003f
        L_0x0027:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x002f:
            L2.a.A(r6)
            androidx.appfunctions.service.AppFunctionServiceDelegate r4 = r4.delegate     // Catch:{ AppFunctionException -> 0x0059, Exception -> 0x0049 }
            if (r4 == 0) goto L_0x0042
            r0.label = r3     // Catch:{ AppFunctionException -> 0x0059, Exception -> 0x0049 }
            java.lang.Object r6 = r4.executeFunction(r5, r0)     // Catch:{ AppFunctionException -> 0x0059, Exception -> 0x0049 }
            if (r6 != r1) goto L_0x003f
            return r1
        L_0x003f:
            androidx.appfunctions.ExecuteAppFunctionResponse r6 = (androidx.appfunctions.ExecuteAppFunctionResponse) r6     // Catch:{ AppFunctionException -> 0x0059, Exception -> 0x0049 }
            return r6
        L_0x0042:
            java.lang.String r4 = "delegate"
            kotlin.jvm.internal.j.k(r4)     // Catch:{ AppFunctionException -> 0x0059, Exception -> 0x0049 }
            r4 = 0
            throw r4     // Catch:{ AppFunctionException -> 0x0059, Exception -> 0x0049 }
        L_0x0049:
            r4 = move-exception
            androidx.appfunctions.ExecuteAppFunctionResponse$Error r5 = new androidx.appfunctions.ExecuteAppFunctionResponse$Error
            androidx.appfunctions.AppFunctionAppUnknownException r6 = new androidx.appfunctions.AppFunctionAppUnknownException
            java.lang.String r4 = r4.getMessage()
            r6.<init>(r4)
            r5.<init>(r6)
            goto L_0x005f
        L_0x0059:
            r4 = move-exception
            androidx.appfunctions.ExecuteAppFunctionResponse$Error r5 = new androidx.appfunctions.ExecuteAppFunctionResponse$Error
            r5.<init>(r4)
        L_0x005f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.service.PlatformAppFunctionService.executeFunction(androidx.appfunctions.ExecuteAppFunctionRequest, qe.c):java.lang.Object");
    }

    public void onCreate() {
        super.onCreate();
        C0886x main = Dispatchers.INSTANCE.getMain();
        Dependencies dependencies = Dependencies.INSTANCE;
        AggregatedAppFunctionInventory aggregatedAppFunctionInventory = dependencies.getAggregatedAppFunctionInventory();
        if (aggregatedAppFunctionInventory != null) {
            AggregatedAppFunctionInvoker aggregatedAppFunctionInvoker$appfunctions_service = ServiceDependencies.INSTANCE.getAggregatedAppFunctionInvoker$appfunctions_service();
            TranslatorSelector translatorSelector = dependencies.getTranslatorSelector();
            this.delegate = new AppFunctionServiceDelegate(this, main, aggregatedAppFunctionInventory, aggregatedAppFunctionInvoker$appfunctions_service, translatorSelector);
            return;
        }
        throw new IllegalStateException("Required value was null.");
    }
}
