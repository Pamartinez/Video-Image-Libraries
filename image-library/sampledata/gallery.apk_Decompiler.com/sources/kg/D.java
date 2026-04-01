package kg;

import ig.f;
import java.util.Arrays;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D extends T {
    public final boolean l = true;

    public D(String str, E e) {
        super(str, e, 1);
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [me.f, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r6v2, types: [me.f, java.lang.Object] */
    public final boolean equals(Object obj) {
        int i2;
        if (this == obj) {
            return true;
        }
        if (obj instanceof D) {
            f fVar = (f) obj;
            if (this.f4679a.equals(fVar.i())) {
                D d = (D) obj;
                if (d.l && Arrays.equals((f[]) this.f4683j.getValue(), (f[]) d.f4683j.getValue()) && (i2 = this.f4680c) == fVar.e()) {
                    int i7 = 0;
                    while (i7 < i2) {
                        if (j.a(h(i7).i(), fVar.h(i7).i()) && j.a(h(i7).b(), fVar.h(i7).b())) {
                            i7++;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return super.hashCode() * 31;
    }

    public final boolean isInline() {
        return this.l;
    }
}
