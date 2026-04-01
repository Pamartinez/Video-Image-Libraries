package v1;

import A0.l;
import B1.b;
import B2.A;
import E1.e;
import Fd.C0744a;
import P1.c;
import android.app.PendingIntent;
import android.content.Context;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.a;
import com.samsung.android.sum.core.Def;
import ge.W0;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import o1.C0246a;
import t1.C0276a;
import t1.C0278c;
import t1.C0279d;
import u1.C0285c;
import u1.f;
import u1.g;
import u1.h;
import w1.r;
import y1.C0356c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k implements g, h {

    /* renamed from: a  reason: collision with root package name */
    public final LinkedList f1964a = new LinkedList();
    public final C0285c b;

    /* renamed from: c  reason: collision with root package name */
    public final C0296a f1965c;
    public final W0 d;
    public final HashSet e = new HashSet();
    public final HashMap f = new HashMap();
    public final int g;

    /* renamed from: h  reason: collision with root package name */
    public final r f1966h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f1967i;

    /* renamed from: j  reason: collision with root package name */
    public final ArrayList f1968j = new ArrayList();
    public C0276a k = null;
    public int l = 0;
    public final /* synthetic */ C0298c m;

    public k(C0298c cVar, f fVar) {
        this.m = cVar;
        Looper looper = cVar.m.getLooper();
        l a7 = fVar.a();
        C0744a aVar = new C0744a((ArraySet) a7.e, (String) a7.f, (String) a7.g);
        C0246a aVar2 = (C0246a) fVar.f1935c.e;
        r.b(aVar2);
        C0285c L = aVar2.L(fVar.f1934a, looper, aVar, fVar.d, this, this);
        String str = fVar.b;
        if (str != null && (L instanceof a)) {
            ((a) L).r = str;
        }
        if (str == null || !(L instanceof g)) {
            this.b = L;
            this.f1965c = fVar.e;
            this.d = new W0(11);
            this.g = fVar.f;
            if (L.e()) {
                Context context = cVar.e;
                e eVar = cVar.m;
                l a10 = fVar.a();
                this.f1966h = new r(context, eVar, new C0744a((ArraySet) a10.e, (String) a10.f, (String) a10.g));
                return;
            }
            this.f1966h = null;
            return;
        }
        A.a.t(L);
        throw null;
    }

    public final void a(C0276a aVar) {
        n(aVar, (RuntimeException) null);
    }

    public final void b(int i2) {
        Looper myLooper = Looper.myLooper();
        e eVar = this.m.m;
        if (myLooper == eVar.getLooper()) {
            h(i2);
        } else {
            eVar.post(new com.google.android.material.datepicker.g(this, i2, 3));
        }
    }

    public final void c(C0276a aVar) {
        HashSet hashSet = this.e;
        Iterator it = hashSet.iterator();
        if (!it.hasNext()) {
            hashSet.clear();
        } else if (it.next() == null) {
            if (r.d(aVar, C0276a.f1921h)) {
                this.b.c();
            }
            throw null;
        } else {
            throw new ClassCastException();
        }
    }

    public final void d(Status status) {
        r.a(this.m.m);
        e(status, (Exception) null, false);
    }

    public final void e(Status status, Exception exc, boolean z) {
        boolean z3;
        r.a(this.m.m);
        boolean z7 = true;
        if (status != null) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (exc != null) {
            z7 = false;
        }
        if (z3 != z7) {
            Iterator it = this.f1964a.iterator();
            while (it.hasNext()) {
                n nVar = (n) it.next();
                if (!z || nVar.f1970a == 2) {
                    if (status != null) {
                        nVar.c(status);
                    } else {
                        nVar.d(exc);
                    }
                    it.remove();
                }
            }
            return;
        }
        throw new IllegalArgumentException("Status XOR exception should be null");
    }

    public final void f() {
        LinkedList linkedList = this.f1964a;
        ArrayList arrayList = new ArrayList(linkedList);
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            n nVar = (n) arrayList.get(i2);
            if (this.b.g()) {
                if (j(nVar)) {
                    linkedList.remove(nVar);
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public final void g() {
        C0298c cVar = this.m;
        r.a(cVar.m);
        this.k = null;
        c(C0276a.f1921h);
        e eVar = cVar.m;
        if (this.f1967i) {
            C0296a aVar = this.f1965c;
            eVar.removeMessages(11, aVar);
            eVar.removeMessages(9, aVar);
            this.f1967i = false;
        }
        Iterator it = this.f.values().iterator();
        if (!it.hasNext()) {
            f();
            i();
            return;
        }
        throw C0212a.h(it);
    }

    public final void h(int i2) {
        C0298c cVar = this.m;
        e eVar = cVar.m;
        r.a(cVar.m);
        this.k = null;
        this.f1967i = true;
        String l8 = this.b.l();
        W0 w02 = this.d;
        w02.getClass();
        StringBuilder sb2 = new StringBuilder("The connection to Google Play services was lost");
        if (i2 == 1) {
            sb2.append(" due to service disconnection.");
        } else if (i2 == 3) {
            sb2.append(" due to dead object exception.");
        }
        if (l8 != null) {
            sb2.append(" Last reason for disconnect: ");
            sb2.append(l8);
        }
        w02.B0(true, new Status(20, sb2.toString(), (PendingIntent) null, (C0276a) null));
        C0296a aVar = this.f1965c;
        eVar.sendMessageDelayed(Message.obtain(eVar, 9, aVar), Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
        eVar.sendMessageDelayed(Message.obtain(eVar, 11, aVar), 120000);
        ((SparseIntArray) cVar.g.e).clear();
        Iterator it = this.f.values().iterator();
        if (it.hasNext()) {
            throw C0212a.h(it);
        }
    }

    public final void i() {
        C0298c cVar = this.m;
        e eVar = cVar.m;
        C0296a aVar = this.f1965c;
        eVar.removeMessages(12, aVar);
        eVar.sendMessageDelayed(eVar.obtainMessage(12, aVar), cVar.f1958a);
    }

    public final boolean j(n nVar) {
        C0278c cVar;
        if (nVar == null) {
            W0 w02 = this.d;
            C0285c cVar2 = this.b;
            nVar.f(w02, cVar2.e());
            try {
                nVar.e(this);
                return true;
            } catch (DeadObjectException unused) {
                b(1);
                cVar2.a("DeadObjectException thrown while running ApiCallRunner.");
                return true;
            }
        } else {
            C0278c[] b5 = nVar.b(this);
            if (b5 != null && b5.length != 0) {
                C0278c[] k10 = this.b.k();
                if (k10 == null) {
                    k10 = new C0278c[0];
                }
                ArrayMap arrayMap = new ArrayMap(k10.length);
                for (C0278c cVar3 : k10) {
                    arrayMap.put(cVar3.d, Long.valueOf(cVar3.b()));
                }
                int length = b5.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    cVar = b5[i2];
                    Long l8 = (Long) arrayMap.get(cVar.d);
                    if (l8 == null || l8.longValue() < cVar.b()) {
                        break;
                    }
                    i2++;
                }
            }
            cVar = null;
            if (cVar == null) {
                W0 w03 = this.d;
                C0285c cVar4 = this.b;
                nVar.f(w03, cVar4.e());
                try {
                    nVar.e(this);
                    return true;
                } catch (DeadObjectException unused2) {
                    b(1);
                    cVar4.a("DeadObjectException thrown while running ApiCallRunner.");
                    return true;
                }
            } else {
                Log.w("GoogleApiManager", this.b.getClass().getName() + " could not execute call because it requires feature (" + cVar.d + ArcCommonLog.TAG_COMMA + cVar.b() + ").");
                if (!this.m.n || !nVar.a(this)) {
                    nVar.d(new u1.k(cVar));
                    return true;
                }
                l lVar = new l(this.f1965c, cVar);
                int indexOf = this.f1968j.indexOf(lVar);
                if (indexOf >= 0) {
                    l lVar2 = (l) this.f1968j.get(indexOf);
                    this.m.m.removeMessages(15, lVar2);
                    e eVar = this.m.m;
                    eVar.sendMessageDelayed(Message.obtain(eVar, 15, lVar2), Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
                } else {
                    this.f1968j.add(lVar);
                    e eVar2 = this.m.m;
                    eVar2.sendMessageDelayed(Message.obtain(eVar2, 15, lVar), Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
                    e eVar3 = this.m.m;
                    eVar3.sendMessageDelayed(Message.obtain(eVar3, 16, lVar), 120000);
                    C0276a aVar = new C0276a(2, (PendingIntent) null);
                    if (!k(aVar)) {
                        this.m.b(aVar, this.g);
                    }
                }
                return false;
            }
        }
    }

    public final boolean k(C0276a aVar) {
        synchronized (C0298c.q) {
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [Yd.b, w1.b, java.lang.Object] */
    public final void l() {
        C0298c cVar = this.m;
        r.a(cVar.m);
        C0285c cVar2 = this.b;
        if (!cVar2.g() && !cVar2.b()) {
            try {
                W0 w02 = cVar.g;
                Context context = cVar.e;
                SparseIntArray sparseIntArray = (SparseIntArray) w02.e;
                r.b(context);
                int j2 = cVar2.j();
                int i2 = ((SparseIntArray) w02.e).get(j2, -1);
                if (i2 == -1) {
                    i2 = 0;
                    int i7 = 0;
                    while (true) {
                        if (i7 >= sparseIntArray.size()) {
                            i2 = -1;
                            break;
                        }
                        int keyAt = sparseIntArray.keyAt(i7);
                        if (keyAt > j2 && sparseIntArray.get(keyAt) == 0) {
                            break;
                        }
                        i7++;
                    }
                    if (i2 == -1) {
                        i2 = ((C0279d) w02.f).b(context, j2);
                    }
                    sparseIntArray.put(j2, i2);
                }
                if (i2 != 0) {
                    C0276a aVar = new C0276a(i2, (PendingIntent) null);
                    Log.w("GoogleApiManager", "The service for " + cVar2.getClass().getName() + " is not available: " + aVar.toString());
                    n(aVar, (RuntimeException) null);
                    return;
                }
                ? obj = new Object();
                obj.f = cVar;
                obj.d = null;
                obj.e = null;
                obj.f3914a = false;
                obj.b = cVar2;
                obj.f3915c = this.f1965c;
                if (cVar2.e()) {
                    r rVar = this.f1966h;
                    r.b(rVar);
                    Handler handler = rVar.f1977c;
                    C0744a aVar2 = rVar.f;
                    O1.a aVar3 = rVar.g;
                    if (aVar3 != null) {
                        aVar3.disconnect();
                    }
                    aVar2.f3376i = Integer.valueOf(System.identityHashCode(rVar));
                    rVar.g = (O1.a) rVar.d.L(rVar.b, handler.getLooper(), aVar2, (N1.a) aVar2.f3375h, rVar, rVar);
                    rVar.f1978h = obj;
                    Set set = rVar.e;
                    if (set == null || set.isEmpty()) {
                        handler.post(new A(18, (Object) rVar));
                    } else {
                        O1.a aVar4 = rVar.g;
                        aVar4.getClass();
                        aVar4.i(new w1.g(aVar4));
                    }
                }
                try {
                    cVar2.i(obj);
                } catch (SecurityException e7) {
                    n(new C0276a(10), e7);
                }
            } catch (IllegalStateException e8) {
                n(new C0276a(10), e8);
            }
        }
    }

    public final void m(n nVar) {
        r.a(this.m.m);
        boolean g3 = this.b.g();
        LinkedList linkedList = this.f1964a;
        if (!g3) {
            linkedList.add(nVar);
            C0276a aVar = this.k;
            if (aVar == null || aVar.e == 0 || aVar.f == null) {
                l();
            } else {
                n(aVar, (RuntimeException) null);
            }
        } else if (j(nVar)) {
            i();
        } else {
            linkedList.add(nVar);
        }
    }

    public final void n(C0276a aVar, RuntimeException runtimeException) {
        O1.a aVar2;
        r.a(this.m.m);
        r rVar = this.f1966h;
        if (!(rVar == null || (aVar2 = rVar.g) == null)) {
            aVar2.disconnect();
        }
        r.a(this.m.m);
        this.k = null;
        ((SparseIntArray) this.m.g.e).clear();
        c(aVar);
        if ((this.b instanceof C0356c) && aVar.e != 24) {
            C0298c cVar = this.m;
            cVar.b = true;
            e eVar = cVar.m;
            eVar.sendMessageDelayed(eVar.obtainMessage(19), 300000);
        }
        if (aVar.e == 4) {
            d(C0298c.f1957p);
        } else if (this.f1964a.isEmpty()) {
            this.k = aVar;
        } else if (runtimeException != null) {
            r.a(this.m.m);
            e((Status) null, runtimeException, false);
        } else if (this.m.n) {
            e(C0298c.c(this.f1965c, aVar), (Exception) null, true);
            if (!this.f1964a.isEmpty() && !k(aVar) && !this.m.b(aVar, this.g)) {
                if (aVar.e == 18) {
                    this.f1967i = true;
                }
                if (this.f1967i) {
                    C0298c cVar2 = this.m;
                    C0296a aVar3 = this.f1965c;
                    e eVar2 = cVar2.m;
                    eVar2.sendMessageDelayed(Message.obtain(eVar2, 9, aVar3), Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
                    return;
                }
                d(C0298c.c(this.f1965c, aVar));
            }
        } else {
            d(C0298c.c(this.f1965c, aVar));
        }
    }

    public final void o(C0276a aVar) {
        r.a(this.m.m);
        C0285c cVar = this.b;
        String name = cVar.getClass().getName();
        String valueOf = String.valueOf(aVar);
        cVar.a("onSignInFailed for " + name + " with " + valueOf);
        n(aVar, (RuntimeException) null);
    }

    public final void onConnected() {
        Looper myLooper = Looper.myLooper();
        e eVar = this.m.m;
        if (myLooper == eVar.getLooper()) {
            g();
        } else {
            eVar.post(new A(16, (Object) this));
        }
    }

    public final void p() {
        r.a(this.m.m);
        Status status = C0298c.f1956o;
        d(status);
        this.d.B0(false, status);
        for (f fVar : (f[]) this.f.keySet().toArray(new f[0])) {
            m(new t(new c()));
        }
        c(new C0276a(4));
        C0285c cVar = this.b;
        if (cVar.g()) {
            cVar.h(new b(17, this));
        }
    }
}
