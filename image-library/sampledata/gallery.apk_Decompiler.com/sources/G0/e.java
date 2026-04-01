package G0;

import Gf.o;
import Hf.C0774x;
import J0.b;
import Jf.c;
import Ne.i;
import P2.C;
import P2.C0056f;
import P2.l;
import P2.v;
import Qe.A;
import Qe.C0813c;
import Qe.C0817g;
import Qe.C0822l;
import Qe.C0824n;
import Qe.C0826p;
import Qe.C0830u;
import Qe.C0831v;
import Qe.O;
import Qe.U;
import Re.d;
import S2.q;
import S2.t;
import S2.u;
import Te.B;
import Te.C0845f;
import Te.C0847h;
import Te.C0848i;
import Te.F;
import Te.H;
import Te.I;
import Te.J;
import Te.Q;
import Te.w;
import Te.y;
import Te.z;
import Z1.a;
import android.content.Context;
import android.provider.Settings;
import c2.h;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import ge.C1048o0;
import ge.F0;
import ge.O0;
import h2.g;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;
import jf.p;
import kf.C1116b;
import kf.C1117c;
import kf.C1119e;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1194l;
import qf.C1235b;
import qf.C1240g;
import qf.C1242i;
import sf.C1278e;
import sf.C1280g;
import sf.C1281h;
import sf.C1283j;
import sf.C1285l;
import sf.C1288o;
import sf.C1293t;
import tf.C1301e;
import vf.C1326f;
import x0.C0320B;
import x0.n;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class e implements o, C0830u, a, h, C1048o0, O0, g, jf.o, C0824n {
    public Object d;

    public /* synthetic */ e(Object obj) {
        this.d = obj;
    }

    public C0830u A(C1240g gVar) {
        j.e(gVar, "name");
        return this;
    }

    public /* bridge */ /* synthetic */ Object D(C0831v vVar, Object obj) {
        O(vVar, (StringBuilder) obj);
        return x.f4917a;
    }

    public Object E(Q q, Object obj) {
        ((C1283j) this.d).f0(q, true, (StringBuilder) obj, true);
        return x.f4917a;
    }

    public C0830u F(C0774x xVar) {
        j.e(xVar, "type");
        return this;
    }

    public Object G(I i2, Object obj) {
        P(i2, (StringBuilder) obj, "getter");
        return x.f4917a;
    }

    public C0320B K(Context context, String str, InputStream inputStream, String str2, String str3) {
        C0320B b;
        b bVar;
        C0320B f;
        c cVar = (c) this.d;
        if (str2 == null) {
            str2 = "application/json";
        }
        if (str2.contains("application/zip") || str2.contains("application/x-zip") || str2.contains("application/x-zip-compressed") || str.split("\\?")[0].endsWith(".lottie")) {
            b.a();
            b bVar2 = b.ZIP;
            if (str3 != null) {
                f = n.f(context, new ZipInputStream(new FileInputStream(cVar.C(str, inputStream, bVar2))), str);
            } else {
                f = n.f(context, new ZipInputStream(inputStream), (String) null);
            }
            b = f;
            bVar = bVar2;
        } else if (str2.contains("application/gzip") || str2.contains("application/x-gzip") || str.split("\\?")[0].endsWith(".tgs")) {
            b.a();
            bVar = b.GZIP;
            if (str3 != null) {
                b = n.c(str, new GZIPInputStream(new FileInputStream(cVar.C(str, inputStream, bVar))));
            } else {
                b = n.c((String) null, new GZIPInputStream(inputStream));
            }
        } else {
            b.a();
            bVar = b.JSON;
            if (str3 != null) {
                b = n.c(str, new FileInputStream(cVar.C(str, inputStream, bVar).getAbsolutePath()));
            } else {
                b = n.c((String) null, inputStream);
            }
        }
        if (!(str3 == null || b.f2042a == null)) {
            File file = new File(cVar.y(), c.k(str, bVar, true));
            File file2 = new File(file.getAbsolutePath().replace(".temp", ""));
            boolean renameTo = file.renameTo(file2);
            file2.toString();
            b.a();
            if (!renameTo) {
                b.b("Unable to rename cache file " + file.getAbsolutePath() + " to " + file2.getAbsolutePath() + ".");
            }
        }
        return b;
    }

    public int L(S2.a aVar) {
        P2.o oVar;
        C0056f fVar = (C0056f) this.d;
        if (aVar instanceof t) {
            C c5 = fVar.e;
            c5.f();
            oVar = (P2.o) ((TreeMap) c5.g).get((t) aVar);
            if (oVar == null) {
                throw new IllegalArgumentException("not found");
            }
        } else if (aVar instanceof u) {
            C c6 = fVar.f;
            c6.f();
            oVar = (P2.o) ((TreeMap) c6.g).get(((u) aVar).d);
            if (oVar == null) {
                throw new IllegalArgumentException("not found: " + aVar);
            }
        } else if (aVar instanceof q) {
            v vVar = fVar.f596i;
            vVar.f();
            oVar = (P2.o) vVar.f.get((q) aVar);
            if (oVar == null) {
                throw new IllegalArgumentException("not found");
            }
        } else if (aVar instanceof S2.h) {
            l lVar = fVar.f595h;
            lVar.f();
            oVar = (P2.o) lVar.f.get((S2.h) aVar);
            if (oVar == null) {
                throw new IllegalArgumentException("not found");
            }
        } else {
            fVar.getClass();
            oVar = null;
        }
        if (oVar == null) {
            return -1;
        }
        return oVar.e();
    }

    public boolean M() {
        if (Settings.System.getInt(((Context) ((Ed.b) this.d).e).getContentResolver(), "samsung_errorlog_agree", 0) == 1) {
            return true;
        }
        return false;
    }

    public void N(boolean z) {
        F0 f02 = (F0) this.d;
        f02.Z.A0(f02.E, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00b2, code lost:
        if (((java.lang.Boolean) r1.f5095O.f(r1, sf.C1288o.Y[39])).booleanValue() != false) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00f6, code lost:
        if (((java.lang.Boolean) r1.f5095O.f(r1, sf.C1288o.Y[39])).booleanValue() != false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x01ab, code lost:
        if (Ne.i.D(r1, Ne.p.d) == false) goto L_0x01ad;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void O(Qe.C0831v r11, java.lang.StringBuilder r12) {
        /*
            r10 = this;
            java.lang.Object r10 = r10.d
            sf.j r10 = (sf.C1283j) r10
            sf.o r0 = r10.f5084a
            sf.o r1 = r10.f5084a
            boolean r2 = r10.r()
            java.lang.String r3 = "getTypeParameters(...)"
            r4 = 1
            if (r2 != 0) goto L_0x015f
            sf.n r2 = r1.g
            He.t[] r5 = sf.C1288o.Y
            r6 = 5
            r6 = r5[r6]
            java.lang.Object r2 = r2.f(r1, r6)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L_0x0144
            java.util.List r2 = r11.i0()
            java.lang.String r6 = "getContextReceiverParameters(...)"
            kotlin.jvm.internal.j.d(r2, r6)
            r10.C(r12, r2)
            r2 = 0
            r10.y(r12, r11, r2)
            Qe.p r2 = r11.getVisibility()
            java.lang.String r6 = "getVisibility(...)"
            kotlin.jvm.internal.j.d(r2, r6)
            r10.h0(r2, r12)
            r10.M(r11, r12)
            sf.n r2 = r1.T
            r6 = 44
            r7 = r5[r6]
            java.lang.Object r2 = r2.f(r1, r7)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x0058
            r10.K(r11, r12)
        L_0x0058:
            r10.S(r11, r12)
            sf.n r2 = r1.T
            r5 = r5[r6]
            java.lang.Object r2 = r2.f(r1, r5)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            java.lang.String r5 = "suspend"
            if (r2 == 0) goto L_0x011e
            boolean r2 = r11.isOperator()
            r6 = 39
            r7 = 0
            java.lang.String r8 = "getOverriddenDescriptors(...)"
            if (r2 == 0) goto L_0x00b6
            java.util.Collection r2 = r11.h()
            kotlin.jvm.internal.j.d(r2, r8)
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            r9 = r2
            java.util.Collection r9 = (java.util.Collection) r9
            boolean r9 = r9.isEmpty()
            if (r9 == 0) goto L_0x008c
            goto L_0x00b4
        L_0x008c:
            java.util.Iterator r2 = r2.iterator()
        L_0x0090:
            boolean r9 = r2.hasNext()
            if (r9 == 0) goto L_0x00b4
            java.lang.Object r9 = r2.next()
            Qe.v r9 = (Qe.C0831v) r9
            boolean r9 = r9.isOperator()
            if (r9 == 0) goto L_0x0090
            sf.n r2 = r1.f5095O
            He.t[] r9 = sf.C1288o.Y
            r9 = r9[r6]
            java.lang.Object r2 = r2.f(r1, r9)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x00b6
        L_0x00b4:
            r2 = r4
            goto L_0x00b7
        L_0x00b6:
            r2 = r7
        L_0x00b7:
            boolean r9 = r11.isInfix()
            if (r9 == 0) goto L_0x00f9
            java.util.Collection r9 = r11.h()
            kotlin.jvm.internal.j.d(r9, r8)
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            r8 = r9
            java.util.Collection r8 = (java.util.Collection) r8
            boolean r8 = r8.isEmpty()
            if (r8 == 0) goto L_0x00d0
            goto L_0x00f8
        L_0x00d0:
            java.util.Iterator r8 = r9.iterator()
        L_0x00d4:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x00f8
            java.lang.Object r9 = r8.next()
            Qe.v r9 = (Qe.C0831v) r9
            boolean r9 = r9.isInfix()
            if (r9 == 0) goto L_0x00d4
            sf.n r8 = r1.f5095O
            He.t[] r9 = sf.C1288o.Y
            r6 = r9[r6]
            java.lang.Object r1 = r8.f(r1, r6)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x00f9
        L_0x00f8:
            r7 = r4
        L_0x00f9:
            boolean r1 = r11.w()
            java.lang.String r6 = "tailrec"
            r10.N(r12, r1, r6)
            boolean r1 = r11.isSuspend()
            r10.N(r12, r1, r5)
            boolean r1 = r11.isInline()
            java.lang.String r5 = "inline"
            r10.N(r12, r1, r5)
            java.lang.String r1 = "infix"
            r10.N(r12, r7, r1)
            java.lang.String r1 = "operator"
            r10.N(r12, r2, r1)
            goto L_0x0125
        L_0x011e:
            boolean r1 = r11.isSuspend()
            r10.N(r12, r1, r5)
        L_0x0125:
            r10.J(r11, r12)
            boolean r1 = r10.u()
            if (r1 == 0) goto L_0x0144
            boolean r1 = r11.l0()
            if (r1 == 0) goto L_0x0139
            java.lang.String r1 = "/*isHiddenToOvercomeSignatureClash*/ "
            r12.append(r1)
        L_0x0139:
            boolean r1 = r11.o0()
            if (r1 == 0) goto L_0x0144
            java.lang.String r1 = "/*isHiddenForResolutionEverywhereBesideSupercalls*/ "
            r12.append(r1)
        L_0x0144:
            java.lang.String r1 = "fun"
            java.lang.String r1 = r10.I(r1)
            r12.append(r1)
            java.lang.String r1 = " "
            r12.append(r1)
            java.util.List r1 = r11.getTypeParameters()
            kotlin.jvm.internal.j.d(r1, r3)
            r10.d0(r1, r12, r4)
            r10.V(r11, r12)
        L_0x015f:
            r10.P(r11, r12, r4)
            java.util.List r1 = r11.B()
            java.lang.String r2 = "getValueParameters(...)"
            kotlin.jvm.internal.j.d(r1, r2)
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r2 = r11.Z()
            r10.g0(r1, r2, r12)
            r10.W(r11, r12)
            Hf.x r1 = r11.getReturnType()
            sf.n r2 = r0.l
            He.t[] r4 = sf.C1288o.Y
            r5 = 10
            r5 = r4[r5]
            java.lang.Object r2 = r2.f(r0, r5)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L_0x01be
            sf.n r2 = r0.k
            r5 = 9
            r4 = r4[r5]
            java.lang.Object r0 = r2.f(r0, r4)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x01ad
            if (r1 == 0) goto L_0x01ad
            qf.g r0 = Ne.i.e
            qf.e r0 = Ne.p.d
            boolean r0 = Ne.i.D(r1, r0)
            if (r0 != 0) goto L_0x01be
        L_0x01ad:
            java.lang.String r0 = ": "
            r12.append(r0)
            if (r1 != 0) goto L_0x01b7
            java.lang.String r0 = "[NULL]"
            goto L_0x01bb
        L_0x01b7:
            java.lang.String r0 = r10.Y(r1)
        L_0x01bb:
            r12.append(r0)
        L_0x01be:
            java.util.List r11 = r11.getTypeParameters()
            kotlin.jvm.internal.j.d(r11, r3)
            r10.i0(r12, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: G0.e.O(Qe.v, java.lang.StringBuilder):void");
    }

    public void P(F f, StringBuilder sb2, String str) {
        C1283j jVar = (C1283j) this.d;
        C1288o oVar = jVar.f5084a;
        int i2 = C1281h.f5081a[((C1293t) oVar.f5089H.f(oVar, C1288o.Y[32])).ordinal()];
        if (i2 == 1) {
            jVar.K(f, sb2);
            sb2.append(str.concat(" for "));
            O E02 = f.E0();
            j.d(E02, "getCorrespondingProperty(...)");
            C1283j.n(jVar, E02, sb2);
        } else if (i2 == 2) {
            O(f, sb2);
        } else if (i2 != 3) {
            throw new RuntimeException();
        }
    }

    public C0830u b(C0822l lVar) {
        j.e(lVar, "owner");
        return this;
    }

    public C0831v build() {
        return (c) this.d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x012c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object c(Te.C0848i r11, java.lang.Object r12) {
        /*
            r10 = this;
            boolean r0 = r11.f3779H
            java.lang.StringBuilder r12 = (java.lang.StringBuilder) r12
            java.lang.Object r10 = r10.d
            sf.j r10 = (sf.C1283j) r10
            r10.getClass()
            r1 = 0
            r10.y(r12, r11, r1)
            sf.o r1 = r10.f5084a
            sf.n r2 = r1.f5104o
            He.t[] r3 = sf.C1288o.Y
            r4 = 13
            r4 = r3[r4]
            java.lang.Object r2 = r2.f(r1, r4)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            r4 = 0
            r5 = 1
            if (r2 != 0) goto L_0x0033
            Qe.f r2 = r11.W()
            Qe.A r2 = r2.k()
            Qe.A r6 = Qe.A.SEALED
            if (r2 == r6) goto L_0x0044
        L_0x0033:
            Qe.p r2 = r11.getVisibility()
            java.lang.String r6 = "getVisibility(...)"
            kotlin.jvm.internal.j.d(r2, r6)
            boolean r2 = r10.h0(r2, r12)
            if (r2 == 0) goto L_0x0044
            r2 = r5
            goto L_0x0045
        L_0x0044:
            r2 = r4
        L_0x0045:
            r10.J(r11, r12)
            sf.n r6 = r1.f5096P
            r7 = 40
            r7 = r3[r7]
            java.lang.Object r6 = r6.f(r1, r7)
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L_0x0061
            if (r0 == 0) goto L_0x0061
            if (r2 == 0) goto L_0x005f
            goto L_0x0061
        L_0x005f:
            r2 = r4
            goto L_0x0062
        L_0x0061:
            r2 = r5
        L_0x0062:
            if (r2 == 0) goto L_0x006d
            java.lang.String r6 = "constructor"
            java.lang.String r6 = r10.I(r6)
            r12.append(r6)
        L_0x006d:
            Qe.f r6 = r11.g()
            java.lang.String r7 = "getContainingDeclaration(...)"
            kotlin.jvm.internal.j.d(r6, r7)
            sf.n r7 = r1.f5085A
            r8 = 25
            r9 = r3[r8]
            java.lang.Object r7 = r7.f(r1, r9)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x0099
            if (r2 == 0) goto L_0x008f
            java.lang.String r2 = " "
            r12.append(r2)
        L_0x008f:
            r10.P(r6, r12, r5)
            java.util.List r2 = r11.getTypeParameters()
            r10.d0(r2, r12, r4)
        L_0x0099:
            java.util.List r2 = r11.B()
            java.lang.String r4 = "getValueParameters(...)"
            kotlin.jvm.internal.j.d(r2, r4)
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r5 = r11.Z()
            r10.g0(r2, r5, r12)
            sf.n r2 = r1.q
            r5 = 15
            r3 = r3[r5]
            java.lang.Object r2 = r2.f(r1, r3)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x011a
            if (r0 != 0) goto L_0x011a
            Te.i r0 = r6.y()
            if (r0 == 0) goto L_0x011a
            Te.t r0 = (Te.t) r0
            java.util.List r0 = r0.B()
            kotlin.jvm.internal.j.d(r0, r4)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x00d9:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x00f4
            java.lang.Object r3 = r0.next()
            r4 = r3
            Te.Q r4 = (Te.Q) r4
            boolean r5 = r4.F0()
            if (r5 != 0) goto L_0x00d9
            Hf.x r4 = r4.n
            if (r4 != 0) goto L_0x00d9
            r2.add(r3)
            goto L_0x00d9
        L_0x00f4:
            boolean r0 = r2.isEmpty()
            if (r0 != 0) goto L_0x011a
            java.lang.String r0 = " : "
            r12.append(r0)
            java.lang.String r0 = "this"
            java.lang.String r0 = r10.I(r0)
            r12.append(r0)
            sf.d r6 = sf.C1277d.q
            r7 = 24
            java.lang.String r3 = ", "
            java.lang.String r4 = "("
            java.lang.String r5 = ")"
            java.lang.String r0 = ne.C1194l.R0(r2, r3, r4, r5, r6, r7)
            r12.append(r0)
        L_0x011a:
            sf.n r0 = r1.f5085A
            He.t[] r2 = sf.C1288o.Y
            r2 = r2[r8]
            java.lang.Object r0 = r0.f(r1, r2)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0133
            java.util.List r11 = r11.getTypeParameters()
            r10.i0(r12, r11)
        L_0x0133:
            me.x r10 = me.x.f4917a
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: G0.e.c(Te.i, java.lang.Object):java.lang.Object");
    }

    public Object d(z zVar, Object obj) {
        ((C1283j) this.d).P(zVar, (StringBuilder) obj, true);
        return x.f4917a;
    }

    public Object e(H h5, Object obj) {
        j.e(h5, "descriptor");
        C1283j.n((C1283j) this.d, h5, (StringBuilder) obj);
        return x.f4917a;
    }

    public Object f(y yVar, Object obj) {
        boolean z;
        C0848i y;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        boolean z11;
        String str;
        StringBuilder sb2 = (StringBuilder) obj;
        C1283j jVar = (C1283j) this.d;
        C1288o oVar = jVar.f5084a;
        if (yVar.b() == C0817g.ENUM_ENTRY) {
            z = true;
        } else {
            z = false;
        }
        if (!jVar.r()) {
            List R = yVar.R();
            j.d(R, "getContextReceivers(...)");
            jVar.C(sb2, R);
            jVar.y(sb2, yVar, (d) null);
            if (!z) {
                C0826p visibility = yVar.getVisibility();
                j.d(visibility, "getVisibility(...)");
                jVar.h0(visibility, sb2);
            }
            if (!(yVar.b() == C0817g.INTERFACE && yVar.k() == A.ABSTRACT) && (!yVar.b().a() || yVar.k() != A.FINAL)) {
                A k = yVar.k();
                j.d(k, "getModality(...)");
                jVar.L(k, sb2, C1283j.v(yVar));
            }
            jVar.K(yVar, sb2);
            if (!jVar.q().contains(C1285l.INNER) || !yVar.s()) {
                z3 = false;
            } else {
                z3 = true;
            }
            jVar.N(sb2, z3, "inner");
            if (!jVar.q().contains(C1285l.DATA) || !yVar.t0()) {
                z7 = false;
            } else {
                z7 = true;
            }
            jVar.N(sb2, z7, "data");
            if (!jVar.q().contains(C1285l.INLINE) || !yVar.isInline()) {
                z9 = false;
            } else {
                z9 = true;
            }
            jVar.N(sb2, z9, "inline");
            if (!jVar.q().contains(C1285l.VALUE) || !yVar.l()) {
                z10 = false;
            } else {
                z10 = true;
            }
            jVar.N(sb2, z10, "value");
            if (!jVar.q().contains(C1285l.FUN) || !yVar.X()) {
                z11 = false;
            } else {
                z11 = true;
            }
            jVar.N(sb2, z11, "fun");
            if (yVar instanceof U) {
                str = "typealias";
            } else if (yVar.T()) {
                str = "companion object";
            } else {
                switch (C1278e.f5079a[yVar.b().ordinal()]) {
                    case 1:
                        str = "class";
                        break;
                    case 2:
                        str = "interface";
                        break;
                    case 3:
                        str = "enum class";
                        break;
                    case 4:
                        str = "object";
                        break;
                    case 5:
                        str = "annotation class";
                        break;
                    case 6:
                        str = "enum entry";
                        break;
                    default:
                        throw new RuntimeException();
                }
            }
            sb2.append(jVar.I(str));
        }
        if (!C1301e.l(yVar)) {
            if (!jVar.r()) {
                C1283j.X(sb2);
            }
            jVar.P(yVar, sb2, true);
        } else {
            if (((Boolean) oVar.f5088G.f(oVar, C1288o.Y[31])).booleanValue()) {
                if (jVar.r()) {
                    sb2.append("companion object");
                }
                C1283j.X(sb2);
                C0822l g = yVar.g();
                if (g != null) {
                    sb2.append("of ");
                    C1240g name = g.getName();
                    j.d(name, "getName(...)");
                    sb2.append(jVar.O(name, false));
                }
            }
            if (jVar.u() || !j.a(yVar.getName(), C1242i.b)) {
                if (!jVar.r()) {
                    C1283j.X(sb2);
                }
                C1240g name2 = yVar.getName();
                j.d(name2, "getName(...)");
                sb2.append(jVar.O(name2, true));
            }
        }
        if (!z) {
            List j2 = yVar.j();
            j.d(j2, "getDeclaredTypeParameters(...)");
            jVar.d0(j2, sb2, false);
            jVar.A(yVar, sb2);
            if (!yVar.b().a() && ((Boolean) oVar.f5102i.f(oVar, C1288o.Y[7])).booleanValue() && (y = yVar.y()) != null) {
                sb2.append(" ");
                jVar.y(sb2, y, (d) null);
                Te.t tVar = y;
                C0826p visibility2 = tVar.getVisibility();
                j.d(visibility2, "getVisibility(...)");
                jVar.h0(visibility2, sb2);
                sb2.append(jVar.I("constructor"));
                List B = tVar.B();
                j.d(B, "getValueParameters(...)");
                jVar.g0(B, y.Z(), sb2);
            }
            if (!((Boolean) oVar.f5106x.f(oVar, C1288o.Y[22])).booleanValue() && !i.E(yVar.i())) {
                Collection h5 = yVar.q().h();
                j.d(h5, "getSupertypes(...)");
                if (!h5.isEmpty() && (h5.size() != 1 || !i.x((C0774x) h5.iterator().next()))) {
                    C1283j.X(sb2);
                    sb2.append(": ");
                    C1194l.Q0(h5, sb2, ArcCommonLog.TAG_COMMA, (String) null, (String) null, new C1280g(jVar, 1), 60);
                }
            }
            jVar.i0(sb2, j2);
        }
        return x.f4917a;
    }

    public Object g(J j2, Object obj) {
        P(j2, (StringBuilder) obj, "setter");
        return x.f4917a;
    }

    public Object h(w wVar, Object obj) {
        StringBuilder sb2 = (StringBuilder) obj;
        C1283j jVar = (C1283j) this.d;
        jVar.getClass();
        jVar.T(wVar.f3806h, "package", sb2);
        if (jVar.f5084a.n()) {
            sb2.append(" in context of ");
            jVar.P(wVar.g, sb2, false);
        }
        return x.f4917a;
    }

    public C0830u j(Re.h hVar) {
        j.e(hVar, "additionalAnnotations");
        return this;
    }

    public Object l(Te.u uVar, Object obj) {
        ((StringBuilder) obj).append(uVar.getName());
        return x.f4917a;
    }

    public void lock() {
        ((ReentrantLock) this.d).lock();
    }

    public p o(C1240g gVar) {
        String b = gVar.b();
        if ("d1".equals(b)) {
            return new C1117c(this, 0);
        }
        if ("d2".equals(b)) {
            return new C1117c(this, 1);
        }
        return null;
    }

    public jf.o p(C1235b bVar, C1240g gVar) {
        return null;
    }

    public void s(C1240g gVar, Object obj) {
        C1119e eVar = (C1119e) this.d;
        String b = gVar.b();
        if ("k".equals(b)) {
            if (obj instanceof Integer) {
                C1116b.Companion.getClass();
                C1116b bVar = (C1116b) C1116b.entryById.get((Integer) obj);
                if (bVar == null) {
                    bVar = C1116b.UNKNOWN;
                }
                eVar.f4661j = bVar;
            }
        } else if ("mv".equals(b)) {
            if (obj instanceof int[]) {
                eVar.d = (int[]) obj;
            }
        } else if ("xs".equals(b)) {
            if (obj instanceof String) {
                String str = (String) obj;
                if (!str.isEmpty()) {
                    eVar.e = str;
                }
            }
        } else if (!"xi".equals(b)) {
            "pn".equals(b);
        } else if (obj instanceof Integer) {
            eVar.f = ((Integer) obj).intValue();
        }
    }

    public C0830u t(C0826p pVar) {
        j.e(pVar, "visibility");
        return this;
    }

    public C0830u u(C0813c cVar) {
        j.e(cVar, "kind");
        return this;
    }

    public void unlock() {
        ((ReentrantLock) this.d).unlock();
    }

    public Object v(C0847h hVar, Object obj) {
        ((C1283j) this.d).b0(hVar, (StringBuilder) obj, true);
        return x.f4917a;
    }

    public Object w(B b, Object obj) {
        StringBuilder sb2 = (StringBuilder) obj;
        C1283j jVar = (C1283j) this.d;
        jVar.getClass();
        jVar.T(b.f3743i, "package-fragment", sb2);
        if (jVar.f5084a.n()) {
            sb2.append(" in ");
            jVar.P(b.g(), sb2, false);
        }
        return x.f4917a;
    }

    public Object x(C0845f fVar, Object obj) {
        StringBuilder sb2 = (StringBuilder) obj;
        C1283j jVar = (C1283j) this.d;
        jVar.getClass();
        jVar.y(sb2, fVar, (d) null);
        C0826p pVar = fVar.f3775j;
        j.d(pVar, "getVisibility(...)");
        jVar.h0(pVar, sb2);
        jVar.K(fVar, sb2);
        sb2.append(jVar.I("typealias"));
        sb2.append(" ");
        jVar.P(fVar, sb2, true);
        jVar.d0(fVar.j(), sb2, false);
        jVar.A(fVar, sb2);
        sb2.append(" = ");
        sb2.append(jVar.Y(((Ff.w) fVar).G0()));
        return x.f4917a;
    }

    public void y() {
        ((AtomicLong) this.d).getAndAdd(1);
    }

    public e(int i2) {
        switch (i2) {
            case 11:
                this.d = new AtomicLong();
                return;
            default:
                this.d = new HashSet();
                return;
        }
    }

    public C0830u B() {
        return this;
    }

    public C0830u J() {
        return this;
    }

    public C0830u i() {
        return this;
    }

    public C0830u k() {
        return this;
    }

    public C0830u m() {
        return this;
    }

    public void n() {
    }

    public C0830u q() {
        return this;
    }

    public C0830u z() {
        return this;
    }

    public C0830u C(Te.u uVar) {
        return this;
    }

    public C0830u H(A a7) {
        return this;
    }

    public C0830u a(List list) {
        return this;
    }

    public void I(C1240g gVar, C1326f fVar) {
    }

    public void r(C1240g gVar, C1235b bVar, C1240g gVar2) {
    }
}
