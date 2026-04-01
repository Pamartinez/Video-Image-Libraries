package androidx.appfunctions.service;

import android.content.Context;
import androidx.appfunctions.AppFunctionContext;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"androidx/appfunctions/service/AppFunctionServiceDelegate$buildAppFunctionContext$1", "Landroidx/appfunctions/AppFunctionContext;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "appfunctions-service"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionServiceDelegate$buildAppFunctionContext$1 implements AppFunctionContext {
    final /* synthetic */ AppFunctionServiceDelegate this$0;

    public AppFunctionServiceDelegate$buildAppFunctionContext$1(AppFunctionServiceDelegate appFunctionServiceDelegate) {
        this.this$0 = appFunctionServiceDelegate;
    }

    public Context getContext() {
        Context access$getAppContext$p = this.this$0.appContext;
        j.d(access$getAppContext$p, "access$getAppContext$p(...)");
        return access$getAppContext$p;
    }
}
