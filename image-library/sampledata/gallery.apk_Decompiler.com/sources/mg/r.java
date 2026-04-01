package mg;

import B0.a;
import Ed.b;
import G0.e;
import Tf.n;
import a.C0068a;
import c0.C0086a;
import ig.c;
import ig.f;
import ig.k;
import ig.l;
import java.util.Arrays;
import lg.C1174b;
import lg.i;
import lg.j;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends C0246a implements j {

    /* renamed from: h  reason: collision with root package name */
    public final C1174b f4944h;

    /* renamed from: i  reason: collision with root package name */
    public final u f4945i;

    /* renamed from: j  reason: collision with root package name */
    public final b f4946j;
    public final a k;
    public int l = -1;
    public e m;
    public final i n;

    /* renamed from: o  reason: collision with root package name */
    public final f f4947o;

    public r(C1174b bVar, u uVar, b bVar2, f fVar, e eVar) {
        f fVar2;
        kotlin.jvm.internal.j.e(uVar, "mode");
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        this.f4944h = bVar;
        this.f4945i = uVar;
        this.f4946j = bVar2;
        this.k = bVar.b;
        this.m = eVar;
        i iVar = bVar.f4894a;
        this.n = iVar;
        if (iVar.f4902c) {
            fVar2 = null;
        } else {
            fVar2 = new f(fVar);
        }
        this.f4947o = fVar2;
    }

    public final String B() {
        return this.f4946j.k();
    }

    public final boolean D() {
        boolean z;
        f fVar = this.f4947o;
        if (fVar != null) {
            z = fVar.b;
        } else {
            z = false;
        }
        if (z || this.f4946j.C(true)) {
            return false;
        }
        return true;
    }

    public final C1174b F() {
        return this.f4944h;
    }

    public final byte G() {
        b bVar = this.f4946j;
        long j2 = bVar.j();
        byte b = (byte) ((int) j2);
        if (j2 == ((long) b)) {
            return b;
        }
        b.p(bVar, "Failed to parse byte for input '" + j2 + '\'', 0, (String) null, 6);
        throw null;
    }

    public final jg.a a(f fVar) {
        u uVar;
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        C0068a b = fVar.b();
        boolean z = b instanceof c;
        C1174b bVar = this.f4944h;
        if (z) {
            uVar = u.POLY_OBJ;
        } else if (kotlin.jvm.internal.j.a(b, l.e)) {
            uVar = u.LIST;
        } else if (kotlin.jvm.internal.j.a(b, l.f)) {
            f e = h.e(fVar.h(0), bVar.b);
            C0068a b5 = e.b();
            if ((b5 instanceof ig.e) || kotlin.jvm.internal.j.a(b5, k.d)) {
                uVar = u.MAP;
            } else {
                throw h.b(e);
            }
        } else {
            uVar = u.OBJ;
        }
        u uVar2 = uVar;
        b bVar2 = this.f4946j;
        j jVar = (j) bVar2.f3372c;
        int i2 = jVar.f4930c + 1;
        jVar.f4930c = i2;
        Object[] objArr = jVar.f4929a;
        if (i2 == objArr.length) {
            int i7 = i2 * 2;
            Object[] copyOf = Arrays.copyOf(objArr, i7);
            kotlin.jvm.internal.j.d(copyOf, "copyOf(...)");
            jVar.f4929a = copyOf;
            int[] copyOf2 = Arrays.copyOf(jVar.b, i7);
            kotlin.jvm.internal.j.d(copyOf2, "copyOf(...)");
            jVar.b = copyOf2;
        }
        jVar.f4929a[i2] = fVar;
        bVar2.i(uVar2.begin);
        if (bVar2.w() != 4) {
            int i8 = q.f4943a[uVar2.ordinal()];
            if (i8 == 1 || i8 == 2 || i8 == 3) {
                return new r(bVar, uVar2, bVar2, fVar, this.m);
            }
            if (this.f4945i == uVar2 && bVar.f4894a.f4902c) {
                return this;
            }
            return new r(bVar, uVar2, bVar2, fVar, this.m);
        }
        b.p(bVar2, "Unexpected leading comma", 0, (String) null, 6);
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(ig.f r5) {
        /*
            r4 = this;
            java.lang.String r0 = "descriptor"
            kotlin.jvm.internal.j.e(r5, r0)
            lg.b r0 = r4.f4944h
            lg.i r0 = r0.f4894a
            boolean r0 = r0.b
            r1 = -1
            if (r0 == 0) goto L_0x001a
            int r0 = r5.e()
            if (r0 != 0) goto L_0x001a
        L_0x0014:
            int r0 = r4.d(r5)
            if (r0 != r1) goto L_0x0014
        L_0x001a:
            Ed.b r5 = r4.f4946j
            boolean r0 = r5.B()
            if (r0 != 0) goto L_0x0043
            mg.u r4 = r4.f4945i
            char r4 = r4.end
            r5.i(r4)
            java.lang.Object r4 = r5.f3372c
            mg.j r4 = (mg.j) r4
            int r5 = r4.f4930c
            int[] r0 = r4.b
            r2 = r0[r5]
            r3 = -2
            if (r2 != r3) goto L_0x003b
            r0[r5] = r1
            int r5 = r5 + r1
            r4.f4930c = r5
        L_0x003b:
            int r5 = r4.f4930c
            if (r5 == r1) goto L_0x0042
            int r5 = r5 + r1
            r4.f4930c = r5
        L_0x0042:
            return
        L_0x0043:
            java.lang.String r4 = ""
            mg.h.k(r5, r4)
            r4 = 0
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: mg.r.b(ig.f):void");
    }

    public final int c(f fVar) {
        kotlin.jvm.internal.j.e(fVar, "enumDescriptor");
        b bVar = this.f4946j;
        return h.j(fVar, this.f4944h, bVar.k(), " at path ".concat(((j) bVar.f3372c).a()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00be, code lost:
        if (r13 == null) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c0, code lost:
        r0 = r13.f4926a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c4, code lost:
        if (r10 >= 64) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c6, code lost:
        r0.f4719c |= 1 << r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00cd, code lost:
        r13 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d0, code lost:
        r1 = (r10 >>> 6) - 1;
        r0 = r0.d;
        r0[r1] = r0[r1] | (1 << (r10 & 63));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int d(ig.f r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            Ed.b r2 = r0.f4946j
            java.lang.Object r3 = r2.f3372c
            mg.j r3 = (mg.j) r3
            java.lang.Object r4 = r2.f
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r5 = "descriptor"
            kotlin.jvm.internal.j.e(r1, r5)
            int[] r5 = mg.q.f4943a
            mg.u r6 = r0.f4945i
            int r7 = r6.ordinal()
            r5 = r5[r7]
            r7 = 2
            java.lang.String r8 = "object"
            r9 = 4
            r10 = 58
            r11 = 0
            r12 = 1
            r13 = -1
            r14 = 0
            if (r5 == r7) goto L_0x0234
            r7 = 6
            if (r5 == r9) goto L_0x0053
            boolean r1 = r2.B()
            boolean r4 = r2.d()
            if (r4 == 0) goto L_0x0049
            int r4 = r0.l
            if (r4 == r13) goto L_0x0043
            if (r1 == 0) goto L_0x003d
            goto L_0x0043
        L_0x003d:
            java.lang.String r0 = "Expected end of the array or comma"
            Ed.b.p(r2, r0, r11, r14, r7)
            throw r14
        L_0x0043:
            int r13 = r4 + 1
            r0.l = r13
            goto L_0x027d
        L_0x0049:
            if (r1 != 0) goto L_0x004d
            goto L_0x027d
        L_0x004d:
            java.lang.String r0 = "array"
            mg.h.k(r2, r0)
            throw r14
        L_0x0053:
            boolean r5 = r2.B()
        L_0x0057:
            boolean r9 = r2.d()
            r16 = 1
            mg.f r13 = r0.f4947o
            if (r9 == 0) goto L_0x01c3
            java.lang.String r5 = r2.f()
            r2.i(r10)
            lg.b r9 = r0.f4944h
            int r10 = mg.h.i(r1, r9, r5)
            r7 = -3
            lg.i r14 = r0.n
            if (r10 == r7) goto L_0x00e0
            boolean r15 = r14.e
            if (r15 == 0) goto L_0x00bc
            ig.f r15 = r1.h(r10)
            boolean r19 = r15.c()
            if (r19 != 0) goto L_0x008a
            boolean r19 = r2.C(r12)
            if (r19 == 0) goto L_0x008a
            r19 = r12
            goto L_0x00b6
        L_0x008a:
            r19 = r12
            a.a r12 = r15.b()
            ig.k r7 = ig.k.d
            boolean r7 = kotlin.jvm.internal.j.a(r12, r7)
            if (r7 == 0) goto L_0x00be
            boolean r7 = r15.c()
            if (r7 == 0) goto L_0x00a5
            boolean r7 = r2.C(r11)
            if (r7 == 0) goto L_0x00a5
            goto L_0x00be
        L_0x00a5:
            java.lang.String r7 = r2.x()
            if (r7 != 0) goto L_0x00ac
            goto L_0x00be
        L_0x00ac:
            int r7 = mg.h.i(r15, r9, r7)
            r9 = -3
            if (r7 != r9) goto L_0x00be
            r2.k()
        L_0x00b6:
            boolean r7 = r2.B()
            r9 = r11
            goto L_0x00e5
        L_0x00bc:
            r19 = r12
        L_0x00be:
            if (r13 == 0) goto L_0x00cd
            kg.s r0 = r13.f4926a
            r1 = 64
            if (r10 >= r1) goto L_0x00d0
            long r1 = r0.f4719c
            long r4 = r16 << r10
            long r1 = r1 | r4
            r0.f4719c = r1
        L_0x00cd:
            r13 = r10
            goto L_0x027d
        L_0x00d0:
            int r1 = r10 >>> 6
            int r1 = r1 + -1
            r2 = r10 & 63
            long[] r0 = r0.d
            r4 = r0[r1]
            long r7 = r16 << r2
            long r4 = r4 | r7
            r0[r1] = r4
            goto L_0x00cd
        L_0x00e0:
            r19 = r12
            r7 = r11
            r9 = r19
        L_0x00e5:
            if (r9 == 0) goto L_0x01c1
            boolean r7 = r14.b
            if (r7 != 0) goto L_0x011c
            G0.e r7 = r0.m
            if (r7 == 0) goto L_0x00fd
            java.lang.Object r9 = r7.d
            java.lang.String r9 = (java.lang.String) r9
            boolean r9 = kotlin.jvm.internal.j.a(r9, r5)
            if (r9 == 0) goto L_0x00fd
            r9 = 0
            r7.d = r9
            goto L_0x011c
        L_0x00fd:
            int r0 = r2.b
            java.lang.CharSequence r0 = r4.subSequence(r11, r0)
            java.lang.String r0 = r0.toString()
            r1 = 6
            int r0 = Tf.n.E0(r11, r1, r0, r5)
            java.lang.String r1 = "Encountered an unknown key '"
            r3 = 39
            java.lang.String r1 = c0.C0086a.h(r3, r1, r5)
            java.lang.String r3 = "Use 'ignoreUnknownKeys = true' in 'Json {}' builder to ignore unknown keys."
            r2.o(r0, r1, r3)
            r18 = 0
            throw r18
        L_0x011c:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            byte r7 = r2.w()
            r9 = 8
            r10 = 6
            if (r7 == r9) goto L_0x0131
            if (r7 == r10) goto L_0x0131
            r2.l()
            goto L_0x01ab
        L_0x0131:
            byte r7 = r2.w()
            r12 = r19
            if (r7 != r12) goto L_0x013f
            r2.f()
        L_0x013c:
            r19 = 1
            goto L_0x0131
        L_0x013f:
            if (r7 != r9) goto L_0x0142
            goto L_0x0144
        L_0x0142:
            if (r7 != r10) goto L_0x014c
        L_0x0144:
            java.lang.Byte r7 = java.lang.Byte.valueOf(r7)
            r5.add(r7)
            goto L_0x01a2
        L_0x014c:
            r10 = 9
            if (r7 != r10) goto L_0x0175
            java.lang.Object r7 = ne.C1194l.T0(r5)
            java.lang.Number r7 = (java.lang.Number) r7
            byte r7 = r7.byteValue()
            if (r7 != r9) goto L_0x0160
            ne.C1200r.C0(r5)
            goto L_0x01a2
        L_0x0160:
            int r0 = r2.b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "found ] instead of } at path: "
            r1.<init>(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            mg.d r0 = mg.h.d(r0, r1, r4)
            throw r0
        L_0x0175:
            r10 = 7
            if (r7 != r10) goto L_0x019e
            java.lang.Object r7 = ne.C1194l.T0(r5)
            java.lang.Number r7 = (java.lang.Number) r7
            byte r7 = r7.byteValue()
            r10 = 6
            if (r7 != r10) goto L_0x0189
            ne.C1200r.C0(r5)
            goto L_0x01a2
        L_0x0189:
            int r0 = r2.b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "found } instead of ] at path: "
            r1.<init>(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            mg.d r0 = mg.h.d(r0, r1, r4)
            throw r0
        L_0x019e:
            r10 = 10
            if (r7 == r10) goto L_0x01b9
        L_0x01a2:
            r2.g()
            int r7 = r5.size()
            if (r7 != 0) goto L_0x01b7
        L_0x01ab:
            boolean r5 = r2.B()
        L_0x01af:
            r7 = 6
            r10 = 58
            r12 = 1
            r13 = -1
            r14 = 0
            goto L_0x0057
        L_0x01b7:
            r10 = 6
            goto L_0x013c
        L_0x01b9:
            java.lang.String r0 = "Unexpected end of input due to malformed JSON during ignoring unknown keys"
            r9 = 0
            r10 = 6
            Ed.b.p(r2, r0, r11, r9, r10)
            throw r9
        L_0x01c1:
            r5 = r7
            goto L_0x01af
        L_0x01c3:
            if (r5 != 0) goto L_0x022e
            if (r13 == 0) goto L_0x022c
            kg.s r0 = r13.f4926a
            mg.e r1 = r0.b
            ig.f r2 = r0.f4718a
            int r4 = r2.e()
        L_0x01d1:
            long r7 = r0.f4719c
            r9 = -1
            int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r5 == 0) goto L_0x01f8
            long r7 = ~r7
            int r5 = java.lang.Long.numberOfTrailingZeros(r7)
            long r7 = r0.f4719c
            long r9 = r16 << r5
            long r7 = r7 | r9
            r0.f4719c = r7
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
            java.lang.Object r7 = r1.invoke(r2, r7)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x01d1
            r13 = r5
            goto L_0x027d
        L_0x01f8:
            r5 = 64
            if (r4 <= r5) goto L_0x022c
            long[] r0 = r0.d
            int r4 = r0.length
        L_0x01ff:
            if (r11 >= r4) goto L_0x022c
            int r5 = r11 + 1
            int r7 = r5 * 64
            r12 = r0[r11]
        L_0x0207:
            int r8 = (r12 > r9 ? 1 : (r12 == r9 ? 0 : -1))
            if (r8 == 0) goto L_0x0228
            long r14 = ~r12
            int r8 = java.lang.Long.numberOfTrailingZeros(r14)
            long r14 = r16 << r8
            long r12 = r12 | r14
            int r8 = r8 + r7
            java.lang.Integer r14 = java.lang.Integer.valueOf(r8)
            java.lang.Object r14 = r1.invoke(r2, r14)
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x0207
            r0[r11] = r12
            r13 = r8
            goto L_0x027d
        L_0x0228:
            r0[r11] = r12
            r11 = r5
            goto L_0x01ff
        L_0x022c:
            r13 = -1
            goto L_0x027d
        L_0x022e:
            mg.h.k(r2, r8)
            r18 = 0
            throw r18
        L_0x0234:
            int r1 = r0.l
            int r4 = r1 % 2
            if (r4 == 0) goto L_0x023c
            r12 = 1
            goto L_0x023d
        L_0x023c:
            r12 = r11
        L_0x023d:
            if (r12 == 0) goto L_0x0247
            r4 = -1
            if (r1 == r4) goto L_0x024d
            boolean r11 = r2.B()
            goto L_0x024d
        L_0x0247:
            r1 = 58
            r4 = -1
            r2.i(r1)
        L_0x024d:
            boolean r1 = r2.d()
            if (r1 == 0) goto L_0x027a
            if (r12 == 0) goto L_0x0271
            int r1 = r0.l
            if (r1 != r4) goto L_0x0265
            int r1 = r2.b
            if (r11 != 0) goto L_0x025e
            goto L_0x0271
        L_0x025e:
            java.lang.String r0 = "Unexpected leading comma"
            r4 = 0
            Ed.b.p(r2, r0, r1, r4, r9)
            throw r4
        L_0x0265:
            r4 = 0
            int r1 = r2.b
            if (r11 == 0) goto L_0x026b
            goto L_0x0271
        L_0x026b:
            java.lang.String r0 = "Expected comma after the key-value pair"
            Ed.b.p(r2, r0, r1, r4, r9)
            throw r4
        L_0x0271:
            int r1 = r0.l
            r19 = 1
            int r13 = r1 + 1
            r0.l = r13
            goto L_0x027d
        L_0x027a:
            if (r11 != 0) goto L_0x0288
            r13 = r4
        L_0x027d:
            mg.u r0 = mg.u.MAP
            if (r6 == r0) goto L_0x0287
            int[] r0 = r3.b
            int r1 = r3.f4930c
            r0[r1] = r13
        L_0x0287:
            return r13
        L_0x0288:
            mg.h.k(r2, r8)
            r18 = 0
            throw r18
        */
        throw new UnsupportedOperationException("Method not decompiled: mg.r.d(ig.f):int");
    }

    public final lg.l e() {
        return new Q2.a(this.f4944h.f4894a, this.f4946j).b();
    }

    public final int f() {
        b bVar = this.f4946j;
        long j2 = bVar.j();
        int i2 = (int) j2;
        if (j2 == ((long) i2)) {
            return i2;
        }
        b.p(bVar, "Failed to parse int for input '" + j2 + '\'', 0, (String) null, 6);
        throw null;
    }

    /* JADX WARNING: type inference failed for: r2v9, types: [G0.e, java.lang.Object] */
    public final Object g(gg.a aVar) {
        b bVar = this.f4946j;
        C1174b bVar2 = this.f4944h;
        kotlin.jvm.internal.j.e(aVar, "deserializer");
        try {
            if (!(aVar instanceof gg.e)) {
                return aVar.deserialize(this);
            }
            String g = h.g(aVar.getDescriptor(), bVar2);
            String v = bVar.v(g);
            if (v == null) {
                return h.h(this, aVar);
            }
            gg.a x9 = og.k.x((gg.e) aVar, this, v);
            ? obj = new Object();
            obj.d = g;
            this.m = obj;
            return x9.deserialize(this);
        } catch (gg.f e) {
            String message = e.getMessage();
            kotlin.jvm.internal.j.b(message);
            String I02 = n.I0(n.P0(message, 10), ".");
            String message2 = e.getMessage();
            kotlin.jvm.internal.j.b(message2);
            b.p(bVar, I02, 0, n.M0(10, message2, ""), 2);
            throw null;
        } catch (gg.b e7) {
            String message3 = e7.getMessage();
            kotlin.jvm.internal.j.b(message3);
            if (n.u0(message3, "at path")) {
                throw e7;
            }
            throw new gg.b(e7.d, e7.getMessage() + " at path: " + ((j) bVar.f3372c).a(), e7);
        }
    }

    public final long j() {
        return this.f4946j.j();
    }

    public final Object n(f fVar, int i2, gg.a aVar, Object obj) {
        boolean z;
        j jVar = (j) this.f4946j.f3372c;
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        kotlin.jvm.internal.j.e(aVar, "deserializer");
        if (this.f4945i == u.MAP && (i2 & 1) == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            int[] iArr = jVar.b;
            int i7 = jVar.f4930c;
            if (iArr[i7] == -2) {
                jVar.f4929a[i7] = i.f4928a;
            }
        }
        Object n3 = super.n(fVar, i2, aVar, obj);
        if (z) {
            int[] iArr2 = jVar.b;
            int i8 = jVar.f4930c;
            if (iArr2[i8] != -2) {
                int i10 = i8 + 1;
                jVar.f4930c = i10;
                Object[] objArr = jVar.f4929a;
                if (i10 == objArr.length) {
                    int i11 = i10 * 2;
                    Object[] copyOf = Arrays.copyOf(objArr, i11);
                    kotlin.jvm.internal.j.d(copyOf, "copyOf(...)");
                    jVar.f4929a = copyOf;
                    int[] copyOf2 = Arrays.copyOf(jVar.b, i11);
                    kotlin.jvm.internal.j.d(copyOf2, "copyOf(...)");
                    jVar.b = copyOf2;
                }
            }
            Object[] objArr2 = jVar.f4929a;
            int i12 = jVar.f4930c;
            objArr2[i12] = n3;
            jVar.b[i12] = -2;
        }
        return n3;
    }

    public final short o() {
        b bVar = this.f4946j;
        long j2 = bVar.j();
        short s = (short) ((int) j2);
        if (j2 == ((long) s)) {
            return s;
        }
        b.p(bVar, "Failed to parse short for input '" + j2 + '\'', 0, (String) null, 6);
        throw null;
    }

    public final float p() {
        b bVar = this.f4946j;
        String l8 = bVar.l();
        try {
            float parseFloat = Float.parseFloat(l8);
            if (!Float.isInfinite(parseFloat) && !Float.isNaN(parseFloat)) {
                return parseFloat;
            }
            h.n(bVar, Float.valueOf(parseFloat));
            throw null;
        } catch (IllegalArgumentException unused) {
            b.p(bVar, C0086a.h('\'', "Failed to parse type 'float' for input '", l8), 0, (String) null, 6);
            throw null;
        }
    }

    public final double s() {
        b bVar = this.f4946j;
        String l8 = bVar.l();
        try {
            double parseDouble = Double.parseDouble(l8);
            if (!Double.isInfinite(parseDouble) && !Double.isNaN(parseDouble)) {
                return parseDouble;
            }
            h.n(bVar, Double.valueOf(parseDouble));
            throw null;
        } catch (IllegalArgumentException unused) {
            b.p(bVar, C0086a.h('\'', "Failed to parse type 'double' for input '", l8), 0, (String) null, 6);
            throw null;
        }
    }

    public final jg.c u(f fVar) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        if (s.a(fVar)) {
            return new c(this.f4946j, this.f4944h);
        }
        return this;
    }

    public final boolean v() {
        boolean z;
        boolean z3;
        b bVar = this.f4946j;
        int z7 = bVar.z();
        String str = (String) bVar.f;
        if (z7 != str.length()) {
            if (str.charAt(z7) == '\"') {
                z7++;
                z = true;
            } else {
                z = false;
            }
            int y = bVar.y(z7);
            if (y >= str.length() || y == -1) {
                b.p(bVar, "EOF", 0, (String) null, 6);
                throw null;
            }
            int i2 = y + 1;
            char charAt = str.charAt(y) | ' ';
            if (charAt == 'f') {
                bVar.e(i2, "alse");
                z3 = false;
            } else if (charAt == 't') {
                bVar.e(i2, "rue");
                z3 = true;
            } else {
                b.p(bVar, "Expected valid boolean literal prefix, but had '" + bVar.l() + '\'', 0, (String) null, 6);
                throw null;
            }
            if (!z) {
                return z3;
            }
            if (bVar.b == str.length()) {
                b.p(bVar, "EOF", 0, (String) null, 6);
                throw null;
            } else if (str.charAt(bVar.b) == '\"') {
                bVar.b++;
                return z3;
            } else {
                b.p(bVar, "Expected closing quotation mark", 0, (String) null, 6);
                throw null;
            }
        } else {
            b.p(bVar, "EOF", 0, (String) null, 6);
            throw null;
        }
    }

    public final char w() {
        b bVar = this.f4946j;
        String l8 = bVar.l();
        if (l8.length() == 1) {
            return l8.charAt(0);
        }
        b.p(bVar, C0086a.h('\'', "Expected single char, but got '", l8), 0, (String) null, 6);
        throw null;
    }

    public final a y() {
        return this.k;
    }
}
