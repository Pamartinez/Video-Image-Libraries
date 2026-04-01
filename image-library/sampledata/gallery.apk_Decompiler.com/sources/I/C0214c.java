package i;

import Vf.u0;
import android.os.CancellationSignal;
import androidx.appfunctions.AppFunctionService;
import androidx.appfunctions.ExtensionsAppFunctionService;

/* renamed from: i.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0214c implements CancellationSignal.OnCancelListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1781a;
    public final /* synthetic */ u0 b;

    public /* synthetic */ C0214c(u0 u0Var, int i2) {
        this.f1781a = i2;
        this.b = u0Var;
    }

    public final void onCancel() {
        int i2 = this.f1781a;
        u0 u0Var = this.b;
        switch (i2) {
            case 0:
                AppFunctionService.onExecuteFunction$lambda$0(u0Var);
                return;
            default:
                ExtensionsAppFunctionService.onExecuteFunction$lambda$0(u0Var);
                return;
        }
    }
}
