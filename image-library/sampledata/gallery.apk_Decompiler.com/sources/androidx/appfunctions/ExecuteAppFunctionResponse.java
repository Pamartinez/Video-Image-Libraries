package androidx.appfunctions;

import Wf.c;
import android.content.Context;
import i.C0213b;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0001\u0002\u0004\u0005ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/appfunctions/ExecuteAppFunctionResponse;", "", "Success", "Error", "Landroidx/appfunctions/ExecuteAppFunctionResponse$Error;", "Landroidx/appfunctions/ExecuteAppFunctionResponse$Success;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ExecuteAppFunctionResponse {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/appfunctions/ExecuteAppFunctionResponse$Error;", "Landroidx/appfunctions/ExecuteAppFunctionResponse;", "error", "Landroidx/appfunctions/AppFunctionException;", "<init>", "(Landroidx/appfunctions/AppFunctionException;)V", "getError", "()Landroidx/appfunctions/AppFunctionException;", "toString", "", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Error implements ExecuteAppFunctionResponse {
        private final AppFunctionException error;

        public Error(AppFunctionException appFunctionException) {
            j.e(appFunctionException, "error");
            this.error = appFunctionException;
        }

        public final AppFunctionException getError() {
            return this.error;
        }

        public String toString() {
            return "AppFunctionResponse.Error(error=" + this.error + ')';
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\t\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\u00020\nH\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Landroidx/appfunctions/ExecuteAppFunctionResponse$Success;", "Landroidx/appfunctions/ExecuteAppFunctionResponse;", "Landroidx/appfunctions/AppFunctionData;", "returnValue", "<init>", "(Landroidx/appfunctions/AppFunctionData;)V", "Lcom/android/extensions/appfunctions/ExecuteAppFunctionResponse;", "toPlatformExtensionClass$appfunctions", "()Lcom/android/extensions/appfunctions/ExecuteAppFunctionResponse;", "toPlatformExtensionClass", "Landroid/app/appfunctions/ExecuteAppFunctionResponse;", "toPlatformClass$appfunctions", "()Landroid/app/appfunctions/ExecuteAppFunctionResponse;", "toPlatformClass", "Landroid/content/Context;", "context", "", "callingPackageName", "Lme/x;", "grantUriAccess$appfunctions", "(Landroid/content/Context;Ljava/lang/String;)V", "grantUriAccess", "Landroidx/appfunctions/AppFunctionData;", "getReturnValue", "()Landroidx/appfunctions/AppFunctionData;", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Success implements ExecuteAppFunctionResponse {
        public static final Companion Companion = new Companion((e) null);
        private final AppFunctionData returnValue;

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006XT¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/appfunctions/ExecuteAppFunctionResponse$Success$Companion;", "", "<init>", "()V", "", "PROPERTY_RETURN_VALUE", "Ljava/lang/String;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            private Companion() {
            }
        }

        public Success(AppFunctionData appFunctionData) {
            j.e(appFunctionData, "returnValue");
            this.returnValue = appFunctionData;
        }

        /* access modifiers changed from: private */
        public static final x grantUriAccess$lambda$0(Context context, String str, AppFunctionUriGrant appFunctionUriGrant) {
            j.e(appFunctionUriGrant, "uriGrant");
            context.grantUriPermission(str, appFunctionUriGrant.getUri(), appFunctionUriGrant.getModeFlags());
            return x.f4917a;
        }

        public final void grantUriAccess$appfunctions(Context context, String str) {
            j.e(context, "context");
            j.e(str, "callingPackageName");
            this.returnValue.visitAppFunctionUriGrants(new c(9, context, str));
        }

        public final android.app.appfunctions.ExecuteAppFunctionResponse toPlatformClass$appfunctions() {
            C0213b.q();
            return C0213b.b(this.returnValue.getGenericDocument$appfunctions(), this.returnValue.getExtras$appfunctions());
        }

        public final com.android.extensions.appfunctions.ExecuteAppFunctionResponse toPlatformExtensionClass$appfunctions() {
            return new com.android.extensions.appfunctions.ExecuteAppFunctionResponse(this.returnValue.getGenericDocument$appfunctions(), this.returnValue.getExtras$appfunctions());
        }
    }
}
