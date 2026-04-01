package E0;

import java.util.Arrays;
import java.util.List;
import x0.C0332j;
import x0.w;
import z0.c;
import z0.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s implements b {

    /* renamed from: a  reason: collision with root package name */
    public final String f151a;
    public final List b;

    /* renamed from: c  reason: collision with root package name */
    public final boolean f152c;

    public s(String str, List list, boolean z) {
        this.f151a = str;
        this.b = list;
        this.f152c = z;
    }

    public final c a(w wVar, C0332j jVar, F0.c cVar) {
        return new d(wVar, cVar, this, jVar);
    }

    public final String toString() {
        return "ShapeGroup{name='" + this.f151a + "' Shapes: " + Arrays.toString(this.b.toArray()) + '}';
    }
}
