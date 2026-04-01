package com.samsung.android.vexfwk.extensions;

import Ae.b;
import Ae.c;
import B1.a;
import Ge.e;
import L1.d;
import Vf.v0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jd.C1099b;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;
import ne.C1196n;
import qe.C1227c;
import re.C1245a;

@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010!\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u001a#\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a3\u0010\b\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\t\u001aJ\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\n2\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bH@¢\u0006\u0004\b\u0011\u0010\u0012\u001aJ\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00132\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bH@¢\u0006\u0004\b\u0011\u0010\u0014\u001aJ\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bH@¢\u0006\u0004\b\u0011\u0010\u0015\u001ab\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0002\"\u0004\b\u0000\u0010\u0016\"\u0004\b\u0001\u0010\u0017*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00182.\u0010\u000f\u001a*\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bH@¢\u0006\u0004\b\u0011\u0010\u001a\u001aV\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001c0\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u001b*\b\u0012\u0004\u0012\u00028\u00000\n2\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bH@¢\u0006\u0004\b\u001d\u0010\u0012\u001an\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u001c0\u0002\"\u0004\b\u0000\u0010\u0016\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u001b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00182.\u0010\u000f\u001a*\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bH@¢\u0006\u0004\b\u001d\u0010\u001a\u001a5\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u001f\u001a\u00020\u001e2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b!\u0010\"¨\u0006#"}, d2 = {"T", "", "", "drain", "(Ljava/util/List;)Ljava/util/List;", "Lkotlin/Function1;", "", "predicate", "removeFirstOrNull", "(Ljava/util/List;LAe/b;)Ljava/lang/Object;", "", "Lkotlin/Function2;", "Lqe/c;", "Lme/x;", "", "block", "LVf/e0;", "launchEach", "(Ljava/lang/Iterable;LAe/c;Lqe/c;)Ljava/lang/Object;", "", "([Ljava/lang/Object;LAe/c;Lqe/c;)Ljava/lang/Object;", "(Ljava/util/List;LAe/c;Lqe/c;)Ljava/lang/Object;", "K", "V", "", "", "(Ljava/util/Map;LAe/c;Lqe/c;)Ljava/lang/Object;", "R", "LVf/G;", "asyncEach", "", "times", "transform", "repeatMap", "(ILAe/b;)Ljava/util/List;", "VexFrameworkSDK_forInternalRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CollectionsKt {
    public static final <T, R> Object asyncEach(Iterable<? extends T> iterable, c cVar, C1227c cVar2) {
        C1099b bVar = new C1099b(iterable, cVar, (C1227c) null, 0);
        v0 v0Var = new v0(cVar2.getContext(), cVar2, 0);
        Object w = d.w(v0Var, v0Var, bVar);
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        return w;
    }

    public static final <T> List<T> drain(List<T> list) {
        j.e(list, "<this>");
        List<T> k12 = C1194l.k1(list);
        list.clear();
        return k12;
    }

    public static final <T> Object launchEach(Iterable<? extends T> iterable, c cVar, C1227c cVar2) {
        C1099b bVar = new C1099b(iterable, cVar, (C1227c) null, 1);
        v0 v0Var = new v0(cVar2.getContext(), cVar2, 0);
        Object w = d.w(v0Var, v0Var, bVar);
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        return w;
    }

    public static final <T> T removeFirstOrNull(List<T> list, b bVar) {
        j.e(list, "<this>");
        j.e(bVar, "predicate");
        Iterator<T> it = list.iterator();
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            } else if (((Boolean) bVar.invoke(it.next())).booleanValue()) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1) {
            return list.remove(i2);
        }
        return null;
    }

    public static final <T> List<T> repeatMap(int i2, b bVar) {
        j.e(bVar, "transform");
        e<Object> Z = a.Z(0, i2);
        ArrayList arrayList = new ArrayList(C1196n.w0(Z, 10));
        for (Object invoke : Z) {
            arrayList.add(bVar.invoke(invoke));
        }
        return arrayList;
    }

    public static final <K, V, R> Object asyncEach(Map<K, ? extends V> map, c cVar, C1227c cVar2) {
        return asyncEach(map.entrySet(), cVar, cVar2);
    }

    public static final <T> Object launchEach(T[] tArr, c cVar, C1227c cVar2) {
        return launchEach(C1192j.Z(tArr), cVar, cVar2);
    }

    public static final <T> Object launchEach(List<? extends T> list, c cVar, C1227c cVar2) {
        return launchEach(list, cVar, cVar2);
    }

    public static final <K, V> Object launchEach(Map<K, ? extends V> map, c cVar, C1227c cVar2) {
        return launchEach(map.entrySet(), cVar, cVar2);
    }
}
