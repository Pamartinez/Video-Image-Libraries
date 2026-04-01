package com.samsung.android.gallery.crossapp.function;

import B1.a;
import androidx.appfunctions.service.internal.AppFunctionInvoker;
import java.util.Set;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH@¢\u0006\u0004\b\u000b\u0010\fR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\r8\u0016X\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"com/samsung/android/gallery/crossapp/function/$SearchFunctions_AppFunctionInvoker", "Landroidx/appfunctions/service/internal/AppFunctionInvoker;", "<init>", "()V", "Landroidx/appfunctions/AppFunctionContext;", "appFunctionContext", "", "functionIdentifier", "", "", "parameters", "unsafeInvoke", "(Landroidx/appfunctions/AppFunctionContext;Ljava/lang/String;Ljava/util/Map;Lqe/c;)Ljava/lang/Object;", "", "supportedFunctionIds", "Ljava/util/Set;", "getSupportedFunctionIds", "()Ljava/util/Set;", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* renamed from: com.samsung.android.gallery.crossapp.function.$SearchFunctions_AppFunctionInvoker  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$SearchFunctions_AppFunctionInvoker implements AppFunctionInvoker {
    private final Set<String> supportedFunctionIds = a.S("com.samsung.android.gallery.crossapp.function.SearchFunctions#findPhotos");

    /* access modifiers changed from: private */
    public static final SearchFunctions unsafeInvoke$lambda$0() {
        return new SearchFunctions();
    }

    public Set<String> getSupportedFunctionIds() {
        return this.supportedFunctionIds;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object unsafeInvoke(androidx.appfunctions.AppFunctionContext r5, java.lang.String r6, java.util.Map<java.lang.String, ? extends java.lang.Object> r7, qe.C1227c r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof com.samsung.android.gallery.crossapp.function.C$SearchFunctions_AppFunctionInvoker$unsafeInvoke$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.samsung.android.gallery.crossapp.function.$SearchFunctions_AppFunctionInvoker$unsafeInvoke$1 r0 = (com.samsung.android.gallery.crossapp.function.C$SearchFunctions_AppFunctionInvoker$unsafeInvoke$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.gallery.crossapp.function.$SearchFunctions_AppFunctionInvoker$unsafeInvoke$1 r0 = new com.samsung.android.gallery.crossapp.function.$SearchFunctions_AppFunctionInvoker$unsafeInvoke$1
            r0.<init>(r4, r8)
        L_0x0018:
            java.lang.Object r4 = r0.result
            re.a r8 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L_0x002f
            if (r1 != r2) goto L_0x0027
            L2.a.A(r4)
            goto L_0x0070
        L_0x0027:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x002f:
            L2.a.A(r4)
            java.lang.String r4 = "com.samsung.android.gallery.crossapp.function.SearchFunctions#findPhotos"
            boolean r4 = kotlin.jvm.internal.j.a(r6, r4)
            if (r4 == 0) goto L_0x0073
            androidx.appfunctions.service.internal.ConfigurableAppFunctionFactory r4 = new androidx.appfunctions.service.internal.ConfigurableAppFunctionFactory
            android.content.Context r6 = r5.getContext()
            Ad.b r1 = new Ad.b
            r3 = 27
            r1.<init>(r3)
            r4.<init>(r6, r1)
            java.lang.Class<com.samsung.android.gallery.crossapp.function.SearchFunctions> r6 = com.samsung.android.gallery.crossapp.function.SearchFunctions.class
            java.lang.Object r4 = r4.createEnclosingClass(r6)
            com.samsung.android.gallery.crossapp.function.SearchFunctions r4 = (com.samsung.android.gallery.crossapp.function.SearchFunctions) r4
            java.lang.String r6 = "queryString"
            java.lang.Object r6 = r7.get(r6)
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.String"
            kotlin.jvm.internal.j.c(r6, r1)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r1 = "maxCount"
            java.lang.Object r7 = r7.get(r1)
            java.lang.Integer r7 = (java.lang.Integer) r7
            r0.label = r2
            java.lang.Object r4 = r4.findPhotos(r5, r6, r7, r0)
            if (r4 != r8) goto L_0x0070
            return r8
        L_0x0070:
            com.samsung.android.gallery.crossapp.function.FindPhotosResponse r4 = (com.samsung.android.gallery.crossapp.function.FindPhotosResponse) r4
            return r4
        L_0x0073:
            androidx.appfunctions.AppFunctionFunctionNotFoundException r4 = new androidx.appfunctions.AppFunctionFunctionNotFoundException
            java.lang.String r5 = "Unable to find "
            java.lang.String r5 = i.C0212a.l(r5, r6)
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.crossapp.function.C$SearchFunctions_AppFunctionInvoker.unsafeInvoke(androidx.appfunctions.AppFunctionContext, java.lang.String, java.util.Map, qe.c):java.lang.Object");
    }
}
