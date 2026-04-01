package E0;

import D0.a;
import android.graphics.Path;
import c0.C0086a;
import x0.C0332j;
import x0.w;
import z0.c;
import z0.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r implements b {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f149a;
    public final Path.FillType b;

    /* renamed from: c  reason: collision with root package name */
    public final String f150c;
    public final a d;
    public final a e;
    public final boolean f;

    public r(String str, boolean z, Path.FillType fillType, a aVar, a aVar2, boolean z3) {
        this.f150c = str;
        this.f149a = z;
        this.b = fillType;
        this.d = aVar;
        this.e = aVar2;
        this.f = z3;
    }

    public final c a(w wVar, C0332j jVar, F0.c cVar) {
        return new g(wVar, cVar, this);
    }

    public final String toString() {
        return C0086a.n(new StringBuilder("ShapeFill{color=, fillEnabled="), this.f149a, '}');
    }
}
