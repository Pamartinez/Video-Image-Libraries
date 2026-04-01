package androidx.appfunctions;

import Vf.A;
import Vf.C;
import Vf.C0867e0;
import Vf.C0886x;
import Vf.D;
import android.os.CancellationSignal;
import android.os.OutcomeReceiver;
import androidx.appfunctions.internal.Dispatchers;
import com.android.extensions.appfunctions.AppFunctionException;
import com.android.extensions.appfunctions.AppFunctionService;
import com.android.extensions.appfunctions.ExecuteAppFunctionRequest;
import com.android.extensions.appfunctions.ExecuteAppFunctionResponse;
import com.samsung.android.sdk.mobileservice.profile.Profile;
import i.C0214c;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import qe.C1227c;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0011H§@¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u000eH\u0017¢\u0006\u0004\b\u0015\u0010\u0003R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Landroidx/appfunctions/ExtensionsAppFunctionService;", "Lcom/android/extensions/appfunctions/AppFunctionService;", "<init>", "()V", "Lcom/android/extensions/appfunctions/ExecuteAppFunctionRequest;", "request", "", "callingPackage", "Landroid/os/CancellationSignal;", "cancellationSignal", "Landroid/os/OutcomeReceiver;", "Lcom/android/extensions/appfunctions/ExecuteAppFunctionResponse;", "Lcom/android/extensions/appfunctions/AppFunctionException;", "callback", "Lme/x;", "onExecuteFunction", "(Lcom/android/extensions/appfunctions/ExecuteAppFunctionRequest;Ljava/lang/String;Landroid/os/CancellationSignal;Landroid/os/OutcomeReceiver;)V", "Landroidx/appfunctions/ExecuteAppFunctionRequest;", "Landroidx/appfunctions/ExecuteAppFunctionResponse;", "executeFunction", "(Landroidx/appfunctions/ExecuteAppFunctionRequest;Lqe/c;)Ljava/lang/Object;", "onDestroy", "LVf/A;", "workerCoroutineScope", "LVf/A;", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ExtensionsAppFunctionService extends AppFunctionService {
    public static final Companion Companion = new Companion((e) null);
    public static final String SERVICE_INTERFACE = "android.app.appfunctions.AppFunctionService";
    private final A workerCoroutineScope = D.a(Dispatchers.INSTANCE.getWorker());

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/appfunctions/ExtensionsAppFunctionService$Companion;", "", "<init>", "()V", "SERVICE_INTERFACE", "", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* access modifiers changed from: private */
    public static final void onExecuteFunction$lambda$0(C0867e0 e0Var) {
        e0Var.a((CancellationException) null);
    }

    public abstract Object executeFunction(ExecuteAppFunctionRequest executeAppFunctionRequest, C1227c cVar);

    public void onDestroy() {
        ExtensionsAppFunctionService.super.onDestroy();
        D.c(this.workerCoroutineScope);
    }

    public final void onExecuteFunction(ExecuteAppFunctionRequest executeAppFunctionRequest, String str, CancellationSignal cancellationSignal, OutcomeReceiver<ExecuteAppFunctionResponse, AppFunctionException> outcomeReceiver) {
        j.e(executeAppFunctionRequest, "request");
        j.e(str, "callingPackage");
        j.e(cancellationSignal, "cancellationSignal");
        j.e(outcomeReceiver, Profile.PhoneNumberData.TYPE_CALLBACK);
        cancellationSignal.setOnCancelListener(new C0214c(D.n(this.workerCoroutineScope, (C0886x) null, (C) null, new ExtensionsAppFunctionService$onExecuteFunction$functionExecutionJob$1(this, executeAppFunctionRequest, str, outcomeReceiver, (C1227c) null), 3), 1));
    }
}
