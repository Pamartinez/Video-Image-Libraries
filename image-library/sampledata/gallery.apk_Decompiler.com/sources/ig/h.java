package ig;

import B1.a;
import L1.d;
import Sf.b;
import Sf.q;
import Sf.r;
import a.C0068a;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kg.C1129j;
import kg.Q;
import kotlin.jvm.internal.j;
import me.i;
import me.m;
import ne.C1194l;
import ne.C1196n;
import ne.x;
import ne.z;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h implements f, C1129j {

    /* renamed from: a  reason: collision with root package name */
    public final String f4606a;
    public final C0068a b;

    /* renamed from: c  reason: collision with root package name */
    public final int f4607c;
    public final List d;
    public final HashSet e;
    public final String[] f;
    public final f[] g;

    /* renamed from: h  reason: collision with root package name */
    public final List[] f4608h;

    /* renamed from: i  reason: collision with root package name */
    public final boolean[] f4609i;

    /* renamed from: j  reason: collision with root package name */
    public final Map f4610j;
    public final f[] k;
    public final m l;

    public h(String str, C0068a aVar, int i2, List list, a aVar2) {
        j.e(str, "serialName");
        this.f4606a = str;
        this.b = aVar;
        this.f4607c = i2;
        this.d = aVar2.b;
        ArrayList arrayList = aVar2.f4602c;
        j.e(arrayList, "<this>");
        HashSet hashSet = new HashSet(z.Z(C1196n.w0(arrayList, 12)));
        C1194l.i1(arrayList, hashSet);
        this.e = hashSet;
        int i7 = 0;
        this.f = (String[]) arrayList.toArray(new String[0]);
        this.g = Q.c(aVar2.e);
        this.f4608h = (List[]) aVar2.f.toArray(new List[0]);
        ArrayList arrayList2 = aVar2.g;
        j.e(arrayList2, "<this>");
        boolean[] zArr = new boolean[arrayList2.size()];
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            zArr[i7] = ((Boolean) it.next()).booleanValue();
            i7++;
        }
        this.f4609i = zArr;
        String[] strArr = this.f;
        j.e(strArr, "<this>");
        r rVar = new r(3, new q(21, strArr));
        ArrayList arrayList3 = new ArrayList(C1196n.w0(rVar, 10));
        Iterator it2 = rVar.iterator();
        while (true) {
            b bVar = (b) it2;
            if (bVar.e.hasNext()) {
                x xVar = (x) bVar.next();
                arrayList3.add(new i(xVar.b, Integer.valueOf(xVar.f4950a)));
            } else {
                this.f4610j = z.e0(arrayList3);
                this.k = Q.c(list);
                this.l = d.q(new gg.d(1, this));
                return;
            }
        }
    }

    public final Set a() {
        return this.e;
    }

    public final C0068a b() {
        return this.b;
    }

    public final boolean c() {
        return false;
    }

    public final int d(String str) {
        j.e(str, "name");
        Integer num = (Integer) this.f4610j.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    public final int e() {
        return this.f4607c;
    }

    public final boolean equals(Object obj) {
        int i2;
        if (this == obj) {
            return true;
        }
        if (obj instanceof h) {
            f fVar = (f) obj;
            if (j.a(this.f4606a, fVar.i()) && Arrays.equals(this.k, ((h) obj).k) && (i2 = this.f4607c) == fVar.e()) {
                int i7 = 0;
                while (i7 < i2) {
                    f[] fVarArr = this.g;
                    if (j.a(fVarArr[i7].i(), fVar.h(i7).i()) && j.a(fVarArr[i7].b(), fVar.h(i7).b())) {
                        i7++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final String f(int i2) {
        return this.f[i2];
    }

    public final List g(int i2) {
        return this.f4608h[i2];
    }

    public final List getAnnotations() {
        return this.d;
    }

    public final f h(int i2) {
        return this.g[i2];
    }

    public final int hashCode() {
        return ((Number) this.l.getValue()).intValue();
    }

    public final String i() {
        return this.f4606a;
    }

    public final boolean isInline() {
        return false;
    }

    public final boolean j(int i2) {
        return this.f4609i[i2];
    }

    public final String toString() {
        return C1194l.R0(a.Z(0, this.f4607c), ArcCommonLog.TAG_COMMA, C0086a.m(new StringBuilder(), this.f4606a, '('), ")", new g(0, this), 24);
    }
}
