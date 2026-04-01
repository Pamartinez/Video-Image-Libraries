package com.samsung.android.gallery.crossapp.function;

import androidx.appfunctions.AppFunctionContext;
import java.util.Map;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@C1273e(c = "com.samsung.android.gallery.crossapp.function.$SearchFunctions_AppFunctionInvoker", f = "$SearchFunctions_AppFunctionInvoker.kt", l = {30}, m = "unsafeInvoke")
/* renamed from: com.samsung.android.gallery.crossapp.function.$SearchFunctions_AppFunctionInvoker$unsafeInvoke$1  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$SearchFunctions_AppFunctionInvoker$unsafeInvoke$1 extends C1271c {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ C$SearchFunctions_AppFunctionInvoker this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C$SearchFunctions_AppFunctionInvoker$unsafeInvoke$1(C$SearchFunctions_AppFunctionInvoker _searchfunctions_appfunctioninvoker, C1227c cVar) {
        super(cVar);
        this.this$0 = _searchfunctions_appfunctioninvoker;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.unsafeInvoke((AppFunctionContext) null, (String) null, (Map<String, ? extends Object>) null, this);
    }
}
