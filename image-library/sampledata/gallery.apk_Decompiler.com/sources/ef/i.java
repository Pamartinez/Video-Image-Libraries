package ef;

import Af.p;
import Hf.B;
import Hf.C0768q;
import Hf.C0774x;
import Hf.I;
import Hf.P;
import Hf.c0;
import If.d;
import If.f;
import Qe.C0816f;
import Qe.C0819i;
import Tf.n;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.ArrayList;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import o1.C0246a;
import sf.C1280g;
import sf.C1283j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends C0768q {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(B b, B b5) {
        super(b, b5);
        j.e(b, "lowerBound");
        j.e(b5, "upperBound");
        d.f3459a.b(b, b5);
    }

    public static final ArrayList D0(C1283j jVar, C0774x xVar) {
        Iterable<P> e02 = xVar.e0();
        ArrayList arrayList = new ArrayList(C1196n.w0(e02, 10));
        for (P p6 : e02) {
            j.e(p6, "typeProjection");
            StringBuilder sb2 = new StringBuilder();
            C1194l.Q0(C0246a.e0(p6), sb2, ArcCommonLog.TAG_COMMA, (String) null, (String) null, new C1280g(jVar, 0), 60);
            String sb3 = sb2.toString();
            j.d(sb3, "toString(...)");
            arrayList.add(sb3);
        }
        return arrayList;
    }

    public static final String E0(String str, String str2) {
        if (!n.v0(str, '<')) {
            return str;
        }
        return n.P0(str, '<') + '<' + str2 + '>' + n.O0(str, '>');
    }

    public final p A() {
        C0816f fVar;
        C0819i g = s0().g();
        if (g instanceof C0816f) {
            fVar = (C0816f) g;
        } else {
            fVar = null;
        }
        if (fVar != null) {
            p a0 = fVar.a0(new g());
            j.d(a0, "getMemberScope(...)");
            return a0;
        }
        throw new IllegalStateException(("Incorrect classifier: " + s0().g()).toString());
    }

    public final c0 A0(I i2) {
        j.e(i2, "newAttributes");
        return new i(this.e.A0(i2), this.f.A0(i2));
    }

    public final B B0() {
        return this.e;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x009d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String C0(sf.C1283j r11, sf.C1283j r12) {
        /*
            r10 = this;
            Hf.B r0 = r10.e
            java.lang.String r1 = r11.Y(r0)
            Hf.B r2 = r10.f
            java.lang.String r3 = r11.Y(r2)
            sf.o r12 = r12.f5084a
            boolean r12 = r12.n()
            if (r12 == 0) goto L_0x0030
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "raw ("
            r10.<init>(r11)
            r10.append(r1)
            java.lang.String r11 = ".."
            r10.append(r11)
            r10.append(r3)
            r11 = 41
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            return r10
        L_0x0030:
            java.util.List r12 = r2.e0()
            boolean r12 = r12.isEmpty()
            if (r12 == 0) goto L_0x0043
            Ne.i r10 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.z(r10)
            java.lang.String r10 = r11.F(r1, r3, r10)
            return r10
        L_0x0043:
            java.util.ArrayList r4 = D0(r11, r0)
            java.util.ArrayList r12 = D0(r11, r2)
            ef.h r8 = ef.h.d
            r9 = 30
            java.lang.String r5 = ", "
            r6 = 0
            r7 = 0
            java.lang.String r0 = ne.C1194l.R0(r4, r5, r6, r7, r8, r9)
            java.util.ArrayList r12 = ne.C1194l.r1(r4, r12)
            boolean r2 = r12.isEmpty()
            if (r2 == 0) goto L_0x0062
            goto L_0x008f
        L_0x0062:
            java.util.Iterator r12 = r12.iterator()
        L_0x0066:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L_0x008f
            java.lang.Object r2 = r12.next()
            me.i r2 = (me.i) r2
            java.lang.Object r4 = r2.d
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r2 = r2.e
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r5 = "out "
            java.lang.String r5 = Tf.n.H0(r2, r5)
            boolean r4 = kotlin.jvm.internal.j.a(r4, r5)
            if (r4 != 0) goto L_0x0066
            java.lang.String r4 = "*"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0093
            goto L_0x0066
        L_0x008f:
            java.lang.String r3 = E0(r3, r0)
        L_0x0093:
            java.lang.String r12 = E0(r1, r0)
            boolean r0 = kotlin.jvm.internal.j.a(r12, r3)
            if (r0 == 0) goto L_0x009e
            return r12
        L_0x009e:
            Ne.i r10 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.z(r10)
            java.lang.String r10 = r11.F(r12, r3, r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: ef.i.C0(sf.j, sf.j):java.lang.String");
    }

    public final C0774x w0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        B b = this.e;
        j.e(b, "type");
        B b5 = this.f;
        j.e(b5, "type");
        return new C0768q(b, b5);
    }

    public final c0 y0(boolean z) {
        return new i(this.e.y0(z), this.f.y0(z));
    }

    public final c0 z0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        B b = this.e;
        j.e(b, "type");
        B b5 = this.f;
        j.e(b5, "type");
        return new C0768q(b, b5);
    }
}
