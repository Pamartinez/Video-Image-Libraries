package F2;

import D1.f;
import java.util.Map;

/* renamed from: F2.y  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0043y extends C0035p {
    public final Object d;
    public int e;
    public final /* synthetic */ A f;

    public C0043y(A a7, int i2) {
        this.f = a7;
        Object obj = A.m;
        this.d = a7.k()[i2];
        this.e = i2;
    }

    public final void a() {
        int i2 = this.e;
        Object obj = this.d;
        A a7 = this.f;
        if (i2 != -1 && i2 < a7.size()) {
            if (f.p(obj, a7.k()[this.e])) {
                return;
            }
        }
        Object obj2 = A.m;
        this.e = a7.e(obj);
    }

    public final Object getKey() {
        return this.d;
    }

    public final Object getValue() {
        A a7 = this.f;
        Map c5 = a7.c();
        if (c5 != null) {
            return c5.get(this.d);
        }
        a();
        int i2 = this.e;
        if (i2 == -1) {
            return null;
        }
        return a7.l()[i2];
    }

    public final Object setValue(Object obj) {
        A a7 = this.f;
        Map c5 = a7.c();
        Object obj2 = this.d;
        if (c5 != null) {
            return c5.put(obj2, obj);
        }
        a();
        int i2 = this.e;
        if (i2 == -1) {
            a7.put(obj2, obj);
            return null;
        }
        Object obj3 = a7.l()[i2];
        a7.l()[this.e] = obj;
        return obj3;
    }
}
