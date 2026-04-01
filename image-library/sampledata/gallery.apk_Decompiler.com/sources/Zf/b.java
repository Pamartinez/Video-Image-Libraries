package Zf;

import Vf.D;
import Xf.a;
import Xf.r;
import Yf.g;
import Yf.h;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.ArrayList;
import me.x;
import ne.C1194l;
import qe.C1227c;
import qe.C1232h;
import qe.C1233i;
import re.C1245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class b implements g {

    /* renamed from: a  reason: collision with root package name */
    public final C1232h f3978a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final a f3979c;

    public b(C1232h hVar, int i2, a aVar) {
        this.f3978a = hVar;
        this.b = i2;
        this.f3979c = aVar;
    }

    public abstract Object a(r rVar, C1227c cVar);

    public abstract b b(C1232h hVar, int i2, a aVar);

    public Object collect(h hVar, C1227c cVar) {
        Object d = D.d(new com.samsung.android.vexfwk.sdk.common.r(hVar, this, (C1227c) null), cVar);
        if (d == C1245a.COROUTINE_SUSPENDED) {
            return d;
        }
        return x.f4917a;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList(4);
        C1233i iVar = C1233i.d;
        C1232h hVar = this.f3978a;
        if (hVar != iVar) {
            arrayList.add("context=" + hVar);
        }
        int i2 = this.b;
        if (i2 != -3) {
            arrayList.add("capacity=" + i2);
        }
        a aVar = a.SUSPEND;
        a aVar2 = this.f3979c;
        if (aVar2 != aVar) {
            arrayList.add("onBufferOverflow=" + aVar2);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getClass().getSimpleName());
        sb2.append('[');
        return C0086a.m(sb2, C1194l.R0(arrayList, ArcCommonLog.TAG_COMMA, (String) null, (String) null, (Ae.b) null, 62), ']');
    }
}
