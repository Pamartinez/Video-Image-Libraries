package R2;

import S2.a;
import S2.t;
import T2.c;
import T2.d;
import U2.g;
import c0.C0086a;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k implements d, g, Comparable {
    public static final HashMap f = new HashMap(1000);
    public static final j g = new Object();
    public final int d;
    public final d e;

    public k(int i2, d dVar) {
        if (i2 < 0) {
            throw new IllegalArgumentException("reg < 0");
        } else if (dVar != null) {
            this.d = i2;
            this.e = dVar;
        } else {
            throw new NullPointerException("type == null");
        }
    }

    public static k d(int i2, d dVar) {
        HashMap hashMap = f;
        synchronized (hashMap) {
            try {
                j jVar = g;
                jVar.f651a = i2;
                jVar.b = dVar;
                k kVar = (k) hashMap.get(jVar);
                if (kVar != null) {
                    return kVar;
                }
                k kVar2 = new k(jVar.f651a, jVar.b);
                hashMap.put(kVar2, kVar2);
                return kVar2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final String a() {
        return f(true);
    }

    public final int b() {
        return this.e.b();
    }

    public final int c() {
        return this.e.getType().e();
    }

    public final int compareTo(Object obj) {
        k kVar = (k) obj;
        int i2 = kVar.d;
        int i7 = this.d;
        if (i7 < i2) {
            return -1;
        }
        if (i7 > i2) {
            return 1;
        }
        int compareTo = this.e.getType().d.compareTo(kVar.e.getType().d);
        if (compareTo != 0) {
            return compareTo;
        }
        return 0;
    }

    public final String e() {
        return C0086a.i(this.d, "v");
    }

    public final boolean equals(Object obj) {
        boolean z = obj instanceof k;
        d dVar = this.e;
        int i2 = this.d;
        if (!z) {
            if (obj instanceof j) {
                j jVar = (j) obj;
                int i7 = jVar.f651a;
                d dVar2 = jVar.b;
                if (i2 != i7 || !dVar.equals(dVar2)) {
                    return false;
                }
                return true;
            }
            return false;
        }
        k kVar = (k) obj;
        int i8 = kVar.d;
        d dVar3 = kVar.e;
        if (i2 != i8 || !dVar.equals(dVar3)) {
            return false;
        }
        return true;
    }

    public final String f(boolean z) {
        StringBuffer stringBuffer = new StringBuffer(40);
        stringBuffer.append(e());
        stringBuffer.append(NumericEnum.SEP);
        d dVar = this.e;
        c type = dVar.getType();
        stringBuffer.append(type);
        if (type != dVar) {
            stringBuffer.append("=");
            if (z && (dVar instanceof t)) {
                stringBuffer.append(((t) dVar).f());
            } else if (!z || !(dVar instanceof a)) {
                stringBuffer.append(dVar);
            } else {
                stringBuffer.append(dVar.a());
            }
        }
        return stringBuffer.toString();
    }

    public final c getType() {
        return this.e.getType();
    }

    public final int hashCode() {
        return (this.e.hashCode() * 31) + this.d;
    }

    public final String toString() {
        return f(false);
    }
}
