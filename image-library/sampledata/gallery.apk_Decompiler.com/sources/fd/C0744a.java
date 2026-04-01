package Fd;

import B1.b;
import D1.f;
import Df.C0737c;
import Df.C0738d;
import Df.C0740f;
import Df.x;
import He.F;
import Hf.C0774x;
import L1.d;
import N1.a;
import Ne.u;
import Qe.C0833x;
import Te.z;
import Tf.n;
import We.C0892d;
import Ze.w;
import a.C0068a;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.HandlerThread;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import b3.C0083a;
import i.C0212a;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jf.C1101a;
import jf.C1102b;
import jf.C1103c;
import jf.C1104d;
import jf.C1105e;
import jf.C1107g;
import jf.r;
import jf.s;
import kotlin.jvm.internal.j;
import lf.C1154g;
import lf.C1159l;
import lf.C1166t;
import lf.C1171y;
import lf.G;
import lf.Q;
import lf.W;
import ne.C1196n;
import ne.C1202t;
import nf.C1208e;
import nf.C1209f;
import of.c;
import of.k;
import pf.e;
import pf.i;
import qf.C1235b;
import qf.C1240g;
import rf.C1252b;
import rf.C1258h;
import rf.C1264n;
import rf.C1266p;
import vf.C1320A;
import vf.C1324d;
import vf.C1327g;
import vf.C1328h;
import vf.C1330j;
import vf.C1331k;
import vf.C1342v;
import vf.C1344x;

