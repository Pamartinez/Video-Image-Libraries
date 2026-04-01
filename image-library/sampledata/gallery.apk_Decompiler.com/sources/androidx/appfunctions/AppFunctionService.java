package androidx.appfunctions;

import Vf.A;
import Vf.C;
import Vf.C0867e0;
import Vf.C0886x;
import Vf.D;
import android.app.appfunctions.AppFunctionException;
import android.app.appfunctions.ExecuteAppFunctionRequest;
import android.app.appfunctions.ExecuteAppFunctionResponse;
import android.content.pm.SigningInfo;
import android.os.CancellationSignal;
import android.os.OutcomeReceiver;
import androidx.appfunctions.internal.Dispatchers;
import com.samsung.android.sdk.mobileservice.profile.Profile;
import i.C0214c;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import qe.C1227c;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JA\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0013H§@¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0010H\u0017¢\u0006\u0004\b\u0017\u0010\u0003R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Landroidx/appfunctions/AppFunctionService;", "Landroid/app/appfunctions/AppFunctionService;", "<init>", "()V", "Landroid/app/appfunctions/ExecuteAppFunctionRequest;", "request", "", "callingPackage", "Landroid/content/pm/SigningInfo;", "signingInfo", "Landroid/os/CancellationSignal;", "cancellationSignal", "Landroid/os/OutcomeReceiver;", "Landroid/app/appfunctions/ExecuteAppFunctionResponse;", "Landroid/app/appfunctions/AppFunctionException;", "callback", "Lme/x;", "onExecuteFunction", "(Landroid/app/appfunctions/ExecuteAppFunctionRequest;Ljava/lang/String;Landroid/content/pm/SigningInfo;Landroid/os/CancellationSignal;Landroid/os/OutcomeReceiver;)V", "Landroidx/appfunctions/ExecuteAppFunctionRequest;", "Landroidx/appfunctions/ExecuteAppFunctionResponse;", "executeFunction", "(Landroidx/appfunctions/ExecuteAppFunctionRequest;Lqe/c;)Ljava/lang/Object;", "onDestroy", "LVf/A;", "workerCoroutineScope", "LVf/A;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppFunctionService extends android.app.appfunctions.AppFunctionService {
    private final A workerCoroutineScope = D.a(Dispatchers.INSTANCE.getWorker());

    /* access modifiers changed from: private */
    public static final void onExecuteFunction$lambda$0(C0867e0 e0Var) {
        e0Var.a((CancellationException) null);
    }

    public abstract Object executeFunction(ExecuteAppFunctionRequest executeAppFunctionRequest, C1227c cVar);

    public void onDestroy() {
        super.onDestroy();
        D.c(this.workerCoroutineScope);
    }

    public final void onExecuteFunction(ExecuteAppFunctionRequest executeAppFunctionRequest, String str, SigningInfo signingInfo, CancellationSignal cancellationSignal, OutcomeReceiver<ExecuteAppFunctionResponse, AppFunctionException> outcomeReceiver) {
        j.e(executeAppFunctionRequest, "request");
        j.e(str, "callingPackage");
        j.e(signingInfo, "signingInfo");
        j.e(cancellationSignal, "cancellationSignal");
        j.e(outcomeReceiver, Profile.PhoneNumberData.TYPE_CALLBACK);
        cancellationSignal.setOnCancelListener(new C0214c(D.n(this.workerCoroutineScope, (C0886x) null, (C) null, new AppFunctionService$onExecuteFunction$functionExecutionJob$1(this, executeAppFunctionRequest, str, outcomeReceiver, (C1227c) null), 3), 0));
    }
}
