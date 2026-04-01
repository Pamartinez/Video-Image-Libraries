package Q2;

import Ed.b;
import Hf.c0;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.y;
import lg.C;
import lg.C1176d;
import lg.i;
import lg.l;
import lg.s;
import lg.v;
import me.C1177a;
import me.x;
import mg.h;
import mg.o;
import qe.C1227c;
import re.C1245a;
import t1.C0276a;
import w1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public int f634a;
    public final Object b;

    public a(C0276a aVar, int i2) {
        r.b(aVar);
        this.b = aVar;
        this.f634a = i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object a(Q2.a r12, me.b r13, se.C1269a r14) {
        /*
            java.lang.Object r0 = r12.b
            Ed.b r0 = (Ed.b) r0
            boolean r1 = r14 instanceof mg.p
            if (r1 == 0) goto L_0x0017
            r1 = r14
            mg.p r1 = (mg.p) r1
            int r2 = r1.f4942j
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0017
            int r2 = r2 - r3
            r1.f4942j = r2
            goto L_0x001c
        L_0x0017:
            mg.p r1 = new mg.p
            r1.<init>(r12, r14)
        L_0x001c:
            java.lang.Object r14 = r1.f4940h
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.f4942j
            r4 = 0
            r5 = 0
            r6 = 6
            r7 = 7
            r8 = 4
            r9 = 1
            if (r3 == 0) goto L_0x0044
            if (r3 != r9) goto L_0x003c
            java.lang.String r12 = r1.g
            java.util.LinkedHashMap r13 = r1.f
            Q2.a r0 = r1.e
            me.b r3 = r1.d
            L2.a.A(r14)
            r11 = r1
            r1 = r13
            r13 = r3
            r3 = r11
            goto L_0x0082
        L_0x003c:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0044:
            L2.a.A(r14)
            byte r14 = r0.h(r6)
            byte r3 = r0.w()
            if (r3 == r8) goto L_0x00bc
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
        L_0x0056:
            java.lang.Object r3 = r12.b
            Ed.b r3 = (Ed.b) r3
            boolean r10 = r3.d()
            if (r10 == 0) goto L_0x00a4
            java.lang.String r14 = r3.k()
            r10 = 5
            r3.h(r10)
            r1.d = r13
            r1.e = r12
            r1.f = r0
            r1.g = r14
            r1.f4942j = r9
            r13.getClass()
            r13.e = r1
            re.a r3 = re.C1245a.COROUTINE_SUSPENDED
            if (r3 != r2) goto L_0x007c
            return r2
        L_0x007c:
            r11 = r0
            r0 = r12
            r12 = r14
            r14 = r3
            r3 = r1
            r1 = r11
        L_0x0082:
            lg.l r14 = (lg.l) r14
            r1.put(r12, r14)
            java.lang.Object r12 = r0.b
            Ed.b r12 = (Ed.b) r12
            byte r14 = r12.g()
            if (r14 == r8) goto L_0x00a0
            if (r14 != r7) goto L_0x0096
            r12 = r0
            r0 = r1
            goto L_0x00a4
        L_0x0096:
            java.lang.Object r12 = r0.b
            Ed.b r12 = (Ed.b) r12
            java.lang.String r13 = "Expected end of the object or comma"
            Ed.b.p(r12, r13, r4, r5, r6)
            throw r5
        L_0x00a0:
            r12 = r0
            r0 = r1
            r1 = r3
            goto L_0x0056
        L_0x00a4:
            java.lang.Object r12 = r12.b
            Ed.b r12 = (Ed.b) r12
            if (r14 != r6) goto L_0x00ae
            r12.h(r7)
            goto L_0x00b0
        L_0x00ae:
            if (r14 == r8) goto L_0x00b6
        L_0x00b0:
            lg.y r12 = new lg.y
            r12.<init>(r0)
            return r12
        L_0x00b6:
            java.lang.String r13 = "object"
            mg.h.k(r12, r13)
            throw r5
        L_0x00bc:
            java.lang.String r12 = "Unexpected leading comma"
            Ed.b.p(r0, r12, r4, r5, r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: Q2.a.a(Q2.a, me.b, se.a):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r1v12, types: [java.lang.Object, qe.c, me.b] */
    public l b() {
        l lVar;
        Object obj;
        b bVar = (b) this.b;
        byte w = bVar.w();
        if (w == 1) {
            return d(true);
        }
        if (w == 0) {
            return d(false);
        }
        if (w == 6) {
            int i2 = this.f634a + 1;
            this.f634a = i2;
            if (i2 == 200) {
                o oVar = new o(this, (C1227c) null);
                C1245a aVar = C1177a.f4913a;
                ? obj2 = new Object();
                obj2.d = oVar;
                obj2.e = obj2;
                C1245a aVar2 = C1177a.f4913a;
                obj2.f = aVar2;
                while (true) {
                    obj = obj2.f;
                    C1227c cVar = obj2.e;
                    if (cVar == null) {
                        break;
                    } else if (j.a(aVar2, obj)) {
                        try {
                            o oVar2 = obj2.d;
                            y.c(3, oVar2);
                            o oVar3 = new o(oVar2.f, cVar);
                            oVar3.e = obj2;
                            Object invokeSuspend = oVar3.invokeSuspend(x.f4917a);
                            if (invokeSuspend != C1245a.COROUTINE_SUSPENDED) {
                                cVar.resumeWith(invokeSuspend);
                            }
                        } catch (Throwable th) {
                            cVar.resumeWith(L2.a.l(th));
                        }
                    } else {
                        obj2.f = aVar2;
                        cVar.resumeWith(obj);
                    }
                }
                L2.a.A(obj);
                lVar = (l) obj;
            } else {
                byte h5 = bVar.h((byte) 6);
                if (bVar.w() != 4) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    while (true) {
                        if (!bVar.d()) {
                            break;
                        }
                        String k = bVar.k();
                        bVar.h((byte) 5);
                        linkedHashMap.put(k, b());
                        h5 = bVar.g();
                        if (h5 != 4) {
                            if (h5 != 7) {
                                b.p(bVar, "Expected end of the object or comma", 0, (String) null, 6);
                                throw null;
                            }
                        }
                    }
                    if (h5 == 6) {
                        bVar.h((byte) 7);
                    } else if (h5 == 4) {
                        h.k(bVar, "object");
                        throw null;
                    }
                    lVar = new lg.y(linkedHashMap);
                } else {
                    b.p(bVar, "Unexpected leading comma", 0, (String) null, 6);
                    throw null;
                }
            }
            this.f634a--;
            return lVar;
        } else if (w == 8) {
            return c();
        } else {
            b.p(bVar, "Cannot read Json element because of unexpected ".concat(h.o(w)), 0, (String) null, 6);
            throw null;
        }
    }

    public C1176d c() {
        boolean z;
        b bVar = (b) this.b;
        byte g = bVar.g();
        if (bVar.w() != 4) {
            ArrayList arrayList = new ArrayList();
            while (bVar.d()) {
                arrayList.add(b());
                g = bVar.g();
                if (g != 4) {
                    if (g == 9) {
                        z = true;
                    } else {
                        z = false;
                    }
                    int i2 = bVar.b;
                    if (!z) {
                        b.p(bVar, "Expected end of the array or comma", i2, (String) null, 4);
                        throw null;
                    }
                }
            }
            if (g == 8) {
                bVar.h((byte) 9);
            } else if (g == 4) {
                h.k(bVar, "array");
                throw null;
            }
            return new C1176d(arrayList);
        }
        b.p(bVar, "Unexpected leading comma", 0, (String) null, 6);
        throw null;
    }

    public C d(boolean z) {
        String str;
        b bVar = (b) this.b;
        if (!z) {
            str = bVar.l();
        } else {
            str = bVar.k();
        }
        if (z || !j.a(str, "null")) {
            return new s(str, z);
        }
        return v.INSTANCE;
    }

    public a(i iVar, b bVar) {
        this.b = bVar;
    }

    public a(c0 c0Var, int i2) {
        this.b = c0Var;
        this.f634a = i2;
    }

    public a(byte[] bArr) {
        int length = bArr.length;
        if (length < 0) {
            throw new IllegalArgumentException("end < start");
        } else if (length <= bArr.length) {
            this.b = bArr;
            this.f634a = length;
        } else {
            throw new IllegalArgumentException("end > bytes.length");
        }
    }

    public a(int i2, String str) {
        this.f634a = i2;
        this.b = str;
    }

    public a(int i2) {
        this.f634a = 0;
        this.b = new int[i2];
    }
}
