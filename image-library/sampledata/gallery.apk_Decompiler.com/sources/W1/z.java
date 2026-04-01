package w1;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.StrictMode;
import java.util.HashMap;
import java.util.concurrent.Executor;
import z1.C0361a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class z implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final HashMap f2022a = new HashMap();
    public int b = 2;

    /* renamed from: c  reason: collision with root package name */
    public boolean f2023c;
    public IBinder d;
    public final y e;
    public ComponentName f;
    public final /* synthetic */ C0313B g;

    public z(C0313B b5, y yVar) {
        this.g = b5;
        this.e = yVar;
    }

    public final void a(String str, Executor executor) {
        this.b = 3;
        StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
        if (Build.VERSION.SDK_INT >= 31) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(vmPolicy).permitUnsafeIntentLaunch().build());
        }
        try {
            C0313B b5 = this.g;
            C0361a aVar = b5.d;
            Context context = b5.b;
            boolean b8 = aVar.b(context, str, this.e.a(context), this, executor);
            this.f2023c = b8;
            if (b8) {
                this.g.f1995c.sendMessageDelayed(this.g.f1995c.obtainMessage(1, this.e), this.g.f);
            } else {
                this.b = 2;
                try {
                    C0313B b10 = this.g;
                    b10.d.a(b10.b, this);
                } catch (IllegalArgumentException unused) {
                }
            }
            StrictMode.setVmPolicy(vmPolicy);
        } catch (Throwable th) {
            Throwable th2 = th;
            StrictMode.setVmPolicy(vmPolicy);
            throw th2;
        }
    }

    public final void onBindingDied(ComponentName componentName) {
        onServiceDisconnected(componentName);
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.g.f1994a) {
            try {
                this.g.f1995c.removeMessages(1, this.e);
                this.d = iBinder;
                this.f = componentName;
                for (ServiceConnection onServiceConnected : this.f2022a.values()) {
                    onServiceConnected.onServiceConnected(componentName, iBinder);
                }
                this.b = 1;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.g.f1994a) {
            try {
                this.g.f1995c.removeMessages(1, this.e);
                this.d = null;
                this.f = componentName;
                for (ServiceConnection onServiceDisconnected : this.f2022a.values()) {
                    onServiceDisconnected.onServiceDisconnected(componentName);
                }
                this.b = 2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
