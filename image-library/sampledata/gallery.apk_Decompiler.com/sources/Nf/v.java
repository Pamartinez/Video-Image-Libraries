package Nf;

import Ae.b;
import L1.d;
import bf.g;
import kotlin.jvm.internal.j;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class v implements d {

    /* renamed from: a  reason: collision with root package name */
    public final b f3605a;
    public final String b;

    public v(String str, b bVar) {
        this.f3605a = bVar;
        this.b = "must return ".concat(str);
    }

    public final boolean a(g gVar) {
        return j.a(gVar.k, this.f3605a.invoke(C1353d.e(gVar)));
    }

    public final String b(g gVar) {
        return d.n(this, gVar);
    }

    public final String getDescription() {
        return this.b;
    }
}
