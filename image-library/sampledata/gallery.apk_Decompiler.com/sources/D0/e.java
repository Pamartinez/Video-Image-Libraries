package D0;

import Dd.C0732c;
import Df.C0735a;
import Df.C0736b;
import Df.C0737c;
import Df.C0738d;
import Df.C0739e;
import Df.C0740f;
import Df.x;
import He.F;
import Hf.B;
import Hf.C0754c;
import Hf.C0768q;
import Hf.C0774x;
import Hf.I;
import Hf.O;
import Hf.P;
import Hf.T;
import Hf.X;
import Hf.c0;
import Jf.l;
import Ne.p;
import P2.C0056f;
import Qe.C;
import Qe.C0816f;
import Qe.C0817g;
import Qe.C0819i;
import Qe.C0833x;
import Qe.V;
import S2.b;
import S2.c;
import S2.d;
import S2.f;
import S2.g;
import S2.h;
import S2.i;
import S2.j;
import S2.k;
import S2.n;
import S2.o;
import S2.q;
import S2.s;
import S2.t;
import S2.u;
import Sd.v;
import Te.C0848i;
import Te.Q;
import Xd.a;
import Ze.A;
import a.C0068a;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextUtils;
import ee.C0968a;
import ee.C0969b;
import ee.C0971d;
import ee.C0973f;
import ee.C0975h;
import ee.C0981n;
import ee.M;
import ee.a0;
import ee.b0;
import ee.d0;
import ee.e0;
import ef.C0993a;
import ge.C1019e0;
import ge.C1048o0;
import ge.C1054s;
import ge.C1056t;
import ge.F0;
import ge.Q0;
import ge.r;
import he.C1076a;
import io.grpc.Deadline;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lf.C1150c;
import lf.C1151d;
import lf.C1152e;
import lf.C1154g;
import lf.C1159l;
import lf.C1166t;
import lf.C1171y;
import lf.G;
import lf.W;
import lf.Z;
import me.m;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import ne.C1203u;
import ne.y;
import ne.z;
import nf.C1208e;
import nf.C1209f;
import o1.C0246a;
import oe.C1220i;
import qf.C1240g;
import rf.C1252b;
import tf.C1301e;
import vf.C1320A;
import vf.C1322b;
import vf.C1323c;
import vf.C1324d;
import vf.C1327g;
import vf.C1329i;
import vf.C1330j;
import vf.C1331k;
import vf.C1341u;
import vf.C1342v;
import vf.C1344x;
import vf.C1346z;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class e implements C0738d, C0740f, a, A, r, C1056t {
    public final /* synthetic */ int d;
    public Object e;
    public Object f;

    public /* synthetic */ e(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public static int C(S2.a aVar) {
        if (aVar instanceof S2.e) {
            return 0;
        }
        if (aVar instanceof s) {
            return 2;
        }
        if (aVar instanceof f) {
            return 3;
        }
        if (aVar instanceof j) {
            return 4;
        }
        if (aVar instanceof o) {
            return 6;
        }
        if (aVar instanceof i) {
            return 16;
        }
        if (aVar instanceof g) {
            return 17;
        }
        if (aVar instanceof t) {
            return 23;
        }
        if (aVar instanceof u) {
            return 24;
        }
        if (aVar instanceof h) {
            return 25;
        }
        if (aVar instanceof q) {
            return 26;
        }
        if (aVar instanceof c) {
            return 28;
        }
        if (aVar instanceof k) {
            return 30;
        }
        if (aVar instanceof d) {
            return 31;
        }
        throw new RuntimeException("Shouldn't happen");
    }

    public static I D(List list) {
        if (list.isEmpty()) {
            return I.f;
        }
        return new I(list);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [og.a, java.lang.Object] */
    public static e S(String... strArr) {
        String str;
        try {
            og.c[] cVarArr = new og.c[strArr.length];
            ? obj = new Object();
            for (int i2 = 0; i2 < strArr.length; i2++) {
                String str2 = strArr[i2];
                String[] strArr2 = I0.c.f341h;
                obj.m(34);
                int length = str2.length();
                int i7 = 0;
                for (int i8 = 0; i8 < length; i8++) {
                    char charAt = str2.charAt(i8);
                    if (charAt < 128) {
                        str = strArr2[charAt];
                        if (str == null) {
                        }
                    } else if (charAt == 8232) {
                        str = "\\u2028";
                    } else if (charAt == 8233) {
                        str = "\\u2029";
                    }
                    if (i7 < i8) {
                        obj.p(i7, i8, str2);
                    }
                    obj.p(0, str.length(), str);
                    i7 = i8 + 1;
                }
                if (i7 < length) {
                    obj.p(i7, length, str2);
                }
                obj.m(34);
                obj.c();
                cVarArr[i2] = new og.c(obj.f(obj.e));
            }
            return new e(8, (Object) (String[]) strArr.clone(), (Object) og.f.p(cVarArr));
        } catch (EOFException e7) {
            throw new AssertionError(e7);
        } catch (IOException e8) {
            throw new AssertionError(e8);
        }
    }

    public static void s(C0056f fVar, S2.a aVar) {
        if (aVar instanceof c) {
            b bVar = ((c) aVar).d;
            int length = bVar.e.length;
            for (int i2 = 0; i2 < length; i2++) {
                s(fVar, (S2.a) bVar.d(i2));
            }
            return;
        }
        fVar.a(aVar);
    }

    public C0969b A() {
        if (((IdentityHashMap) this.f) != null) {
            for (Map.Entry entry : ((C0969b) this.e).f4292a.entrySet()) {
                if (!((IdentityHashMap) this.f).containsKey(entry.getKey())) {
                    ((IdentityHashMap) this.f).put((C0968a) entry.getKey(), entry.getValue());
                }
            }
            this.e = new C0969b((IdentityHashMap) this.f);
            this.f = null;
        }
        return (C0969b) this.e;
    }

    public void B() {
        ((d0) this.e).e = true;
        ((ScheduledFuture) this.f).cancel(false);
    }

    public Re.c E(C1154g gVar, C1209f fVar) {
        Map map;
        kotlin.jvm.internal.j.e(gVar, "proto");
        kotlin.jvm.internal.j.e(fVar, "nameResolver");
        C0816f f5 = C0833x.f((C) this.e, com.samsung.context.sdk.samsunganalytics.internal.sender.c.A(fVar, gVar.f), (f) this.f);
        if (gVar.g.size() != 0 && !l.f(f5)) {
            int i2 = C1301e.f5137a;
            if (C1301e.n(f5, C0817g.ANNOTATION_CLASS)) {
                Collection d2 = f5.d();
                kotlin.jvm.internal.j.d(d2, "getConstructors(...)");
                C0848i iVar = (C0848i) C1194l.c1(d2);
                if (iVar != null) {
                    List B = iVar.B();
                    kotlin.jvm.internal.j.d(B, "getValueParameters(...)");
                    Iterable iterable = B;
                    int Z = z.Z(C1196n.w0(iterable, 10));
                    if (Z < 16) {
                        Z = 16;
                    }
                    LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
                    for (Object next : iterable) {
                        linkedHashMap.put(((Q) next).getName(), next);
                    }
                    List<C1152e> list = gVar.g;
                    kotlin.jvm.internal.j.d(list, "getArgumentList(...)");
                    ArrayList arrayList = new ArrayList();
                    for (C1152e eVar : list) {
                        kotlin.jvm.internal.j.b(eVar);
                        Q q = (Q) linkedHashMap.get(com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(fVar, eVar.f));
                        C1327g gVar2 = null;
                        if (q != null) {
                            C1240g C5 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(fVar, eVar.f);
                            C0774x type = q.getType();
                            kotlin.jvm.internal.j.d(type, "getType(...)");
                            C1151d dVar = eVar.g;
                            kotlin.jvm.internal.j.d(dVar, "getValue(...)");
                            C1327g V = V(type, dVar, fVar);
                            if (F(V, type, dVar)) {
                                gVar2 = V;
                            }
                            if (gVar2 == null) {
                                String str = "Unexpected argument value: actual type " + dVar.f + " != expected type " + type;
                                kotlin.jvm.internal.j.e(str, "message");
                                gVar2 = new C1330j(str);
                            }
                            gVar2 = new me.i(C5, gVar2);
                        }
                        if (gVar2 != null) {
                            arrayList.add(gVar2);
                        }
                    }
                    map = z.e0(arrayList);
                    return new Re.c(f5.i(), map, Qe.Q.f3662a);
                }
            }
        }
        map = C1203u.d;
        return new Re.c(f5.i(), map, Qe.Q.f3662a);
    }

    public boolean F(C1327g gVar, C0774x xVar, C1151d dVar) {
        int i2;
        C0816f fVar;
        C c5 = (C) this.e;
        C1150c cVar = dVar.f;
        if (cVar == null) {
            i2 = -1;
        } else {
            i2 = C0739e.f3342a[cVar.ordinal()];
        }
        if (i2 == 10) {
            C0819i g = xVar.s0().g();
            if (g instanceof C0816f) {
                fVar = (C0816f) g;
            } else {
                fVar = null;
            }
            if (fVar != null) {
                C1240g gVar2 = Ne.i.e;
                if (Ne.i.b(fVar, p.Q)) {
                    return true;
                }
            }
            return true;
        } else if (i2 != 13) {
            return kotlin.jvm.internal.j.a(gVar.a(c5), xVar);
        } else {
            if (gVar instanceof C1322b) {
                Object obj = ((C1322b) gVar).f5158a;
                if (((List) obj).size() == dVar.n.size()) {
                    C0774x f5 = c5.f().f(xVar);
                    Collection collection = (Collection) obj;
                    kotlin.jvm.internal.j.e(collection, "<this>");
                    Ge.c cVar2 = new Ge.c(0, collection.size() - 1, 1);
                    if (!(cVar2 instanceof Collection) || !((Collection) cVar2).isEmpty()) {
                        Iterator it = cVar2.iterator();
                        while (((Ge.d) it).f) {
                            int nextInt = ((y) it).nextInt();
                            C1151d dVar2 = (C1151d) dVar.n.get(nextInt);
                            kotlin.jvm.internal.j.d(dVar2, "getArrayElement(...)");
                            if (!F((C1327g) ((List) obj).get(nextInt), f5, dVar2)) {
                            }
                        }
                    }
                    return true;
                }
            }
            throw new IllegalStateException(("Deserialized ArrayValue should have the same number of elements as the original array value: " + gVar).toString());
        }
        return false;
    }

    public c0 H(C0993a aVar) {
        c0 M2;
        B b = aVar.f;
        if (b == null || (M2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.M(b)) == null) {
            return (Jf.i) ((m) this.e).getValue();
        }
        return M2;
    }

    public C0774x I(V v, C0993a aVar) {
        kotlin.jvm.internal.j.e(v, "typeParameter");
        kotlin.jvm.internal.j.e(aVar, "typeAttr");
        return (C0774x) ((Gf.e) this.f).invoke(new O(v, aVar));
    }

    public int J(String str) {
        int i2;
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.e;
        kotlin.jvm.internal.j.e(concurrentHashMap, "<this>");
        Integer num = (Integer) concurrentHashMap.get(str);
        if (num != null) {
            return num.intValue();
        }
        synchronized (concurrentHashMap) {
            try {
                Integer num2 = (Integer) concurrentHashMap.get(str);
                if (num2 != null) {
                    i2 = num2.intValue();
                } else {
                    i2 = ((AtomicInteger) this.f).getAndIncrement();
                    concurrentHashMap.putIfAbsent(str, Integer.valueOf(i2));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2;
    }

    public Object K(K0.b bVar) {
        return (x0.I) this.f;
    }

    public Object L(float f5, float f8, Object obj, Object obj2, float f10, float f11, float f12) {
        K0.b bVar = (K0.b) this.e;
        bVar.f376a = f5;
        bVar.b = f8;
        bVar.f = obj;
        bVar.g = obj2;
        bVar.f377c = f10;
        bVar.d = f11;
        bVar.e = f12;
        return K(bVar);
    }

    public void M(com.samsung.context.sdk.samsunganalytics.internal.sender.d dVar) {
        SQLiteDatabase writableDatabase = ((Ld.a) this.e).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", Long.valueOf(dVar.b));
        contentValues.put("data", dVar.f4199c);
        contentValues.put("logtype", dVar.d.a());
        writableDatabase.insert("logs_v2", (String) null, contentValues);
    }

    public Re.c N(C1154g gVar, C1209f fVar) {
        kotlin.jvm.internal.j.e(gVar, "proto");
        kotlin.jvm.internal.j.e(fVar, "nameResolver");
        return ((e) this.f).E(gVar, fVar);
    }

    public Sd.t O(com.samsung.android.gallery.module.cloud.a aVar) {
        Sd.u uVar = new Sd.u(this, "notify_album_status", new Qb.c(15, aVar));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.samsung.scloud.gallery.broadcast.external_event");
        ((Context) this.e).registerReceiver(uVar, intentFilter, "com.samsung.android.scloud.gallery.permission.READ", (Handler) null, 2);
        return new Sd.t(this, uVar, 0);
    }

    public Sd.t P(com.samsung.android.gallery.module.cloud.a aVar) {
        Sd.u uVar = new Sd.u(this, "notify_service_availability", new Qb.c(16, aVar));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.samsung.scloud.gallery.broadcast.external_event");
        ((Context) this.e).registerReceiver(uVar, intentFilter, "com.samsung.android.scloud.gallery.permission.READ", (Handler) null, 2);
        return new Sd.t(this, uVar, 1);
    }

    public Sd.s Q(Handler handler, com.samsung.android.gallery.module.cloud.a aVar) {
        v vVar = new v(this, handler, aVar, 0);
        ((Context) this.e).getContentResolver().registerContentObserver(Sd.l.f3709c, true, vVar);
        return new Sd.s(0, this, vVar);
    }

    public Sd.s R(Handler handler, com.samsung.android.gallery.module.cloud.a aVar) {
        v vVar = new v(this, handler, aVar, 1);
        ((Context) this.e).getContentResolver().registerContentObserver(Sd.l.b, true, vVar);
        return new Sd.s(1, this, vVar);
    }

    public void U() {
        e0 e0Var = ((F0) this.f).m;
        e0Var.d();
        e0Var.execute(new B2.A(8, (Object) this));
    }

    public C1327g V(C0774x xVar, C1151d dVar, C1209f fVar) {
        int i2;
        boolean z;
        kotlin.jvm.internal.j.e(dVar, "value");
        kotlin.jvm.internal.j.e(fVar, "nameResolver");
        boolean booleanValue = C1208e.f4964N.c(dVar.f4818p).booleanValue();
        C1150c cVar = dVar.f;
        if (cVar == null) {
            i2 = -1;
        } else {
            i2 = C0739e.f3342a[cVar.ordinal()];
        }
        switch (i2) {
            case 1:
                byte b = (byte) ((int) dVar.g);
                if (booleanValue) {
                    return new C1320A(b);
                }
                return new C1324d(b);
            case 2:
                return new C1327g(Character.valueOf((char) ((int) dVar.g)));
            case 3:
                short s = (short) ((int) dVar.g);
                if (booleanValue) {
                    return new C1320A(s);
                }
                return new C1344x(s);
            case 4:
                int i7 = (int) dVar.g;
                if (booleanValue) {
                    return new C1320A(i7);
                }
                return new C1331k(i7);
            case 5:
                long j2 = dVar.g;
                if (booleanValue) {
                    return new C1320A(j2);
                }
                return new C1342v(j2);
            case 6:
                return new C1323c(dVar.f4814h);
            case 7:
                return new C1323c(dVar.f4815i);
            case 8:
                if (dVar.g != 0) {
                    z = true;
                } else {
                    z = false;
                }
                return new C1323c((Object) Boolean.valueOf(z));
            case 9:
                return new C1327g(fVar.getString(dVar.f4816j));
            case 10:
                return new C1341u(com.samsung.context.sdk.samsunganalytics.internal.sender.c.A(fVar, dVar.k), dVar.f4817o);
            case 11:
                return new C1329i(com.samsung.context.sdk.samsunganalytics.internal.sender.c.A(fVar, dVar.k), com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(fVar, dVar.l));
            case 12:
                C1154g gVar = dVar.m;
                kotlin.jvm.internal.j.d(gVar, "getAnnotation(...)");
                return new C1327g(E(gVar, fVar));
            case 13:
                List list = dVar.n;
                kotlin.jvm.internal.j.d(list, "getArrayElementList(...)");
                Iterable<C1151d> iterable = list;
                ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                for (C1151d dVar2 : iterable) {
                    B e7 = ((C) this.e).f().e();
                    kotlin.jvm.internal.j.b(dVar2);
                    arrayList.add(V(e7, dVar2, fVar));
                }
                return new C1346z(arrayList, xVar);
            default:
                throw new IllegalStateException(("Unsupported annotation argument type: " + dVar.f + " (expected " + xVar + ')').toString());
        }
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [com.samsung.context.sdk.samsunganalytics.internal.sender.d, java.lang.Object] */
    public LinkedBlockingQueue W(String str) {
        LinkedBlockingQueue linkedBlockingQueue = (LinkedBlockingQueue) this.f;
        linkedBlockingQueue.clear();
        Cursor rawQuery = ((Ld.a) this.e).getReadableDatabase().rawQuery(str, (String[]) null);
        while (rawQuery.moveToNext()) {
            ? obj = new Object();
            obj.f4198a = rawQuery.getString(rawQuery.getColumnIndex("_id"));
            obj.f4199c = rawQuery.getString(rawQuery.getColumnIndex("data"));
            obj.b = rawQuery.getLong(rawQuery.getColumnIndex("timestamp"));
            String string = rawQuery.getString(rawQuery.getColumnIndex("logtype"));
            com.samsung.context.sdk.samsunganalytics.internal.sender.b bVar = com.samsung.context.sdk.samsunganalytics.internal.sender.b.DEVICE;
            if (!string.equals(bVar.a())) {
                bVar = com.samsung.context.sdk.samsunganalytics.internal.sender.b.UIX;
            }
            obj.d = bVar;
            linkedBlockingQueue.add(obj);
        }
        rawQuery.close();
        return linkedBlockingQueue;
    }

    public void X(C0968a aVar, Object obj) {
        if (((IdentityHashMap) this.f) == null) {
            this.f = new IdentityHashMap(1);
        }
        ((IdentityHashMap) this.f).put(aVar, obj);
    }

    public List a(Df.z zVar, C1166t tVar) {
        kotlin.jvm.internal.j.e(zVar, "container");
        Iterable iterable = (List) tVar.j(((Cf.a) this.e).f3327h);
        if (iterable == null) {
            iterable = C1202t.d;
        }
        Iterable<C1154g> iterable2 = iterable;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable2, 10));
        for (C1154g N6 : iterable2) {
            arrayList.add(N(N6, zVar.f3369a));
        }
        return arrayList;
    }

    public void b(C0981n nVar) {
        switch (this.d) {
            case 24:
                return;
            default:
                ((r) this.e).b(nVar);
                return;
        }
    }

    public void b0() {
        ((C1056t) this.e).b0();
    }

    public List c(Df.z zVar, C1252b bVar, C0737c cVar) {
        Cf.a aVar = (Cf.a) this.e;
        kotlin.jvm.internal.j.e(bVar, "proto");
        kotlin.jvm.internal.j.e(cVar, "kind");
        if (bVar instanceof C1171y) {
            aVar.getClass();
        } else if (bVar instanceof G) {
            int i2 = C0735a.f3341a[cVar.ordinal()];
            if (i2 == 1 || i2 == 2 || i2 == 3) {
                aVar.getClass();
            } else {
                throw new IllegalStateException(("Unsupported callable kind with property proto for receiver annotations: " + cVar).toString());
            }
        } else {
            throw new IllegalStateException(("Unknown message: " + bVar).toString());
        }
        Iterable<C1154g> iterable = C1202t.d;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (C1154g N6 : iterable) {
            arrayList.add(N(N6, zVar.f3369a));
        }
        return arrayList;
    }

    public ArrayList d(lf.Q q, C1209f fVar) {
        kotlin.jvm.internal.j.e(q, "proto");
        kotlin.jvm.internal.j.e(fVar, "nameResolver");
        Iterable iterable = (List) q.j(((Cf.a) this.e).k);
        if (iterable == null) {
            iterable = C1202t.d;
        }
        Iterable<C1154g> iterable2 = iterable;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable2, 10));
        for (C1154g N6 : iterable2) {
            arrayList.add(N(N6, fVar));
        }
        return arrayList;
    }

    public C1220i d0(X x9, List list, C0993a aVar) {
        c0 c0Var;
        boolean z;
        boolean z3;
        boolean z7;
        X x10 = x9;
        C0993a aVar2 = aVar;
        C1220i iVar = new C1220i();
        Iterator it = list.iterator();
        if (it.hasNext()) {
            C0774x xVar = (C0774x) it.next();
            C0819i g = xVar.s0().g();
            if (g instanceof C0816f) {
                Set set = aVar2.e;
                c0 x02 = xVar.x0();
                if (x02 instanceof C0768q) {
                    C0768q qVar = (C0768q) x02;
                    B b = qVar.e;
                    if (!b.s0().getParameters().isEmpty() && b.s0().g() != null) {
                        List parameters = b.s0().getParameters();
                        kotlin.jvm.internal.j.d(parameters, "getParameters(...)");
                        Iterable<V> iterable = parameters;
                        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                        for (V v : iterable) {
                            P p6 = (P) C1194l.O0(v.getIndex(), xVar.e0());
                            if (set == null || !set.contains(v)) {
                                z7 = false;
                            } else {
                                z7 = true;
                            }
                            if (p6 != null && !z7) {
                                T f5 = x10.f();
                                C0774x b5 = p6.b();
                                kotlin.jvm.internal.j.d(b5, "getType(...)");
                                if (f5.d(b5) != null) {
                                    arrayList.add(p6);
                                }
                            }
                            p6 = new Hf.G(v);
                            arrayList.add(p6);
                        }
                        b = C0754c.r(b, arrayList, (I) null, 2);
                    }
                    B b8 = qVar.f;
                    if (!b8.s0().getParameters().isEmpty() && b8.s0().g() != null) {
                        List parameters2 = b8.s0().getParameters();
                        kotlin.jvm.internal.j.d(parameters2, "getParameters(...)");
                        Iterable<V> iterable2 = parameters2;
                        ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
                        for (V v6 : iterable2) {
                            P p8 = (P) C1194l.O0(v6.getIndex(), xVar.e0());
                            if (set == null || !set.contains(v6)) {
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                            if (p8 != null && !z3) {
                                T f8 = x10.f();
                                C0774x b10 = p8.b();
                                kotlin.jvm.internal.j.d(b10, "getType(...)");
                                if (f8.d(b10) != null) {
                                    arrayList2.add(p8);
                                }
                            }
                            p8 = new Hf.G(v6);
                            arrayList2.add(p8);
                        }
                        b8 = C0754c.r(b8, arrayList2, (I) null, 2);
                    }
                    c0Var = C0754c.f(b, b8);
                } else if (x02 instanceof B) {
                    B b11 = (B) x02;
                    if (b11.s0().getParameters().isEmpty() || b11.s0().g() == null) {
                        c0Var = b11;
                    } else {
                        List parameters3 = b11.s0().getParameters();
                        kotlin.jvm.internal.j.d(parameters3, "getParameters(...)");
                        Iterable<V> iterable3 = parameters3;
                        ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable3, 10));
                        for (V v8 : iterable3) {
                            P p10 = (P) C1194l.O0(v8.getIndex(), xVar.e0());
                            if (set == null || !set.contains(v8)) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (p10 != null && !z) {
                                T f10 = x10.f();
                                C0774x b12 = p10.b();
                                kotlin.jvm.internal.j.d(b12, "getType(...)");
                                if (f10.d(b12) != null) {
                                    arrayList3.add(p10);
                                }
                            }
                            p10 = new Hf.G(v8);
                            arrayList3.add(p10);
                        }
                        c0Var = C0754c.r(b11, arrayList3, (I) null, 2);
                    }
                } else {
                    throw new RuntimeException();
                }
                iVar.add(x10.g(C0754c.i(c0Var, x02), Hf.d0.OUT_VARIANCE));
            } else if (g instanceof V) {
                Set set2 = aVar2.e;
                if (set2 == null || !set2.contains(g)) {
                    List upperBounds = ((V) g).getUpperBounds();
                    kotlin.jvm.internal.j.d(upperBounds, "getUpperBounds(...)");
                    iVar.addAll(d0(x10, upperBounds, aVar2));
                } else {
                    iVar.add(H(aVar2));
                }
            }
        }
        return B1.a.d(iVar);
    }

    public void e(G0.c cVar) {
        switch (this.d) {
            case 24:
                return;
            default:
                ((r) this.e).e(cVar);
                return;
        }
    }

    public void e0(C0975h hVar, C0971d dVar) {
        e0 e0Var = ((F0) this.f).m;
        e0Var.d();
        F.n(hVar, "newState");
        e0Var.execute(new S1.j(this, dVar, hVar, 4));
    }

    public void f(int i2) {
        switch (this.d) {
            case 24:
                synchronized (((fe.i) this.e)) {
                    fe.i iVar = (fe.i) this.e;
                    iVar.f4356p += i2;
                    iVar.c();
                }
                return;
            default:
                ((r) this.e).f(i2);
                return;
        }
    }

    public void f0(c cVar, boolean z) {
        boolean z3;
        String str;
        P0.b bVar = (P0.b) this.f;
        if (!z || !bVar.d()) {
            z3 = false;
        } else {
            z3 = true;
        }
        b bVar2 = cVar.d;
        int length = bVar2.e.length;
        if (z3) {
            bVar.c("  size: ".concat(L2.a.E(length)));
        }
        bVar.o(length);
        for (int i2 = 0; i2 < length; i2++) {
            S2.a aVar = (S2.a) bVar2.d(i2);
            if (z3) {
                StringBuilder sb2 = new StringBuilder("  [");
                sb2.append(Integer.toHexString(i2));
                sb2.append("] ");
                if (C(aVar) == 30) {
                    str = "null";
                } else {
                    str = aVar.e() + ' ' + aVar.a();
                }
                sb2.append(str);
                bVar.c(sb2.toString());
            }
            C0056f fVar = (C0056f) this.e;
            int C5 = C(aVar);
            if (!(C5 == 0 || C5 == 6 || C5 == 2)) {
                if (C5 == 3) {
                    i0(C5, ((n) aVar).h());
                } else if (C5 != 4) {
                    if (C5 == 16) {
                        g0(C5, ((long) ((i) aVar).d) << 32);
                    } else if (C5 != 17) {
                        switch (C5) {
                            case 23:
                                i0(C5, (long) fVar.e.l((t) aVar));
                                break;
                            case 24:
                                i0(C5, (long) fVar.f.m((u) aVar));
                                break;
                            case 25:
                                i0(C5, (long) fVar.f595h.l((h) aVar));
                                break;
                            case 26:
                                i0(C5, (long) fVar.f596i.l((q) aVar));
                                break;
                            case 27:
                                throw new ClassCastException();
                            case 28:
                                bVar.k(C5);
                                f0((c) aVar, false);
                                break;
                            case 29:
                                bVar.k(C5);
                                throw new ClassCastException();
                            case 30:
                                bVar.k(C5);
                                break;
                            case 31:
                                bVar.k((((d) aVar).d << 5) | C5);
                                break;
                            default:
                                throw new RuntimeException("Shouldn't happen");
                        }
                    } else {
                        g0(C5, ((g) aVar).d);
                    }
                }
            }
            long h5 = ((n) aVar).h();
            int numberOfLeadingZeros = (72 - Long.numberOfLeadingZeros((h5 >> 63) ^ h5)) >> 3;
            bVar.k(C5 | ((numberOfLeadingZeros - 1) << 5));
            while (numberOfLeadingZeros > 0) {
                bVar.k((byte) ((int) h5));
                h5 >>= 8;
                numberOfLeadingZeros--;
            }
        }
        if (z3) {
            bVar.f();
        }
    }

    public void flush() {
        switch (this.d) {
            case 24:
                return;
            default:
                ((r) this.e).flush();
                return;
        }
    }

    public void g(int i2) {
        switch (this.d) {
            case 24:
                return;
            default:
                ((r) this.e).g(i2);
                return;
        }
    }

    public void g0(int i2, long j2) {
        P0.b bVar = (P0.b) this.f;
        int numberOfTrailingZeros = 64 - Long.numberOfTrailingZeros(j2);
        if (numberOfTrailingZeros == 0) {
            numberOfTrailingZeros = 1;
        }
        int i7 = (numberOfTrailingZeros + 7) >> 3;
        long j3 = j2 >> (64 - (i7 * 8));
        bVar.k(i2 | ((i7 - 1) << 5));
        while (i7 > 0) {
            bVar.k((byte) ((int) j3));
            j3 >>= 8;
            i7--;
        }
    }

    public void h(int i2) {
        switch (this.d) {
            case 24:
                return;
            default:
                ((r) this.e).h(i2);
                return;
        }
    }

    public void h0(fe.i iVar) {
        ((C1056t) this.e).h0(iVar);
    }

    public List i(Df.z zVar, C1252b bVar, C0737c cVar) {
        Iterable iterable;
        Cf.a aVar = (Cf.a) this.e;
        kotlin.jvm.internal.j.e(bVar, "proto");
        kotlin.jvm.internal.j.e(cVar, "kind");
        if (bVar instanceof C1159l) {
            iterable = (List) ((C1159l) bVar).j(aVar.b);
        } else if (bVar instanceof C1171y) {
            iterable = (List) ((C1171y) bVar).j(aVar.d);
        } else if (bVar instanceof G) {
            int i2 = C0735a.f3341a[cVar.ordinal()];
            if (i2 == 1) {
                iterable = (List) ((G) bVar).j(aVar.e);
            } else if (i2 == 2) {
                iterable = (List) ((G) bVar).j(aVar.f);
            } else if (i2 == 3) {
                iterable = (List) ((G) bVar).j(aVar.g);
            } else {
                throw new IllegalStateException("Unsupported callable kind with property proto");
            }
        } else {
            throw new IllegalStateException(("Unknown message: " + bVar).toString());
        }
        if (iterable == null) {
            iterable = C1202t.d;
        }
        Iterable<C1154g> iterable2 = iterable;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable2, 10));
        for (C1154g N6 : iterable2) {
            arrayList.add(N(N6, zVar.f3369a));
        }
        return arrayList;
    }

    public void i0(int i2, long j2) {
        P0.b bVar = (P0.b) this.f;
        int numberOfLeadingZeros = 64 - Long.numberOfLeadingZeros(j2);
        if (numberOfLeadingZeros == 0) {
            numberOfLeadingZeros = 1;
        }
        int i7 = (numberOfLeadingZeros + 7) >> 3;
        bVar.k(i2 | ((i7 - 1) << 5));
        while (i7 > 0) {
            bVar.k((byte) ((int) j2));
            j2 >>= 8;
            i7--;
        }
    }

    public boolean isReady() {
        switch (this.d) {
            case 24:
                return ((fe.q) this.f).f4363a.k();
            default:
                return ((r) this.e).isReady();
        }
    }

    public void j() {
        switch (this.d) {
            case 24:
                return;
            default:
                ((r) this.e).j();
                return;
        }
    }

    public void k(C1056t tVar) {
        switch (this.d) {
            case 24:
                synchronized (((fe.i) this.e)) {
                    fe.i iVar = (fe.i) this.e;
                    fe.q qVar = (fe.q) this.f;
                    iVar.d = qVar;
                    iVar.e = qVar.f4364c;
                    iVar.f = tVar;
                    if (iVar.n != fe.j.CLOSED) {
                        iVar.h(fe.j.INITIALIZED);
                    }
                }
                if (((fe.q) this.f).f4363a.k()) {
                    tVar.b0();
                }
                try {
                    synchronized (((fe.q) this.f)) {
                        fe.q qVar2 = (fe.q) this.f;
                        qVar2.e = true;
                        qVar2.c();
                    }
                    return;
                } catch (b0 e7) {
                    synchronized (((fe.i) this.e)) {
                        a0 a0Var = e7.d;
                        ((fe.i) this.e).b(a0Var, a0Var, false);
                        return;
                    }
                }
            default:
                f fVar = ((C1019e0) this.f).b;
                ((C1048o0) fVar.f).y();
                ((Q0) fVar.e).getClass();
                TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
                ((r) this.e).k(new e((Object) this, (Object) tVar, false, 26));
                return;
        }
    }

    public List l(Df.z zVar, G g) {
        kotlin.jvm.internal.j.e(g, "proto");
        ((Cf.a) this.e).getClass();
        Iterable<C1154g> iterable = C1202t.d;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (C1154g N6 : iterable) {
            arrayList.add(N(N6, zVar.f3369a));
        }
        return arrayList;
    }

    public List m(Df.z zVar, G g) {
        kotlin.jvm.internal.j.e(g, "proto");
        ((Cf.a) this.e).getClass();
        Iterable<C1154g> iterable = C1202t.d;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (C1154g N6 : iterable) {
            arrayList.add(N(N6, zVar.f3369a));
        }
        return arrayList;
    }

    public ArrayList n(W w, C1209f fVar) {
        kotlin.jvm.internal.j.e(w, "proto");
        kotlin.jvm.internal.j.e(fVar, "nameResolver");
        Iterable iterable = (List) w.j(((Cf.a) this.e).l);
        if (iterable == null) {
            iterable = C1202t.d;
        }
        Iterable<C1154g> iterable2 = iterable;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable2, 10));
        for (C1154g N6 : iterable2) {
            arrayList.add(N(N6, fVar));
        }
        return arrayList;
    }

    public Object o(Df.z zVar, G g, C0774x xVar) {
        kotlin.jvm.internal.j.e(g, "proto");
        C1151d dVar = (C1151d) D1.f.q(g, ((Cf.a) this.e).f3328i);
        if (dVar == null) {
            return null;
        }
        return ((e) this.f).V(xVar, dVar, zVar.f3369a);
    }

    public int onFinish() {
        switch (this.d) {
            case 3:
                return 0;
            default:
                return 0;
        }
    }

    public List p(Df.z zVar, C1252b bVar, C0737c cVar, int i2, Z z) {
        kotlin.jvm.internal.j.e(bVar, "callableProto");
        kotlin.jvm.internal.j.e(cVar, "kind");
        Iterable iterable = (List) z.j(((Cf.a) this.e).f3329j);
        if (iterable == null) {
            iterable = C1202t.d;
        }
        Iterable<C1154g> iterable2 = iterable;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable2, 10));
        for (C1154g N6 : iterable2) {
            arrayList.add(N(N6, zVar.f3369a));
        }
        return arrayList;
    }

    public ArrayList q(x xVar) {
        kotlin.jvm.internal.j.e(xVar, "container");
        Iterable iterable = (List) xVar.d.j(((Cf.a) this.e).f3326c);
        if (iterable == null) {
            iterable = C1202t.d;
        }
        Iterable<C1154g> iterable2 = iterable;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable2, 10));
        for (C1154g N6 : iterable2) {
            arrayList.add(N(N6, xVar.f3369a));
        }
        return arrayList;
    }

    public Object r(Df.z zVar, G g, C0774x xVar) {
        kotlin.jvm.internal.j.e(g, "proto");
        return null;
    }

    public void run() {
        String str;
        HashMap hashMap;
        switch (this.d) {
            case 3:
                Map map = (Map) this.e;
                Ed.b bVar = (Ed.b) this.f;
                boolean b = Ed.b.b(bVar);
                C0732c cVar = (C0732c) bVar.d;
                Context context = (Context) bVar.e;
                if (b) {
                    if (710000000 > C0068a.x(context)) {
                        if (!cVar.d.M()) {
                            C0068a.b("user do not agree");
                            return;
                        } else {
                            map.remove("pd");
                            map.remove("ps");
                        }
                    }
                    if (map == null || map.isEmpty()) {
                        C0068a.b("Failure to send Logs : No data");
                        return;
                    }
                    if (og.k.f < 2) {
                        cVar.getClass();
                        if (TextUtils.isEmpty((CharSequence) null)) {
                            C0068a.b("did is empty");
                            return;
                        }
                    }
                    if ("pp".equals(map.get("t"))) {
                        map.remove("t");
                        SharedPreferences sharedPreferences = context.getSharedPreferences(com.samsung.context.sdk.samsunganalytics.internal.sender.c.D("SAProperties"), 0);
                        for (Map.Entry entry : map.entrySet()) {
                            if (TextUtils.isEmpty((CharSequence) entry.getValue())) {
                                sharedPreferences.edit().remove((String) entry.getKey()).apply();
                            } else {
                                sharedPreferences.edit().putString((String) entry.getKey(), (String) entry.getValue()).apply();
                            }
                        }
                        C0246a.k0(context, cVar);
                        return;
                    }
                    if ("ev".equals(map.get("t")) && (str = (String) map.get("et")) != null && (str.equals(String.valueOf(10)) || str.equals(String.valueOf(11)))) {
                        String string = context.getSharedPreferences(com.samsung.context.sdk.samsunganalytics.internal.sender.c.D("SAProperties"), 0).getString("guid", "");
                        if (!TextUtils.isEmpty(string)) {
                            String str2 = (String) map.get("cd");
                            if (TextUtils.isEmpty(str2)) {
                                hashMap = new HashMap();
                            } else {
                                Od.d dVar = Od.d.TWO_DEPTH;
                                HashMap hashMap2 = new HashMap();
                                for (String split : str2.split(dVar.a())) {
                                    String[] split2 = split.split(dVar.b());
                                    if (split2.length > 1) {
                                        hashMap2.put(split2[0], split2[1]);
                                    }
                                }
                                hashMap = hashMap2;
                            }
                            hashMap.put("guid", string);
                            map.put("cd", C0246a.f0(Gd.a.f(hashMap), Od.d.TWO_DEPTH));
                        }
                    }
                    com.samsung.context.sdk.samsunganalytics.internal.sender.c.w((Application) bVar.f3372c, og.k.f, cVar).c(map);
                    return;
                }
                return;
            default:
                Ed.b bVar2 = (Ed.b) this.f;
                Context context2 = (Context) bVar2.e;
                HashMap hashMap3 = (HashMap) this.e;
                C0068a.g("register setting status");
                SharedPreferences E = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(context2);
                Set<String> stringSet = E.getStringSet("AppPrefs", new HashSet());
                HashSet hashSet = new HashSet();
                if (C0246a.a0(context2, "appVersionForSetting")) {
                    for (String remove : stringSet) {
                        E.edit().remove(remove).apply();
                    }
                    E.edit().remove("AppPrefs").apply();
                } else {
                    hashSet.addAll(stringSet);
                }
                for (Map.Entry entry2 : hashMap3.entrySet()) {
                    String str3 = (String) entry2.getKey();
                    hashSet.add(str3);
                    HashSet hashSet2 = new HashSet();
                    hashSet2.addAll(E.getStringSet(str3, new HashSet()));
                    hashSet2.addAll((Collection) entry2.getValue());
                    E.edit().putStringSet(str3, hashSet2).apply();
                }
                E.edit().putStringSet("AppPrefs", hashSet).apply();
                if (Ed.b.b(bVar2)) {
                    C0246a.l0((Context) bVar2.e, (C0732c) bVar2.d);
                    return;
                }
                return;
        }
    }

    public void t() {
        switch (this.d) {
            case 24:
                try {
                    synchronized (((fe.q) this.f)) {
                        fe.q qVar = (fe.q) this.f;
                        qVar.f4365h = true;
                        qVar.c();
                    }
                    return;
                } catch (b0 e7) {
                    synchronized (((fe.i) this.e)) {
                        a0 a0Var = e7.d;
                        ((fe.i) this.e).b(a0Var, a0Var, false);
                        return;
                    }
                }
            default:
                ((r) this.e).t();
                return;
        }
    }

    public String toString() {
        switch (this.d) {
            case 18:
                return (String) this.e;
            case 24:
                return "MultiMessageClientStream[" + ((fe.i) this.e) + "/" + ((fe.q) this.f) + "]";
            case 26:
                E2.k V = B1.a.V(this);
                V.a((C1056t) this.e, "delegate");
                return V.toString();
            case 27:
                E2.k V5 = B1.a.V(this);
                V5.a((r) this.e, "delegate");
                return V5.toString();
            default:
                return super.toString();
        }
    }

    public void u(Deadline deadline) {
        switch (this.d) {
            case 24:
                synchronized (((fe.q) this.f)) {
                    M m = ((fe.q) this.f).l;
                    ee.G g = ge.Z.f4490c;
                    m.a(g);
                    TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                    m.c(g, Long.valueOf(Math.max(0, deadline.b())));
                }
                return;
            default:
                ((r) this.e).u(deadline);
                return;
        }
    }

    public void u0(a0 a0Var, C1054s sVar, M m) {
        f fVar = ((C1019e0) ((e) this.f).f).b;
        if (a0Var.e()) {
            ((C1048o0) fVar.g).y();
        } else {
            ((C1048o0) fVar.f106h).y();
        }
        ((C1056t) this.e).u0(a0Var, sVar, m);
    }

    public void v(a0 a0Var) {
        switch (this.d) {
            case 24:
                synchronized (((fe.i) this.e)) {
                    ((fe.i) this.e).b(a0.f, a0Var, false);
                }
                return;
            default:
                ((r) this.e).v(a0Var);
                return;
        }
    }

    public void w(C0973f fVar) {
        switch (this.d) {
            case 24:
                return;
            default:
                ((r) this.e).w(fVar);
                return;
        }
    }

    public void x(C1076a aVar) {
        switch (this.d) {
            case 24:
                try {
                    synchronized (((fe.q) this.f)) {
                        fe.q qVar = (fe.q) this.f;
                        qVar.e = true;
                        ConcurrentLinkedQueue concurrentLinkedQueue = qVar.g;
                        if (concurrentLinkedQueue != null) {
                            concurrentLinkedQueue.add(aVar);
                        } else if (qVar.f == null) {
                            qVar.f = aVar;
                        } else {
                            ConcurrentLinkedQueue concurrentLinkedQueue2 = new ConcurrentLinkedQueue();
                            qVar.g = concurrentLinkedQueue2;
                            concurrentLinkedQueue2.add(aVar);
                        }
                        ((fe.q) this.f).c();
                    }
                    return;
                } catch (b0 e7) {
                    synchronized (((fe.i) this.e)) {
                        a0 a0Var = e7.d;
                        ((fe.i) this.e).b(a0Var, a0Var, false);
                        return;
                    }
                }
            default:
                ((r) this.e).x(aVar);
                return;
        }
    }

    public void z(M m) {
        ((C1056t) this.e).z(m);
    }

    public /* synthetic */ e(Object obj, Object obj2, boolean z, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = obj2;
    }

    public e(C c5, f fVar, Cf.a aVar) {
        this.d = 1;
        kotlin.jvm.internal.j.e(c5, "module");
        kotlin.jvm.internal.j.e(aVar, "protocol");
        this.e = aVar;
        this.f = new e(c5, fVar);
    }

    public e(ef.f fVar) {
        this.d = 7;
        Gf.m mVar = new Gf.m("Type parameter upper bound erasure results");
        this.e = L1.d.q(new Af.g(9, this));
        this.f = mVar.b(new C0736b(5, this));
    }

    public e(Ae.b bVar) {
        this.d = 10;
        this.e = bVar;
        this.f = new ConcurrentHashMap();
    }

    public e(Object obj) {
        this.d = 5;
        this.e = obj;
        this.f = Thread.currentThread();
    }

    public e(Context context, int i2) {
        this.d = i2;
        switch (i2) {
            case 17:
                this.f = String.format("[SCG-SDK][%s][%S]", new Object[]{"0.0.0019", "SCGalleryObserver"});
                this.e = context;
                return;
            default:
                SQLiteOpenHelper sQLiteOpenHelper = new SQLiteOpenHelper(context, "SamsungAnalytics.db", (SQLiteDatabase.CursorFactory) null, 1);
                this.f = new LinkedBlockingQueue();
                this.e = sQLiteOpenHelper;
                sQLiteOpenHelper.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS logs_v2 (_id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp INTEGER, logtype TEXT, data TEXT)");
                sQLiteOpenHelper.getWritableDatabase().delete("logs_v2", "timestamp <= 5", (String[]) null);
                return;
        }
    }

    private final void G() {
    }

    private final void T() {
    }

    private final void Y(C0973f fVar) {
    }

    private final void Z(C0981n nVar) {
    }

    private final void a0(int i2) {
    }

    private final void c0(int i2) {
    }

    private final void y(G0.c cVar) {
    }

    public e(C c5, f fVar) {
        this.d = 2;
        kotlin.jvm.internal.j.e(c5, "module");
        kotlin.jvm.internal.j.e(fVar, "notFoundClasses");
        this.e = c5;
        this.f = fVar;
    }

    public e(x0.I i2) {
        this.d = 9;
        this.e = new Object();
        this.f = i2;
    }

    public e(String str, W2.a aVar) {
        this.d = 18;
        System.currentTimeMillis();
        this.e = str;
        this.f = aVar;
    }

    public e(a0 a0Var, Object obj) {
        this.d = 21;
        F.n(a0Var, "status");
        this.e = a0Var;
        this.f = obj;
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 131 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public e(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 25
            r6.d = r0
            java.util.logging.Logger r0 = ee.E.f4270c
            java.lang.Class<ee.E> r0 = ee.E.class
            monitor-enter(r0)
            ee.E r1 = ee.E.d     // Catch:{ all -> 0x005e }
            if (r1 != 0) goto L_0x0065
            java.lang.Class<ee.D> r1 = ee.C0967D.class
            java.util.List r2 = ee.E.e     // Catch:{ all -> 0x005e }
            java.lang.Class<ee.D> r3 = ee.C0967D.class
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch:{ all -> 0x005e }
            ee.Z r4 = new ee.Z     // Catch:{ all -> 0x005e }
            r5 = 3
            r4.<init>(r5)     // Catch:{ all -> 0x005e }
            java.util.List r1 = ee.C0971d.a(r1, r2, r3, r4)     // Catch:{ all -> 0x005e }
            ee.E r2 = new ee.E     // Catch:{ all -> 0x005e }
            r2.<init>()     // Catch:{ all -> 0x005e }
            ee.E.d = r2     // Catch:{ all -> 0x005e }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x005e }
        L_0x002c:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x005e }
            if (r2 == 0) goto L_0x0060
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x005e }
            ee.D r2 = (ee.C0967D) r2     // Catch:{ all -> 0x005e }
            java.util.logging.Logger r3 = ee.E.f4270c     // Catch:{ all -> 0x005e }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x005e }
            r4.<init>()     // Catch:{ all -> 0x005e }
            java.lang.String r5 = "Service loader found "
            r4.append(r5)     // Catch:{ all -> 0x005e }
            r4.append(r2)     // Catch:{ all -> 0x005e }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x005e }
            r3.fine(r4)     // Catch:{ all -> 0x005e }
            ee.E r3 = ee.E.d     // Catch:{ all -> 0x005e }
            monitor-enter(r3)     // Catch:{ all -> 0x005e }
            r2.getClass()     // Catch:{ all -> 0x005b }
            java.util.LinkedHashSet r4 = r3.f4271a     // Catch:{ all -> 0x005b }
            r4.add(r2)     // Catch:{ all -> 0x005b }
            monitor-exit(r3)     // Catch:{ all -> 0x005e }
            goto L_0x002c
        L_0x005b:
            r6 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x005b }
            throw r6     // Catch:{ all -> 0x005e }
        L_0x005e:
            r6 = move-exception
            goto L_0x007a
        L_0x0060:
            ee.E r1 = ee.E.d     // Catch:{ all -> 0x005e }
            r1.b()     // Catch:{ all -> 0x005e }
        L_0x0065:
            ee.E r1 = ee.E.d     // Catch:{ all -> 0x005e }
            monitor-exit(r0)
            r6.<init>()
            java.lang.String r0 = "registry"
            He.F.n(r1, r0)
            r6.e = r1
            java.lang.String r0 = "defaultPolicy"
            He.F.n(r7, r0)
            r6.f = r7
            return
        L_0x007a:
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: D0.e.<init>(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Object, N2.t] */
    public e(t1.i iVar, int i2, int i7, int i8) {
        this.d = 13;
        ? obj = new Object();
        obj.d = i8;
        obj.f = new ArrayList(i2);
        obj.e = -1;
        this.e = obj;
        this.f = new ArrayList(i7);
    }

    public e(int i2) {
        this.d = i2;
        switch (i2) {
            case 9:
                this.e = new Object();
                this.f = null;
                return;
            case 16:
                this.e = new Rect();
                this.f = new Rect();
                return;
            case 22:
                return;
            default:
                this.e = new ConcurrentHashMap();
                this.f = new AtomicInteger(0);
                return;
        }
    }

    public e(C0056f fVar, P0.b bVar) {
        this.d = 15;
        if (fVar != null) {
            this.e = fVar;
            this.f = bVar;
            return;
        }
        throw new NullPointerException("file == null");
    }

    public e(Map map) {
        this.d = 19;
        this.e = map;
        this.f = new Gf.m("Java nullability annotation states").c(new C0736b(13, this));
    }

    public e(C0969b bVar) {
        this.d = 20;
        this.e = bVar;
    }

    public e(d0 d0Var, ScheduledFuture scheduledFuture) {
        this.d = 23;
        this.e = d0Var;
        F.n(scheduledFuture, "future");
        this.f = scheduledFuture;
    }

    public e(F0 f02) {
        this.d = 28;
        this.f = f02;
    }
}
