package androidx.appfunctions;

import android.os.Bundle;
import i.C0213b;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\b&\u0018\u0000 \u001e2\u00060\u0001j\u0002`\u0002:\u0001\u001eB%\b\u0000\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u00038\u0000X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\b\u001a\u00020\u00078\u0000X\u0004¢\u0006\f\n\u0004\b\b\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R \u0010\u001a\u001a\u00020\u00038\u0000X\u0004¢\u0006\u0012\n\u0004\b\u001a\u0010\u0011\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001b\u0010\u0013¨\u0006\u001f"}, d2 = {"Landroidx/appfunctions/AppFunctionException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "", "internalErrorCode", "", "errorMessage", "Landroid/os/Bundle;", "extras", "<init>", "(ILjava/lang/String;Landroid/os/Bundle;)V", "Lcom/android/extensions/appfunctions/AppFunctionException;", "toPlatformExtensionsClass", "()Lcom/android/extensions/appfunctions/AppFunctionException;", "Landroid/app/appfunctions/AppFunctionException;", "toPlatformClass", "()Landroid/app/appfunctions/AppFunctionException;", "I", "getInternalErrorCode$appfunctions", "()I", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "Landroid/os/Bundle;", "getExtras$appfunctions", "()Landroid/os/Bundle;", "errorCategory", "getErrorCategory$appfunctions", "getErrorCategory$appfunctions$annotations", "()V", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppFunctionException extends Exception {
    public static final Companion Companion = new Companion((e) null);
    private final int errorCategory;
    private final String errorMessage;
    private final Bundle extras;
    private final int internalErrorCode;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\u000b\u0010\u0006R\u0014\u0010\f\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\r\u0010\u0006R\u0014\u0010\u000e\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\u000f\u0010\u0006R\u0014\u0010\u0010\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0012\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\u0013\u0010\u0006R\u0014\u0010\u0014\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u00048\u0000XT¢\u0006\u0006\n\u0004\b\u0015\u0010\u0006¨\u0006\u0016"}, d2 = {"Landroidx/appfunctions/AppFunctionException$Companion;", "", "<init>", "()V", "", "ERROR_CATEGORY_UNKNOWN", "I", "ERROR_CATEGORY_REQUEST_ERROR", "ERROR_CATEGORY_SYSTEM", "ERROR_CATEGORY_APP", "ERROR_DENIED", "ERROR_INVALID_ARGUMENT", "ERROR_DISABLED", "ERROR_FUNCTION_NOT_FOUND", "ERROR_RESOURCE_NOT_FOUND", "ERROR_LIMIT_EXCEEDED", "ERROR_RESOURCE_ALREADY_EXISTS", "ERROR_SYSTEM_ERROR", "ERROR_CANCELLED", "ERROR_APP_UNKNOWN_ERROR", "ERROR_PERMISSION_REQUIRED", "ERROR_NOT_SUPPORTED", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionException(int i2, String str, Bundle bundle) {
        super(str);
        int i7;
        j.e(bundle, "extras");
        this.internalErrorCode = i2;
        this.errorMessage = str;
        this.extras = bundle;
        if (1000 <= i2 && i2 < 2000) {
            i7 = 1;
        } else if (2000 <= i2 && i2 < 3000) {
            i7 = 2;
        } else if (3000 > i2 || i2 >= 4000) {
            i7 = 0;
        } else {
            i7 = 3;
        }
        this.errorCategory = i7;
    }

    public final android.app.appfunctions.AppFunctionException toPlatformClass() {
        C0213b.j();
        return C0213b.a(this.internalErrorCode, this.errorMessage, this.extras);
    }

    public final com.android.extensions.appfunctions.AppFunctionException toPlatformExtensionsClass() {
        return new com.android.extensions.appfunctions.AppFunctionException(this.internalErrorCode, this.errorMessage, this.extras);
    }
}
