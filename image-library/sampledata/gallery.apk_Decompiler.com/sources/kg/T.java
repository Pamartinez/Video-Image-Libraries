package kg;

import L1.d;
import a.C0068a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import gg.a;
import ig.f;
import ig.g;
import ig.l;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.j;
import me.h;
import ne.C1194l;
import ne.C1202t;
import ne.C1203u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class T implements f, C1129j {

    /* renamed from: a  reason: collision with root package name */
    public final String f4679a;
    public final A b;

    /* renamed from: c  reason: collision with root package name */
    public final int f4680c;
    public int d = -1;
    public final String[] e;
    public final List[] f;
    public final boolean[] g;

    /* renamed from: h  reason: collision with root package name */
    public Object f4681h;

    /* renamed from: i  reason: collision with root package name */
    public final Object f4682i;

    /* renamed from: j  reason: collision with root package name */
    public final Object f4683j;
    public final Object k;

    public T(String str, A a7, int i2) {
        this.f4679a = str;
        this.b = a7;
        this.f4680c = i2;
        String[] strArr = new String[i2];
        for (int i7 = 0; i7 < i2; i7++) {
            strArr[i7] = "[UNINITIALIZED]";
        }
        this.e = strArr;
        int i8 = this.f4680c;
        this.f = new List[i8];
        this.g = new boolean[i8];
        this.f4681h = C1203u.d;
        h hVar = h.PUBLICATION;
        this.f4682i = d.p(hVar, new S(this, 1));
        this.f4683j = d.p(hVar, new S(this, 2));
        this.k = d.p(hVar, new S(this, 0));
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.util.Map, java.lang.Object] */
    public final Set a() {
        return this.f4681h.keySet();
    }

    public C0068a b() {
        return l.d;
    }

    public final boolean c() {
        return false;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.util.Map, java.lang.Object] */
    public final int d(String str) {
        j.e(str, "name");
        Integer num = (Integer) this.f4681h.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    public final int e() {
        return this.f4680c;
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [me.f, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r6v2, types: [me.f, java.lang.Object] */
    public boolean equals(Object obj) {
        int i2;
        if (this == obj) {
            return true;
        }
        if (obj instanceof T) {
            f fVar = (f) obj;
            if (this.f4679a.equals(fVar.i()) && Arrays.equals((f[]) this.f4683j.getValue(), (f[]) ((T) obj).f4683j.getValue()) && (i2 = this.f4680c) == fVar.e()) {
                int i7 = 0;
                while (i7 < i2) {
                    if (j.a(h(i7).i(), fVar.h(i7).i()) && j.a(h(i7).b(), fVar.h(i7).b())) {
                        i7++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final String f(int i2) {
        return this.e[i2];
    }

    public final List g(int i2) {
        List list = this.f[i2];
        if (list == null) {
            return C1202t.d;
        }
        return list;
    }

    public final List getAnnotations() {
        return C1202t.d;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public f h(int i2) {
        return ((a[]) this.f4682i.getValue())[i2].getDescriptor();
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public int hashCode() {
        return ((Number) this.k.getValue()).intValue();
    }

    public final String i() {
        return this.f4679a;
    }

    public boolean isInline() {
        return false;
    }

    public final boolean j(int i2) {
        return this.g[i2];
    }

    public final void k(String str, boolean z) {
        j.e(str, "name");
        int i2 = this.d + 1;
        this.d = i2;
        String[] strArr = this.e;
        strArr[i2] = str;
        this.g[i2] = z;
        this.f[i2] = null;
        if (i2 == this.f4680c - 1) {
            HashMap hashMap = new HashMap();
            int length = strArr.length;
            for (int i7 = 0; i7 < length; i7++) {
                hashMap.put(strArr[i7], Integer.valueOf(i7));
            }
            this.f4681h = hashMap;
        }
    }

    public String toString() {
        return C1194l.R0(B1.a.Z(0, this.f4680c), ArcCommonLog.TAG_COMMA, this.f4679a.concat("("), ")", new g(2, this), 24);
    }
}
