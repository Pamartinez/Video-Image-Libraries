package androidx.appfunctions;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B%\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/appfunctions/AppFunctionAppException;", "Landroidx/appfunctions/AppFunctionException;", "errorCode", "", "errorMessage", "", "extras", "Landroid/os/Bundle;", "<init>", "(ILjava/lang/String;Landroid/os/Bundle;)V", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppFunctionAppException extends AppFunctionException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionAppException(int i2, String str, Bundle bundle) {
        super(i2, str, bundle);
        j.e(bundle, "extras");
    }
}
