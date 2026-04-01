package androidx.appfunctions.service;

import android.content.Context;
import androidx.appfunctions.AppFunctionContext;
import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.ExecuteAppFunctionRequest;
import androidx.appfunctions.internal.AggregatedAppFunctionInventory;
import androidx.appfunctions.internal.Translator;
import androidx.appfunctions.internal.TranslatorSelector;
import androidx.appfunctions.metadata.AppFunctionParameterMetadata;
import androidx.appfunctions.metadata.AppFunctionSchemaMetadata;
import androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata;
import androidx.appfunctions.service.internal.AggregatedAppFunctionInvoker;
import androidx.appfunctions.service.internal.AppFunctionDataParameterExtractorKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import oe.C1217f;
import qe.C1232h;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ#\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J7\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00182\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0012H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJH\u0010 \u001a\u00020\u001f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001c2\u0014\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0012H@¢\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\"H\u0002¢\u0006\u0004\b#\u0010$J\u0018\u0010&\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u000eH@¢\u0006\u0004\b&\u0010'R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010(R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010)R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010*R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010+R\u001c\u0010-\u001a\n ,*\u0004\u0018\u00010\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b-\u0010.¨\u0006/"}, d2 = {"Landroidx/appfunctions/service/AppFunctionServiceDelegate;", "", "Landroid/content/Context;", "context", "Lqe/h;", "mainCoroutineContext", "Landroidx/appfunctions/internal/AggregatedAppFunctionInventory;", "aggregatedInventory", "Landroidx/appfunctions/service/internal/AggregatedAppFunctionInvoker;", "aggregatedInvoker", "Landroidx/appfunctions/internal/TranslatorSelector;", "translatorSelector", "<init>", "(Landroid/content/Context;Lqe/h;Landroidx/appfunctions/internal/AggregatedAppFunctionInventory;Landroidx/appfunctions/service/internal/AggregatedAppFunctionInvoker;Landroidx/appfunctions/internal/TranslatorSelector;)V", "Landroidx/appfunctions/ExecuteAppFunctionRequest;", "request", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "schemaMetadata", "Landroidx/appfunctions/internal/Translator;", "getTranslator", "(Landroidx/appfunctions/ExecuteAppFunctionRequest;Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;)Landroidx/appfunctions/internal/Translator;", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "appFunctionMetadata", "translator", "", "", "extractParameters", "(Landroidx/appfunctions/ExecuteAppFunctionRequest;Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;Landroidx/appfunctions/internal/Translator;)Ljava/util/Map;", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "componentsMetadata", "parameters", "Landroidx/appfunctions/ExecuteAppFunctionResponse;", "unsafeInvokeFunction", "(Landroidx/appfunctions/ExecuteAppFunctionRequest;Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;Ljava/util/Map;Landroidx/appfunctions/internal/Translator;Lqe/c;)Ljava/lang/Object;", "Landroidx/appfunctions/AppFunctionContext;", "buildAppFunctionContext", "()Landroidx/appfunctions/AppFunctionContext;", "executeAppFunctionRequest", "executeFunction", "(Landroidx/appfunctions/ExecuteAppFunctionRequest;Lqe/c;)Ljava/lang/Object;", "Lqe/h;", "Landroidx/appfunctions/internal/AggregatedAppFunctionInventory;", "Landroidx/appfunctions/service/internal/AggregatedAppFunctionInvoker;", "Landroidx/appfunctions/internal/TranslatorSelector;", "kotlin.jvm.PlatformType", "appContext", "Landroid/content/Context;", "appfunctions-service"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionServiceDelegate {
    private final AggregatedAppFunctionInventory aggregatedInventory;
    /* access modifiers changed from: private */
    public final AggregatedAppFunctionInvoker aggregatedInvoker;
    /* access modifiers changed from: private */
    public final Context appContext;
    private final C1232h mainCoroutineContext;
    private final TranslatorSelector translatorSelector;

    public AppFunctionServiceDelegate(Context context, C1232h hVar, AggregatedAppFunctionInventory aggregatedAppFunctionInventory, AggregatedAppFunctionInvoker aggregatedAppFunctionInvoker, TranslatorSelector translatorSelector2) {
        j.e(context, "context");
        j.e(hVar, "mainCoroutineContext");
        j.e(aggregatedAppFunctionInventory, "aggregatedInventory");
        j.e(aggregatedAppFunctionInvoker, "aggregatedInvoker");
        j.e(translatorSelector2, "translatorSelector");
        this.mainCoroutineContext = hVar;
        this.aggregatedInventory = aggregatedAppFunctionInventory;
        this.aggregatedInvoker = aggregatedAppFunctionInvoker;
        this.translatorSelector = translatorSelector2;
        this.appContext = context.getApplicationContext();
    }

    /* access modifiers changed from: private */
    public final AppFunctionContext buildAppFunctionContext() {
        return new AppFunctionServiceDelegate$buildAppFunctionContext$1(this);
    }

    private final Map<String, Object> extractParameters(ExecuteAppFunctionRequest executeAppFunctionRequest, CompileTimeAppFunctionMetadata compileTimeAppFunctionMetadata, Translator translator) {
        AppFunctionData appFunctionData;
        if (translator == null || (appFunctionData = translator.upgradeRequest(executeAppFunctionRequest.getFunctionParameters())) == null) {
            appFunctionData = executeAppFunctionRequest.getFunctionParameters();
        }
        C1217f fVar = new C1217f();
        for (AppFunctionParameterMetadata next : compileTimeAppFunctionMetadata.getParameters()) {
            fVar.put(next.getName(), AppFunctionDataParameterExtractorKt.unsafeGetParameterValue(appFunctionData, next));
        }
        return fVar.b();
    }

    private final Translator getTranslator(ExecuteAppFunctionRequest executeAppFunctionRequest, AppFunctionSchemaMetadata appFunctionSchemaMetadata) {
        if (!executeAppFunctionRequest.getUseJetpackSchema() && appFunctionSchemaMetadata != null) {
            this.translatorSelector.getTranslator(appFunctionSchemaMetadata);
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: androidx.appfunctions.metadata.AppFunctionComponentsMetadata} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object unsafeInvokeFunction(androidx.appfunctions.ExecuteAppFunctionRequest r6, androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata r7, androidx.appfunctions.metadata.AppFunctionComponentsMetadata r8, java.util.Map<java.lang.String, ? extends java.lang.Object> r9, androidx.appfunctions.internal.Translator r10, qe.C1227c r11) {
        /*
            r5 = this;
            boolean r0 = r11 instanceof androidx.appfunctions.service.AppFunctionServiceDelegate$unsafeInvokeFunction$1
            if (r0 == 0) goto L_0x0013
            r0 = r11
            androidx.appfunctions.service.AppFunctionServiceDelegate$unsafeInvokeFunction$1 r0 = (androidx.appfunctions.service.AppFunctionServiceDelegate$unsafeInvokeFunction$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.appfunctions.service.AppFunctionServiceDelegate$unsafeInvokeFunction$1 r0 = new androidx.appfunctions.service.AppFunctionServiceDelegate$unsafeInvokeFunction$1
            r0.<init>(r5, r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 != r4) goto L_0x003d
            java.lang.Object r5 = r0.L$2
            if (r5 != 0) goto L_0x0037
            java.lang.Object r5 = r0.L$1
            r8 = r5
            androidx.appfunctions.metadata.AppFunctionComponentsMetadata r8 = (androidx.appfunctions.metadata.AppFunctionComponentsMetadata) r8
            java.lang.Object r5 = r0.L$0
            r7 = r5
            androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata r7 = (androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata) r7
            L2.a.A(r11)
            r10 = r3
            goto L_0x005e
        L_0x0037:
            java.lang.ClassCastException r5 = new java.lang.ClassCastException
            r5.<init>()
            throw r5
        L_0x003d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0045:
            L2.a.A(r11)
            qe.h r11 = r5.mainCoroutineContext
            androidx.appfunctions.service.AppFunctionServiceDelegate$unsafeInvokeFunction$result$1 r2 = new androidx.appfunctions.service.AppFunctionServiceDelegate$unsafeInvokeFunction$result$1
            r2.<init>(r5, r6, r9, r3)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r10
            r0.label = r4
            java.lang.Object r11 = Vf.D.w(r11, r2, r0)
            if (r11 != r1) goto L_0x005e
            return r1
        L_0x005e:
            androidx.appfunctions.metadata.AppFunctionResponseMetadata r5 = r7.getResponse()
            androidx.appfunctions.AppFunctionData r5 = androidx.appfunctions.service.internal.AppFunctionResponseMetadataReturnValueBuilderKt.unsafeBuildReturnValue((androidx.appfunctions.metadata.AppFunctionResponseMetadata) r5, (java.lang.Object) r11, (androidx.appfunctions.metadata.AppFunctionComponentsMetadata) r8)
            if (r10 == 0) goto L_0x0070
            androidx.appfunctions.AppFunctionData r6 = r10.downgradeResponse(r5)
            if (r6 != 0) goto L_0x006f
            goto L_0x0070
        L_0x006f:
            r5 = r6
        L_0x0070:
            androidx.appfunctions.ExecuteAppFunctionResponse$Success r6 = new androidx.appfunctions.ExecuteAppFunctionResponse$Success
            r6.<init>(r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.service.AppFunctionServiceDelegate.unsafeInvokeFunction(androidx.appfunctions.ExecuteAppFunctionRequest, androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata, androidx.appfunctions.metadata.AppFunctionComponentsMetadata, java.util.Map, androidx.appfunctions.internal.Translator, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v28, resolved type: androidx.appfunctions.ExecuteAppFunctionRequest} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object executeFunction(androidx.appfunctions.ExecuteAppFunctionRequest r12, qe.C1227c r13) {
        /*
            r11 = this;
            java.lang.String r0 = " is not available"
            boolean r1 = r13 instanceof androidx.appfunctions.service.AppFunctionServiceDelegate$executeFunction$1
            if (r1 == 0) goto L_0x0016
            r1 = r13
            androidx.appfunctions.service.AppFunctionServiceDelegate$executeFunction$1 r1 = (androidx.appfunctions.service.AppFunctionServiceDelegate$executeFunction$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0016
            int r2 = r2 - r3
            r1.label = r2
        L_0x0014:
            r8 = r1
            goto L_0x001c
        L_0x0016:
            androidx.appfunctions.service.AppFunctionServiceDelegate$executeFunction$1 r1 = new androidx.appfunctions.service.AppFunctionServiceDelegate$executeFunction$1
            r1.<init>(r11, r13)
            goto L_0x0014
        L_0x001c:
            java.lang.Object r13 = r8.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r8.label
            java.lang.String r9 = "Failed to invoke "
            r3 = 1
            java.lang.String r10 = "AppFunctions"
            if (r2 == 0) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            java.lang.Object r11 = r8.L$0
            r12 = r11
            androidx.appfunctions.ExecuteAppFunctionRequest r12 = (androidx.appfunctions.ExecuteAppFunctionRequest) r12
            L2.a.A(r13)     // Catch:{ CancellationException -> 0x003c, AppFunctionException -> 0x0038, Exception -> 0x0034 }
            goto L_0x007e
        L_0x0034:
            r0 = move-exception
        L_0x0035:
            r11 = r0
            goto L_0x00c6
        L_0x0038:
            r0 = move-exception
        L_0x0039:
            r11 = r0
            goto L_0x00e3
        L_0x003c:
            r0 = move-exception
        L_0x003d:
            r11 = r0
            goto L_0x00f7
        L_0x0040:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0048:
            L2.a.A(r13)
            androidx.appfunctions.internal.AggregatedAppFunctionInventory r13 = r11.aggregatedInventory     // Catch:{ CancellationException -> 0x0093, AppFunctionException -> 0x0090, Exception -> 0x008d }
            java.util.Map r13 = r13.getFunctionIdToMetadataMap()     // Catch:{ CancellationException -> 0x0093, AppFunctionException -> 0x0090, Exception -> 0x008d }
            java.lang.String r2 = r12.getFunctionIdentifier()     // Catch:{ CancellationException -> 0x0093, AppFunctionException -> 0x0090, Exception -> 0x008d }
            java.lang.Object r13 = r13.get(r2)     // Catch:{ CancellationException -> 0x0093, AppFunctionException -> 0x0090, Exception -> 0x008d }
            r4 = r13
            androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata r4 = (androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata) r4     // Catch:{ CancellationException -> 0x0093, AppFunctionException -> 0x0090, Exception -> 0x008d }
            if (r4 == 0) goto L_0x0096
            androidx.appfunctions.metadata.AppFunctionSchemaMetadata r13 = r4.getSchema()     // Catch:{ CancellationException -> 0x0093, AppFunctionException -> 0x0090, Exception -> 0x008d }
            r11.getTranslator(r12, r13)     // Catch:{ CancellationException -> 0x0093, AppFunctionException -> 0x0090, Exception -> 0x008d }
            r7 = 0
            java.util.Map r6 = r11.extractParameters(r12, r4, r7)     // Catch:{ CancellationException -> 0x0093, AppFunctionException -> 0x0090, Exception -> 0x008d }
            androidx.appfunctions.internal.AggregatedAppFunctionInventory r13 = r11.aggregatedInventory     // Catch:{ CancellationException -> 0x0093, AppFunctionException -> 0x0090, Exception -> 0x008d }
            androidx.appfunctions.metadata.AppFunctionComponentsMetadata r5 = r13.getComponentsMetadata()     // Catch:{ CancellationException -> 0x0093, AppFunctionException -> 0x0090, Exception -> 0x008d }
            r8.L$0 = r12     // Catch:{ CancellationException -> 0x0093, AppFunctionException -> 0x0090, Exception -> 0x008d }
            r8.label = r3     // Catch:{ CancellationException -> 0x0093, AppFunctionException -> 0x0090, Exception -> 0x008d }
            r2 = r11
            r3 = r12
            java.lang.Object r13 = r2.unsafeInvokeFunction(r3, r4, r5, r6, r7, r8)     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            if (r13 != r1) goto L_0x007d
            return r1
        L_0x007d:
            r12 = r3
        L_0x007e:
            androidx.appfunctions.ExecuteAppFunctionResponse r13 = (androidx.appfunctions.ExecuteAppFunctionResponse) r13     // Catch:{ CancellationException -> 0x003c, AppFunctionException -> 0x0038, Exception -> 0x0034 }
            return r13
        L_0x0081:
            r0 = move-exception
            r11 = r0
            r12 = r3
            goto L_0x00c6
        L_0x0085:
            r0 = move-exception
            r11 = r0
            r12 = r3
            goto L_0x00e3
        L_0x0089:
            r0 = move-exception
            r11 = r0
            r12 = r3
            goto L_0x00f7
        L_0x008d:
            r0 = move-exception
            r3 = r12
            goto L_0x0035
        L_0x0090:
            r0 = move-exception
            r3 = r12
            goto L_0x0039
        L_0x0093:
            r0 = move-exception
            r3 = r12
            goto L_0x003d
        L_0x0096:
            r3 = r12
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            r11.<init>()     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            java.lang.String r12 = r3.getFunctionIdentifier()     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            r11.append(r12)     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            r11.append(r0)     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            java.lang.String r11 = r11.toString()     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            android.util.Log.d(r10, r11)     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            androidx.appfunctions.AppFunctionFunctionNotFoundException r11 = new androidx.appfunctions.AppFunctionFunctionNotFoundException     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            r12.<init>()     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            java.lang.String r13 = r3.getFunctionIdentifier()     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            r12.append(r13)     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            r12.append(r0)     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            java.lang.String r12 = r12.toString()     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            r11.<init>(r12)     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
            throw r11     // Catch:{ CancellationException -> 0x0089, AppFunctionException -> 0x0085, Exception -> 0x0081 }
        L_0x00c6:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r9)
            java.lang.String r12 = r12.getFunctionIdentifier()
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            android.util.Log.d(r10, r12, r11)
            androidx.appfunctions.AppFunctionAppUnknownException r12 = new androidx.appfunctions.AppFunctionAppUnknownException
            java.lang.String r11 = r11.getMessage()
            r12.<init>(r11)
            throw r12
        L_0x00e3:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r9)
            java.lang.String r12 = r12.getFunctionIdentifier()
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            android.util.Log.d(r10, r12, r11)
            throw r11
        L_0x00f7:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = "Invocation of "
            r13.<init>(r0)
            java.lang.String r12 = r12.getFunctionIdentifier()
            r13.append(r12)
            java.lang.String r12 = " was cancelled"
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            android.util.Log.d(r10, r12, r11)
            androidx.appfunctions.AppFunctionCancelledException r12 = new androidx.appfunctions.AppFunctionCancelledException
            java.lang.String r11 = r11.getMessage()
            r12.<init>(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.service.AppFunctionServiceDelegate.executeFunction(androidx.appfunctions.ExecuteAppFunctionRequest, qe.c):java.lang.Object");
    }
}
