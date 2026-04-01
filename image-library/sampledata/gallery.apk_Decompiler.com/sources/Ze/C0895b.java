package Ze;

import Qe.C0816f;
import Re.b;
import Re.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1200r;
import ne.C1202t;
import o1.C0246a;
import qf.C1236c;
import qf.C1240g;
import vf.C1322b;
import vf.C1327g;
import vf.C1329i;
import xf.C1353d;

/* renamed from: Ze.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0895b {

    /* renamed from: c  reason: collision with root package name */
    public static final LinkedHashMap f3937c;

    /* renamed from: a  reason: collision with root package name */
    public final t f3938a;
    public final ConcurrentHashMap b = new ConcurrentHashMap();

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (C0894a aVar : C0894a.values()) {
            String a7 = aVar.a();
            if (linkedHashMap.get(a7) == null) {
                linkedHashMap.put(a7, aVar);
            }
        }
        f3937c = linkedHashMap;
    }

    public C0895b(t tVar) {
        j.e(tVar, "javaTypeEnhancementState");
        this.f3938a = tVar;
    }

    public static ArrayList a(Object obj, boolean z) {
        Iterable iterable;
        b bVar = (b) obj;
        j.e(bVar, "<this>");
        Map a7 = bVar.a();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : a7.entrySet()) {
            C1240g gVar = (C1240g) entry.getKey();
            C1327g gVar2 = (C1327g) entry.getValue();
            if (!z || j.a(gVar, x.b)) {
                iterable = j(gVar2);
            } else {
                iterable = C1202t.d;
            }
            C1200r.A0(iterable, arrayList);
        }
        return arrayList;
    }

    public static Object c(Object obj, C1236c cVar) {
        for (Object next : e(obj)) {
            if (j.a(d(next), cVar)) {
                return next;
            }
        }
        return null;
    }

    public static C1236c d(Object obj) {
        b bVar = (b) obj;
        j.e(bVar, "<this>");
        return bVar.b();
    }

    public static Iterable e(Object obj) {
        h annotations;
        b bVar = (b) obj;
        j.e(bVar, "<this>");
        C0816f d = C1353d.d(bVar);
        if (d == null || (annotations = d.getAnnotations()) == null) {
            return C1202t.d;
        }
        return annotations;
    }

    public static boolean f(Object obj, C1236c cVar) {
        Iterable<Object> e = e(obj);
        if ((e instanceof Collection) && ((Collection) e).isEmpty()) {
            return false;
        }
        for (Object d : e) {
            if (j.a(d(d), cVar)) {
                return true;
            }
        }
        return false;
    }

    public static List j(C1327g gVar) {
        if (gVar instanceof C1322b) {
            ArrayList arrayList = new ArrayList();
            for (C1327g j2 : (Iterable) ((C1322b) gVar).f5158a) {
                C1200r.A0(j(j2), arrayList);
            }
            return arrayList;
        } else if (gVar instanceof C1329i) {
            return C0246a.e0(((C1329i) gVar).f5159c.c());
        } else {
            return C1202t.d;
        }
    }

    /* JADX WARNING: type inference failed for: r11v16, types: [java.util.Map, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x001c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0198  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Ze.u b(Ze.u r18, Re.h r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            java.lang.String r2 = "annotations"
            r3 = r19
            kotlin.jvm.internal.j.e(r3, r2)
            Ze.t r2 = r0.f3938a
            boolean r4 = r2.b
            if (r4 == 0) goto L_0x0013
            goto L_0x020d
        L_0x0013:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r3 = r3.iterator()
        L_0x001c:
            boolean r5 = r3.hasNext()
            r6 = 1
            r7 = 0
            if (r5 == 0) goto L_0x019d
            java.lang.Object r5 = r3.next()
            boolean r8 = r2.b
            r9 = 0
            if (r8 == 0) goto L_0x002f
        L_0x002d:
            r12 = r9
            goto L_0x0083
        L_0x002f:
            java.util.LinkedHashMap r8 = Ze.n.e
            qf.c r10 = d(r5)
            java.lang.Object r8 = r8.get(r10)
            Ze.m r8 = (Ze.m) r8
            if (r8 == 0) goto L_0x002d
            qf.c r10 = d(r5)
            if (r10 == 0) goto L_0x0054
            java.lang.Object r11 = Ze.n.f3950c
            boolean r11 = r11.containsKey(r10)
            if (r11 == 0) goto L_0x0054
            Ze.s r11 = Ze.s.d
            java.lang.Object r10 = r11.invoke(r10)
            Ze.C r10 = (Ze.C) r10
            goto L_0x005f
        L_0x0054:
            Ze.C r10 = r0.h(r5)
            if (r10 == 0) goto L_0x005b
            goto L_0x005f
        L_0x005b:
            Ze.v r10 = r2.f3958a
            Ze.C r10 = r10.f3960a
        L_0x005f:
            Ze.C r11 = Ze.C.IGNORE
            if (r10 == r11) goto L_0x0064
            goto L_0x0065
        L_0x0064:
            r10 = r9
        L_0x0065:
            if (r10 != 0) goto L_0x0068
            goto L_0x002d
        L_0x0068:
            hf.h r11 = r8.f3947a
            Ze.C r12 = Ze.C.WARN
            if (r10 != r12) goto L_0x0070
            r10 = r6
            goto L_0x0071
        L_0x0070:
            r10 = r7
        L_0x0071:
            hf.h r10 = hf.C1086h.a(r11, r9, r10, r6)
            java.util.Collection r11 = r8.b
            boolean r8 = r8.f3948c
            java.lang.String r12 = "qualifierApplicabilityTypes"
            kotlin.jvm.internal.j.e(r11, r12)
            Ze.m r12 = new Ze.m
            r12.<init>(r10, r11, r8)
        L_0x0083:
            if (r12 == 0) goto L_0x0088
            r9 = r12
            goto L_0x0196
        L_0x0088:
            Ze.v r8 = r2.f3958a
            boolean r8 = r8.d
            if (r8 == 0) goto L_0x0091
        L_0x008e:
            r8 = r9
            goto L_0x012a
        L_0x0091:
            qf.c r8 = Ze.y.f
            java.lang.Object r8 = c(r5, r8)
            if (r8 != 0) goto L_0x009a
            goto L_0x008e
        L_0x009a:
            java.lang.Iterable r10 = e(r5)
            java.util.Iterator r10 = r10.iterator()
        L_0x00a2:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x00b3
            java.lang.Object r11 = r10.next()
            java.lang.Object r12 = r0.i(r11)
            if (r12 == 0) goto L_0x00a2
            goto L_0x00b4
        L_0x00b3:
            r11 = r9
        L_0x00b4:
            if (r11 != 0) goto L_0x00b7
            goto L_0x008e
        L_0x00b7:
            java.util.ArrayList r8 = a(r8, r6)
            java.util.LinkedHashSet r10 = new java.util.LinkedHashSet
            r10.<init>()
            java.util.Iterator r8 = r8.iterator()
        L_0x00c4:
            boolean r12 = r8.hasNext()
            if (r12 == 0) goto L_0x00de
            java.lang.Object r12 = r8.next()
            java.lang.String r12 = (java.lang.String) r12
            java.util.LinkedHashMap r13 = f3937c
            java.lang.Object r12 = r13.get(r12)
            Ze.a r12 = (Ze.C0894a) r12
            if (r12 == 0) goto L_0x00c4
            r10.add(r12)
            goto L_0x00c4
        L_0x00de:
            me.i r8 = new me.i
            Ze.a r12 = Ze.C0894a.TYPE_USE
            boolean r12 = r10.contains(r12)
            if (r12 == 0) goto L_0x0127
            Ze.a[] r12 = Ze.C0894a.values()
            java.util.Set r12 = ne.C1192j.z0(r12)
            Ze.a r13 = Ze.C0894a.TYPE_PARAMETER_BOUNDS
            java.util.LinkedHashSet r14 = new java.util.LinkedHashSet
            int r15 = r12.size()
            int r15 = ne.z.Z(r15)
            r14.<init>(r15)
            java.util.Iterator r12 = r12.iterator()
            r15 = r7
        L_0x0104:
            boolean r16 = r12.hasNext()
            if (r16 == 0) goto L_0x0123
            java.lang.Object r6 = r12.next()
            if (r15 != 0) goto L_0x011a
            boolean r16 = kotlin.jvm.internal.j.a(r6, r13)
            if (r16 == 0) goto L_0x011a
            r16 = r7
            r15 = 1
            goto L_0x011c
        L_0x011a:
            r16 = 1
        L_0x011c:
            if (r16 == 0) goto L_0x0121
            r14.add(r6)
        L_0x0121:
            r6 = 1
            goto L_0x0104
        L_0x0123:
            java.util.LinkedHashSet r10 = ne.C1182C.b0(r14, r10)
        L_0x0127:
            r8.<init>(r11, r10)
        L_0x012a:
            if (r8 != 0) goto L_0x012e
            goto L_0x0196
        L_0x012e:
            java.lang.Object r6 = r8.d
            java.lang.Object r8 = r8.e
            java.util.Set r8 = (java.util.Set) r8
            Ze.C r5 = r0.h(r5)
            if (r5 != 0) goto L_0x0145
            Ze.C r5 = r0.h(r6)
            if (r5 == 0) goto L_0x0141
            goto L_0x0145
        L_0x0141:
            Ze.v r5 = r2.f3958a
            Ze.C r5 = r5.f3960a
        L_0x0145:
            Ze.C r10 = Ze.C.IGNORE
            if (r5 != r10) goto L_0x014a
            goto L_0x0196
        L_0x014a:
            java.lang.String r11 = "$this$extractNullability"
            kotlin.jvm.internal.j.e(r6, r11)
            hf.h r11 = r0.g(r6, r7)
            if (r11 == 0) goto L_0x0158
            r6 = r11
        L_0x0156:
            r11 = 1
            goto L_0x0184
        L_0x0158:
            java.lang.Object r11 = r0.i(r6)
            if (r11 != 0) goto L_0x0160
        L_0x015e:
            r6 = r9
            goto L_0x0156
        L_0x0160:
            Ze.C r6 = r0.h(r6)
            if (r6 == 0) goto L_0x0167
            goto L_0x016b
        L_0x0167:
            Ze.v r6 = r2.f3958a
            Ze.C r6 = r6.f3960a
        L_0x016b:
            if (r6 != r10) goto L_0x016e
            goto L_0x015e
        L_0x016e:
            hf.h r10 = r0.g(r11, r7)
            if (r10 == 0) goto L_0x0182
            Ze.C r11 = Ze.C.WARN
            if (r6 != r11) goto L_0x017b
            r6 = 1
        L_0x0179:
            r11 = 1
            goto L_0x017d
        L_0x017b:
            r6 = r7
            goto L_0x0179
        L_0x017d:
            hf.h r6 = hf.C1086h.a(r10, r9, r6, r11)
            goto L_0x0184
        L_0x0182:
            r11 = 1
            r6 = r9
        L_0x0184:
            if (r6 != 0) goto L_0x0187
            goto L_0x0196
        L_0x0187:
            Ze.m r10 = new Ze.m
            Ze.C r12 = Ze.C.WARN
            if (r5 != r12) goto L_0x018e
            r7 = r11
        L_0x018e:
            hf.h r5 = hf.C1086h.a(r6, r9, r7, r11)
            r10.<init>(r5, r8)
            r9 = r10
        L_0x0196:
            if (r9 == 0) goto L_0x001c
            r4.add(r9)
            goto L_0x001c
        L_0x019d:
            r11 = r6
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L_0x01a5
            goto L_0x020d
        L_0x01a5:
            java.util.EnumMap r0 = new java.util.EnumMap
            java.lang.Class<Ze.a> r2 = Ze.C0894a.class
            r0.<init>(r2)
            java.util.Iterator r3 = r4.iterator()
        L_0x01b0:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x01d5
            java.lang.Object r4 = r3.next()
            Ze.m r4 = (Ze.m) r4
            java.util.Collection r5 = r4.b
            java.util.Iterator r5 = r5.iterator()
        L_0x01c2:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x01b0
            java.lang.Object r6 = r5.next()
            Ze.a r6 = (Ze.C0894a) r6
            r0.containsKey(r6)
            r0.put(r6, r4)
            goto L_0x01c2
        L_0x01d5:
            if (r1 == 0) goto L_0x01df
            java.util.EnumMap r2 = r1.f3959a
            java.util.EnumMap r3 = new java.util.EnumMap
            r3.<init>(r2)
            goto L_0x01e4
        L_0x01df:
            java.util.EnumMap r3 = new java.util.EnumMap
            r3.<init>(r2)
        L_0x01e4:
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x01ec:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x020b
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r4 = r2.getKey()
            Ze.a r4 = (Ze.C0894a) r4
            java.lang.Object r2 = r2.getValue()
            Ze.m r2 = (Ze.m) r2
            if (r2 == 0) goto L_0x01ec
            r3.put(r4, r2)
            r7 = r11
            goto L_0x01ec
        L_0x020b:
            if (r7 != 0) goto L_0x020e
        L_0x020d:
            return r1
        L_0x020e:
            Ze.u r0 = new Ze.u
            r0.<init>(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Ze.C0895b.b(Ze.u, Re.h):Ze.u");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0062, code lost:
        if (r5.equals("ALWAYS") != false) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0077, code lost:
        if (r5.equals("NEVER") == false) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0080, code lost:
        if (r5.equals("MAYBE") == false) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0083, code lost:
        r5 = hf.C1085g.NULLABLE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final hf.C1086h g(java.lang.Object r5, boolean r6) {
        /*
            r4 = this;
            qf.c r0 = d(r5)
            r1 = 0
            if (r0 != 0) goto L_0x0009
            goto L_0x0096
        L_0x0009:
            Ze.t r4 = r4.f3938a
            r4.getClass()
            Ze.s r4 = Ze.s.d
            java.lang.Object r4 = r4.invoke(r0)
            Ze.C r4 = (Ze.C) r4
            r4.getClass()
            Ze.C r2 = Ze.C.IGNORE
            if (r4 != r2) goto L_0x001e
            return r1
        L_0x001e:
            java.util.Set r2 = Ze.y.k
            boolean r2 = r2.contains(r0)
            r3 = 0
            if (r2 == 0) goto L_0x002a
            hf.g r5 = hf.C1085g.NOT_NULL
            goto L_0x0088
        L_0x002a:
            java.util.Set r2 = Ze.y.l
            boolean r2 = r2.contains(r0)
            if (r2 == 0) goto L_0x0035
            hf.g r5 = hf.C1085g.NULLABLE
            goto L_0x0088
        L_0x0035:
            java.util.Set r2 = Ze.y.m
            boolean r2 = r2.contains(r0)
            if (r2 == 0) goto L_0x0040
            hf.g r5 = hf.C1085g.FORCE_FLEXIBILITY
            goto L_0x0088
        L_0x0040:
            qf.c r2 = Ze.y.g
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0096
            java.util.ArrayList r5 = a(r5, r3)
            java.lang.Object r5 = ne.C1194l.M0(r5)
            java.lang.String r5 = (java.lang.String) r5
            if (r5 == 0) goto L_0x0086
            int r0 = r5.hashCode()
            switch(r0) {
                case 73135176: goto L_0x007a;
                case 74175084: goto L_0x0071;
                case 433141802: goto L_0x0065;
                case 1933739535: goto L_0x005c;
                default: goto L_0x005b;
            }
        L_0x005b:
            goto L_0x0096
        L_0x005c:
            java.lang.String r0 = "ALWAYS"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0096
            goto L_0x0086
        L_0x0065:
            java.lang.String r0 = "UNKNOWN"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x006e
            goto L_0x0096
        L_0x006e:
            hf.g r5 = hf.C1085g.FORCE_FLEXIBILITY
            goto L_0x0088
        L_0x0071:
            java.lang.String r0 = "NEVER"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0083
            goto L_0x0096
        L_0x007a:
            java.lang.String r0 = "MAYBE"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0083
            goto L_0x0096
        L_0x0083:
            hf.g r5 = hf.C1085g.NULLABLE
            goto L_0x0088
        L_0x0086:
            hf.g r5 = hf.C1085g.NOT_NULL
        L_0x0088:
            hf.h r0 = new hf.h
            Ze.C r1 = Ze.C.WARN
            if (r4 != r1) goto L_0x008f
            goto L_0x0091
        L_0x008f:
            if (r6 == 0) goto L_0x0092
        L_0x0091:
            r3 = 1
        L_0x0092:
            r0.<init>(r5, r3)
            return r0
        L_0x0096:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: Ze.C0895b.g(java.lang.Object, boolean):hf.h");
    }

    public final C h(Object obj) {
        String str;
        t tVar = this.f3938a;
        C c5 = (C) tVar.f3958a.f3961c.get(d(obj));
        if (c5 != null) {
            return c5;
        }
        Object c6 = c(obj, y.f3976p);
        if (c6 == null || (str = (String) C1194l.M0(a(c6, false))) == null) {
            return null;
        }
        C c8 = tVar.f3958a.b;
        if (c8 != null) {
            return c8;
        }
        int hashCode = str.hashCode();
        if (hashCode != -2137067054) {
            if (hashCode != -1838656823) {
                if (hashCode == 2656902 && str.equals("WARN")) {
                    return C.WARN;
                }
                return null;
            } else if (!str.equals("STRICT")) {
                return null;
            } else {
                return C.STRICT;
            }
        } else if (!str.equals("IGNORE")) {
            return null;
        } else {
            return C.IGNORE;
        }
    }

    public final Object i(Object obj) {
        Object obj2;
        j.e(obj, "annotation");
        if (!this.f3938a.f3958a.d) {
            if (C1194l.G0(y.f3974j, d(obj)) || f(obj, y.d)) {
                return obj;
            }
            if (f(obj, y.e)) {
                C0816f d = C1353d.d((b) obj);
                j.b(d);
                ConcurrentHashMap concurrentHashMap = this.b;
                Object obj3 = concurrentHashMap.get(d);
                if (obj3 != null) {
                    return obj3;
                }
                Iterator it = e(obj).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    obj2 = i(it.next());
                    if (obj2 != null) {
                        break;
                    }
                }
                if (obj2 != null) {
                    Object putIfAbsent = concurrentHashMap.putIfAbsent(d, obj2);
                    if (putIfAbsent == null) {
                        return obj2;
                    }
                    return putIfAbsent;
                }
            }
        }
        return null;
    }
}
