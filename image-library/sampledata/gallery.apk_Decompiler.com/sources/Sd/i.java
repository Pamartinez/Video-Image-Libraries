package Sd;

import android.content.Context;
import android.os.Bundle;
import com.samsung.scsp.framework.core.api.Response;
import i.C0212a;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class i {

    /* renamed from: c  reason: collision with root package name */
    public static final Bundle f3703c;
    public static final Bundle d;

    /* renamed from: a  reason: collision with root package name */
    public final Context f3704a;
    public final String b;

    static {
        Bundle bundle = new Bundle();
        f3703c = bundle;
        bundle.putInt("rcode", 90300002);
        bundle.putString(Response.RMSG, "IPC error. call result is null");
        bundle.putInt("result", 2);
        Bundle bundle2 = new Bundle();
        d = bundle2;
        bundle2.putInt("rcode", 90300002);
        bundle2.putString(Response.RMSG, "IPC error. ContentProviderClient is null");
        bundle2.putInt("result", 2);
    }

    public i(Context context, String str) {
        this.f3704a = context;
        this.b = C0212a.m("[SCG-SDK][0.0.0019][", str, "]");
    }

    public static Bundle b(Throwable th) {
        Bundle bundle = new Bundle();
        bundle.putInt("rcode", 90000000);
        bundle.putString(Response.RMSG, "IPC error. " + th.getMessage());
        bundle.putInt("result", 2);
        return bundle;
    }

    public abstract boolean a(Bundle bundle, String str, Consumer consumer);
}
