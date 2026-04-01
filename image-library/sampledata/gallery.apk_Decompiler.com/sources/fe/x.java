package fe;

import G0.a;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import ee.a0;
import ie.c;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x implements ServiceConnection {

    /* renamed from: i  reason: collision with root package name */
    public static final Logger f4369i = Logger.getLogger(x.class.getName());

    /* renamed from: a  reason: collision with root package name */
    public final c f4370a;
    public final Intent b;

    /* renamed from: c  reason: collision with root package name */
    public final int f4371c;
    public final c d;
    public final Executor e;
    public w f;
    public Context g;

    /* renamed from: h  reason: collision with root package name */
    public w f4372h;

    public x(Executor executor, Context context, c cVar, Intent intent, int i2, c cVar2) {
        synchronized (this) {
            this.b = intent;
            this.f4371c = i2;
            this.d = cVar2;
            this.g = context;
            this.e = executor;
            this.f4370a = cVar;
            w wVar = w.NOT_BINDING;
            this.f = wVar;
            this.f4372h = wVar;
        }
    }

    public static a0 b(Context context, Intent intent, x xVar, int i2, c cVar) {
        boolean z;
        Intent intent2;
        v vVar = v.BIND_SERVICE;
        try {
            int i7 = u.f4368a[vVar.ordinal()];
            if (i7 == 1) {
                intent2 = intent;
                z = context.bindService(intent2, xVar, i2);
            } else if (i7 == 2) {
                intent2 = intent;
                z = context.bindServiceAsUser(intent2, xVar, i2, (UserHandle) null);
            } else if (i7 != 3) {
                z = false;
                intent2 = intent;
            } else {
                intent2 = intent;
                z = ((DevicePolicyManager) context.getSystemService("device_policy")).bindDeviceAdminServiceAsUser((ComponentName) null, intent2, xVar, i2, (UserHandle) null);
            }
            if (z) {
                return a0.e;
            }
            a0 a0Var = a0.m;
            return a0Var.g(vVar.a() + "(" + intent2 + ") returned false");
        } catch (SecurityException e7) {
            a0 f5 = a0.f4287i.f(e7);
            return f5.g("SecurityException from " + vVar.a());
        } catch (RuntimeException e8) {
            a0 f8 = a0.n.f(e8);
            return f8.g("RuntimeException from " + vVar.a());
        }
    }

    public final synchronized void a() {
        if (this.f == w.NOT_BINDING) {
            this.f = w.BINDING;
            a0 b5 = b(this.g, this.b, this, this.f4371c, this.f4370a);
            if (!b5.e()) {
                try {
                    this.g.unbindService(this);
                } catch (RuntimeException e7) {
                    f4369i.log(Level.FINE, "Could not clean up after bindService() failure.", e7);
                }
                this.f = w.UNBOUND;
                this.e.execute(new t(this, b5, 0));
            }
        }
    }

    public final void c(a0 a0Var) {
        Logger logger = f4369i;
        Level level = Level.FINEST;
        logger.log(level, "notify unbound ", a0Var);
        this.g = null;
        w wVar = this.f4372h;
        w wVar2 = w.UNBOUND;
        if (wVar != wVar2) {
            this.f4372h = wVar2;
            logger.log(level, "notify unbound - notifying");
            c cVar = this.d;
            synchronized (cVar) {
                cVar.o(a0Var, true);
            }
        }
    }

    public final void d(a0 a0Var) {
        Context context;
        synchronized (this) {
            try {
                w wVar = this.f;
                if (wVar != w.BINDING) {
                    if (wVar != w.BOUND) {
                        context = null;
                        this.f = w.UNBOUND;
                    }
                }
                context = this.g;
                this.f = w.UNBOUND;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.e.execute(new t(this, a0Var, 1));
        if (context != null) {
            context.unbindService(this);
        }
    }

    public final void onBindingDied(ComponentName componentName) {
        a0 a0Var = a0.f4289o;
        d(a0Var.g("onBindingDied: " + componentName));
    }

    public final void onNullBinding(ComponentName componentName) {
        a0 a0Var = a0.m;
        d(a0Var.g("onNullBinding: " + componentName));
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        boolean z;
        o oVar;
        a f5;
        synchronized (this) {
            try {
                if (this.f == w.BINDING) {
                    this.f = w.BOUND;
                    z = true;
                } else {
                    z = false;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (z && this.f4372h == w.NOT_BINDING) {
            this.f4372h = w.BOUND;
            f4369i.log(Level.FINEST, "notify bound - notifying");
            c cVar = this.d;
            synchronized (cVar) {
                Executor executor = cVar.f4342o;
                Logger logger = o.b;
                if (iBinder instanceof Binder) {
                    oVar = new m(iBinder, executor);
                } else {
                    oVar = new o(iBinder);
                }
                try {
                    f5 = a.f();
                    f5.c().writeInt(1);
                    f5.c().writeStrongBinder(cVar.d);
                    oVar.a(1, f5);
                    f5.close();
                } catch (RemoteException e7) {
                    cVar.o(c.p(e7), true);
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            return;
        }
        return;
        throw th;
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        a0 a0Var = a0.f4289o;
        d(a0Var.g("onServiceDisconnected: " + componentName));
    }
}
