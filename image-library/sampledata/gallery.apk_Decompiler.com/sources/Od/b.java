package Od;

import a.C0068a;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.protobuf.ExtensionRegistryLite;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public int f3609a;
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public Object f3610c;

    public b(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.getClass();
    }

    public boolean a() {
        SharedPreferences E = c.E((Context) this.f3610c);
        boolean z = false;
        if (this.b == 0) {
            this.b = E.getLong("deleteCountResetTime", 0);
            this.f3609a = E.getInt("deleteCount", 0);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (TimeUnit.DAYS.toMillis(1) + this.b < currentTimeMillis) {
            C0068a.g("Initialize delete api call counting");
            this.b = currentTimeMillis;
            this.f3609a = 0;
            SharedPreferences.Editor edit = E.edit();
            edit.putInt("deleteCount", this.f3609a);
            edit.putLong("deleteCountResetTime", this.b).apply();
            return true;
        }
        if (this.f3609a < 5) {
            z = true;
        }
        if (!z) {
            C0068a.g("SDK operation was stopped for 24 hours due to excessive delete API calls");
        }
        return z;
    }
}
