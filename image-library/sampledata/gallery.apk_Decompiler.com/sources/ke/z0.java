package Ke;

import Dd.C0730a;
import He.C0748d;
import He.C0750f;
import He.C0751g;
import He.q;
import He.s;
import Hf.C0774x;
import Qe.C0831v;
import Te.K;
import cg.i;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.io.ByteArrayInputStream;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.b;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.w;
import lf.C1148a;
import lf.C1171y;
import lf.X;
import ne.C1194l;
import pf.a;
import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.u;
import sf.C1283j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class z0 extends w {
    public static F i(b bVar) {
        C0750f owner = bVar.getOwner();
        if (owner instanceof F) {
            return (F) owner;
        }
        return C0783d.e;
    }

    public final C0751g a(g gVar) {
        F i2 = i(gVar);
        String name = gVar.getName();
        String signature = gVar.getSignature();
        Object boundReceiver = gVar.getBoundReceiver();
        j.e(name, "name");
        j.e(signature, "signature");
        return new H(i2, name, signature, (C0831v) null, boundReceiver);
    }

    public final C0748d b(Class cls) {
        return C0781c.a(cls);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0016, code lost:
        r0 = ((Ae.b) r1.e).invoke(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final He.C0750f c(java.lang.Class r2, java.lang.String r3) {
        /*
            r1 = this;
            D0.e r1 = Ke.C0781c.f3500a
            java.lang.String r1 = "jClass"
            kotlin.jvm.internal.j.e(r2, r1)
            D0.e r1 = Ke.C0781c.b
            r1.getClass()
            java.lang.Object r3 = r1.f
            java.util.concurrent.ConcurrentHashMap r3 = (java.util.concurrent.ConcurrentHashMap) r3
            java.lang.Object r0 = r3.get(r2)
            if (r0 != 0) goto L_0x0026
            java.lang.Object r1 = r1.e
            Ae.b r1 = (Ae.b) r1
            java.lang.Object r0 = r1.invoke(r2)
            java.lang.Object r1 = r3.putIfAbsent(r2, r0)
            if (r1 != 0) goto L_0x0025
            goto L_0x0026
        L_0x0025:
            r0 = r1
        L_0x0026:
            He.f r0 = (He.C0750f) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.z0.c(java.lang.Class, java.lang.String):He.f");
    }

    public final He.j d(l lVar) {
        return new L(i(lVar), lVar.getName(), lVar.getSignature(), lVar.getBoundReceiver());
    }

    public final q e(i iVar) {
        return new C0778a0(i(iVar), iVar.getName(), iVar.getSignature(), iVar.getBoundReceiver());
    }

    public final s f(o oVar) {
        return new C0784d0(i(oVar), oVar.getName(), oVar.getSignature(), oVar.getBoundReceiver());
    }

    public final String g(f fVar) {
        H b;
        Metadata metadata = (Metadata) fVar.getClass().getAnnotation(Metadata.class);
        H h5 = null;
        if (metadata != null) {
            String[] d12 = metadata.d1();
            if (d12.length == 0) {
                d12 = null;
            }
            if (d12 != null) {
                String[] d2 = metadata.d2();
                C1258h hVar = pf.i.f5029a;
                j.e(d2, "strings");
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a.a(d12));
                C1258h hVar2 = pf.i.f5029a;
                pf.g g = pf.i.g(byteArrayInputStream, d2);
                C1258h hVar3 = pf.i.f5029a;
                C1148a aVar = C1171y.y;
                aVar.getClass();
                C1256f fVar2 = new C1256f(byteArrayInputStream);
                C1252b bVar = (C1252b) aVar.a(fVar2, hVar3);
                boolean z = false;
                try {
                    fVar2.a(0);
                    if (bVar.isInitialized()) {
                        C1171y yVar = (C1171y) bVar;
                        int[] mv = metadata.mv();
                        if ((metadata.xi() & 8) != 0) {
                            z = true;
                        }
                        pf.f fVar3 = new pf.f(z, mv);
                        Class<?> cls = fVar.getClass();
                        X x9 = yVar.s;
                        j.d(x9, "getTypeTable(...)");
                        h5 = new H(C0783d.e, (K) E0.f(cls, yVar, g, new B1.b(x9), fVar3, Je.a.d));
                    } else {
                        u uVar = new u(new C0730a().getMessage());
                        uVar.d = bVar;
                        throw uVar;
                    }
                } catch (u e) {
                    u uVar2 = e;
                    uVar2.d = bVar;
                    throw uVar2;
                }
            }
        }
        if (h5 == null || (b = E0.b(h5)) == null) {
            return super.g(fVar);
        }
        C1283j jVar = B0.f3485a;
        C0831v t = b.j();
        StringBuilder sb2 = new StringBuilder();
        B0.a(t, sb2);
        List B = t.B();
        j.d(B, "getValueParameters(...)");
        C1194l.Q0(B, sb2, ArcCommonLog.TAG_COMMA, "(", ")", C0779b.l, 48);
        sb2.append(" -> ");
        C0774x returnType = t.getReturnType();
        j.b(returnType);
        sb2.append(B0.d(returnType));
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public final String h(k kVar) {
        return g(kVar);
    }
}
