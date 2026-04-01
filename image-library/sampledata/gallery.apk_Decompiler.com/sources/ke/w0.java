package Ke;

import D0.f;
import Df.C0736b;
import G0.c;
import Gf.m;
import Gf.o;
import Hf.C0763l;
import If.l;
import Ne.i;
import Pe.g;
import Pe.h;
import Pe.k;
import Pe.q;
import Pe.s;
import Qe.C;
import Qe.K;
import Qe.S;
import Se.a;
import Se.b;
import Se.d;
import Te.C0851l;
import Te.z;
import Ve.e;
import We.C0892d;
import Ze.C0895b;
import Ze.t;
import af.C0910h;
import cf.C0922a;
import ge.W0;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import jf.C1107g;
import jf.C1108h;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1192j;
import ne.C1195m;
import ne.C1202t;
import o1.C0246a;
import qf.C1240g;
import x2.C0338e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class w0 {

    /* renamed from: a  reason: collision with root package name */
    public static final ConcurrentHashMap f3513a = new ConcurrentHashMap();

    /* JADX WARNING: type inference failed for: r21v0, types: [jf.g, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r34v0, types: [hf.c, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r39v0, types: [jf.h, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v6, types: [Fd.a, Df.d, java.lang.Object] */
    public static final e a(Class cls) {
        m mVar;
        k kVar;
        b bVar;
        d dVar;
        Df.m mVar2 = Df.m.f;
        Class cls2 = cls;
        j.e(cls2, "<this>");
        ClassLoader d = C0892d.d(cls2);
        F0 f02 = new F0(d);
        ConcurrentHashMap concurrentHashMap = f3513a;
        WeakReference weakReference = (WeakReference) concurrentHashMap.get(f02);
        if (weakReference != null) {
            e eVar = (e) weakReference.get();
            if (eVar != null) {
                return eVar;
            }
            concurrentHashMap.remove(f02, weakReference);
        }
        B1.b bVar2 = new B1.b(8, d);
        ClassLoader classLoader = x.class.getClassLoader();
        j.d(classLoader, "getClassLoader(...)");
        B1.b bVar3 = new B1.b(8, classLoader);
        G0.e eVar2 = new G0.e((Object) d);
        String str = "runtime module for " + d;
        Ve.d dVar2 = Ve.d.b;
        Ve.d dVar3 = Ve.d.f3831c;
        j.e(str, "moduleName");
        m mVar3 = new m("DeserializationComponentsForJava.ModuleData");
        k kVar2 = new k(mVar3, h.FROM_DEPENDENCIES);
        z zVar = new z(C1240g.g("<" + str + '>'), mVar3, kVar2, 56);
        o oVar = mVar3.f3416a;
        oVar.lock();
        try {
            if (kVar2.f3547a == null) {
                kVar2.f3547a = zVar;
                oVar.unlock();
                kVar2.f = new Ne.m(zVar, 1);
                ? obj = new Object();
                B1.b bVar4 = new B1.b(10);
                f fVar = new f(mVar3, (C) zVar);
                C1108h hVar = C1108h.f4644c;
                C0910h hVar2 = C0910h.f3996c;
                C0910h hVar3 = C0910h.f3995a;
                C0338e eVar3 = new C0338e(mVar3);
                S s = S.f;
                Ne.o oVar2 = new Ne.o(zVar, fVar);
                t tVar = t.f3957c;
                C0895b bVar5 = new C0895b(tVar);
                cf.b bVar6 = cf.b.f4014a;
                ? obj2 = new Object();
                If.k.b.getClass();
                l lVar = If.j.b;
                B1.b bVar7 = bVar4;
                B1.b bVar8 = bVar2;
                m mVar4 = mVar3;
                z zVar2 = zVar;
                F0 f03 = f02;
                ConcurrentHashMap concurrentHashMap2 = concurrentHashMap;
                B1.b bVar9 = bVar3;
                m mVar5 = mVar4;
                B1.b bVar10 = bVar8;
                z zVar3 = zVar2;
                K dVar4 = new cf.d(new C0922a(mVar4, eVar2, bVar8, obj, hVar2, dVar2, hVar3, eVar3, dVar3, bVar7, hVar, s, Ye.b.f3918a, zVar2, oVar2, bVar5, obj2, Ze.l.f3946a, bVar6, lVar, tVar, new Object()));
                pf.f fVar2 = pf.f.g;
                j.e(fVar2, "jvmMetadataVersion");
                F0 f04 = f03;
                W0 w02 = new W0(bVar10, obj, false, 5);
                ? obj3 = new Object();
                obj3.d = bVar10;
                obj3.e = mVar5.b(new C0736b(24, obj3));
                obj3.f = zVar3;
                obj3.g = fVar;
                obj3.f3375h = new D0.e((C) zVar3, fVar);
                obj3.f3376i = pf.f.g;
                obj3.f3376i = fVar2;
                List e02 = C0246a.e0(C0763l.f3448a);
                i iVar = zVar3.f3811h;
                if (iVar instanceof k) {
                    kVar = (k) iVar;
                } else {
                    kVar = null;
                }
                B1.b bVar11 = bVar10;
                List list = e02;
                C1108h hVar4 = C1108h.b;
                if (kVar == null || (bVar = kVar.J()) == null) {
                    bVar = a.b;
                }
                if (kVar == null || (dVar = kVar.J()) == null) {
                    dVar = a.d;
                }
                B1.b bVar12 = bVar9;
                k kVar3 = kVar2;
                ConcurrentHashMap concurrentHashMap3 = concurrentHashMap2;
                f fVar3 = fVar;
                F0 f05 = f04;
                d dVar5 = dVar;
                Df.l lVar2 = new Df.l(mVar5, zVar3, w02, obj3, dVar4, dVar2, hVar4, C1202t.d, fVar3, bVar, dVar5, pf.i.f5029a, lVar, new C0338e(mVar5), list, mVar2);
                C1107g gVar = obj;
                gVar.f4643a = lVar2;
                bVar7.e = new c(19, (Object) dVar4);
                q J4 = kVar3.J();
                q J7 = kVar3.J();
                C0338e eVar4 = new C0338e(mVar5);
                j.e(J4, "additionalClassPartsProvider");
                j.e(J7, "platformDependentDeclarationFilter");
                s sVar = new s(mVar5, bVar12, zVar3);
                c cVar = new c(2, (Object) sVar);
                Ef.a aVar = Ef.a.m;
                s sVar2 = sVar;
                c cVar2 = cVar;
                D0.e eVar5 = new D0.e((C) zVar3, fVar3, (Cf.a) aVar);
                m mVar6 = mVar5;
                l lVar3 = lVar;
                s sVar3 = sVar2;
                sVar3.f3651c = new Df.l(mVar6, zVar3, cVar2, eVar5, sVar2, C1195m.q0(new Oe.a(mVar5, zVar3), new g(mVar5, zVar3)), fVar3, J4, J7, aVar.f3325a, lVar3, eVar4, 262144);
                zVar3.k = new G0.e((Object) C1192j.x0(new z[]{zVar3}));
                zVar3.l = new C0851l(C1195m.q0(dVar4, sVar3), "CompositeProvider@RuntimeModuleData for " + zVar3);
                e eVar6 = new e(lVar2, new A0.l(gVar, bVar11));
                while (true) {
                    F0 f06 = f05;
                    ConcurrentHashMap concurrentHashMap4 = concurrentHashMap3;
                    WeakReference weakReference2 = (WeakReference) concurrentHashMap4.putIfAbsent(f06, new WeakReference(eVar6));
                    if (weakReference2 == null) {
                        return eVar6;
                    }
                    e eVar7 = (e) weakReference2.get();
                    if (eVar7 != null) {
                        return eVar7;
                    }
                    concurrentHashMap4.remove(f06, weakReference2);
                    f05 = f06;
                    concurrentHashMap3 = concurrentHashMap4;
                }
            } else {
                mVar = mVar3;
                k kVar4 = kVar2;
                try {
                    throw new AssertionError("Built-ins module is already set: " + kVar4.f3547a + " (attempting to reset to " + zVar + ")");
                } catch (Throwable th) {
                    th = th;
                    try {
                        mVar.b.getClass();
                        throw th;
                    } catch (Throwable th2) {
                        oVar.unlock();
                        throw th2;
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            mVar = mVar3;
            mVar.b.getClass();
            throw th;
        }
    }
}
