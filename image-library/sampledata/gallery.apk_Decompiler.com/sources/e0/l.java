package E0;

import J0.b;
import java.util.HashSet;
import x0.C0332j;
import x0.w;
import x0.x;
import z0.c;
import z0.m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l implements b {

    /* renamed from: a  reason: collision with root package name */
    public final k f138a;
    public final boolean b;

    public l(String str, k kVar, boolean z) {
        this.f138a = kVar;
        this.b = z;
    }

    public final c a(w wVar, C0332j jVar, F0.c cVar) {
        if (((HashSet) wVar.r.e).contains(x.MergePathsApi19)) {
            return new m(this);
        }
        b.b("Animation contains merge paths but they are disabled.");
        return null;
    }

    public final String toString() {
        return "MergePaths{mode=" + this.f138a + '}';
    }
}
