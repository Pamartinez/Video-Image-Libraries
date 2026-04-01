package androidx.appfunctions.internal;

import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@C1273e(c = "androidx.appfunctions.internal.AppSearchAppFunctionReader", f = "AppSearchAppFunctionReader.kt", l = {358, 370, 380, 406}, m = "getAppFunctionMetadata", v = 1)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppSearchAppFunctionReader$getAppFunctionMetadata$1 extends C1271c {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AppSearchAppFunctionReader this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppSearchAppFunctionReader$getAppFunctionMetadata$1(AppSearchAppFunctionReader appSearchAppFunctionReader, C1227c cVar) {
        super(cVar);
        this.this$0 = appSearchAppFunctionReader;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getAppFunctionMetadata((String) null, (String) null, this);
    }
}