/* renamed from: Fd.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0744a implements C0738d, C0740f {

    /* renamed from: j  reason: collision with root package name */
    public static volatile C0744a f3374j;
    public Object d;
    public Object e;
    public Object f;
    public Object g;

    /* renamed from: h  reason: collision with root package name */
    public Object f3375h;

    /* renamed from: i  reason: collision with root package name */
    public Object f3376i;

    public C0744a(Set set, String str, String str2) {
        Set unmodifiableSet = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        this.f = unmodifiableSet;
        Map map = Collections.EMPTY_MAP;
        this.d = str;
        this.e = str2;
        this.f3375h = a.b;
        HashSet hashSet = new HashSet(unmodifiableSet);
        Iterator it = map.values().iterator();
        if (!it.hasNext()) {
            this.g = Collections.unmodifiableSet(hashSet);
            return;
        }
        throw C0212a.h(it);
    }

    public static final C1327g b(C0744a aVar, C1240g gVar, Object obj) {
        C1327g b = C1328h.b((z) aVar.f, obj);
        if (b != null) {
            return b;
        }
        String str = "Unsupported annotation argument: " + gVar;
        j.e(str, "message");
        return new C1330j(str);
    }

    public static /* synthetic */ List g(C0744a aVar, Df.z zVar, s sVar, Boolean bool, boolean z, int i2) {
        boolean z3;
        boolean z7;
        if ((i2 & 4) != 0) {
            z3 = false;
        } else {
            z3 = true;
        }
        if ((i2 & 16) != 0) {
            bool = null;
        }
        Boolean bool2 = bool;
        if ((i2 & 32) != 0) {
            z7 = false;
        } else {
            z7 = z;
        }
        return aVar.f(zVar, sVar, z3, false, bool2, z7);
    }

    public static s h(C1252b bVar, C1209f fVar, b bVar2, C0737c cVar, boolean z) {
        j.e(bVar, "proto");
        j.e(fVar, "nameResolver");
        j.e(cVar, "kind");
        if (bVar instanceof C1159l) {
            C1258h hVar = i.f5029a;
            e a7 = i.a((C1159l) bVar, fVar, bVar2);
            if (a7 != null) {
                return d.h(a7);
            }
        } else if (bVar instanceof C1171y) {
            C1258h hVar2 = i.f5029a;
            e c5 = i.c((C1171y) bVar, fVar, bVar2);
            if (c5 != null) {
                return d.h(c5);
            }
        } else if (bVar instanceof G) {
            C1266p pVar = k.d;
            j.d(pVar, "propertySignature");
            of.e eVar = (of.e) f.q((C1264n) bVar, pVar);
            if (eVar != null) {
                int i2 = C1103c.f4636a[cVar.ordinal()];
                if (i2 == 1) {
                    C1209f fVar2 = fVar;
                    if ((eVar.e & 4) == 4) {
                        c cVar2 = eVar.f4994h;
                        j.d(cVar2, "getGetter(...)");
                        return new s(fVar2.getString(cVar2.f).concat(fVar2.getString(cVar2.g)));
                    }
                } else if (i2 == 2) {
                    C1209f fVar3 = fVar;
                    if ((eVar.e & 8) != 8) {
                        return null;
                    }
                    c cVar3 = eVar.f4995i;
                    j.d(cVar3, "getSetter(...)");
                    return new s(fVar3.getString(cVar3.f).concat(fVar3.getString(cVar3.g)));
                } else if (i2 == 3) {
                    return f.w((G) bVar, fVar, bVar2, true, true, z);
                }
            }
        }
        return null;
    }

    public static C0744a j(Context context) {
        synchronized (C0744a.class) {
            try {
                if (f3374j == null) {
                    f3374j = new C0744a(context);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return f3374j;
    }

    public List a(Df.z zVar, C1166t tVar) {
        j.e(zVar, "container");
        String string = zVar.f3369a.getString(tVar.g);
        String b = pf.b.b(((x) zVar).f.b());
        j.e(b, "desc");
        return g(this, zVar, new s(string + '#' + b), (Boolean) null, false, 60);
    }

    public List c(Df.z zVar, C1252b bVar, C0737c cVar) {
        j.e(bVar, "proto");
        j.e(cVar, "kind");
        s h5 = h(bVar, zVar.f3369a, zVar.b, cVar, false);
        if (h5 == null) {
            return C1202t.d;
        }
        return g(this, zVar, new s(C0212a.p(new StringBuilder(), h5.f4652a, "@0")), (Boolean) null, false, 60);
    }

    public ArrayList d(Q q, C1209f fVar) {
        j.e(q, "proto");
        j.e(fVar, "nameResolver");
        Object j2 = q.j(k.f);
        j.d(j2, "getExtension(...)");
        Iterable<C1154g> iterable = (Iterable) j2;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (C1154g gVar : iterable) {
            j.b(gVar);
            arrayList.add(((D0.e) this.f3375h).E(gVar, fVar));
        }
        return arrayList;
    }

    public void e() {
        og.k.m("AsServiceConnection", "deInit", new Object[0]);
        E2.k kVar = (E2.k) this.d;
        if (kVar != null) {
            og.k.m("AsConnectionManager", "disConnectService mIsConnected = ", Boolean.valueOf(kVar.b));
            if (kVar.b) {
                og.k.m("AsConnectionManager", "unbindService", new Object[0]);
                C0083a aVar = (C0083a) kVar.e;
                if (aVar != null) {
                    try {
                        ((Context) kVar.d).unbindService(aVar);
                    } catch (Exception e7) {
                        Log.e("AsConnectionManager", og.k.j("disconnect", new Object[0]), e7);
                    }
                }
                kVar.f(2, (ComponentName) null, (IBinder) null);
                kVar.b = false;
            }
        }
        HandlerThread handlerThread = (HandlerThread) this.f;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
    }

    public List f(Df.z zVar, s sVar, boolean z, boolean z3, Boolean bool, boolean z7) {
        List list;
        r rVar;
        Df.z zVar2 = zVar;
        Ve.b y = B1.a.y(zVar2, z, z3, bool, z7, (b) this.d, (pf.f) this.f3376i);
        if (y == null) {
            if (zVar2 instanceof x) {
                Qe.Q q = ((x) zVar2).f3370c;
                if (q instanceof r) {
                    rVar = (r) q;
                } else {
                    rVar = null;
                }
                if (rVar != null) {
                    y = rVar.d;
                }
            }
            y = null;
        }
        if (y == null || (list = (List) ((C1104d) ((Gf.e) this.e).invoke(y)).f4637a.get(sVar)) == null) {
            return C1202t.d;
        }
        return list;
    }

    public List i(Df.z zVar, C1252b bVar, C0737c cVar) {
        j.e(bVar, "proto");
        j.e(cVar, "kind");
        if (cVar == C0737c.PROPERTY) {
            return v(zVar, (G) bVar, C1102b.PROPERTY);
        }
        s h5 = h(bVar, zVar.f3369a, zVar.b, cVar, false);
        if (h5 == null) {
            return C1202t.d;
        }
        return g(this, zVar, h5, (Boolean) null, false, 60);
    }

    public boolean k(C1235b bVar) {
        Ve.b E;
        if (!(bVar.e() == null || !j.a(bVar.f().b(), "Container") || (E = F.E((b) this.d, bVar, (pf.f) this.f3376i)) == null)) {
            LinkedHashSet linkedHashSet = Me.a.f3534a;
            Class cls = E.f3829a;
            j.e(cls, "klass");
            ig.i g3 = j.g(cls.getDeclaredAnnotations());
            boolean z = false;
            while (g3.hasNext()) {
                Annotation annotation = (Annotation) g3.next();
                j.b(annotation);
                if (C0892d.a(C0068a.A(C0068a.w(annotation))).equals(w.b)) {
                    z = true;
                }
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public List l(Df.z zVar, G g3) {
        j.e(g3, "proto");
        return v(zVar, g3, C1102b.DELEGATE_FIELD);
    }

    public List m(Df.z zVar, G g3) {
        j.e(g3, "proto");
        return v(zVar, g3, C1102b.BACKING_FIELD);
    }

    public ArrayList n(W w, C1209f fVar) {
        j.e(w, "proto");
        j.e(fVar, "nameResolver");
        Object j2 = w.j(k.f5010h);
        j.d(j2, "getExtension(...)");
        Iterable<C1154g> iterable = (Iterable) j2;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (C1154g gVar : iterable) {
            j.b(gVar);
            arrayList.add(((D0.e) this.f3375h).E(gVar, fVar));
        }
        return arrayList;
    }

    public Object o(Df.z zVar, G g3, C0774x xVar) {
        j.e(g3, "proto");
        return u(zVar, g3, C0737c.PROPERTY, xVar, C1101a.f);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        if ((r11 & 64) == 64) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004e, code lost:
        if (r11.f3368h != false) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0028, code lost:
        if ((r11 & 64) == 64) goto L_0x002a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List p(Df.z r10, rf.C1252b r11, Df.C0737c r12, int r13, lf.Z r14) {
        /*
            r9 = this;
            java.lang.String r14 = "callableProto"
            kotlin.jvm.internal.j.e(r11, r14)
            java.lang.String r14 = "kind"
            kotlin.jvm.internal.j.e(r12, r14)
            nf.f r14 = r10.f3369a
            B1.b r0 = r10.b
            r1 = 0
            jf.s r12 = h(r11, r14, r0, r12, r1)
            if (r12 == 0) goto L_0x008e
            boolean r14 = r11 instanceof lf.C1171y
            r0 = 32
            r2 = 64
            r3 = 1
            if (r14 == 0) goto L_0x002c
            lf.y r11 = (lf.C1171y) r11
            int r11 = r11.f
            r14 = r11 & 32
            if (r14 != r0) goto L_0x0027
            goto L_0x002a
        L_0x0027:
            r11 = r11 & r2
            if (r11 != r2) goto L_0x0051
        L_0x002a:
            r1 = r3
            goto L_0x0051
        L_0x002c:
            boolean r14 = r11 instanceof lf.G
            if (r14 == 0) goto L_0x003d
            lf.G r11 = (lf.G) r11
            int r11 = r11.f
            r14 = r11 & 32
            if (r14 != r0) goto L_0x0039
            goto L_0x003c
        L_0x0039:
            r11 = r11 & r2
            if (r11 != r2) goto L_0x0051
        L_0x003c:
            goto L_0x002a
        L_0x003d:
            boolean r14 = r11 instanceof lf.C1159l
            if (r14 == 0) goto L_0x0076
            r11 = r10
            Df.x r11 = (Df.x) r11
            lf.i r14 = r11.g
            lf.i r0 = lf.C1156i.ENUM_CLASS
            if (r14 != r0) goto L_0x004c
            r1 = 2
            goto L_0x0051
        L_0x004c:
            boolean r11 = r11.f3368h
            if (r11 == 0) goto L_0x0051
            goto L_0x002a
        L_0x0051:
            int r13 = r13 + r1
            jf.s r5 = new jf.s
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = r12.f4652a
            r11.append(r12)
            r11.append(r2)
            r11.append(r13)
            java.lang.String r11 = r11.toString()
            r5.<init>(r11)
            r7 = 0
            r8 = 60
            r6 = 0
            r3 = r9
            r4 = r10
            java.util.List r9 = g(r3, r4, r5, r6, r7, r8)
            return r9
        L_0x0076:
            java.lang.UnsupportedOperationException r9 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r12 = "Unsupported message: "
            r10.<init>(r12)
            java.lang.Class r11 = r11.getClass()
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x008e:
            ne.t r9 = ne.C1202t.d
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: Fd.C0744a.p(Df.z, rf.b, Df.c, int, lf.Z):java.util.List");
    }

    public ArrayList q(x xVar) {
        r rVar;
        j.e(xVar, "container");
        Qe.Q q = xVar.f3370c;
        Ve.b bVar = null;
        if (q instanceof r) {
            rVar = (r) q;
        } else {
            rVar = null;
        }
        if (rVar != null) {
            bVar = rVar.d;
        }
        if (bVar != null) {
            ArrayList arrayList = new ArrayList(1);
            Class cls = bVar.f3829a;
            j.e(cls, "klass");
            ig.i g3 = j.g(cls.getDeclaredAnnotations());
            while (g3.hasNext()) {
                Annotation annotation = (Annotation) g3.next();
                j.b(annotation);
                Class A10 = C0068a.A(C0068a.w(annotation));
                C1105e t = t(C0892d.a(A10), new Ve.a(annotation), arrayList);
                if (t != null) {
                    Gd.a.b0(t, annotation, A10);
                }
            }
            return arrayList;
        }
        throw new IllegalStateException(("Class for loading annotations is not found: " + xVar.f.a()).toString());
    }

    public Object r(Df.z zVar, G g3, C0774x xVar) {
        j.e(g3, "proto");
        return u(zVar, g3, C0737c.PROPERTY_GETTER, xVar, C1101a.e);
    }

    public C1105e s(C1235b bVar, Qe.Q q, List list) {
        j.e(list, "result");
        return new C1105e(this, C0833x.f((z) this.f, bVar, (D0.f) this.g), bVar, list, q);
    }

    public C1105e t(C1235b bVar, Ve.a aVar, List list) {
        j.e(list, "result");
        if (Me.a.f3534a.contains(bVar)) {
            return null;
        }
        return s(bVar, aVar, list);
    }

    public Object u(Df.z zVar, G g3, C0737c cVar, C0774x xVar, Ae.c cVar2) {
        Object invoke;
        r rVar;
        Df.z zVar2 = zVar;
        Ve.b y = B1.a.y(zVar2, true, true, C1208e.B.c(g3.g), i.d(g3), (b) this.d, (pf.f) this.f3376i);
        if (y == null) {
            if (zVar2 instanceof x) {
                Qe.Q q = ((x) zVar2).f3370c;
                if (q instanceof r) {
                    rVar = (r) q;
                } else {
                    rVar = null;
                }
                if (rVar != null) {
                    y = rVar.d;
                }
            }
            y = null;
        }
        if (y != null) {
            pf.f fVar = C1107g.e;
            j.e(fVar, "version");
            s h5 = h(g3, zVar2.f3369a, zVar2.b, cVar, ((pf.f) y.b.d).a(fVar.b, fVar.f4952c, fVar.d));
            if (!(h5 == null || (invoke = cVar2.invoke(((Gf.e) this.e).invoke(y), h5)) == null)) {
                if (u.a(xVar)) {
                    invoke = (C1327g) invoke;
                    if (invoke instanceof C1324d) {
                        return new C1320A(((Number) ((C1324d) invoke).f5158a).byteValue());
                    }
                    if (invoke instanceof C1344x) {
                        return new C1320A(((Number) ((C1344x) invoke).f5158a).shortValue());
                    }
                    if (invoke instanceof C1331k) {
                        return new C1320A(((Number) ((C1331k) invoke).f5158a).intValue());
                    }
                    if (invoke instanceof C1342v) {
                        return new C1320A(((Number) ((C1342v) invoke).f5158a).longValue());
                    }
                }
                return invoke;
            }
        }
        return null;
    }

    public List v(Df.z zVar, G g3, C1102b bVar) {
        boolean z;
        b bVar2 = zVar.b;
        C1209f fVar = zVar.f3369a;
        Boolean c5 = C1208e.B.c(g3.g);
        boolean d2 = i.d(g3);
        if (bVar == C1102b.PROPERTY) {
            s x9 = f.x(g3, fVar, bVar2, 40);
            if (x9 != null) {
                return g(this, zVar, x9, c5, d2, 8);
            }
        } else {
            Df.z zVar2 = zVar;
            s x10 = f.x(g3, fVar, bVar2, 48);
            if (x10 != null) {
                boolean u02 = n.u0(x10.f4652a, "$delegate");
                if (bVar == C1102b.DELEGATE_FIELD) {
                    z = true;
                } else {
                    z = false;
                }
                if (u02 == z) {
                    return f(zVar2, x10, true, true, c5, d2);
                }
            }
        }
        return C1202t.d;
    }

    public C0744a(Context context) {
        String simOperator;
        this.f3375h = "";
        this.f3376i = "";
        this.d = context.getResources().getConfiguration().locale.getLanguage();
        this.e = Build.VERSION.RELEASE;
        this.f = Build.MODEL;
        this.g = Build.VERSION.INCREMENTAL;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null && telephonyManager.getSimState() == 5 && (simOperator = telephonyManager.getSimOperator()) != null && simOperator.length() >= 3) {
            this.f3375h = simOperator.substring(0, 3);
            this.f3376i = simOperator.substring(3);
        }
    }
}
