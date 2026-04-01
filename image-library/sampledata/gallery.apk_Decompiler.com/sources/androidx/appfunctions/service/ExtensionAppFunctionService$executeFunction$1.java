package androidx.appfunctions.service;

import androidx.appfunctions.ExecuteAppFunctionRequest;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@C1273e(c = "androidx.appfunctions.service.ExtensionAppFunctionService", f = "ExtensionAppFunctionService.kt", l = {54}, m = "executeFunction", v = 1)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExtensionAppFunctionService$executeFunction$1 extends C1271c {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ExtensionAppFunctionService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtensionAppFunctionService$executeFunction$1(ExtensionAppFunctionService extensionAppFunctionService, C1227c cVar) {
        super(cVar);
        this.this$0 = extensionAppFunctionService;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.executeFunction((ExecuteAppFunctionRequest) null, this);
    }
}
