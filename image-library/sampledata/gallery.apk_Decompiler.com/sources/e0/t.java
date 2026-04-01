package E0;

import D0.a;
import N2.j;
import x0.C0332j;
import x0.w;
import z0.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t implements b {

    /* renamed from: a  reason: collision with root package name */
    public final String f153a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final a f154c;
    public final boolean d;

    public t(String str, int i2, a aVar, boolean z) {
        this.f153a = str;
        this.b = i2;
        this.f154c = aVar;
        this.d = z;
    }

    public final c a(w wVar, C0332j jVar, F0.c cVar) {
        return new z0.t(wVar, cVar, this);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("ShapePath{name=");
        sb2.append(this.f153a);
        sb2.append(", index=");
        return j.e(sb2, this.b, '}');
    }
}
