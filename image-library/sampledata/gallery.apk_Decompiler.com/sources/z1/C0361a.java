package z1;

import B1.c;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import w1.z;

/* renamed from: z1.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0361a {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f2201a = new Object();
    public static volatile C0361a b;

    public final void a(Context context, z zVar) {
        try {
            context.unbindService(zVar);
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException unused) {
        }
    }

    public final boolean b(Context context, String str, Intent intent, z zVar, Executor executor) {
        ComponentName component = intent.getComponent();
        if (component != null) {
            String packageName = component.getPackageName();
            "com.google.android.gms".equals(packageName);
            try {
                if ((((Context) c.a(context).e).getPackageManager().getApplicationInfo(packageName, 0).flags & 2097152) != 0) {
                    Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
                    return false;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        if (executor == null) {
            executor = null;
        }
        if (executor != null) {
            return context.bindService(intent, 4225, executor, zVar);
        }
        return context.bindService(intent, zVar, 4225);
    }
}
