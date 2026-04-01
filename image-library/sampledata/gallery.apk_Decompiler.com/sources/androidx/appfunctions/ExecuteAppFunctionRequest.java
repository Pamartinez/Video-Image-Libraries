package androidx.appfunctions;

import android.app.appsearch.GenericDocument;
import android.os.Bundle;
import androidx.appfunctions.metadata.AppFunctionMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B)\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\r\u001a\u0004\b\u000f\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\b\u001a\u00020\u00078\u0007¢\u0006\f\n\u0004\b\b\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Landroidx/appfunctions/ExecuteAppFunctionRequest;", "", "", "targetPackageName", "functionIdentifier", "Landroidx/appfunctions/AppFunctionData;", "functionParameters", "", "useJetpackSchema", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroidx/appfunctions/AppFunctionData;Z)V", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getTargetPackageName", "getFunctionIdentifier", "Landroidx/appfunctions/AppFunctionData;", "getFunctionParameters", "()Landroidx/appfunctions/AppFunctionData;", "Z", "getUseJetpackSchema", "()Z", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExecuteAppFunctionRequest {
    public static final Companion Companion = new Companion((e) null);
    private final String functionIdentifier;
    private final AppFunctionData functionParameters;
    private final String targetPackageName;
    private final boolean useJetpackSchema;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001¢\u0006\u0002\b\rJ\u001d\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0001¢\u0006\u0002\b\u0010J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0012H\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/appfunctions/ExecuteAppFunctionRequest$Companion;", "", "<init>", "()V", "EXTRA_PARAMETERS", "", "EXTRA_USE_JETPACK_SCHEMA", "fromPlatformExtensionClass", "Landroidx/appfunctions/ExecuteAppFunctionRequest;", "request", "Lcom/android/extensions/appfunctions/ExecuteAppFunctionRequest;", "functionMetadata", "Landroidx/appfunctions/metadata/AppFunctionMetadata;", "fromPlatformExtensionClass$appfunctions", "fromPlatformClass", "Landroid/app/appfunctions/ExecuteAppFunctionRequest;", "fromPlatformClass$appfunctions", "createAppFunctionDataWithParameterSpec", "Landroidx/appfunctions/AppFunctionData;", "functionIdentifier", "parametersAfd", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private final AppFunctionData createAppFunctionDataWithParameterSpec(AppFunctionMetadata appFunctionMetadata, String str, AppFunctionData appFunctionData) {
            return appFunctionData.replaceSpecWith$appfunctions(appFunctionMetadata.getParameters(), appFunctionMetadata.getComponents());
        }

        public final ExecuteAppFunctionRequest fromPlatformClass$appfunctions(android.app.appfunctions.ExecuteAppFunctionRequest executeAppFunctionRequest, AppFunctionMetadata appFunctionMetadata) {
            j.e(executeAppFunctionRequest, "request");
            j.e(appFunctionMetadata, "functionMetadata");
            String p6 = executeAppFunctionRequest.getTargetPackageName();
            j.d(p6, "getTargetPackageName(...)");
            String f = executeAppFunctionRequest.getFunctionIdentifier();
            j.d(f, "getFunctionIdentifier(...)");
            String f5 = executeAppFunctionRequest.getFunctionIdentifier();
            j.d(f5, "getFunctionIdentifier(...)");
            GenericDocument d = executeAppFunctionRequest.getParameters();
            j.d(d, "getParameters(...)");
            Bundle bundle = executeAppFunctionRequest.getExtras().getBundle("androidXAppfunctionsExtraParameters");
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            j.b(bundle);
            return new ExecuteAppFunctionRequest(p6, f, createAppFunctionDataWithParameterSpec(appFunctionMetadata, f5, new AppFunctionData(d, bundle)), executeAppFunctionRequest.getExtras().getBoolean("androidXAppfunctionsExtraUseJetpackSchema", false));
        }

        public final ExecuteAppFunctionRequest fromPlatformExtensionClass$appfunctions(com.android.extensions.appfunctions.ExecuteAppFunctionRequest executeAppFunctionRequest, AppFunctionMetadata appFunctionMetadata) {
            j.e(executeAppFunctionRequest, "request");
            j.e(appFunctionMetadata, "functionMetadata");
            String targetPackageName = executeAppFunctionRequest.getTargetPackageName();
            j.d(targetPackageName, "getTargetPackageName(...)");
            String functionIdentifier = executeAppFunctionRequest.getFunctionIdentifier();
            j.d(functionIdentifier, "getFunctionIdentifier(...)");
            String functionIdentifier2 = executeAppFunctionRequest.getFunctionIdentifier();
            j.d(functionIdentifier2, "getFunctionIdentifier(...)");
            GenericDocument parameters = executeAppFunctionRequest.getParameters();
            j.d(parameters, "getParameters(...)");
            Bundle bundle = executeAppFunctionRequest.getExtras().getBundle("androidXAppfunctionsExtraParameters");
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            j.b(bundle);
            return new ExecuteAppFunctionRequest(targetPackageName, functionIdentifier, createAppFunctionDataWithParameterSpec(appFunctionMetadata, functionIdentifier2, new AppFunctionData(parameters, bundle)), executeAppFunctionRequest.getExtras().getBoolean("androidXAppfunctionsExtraUseJetpackSchema", false));
        }

        private Companion() {
        }
    }

    public ExecuteAppFunctionRequest(String str, String str2, AppFunctionData appFunctionData, boolean z) {
        j.e(str, "targetPackageName");
        j.e(str2, "functionIdentifier");
        j.e(appFunctionData, "functionParameters");
        this.targetPackageName = str;
        this.functionIdentifier = str2;
        this.functionParameters = appFunctionData;
        this.useJetpackSchema = z;
    }

    public final String getFunctionIdentifier() {
        return this.functionIdentifier;
    }

    public final AppFunctionData getFunctionParameters() {
        return this.functionParameters;
    }

    public final boolean getUseJetpackSchema() {
        return this.useJetpackSchema;
    }

    public String toString() {
        return "ExecuteAppFunctionRequest(targetPackageName=" + this.targetPackageName + ", functionIdentifier=" + this.functionIdentifier + ", functionParameters=" + this.functionParameters + ')';
    }
}
