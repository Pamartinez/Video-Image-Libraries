package v1;

import B1.a;
import B1.b;
import E1.c;
import E1.d;
import E1.e;
import android.app.ActivityManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.collection.ArraySet;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.Status;
import ge.W0;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import t1.C0276a;
import t1.C0278c;
import t1.C0279d;
import u1.C0285c;
import u1.f;
import u1.k;
import w1.C0313B;
import w1.g;
import w1.h;
import w1.i;
import w1.j;
import w1.r;
import y1.C0355b;

/* renamed from: v1.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0298c implements Handler.Callback {

    /* renamed from: o  reason: collision with root package name */
    public static final Status f1956o = new Status(4, "Sign-out occurred while this API call was in progress.", (PendingIntent) null, (C0276a) null);

    /* renamed from: p  reason: collision with root package name */
    public static final Status f1957p = new Status(4, "The user must be signed in to make this API call.", (PendingIntent) null, (C0276a) null);
    public static final Object q = new Object();
    public static C0298c r;

    /* renamed from: a  reason: collision with root package name */
    public long f1958a = 10000;
    public boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    public i f1959c;
    public C0355b d;
    public final Context e;
    public final C0279d f;
    public final W0 g;

    /* renamed from: h  reason: collision with root package name */
    public final AtomicInteger f1960h = new AtomicInteger(1);

    /* renamed from: i  reason: collision with root package name */
    public final AtomicInteger f1961i = new AtomicInteger(0);

    /* renamed from: j  reason: collision with root package name */
    public final ConcurrentHashMap f1962j = new ConcurrentHashMap(5, 0.75f, 1);
    public final ArraySet k = new ArraySet();
    public final ArraySet l = new ArraySet();
    public final e m;
    public volatile boolean n = true;

    /* JADX WARNING: type inference failed for: r2v5, types: [E1.e, android.os.Handler] */
    public C0298c(Context context, Looper looper) {
        C0279d dVar = C0279d.d;
        this.e = context;
        ? handler = new Handler(looper, this);
        Looper.getMainLooper();
        this.m = handler;
        this.f = dVar;
        this.g = new W0(14);
        PackageManager packageManager = context.getPackageManager();
        if (a.f38i == null) {
            a.f38i = Boolean.valueOf(packageManager.hasSystemFeature("android.hardware.type.automotive"));
        }
        if (a.f38i.booleanValue()) {
            this.n = false;
        }
        handler.sendMessage(handler.obtainMessage(6));
    }

    public static Status c(C0296a aVar, C0276a aVar2) {
        return new Status(17, C0212a.n("API: ", (String) aVar.b.f, " is not available on this device. Connection failed with: ", String.valueOf(aVar2)), aVar2.f, aVar2);
    }

    public static C0298c e(Context context) {
        C0298c cVar;
        HandlerThread handlerThread;
        synchronized (q) {
            if (r == null) {
                synchronized (C0313B.g) {
                    try {
                        handlerThread = C0313B.f1993i;
                        if (handlerThread == null) {
                            HandlerThread handlerThread2 = new HandlerThread("GoogleApiHandler", 9);
                            C0313B.f1993i = handlerThread2;
                            handlerThread2.start();
                            handlerThread = C0313B.f1993i;
                        }
                    } catch (Throwable th) {
                        while (true) {
                            throw th;
                        }
                    }
                }
                Looper looper = handlerThread.getLooper();
                Context applicationContext = context.getApplicationContext();
                Object obj = C0279d.f1922c;
                r = new C0298c(applicationContext, looper);
            }
            cVar = r;
        }
        return cVar;
    }

    public final boolean a() {
        if (this.b) {
            return false;
        }
        h hVar = (h) g.b().f2007a;
        if (hVar != null && !hVar.e) {
            return false;
        }
        int i2 = ((SparseIntArray) this.g.e).get(203400000, -1);
        if (i2 == -1 || i2 == 0) {
            return true;
        }
        return false;
    }

    public final boolean b(C0276a aVar, int i2) {
        PendingIntent pendingIntent;
        boolean z;
        boolean z3;
        PendingIntent pendingIntent2;
        Boolean bool;
        C0279d dVar = this.f;
        Context context = this.e;
        dVar.getClass();
        synchronized (a.class) {
            Context applicationContext = context.getApplicationContext();
            Context context2 = a.d;
            pendingIntent = null;
            if (!(context2 == null || (bool = a.e) == null)) {
                if (context2 == applicationContext) {
                    z = bool.booleanValue();
                }
            }
            a.e = null;
            boolean isInstantApp = applicationContext.getPackageManager().isInstantApp();
            a.e = Boolean.valueOf(isInstantApp);
            a.d = applicationContext;
            z = isInstantApp;
        }
        if (!z) {
            int i7 = aVar.e;
            if (i7 == 0 || aVar.f == null) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z3) {
                pendingIntent2 = aVar.f;
            } else {
                Intent a7 = dVar.a(context, i7, (String) null);
                if (a7 != null) {
                    pendingIntent = PendingIntent.getActivity(context, 0, a7, 201326592);
                }
                pendingIntent2 = pendingIntent;
            }
            if (pendingIntent2 != null) {
                int i8 = aVar.e;
                int i10 = GoogleApiActivity.e;
                Intent intent = new Intent(context, GoogleApiActivity.class);
                intent.putExtra("pending_intent", pendingIntent2);
                intent.putExtra("failing_client_id", i2);
                intent.putExtra("notify_manager", true);
                dVar.f(context, i8, PendingIntent.getActivity(context, 0, intent, d.f165a | 134217728));
                return true;
            }
        }
        return false;
    }

    public final k d(f fVar) {
        C0296a aVar = fVar.e;
        ConcurrentHashMap concurrentHashMap = this.f1962j;
        k kVar = (k) concurrentHashMap.get(aVar);
        if (kVar == null) {
            kVar = new k(this, fVar);
            concurrentHashMap.put(aVar, kVar);
        }
        if (kVar.b.e()) {
            this.l.add(aVar);
        }
        kVar.l();
        return kVar;
    }

    public final void f(C0276a aVar, int i2) {
        if (!b(aVar, i2)) {
            e eVar = this.m;
            eVar.sendMessage(eVar.obtainMessage(5, i2, 0, aVar));
        }
    }

    /* JADX WARNING: type inference failed for: r4v4, types: [u1.f, y1.b] */
    /* JADX WARNING: type inference failed for: r9v6, types: [u1.f, y1.b] */
    /* JADX WARNING: type inference failed for: r4v8, types: [u1.f, y1.b] */
    public final boolean handleMessage(Message message) {
        k kVar;
        Status status;
        C0278c[] b5;
        int i2 = message.what;
        long j2 = 300000;
        switch (i2) {
            case 1:
                if (true == ((Boolean) message.obj).booleanValue()) {
                    j2 = 10000;
                }
                this.f1958a = j2;
                this.m.removeMessages(12);
                for (C0296a obtainMessage : this.f1962j.keySet()) {
                    e eVar = this.m;
                    eVar.sendMessageDelayed(eVar.obtainMessage(12, obtainMessage), this.f1958a);
                }
                break;
            case 2:
                message.obj.getClass();
                throw new ClassCastException();
            case 3:
                for (k kVar2 : this.f1962j.values()) {
                    r.a(kVar2.m.m);
                    kVar2.k = null;
                    kVar2.l();
                }
                break;
            case 4:
            case 8:
            case 13:
                q qVar = (q) message.obj;
                k kVar3 = (k) this.f1962j.get(qVar.f1975c.e);
                if (kVar3 == null) {
                    kVar3 = d(qVar.f1975c);
                }
                if (!kVar3.b.e() || this.f1961i.get() == qVar.b) {
                    kVar3.m(qVar.f1974a);
                    return true;
                }
                qVar.f1974a.c(f1956o);
                kVar3.p();
                return true;
            case 5:
                int i7 = message.arg1;
                C0276a aVar = (C0276a) message.obj;
                Iterator it = this.f1962j.values().iterator();
                while (true) {
                    if (it.hasNext()) {
                        kVar = (k) it.next();
                        if (kVar.g == i7) {
                        }
                    } else {
                        kVar = null;
                    }
                }
                if (kVar != null) {
                    int i8 = aVar.e;
                    if (i8 == 13) {
                        this.f.getClass();
                        AtomicBoolean atomicBoolean = t1.h.f1924a;
                        kVar.d(new Status(17, C0212a.n("Error resolution was canceled by the user, original error message: ", C0276a.b(i8), ": ", aVar.g), (PendingIntent) null, (C0276a) null));
                        return true;
                    }
                    kVar.d(c(kVar.f1965c, aVar));
                    return true;
                }
                Log.wtf("GoogleApiManager", C0212a.j(i7, "Could not find API instance ", " while trying to fail enqueued calls."), new Exception());
                return true;
            case 6:
                if (this.e.getApplicationContext() instanceof Application) {
                    Application application = (Application) this.e.getApplicationContext();
                    C0297b bVar = C0297b.f1955h;
                    synchronized (bVar) {
                        try {
                            if (!bVar.g) {
                                application.registerActivityLifecycleCallbacks(bVar);
                                application.registerComponentCallbacks(bVar);
                                bVar.g = true;
                            }
                        } catch (Throwable th) {
                            while (true) {
                                throw th;
                                break;
                            }
                        }
                    }
                    j jVar = new j(this);
                    synchronized (bVar) {
                        bVar.f.add(jVar);
                    }
                    AtomicBoolean atomicBoolean2 = bVar.d;
                    AtomicBoolean atomicBoolean3 = bVar.e;
                    if (!atomicBoolean3.get()) {
                        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                        ActivityManager.getMyMemoryState(runningAppProcessInfo);
                        if (!atomicBoolean3.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                            atomicBoolean2.set(true);
                        }
                    }
                    if (!atomicBoolean2.get()) {
                        this.f1958a = 300000;
                        return true;
                    }
                }
                break;
            case 7:
                d((f) message.obj);
                return true;
            case 9:
                if (this.f1962j.containsKey(message.obj)) {
                    k kVar4 = (k) this.f1962j.get(message.obj);
                    r.a(kVar4.m.m);
                    if (kVar4.f1967i) {
                        kVar4.l();
                        return true;
                    }
                }
                break;
            case 10:
                for (C0296a remove : this.l) {
                    k kVar5 = (k) this.f1962j.remove(remove);
                    if (kVar5 != null) {
                        kVar5.p();
                    }
                }
                this.l.clear();
                return true;
            case 11:
                if (this.f1962j.containsKey(message.obj)) {
                    k kVar6 = (k) this.f1962j.get(message.obj);
                    C0298c cVar = kVar6.m;
                    r.a(cVar.m);
                    boolean z = kVar6.f1967i;
                    if (z) {
                        C0296a aVar2 = kVar6.f1965c;
                        e eVar2 = kVar6.m.m;
                        if (z) {
                            eVar2.removeMessages(11, aVar2);
                            eVar2.removeMessages(9, aVar2);
                            kVar6.f1967i = false;
                        }
                        if (cVar.f.b(cVar.e, t1.f.f1923a) == 18) {
                            status = new Status(21, "Connection timed out waiting for Google Play services update to complete.", (PendingIntent) null, (C0276a) null);
                        } else {
                            status = new Status(22, "API failed to connect while resuming due to an unknown error.", (PendingIntent) null, (C0276a) null);
                        }
                        kVar6.d(status);
                        kVar6.b.a("Timing out connection while resuming.");
                        return true;
                    }
                }
                break;
            case 12:
                if (this.f1962j.containsKey(message.obj)) {
                    k kVar7 = (k) this.f1962j.get(message.obj);
                    r.a(kVar7.m.m);
                    C0285c cVar2 = kVar7.b;
                    if (cVar2.g() && kVar7.f.isEmpty()) {
                        W0 w02 = kVar7.d;
                        if (!((Map) w02.e).isEmpty() || !((Map) w02.f).isEmpty()) {
                            kVar7.i();
                        } else {
                            cVar2.a("Timing out service connection.");
                            return true;
                        }
                    }
                    return true;
                }
                break;
            case 14:
                message.obj.getClass();
                throw new ClassCastException();
            case 15:
                l lVar = (l) message.obj;
                if (this.f1962j.containsKey(lVar.f1969a)) {
                    k kVar8 = (k) this.f1962j.get(lVar.f1969a);
                    if (kVar8.f1968j.contains(lVar) && !kVar8.f1967i) {
                        if (!kVar8.b.g()) {
                            kVar8.l();
                            return true;
                        }
                        kVar8.f();
                        return true;
                    }
                }
                break;
            case 16:
                l lVar2 = (l) message.obj;
                if (this.f1962j.containsKey(lVar2.f1969a)) {
                    k kVar9 = (k) this.f1962j.get(lVar2.f1969a);
                    ArrayList arrayList = kVar9.f1968j;
                    C0298c cVar3 = kVar9.m;
                    LinkedList<n> linkedList = kVar9.f1964a;
                    if (arrayList.remove(lVar2)) {
                        cVar3.m.removeMessages(15, lVar2);
                        cVar3.m.removeMessages(16, lVar2);
                        C0278c cVar4 = lVar2.b;
                        ArrayList arrayList2 = new ArrayList(linkedList.size());
                        for (n nVar : linkedList) {
                            if (!(nVar == null || (b5 = nVar.b(kVar9)) == null)) {
                                int length = b5.length;
                                int i10 = 0;
                                while (true) {
                                    if (i10 < length) {
                                        if (!r.d(b5[i10], cVar4)) {
                                            i10++;
                                        } else if (i10 >= 0) {
                                            arrayList2.add(nVar);
                                        }
                                    }
                                }
                            }
                        }
                        int size = arrayList2.size();
                        for (int i11 = 0; i11 < size; i11++) {
                            n nVar2 = (n) arrayList2.get(i11);
                            linkedList.remove(nVar2);
                            nVar2.d(new k(cVar4));
                        }
                        break;
                    }
                }
                break;
            case 17:
                i iVar = this.f1959c;
                if (iVar != null) {
                    if (iVar.d > 0 || a()) {
                        if (this.d == null) {
                            this.d = new f(this.e, C0355b.f2139i, j.b, u1.e.b);
                        }
                        C0355b bVar2 = this.d;
                        bVar2.getClass();
                        Y1.f fVar = new Y1.f();
                        fVar.f951c = 0;
                        fVar.e = new C0278c[]{c.f164a};
                        fVar.b = false;
                        fVar.d = new b(18, iVar);
                        bVar2.b(2, fVar.a());
                    }
                    this.f1959c = null;
                    return true;
                }
                break;
            case 18:
                p pVar = (p) message.obj;
                if (pVar.f1973c == 0) {
                    i iVar2 = new i(pVar.b, Arrays.asList(new w1.f[]{pVar.f1972a}));
                    if (this.d == null) {
                        this.d = new f(this.e, C0355b.f2139i, j.b, u1.e.b);
                    }
                    C0355b bVar3 = this.d;
                    bVar3.getClass();
                    Y1.f fVar2 = new Y1.f();
                    fVar2.f951c = 0;
                    fVar2.e = new C0278c[]{c.f164a};
                    fVar2.b = false;
                    fVar2.d = new b(18, iVar2);
                    bVar3.b(2, fVar2.a());
                    return true;
                }
                i iVar3 = this.f1959c;
                if (iVar3 != null) {
                    List list = iVar3.e;
                    if (iVar3.d != pVar.b || (list != null && list.size() >= pVar.d)) {
                        this.m.removeMessages(17);
                        i iVar4 = this.f1959c;
                        if (iVar4 != null) {
                            if (iVar4.d > 0 || a()) {
                                if (this.d == null) {
                                    this.d = new f(this.e, C0355b.f2139i, j.b, u1.e.b);
                                }
                                C0355b bVar4 = this.d;
                                bVar4.getClass();
                                Y1.f fVar3 = new Y1.f();
                                fVar3.f951c = 0;
                                fVar3.e = new C0278c[]{c.f164a};
                                fVar3.b = false;
                                fVar3.d = new b(18, iVar4);
                                bVar4.b(2, fVar3.a());
                            }
                            this.f1959c = null;
                        }
                    } else {
                        i iVar5 = this.f1959c;
                        w1.f fVar4 = pVar.f1972a;
                        if (iVar5.e == null) {
                            iVar5.e = new ArrayList();
                        }
                        iVar5.e.add(fVar4);
                    }
                }
                if (this.f1959c == null) {
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(pVar.f1972a);
                    this.f1959c = new i(pVar.b, arrayList3);
                    e eVar3 = this.m;
                    eVar3.sendMessageDelayed(eVar3.obtainMessage(17), pVar.f1973c);
                    return true;
                }
                break;
            case 19:
                this.b = false;
                return true;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + i2);
                return false;
        }
        return true;
    }
}
