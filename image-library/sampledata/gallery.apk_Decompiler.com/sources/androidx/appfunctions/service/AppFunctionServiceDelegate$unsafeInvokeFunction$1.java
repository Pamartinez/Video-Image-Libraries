package androidx.appfunctions.service;

import androidx.appfunctions.ExecuteAppFunctionRequest;
import androidx.appfunctions.internal.Translator;
import androidx.appfunctions.metadata.AppFunctionComponentsMetadata;
import androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata;
import java.util.Map;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@C1273e(c = "androidx.appfunctions.service.AppFunctionServiceDelegate", f = "AppFunctionServiceDelegate.kt", l = {141}, m = "unsafeInvokeFunction", v = 1)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionServiceDelegate$unsafeInvokeFunction$1 extends C1271c {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AppFunctionServiceDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionServiceDelegate$unsafeInvokeFunction$1(AppFunctionServiceDelegate appFunctionServiceDelegate, C1227c cVar) {
        super(cVar);
        this.this$0 = appFunctionServiceDelegate;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.unsafeInvokeFunction((ExecuteAppFunctionRequest) null, (CompileTimeAppFunctionMetadata) null, (AppFunctionComponentsMetadata) null, (Map<String, ? extends Object>) null, (Translator) null, this);
    }
}
