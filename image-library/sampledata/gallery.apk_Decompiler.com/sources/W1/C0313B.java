package w1;

import E1.e;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.samsung.android.sum.core.Def;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import z1.C0361a;

/* renamed from: w1.B  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0313B {
    public static final Object g = new Object();

    /* renamed from: h  reason: collision with root package name */
    public static C0313B f1992h;

    /* renamed from: i  reason: collision with root package name */
    public static HandlerThread f1993i;

    /* renamed from: a  reason: collision with root package name */
    public final HashMap f1994a = new HashMap();
    public final Context b;

    /* renamed from: c  reason: collision with root package name */
    public volatile e f1995c;
    public final C0361a d;
    public final long e;
    public final long f;

    /* JADX WARNING: type inference failed for: r2v2, types: [E1.e, android.os.Handler] */
    /* JADX WARNING: type inference failed for: r3v2, types: [z1.a, java.lang.Object] */
    public C0313B(Context context, Looper looper) {
        C0312A a7 = new C0312A(this);
        this.b = context.getApplicationContext();
        ? handler = new Handler(looper, a7);
        Looper.getMainLooper();
        this.f1995c = handler;
        if (C0361a.b == null) {
            synchronized (C0361a.f2201a) {
                try {
                    if (C0361a.b == null) {
                        ? obj = new Object();
                        new ConcurrentHashMap();
                        C0361a.b = obj;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        C0361a aVar = C0361a.b;
        r.b(aVar);
        this.d = aVar;
        this.e = Def.SURFACE_CHANNEL_TIMEOUT_MILLIS;
        this.f = 300000;
    }

    public final void a(String str, ServiceConnection serviceConnection, boolean z) {
        y yVar = new y(str, z);
        r.c(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.f1994a) {
            try {
                z zVar = (z) this.f1994a.get(yVar);
                if (zVar == null) {
                    throw new IllegalStateException("Nonexistent connection status for service config: ".concat(yVar.toString()));
                } else if (zVar.f2022a.containsKey(serviceConnection)) {
                    zVar.f2022a.remove(serviceConnection);
                    if (zVar.f2022a.isEmpty()) {
                        this.f1995c.sendMessageDelayed(this.f1995c.obtainMessage(0, yVar), this.e);
                    }
                } else {
                    throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=".concat(yVar.toString()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean b(y yVar, u uVar, String str) {
        boolean z;
        synchronized (this.f1994a) {
            try {
                z zVar = (z) this.f1994a.get(yVar);
                if (zVar == null) {
                    zVar = new z(this, yVar);
                    zVar.f2022a.put(uVar, uVar);
                    zVar.a(str, (Executor) null);
                    this.f1994a.put(yVar, zVar);
                } else {
                    this.f1995c.removeMessages(0, yVar);
                    if (!zVar.f2022a.containsKey(uVar)) {
                        zVar.f2022a.put(uVar, uVar);
                        int i2 = zVar.b;
                        if (i2 == 1) {
                            uVar.onServiceConnected(zVar.f, zVar.d);
                        } else if (i2 == 2) {
                            zVar.a(str, (Executor) null);
                        }
                    } else {
                        throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=".concat(yVar.toString()));
                    }
                }
                z = zVar.f2023c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }
}
