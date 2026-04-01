package com.google.android.gms.common.internal;

import B1.b;
import B2.A;
import Fd.C0744a;
import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import t1.C0276a;
import t1.C0278c;
import t1.C0279d;
import t1.f;
import u1.C0285c;
import u1.h;
import v1.k;
import w1.C0313B;
import w1.C0315b;
import w1.d;
import w1.e;
import w1.g;
import w1.o;
import w1.q;
import w1.r;
import w1.s;
import w1.t;
import w1.u;
import w1.v;
import w1.w;
import w1.x;
import w1.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a implements C0285c {

    /* renamed from: x  reason: collision with root package name */
    public static final C0278c[] f1337x = new C0278c[0];

    /* renamed from: a  reason: collision with root package name */
    public volatile String f1338a;
    public Yd.a b;

    /* renamed from: c  reason: collision with root package name */
    public final Context f1339c;
    public final C0313B d;
    public final s e;
    public final Object f;
    public final Object g;

    /* renamed from: h  reason: collision with root package name */
    public q f1340h;

    /* renamed from: i  reason: collision with root package name */
    public C0315b f1341i;

    /* renamed from: j  reason: collision with root package name */
    public IInterface f1342j;
    public final ArrayList k;
    public u l;
    public int m;
    public final g n;

    /* renamed from: o  reason: collision with root package name */
    public final g f1343o;

    /* renamed from: p  reason: collision with root package name */
    public final int f1344p;
    public final String q;
    public volatile String r;
    public C0276a s;
    public boolean t;
    public volatile x u;
    public final AtomicInteger v;
    public final Set w;

    public a(Context context, Looper looper, int i2, C0744a aVar, u1.g gVar, h hVar) {
        synchronized (C0313B.g) {
            try {
                if (C0313B.f1992h == null) {
                    C0313B.f1992h = new C0313B(context.getApplicationContext(), context.getMainLooper());
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        C0313B b5 = C0313B.f1992h;
        Object obj = C0279d.f1922c;
        r.b(gVar);
        r.b(hVar);
        g gVar2 = new g(gVar);
        g gVar3 = new g(hVar);
        this.f1338a = null;
        this.f = new Object();
        this.g = new Object();
        this.k = new ArrayList();
        this.m = 1;
        this.s = null;
        this.t = false;
        this.u = null;
        this.v = new AtomicInteger(0);
        r.c(context, "Context must not be null");
        this.f1339c = context;
        r.c(looper, "Looper must not be null");
        r.c(b5, "Supervisor must not be null");
        this.d = b5;
        this.e = new s(this, looper);
        this.f1344p = i2;
        this.n = gVar2;
        this.f1343o = gVar3;
        this.q = (String) aVar.e;
        Set<Scope> set = (Set) aVar.g;
        for (Scope contains : set) {
            if (!set.contains(contains)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        this.w = set;
    }

    public static /* bridge */ /* synthetic */ boolean u(a aVar, int i2, int i7, IInterface iInterface) {
        synchronized (aVar.f) {
            try {
                if (aVar.m != i2) {
                    return false;
                }
                aVar.v(i7, iInterface);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void a(String str) {
        this.f1338a = str;
        disconnect();
    }

    public final boolean b() {
        boolean z;
        synchronized (this.f) {
            int i2 = this.m;
            z = true;
            if (i2 != 2) {
                if (i2 != 3) {
                    z = false;
                }
            }
        }
        return z;
    }

    public final void c() {
        if (!g() || this.b == null) {
            throw new RuntimeException("Failed to connect when checking package");
        }
    }

    public final void d(e eVar, Set set) {
        Set set2 = set;
        Bundle o2 = o();
        String str = this.r;
        int i2 = f.f1923a;
        Scope[] scopeArr = d.r;
        Bundle bundle = new Bundle();
        int i7 = this.f1344p;
        C0278c[] cVarArr = d.s;
        d dVar = new d(6, i7, i2, (String) null, (IBinder) null, scopeArr, bundle, (Account) null, cVarArr, cVarArr, true, 0, false, str);
        dVar.g = this.f1339c.getPackageName();
        dVar.f2000j = o2;
        if (set2 != null) {
            dVar.f1999i = (Scope[]) set2.toArray(new Scope[0]);
        }
        if (e()) {
            dVar.k = new Account("<<default account>>", "com.google");
            if (eVar != null) {
                dVar.f1998h = ((E1.a) eVar).b;
            }
        }
        dVar.l = f1337x;
        dVar.m = n();
        if (this instanceof G1.f) {
            dVar.f2002p = true;
        }
        try {
            synchronized (this.g) {
                q qVar = this.f1340h;
                if (qVar != null) {
                    qVar.a(new t(this, this.v.get()), dVar);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e7) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e7);
            int i8 = this.v.get();
            s sVar = this.e;
            sVar.sendMessage(sVar.obtainMessage(6, i8, 3));
        } catch (SecurityException e8) {
            throw e8;
        } catch (RemoteException | RuntimeException e9) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e9);
            int i10 = this.v.get();
            v vVar = new v(this, 8, (IBinder) null, (Bundle) null);
            s sVar2 = this.e;
            sVar2.sendMessage(sVar2.obtainMessage(1, i10, -1, vVar));
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void disconnect() {
        this.v.incrementAndGet();
        synchronized (this.k) {
            try {
                int size = this.k.size();
                for (int i2 = 0; i2 < size; i2++) {
                    o oVar = (o) this.k.get(i2);
                    synchronized (oVar) {
                        oVar.f2011a = null;
                    }
                }
                this.k.clear();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        synchronized (this.g) {
            this.f1340h = null;
        }
        v(1, (IInterface) null);
    }

    public boolean e() {
        return false;
    }

    public final Set f() {
        if (e()) {
            return this.w;
        }
        return Collections.EMPTY_SET;
    }

    public final boolean g() {
        boolean z;
        synchronized (this.f) {
            if (this.m == 4) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public final void h(b bVar) {
        ((k) bVar.e).m.m.post(new A(17, (Object) bVar));
    }

    public final void i(C0315b bVar) {
        this.f1341i = bVar;
        v(2, (IInterface) null);
    }

    public final C0278c[] k() {
        x xVar = this.u;
        if (xVar == null) {
            return null;
        }
        return xVar.e;
    }

    public final String l() {
        return this.f1338a;
    }

    public abstract IInterface m(IBinder iBinder);

    public C0278c[] n() {
        return f1337x;
    }

    public Bundle o() {
        return new Bundle();
    }

    public final IInterface p() {
        IInterface iInterface;
        synchronized (this.f) {
            try {
                if (this.m == 5) {
                    throw new DeadObjectException();
                } else if (g()) {
                    iInterface = this.f1342j;
                    r.c(iInterface, "Client is connected but service is null");
                } else {
                    throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return iInterface;
    }

    public abstract String q();

    public abstract String r();

    public boolean s() {
        if (j() >= 211700000) {
            return true;
        }
        return false;
    }

    public void t() {
        System.currentTimeMillis();
    }

    /* JADX WARNING: type inference failed for: r10v4, types: [Yd.a, java.lang.Object] */
    public final void v(int i2, IInterface iInterface) {
        boolean z;
        Yd.a aVar;
        boolean z3 = false;
        if (i2 != 4) {
            z = false;
        } else {
            z = true;
        }
        if (iInterface != null) {
            z3 = true;
        }
        if (z == z3) {
            synchronized (this.f) {
                try {
                    this.m = i2;
                    this.f1342j = iInterface;
                    if (i2 == 1) {
                        u uVar = this.l;
                        if (uVar != null) {
                            C0313B b5 = this.d;
                            String str = this.b.f3913a;
                            r.b(str);
                            this.b.getClass();
                            if (this.q == null) {
                                this.f1339c.getClass();
                            }
                            b5.a(str, uVar, this.b.b);
                            this.l = null;
                        }
                    } else if (i2 == 2 || i2 == 3) {
                        u uVar2 = this.l;
                        if (!(uVar2 == null || (aVar = this.b) == null)) {
                            Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + aVar.f3913a + " on com.google.android.gms");
                            C0313B b8 = this.d;
                            String str2 = this.b.f3913a;
                            r.b(str2);
                            this.b.getClass();
                            if (this.q == null) {
                                this.f1339c.getClass();
                            }
                            b8.a(str2, uVar2, this.b.b);
                            this.v.incrementAndGet();
                        }
                        u uVar3 = new u(this, this.v.get());
                        this.l = uVar3;
                        String r5 = r();
                        boolean s5 = s();
                        ? obj = new Object();
                        obj.f3913a = r5;
                        obj.b = s5;
                        this.b = obj;
                        if (s5) {
                            if (j() < 17895000) {
                                throw new IllegalStateException("Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: ".concat(String.valueOf(this.b.f3913a)));
                            }
                        }
                        C0313B b10 = this.d;
                        String str3 = this.b.f3913a;
                        r.b(str3);
                        this.b.getClass();
                        String str4 = this.q;
                        if (str4 == null) {
                            str4 = this.f1339c.getClass().getName();
                        }
                        if (!b10.b(new y(str3, this.b.b), uVar3, str4)) {
                            Log.w("GmsClient", "unable to connect to service: " + this.b.f3913a + " on com.google.android.gms");
                            int i7 = this.v.get();
                            w wVar = new w(this, 16);
                            s sVar = this.e;
                            sVar.sendMessage(sVar.obtainMessage(7, i7, -1, wVar));
                        }
                    } else if (i2 == 4) {
                        r.b(iInterface);
                        System.currentTimeMillis();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return;
        }
        throw new IllegalArgumentException();
    }
}
