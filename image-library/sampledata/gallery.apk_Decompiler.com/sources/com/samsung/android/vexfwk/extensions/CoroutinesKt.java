package com.samsung.android.vexfwk.extensions;

import Ae.c;
import L2.a;
import Vf.A;
import Vf.D;
import Xf.i;
import Xf.k;
import java.util.ArrayList;
import java.util.List;
import jd.C1098a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import qe.C1227c;
import qe.C1233i;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a#\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u001aA\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r\"\u0004\b\u0000\u0010\u0000*\u00020\b2\"\u0010\f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"T", "LXf/i;", "LXf/l;", "tryReceiveLast", "(LXf/i;)Ljava/lang/Object;", "", "drain", "(LXf/i;)Ljava/util/List;", "LVf/A;", "Lkotlin/Function2;", "Lqe/c;", "", "block", "Lme/k;", "asyncAndAwait", "(LVf/A;LAe/c;)Ljava/lang/Object;", "VexFrameworkSDK_forInternalRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CoroutinesKt {
    public static final <T> Object asyncAndAwait(A a7, c cVar) {
        j.e(a7, "<this>");
        j.e(cVar, "block");
        try {
            return D.r(C1233i.d, new C1098a(cVar, (C1227c) null));
        } catch (Throwable th) {
            return a.l(th);
        }
    }

    public static final <T> List<T> drain(i iVar) {
        boolean z;
        j.e(iVar, "<this>");
        ArrayList arrayList = new ArrayList();
        do {
            Object h5 = iVar.h();
            z = h5 instanceof k;
            if (!z) {
                arrayList.add(h5);
                continue;
            }
        } while (!z);
        return arrayList;
    }

    public static final <T> Object tryReceiveLast(i iVar) {
        j.e(iVar, "<this>");
        Object h5 = iVar.h();
        if (h5 instanceof k) {
            return h5;
        }
        while (true) {
            Object h6 = iVar.h();
            if (h6 instanceof k) {
                return h5;
            }
            h5 = h6;
        }
    }
}
