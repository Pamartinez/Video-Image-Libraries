package Ff;

import Ad.f;
import Ae.b;
import D0.e;
import Df.H;
import Df.l;
import Df.w;
import Hf.B;
import Qe.C0822l;
import Qf.k;
import Re.g;
import Re.h;
import Re.i;
import Sf.a;
import Sf.n;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.jvm.internal.j;
import lf.C1148a;
import lf.C1154g;
import lf.C1171y;
import lf.G;
import lf.Q;
import lf.T;
import lf.f0;
import ne.C1196n;
import ne.C1202t;
import nf.C1204a;
import nf.C1208e;
import nf.C1209f;
import nf.C1211h;
import o1.C0246a;
import qf.C1240g;

public final class o implements b {
    public final /* synthetic */ int d;
    public final r e;

    public /* synthetic */ o(r rVar, int i2) {
        this.d = i2;
        this.e = rVar;
    }

    public final Object invoke(Object obj) {
        Collection<C1171y> collection;
        Collection<G> collection2;
        h iVar;
        Q q;
        Q q10;
        C1240g gVar = (C1240g) obj;
        switch (this.d) {
            case 0:
                j.e(gVar, "it");
                r rVar = this.e;
                LinkedHashMap linkedHashMap = rVar.f3393a;
                C1148a aVar = C1171y.y;
                j.d(aVar, "PARSER");
                s sVar = rVar.f3396i;
                byte[] bArr = (byte[]) linkedHashMap.get(gVar);
                if (bArr != null) {
                    q qVar = new q(aVar, new ByteArrayInputStream(bArr), sVar, 0);
                    collection = n.v0(new a(new Sf.j((Ae.a) qVar, (b) new f(1, (Object) qVar))));
                } else {
                    collection = C1202t.d;
                }
                ArrayList arrayList = new ArrayList(collection.size());
                for (C1171y yVar : collection) {
                    j.b(yVar);
                    v e7 = ((w) sVar.b.f3361i).e(yVar);
                    if (!sVar.r(e7)) {
                        e7 = null;
                    }
                    if (e7 != null) {
                        arrayList.add(e7);
                    }
                }
                sVar.j(arrayList, gVar);
                return k.d(arrayList);
            case 1:
                j.e(gVar, "it");
                r rVar2 = this.e;
                LinkedHashMap linkedHashMap2 = rVar2.b;
                C1148a aVar2 = G.y;
                j.d(aVar2, "PARSER");
                s sVar2 = rVar2.f3396i;
                byte[] bArr2 = (byte[]) linkedHashMap2.get(gVar);
                if (bArr2 != null) {
                    q qVar2 = new q(aVar2, new ByteArrayInputStream(bArr2), sVar2, 0);
                    collection2 = n.v0(new a(new Sf.j((Ae.a) qVar2, (b) new f(1, (Object) qVar2))));
                } else {
                    collection2 = C1202t.d;
                }
                ArrayList arrayList2 = new ArrayList(collection2.size());
                for (G g : collection2) {
                    j.b(g);
                    arrayList2.add(((w) sVar2.b.f3361i).f(g));
                }
                sVar2.k(arrayList2, gVar);
                return k.d(arrayList2);
            default:
                j.e(gVar, "it");
                r rVar3 = this.e;
                Df.n nVar = rVar3.f3396i.b;
                byte[] bArr3 = (byte[]) rVar3.f3394c.get(gVar);
                if (bArr3 != null) {
                    T t = (T) T.s.b(new ByteArrayInputStream(bArr3), ((l) nVar.f3358a).f3355p);
                    if (t != null) {
                        w wVar = (w) nVar.f3361i;
                        Df.n nVar2 = wVar.f3367a;
                        C1209f fVar = (C1209f) nVar2.b;
                        B1.b bVar = (B1.b) nVar2.d;
                        List list = t.n;
                        j.d(list, "getAnnotationList(...)");
                        Iterable<C1154g> iterable = list;
                        ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable, 10));
                        for (C1154g gVar2 : iterable) {
                            e eVar = wVar.b;
                            j.b(gVar2);
                            arrayList3.add(eVar.E(gVar2, fVar));
                        }
                        if (arrayList3.isEmpty()) {
                            iVar = g.f3692a;
                        } else {
                            iVar = new i(0, arrayList3);
                        }
                        h hVar = iVar;
                        w wVar2 = new w(((l) nVar2.f3358a).f3349a, (C0822l) nVar2.f3359c, hVar, c.C(fVar, t.f4781h), C0246a.T((f0) C1208e.d.c(t.g)), t, (C1209f) nVar2.b, bVar, (C1211h) nVar2.e, (m) nVar2.g);
                        List list2 = t.f4782i;
                        j.d(list2, "getTypeParameterList(...)");
                        H h5 = (H) nVar2.a(wVar2, list2, (C1209f) nVar2.b, (B1.b) nVar2.d, (C1211h) nVar2.e, (C1204a) nVar2.f).f3360h;
                        List b = h5.b();
                        int i2 = t.f;
                        if ((i2 & 4) == 4) {
                            q = t.f4783j;
                            j.d(q, "getUnderlyingType(...)");
                        } else if ((i2 & 8) == 8) {
                            q = bVar.g(t.k);
                        } else {
                            throw new IllegalStateException("No underlyingType in ProtoBuf.TypeAlias");
                        }
                        B d2 = h5.d(q, false);
                        int i7 = t.f;
                        if ((i7 & 16) == 16) {
                            q10 = t.l;
                            j.d(q10, "getExpandedType(...)");
                        } else if ((i7 & 32) == 32) {
                            q10 = bVar.g(t.m);
                        } else {
                            throw new IllegalStateException("No expandedType in ProtoBuf.TypeAlias");
                        }
                        wVar2.H0(b, d2, h5.d(q10, false));
                        return wVar2;
                    }
                }
                return null;
        }
    }
}
