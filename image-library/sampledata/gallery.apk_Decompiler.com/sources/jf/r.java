package jf;

import Ff.l;
import Ff.m;
import Ve.b;
import We.C0892d;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r implements m {
    public final b d;

    public r(b bVar, l lVar) {
        j.e(lVar, "abiStability");
        this.d = bVar;
    }

    public final String a() {
        return "Class '" + C0892d.a(this.d.f3829a).a().b() + '\'';
    }

    public final String toString() {
        return r.class.getSimpleName() + ": " + this.d;
    }
}
