package androidx.appfunctions;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0015\b\u0016\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\b¨\u0006\t"}, d2 = {"Landroidx/appfunctions/AppFunctionCancelledException;", "Landroidx/appfunctions/AppFunctionSystemException;", "errorMessage", "", "extras", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "(Ljava/lang/String;)V", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionCancelledException extends AppFunctionSystemException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionCancelledException(String str, Bundle bundle) {
        super(2001, str, bundle);
        j.e(bundle, "extras");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppFunctionCancelledException(java.lang.String r3) {
        /*
            r2 = this;
            android.os.Bundle r0 = android.os.Bundle.EMPTY
            java.lang.String r1 = "EMPTY"
            kotlin.jvm.internal.j.d(r0, r1)
            r2.<init>(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.AppFunctionCancelledException.<init>(java.lang.String):void");
    }
}
