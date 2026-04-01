package Ff;

import B2.o;
import D1.f;
import Df.l;
import Gf.e;
import Gf.h;
import Gf.i;
import Gf.j;
import Gf.m;
import He.t;
import Ye.a;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import lf.C1171y;
import lf.G;
import lf.T;
import me.x;
import ne.C1196n;
import ne.C1202t;
import ne.z;
import nf.C1209f;
import qf.C1240g;
import rf.C1252b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r {

    /* renamed from: j  reason: collision with root package name */
    public static final /* synthetic */ t[] f3392j;

    /* renamed from: a  reason: collision with root package name */
    public final LinkedHashMap f3393a;
    public final LinkedHashMap b;

    /* renamed from: c  reason: collision with root package name */
    public final LinkedHashMap f3394c;
    public final e d;
    public final e e;
    public final j f;
    public final i g;

    /* renamed from: h  reason: collision with root package name */
    public final i f3395h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ s f3396i;

    static {
        w wVar = v.f4727a;
        Class<r> cls = r.class;
        f3392j = new t[]{wVar.f(new p(wVar.b(cls), "functionNames", "getFunctionNames()Ljava/util/Set;")), wVar.f(new p(wVar.b(cls), "variableNames", "getVariableNames()Ljava/util/Set;"))};
    }

    /* JADX WARNING: type inference failed for: r5v30, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r5v32, types: [Gf.h, Gf.i] */
    public r(s sVar, List list, List list2, List list3) {
        kotlin.jvm.internal.j.e(list, "functionList");
        kotlin.jvm.internal.j.e(list2, "propertyList");
        kotlin.jvm.internal.j.e(list3, "typeAliasList");
        this.f3396i = sVar;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object next : list) {
            C1240g C5 = c.C((C1209f) sVar.b.b, ((C1171y) ((C1252b) next)).f4888i);
            Object obj = linkedHashMap.get(C5);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(C5, obj);
            }
            ((List) obj).add(next);
        }
        this.f3393a = c(linkedHashMap);
        s sVar2 = this.f3396i;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object next2 : list2) {
            C1240g C6 = c.C((C1209f) sVar2.b.b, ((G) ((C1252b) next2)).f4749i);
            Object obj2 = linkedHashMap2.get(C6);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap2.put(C6, obj2);
            }
            ((List) obj2).add(next2);
        }
        this.b = c(linkedHashMap2);
        ((l) this.f3396i.b.f3358a).f3350c.getClass();
        s sVar3 = this.f3396i;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        for (Object next3 : list3) {
            C1240g C8 = c.C((C1209f) sVar3.b.b, ((T) ((C1252b) next3)).f4781h);
            Object obj3 = linkedHashMap3.get(C8);
            if (obj3 == null) {
                obj3 = new ArrayList();
                linkedHashMap3.put(C8, obj3);
            }
            ((List) obj3).add(next3);
        }
        this.f3394c = c(linkedHashMap3);
        this.d = ((l) this.f3396i.b.f3358a).f3349a.b(new o(this, 0));
        this.e = ((l) this.f3396i.b.f3358a).f3349a.b(new o(this, 1));
        this.f = ((l) this.f3396i.b.f3358a).f3349a.c(new o(this, 2));
        s sVar4 = this.f3396i;
        m mVar = ((l) sVar4.b.f3358a).f3349a;
        p pVar = new p(this, sVar4, 0);
        mVar.getClass();
        this.g = new h(mVar, pVar);
        s sVar5 = this.f3396i;
        m mVar2 = ((l) sVar5.b.f3358a).f3349a;
        p pVar2 = new p(this, sVar5, 1);
        mVar2.getClass();
        this.f3395h = new h(mVar2, pVar2);
    }

    public static LinkedHashMap c(LinkedHashMap linkedHashMap) {
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(z.Z(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Object key = entry.getKey();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Iterable<C1252b> iterable = (Iterable) entry.getValue();
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (C1252b bVar : iterable) {
                int a7 = bVar.a();
                int f5 = o.f(a7) + a7;
                if (f5 > 4096) {
                    f5 = 4096;
                }
                o j2 = o.j(byteArrayOutputStream, f5);
                j2.v(a7);
                bVar.d(j2);
                j2.i();
                arrayList.add(x.f4917a);
            }
            linkedHashMap2.put(key, byteArrayOutputStream.toByteArray());
        }
        return linkedHashMap2;
    }

    public final Collection a(C1240g gVar, a aVar) {
        kotlin.jvm.internal.j.e(gVar, "name");
        kotlin.jvm.internal.j.e(aVar, "location");
        if (!((Set) f.y(this.g, f3392j[0])).contains(gVar)) {
            return C1202t.d;
        }
        return (Collection) this.d.invoke(gVar);
    }

    public final Collection b(C1240g gVar, a aVar) {
        kotlin.jvm.internal.j.e(gVar, "name");
        kotlin.jvm.internal.j.e(aVar, "location");
        if (!((Set) f.y(this.f3395h, f3392j[1])).contains(gVar)) {
            return C1202t.d;
        }
        return (Collection) this.e.invoke(gVar);
    }
}
