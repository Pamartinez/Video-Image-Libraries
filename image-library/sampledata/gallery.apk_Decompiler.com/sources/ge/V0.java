package ge;

import A0.l;
import B2.A;
import D0.e;
import F2.C0040v;
import F2.G;
import F2.Q;
import F2.U;
import F2.y0;
import Gd.a;
import He.F;
import O3.b;
import ee.C0964A;
import ee.C0965B;
import ee.C0969b;
import ee.C0971d;
import ee.C0975h;
import ee.C0976i;
import ee.C0982o;
import ee.C0984q;
import ee.Z;
import ee.a0;
import ee.d0;
import java.lang.reflect.Array;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class V0 extends C0984q {
    public static final Logger m = Logger.getLogger(V0.class.getName());
    public final e d;
    public final HashMap e = new HashMap();
    public C1022f0 f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f4483h;

    /* renamed from: i  reason: collision with root package name */
    public e f4484i;

    /* renamed from: j  reason: collision with root package name */
    public C0975h f4485j;
    public C0975h k;
    public final boolean l;

    public V0(e eVar) {
        boolean z = false;
        this.g = 0;
        this.f4483h = true;
        C0975h hVar = C0975h.IDLE;
        this.f4485j = hVar;
        this.k = hVar;
        Logger logger = Z.f4489a;
        String str = System.getenv("GRPC_EXPERIMENTAL_XDS_DUALSTACK_ENDPOINTS");
        str = str == null ? System.getProperty("GRPC_EXPERIMENTAL_XDS_DUALSTACK_ENDPOINTS") : str;
        if (!a.h0(str) && Boolean.parseBoolean(str)) {
            z = true;
        }
        this.l = z;
        this.d = eVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0018, code lost:
        if (r3.size() == 1) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.SocketAddress n(ge.E0 r3) {
        /*
            ge.F0 r0 = r3.f4395j
            ee.e0 r0 = r0.m
            r0.d()
            boolean r0 = r3.g
            java.lang.String r1 = "not started"
            He.F.t(r0, r1)
            java.util.List r3 = r3.e
            r0 = 0
            if (r3 == 0) goto L_0x001b
            int r1 = r3.size()
            r2 = 1
            if (r1 != r2) goto L_0x001b
            goto L_0x001c
        L_0x001b:
            r2 = r0
        L_0x001c:
            java.lang.String r1 = "%s does not have exactly one group"
            He.F.q(r1, r3, r2)
            java.lang.Object r3 = r3.get(r0)
            ee.o r3 = (ee.C0982o) r3
            java.util.List r3 = r3.f4304a
            java.lang.Object r3 = r3.get(r0)
            java.net.SocketAddress r3 = (java.net.SocketAddress) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.V0.n(ge.E0):java.net.SocketAddress");
    }

    /* JADX WARNING: type inference failed for: r0v12, types: [ge.f0, java.lang.Object] */
    public final a0 a(C0965B b) {
        C0975h hVar;
        List list;
        List list2;
        List list3;
        if (this.f4485j == C0975h.SHUTDOWN) {
            return a0.l.g("Already shut down");
        }
        List<C0982o> list4 = b.f4268a;
        C0969b bVar = b.b;
        if (list4.isEmpty()) {
            a0 a0Var = a0.f4289o;
            a0 g3 = a0Var.g("NameResolver returned no usable address. addrs=" + list4 + ", attrs=" + bVar);
            e(g3);
            return g3;
        }
        for (C0982o oVar : list4) {
            if (oVar == null) {
                a0 a0Var2 = a0.f4289o;
                a0 g10 = a0Var2.g("NameResolver returned address list with null endpoint. addrs=" + list4 + ", attrs=" + bVar);
                e(g10);
                return g10;
            }
        }
        this.f4483h = true;
        Q x9 = U.x();
        x9.c(list4);
        y0 f5 = x9.f();
        C1022f0 f0Var = this.f;
        if (f0Var == null) {
            ? obj = new Object();
            if (f5 != null) {
                list3 = f5;
            } else {
                list3 = Collections.EMPTY_LIST;
            }
            obj.f4509a = list3;
            this.f = obj;
        } else if (this.f4485j == C0975h.READY) {
            SocketAddress a7 = f0Var.a();
            C1022f0 f0Var2 = this.f;
            if (f5 != null) {
                list2 = f5;
            } else {
                f0Var2.getClass();
                list2 = Collections.EMPTY_LIST;
            }
            f0Var2.f4509a = list2;
            f0Var2.b = 0;
            f0Var2.f4510c = 0;
            if (this.f.e(a7)) {
                return a0.e;
            }
            C1022f0 f0Var3 = this.f;
            f0Var3.b = 0;
            f0Var3.f4510c = 0;
        } else {
            if (f5 != null) {
                list = f5;
            } else {
                list = Collections.EMPTY_LIST;
            }
            f0Var.f4509a = list;
            f0Var.b = 0;
            f0Var.f4510c = 0;
        }
        HashMap hashMap = this.e;
        HashSet hashSet = new HashSet(hashMap.keySet());
        HashSet hashSet2 = new HashSet();
        G A10 = f5.listIterator(0);
        while (A10.hasNext()) {
            hashSet2.addAll(((C0982o) A10.next()).f4304a);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            SocketAddress socketAddress = (SocketAddress) it.next();
            if (!hashSet2.contains(socketAddress)) {
                ((U0) hashMap.remove(socketAddress)).f4481a.b();
            }
        }
        if (hashSet.size() == 0 || (hVar = this.f4485j) == C0975h.CONNECTING || hVar == C0975h.READY) {
            C0975h hVar2 = C0975h.CONNECTING;
            this.f4485j = hVar2;
            q(hVar2, new S0(C0964A.d));
            m();
            o();
        } else {
            C0975h hVar3 = C0975h.IDLE;
            if (hVar == hVar3) {
                q(hVar3, new T0(this, this));
            } else if (hVar == C0975h.TRANSIENT_FAILURE) {
                m();
                o();
            }
        }
        return a0.e;
    }

    public final void e(a0 a0Var) {
        HashMap hashMap = this.e;
        for (U0 u02 : hashMap.values()) {
            u02.f4481a.b();
        }
        hashMap.clear();
        q(C0975h.TRANSIENT_FAILURE, new S0(C0964A.a(a0Var)));
    }

    public final void j() {
        Level level = Level.FINE;
        HashMap hashMap = this.e;
        m.log(level, "Shutting down, currently have {} subchannels created", Integer.valueOf(hashMap.size()));
        C0975h hVar = C0975h.SHUTDOWN;
        this.f4485j = hVar;
        this.k = hVar;
        m();
        for (U0 u02 : hashMap.values()) {
            u02.f4481a.b();
        }
        hashMap.clear();
    }

    public final void m() {
        e eVar = this.f4484i;
        if (eVar != null) {
            eVar.B();
            this.f4484i = null;
        }
    }

    public final void o() {
        E0 e02;
        Z z;
        C1022f0 f0Var = this.f;
        if (f0Var != null && f0Var.c() && this.f4485j != C0975h.SHUTDOWN) {
            SocketAddress a7 = this.f.a();
            HashMap hashMap = this.e;
            if (hashMap.containsKey(a7)) {
                e02 = ((U0) hashMap.get(a7)).f4481a;
            } else {
                l lVar = new l(this);
                C0969b bVar = C0969b.b;
                int[] iArr = new int[2];
                iArr[1] = 2;
                iArr[0] = 0;
                Class<Object> cls = Object.class;
                Object[][] objArr = (Object[][]) Array.newInstance(cls, iArr);
                C0040v.c(1, "arraySize");
                ArrayList arrayList = new ArrayList(C0246a.j0(((long) 1) + 5 + ((long) 0)));
                Collections.addAll(arrayList, new C0982o[]{new C0982o(a7)});
                F.i("addrs is empty", !arrayList.isEmpty());
                List unmodifiableList = Collections.unmodifiableList(new ArrayList(arrayList));
                int i2 = 0;
                while (true) {
                    int length = objArr.length;
                    z = C0984q.b;
                    if (i2 >= length) {
                        i2 = -1;
                        break;
                    } else if (z.equals(objArr[i2][0])) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i2 == -1) {
                    int[] iArr2 = new int[2];
                    iArr2[1] = 2;
                    iArr2[0] = objArr.length + 1;
                    Object[][] objArr2 = (Object[][]) Array.newInstance(cls, iArr2);
                    System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
                    i2 = objArr2.length - 1;
                    objArr = objArr2;
                }
                objArr[i2] = new Object[]{z, lVar};
                l lVar2 = new l(unmodifiableList, bVar, objArr);
                F0 f02 = (F0) this.d.f;
                f02.m.d();
                F.t(!f02.f4408H, "Channel is being terminated");
                E0 e03 = new E0(f02, lVar2);
                U0 u02 = new U0(e03, C0975h.IDLE, lVar);
                lVar.f = u02;
                hashMap.put(a7, u02);
                if (((C0969b) e03.f4391a.e).f4292a.get(C0984q.f4307c) == null) {
                    lVar.e = C0976i.a(C0975h.READY);
                }
                e03.c(new b(26, this, e03));
                e02 = e03;
            }
            int i7 = R0.f4472a[((U0) hashMap.get(a7)).b.ordinal()];
            if (i7 == 1) {
                e02.a();
                U0.a((U0) hashMap.get(a7), C0975h.CONNECTING);
                p();
            } else if (i7 != 2) {
                if (i7 == 3) {
                    m.warning("Requesting a connection even though we have a READY subchannel");
                } else if (i7 == 4) {
                    this.f.b();
                    o();
                }
            } else if (this.l) {
                p();
            } else {
                e02.a();
            }
        }
    }

    public final void p() {
        if (this.l) {
            e eVar = this.f4484i;
            if (eVar != null) {
                d0 d0Var = (d0) eVar.e;
                if (!d0Var.f && !d0Var.e) {
                    return;
                }
            }
            F0 f02 = (F0) this.d.f;
            this.f4484i = f02.m.c(new A(11, (Object) this), 250, TimeUnit.MILLISECONDS, f02.g);
        }
    }

    public final void q(C0975h hVar, C0971d dVar) {
        if (hVar != this.k || (hVar != C0975h.IDLE && hVar != C0975h.CONNECTING)) {
            this.k = hVar;
            this.d.e0(hVar, dVar);
        }
    }

    public final void r(U0 u02) {
        C0975h hVar = u02.b;
        C0975h hVar2 = C0975h.READY;
        if (hVar == hVar2) {
            C0976i iVar = (C0976i) u02.f4482c.e;
            C0975h hVar3 = iVar.f4298a;
            if (hVar3 == hVar2) {
                q(hVar2, new C1053r0(new C0964A(u02.f4481a, a0.e, false)));
                return;
            }
            C0975h hVar4 = C0975h.TRANSIENT_FAILURE;
            if (hVar3 == hVar4) {
                q(hVar4, new S0(C0964A.a(iVar.b)));
            } else if (this.k != hVar4) {
                q(hVar3, new S0(C0964A.d));
            }
        }
    }
}
