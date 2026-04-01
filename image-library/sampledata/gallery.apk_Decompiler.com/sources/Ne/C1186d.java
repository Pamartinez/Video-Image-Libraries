package ne;

import java.util.RandomAccess;

/* renamed from: ne.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1186d extends C1187e implements RandomAccess {
    public final C1187e d;
    public final int e;
    public final int f;

    public C1186d(C1187e eVar, int i2, int i7) {
        this.d = eVar;
        this.e = i2;
        C1184b bVar = C1187e.Companion;
        int size = eVar.size();
        bVar.getClass();
        C1184b.c(i2, i7, size);
        this.f = i7 - i2;
    }

    public final Object get(int i2) {
        C1187e.Companion.getClass();
        C1184b.a(i2, this.f);
        return this.d.get(this.e + i2);
    }

    public final int p() {
        return this.f;
    }
}
