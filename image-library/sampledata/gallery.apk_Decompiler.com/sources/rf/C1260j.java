package rf;

import B2.o;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: rf.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1260j {

    /* renamed from: c  reason: collision with root package name */
    public static final C1260j f5065c = new C1260j(0);

    /* renamed from: a  reason: collision with root package name */
    public final D f5066a = new D(16);
    public boolean b;

    public C1260j() {
    }

    public static int c(Q q, Object obj) {
        switch (C1259i.b[q.ordinal()]) {
            case 1:
                ((Double) obj).getClass();
                return 8;
            case 2:
                ((Float) obj).getClass();
                return 4;
            case 3:
                return o.g(((Long) obj).longValue());
            case 4:
                return o.g(((Long) obj).longValue());
            case 5:
                return o.c(((Integer) obj).intValue());
            case 6:
                ((Long) obj).getClass();
                return 8;
            case 7:
                ((Integer) obj).getClass();
                return 4;
            case 8:
                ((Boolean) obj).getClass();
                return 1;
            case 9:
                try {
                    byte[] bytes = ((String) obj).getBytes("UTF-8");
                    return o.f(bytes.length) + bytes.length;
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("UTF-8 not supported.", e);
                }
            case 10:
                if (obj instanceof C1255e) {
                    C1255e eVar = (C1255e) obj;
                    return eVar.size() + o.f(eVar.size());
                }
                byte[] bArr = (byte[]) obj;
                return o.f(bArr.length) + bArr.length;
            case 11:
                return o.f(((Integer) obj).intValue());
            case 12:
                ((Integer) obj).getClass();
                return 4;
            case 13:
                ((Long) obj).getClass();
                return 8;
            case 14:
                int intValue = ((Integer) obj).intValue();
                return o.f((intValue >> 31) ^ (intValue << 1));
            case 15:
                long longValue = ((Long) obj).longValue();
                return o.g((longValue >> 63) ^ (longValue << 1));
            case 16:
                return ((C1252b) obj).a();
            case 17:
                return o.e((C1252b) obj);
            case 18:
                if (obj instanceof r) {
                    return o.c(((r) obj).a());
                }
                return o.c(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int d(C1265o oVar, Object obj) {
        Q q = oVar.e;
        int i2 = oVar.d;
        if (oVar.f) {
            int i7 = 0;
            for (Object next : (List) obj) {
                int h5 = o.h(i2);
                if (q == Q.GROUP) {
                    h5 *= 2;
                }
                i7 += c(q, next) + h5;
            }
            return i7;
        }
        int h6 = o.h(i2);
        if (q == Q.GROUP) {
            h6 *= 2;
        }
        return c(q, obj) + h6;
    }

    public static boolean e(Map.Entry entry) {
        C1265o oVar = (C1265o) entry.getKey();
        if (oVar.e.a() != S.MESSAGE) {
            return true;
        }
        if (oVar.f) {
            for (C1252b isInitialized : (List) entry.getValue()) {
                if (!isInitialized.isInitialized()) {
                    return false;
                }
            }
            return true;
        }
        Object value = entry.getValue();
        if (!(value instanceof C1252b)) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        } else if (!((C1252b) value).isInitialized()) {
            return false;
        } else {
            return true;
        }
    }

    public static Object h(C1256f fVar, Q q) {
        boolean z = true;
        switch (C1259i.b[q.ordinal()]) {
            case 1:
                return Double.valueOf(Double.longBitsToDouble(fVar.j()));
            case 2:
                return Float.valueOf(Float.intBitsToFloat(fVar.i()));
            case 3:
                return Long.valueOf(fVar.l());
            case 4:
                return Long.valueOf(fVar.l());
            case 5:
                return Integer.valueOf(fVar.k());
            case 6:
                return Long.valueOf(fVar.j());
            case 7:
                return Integer.valueOf(fVar.i());
            case 8:
                if (fVar.l() == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 9:
                int k = fVar.k();
                int i2 = fVar.b;
                int i7 = fVar.d;
                if (k <= i2 - i7 && k > 0) {
                    String str = new String(fVar.f5058a, i7, k, "UTF-8");
                    fVar.d += k;
                    return str;
                } else if (k == 0) {
                    return "";
                } else {
                    return new String(fVar.h(k), "UTF-8");
                }
            case 10:
                return fVar.e();
            case 11:
                return Integer.valueOf(fVar.k());
            case 12:
                return Integer.valueOf(fVar.i());
            case 13:
                return Long.valueOf(fVar.j());
            case 14:
                int k10 = fVar.k();
                return Integer.valueOf((-(k10 & 1)) ^ (k10 >>> 1));
            case 15:
                long l = fVar.l();
                return Long.valueOf((-(l & 1)) ^ (l >>> 1));
            case 16:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case 17:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case 18:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001e, code lost:
        if ((r3 instanceof rf.r) == false) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0021, code lost:
        r0 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void j(rf.Q r2, java.lang.Object r3) {
        /*
            r3.getClass()
            int[] r0 = rf.C1259i.f5064a
            rf.S r2 = r2.a()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L_0x003c;
                case 2: goto L_0x0039;
                case 3: goto L_0x0036;
                case 4: goto L_0x0033;
                case 5: goto L_0x0030;
                case 6: goto L_0x002d;
                case 7: goto L_0x0024;
                case 8: goto L_0x0018;
                case 9: goto L_0x0015;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x003e
        L_0x0015:
            boolean r1 = r3 instanceof rf.C1252b
            goto L_0x003e
        L_0x0018:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0022
            boolean r2 = r3 instanceof rf.r
            if (r2 == 0) goto L_0x0021
            goto L_0x0022
        L_0x0021:
            r0 = r1
        L_0x0022:
            r1 = r0
            goto L_0x003e
        L_0x0024:
            boolean r2 = r3 instanceof rf.C1255e
            if (r2 != 0) goto L_0x0022
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L_0x0021
            goto L_0x0022
        L_0x002d:
            boolean r1 = r3 instanceof java.lang.String
            goto L_0x003e
        L_0x0030:
            boolean r1 = r3 instanceof java.lang.Boolean
            goto L_0x003e
        L_0x0033:
            boolean r1 = r3 instanceof java.lang.Double
            goto L_0x003e
        L_0x0036:
            boolean r1 = r3 instanceof java.lang.Float
            goto L_0x003e
        L_0x0039:
            boolean r1 = r3 instanceof java.lang.Long
            goto L_0x003e
        L_0x003c:
            boolean r1 = r3 instanceof java.lang.Integer
        L_0x003e:
            if (r1 == 0) goto L_0x0041
            return
        L_0x0041:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: rf.C1260j.j(rf.Q, java.lang.Object):void");
    }

    public static void k(o oVar, Q q, Object obj) {
        switch (C1259i.b[q.ordinal()]) {
            case 1:
                double doubleValue = ((Double) obj).doubleValue();
                oVar.getClass();
                oVar.u(Double.doubleToRawLongBits(doubleValue));
                return;
            case 2:
                float floatValue = ((Float) obj).floatValue();
                oVar.getClass();
                oVar.t(Float.floatToRawIntBits(floatValue));
                return;
            case 3:
                oVar.w(((Long) obj).longValue());
                return;
            case 4:
                oVar.w(((Long) obj).longValue());
                return;
            case 5:
                oVar.n(((Integer) obj).intValue());
                return;
            case 6:
                oVar.u(((Long) obj).longValue());
                return;
            case 7:
                oVar.t(((Integer) obj).intValue());
                return;
            case 8:
                oVar.q(((Boolean) obj).booleanValue() ? 1 : 0);
                return;
            case 9:
                oVar.getClass();
                byte[] bytes = ((String) obj).getBytes("UTF-8");
                oVar.v(bytes.length);
                oVar.s(bytes);
                return;
            case 10:
                if (obj instanceof C1255e) {
                    C1255e eVar = (C1255e) obj;
                    oVar.getClass();
                    oVar.v(eVar.size());
                    oVar.r(eVar);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                oVar.getClass();
                oVar.v(bArr.length);
                oVar.s(bArr);
                return;
            case 11:
                oVar.v(((Integer) obj).intValue());
                return;
            case 12:
                oVar.t(((Integer) obj).intValue());
                return;
            case 13:
                oVar.u(((Long) obj).longValue());
                return;
            case 14:
                int intValue = ((Integer) obj).intValue();
                oVar.v((intValue >> 31) ^ (intValue << 1));
                return;
            case 15:
                long longValue = ((Long) obj).longValue();
                oVar.w((longValue >> 63) ^ (longValue << 1));
                return;
            case 16:
                oVar.getClass();
                ((C1252b) obj).d(oVar);
                return;
            case 17:
                oVar.p((C1252b) obj);
                return;
            case 18:
                if (obj instanceof r) {
                    oVar.n(((r) obj).a());
                    return;
                } else {
                    oVar.n(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final void a(C1265o oVar, Object obj) {
        List list;
        if (oVar.f) {
            j(oVar.e, obj);
            D d = this.f5066a;
            Object obj2 = d.get(oVar);
            if (obj2 == null) {
                list = new ArrayList();
                d.put(oVar, list);
            } else {
                list = (List) obj2;
            }
            list.add(obj);
            return;
        }
        throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    }

    /* renamed from: b */
    public final C1260j clone() {
        D d;
        C1260j jVar = new C1260j();
        int i2 = 0;
        while (true) {
            d = this.f5066a;
            if (i2 >= d.e.size()) {
                break;
            }
            Map.Entry entry = (Map.Entry) d.e.get(i2);
            jVar.i((C1265o) entry.getKey(), entry.getValue());
            i2++;
        }
        for (Map.Entry entry2 : d.c()) {
            jVar.i((C1265o) entry2.getKey(), entry2.getValue());
        }
        return jVar;
    }

    public final void f() {
        Map map;
        if (!this.b) {
            D d = this.f5066a;
            if (!d.g) {
                for (int i2 = 0; i2 < d.e.size(); i2++) {
                    Map.Entry entry = (Map.Entry) d.e.get(i2);
                    if (((C1265o) entry.getKey()).f) {
                        entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                    }
                }
                for (Map.Entry entry2 : d.c()) {
                    if (((C1265o) entry2.getKey()).f) {
                        entry2.setValue(Collections.unmodifiableList((List) entry2.getValue()));
                    }
                }
            }
            if (!d.g) {
                if (d.f.isEmpty()) {
                    map = Collections.EMPTY_MAP;
                } else {
                    map = Collections.unmodifiableMap(d.f);
                }
                d.f = map;
                d.g = true;
            }
            this.b = true;
        }
    }

    public final void g(Map.Entry entry) {
        C1265o oVar = (C1265o) entry.getKey();
        Object value = entry.getValue();
        boolean z = oVar.f;
        D d = this.f5066a;
        if (z) {
            Object obj = d.get(oVar);
            if (obj == null) {
                obj = new ArrayList();
            }
            for (Object next : (List) value) {
                List list = (List) obj;
                if (next instanceof byte[]) {
                    byte[] bArr = (byte[]) next;
                    byte[] bArr2 = new byte[bArr.length];
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    next = bArr2;
                }
                list.add(next);
            }
            d.put(oVar, obj);
        } else if (oVar.e.a() == S.MESSAGE) {
            Object obj2 = d.get(oVar);
            if (obj2 == null) {
                if (value instanceof byte[]) {
                    byte[] bArr3 = (byte[]) value;
                    byte[] bArr4 = new byte[bArr3.length];
                    System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
                    value = bArr4;
                }
                d.put(oVar, value);
                return;
            }
            d.put(oVar, ((C1252b) obj2).c().c((C1267q) ((C1252b) value)).a());
        } else {
            if (value instanceof byte[]) {
                byte[] bArr5 = (byte[]) value;
                byte[] bArr6 = new byte[bArr5.length];
                System.arraycopy(bArr5, 0, bArr6, 0, bArr5.length);
                value = bArr6;
            }
            d.put(oVar, value);
        }
    }

    public final void i(C1265o oVar, Object obj) {
        boolean z = oVar.f;
        Q q = oVar.e;
        if (!z) {
            j(q, obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                j(q, it.next());
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        this.f5066a.put(oVar, obj);
    }

    public C1260j(int i2) {
        f();
    }
}
