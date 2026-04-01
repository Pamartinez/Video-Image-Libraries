package P2;

import P0.b;
import java.util.Collection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class I extends D {
    public final int a(p pVar) {
        o oVar = (o) pVar;
        int c5 = oVar.c() * oVar.e();
        if (c5 >= 0) {
            int i2 = this.d;
            if (i2 >= 0) {
                return i2 + c5;
            }
            throw new RuntimeException("fileOffset not yet set");
        }
        throw new IllegalArgumentException("relative < 0");
    }

    public final void e() {
        k();
        for (p a7 : c()) {
            a7.a(this.b);
        }
    }

    public final int h() {
        Collection c5 = c();
        int size = c5.size();
        if (size == 0) {
            return 0;
        }
        return ((p) c5.iterator().next()).c() * size;
    }

    public final void j(b bVar) {
        for (p d : c()) {
            d.d(this.b, bVar);
            bVar.a(this.f578c);
        }
    }

    public abstract void k();
}
